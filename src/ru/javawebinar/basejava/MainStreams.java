package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStreams {
    public static void main(String[] args) {

        assert minValue(new int[] {1, 2, 3, 3, 2, 3}) == 123 : "Not equal";
        assert minValue(new int[] {9, 8}) == 89 : "Not equal";

        assert minValueSimple(new int[] {1, 2, 3, 3, 2, 3}) == 123 : "Not equal";
        assert minValueSimple(new int[] {9, 8}) == 89 : "Not equal";

        assert oddOrEven(new ArrayList<>(Arrays.asList(2, 2, 3, 5))).equals(Arrays.asList(3, 5)) : "Not equal";
        assert oddOrEven(new ArrayList<>(Arrays.asList(2, 2, 3, 5, 1))).equals(Arrays.asList(2, 2)) : "Not equal";
    }

    private static int minValue(int[] values) {
        AtomicInteger counter = new AtomicInteger(0);
        return Arrays.stream(values)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .reduce(0, (a, b) -> a + b * ((int) Math.pow(10, counter.getAndIncrement())));
    }

    private static int minValueSimple(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (a, b) -> a * 10 + b);

    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = (int) integers.stream().mapToInt(Integer::intValue).count();
        return integers
                .stream()
                .filter(item -> (sum % 2 != item % 2))
                .collect(Collectors.toList());
    }
}
