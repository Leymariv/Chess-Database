package interface_IO;

import java.util.Scanner;
import fabrique.*;

public class JeuListeJoueurs extends InterfaceGraphique{
	
	ProduitAbstrait listeJ;
	public JeuListeJoueurs(ProduitAbstrait p) throws Exception{
		phaseCourante=p;
		f=new FabriqueListeJoueur();
		listeJ=f.creer();
	}

	@Override
	public void affiche() throws Exception {
		System.out.println("Selectionnez un joueur:");
		System.out.println("0- Retour au menu principal");
		System.out.println("Listes des joueurs :");
		listeJ.ParcoursListeJoueur();
		while (listeJ.JoueurHasNext()){
			System.out.println(listeJ.JoueurNextNum() + " \t" + listeJ.JoueurNextNom());
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
			if (listeJ.JoueurExist(val))
				return new JeuMenuJoueur(phaseCourante,val);
			else {
				System.out.println("Veuillez choisir un joueur existant");
				return this;
			}
		}
	}
}
