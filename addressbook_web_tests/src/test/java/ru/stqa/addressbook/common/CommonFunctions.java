package ru.stqa.addressbook.common;

import java.util.Random;

public class CommonFunctions {

    // метод генерирует "случайное" имя для названия параметра
    // в качестве параметра указывается длина генерируемой строки
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }
}
