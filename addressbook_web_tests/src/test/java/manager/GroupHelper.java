package manager;

import model.GroupData;
import org.openqa.selenium.By;

// Класс помощник для работы с группами
public class GroupHelper {

    // Ссылка на делегирующий класс manager
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    // Открывает страницу группы
    public void openGroupsPage() {
        // Если нет элемента с именем "new", т.е. ни одна группа не создана, то кликаем по кнопке "groups",
        // чтобы перейти к созданию групп
        if (! manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    // Проверяет наличие элемента
    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    // Создаёт группу
    public void createGroup(GroupData group) {
        openGroupsPage();
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    // Удаляет группу
    public void removeGroup() {
        openGroupsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
