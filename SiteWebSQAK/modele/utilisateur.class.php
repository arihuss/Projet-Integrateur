<?php

class Utilisateur implements JsonSerializable {
    private int $id_utilisateur;
    private ?string $imgUtilisateur;
    private string $prenom;
    private string $nom;
    private string $courriel;
    private string $num_tel;
    private string $bio;
    private string $mot_de_passe;
    
    // Constructeur
    public function __construct(
        int $id_utilisateur,
        ?string $imgUtilisateur,
        string $prenom,
        string $nom,
        string $courriel,
        string $num_tel,
        string $bio,
        string $mot_de_passe
    ) {
        $this->id_utilisateur = $id_utilisateur;
        $this->imgUtilisateur= $imgUtilisateur;
        $this->prenom = $prenom;
        $this->nom = $nom;
        $this->courriel = $courriel;
        $this->num_tel = $num_tel;
        $this->bio = $bio;
        $this->mot_de_passe = $mot_de_passe;
    }
    
    // Getters
    public function getIdUtilisateur(): int
    {
        return $this->id_utilisateur;
    }

    public function getImgUtilisateur(): ?string{
        return $this->imgUtilisateur;
     }
    
    public function getPrenom(): string
    {
        return $this->prenom;
    }
    
    public function getNom(): string
    {
        return $this->nom;
    }
    
    public function getCourriel(): string
    {
        return $this->courriel;
    }
    
    public function getNumTel(): string
    {
        return $this->num_tel;
    }
    
    public function getBio(): string
    {
        return $this->bio;
    }
    
    public function getMotDePasse(): string
    {
        return $this->mot_de_passe;
    }
    
    // Setters
    public function setIdUtilisateur(int $id_utilisateur): void
    {
        $this->id_utilisateur = $id_utilisateur;
    }

    public function setImgUtilisateur(string $imgUtilisateur):void{
        $this->imgUtilisateur=$imgUtilisateur;
     }
    
    public function setPrenom(string $prenom): void
    {
        $this->prenom = $prenom;
    }
    
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }
    
    public function setCourriel(string $courriel): void
    {
        $this->courriel = $courriel;
    }
    
    public function setNumTel(string $num_tel): void
    {
        $this->num_tel = $num_tel;
    }
    
    public function setBio(string $bio): void
    {
        $this->bio = $bio;
    }
    
    public function setMotDePasse(string $mot_de_passe): void
    {
        $this->mot_de_passe = $mot_de_passe;
    }
    
    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id_utilisateur' => $this->id_utilisateur,
            'img_utilisateur' => $this->imgUtilisateur,
            'prenom' => $this->prenom,
            'nom' => $this->nom,
            'courriel' => $this->courriel,
            'num_tel' => $this->num_tel,
            'bio' => $this->bio,
            'mot_de_passe' => $this->mot_de_passe,
        ];
    }
}