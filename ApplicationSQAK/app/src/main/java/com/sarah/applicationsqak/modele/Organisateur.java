package com.sarah.applicationsqak.modele;

public class Organisateur {

    private long idOrg;
    private String prenomPOrg;
    private String nomPOrg;
    private String courrielOrg;
    private String bioOrg;
    private String nomOrg;
    private String motDePasseOrg;
    private int nbEvents;
    private String imageUrlOrg;

    public Organisateur(long idOrg, String prenomPOrg, String nomPOrg, String courrielOrg, String bioOrg, String nomOrg, String motDePasseOrg, int nbEvents, String imageUrlOrg) {
        this.idOrg = idOrg;
        this.prenomPOrg = prenomPOrg;
        this.nomPOrg = nomPOrg;
        this.courrielOrg = courrielOrg;
        this.bioOrg = bioOrg;
        this.nomOrg = nomOrg;
        this.motDePasseOrg = motDePasseOrg;
        this.nbEvents = nbEvents;
        this.imageUrlOrg = imageUrlOrg;
    }

    public long getIdOrg() { return idOrg; }
    public void setIdOrg(long idOrg) { this.idOrg = idOrg; }

    public String getPrenomPOrg() { return prenomPOrg; }
    public void setPrenomPOrg(String prenomPOrg) { this.prenomPOrg = prenomPOrg; }

    public String getNomPOrg() { return nomPOrg; }
    public void setNomPOrg(String nomPOrg) { this.nomPOrg = nomPOrg; }

    public String getCourrielOrg() { return courrielOrg; }
    public void setCourrielOrg(String courrielOrg) {this.courrielOrg = courrielOrg;}

    public String getBioOrg() { return bioOrg; }
    public void setBioOrg(String bioOrg) { this.bioOrg = bioOrg; }

    public String getNomOrg() { return nomOrg; }
    public void setNomOrg(String nomOrg) { this.nomOrg = nomOrg; }

    public String getMotDePasseOrg() { return motDePasseOrg; }

    public void setMotDePasseOrg(String motDePasseOrg) { this.motDePasseOrg = motDePasseOrg; }

    public int getNbEvents() { return nbEvents; }

    public void setNbEvents(int nbEvents) { this.nbEvents = nbEvents; }

    public String getImageUrlOrg() { return imageUrlOrg; }
    public void setImageUrlOrg(String imageUrlOrg) { this.imageUrlOrg = imageUrlOrg; }
}
