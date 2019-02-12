package ru.javawebinar.basejava.model.section;

import java.util.Objects;

public class TextSection implements Section<String> {

  private String content;

  public TextSection() {
  }

  public TextSection(String content) {
    Objects.requireNonNull(content, "content must not be null");
    this.content = content;
  }

  @Override
  public String getContent() {
    return content;
  }

  @Override
  public void setContent(String content) {
    Objects.requireNonNull(content, "content must not be null");
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TextSection that = (TextSection) o;
    return content.equals(that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }
}
