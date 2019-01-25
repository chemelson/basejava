package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected boolean isElementExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void updateElement(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected void saveElement(Resume r, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", r.getUuid());
        }
        insertElement(r, searchKey);
        size++;
    }

    @Override
    protected Resume getElement(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void deleteElement(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

}