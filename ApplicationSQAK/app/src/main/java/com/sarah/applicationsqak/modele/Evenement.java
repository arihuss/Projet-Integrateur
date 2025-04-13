package com.sarah.applicationsqak.modele;

import java.io.Serializable;

public class Evenement implements Serializable {
    private int id;
    private int id_organisateur;
    private int id_statistique;
    private String nomEvent;
    private String lieu;
    private String dateDebut;
    private String dateFin;
    private int nbBenevolesMax;
    private int nbParticipantsMax;
    private boolean etatBenevole;
    private String categorie;
    private String description;
    private String etat;
    private int nbInscriptions;
    private int nbBenevolesAcceptes;
    private int completBenevole;
    private int completVisiteur;
    private String imageUrl;  // permet d'utiliser une image de l'internet
   


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
