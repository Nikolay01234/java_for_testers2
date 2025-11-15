package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    // Метод удаляет контакт
    // Если нет контакта, то он будет создан, а затем удалён
    @Test
    public void CanRemoveContact() throws InterruptedException {
        // contacts() - в этом случае сработает ленивая инициализация
        // при первом обращении к методу contacts() помощник будет проинициализирован
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("lastName", "firstName", "address", "email", "phone"));
        }
        Thread.sleep(2000);
        app.contacts().removeContact();
    }
}
