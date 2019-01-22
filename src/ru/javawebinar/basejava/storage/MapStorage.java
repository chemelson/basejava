package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = storage.get(uuid);
        if (resume == null) {
            throw new NotExistStorageException(uuid);
        } else {
            return resume;
        }
    }

    @Override
    public void delete(String uuid) {
        boolean resumeExists = storage.containsKey(uuid);
        if (!resumeExists) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(uuid);
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size]);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        List<Resume> resumes = new LinkedList<>(storage.values());
        return resumes.indexOf(resume);
    }
}
