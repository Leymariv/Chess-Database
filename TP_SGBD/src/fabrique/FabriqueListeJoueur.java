package fabrique;

public class FabriqueListeJoueur implements FabriqueAbstraite{

	@Override
	public ProduitAbstrait creer() throws Exception {
		return new ProduitListeJoueur();
	}

	private static final Exception FabriqueListeJoueurErreur = null;
	public ProduitAbstrait creer(int i,int j) throws Exception {
		throw FabriqueListeJoueurErreur;
	}

}
