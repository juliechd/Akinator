import java.util.HashMap;
import java.util.Map ;
public class Attribut{

// attributs
private String nom;
private String question;
private double effectifTot;
private double meilleurPourCentage;
private String meilleurCaract;
private HashMap<String, Integer> combinaison;

// constructeur
public Attribut(String nom, String question){
	this.nom = nom;
	this.combinaison = new HashMap<String, Integer>();
	this.effectifTot = 0;
	this.question = question;
	this.meilleurCaract = "";
	this.meilleurPourCentage = 10;	
}

public Attribut(){
	this.nom = "";
	this.combinaison = new HashMap<String, Integer>();
	this.effectifTot = 0;
	this.question = "";	
	this.meilleurCaract = "";
	this.meilleurPourCentage = 10;	
}

public void affichage(){
	System.out.println(" nom "+nom+" effectif "+effectifTot);
	for (Map.Entry<String,Integer> p : combinaison.entrySet()){
		System.out.print( p.getValue()+"  "+p.getKey());
	}
	System.out.println();
	System.out.println("mc  "+this.meilleurCaract+"  pourcent  "+this.meilleurPourCentage);
}

public int ouiNonQuestion(){ // nul !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! a refaire mieux !
	//permet de savoir si l'attribue a plus de deux reponse possible ou non
	int i=0;
	System.out.println("oui non question "+this.nom);
	if(this.combinaison.size()==1){
		i=1;
	}
	return i;
}
// mutateurs
public void setNom(String nom){
	this.nom = nom;
}

public void setEffectifTot(double effectifTot){
	this.effectifTot = effectifTot;
}


public void setCombinaison(HashMap<String, Integer> combinaison){
	this.combinaison = combinaison;
	this.majMeilleurCaract();
}

public void setQuestion(String question){
	this.question = question;
}

//accesseurs 
public double getEffectifTot(){
	return this.effectifTot;
}
public int getNbCaract(){
	return this.combinaison.size();
}


public String getNom(){
	return this.nom;
}

public String getQuestion(){
	return this.question;
}

public double getMeilleurPourCentage(){
	return this.meilleurPourCentage;
}

public String getMeilleurCaract(){
	return this.meilleurCaract;
}

public HashMap<String, Integer> getCombinaison(){
	return this.combinaison;
}

// méthodes
	
	public String poserQuestion(String caracteristique){
		String questionposee;
				questionposee = this.question + caracteristique;
	
		return questionposee;	
	}
	
	public int retirerCaracteristique(String caracteristique){
		this.combinaison.remove(caracteristique);
		if(caracteristique == this.meilleurCaract){
			//System.out.println(" j'enleve la meilleur caracteristique "+caracteristique);
			majMeilleurCaract();
			//System.out.println(" nouvelle meilleur caract "+this.meilleurCaract+"  pourcentage  "+this.meilleurPourCentage);
		}
		return combinaison.size();		
	}
	
	public void ajouterCaracteristique(String caracteristique){
		this.combinaison.put(caracteristique,1);
	}
	
	public void ajoutEffectifLocal(String caracteristique){
		this.combinaison.put(caracteristique, this.combinaison.get(caracteristique) + 1);
		//verifMeilleurCaracteristique(caracteristique);
		majMeilleurCaract();
	}
	
	public void enleverEffectifLocal(String caracteristique){
		//System.out.println(caracteristique);
		this.combinaison.put(caracteristique, this.combinaison.get(caracteristique) - 1);
		//System.out.println("nom caract "+ caracteristique+" nb dans caract "+this.combinaison.get(caracteristique)+" effectif tot "+this.effectifTot);
		if(this.combinaison.get(caracteristique)==0){
			//System.out.println("j'enleve "+caracteristique+" de ma combinaison");
			//this.retirerCaracteristique(caracteristique);
			this.combinaison.remove(caracteristique);
			//if(caracteristique == this.meilleurCaract){
				majMeilleurCaract();
				//System.out.println("nouvelle meilleur caract apres supression "+this.meilleurCaract);
			//}
		} else {
			//System.out.println(" je verifie la meilleur caracteristique pour "+caracteristique+"  meilleur caract act"+this.meilleurCaract +" nb "+this.combinaison.get(caracteristique));
			verifMeilleurCaracteristique(caracteristique);
			//majMeilleurCaract();
			//System.out.println("la nouvelle meilleur caracteristique "+this.meilleurCaract);
		}
	}

	public void verifMeilleurCaracteristique(String caracteristique){
		//this.meilleurPourCentage =10;
		System.out.println("avant "+this.meilleurPourCentage);
		this.meilleurPourCentage = (double) this.combinaison.get(this.meilleurCaract)/this.effectifTot;
		System.out.println("apres "+this.meilleurPourCentage);
		//if( caracteristique != this.meilleurCaract){
			int effectifLocal = this.combinaison.get(caracteristique);
				//System.out.println("effectif local "+effectifLocal);
				//System.out.println("pourcentage local "+(effectifLocal/this.effectifTot)+" meullieurPourCentage "+this.meilleurPourCentage);
				//System.out.println(" mc "+this.meilleurCaract+" loc "+caracteristique);
				if(Math.abs(0.5 - (double) effectifLocal/this.effectifTot)< Math.abs(0.5 - this.meilleurPourCentage)){
					this.meilleurPourCentage = (double) effectifLocal/this.effectifTot;
					this.meilleurCaract = caracteristique;
				}
		//}
	}

	public void majMeilleurCaract(){
		this.meilleurPourCentage= 100;
		double min = Math.abs(0.5 - this.meilleurPourCentage);
		for (Map.Entry<String,Integer> e : this.combinaison.entrySet()){
			if(Math.abs(0.5 - (double) e.getValue()/this.effectifTot)<min){
					this.meilleurPourCentage = (double) e.getValue()/this.effectifTot;
					//System.out.println("i "+i);
					this.meilleurCaract = e.getKey();
					//System.out.println("caract "+caracteristique);
				}
		}
	}

//add
	public void add(String valeur){

		if (this.combinaison.containsKey(valeur)) {
			//System.out.println("j'ajoute dans l'effectif");
			this.ajoutEffectifLocal(valeur);
		} else {
			this.ajouterCaracteristique(valeur);
			}
		this.effectifTot = this.effectifTot + 1;
		this.majMeilleurCaract();
	}
}
