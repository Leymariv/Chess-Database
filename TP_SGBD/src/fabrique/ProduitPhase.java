package fabrique;

import java.sql.*;


enum Phase {Inscription,Qualif,Quart,Demi,Finale,Fini};

public class ProduitPhase extends ProduitAbstrait{
	private Phase i;
	
	public void getPhase() throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT (ID_PHASE_COUR) FROM PHASE_COUR");
		if (rset.next()){
			switch(rset.getInt(1)){
			case 0: i=Phase.Inscription;break;
			case 1: i=Phase.Qualif;break;
			case 2: i=Phase.Quart;break;
			case 3: i=Phase.Demi;break;
			case 4: i=Phase.Finale;break;
			case 5: i=Phase.Fini;break;
			}
		} else {
			i=Phase.Inscription;
			stmt=conn.createStatement();
			stmt.execute("INSERT INTO PHASE_COUR(ID_PHASE_COUR) VALUES(0)");
		}
		rset.close();
		stmt.close();
	}
	public boolean equals(Object o){
		return this.i==((ProduitPhase)o).i;
	}
	
	public boolean phaseInscription(){
		return i==Phase.Inscription;
	}
	public boolean phaseJeu(){
		return i==Phase.Qualif || i==Phase.Quart || i==Phase.Demi || i==Phase.Finale;
	}
	public boolean phaseFini(){
		return i==Phase.Fini;
	}
	
	private static final Exception PhaseSuivantErreur = null;
	public void phaseSuivante() throws Exception {
		int val=0;
		switch (i){
		case Inscription: i=Phase.Qualif;val=1;break;
		case Qualif: i=Phase.Quart;val=2;break;
		case Quart: i=Phase.Demi;val=3;break;
		case Demi: i=Phase.Finale;val=4;break;
		case Finale: i=Phase.Fini;val=5;break;
		case Fini: val=5;throw PhaseSuivantErreur;
		}
		//Acces reel à la BD pour changer la phase en cours
		PreparedStatement pstmt = conn.prepareStatement("UPDATE PHASE_COUR SET ID_PHASE_COUR=?");
		pstmt.setInt(1, val);
		pstmt.executeQuery();
		pstmt.close();
	}
	
	public int getNumPhase(){
		switch(i){
		case Inscription:return 0;
		case Qualif:return 1;
		case Quart:return 2;
		case Demi:return 3;
		case Finale:return 4;
		case Fini:return 5;
		}
		return 0;
	}
}
