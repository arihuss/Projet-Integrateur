DROP TABLE Utilisateur CASCADE CONSTRAINTS;
DROP TABLE Inscription CASCADE CONSTRAINTS;
DROP TABLE Commentaire CASCADE CONSTRAINTS;
DROP TABLE Statistique CASCADE CONSTRAINTS;
DROP TABLE Evenement CASCADE CONSTRAINTS;
DROP TABLE Organisateur CASCADE CONSTRAINTS;
DROP TABLE Message CASCADE CONSTRAINTS;
DROP TABLE Envoi CASCADE CONSTRAINTS;

-- Table Utilisateur 

CREATE TABLE Utilisateur
(
	id_utilisateur NUMBER(10) PRIMARY KEY,
	prenom VARCHAR2(55),
	nom VARCHAR2(55),
	courriel VARCHAR2(55) UNIQUE,
	numTel VARCHAR2(14) UNIQUE,
	bio VARCHAR2(250),
	motDePasse VARCHAR2(25)
);

-- Table Evenement
CREATE TABLE Evenement (
    id_evenement NUMBER(10) PRIMARY KEY,
    id_statistique NUMBER(10) UNIQUE, 
    id_organisateur NUMBER(10),
    nom_event VARCHAR2(50),
    lieu VARCHAR2(25),
    date_debut VARCHAR2(25),
    date_fin VARCHAR2(25),
    nb_benevoles_max NUMBER(3),
    nb_participants_max NUMBER(3),
    etat_benevole BOOLEAN,
    categorie VARCHAR2(25),
    description VARCHAR2(255),
    etat NUMBER(25),
    nb_inscriptions NUMBER(3),
    nb_benevoles_acceptes NUMBER(3),
    complet_benevole BOOLEAN,
    complet_visiteur BOOLEAN,
    CONSTRAINT fk_evenement_organisateur FOREIGN KEY (id_organisateur) REFERENCES Organisateur(id_organisateur)
);

-- Table Inscription

CREATE TABLE Inscription
(
	id_inscription NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) REFERENCES Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	role VARCHAR2(25),
	date_inscription DATE NOT NULL,
	date_annulation DATE NOT NULL,
	CONSTRAINT fk_inscription_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur), 
    CONSTRAINT fk_inscription_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
);


-- Table Commentaire

CREATE TABLE Commentaire
(
	id_commentaire NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) REFERENCES Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	message VARCHAR2(255),
	date_envoi DATE NOT NULL,
	CONSTRAINT fk_commentaire_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur), 
    CONSTRAINT fk_commentaire_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
);


-- Table Statistique

CREATE TABLE Statistique
(
	id_statistique NUMBER(10) PRIMARY KEY,
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	nbVisiteurs NUMBER(3),
	nbBenevoles NUMBER(3),
	nbLikes NUMBER(10),
	nbVues NUMBER(10),
	nbPartages NUMBER(10),
	CONSTRAINT fk_statistique_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
);


-- Table Message

CREATE TABLE Message
(
	id_message NUMBER(10) PRIMARY KEY,
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	moyen_communication VARCHAR2(10),
	type_destinataire VARCHAR2(10),
	message VARCHAR2(500),
	date_envoi DATE,
	CONSTRAINT fk_message_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
);

-- Table Organisateur
CREATE TABLE Organisateur (
    id_organisateur NUMBER(10) PRIMARY KEY,
    prenom VARCHAR2(55),
    nom VARCHAR2(55),
    courriel VARCHAR2(100) UNIQUE,
    bio VARCHAR2(240),
    nom_organisateur VARCHAR2(150),
    mot_de_passe VARCHAR2(25),
    nb_events NUMBER(10)
);

