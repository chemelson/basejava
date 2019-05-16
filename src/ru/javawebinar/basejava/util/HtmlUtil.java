package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Organization;

public class HtmlUtil {
    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }

}

