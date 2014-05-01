import java.util.Random;


/**
 * Classe principale 
 */
public class Triominos {
	
	public static void main(String args[]) {
		System.out.println("Jeu des triominos");
	
		Random generator = new Random();
		
		/** base encodage des caractère (de 2 à 16) */
		int base =  2 + Math.abs(generator.nextInt()%15);
		/** taille du triangle (pyramide) du plateau (de 1 à 6) */
		int size =  1 + Math.abs(generator.nextInt()%6);
		
		Jeu jeu = new Jeu(size, base);
		Plateau p = new Plateau(size);
		jeu.affiche();

		/*  placer les triominos sur le plateau.
		 *    on ne résout pas le problème ici
		 */ 

		// place les triominos de notre jeu sur le plateau mais 
		// de facon désordonnée :
		int k = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < 2*i+1; j++) {
				p.set(i,j,jeu.get(k));
				k++;
			}
		}

		affiche_plateau_mini(p);
	}
	
	
	/**
	 * Methode implantant l'algorithme de résolution du jeu de triominos
	 * @param jeu_trio Notre jeu de triominos
	 * @param plateau Reférence au plateau de triominos
	 * @param pos Position du triomino dans le plateau
	 * @param largeur Largeur du plateau de triominos
	 * @return True si trouve une solution, false sinon
	 */
	public boolean resoudre(Jeu jeu_trio, Plateau plateau, int pos, int largeur) {
		int next_pos = next(pos, largeur);
		
		if( next_pos == -1 ) { // fin
			return true;
		}
		else {
			// on parcours notre jeu de triominos :
			int num_trio = 0;
			for(int num_col=0; num_col<largeur; num_col++) {
				for(int num_ligne=0; num_ligne<2*num_col+1; num_ligne++) { 
					
					// on enlève de notre jeu le triomino en cours :
					jeu_trio.get(num_trio).setPlace(true);
					
					// on essaye de le placer dans les 3 sens possibles :
					for(int r=0; r<3; r++) {
						
						// si les contraintes avec les voisins sont respectées :
						if( contraintes(jeu_trio.get(num_trio), plateau, num_col, num_ligne) ) {
							
							// on place le triomino sur le plateau :
							plateau.set(num_col, num_ligne, jeu_trio.get(num_trio));
							
							// appel recursif :
							if( resoudre(jeu_trio, plateau, next_pos, largeur) ) 
								return true;
							else plateau.set(num_col, num_ligne, null);
							
						}
						jeu_trio.get(num_trio).tourner();
					}
					
					// si le triomino ne passe pas, on le remet dans notre jeu :
					jeu_trio.get(num_trio).setPlace(false);
					
					num_trio++;
				}
			}
		}
		
		return false;
	}

	
	/**
	 * Methode qui donne la position du triomino suivant
	 * @param pos position actuelle
	 * @param largeur largeur du plateau 
	 * @return position suivante ou -1 si c'est la fin
	 */
	private int next(int pos, int largeur) {
		if( pos < largeur*largeur )
			return pos+1;
		else return -1;
  }
	


	/**
	 * Methode  qui verifie les contraintes (si le triomino s'accorde avec les 
	 * voisins ou non.
	 * @param jeu_trio Le triomino en question
	 * @param pos La position actuelle sur le plateau
	 * @param plateau Le plateau de triominos
	 * @return true si on peut le placer dans ce sens, false sinon
	 */
	private boolean contraintes(Triomino trio, Plateau plateau, int num_col, int num_ligne) {
	  // TODO Auto-generated method stub
	  return false;
  }



	
	
	/**  
	 * conversion d'un entier positif (<62) en caractere [0-9A-Za-z]
	 */
	static char onechar(int value){
		if (value >= 0) {
			if (value < 10) 
				return (char) ('0'+value);
			else
				if (value < 36)
					return (char) ('A'+value-10);
				else
					if (value < 62)
						return (char) ('a'+value-36);
		}
		return '\0';
	}

	
	static char charorstar(Triomino t,int c) {
		if (t!=null)
			return (onechar(c));
		else return('*');
	}


	/**
	 * affiche un plateau, meme partiellement rempli.
	 */
	static void affiche_plateau_mini(Plateau p) {
		for (int i = 0 ; i < p.largeur ; i++) {
			for (int l=0; l<3; l++) {      
				int k;
				for(k=0 ; k<p.largeur-i;k++) System.out.print("   ");
				if (l%2>0) System.out.print(" ");

				for (int j=0 ; j <= 2*i ; j++ ){
					Triomino t=p.get(i,j);
					switch (l) {
					case 0:
						/* top line */
						if (j%2>0)
							System.out.print(" "+charorstar(t,t.a));
						else
							System.out.print("  ^ ");
						break;
					case 1:
						/* middle line */
						if (j%2>0)
							System.out.print(charorstar(t,t.b)+" "+charorstar(t,t.c));
						else
							System.out.print(charorstar(t,t.c)+" "+charorstar(t,t.b));
						break;
					case 2:
						/* bottom line */
						if (j%2>0)
							System.out.print(" ");
						else
							System.out.print("/_" + charorstar(t,t.a) + "_\\");
					}
				}
				System.out.println("");
			}
		}
	}




}