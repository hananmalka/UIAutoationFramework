package com.ui.automation.selenium.lifecycle;

import com.ui.automation.selenium.wd.MaasDriver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Implementation of {@link DriverTestContext}
 */
@Component
public class DriverTestContextImpl implements DriverTestContext, BeanFactoryAware {

    private static final String MAAS_DRIVER = "MAAS_DRIVER";
    private BeanFactory beanFactory;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void setup() {
        // Create a new instance of MaasDriver
        MaasDriver driver = beanFactory.getBean(MaasDriver.class);
        // Set on thread
        ThreadVariables.setThreadVariable(MAAS_DRIVER, driver);
        logger.info("setup thread: " + Thread.currentThread().getName() + " driver " + driver);
    }

    @Override
    public void teardown() {
        logger.info("teardown thread: " + Thread.currentThread().getName() + " driver " + getDriver());
        ThreadVariables.destroy();
    }

    @Override
    public MaasDriver getDriver() {
        final MaasDriver maasDriver = (MaasDriver) ThreadVariables.getThreadVariable(MAAS_DRIVER);
        return maasDriver;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * Per-thread variables container
     */
    private static class ThreadVariables {

        private final static ThreadLocal<Map<String, Object>> THREAD_VARIABLES = new ThreadLocal<Map<String, Object>>() {
            /**
             * @see ThreadLocal#initialValue()
             */
            @Override
            protected Map<String, Object> initialValue() {
                return new HashMap<>();
            }
        };

        public static Object getThreadVariable(String name) {
            return THREAD_VARIABLES.get().get(name);
        }
        public static void setThreadVariable(String name, Object value) {
            THREAD_VARIABLES.get().put(name, value);
        }
        public static void destroy() {
            THREAD_VARIABLES.remove();
        }
    }
}
