package model;

// класс (record) GroupData моделирует группу - Group
// у группы есть три поля "group name", "group header", "group footer"
// группа может быть создана только с именем, только с хедером, только с футером
public record GroupData(String name, String header, String footer) {

    // Конструктор класса GroupData, который принимает на вход ноль параметров
    // Внутри конструктора класса GroupData вызывается конструктор объекта
    // с передачей в конструктор объекта трёх пустых строк
    public GroupData() {
        this("", "", "");
    }

    // Метод для создания группы только с именем
    public GroupData withName(String name) {
        return new GroupData(name, this.header, this.footer);
    }

    // Метод для создания группы только с header'ом
    public GroupData withHeader(String header) {
        return new GroupData(this.name, header, this.footer);
    }

    // Метод для создания группы только с footer'ом
    public GroupData withFooter(String footer) {
        return new GroupData(this.name, this.header, footer);
    }
}