import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class LectureFichier {
	
	private Triomino[] tab_trio;
	private int base;
	private int size;

	public LectureFichier(String nom_fich) {
		// liste des chiffres rencontrés sur la ligne du milieu (mid) et
		// du bas (bot) :
		ArrayList<Character> mid = new ArrayList<Character>();
		ArrayList<Character> bot = new ArrayList<Character>();
		
		
		try {
	    Scanner sc = new Scanner(new File(nom_fich));
	    
	    while(sc.hasNextLine()) {
	    	String line = sc.nextLine();
	    	
	    	// chargement des listes :
	    	if(line.contains("^")) // top line
	    		continue;
	    	else if( line.contains("/")) { // bottom line
	    		for(int i=0; i<line.length(); i++) {
	    			char carac = line.toUpperCase().charAt(i);
	    			if( "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(""+carac) ) 
	    				bot.add(carac);
	    		}
	    	}
	    	else { // middle line
	    		for(int i=0; i<line.length(); i++) {
	    			char carac = line.toUpperCase().charAt(i);
	    			if( "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(""+carac) ) 
	    				mid.add(carac);
	    		}
	    	}
	    	
	    	//TODO conversion des listes en tableau de triominos
	    	
	    	
	    }
    } 
		catch (FileNotFoundException e) {
    	System.out.println("Le fichier " + nom_fich + " n'existe pas");
    }

	}

	public int getBase() {
	  // TODO Auto-generated method stub
	  return 0;
  }

	public int getSize() {
	  // TODO Auto-generated method stub
	  return 0;
  }

	public Triomino[] getTab_trio() {
	  return tab_trio;
  }

}
