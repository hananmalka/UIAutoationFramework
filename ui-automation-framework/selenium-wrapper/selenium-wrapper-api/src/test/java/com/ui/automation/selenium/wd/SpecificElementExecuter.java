package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.wd.specificElements.*;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 01/05/14
 * Time: 15:53
 * The class will implement specific element action/expects.
 * This class will be used for complex elements that we have to use short timeouts.
 * Any code write to this method must
 */
public interface SpecificElementExecuter {

    /**
     * Returns an instance of HeaderSubMenu for SAW navigation purposes.
     * @return
     */
    HeaderSubMenu headerSubMenu();

    /**
     * Returns an instance of CKEditor
     *
     * @return
     */
    CKEditor ckeditor();

    /**
     * Return as instance of VisibleAfterAction
     * @return
     */
    VisibleAfterAction visibleAfterAction();

    DslCodeMirrorEditor dslCodeMirrorEditor(Element element);

    RelatedEntityActions relatedEntityActions();
}
