DROP TABLE TOUR;
DROP TABLE RENCONTRE CASCADE CONSTRAINTS;
DROP TABLE JOUEUR CASCADE CONSTRAINTS;
DROP TABLE COULEUR CASCADE CONSTRAINTS;
DROP TABLE PIECE CASCADE CONSTRAINTS;
DROP TABLE JRC;
DROP TABLE PHASE_COUR;
DROP TABLE DEMANDE_INSCRIPTION;

-- JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
-- 	ID_JOUEUR > 0
CREATE TABLE JOUEUR(
ID_JOUEUR int primary key check (ID_JOUEUR > 0),
NOM Varchar(10),
PRENOM Varchar(10),
ADDR_POSTALE Varchar(20),
DATE_NAISSANCE Varchar(10)
);

-- RENCONTRE(ID_PHASE, ID_PARTIE, ID_JOUEUR)
-- 	ID_PHASE = {0, 1, 2, 3} [qualif, ¼ finales, ½ finales, finale]
-- 	ID_JOUEUR référence JOUEUR (peut être nul)
CREATE TABLE RENCONTRE(
ID_PHASE int,
ID_PARTIE int,
PRIMARY KEY(ID_PHASE, ID_PARTIE),
ID_GAGNANT int references JOUEUR,
ID_J1 int references JOUEUR,
ID_J2 int references JOUEUR,
-- 0 false 1 true
RESET_REQUEST int DEFAULT 0 CHECK (RESET_REQUEST IN (0,1)) 
);

-- COULEUR(ID_COULEUR)
-- 	ID_COULEUR = {NOIR, BLANC, UNDEFINED} = {0, 1, -1}
CREATE TABLE COULEUR(
ID_COULEUR int PRIMARY KEY CHECK (ID_COULEUR IN (0,1,-1))
);

-- PIECE(ID_PIECE, X, Y, PREX, PREY, ID_PHASE, ID_PARTIE, ID_COULEUR)
-- 	X, Y, PREX, PREY >= 0
-- 	f(X,Y,PREX,PREY) = TRUE (fonction de déplacement valide)
-- 	ID_PHASE & ID_PARTIE référencent RENCONTRE (ne peuvent pas être nuls)
-- 	ID_COULEUR référence COULEUR
CREATE TABLE PIECE(
ID_PIECE int check (ID_PIECE BETWEEN 1 AND 32),
X int check (X >= 0 AND X <= 7),
Y int check (Y >= 0 AND Y <= 7),
PREX int check (PREX >= 0 AND PREX <= 7),
PREY int check (PREY >= 0 AND PREY <= 7),
-- validité du mouvement
CONSTRAINT DEPLACEMENT CHECK (
-------------------------- pion noir
((ID_PIECE BETWEEN 9 AND 16) AND (ABS(X-PREX)<=1 AND Y=PREY-1)) OR
-------------------------- pion blanc
((ID_PIECE BETWEEN 17 AND 24) AND (ABS(X-PREX)<=1 AND Y=PREY+1)) OR
--------------------------  tour
(ID_PIECE IN (1,8,25,32) AND (X=PREX OR Y=PREY)) OR
------------------------- - cavalier
(ID_PIECE IN (2,7,26,31) AND (ABS(X-PREX) = 2 AND ABS(Y-PREY) = 1) OR 
	     		     (ABS(X-PREX) = 1 AND ABS(Y-PREY) = 2)) OR
------------------------- - fou
(ID_PIECE IN (3,6,27,30) AND (ABS(X-PREX)=ABS(Y-PREY))) OR
------------------------- - roi
(ID_PIECE IN (29, 4) AND (ABS(X-PREX)<=1 AND ABS(Y-PREY) <=1)) OR
------------------------- - reine
(ID_PIECE IN (28, 5) AND (X=PREX OR Y=PREY OR ABS(X-PREX)=ABS(Y-PREY)))
),
ID_PHASE int NOT NULL,
ID_PARTIE int NOT NULL,
FOREIGN KEY (ID_PHASE, ID_PARTIE) references RENCONTRE,
	ID_COULEUR int references COULEUR,
PRIMARY KEY(ID_PIECE,ID_PARTIE,ID_PHASE)
);

-- TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE)
-- 	NUM_TOUR, X, Y >= 0
-- 	ID_PHASE & ID_PARTIE (non nuls) référencent RENCONTRE
-- 	ID_PIECE référence PIECE (ne peut pas être nul)
CREATE TABLE TOUR(
NUM_TOUR int check (NUM_TOUR >= 0),
X int check (X >= 0 AND X <= 7),
Y int check (Y >= 0 AND Y <= 7),
ID_PHASE int,
ID_PARTIE int,
FOREIGN KEY (ID_PHASE, ID_PARTIE) references RENCONTRE,
ID_PIECE int not null,
FOREIGN KEY (ID_PIECE, ID_PARTIE, ID_PHASE) references PIECE,
PRIMARY KEY(NUM_TOUR,ID_PHASE,ID_PARTIE)
);

-- Thibault: en fait c'est bizarre de faire comme ça, 
-- pour les rencontres on n'a qu'à considérer qu'une
-- liaison 2..2 --------- 0..* c'est comme deux liaisons
-- 1..1 ---------- 0..* (cf table RENCONTRE)
-- JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
-- 	ID_JOUEUR référence JOUEUR 
-- 	ID_PHASE & ID_PARTIE référencent RENCONTRE
-- 	ID_COULEUR référence COULEUR 
CREATE TABLE JRC(
ID_JOUEUR int references JOUEUR,
ID_PHASE int,
ID_PARTIE int,
FOREIGN KEY (ID_PHASE, ID_PARTIE) references RENCONTRE,
ID_COULEUR int references COULEUR,
PRIMARY KEY(ID_JOUEUR,ID_PHASE,ID_PARTIE,ID_COULEUR)
);
--la ligne suivant indique qu'une couleur ne peut pas être partagé entre les joueurs d'une même rencontre ( unicité du triplet phase/partie/couleur )
ALTER TABLE JRC ADD constraint unicouleur UNIQUE(ID_PHASE,ID_PARTIE,ID_COULEUR);

CREATE TABLE PHASE_COUR(
ID_PHASE_COUR int primary key check (ID_PHASE_COUR >= 0)
);

-- Dans un premier temps, après on va pê le lier a JOUEUR
CREATE TABLE DEMANDE_INSCRIPTION(
ID_JOUEUR int primary key check (ID_JOUEUR > 0),
NOM Varchar(10),
PRENOM Varchar(10),
ADDR_POSTALE Varchar(20),
DATE_NAISSANCE Varchar(10)
);
