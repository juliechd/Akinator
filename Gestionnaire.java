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
    private HashMap<String,Personnage> personnages;
    private HashMap<String,Attribut> attributs;

    public Gestionnaire(){
        personnages = new HashMap<String,Personnage>();
        attributs = new HashMap<String,Attribut>();
    }

    public void chargerPerso(String file){
	String line;
        String[] s;
        String nom;
        int taille;
        Personnage perso;

        try{
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            //lecture nombre de personnage
            line = bReader.readLine();
            taille = Integer.parseInt(line);

            for(int i =0; i<taille; i++){
                line = bReader.readLine();
                nom=line;
                perso = new Personnage(nom);
                line = bReader.readLine();
                s = line.split(";");
                for(int j=0; j<s.size()-1; j=j+2){
                    perso.ajouterCaracteristique(new Caracteristique(s[j],s[j+1]));
                }
                personnages.put(nom,perso);
            }
            bReader.close();
            fReader.close();
        } catch (Exception e){
            System.out.println("Erreur de chargement de perso :"+ e);
        }
    }

    public void chargerAttribut(String file){
	String line;
        String[] s;
        int taille;
        try{
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            //lecture nombre d'attribut
            line = bReader.readLine();
            taille = Integer.parseInt(line);

            for(int i=0; i<taille,i++){
                line=bReader.readLine();
                s= line.split("**");
                attributs.put(s[0],new Attribut(s[0],s[1]));
            }
            bReader.close();
            fReader.close();
        }
    }

    public void enregistrerPerso(String file){
                try{
            Writer fWriter = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.valueOf((personnages.size()));
            bWriter.newLine();
            for (String nom : personnages.keySet()){
                bWriter.write(nom);
                bWriter.newLine();
                for( Caracteristique c : personnages.get(nom))
                    bWriter.write(c.getnom()+';'+c.getvaleur()+';')
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
            
        }
        } catch( Exception e){
            System.out.println("Erreur d'enregistrement dans les personnages"+e);
        }
    }

    public void enregistrerAttribut(String file){
        try{
            Writer fWriter = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(String.valueOf(attributs.size()));
            bWriter.newLine();
            for( String nom : attributs.keySet()){
                bWriter(nom+"**"+attributs.get(nom).getQuestion());
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
        } catch(Exception e){
            System.out.println("Erreur d'enregistrement dans les attributs"+e);
        }
    }

    public void deroulerPartie(){

    }

    public Caracteristique transformerReponse(){

    }
}
