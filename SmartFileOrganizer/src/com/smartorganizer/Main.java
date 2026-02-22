package com.smartorganizer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.smartorganizer.logger.AppLogger;
import com.smartorganizer.watcher.FolderWatcher;

/*
 * Main class - Entry point of the application
 */
public class Main {

    private static ExecutorService executorService;
    private static FolderWatcher folderWatcher;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {
                executorService = Executors.newSingleThreadExecutor();
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Smart File Organizer");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Select a folder to start monitoring", SwingConstants.CENTER);

        JButton startButton = new JButton("Start Watching Folder");

        startButton.addActionListener((ActionEvent e) -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {

                Path selectedPath = chooser.getSelectedFile().toPath();

                // Stop previous watcher if running
                if (folderWatcher != null) {
                    folderWatcher.stop();
                }

                folderWatcher = new FolderWatcher(selectedPath);
                executorService.submit(folderWatcher);

                label.setText("Watching: " + selectedPath.toString());

                AppLogger.getLogger().info("Started watching folder: " + selectedPath);
            }
        });

        frame.add(label, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}