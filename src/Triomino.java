

/**
 * Classe qui représente un unique triomino
 */
public class Triomino{
	int a,b,c;
	private boolean place;

	
	/**
	 * Constructeur d'un triomino
	 * @param a valeur coin a
	 * @param b valeur coin b
	 * @param c valeur coin c
	 */
	Triomino(int a, int b, int c) {
		this.a=a;
		this.b=b;
		this.c=c;
		this.place = false;
	}
	
	
	/**
	 * Seter de l'attribut placé
	 * @param etat true ou false
	 */
	public void setPlace(boolean etat) {
		this.place = etat;
	}
	
	
	/**
	 * Methode qui dit si le triomino est placé ou non
	 * @return vrai ou faux
	 */
	public boolean estPlace() {
		return this.place;
	}
	
	
	/**
	 * Methode qui permet de tourner un triomino
	 */
	public void tourner() {
		int tmp = b;
		b = a;
		a = c;
		c = tmp;
	}
	
}

