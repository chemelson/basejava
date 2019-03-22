package ru.javawebinar.basejava.storage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_PATH_DIR, new ObjectStreamSerializationStrategy()));
    }
}
