package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataStreamSerializer;
import ru.javawebinar.basejava.storage.serializer.JsonStreamSerializer;

import java.nio.file.Paths;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(Paths.get(STORAGE_DIR), new DataStreamSerializer()));
    }
}
