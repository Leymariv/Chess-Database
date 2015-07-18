package fabrique;

import java.util.*;
import java.sql.*;


public class ProduitListeInscrit extends ProduitAbstrait{
	
	class Joueur{
		public String nom,prenom,adresse,date;
		public int num;
		public Joueur(int num,String nom,String prenom,String adresse,String date){
			this.num=num;
			this.nom=nom;
			this.prenom=prenom;
			this.adresse=adresse;
			this.date=date;
		}
	}
	
	LinkedList<Joueur> listeInscrit;
	Iterator<Joueur> i;
	Joueur j;
	public ProduitListeInscrit() throws Exception{
		listeInscrit=new LinkedList<Joueur>();
		//Acces à la BD pour rechercher les demandes d'inscriptions
		openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * FROM DEMANDE_INSCRIPTION");
			
		while (rset.next()) {
			listeInscrit.add(new Joueur(rset.getInt(1),rset.getString("NOM"),rset.getString("PRENOM"),rset.getString("ADDR_POSTALE"),rset.getString("DATE_NAISSANCE")));
		}
		
		rset.close();
		stmt.close();
		closeConnection();
	}
	
	public void parcoursListeInscrit(){
		i= listeInscrit.iterator();
	}
	public boolean inscritHasNext(){
		if (i.hasNext()){
			j=i.next();
			return true;
		} else {
			return false;
		}
	}
	public String inscritNextNom(){
		return j.nom;
	}
	public int inscritNextNum(){
		return j.num;
	}
	
	public void ajouteInscrit(String nom,String prenom,String adresse,String date) throws Exception{
		
		//Modification de la BD pour ajouter un inscrit
		openConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT MAX(ID_JOUEUR) AS ID_JOUEUR FROM " +
				"(SELECT MAX(ID_JOUEUR) AS ID_JOUEUR FROM DEMANDE_INSCRIPTION GROUP BY ID_JOUEUR " +
				"UNION ALL " +
				"SELECT MAX(ID_JOUEUR) AS ID_JOUEUR FROM JOUEUR GROUP BY ID_JOUEUR)");
		ResultSet rset=pstmt.executeQuery();
		rset.next();
		int id_to_insert=rset.getInt(1)+1;
		//System.out.println(id_to_insert);
		pstmt=conn.prepareStatement("INSERT INTO DEMANDE_INSCRIPTION(ID_JOUEUR,NOM,PRENOM,ADDR_POSTALE,DATE_NAISSANCE) VALUES(?,?,?,?,?)");
		pstmt.setInt(1, id_to_insert);
		pstmt.setString(2, nom);
		pstmt.setString(3, prenom);
		pstmt.setString(4, adresse);
		pstmt.setString(5, date);
		pstmt.executeQuery();
			
		rset.close();
		pstmt.close();
		closeConnection();
	}
	
	public void validerInscrit(int i) throws Exception{
		//Modification de la BD pour retirer l'inscrit et l'ajouter au joueur;
		openConnection();
		serialConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT ID_JOUEUR,NOM,PRENOM,ADDR_POSTALE,DATE_NAISSANCE FROM DEMANDE_INSCRIPTION WHERE ID_JOUEUR=?");
		pstmt.setInt(1, i);
		ResultSet rset=pstmt.executeQuery();
		rset.next();
		pstmt=conn.prepareStatement("INSERT INTO JOUEUR(ID_JOUEUR,NOM,PRENOM,ADDR_POSTALE,DATE_NAISSANCE) VALUES(?,?,?,?,?)");
		pstmt.setInt(1, i);
		pstmt.setString(2, rset.getString("NOM"));
		pstmt.setString(3, rset.getString("PRENOM"));
		pstmt.setString(4, rset.getString("ADDR_POSTALE"));
		pstmt.setString(5, rset.getString("DATE_NAISSANCE"));
		pstmt.executeQuery();
		pstmt=conn.prepareStatement("DELETE FROM DEMANDE_INSCRIPTION WHERE ID_JOUEUR=?");
		pstmt.setInt(1, i);
		pstmt.executeQuery();
		conn.commit();
		rset.close();
		pstmt.close();
		closeConnection();
	}
}
