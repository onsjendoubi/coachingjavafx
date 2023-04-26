/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;

import first_sprint_crud.entities.FavoriteProgrammes;
import first_sprint_crud.entities.Programme;
import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ons
 */
public class FavoriteProgrammesService {

    Connection cnx;

    public FavoriteProgrammesService() {
        cnx = MyDB.getInstance().getConnection();
    }

    public void ajouter(FavoriteProgrammes f) {
        try {
            String req = "insert into favorite_programmes(programmes_id)"
                    + "values( '" + f.getProg().getId() + "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Favorite Programme Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimer(int id) {
        try {
            String req = "delete from favorite_programmes  where id=" + " '" + id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();

            System.out.println("Favorite Programme deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Programme> recupererFavorite() {
        List<Programme> programmes = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.nom, p.type, p.media, p.dure, p.likes  "
                    + "FROM programme p "
                    + "JOIN favorite_programmes f ON p.id = f.programmes_id";

            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String type = rs.getString("type");
                String media = rs.getString("media");
                int dure = rs.getInt("dure");
                int likes = rs.getInt("likes");

                Programme programme = new Programme(id, nom, type, media, dure, likes);
                programmes.add(programme);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
        }
        return programmes;
    }

    public int recupererFavoritebyID(int idp) {

        try {
            String sql = "Select  * from favorite_programmes where programmes_id=" + " '" + idp + "' ";

            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt(2);

                return id;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
        }
        return 0;
    }
     public int recupererFavoritebyProgid(int idp) {

        try {
            String sql = "Select  * from favorite_programmes where programmes_id=" + " '" + idp + "' ";

            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt(1);

                return id;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
        }
        return 0;
    }

}
