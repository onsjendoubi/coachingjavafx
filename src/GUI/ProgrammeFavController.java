/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.FavoriteProgrammes;
import first_sprint_crud.entities.Programme;
import first_sprint_crud.services.FavoriteProgrammesService;
import first_sprint_crud.services.ProgrammeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class ProgrammeFavController implements Initializable {

    @FXML
    private ScrollPane panev;

    ProgrammeService psm = new ProgrammeService();
     FavoriteProgrammesService psf = new FavoriteProgrammesService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    
    public void show() {
        // Retrieve a list of coaches from the service

        final List<Programme> programs;

        programs = psm.recuperer();
        

        // Create a VBox to hold the rows
        VBox vbox = new VBox();

        // Iterate through the coaches list two at a time
        for (int i = 0; i < programs.size(); i += 2) {

            // Create an HBox to hold the coaches
            HBox hbox = new HBox();
            hbox.setSpacing(30);

            // Create an ImageView with the static coach image and add it to the HBox
            for (int j = 0; j < 2 && (i + j) < programs.size(); j++) {
                int index = i + j;
            if (psf.recupererFavoritebyID(programs.get(index).getId()) == 0) {
                   continue;

                }
                VBox vboxD = new VBox();
                vboxD.setSpacing(25);
                HBox hboxb = new HBox();
                hboxb.setSpacing(20);
                Image image = new Image(programs.get(i + j).getMedia());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(110);
                imageView.setFitWidth(110);

                hbox.getChildren().add(imageView);

                // Create a Label with the coach's name and surname and add it to the HBox
                Label prog_name = new Label("program name : " + programs.get(i + j).getNom());
                Label prog_type = new Label("type : " + programs.get(i + j).getType());
                Label prog_likes = new Label("likes : " + programs.get(i + j).getLikes());
                
                Button fav = new Button("Remove From favorit");
                 fav.setStyle("-fx-background-color: #ea6368;");

                    fav.setOnAction(e -> {

                        psf.supprimer(psf.recupererFavoritebyProgid(programs.get(index).getId()));
                        show();
                        Notifications.create()
                                .title("Success")
                                .text("program has been removed from your favorites!")
                                .show();
                    
                    // add your code to handle the like button click here
                });
                vboxD.getChildren().add(prog_name);
                vboxD.getChildren().add(prog_type);
                vboxD.getChildren().add(prog_likes);
                vboxD.getChildren().add(hboxb);
                 
                hbox.getChildren().add(vboxD);
                hbox.getChildren().add(fav);

            }

            // Add the HBox to the VBox
            vbox.getChildren().add(hbox);
        }
        // Set the VBox as the content of the scroll pane
        panev.setContent(vbox);
    }
    
}
