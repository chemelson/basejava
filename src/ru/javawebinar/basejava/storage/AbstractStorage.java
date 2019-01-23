package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateElement(r, index);
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveElement(r, index);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(uuid, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteElement(uuid, index);
    }

    public void clear() {
        clearStorage();
        size = 0;
    }

    public int size() {
        return size;
    }

    protected abstract void clearStorage();

    protected abstract int getIndex(String uuid);

    protected abstract void updateElement(Resume r, int index);

    protected abstract void saveElement(Resume r, int index);

    protected abstract Resume getElement(String uuid, int index);

    protected abstract void deleteElement(String uuid, int index);
}
