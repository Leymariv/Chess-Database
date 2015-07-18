package fabrique;


import interface_IO.Echiquier;

import java.sql.*;

public class ProduitEchiquier extends ProduitAbstrait{
	private int tab[][];
	private int numPartie;
	private int numPhase;
	private Pos piece[];
	
	class Pos{
		int i,j;
		public Pos(int i,int j){
			this.i=i;
			this.j=j;
		}
		public boolean equals(Object o){
			return this.i==((Pos)o).i && this.j==((Pos)o).j;
		}
	}
	
	
	public ProduitEchiquier(int n, int p) throws Exception{
		piece=new Pos[32];
		if (!(n==0 && p==0)){
			this.numPartie=n;
			this.numPhase=p;					
			// Acces reel à la base de données pour remplir le tableau tab
			openConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT ID_PIECE,X, Y FROM PIECE WHERE ID_PHASE=? AND ID_PARTIE=? AND X IS NOT NULL AND Y IS NOT NULL");
			pstmt.setInt(1, numPhase);
			pstmt.setInt(2, numPartie);
			ResultSet rset=pstmt.executeQuery();
			while (rset.next()){
				piece[rset.getInt(1)-1]=new Pos(rset.getInt(2),rset.getInt(3));
			}
			rset.close();
			pstmt.close();
			closeConnection();
		} else {
			for (int i=1;i<=8;i++){
				piece[i-1]=new Pos(8-i,7);
			}
			for (int i=9;i<=16;i++){
				piece[i-1]=new Pos(i-9,6);
			}
			for (int i=17;i<=24;i++){
				piece[i-1]=new Pos(i-17,1);
			}
			for (int i=25;i<=32;i++){
				piece[i-1]=new Pos(i-25,0);
			}
			/*piece[24]=new Pos(7,3);
			piece[31]=new Pos(7,2);
			piece[27]=new Pos(0,4);
			piece[3]=new Pos(2,3);
			piece[10]=new Pos(6,4);
			this.numPartie=1;
			this.numPhase=1;*/
		}
		tab=new int[8][8];
		for (int i=1; i<=32; i++){
			if (piece[i-1]!=null)
				tab[piece[i-1].i][piece[i-1].j]=i;
		}
	}
	
	public void changePiece(int phase, int rencontre, int tour) throws Exception{
			openConnection();
			PreparedStatement pstmt=conn.prepareStatement("SELECT ID_PIECE, X, Y FROM TOUR WHERE (NUM_TOUR=? AND ID_PHASE=? AND ID_PARTIE=?)");
			pstmt.setInt(1,tour);
			pstmt.setInt(2, phase);
			pstmt.setInt(3, rencontre);
			ResultSet rset=pstmt.executeQuery();
			rset.next();
			if (tab[rset.getInt(2)][rset.getInt(3)]!=0) 
				piece[tab[rset.getInt(2)][rset.getInt(3)]-1]=null;
			tab[piece[rset.getInt(1)-1].i][piece[rset.getInt(1)-1].j]=0;
			piece[rset.getInt(1)-1]=new Pos(rset.getInt(2),rset.getInt(3));
			tab[rset.getInt(2)][rset.getInt(3)]=rset.getInt(1);
			rset.close();
			pstmt.close();
			closeConnection();
	}
	
	private boolean existPieceEntre(int x, int y, int prex, int prey, int idPiece){
		if (idPiece==1 || idPiece==6 || idPiece==25 || idPiece==30)
			return false;
		if (Math.abs(x-prex) == Math.abs(y-prey) || x-prex==0 || y-prey==0){
			int i=x+Math.abs(prex-x)/(prex-x==0?1:prex-x);
			int j=y+Math.abs(prey-y)/(prey-y==0?1:prey-y);
			while(i!=prex || j!=prey){
				if (tab[i][j]!=0)
					return true;
				i=i+Math.abs(prex-x)/(prex-x==0?1:prex-x);
				j=j+Math.abs(prey-y)/(prey-y==0?1:prey-y);
			}
		} else {
			return true;
		}
		return false;
	}
	
	/*
	 * return true si le joueur numJoueur est en echec
	 */
	public boolean echec(int numJoueur) throws Exception{
		int couleur=0;
		openConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT ID_COULEUR FROM JRC WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_JOUEUR=?");
		pstmt.setInt(1, numPhase);
		pstmt.setInt(2, numPartie);
		pstmt.setInt(3, numJoueur);
		ResultSet rset=pstmt.executeQuery();
		if (rset.next())
			couleur=rset.getInt(1);
		rset.close();
		pstmt.close();
		closeConnection();
		int roi,b1,b2;
		if (couleur==0){
			roi=3;
			b1=16;
			b2=32;
		} else {
			roi=28;
			b1=0;
			b2=16;
		}
		for (int i=b1;i<b2;i++){
			try {
				if (piece[i]!=null 
						&& !existPieceEntre(piece[roi].i, piece[roi].j, piece[i].i, piece[i].j,i)
						&& ( i<8 || i>=24  || ((i>=8 && i<24) && piece[roi].i-piece[i].i!=0))   
								){
					openConnection();
					pstmt=conn.prepareStatement("SELECT X, Y, PREX, PREY FROM PIECE WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
					pstmt.setInt(1, i+1);
					pstmt.setInt(3, numPhase);
					pstmt.setInt(2, numPartie);
					rset=pstmt.executeQuery();
					rset.next();
					pstmt=conn.prepareStatement("UPDATE PIECE SET X=?, Y=?, PREX=?,PREY=? WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
					pstmt.setInt(5, i+1);
					pstmt.setInt(1, piece[roi].i);
					pstmt.setInt(2, piece[roi].j);
					pstmt.setInt(3, piece[i].i);
					pstmt.setInt(4, piece[i].j);
					pstmt.setInt(7, numPhase);
					pstmt.setInt(6, numPartie);
					pstmt.executeQuery();
					pstmt=conn.prepareStatement("UPDATE PIECE SET X=?, Y=?, PREX=?,PREY=? WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
					pstmt.setInt(5, i+1);
					pstmt.setInt(1, rset.getInt(1));
					pstmt.setInt(2, rset.getInt(2));
					pstmt.setInt(3, rset.getInt(3));
					pstmt.setInt(4, rset.getInt(4));
					pstmt.setInt(7, numPhase);
					pstmt.setInt(6, numPartie);
					pstmt.executeQuery();
					rset.close();
					pstmt.close();
					closeConnection();
					return true;
				}
			} catch (Exception e){
				;	
			}
		}
		return false;
	}
	
	/*
	 * return true si le joueur numJoueur est echec et mat
	 */
	public boolean echecmat(int numJoueur) throws Exception{
		int couleur=0;
		openConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT ID_COULEUR FROM JRC WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_JOUEUR=?");
		pstmt.setInt(1, numPhase);
		pstmt.setInt(2, numPartie);
		pstmt.setInt(3, numJoueur);
		ResultSet rset=pstmt.executeQuery();
		if (rset.next())
			couleur=rset.getInt(1);
		rset.close();
		pstmt.close();
		openConnection();
		int b1,b2,roi;
		if (couleur==0){//noir
			b1=0;b2=16;roi=3;
		} else {//blanc
			b1=16;b2=32;roi=12;
		}
		for (int k=b1; k<b2;k++){
			for (int i=0; i<8;i++){
				for (int j=0; j<8; j++){
					int k2=(k+roi)%16+b1;
					try {
						if (piece[k2]!=null && !existPieceEntre(i, j, piece[k2].i, piece[k2].j,k2)
								&& (k2<8 || k2>=24 || ((k2>=8 && k2<24) && ( (i-piece[k2].i==0 && tab[i][j]==0) || (i-piece[k2].i!=0 && tab[i][j]!=0)  ))
								)){
							openConnection();
							pstmt=conn.prepareStatement("SELECT X, Y, PREX, PREY FROM PIECE WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
							pstmt.setInt(1, k2+1);
							pstmt.setInt(3, numPhase);
							pstmt.setInt(2, numPartie);
							rset=pstmt.executeQuery();
							rset.next();
							pstmt=conn.prepareStatement("UPDATE PIECE SET X=?, Y=?, PREX=?,PREY=? WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
							pstmt.setInt(5,k2+1);
							pstmt.setInt(1, i);
							pstmt.setInt(2, j);
							pstmt.setInt(3, piece[k2].i);
							pstmt.setInt(4, piece[k2].j);
							pstmt.setInt(7, numPhase);
							pstmt.setInt(6, numPartie);
							pstmt.executeQuery();
							pstmt=conn.prepareStatement("UPDATE PIECE SET X=?, Y=?, PREX=?,PREY=? WHERE ID_PIECE=? AND ID_PARTIE=? AND ID_PHASE=?");
							pstmt.setInt(5,k2+1);
							pstmt.setInt(1, rset.getInt(1));
							pstmt.setInt(2, rset.getInt(2));
							pstmt.setInt(3, rset.getInt(3));
							pstmt.setInt(4, rset.getInt(4));
							pstmt.setInt(7, numPhase);
							pstmt.setInt(6, numPartie);
							pstmt.executeQuery();
							rset.close();							
							int tmpi=piece[k2].i;
							int tmpj=piece[k2].j;
							int tmp=tab[i][j];
							if (tmp==0 || !(tmp-1>=b1 && tmp-1<b2)){
								if (tmp!=0){
									piece[tmp-1]=null;
								}
								piece[k2].i=i;
								piece[k2].j=j;
							/*	Echiquier e=new Echiquier();
								tab [i][j]=k2+1;
								tab[tmpi][tmpj]=0;
								e.affichage(this); 
								if (tmp!=0)
									tab [i][j]=tmp;
								else 
									tab[i][j]=0;
								tab[tmpi][tmpj]=k2+1;*/
								if (!echec(numJoueur)){							
									if (tmp!=0){
										piece[tmp-1]=new Pos(i,j);
									}
									piece[k2].i=tmpi;
									piece[k2].j=tmpj;			
									return false;
								} else if (tmp!=0){
									piece[tmp-1]=new Pos(i,j);
								}
								piece[k2].i=tmpi;
								piece[k2].j=tmpj;
							}
							pstmt.close();
							closeConnection();
						}			
					} catch (Exception e){
						;
					}
				}
			}
		}
		return true;
	}
	
	private boolean identite(boolean a,boolean b){
		return (a && b) || (!a && !b);
	}
	
	public boolean jouerCoup(int iprec, int jprec, int i, int j, int idJoueur) throws Exception{
		if (existPieceEntre(i, j, iprec, jprec,tab[iprec][jprec]-1))
			return true;
		openConnection();
		PreparedStatement pstmt= conn.prepareStatement("SELECT ID_COULEUR FROM JRC WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_JOUEUR=?");
		pstmt.setInt(1, numPhase);
		pstmt.setInt(2, numPartie);
		pstmt.setInt(3,idJoueur);
		ResultSet couleurJoueur=pstmt.executeQuery();
		couleurJoueur.next();
		int couleur=couleurJoueur.getInt(1);
		if (tab[iprec][jprec]==0 || !identite(couleur==1,tab[iprec][jprec]>=17)){
			//System.out.println("Pas de piece, ou piece pas a moi");
			pstmt.close();
			closeConnection();
			return true;
		} else if (tab[iprec][jprec]>=8 && tab[iprec][jprec]<24 && i-iprec==0 && tab[i][j]!=0){
			//System.out.println("Probleme pion");
			pstmt.close();
			closeConnection();
			return true;
		}else if (tab[i][j]!=0 && identite(couleur==1,tab[i][j]>=17)){
			//System.out.println("Deplacement sur case occupé par pièce a moi");
			pstmt.close();
			closeConnection();
			return true;
		} else if (tab[iprec][jprec]>=8 && tab[iprec][jprec]<24 && i-iprec!=0 && tab[i][j]==0){
			//System.out.println("Probleme pion 2");
			pstmt.close();
			closeConnection();
			return true;
		}
		int numTour=0;
		try {
			pstmt=conn.prepareStatement("SELECT MAX(NUM_TOUR) FROM TOUR WHERE ID_PHASE=? AND ID_PARTIE=?");
			pstmt.setInt(1, numPhase);
			pstmt.setInt(2, numPartie);
			ResultSet rset2=pstmt.executeQuery();
			rset2.next();
			numTour=rset2.getInt(1);
			closeConnection();
		} catch (Exception e){
			//System.out.println("Surement le premier tour");
		}
		openConnection();
		if ((numTour+1)%2!=couleur){
			//System.out.println("C'est pas mon tour au faite !!");
			pstmt.close();
			closeConnection();
			return true;
		}
		try {
			pstmt=conn.prepareStatement("UPDATE PIECE SET X=?, Y=?,PREX=X ,PREY=Y " +
					"WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_PIECE=?");
			pstmt.setInt(1, i);
			pstmt.setInt(2, j);
			pstmt.setInt(3, numPhase);
			pstmt.setInt(4, numPartie);
			pstmt.setInt(5, tab[iprec][jprec]);
			pstmt.executeQuery();
			try {
				pstmt=conn.prepareStatement("INSERT INTO TOUR(NUM_TOUR, X, Y, ID_PHASE, ID_PARTIE, ID_PIECE) VALUES(?,?,?,?,?,?)");
				pstmt.setInt(1, numTour+1);
				pstmt.setInt(2, i);
				pstmt.setInt(3, j);
				pstmt.setInt(4, numPhase);
				pstmt.setInt(5, numPartie);
				pstmt.setInt(6, tab[iprec][jprec]);
				pstmt.executeQuery();
			} catch (Exception e) {
				//System.out.println("Insertion echec");
				//e.printStackTrace();
				Connection tmp=DriverManager.getConnection(CONN_URL, LOGIN, MDP);
				pstmt=tmp.prepareStatement("UPDATE PIECE SET X=?, Y=?,PREX=null ,PREY=null " +
						"WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_PIECE=?");
				pstmt.setInt(1, iprec);
				pstmt.setInt(2, jprec);
				pstmt.setInt(3, numPhase);
				pstmt.setInt(4, numPartie);
				pstmt.setInt(5, tab[iprec][jprec]);
				pstmt.executeQuery();
				pstmt.close();
				tmp.close();
				closeConnection();
				return true;
			}
			int idPieceASupprimer=tab[i][j];
			if (tab[i][j]!=0)
				piece[tab[i][j]]=null;			
			piece[tab[iprec][jprec]-1]=new Pos(i,j);
			tab[i][j]=tab[iprec][jprec];
			tab[iprec][jprec]=0;
			
			if (echec(idJoueur)){
				//System.out.println("Auto mise en echec");
				Connection tmp=DriverManager.getConnection(CONN_URL, LOGIN, MDP);
				pstmt=tmp.prepareStatement("UPDATE PIECE SET X=?, Y=?,PREX=null ,PREY=null " +
						"WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_PIECE=?");
				pstmt.setInt(1, iprec);
				pstmt.setInt(2, jprec);
				pstmt.setInt(3, numPhase);
				pstmt.setInt(4, numPartie);
				pstmt.setInt(5, tab[i][j]);
				pstmt.executeQuery();
				pstmt.close();
				tmp.close();
				closeConnection();
				return true;
			}
			//conn.commit();
			if (idPieceASupprimer!=0){
				pstmt=conn.prepareStatement("UPDATE PIECE SET X=null, Y=null, PREX=null, PREY=null " +
						"WHERE ID_PHASE=? AND ID_PARTIE=? AND ID_PIECE=?");
				pstmt.setInt(1, numPhase);
				pstmt.setInt(2, numPartie);
				pstmt.setInt(3,idPieceASupprimer);
				pstmt.executeQuery();		
			}
			pstmt.close();
			closeConnection();
			//System.out.println("Coup enregistré");
			return false;
		} catch (Exception e){
			//System.out.println("Deplacement impossible pour cette piece");
			//e.printStackTrace();
			pstmt.close();
			closeConnection();
			return true;
		}
	}
	
	public int getValEchiquier(int i,int j){
		if (i>=0 && i<=7 && i>=0 && j<=7)
			return tab[i][j];
		return 0;
	}
}
