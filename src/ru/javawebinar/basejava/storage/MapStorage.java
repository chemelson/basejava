package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size]);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveElement(Resume r, int index) {
        storage.put(r.getUuid(), r);
        size++;
    }

    @Override
    protected Resume getElement(String uuid, int index) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        storage.remove(uuid);
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        List<Resume> resumes = new LinkedList<>(storage.values());
        return resumes.indexOf(resume);
    }
}
