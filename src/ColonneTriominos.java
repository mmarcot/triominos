

public class ColonneTriominos{
	private Triomino[] colonne=null;

	/**
	 * Constructeur d'une colonne de triominos
	 * @param hauteur hauteur de la colonne de triominos
	 */
	ColonneTriominos(int hauteur) {
		colonne=new Triomino[hauteur];
	}


	void set(int j,Triomino t) {
		colonne[j]=t;
	}


	Triomino get(int j) {
		return colonne[j];
	}
}
