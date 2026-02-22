package com.smartorganizer.watcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import com.smartorganizer.exception.FileProcessingException;
import com.smartorganizer.logger.AppLogger;
import com.smartorganizer.model.FileType;
import com.smartorganizer.service.FileCategorizer;
import com.smartorganizer.service.FileMover;
import com.smartorganizer.service.FileRenamer;

/*
 * Watches a folder for new files using WatchService API.
 * 
 * This class implements Runnable so it can run
 * inside ExecutorService (background thread).
 */
public class FolderWatcher implements Runnable {

    private final Path folderPath;
    private volatile boolean running = true;

    private final FileCategorizer categorizer = new FileCategorizer();
    private final FileRenamer renamer = new FileRenamer();
    private final FileMover mover = new FileMover();

    public FolderWatcher(Path folderPath) {
        this.folderPath = folderPath;
    }

    /*
     * Stops the watcher safely.
     */
    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            folderPath.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE
            );

            AppLogger.getLogger().info("Watching folder: " + folderPath);

            while (running) {

                WatchKey key = watchService.take(); // blocks until event occurs

                List<WatchEvent<?>> events = key.pollEvents();

                for (WatchEvent<?> event : events) {

                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    Path createdFile = folderPath.resolve((Path) event.context());

                    processFile(createdFile);
                }

                boolean valid = key.reset();

                if (!valid) {
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            AppLogger.getLogger().severe("Watcher stopped due to error: " + e.getMessage());
        }
    }

    /*
     * Handles the full processing pipeline:
     * Categorize → Rename → Move
     */
    private void processFile(Path filePath) {

        try {

            // Small delay to avoid processing file while still copying
            Thread.sleep(500);

            if (!Files.isRegularFile(filePath)) {
                return;
            }

            FileType type = categorizer.categorize(filePath);

            Path renamedFile = renamer.renameFile(filePath);

            mover.moveFile(renamedFile, type);

            AppLogger.getLogger().info("Processed file: " + filePath.getFileName());

        } catch (Exception e) {
            AppLogger.getLogger().severe("Failed to process file: " + e.getMessage());
        }
    }
}