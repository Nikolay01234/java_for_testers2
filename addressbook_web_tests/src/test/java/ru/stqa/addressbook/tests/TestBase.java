package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.manager.ApplicationManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
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
    public void setUp() throws IOException, InterruptedException {
        if (app == null) { // если инициализация ещё не выполнялась
            // читаем содержимое конфигурационного файла "local.properties"
            // создаём новый объект типа new Properties()
            var properties = new Properties();
            // если никакого значения для этого системного свойства не указано,
            // то конфиги читаем из файла "local.properties"
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            // Задано системное свойство browser с дефолтным значением firefox
            // После того, как конфиг был прочитан, данные будут переданы в init()
            app.init(System.getProperty("browser", "firefox"), properties);
        }
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
