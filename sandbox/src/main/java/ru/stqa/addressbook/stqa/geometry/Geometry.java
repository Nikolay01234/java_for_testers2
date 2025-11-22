package ru.stqa.addressbook.stqa.geometry;

import ru.stqa.addressbook.stqa.geometry.figures.Circle;
import ru.stqa.addressbook.stqa.geometry.figures.Rectangle;
import ru.stqa.addressbook.stqa.geometry.figures.Square;
import ru.stqa.addressbook.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle.printTrianglePerimetr(new Triangle(3.0, 4.0, 5.0));
        Triangle.printTrianglePerimetr(new Triangle(6.0, 7.0, 8.0));

        //Triangle.printTrianglePerimetr(new Triangle(-1.0, 7.0, 9.0));
        //Triangle.printTrianglePerimetr(new Triangle(1.0, 7.0, 9.0));

        // Вызывается функция printTriangleSquare, куда передаётся экземпляр класса Triangle, с параметрами
        Triangle.printTriangleSquare(new Triangle(3.0, 4.0, 5.0));
        Triangle.printTriangleSquare(new Triangle(6.0, 7.0, 8.0));

        //Triangle.printTriangleSquare(new Triangle(-1.0, 7.0, 9.0));
        //Triangle.printTriangleSquare(new Triangle(1.0, 7.0, 9.0));

        Circle.printCirclePerimetr(new Circle(4.0));
        Circle.printCirclePerimetr(new Circle(6.0));

        Circle.printCircleSquare(new Circle(4.0));
        Circle.printCircleSquare(new Circle(6.0));

    }

}
