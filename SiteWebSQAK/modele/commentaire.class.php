<?php

class Commentaire implements JsonSerializable
{
    private int $id_commentaire;
    private int $id_utilisateur;
    private int $id_evenement;
    private string $message;
    private string $date_envoi; // Représentée en format 'Y-m-d'

    // Constructeur
    public function __construct(
        int $id_commentaire,
        int $id_utilisateur,
        int $id_evenement,
        string $message,
        string $date_envoi
    ) {
        $this->id_commentaire = $id_commentaire;
        $this->id_utilisateur = $id_utilisateur;
        $this->id_evenement = $id_evenement;
        $this->message = $message;
        $this->date_envoi = $date_envoi;
    }

    // Getters
    public function getIdCommentaire(): int
    {
        return $this->id_commentaire;
    }

    public function getIdUtilisateur(): int
    {
        return $this->id_utilisateur;
    }

    public function getIdEvenement(): int
    {
        return $this->id_evenement;
    }

    public function getMessage(): string
    {
        return $this->message;
    }

    public function getDateEnvoi(): string
    {
        return $this->date_envoi;
    }

    // Setters
    public function setIdCommentaire(int $id_commentaire): void
    {
        $this->id_commentaire = $id_commentaire;
    }

    public function setIdUtilisateur(int $id_utilisateur): void
    {
        $this->id_utilisateur = $id_utilisateur;
    }

    public function setIdEvenement(int $id_evenement): void
    {
        $this->id_evenement = $id_evenement;
    }

    public function setMessage(string $message): void
    {
        $this->message = $message;
    }

    public function setDateEnvoi(string $date_envoi): void
    {
        $this->date_envoi = $date_envoi;
    }

    public function __toString(): string {
        return "Commentaire: {$this->id_utilisateur}, : {$this->message}";
    }

    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id_commentaire' => $this->id_commentaire,
            'id_utilisateur' => $this->id_utilisateur,
            'id_evenement' => $this->id_evenement,
            'message' => $this->message,
            'date_envoi' => $this->date_envoi,
        ];
    }
}
