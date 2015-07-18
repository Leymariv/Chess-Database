DELETE FROM PHASE_COUR WHERE ID_PHASE_COUR=0;

INSERT INTO PHASE_COUR(ID_PHASE_COUR)
VALUES(1);

INSERT INTO COULEUR(ID_COULEUR)
VALUES(0);

INSERT INTO COULEUR(ID_COULEUR)
VALUES(1);

INSERT INTO COULEUR(ID_COULEUR)
VALUES(-1);

INSERT INTO JOUEUR(ID_JOUEUR,NOM,PRENOM,ADDR_POSTALE,DATE_NAISSANCE) VALUES(1,'jodin','a','a','a');
INSERT INTO JOUEUR(ID_JOUEUR,NOM,PRENOM,ADDR_POSTALE,DATE_NAISSANCE) VALUES(2,'leymarie','a','a','a');

INSERT INTO RENCONTRE(ID_PHASE, ID_PARTIE, ID_J1, ID_J2)
VALUES(1,1,1,2);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(1,1,1,0);

INSERT INTO JRC(ID_JOUEUR, ID_PHASE, ID_PARTIE, ID_COULEUR)
VALUES(2,1,1,1);

-- le blanc est en echec
-- le blanc c'est valmon

INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(29,0,0,1,1,1);

INSERT INTO PIECE(ID_PIECE,X,Y,ID_PHASE,ID_PARTIE,ID_COULEUR)
VALUES(3,3,3,1,1,0);
