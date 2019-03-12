package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";

    static {
        RESUME_1 = new Resume(UUID_1, "abc");
        RESUME_2 = new Resume(UUID_2,"def");
        RESUME_3 = new Resume(UUID_3, "ghi");
        RESUME_4 = new Resume(UUID_4, "jkl");

        // RESUME_1
        // Contacts
        RESUME_1.setContact(ContactType.MOBILE, "+7(921) 855-0482");
        RESUME_1.setContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        RESUME_1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME_1.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        RESUME_1.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"
        ));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection(
               "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."
        ));

        // Achievements
        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера.\n");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов" +
                " (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии" +
                " через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                "системы по JMX (Jython/ Django).");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        RESUME_1.setSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        // Qualification
        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualification.add("Python: Django.");
        qualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, " +
                "XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, " +
                "OAuth2, JWT.");
        qualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios," +
                " iReport, OpenCmis, Bonita, pgBouncer.");
        qualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualification.add("Родной русский, английский \"upper intermediate\"");

        RESUME_1.setSection(SectionType.QUALIFICATIONS, new ListSection(qualification));

        // Experience
        List<Organization> organizations = new ArrayList<>();

        Organization.Position position_1 = new Organization.Position(
                of(2013, 10),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."
        );
        List<Organization.Position> positions_1 = new ArrayList<>();
        positions_1.add(position_1);
        Organization organization_1 = new Organization(
                "Java Online Projects",
                "http://javaops.ru/",
                positions_1
        );
        organizations.add(organization_1);

        Organization.Position position_2 = new Organization.Position(
                of(2014, 10),
                of(2016, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike" +
                        " (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO"
        );
        List<Organization.Position> positions_2 = new ArrayList<>();
        positions_2.add(position_2);
        Organization organization_2 = new Organization(
                "Wrike",
                "https://www.wrike.com/",
                positions_2
        );
        organizations.add(organization_2);

        Organization.Position position_3 = new Organization.Position(
                of(2012, 4),
                of(2014, 10),
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                        "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части" +
                        " системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего" +
                        " назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для " +
                        "online редактирование из браузера документов MS Office. Maven + plugin development, Ant, " +
                        "Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                        "Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"
        );
        List<Organization.Position> positions_3 = new ArrayList<>();
        positions_3.add(position_3);
        Organization organization_3 = new Organization(
                "RIT Center",
                null,
                positions_3
        );
        organizations.add(organization_3);

        Organization.Position position_4 = new Organization.Position(
                of(2010, 12),
                of(2012, 4),
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT," +
                        " Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения" +
                        " для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга." +
                        " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
        );
        List<Organization.Position> positions_4 = new ArrayList<>();
        positions_4.add(position_4);
        Organization organization_4 = new Organization(
                "Luxoft (Deutsche Bank)",
                "https://www.luxoft.com/",
                positions_4
        );
        organizations.add(organization_4);

        Organization.Position position_5 = new Organization.Position(
                of(2008, 6),
                of(2010, 12),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX " +
                        "клиента (Python/ Jython, Django, ExtJS)"
        );
        List<Organization.Position> positions_5 = new ArrayList<>();
        positions_5.add(position_5);
        Organization organization_5 = new Organization(
                "Yota",
                "https://www.yota.ru/",
                positions_5
        );
        organizations.add(organization_5);

        Organization.Position position_6 = new Organization.Position(
                of(2007, 3),
                of(2008, 6),
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
                        "частей кластерного J2EE приложения (OLAP, Data mining)."
        );
        List<Organization.Position> positions_6 = new ArrayList<>();
        positions_6.add(position_6);
        Organization organization_6 = new Organization(
                "Enkata",
                "http://enkata.com/",
                positions_6
        );
        organizations.add(organization_6);

        Organization.Position position_7 = new Organization.Position(
                of(2005, 1),
                of(2007, 2),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка " +
                        "ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."
        );
        List<Organization.Position> positions_7 = new ArrayList<>();
        positions_7.add(position_7);
        Organization organization_7 = new Organization(
                "Siemens AG",
                "https://new.siemens.com/ru/ru.html",
                positions_7
        );
        organizations.add(organization_7);

        Organization.Position position_8 = new Organization.Position(
                of(1997, 9),
                of(2005, 1),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."
        );
        List<Organization.Position> positions_8 = new ArrayList<>();
        positions_8.add(position_8);
        Organization organization_8 = new Organization(
                "Alcatel",
                "http://www.alcatel.ru/",
                positions_8
        );
        organizations.add(organization_8);

        RESUME_1.setSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));

        // Education
        List<Organization> courses = new ArrayList<>();

        Organization.Position position_9 = new Organization.Position(
                of(2013, 3),
                of(2013, 5),
                "\"Functional Programming Principles in Scala by Martin Odersky",
                null
        );
        List<Organization.Position> positions_9 = new ArrayList<>();
        positions_9.add(position_9);
        Organization organization_9 = new Organization(
                "Coursera",
                "https://www.coursera.org",
                positions_9
        );
        courses.add(organization_9);

        Organization.Position position_10 = new Organization.Position(
                of(2011, 3),
                of(2011, 4),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null
        );

        // Add to luxoft
        positions_4.add(position_10);
        courses.add(organization_4);

        Organization.Position position_11 = new Organization.Position (
                of(2005, 1),
                of(2005, 4),
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null
        );

        // Add to Siemens AG
        positions_7.add(position_11);
        courses.add(organization_7);

        Organization.Position position_12 = new Organization.Position(
                of(1997, 9),
                of(1998, 3),
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                null
        );

        // Add to Alcatel
        positions_8.add(position_12);
        courses.add(organization_8);

        Organization.Position position_13 = new Organization.Position(
                of(1993, 9),
                of(1996, 7),
                "Аспирантура (программист С, С++)",
                null
        );

        Organization.Position position_14 = new Organization.Position(
                of(1987, 9),
                of(1993, 7),
                "Инженер (программист Fortran, C)",
                null
        );

        List<Organization.Position> positions_13_14 = new ArrayList<>();
        positions_13_14.add(position_13);
        positions_13_14.add(position_14);
        Organization organization_10 = new Organization(
                "Санкт-Петербургский национальный исследовательский " +
                        "университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/ru/",
                positions_13_14
        );
        courses.add(organization_10);

        Organization.Position position_15 = new Organization.Position(
                of(1984, 9),
                of(1987, 6),
                "Закончил с отличием",
                null
        );
        List<Organization.Position> positions_15 = new ArrayList<>();
        positions_15.add(position_15);
        Organization organization_11 = new Organization(
                "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/",
                positions_15
        );
        courses.add(organization_11);
    }
}
