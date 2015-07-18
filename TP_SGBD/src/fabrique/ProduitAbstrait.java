package fabrique;

import java.sql.*;

public abstract class ProduitAbstrait {
	protected String CONN_URL="jdbc:oracle:thin:@localhost:1521:ensi2";
	protected String LOGIN="jodinr";
	protected String MDP="jodinr";
	protected Connection conn;
	private static final Exception ProduitErreur = null;

	
	public Connection getConnection(){
		return conn;
	}
	public void setConnection(Connection conn){
		this.conn=conn;
	}
	public void openConnection() throws Exception{
		conn=DriverManager.getConnection(CONN_URL, LOGIN, MDP);
	}
	public void serialConnection() throws Exception{
		conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	}
	public void notSerialConnection() throws Exception{
		conn.commit();
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		conn.setAutoCommit(true);
	}
	public void closeConnection() throws Exception{
		conn.close();
	}
	//ProduitPhase
	public void getPhase() throws Exception {
		throw ProduitErreur;
	}
	public boolean phaseInscription() throws Exception{
		throw ProduitErreur;
	}
	public boolean phaseJeu() throws Exception{
		throw ProduitErreur;
	}
	public boolean phaseFini() throws Exception{
		throw ProduitErreur;
	}
	public void phaseSuivante() throws Exception{
		throw ProduitErreur;
	}
	public int getNumPhase() throws Exception{
		throw ProduitErreur;
	}
	
	//Produit Echiquier
	public int getValEchiquier(int i, int j) throws Exception {
		throw ProduitErreur;
	}
	public boolean jouerCoup(int iprec, int jprec, int i, int j, int idJoueur) throws Exception {
		throw ProduitErreur;
	}
	public boolean echec(int numJoueur)throws Exception {
		throw ProduitErreur;
	}
	public boolean echecmat(int numJoueur)throws Exception {
		throw ProduitErreur;
	}
	public void changePiece(int phase, int rencontre, int tour) throws Exception {
		throw ProduitErreur;
	}
	
	//ProduitListeInscrit
	public void parcoursListeInscrit() throws Exception {
		throw ProduitErreur;
	}
	public boolean inscritHasNext() throws Exception {
		throw ProduitErreur;
	}
	public String inscritNextNom() throws Exception {
		throw ProduitErreur;
	}
	public int inscritNextNum() throws Exception{
		throw ProduitErreur;
	}
	public void ajouteInscrit(String nom,String prenom,String adresse,String date) throws Exception {
		throw ProduitErreur;
	}
	public void validerInscrit(int i) throws Exception {
		throw ProduitErreur;
	}
	
	//ProduitTabScore
	public void ParcoursTabScore() throws Exception {
		throw ProduitErreur;
	}
	public boolean TabScoreHasNext() throws Exception{
		throw ProduitErreur;
	}
	public String TabScoreNextNom() throws Exception{
		throw ProduitErreur;
	}
	public int TabScoreNextnbG() throws Exception{
		throw ProduitErreur;
	}
	public int TabScoreNextnbJ() throws Exception{
		throw ProduitErreur;
	}
	public int TabScoreNextNum() throws Exception{
		throw ProduitErreur;
	}
	public int TabScoreGetNum(int i) throws Exception {
		throw ProduitErreur;
	}
	
	//ProduitListejoueur
	public void ParcoursListeJoueur() throws Exception {
		throw ProduitErreur;
	}
	public boolean JoueurHasNext() throws Exception{
		throw ProduitErreur;
	}
	public String JoueurNextNom() throws Exception{
		throw ProduitErreur;
	}
	public int JoueurNextNum() throws Exception{
		throw ProduitErreur;
	}
	public boolean JoueurExist(int i) throws Exception {
		throw ProduitErreur;
	}
	public int nbJoueur() throws Exception {
		throw ProduitErreur;
	}
	
	//ProduitRencontre
        public int maCouleur(int idPhase, int idPartie,int idJoueur) throws Exception {
	        throw ProduitErreur;
        }
	public boolean existRencontre(int i) throws Exception {
		throw ProduitErreur;
	}
	public boolean partieFini(int idPhase, int idPartie) throws Exception {
		throw ProduitErreur;
	}
	public void creerCouleur() throws Exception {
		throw ProduitErreur;
	}
	public String adversaireNextNomJ() throws Exception {
		throw ProduitErreur;
	}
	public int adversaireNextidJ() throws Exception {
		throw ProduitErreur;
	}
	public void chercheAdversaire(int idPhase,int idJoueur) throws Exception {
		throw ProduitErreur;
	}
	public boolean adversaireHasNext() throws Exception {
		throw ProduitErreur;
	}
	public void creerRencontre(int idPhase, int idRencontre, int idJoueur1, int idJoueur2) throws Exception {
		throw ProduitErreur;
	}
	public String adversaireNextNom() throws Exception {
		throw ProduitErreur;
	}
	public int adversaireNextidA() throws Exception {
		throw ProduitErreur;
	}
	public int adversaireNextidR() throws Exception {
		throw ProduitErreur;
	}
	public void chercheAllRencontre(int idPhase) throws Exception {
		throw ProduitErreur;
	}
	public boolean monTour(int idPhase,int idPartie, int idJoueur)throws Exception {
		throw ProduitErreur;
	}
	public boolean adDemandeRejouer(int idPhase,int idPartie, int idJoueur)throws Exception {
		throw ProduitErreur;
	}
	public void EstFini(int idPhase,int idPartie, int idJoueur)throws Exception {
		throw ProduitErreur;
	}
	public void rejouer(int idPhase,int idPartie, int idJoueur)throws Exception {
		throw ProduitErreur;
	}
	public void demandeRejouer(int idPhase,int idPartie, int idJoueur,int b)throws Exception {
		throw ProduitErreur;
	}
}

