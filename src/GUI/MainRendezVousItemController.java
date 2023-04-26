/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.RendezVous;


import first_sprint_crud.services.RendezvousService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author ons
 */
public class MainRendezVousItemController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private ImageView delete;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label coach;
    @FXML
    private Label contact;
    int id ;
    @FXML
    private AnchorPane pane1;
    
    RendezvousService psm = new RendezvousService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
      public void  refresh()
     {
         vbox.getChildren().removeAll(vbox.getChildren());
     }
      
      
     public void setData(RendezVous pan)
    {
       
            id = pan.getId();
    
            Image image2;
            
            image2 = new Image("/Images/delete.png");
            delete.setImage(image2);
            
            nom.setText("Nom: "+ pan.getNom());
            prenom.setText("Prenom: "+pan.getPrenom());
            coach.setText("Coach: "+ pan.getCoach().getNom());
            contact.setText("Contact: "+ pan.getContact());
        
        
        
            
        
    }

    @FXML
    private void deleteaction(MouseEvent event) {
            psm.supprimer(id);
         pane1.getChildren().removeAll(pane1.getChildren());
    }
    
}
