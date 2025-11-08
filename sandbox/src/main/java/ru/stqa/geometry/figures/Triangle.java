package ru.stqa.geometry.figures;

//public class Triangle {
public record Triangle(double sideA, double sideB, double sideC) {

//    // Свойства класса == Поля класса
//    private double sideA;
//    private double sideB;
//    private double sideC;

    // Конструктор класса заполняет поля класса, чтобы их значения потом могли использовать другие функции
    //public Triangle(double sideA, double sideB, double sideC) { // Используем такую запись,если у нас clacc, а не record
    public Triangle {

        triangleSideShouldBeNotNegative(sideA, sideB, sideC);
        theSumOfTwoSidesIsGreaterThanTheThirdSide(sideA, sideB, sideC);

        // Используем такую запись,если у нас clacc, а не record
//        this.sideA = sideA;
//        this.sideB = sideB;
//        this.sideC = sideC;
    }

    private static void theSumOfTwoSidesIsGreaterThanTheThirdSide(double sideA, double sideB, double sideC) {
        if (! (sideA + sideB >= sideC && sideA + sideC >= sideB && sideB + sideC >= sideA)) {
            throw new IllegalArgumentException("In a triangle, the sum of any two sides must be greater than the third side");
        }
    }

    private static void triangleSideShouldBeNotNegative(double sideA, double sideB, double sideC) {
        if (sideA < 0 || sideB < 0 || sideC < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
    }

    // Функция принимает на вход объект(экземпляр) класса
    // printTrianglePerimetr(Triangle t) - на вход функция ждёт экземпляр класса, со значениями полей класса sideA, sideB, sideC
    // Triangle - "тип переменной"
    // t - аргумент
    public static void printTrianglePerimetr(Triangle t) {
        triangleSideShouldBeNotNegative(t.sideA, t.sideB, t.sideC);
        theSumOfTwoSidesIsGreaterThanTheThirdSide(t.sideA, t.sideB, t.sideC);
        String text = String.format("Периметр треугольника со сторонами %f %f %f = %f", t.sideA, t.sideB, t.sideC, t.perimetrTriangle());
        System.out.println(text);
    }

    public static void printTriangleSquare(Triangle t) {
        triangleSideShouldBeNotNegative(t.sideA, t.sideB, t.sideC);
        theSumOfTwoSidesIsGreaterThanTheThirdSide(t.sideA, t.sideB, t.sideC);
        String text = String.format("Площадь треугольника со сторонами %f %f %f = %f", t.sideA, t.sideB, t.sideC, t.squareTriangle());
        System.out.println(text);
    }

    // создали через TDD
    public double squareTriangle() {
        double perimetr = this.sideA + this.sideB + this.sideC;
        double halfOfThePerimeter = perimetr/2;
        double square = Math.sqrt(halfOfThePerimeter*(halfOfThePerimeter-this.sideA)*(halfOfThePerimeter-this.sideB)*(halfOfThePerimeter-this.sideC));
        System.out.println("Triangle square is " + square);
        return square;
    }

    // эта функция привязана к экземпляру класса
    // double - тип возвращаемого значения
    // public - функция доступна кому угодно
    // функция без static использует заполненные поля экземпляра класса
    // для вызова такой функции нужно обязательно создать экземпляр класса, передать свойствам класса значения
    // когда static есть вызов может быть Triangle.perimetr(3,4,5);
    public double perimetrTriangle() {
        double perimetr = this.sideA + this.sideB + this.sideC;
        System.out.println("Triangle perimetr is " + perimetr);
        return perimetr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
//        return Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideC) == 0
//                || Double.compare(this.sideC, triangle.sideA) == 0 && Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideC) == 0
//                || Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideB) == 0 && Double.compare(this.sideA, triangle.sideC) == 0
        return Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideC) == 0
               || Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideB) == 0
               || Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideA) == 0
               || Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideB, triangle.sideB) == 0 && Double.compare(this.sideC, triangle.sideA) == 0
               || Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideC) == 0
               || Double.compare(this.sideA, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideA) == 0 && Double.compare(this.sideC, triangle.sideC) == 0
               || Double.compare(this.sideA, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideB) == 0 && Double.compare(this.sideB, triangle.sideA) == 0
               || Double.compare(this.sideA, triangle.sideA) == 0 && Double.compare(this.sideB, triangle.sideC) == 0 && Double.compare(this.sideC, triangle.sideB) == 0;
    }

    @Override
    public int hashCode() {
//        return Objects.hash(sideA, sideB, sideC);
        return 1;
    }
}
