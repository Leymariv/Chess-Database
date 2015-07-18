-----------------------------------------------------------------------
-------------------Test de la cohérence de rencontre-------------------
-----------------------------------------------------------------------

CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL rencontre.lst
SET LINESIZE 100
SET PAGESIZE 50 


-- doublet de la même partie dans la même phase
INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(3,0,2,0);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(4,0,2,1);

--doublet de la même couleur dans la même partie
INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(5,0,3,1);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(6,0,3,1);

--affichage du résultat 
SELECT ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE
from JOUEUR;

SELECT ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR
from JRC;

SPOOL OFF
EXIT;
