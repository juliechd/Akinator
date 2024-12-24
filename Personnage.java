import java.util.HashMap;
import java.util.*;
public class Personnage {
	
	//attributs
		private String nom ;
		private HashMap<String,String> caracteristiques ;
		
		
	//Constructeurs
		public Personnage(String nom){
		this.nom = nom ;
		this.caracteristiques = new HashMap<String,String>() ;
		}

	//getters
		public String getNom(){
			return this.nom ;
		}
		
		public HashMap<String,String> getCaracteristiques(){
			return this.caracteristiques;
		}

		public void afficher(){
				System.out.println(this.nom);
				for (Map.Entry<String,String> car : this.caracteristiques.entrySet()){
					System.out.print(car.getKey() +';'+ car.getValue()+';') ;
				}
				System.out.println();
		}

		
	//copy
		public HashMap<String,String> getCopyCaracteristiques(){
			HashMap<String,String> res ;
			res = new HashMap<String,String>() ;
			for (Map.Entry<String,String> car : this.caracteristiques.entrySet()){
				res.put(car.getKey(),car.getValue()) ;
			}
			return res ;
		}
		
	//checkers
	
		public int verifAttribut(String attribut){
			int res ;
				res = 0;
				if (caracteristiques.containsKey(attribut)){
					res = 1;
				}
				return res ;
		}
		public int verifValeurCaracteristique(String caracteristique, String valeur){
			int res ;
				res = 0;
				if (caracteristiques.containsKey(caracteristique) && caracteristiques.get(caracteristique).equals(valeur)){
					res = 1;
				}
				return res ;
		}
	
	//adders
		public void ajouterCaracteristique(String nom, String valeur){
			this.caracteristiques.put(nom,valeur) ;
		}
}
