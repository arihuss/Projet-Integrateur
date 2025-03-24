<?php

class Statistique implements JsonSerializable
{
    private int $id_statistique;
    private int $nb_visiteurs;
    private int $nb_benevoles;
    private int $nb_likes;
    private int $nb_vues;
    private int $nb_partages;

    // Constructeur
    public function __construct(
        int $id_statistique,
        int $nb_visiteurs,
        int $nb_benevoles,
        int $nb_likes,
        int $nb_vues,
        int $nb_partages
    ) {
        $this->id_statistique = $id_statistique;
        $this->nb_visiteurs = $nb_visiteurs;
        $this->nb_benevoles = $nb_benevoles;
        $this->nb_likes = $nb_likes;
        $this->nb_vues = $nb_vues;
        $this->nb_partages = $nb_partages;
    }

    // Getters
    public function getIdStatistique(): int
    {
        return $this->id_statistique;
    }

    public function getNbVisiteurs(): int
    {
        return $this->nb_visiteurs;
    }

    public function getNbBenevoles(): int
    {
        return $this->nb_benevoles;
    }

    public function getNbLikes(): int
    {
        return $this->nb_likes;
    }

    public function getNbVues(): int
    {
        return $this->nb_vues;
    }

    public function getNbPartages(): int
    {
        return $this->nb_partages;
    }

    // Setters
    public function setIdStatistique(int $id_statistique): void
    {
        $this->id_statistique = $id_statistique;
    }

    public function setNbVisiteurs(int $nb_visiteurs): void
    {
        $this->nb_visiteurs = $nb_visiteurs;
    }

    public function setNbBenevoles(int $nb_benevoles): void
    {
        $this->nb_benevoles = $nb_benevoles;
    }

    public function setNbLikes(int $nb_likes): void
    {
        $this->nb_likes = $nb_likes;
    }

    public function setNbVues(int $nb_vues): void
    {
        $this->nb_vues = $nb_vues;
    }

    public function setNbPartages(int $nb_partages): void
    {
        $this->nb_partages = $nb_partages;
    }

    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id_statistique' => $this->id_statistique,
            'nb_visiteurs' => $this->nb_visiteurs,
            'nb_benevoles' => $this->nb_benevoles,
            'nb_likes' => $this->nb_likes,
            'nb_vues' => $this->nb_vues,
            'nb_partages' => $this->nb_partages,
        ];
    }
}
