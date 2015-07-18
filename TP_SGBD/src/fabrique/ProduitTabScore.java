package fabrique;

import java.sql.*;
import java.util.*;

public class ProduitTabScore extends ProduitAbstrait{
	class Joueur {
		public String nom;
		public int nbGagner,nbJouer,num;
		public Joueur(String nom,int nbGagner, int nbJouer, int num){
			this.nom=nom;
			this.nbGagner=nbGagner;
			this.nbJouer=nbJouer;
			this.num=num;
		}
	}
	class JoueurComparator implements Comparator<Joueur>{
		public int compare(Joueur a, Joueur b){
			if (a.nbGagner>b.nbGagner){
				return -1;
			} else if (a.nbGagner==b.nbGagner){
				return 0;
			} else {
				return 1;
			}
		}
	}
	private LinkedList<Joueur> liste;
	private Iterator<Joueur> i;
	private Joueur j;
	public ProduitTabScore(int idPhase) throws Exception{
		liste=new LinkedList<Joueur>();
		// Recuperer pour chaque joueur, le nombre de partie fini 
		// et le nombre de partie gagné (dans la phase courante)
		
		ProduitAbstrait listeJ=new ProduitListeJoueur();
		listeJ.ParcoursListeJoueur();
		openConnection();
		serialConnection();
		while (listeJ.JoueurHasNext()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT Count(ID_PARTIE) FROM RENCONTRE WHERE (ID_J1=? OR ID_J2=?)" +
					"AND ID_PHASE=? " +
					"AND ID_GAGNANT IS NOT NULL");
			pstmt.setInt(1, listeJ.JoueurNextNum());
			pstmt.setInt(2, listeJ.JoueurNextNum());
			pstmt.setInt(3, idPhase);
			ResultSet nbPartiesJouer=pstmt.executeQuery();
			nbPartiesJouer.next();
			// sélectionne le nb de parties gagnées
			pstmt=conn.prepareStatement("SELECT COUNT(ID_PARTIE) FROM RENCONTRE WHERE (ID_GAGNANT=? AND ID_PHASE=?)");
			pstmt.setInt(1, listeJ.JoueurNextNum());
			pstmt.setInt(2, idPhase);
			ResultSet nbPartiesGagner=pstmt.executeQuery();
			nbPartiesGagner.next();
			liste.add(new Joueur(listeJ.JoueurNextNom(),nbPartiesGagner.getInt(1),nbPartiesJouer.getInt(1),listeJ.JoueurNextNum()));
			pstmt.close();
		}
		notSerialConnection();
		closeConnection();
		Collections.sort(liste,new JoueurComparator());
	}

	public void ParcoursTabScore(){
		i=liste.iterator();
	}
	public boolean TabScoreHasNext(){
		if (i.hasNext()){
			j=i.next();
			return true;
		} else {
			return false;
		}
	}
	public int TabScoreGetNum(int i){
		return liste.get(i).num;
	}
	public String TabScoreNextNom(){
		return j.nom;
	}
	public int TabScoreNextnbG(){
		return j.nbGagner;
	}
	public int TabScoreNextnbJ(){
		return j.nbJouer;
	}
	public int TabScoreNextNum(){
		return j.num;
	}
	
}
