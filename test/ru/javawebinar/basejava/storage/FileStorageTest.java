package ru.javawebinar.basejava.storage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_FILE_DIR, new ObjectStreamSerializationStrategy()));
    }
}
