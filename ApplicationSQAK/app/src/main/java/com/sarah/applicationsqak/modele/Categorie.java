package com.sarah.applicationsqak.modele;

public enum Categorie {
    ENVIRONNEMENT("Environnement"),
    COMMUNAUTAIRE("Communautaire"),
    CULTUREL("Culturel"),
    SANTÉ("Santé et bien-être"),
    EDUCATION("Éducation et mentorat"),
    SPORTS("Sports et loisirs");

    private final String label;

    Categorie(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }

}

