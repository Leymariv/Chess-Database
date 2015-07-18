package fabrique;

public class FabriqueEchiquier implements FabriqueAbstraite{

	@Override
	public ProduitAbstrait creer(int i, int j) throws Exception {		
		return new ProduitEchiquier(i, j);
	}

	private static final Exception FabriqueEchiquierErreur = null;
	public ProduitAbstrait creer() throws Exception {
		throw FabriqueEchiquierErreur;
	}
}
