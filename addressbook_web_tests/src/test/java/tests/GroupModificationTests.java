package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

// Класс для модификации групп
public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() throws InterruptedException {
        // Если нет ни одной группы, то группа будет создана
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        // Вызов метода, который будет модифицировать группу
        // В качестве параметра в modifyGroup будет перадан объект типа groupData,
        // который содержит новый набор свойств
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
