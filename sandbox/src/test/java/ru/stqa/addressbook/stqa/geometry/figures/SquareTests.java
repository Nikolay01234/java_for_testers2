package ru.stqa.addressbook.stqa.geometry.figures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }




    @Test
    void testEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertEquals(s1.area(), s2.area());
    }

    @Test
    void testNonEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(4.0);
        Assertions.assertNotEquals(s1.area(), s2.area());
    }

    @Test
    void testTrue() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
//        double s1area = s1.area();
//        double s2area = s2.area();
//        Assertions.assertTrue(Objects.equals(s1area, s2area));  // сравнение успешно
//        Assertions.assertTrue(Objects.equals(s1.area(), s2.area())); // сравнение успешно
        Assertions.assertTrue(s1.equals(s2)); // работает, только когда класс записан,как record
    }

    @Test
    void callCanCalculateTrianglePerimetr() {
        canCalculateTrianglePerimetr(7.0, 11.0, 18.0);
//       canCalculateTrianglePerimetr(-1.0, 7.0, 9.0);
//        canCalculateTrianglePerimetr(1.0, 7.0, 9.0);

    }

    @Test
    void canCalculateTriangleSquare() {
        canCalculateTriangleSquare(7.0, 14.0, 7.0);
//        canCalculateTriangleSquare(-1.0, 7.0, 9.0);
//        canCalculateTriangleSquare(1.0, 7.0, 9.0);
    }

    //@Test
    //public void canCalculateTrianglePerimetr() {
    public void canCalculateTrianglePerimetr(double sideA, double sideB, double sideC) {
        // var принимает тип значения переменной, значение которой было написано после знака =
        // создали экземпляр класса tp
        // при этом передали параметры
        // var tp = new Triangle(6.0, 7.0, 8.0);
        var tp = new Triangle(sideA, sideB, sideC);
        double result = tp.perimetrTriangle();
        Assertions.assertEquals(result, new Triangle(sideA, sideB, sideC).perimetrTriangle());
    }

    //@Test
    public void canCalculateTriangleSquare(double sideA, double sideB, double sideC) {
        var ts = new Triangle(sideA, sideB, sideC);
        double result = ts.squareTriangle();
        Assertions.assertEquals(result, new Triangle(sideA, sideB, sideC).squareTriangle());
    }



    @Test
    void canCalculateCirclePerimetr() {
        var cp = new Circle(3.0);
        double result = cp.perimetrCircle();
        Assertions.assertEquals(result, new Circle(3.0).perimetrCircle());
    }

    @Test
    void canCalculateCircleSquare() {
        var cs = new Circle(4.0);
        double result = cs.scuareCircle();
        Assertions.assertEquals(result, new Circle(4.0).scuareCircle());
    }

}
