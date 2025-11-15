package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{

    @Test
    // createGroup принимает параметр, который имеет тип GroupData
    public void canCreateGroup() throws InterruptedException {
        // Считаем количество уже имеющихся групп с помощью метода getCount()
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
        //System.out.println(newGroupCount);
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
