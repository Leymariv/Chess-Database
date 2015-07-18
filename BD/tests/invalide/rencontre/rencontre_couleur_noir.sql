-----------------------------------------------------------------------
-------------------Test de la cohérence de rencontre-------------------
-----------------------------------------------------------------------
CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL rencontre_couleur_noir.lst
SET LINESIZE 100
SET PAGESIZE 50 

-- table de rencontre, on en créé une
INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE)
VALUES(0,2);

-- dans cette rencontre on insère deux joueurs qui partagent la même couleur ( interdit ) : noir
INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(1,0,2,0);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(2,0,2,0);



--affichage du résultat 
SELECT ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE
from JOUEUR;

SELECT ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR
from JRC;

SELECT *from RENCONTRE;

SPOOL OFF
EXIT;
