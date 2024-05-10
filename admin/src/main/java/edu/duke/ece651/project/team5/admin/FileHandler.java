package edu.duke.ece651.project.team5.admin;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.Stage;

public class FileHandler {

    public static String getFilePath(Stage primaryStage) throws IllegalArgumentException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        // Set initial directory for convenience (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Open the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        String relativePath = "";
        if (selectedFile != null) {
            // Get the relative path
            relativePath = getRelativePath(selectedFile);
            System.out.println("Relative Path: " + relativePath);
            return relativePath;
        } else {
            System.out.println("File selection cancelled.");
            throw new IllegalArgumentException("File selection cancelled.");
        }
        
    }
    public static String getRelativePath(File file) {
        Path absolutePath = Paths.get(file.getAbsolutePath());
        Path basePath = Paths.get(System.getProperty("user.dir"));
        Path relativePath = basePath.relativize(absolutePath);
        return relativePath.toString();
    }
}
