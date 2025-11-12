package manager;

import model.ContactData;
import org.openqa.selenium.By;

// Класс помощник для работы с контактами
public class ContactHelperCopy {

    // Ссылка на делегирующий класс manager
    private final ApplicationManager manager;

    public ContactHelperCopy(ApplicationManager manager) {
        this.manager = manager;
    }

    // Специально открывать страницу контактов не нужно - при успешном логине мы попадаем на страницу со списком контактов
    // Однако перестраховываемся и открываем специально страницу с контактами
    public void openContactsPage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    // Проверяет наличие элемента
    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.xpath("//img[@alt=\'vCard\']"));
    }

    // Создаёт контакт
    public void createContact(ContactData contact) throws InterruptedException {
        openContactsPage();
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.phone());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        manager.driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        manager.driver.findElement(By.linkText("home")).click();
    }

    // Удаляет контакт
    public void removeContact() {
        openContactsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("home")).click();
    }

}
