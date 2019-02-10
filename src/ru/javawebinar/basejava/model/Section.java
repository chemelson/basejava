package ru.javawebinar.basejava.model;

import java.util.List;

public interface Section<CNT> {

    List<CNT> getContent();

    void setContent(List<CNT> content);
}
