package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    // свойство, которое соответствует параметрам запуска
    @Parameter(names = {"--type", "-t"})
    // тип генерируемых значений
            String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
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
    private void run() throws IOException {
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
        var result = new ArrayList<ContactData>(List.of());
        for (int i = 0; i < count; i++) {
            // в список добавляется объект типа ContactData
            // случайно сгенерированным astName, firstName, address
            result.add(new ContactData()
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withAddress(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.isEnabled(SerializationFeature.INDENT_OUTPUT);
            ObjectMapper mapper = JsonMapper.builder()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .build();
            // Данные из переменной data сохраняются в указываемый файл
            // Название файла будет передано в качестве опции запуска
            // и содержатся в переменной output
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
