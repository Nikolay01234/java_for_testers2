package ru.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Класс, в котором будут находиться методы, предназначенные для управления
// тестируемым приложением, для взаимодействия с ним.
public class ApplicationManager {

    protected WebDriver driver;

    // Ссылка на помощника LoginHelper
    private LoginHelper session;

    // Ссылка на помощника GroupHelper
    private GroupHelper groups;

    // Ссылка на помощника ContactHelper
    private ContactHelper contacts;

    // Инициализация драйвера, закрытие браузера, переход на страницу addressbook, а также ввод логина и пароля
    public void init(String browser) {
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            // тут (ниже) закрывается браузер
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(550, 692));
            session().login("admin", "secret");
        }
    }

    // Метод, который выполняет ленивую инициализацию помощника LoginHelper
    public LoginHelper session() {
        // если помощник не проинициализироваан, значит нужно его проинициализировать
        if (session == null){
            session = new LoginHelper(this);
        }
        // если помощник проинициализирован, то сразу возвращаем ссылку на помощника
        return session;
    }

    // Метод, который выполняет ленивую инициализацию помощника GroupHelper
    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    // Метод, который выполняет ленивую инициализацию помощника ContactHelper
    public ContactHelper contacts() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    // Проверяет, есть ли элемент на странице, если элемента нет, то выкидывает исключение NoSuchElementException
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
