import java.util.Set;
import java.util.HashMap;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Gestionnaire {
    private HashMap<String,Personnage> listePerso;
    private HashMap<String,Attribut> listeAttribut;

    public Gestionnaire(){
        listePerso = new HashMap<String,Personnage>();
        listeAttribut = new HashMap<String,Attribut>();
    }

    public void chargerPerso(String file){

    }

    public void chargerAttribut(String file){

    }

    public void enregistrerPerso(String file){
        try{
            Writer fWriter = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for (String nom : listePerso.keySet()){
                bWriter.write(nom);
                bWriter.newLine();
                for( Caracteristique c : listePerso.get(nom))
                    bWriter.write(c.getnom()+';'+c.getvaleur()+';')
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
            
        }
    } catch( Exception e){
        System.out.println("Erreur d'enregistrement"+e);
    }

    public void enregistrerAttribut(String file){

    }

    public void deroulerPartie(){

    }

    public Caracteristique transformerReponse(){

    }
}