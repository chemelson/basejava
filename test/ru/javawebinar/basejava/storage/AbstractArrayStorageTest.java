package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException ex) {
            Assert.fail("Error on save before reaching storage limit.");
        }
        storage.save(new Resume());

    }
}
