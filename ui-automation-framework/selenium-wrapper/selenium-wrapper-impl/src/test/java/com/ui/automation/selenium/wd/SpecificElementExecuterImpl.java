package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.wd.specificElements.*;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/05/14
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class SpecificElementExecuterImpl implements SpecificElementExecuter {
    DriverServices driverServices;

    public SpecificElementExecuterImpl(DriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @Override
    public HeaderSubMenu headerSubMenu() {
        return new HeaderSubMenuImpl(driverServices);
    }

    @Override
    public CKEditor ckeditor() {
        return new CKEditorImpl(driverServices);
    }



    /**
     * Return as instance of VisibleAfterAction
     * @return
     */
    @Override
    public VisibleAfterAction visibleAfterAction() {
        return new VisibleAfterActionImpl(driverServices);
    }

    @Override
    public DslCodeMirrorEditor dslCodeMirrorEditor(Element element) {
        return new DslCodeMirrorEditorImpl(driverServices, element);
    }

    @Override
    public RelatedEntityActions relatedEntityActions() {
        return new RelatedEntityActionsImpl(driverServices);
    }
}
