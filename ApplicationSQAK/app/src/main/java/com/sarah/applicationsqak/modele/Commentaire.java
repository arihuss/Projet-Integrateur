package com.sarah.applicationsqak.modele;

public class Commentaire {
    private long id;
    private long id_utilisateur;
    private long id_evenement;
    private String message;
    private String date_envoi;

    public Commentaire() {

    }

    // Accesseurs et mutateurs

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public long getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(long id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_envoi() {
        return date_envoi;
    }

    public void setDate_envoi(String date_envoi) {
        this.date_envoi = date_envoi;
    }
}
