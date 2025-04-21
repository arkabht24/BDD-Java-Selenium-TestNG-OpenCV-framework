package com.orangehrm.utils;

import java.io.File;

public class FileUtils {
    public void createDirectoryIfNotExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory created at: " + dirPath);
            } else {
                System.out.println("Failed to create directory at: " + dirPath);
            }
        } else {
            System.out.println("Directory already exists at: " + dirPath);
        }
    }

    public boolean deleteDirectory(String dirPath) {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + dirPath);
            return false;
        }

        return deleteRecursively(directory);
    }

    private boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] allContents = file.listFiles();
            if (allContents != null) {
                for (File content : allContents) {
                    deleteRecursively(content);
                }
            }
        }
        return file.delete();
    }
}
