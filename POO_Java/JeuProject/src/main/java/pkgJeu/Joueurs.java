/**
 *
 * @author Camille Andriamamonjy & Stephanie Katcha Anselme
 * @version 1.0
 */

package pkgJeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Joueurs {
    String nom, prenom, dateNais;
    float salaire;

    ArrayList listeJoueur = new ArrayList<>();


    public Joueurs(String nom, String prenom, String dateNais, float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.salaire = salaire;
    }

    public Joueurs() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNais() {
        return dateNais;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public ArrayList getListeJoueur() {
        return listeJoueur;
    }

    public void setListeJoueur(ArrayList listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    /**
     * Fonction permettant d'afficher les informations concernant un joueur
     */
    public String toString() {
        return ("Nom : " + nom + "\nPrenom : " + prenom + "\nDate de naissance : "
                + dateNais + "\nSalaire : " + salaire);
    }

    /**
     * Afficher la liste des joueurs créés
     * @throws RuntimeException La liste ne contient pas de joueur
     */
    public void afficherListeJoueurs() {
        int tailleListeJoueur = this.listeJoueur.size();
        int index = 1;

        if (tailleListeJoueur == 0) {
            throw new RuntimeException("Aucun joueur n'a été crée");
        } else {
            for (int i = 0; i < tailleListeJoueur; i++) {
                System.out.println("----------------\n" +
                        "\tJoueur " + index + "\n----------------\n" +
                        this.listeJoueur.get(i));
                index++;

            }
        }
    }

    /**
     * Passer en paramètre un type d'objet et créer plusieurs objets selon la quantité
     * requise passée aussi en paramètre.
     * (Objet) Fonction pour instancier des joueurs en allant dans un fichier, lire son contenu
     * récupérer les caractéristiques d'un joueur selon le type de joueurs passé en paramètres
     *
     * @param (nomTypeJoueur) Le type de joueur que l'on veut instancier
     * @param (nbreDefois) le nombre de fois que l'on veut instancier ce joueur
     * @throws IllegalStateException
     *
     * @return Object[] La liste des joueurs instanciés
     */
    public static Object instancier_des_joueurs(String nomTypeJoueur, int nbreDefois) throws IOException {
        // Dictionnaire contenant une liste de joueurs ainsi que leurs caractéristiques
        Map dictJoueurs = chargerJoueur("Joueurs.txt");
        int index = 1;
        String typeJoueur = "";
        String ntJoueur = nomTypeJoueur;
        Object[] joueurs;

        switch (ntJoueur){
            case "Gardien":
                // Instanciation d'une liste de plusieurs gardiens
                joueurs = new Gardien[nbreDefois];
                typeJoueur = "Gardien";

                // Boucle pour générer plusieurs gardiens
                for (int i = 0; i < nbreDefois ; i++) {
                    typeJoueur = typeJoueur + (Math.round(Math.random() * i)+1);    // Le gardien sera sélectionné aléatoirement
                    Map gardien =  valeur_joueur(dictJoueurs, typeJoueur);

                    joueurs[i] = new Gardien((String) gardien.get("Nom"), (String) gardien.get("Prenom"),
                            (String) gardien.get("DateDeNaissance"),
                            Float.parseFloat((String)gardien.get("Salaire")),
                            (String) gardien.get("Talent"),
                            Integer.parseInt((String) gardien.get("NbPoints")),
                            (String) gardien.get("Position"));
                    typeJoueur = "Gardien";
                }
                break;
            case "Defense":
                // Instanciation d'une liste de plusieurs defenses
                joueurs = new Defense[nbreDefois];
                typeJoueur = "Defense";

                // Boucle pour générer plusieurs defenses
                for (int i = 0; i < nbreDefois ; i++) {
                    typeJoueur = typeJoueur + (Math.round(Math.random() * i)+1);    // Le défenseur sera sélectionné aléatoirement
                    Map defense =  valeur_joueur(dictJoueurs, typeJoueur);

                    joueurs[i] = new Defense((String) defense.get("Nom"), (String) defense.get("Prenom"),
                            (String) defense.get("DateDeNaissance"),
                            Float.parseFloat((String)defense.get("Salaire")),
                            (String) defense.get("Talent"),
                            Integer.parseInt((String) defense.get("NbPoints")),
                            (String) defense.get("Position"));
                    typeJoueur = "Defense";
                }
                break;
            case "Attaque":
                // Instanciation d'une liste de plusieurs attaques
                joueurs = new Attaque[nbreDefois];
                typeJoueur = "Attaque";

                // Boucle pour générer plusieurs attaques
                for (int i = 0; i < nbreDefois ; i++) {
                    typeJoueur = typeJoueur + (Math.round(Math.random() * i)+1);    // L'attaquant sera sélectionné aléatoirement
                    Map attaque =  valeur_joueur(dictJoueurs, typeJoueur);

                    joueurs[i] = new Attaque((String) attaque.get("Nom"), (String) attaque.get("Prenom"),
                            (String) attaque.get("DateDeNaissance"),
                            Float.parseFloat((String)attaque.get("Salaire")),
                            (String) attaque.get("Talent"),
                            Integer.parseInt((String) attaque.get("NbPoints")),
                            (String) attaque.get("Position"));
                    typeJoueur = "Attaque";
                }
                break;
            case "Entraineur":
                // Instanciation d'une liste de plusieurs entraineurs
                joueurs = new Entraineur[nbreDefois];
                typeJoueur = "Entraineur";

                // Boucle pour générer plusieurs entraineurs
                for (int i = 0; i < nbreDefois ; i++) {
                    typeJoueur = typeJoueur + (Math.round(Math.random() * i)+1);    // L'entraîneur sera selectionné aléatoirement
                    Map entraineur =  valeur_joueur(dictJoueurs, typeJoueur);

                    joueurs[i] = new Entraineur((String) entraineur.get("Nom"), (String) entraineur.get("Prenom"),
                            (String) entraineur.get("DateDeNaissance"),
                            Float.parseFloat((String)entraineur.get("Salaire")),
                            (String) entraineur.get("Talent"),
                            Integer.parseInt((String) entraineur.get("NbPoints")));
                    typeJoueur = "Entraineur";
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ntJoueur);
        }
        return joueurs;
    }

    /**
     * Fonction permettant de retourner un joueur avec ses caractéristiques dans un dictionnaire de joueurs
     *
     * @param (listeJoueur) dictionnaire de joueurs
     * @param (cle) clé pour accéder aux valeurs du type de joueurs
     *
     * @return Map Les valeurs correspondant au type de joueurs
     */
    public static Map valeur_joueur(Map listeJoueur, String cle){
        return (Map) listeJoueur.get(cle);
    }

    /**
     * Cette fonction lit le fichier Joueurs.txt et le transforme en un dictionnaire
     * de joueurs utilisables dans le reste du programme.
     * Attention! Si vous modifiez le fichier Joueurs.txt, cette fonction pourrait ne plus
     * fonctionner correctement.
     * @throws IOException Fichier texte vide
     *
     * @return HashMap Le dictionnaire de joueurs.
     *
     */
    public static Map chargerJoueur(String fichier) throws IOException {
        FileReader lireFichier = null;
        // Attraper l'erreur si le fichier n'existe pas
        try {
            lireFichier = new FileReader(fichier);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Mettre en mémoire le fichier lu
        BufferedReader fichierLu = new BufferedReader(lireFichier);

        // Instanciation de dictionnaire
        Map <String, Map<String, String>> dictJoueurs = new HashMap<String, Map<String, String>>();
        Map<String, String> valDicJoueurs = new HashMap<>();

        //Lire la première ligne du fichier
        String ligne = fichierLu.readLine();
        String typejoueur = null;
        String cle ="";
        String valeur="";
        String tJoueurComparaison = "Joueur";
        String[] ligneLu = {};
        boolean test = false;

        // Parcourir le fichier et remplir les dictionnaires
        while (ligne != null){
            ligneLu = lire_entre_ligne(ligne);

            if (!ligne.isBlank() && !mot_existe_dans_chaine(ligne,"Fin")){
                cle = ligneLu[0];
                valeur = ligneLu[1];

                if (mot_existe_dans_chaine(tJoueurComparaison,cle)){
                    typejoueur = valeur;
                    dictJoueurs.put(typejoueur,new HashMap<String,String>());
                    valDicJoueurs = new HashMap<>();

                }else {
                    valDicJoueurs.put(cle, valeur);
                    dictJoueurs.put(typejoueur,valDicJoueurs);
                }
            }
            else{
                if (mot_existe_dans_chaine(ligne,"Fin")) {
                    // Vider la mémoire
                    fichierLu.close();
                    lireFichier.close();
                    valDicJoueurs = null;
                    return dictJoueurs;
                }
            }

            ligne = fichierLu.readLine();
        }
        dictJoueurs.put(null,new HashMap<String,String>());
        // Vider la mémoire
        valDicJoueurs = null;
        fichierLu.close();
        lireFichier.close();
        return dictJoueurs;
    }

    /**  (bool) Fonction permettant de retourner vrai si un mot existe dans une chaine
     *
     *  @param (chaine) Chaine d'intérêt
     *  @param (mot) mot à rechercher
     *
     *  @return bool Si le mot existe ou non dans la chaine
     **/
    public  static  boolean mot_existe_dans_chaine(String chaine, String mot){
        boolean motExist = false;
        int indexMot = chaine.indexOf(mot);
        if (indexMot != -1) {
            motExist = true;
        }
        return motExist;
    }

    /**
     * Cette fonction prend une chaîne de caractère provenant du fichier de joueurs, donc
     * au format "type_caracteristique:valeur_caracteristique\n", et retourne un tableau
     * ["type_caracteristique", "valeur_caracteristique"].
     *
     * @param (ligne) La ligne dont on veut extraire le contenu
     *
     * @return String[]: Le type de caractéristique et la valeur (sous forme de chaîne de caractère)
     *
     * */
    public static String[] lire_entre_ligne(String ligne){
        String[] cleValeur = null;
        if (ligne != null) {
            cleValeur = ligne.strip().split(":");
        }
        return  cleValeur;
    }

}