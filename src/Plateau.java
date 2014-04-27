
/**
 * Plateau sur lequel on pose les triominos un par un de facon ordonnée
 */
public class Plateau {
	int largeur;
	ColonneTriominos[] col_triominos=null;

	
	/**
	 * Constructeur d'un plateau
	 * @param largeur largeur du plateau
	 */
	Plateau(int largeur) {
		this.largeur = largeur;
		col_triominos = new ColonneTriominos[largeur];

		for(int i=0; i<largeur; i++)
			col_triominos[i]=new ColonneTriominos(2*i+1);
	}

	
	/**
	 * Place un triomino dans une colonne à un endroit donné
	 * @param i numero de la colonne
	 * @param j numero du triomino dans la colonne
	 * @param t triomino à mettre
	 */
	void set(int i, int j, Triomino t) {
		col_triominos[i].set(j,t);
	}


	/**
	 * Accesseur d'un triomino à un endroit donné
	 * @param i numero de la colonne
	 * @param j numéro du triomino dans la colonne
	 * @return triomino
	 */
	Triomino get(int i,int j) {
		return col_triominos[i].get(j);
	}

}

