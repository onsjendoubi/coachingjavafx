/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Coach;
import first_sprint_crud.services.CoachService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class MainCoachController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private TableView<Coach> tabview;
    @FXML
    private TableColumn<Coach, String> nomT;
    @FXML
    private TableColumn<Coach, String> prenomT;
    @FXML
    private TableColumn<Coach, Integer> idT;

    CoachService psm = new CoachService();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tabview.setEditable(true);

        // Set cell factory for nomT
        nomT.setCellFactory(TextFieldTableCell.forTableColumn());
       
        // Set cell factory for prenomT
        prenomT.setCellFactory(TextFieldTableCell.forTableColumn());
      

        show();
    }    

    @FXML
    private void save(ActionEvent event) {
        if(nom.getText().isEmpty()  || prenom.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
             
            
             Coach b = new Coach(nom.getText(),prenom.getText());

        psm.ajouter(b);
        show();
        }
    }

    @FXML
    private void onremove(ActionEvent event) {

        Coach b = tabview.getSelectionModel().getSelectedItem();
       psm.supprimer(b.getId());
      show();
    }

    @FXML
    private void returnto(MouseEvent event) {
    }

    
    public void show()
    {
       
          

      

      idT.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
       nomT.setCellValueFactory(new PropertyValueFactory<Coach,String>("nom"));
       prenomT.setCellValueFactory(new PropertyValueFactory<Coach,String>("prenom"));
        //tabB.setItems(list);
        ObservableList<Coach> items = FXCollections.observableArrayList(psm.recuperer());
        tabview.setItems(items);

    }


    @FXML
    private void onEditNom(TableColumn.CellEditEvent<Coach, String> event) {
        Coach coach = event.getRowValue();
            coach.setNom(event.getNewValue());
            psm.modifier(coach);
    }

    @FXML
    private void onEditPrenom(TableColumn.CellEditEvent<Coach, String> event) {
          Coach coach = event.getRowValue();
            coach.setPrenom(event.getNewValue());
            psm.modifier(coach);
    }

   
}
