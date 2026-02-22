package com.smartorganizer.model;

/*
 * Enum representing supported file categories.
 * 
 * This helps avoid using raw strings like "pdf", "image", etc.
 * Cleaner and safer design.
 */
public enum FileType {

    PDF,
    IMAGE,
    DOCUMENT,
    TEXT,
    UNKNOWN;
}