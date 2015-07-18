package interface_IO;

import java.util.Scanner;
import fabrique.*;

public class JeuMenuJoueur extends InterfaceGraphique{
	private ProduitAbstrait p;
	private int numJoueur;
	public JeuMenuJoueur(ProduitAbstrait p,int i){
		numJoueur=i;
		phaseCourante=p;
	}
	
	@Override
	public void affiche() throws Exception {
		System.out.println("Selectionnez une partie:");
		System.out.println("0- Menu principal");

		f=new FabriqueRencontre();
		p=f.creer();
		p.chercheAdversaire(phaseCourante.getNumPhase(), numJoueur);
		System.out.println("Parties en cours possibles");
		while (p.adversaireHasNext()){
			System.out.println(p.adversaireNextidR() + "\t" + p.adversaireNextNom());
		}
	}

	@Override
	public InterfaceGraphique get() throws Exception {
		Scanner sc = new Scanner(System.in);	
		int val;
		try {
			val=sc.nextInt();
		} catch (Exception e){
			System.out.println("Commande inconnue");
			return this;
		}
		switch (val){
		case 0: return new JeuMenuPrincipal(phaseCourante); 
		default: 
			if (!p.existRencontre(val)){
				System.out.println("Cette partie n'existe pas");
				return this;
			}
			return new JeuMenuPartie(phaseCourante,numJoueur,val);
		}
	}
}

