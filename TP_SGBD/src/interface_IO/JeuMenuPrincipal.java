package interface_IO;
import java.util.Scanner;
import fabrique.*;

public class JeuMenuPrincipal extends InterfaceGraphique {
	
	public JeuMenuPrincipal(ProduitAbstrait p){
		phaseCourante=p;
	}

	public void affiche(){
		System.out.println("MENU PRINCIPAL - Actions disponibles");
		System.out.println("1- Regarder une rencontre");
		System.out.println("2- Consulter la liste des joueurs");
		System.out.println("3- Menu Organisation");
		System.out.println("0- Quitter");
		
	}
	
	public InterfaceGraphique get() throws Exception{
		Scanner sc = new Scanner(System.in);
		int val ;
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
			case 1: return new JeuMenuDesMatch(p); 
			case 2: return new JeuListeJoueurs(p); 
			case 3: return new JeuMenuOrganisation(phaseCourante);
			case 0: return null;
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
