package ru.manager;

import org.openqa.selenium.By;

// Ввод кредов и вход в addressbook
public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager) {
        // Параметр manager передаётся в конструктор базового класса
        super(manager);
    }

    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
