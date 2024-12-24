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

	public HashMap<String,Attribut> getAttributs(){
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
		public boolean continuer(){
			boolean continuer = true;
			System.out.println("taille attribut "+this.attributs.size()+"  nb perso "+this.personnages.size());
			continuer = (this.attributs.size()!= 0) && (this.personnages.size() != 1);
			return continuer;
		}

		public void MAJPersonnages(String attribut, String valeur, int choix){
			Iterator<HashMap.Entry<String, Personnage>> it = personnages.entrySet().iterator();
				//System.out.println("maj perso "+attribut+"      "+valeur);
			while(it.hasNext()){
					Map.Entry<String, Personnage> p = it.next();
					//p.getValue().afficher();
					if (p.getValue().verifValeurCaracteristique(attribut,valeur) == choix){
						it.remove();	
						MAJEffectifs(p.getValue());					
					}
			}
			//System.out.println("fin maj perso");
		}
		
		//essaie pour faire un truc un peut mieux mais pas ouf
		public void MAJAttributs(String attribut, String valeur, int choix){
			int compteur;
			int enlever=0;
			//Si jamais c'est une question avec seulement deux réponse, mettre non implique forcement que la réponse est oui, sinon le joueur met je ne sais pas
			if(choix ==1 && attributs.get(attribut).ouiNonQuestion()==1){//pas encore fonctionnel
				enlever =1;
			}
			compteur = attributs.get(attribut).retirerCaracteristique(valeur);
			if (compteur == 1 || choix == 0 || enlever ==1 ){
				attributs.remove(attribut);
			}
			//affichageAttribut();
		}

		public void EnleverAttributs(String attribut){
			this.attributs.remove(attribut);
		}
		
		public void MAJEffectifs(Personnage p){
			Attribut a;
			//p.afficher();
			for (Map.Entry<String,String> c : p.getCaracteristiques().entrySet()){
				//System.out.println(c.getValue()+"    "+c.getKey());
				if(attributs.containsKey(c.getKey())){
					a= attributs.get(c.getKey());
					a.enleverEffectifLocal(c.getValue());
					a.setEffectifTot(attributs.get(c.getKey()).getEffectifTot()-1);
				}
			}
		}
		
		public String[] trouvercaracteristiqueDepartageant(){
			String[] result = new String[2];
			Attribut attribut = meuilleurAttribut();
			result[0] = attribut.getNom();
			result[1] = effectifLocal(attribut);
			//System.out.println(" trouve caract dep "+result[0]+"   "+result[1]);
			return result;
				
		}
		
		public Attribut meuilleurAttribut(){
		double max = 0;
		Attribut attribut = new Attribut();
		double pourcent;
		for (Attribut a : attributs.values()){
			//System.out.println("meilleur attribu parcourt "+a.getNom()+"   "+a.getEffectifTot());
			if (a.getEffectifTot() > max){
				//caract = effectifLocal(a);
				max =a.getEffectifTot();
				attribut = a;
			}
		}
			//System.out.println("meilleur attribu parcourt "+attribut.getNom());
		
			System.out.println("max "+max+"    "+attribut.getNom());
		return attribut;
		}
		/*
		public Attribut meuilleurAttributtest(){
		double max = 0;
		Attribut attribut = new Attribut();
		double pourcent;
		for (Attribut a : attributs.values()){
			System.out.println("meilleur attribu parcourt "+a.getNom()+"   "+a.getEffectifTot());
			pourcent = a.getEffectifTot()*a.getNbCaract();
			if (pourcent > max && pourcent != this.personnages.size()){
				//caract = effectifLocal(a);
				max = pourcent;
				attribut = a;
			}
		}
			//System.out.println("meilleur attribu parcourt "+attribut.getNom());
		
			System.out.println("max "+max+"    "+attribut.getNom());
		return attribut;
		}
		*/
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

