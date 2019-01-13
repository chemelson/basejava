package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    private SortedArrayStorage sortedArrayStorage;

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        sortedArrayStorage = (SortedArrayStorage) storage;
    }

    @Test
    public void fillDeletedElement() throws Exception {
        sortedArrayStorage.fillDeletedElement(0);
        Assert.assertEquals(0, sortedArrayStorage.getIndex("uuid2"));
    }

    @Test
    public void insertElement() throws Exception {
        sortedArrayStorage.delete(UUID_2);
        Resume resume = new Resume(UUID_2);
        sortedArrayStorage.insertElement(resume, -2);
        Assert.assertEquals(1, sortedArrayStorage.getIndex(UUID_2));
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, sortedArrayStorage.getIndex(UUID_1));
        Assert.assertEquals(-4, sortedArrayStorage.getIndex("uuid4"));
    }

}