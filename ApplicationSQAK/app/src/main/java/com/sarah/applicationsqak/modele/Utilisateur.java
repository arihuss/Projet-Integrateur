package com.sarah.applicationsqak.modele;

public class Utilisateur {
    private long id;
    private String prenom;
    private String nom;
    private String courriel;
    private String numTel;
    private String bio;
    private String motDePasse;
    private String imageUrl;

    // Constructeur complet
    public Utilisateur(long id, String prenom, String nom, String courriel, String numTel, String bio, String motDePasse, String imageUrl) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.numTel = numTel;
        this.bio = bio;
        this.motDePasse = motDePasse;
        this.imageUrl = imageUrl;
    }

    // Constructeur sans ID (pour l'insertion)
    public Utilisateur(String prenom, String nom, String courriel, String numTel, String motDePasse) {
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.numTel = numTel;
        this.bio = ""; // par défaut vide
        this.motDePasse = motDePasse;
        this.imageUrl = ""; // par défaut vide
    }

    // Getters & Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCourriel() { return courriel; }
    public void setCourriel(String courriel) { this.courriel = courriel; }

    public String getNumTel() { return numTel; }
    public void setNumTel(String numTel) { this.numTel = numTel; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

