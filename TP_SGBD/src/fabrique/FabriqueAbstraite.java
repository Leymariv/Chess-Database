package fabrique;

public interface FabriqueAbstraite {

    public ProduitAbstrait creer() throws Exception;
    public ProduitAbstrait creer(int i,int j) throws Exception;

}