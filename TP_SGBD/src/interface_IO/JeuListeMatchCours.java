package interface_IO;

import java.util.Scanner;
import fabrique.*;

public class JeuListeMatchCours extends InterfaceGraphique{
	private int phase,rencontre;
	
	public JeuListeMatchCours(ProduitAbstrait p,int phase,int rencontre) {
		phaseCourante=p;
		this.phase=phase;
		this.rencontre=rencontre;
	}

	public void affiche() throws Exception{
		f=new FabriqueEchiquier();
		ProduitAbstrait p=f.creer(rencontre,phase);
		Echiquier e=new Echiquier();
		e.affichage(p);
	
		System.out.println("Actions disponibles");
		System.out.println("1- Actualiser");
		System.out.println("0- Retour au menu des rencontres");
	}
	
	public InterfaceGraphique get() throws Exception{
		f=new FabriqueRencontre();
		ProduitAbstrait p=f.creer();
		if (p.partieFini(phase, rencontre)){
			System.out.println("La partie est finie");
			return new JeuMenuDesMatch(p);
		}
		Scanner sc = new Scanner(System.in);
		int val;
		try {
			val=sc.nextInt();
		} catch (Exception e){
			System.out.println("Commande inconnue");
			return this;
		}
		switch (val){
		case 0: return new JeuMenuDesMatch(phaseCourante); 
		case 1: return this;
		default:
			System.out.println("Commande inconnue");
			return this;
		}
	}
}

