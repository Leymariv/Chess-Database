-------------------------------------------------------------------------------
-- Ce fichier a pour but de tester différentes tentatives de coups invalides
-- A noter que la BD ne s'occupe pas des obstacles/prises de pions
-- 
-- PREREQUIS: les pièces doivent avoir été créées.
-------------------------------------------------------------------------------

-- Pion qui va autrement que une case tout droit
UPDATE PIECE
SET X=1, Y=5, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=9 AND
    	   	       	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=9 AND ID_PHASE=0 AND ID_PARTIE=1);    

-- Tour qui va en diagonale
UPDATE PIECE
SET X=7, Y=0, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=1 AND
    	      	     	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=1 AND ID_PHASE=0 AND ID_PARTIE=1);

-- Cavalier qui bouge anormalement
UPDATE PIECE
SET X=2, Y=2, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=2 AND
    	      	     	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=2 AND ID_PHASE=0 AND ID_PARTIE=1);

-- Roi qui essaie de bouger de plus d'une case
UPDATE PIECE
SET X=3, Y=5, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=4 AND
    	      	     	     	     	   	      	     ID_PHASE=0 AND
							     ID_PARTIE=1))
WHERE (ID_PIECE=4 AND ID_PHASE=0 AND ID_PARTIE=1);

-- Reine qui essaye d'accéder à une case à laquelle elle ne peut pas aller
UPDATE PIECE
SET X=5, Y=5, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=5 AND
    	      	     	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=5 AND ID_PHASE=0 AND ID_PARTIE=1);

-- Pièce qui essaye de sortir du plateau
UPDATE PIECE
SET X=8, Y=7, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=8 AND
    	      	     	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=8 AND ID_PHASE=0 AND ID_PARTIE=1);