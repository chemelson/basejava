package ru.javawebinar.basejava.storage;

public class ObjectStreamStrategyFileStorageTest extends AbstractStorageTest {

    public ObjectStreamStrategyFileStorageTest() {
        super(new FileStorage(STORAGE_FILE_DIR, new ObjectStreamSerializationStrategy()));
    }
}
