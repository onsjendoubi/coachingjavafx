/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;       

import first_sprint_crud.entities.Coach;


import first_sprint_crud.util.MyDB;



import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ouss
 */
public class CoachService implements IService<Coach> {
    
     Connection cnx;

    public CoachService() {
        cnx = MyDB.getInstance().getConnection();
    }
    

   @Override
    public void ajouter(Coach c) {


        try {
   String req = "insert into coach(nom,prenom)"
   + "values( '" + c.getNom()+ "' ,   '" + c.getPrenom()+  "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Coach Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    
      @Override
    public void modifier(Coach c) {
        
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE coach SET nom = ?, prenom = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, c.getNom());
        ps.setString(2, c.getPrenom());
        ps.setInt(3, c.getId());
        
          ps.executeUpdate();
            System.out.println("Coach Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }

        
    }
    
    @Override
    public void supprimer(int id) {
 try {
            String req = "delete from coach  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Coach deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public List<Coach> recuperer() {
       List<Coach> coachs = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM coach";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

            Coach coach = new Coach(id, nom, prenom);
            coachs.add(coach);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des coachs : " + ex.getMessage());
    }

    return coachs;
        
    }
    
    public Coach recupererById(int idF) {
      Coach  coach = null;
    try {
        
    
      
        String sql = "SELECT * FROM coach where id=" + " '"+ idF + "'  ";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

             coach = new Coach(id, nom, prenom);
   
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de coach : " + ex.getMessage());
    }

    return coach;
    
    }
    
    
    public List<Coach> recupererByNomPrenom(String search) {
     List<Coach> coachs = new ArrayList<>();
     String sql = "SELECT * FROM coach where nom LIKE '" + "%" +search+ "%" + "'  or prenom LIKE '" + "%" +search+ "%" + "'  ";
    try {

        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

             Coach coach = new Coach(id, nom, prenom);
             coachs.add(coach);
   
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de coach : " + ex.getMessage());
    }

    return coachs;
    
    }
    
}
