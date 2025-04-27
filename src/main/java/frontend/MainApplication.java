package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.antlr.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

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
            stage.setWidth(600);
            stage.setHeight(600);

            stage.show();
            //        Image icon = new Image(""); \\ tu sie doda logo jesli bedziemy miec//
            //        stage.getIcons().add(icon);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());

            stage.setResizable(true);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}