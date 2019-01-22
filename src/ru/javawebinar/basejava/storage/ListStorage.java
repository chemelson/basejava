package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.set(index, r);
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
            storage.add(r);
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage.get(index);
        }
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(resume);
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size]);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
