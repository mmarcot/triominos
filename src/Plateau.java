
public class Plateau {
    int largeur;
    ColonneTriominos[] triominos=null;
    
    /**
     * Constructeur d'un plateau
     * @param largeur largeur du plateau
     */
    Plateau(int largeur) {
    	this.largeur = largeur;
		triominos = new ColonneTriominos[largeur];
		
		for(int i=0;i<largeur;i++)
			triominos[i]=new ColonneTriominos(2*i+1);
    }

    
    void set(int i,int j,Triomino t) {
    	triominos[i].set(j,t);
    }

    
    Triomino get(int i,int j) {
    	return triominos[i].get(j);
    }

}

