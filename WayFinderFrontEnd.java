import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface WayFinderFrontEndInterface {
	public void start(Stage stage) throws FileNotFoundException;

	public List<String> sendInputToBack(String start, String end);
}

public class WayFinderFrontEnd extends Application implements WayFinderFrontEndInterface {
	public static WayFinderBackEnd<String> graph;

	public void start(Stage primaryStage) {
		try {
			startFront(primaryStage);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public void startFront(Stage stage) throws FileNotFoundException {
		InputStream stream = new FileInputStream("railSystem.png");
		Image image = new Image(stream);
		// Creating the image view
		ImageView imageView = new ImageView();
		// Setting image to the image view
		imageView.setImage(image);
		// Setting the image view parameters
		imageView.setX(20);
		imageView.setY(5);
		imageView.setFitWidth(750);
		imageView.setFitHeight(500);
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(50, 50, 50, 50));
		// Creating nodes
		TextField textField1 = new TextField("Enter the starting city");
		TextField textField2 = new TextField("Enter the ending city");
		// Creating labels
		Label label1 = new Label("Departure from: ");
		label1.setStyle("-fx-text-fill:BLACK; -fx-font-size: 20; -fx-font-family: Barlow;");
		Label label2 = new Label("Arrival to: ");
		label2.setStyle("-fx-text-fill:BLACK; -fx-font-size: 20; -fx-font-family: Barlow;");
		Label label3 = new Label("", imageView);
		Label optionTitle = new Label("City Options:\n");
		optionTitle.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
		Label options = new Label(graph.cityNames());
		options.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
		Button sButton = new Button("Submit");
		Button rButton = new Button("Reset");
		label3.setPadding(new Insets(25, 5, 5, 50));
		// Adding labels for nodes
		VBox box = new VBox(5);
		box.setPadding(new Insets(25, 5, 5, 50));
		box.getChildren().addAll(label1, textField1, label2, textField2, sButton, optionTitle, options);
		borderPane.setLeft(box);
		borderPane.setCenter(label3);
		Scene scene = new Scene(borderPane, 1300, 700, Color.BEIGE);
		scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@300&display=swap");
		stage.setTitle("High Speed Rail Trip");

		sButton.setOnAction(e -> {
			String start = String.valueOf(textField1.getText());
			String end = String.valueOf(textField2.getText());
			// Label label4 = new Label(result(start, end));
			// label4.setStyle("-fx-text-fill:BLUE; -fx-font-size: 15; -fx-font-family:
			// Barlow;");
			box.getChildren().addAll(rButton);
			String path = "";
			Label finalPath = new Label("Final Route");
			Label totalMiles = new Label(" 0 Miles");

			if (start.equals(end)) {
				finalPath = new Label("Start and end location are the same.");
			} else {

				if (!options.getText().contains(start + ", ") || !options.getText().contains(end + ", ")) {
					finalPath = new Label("Start or end location is not in the high speed rail system.");
				} else {

					Iterator<String> it = sendInputToBack(start, end).iterator();
					int count = 0;
					while (it.hasNext()) {
						path += it.next() + " ->";
						count++;
						if (count % 11 == 0) {
							path += "\n";
						}
					}
					path = path.substring(0, path.length() - 2);
					String pathSize = "Total Distance:  " + pathCost(start, end) + " miles";
					finalPath = new Label(path);
					totalMiles = new Label(pathSize);
				}
			}
			totalMiles.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
			finalPath.setStyle("-fx-text-fill:BLUE; -fx-font-size: 15; -fx-font-family: Barlow;");
			HBox hbox = new HBox(finalPath, totalMiles);
			hbox.setAlignment(Pos.TOP_CENTER);
			borderPane.setTop(hbox);

			rButton.setOnAction(r -> {
				restart(stage);
			});

		});

		stage.setScene(scene);
		stage.show();
	}

	public void restart(Stage stage) {
		try {
			startFront(stage);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		launch(args);
	}

	// sends input and retrieves shortest path
	@Override
	public List<String> sendInputToBack(String start, String end) {
			return graph.shortestPath(start, end);	
	}

	public int pathCost(String start, String end) {
		return graph.getPathCost(start, end);
	}

	public String result(String start, String end) {
		return "Start: " + start + "\n End: " + end;
	}

	public String simSubmit(String start, String end) {
		String options = graph.cityNames();
		String path = "";
		if (start.equals(end)) {
			path = "Start and end location are the same.";
		} else {

			if (!options.contains(start + ", ") || !options.contains(end + ", ")) {
				path = "Start or end location is not in the high speed rail system.";
			} else {

				Iterator<String> it = sendInputToBack(start, end).iterator();
				int count = 0;
				while (it.hasNext()) {
					path += it.next() + " ->";
					count++;
					if (count % 11 == 0) {
						path += "\n";
					}
				}
				path = path.substring(0, path.length() - 2);
			}

		}
		return path;
	}
}