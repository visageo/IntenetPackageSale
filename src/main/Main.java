package main;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxml = getClass().getClassLoader().getResource("fxml/gui.fxml");
        HBox root = new HBox(10);
        root = FXMLLoader.<HBox>load(fxml);


        Scene s = new Scene(root, 605, 375);
        s.getStylesheets().add("custom.css");
        primaryStage.setTitle("Internet Package Sales Registry");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
