package com.ui.automation.elements.entities.appmodule;

import com.ui.automation.elements.entities.test.TestsContainer;
import com.ui.automation.elements.entityform.EntityForm;
import com.ui.automation.elements.entityform.EntityFormTab;

/**
 * Created by Dana Shalev on 26/11/2015.
 */
public class AppModuleForm extends EntityForm {

    public EntityFormTab testsTab() {
        return tab("tests");
    }

    public TestsContainer testsContainer() {
        return new TestsContainer(this);
    }
}
