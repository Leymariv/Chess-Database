package interface_IO;

import java.util.Scanner;

import fabrique.*;

public class JeuListeMatchDebut extends InterfaceGraphique{
	private int phase,rencontre;
	private int tour=0;
	ProduitAbstrait p;
	public JeuListeMatchDebut(ProduitAbstrait p,int phase,int rencontre) throws Exception{
		phaseCourante=p;
		this.phase=phase;
		this.rencontre=rencontre;
		FabriqueAbstraite f=new FabriqueEchiquier();
		this.p=f.creer(0,0);
	}
	
	public void affiche() throws Exception{
		Echiquier e= new Echiquier();
		e.affichage(this.p);
	
		System.out.println("Actions disponibles");
		System.out.println("1- Coup suivant");
		System.out.println("0- Retour au menu des rencontres");
	}
	
	public InterfaceGraphique get() throws Exception{
		Scanner sc = new Scanner(System.in);
		int val;
		try {
			val=sc.nextInt();
		} catch (Exception e){
			System.out.println("Commande inconnue");
			return this;
		}
		f=new FabriquePhase();
		ProduitAbstrait p=f.creer();
		p.openConnection();
		p.getPhase();
		p.closeConnection();
		if (phaseCourante.equals(p)){
			switch (val){
			case 0: return new JeuMenuDesMatch(p); 
			case 1: 
				try {
					this.p.changePiece(phase, rencontre, tour+1);
					tour++;
					return this;
				} catch (Exception e) {
					System.out.println("Vous êtes arrivé à l'état courant de la partie");
					return new JeuListeMatchCours(p, phase, rencontre);
				}		
			default:
				System.out.println("Commande inconnue");
				return this;
			}
		} else {
			System.out.println("Demande annulée, un organisateur a lancé la phase de jeu suivante...");
			return new JeuMenuPrincipal(p);
		}
	}
}

