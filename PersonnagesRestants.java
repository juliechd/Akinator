import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;
public class PersonnagesRestants {
	
	//attributs
		private HashMap<String,Attribut> attributs ;
		private HashMap<String,Personnage> personnages ;
		
	//constructeur
	public PersonnagesRestants(HashMap<String,Attribut> attributs, HashMap<String,Personnage> personnages){
		//Attribut a;
		String valeur;
		this.attributs = attributs;
		this.personnages = personnages;
		for (Personnage p : personnages.values()){
			for (Map.Entry<String,String> c : p.getCaracteristiques().entrySet()){
				Attribut a;
				a = this.attributs.get(c.getKey());
				valeur = c.getValue();
				a.add(valeur) ;
				//a.affichage();
				this.attributs.put(c.getKey(),a);
			}
		}
		
	}

	public HashMap<String,Attribut> getAttribut(){
            return this.attributs;
    }

    public HashMap<String,Personnage> getPersonnage(){
        return this.personnages;
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
		public void MAJPersonnages(String attribut, String valeur, int choix){
			Iterator<HashMap.Entry<String, Personnage>> it = personnages.entrySet().iterator();
			while(it.hasNext()){
					Map.Entry<String, Personnage> p = it.next();
					if (p.getValue().verifValeurCaracteristique(attribut,valeur) == choix){
						it.remove();	
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
			Attribut attribut = meuilleurAttribut();
			result[0] = attribut.getNom();
			result[1] = effectifLocal(attribut);
			return result;
				
		}
		
		public Attribut meuilleurAttribut(){
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
				//System.out.println("e "+e.getValue()+" effectif total"+attribut.getEffectifTot()+"  "+((double) e.getValue()/attribut.getEffectifTot()));
				//System.out.println(Math.abs(0.5 - ((double) e.getValue()/attribut.getEffectifTot())));
				if(Math.abs(0.5 - (double) e.getValue()/attribut.getEffectifTot())<i){
					i = Math.abs(0.5 - (double) e.getValue()/attribut.getEffectifTot());
					//System.out.println("i "+i);
					caracteristique = e.getKey();
					//System.out.println("caract "+caracteristique);
				}
			}
			return caracteristique;
		}

		public void trier(){
			int choix=-1;
			String[] caract = this.trouvercaracteristiqueDepartageant();
			System.out.println(caract[0]+"    "+caract[1]);
			//poser question
			try ( Scanner scanner = new Scanner( System.in ) ) {
				while((choix!=0) && (choix!=1)) {
					System.out.println(this.attributs.get(caract[0]).poserQuestion(caract[1])+" 0 pour oui / 1 nom");
					choix= scanner.nextInt();
				}
            }
			MAJPersonnages(caract[0],caract[1],choix);
			MAJAttributs(caract[0],caract[0]);
		}

		public void affichageAttribut(){

        for( String nom : attributs.keySet()){
                System.out.println(nom+"!"+attributs.get(nom).getQuestion());
            }
    }

		public void affichagePersos(){
        HashMap<String,String> temp ;
        for (Map.Entry<String,Personnage> p : personnages.entrySet()){
				System.out.println(p.getKey());
				temp = p.getValue().getCopyCaracteristiques();
				for (Map.Entry<String,String> car : temp.entrySet()){
					System.out.print(car.getKey() +';'+ car.getValue()+';') ;
				}
				System.out.println();
			}
    }
}

