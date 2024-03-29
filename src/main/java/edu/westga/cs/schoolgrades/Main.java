package edu.westga.cs.schoolgrades;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Babble project
 * 
 * @author lewisb, starter file from Dr. B
 * @version cs6241
 */
public class Main extends Application {

private static final String GUI_RESOURCE = "edu/westga/cs/schoolgrades/views/GradesGui.fxml";
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URL resource = classLoader.getResource(GUI_RESOURCE);
        System.out.println(resource);
       
        final FXMLLoader loader = new FXMLLoader(resource);
        final Parent root = (Parent)loader.load();
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grades Worksheet");
        primaryStage.show();
    }

    /**
     * Start point for the appliction.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

}
