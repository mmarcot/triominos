import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class LectureFichier {
	
	private Triomino[] tab_trio;
	private int base = 0;
	private int size;

	public LectureFichier(String nom_fich) {
		// liste des chiffres rencontrés sur la ligne du milieu (mid) et
		// du bas (bot) :
		ArrayList<Integer> mid = new ArrayList<Integer>();
		ArrayList<Integer> bot = new ArrayList<Integer>();
		
		
		try {
	    Scanner sc = new Scanner(new File(nom_fich));
	    
	    while(sc.hasNextLine()) {
	    	String line = sc.nextLine();
	    	
	    	// chargement des listes :
	    	if(line.contains("^")) // top line
	    		continue;
	    	else if( line.contains("_")) { // bottom line
	    		for(int i=0; i<line.length(); i++) {
	    			char carac = line.charAt(i);
	    			if( "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(""+carac) ) {
	    				bot.add(Character.getNumericValue(carac));
	    				base = Math.max(base, Character.getNumericValue(carac) );
	    			}
	    		}
	    	}
	    	else { // middle line
	    		for(int i=0; i<line.length(); i++) {
	    			char carac = line.charAt(i);
	    			if( "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(""+carac) ) {
	    				mid.add(Character.getNumericValue(carac));
	    				base = Math.max(base, Character.getNumericValue(carac) );
	    			}
	    		}
	    	}
	    }
    	// on construit le tableau de triominos
    	tab_trio = new Triomino[bot.size()];
    	size = (int) Math.sqrt(bot.size());
    	int i = 0;
    	while( !mid.isEmpty() && !bot.isEmpty() ) {
    		tab_trio[i] = new Triomino(bot.remove(0), mid.remove(1), mid.remove(0) );
    		i++;
    	}
    	
    } 
		catch (FileNotFoundException e) {
    	System.out.println("Le fichier " + nom_fich + " n'existe pas");
    }

	}

	public int getBase() {
	  return base;
  }

	public int getSize() {
	  return size;
  }

	public Triomino[] getTab_trio() {
	  return tab_trio;
  }

}
