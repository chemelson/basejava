package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid, null);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(getUuid(searchKey), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(getUuid(searchKey));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(getUuid(searchKey), r);
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
    public List<Resume> getAllSorted() {
        return storage.values()
                .stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }

    private String getUuid(Object searchKey) {
        return ((Resume) searchKey).getUuid();
    }

}