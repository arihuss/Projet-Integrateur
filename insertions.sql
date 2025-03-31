-- Insert Utilisateur
INSERT INTO Utilisateur (prenom, nom, courriel, num_tel, bio, mot_de_passe) 
VALUES ('Camille', 'Test', 'camille.test@email.com', '1234567890', 'Aime aider les autres', 'motdepasse'),
       ('Jean', 'Dupont', 'jean.dupont@email.com', '0987654321', 'Passionné de randonnée', 'password123'),
       ('Marie', 'Curie', 'marie.curie@email.com', '1122334455', 'Scientifique et curieuse', 'radioactif'),
       ('Paul', 'Martin', 'paul.martin@email.com', '2233445566', 'Fan de musique classique', 'beethoven'),
       ('Sophie', 'Lemoine', 'sophie.lemoine@email.com', '3344556677', 'Voyageuse dans l’âme', 'globetrotter'),
       ('Lucas', 'Bernard', 'lucas.bernard@email.com', '4455667788', 'Joueur de football amateur', 'goalkeeper'),
       ('Emma', 'Dubois', 'emma.dubois@email.com', '5566778899', 'Adore cuisiner', 'chocolat'),
       ('Hugo', 'Morel', 'hugo.morel@email.com', '6677889900', 'Passionné de cinéma', 'popcorn'),
       ('Léa', 'Fournier', 'lea.fournier@email.com', '7788990011', 'Amatrice de lecture', 'livres123'),
       ('Nathan', 'Rousseau', 'nathan.rousseau@email.com', '8899001122', 'Adepte de jeux vidéo', 'gamerlife');


-- Insert Organisateur
INSERT INTO Organisateur (prenom, nom, courriel, bio, nom_organisateur, mot_de_passe, nb_events)
VALUES 
('Jean', 'Durand', 'jean.durand@organise.com', 'Engagé pour la communauté locale', 'Solidarité Québec', 'mdp123', 4),
('Marie', 'Moreau', 'marie.moreau@organise.com', 'Organise des activités pour les jeunes', 'Jeunesse Unie', 'mdp456', 6),
('Luc', 'Bernard', 'luc.bernard@organise.com', 'Promotion des arts et de la culture', 'Art Sans Frontière', 'mdp789', 3),
('Sophie', 'Lavoie', 'sophie.lavoie@organise.com', 'Soutien aux personnes en difficulté', 'Main Tendue', 'mdpabc', 5),
('Antoine', 'Girard', 'antoine.girard@organise.com', 'Initiatives environnementales locales', 'ÉcoCitoyens', 'mdpdef', 2),
('Camille', 'Tremblay', 'camille.tremblay@organise.com', 'Favorise l’inclusion sociale', 'Ensemble Pour Tous', 'mdpghi', 8),
('Marc', 'Boucher', 'marc.boucher@organise.com', 'Organisateur d’activités éducatives', 'Savoirs Partagés', 'mdpjkl', 7),
('Élise', 'Roy', 'elise.roy@organise.com', 'Événements de sensibilisation', 'Voix Oubliées', 'mdpmno', 4),
('David', 'Pelletier', 'david.pelletier@organise.com', 'Mobilisation citoyenne', 'Action Commune', 'mdpqrs', 9),
('Nadia', 'Gagnon', 'nadia.gagnon@organise.com', 'Événements pour les aînés', 'Vie Active 60+', 'mdptuv', 1);

-- Insert Statistique
INSERT INTO Statistique (nb_visiteurs, nb_benevoles, nb_likes, nb_vues, nb_partages)
VALUES 
(500, 50, 200, 1000, 150),
(300, 30, 120, 750, 80),
(600, 40, 250, 1200, 200),
(200, 20, 90, 400, 50),
(450, 25, 170, 950, 120),
(700, 35, 300, 1500, 220),
(550, 40, 230, 1100, 160),
(320, 30, 100, 700, 90),
(800, 60, 340, 1700, 250),
(400, 45, 180, 900, 100);


-- Insert Evenement
INSERT INTO Evenement (id_statistique, id_organisateur, nom_event, lieu, date_debut, date_fin, etat, categorie, description, nb_inscriptions, nb_benevoles_acceptes, complet_benevole, complet_visiteur)
VALUES 
(1, 1, 'Marathon caritatif', 'Montréal', '2025-05-01', '2025-05-01', 'disponible', 'Évènement à but non-lucratif', 'Course à pied pour collecter des fonds pour la santé mentale.', 100, 50, 0, 0),
(2, 2, 'Fête de la jeunesse', 'Québec', '2025-06-10', '2025-06-12', 'disponible', 'Évènement à but non-lucratif', 'Activités pour les jeunes dans les quartiers défavorisés.', 80, 30, 0, 1),
(3, 3, 'Expo culture urbaine', 'Trois-Rivières', '2025-07-05', '2025-07-06', 'disponible', 'Évènement à but non-lucratif', 'Promotion des arts de rue et de la culture locale.', 120, 40, 1, 0),
(4, 4, 'Distribution alimentaire', 'Sherbrooke', '2025-04-15', '2025-04-15', 'disponible', 'Évènement à but non-lucratif', 'Aide alimentaire pour les familles en besoin.', 60, 20, 0, 0),
(5, 5, 'Nettoyage des parcs', 'Laval', '2025-05-20', '2025-05-20', 'disponible', 'Évènement à but non-lucratif', 'Initiative écoresponsable pour un environnement plus propre.', 50, 25, 0, 0),
(6, 6, 'Festival de l’inclusion', 'Gatineau', '2025-08-01', '2025-08-03', 'disponible', 'Évènement à but non-lucratif', 'Célébration de la diversité et de l’accessibilité.', 90, 35, 0, 1),
(7, 7, 'Foire éducative', 'Longueuil', '2025-09-12', '2025-09-13', 'disponible', 'Évènement à but non-lucratif', 'Stands interactifs pour promouvoir l’éducation.', 100, 40, 1, 0),
(8, 8, 'Forum citoyen', 'Drummondville', '2025-03-20', '2025-03-20', 'disponible', 'Évènement à but non-lucratif', 'Discussions et ateliers sur les droits civiques.', 70, 30, 0, 0),
(9, 9, 'Assemblée verte', 'Granby', '2025-04-22', '2025-04-22', 'disponible', 'Évènement à but non-lucratif', 'Activités pour la Journée de la Terre.', 110, 60, 1, 1),
(10, 10, 'Bal intergénérationnel', 'Lévis', '2025-06-01', '2025-06-01', 'disponible', 'Évènement à but non-lucratif', 'Rencontre festive entre jeunes et aînés.', 85, 45, 0, 1);


-- Insert Inscription
INSERT INTO Inscription (id_utilisateur, id_evenement, role, date_inscription) 
VALUES

(1, 1, 'benevole', '2025-04-15'),     -- Marathon caritatif (2025-05-01)
(2, 1, 'visiteur', '2025-04-20'),

(3, 2, 'benevole', '2025-05-15'),     -- Fête jeunesse (2025-06-10 au 12)
(4, 2, 'appliquant', '2025-06-01'),

(5, 3, 'visiteur', '2025-07-01'),     -- Expo culture (2025-07-05 au 06)
(6, 3, 'benevole', '2025-06-20'),

(7, 4, 'visiteur', '2025-04-10'),     -- Distribution alimentaire (2025-04-15)
(8, 4, 'benevole', '2025-04-12'),

(9, 5, 'appliquant', '2025-05-15'),   -- Nettoyage des parcs (2025-05-20)
(10, 5, 'benevole', '2025-05-16'),

(11, 6, 'visiteur', '2025-07-25'),    -- Festival inclusion (2025-08-01 au 03)
(12, 6, 'benevole', '2025-07-10'),

(13, 7, 'visiteur', '2025-09-01'),    -- Foire éducative (2025-09-12 au 13)

(14, 8, 'benevole', '2025-03-15'),    -- Forum citoyen (2025-03-20)

(15, 9, 'visiteur', '2025-04-20');    -- Assemblée verte (2025-04-22)


-- Insert Commentaire
INSERT INTO Commentaire (id_utilisateur, id_evenement, message, date_envoi)
VALUES 
(1, 1, 'Une ambiance incroyable! Le marathon était bien organisé et très motivant.', '2025-05-02'),
(2, 2, 'Ma fille a adoré la fête, c’est super de voir des événements pour les jeunes!', '2025-06-13'),
(3, 3, 'L’Expo était haute en couleur, un bel hommage à la culture urbaine.', '2025-07-07'),
(4, 4, 'Merci aux bénévoles, la distribution s’est faite dans le respect et la dignité.', '2025-04-16'),
(5, 5, 'Un bel effort collectif pour nettoyer notre quartier, bravo à tous!', '2025-05-21'),
(6, 6, 'Une fête inclusive et touchante. J’ai découvert des réalités que je ne connaissais pas.', '2025-08-04'),
(7, 7, 'J’ai appris plein de choses grâce aux ateliers interactifs, à refaire!', '2025-09-14'),
(8, 8, 'Beaucoup d’échanges enrichissants au forum, merci pour cet espace citoyen.', '2025-03-21'),
(9, 9, 'Magnifique journée pour la planète, ça fait du bien de s’unir pour la Terre!', '2025-04-23'),
(10, 10, 'Touchant et festif! Une belle connexion entre générations, à refaire absolument.', '2025-06-02');

INSERT INTO Message (id_evenement, moyen_communication, type_destinataire, message, date_envoi)
VALUES 
-- Marathon caritatif
(1, 'SMS', 'BÉNÉVOLE', 'Merci encore pour votre inscription au Marathon! N’oubliez pas vos chaussures de course  et d’arriver à 7h30.', '2025-04-29'),
(1, 'SMS', 'VISITEUR', 'Bonjour! Le Marathon caritatif commence à 8h. Présentez ce SMS à l’entrée.', '2025-04-30'),

-- Fête de la jeunesse
(2, 'SMS', 'BÉNÉVOLE', 'Préparez-vous pour l’accueil des jeunes : rendez-vous à 8h30 au kiosque 2. Merci!', '2025-06-09'),
(2, 'SMS', 'VISITEUR', 'Bienvenue à la Fête de la jeunesse! Pensez à apporter une bouteille d’eau et votre sourire ', '2025-06-10'),

-- Expo culture urbaine
(3, 'SMS', 'BÉNÉVOLE', 'Les installations artistiques arrivent à 7h00. Merci de venir aider au déchargement.', '2025-07-04'),

-- Distribution alimentaire
(4, 'SMS', 'BÉNÉVOLE', 'Bonjour! Merci d’être prêt(e) à 6h30. Des vestes et gants seront distribués sur place.', '2025-04-14'),

-- Nettoyage des parcs
(5, 'SMS', 'BÉNÉVOLE', 'Merci de votre implication ! RDV à 9h au parc central avec des vêtements confortables.', '2025-05-19'),

-- Festival de l’inclusion
(6, 'SMS', 'VISITEUR', 'Le Festival débute à 11h! Accès par la porte sud. Merci de participer à cette belle cause.', '2025-08-01'),
(6, 'SMS', 'BÉNÉVOLE', 'Rappel : réunion des bénévoles à 9h au kiosque central pour les consignes du jour.', '2025-07-31'),

-- Foire éducative
(7, 'SMS', 'BÉNÉVOLE', 'Merci de participer à la Foire éducative. RDV à 8h pour montage des stands.', '2025-09-11'),

-- Forum citoyen
(8, 'SMS', 'VISITEUR', 'Forum citoyen demain à 9h. Merci d’arriver un peu en avance pour l’enregistrement.', '2025-03-19'),

-- Assemblée verte
(9, 'SMS', 'BÉNÉVOLE', 'Pensez à apporter votre gourde et votre bonne humeur pour l’Assemblée verte !', '2025-04-21'),

-- Bal intergénérationnel
(10, 'SMS', 'BÉNÉVOLE', 'Les bénévoles sont attendus dès 16h30 pour les préparatifs du Bal. Tenue élégante recommandée.', '2025-05-31'),
(10, 'SMS', 'VISITEUR', 'Bienvenue au Bal intergénérationnel ! Arrivée des invités prévue dès 18h.', '2025-06-01');