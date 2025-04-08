<?php

class Evenement implements JsonSerializable
{
    private int $id;
    private int $idStats;
    private int $idOrganisateur;
    private string $nom;
    private string $lieu;
    private string $dateDebut;
    private string $dateFin;
    private ?int $nbBenevolesMax;
    private ?int $nbParticipantsMax;
    private ?bool $etatBenevole;
    private ?string $categorie;
    private string $description;
    private string $etat;
    private int $nbInscriptions;
    private bool $completBenevole;
    private bool $completVisiteur;
    private string $imageEvenement; 

    // Constructeur
    public function __construct(
        ?int $id,
        ?int $idStats,
        int $idOrganisateur,
        string $nom,
        string $lieu,
        string $dateDebut,
        string $dateFin,
        ?int $nbBenevolesMax,
        ?int $nbParticipantsMax,
        ?bool $etatBenevole,
        ?string $categorie,
        string $description,
        string $etat,
        int $nbInscriptions,
        bool $completBenevole,
        bool $completVisiteur,
        string $imageEvenement
    ) {
        $this->id = $id;
        $this->idStats = $idStats;
        $this->idOrganisateur = $idOrganisateur;
        $this->nom = $nom;
        $this->lieu = $lieu;
        $this->dateDebut = $dateDebut;
        $this->dateFin = $dateFin;
        $this->nbBenevolesMax = $nbBenevolesMax;
        $this->nbParticipantsMax = $nbParticipantsMax;
        $this->etatBenevole = $etatBenevole;
        $this->categorie = $categorie;
        $this->description = $description;
        $this->etat = $etat;
        $this->nbInscriptions = $nbInscriptions;
        $this->completBenevole = $completBenevole;
        $this->completVisiteur = $completVisiteur;
        $this->imageEvenement= $imageEvenement;
    }

    // Getters
    public function getId(): int { return $this->id; }
    public function getIdStats():int{return $this->idStats;}
    public function getIdOrganisateur():int{return $this->idOrganisateur;}
    public function getNom(): string { return $this->nom; }
    public function getLieu(): string { return $this->lieu; }
    public function getDateDebut(): string { return $this->dateDebut; }
    public function getDateFin(): string { return $this->dateFin; }
    public function getNbBenevolesMax(): ?int { return $this->nbBenevolesMax; }
    public function getNbParticipantsMax(): ?int { return $this->nbParticipantsMax; }
    public function getEtatBenevole(): ?bool { return $this->etatBenevole; }
    public function getCategorie(): ?string { return $this->categorie; }
    public function getDescription(): string { return $this->description; }
    public function getEtat(): string { return $this->etat; }
    public function getNbInscriptions(): int { return $this->nbInscriptions; }
    public function getCompletBenevole(): bool { return $this->completBenevole; }
    public function getCompletVisiteur(): bool { return $this->completVisiteur; }
    public function getImageEvenement(): ?string { return $this->imageEvenement;}
    
    // Setters
    public function setId(int $id): void { $this->id = $id; }
    public function setIdStats(int $idStats):void{$this->idStats=$idStats;}
    public function setIdOrganisateur(int $idOrganisateur):void{$this->idOrganisateur=$idOrganisateur;}
    public function setNom(string $nom): void { $this->nom = $nom; }
    public function setLieu(string $lieu): void { $this->lieu = $lieu; }
    public function setDateDebut(string $dateDebut): void { $this->dateDebut = $dateDebut; }
    public function setDateFin(string $dateFin): void { $this->dateFin = $dateFin; }
    public function setNbBenevolesMax(?int $nbBenevolesMax): void { $this->nbBenevolesMax = $nbBenevolesMax; }
    public function setNbParticipantsMax(?int $nbParticipantsMax): void { $this->nbParticipantsMax = $nbParticipantsMax; }
    public function setEtatBenevole(bool $etatBenevole): void { $this->etatBenevole = $etatBenevole; }
    public function setCategorie(?string $categorie): void { $this->categorie = $categorie; }
    public function setDescription(string $description): void { $this->description = $description; }
    public function setEtat(string $etat): void { $this->etat = $etat; }
    public function setNbInscriptions(int $nbInscriptions): void { $this->nbInscriptions = $nbInscriptions; }
    public function setNbBenevolesAcceptes(int $nbBenevolesAcceptes): void { $this->nbBenevolesAcceptes = $nbBenevolesAcceptes; }
    public function setCompletVisiteur(bool $completVisiteur): void { $this->completVisiteur = $completVisiteur; }
    public function setImageEvenement(?string $image): void { $this->imageEvenement = $image;}

    
    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id' => $this->id,
            'idStats'=>$this->idStats,
            'idOrganisateur'=>$this->idOrganisateur,
            'nom' => $this->nom,
            'lieu' => $this->lieu,
            'dateDebut' => $this->dateDebut,
            'dateFin' => $this->dateFin,
            'nbBenevolesMax' => $this->nbBenevolesMax,
            'nbParticipantsMax' => $this->nbParticipantsMax,
            'etatBenevole' => $this->etatBenevole,
            'categorie' => $this->categorie,
            'description' => $this->description,
            'etat' => $this->etat,
            'nbInscriptions' => $this->nbInscriptions,
            'completBenevole' => $this->completBenevole,
            'completVisiteur' => $this->completVisiteur,
            'imageEvenement' => $this->imageEvenement
        ];
    }
}
?>
