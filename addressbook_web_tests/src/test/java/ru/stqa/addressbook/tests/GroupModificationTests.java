package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

// Класс для модификации групп
public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() throws InterruptedException {
        // Если нет ни одной группы, то группа будет создана
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        // Список уже имеющихся групп
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        // случайным образом выбираем индекс элемента из списка oldGroups
        var index = rnd.nextInt(oldGroups.size());
        // Вызов метода, который будет модифицировать группу
        // В качестве параметра в modifyGroup будет перадан объект типа groupData,
        // который содержит новый набор свойств
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        // После модификации группы, получаем новый список групп
        var newGroups = app.groups().getList();
        // Строим копию списка oldGroups
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        // Сортируем два списка.
        // В обоих используются одинаковые правила сравнения элементов.
        // Они упорядочиваются по возрастанию идентификаторов.
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
