package com.ui.automation.elements.controls.grid;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.api.ElementCollection;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.CheckBox;

import java.util.List;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class Grid extends BaseElement {

    protected final String GRID_ROW_CLASS = "slick-row";
    protected final String GRID_CELL_CLASS = "slick-cell";

    public Grid(Element parent) {
        super(Locator.xpath(".//*[contains(@data-aid, 'alm-entity-grid')]"), parent);
    }

    public Grid(Locator self, Element parent) {
        super(self, parent);
    }

    /**
     * Click on grid cell that have the given value
     *
     * @param text
     */
    public void clickCellWithText(String text) {
        Element cell = getCellByText(text);
        getDriver().actions().click(cell);
    }

    public void clickCellWithExactText(String text) {
        Element cell = getCellWithExactText(text);
        getDriver().actions().click(cell);
    }

    public void sortByColumn(String columnPartialDataAid) {
        gridHeader().clickColumn(columnPartialDataAid);
    }

    public void expectColumnValue(String columnPartialDataAid, String value) {
        GridCellContainingText cell = getGridCellContainingText(columnPartialDataAid, value);

        getDriver().expects().elementToContainText(cell, value);
    }

    public void expectColumnsValuesNumberOfAppearances(String columnPartialDataAid, String value, Integer numberOfAppearances) {
        GridCellContainingText cell = getGridCellContainingText(columnPartialDataAid, value);
        getDriver().expects().numberOfElementsToBe(cell, numberOfAppearances);
    }

    private GridCellContainingText getGridCellContainingText(String columnPartialDataAid, String value) {
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        final int columnIndex = getDriver().finds().index(column);

        // Returns the cell in all grid rows
        return new GridCellContainingText(columnIndex, viewport(), value);
    }

    public void expectNoColumnValue(String columnPartialDataAid, String value) {
        GridCellContainingText cell = getGridCellContainingText(columnPartialDataAid, value);

        getDriver().expects().elementNotToContainText(cell, value);
    }

    public void expectColumnValueByUnique(String columnPartialDataAid, String valueToVerify, String uniqueData) {
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        final int columnIndex = getDriver().finds().index(column);

        // Returns the cell in all grid rows
        GridCellByIdentifier cell = new GridCellByIdentifier(columnIndex, viewport(), uniqueData);

        getDriver().expects().elementToContainText(cell, valueToVerify);
    }

    public void clickColumnValueByUnique(String columnPartialDataAid, String valueToClick, String uniqueData){
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        final int columnIndex = getDriver().finds().index(column);

        // Returns the cell in all grid rows
        GridCellByIdentifier cell = new GridCellByIdentifier(columnIndex, viewport(), uniqueData);
        BaseElement valueInCell = new BaseElement(Locator.xpath(".//*[text()='" + valueToClick + "']"), cell);
        getDriver().actions().click(valueInCell);
    }

    public void expectColumnCheckboxValueByUnique(String checkBoxcolumnPartialDataAid, boolean isChecked, String uniqueData) {
        GridColumn column = new GridColumn(checkBoxcolumnPartialDataAid, this);
        final int columnIndex = getDriver().finds().index(column);

        // Returns the cell in all grid rows
        GridCellByIdentifier cell = new GridCellByIdentifier(columnIndex, viewport(), uniqueData);

        CheckBox cb = new CheckBox(Locator.className("checkboxes"), cell);
        if (isChecked)
            cb.expectChecked();
        else {
            cb.expectUnChecked();
        }
    }

    public void expectColumnDisplayed(String columnPartialDataAid) {
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        getDriver().expects().elementToBeVisible(column);
    }

    public void expectColumnHidden(String columnPartialDataAid) {
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        getDriver().expects().elementNotVisible(this, column);
    }

    public GridHeader gridHeader() {
        return new GridHeader(this);
    }

    public void setRowCheckBoxByExactText(String... text) {
        checkRowsByText(new GridCellIdentifierFactory() {
            public Element createGridIdentifier(String t) {
                return new GridCellByExactIdentifier(0, viewport(), t);
            }
        }, text);
    }

    public void setRowCheckBoxByContainsTextWithoutClearingSelection(String... text) {
        checkRowsByTextWithoutClearingSelection(new GridCellIdentifierFactory() {
            public Element createGridIdentifier(String t) {
                return new GridCellByIdentifier(0, viewport(), t);
            }
        }, text);
    }

    public void unsetRowCheckBoxByContainsText(String... text) {
        uncheckRowsByText(new GridCellIdentifierFactory() {
            public Element createGridIdentifier(String t) {
                return new GridCellByIdentifier(0, viewport(), t);
            }
        }, text);
    }

    public void setRowCheckBoxByContainsText(String... text) {
        checkRowsByText(new GridCellIdentifierFactory() {
            public Element createGridIdentifier(String t) {
                return new GridCellByIdentifier(0, viewport(), t);
            }
        }, text);
    }

    private static interface GridCellIdentifierFactory {
        Element createGridIdentifier(String text);
    }

    private void checkRowsByText(GridCellIdentifierFactory gridCellIdFactory, String... text) {
        //clear selection first
        setSelectAllCheckBox(false);
        checkRowsByTextWithoutClearingSelection(gridCellIdFactory, text);
    }

    private void checkRowsByTextWithoutClearingSelection(GridCellIdentifierFactory gridCellIdFactory, String... text) {
        CheckBox checkBox;
        for (String t : text) {
            Element identifier = gridCellIdFactory.createGridIdentifier(t);
            checkBox = new CheckBox(identifier);
            checkBox.set(true);
        }
    }

    private void uncheckRowsByText(GridCellIdentifierFactory gridCellIdFactory, String... text) {
        CheckBox checkBox;
        for (String t : text) {
            Element identifier = gridCellIdFactory.createGridIdentifier(t);
            checkBox = new CheckBox(identifier);
            checkBox.set(false);
        }
    }

    public void clickSelectAllCheckBox() {

        gridHeader().clickColumn("isSelected");
    }

    public void setSelectAllCheckBox(Boolean check) {
        gridHeader().setSelectAllCheckbox(check);
    }


    /**
     * Check if the grid have cell with given text value
     *
     * @param text
     * @return
     */
    public void expectCellWithText(String text) {
        Element cell = getCellByText(text);
        getDriver().expects().elementTextToBeEqual(cell, text);
    }

    public void expectCellWithDescendantAttributeToBeVisible(String attr, String value) {
        Element cellElement = new BaseElement(Locator.xpath(".//*[contains(@" + attr + ",'" + value + "') and ancestor-or-self::div[contains(@class,'slick-cell')]]"), viewport());
        getDriver().expects().elementToBeVisible(cellElement);
    }

    public void expectCellWithTextNotExist(String text) {
        Element cell = getCellByText(text);
        getDriver().expects().elementNotVisible(this, cell);
    }

    public void expectMarkedRowWithCellText(String rowMarkerClassName, String cellText) {
        Element cell = getRowWithMarkerAndCellByText(rowMarkerClassName, cellText);
        getDriver().expects().elementToBeVisible(cell);
    }

    public void expectNumberOfRows(int number) {
        Element row = new BaseElement(Locator.className("slick-row"), this.viewport());
        getDriver().expects().numberOfElementsToBe(row, number);
    }

    public void expectRowsOrder(String columnPartialDataAid, List<String> cellsTexts) {
        GridColumn column = new GridColumn(columnPartialDataAid, this);
        final int columnIndex = getDriver().finds().index(column);

        ElementCollection<BaseElement> cells = getDriver().expects().collection(new BaseElement(Locator.xpath(".//*[contains(@class, 'l" + columnIndex + "')]"), this), cellsTexts.size());
        for(int i=0;i<cellsTexts.size();i++) {
            getDriver().expects().elementToContainText(cells.get(i), cellsTexts.get(i));
        }
    }

    public Element getCellByText(String text) {
        return new BaseElement(Locator.xpath(".//*[contains(text(),'" + text + "') and ancestor-or-self::div[contains(@class,'" + GRID_CELL_CLASS + "')]]"), viewport());
    }

    public void expectCellToContainFormattedText(String text) {
        String xpath="";
        String[] splitedTextBySpace = text.split(" ");

        for(String t: splitedTextBySpace){
            xpath += " and descendant-or-self::*[contains(text(),'" + t + "')]";
        }

        Element p = new BaseElement(Locator.xpath(".//*[ancestor-or-self::div[contains(@class,'" + GRID_CELL_CLASS + "')]" + xpath + "]"), viewport());
        getDriver().expects().elementToBeVisible(p);
        getDriver().expects().elementToContainText(p, text);
    }

    public Element getRowWithMarkerAndCellByText(String markerClass,String text) {
        return new BaseElement(Locator.xpath(".//*[contains(text(),'" + text + "') and ancestor-or-self::div[contains(@class,'" + GRID_ROW_CLASS + "') and contains(@class,'" + markerClass + "')]]"), viewport());
    }

    public Element getCellByColumn(String markerClass,String text) {
        return new BaseElement(Locator.xpath(".//*[contains(text(),'" + text + "') and ancestor-or-self::div[contains(@class,'" + GRID_ROW_CLASS + "') and contains(@class,'" + markerClass + "')]]"), viewport());
    }

    private Element getCellWithExactText(String text) {
        return new BaseElement(Locator.xpath(".//*[text()='" + text + "' and ancestor-or-self::div[contains(@class,'" + GRID_CELL_CLASS + "')]]"), viewport());
    }

    private Element getUniqueCellWithExactText(String unique, String text){
        return new BaseElement(Locator.xpath(".//*[text()='" + text + "' and ancestor-or-self::div[contains(@class,'" + unique + "')]]"), viewport());
    }


    protected Element viewport() {
        return new BaseElement(Locator.className("slick-viewport"), this);
    }

    private static class GridColumn extends BaseElement {
        public GridColumn(String columnPartialDataAid, Element parent) {
            super(Locator.dataAid("grid_header_" + columnPartialDataAid), parent);
        }
    }


    private static class GridCellContainingText extends BaseElement {
        public GridCellContainingText(int cellIndex, Element parent, String text) {
            super(Locator.xpath(".//*[contains(@class, 'l" + cellIndex + "')" + // finds the cell with the expected value
                    " and descendant-or-self::*[contains(text(), '" + text + "')]]"), parent); // Adds the text condition
        }
    }

    private static class GridCellByIdentifier extends BaseElement {
        public GridCellByIdentifier(int cellIndex, Element parent, String identifier) {
            // Search for the unique value in siblings, e.g.
            // $x("//*[contains(@class, 'l1 r1') and (./preceding-sibling::*[./descendant-or-self::*[contains(text(), '♂')]]|./following-sibling::*[./descendant-or-self::*[contains(text(), '♂')]])]");
            super(Locator.xpath(".//*[contains(@class, 'l" + cellIndex + "')" + // finds the cell with the expected value
                            " and (" +
                            "./preceding-sibling::*[./descendant-or-self::*[contains(text(), '" + identifier + "')]]" + // Searches for the identifier in the previous cells and their DOM element sub trees
                            " | " +
                            "./following-sibling::*[./descendant-or-self::*[contains(text(), '" + identifier + "')]]" + // Searches for the identifier in the following cells and their DOM element sub trees
                            ")]"),
                    parent);
        }

    }

    private static class GridCellByExactIdentifier extends BaseElement {
        public GridCellByExactIdentifier(int cellIndex, Element parent, String identifier) {
            // Search for the unique value in siblings, e.g.
            // $x("//*[contains(@class, 'l1 r1') and (./preceding-sibling::*[./descendant-or-self::*[contains(text(), '♂')]]|./following-sibling::*[./descendant-or-self::*[contains(text(), '♂')]])]");
            super(Locator.xpath(".//*[contains(@class, 'l" + cellIndex + "')" + // finds the cell with the expected value
                            " and (" +
                            "./preceding-sibling::*[./descendant-or-self::*[text()='" + identifier + "']]" + // Searches for the identifier in the previous cells and their DOM element sub trees
                            " | " +
                            "./following-sibling::*[./descendant-or-self::*[text()='" + identifier + "']]" + // Searches for the identifier in the following cells and their DOM element sub trees
                            ")]"),
                    parent);
        }

    }
}
