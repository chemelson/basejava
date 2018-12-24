package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void insert(int index, Resume r) {
        int position = -index - 1;
        if (size != 0) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = r;
    }

    protected void remove(int index) {
        int lastIndex = size - 1;
        if ((size != 1) && (index != lastIndex)) {
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        }
        storage[lastIndex] = null;
    }
}
