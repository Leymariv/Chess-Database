package interface_IO;

import fabrique.*;
import java.util.Scanner;

public class InscriptionMenuInscription extends InterfaceGraphique{
	
	public String nom, prenom, adresse, dateNaissance;
	public InscriptionMenuInscription(ProduitAbstrait p){
		phaseCourante=p;
	}
	
	public void affiche(){
		System.out.println("Actions disponibles");
		System.out.println("1- S'inscrire");
		System.out.println("0- Retour au menu principal");
	}
	
	public InterfaceGraphique get() throws Exception{
		Scanner sc= new Scanner(System.in);
		try {
			switch (sc.nextInt()){
			case 0: return new InscriptionMenuPrincipal(phaseCourante);
			case 1:			
				sc= new Scanner(System.in);
				System.out.println("Bonjour! Veuillez Renseigner le formulaire ci-dessous");
				System.out.println("Nom:");
				nom = sc.nextLine();
				System.out.println("Prénom:");
				prenom = sc.nextLine();
				System.out.println("Adresse:");
				adresse = sc.nextLine();
				System.out.println("Date:");
				dateNaissance = sc.nextLine();
				System.out.println("Formulaire complété! Retour au menu principal.");
			
				f=new FabriqueListeInscrit();
				ProduitAbstrait p=f.creer();
				p.ajouteInscrit(nom,prenom,adresse,dateNaissance);
			
				return new InscriptionMenuPrincipal(phaseCourante);
			default : 
				System.out.println("Commande inconnue");
				return this;
			}
		} catch (Exception e) {
			System.out.println("Erreur, inscription annulée");
			//e.printStackTrace();
			return new InscriptionMenuPrincipal(phaseCourante);
		}
	}
}
