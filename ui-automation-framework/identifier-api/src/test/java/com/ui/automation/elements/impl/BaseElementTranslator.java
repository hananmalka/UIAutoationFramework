package com.ui.automation.elements.impl;

import com.ui.automation.elements.api.ElementTranslator;
import com.ui.automation.locator.LocatorType;
import com.ui.automation.elements.api.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 18/03/14
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */

public class BaseElementTranslator implements ElementTranslator {
    List<LocatorMapHolder> locatorMapList = new ArrayList<LocatorMapHolder>();
    protected BaseElementTranslator(InputStream fileInputStream) {

        try {


           // File fXmlFile = new File(fileInputStream);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileInputStream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("locator");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                    LocatorMapHolder cl = new LocatorMapHolder(
                            eElement.getAttribute("pattern"),
                            eElement.getAttribute("return"),
                            eElement.getAttribute("type"));


                    locatorMapList.add(cl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String translate(Element element) {
        for (LocatorMapHolder locatorMap : locatorMapList){
            if(element.getLocator().getType().equals(locatorMap.type)){
                //if no pattern needed and it is the same value
                if(locatorMap.pattern.equals(element.getLocator().getExpression())){
                    return locatorMap.toReturn.replace("%s", element.getLocator().getExpression().
                            replace("-", " ").replace("<", "&lt;").replace(">", "&gt;"));
                }

                Pattern patt = null;
                try{
                     patt = Pattern.compile(locatorMap.pattern, Pattern.DOTALL);
                }
                catch (Exception ex){continue;}
                Matcher matcher = patt.matcher(element.getLocator().getExpression());
                if(matcher.matches()){
                    try{ return locatorMap.toReturn.replace("%s", matcher.group(1).
                            replace("-", " ").replace("<", "&lt;").replace(">", "&gt;"));}
                    catch (Exception ex){}
                }
            }
        }

        return null;

    }

    class LocatorMapHolder{
        String pattern ="";
        String toReturn ="";
        LocatorType type;

        public LocatorMapHolder(String pattern,String toReturn,String type){
            this.pattern = pattern;
            this.toReturn = toReturn;
            this.type = LocatorType.valueOf(type);
        }
    }
}
