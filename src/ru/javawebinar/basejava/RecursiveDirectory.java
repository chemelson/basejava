package ru.javawebinar.basejava;

import java.io.File;

public class RecursiveDirectory {
    public static void main(String[] args) {
            File root = new File(".");
            printDirectory(root);
    }

    private static void printDirectory(File root) {
       if (root.isFile()) {
            System.out.println(root.getName());
        } else {
            for (File item : root.listFiles()) {
                printDirectory(item);
            }
        }
    }
}
