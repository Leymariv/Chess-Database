package interface_IO;
import fabrique.*;


public abstract class InterfaceGraphique {
	
	protected ProduitAbstrait phaseCourante;
	protected FabriqueAbstraite f;
	public abstract void affiche() throws Exception;
	public abstract InterfaceGraphique get() throws Exception; 
	
}
