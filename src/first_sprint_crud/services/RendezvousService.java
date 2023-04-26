/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;
import first_sprint_crud.entities.Coach;
import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import first_sprint_crud.entities.RendezVous;

/**
 *
 * @author ons
 */
public class RendezvousService implements IService<RendezVous> {
     Connection cnx;

    public RendezvousService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public List<RendezVous> recuperer() {
       List<RendezVous> RendezVouss = new ArrayList<>();
    try {
        
    
      
        String sql = "select * from rendez_vous r inner join coach on coach.id = r.coach_id";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt(1);
            int  id_coach = rs.getInt(2);
            String nom = rs.getString(3);
            String prenom = rs.getString(4);
            String contact = rs.getString(5);
            String nom_coach = rs.getString(7);
            String prenom_coach = rs.getString(8);
            Coach c = new Coach(id_coach,nom_coach,prenom_coach);
            RendezVous rendezvous = new RendezVous(id, nom, prenom, c,contact);
            RendezVouss.add(rendezvous);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des Livraisons : " + ex.getMessage());
    }

    return RendezVouss;
        
    }
    
    
    public List<RendezVous> recupererById(int idF) {
       List<RendezVous> RendezVouss = new ArrayList<>();
    try {
        
    
      
        String sql = "select * from rendez_vous  r inner join coach on coach.id = " + " '"+ idF + "' ";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt(1);
            int  id_coach = rs.getInt(2);
            String nom = rs.getString(3);
            String prenom = rs.getString(4);
            String contact = rs.getString(5);
            String nom_coach = rs.getString(7);
            String prenom_coach = rs.getString(8);
            Coach c = new Coach(id_coach,nom_coach,prenom_coach);
            RendezVous rendezvous = new RendezVous(id, nom, prenom, c,contact);
            RendezVouss.add(rendezvous);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des Livraisons : " + ex.getMessage());
    }

    return RendezVouss;
        
    }
    
    
    public void supprimer(int id) {
 try {
            String req = "delete from rendez_vous  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("rendez_vous deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(RendezVous c) {
        
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE RendezVous SET nom = ?, prenom = ?, contact = ?, coach_id = ? WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, c.getNom());
        ps.setString(2, c.getPrenom());
        ps.setString(3, c.getContact());
        ps.setInt(4, c.getCoach().getId());
        ps.setInt(5, c.getId());
        
          ps.executeUpdate();
            System.out.println("rendez_vous Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }

        
    }
    @Override
    public void ajouter(RendezVous c) {


        try {
   String req = "insert into rendez_vous(coach_id,nom,prenom,contact)"
   + "values( '" + c.getCoach().getId()+ "' ,   '" + c.getNom()+  "' ,  '" + c.getPrenom() + "' ,   '" + c.getContact()+ "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("RendezVous Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
}
