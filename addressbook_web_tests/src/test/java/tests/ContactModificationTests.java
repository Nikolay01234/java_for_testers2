package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifiyGroup() throws InterruptedException {
        // Если нет ни одного контакта, то контакт будет создан.
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new ContactData());
        }
        // Вызов метода, который будет модифицировать контакт
        // В качестве параметра в modifyContact будет передан объект типа ContactData,
        // который содержит новый набор свойств
        app.contacts().modifyContact(new ContactData().withFirstName("modified contact 333"));
    }
}
