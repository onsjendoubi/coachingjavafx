/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Article;



import first_sprint_crud.services.ArticleService;
import first_sprint_crud.util.MyDB;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ouss
 */
public class MainArticleController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private ImageView imgv;
    @FXML
    private TableColumn<Article, String> titreT;
    @FXML
    private TableColumn<Article, String> sujetT;
    @FXML
    private TableColumn<Article, String> imageT;
    @FXML
    private TextField titre;
    @FXML
    private TextField sujet;
     ArticleService psm = new ArticleService();
    @FXML
    private TableColumn<Article, Integer> idT;
    @FXML
    private TableView<Article> tabview;
    @FXML
    private Label imgurl;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tabview.setEditable(true);
        // TODO
         titreT.setCellFactory(TextFieldTableCell.forTableColumn());
          sujetT.setCellFactory(TextFieldTableCell.forTableColumn());
        show("");
    }    

    public void show(String s)
    {
       
          
imageT.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Article, String> cell = new TableCell<Article, String>() {
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
        imageT.setCellValueFactory(new PropertyValueFactory<Article, String>("image"));
      

      idT.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
       titreT.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
       sujetT.setCellValueFactory(new PropertyValueFactory<Article,String>("sujet_art"));
        //tabB.setItems(list);
        ObservableList<Article> items;
        if(search.getText().length() == 0)
        items= FXCollections.observableArrayList(psm.recuperer());
        else
        {
          items= FXCollections.observableArrayList(psm.recupererBySujetTitre(s));
        }
         
        tabview.setItems(items);

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
    private void save(ActionEvent event) throws IOException {
         if (titre.getText().isEmpty() || imgurl.getText().isEmpty() || sujet.getText().isEmpty()) {
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

            Article a = new Article(sujet.getText(),titre.getText(),nameImage);

            psm.ajouter(a);
            
            sujet.setText("");
            titre.setText("");
            imgurl.setText("");
            imgv.setImage(null);
            
           
            show("");
        }
    }

    @FXML
    private void onremove(ActionEvent event) {
        Article article = tabview.getSelectionModel().getSelectedItem();
       psm.supprimer(article.getId());
      show("");
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
    private void onChangeTitre(TableColumn.CellEditEvent<Article, String> event) {
        Article article = event.getRowValue();
            article.setTitre(event.getNewValue());
            psm.modifier(article);
    }
    @FXML
    private void onChangeSujet(TableColumn.CellEditEvent<Article, String> event) {
        Article article = event.getRowValue();
            article.setSujet_art(event.getNewValue());
            psm.modifier(article);
    }

   

    @FXML
    private void onsearch(KeyEvent event) {
         String searchText = search.getText();
        show(searchText);
    }

   

    
}
