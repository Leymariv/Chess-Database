-------------------------------------------------------------------------------
-- Ce test cherche à vérifier le bon fonctionnement des tours
--
-- PREREQUIS: les pièces doivent avoir été crées.
-------------------------------------------------------------------------------

-- Les blancs commencent.
-- TOUR1: Pion 9 en (0,5)
INSERT INTO TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE)
VALUES(1, 0, 5, 0, 1, 9);
UPDATE PIECE
SET X=0, Y=5, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=9 AND
    	   	       	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=9 AND ID_PHASE=0 AND ID_PARTIE=1);   

-- TOUR2: Pion 24 en (7,2)
INSERT INTO TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE)
VALUES(2, 7, 2, 0, 1, 24);
UPDATE PIECE
SET X=7, Y=2, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=24 AND
    	   	       	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=24 AND ID_PHASE=0 AND ID_PARTIE=1);  

-- TOUR3: Pion 9 en (0,4)
INSERT INTO TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE)
VALUES(3, 0, 4, 0, 1, 9);
UPDATE PIECE
SET X=0, Y=4, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=9 AND
    	   	       	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=9 AND ID_PHASE=0 AND ID_PARTIE=1); 

-- TOUR4: Pion 24 en (7,3)
INSERT INTO TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE)
VALUES(4, 7, 3, 0, 1, 24);
UPDATE PIECE
SET X=7, Y=3, (PREX, PREY) = (SELECT X, Y FROM PIECE WHERE (ID_PIECE=24 AND
    	   	       	     	     	       	     	    ID_PHASE=0 AND
							    ID_PARTIE=1))
WHERE (ID_PIECE=24 AND ID_PHASE=0 AND ID_PARTIE=1);  

COMMIT;
