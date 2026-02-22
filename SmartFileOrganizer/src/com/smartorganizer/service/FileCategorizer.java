package com.smartorganizer.service;

import java.nio.file.Path;

import com.smartorganizer.model.FileType;

/*
 * Responsible for identifying the type of file
 * based on its extension.
 */
public class FileCategorizer {

    /*
     * Determines the file type using file extension.
     * 
     * @param path - file path
     * @return FileType
     */
    public FileType categorize(Path path) {

        if (path == null) {
            return FileType.UNKNOWN;
        }

        String fileName = path.getFileName().toString();

        // Check if file has an extension
        int lastDotIndex = fileName.lastIndexOf(".");

        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return FileType.UNKNOWN;
        }

        String extension = fileName.substring(lastDotIndex + 1).toLowerCase();

        switch (extension) {

            case "pdf":
                return FileType.PDF;

            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return FileType.IMAGE;

            case "doc":
            case "docx":
                return FileType.DOCUMENT;

            case "txt":
                return FileType.TEXT;

            default:
                return FileType.UNKNOWN;
        }
    }
}