package ru.javawebinar.basejava.model.section;


public interface Section<CNT> {

    CNT getContent();

    void setContent(CNT content);
}
