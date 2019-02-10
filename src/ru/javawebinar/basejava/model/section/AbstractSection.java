package ru.javawebinar.basejava.model.section;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSection<CNT> implements Section<CNT> {

    private List<CNT> content;

    @Override
    public List<CNT> getContent() {
        return new ArrayList<>(content);
    }

    @Override
    public void setContent(List<CNT> content) {
        this.content = content;
    }
}
