package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void saveElement(Resume r, int index) {
        insertElement(r, index);
        size++;
    }

    @Override
    protected Resume getElement(String uuid, int index) {
        return storage[index];
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

}