package ru.tests;

import ru.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

// Базовый класс для тестов. Parent class
// Наследники могут использовать его переменные и методы
public class TestBase {

    // Переменная app это ссылка на ApplicationManager
    protected static ApplicationManager app;

    // Метод для подготовки
    // Инициализирует ApplicationManager
    // в методе init() происходит переход на страницу "http://localhost/addressbook/" и ввод логина/пароля
    @BeforeEach
    public void setUp() {
        if (app == null) { // если инициализация ещё не выполнялась
            app = new ApplicationManager();
        }
        // Задано системное свойство browser с дефолтным значением firefox
        app.init(System.getProperty("browser", "firefox"));
    }

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

    // метод в качестве параметра, принимает путь к деректории с картинками
    // метод выбирает случайный файл в заданной директории
    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        // путь к файлу - соединяем путь к директории и имя файла
        return Paths.get(dir, fileNames[index]).toString();
    }
}
