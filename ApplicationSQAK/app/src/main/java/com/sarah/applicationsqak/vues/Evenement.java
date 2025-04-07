package com.sarah.applicationsqak.vues;

import java.io.Serializable;

public class Evenement implements Serializable {
    private String nom;
    private String organisateur;
    private int nbLikes;
    private String description;
    private String date;
    private String lieu;
    private String imageUrl;  // permet d'utiliser une image de l'internet
    private String etat;  // [disponible, termine]


    // Constructeur
    public Evenement(String nom, String organisateur, String imageUrl) {
        this.nom = nom;
        this.organisateur = organisateur;
        this.imageUrl = imageUrl;
    }

    // Accesseurs et mutateurs

    public String getNom() {
        return nom;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEtat() {
        return etat;
    }
}
