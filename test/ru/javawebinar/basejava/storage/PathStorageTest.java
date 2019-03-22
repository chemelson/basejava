package ru.javawebinar.basejava.storage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new FileStorage(STORAGE_FILE_DIR, new ObjectStreamSerializationStrategy()));
    }
}
