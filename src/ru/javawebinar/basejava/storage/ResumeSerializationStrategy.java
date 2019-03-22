package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ResumeSerializationStrategy {

    void serialize(Resume resume, OutputStream os) throws IOException;

    Resume deserialize(InputStream is) throws IOException;
}
