package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.contact.Contact;
import ru.javawebinar.basejava.model.contact.ContactType;
import ru.javawebinar.basejava.model.section.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("John Doe");

        Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.CELL, new Contact("666-66-66"));
        contacts.put(ContactType.SKYPE, new Contact("Proger42"));
        contacts.put(ContactType.EMAIL, new Contact("ross03@mail.ru"));
        contacts.put(ContactType.LINKEDIN, new Contact("no linkedin profile i have"));
        contacts.put(ContactType.GITHUB, new Contact("https://github.com/chemelson"));
        contacts.put(ContactType.STACKOVERFLOW, new Contact("neither have it i"));
        contacts.put(ContactType.HOMEPAGE, new Contact("nore personal page i have"));

        resume.setContacts(contacts);

        System.out.println("Contacts =====>>>>>");
        for (Map.Entry<ContactType, Contact> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
        }
        System.out.println("\n----------");


        Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

        Section personal = new TextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");
        sections.put(SectionType.PERSONAL, personal);

        Section objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web" +
                " и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);

        List<String> achievementsContent = new ArrayList<>();
        achievementsContent.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        achievementsContent.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementsContent.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
                " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.");
        achievementsContent.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring," +
                " Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementsContent.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievementsContent.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        Section achievements = new ListTextSection(achievementsContent);
        sections.put(SectionType.ACHIEVEMENT, achievements);

        List<String> qualificationsContent = new ArrayList<>();
        qualificationsContent.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsContent.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsContent.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationsContent.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsContent.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualificationsContent.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationsContent.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis," +
                " Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, " +
                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit," +
                " Selenium (htmlelements)");
        qualificationsContent.add("Python: Django");
        qualificationsContent.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationsContent.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationsContent.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB," +
                " StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS," +
                " BPMN2, LDAP, OAuth1, OAuth2, JWT");
        qualificationsContent.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualificationsContent.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, " +
                "Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualificationsContent.add("Отличное знание и опыт применения концепций ООП, SOA," +
                " шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationsContent.add("Родной русский, английский \"upper intermediate\"");
        Section qualifications = new ListTextSection(qualificationsContent);
        sections.put(SectionType.QUALIFICATIONS, qualifications);

        List<Place> experienceContent = new ArrayList<>();
        experienceContent.add(new Place("Java Online Projects",
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта",
                "http://javaops.ru/",
                "Создание, организация и проведение Java онлайн проектов и стажировок")
        );
        experienceContent.add(new Place("Wrike",
                LocalDate.of(2014, 4, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)",
                "https://www.wrike.com/",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO")
        );
        experienceContent.add(new Place("RIT Center",
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1),
                "Java архитектор",
                null,
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения " +
                        "(почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование" +
                        " из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                        "Unix shell remote scripting via ssh tunnels, PL/Python")
        );
        experienceContent.add(new Place("Luxoft (Deutsche Bank)",
                LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1),
                "Ведущий программист",
                "https://www.luxoft.com/",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, " +
                        "GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                        "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
                        "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5")
        );
        experienceContent.add(new Place("Yota",
                LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1),
                "Ведущий специалист",
                "https://www.yota.ru/",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online " +
                        "JMX клиента (Python/ Jython, Django, ExtJS)")
        );
        experienceContent.add(new Place("Enkata",
                LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1),
                "Разработчик ПО",
                "https://www.pega.com/enkata/",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)" +
                        " частей кластерного J2EE приложения (OLAP, Data mining)")
        );
        experienceContent.add(new Place("Siemens AG",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1),
                "Разработчик ПО",
                "https://new.siemens.com/ru/ru.html",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                        "на мобильной IN платформе Siemens @vantage (Java, Unix)")
        );
        experienceContent.add(new Place("Alcatel",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1),
                "Инженер по аппаратному и программному тестированию",
                "http://www.alcatel.ru/",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)")
        );
        Section experience = new ListPlaceSection(experienceContent);
        sections.put(SectionType.EXPERIENCE, experience);

        List<Place> educationContent = new ArrayList<>();
        educationContent.add(new Place("Coursera",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                "https://www.coursera.org/learn/progfun1",
                null)
        );
        educationContent.add(new Place("Luxoft",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2014, 4, 1),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy__analiz_is_kontseptualnoe_modelirovanie_na_uml_dlya_sistemnyh_analitikov_.html",
                null)
        );
        educationContent.add(new Place("Siemens AG",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1),
                "3 месяца обучения мобильным IN сетям (Берлин)",
                "https://new.siemens.com/ru/ru.html",
                null)
        );
        educationContent.add(new Place("Alcatel",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1),
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                "http://www.alcatel.ru/",
                null)
        );
        educationContent.add(new Place("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1),
                "Аспирантура (программист С, С++)",
                "http://www.ifmo.ru/ru/",
                null)
        );
        educationContent.add(new Place("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1),
                "Инженер (программист Fortran, C)",
                "http://www.ifmo.ru/ru/",
                null)
        );
        educationContent.add(new Place("Заочная физико-техническая школа при МФТИ",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1),
                "Закончил с отличием",
                "http://www.school.mipt.ru/",
                null)
        );
        Section education = new ListPlaceSection(educationContent);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);

        System.out.println("Sections =====>>>>>");
        for (Map.Entry<SectionType, Section> section : resume.getSections().entrySet()) {
            System.out.println(section.getKey().getTitle().toUpperCase() + ":");
            System.out.println(section.getValue());
        }
    }
}
