package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size]);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void saveElement(Resume r, int index) {
        storage.add(r);
        size++;
    }

    @Override
    protected Resume getElement(String uuid, int index) {
        return storage.get(index);
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        Resume resume = storage.get(index);
        storage.remove(resume);
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
