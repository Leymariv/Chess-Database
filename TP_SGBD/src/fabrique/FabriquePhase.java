package fabrique;

public class FabriquePhase implements FabriqueAbstraite {

	@Override
	public ProduitAbstrait creer() throws Exception{
		return new ProduitPhase();
	}

	private static final Exception FabriquePhaseErreur = null;
	public ProduitAbstrait creer(int i,int j) throws Exception {
		throw FabriquePhaseErreur;
	}

}
