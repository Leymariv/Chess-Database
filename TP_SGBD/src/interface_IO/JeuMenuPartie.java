package interface_IO;

import java.util.Scanner;
import fabrique.*;

public class JeuMenuPartie extends InterfaceGraphique{
	int numJoueur=0;
	int numPartie=0;
	ProduitAbstrait e,ren;
	
	boolean monTour,demandeRejoue;
	
	public JeuMenuPartie(ProduitAbstrait pr,int j,int p) throws Exception{
		numJoueur=j;
		numPartie=p;
		phaseCourante=pr;
		FabriqueAbstraite f=new FabriqueRencontre();
		this.ren=f.creer();
		
	}
	@Override
	public void affiche() throws Exception {
		ren.openConnection();
		ren.serialConnection();
		monTour= ren.monTour(phaseCourante.getNumPhase(),numPartie,numJoueur);
		demandeRejoue=ren.adDemandeRejouer(phaseCourante.getNumPhase(),numPartie,numJoueur);
		int couleur = ren.maCouleur(phaseCourante.getNumPhase(),numPartie,numJoueur);
		ren.notSerialConnection();
		ren.closeConnection();
		if (couleur==0)
		    System.out.println("Vous êtes les noirs");
		else
		    System.out.println("Vous êtes les blancs");
		System.out.println("Liste des actions disponibles:");
		FabriqueAbstraite f=new FabriqueEchiquier();
		this.e=f.creer(numPartie,phaseCourante.getNumPhase());
		Echiquier e=new Echiquier();
		e.affichage(this.e);
		if (monTour){		
			System.out.println("1- Jouer un coup");
			System.out.println("2- Demander à rejouer la partie");
			System.out.println("3- Abandonner la partie");
			System.out.println("0- Retour au menu du Joueur");		
		} else {
			if (demandeRejoue){
				System.out.println("1- Refuser de rejouer la partie");
				System.out.println("2- Accepter de rejouer la partie");
			}
		}
	}

	private InterfaceGraphique jouerUnCoup() throws Exception{
		System.out.println("Veuillez entrer votre coup ainsi : 2345 " +
				   "\n2: ligne de la piece à déplacer "+
				   "\n3: colonne de la piece à déplacer" +
				   "\n4: ligne à laquelle la pièce va être deplacée "+
				   "\n5: colonne à laquelle la pièce va être deplacée");
		Scanner sc= new Scanner(System.in);
		int val;
		try {
			val=sc.nextInt();
		} catch (Exception e) {
			System.out.println("Commande inconnue");
			return this;
		}
		if (val<=8888 && val>=1111){
			if (val/1000==0 || val/1000==9 ||
			    (val-(val/1000)*1000)/100==0 ||(val-(val/1000)*1000)/100==9 ||
			    (val-(val/100)*100)/10==0 || (val-(val/100)*100)/10==9 ||
			    val-(val/10)*10==0 || val-(val/10)*10==9 ||
			    e.jouerCoup(val/1000-1,(val-(val/1000)*1000)/100-1,(val-(val/100)*100)/10-1,val-(val/10)*10-1,numJoueur)){
				System.out.println("Coup Invalide...");
			}
		} else {
			System.out.println("Coup invalide...");
		}
		return this;
	}
	
	@Override
	public InterfaceGraphique get() throws Exception {
		if ((monTour && demandeRejoue) || (!monTour && !demandeRejoue)){
			System.out.println("Ce n'est pas à vous de jouer dans cette partie, veuillez en choisir une autre.");
			return new JeuMenuJoueur(phaseCourante,numJoueur);
		}
		if(e.echec(numJoueur)){
			System.out.println("Vous êtes en echec");
			if (e.echecmat(numJoueur)){
				System.out.println("Vous êtes MAT");
				ren.EstFini(phaseCourante.getNumPhase(),numPartie,numJoueur);
				return new JeuMenuJoueur(phaseCourante,numJoueur);
			}
		}	
		Scanner sc = new Scanner(System.in);
		int val;
		try {
			val=sc.nextInt();
		} catch (Exception e) {
			System.out.println("Commande inconnue");
			return this;
		}
		FabriqueAbstraite f=new FabriquePhase();
		ProduitAbstrait p=f.creer();
		p.openConnection();
		p.getPhase();
		p.closeConnection();
		if (phaseCourante.equals(p)){
			switch (val){
			case 1: 
				if (demandeRejoue)
					ren.demandeRejouer(phaseCourante.getNumPhase(),numPartie,numJoueur,0);
				else
					return jouerUnCoup(); 
				return new JeuMenuJoueur(p,numJoueur);
			case 2: 
				if (demandeRejoue) {
					ren.rejouer(phaseCourante.getNumPhase(),numPartie,numJoueur);
					ren.demandeRejouer(phaseCourante.getNumPhase(),numPartie,numJoueur,0);
				}
				else
					ren.demandeRejouer(phaseCourante.getNumPhase(),numPartie,numJoueur,1);
				return new JeuMenuJoueur(p,numJoueur);
			case 3: 
				if (monTour){
					System.out.println("Abandon du joueur, partie finie ");
					ren.EstFini(phaseCourante.getNumPhase(),numPartie,numJoueur);
					return new JeuMenuJoueur(p,numJoueur); 
				} else {
					System.out.println("Commande inconnue");
					return this;
				}
			case 0: return new JeuMenuJoueur(p,numJoueur); 
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
