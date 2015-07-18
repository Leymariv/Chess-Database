package interface_IO;

import fabrique.*;

import java.util.Scanner;

public class InscriptionMenuOrganisation extends InterfaceGraphique{
	
	private ProduitAbstrait listeInscrit;
	public InscriptionMenuOrganisation(ProduitAbstrait p) throws Exception {
		phaseCourante=p;
	}
	
	@Override
	public void affiche() throws Exception {
		f= new FabriqueListeInscrit();
		listeInscrit=f.creer();
		System.out.println("Liste des demandes d'inscriptions :");
		listeInscrit.parcoursListeInscrit();
		while (listeInscrit.inscritHasNext()){
			System.out.println(listeInscrit.inscritNextNum() + "\t" + listeInscrit.inscritNextNom());
		}
		System.out.println(" ");
		System.out.println("MENU ORGANISATION - Actions disponibles");
		System.out.println("1- Valider une inscription");
		System.out.println("2- Cloturer la phase d'inscription");
		System.out.println("0- Retour au menu principal");
		
    }

	public InterfaceGraphique pSuiv(ProduitAbstrait p) throws Exception{
		try{
		f=new FabriqueListeJoueur();
		ProduitAbstrait p1=f.creer();
		if (p1.nbJoueur()>=8){
			ProduitAbstrait p2=f.creer();
			f=new FabriqueRencontre();
			ProduitAbstrait Rencontre=f.creer();
			p1.ParcoursListeJoueur();
			int increm=1;	
			Rencontre.creerCouleur();
			p.setConnection(Rencontre.getConnection());
			Rencontre.serialConnection();
			p.getPhase();
			if (p.phaseInscription()){
				p.phaseSuivante();
				Rencontre.notSerialConnection();
				while (p1.JoueurHasNext()){
					p2.ParcoursListeJoueur();
					while (p2.JoueurHasNext() && p1.JoueurNextNum()!=p2.JoueurNextNum()){
						if (increm%2==0)
							Rencontre.creerRencontre(1,increm,p1.JoueurNextNum(),p2.JoueurNextNum());
						else
							Rencontre.creerRencontre(1,increm,p2.JoueurNextNum(),p1.JoueurNextNum());
						increm++;
					}
				}			
				Rencontre.closeConnection();
				return new JeuMenuPrincipal(p);
			} else {
				System.err.println("Erreur de phase, une personne a déjà dû demander de passer à la phase suivante");
				return this;
			}
		} else {
			System.out.println("Il faut un minimum de 8 joueurs pour cloturer la phase d'inscription");
			return this;
		}	
		}catch (Exception e){
			System.out.println("Erreur, retour au menu organisation");
			return this;
		}
	}

	public InterfaceGraphique get() throws Exception {
		Scanner sc = new Scanner(System.in);	
		int val;
		try{
			val=sc.nextInt();
		} catch (Exception e) {
			System.out.println("Commande inconnue");
			return this;
		}
		f= new FabriquePhase();
		ProduitAbstrait p=f.creer();
		p.openConnection();
		p.getPhase();
		p.closeConnection();
		if (phaseCourante.equals(p)){
			switch (val){
			case 0: return new InscriptionMenuPrincipal(p);
			case 1: 
				System.out.println("Numéro de l'inscription à valider"); 
				try {
					int i=sc.nextInt();
					listeInscrit.validerInscrit(i);
				} catch (Exception e){
					System.out.println("Numéro invalide");
				}
				return this;
			case 2: 
				return pSuiv(p);
			default:
				System.out.println("Commande inconnue");
				return this;
			}
		} else {
			System.out.println("Demande annulée, un organisateur a lancé la phase de jeu...");
			return new JeuMenuPrincipal(p);
		}
	}
}
