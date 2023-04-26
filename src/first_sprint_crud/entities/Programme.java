/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class Programme {
     private int id;
     private String nom;
     private String type;
     private String media;
     private int dure;
     private int likes;

    public Programme(int id) {
        this.id = id;
    }

    public Programme(Programme p) {
        this.id = p.id;
        this.nom = p.nom;
        this.type = p.type;
        this.media = p.media;
        this.dure = p.dure;
        this.likes = p.likes;
    }
     
     

    public Programme(int id, String nom, String type, String media, int dure, int likes) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.media = media;
        this.dure = dure;
        this.likes = likes;
    }
    
       public Programme(String nom, String type, String media, int dure, int likes) {
        this.nom = nom;
        this.type = type;
        this.media = media;
        this.dure = dure;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getDure() {
        return dure;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", media=" + media + ", dure=" + dure + ", likes=" + likes + '}';
    }
       
     
     
     
}
