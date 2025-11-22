package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;

public class Generator {

    // свойство, которое соответствует параметрам запуска
    @Parameter(names={"--type", "-t"})
    // тип генерируемых значений
    String type;

    @Parameter(names={"--outpoot", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        // код, который будет анализировать опции коммандной строки передаваемые через переменную args
        // создаётся парсер(анализатор) коммандной строки,
        // который будет анализировать параметры описанные в объекте generator
        // далее на вход парсеру передаются аргументы args
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        // после того, как парсер отработает, в объекте generator в соотв. свойствах (@Parameter),
        // будут прописаны те значения, которые переданы в параметрах
        generator.run();
    }

    // Метод генерирует вспомогательные данные
    // генерация данных и их сохранение
    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        // список объектов типа groupData
        var result = new ArrayList<GroupData>();
        // цикл до значения, которое указано в параметре
        for (int i = 0; i < count; i++) {
            // генерация объекта типа GroupData
            // в список добавляется объект типа GroupData
            // случайно сгенерированным именем, хедером, и футером
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        return null;
    }

    private void save(Object data) {
    }
}
