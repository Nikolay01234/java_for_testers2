package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{

    public JdbcHelper(ApplicationManager manager) {
        // Вызывается конструктор базового класса
        // Значение параметра manager передаётся в конструктор базового класса
        // и там сохраняется
        super(manager);
    }

    // Метод будет возвращать List объектов типа GroupData
    public List<GroupData> getGroupList() {
        // Создаём пустой список groups
        var groups = new ArrayList<GroupData>();
        try (var conn  = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    // Метод будет возвращать List объектов типа ContactData
    public List<ContactData> getContactList() {
        // Создаём пустой список contacts
        var contacts = new ArrayList<ContactData>(List.of());
        try (var conn  = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, firstname, lastname, address FROM addressbook"))
        {
            while (result.next()) {
                contacts.add(new ContactData()
                        .withContactId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withLastName(result.getString("lastname"))
                        .withAddress(result.getString("address")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
