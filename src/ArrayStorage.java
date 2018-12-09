import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume resume) {
        Resume foundResume = search(resume.uuid);
        int currentSize = size();
        if (foundResume == null && currentSize != storage.length) {
            if (currentSize == 0) {
                storage[currentSize] = resume;
            } else {
                storage[currentSize + 1] = resume;
            }
        }
    }

    Resume get(String uuid) {
        return search(uuid);
    }

    void delete(String uuid) {
        Resume resume = search(uuid);
        if (resume != null) {
            int currentSize = size();
            for (int i = 0; i < currentSize; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    for (int j = i; j <= currentSize; j++) {
                        storage[j] = storage[j + 1];
                    }
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return getResumes().toArray(Resume[]::new);
    }

    int size() {
        return (int) getResumes().count();
    }

    private Stream<Resume> getResumes() {
        return Arrays.stream(storage).filter(Objects::nonNull);
    }

    private Resume search(String uuid) {
        return getResumes().filter(resume -> resume.uuid.equals(uuid)).findFirst().orElse(null);
    }
}
