UPDATE RENCONTRE SET ID_GAGNANT=1 WHERE ID_PHASE=1 AND (ID_J1=1 OR ID_J2=1);
UPDATE RENCONTRE SET ID_GAGNANT=2 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=2 OR ID_J2=2);
UPDATE RENCONTRE SET ID_GAGNANT=3 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=3 OR ID_J2=3);
UPDATE RENCONTRE SET ID_GAGNANT=4 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=4 OR ID_J2=4);
UPDATE RENCONTRE SET ID_GAGNANT=5 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=5 OR ID_J2=5);
UPDATE RENCONTRE SET ID_GAGNANT=6 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=6 OR ID_J2=6);
UPDATE RENCONTRE SET ID_GAGNANT=7 WHERE ID_PHASE=1 AND ID_GAGNANT IS NULL AND (ID_J1=7 OR ID_J2=7);