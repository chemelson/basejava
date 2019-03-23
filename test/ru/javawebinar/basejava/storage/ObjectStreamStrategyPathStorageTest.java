package ru.javawebinar.basejava.storage;

public class ObjectStreamStrategyPathStorageTest extends AbstractStorageTest {

    public ObjectStreamStrategyPathStorageTest() {
        super(new PathStorage(STORAGE_PATH_DIR, new ObjectStreamSerializationStrategy()));
    }
}
