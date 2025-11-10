package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {
//    слишком много комментов,
//    перепишу ниже метод с отсутствием комментов

//    @Test
//    public void CanRemoveGroup() {
//        // код, который незакомментирован на строках 9, 10, 13 совпадает с кодом показанным в Лекции 3.3. таймкод - 4:12
//        app.groups.openGroupsPage();
//        if (!app.isGroupPresent()) {
//            //app.createGroup(new GroupData("", "", ""));
//            // добавил вариант ниже,т.к. он ближе к тому,что показано на на -5:00 Лекция 3.3
//            app.createGroup(new GroupData("group name", "group header", "group footer"));
//        }
////        if (!isGroupPresent()) { // на -5:00 Лекция 3.3 показан именно такой вид
////            createGroup(new GroupData("group name", "group header", "group footer"));
////        }
//        app.removeGroup();
//    }

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
