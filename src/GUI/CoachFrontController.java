/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Coach;
import first_sprint_crud.services.CoachService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class CoachFrontController implements Initializable {

    CoachService psm = new CoachService();
    @FXML
    private ScrollPane panev;
    @FXML
    private TextField searchb;
    private Timeline searchTimeline;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
   
    show("");

   
}   

    public void show(String search)
    {
         // Retrieve a list of coaches from the service
        
        List<Coach> coaches ;
        if(search.length() == 0)
        coaches= psm.recuperer();
        else
        {
            coaches = psm.recupererByNomPrenom(search);
        }

    // Create a VBox to hold the rows
    VBox vbox = new VBox();

    // Iterate through the coaches list two at a time
    for (int i = 0; i < coaches.size(); i += 2) {
        // Create an HBox to hold the coaches
        HBox hbox = new HBox();
        hbox.setSpacing(90);
        
        // Create an ImageView with the static coach image and add it to the HBox
        for (int j = 0; j < 2 && (i + j) < coaches.size(); j++) {
            VBox vboxD = new VBox();
             vboxD.setSpacing(25);
            Image image = new Image("/assets/coach_logo.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(110);
            imageView.setFitWidth(110);

            hbox.getChildren().add(imageView);

            // Create a Label with the coach's name and surname and add it to the HBox
            Label nameLabel = new Label("nom : "+coaches.get(i + j).getNom());
            Label prenomlabel = new Label("prenom : "+coaches.get(i + j).getPrenom());
            vboxD.getChildren().add(prenomlabel);
            vboxD.getChildren().add(nameLabel);
            hbox.getChildren().add(vboxD);
        }

        // Add the HBox to the VBox
        vbox.getChildren().add(hbox);
    }
     // Set the VBox as the content of the scroll pane
    panev.setContent(vbox);
    }

  @FXML
private void onSearch(KeyEvent event) {
    if (searchTimeline != null) {
        searchTimeline.stop();
    }

    searchTimeline = new Timeline(new KeyFrame(Duration.millis(300), actionEvent -> {
        String searchText = searchb.getText();
        show(searchText);
    }));

    searchTimeline.play();
}
    
}
