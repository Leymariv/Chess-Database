-----------------------------------------------------------------------
-------------------Test de la cohérence de joueur-------------------
-----------------------------------------------------------------------

CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL joueur_id.lst
SET LINESIZE 100
SET PAGESIZE 50 

-- On insère un ID déjà présent.
INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (1, 'Doppleganger','simon','Rue bonjour', '10/10/2011')

INSERT INTO JOUEUR(ID_JOUEUR, NOM, PRENOM, ADDR_POSTALE, DATE_NAISSANCE)
VALUES (1, 'Dopper','imon','Rue jour', '11/11/2010')

SELECT * from JOUEUR;

SPOOL OFF
EXIT;
