#!/bin/bash


for p in 5 29
#on met les nums des différentes roi 
do
for x in `seq 0 7`
do
for y in `seq 0 7`
do	
	#tout les déplacements possible sur le terrain,
	touch roi$p\_deplacement_$x\_$y.sql
	echo "-------------------------------------------------------------------------------
-- Ce fichier a pour but de tester différentes tentatives de coups invalides
-- A noter que la BD ne s'occupe pas des obstacles/prises de pions
-- 
-- PREREQUIS: les pièces doivent avoir été créées.
-------------------------------------------------------------------------------
CONNECT koenigr/koenigr
-- Pour enregsitrer le result dans un fichier.
SPOOL roi$p\_deplacement_$x\_$y.lst
SET LINESIZE 100
SET PAGESIZE 50 

UPDATE PIECE
SET X=$x, Y=$y, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=$p AND
                                                            ID_PHASE=0 AND						    
							    ID_PARTIE=1))
WHERE (ID_PIECE=$p AND ID_PHASE=0 AND ID_PARTIE=1);
SPOOL OFF
EXIT;">>roi$p\_deplacement_$x\_$y.sql
done
done
done
