import java.util.Set;
import java.util.HashMap;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map ;
import java.util.Scanner;

public class Gestionnaire {
    private HashMap<String,Personnage> personnages;
    private HashMap<String,Attribut> attributs;
    private PersonnagesRestants persorest;

    public Gestionnaire(){
        personnages = new HashMap<String,Personnage>();
        attributs = new HashMap<String,Attribut>();
        persorest = new PersonnagesRestants(attributs,personnages);
    }

    public Gestionnaire(String fichierattribut, String fichierperso){
        personnages = new HashMap<String,Personnage>();
        attributs = new HashMap<String,Attribut>();
        chargerPerso(fichierperso);
        chargerAttribut(fichierattribut);
        persorest = new PersonnagesRestants(attributs,personnages);
    }

    public HashMap<String,Attribut> getAttribut(){
            return this.attributs;
    }

    public HashMap<String,Personnage> getPersonnage(){
        return this.personnages;
    }

    public void setAttribut(HashMap<String,Attribut> a){
            this.attributs=a;
    }

    public void setPersonnage(HashMap<String,Personnage> p){
        this.personnages = p;
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
                for(int j=0; j<s.length; j=j+2){
                    perso.ajouterCaracteristique(s[j],s[j+1]);
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
            for(int i=0; i<taille;i++){
                line=bReader.readLine();
                s= line.split("!");
                attributs.put(s[0],new Attribut(s[0],s[1]));
            }
            bReader.close();
            fReader.close();
        } catch (Exception e) {
			System.out.println(e);
		}
    }

    public void enregistrerPerso(String file){
		HashMap<String,String> temp ;
		try{
			Writer fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(String.valueOf((personnages.size())));
			bWriter.newLine();
			for (Map.Entry<String,Personnage> p : personnages.entrySet()){
				bWriter.write(p.getKey());
				bWriter.newLine();
				temp = p.getValue().getCopyCaracteristiques();
				for (Map.Entry<String,String> car : temp.entrySet()){
					bWriter.write(car.getKey() +';'+ car.getValue()+';') ;
				}
				bWriter.newLine();
			}
			bWriter.close();
			fWriter.close();
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
                bWriter.write(nom+"!"+attributs.get(nom).getQuestion());
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
        } catch(Exception e){
            System.out.println("Erreur d'enregistrement dans les attributs"+e);
        }
    }

    public void deroulerPartie(Scanner scanner){
        //persorest.affichagePersos();
        //persorest.affichageAttribut();
            while(persorest.continuer()){
                this.trier(scanner);
            }
            System.out.println("fin");
    }

    public void trier(Scanner scanner){
			int choix=-1;
			String[] caract = persorest.trouvercaracteristiqueDepartageant();
			//System.out.println(caract[0]+"    "+caract[1]);
			//poser question
			//try ( Scanner scanner = new Scanner( System.in ) ) {
				//while((choix!=0) && (choix!=1) && (persorest.getAttributs().size()!=0)) {
				while((choix!=0) && (choix!=1) && (choix !=2)) {
					System.out.println(persorest.getAttributs().get(caract[0]).poserQuestion(caract[1])+" 0 pour oui / 1 nom / 2 je ne sais pas");
					choix= scanner.nextInt();
                    /*if(choix ==2){ //si le joueur ne sais pas, on pose une nouvelle question
                        //persorest.MAJAttributs(caract[0],caract[0]);
                        persorest.EnleverAttributs(caract[0]);
                        caract = persorest.trouvercaracteristiqueDepartageant();
                        //System.out.println(caract[0]+"    "+caract[1]);
                    }*/
				}
           // }
            if(choix == 2){//si le joueur ne sais pas, on pose une nouvelle question
                        //persorest.MAJAttributs(caract[0],caract[0]);
                        persorest.EnleverAttributs(caract[0]);
                        caract = persorest.trouvercaracteristiqueDepartageant();
                        //System.out.println(caract[0]+"    "+caract[1]);
            } else {
                persorest.MAJPersonnages(caract[0],caract[1],choix);
                persorest.MAJAttributs(caract[0],caract[0],choix);}
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
    public static void main(String[] args) {
        // teste chargement
        String f ="fichierperso.txt";
        String f2 = "fichierattribut.txt";

        Gestionnaire g = new Gestionnaire(f2,f);
        //g.chargerPerso(f);
        //g.chargerAttribut("fichierattribut.txt");
        //g.affichageAttribut();
        g.affichagePersos();
        //PersonnagesRestants persorest = new PersonnagesRestants(g.getAttribut(),g.getPersonnage());
        //persorest.trier();
        //persorest.affichagePersos();
        //g.trier();
		try ( Scanner scanner = new Scanner( System.in ) ) {
            g.deroulerPartie(scanner);}
        catch(Exception e){
            System.out.println(e);
        }
        g.affichagePersos();

        g.enregistrerAttribut("testattribut.txt");
        g.enregistrerPerso("testperso.txt");
    }
}
