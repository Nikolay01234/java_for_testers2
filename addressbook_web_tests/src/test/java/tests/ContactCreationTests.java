package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() throws InterruptedException {
        // createContact принимает параметр, который имеет тип ContactData
        app.contacts().createContact(new ContactData("lastName", "firstName", "address", "email@mail.ml", "+79998887766"));
    }

    // Создаётся контакт со всеми пустыми полями
    @Test
    public void canCreateContactOnlyWithEmptyFields() throws InterruptedException {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithLastNameOnly() throws InterruptedException {
        app.contacts().createContact(new ContactData().withLastName("someLastName"));
    }
}
