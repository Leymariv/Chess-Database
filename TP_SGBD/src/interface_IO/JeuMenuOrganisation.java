package interface_IO;

import java.util.Scanner;
import fabrique.*;

public class JeuMenuOrganisation extends InterfaceGraphique{

	public JeuMenuOrganisation(ProduitAbstrait p){
		phaseCourante=p;
	}
	
	public void affiche() throws Exception {
		f= new FabriqueTabScore();
		ProduitAbstrait p=f.creer(phaseCourante.getNumPhase(),0);
		p.ParcoursTabScore();
		System.out.println("TABLEAU DES SCORES :\nNom\t\tParties Gagnées\tParties Jouées");
		while (p.TabScoreHasNext()){
			System.out.println(p.TabScoreNextNom() + "\t\t" + p.TabScoreNextnbG() + "\t" + p.TabScoreNextnbJ());
		}
		System.out.println("MENU ORGANISATION - Actions disponibles");
		System.out.println("1- Avancer à la phase suivante");
		System.out.println("0- Retour au menu principal");
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
		f=new FabriquePhase();
		ProduitAbstrait p=f.creer();
		p.openConnection();
		p.getPhase();
		p.closeConnection();
		if (phaseCourante.equals(p)){
			switch (val){
			case 1:
				if (!p.phaseFini()){
					f=new FabriqueTabScore();
					ProduitAbstrait tabScore=f.creer(p.getNumPhase(),0);
					p.openConnection();
					p.serialConnection();
					p.getPhase();
					if (!p.equals(phaseCourante)){
						System.out.println("Demande annulée, un organisateur a lancé la phase de jeu suivante...");
						p.closeConnection();
						return new JeuMenuPrincipal(p);
					}
					p.phaseSuivante();
					if (!p.phaseFini()){	
					f=new FabriqueRencontre();
					ProduitAbstrait ren=f.creer();
					ren.setConnection(p.getConnection());				
					int max=(p.getNumPhase()==2?4:(p.getNumPhase()==3?2:1));
					for (int i=1;i<=max;i++){
						ren.creerRencontre(p.getNumPhase(), i, tabScore.TabScoreGetNum(i-1), tabScore.TabScoreGetNum(2*max-i));
					}
					}
					p.notSerialConnection();
					p.closeConnection();
				} else {
					System.err.println("Le tournoi est déjà fini...");
					return this;
				}
				return new JeuMenuPrincipal(p);
			case 0: return new JeuMenuPrincipal(phaseCourante);
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
