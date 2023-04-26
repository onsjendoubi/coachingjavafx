/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Coach;
import first_sprint_crud.entities.Programme;
import first_sprint_crud.services.ProgrammeService;
import first_sprint_crud.util.MyDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;


import javafx.util.StringConverter;
/**
 * FXML Controller class
 *
 * @author ons
 */
public class MainProgrammeController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private TableView<Programme> tabview;
    @FXML
    private TableColumn<Programme, String> nomT;
    @FXML
    private TableColumn<Programme, String> typeT;
    @FXML
    private TableColumn<Programme, Integer> dureeT;
    @FXML
    private TableColumn<Programme, Integer> likesT;
    @FXML
    private TableColumn<Programme, String> MediaT;
    @FXML
    private TableColumn<Programme, Integer> idT;

    ProgrammeService psm = new ProgrammeService();
    @FXML
    private ImageView imgv;
    @FXML
    private Label imgurl;
    @FXML
    private TextField nom;
    @FXML
    private TextField duree;
    @FXML
    private ComboBox<String> type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 0, c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            } else {
                return null;
            }
        });
        
        duree.setTextFormatter(formatter);
        
        tabview.setEditable(true);
        nomT.setCellFactory(TextFieldTableCell.forTableColumn());

        // Set cell factory for prenomT
        //typeT.setCellFactory(TextFieldTableCell.forTableColumn());
        //typeT.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeT.setOnEditCommit(event -> {

            TablePosition<Programme, String> pos = event.getTablePosition();
            String newType = event.getNewValue();
            int row = pos.getRow();
            Programme programme = event.getTableView().getItems().get(row);
            programme.setType(newType);
            psm.modifier(programme);
            show();
        });

       // dureeT.setCellFactory(TextFieldTableCell.forTableColumn());
       dureeT.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
    @Override
    public String toString(Integer value) {
        if (value == null) {
        return "";
    }
    return value.toString();
    }
    
    @Override
    public Integer fromString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}));
        ObservableList<String> progtypes = FXCollections.observableArrayList(
                "Abdou",
                "Yoga",
                "Musculation",
                "Fitness"
        );

        type.setItems(progtypes);
        show();
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        if (nom.getText().isEmpty() || type.getSelectionModel().getSelectedIndex() == 0 || imgurl.getText().isEmpty() || duree.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else {

            Image img3 = new Image("file:/" + imgurl.getText());

// Extract the file extension from the image URL
            String extension = "";
            int i = imgurl.getText().lastIndexOf('.');
            if (i > 0) {
                extension = imgurl.getText().substring(i + 1);
            }

            String nameImage = generateRandomPassword(10) + "." + extension;
            String imagepath = imgurl.getText();
            // System.out.println(imagepath);
            Path source = Paths.get(imagepath); //original file
            Path targetDir = Paths.get(MyDB.url_target);

            Files.createDirectories(targetDir);//in case target directory didn't exist
            Path target = targetDir.resolve(MyDB.url_target + nameImage);// create new path ending with `name` content
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

            Programme b = new Programme(nom.getText(), type.getSelectionModel().getSelectedItem(), nameImage, Integer.parseInt(duree.getText()), 0);

            psm.ajouter(b);
            
            nom.setText("");
            duree.setText("");
            imgurl.setText("");
            imgv.setImage(null);
            
           
            show();
        }

    }

    public String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    @FXML
    private void onremove(ActionEvent event) {
        Programme p = tabview.getSelectionModel().getSelectedItem();
       psm.supprimer(p.getId());
      show();
    }

    public void show() {

        MediaT.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Programme, String> cell = new TableCell<Programme, String>() {
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        Image image = new Image(item); // Convert string to Image
                        imageview.setImage(image);
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });
        MediaT.setCellValueFactory(new PropertyValueFactory<Programme, String>("media"));
        idT.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("id"));
        nomT.setCellValueFactory(new PropertyValueFactory<Programme, String>("nom"));

        ObservableList<String> options = FXCollections.observableArrayList("Abdou",
                "Yoga",
                "Musculation",
                "Fitness");

// Set up the typeT column
        typeT.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeT.setCellFactory(ComboBoxTableCell.forTableColumn(options));

        dureeT.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("dure"));
        likesT.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("likes"));

        //tabB.setItems(list);
        ObservableList<Programme> items = FXCollections.observableArrayList(psm.recuperer());
        tabview.setItems(items);

    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File f = fileChooser.showOpenDialog(new Stage());
        imgurl.setText(f.getAbsoluteFile().toURI().toString());

        if (imgurl.getText().startsWith("file:/")) {
            imgurl.setText(imgurl.getText().substring("file:/".length()));
        }
        System.out.println(imgurl.getText());

        try {
            FileInputStream fis = new FileInputStream(f);
            Image image = new Image(fis);
            imgv.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditNom(TableColumn.CellEditEvent<Programme, String> event) {
        Programme program = tabview.getSelectionModel().getSelectedItem();
        program.setNom(event.getNewValue());
        psm.modifier(program);
    }

    @FXML
    private void OnEditType(TableColumn.CellEditEvent<Programme, ComboBox<String>> event) {
        System.out.println("1");
        Programme programme = event.getTableView().getItems().get(event.getTablePosition().getRow());
        // Update the type value of the selected Programme
        programme.setType(event.getNewValue().getValue());
        // Update the Programme in the database
        psm.modifier(programme);
    }

    @FXML
    private void onEditDuree(TableColumn.CellEditEvent<Programme, Integer> event) {
        Programme program = tabview.getSelectionModel().getSelectedItem();
        program.setDure(event.getNewValue());
        psm.modifier(program);
    }

    @FXML
    private void onEditMedia(TableColumn.CellEditEvent<Programme, String> event) {
        Programme program = tabview.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File f = fileChooser.showOpenDialog(new Stage());
        program.setMedia(f.getAbsoluteFile().toURI().toString());
        psm.modifier(program);
    }

}
