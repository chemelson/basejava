package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid, null);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put(getUuid(searchKey), resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(getUuid(searchKey));
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(getUuid(searchKey), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(getUuid(searchKey));
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(getUuid(searchKey));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Stream<Resume> getResumeStream() {
        return storage.values().stream();
    }

    private String getUuid(Object searchKey) {
        return ((Resume) searchKey).getUuid();
    }

}