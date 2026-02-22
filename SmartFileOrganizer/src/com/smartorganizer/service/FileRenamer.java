package com.smartorganizer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Responsible for renaming files
 * using a date-time pattern.
 */
public class FileRenamer {

    // Date format pattern for renaming
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    /*
     * Renames the file using current timestamp.
     * Keeps original extension.
     * 
     * @param filePath - path of the file
     * @return new renamed Path
     * @throws IOException
     */
    public Path renameFile(Path filePath) throws IOException {

        if (filePath == null || !Files.exists(filePath)) {
            return filePath;
        }

        String fileName = filePath.getFileName().toString();

        int lastDotIndex = fileName.lastIndexOf(".");

        String extension = "";
        if (lastDotIndex != -1) {
            extension = fileName.substring(lastDotIndex);
        }

        // Generate new name using current timestamp
        String newFileName = FORMATTER.format(LocalDateTime.now()) + extension;

        Path newFilePath = filePath.getParent().resolve(newFileName);

        /*
         * Use REPLACE_EXISTING carefully.
         * In real systems, we might handle duplicates differently.
         */
        return Files.move(filePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
    }
}