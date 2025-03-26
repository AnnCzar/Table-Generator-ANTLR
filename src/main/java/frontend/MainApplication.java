package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/org/example/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(true);
            stage.setTitle("Table generator");
            String css = Objects.requireNonNull(this.getClass().getResource("/org/example/styles.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
            //        Image icon = new Image(""); \\ tu sie doda logo jesli bedziemy miec//
            //        stage.getIcons().add(icon);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX(screenBounds.getMinX());
//        stage.setY(screenBounds.getMinY());
//        stage.setWidth(screenBounds.getWidth());
//        stage.setHeight(screenBounds.getHeight());






    }

    public static void main(String[] args) {
        launch(args);
    }
}