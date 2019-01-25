package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    public void update(Resume r) {
        SK searchKey = getExistingSearchKey(r.getUuid());
        updateElement(r, searchKey);
    }

    public void save(Resume r) {
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        saveElement(r, searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        deleteElement(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        return getElement(searchKey);
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isElementExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isElementExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isElementExist(SK searchKey);

    protected abstract void updateElement(Resume r, SK searchKey);

    protected abstract void saveElement(Resume r, SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract void deleteElement(SK searchKey);
}
