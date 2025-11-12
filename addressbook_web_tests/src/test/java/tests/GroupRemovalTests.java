package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {
    // Метод удаляет группу
    // Если нет группы, то группа будет создана, а потом удалена
    @Test
    public void CanRemoveGroup() throws InterruptedException {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }
}
