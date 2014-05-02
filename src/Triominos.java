import java.math.BigInteger;
import java.util.Random;


/**
 * Classe principale 
 */
public class Triominos {
	/** compteur de coups */
	static BigInteger cpt = new BigInteger("0");
	
	public static void main(String args[]) {
		System.out.println("Jeu des triominos");
		int base, size = 0;
		Jeu jeu = null;
		

		if( args.length == 0 ) { // mode random :
			Random generator = new Random();
			/** base encodage des caractère (de 2 à 16) */
			base =  2 + Math.abs(generator.nextInt()%15);
			/** taille du triangle (pyramide) du plateau (de 1 à 6) */
			size =  1 + Math.abs(generator.nextInt()%6);
			
			jeu = new Jeu(size, base);
		}
		else if( args.length == 1 ) { // mode fichier :
			LectureFichier fich = new LectureFichier(args[0]);
			base = fich.getBase();
			size = fich.getSize();
			
			jeu = new Jeu(size, base, fich.getTab_trio());
		}
		else if( args.length == 2 ) { // mode random parametrable :
			base = Integer.valueOf(args[0]);
			size = Integer.valueOf(args[1]);
			
			jeu = new Jeu(size, base);
		}
		else { // erreur commande
			System.out.println("Mauvaise commande merci de vous référer au fichier README");
			System.exit(1);
		}
		
		Plateau p = new Plateau(size);
		jeu.affiche();

		if( resoudre(jeu, p, 0, size) ) {
			affiche_plateau_mini(p);
			System.out.println("\nSolution trouvée en " + cpt + " coup(s) !");
		}
		else System.out.println("\nPas de solution, " + cpt + " coup(s)");
	}
	
	
	/**
	 * Methode implantant l'algorithme de résolution du jeu de triominos
	 * @param jeu_trio Notre jeu de triominos
	 * @param plateau Reférence au plateau de triominos
	 * @param pos Position du triomino dans le plateau
	 * @param largeur Largeur du plateau de triominos
	 * @return True si trouve une solution, false sinon
	 */
	public static boolean resoudre(Jeu jeu_trio, Plateau plateau, int pos, int largeur) {
		int next_pos = next(pos, largeur);
		
		if( next_pos == -1 ) { // fin
			return true;
		}
		else {
			// on parcours notre jeu de triominos :
			for(int num_trio=0; num_trio<jeu_trio.tab.length; num_trio++) {
				
				if( jeu_trio.get(num_trio).estPlace() == false ) {
					// on enlève de notre jeu le triomino en cours :
					jeu_trio.get(num_trio).setPlace(true);
					
					// on essaye de le placer dans les 3 sens possibles :
					for(int r=0; r<3; r++) {
						if( contraintes(jeu_trio.get(num_trio), plateau, pos) ) {
							// on place le triomino sur le plateau :
							plateau.set(posToCoord(plateau,pos)[0], posToCoord(plateau,pos)[1], jeu_trio.get(num_trio));
							cpt = cpt.add(BigInteger.ONE);
							
							if( resoudre(jeu_trio, plateau, next_pos, largeur) ) 
								return true;
						
							// on l'enlève du plateau :
							plateau.set(posToCoord(plateau,pos)[0], posToCoord(plateau,pos)[1], null);
						}
						jeu_trio.get(num_trio).tourner();
					}
					
					// si le triomino ne passe pas, on le remet dans notre jeu :
					jeu_trio.get(num_trio).setPlace(false);
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
	private static int next(int pos, int largeur) {
		if( pos < largeur*largeur )
			return pos+1;
		else return -1;
  }
	


	/**
	 * Methode  qui verifie les contraintes (si le triomino s'accorde avec les 
	 * voisins ou non.
	 * @param trio Le triomino en question
	 * @param pos La position actuelle sur le plateau
	 * @param plateau Le plateau de triominos
	 * @return true si on peut le placer dans ce sens, false sinon
	 */
	private static boolean contraintes(Triomino trio, Plateau plateau, int pos) {
		int num_col = posToCoord(plateau,pos)[0];
		int num_ligne = posToCoord(plateau,pos)[1];
		
		if( num_ligne == 0 )
			return true;
		else {
			if( num_ligne%2 == 1 ) { // ligne impaire
				if( plateau.get(num_col-1, num_ligne-1).a != trio.a )
					return false;
				
				if( plateau.get(num_col, num_ligne-1).b != trio.b ) 
					return false;
			}
			else { // ligne paire
				if( plateau.get(num_col, num_ligne-1).c != trio.c )
					return false;
			}
			return true;
		}
  }
	
	
	/**
	 * Methode qui convertit une position dans un triangle en numero de 
	 * colonne et de ligne
	 * @param p Plateau
	 * @param pos Postion dans le triangle 
	 * @return tableau de coord tab[0]=col et tab[1]=ligne
	 */
	private static int[] posToCoord(Plateau p, int pos) {
		int[] coord = new int[2];
		int col = 0, ligne = 0;
		int col_perdu = 0; // nb de colonne perdu en passant à la ligne suivante
		int[] taille_ligne = tailleLigne(p.largeur);
		

		for(int i=0; i<taille_ligne.length; i++){
			if( pos < taille_ligne[i]) {
				col_perdu = p.largeur - taille_ligne[i];
				col = pos + col_perdu;
				ligne = i ;
				break;
			}
			else pos -= taille_ligne[i];
		}
		
		coord[0] = col;
		coord[1] = ligne;
		
		return coord;
	}
	
	/**
	 * Methode qui renvoie la taille de toutes les lignes dans un triangle
	 * @param largeur Largeur du triangle
	 * @return tableau qui à la ligne i associe n triominos
	 */
	public static int[] tailleLigne(int largeur) { // OK
		int nb_lignes = largeur*2-1;
		int[] taille_ligne = new int[nb_lignes];
		boolean passage = true;
		
		for(int i=0; i<nb_lignes; i++) {
			taille_ligne[i] = largeur;
			
			if(passage) {
				largeur--;
				passage = false;
			}
			else passage = true;
		}
		return taille_ligne;
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
				for(k=0 ; k<p.largeur-i;k++) 
					System.out.print("   ");
				if (l%2>0) 
					System.out.print(" ");

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