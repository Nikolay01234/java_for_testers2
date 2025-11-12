package manager;

import model.GroupData;
import org.openqa.selenium.By;

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

    // Проверяет наличие элемента
    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
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
    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    // Модифицирует группу
    public void modifyGroup(GroupData modifiedGroup) {
        // открыть страницу групп
        openGroupsPage();
        // выбрать группу
        selectGroup();
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
    private void removeSelectedGroup() {
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
    private void selectGroup() {
        click(By.name("selected[]"));
    }
}
