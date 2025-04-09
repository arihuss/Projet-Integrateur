<?php

class Organisateur implements JsonSerializable
{
    private ?int $id;
    private ?string $imgOrganisateur;
    private ?string $prenom;
    private ?string $nom;
    private string $courriel;
    private string $biographie;
    private ?string $nomOrganisateur;
    private string $motDePasse;
    private int $nbEvents;
    private ?string $telephone;

    
    //Constructeur

    public function __construct(
        ?int $id,
        ?string $imgOrganisateur,
        ?string $prenom,
        ?string $nom,
        string $courriel,
        string $biographie,
        ?string $nomOrganisateur,
        string $motDePasse,
        int $nbEvents,
        ?string $telephone
    ){
        $this->id = $id;
        $this->imgOrganisateur=$imgOrganisateur;
        $this->prenom = $prenom;
        $this->nom = $nom;
        $this->courriel = $courriel;
        $this->biographie = $biographie;
        $this->nomOrganisateur = $nomOrganisateur;
        $this->motDePasse = $motDePasse;
        $this->nbEvents = $nbEvents;
        $this->telephone=$telephone;
    }

     // Getters
     public function getId(): int
     {
         return $this->id;
     }

     public function getImgOrganisateur():?string{
        return $this->imgOrganisateur;
     }
 
     public function getPrenom(): ?string
     {
         return $this->prenom;
     }
 
     public function getNom(): ?string
     {
         return $this->nom;
     }
 
     public function getCourriel(): string
     {
         return $this->courriel;
     }
 
     public function getBiographie(): string
     {
         return $this->biographie;
     }
 
     public function getNomOrganisateur(): ?string
     {
         return $this->nomOrganisateur;
     }
 
     public function getMotDePasse(): string
     {
         return $this->motDePasse;
     }
 
     public function getNbEvents(): int
     {
         return $this->nbEvents;
     }
 
     // Setters
     public function setId(int $id): void
     {
         $this->id = $id;
     }

     public function setImgOrganisateur(string $imgOrganisateur):void{
        $this->imgOrganisateur=$imgOrganisateur;
     }
 
     public function setPrenom(?string $prenom): void
     {
         $this->prenom = $prenom;
     }
 
     public function setNom(?string $nom): void
     {
         $this->nom = $nom;
     }
 
     public function setCourriel(string $courriel): void
     {
         $this->courriel = $courriel;
     }
 
     public function setBiographie(string $biographie): void
     {
         $this->biographie = $biographie;
     }
 
     public function setNomOrganisateur(?string $nomOrganisateur): void
     {
         $this->nomOrganisateur = $nomOrganisateur;
     }
 
     public function setMotDePasse(string $motDePasse): void
     {
         $this->motDePasse = $motDePasse;
     }
 
     public function setNbEvents(int $nbEvents): void
     {
         $this->nbEvents = $nbEvents;
     }

     public function __toString(): string {
        return "Organisateur ID: {$this->id}, Nom: {$this->nom}, Prénom: {$this->prenom}";
    }
    
    public function getTelephone(): ?string {
        return $this->telephone;
    }
    
    public function setTelephone(?string $telephone): void {
        $this->telephone = $telephone;
    }
 
     // Implémentation de JsonSerializable
     public function jsonSerialize(): array
     {
         return [
             'id' => $this->id,
             'prenom' => $this->prenom,
             'nom' => $this->nom,
             'courriel' => $this->courriel,
             'biographie' => $this->biographie,
             'nomOrganisateur' => $this->nomOrganisateur,
             'nbEvents' => $this->nbEvents,
             'telephone'=> $this->telephone
         ];
     }
}