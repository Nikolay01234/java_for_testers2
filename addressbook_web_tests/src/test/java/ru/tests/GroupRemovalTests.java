package ru.tests;

import ru.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    // Метод удаляет группу
    // Если нет группы, то группа будет создана, а потом удалена
    @Test
    public void canRemoveGroup() throws InterruptedException {
        // Если ни одной группы нет, нужно создать новую группу
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        // Список уже имеющихся групп
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        // случайным образом выбираем индекс элемента из списка oldGroups
        var index = rnd.nextInt(oldGroups.size());
        // удаляем группу по её индексу, который получен в строке выше
        Thread.sleep(2000);
        app.groups().removeGroup(oldGroups.get(index));
        // После удаления группы получаем новый список групп
        var newGroups = app.groups().getList();
        // Строим копию списка oldGroups
        var expectedList = new ArrayList<>(oldGroups);
        // Удаляем элемент с заданным индексом
        expectedList.remove(index);
        // с expectedList сравниваем реальный список newGroups, полученный из приложения.
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroupsAtOnce() throws InterruptedException {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
