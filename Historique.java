//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Historique {

	//attributs
    private LinkedList<String> historiquePersonnages ;
    //private final static int TAILLEHIST = 5 ;
    private ArrayList<String> historiqueAttributs ;
    private Personnage historiqueCaracteristiques ;

    //constructeur
    public Historique(){
        this.historiquePersonnages = new LinkedList<String>() ;
        this.historiqueAttributs = new ArrayList<String>() ;
        this.historiqueCaracteristiques = new Personnage("Hist");
    }

    //getters 
    public LinkedList<String> getHistoriquePersonnages(){
        return this.historiquePersonnages ;
    }

    public ArrayList<String> getHistoriqueAttributs(){
        return this.historiqueAttributs ;  
    }

    public Personnage getHistoriqueCaracteristiques(){
        return this.historiqueCaracteristiques ;
    }
    

    //MAJ HISTORIQUE
    
    public void MAJHistorique(HashMap<String,Personnage> remplacantsPersonnages, HashMap<String,Attribut> remplacantsAttributs, String nvAttribut, String nvValeur){
        this.MAJHistoriquePersonnages(remplacantsPersonnages);
        this.MAJHistoriqueAttributs(remplacantsAttributs);
        this.MAJHistoriqueCaracteristiques(nvAttribut, nvValeur);
    }

    public void MAJHistoriquePersonnages(HashMap<String,Personnage> remplacantsPersonnages){
        this.historiquePersonnages.clear();
        for (Map.Entry<String,Personnage> p : remplacantsPersonnages.entrySet()){
            this.historiquePersonnages.add(p.getKey());
        }
    }

    public void MAJHistoriqueAttributs(HashMap<String,Attribut> remplacantsAttributs){
        this.historiqueAttributs.clear();
        for (Map.Entry<String,Attribut> a : remplacantsAttributs.entrySet()){
            this.historiquePersonnages.add(a.getKey());
        }
    }

    public void MAJHistoriqueCaracteristiques(String nvAttribut, String nvValeur){
        this.historiqueCaracteristiques.ajouterCaracteristique(nvAttribut, nvValeur) ;
    }



}
    



