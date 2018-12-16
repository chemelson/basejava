import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size != storage.length) {
            int index = getIndex(resume.uuid);
            if (index == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume can't be saved: duplicate found.");
            }
        } else {
            System.out.println("Resume can't be saved: storage overflow.");
        }
    }

    void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume can't be updated: not found.");
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume can't be retrieved: not found.");
            return null;
        }
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume can't be deleted: not found.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    int size() {
        return size;
    }
}
