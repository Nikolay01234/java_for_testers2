package manager;

import org.openqa.selenium.By;

public class HelperBase {

    // Ссылка на делегирующий класс manager
    protected final ApplicationManager manager;

    // Ссылка сохраняется в конструкторе
    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    // Метод кликает по элементам принимая на вход локатор
    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    // Метод заполняет поле ввода
    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }
}
