package ru.javawebinar.basejava.storage;

public class ObjectStreamStrategyPathStorageTest extends AbstractStorageTest {

    public ObjectStreamStrategyPathStorageTest() {
        super(new FileStorage(STORAGE_FILE_DIR, new ObjectStreamSerializationStrategy()));
    }
}
