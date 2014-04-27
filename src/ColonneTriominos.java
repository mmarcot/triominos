public class ColonneTriominos{
    private Triomino[] colonne=null;
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
