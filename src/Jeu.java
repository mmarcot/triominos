import java.util.Random;


/**
 * Classe qui représente notre jeu de triominos 
 */
public class Jeu {
	private int largeur;
	/** tableau contenant l'ensemble de notre jeu de triominos */
	Triomino[] tab=null;


	/**
	 * Constructeur d'un jeu de triominos
	 * @param largeur largeur du triangle (plateau)
	 * @param base base encodage des nombres des triominos
	 */
	Jeu(int largeur, int base) {
		this.largeur=largeur;
		tab = new Triomino[largeur*largeur];
		Random generator = new Random();

		// on tire au hasard les valeurs de chaque triomino :
		for (int i=0;i<largeur*largeur;i++) {
			tab[i]=new Triomino(Math.abs(generator.nextInt()%base),
													Math.abs(generator.nextInt()%base),
													Math.abs(generator.nextInt()%base));
		}
	}


	/**
	 * Methode qui affiche notre jeu de triomino de départ
	 */
	void affiche() {
		for(int i=0; i<largeur*largeur;i+=12){
			int j1,j2,j3;

			for(j1 = i ; (j1 < largeur*largeur) && (j1 < i+12) ; j1++)
				System.out.print("   ^  ");

			System.out.println("");

			for(j2 = i ; (j2 < largeur*largeur) && (j2 < i+12) ; j2++)
				System.out.print("  "+Triominos.onechar(tab[j2].c) + " " 
						+Triominos.onechar(tab[j2].b)+ " ");

			System.out.println("");

			for(j3 = i ; (j3 < largeur*largeur) && (j3 < i+12) ; j3++)
				System.out.print(" /_" + Triominos.onechar(tab[j3].a) + "_\\");

			System.out.println("");
		}
	}

	
	/**
	 * Methode qui retourne le i-ème triomino de notre jeu
	 * @param i numéro du triomino dans le tableau
	 * @return triomino correspondant
	 */
	Triomino get(int i) {
		return tab[i];
	}
	
	
}









