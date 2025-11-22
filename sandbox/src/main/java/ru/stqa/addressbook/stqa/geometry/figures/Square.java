package ru.stqa.addressbook.stqa.geometry.figures;

//public class Square {
public record Square(double side) {


    //  Свойство класса
    // private double side; // Описание структуры объекта перечисляет его свойства

    // Конструктор класса
    // Конструктор присваивает значения параметра в эти свойства
    public Square(double side) {
        this.side = side;
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
