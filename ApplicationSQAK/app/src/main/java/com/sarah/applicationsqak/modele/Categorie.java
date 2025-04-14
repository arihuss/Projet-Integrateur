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

    public static String[] getAllLabels() {
        Categorie[] values = Categorie.values();
        String[] labels = new String[values.length];
        for(int i=0; i<values.length; i++) {
            labels[i] = values[i].getLabel();
        }

        return labels;
    }

    public static Categorie fromLabel(String label) {
        for(Categorie c : values()) {
            if(c.label.equalsIgnoreCase(label)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Catégorie inconnue: " + label);
    }

}

