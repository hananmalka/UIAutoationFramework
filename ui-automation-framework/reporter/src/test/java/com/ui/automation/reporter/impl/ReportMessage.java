package com.ui.automation.reporter.impl;

import com.hp.gagawa.java.elements.*;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.reporter.api.StepLogType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

import static com.ui.automation.app.eventBus.TestEvent.IS_FAILED;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/03/14
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class ReportMessage {
    private StepLogType type;
    // on AUTO STEP this will be teh
    private String message;
    private String hour;
    private Throwable stepFailureException;
    /*
    AUTO STEP - The details of collapsed panel
     */
    private Map<String, String> details;

    /**
     * Step unique id
     */
    private final String uuid;


    /**
     * This will be constractor of auto step
     *
     * @param type    - the type of action (e.g. clicking, hovering, expect visible etc...
     * @param message - the message will be the element list as string
     * @param details - the collapsed panel text
     */
    public ReportMessage(StepLogType type, String message, Map<String, String> details) {
        this(type, message, details, UUID.randomUUID().toString());
    }

    /**
     * This will be constractor of auto step
     *
     * @param type    - the type of action (e.g. clicking, hovering, expect visible etc...
     * @param message - the message will be the element list as string
     * @param details - the collapsed panel text
     */
    public ReportMessage(StepLogType type, String message, Map<String, String> details, String uuid) {
        this.hour = getHour();
        this.type = type;
        this.message = message;
        this.details = details;
        this.uuid = uuid;
    }




    public ReportMessage(StepLogType type, String message) {
        this(type, message, Collections.<String, String>emptyMap(), UUID.randomUUID().toString());
    }

    private String getHour() {
        DateFormat dateFormat = new SimpleDateFormat(CssProperties.HOUR_FORMAT.value());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Tr getBasicStep() {
        Tr tr = new Tr();
        Td time = new Td();
        tr.appendChild(time);
        time.appendText(this.hour);
        Td step = new Td();
        tr.appendChild(step);
        Div div = new Div();
        step.appendChild(div);
        Span sp = new Span();
        div.appendChild(sp);
        Div divDetails = new Div();
        sp.appendText(this.message);
        if (this.type.equals(StepLogType.INFO)) {
            tr.setCSSClass(CssProperties.STEP_INFO.value());
            div.setCSSClass(CssProperties.IMG_STEP_INFO.value());
        } else if (this.type.equals(StepLogType.TESTSTEP)) {
            tr.setCSSClass(CssProperties.STEP_TEST.value());
            div.setCSSClass(CssProperties.IMG_STEP_TEST.value());
        } else if (this.type.equals(StepLogType.WARNING)) {
            tr.setCSSClass(CssProperties.STEP_WARN.value());
            div.setCSSClass(CssProperties.IMG_STEP_WARN.value());
        } else if (this.type.equals(StepLogType.ERROR)) {
            tr.setCSSClass(CssProperties.STEP_ERROR.value());
            div.setCSSClass(CssProperties.IMG_STEP_ERROR.value());
        } else if (this.type.equals(StepLogType.DEBUG)) {
            tr.setCSSClass(CssProperties.STEP_DEBUG.value());
            div.setCSSClass(CssProperties.IMG_STEP_DEBUG.value());
        }

        if (details.size() > 0) {
            sp.setCSSClass(CssProperties.IMG_EXPAND.value());
            divDetails.setStyle("display: none;");
            sp.setAttribute("onclick", "expColDetails(this)");
            divDetails.setCSSClass("step-details");
            step.appendChild(divDetails);
            Span spDetails = new Span();
            spDetails.setCSSClass("by-str");
            divDetails.appendChild(spDetails);
            Div detailsColPanel = new Div();
            spDetails.appendChild(detailsColPanel);


            Iterator it = details.entrySet().iterator();

            while (it.hasNext()) {
                Span detsP = new Span();

                Map.Entry pairs = (Map.Entry) it.next();
                detailsColPanel.appendChild(detsP);
                detailsColPanel.setCSSClass("failure-container");
                detsP.appendText("<b>" + pairs.getKey() + ": </b> " + pairs.getValue() + "<br/>");
            }
            if (this.type.equals(StepLogType.ERROR)) {
                divDetails.setStyle("display: block;");
                div.setCSSClass(CssProperties.IMG_STEP_FUNCTIONAL.value() + " " + CssProperties.COLOR_RED_CLASS.value());

            }
        }

        return tr;
    }

    private Tr getAutoStep() {
        Tr tr = new Tr();
        Td time = new Td();
        tr.appendChild(time);
        time.appendText(this.hour);

        Td step = new Td();
        tr.appendChild(step);
        Div div = new Div();
        step.appendChild(div);
        Span sp = new Span();
        div.appendChild(sp);
        Div divDetails = new Div();

        boolean isFailed = false;
        if (details.containsKey(IS_FAILED)) {
            isFailed = true;
            sp.appendText("FAILURE " + this.message);
            div.setCSSClass(CssProperties.IMG_STEP_FUNCTIONAL.value() + " " + CssProperties.COLOR_RED_CLASS.value());
            divDetails.setStyle("display: block;");
            sp.setCSSClass(CssProperties.IMG_COLLAPSE.value());
        } else {
            sp.appendText(this.message);
            div.setCSSClass(CssProperties.IMG_STEP_FUNCTIONAL.value());
            divDetails.setStyle("display: none;");
            sp.setCSSClass(CssProperties.IMG_EXPAND.value());
        }

        tr.setCSSClass(CssProperties.STEP_FUNCTIONAL.value());
        sp.setAttribute("onclick", "expColDetails(this)");
        divDetails.setCSSClass("step-details");

        step.appendChild(divDetails);
        Span spDetails = new Span();
        spDetails.setCSSClass("by-str");
        divDetails.appendChild(spDetails);
        Div detailsColPanel = new Div();
        spDetails.appendChild(detailsColPanel);
        Map<String, String> reversedHashMap = new HashMap<>();

        for (String i : details.keySet()) {
            reversedHashMap.put(i, details.get(i));
        }
        Iterator it = reversedHashMap.entrySet().iterator();

        String imageLocation = "";
        String excCause = "";
        String excOccurs = "";
        String excStackTrace = "";
        String excBrowserLog = "";
        String excHtmlSrc = "";

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String key = String.valueOf(pairs.getKey());
            if (key.equals(TestEvent.IMAGE)) {
                imageLocation = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_CAUSE)) {
                excCause = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_OCCURS)) {
                excOccurs = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_STACK_TRACE)) {
                excStackTrace = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_BROWSER_LOG)) {
                excBrowserLog = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_PAGE_SRC)) {
                excHtmlSrc = String.valueOf(pairs.getValue());
            } else {
                Span detsP = new Span();
                detailsColPanel.appendChild(detsP);
                detsP.appendText("<b>" + pairs.getKey() + ": </b> " + pairs.getValue() + "<br/>");
            }
        }
        A imageLink = new A();
        imageLink.setHref(imageLocation);
        imageLink.setTarget("_blank");
        Img imgs = new Img("", "");
        imgs.setSrc(imageLocation);
        imgs.setStyle("max-width: 100%; max-height: 100%;padding-bottom: 30px;");
        imageLink.appendChild(imgs);

        //if autostep failed do red marked step
        if (isFailed) {
            Div failedSpan = new Div();
            failedSpan.setCSSClass("failure-container");
            failedSpan.setStyle("padding-left: 15px;font-size: 15px;margin-top:0px;margin-left: -45px;");

            Div logs = new Div();
            logs.setCSSClass("test-summary-logs");
            logs.setStyle("padding-left: 15px;");
            failedSpan.appendChild(logs);


            logs.appendText("<a href=\"" + excBrowserLog + "\" target=\"_blank\">Browser Log</a>");
            logs.appendText("<a href=\"" + imageLocation + "\" target=\"_blank\">Screenshot</a>");
            logs.appendText("<a href=\"" + excHtmlSrc + "\" target=\"_blank\">HTML Source</a>");
            logs.appendText("<a style=\"cursor: pointer;\" onclick=\"scrollToVideo(this)\"><u><i class=\"video-scroll\">Scroll to Video Recording</i></u></a>");


            //add the image
            Span imgDetsP = new Span();
            failedSpan.appendChild(imgDetsP);
            imgDetsP.appendChild(imageLink);


            Span failDetsP = new Span();
            failedSpan.appendChild(failDetsP);
            failDetsP.appendText("<b>" + TestEvent.EXC_OCCURS + ": </b> " + excOccurs + "<br/><br/>");

            Span failDetsP2 = new Span();
            failedSpan.appendChild(failDetsP2);
            failDetsP2.appendText("<b>" + TestEvent.EXC_CAUSE + ": </b> " + excCause + "<br/><br/>");

            Span failDetsP3 = new Span();
            failedSpan.appendChild(failDetsP3);
            failDetsP3.appendText("<b>" + TestEvent.EXC_STACK_TRACE + ": </b> " + excStackTrace + "<br/><br/>");


            detailsColPanel.appendChild(failedSpan);

        } else if (!imageLocation.trim().equals("")) {
            Span detsP = new Span();
            detailsColPanel.appendChild(detsP);
            detsP.appendChild(imageLink);
        }


        return tr;
    }

    private Tr getGeneralError(){
        Tr tr = new Tr();
        Td time = new Td();
        tr.appendChild(time);
        time.appendText(this.hour);

        Td step = new Td();
        tr.appendChild(step);
        Div div = new Div();
        step.appendChild(div);
        Span sp = new Span();
        div.appendChild(sp);
        Div divDetails = new Div();
        sp.appendText("FAILURE " + this.message);
        div.setCSSClass(CssProperties.IMG_STEP_ERROR.value() + " " + CssProperties.COLOR_RED_CLASS.value());
        divDetails.setStyle("display: block;");
        sp.setCSSClass(CssProperties.IMG_COLLAPSE.value());
        tr.setCSSClass(CssProperties.STEP_FUNCTIONAL.value());
        sp.setAttribute("onclick", "expColDetails(this)");
        divDetails.setCSSClass("step-details");


        step.appendChild(divDetails);
        Span spDetails = new Span();
        spDetails.setCSSClass("by-str");
        divDetails.appendChild(spDetails);
        Div detailsColPanel = new Div();
        spDetails.appendChild(detailsColPanel);
        Map<String, String> reversedHashMap = new HashMap<>();

        for (String i : details.keySet()) {
            reversedHashMap.put(i, details.get(i));
        }
        Iterator it = reversedHashMap.entrySet().iterator();

        String imageLocation = "";
        String excCause = "";
        String excOccurs = "";
        String excStackTrace = "";
        String excBrowserLog = "";
        String excHtmlSrc = "";

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String key = String.valueOf(pairs.getKey());
            if (key.equals(TestEvent.IMAGE)) {
                imageLocation = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_CAUSE)) {
                excCause = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_OCCURS)) {
                excOccurs = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_STACK_TRACE)) {
                excStackTrace = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_BROWSER_LOG)) {
                excBrowserLog = String.valueOf(pairs.getValue());
            } else if (key.equals(TestEvent.EXC_PAGE_SRC)) {
                excHtmlSrc = String.valueOf(pairs.getValue());
            }
            /*else {
                Span detsP = new Span();
                detailsColPanel.appendChild(detsP);
                detsP.appendText("<b>" + pairs.getKey() + ": </b> " + pairs.getValue() + "<br/>");
            }*/
        }

        Div failedSpan = new Div();
        failedSpan.setCSSClass("failure-container");
        failedSpan.setStyle("padding-left: 15px;font-size: 15px;margin-top:0px;margin-left: -45px; width:100%");

        Div logs = new Div();
        logs.setCSSClass("test-summary-logs");
        logs.setStyle("padding-left: 15px;");
        failedSpan.appendChild(logs);
        Span failDetsP = new Span();
        failedSpan.appendChild(failDetsP);
        failDetsP.appendText("<b>" + TestEvent.EXC_OCCURS + ": </b> " + excOccurs + "<br/><br/>");


        Span showMoreContainer = new Span();
        showMoreContainer.setCSSClass("show-more-container");
        failedSpan.appendChild(showMoreContainer);


        Span showMoreButton = new Span();
        showMoreButton.appendText("Show More");
        showMoreContainer.appendChild(showMoreButton);
        showMoreButton.setCSSClass("show-more hidden");
        showMoreButton.setAttribute("onclick", "failureShowMore(this)");



        Span showMoreInnerPanel = new Span();
        showMoreInnerPanel.setCSSClass("show-more-panel");
        showMoreContainer.appendChild(showMoreInnerPanel);


        if(!excCause.isEmpty()) {
            Span failDetsP2 = new Span();
            showMoreInnerPanel.appendChild(failDetsP2);
            failDetsP2.appendText("<b>" + TestEvent.EXC_CAUSE + ": </b> " + excCause + "<br/><br/>");
        }



        Span failDetsP3 = new Span();
        showMoreInnerPanel.appendChild(failDetsP3);
        failDetsP3.appendText("<b>" + TestEvent.EXC_STACK_TRACE + ": </b> " + excStackTrace + "<br/><br/>");


        detailsColPanel.appendChild(failedSpan);


        return tr;
    }


    public Tr getHtml() {

       /* if (this.type.equals(StepLogType.AUTOSTEP) || this.type.equals(StepLogType.GENERAL_ERROR) ) {
            return getAutoStep();
        }  else{
            return this.getBasicStep();
        }*/

        if (this.type.equals(StepLogType.AUTOSTEP) ) {
            return getAutoStep();
        } else if(this.type.equals(StepLogType.GENERAL_ERROR)){
            return getGeneralError();
        } else{
            return this.getBasicStep();
        }


    }


}

