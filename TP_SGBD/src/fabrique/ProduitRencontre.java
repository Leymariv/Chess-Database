package fabrique;

import java.sql.*;
import java.util.*;

public class ProduitRencontre extends ProduitAbstrait {
	class Adversaire{
		int idAdversaire, idJoueur;
		String nomAdversaire, nomJoueur;
		int idRencontre;
		public Adversaire(int idA,String nomA,int idR,String nomJ,int idJ){
			this.idAdversaire=idA;
			this.nomAdversaire=nomA;
			this.idRencontre=idR;
			this.idJoueur=idJ;
			this.nomJoueur=nomJ;
		}
	}
	LinkedList<Adversaire> listeRencontre;
	Iterator<Adversaire> i;
	Adversaire a;
	public void chercheAdversaire(int idPhase,int idJoueur) throws Exception{
		openConnection();
		PreparedStatement pstmt= conn.prepareStatement("SELECT r.ID_J2, j.NOM, r.ID_PARTIE FROM RENCONTRE r, JOUEUR j "+
		  "WHERE r.ID_PHASE=? AND r.ID_J1=? AND r.ID_J2=j.ID_JOUEUR AND r.ID_GAGNANT IS NULL");
		pstmt.setInt(1, idPhase);
		pstmt.setInt(2, idJoueur);
		ResultSet rset1=pstmt.executeQuery();
		pstmt=conn.prepareStatement("SELECT r.ID_J1, j.NOM, r.ID_PARTIE FROM RENCONTRE r, JOUEUR j "+
				  "WHERE r.ID_PHASE=? AND r.ID_J2=? AND r.ID_GAGNANT IS NULL AND r.ID_J1=j.ID_JOUEUR");
		pstmt.setInt(1, idPhase);
		pstmt.setInt(2, idJoueur);
		ResultSet rset2=pstmt.executeQuery();
		listeRencontre=new LinkedList<Adversaire>();
		while (rset1.next()){
			listeRencontre.add(new Adversaire(rset1.getInt(1), rset1.getString(2), rset1.getInt(3)," ",0));
		}
		while (rset2.next()){
			listeRencontre.add(new Adversaire(rset2.getInt(1), rset2.getString(2), rset2.getInt(3)," ",0));
		}
		i=listeRencontre.iterator();
		rset1.close();
		rset2.close();
		pstmt.close();
		closeConnection();
	}
	
	public void chercheAllRencontre(int idPhase) throws Exception{
		openConnection();
		PreparedStatement pstmt= conn.prepareStatement("SELECT j1.NOM,j2.NOM, r.ID_PARTIE FROM RENCONTRE r, JOUEUR j1, JOUEUR j2 WHERE r.ID_PHASE=? AND r.ID_J2=j2.ID_JOUEUR AND r.ID_J1=j1.ID_JOUEUR");
		pstmt.setInt(1, idPhase);
		ResultSet rset =pstmt.executeQuery();
		listeRencontre=new LinkedList<Adversaire>();
		while (rset.next()){
			listeRencontre.add(new Adversaire(0, rset.getString(1), rset.getInt(3), rset.getString(2), 0));
		}		
		i=listeRencontre.iterator();
		rset.close();
		pstmt.close();
		closeConnection();
	}
	
	public boolean existRencontre(int i){
		Iterator<Adversaire> it=listeRencontre.iterator();
		while (it.hasNext()){
			if (i==it.next().idRencontre)
				return true;
		}
		return false;
	}
	
	public boolean adversaireHasNext(){
		if (i.hasNext()){
			a=i.next();
			return true;
		} else {
			return false;
		}
	}
	public String adversaireNextNomJ(){
		return a.nomJoueur;
	}
	public int adversaireNextidJ(){
		return a.idJoueur;
	}
	public String adversaireNextNom(){
		return a.nomAdversaire;
	}
	public int adversaireNextidA(){
		return a.idAdversaire;
	}
	public int adversaireNextidR(){
		return a.idRencontre;
	}
	
	public void creerCouleur() throws Exception{
		openConnection();
			
		PreparedStatement pstmt= conn.prepareStatement("INSERT INTO COULEUR(ID_COULEUR) VALUES (1)");
		pstmt.executeQuery();
		pstmt=conn.prepareStatement("INSERT INTO COULEUR(ID_COULEUR) VALUES (0)");
		pstmt.executeQuery();
		pstmt.close();
	}
	
	public void creerRencontre(int idPhase, int idRencontre, int idJoueur1, int idJoueur2) throws Exception{		
		PreparedStatement pstmt= conn.prepareStatement("INSERT INTO RENCONTRE(ID_PHASE,ID_PARTIE,ID_GAGNANT,ID_J1,ID_J2,RESET_REQUEST) VALUES(?,?,null,?,?,0)");
		pstmt.setInt(1, idPhase);
		pstmt.setInt(2, idRencontre);
		pstmt.setInt(3, idJoueur1);
		pstmt.setInt(4, idJoueur2);
		pstmt.executeQuery();
		pstmt= conn.prepareStatement("INSERT INTO JRC(ID_JOUEUR,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,0)");
		pstmt.setInt(2, idPhase);
		pstmt.setInt(3, idRencontre);
		pstmt.setInt(1, idJoueur1);
		pstmt.executeQuery();
		pstmt= conn.prepareStatement("INSERT INTO JRC(ID_JOUEUR,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,1)");
		pstmt.setInt(2, idPhase);
		pstmt.setInt(3, idRencontre);
		pstmt.setInt(1, idJoueur2);
		pstmt.executeQuery();
		pstmt.close();
		for (int i=9; i<17;i++){
			pstmt=conn.prepareStatement("INSERT INTO PIECE(ID_PIECE, X, Y, PREX, PREY,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,null,null,?,?,0)");
			pstmt.setInt(1,i);
			pstmt.setInt(2, i-9);
			pstmt.setInt(3, 6);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idRencontre);
			pstmt.executeQuery();
			pstmt.close();
		}
		for (int i=17; i<25;i++){
			pstmt=conn.prepareStatement("INSERT INTO PIECE(ID_PIECE, X, Y, PREX, PREY,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,null,null,?,?,1)");
			pstmt.setInt(1,i);
			pstmt.setInt(2, i-17);
			pstmt.setInt(3, 1);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idRencontre);
			pstmt.executeQuery();
			pstmt.close();
		}
		for (int i=0;i<8;i++){
			pstmt=conn.prepareStatement("INSERT INTO PIECE(ID_PIECE, X, Y, PREX, PREY,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,null,null,?,?,0)");
			pstmt.setInt(1,8-i);
			pstmt.setInt(2, i);
			pstmt.setInt(3, 7);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idRencontre);
			pstmt.executeQuery();
			pstmt.close();
			pstmt=conn.prepareStatement("INSERT INTO PIECE(ID_PIECE, X, Y, PREX, PREY,ID_PHASE,ID_PARTIE,ID_COULEUR) VALUES(?,?,?,null,null,?,?,1)");
			pstmt.setInt(1,25+i);
			pstmt.setInt(2, i);
			pstmt.setInt(3, 0);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idRencontre);
			pstmt.executeQuery();
			pstmt.close();
		}
	}
	
	public boolean monTour(int idPhase,int idPartie, int idJoueur) throws Exception{
		// Noir=0 ; Blanc=1
		int numTour=0;
		int idCouleur=0;
		PreparedStatement pstmt=conn.prepareStatement("SELECT ID_COULEUR FROM JRC WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_JOUEUR=?");
		pstmt.setInt(1,idPhase);
		pstmt.setInt(2, idPartie);
		pstmt.setInt(3, idJoueur);
		ResultSet rset=pstmt.executeQuery();
		rset.next();
		idCouleur=rset.getInt(1);
		pstmt=conn.prepareStatement("SELECT Max(NUM_TOUR) FROM TOUR WHERE ID_PHASE=? AND ID_PARTIE=?");
		pstmt.setInt(1,idPhase);
		pstmt.setInt(2, idPartie);
		rset=pstmt.executeQuery();
		rset.next();
		numTour=rset.getInt(1);
		rset.close();
		pstmt.close();
		return (numTour+1) % 2 == idCouleur;
	}
	
	public boolean adDemandeRejouer(int idPhase,int idPartie, int idJoueur) throws Exception{
		int i=0;
		PreparedStatement pstmt=conn.prepareStatement("SELECT RESET_REQUEST FROM RENCONTRE WHERE ID_PHASE=? AND ID_PARTIE=?");
		pstmt.setInt(1,idPhase);
		pstmt.setInt(2, idPartie);
		ResultSet rset=pstmt.executeQuery();
		rset.next();
		i=rset.getInt(1);
		rset.close();
		pstmt.close();
		return i==1;
	}
	
	public void EstFini(int idPhase,int idPartie, int idJoueur) throws Exception{
			openConnection();
			PreparedStatement pstmt=conn.prepareStatement("UPDATE RENCONTRE SET ID_GAGNANT=? WHERE ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(1,idJoueur);
			pstmt.setInt(2,idPhase);
			pstmt.setInt(3, idPartie);
			pstmt.executeQuery();
			pstmt.close();
			closeConnection();
	}
	
	public void rejouer(int idPhase,int idPartie, int idJoueur) throws Exception{
		// On doit supprimer l'ensemble des tours et remettre l'état initial
		openConnection();
		serialConnection();
		PreparedStatement pstmt=conn.prepareStatement("DELETE FROM TOUR WHERE ID_PHASE=? AND ID_PARTIE=?");
		pstmt.setInt(1,idPhase);
		pstmt.setInt(2,idPartie);
		pstmt.executeQuery();
		for (int i=9; i<17;i++){
			pstmt=conn.prepareStatement("UPDATE PIECE SET X=?,Y=?, PREX=null, PREY=null WHERE ID_PIECE=? AND ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(3,i);
			pstmt.setInt(1, i-9);
			pstmt.setInt(2, 6);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idPartie);
			pstmt.executeQuery();
		}
		for (int i=17; i<25;i++){
			pstmt=conn.prepareStatement("UPDATE PIECE SET X=?,Y=?, PREX=null, PREY=null WHERE ID_PIECE=? AND ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(3,i);
			pstmt.setInt(1, i-17);
			pstmt.setInt(2, 1);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idPartie);
			pstmt.executeQuery();
		}
		for (int i=0;i<8;i++){
			pstmt=conn.prepareStatement("UPDATE PIECE SET X=?,Y=?, PREX=null, PREY=null WHERE ID_PIECE=? AND ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(3,8-i);
			pstmt.setInt(1, i);
			pstmt.setInt(2, 7);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idPartie);
			pstmt.executeQuery();
			pstmt=conn.prepareStatement("UPDATE PIECE SET X=?,Y=?, PREX=null, PREY=null WHERE ID_PIECE=? AND ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(3,25+i);
			pstmt.setInt(1, i);
			pstmt.setInt(2, 0);
			pstmt.setInt(4,idPhase);
			pstmt.setInt(5,idPartie);
			pstmt.executeQuery();
		}
		pstmt.close();
		notSerialConnection();
		closeConnection();
	}
	
	public void demandeRejouer(int idPhase,int idPartie, int idJoueur,int b) throws Exception{
		//idJoueur inutile car partie unique?
		openConnection();
		PreparedStatement pstmt=conn.prepareStatement("UPDATE RENCONTRE SET RESET_REQUEST=? WHERE ID_PHASE=? AND ID_PARTIE=?");
		pstmt.setInt(1,b);
		pstmt.setInt(2,idPhase);
		pstmt.setInt(3, idPartie);
		pstmt.executeQuery();
		pstmt.close();
		closeConnection();
	}
	
	public boolean partieFini(int idPhase, int idPartie) throws Exception{
		int idGagnant=0;
		openConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT ID_GAGNANT FROM RENCONTRE WHERE ID_PHASE=? AND ID_PARTIE=?");
		pstmt.setInt(1,idPhase);
		pstmt.setInt(2, idPartie);
		ResultSet rset =pstmt.executeQuery();
		rset.next();
		idGagnant=rset.getInt(1);
		pstmt.close();
		closeConnection();
		return idGagnant!=0;
	}

    public int maCouleur(int idPhase, int idPartie,int idJoueur) throws Exception{
	int idCouleur;
	PreparedStatement pstmt=conn.prepareStatement("SELECT ID_COULEUR FROM JRC WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_JOUEUR=?");
	pstmt.setInt(1,idPhase);
	pstmt.setInt(2, idPartie);
	pstmt.setInt(3, idJoueur);
	ResultSet rset=pstmt.executeQuery();
	rset.next();
	idCouleur=rset.getInt(1);
	return idCouleur;
    }
}
