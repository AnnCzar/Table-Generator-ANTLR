package frontend;

import frontend.svg.SVGLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.example.Main;


import javax.print.DocFlavor;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private String selectedFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tableInputs = FXCollections.observableArrayList("Z pliku w formacie .txt", "Wprowadź ręcznie");
        tableInputChoice.setItems(tableInputs);

        popup = new Popup();
        Label label = new Label("""
                Należy rozpocząć od deklaracji tabeli słowem kluczowym "table", po czym należy podać unikalną nazwę tabeli (bez spacji i cudzysłowów).
                Całą treść tabeli należy zawrzeć w nawiasach klamrowych { }.
                Aby zdefiniować liczbę kolumn, należy użyć słowa kluczowego "columns", po którym umieszcza się dwukropek i liczbę określającą ilość kolumn (np. columns: 3). 
                Następnie należy określić wyrównanie zawartości komórek za pomocą "align: left | right | center", wybierając jedną z trzech opcji.
                Opcjonalnie można dodać styl obramowania tabeli poprzez "border: frame | grid | none", gdzie:
                    - frame - obramowanie tylko wokół tabeli
                    - grid - pełna siatka z liniami między komórkami
                    - none - brak obramowania
                
                Kolejnym elementem jest nagłówek tabeli, który deklaruje się przez "header: {"nagłówek1" | "nagłówek2" | ...}"
                Poszczególne nagłówki kolumn oddziela się pionowymi kreskami | i każdy musi być ujęty w podwójne cudzysłowy.
                Do wprowadzania danych w tabeli służy sekcja "rows". Po dwukropku należy umieścić nawias klamrowy {, a w nim kolejne wiersze danych, każdy ujęty w osobne nawiasy okrągłe ( ). 
                Poszczególne komórki w wierszu oddziela się pionowymi kreskami, np.: ("dane1" | "dane2" | ...). Każda wartość w komórce musi być otoczona podwójnymi cudzysłowami.
                
                Dodatkowo tekst w komórkach można formatować używając przed wartością słów kluczowych bold (pogrubienie) lub italic (kursywa), np.: (bold "ważne" | italic "uwaga").
                Każdy wiersz należy zamknąć nawiasem okrągłym, a całą sekcję wierszy nawiasem klamrowym. Na końcu zamykamy główny blok tabeli nawiasem klamrowym.
                
                Dopuszczalne jest wstawienie nowej tabeli do pojedynczej komórki.
                
                Przykładowa tabela:
                table nazwa_tabeli {
                    columns: liczba_kolumn
                    align: left | right | center
                    [border: frame | grid | none]  <!-- opcjonalne -->
                    header: { "Nagłówek 1" | "Nagłówek 2" | ... }
                    rows: {
                        ("wartość 1" | "wartość 2" | ...)
                        (bold "wartość 1" | italic "wartość 2" | ...)
                        ...
                    }
                }  
                """);
        popup.getContent().add(label);
        popup.setAutoHide(true);

        setSVGIcon(helpButton, "/org/example/images/help_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.svg");
        setSVGIcon(searchButton, "/org/example/images/folder_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24.svg");
        searchButton.setText("");
        helpButton.setText("");

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
    private void chooseTableInput() {
        String selectedOption = tableInputChoice.getValue();
        Main.setOption(selectedOption);

        switch (selectedOption) {
            case "Z pliku w formacie .txt":
                searchButton.setVisible(true);
                tableInput.setDisable(true);
                System.out.println("z pliku: " + tableInput.getText());
                break;
            case "Wprowadź ręcznie":
                searchButton.setVisible(false);
                tableInput.setDisable(false);
                System.out.println("ręcznie " + tableInput.getText());
                break;
            default:
                searchButton.setVisible(false);

        }

    }



    @FXML
    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt", "*.first" ));
        Stage stage = (Stage) searchButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Wybrano plik: " + selectedFile.getAbsolutePath());
            Main.setFilename(selectedFile.getAbsolutePath());
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
        Main.setOption(selectedOption);

        RadioButton selectedRadioButton = (RadioButton) tableFormat.getSelectedToggle();
        selectedFormat = selectedRadioButton.getText();
        Main.setFormat(selectedFormat);

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
                   } else {
                       String content = tableInput.getText();
                       Main.setTableInput(content);
                   }
                    break;
            }
        }

        if (selectedOption != null && tableFormat.getSelectedToggle() != null) {
            try {
                Main.main(null);
                generatedTable.setVisible(true);
                generatedTable.setEditable(false);
                peepButton.setVisible(true);
                generatedCode.setVisible(true);
                String output = Main.getOutput();
                generatedTable.setText(output);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                String message = "Niewłaściwy format tabeli";
                alert.setContentText(message);
                alert.showAndWait();
            }

        } else {
            popup = new Popup();
            Label label = new Label("Wprowadź wymagane dane");
            popup.getContent().add(label);
            popup.setAutoHide(true);
            showPopupData();
        }

    }

    @FXML
    private void peepTable() {
        if (selectedFormat != null) {
            try {
                switch (selectedFormat) {
                    case "html flex":
                        File htmlFileFlex = new File("C:flex_current.html");
                        if (htmlFileFlex.exists()) {
                            Desktop.getDesktop().browse(htmlFileFlex.toURI());
                        }
                        break;

                    case "LaTeX":
                        File texFile = new File("C:latex_current.tex");
                        if (texFile.exists()) {
                            try {
                                Desktop.getDesktop().open(texFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                    case "html":
                        System.out.println("to do");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

