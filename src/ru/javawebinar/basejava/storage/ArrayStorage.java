package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based ru.javawebinar.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected void insert(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void remove(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }


}
