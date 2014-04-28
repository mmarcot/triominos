

/**
 * Classe qui représente une colonne de triomino sur le plateau,
 * colonne qui commence en haut à gauche et fini en bas à droite
 */
public class ColonneTriominos{
	private Triomino[] colonne=null;

	
	/**
	 * Constructeur d'une colonne de triominos
	 * @param hauteur hauteur de la colonne de triominos
	 */
	ColonneTriominos(int hauteur) {
		colonne=new Triomino[hauteur];
	}


	/**
	 * Place un triomino donné dans la colonne du plateau donnée
	 * @param j numéro de la colonne
	 * @param t triomino à placer
	 */
	void set(int j, Triomino t) {
		colonne[j]=t;
	}


	/**
	 * Accesseur d'un triomino d'une colonne à un endroit donné
	 * @param j numéro du triomino dans la colonne 
	 * @return le triomino
	 */
	Triomino get(int j) {
		return colonne[j];
	}
}
