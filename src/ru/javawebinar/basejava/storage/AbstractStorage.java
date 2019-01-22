package ru.javawebinar.basejava.storage;


public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    public void clear() {
        clearStorage();
        size = 0;
    }

    public int size() {
        return size;
    }

    protected abstract void clearStorage();

    protected abstract int getIndex(String uuid);

}
