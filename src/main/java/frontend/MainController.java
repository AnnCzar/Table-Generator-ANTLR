package frontend;

import frontend.svg.SVGLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;


import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ComboBox<String> tableInputChoice;

    @FXML
    Button searchButton;

    @FXML
    Button helpButton;

    @FXML
    TextArea tableInput;

    @FXML
    Button generateButton;

    @FXML
    ToggleGroup tableFormat;

    @FXML
    RadioButton htmlRadio;

    @FXML
    RadioButton htmlFlexRadio;

    @FXML
    RadioButton latexRadio;

    @FXML
    TextArea generatedTable;

    @FXML
    Button peepButton;

    @FXML
    Text generatedCode;



    private Popup popup;
    private File selectedFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tableInputs = FXCollections.observableArrayList("Z pliku w formacie .txt", "Wprowadź ręcznie");
        tableInputChoice.setItems(tableInputs);

        popup = new Popup();
        Label label = new Label("tu bedzie instrukcja co do sposobu wprowadzania tabel");
        popup.getContent().add(label);
        popup.setAutoHide(true);

        setSVGIcon(helpButton, "/org/example/images/help_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.svg");
        setSVGIcon(searchButton, "/org/example/images/folder_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.svg");
    }

    @FXML
    private void showPopup() {
        if (!popup.isShowing()) {
            Stage stage = (Stage) helpButton.getScene().getWindow();
            double x = helpButton.localToScreen(helpButton.getWidth(), 0).getX();
            double y = helpButton.localToScreen(0, helpButton.getHeight()).getY();

            popup.show(stage, x, y);
        } else {
            popup.hide();
        }
    }

    @FXML
    private void showPopupData() {
        if (!popup.isShowing()) {
            Stage stage = (Stage) generateButton.getScene().getWindow();
            double x = generateButton.localToScreen(0, 0).getX();
            double y = generateButton.localToScreen(0, generateButton.getHeight()).getY();

            popup.show(stage, x, y);
        } else {
            popup.hide();
        }
    }



    private void setSVGIcon(Button button, String resourcePath) {
        URL imgUrl = getClass().getResource(resourcePath);
        if (imgUrl == null) {
            System.out.println("Błąd: nie znaleziono pliku obrazu " + resourcePath);
            return;
        }

        Group svgImage = SVGLoader.load(imgUrl.toString());
        svgImage.setScaleX(0.04);
        svgImage.setScaleY(0.04);
        button.setGraphic(svgImage);

    }
    @FXML
    private void chooseTableFormat() {
        String selectedOption = tableInputChoice.getValue();

        switch (selectedOption) {
            case "Z pliku w formacie .txt":
                searchButton.setVisible(true);
                tableInput.setDisable(true);
                break;
            case "Wprowadź ręcznie":
                searchButton.setVisible(false);
                tableInput.setDisable(false);
                break;
            default:
                searchButton.setVisible(false);

        }

    }

    @FXML
    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt"));
        Stage stage = (Stage) searchButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Wybrano plik: " + selectedFile.getAbsolutePath());
            try {
                String content = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
                tableInput.setText(content);
                tableInput.setDisable(false);
            } catch (IOException e) {
                System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
                tableInput.setText("Błąd podczas odczytu pliku.");
            }
        } else {
            System.out.println("Nie wybrano pliku.");
        }
    }

    @FXML
    private void generateTable() {
        String selectedOption = tableInputChoice.getValue();
        if (selectedOption != null) {
            switch (selectedOption) {
                case "Z pliku w formacie .txt":
                    if (selectedFile == null) {
                        popup = new Popup();
                        Label label = new Label("Nie wybrano pliku!");
                        popup.getContent().add(label);
                        popup.setAutoHide(true);
                        showPopupData();
                        return;
                    }

                    break;
                case "Wprowadź ręcznie":
                   if(tableInput.getText().isBlank()) {
                       popup = new Popup();
                       Label label = new Label("Nie wprowadzono tabeli");
                       popup.getContent().add(label);
                       popup.setAutoHide(true);
                       showPopupData();
                       return;
                   }
                    break;
            }
        }


        if (selectedOption != null && tableFormat.getSelectedToggle() != null) {
            generatedTable.setVisible(true);
            generatedTable.setEditable(false);
            peepButton.setVisible(true);
            generatedCode.setVisible(true);

        } else {
            popup = new Popup();
            Label label = new Label("Wprowadź wymagane dane");
            popup.getContent().add(label);
            popup.setAutoHide(true);
            showPopupData();
        }

    }

}

