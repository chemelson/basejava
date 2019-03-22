package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    public AbstractPathStorage(Path directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory + " is not directory or is not writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return ((int) Files.list(directory).count());
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString(), uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " +
                    path.toAbsolutePath().toString(), path.getFileName().toString(), e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
       try {
           Files.delete(path);
       } catch (IOException e) {
           throw new StorageException("File delete error", path.getFileName().toString(), e);
       }
    }

    @Override
    protected List<Resume> doCopyAll() {
        Stream<Path> paths = null;
        try {
            paths = Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        if (paths != null) {
            return paths.map(this::doGet).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
