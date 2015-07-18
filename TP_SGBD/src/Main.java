import fabrique.*;
import interface_IO.*;
import java.sql.*;

public class Main {

	/**
	 * @pa1ram args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//Chargement du driver
		try {
		 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (Exception e){
			System.err.println("DRIVER LOADED FAILED");
			e.printStackTrace(System.err);
		}
		
		FabriqueAbstraite f= new FabriquePhase();
		ProduitAbstrait p=f.creer();
		p.openConnection();
		p.getPhase();
		p.closeConnection();
		InterfaceGraphique I;
		if (!p.phaseJeu() && ! p.phaseFini())
			I= new InscriptionMenuPrincipal(p);
		else
			I=new JeuMenuPrincipal(p);
		while (I!=null){
			I.affiche();
			I=I.get();
		}
		System.out.println("Merci de votre visite! À plus tard!");
	}
}
