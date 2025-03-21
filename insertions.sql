-- Insert Utilisateur
INSERT INTO Utilisateur (prenom, nom, courriel, num_tel, bio, mot_de_passe) 
VALUES ('Camille', 'Test', 'camille.test@email.com', '1234567890', 'Aime aider les autres', 'motdepasse');

-- Insert Organisateur
INSERT INTO Organisateur (prenom, nom, courriel, bio, nom_organisateur, mot_de_passe, nb_events)
VALUES ('Paul', 'Valide', 'paul.valide@organise.com', 'Organisateur en tout genre', 'organisation corp', 'mdporganisateur', 5);

-- Insert Statistique
INSERT INTO Statistique (nb_visiteurs, nb_benevoles, nb_likes, nb_vues, nb_partages)
VALUES (500, 50, 200, 1000, 150);

SET @id_statistique = LAST_INSERT_ID();

-- Insert Evenement
INSERT INTO Evenement (id_statistique, id_organisateur, nom_event, lieu, date_debut, date_fin, etat, categorie, description, nb_inscriptions, nb_benevoles_acceptes, complet_benevole, complet_visiteur)
VALUES (@id_statistique, 1, 'Marathon caritatif', 'Montréal', '2025-05-01', '2025-05-31', 'disponible', 'Évènement à but non-lucratif', 'Voici une description', 100, 50, 1, 1);

SET @id_evenement = LAST_INSERT_ID();

-- Insert Inscription
INSERT INTO Inscription (id_utilisateur, id_evenement, role, date_inscription)
VALUES (1, @id_evenement, 'benevole', '2025-05-02');

-- Insert Commentaire
INSERT INTO Commentaire (id_utilisateur, id_evenement, message, date_envoi)
VALUES (1, @id_evenement, 'Évènement incroyable avec une magnifique organisation, hâte au prochain!', '2025-06-05');

-- Insert Message
INSERT INTO Message (id_evenement, moyen_communication, type_destinataire, message, date_envoi)
VALUES (@id_evenement, 'SMS', 'BÉNÉVOLE', 'Bonjour, ne pas oublier de se présenter au guichet à 7h30 pour récupérer vos badges de bénévoles.', '2025-05-05');