package interface_IO;
import java.util.Scanner;
import fabrique.*;

public class InscriptionMenuPrincipal extends InterfaceGraphique {

	public InscriptionMenuPrincipal(ProduitAbstrait p){
		phaseCourante=p;
	}
	
	public void affiche(){
		System.out.println("MENU PRINCIPAL - Actions disponibles");
		System.out.println("1- S'inscrire");
		System.out.println("2- Menu Organisation");
		System.out.println("0- Quitter");
	}
	
	public InterfaceGraphique get() throws Exception{
		Scanner sc = new Scanner(System.in);
		int val;
		try{
			val=sc.nextInt();
		} catch (Exception e) {
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
			case 0: return null;
			case 1: 
				if (p.phaseInscription()){
					return new InscriptionMenuInscription(phaseCourante); 
				} else {
					System.err.println("Problème interne, phase inscription attendue...");
					return this;
				}
			case 2: return new InscriptionMenuOrganisation(phaseCourante); 
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
