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
    private Categorie categorie;
    private String description;
    private String etat;
    private int nbInscriptions;
    private int nbBenevolesAcceptes;
    private int completBenevole;
    private int completVisiteur;
    private String imageUrl;  // permet d'utiliser une image de l'internet



    // Constructeur
    public Evenement(int id_organisateur, String nomEvent, String imageUrl, String lieu, Categorie categorie) {
        // Valeurs fournies
        this.id_organisateur = id_organisateur;
        this.nomEvent = nomEvent;
        this.imageUrl = imageUrl;
        this.categorie = categorie;

        // Valeurs par défaut
        this.description = "Voici la description";
        this.dateDebut = "Date début";
        this.dateFin = "Date fin";
        this.lieu = "289 Rue Saint-Charles Ouest";
        this.etat = "disponible";
    }

    // Accesseurs et mutateurs


}
