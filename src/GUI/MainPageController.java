/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Programme;
import first_sprint_crud.entities.Reclamation;
import first_sprint_crud.entities.Reponse;
import first_sprint_crud.services.ProgrammeService;
import first_sprint_crud.services.ReclamationService;
import first_sprint_crud.services.ReponseService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafxpi.JavafxPi;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class MainPageController implements Initializable {

    @FXML
    private Button coach;
    @FXML
    private Button prog;
    @FXML
    private Pane view;
    @FXML
    private Button produit;
    @FXML
    private Button livreur;
    
      private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button article;
    @FXML
    private Button reclamationback;

     ProgrammeService psp = new ProgrammeService();
     ReclamationService psr = new ReclamationService();
     ReponseService pse = new ReponseService();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show_home();
       
        
    }

    @FXML
    private void coachview(ActionEvent event) throws FileNotFoundException {
        URL fileUrl = JavafxPi.class.getResource("/GUI/MainCoach.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
}

@FXML
private void progview(ActionEvent event) {
     URL fileUrl = JavafxPi.class.getResource("/GUI/MainProgramme.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
    }

    @FXML
    private void produitview(ActionEvent event) {
        
             URL fileUrl = JavafxPi.class.getResource("/GUI/MainProduit.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
    }

    @FXML
    private void livreurview(ActionEvent event) {
               URL fileUrl = JavafxPi.class.getResource("/GUI/MainLivreur.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
        
    }

    private void rendezvousview(ActionEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("MainRendezVous.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    

    @FXML
    private void articleview(ActionEvent event) throws IOException {
         URL fileUrl = JavafxPi.class.getResource("/GUI/MainArticle.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
    }

    private void articlevf(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArticleFront.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    private void reclamationview(ActionEvent event)throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("MainReclamation.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void reclamationbackview(ActionEvent event) {
      URL fileUrl = JavafxPi.class.getResource("/GUI/MainRepose.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
          catch(Exception e ){
System.out.println("no file found");
    }
    }

    @FXML
    private void frontv(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void viewhome(MouseEvent event) {
      show_home();
    }
    public void show_home()
    {
        view.getChildren().clear();
         List<Programme> programms = psp.recuperer();
         List<Reclamation> reclamations  = psr.recuperer();
         List<Reponse> reponses = pse.recuperer();
         int replied_rec = reponses.size() ;

         int nnreplied_rec =  reclamations.size()  - reponses.size();
         
         
         
        int abdou_count = 0 , yoga_count  = 0, musculation_count = 0, fitness_count = 0;
        
 
        for ( int i = 0 ; i< programms.size() ; i++)
        {
            if (programms.get(i).getType().equals("Musculation"))
                musculation_count++;
            if (programms.get(i).getType().equals("Abdou"))
                abdou_count++;
            if (programms.get(i).getType().equals("Fitness"))
                fitness_count++;
            if (programms.get(i).getType().equals("Yoga"))
                yoga_count++;
            
            
        }
 
        
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        

if (abdou_count > 0) {
    pieChartData.add(new PieChart.Data("Abdou", abdou_count));
}

if (yoga_count > 0) {
    pieChartData.add(new PieChart.Data("Yoga", yoga_count));
}

if (musculation_count > 0) {
    pieChartData.add(new PieChart.Data("Musculation", musculation_count));
}

if (fitness_count > 0) {
    pieChartData.add(new PieChart.Data("Fitness", fitness_count));
}

         
        // Create the chart
        PieChart chart = new PieChart(pieChartData);
        chart.setLegendVisible(true); // Hide the chart legend
        chart.setLabelsVisible(true); // Hide the labels on the chart
        chart.setStyle("-fx-background-color: transparent;");

        

        // Create a VBox layout to hold the chart
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        
        
        ///////////////////////////// Reclamation
        
        
          ObservableList<PieChart.Data> pieChartData_reclamation = FXCollections.observableArrayList();
          if(replied_rec>0)
          {
              pieChartData_reclamation.add(new PieChart.Data("reclamation avec des reponses [ "+ replied_rec + " ] ", replied_rec));
          }
          if(nnreplied_rec>0)
          {
               pieChartData_reclamation.add(new PieChart.Data("reclamation sans reponses  [ "+ nnreplied_rec + " ] ", nnreplied_rec));
          }
           PieChart chart_reclamation = new PieChart(pieChartData_reclamation);
        chart_reclamation.setLegendVisible(true); // Hide the chart legend
        chart_reclamation.setLabelsVisible(true); // Hide the labels on the chart
        chart_reclamation.setStyle("-fx-background-color: transparent;");
        
        layout.getChildren().add(chart);
        layout.getChildren().add(chart_reclamation);
        // Create a Scene and display it
        Scene scenee = new Scene(layout, 1, 1);
        view.getChildren().add(scenee.getRoot());
    }
}
