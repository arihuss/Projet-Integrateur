DROP TABLE Utilisateur CASCADE CONSTRAINTS;
DROP TABLE Inscription CASCADE CONSTRAINTS;
DROP TABLE Commentaire CASCADE CONSTRAINTS;
DROP TABLE Statistique CASCADE CONSTRAINTS;
DROP TABLE Evenement CASCADE CONSTRAINTS;
DROP TABLE Organisateur CASCADE CONSTRAINTS;
DROP TABLE Message CASCADE CONSTRAINTS;

-- Table Utilisateur 

CREATE TABLE Utilisateur
(
	id_utilisateur NUMBER(10) PRIMARY KEY,
	prenom VARCHAR2(55),
	nom VARCHAR2(55),
	courriel VARCHAR2(55) UNIQUE,
	num_tel VARCHAR2(14) UNIQUE,
	bio VARCHAR2(250),
	mot_de_passe VARCHAR2(50) CHECK(length(mot_de_passe) >= 8)
);

-- Table Organisateur
CREATE TABLE Organisateur (
    id_organisateur NUMBER(10) PRIMARY KEY,
    prenom VARCHAR2(55),
    nom VARCHAR2(55),
    courriel VARCHAR2(30) UNIQUE,
    bio VARCHAR2(240),
    nom_organisateur VARCHAR2(30),
    mot_de_passe VARCHAR2(50) CHECK(length(mot_de_passe) >= 8),
    nb_events NUMBER(10)
);


-- Table Statistique

CREATE TABLE Statistique
(
	id_statistique NUMBER(10) PRIMARY KEY,
	nb_visiteurs NUMBER(3),
	nb_benevoles NUMBER(3),
	nb_likes NUMBER(10),
	nb_vues NUMBER(10),
	nb_partages NUMBER(10)
);

-- Table Evenement
CREATE TABLE Evenement (
    id_evenement NUMBER(10) PRIMARY KEY,
    id_statistique NUMBER(10), 
    id_organisateur NUMBER(10),
    nom_event VARCHAR2(50),
    lieu VARCHAR2(100),
    date_debut VARCHAR2(25),
    date_fin VARCHAR2(25),
    nb_benevoles_max NUMBER(10),
    nb_participants_max NUMBER(10),
    etat_benevole NUMBER(1) CHECK (etat_benevole IN(0,1)),
    categorie VARCHAR2(80),
    description VARCHAR2(500),
    etat VARCHAR2(25) CHECK (etat IN ('en_cours', 'termine')),
    nb_inscriptions NUMBER(3),
    nb_benevoles_acceptes NUMBER(3),
    complet_benevole NUMBER(1) CHECK (complet_benevole IN(0,1)),
    complet_visiteur NUMBER(1) CHECK (complet_visiteur IN(0,1)),
    CONSTRAINT FK_id_organisateur FOREIGN KEY (id_organisateur) REFERENCES Organisateur(id_organisateur),
    CONSTRAINT FK_id_statistique FOREIGN KEY (id_statistique) REFERENCES Statistique(id_statistique)
);

-- Table Inscription

CREATE TABLE Inscription
(
	id_inscription NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) REFERENCES Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	role VARCHAR2(25) CHECK (role IN ('benevole', 'visiteur', 'appliquant')),
	date_inscription DATE NOT NULL,
	date_annulation DATE,
	CONSTRAINT fk_inscription_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur), 
    CONSTRAINT fk_inscription_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
);


-- Table Commentaire

CREATE TABLE Commentaire
(
	id_commentaire NUMBER(10) PRIMARY KEY,
	id_utilisateur NUMBER(10) REFERENCES Utilisateur(id_utilisateur), 
	id_evenement NUMBER(10) REFERENCES Evenement(id_evenement),
	message VARCHAR2(500),
	date_envoi DATE NOT NULL,
	CONSTRAINT fk_commentaire_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur), 
    CONSTRAINT fk_commentaire_evenement FOREIGN KEY (id_evenement) REFERENCES Evenement(id_evenement)
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

-- TESTS --
--INSERT UTILISATEUR--
INSERT INTO Utilisateur VALUES(1 , 'Camille' , 'Test' , 'camille.test@email.com' , '1234567890', 'Aime aider les autres' , 'motdepasse' ) ;
--INSERT ORGANISATEUR--
INSERT INTO Organisateur VALUES(1, 'Paul' , 'Valide' , 'paul.valide@organise.com' , 'Organisateur en tout genre' , 'organisation corp' , 'mdporganisateur', 5 ) ;
--INSERT Evenement--
INSERT INTO Evenement VALUES(1, 1, 1, 'Marathon caritatif', 'Montréal', TO_DATE('2025-05-01' , 'YYYY-MM-DD' ),  TO_DATE('2025-05-31' , 'YYYY-MM-DD' ), 50, 500, 1, 'Évènement à but non-lucratif', 'Nous organisons une course à but non-lucrative afin de récolter des fonds pour soutenir les organisations contre le cancer du pancréas', 'EN COURS', 100, 50, 1, 1 );
--INSERT Inscription--
INSERT INTO Inscription VALUES(1, 1, 1, 'BÉNÉVOLE', TO_DATE('2025-05-02' , 'YYYY-MM-DD' ), NULL);
--INSERT COMMENTAIRE--
INSERT INTO Commentaire VALUES(1, 1, 1, 'évènement incroyable avec une magnifique organisation, hâte au prochain!', TO_DATE('2025-06-05' , 'YYYY-MM-DD' ));
--INSERT STATISTIQUES--
INSERT INTO Statistique VALUES(1, 1, 500, 50, 200, 1000, 150);
--INSERT MESSAGE--
INSERT INTO Message VALUES(1, 1, 'SMS', 'BÉNÉVOLE', 'Bonjour, ne pas oublier de se présenter au guichet à 7h30 pour récupérer vos badges de bénévoles.', TO_DATE('2025-05-05', 'YYYY-MM-DD'));



--TEST UTILISATEUR(DOIS ÉCHOUER : MÊME COURRIEL ET MÊME NUM) validé--
INSERT INTO Utilisateur VALUES(2, 'Jean' , 'Marc' , 'camille.test@email.com' , '1234567890', 'Aime rien' , 'qwerty' ) ;

--TEST ÉVÈNEMENT(DOIT ÉCHOUER id_organisateur non-existant donc doit échouer)--
INSERT INTO Evenement VALUES(2, 2, 99, 'Conférence Test', 'Paris', '2025-06-10', '2025-06-12', 5, 50, 0, 'Technologie', 'Test clé étrangère', 1, 10, 3, 1, 0);

--TEST INSCRIPTION (ID UTILISATEUR INEXISTANT)--
INSERT INTO Inscription VALUES(2, 4, 1, 'Participant', TO_DATE('2025-04-10', 'YYYY-MM-DD'), NULL);

--TEST COMMENTAIRE (DATE QUI ÉCHOUE)--
INSERT INTO Commentaire VALUES (2, 1, 1, 'Test date vide', NULL);

-- TEST MESSAGE AVEC ID_EVENEMENT QUI EXISTE PAS DONC DOIT ÉCHOUER--
INSERT INTO Message VALUES (2, 99, 'SMS', 'BÉNÉVOLE', 'Test', TO_DATE('2025-05-05', 'YYYY-MM-DD'));
