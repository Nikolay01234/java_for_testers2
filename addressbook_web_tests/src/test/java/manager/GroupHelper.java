package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

// Класс помощник для работы с группами
public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager) {
        // Вызывается конструктор базового класса
        // Значение параметра manager передаётся в конструктор базового класса
        // и там сохраняется
        super(manager);
    }

    // Открывает страницу группы
    public void openGroupsPage() {
        // Если нет элемента с именем "new", т.е. ни одна группа не создана, то кликаем по кнопке "groups",
        // чтобы перейти к созданию групп
        if (! manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    // Создаёт группу
    public void createGroup(GroupData group) throws InterruptedException {
        openGroupsPage();
        initGroupCreation();
        Thread.sleep(1000);
        fillGroupForm(group);
        Thread.sleep(1000);
        submitGroupCreation();
        Thread.sleep(1000);
        returnToGroupsPage();
    }

    // Удаляет группу
    public void removeGroup(GroupData group) throws InterruptedException {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    // Модифицирует группу
    public void modifyGroup(GroupData group, GroupData modifiedGroup) throws InterruptedException {
        // открыть страницу групп
        openGroupsPage();
        // группа будет отмечена галочкой и потом модифицирована
        selectGroup(group);
        // нажать на кнопку для модификации группы
        initGroupModification();
        // заполнить форму данными, которые содержатся
        // в объекте переданном в качестве параметра
        fillGroupForm(modifiedGroup);
        // подтвердить изменения
        submitGroupModification();
        // вернуться на страницу со списком групп
        returnToGroupsPage();
    }

    // В окне создания группы жмём на кнопку "Enter information"
    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    // Жмём на кнопку "New group"
    private void initGroupCreation() {
        click(By.name("new"));
    }

    // Жмём на кнопку "Delete group(s)"
    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    // Возвращаемся на страницу группы
    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    // Подтверждаем модификацию группы - жмём на кнопку Update
    private void submitGroupModification() {
        click(By.name("update"));
    }

    // Заполнение полей формы группы
    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    // Жмём на кнопку Edit, чтобы модифицировать группу
    private void initGroupModification() {
        click(By.name("edit"));
    }

    // Активация чек-бокса группы
    // Принимает на вход параметр - объект типа GroupData,
    // который содержит идентификатор нужной группы
    private void selectGroup(GroupData group) throws InterruptedException {
        Thread.sleep(1000);
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
        //click(By.cssSelector(String.format("input[type=\"checkbox\"][value=\"%s\"]", group.id())));
    }

    // Метод возвращает СПИСОК элементов - количество групп
    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectedAllGroups();
        removeSelectedGroups();
    }

    private void selectedAllGroups() {
        // Коллекция checkboxes, которая возвращает метод findElements
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        // Цикл пробегает по всем элементам коллекции checkboxes
        // переменная checkbox последовательно принимает значения
        // соответствующие элементам этой коллекции (этого списка)
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        // переменная spans получит все элементы, которые имеют класс "group" и тег span
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans){
            var name = span.getText();
            // найдём чек-бокс, который находится внутри элемента span
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            // построенный объект помещаем в список,
            // который будет возвращаться из метода getList.
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
