
-----------------------------------------------------------------------
-------------------Test de la cohérence de rencontre-------------------
-----------------------------------------------------------------------

CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL rencontre_trois_J.lst
SET LINESIZE 100
SET PAGESIZE 50 


INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE)
VALUES(0,3);


--doublet de la même couleur dans la même partie
INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(1,0,3,0);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(2,0,3,1);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(3,0,3,1);

--affichage du résultat 
SELECT ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE
from JOUEUR;

SELECT ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR
from JRC;

SELECT * from RENCONTRE;

SPOOL OFF
EXIT;
