package interface_IO;
import fabrique.*;

public class Echiquier {
	private void print(String s, int i, int j){
		if (i%2==j%2 )
			System.out.print("/" + s + "/");
		else 
			System.out.print(" " + s + " ");
	}
	public void affichage(ProduitAbstrait p) throws Exception{	
		System.out.println("    1    2    3    4    5    6    7    8");
		System.out.println("  +----+----+----+----+----+----+----+----+");
		for (int i=0; i<8; i++){
			System.out.print((i+1) + " |");
			for (int j=0; j<8;j++){
				if (p.getValEchiquier(i, j)>=9 && p.getValEchiquier(i, j)<=16){
					print("♟ ",i,j);
				} else if (p.getValEchiquier(i, j)>=17 && p.getValEchiquier(i, j)<=24){
					print("♙ ",i,j);
				} else if (p.getValEchiquier(i, j)==1 || p.getValEchiquier(i, j)==8){
					print("♜ ",i,j);
				} else if (p.getValEchiquier(i, j)==25 || p.getValEchiquier(i, j)==32){
					print("♖ ",i,j);
				} else if (p.getValEchiquier(i, j)==2 || p.getValEchiquier(i, j)==7){
					print("♞ ",i,j);
				} else if (p.getValEchiquier(i, j)==26 || p.getValEchiquier(i, j)==31){
					print("♘ ",i,j);
				} else if (p.getValEchiquier(i, j)==3 || p.getValEchiquier(i, j)==6){
					print("♝ ",i,j);
				} else if (p.getValEchiquier(i, j)==27 || p.getValEchiquier(i, j)==30){
					print("♗ ",i,j);
				} else if (p.getValEchiquier(i, j)==4){
					print("♚ ",i,j);
				} else if (p.getValEchiquier(i, j)==29){
					print("♔ ",i,j);
				} else if (p.getValEchiquier(i, j)==5){
					print("♛ ",i,j);
				} else if (p.getValEchiquier(i, j)==28){
					print("♕ ",i,j);
				} else {
					if (i%2==j%2 )
						System.out.print("////");
					else 
						System.out.print("    ");
				}
				System.out.print("|");
			}
			System.out.println(" ");
			System.out.println("  +----+----+----+----+----+----+----+----+");
		}
	}
}
