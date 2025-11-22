package ru.stqa.addressbook.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        //var t1 = new Triangle(5.0, 5.0, 5.0);// isosceles triangle
        var t1 = new Triangle(3.0, 4.0, 5.0);
        //var t2 = new Triangle(3.0, 4.0, 5.0);// positive test
        //var t2 = new Triangle(5.0, 3.0, 4.0 ); // positive test
        //var t2 = new Triangle(5.0, 5.0, 5.0);// isosceles triangle
        //var t2 = new Triangle(4.0, 5.0, 3.0 ); // positive test
        //var t2 = new Triangle(3.0, 5.0, 4.0 ); // negative test
        //var t2 = new Triangle(4.0, 3.0, 5.0 ); // negative test
        //var t2 = new Triangle(5.0, 4.0, 3.0 ); // negative test
        //var t2 = new Triangle(5.0, 4.0, 3.0 ); // negative test
        var t2 = new Triangle(3.0, 5.0, 4.0 );
        Assertions.assertEquals(t1, t2);
    }

}
