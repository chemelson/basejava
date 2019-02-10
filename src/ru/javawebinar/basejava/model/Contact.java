package ru.javawebinar.basejava.model;

public class Contact {

    private final String contact;

    public Contact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return contact;
    }
}
