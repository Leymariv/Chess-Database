package fabrique;

public class FabriqueTabScore implements FabriqueAbstraite{

	private static final Exception FabriqueTabScoreErreur = null;
	@Override
	public ProduitAbstrait creer(int i, int j) throws Exception {		
		return new ProduitTabScore(i);
	}

	
	public ProduitAbstrait creer() throws Exception{
		throw FabriqueTabScoreErreur;
	}
}