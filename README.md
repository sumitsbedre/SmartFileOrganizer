<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
</head>
<body>

<div class="container">

<h1>Smart File Organizer 📂⚡</h1>

<h2>Overview</h2>
<p>
Tired of manually organizing messy folders? Want files to automatically sort themselves the moment they are added?
</p>
<p>
Smart File Organizer is a Java Swing desktop application that monitors a folder in real-time and automatically categorizes, renames, and organizes files into structured directories.
</p>
<p>
It leverages core Java SE features like <code>WatchService</code>, <code>ExecutorService</code>, and <code>java.nio.file</code> to build a lightweight system-level utility.
</p>

<h2>Features</h2>
<ul>
    <li><strong>Real-Time Folder Monitoring</strong> using WatchService</li>
    <li><strong>Automatic File Categorization</strong> (PDF, Images, Documents, Text, etc.)</li>
    <li><strong>Timestamp-Based Renaming</strong></li>
    <li><strong>Automatic Folder Creation</strong></li>
    <li><strong>Background Processing</strong> using ExecutorService</li>
    <li><strong>Centralized Logging</strong> using java.util.logging</li>
    <li><strong>Clean Layered Architecture</strong></li>
</ul>

<h2>Tech Stack</h2>
<ul>
    <li>Java SE</li>
    <li>Java Swing (GUI)</li>
    <li>WatchService API</li>
    <li>ExecutorService (Multithreading)</li>
    <li>java.nio.file</li>
    <li>java.util.logging</li>
</ul>

<h2>Prerequisites</h2>
<ul>
    <li>Java 8 or above</li>
    <li>Eclipse IDE (or any Java IDE)</li>
</ul>

<h2>Installation</h2>
<pre>
git clone https://github.com/sumitsbedre/SmartFileOrganizer.git
cd SmartFileOrganizer
</pre>

<h2>Usage</h2>
<p>Run the <code>Main.java</code> file as a Java Application.</p>

<ol>
    <li>Click <strong>Start Watching Folder</strong></li>
    <li>Select a folder</li>
    <li>Add files into that folder</li>
    <li>Files will be automatically categorized, renamed, and moved</li>
</ol>

<h2>Project Structure</h2>
<pre>
com.smartorganizer
 ├── Main
 ├── watcher
 │     └── FolderWatcher
 ├── service
 │     ├── FileCategorizer
 │     ├── FileRenamer
 │     └── FileMover
 ├── exception
 │     └── FileProcessingException
 ├── logger
 │     └── AppLogger
 └── model
       └── FileType
</pre>

<h2>Future Improvements</h2>
<ul>
    <li>Stop/Resume monitoring</li>
    <li>Log display panel in UI</li>
    <li>File-type configuration settings</li>
    <li>System tray support</li>
    <li>Retry mechanism for locked files</li>
</ul>

<h2>Contributing</h2>
<p>
Contributions are welcome! Feel free to open an issue or submit a pull request.
</p>

</div>

</body>
</html>
