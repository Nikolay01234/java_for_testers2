package ru.tests;

import ru.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() throws InterruptedException {
        // Если нет ни одного контакта, то контакт будет создан
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "lastName", "firstName", "address", "email", "phone", "photo"));
        }
        // Список уже имеющихся контактов
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        // случайным образом выбираем индекс элемента из списка oldContacts
        var index = rnd.nextInt(oldContacts.size());
        // Вызов метода, который будет модифицировать контакт
        // В качестве параметра в modifyContact будет перадан объект типа contactData,
        // который содержит новый набор свойств
        var testData = new ContactData().withLastName("modified lastName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        // После модификации контакта, получаем новый список контактов
        var newContacts = app.contacts().getList();
        // Строим копию списка oldContacts
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withContactId(oldContacts.get(index).id()));
        // Сортируем два списка.
        // В обоих используются одинаковые правила сравнения элементов.
        // Они упорядочиваются по возрастанию идентификаторов.
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
