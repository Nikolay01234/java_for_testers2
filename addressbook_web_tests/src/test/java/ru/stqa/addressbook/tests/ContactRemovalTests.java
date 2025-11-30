package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
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
        if (app.hbm().getContactCount() == 0) {
            //app.hbm().createContact(new ContactData("", "lastName", "firstName", "address", "email", "phone", "photo"));
            app.hbm().createContact(new ContactData("", "lastName", "firstName", "address", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactsAtOnce() throws InterruptedException {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "lastName", "firstName", "address", "email", "phone", "photo"));
            //app.hbm().createContact(new ContactData("", "lastName", "firstName", "address", "", "", ""));
        }
        app.contacts().removeAllContacts();
        Thread.sleep(2000);
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}
