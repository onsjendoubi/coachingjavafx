/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Coach;
import first_sprint_crud.entities.RendezVous;


import first_sprint_crud.services.RendezvousService;
import first_sprint_crud.services.CoachService;
import java.io.IOException;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class MainRendezVousController implements Initializable {

    @FXML
    private VBox ChosenProdCard;
    @FXML
    private Button Add;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenomm;
    @FXML
    private ComboBox<String> coach;
    @FXML
    private TextField contact;
    private Stage stage;
    private Scene scene;
    
RendezvousService psm = new RendezvousService();
CoachService psc = new CoachService();



 ObservableMap<String, Coach> coachMap = FXCollections.observableHashMap();
 
    @FXML
    private ComboBox<String> cbcoach;
    @FXML
    private Button refresh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (psm.recuperer().size()==0)
        {
            prodvide.setText("Rendez Vous vide");
             
        }
            else
         prodvide.setText("Rendez Vous:");
         
      
for (Coach c : psc.recuperer()) {
    String key = c.getNom() + " " + c.getPrenom();
    coachMap.put(key, c);
}
coach.setItems(FXCollections.observableArrayList(coachMap.keySet()));
cbcoach.setItems(FXCollections.observableArrayList(coachMap.keySet()));

    

ObservableList<RendezVous> items = FXCollections.observableArrayList(psm.recuperer());
            
        show(items);
    }   
    
    
    public void show(ObservableList<RendezVous> items2 )
    {
        int column=0;
        int row=1;
        int x=0;
        
        
                      
                  FXMLLoader fxmlloader2 = new  FXMLLoader();
                fxmlloader2.setLocation(getClass().getResource("MainRendezVousItem.fxml"));
        try {
            AnchorPane pane2 = fxmlloader2.load();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                GUI.MainRendezVousItemController items3 = fxmlloader2.getController();
                items3.refresh();
                grid.getChildren().removeAll(grid.getChildren());
       
         try {
        for(RendezVous pan : items2)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("MainRendezVousItem.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                MainRendezVousItemController items = fxmlloader.getController();
                items.setData(pan);
                
                if(column == 1 )
                {
                    column = 0;
                    row++;
                }
                
                grid.add(pane,column++,row);
                
                //width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                
                
                GridPane.setMargin(pane, new Insets(10));
    
        }
        } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }



    @FXML
    private void AddToCart(ActionEvent event) {
        
        if (nom.getText().isEmpty() || prenomm.getText().isEmpty() || coach.getSelectionModel().getSelectedItem() == null || contact.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else{
            
            String selectedCoach = coach.getSelectionModel().getSelectedItem();
            Coach c = coachMap.get(selectedCoach);
            
        
            RendezVous r = new RendezVous(nom.getText(),prenomm.getText(),c,contact.getText());
            psm.ajouter(r);
            nom.setText("");
            prenomm.setText("");
            contact.setText("");
        ObservableList<RendezVous> items = FXCollections.observableArrayList(psm.recuperer());    
            show(items);
            
        }
        
    }

    @FXML
    private void coachcomboaction(ActionEvent event) {
    }

    private void ret(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    
    

    @FXML
    private void selectcoach(ActionEvent event) {
    String selectedCoach = cbcoach.getSelectionModel().getSelectedItem();
    if (selectedCoach == null) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText(" nothing is selected, so do nothing");

            alert.showAndWait(); 
    }
    
    Coach c = coachMap.get(selectedCoach);
  
    ArrayList<RendezVous> list = new ArrayList();
    
       ObservableList<RendezVous> items2 = FXCollections.observableArrayList(psm.recuperer()); 

   for (RendezVous rv:  items2){
       if(c.getId() == rv.getCoach().getId())
       {
           list.add(rv);
       }
   }
   
    ObservableList<RendezVous> items3 = FXCollections.observableArrayList(list); 
    
    System.out.println(items3);
    
    show(items3);
}


    @FXML
    private void refreshaction(ActionEvent event) {
      ObservableList<RendezVous> items = FXCollections.observableArrayList(psm.recuperer());
           show(items);
    }
    
}
