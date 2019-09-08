package com.ui.automation.selenium.service;

import com.ui.automation.locator.Locator;
import com.ui.automation.locator.LocatorType;
import com.ui.automation.selenium.ElementCollectionLocator;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Translation from MaaS {@link Locator} to Selenium's {@link By} objects
 */
@Component
public class LocatorService {

    private Logger logger = Logger.getLogger(getClass().getName());
    private final static int FIND_ELEMENT_WARNING_MILLIS = 800;
    private final static int FIND_ELEMENT_WARNING_SIZE = 20;
    private final static int OVERFLOW_SCROLL_TO_BOTTOM_GRACE_MILLIS = 1000;
    private final static int GRACE_PERIOD_ADDITION = 200;

    /**
     * Returns a single By object that represents the given ordered list of locators.
     * The implementation assumes that the given locator is list is hierarchical,
     * i.e. locators[n] is a DOM element ancestor of locators[n+1].
     *
     * @param locators
     * @return
     */
    public By getByLocators(List<Locator> locators, JavascriptExecutor javascriptExecutor) {
        ArrayList<By> bys = getByList(locators);
        return new ByMaasChained(javascriptExecutor, bys.toArray(new By[bys.size()]));
    }

    public String getXpath(List<Locator> locators) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Locator locator : locators) {
            By by = getByXpath(locator.getType(), locator.getExpression());
            String exp = by.toString().replace("By.xpath: .", "");
            exp = exp.replace("By.xpath: ", "");
            stringBuilder.insert(0, exp);
        }
        return stringBuilder.toString();
    }

    private static By getBy(LocatorType type, String expression) {
        switch (type) {
            case ID:
                return By.id(expression);
            case DATA_AID:
                return By.xpath(".//*[@data-aid=\"" + expression + "\"]");
            case CLASS_NAME:
                return By.className(expression);
            case CSS:
                return By.cssSelector(expression);
            case XPATH:
                return By.xpath(expression);
            default:
                throw new RuntimeException("Unknown locator type " + type);
        }
    }

    private static By getByXpath(LocatorType type, String expression) {
        switch (type) {
            case ID:
                return By.xpath(".//*[@id='" + expression + "']");
            case DATA_AID:
                return By.xpath(".//*[@data-aid='" + expression + "']");
            case CLASS_NAME:
                return By.xpath(".//*[contains(@class,'" + expression + "')]");
            case CSS:
                return By.cssSelector(expression);
            case XPATH:
                return By.xpath(expression);
            default:
                throw new RuntimeException("Unknown locator type " + type);
        }
    }

    public By getByFailedLocators(List<Locator> locators) {
        ArrayList<By> bys = getByList(locators);
        return new ByFailedChained(bys.toArray(new By[bys.size()]));
    }

    private ArrayList<By> getByList(List<Locator> locators) {
        ArrayList<By> bys = new ArrayList<>(locators.size());
        for (Locator locator : locators) {
            if (locator.getClass().isAssignableFrom(ElementCollectionLocator.class)) {
                bys.add(new ByXpathPosition(locator.getExpression(), ((ElementCollectionLocator) locator).getIndex()));
            } else {
                bys.add(getBy(locator.getType(), locator.getExpression()));
            }
        }
        Collections.reverse(bys);
        return bys;
    }

    public class ByFailedChained extends By {

        private By[] bys;

        public ByFailedChained(By... bys) {
            this.bys = bys;
        }

        @Override
        public WebElement findElement(SearchContext context) {
            List<WebElement> elements = findElements(context);
            if (elements.isEmpty())
                throw new NoSuchElementException("Cannot locate an element using " + toString());
            return elements.get(0);
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            if (bys.length == 0) {
                return Collections.emptyList();
            }

            List<WebElement> elems = null;
            for (int i = 0; i < bys.length; i++) {
                By by = bys[i];

                List<WebElement> newElems = new ArrayList<>();

                if (elems == null) {
                    // Finding root element in chain
                    newElems.addAll(by.findElements(context));
                    if (newElems.size() == 0) {
                        throw new NoSuchElementException("Failed to locate root element in chained: " + by.toString());
                    }
                } else {
                    // Finding descendant elements in chain
                    for (WebElement elem : elems) {
                        List<WebElement> tempList = elem.findElements(by);
                        newElems.addAll(tempList);
                    }
                }
                // Check that some elements were found in the chain
                // It is ok that leaf elements are not found - in this case the returned list should be with size 0
                if (newElems.size() == 0) {
                    if (i < bys.length - 1) {
                        throw new NoSuchElementException("Failed to located the next element in chained: " + by.toString());
                    }
                    else {
                        logger.info("Couldn't find elements for leaf by:" + by.toString());
                    }
                }
                elems = newElems;
            }

            return elems;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("By.chained(");
            stringBuilder.append("{");

            boolean first = true;
            for (By by : bys) {
                stringBuilder.append((first ? "" : ",")).append(by);
                first = false;
            }
            stringBuilder.append("})");
            return stringBuilder.toString();
        }


    }

    private class ByMaasChained extends By {
        private By[] bys;
        JavascriptExecutor javascriptExecutor;

        public ByMaasChained(JavascriptExecutor javascriptExecutor, By... bys) {
            this.bys = bys;
            this.javascriptExecutor = javascriptExecutor;
        }

        @Override
        public WebElement findElement(SearchContext context) {
            List<WebElement> elements = findElements(context);
            if (elements.isEmpty())
                throw new NoSuchElementException("Cannot locate an element using " + toString());
            return elements.get(0);
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            if (bys.length == 0) {
                return Collections.emptyList();
            }

            List<WebElement> elems = null;
            for (By by : bys) {
                final long startTime = System.currentTimeMillis();
                List<WebElement> newElems = new ArrayList<>();
                if (elems == null) {
                    // If no previous elements were found, search for elements without a parent context, directly from the by object
                    List<WebElement> tempElems = by.findElements(context);
                    if (ByXpathPosition.class.isInstance(by)) {
                        // This is a special by object that refers to an index in the parent by
                        // Start with sorting the elements by their rendering location
                        Collections.sort(tempElems, WebElementLocationComparator.getInstance());
                        newElems.add(tempElems.get(((ByXpathPosition) by).getIndex()));
                    } else {
                        newElems.addAll(tempElems);
                    }
                } else {
                    if (ByXpathPosition.class.isInstance(by)) {
                        // This is a special by object that refers to an index in the parent by
                        // Start with sorting the elements by their rendering location
                        Collections.sort(elems, WebElementLocationComparator.getInstance());
                        newElems.add(elems.get(((ByXpathPosition) by).getIndex()));
                    } else {
                        for (WebElement elem : elems) {
                            // Search for the new elements in the context of the parents already found
                            List<WebElement> tempElems = findElementsScrollDown(elem, by, OVERFLOW_SCROLL_TO_BOTTOM_GRACE_MILLIS);
                            newElems.addAll(tempElems);
                        }
                    }
                }
                elems = newElems;

                logSlowFinds(newElems, by, startTime);
            }
            return elems;
        }

        private List<WebElement> findElementsScrollDown(WebElement searchContext, By by, int lazyLoadingGracePeriodMillis) {
            List<WebElement> tempElems = searchContext.findElements(by);
            if (tempElems.isEmpty()) {
                // No elements were found in the parent element search context.
                // Check if that parent element has any scrolling that can be done and search again
                if (!isAtEndOfScroll(searchContext)) {
                    if (scrollDownOverflowElement(searchContext, lazyLoadingGracePeriodMillis)) {
                        // Scroll succeeded
                        // Wait a little longer next time (200 ms) when reaching lazy loading
                        return findElementsScrollDown(searchContext, by, lazyLoadingGracePeriodMillis + GRACE_PERIOD_ADDITION);
                    } else {
                        return tempElems;
                    }
                } else {
                    // Already scrolled all the way down, now scroll up, try to find the element there
                    if (scrollUpOverflowElement(searchContext)) {
                        // Scroll succeeded
                        return findElementsScrollUp(searchContext, by);
                    } else {
                        return tempElems;
                    }
                }
            }
            return tempElems;
        }

        private List<WebElement> findElementsScrollUp(WebElement searchContext, By by) {
            List<WebElement> tempElems = searchContext.findElements(by);
            if (tempElems.isEmpty() && !isAtTopOfScroll(searchContext)) {
                if (scrollUpOverflowElement(searchContext)) {
                    return findElementsScrollUp(searchContext, by);
                } else {
                    return tempElems;
                }
            }
            return tempElems;
        }

        /**
         * Causes the current thread to sleep for the given duration.
         *
         * @param milliseconds milliseconds ot wait
         */
        private void sleep(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        // Returns true if element is at end of scroll, false otherwise
        // See https://developer.mozilla.org/en-US/docs/Web/API/Element.scrollHeight
        private boolean isAtEndOfScroll(WebElement element) {
            // scrollTop is the number of number of pixels scrolled down from the top (0 when scrolled all the way up)
            // scrollHeight is the height of the scroll view of an element. It includes the element padding but not its margin.
            // clientHeight is the inner height of an element in pixels, including padding but not the horizontal scrollbar height, border, or margin. (the displayed size of an overflow element)
            final boolean isAtEnd = (boolean) javascriptExecutor.executeScript("return arguments[0].scrollHeight - arguments[0].scrollTop === arguments[0].clientHeight", element);
            // Debugging purposes - START
            try {
                if (!isAtEnd) {
                    long scrollHeight = (long) javascriptExecutor.executeScript("return arguments[0].scrollHeight", element);
                    long scrollTop = (long) javascriptExecutor.executeScript("return arguments[0].scrollTop", element);
                    long clientHeight = (long) javascriptExecutor.executeScript("return arguments[0].clientHeight", element);
                    logger.warning("Scrolling [" + element.getTagName() + "] [" + element.getAttribute("class") + "] scrollHeight = " + scrollHeight + " scrollTop = " + scrollTop + " clientHeight = " + clientHeight);
                }
            } catch (Exception e) {
                // Swallow - this code is strictly for debugging
                e.printStackTrace();
            }
            // Debugging purposes - END
            return isAtEnd;
        }

        private boolean isAtTopOfScroll(WebElement element) {
            return (boolean) javascriptExecutor.executeScript("return arguments[0].scrollTop === 0", element);
        }

        // Returns true if scroll succeeded, false otherwise
        private boolean scrollDownOverflowElement(WebElement element, int lazyLoadingGracePeriodMillis) {
            long scrollTopBefore = (long) javascriptExecutor.executeScript("return arguments[0].scrollTop", element);
            javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].clientHeight", element);
            if (isAtEndOfScroll(element)) {
                // Wait for 1 second to give additional async results a chance to load (e.g. select2 drop downs)
                sleep(lazyLoadingGracePeriodMillis);
            }
            long scrollTopAfter = (long) javascriptExecutor.executeScript("return arguments[0].scrollTop", element);
            return scrollTopAfter != scrollTopBefore;
        }

        // Returns true if scroll succeeded, false otherwise
        private boolean scrollUpOverflowElement(WebElement element) {
            long scrollTopBefore = (long) javascriptExecutor.executeScript("return arguments[0].scrollTop", element);
            javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollTop - arguments[0].clientHeight", element);
            long scrollTopAfter = (long) javascriptExecutor.executeScript("return arguments[0].scrollTop", element);
            return scrollTopAfter != scrollTopBefore;
        }


        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("By.chained(");
            stringBuilder.append("{");

            boolean first = true;
            for (org.openqa.selenium.By by : bys) {
                stringBuilder.append((first ? "" : ",")).append(by);
                first = false;
            }
            stringBuilder.append("})");
            return stringBuilder.toString();
        }

        private void logSlowFinds(List<WebElement> newElems, By by, long startTime) {
            /* DEBUG test execution time - START */
            if (newElems.size() > FIND_ELEMENT_WARNING_SIZE) {
                // Log iterations that took found more than x elements to locate performance bottlenecks in tests
                logger.warning("[Slow] Finding " + by + " returned " + newElems.size() + " results");
            }
            final long endTime = System.currentTimeMillis();
            final long findElementTime = endTime - startTime;
            if (findElementTime > FIND_ELEMENT_WARNING_MILLIS) {
                // Log iterations that took more than x millis to locate performance bottlenecks in tests
                logger.warning("[Slow] Finding " + by + " took " + findElementTime + " millis");
            }
            /* DEBUG test execution time - END */
        }
    }

    private static class ByXpathPosition extends By.ByXPath {

        private final int index;

        public ByXpathPosition(String xpathExpression, int index) {
            super(xpathExpression);
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private static class WebElementLocationComparator implements Comparator<WebElement> {

        private static final WebElementLocationComparator instance = new WebElementLocationComparator();

        private WebElementLocationComparator() {
        }

        public static WebElementLocationComparator getInstance() {
            return instance;
        }

        @Override
        public int compare(WebElement o1, WebElement o2) {
            // Return elements that are on top first
            int result;
            if (o1.getLocation().getY() != o2.getLocation().getY()) {
                result = o1.getLocation().getY() - o2.getLocation().getY();
            } else {
                result = o1.getLocation().getX() - o2.getLocation().getX();
            }
            return result;
        }
    }
}
