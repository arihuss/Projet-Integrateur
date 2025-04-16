package com.sarah.applicationsqak.modele;

public class Inscription {
    private int id;
    private int id_evenement;
    private String role;  // choix: 'visiteur', 'benevole', 'appliquant'
    private String date_inscription;
    private String date_annulation;

    // Constructeur
    public Inscription() {

    }

    // Accesseurs et mutateurs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getDate_annulation() {
        return date_annulation;
    }

    public void setDate_annulation(String date_annulation) {
        this.date_annulation = date_annulation;
    }
}
