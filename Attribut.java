import java.util.HashMap;
public class Attribut{

// attributs
private String nom;
private String question;
private int effectifTot;
private HashMap<String, Integer> combinaison;

// constructeur
public Attribut(String nom, String question){
	this.nom = nom;
	this.combinaison = new HashMap<String, Integer>();
	this.effectifTot = 0;
	this.question = question;	
}

public Attribut(){
	this.nom = "";
	this.combinaison = new HashMap<String, Integer>();
	this.effectifTot = 0;
	this.question = "";	
}

// mutateurs
public void setNom(String nom){
	this.nom = nom;
}

public void setEffectifTot(int effectifTot){
	this.effectifTot = effectifTot;
}

public void setCombinaison(HashMap<String, Integer> combinaison){
	this.combinaison = combinaison;
}

public void setQuestion(String question){
	this.question = question;
}

//accesseurs 
public int getEffectifTot(){
	return this.effectifTot;
}

public String getNom(){
	return this.nom;
}

public String getQuestion(){
	return this.question;
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
		return combinaison.size();		
	}
	
	public void ajouterCaracteristique(String caracteristique){
		this.combinaison.put(caracteristique,1);
	}
	
	public void ajoutEffectifLocal(String caracteristique){
		this.combinaison.put(caracteristique, this.combinaison.get(caracteristique) + 1);
	}
	
	public void enleverEffectifLocal(String caracteristique){
		this.combinaison.put(caracteristique, this.combinaison.get(caracteristique) - 1);
	}

//add
	public void add(String valeur){
		if (this.combinaison.containsValue(valeur)) {
			this.ajoutEffectifLocal(valeur);
		} else {
			this.ajouterCaracteristique(valeur);
			}
		this.effectifTot = this.effectifTot + 1;
	}
}
