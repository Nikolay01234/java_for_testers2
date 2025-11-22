package ru.stqa.addressbook.model;

public record ContactData(String id, String lastName, String firstName, String address, String email, String phone, String photo) {

    public ContactData() {
        this("","", "", "", "", "", "");
    }

    // Метод получает значение идентификатора
    // Строим и возвращаем новый объект типа contactData с заданным идентификатором,
    // и остальными свойствами такими же, как у текущего объекта.
    public ContactData withContactId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.phone, this.photo);
    }

    // Метод для создания контакта с указанием lastName
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.phone, this.photo);
    }

    // Метод для создания контакта с указанием firstName
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.phone, this.photo);
    }

    // Метод для создания контакта  с указанием address
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.phone, this.photo);
    }

    // Метод для создания контакта с указанием photo
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.phone, photo);
    }

//    // Метод для создания контакта с указанием email
//    public ContactData withEmail(String email) {
//        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.phone, this.photo);
//    }
//
//    // Метод для создания контакта с указанием phone
//    public ContactData withPhone(String phone) {
//        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, phone, this.photo);
//    }
//
//    // Метод для создания контакта с пятью данными, которые отображаются на домашней странице addressbook
//    public ContactData withFiveData(String id, String lastName, String firstName, String address, String email, String phone, this.photo) {
//        return new ContactData(id, lastName, firstName, address, email, phone);
//    }

}
