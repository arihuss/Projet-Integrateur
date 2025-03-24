<?php

class Message implements JsonSerializable
{
    private int $id_message;
    private int $id_evenement;
    private string $moyen_communication;
    private string $type_destinataire;
    private string $message;
    private string $date_envoi; 

   
    public function __construct(
        int $id_message,
        int $id_evenement,
        string $moyen_communication,
        string $type_destinataire,
        string $message,
        string $date_envoi 
    ) {
        $this->id_message = $id_message;
        $this->id_evenement = $id_evenement;
        $this->moyen_communication = $moyen_communication;
        $this->type_destinataire = $type_destinataire;
        $this->message = $message;
        $this->date_envoi = $date_envoi;
    }

  
    public function getIdMessage(): int
    {
        return $this->id_message;
    }

    public function getIdEvenement(): int
    {
        return $this->id_evenement;
    }

    public function getMoyenCommunication(): string
    {
        return $this->moyen_communication;
    }

    public function getTypeDestinataire(): string
    {
        return $this->type_destinataire;
    }

    public function getMessage(): string
    {
        return $this->message;
    }

    public function getDateEnvoi(): string
    {
        return $this->date_envoi;
    }


    public function setIdMessage(int $id_message): void
    {
        $this->id_message = $id_message;
    }

    public function setIdEvenement(int $id_evenement): void
    {
        $this->id_evenement = $id_evenement;
    }

    public function setMoyenCommunication(string $moyen_communication): void
    {
        $this->moyen_communication = $moyen_communication;
    }

    public function setTypeDestinataire(string $type_destinataire): void
    {
        $this->type_destinataire = $type_destinataire;
    }

    public function setMessage(string $message): void
    {
        $this->message = $message;
    }

    public function setDateEnvoi(string $date_envoi): void
    {
        $this->date_envoi = $date_envoi;
    }

    // Implémentation de JsonSerializable
    public function jsonSerialize(): array
    {
        return [
            'id_message' => $this->id_message,
            'id_evenement' => $this->id_evenement,
            'moyen_communication' => $this->moyen_communication,
            'type_destinataire' => $this->type_destinataire,
            'message' => $this->message,
            'date_envoi' => $this->date_envoi,
        ];
    }
}
