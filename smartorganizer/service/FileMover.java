package com.smartorganizer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.smartorganizer.model.FileType;

/*
 * Responsible for moving files
 * into categorized folders.
 */
public class FileMover {

    /*
     * Moves file into folder based on FileType.
     * 
     * @param filePath - file to move
     * @param type - category of file
     * @return new file path after moving
     * @throws IOException
     */
    public Path moveFile(Path filePath, FileType type) throws IOException {

        if (filePath == null || type == null) {
            return filePath;
        }

        Path parentDirectory = filePath.getParent();

        // Create folder name using enum name (PDF, IMAGE, etc.)
        Path targetDirectory = parentDirectory.resolve(type.name());

        // Create directory if it does not exist
        if (!Files.exists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }

        // Final path where file will be moved
        Path targetPath = targetDirectory.resolve(filePath.getFileName());

        /*
         * Move file safely.
         * REPLACE_EXISTING avoids crash if file already exists.
         */
        return Files.move(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
}