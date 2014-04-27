import java.util.Random;

public class Jeu{
    private int largeur;
    private Triomino[] tab=null;
    Jeu(int largeur,int base) {
	this.largeur=largeur;
	tab=new Triomino[largeur*largeur];
        Random generator = new Random();
	for (int i=0;i<largeur*largeur;i++) {
	    tab[i]=new Triomino(generator.nextInt()%base,
                                generator.nextInt()%base,
                                generator.nextInt()%base);
	}
    }

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

    Triomino get(int i) {
	return tab[i];
    }


}