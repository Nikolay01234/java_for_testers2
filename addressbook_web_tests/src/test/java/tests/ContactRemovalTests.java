package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    // Метод удаляет контакт
    // Если нет контакта, то он будет создан, а затем удалён
    @Test
    public void canRemoveContact() throws InterruptedException {
        // contacts() - в этом случае сработает ленивая инициализация
        // при первом обращении к методу contacts() помощник будет проинициализирован
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "lastName", "firstName", "address", "email", "phone", "photo"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactsAtOnce() throws InterruptedException {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "lastName", "firstName", "address", "email", "phone", "photo"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
