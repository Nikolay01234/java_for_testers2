package model;




public record ContactData(String lastName, String firstName, String address, String email, String phone) {




    public ContactData() {
        this("", "", "", "", "");
    }

    // Метод для создания контакта только с lastName
    public ContactData withLastName(String lastName) {
        return new ContactData(lastName, this.firstName, this.address, this.email, this.phone);
    }

    // Метод для создания контакта только с firstName
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.lastName, firstName, this.address, this.email, this.phone);
    }

    // Метод для создания контакта только с address
    public ContactData withAddress(String address) {
        return new ContactData(this.lastName, this.firstName, address, this.email, this.phone);
    }

    // Метод для создания контакта только с email
    public ContactData withEmail(String email) {
        return new ContactData(this.lastName, this.firstName, this.address, email, this.phone);
    }

    // Метод для создания контакта только с phone
    public ContactData withPhone(String phone) {
        return new ContactData(this.lastName, this.firstName, this.address, this.email, phone);
    }

    // Метод для создания контакта с пятью данными, которые отображаются на домашней странице addressbook
    public ContactData withFiveData(String lastName, String firstName, String address, String email, String phone) {
        return new ContactData(lastName, firstName, address, email, phone);
    }

}
