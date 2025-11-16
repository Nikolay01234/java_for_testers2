package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
        int groupCount = app.groups().getCount();
        // Создавать будем группу, которая передаётся в качестве параметра в тестируемую функцию
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        // новое полученное значение должно быть больше на 1
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    // Тест, который имеет один параметр представляющий собой объект типа GroupData
    public void canNotCreateGroup(GroupData group) throws InterruptedException {
        int groupCount = app.groups().getCount();
        // Создавать будем группу, которая передаётся в качестве параметра в тестируемую функцию
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        // новое полученное значение должно совпадать с уже имеющимся
        Assertions.assertEquals(groupCount, newGroupCount);
    }
}
