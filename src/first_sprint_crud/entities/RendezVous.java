/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author ksaay
 */
public class RendezVous {
    private int id;
    private String nom;
    private String prenom;
    private Coach coach;
    private String contact;

    public RendezVous(int id, String nom, String prenom, Coach coach, String contact) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.coach = coach;
        this.contact = contact;
    }

    public RendezVous(String nom, String prenom, Coach coach, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.coach = coach;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "nom=" + nom + ", prenom=" + prenom + ", coach=" + coach + ", contact=" + contact + '}';
    }
    
}
