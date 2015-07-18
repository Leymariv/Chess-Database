package fabrique;

import java.sql.*;
import java.util.*;

public class ProduitListeJoueur extends ProduitAbstrait {
	class Joueur {
		public String nom;
		public int num;
		public Joueur(String nom, int num){
			this.nom=nom;
			this.num=num;
		}
	}

	LinkedList<Joueur> listeJoueur;
	Iterator<Joueur> i;
	Joueur j;
	public ProduitListeJoueur() throws Exception{
		listeJoueur=new LinkedList<Joueur>();
		// Recuperer la liste des joueurs
		openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM JOUEUR");
		while (rset.next()) {
			listeJoueur.add(new Joueur(rset.getString("NOM"),rset.getInt(1)));
		}
		rset.close();
		stmt.close();
		closeConnection();
	}
	public int nbJoueur(){
		return listeJoueur.size();
	}
	public void ParcoursListeJoueur(){
		i=listeJoueur.iterator();
	}
	public boolean JoueurHasNext(){
		if (i.hasNext()){
			j=i.next();
			return true;
		} else {
			return false;
		}
	}
	public String JoueurNextNom(){
		return j.nom;
	}
	public int JoueurNextNum(){
		return j.num;
	}
	public boolean JoueurExist(int i){
		Iterator<Joueur> it=listeJoueur.iterator();
		while (it.hasNext()){
			if (it.next().num==i)
				return true;
		}
		return false;
	}
}
