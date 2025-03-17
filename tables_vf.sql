SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checks temporarily

DROP TABLE IF EXISTS Inscription;
DROP TABLE IF EXISTS Commentaire;
DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Evenement;
DROP TABLE IF EXISTS Statistique;
DROP TABLE IF EXISTS Organisateur;
DROP TABLE IF EXISTS Utilisateur;

SET FOREIGN_KEY_CHECKS = 1; -- Re-enable foreign key checks

-- Table Utilisateur
CREATE TABLE Utilisateur (
    id_utilisateur INT(10) AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(55),
    nom VARCHAR(55),
    courriel VARCHAR(55) UNIQUE,
    num_tel VARCHAR(14) UNIQUE,
    bio VARCHAR(250),
    mot_de_passe VARCHAR(50),
    CHECK (CHAR_LENGTH(mot_de_passe) >= 8)
);

-- Table Organisateur
CREATE TABLE Organisateur (
    id_organisateur INT(10) AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(55),
    nom VARCHAR(55),
    courriel VARCHAR(30) UNIQUE,
    bio VARCHAR(240),
    nom_organisateur VARCHAR(30),
    mot_de_passe VARCHAR(50),
    nb_events INT(10) DEFAULT 0,
    CHECK (CHAR_LENGTH(mot_de_passe) >= 8)
);

-- Table Statistique
CREATE TABLE Statistique (
    id_statistique INT(10) AUTO_INCREMENT PRIMARY KEY,
    nb_visiteurs INT(3) DEFAULT 0,
    nb_benevoles INT(3) DEFAULT 0,
    nb_likes INT(10) DEFAULT 0,
    nb_vues INT(10) DEFAULT 0,
    nb_partages INT(10) DEFAULT 0
);

-- Table Evenement
CREATE TABLE Evenement (
    id_evenement INT(10) AUTO_INCREMENT PRIMARY KEY,
    id_statistique INT(10),
    id_organisateur INT(10),
    nom_event VARCHAR(50),
    lieu VARCHAR(100),
    date_debut DATE,
    date_fin DATE,
    nb_benevoles_max INT(3) DEFAULT 0,
    nb_participants_max INT(3) DEFAULT 0,
    etat_benevole TINYINT(1) CHECK (etat_benevole IN (0,1)),
    categorie VARCHAR(80),
    description VARCHAR(500),
    etat ENUM('disponible', 'termine') NOT NULL,
    nb_inscriptions INT(3) DEFAULT 0,
    nb_benevoles_acceptes INT(3) DEFAULT 0,
    complet_benevole TINYINT(1) CHECK (complet_benevole IN (0,1)),
    complet_visiteur TINYINT(1) CHECK (complet_visiteur IN (0,1)),
    CONSTRAINT fk_evenement_organisateur FOREIGN KEY (id_organisateur) REFERENCES Organisateur(id_organisateur) ON DELETE CASCADE,
    CONSTRAINT fk_evenement_statistique FOREIGN KEY (id_statistique) REFERENCES Statistique(id_statistique) ON DELETE CASCADE
);

-- Table Inscription
CREATE TABLE Inscription (
    id_inscription INT(10) AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT(10),
    id_evenement INT(10),
    role ENUM('benevole', 'visiteur', 'appliquant') NOT NULL,
    date_inscription DATE NOT NULL,
    date_annulation DATE DEFAULT NULL,
    CONSTRAINT fk_inscription_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE, 
    CONSTRAINT fk_inscription_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement) ON DELETE CASCADE
);

-- Table Commentaire
CREATE TABLE Commentaire (
    id_commentaire INT(10) AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT(10),
    id_evenement INT(10),
    message VARCHAR(500),
    date_envoi DATE NOT NULL,
    CONSTRAINT fk_commentaire_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE, 
    CONSTRAINT fk_commentaire_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement) ON DELETE CASCADE
);

-- Table Message
CREATE TABLE Message (
    id_message INT(10) AUTO_INCREMENT PRIMARY KEY,
    id_evenement INT(10),
    moyen_communication VARCHAR(10),
    type_destinataire VARCHAR(10),
    message VARCHAR(500),
    date_envoi DATE,
    CONSTRAINT fk_message_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement) ON DELETE CASCADE
);

