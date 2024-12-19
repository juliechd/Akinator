import java.util.HashMap;
import java.util.*;
public class PersonnagesRestants {
	
	//attributs
		private HashMap<String,Attribut> attributs ;
		private HashMap<String,Personnage> personnages ;
		
		
	//Copy
	/*
		public HashMap<String,Personnage> getCopyPersonnages(){
			HashMap<String,Personnage> res ;
			res = new HashMap<String,Personnage>() ;
			for (Map.Entry<String,Personnage> p : this.personnages.entrySet()){
				res.put(p.getKey(),p.getValue()) ;
			}
			return res ;
		}
	*/
	//MAJ
		public void MAJPersonnages(String attribut, String valeur){
			for (Map.Entry<String,Personnage> p : personnages.entrySet()){
					if (p.getValue().verifValeurCaracteristique(attribut,valeur) == 0){
						personnages.remove(p.getKey());						
					}
			}
		}
		
		public void MAJAttributs(String attribut, String valeur){
			int compteur;
			compteur = attributs.get(attribut).retirerCaracteristique(valeur);
			if (compteur == 1){
				attributs.remove(attribut);
			}
		}


}

