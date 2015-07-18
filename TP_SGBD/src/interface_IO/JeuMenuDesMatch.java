package interface_IO;
import java.util.Scanner;

import fabrique.*;

public class JeuMenuDesMatch extends InterfaceGraphique {
	
	private ProduitAbstrait listeRencontre;
    public JeuMenuDesMatch(ProduitAbstrait p) throws Exception{
    	f=new FabriqueRencontre();
    	listeRencontre=f.creer();
    	phaseCourante=p;
    }

    private int recuperePhase(){
    	System.out.println("Veuillez choisir la phase");
    	Scanner sc = new Scanner(System.in);
    	return sc.nextInt();
    }
    private int recuperePartie(int idPhase) throws Exception{
    	System.out.println("Veuillez choisir la partie");
    	listeRencontre.chercheAllRencontre(idPhase);
    	while (listeRencontre.adversaireHasNext()){
    		System.out.println(listeRencontre.adversaireNextidR() + "\t" + listeRencontre.adversaireNextNomJ()+"\t"+listeRencontre.adversaireNextNom());
    	}
    	Scanner sc = new Scanner(System.in);
    	return sc.nextInt();
    }
    
    public void affiche(){
    	System.out.println("Actions disponibles");
    	System.out.println("1- Regarder une rencontre depuis le début");
    	System.out.println("2- Regarder une rencontre où elle en est");
    	System.out.println("0- Menu Principal");
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
    	int pha,ren;
    	switch (val){
    	case 0: return new JeuMenuPrincipal(phaseCourante); 
    	case 1: 
    		try{
    			pha=recuperePhase();
    			ren=recuperePartie(pha);
    		} catch (Exception e){
    			System.out.println("Erreur de saisi");
    			return this;
    		}
    		if (listeRencontre.existRencontre(ren))
    			return new JeuListeMatchDebut(phaseCourante,pha,ren); 
    		System.out.println("Cette rencontre n'existe pas");
    		return this;
    	case 2: 
    		try {
    			pha=recuperePhase();
    			ren=recuperePartie(pha);
    		} catch (Exception e){
    			System.out.println("Erreur de saisi");
    			return this;
    		}
    		if (listeRencontre.existRencontre(ren))
    			return new JeuListeMatchCours(phaseCourante,pha,ren); 
    		System.out.println("Cette rencontre n'existe pas");
    		return this;
    	default:
    		System.out.println("Commande inconnue");
    		return this;
    	}
    }
}
