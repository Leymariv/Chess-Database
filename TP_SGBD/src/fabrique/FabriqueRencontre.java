package fabrique;

public class FabriqueRencontre implements FabriqueAbstraite{

	@Override
	public ProduitAbstrait creer() throws Exception {
		return new ProduitRencontre();
	}

	private static final Exception FabriqueRencontreErreur = null;
	public ProduitAbstrait creer(int i,int j) throws Exception {
		throw FabriqueRencontreErreur;
	}

}
