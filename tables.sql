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

-- Table Inscription

CREATE TABLE Inscription
(
	id_inscription NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) References Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) References Evenement(id_evenement),
	role VARCHAR2(25),
	date_inscription DATE NOT NULL,
	date_annulation DATE NOT NULL
);

-- Table Commentaire

CREATE TABLE Commentaire
(
	id_commentaire NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) References Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) References Evenement(id_evenement),
	message VARCHAR2(255),
	date_envoi DATE NOT NULL
);


-- Table Statistique

CREATE TABLE Statistique
(
	id_statistique NUMBER(10) PRIMARY KEY,
	id_evenement NUMBER(10) References Evenement(id_evenement),
	nbVisiteurs NUMBER(3),
	nbBenevoles NUMBER(3),
	nbLikes NUMBER(10),
	nbVues NUMBER(10),
	nbPartages NUMBER(10)
);


-- Table Message

CREATE TABLE Message
(
	id_message NUMBER(10) PRIMARY KEY,
	id_evenement NUMBER(10) References Evenement(id_evenement),
	moyen_communication VARCHAR2(10),
	type_destinataire VARCHAR2(10),
	message VARCHAR2(500),
	date_envoi DATE
);


-- Table Envoi

CREATE TABLE Envoi 
(
    id_envoi NUMBER(10) PRIMARY KEY,
    date_envoi DATE NOT NULL
);

-- Modifier la table Commentaire
ALTER TABLE Commentaire
ADD id_envoi NUMBER(10) REFERENCES Envoi(id_envoi);

-- Modifier la table Message
ALTER TABLE Message
ADD id_envoi NUMBER(10) REFERENCES Envoi(id_envoi);




-- Table Evenement 



-- Table Organisateur





