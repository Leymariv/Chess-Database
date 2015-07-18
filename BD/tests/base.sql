--------------------------------------------------------------------------------
-- Ce fichier permet d'initialiser une rencontre entre 2 joueurs non existants
-- Jean joue les noirs et Juan joue les blancs
--------------------------------------------------------------------------------

COMMIT;

-- Insertion des joueurs

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (1, 'Dupont', 'Jean', 'Rue du francais', '13/10/1946');

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (2, 'Martinez', 'Juan', 'Career del espanyol', '25/06/1862');

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (3, 'jodin', 'Romaric', 'grenoble', '01/01/1990');

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (4, 'Leymarie', 'Valmon', 'grenoble', '25/06/1990');

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (5, 'koenig', 'romain', 'grenoble ville', '25/06/1990');

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (6, 'Rihet', 'Thibaut', 'grenoble city', '25/06/1990');

-- Creation des rencontres

INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE)
VALUES(0,1);

INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE)
VALUES(0,2);

INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE)
VALUES(0,3);

-- Creation des couleurs disponibles

INSERT INTO COULEUR(ID_COULEUR)
VALUES(0);

INSERT INTO COULEUR(ID_COULEUR)
VALUES(1);

INSERT INTO COULEUR(ID_COULEUR)
VALUES(-1);

-- Creation de la relation Joueur/Rencontre/Couleur pour joueur 1/2 partie 0/1

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(1,0,1,0);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(2,0,1,1);

-- Creation des pieces

-- noirs
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(1,0,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(2,1,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(3,2,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(4,3,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(5,4,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(6,5,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(7,6,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(8,7,7,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(9,0,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(10,1,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(11,2,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(12,3,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(13,4,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(14,5,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(15,6,6,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(16,7,6,0,1,0);

-- blancs
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(17,0,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(18,1,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(19,2,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(20,3,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(21,4,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(22,5,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(23,6,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(24,7,1,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(25,0,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(26,1,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(27,2,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(28,3,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(29,4,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(30,5,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(31,6,0,0,1,0);
INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(32,7,0,0,1,0);

COMMIT;
