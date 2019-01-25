package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected static final List<Resume> storage = new ArrayList<>(0);

    @Override
    protected Integer getSearchKey(String uuid) {
        if (uuid == null) return -1;
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals((storage.get(i).getUuid()))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isElementExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void updateElement(Resume r, Integer searchKey) {
        storage.set(searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, Integer searchKey) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void deleteElement(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
