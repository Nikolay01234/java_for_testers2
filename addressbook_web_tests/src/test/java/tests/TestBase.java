package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) { // если инициализация ещё не выполнялась
            app = new ApplicationManager();
        }
        app.init();
    }

}
