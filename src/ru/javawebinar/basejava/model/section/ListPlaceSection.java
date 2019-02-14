package ru.javawebinar.basejava.model.section;

import java.util.List;
import java.util.Objects;

public class ListPlaceSection implements Section {

    private List<Place> content;

    public ListPlaceSection(List<Place> content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public List<Place> getContent() {
        return content;
    }

    public void setContent(List<Place> content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPlaceSection that = (ListPlaceSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "ListPlaceSection{" +
                "content=" + content +
                '}';
    }
}
