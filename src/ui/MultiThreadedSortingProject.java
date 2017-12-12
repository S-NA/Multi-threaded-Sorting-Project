package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultiThreadedSortingProject extends Application {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ControlPanel.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Threaded Sorting");
		stage.show();
	}

}
