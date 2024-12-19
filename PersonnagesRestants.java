import java.util.HashMap;
import java.util.*;
public class PersonnagesRestants {
	
	//attributs
		private HashMap<String,Attribut> attributs ;
		private HashMap<String,Personnage> personnages ;
		
	//constructeur
	public PersonnagesRestants(HashMap<String,Attribut> attributs, HashMap<String,Personnage> personnages){
		Attribut a;
		this.attributs = attributs;
		this.personnages = personnages;
		for (Personnage p : personnages.values()){
			for (Map.Entry<String,String> c : p.getCaracteristiques().entrySet()){
				a = attributs.get(c.getKey());
				valeur = c.getValue();
				a.add(valeur) ;
			}
		}
		
	}
		
		
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
						MAJEffectifs(p.getValue());					
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
		
		public void MAJEffectifs(Personnage p){
			Attribut a;
			for (Map.Entry<String,String> c : p.getCaracteristiques().entrySet()){
				a= attributs.get(c.getKey());
				a.enleverEffectifLocal(c.getValue());
				a.setEffectifTot(attributs.get(c.getKey()).getEffectifTot()-1);
			}
		}
		
		public String[] trouvercaracteristiqueDepartageant(){
			String[] result = new String[2];
			Attribut attribut = effectifMax();
			result[0] = attribut.getNom();
			result[1] = effectifLocal(attribut);
			return result;
				
		}
		
		public Attribut effectifMax(){
		int i = 0;
		Attribut attribut = new Attribut();
		for (Attribut a : attributs.values()){
			if (a.getEffectifTot() > i){
				i = a.getEffectifTot();
				attribut = a;
			}
		}
		return attribut;
		}
		
		public String effectifLocal(Attribut attribut){
			double i = 1;
			String caracteristique = "";
			for (Map.Entry<String, Integer> e : attribut.getCombinaison().entrySet()){
				if(Math.abs(0.5 - e.getValue()/attribut.getEffectifTot())<i){
					i = Math.abs(0.5 - e.getValue()/attribut.getEffectifTot());
					caracteristique = e.getKey();
				}
			}
			return caracteristique;
		}

}

