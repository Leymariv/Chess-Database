-----------------------------------------------------------------------
-------------------Test de la cohérence de rencontre-------------------
-----------------------------------------------------------------------
CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL rencontre_couleur_blanc.lst
SET LINESIZE 100
SET PAGESIZE 50 


-- dans cette rencontre on insère deux joueurs qui partagent la même couleur ( interdit ) : noir
INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(1,0,2,1);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(2,0,2,1);

--affichage du résultat 
SELECT ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE
from JOUEUR;

SELECT ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR
from JRC;

SELECT *from RENCONTRE;

SPOOL OFF
EXIT;
