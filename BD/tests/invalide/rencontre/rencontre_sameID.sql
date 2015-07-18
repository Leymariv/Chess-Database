-----------------------------------------------------------------------
-------------------Test de la cohérence de rencontre-------------------
-----------------------------------------------------------------------

CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL rencontre_sameID.lst
SET LINESIZE 100
SET PAGESIZE 50 

INSERT INTO RENCONTRE
values (0,1,1,1,2)

SPOOL OFF
EXIT;
