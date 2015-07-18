package fabrique;

public class FabriqueListeInscrit implements FabriqueAbstraite{

	@Override
	public ProduitAbstrait creer() throws Exception {
		return new ProduitListeInscrit();
	}

	private static final Exception FabriqueListeInscritErreur = null;
	public ProduitAbstrait creer(int i, int j) throws Exception {
		throw FabriqueListeInscritErreur;
	}

}
