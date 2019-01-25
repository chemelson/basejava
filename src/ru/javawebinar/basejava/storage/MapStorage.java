package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    protected static final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        if (uuid == null) return null;
        Resume r = storage.get(uuid);
        return r == null ? null : r.getUuid();
    }

    @Override
    protected boolean isElementExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateElement(Resume r, String searchKey) {
        storage.put(searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, String searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void deleteElement(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
