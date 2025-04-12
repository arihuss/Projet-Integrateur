<?php

class Participant implements JsonSerializable
{
    private int $id_inscription;
    private int $id_utilisateur;
    private int $id_evenement;
    private string $role; // 'benevole', 'visiteur', 'appliquant'
    private string $date_inscription; 
    

    // Constructeur
    public function __construct(
        int $id_inscription,
        int $id_utilisateur,
        int $id_evenement,
        string $role,
        string $date_inscription
        
    ) {
        $this->id_inscription = $id_inscription;
        $this->id_utilisateur = $id_utilisateur;
        $this->id_evenement = $id_evenement;
        $this->role = $role;
        $this->date_inscription = $date_inscription;
       
    }

    // Getters
    public function getIdInscription(): int
    {
        return $this->id_inscription;
    }

    public function getIdUtilisateur(): int
    {
        return $this->id_utilisateur;
    }

    public function getIdEvenement(): int
    {
        return $this->id_evenement;
    }

    public function getRole(): string
    {
        return $this->role;
    }

    public function getDateInscription(): string
    {
        return $this->date_inscription;
    }

   

    // Setters
    public function setIdInscription(int $id_inscription): void
    {
        $this->id_inscription = $id_inscription;
    }

    public function setIdUtilisateur(int $id_utilisateur): void
    {
        $this->id_utilisateur = $id_utilisateur;
    }

    public function setIdEvenement(int $id_evenement): void
    {
        $this->id_evenement = $id_evenement;
    }

    public function setRole(string $role): void
    {
        $this->role = $role;
    }

    public function setDateInscription(string $date_inscription): void
    {
        $this->date_inscription = $date_inscription;
    }

    

    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id_inscription' => $this->id_inscription,
            'id_utilisateur' => $this->id_utilisateur,
            'id_evenement' => $this->id_evenement,
            'role' => $this->role,
            'date_inscription' => $this->date_inscription,
            
        ];
    }
}
