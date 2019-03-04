package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class RecursiveDirectory {
    public static void main(String[] args) {
        try {
            File root = new File(".");
            printDirectory(root, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void printDirectory(File root, int tabLevel) throws IOException {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < tabLevel; i++) {
            tabs.append("\t");
        }
        System.out.println(tabs.toString() + root.getName());
        if (root.isDirectory()) {
            for (File item : root.listFiles()) {
                printDirectory(item, tabLevel + 1);
            }
        }
    }
}
