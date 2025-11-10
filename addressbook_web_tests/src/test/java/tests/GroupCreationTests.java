package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void canCreateGroup() throws InterruptedException {
        // createGroup принимает параметр, который имеет тип GroupData
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    // Создаётся группа с пустыми name, header, footer
    @Test
    public void canCreateGroupWithEmptyName() throws InterruptedException {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() throws InterruptedException {
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}
