package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

public class MainTestSortedArrayStorage {
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");

        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r4);
        SORTED_ARRAY_STORAGE.save(r1);

        System.out.println("\nSaved:");
        printAll();

        System.out.println("\nUpdated:");
        SORTED_ARRAY_STORAGE.update(r4);

        System.out.println("\nDeleted:");
        SORTED_ARRAY_STORAGE.delete("uuid2");
        printAll();

        System.out.println("\nGot resume:");
        System.out.println(SORTED_ARRAY_STORAGE.get("uuid1"));

        System.out.println("\nGot fake resume");
        System.out.println(SORTED_ARRAY_STORAGE.get("fake"));
    }

    static private void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
