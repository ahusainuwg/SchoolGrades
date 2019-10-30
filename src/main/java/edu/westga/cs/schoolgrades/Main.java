package edu.westga.cs.schoolgrades;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Main class for the Babble project
 * 
 * @author lewisb
 * @version cs6241
 */
public class Main extends Application {

	private static final String GUI_RESOURCE = "edu/westga/cs/schoolgrades/views/GradesGui.fxml";
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        URL resource = classLoader.getResource(Main.GUI_RESOURCE);
//        FXMLLoader loader = new FXMLLoader(resource);
//        Parent root = (Parent) loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Grades Worksheet");
//        primaryStage.show();
    }

    /**
     * Start point for the application.
     * 
     * @param args not used
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
