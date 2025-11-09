package manager;

import org.openqa.selenium.By;

// Ввод кредов и вход в addressbook
public class LoginHelper {

    // Ссылка на делегирующий класс manager
    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    void login(String user, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(user);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
