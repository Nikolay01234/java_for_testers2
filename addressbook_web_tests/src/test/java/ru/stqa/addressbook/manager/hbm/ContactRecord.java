package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

//    public int id;
//    public String firstname;
//    public String lastname;
//    public String address;

    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "address")
    public String address;

    public Date deprecated = new Date();
    public String middlename = "middlename";
    public String nickname = "nickname";
    public String company = "company";
    public String title = "title";
    public String home = "home";
    public String mobile = "mobile";
    public String work = "work";
    public String fax = "fax";
    public String email = "email@mail.lm";
    public String email2 = "email2@mail.lm";
    public String email3 = "email3@mail.lm";
    public String homepage = "https://homepage.pg";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.address = address;
    }

}