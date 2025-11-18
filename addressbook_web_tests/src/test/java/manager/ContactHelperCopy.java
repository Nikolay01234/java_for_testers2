package manager;

import model.ContactData;
import org.openqa.selenium.By;

// Класс помощник для работы с контактами
public class ContactHelperCopy extends HelperBase {

    public ContactHelperCopy(ApplicationManager manager) {
        // Вызывается конструктор базового класса
        // Значение параметра manager передаётся в конструктор базового класса
        // и там сохраняется
        super(manager);
    }

    // Специально открывать страницу контактов не нужно - при успешном логине мы
    // попадаем на страницу со списком контактов
    // Однако перестраховываемся и открываем специально страницу с контактами
    public void openContactsPage() {
        manager.driver.findElement(By.linkText("home")).click();
    }


//    // Закоментировано по итогам четвертого модуля
//    // Проверяет наличие элемента
//    public boolean isContactPresent() {
//        openContactsPage();
//        return manager.isElementPresent(By.xpath("//img[@alt=\'vCard\']"));
//    }

    // Создаёт контакт
    public void createContact(ContactData contact) throws InterruptedException {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    // Удаляет контакт
    public void removeContact() {
        openContactsPage();
        selectContact();
        removeSelectedContact();
        returnToContactsPage();
    }

    // Модифицирует контакт
    public void modifyContact(ContactData modifiedContact) throws InterruptedException {
        // Открыть страницу контактов
        openContactsPage();
        // Выбрать контакт
        selectContact();
        // Нажать на кнопку для модификации контакта
        initContactModification();
        // Заполнить форму данными, которые содержатся
        // в объекте переданном в качестве параметра
        fillContactForm(modifiedContact);
        // Подтвердить изменения
        submitContactModification();
        // Вернуться на страницу со списком контактов
        returnToContactsPage();
    }

    // В окне создания контакта жмём на кнопку Enter
    private void submitContactCreation() {
        click(By.name("submit"));
    }

    // Жмём на кнопку
    private void initContactCreation() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Жмём на кнопку ");
        click(By.linkText("add new"));
        //manager.driver.findElement(By.linkText("add new")).click();
    }

    // Жмём на кнопку "Delete"
    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    // Возвращаемся на страницу со списком контактов - домашнюю страницу
    private void returnToContactsPage() {
        click(By.linkText("home"));
    }

    // Подтверждаем модификацию контакта - жмём на кнопку Update
    private void submitContactModification() {
        click(By.name("update"));
    }

    // Заполнение полей формы контакта
    private void fillContactForm(ContactData contact) {
        type(By.name("lastname"), contact.lastName());
        type(By.name("firstname"), contact.firstName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.phone());
    }

    // Жмём на кнопку Edit, чтобы модифицировать контакт
    private void initContactModification() {
        click(By.xpath("//img[@alt=\'Edit\']"));
    }

    // Активация чек-бокса контакта
    private void selectContact() {
        click(By.name("selected[]"));
    }

}
