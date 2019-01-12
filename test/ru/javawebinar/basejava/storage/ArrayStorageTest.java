package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private ArrayStorage arrayStorage;

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        arrayStorage = (ArrayStorage) storage;
    }

    @Test
    public void fillDeletedElement() throws Exception {
        arrayStorage.fillDeletedElement(0);
        Assert.assertEquals(0, arrayStorage.getIndex(UUID_3));
    }

    @Test
    public void insertElement() throws Exception {
        Resume resume = new Resume("uuid4");
        arrayStorage.insertElement(resume, 0);
        Assert.assertEquals(4, arrayStorage.size() + 1);
    }

    @Test
    public void getIndex() {
        int index = arrayStorage.getIndex("uuid1");
        Assert.assertEquals(0, index);
    }
}