package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    private String NULL_REPRESENTER = "<<null>>";

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) section).getOrganizations(), org -> {
                            dos.writeUTF(org.getHomePage().getName());
                            writeNullable(dos, org.getHomePage().getUrl());
//                            dos.writeUTF(org.getHomePage().getUrl());
                            writeCollection(dos, org.getPositions(), position -> {
                                writeLocalDate(dos, position.getStartDate());
                                writeLocalDate(dos, position.getEndDate());
                                dos.writeUTF(position.getTitle());
                                writeNullable(dos, position.getDescription());
//                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }

            });

            /*
            1. Записать uuid +
            2. Записать fullName +
            3. Записать кол-во контактов +
            4. Для каждого контакта: +
                4.1. Название типа контакта +
                4.2. Значение контакта +
            5. Записать кол-во секций
            6. Для каждой секции:
                6.1. Записать тип секции
                6.2. Если тип секции (OBJECTIVE|PERSONAL): //TextSection
                    6.2.1. Записать content
                6.3. Если тип секции (ACHIEVEMENT|QUALIFICATIONS): //ListSection
                    6.3.1. Записать кол-во элементов в секции
                    6.3.2. Для каждого элемента в секции:
                        6.3.2.1. Записать значение элемента
                6.4. Если тип секции (EXPERIENCE|EDUCATION):
                    6.4.1. Записать кол-во организаций в секции.
                    6.4.2. Для каждой организации:
                        6.4.2.1. Записать название
                        6.4.2.2. Записать URL
                        6.4.2.3. Записать кол-во позиций в секции
                        6.4.2.4. Для каждой позиции в секции:
                            6.4.2.4.1. Записать дату начала работы
                            6.4.2.4.2. Записать дату конца работы
                            6.4.2.4.3. Записать название работы
                            6.4.2.4.4. Записать описание работы

                Общее: запись коллекций
                Разное: запись элементов различных типов
             */
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeNullable(DataOutputStream dos, String value) throws IOException {
        if (value == null) {
            dos.writeUTF(NULL_REPRESENTER);
        } else {
            dos.writeUTF(value);
        }
    }

    private String readNullable(DataInputStream dis) throws IOException {
        String value = dis.readUTF();
        if (value.equals(NULL_REPRESENTER)) {
            return null;
        }
        return value;
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;

            /*
            1. Прочитать uuid +
            2. Прочитать fullName +
            3. Создать объект резюме +
            4. Прочитать кол-во контактов +
            5. Итерировать по кол-ву контактов: +
                5.1. Прочитать тип контакта +
                5.2. Прочитать значение контакта +
                5.3. Добавить контакт к резюме +
            6. Прочитать кол-во секций
            7. Итерировать по кол-ву секций:
                7.1. Прочитать тип секции
                7.2. Если тип секции (OBJECTIVE|PERSONAL): //TextSection
                    7.2.1. Прочитать content
                    7.2.2. Создать объект секции
                7.3. Если тип секции (ACHIEVEMENT|QUALIFICATIONS): //ListSection
                    7.3.1. Прочитать кол-во элементов в секции
                    7.3.2. Итерировать по кол-ву элементов в секции:
                        7.3.2.1. Прочитать значение элемента
                    7.3.3. Создать объект секции
                7.4. Если тип секции (EXPERIENCE|EDUCATION):
                    7.4.1. Прочитать кол-во организаций в секции
                    7.4.2. Итерировать по кол-ву организаций:
                        7.4.2.1. Прочитать название
                        7.4.2.2. Прочитать URL
                        7.4.2.3. Прочитать кол-во позиций в секции
                        7.4.2.4. Итерировать по кол-ву позиций в секции:
                            7.4.2.4.1. Прочитать дату начала работы
                            7.4.2.4.2. Получить объект LocalDate для начала работы
                            7.4.2.4.3. Прочитать дату конца работы
                            7.4.2.4.4. Получить объект LocalDate для конца работы
                            7.4.2.4.5. Прочитать название работы
                            7.4.2.4.6. Прочитать описание работы
                            7.4.2.4.7. Создать объект позиции
                        7.4.2.5. Создать объект организации
                        7.4.2.6. Добавить объект организации к списку организаций
                    7.4.3. Создать объект секции
                7.5. Добавить секцию к резюме

             */
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(
                        readList(dis, () -> new Organization(
                                new Link(dis.readUTF(), readNullable(dis)),
                                readList(dis, () -> new Organization.Position(
                                        readLocalDate(dis), readLocalDate(dis), dis.readUTF(), readNullable(dis)
                                ))
                        )));
            default:
                throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ItemReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }


    private interface ValueWriter<T> {
        void write(T value) throws IOException;
    }

    private interface ValueReader {
        void read() throws IOException;
    }

    private interface ItemReader<T> {
        T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ValueWriter<T> writer) throws IOException {
        collection.size();
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dis, ValueReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
