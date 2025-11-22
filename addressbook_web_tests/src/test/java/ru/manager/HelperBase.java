package ru.manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

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

    // Метод прикрепляет картинку к контакту
    protected void attach(By locator, String file) {

        if (file != null && !file.isEmpty()) {
            manager.driver
                    .findElement(locator)
                    .sendKeys(Paths.get(file)
                            .toAbsolutePath().toString());
        } else {
            return;
        }


    }
}
