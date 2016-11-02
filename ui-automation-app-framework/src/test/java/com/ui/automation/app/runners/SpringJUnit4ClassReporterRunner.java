package com.ui.automation.app.runners;

import com.ui.automation.app.runners.listeners.ReportRunListener;
import com.ui.automation.app.runners.listeners.ReporterConsoleLogByEventListener;
import com.ui.automation.app.listeners.helpers.RoleHolder;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: peere
 * Date: 26/01/14
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class SpringJUnit4ClassReporterRunner extends SpringJUnit4ClassRunner {

    private static boolean listenersAdded = false;
    private final String role;

    public SpringJUnit4ClassReporterRunner(Class<?> clazz) throws InitializationError {
        this("dummy", clazz);
    }

    /**
     * Constructs a new <code>SpringJUnit4ClassRunner</code> and initializes a
     * {@link org.springframework.test.context.TestContextManager} to provide Spring testing functionality to
     * standard JUnit tests.
     *
     * @param clazz the test class to be run
     * @see #createTestContextManager(Class)
     */
    public SpringJUnit4ClassReporterRunner(String role, Class<?> clazz) throws InitializationError {
        super(clazz);
        this.role = role;
    }

    @Override
    public void run(RunNotifier notifier) {
        RoleHolder.set(role);
        addListeners(notifier);
        super.run(notifier);
    }

    private static synchronized void addListeners(RunNotifier notifier) {
        // Add listeners once for all threads because all threads are sharing the same notifier
        if (!listenersAdded) {
            notifier.addListener(ReportRunListener.getInstance());
            notifier.addListener(ReporterConsoleLogByEventListener.getInstance());
            listenersAdded = true;
        }
    }
}
