package ru.stqa.geometry.figures;

public class Circle {

    // Свойства класса
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public static void printCirclePerimetr(Circle crcl) {
        String text = String.format("Периметр круга с радиусом %f = %f", crcl.radius, crcl.perimetrCircle());
        System.out.println(text);
    }

    public double perimetrCircle() {
        return 2*this.radius*Math.PI;
    }

    public static void printCircleSquare(Circle crcl) {
        String text = String.format("Площадь круга с радусом %f = %f", crcl.radius, crcl.scuareCircle());
        System.out.println(text);
    }

    public double scuareCircle() {
        return Math.PI*Math.pow(this.radius, 2);
    }
}
