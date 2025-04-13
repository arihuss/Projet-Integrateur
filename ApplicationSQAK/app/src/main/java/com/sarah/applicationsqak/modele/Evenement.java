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
    private int etatBenevole;
    private Categorie categorie;
    private String description;
    private String etat;
    private int nbInscriptions;
    private int nbBenevolesAcceptes;
    private int completBenevole;
    private int completVisiteur;
    private String imageUrl;  // permet d'utiliser une image de l'internet


    // Constructeur vide
    public Evenement() {

    }
    // Constructeur par parametres
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public int getId_statistique() {
        return id_statistique;
    }

    public void setId_statistique(int id_statistique) {
        this.id_statistique = id_statistique;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbBenevolesMax() {
        return nbBenevolesMax;
    }

    public void setNbBenevolesMax(int nbBenevolesMax) {
        this.nbBenevolesMax = nbBenevolesMax;
    }

    public int getNbParticipantsMax() {
        return nbParticipantsMax;
    }

    public void setNbParticipantsMax(int nbParticipantsMax) {
        this.nbParticipantsMax = nbParticipantsMax;
    }

    public int getEtatBenevole() {
        return etatBenevole;
    }

    public void setEtatBenevole(int etatBenevole) {
        this.etatBenevole = etatBenevole;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbInscriptions() {
        return nbInscriptions;
    }

    public void setNbInscriptions(int nbInscriptions) {
        this.nbInscriptions = nbInscriptions;
    }

    public int getNbBenevolesAcceptes() {
        return nbBenevolesAcceptes;
    }

    public void setNbBenevolesAcceptes(int nbBenevolesAcceptes) {
        this.nbBenevolesAcceptes = nbBenevolesAcceptes;
    }

    public int getCompletBenevole() {
        return completBenevole;
    }

    public void setCompletBenevole(int completBenevole) {
        this.completBenevole = completBenevole;
    }

    public int getCompletVisiteur() {
        return completVisiteur;
    }

    public void setCompletVisiteur(int completVisiteur) {
        this.completVisiteur = completVisiteur;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
