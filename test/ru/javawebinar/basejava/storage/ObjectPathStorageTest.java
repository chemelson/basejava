package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.ObjectStreamSerializer;

import java.nio.file.Paths;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR), new ObjectStreamSerializer()));
    }
}
