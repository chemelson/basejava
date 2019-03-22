package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class PathStorage extends AbstractPathStorage {

    private ResumeSerializationStrategy serializationStrategy;

    protected PathStorage(Path directory, ResumeSerializationStrategy serializationStrategy) {
        super(directory);
        this.serializationStrategy =  serializationStrategy;
    }

    @Override
    protected void doWrite(Resume resume, OutputStream os) throws IOException {
        serializationStrategy.serialize(resume, os);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return serializationStrategy.deserialize(is);
    }
}
