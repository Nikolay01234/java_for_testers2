package ru.tests;

import ru.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

    // Метод будет возвращать список объектов типа GroupData
    public static List<GroupData> groupProvider() {
        // Тип объектов, которые находятся в списке GroupData
        var result = new ArrayList<GroupData>();
        // цикл, который перебирает два возможных значения для названия группы
        // для каждого из этих названий, будет вложенный цикл, который перебирает
        // два возможных значений хедера
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")){
                // для каждого сочетания имени и хедера будем перебирать разные значения для футера
                for (var footer : List.of("", "group footer")) {
                    // будем добавлять в список генерируемых объектов разные варианты name, header, footer
                    // из трёх условий выше
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            // в список добавляется объект типа GroupData
            // случайно сгенерированным именем, хедером, и футером
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10)));
        }
        return result;
    }

    // Метод будет возвращать список объектов типа GroupData
    public static List<GroupData> negativeGroupProvider() {
        // Тип объектов, которые находятся в списке GroupData
        // ниже будет список групп, которые не должны создаваться
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    // Тест, который имеет один параметр представляющий собой объект типа GroupData
    public void canCreateMultipleGroups(GroupData group) throws InterruptedException {
        var oldGroups = app.groups().getList();
        // Создавать будем группу, которая передаётся в качестве параметра в тестируемую функцию
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        // Сортируем два списка.
        // В обоих используются одинаковые правила сравнения элементов.
        // Они упорядочиваются по возрастанию идентификаторов.
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        // Строим копию списка oldGroups
        var expectedList = new ArrayList<>(oldGroups);
        // Созданная группа будет иметь идентификатор,
        // такой же как у последнего элемента в списке newGroups
        // Берём этот идентификатор -
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        // Сравниваем списки newGroups - туда добавлен ещё один элемент,
        // и новый список фактически полученный из web приложения
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    // Тест, который имеет один параметр представляющий собой объект типа GroupData
    public void canNotCreateGroup(GroupData group) throws InterruptedException {
        // Получаем старый список
        var oldGroups = app.groups().getList();
        // Создавать будем группу, которая передаётся в качестве параметра в тестируемую функцию
        app.groups().createGroup(group);
        // Получаем новый список
        var newGroups = app.groups().getList();
        int newGroupCount = app.groups().getCount();
        // Сравниваем старый список с новым - списки должны совпасть
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
