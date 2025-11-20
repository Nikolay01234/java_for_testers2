package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

// Класс помощник для работы с контактами
public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
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

    // Создаёт контакт
    public void createContact(ContactData contact) throws InterruptedException {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    // Удаляет контакт
    public void removeContact(ContactData contact) throws InterruptedException {
        openContactsPage();
        selectContact(contact);
        removeSelectedContacts();
        returnToContactsPage();
    }

    // Модифицирует контакт
    public void modifyContact(ContactData contact, ContactData modifiedContact) throws InterruptedException {
        // Открыть страницу контактов
        openContactsPage();
        // Выбрать контакт
        selectContact(contact);
        // Нажать на кнопку для модификации контакта
        initContactModification(contact);
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

    // Жмём на кнопку для создания нового контакта
    private void initContactCreation() throws InterruptedException {
        Thread.sleep(2000);
        click(By.linkText("add new"));
    }

    // Жмём на кнопку "Delete"
    private void removeSelectedContacts() {
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
    // Метод принимает объект класса ContactData
    private void initContactModification(ContactData contact) {
        // клик по локатору, где id берётся из свойства объекта класса
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    // Активация чек-бокса контакта
    // Принимает на вход параметр - объект типа ContactData,
    // который содержит идентификатор нужного контакта
    private void selectContact(ContactData contact) throws InterruptedException {
        Thread.sleep(1000);
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    // Метод возвращает СПИСОК контактов - количество контактов
    public int getCount() {
        openContactsPage();
        // вернёт количество всех элементов (findElements), которые "подходят" под локатор "selected[]"
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openContactsPage();
        selectedAllContacts();
        removeSelectedContacts();
    }

    private void selectedAllContacts() {
        // Коллекция checkboxes, которая возвращает метод findElements
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        // Цикл пробегает по всем элементам коллекции checkboxes
        // переменная checkbox последовательно принимает значения
        // соответствующие элементам этой коллекции (этого списка)
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() throws InterruptedException {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        // переменная contactCheckbox получит все элементы, которые имеют css селектор "tr[name^=\"entry\"]"
        var contactString = manager.driver.findElements(By.cssSelector("tr[name^=\"entry\"]"));
        for (var contact : contactString){
            // найдём чек-бокс, который находится внутри элемента, который имеет css селектор "tr[name^=\"entry\"]"
            var checkbox = contact.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            // Берём элементы контакта из своих колонок
            var lastName = contact.findElement(By.cssSelector("[name=\"entry\"] > td:nth-child(2)")).getText();
            var firstName = contact.findElement(By.cssSelector("[name=\"entry\"] > td:nth-child(3)")).getText();
            var address = contact.findElement(By.cssSelector("[name=\"entry\"] > td:nth-child(4)")).getText();
            // построенный объект помещаем в список, который будет возвращаться из метода getList.
            contacts.add(new ContactData().withContactId(id).withLastName(lastName).withFirstName(firstName).withAddress(address));
        }
        return contacts;
    }

}
