package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    // Метод будет возвращать список объектов типа ContactData
    public static List<ContactData> contactProvider() throws InterruptedException {
        // Тип объектов, которые находятся в списке ContactData
        var result = new ArrayList<ContactData>(List.of());
        // цикл, который перебирает два возможных значения для lastName контакта
        // для каждого из этих lastName, будет вложенный цикл, который перебирает
        // два возможных значений для firstName
        for (var lastName : List.of("", "contact lastName")) {
            for (var firstName : List.of("", "contact firstName")){
                // для каждого сочетания lastName и firstName будем перебирать разные значения для address
                for (var address : List.of("", "contact address")) {
                    // будем добавлять в список генерируемых объектов разные варианты lastName, firstName, address
                    // из трёх условий выше
                    Thread.sleep(500);
                    result.add(new ContactData().withLastName(lastName).withFirstName(firstName).withAddress(address));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            // в список добавляется объект типа ContactData
            // случайно сгенерированным astName, firstName, address
            result.add(new ContactData()
                    .withLastName(randomString(i * 10))
                    .withFirstName(randomString(i * 10))
                    .withAddress(randomString(i * 10)));
        }
        return result;
    }

    // Метод будет возвращать список объектов типа ContactData
    public static List<ContactData> negativeContactProvider() {
        // Тип объектов, которые находятся в списке ContactData
        // ниже будет список групп, которые не должны создаваться
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("","lastName'", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    // Тест, который имеет один параметр представляющий собой объект типа ContactData
    public void canCreateMultipleContacts(ContactData contact) throws InterruptedException {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact); // Создавать будем контакт, который передаётся в качестве параметра в тестируемую функцию
        var newContacts = app.contacts().getList();
        // Сортируем два списка.
        // В обоих используются одинаковые правила сравнения элементов.
        // Они упорядочиваются по возрастанию идентификаторов.
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        // Строим копию списка oldContacts
        var expectedList = new ArrayList<>(oldContacts);
        // Созданный контакт будет иметь идентификатор, такой же как у последнего элемента в списке newContacts
        // Берём этот идентификатор
        //expectedList.add(contact.withContactId(newContacts.get(newContacts.size() - 1).id()).withLastName("").withFirstName(""));
        expectedList.add(contact.withContactId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        // Сравниваем списки newContacts - туда добавлен ещё один элемент,
        // и новый список newContacts фактически полученный из web приложения
        System.out.println("newContacts " + newContacts); //                 newContacts - из веба
        System.out.println("expectedList" + expectedList); //                expectedList - из кода

        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    // Тест, который имеет один параметр представляющий собой объект типа ContactData
    public void canNotCreateContact(ContactData contact) throws InterruptedException {
        // Получаем старый список
        var oldContacts = app.contacts().getList();
        // Создавать будем группу, которая передаётся в качестве параметра в тестируемую функцию
        app.contacts().createContact(contact);
        // Получаем новый список
        var newContacts = app.contacts().getList();
        int newContactCount = app.contacts().getCount();
        // Сравниваем старый список с новым - списки должны совпасть
        Assertions.assertEquals(newContacts, oldContacts);
    }

}
