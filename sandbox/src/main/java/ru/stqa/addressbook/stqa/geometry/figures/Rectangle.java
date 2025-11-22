package ru.stqa.addressbook.stqa.geometry.figures;

//public class Rectangle {
public record Rectangle(double a, double b) {

//    private double a;
//    private double b;
//
    // Для конструктора круглые скобки не нужны - параметры описаны при описании Record'а
    public Rectangle {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }

    public static void printRectangleArea(double a, double b) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(this.a, rectangle.a) == 0 && Double.compare(this.b, rectangle.b) == 0)
                || (Double.compare(this.b, rectangle.a) == 0 && Double.compare(this.a, rectangle.b) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
