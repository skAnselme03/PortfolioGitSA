/**
 *
 * @author Camille Andriamamonjy & Stephanie Katcha Anselme
 * @version 1.0
 */

package pkgJeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipes {
    String nom;
    float budget;
    int nbPoints;
    int gardiens;       // max 2, min 1
    int defense;        // max 11, min 5
    int attaque;        // max 11, min 5
    int entraineur;     // obligatoirement 1

    public Equipes(String nom, float budget) {
        this.nom = nom;
        this.budget = budget;
        this.nbPoints = 0;
        this.gardiens = 0;
        this.defense = 0;
        this.attaque = 0;
        this.entraineur = 0;
    }

    public Equipes(String nom, float budget, int nbPoints, int attaque, int defense, int gardiens, int entraineur) {
        this.nom = nom;
        this.budget = budget;
        this.nbPoints = nbPoints;
        this.attaque = attaque;
        this.defense = defense;
        this.gardiens = gardiens;
        this.entraineur = entraineur;
    }

    public Equipes() {
    }

    // Getters et Setters
    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public float getBudget() { return budget; }

    public void setBudget(float budget) { this.budget = budget; }

    public int getNbPoints() { return nbPoints; }

    public void setNbPoints(int nbPoints) { this.nbPoints = nbPoints; }

    public int getGardiens() { return gardiens; }

    public void setGardiens(int gardiens) {
        this.gardiens = gardiens;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(int entraineur) {
        this.entraineur = entraineur;
    }

    /**
     * Afficher les informations d'une équipe
     */
    public String toString(){
        return ("Nom de l'équipe : " + nom + "\nBudget : " + budget + "\nNombre de points : " + nbPoints
                + "\nAttaquants : " + attaque + "\nDéfenseurs : " + defense + "\nEntraîneur : " + entraineur + "\nGardiens : " + gardiens);
    }

    // Les méthodes suivantes servent à ajouter des joueurs à une équipe
    /**
     * Ajouter des attaquants à une équipe
     * @param attaque L'attaquant à ajouter
     * @param equipes L'équipe à laquelle le joueur est ajouté
     * @see Attaque
     */
    public static void ajouterAttaque(Attaque attaque, Equipes equipes){
        if (equipes.attaque < 11){
            if (equipes.budget >= attaque.salaire){
                if (attaque.nomEquipe == "Sans équipe"){
                    equipes.budget -= attaque.salaire;
                    equipes.attaque += 1;
                    equipes.nbPoints += attaque.nbPoints;
                    attaque.nomEquipe = equipes.nom;
                } else {
                    System.out.println("L'attaquant " + attaque.nom + " est déjà dans l'équipe " + attaque.nomEquipe);
                }
            } else {
                System.out.println("L'équipe " + equipes.nom + " n'a pas le budget pour recruter l'attaquant " + attaque.nom);
            }
        } else {
            System.out.println("L'équipe " + equipes.nom + " a déjà atteint son maximum d'attaquants");
        }
    }

    /**
     * Ajouter des défenseurs à une équipe
     * @param defense Le défenseur à ajouter
     * @param equipes L'équipe à laquelle le joueur est ajouté
     * @see Defense
     */
    public static void ajouterDefense(Defense defense, Equipes equipes){
        if (equipes.defense < 11){
            if (equipes.budget >= defense.salaire){
                if (defense.nomEquipe == "Sans équipe"){
                    equipes.budget -= defense.salaire;
                    equipes.defense += 1;
                    equipes.nbPoints += defense.nbPoints;
                    defense.nomEquipe = equipes.nom;
                } else {
                    System.out.println("Le défenseur " + defense.nom + " est déjà dans l'équipe " + defense.nomEquipe);
                }
            } else {
                System.out.println("L'équipe " + equipes.nom + " n'a pas le budget pour recruter le défenseur " + defense.nom);
            }
        } else {
            System.out.println("L'équipe " + equipes.nom + " a déjà atteint son maximum de défenseurs");
        }
    }

    /**
     * Ajouter des gardiens à une équipe
     * @param gardien Le gardien à ajouter
     * @param equipes L'équipe à laquelle le joueur est ajouté
     * @see Gardien
     */
    public static void ajouterGardien(Gardien gardien, Equipes equipes){
        if (equipes.gardiens < 2){
            if (equipes.budget >= gardien.salaire){
                if (gardien.nomEquipe == "Sans équipe"){
                    equipes.budget -= gardien.salaire;
                    equipes.gardiens += 1;
                    equipes.nbPoints += gardien.nbPoints;
                    gardien.nomEquipe = equipes.nom;
                } else {
                    System.out.println("Le gardien " + gardien.nom + " est déjà dans l'équipe " + gardien.nomEquipe);
                }
            } else {
                System.out.println("L'équipe " + equipes.nom + " n'a pas le budget pour recruter le gardien " + gardien.nom);
            }
        } else {
            System.out.println("L'équipe " + equipes.nom + " a déjà atteint son maximum de gardiens");
        }
    }

    /**
     * Ajouter des entraîneurs à une équipe
     * @param entraineur L'entraîneur à ajouter
     * @param equipes L'équipe à laquelle le joueur est ajouté
     * @see Entraineur
     */
    public static void ajouterEntraineur(Entraineur entraineur, Equipes equipes){
        if (equipes.entraineur < 1){
            if (equipes.budget >= entraineur.salaire){
                if (entraineur.nomEquipe == "Sans équipe"){
                    equipes.budget -= entraineur.salaire;
                    equipes.entraineur += 1;
                    equipes.nbPoints += entraineur.nbPoints;
                    entraineur.nomEquipe = equipes.nom;
                } else {
                    System.out.println("L'entraîneur " + entraineur.nom + " est déjà dans l'équipe " + entraineur.nomEquipe);
                }
            } else {
                System.out.println("L'équipe " + equipes.nom + " n'a pas le budget pour recruter l'entraîneur' " + entraineur.nom);
            }
        } else {
            System.out.println("L'équipe " + equipes.nom + " a déjà un entraîneur.");
        }
    }

    // Les méthodes suivantes servent à retirer des joueurs d'une équipe
    /**
     * Retirer des attaquants d'une équipe
     * @param attaque L'attaquant à retirer
     * @param equipes L'équipe de laquelle le joueur est retiré
     * @see Attaque
     */
    public static void retirerAttaque(Attaque attaque, Equipes equipes){
        if (attaque.nomEquipe == equipes.nom){
            equipes.budget += attaque.salaire;
            equipes.attaque -= 1;
            equipes.nbPoints -= attaque.nbPoints;
            attaque.nomEquipe = "Sans équipe";
        } else {
            System.out.println("L'attaquant " + attaque.nom + " ne fait pas partie de l'équipe " + equipes.nom);
        }
    }

    /**
     * Retirer des défenseurs d'une équipe
     * @param defense Le défenseur à retirer
     * @param equipes L'équipe de laquelle le joueur est retiré
     * @see Defense
     */
    public static void retirerDefense(Defense defense, Equipes equipes){
        if (defense.nomEquipe == equipes.nom){
            equipes.budget += defense.salaire;
            equipes.defense -= 1;
            equipes.nbPoints -= defense.nbPoints;
            defense.nomEquipe = "Sans équipe";
        } else {
            System.out.println("Le défenseur " + defense.nom + " ne fait pas partie de l'équipe " + equipes.nom);
        }
    }

    /**
     * Retirer des gardiens d'une équipe
     * @param gardien Le gardien à retirer
     * @param equipes L'équipe de laquelle le joueur est retiré
     * @see Gardien
     */
    public static void retirerGardien(Gardien gardien, Equipes equipes){
        if (gardien.nomEquipe == equipes.nom){
            equipes.budget += gardien.salaire;
            equipes.gardiens -= 1;
            equipes.nbPoints -= gardien.nbPoints;
            gardien.nomEquipe = "Sans équipe";
        } else {
            System.out.println("Le gardien " + gardien.nom + " ne fait pas partie de l'équipe " + equipes.nom);
        }
    }

    /**
     * Retirer des entraîneurs d'une équipe
     * @param entraineur L'entraîneur à retirer
     * @param equipes L'équipe de laquelle le joueur est retiré
     * @see Entraineur
     */
    public static void retirerEntraineur(Entraineur entraineur, Equipes equipes){
        if (entraineur.nomEquipe == equipes.nom){
            equipes.budget += entraineur.salaire;
            equipes.entraineur -= 1;
            equipes.nbPoints -= entraineur.nbPoints;
            entraineur.nomEquipe = "Sans équipe";
        } else {
            System.out.println("L'entraîneur " + entraineur.nom + " ne fait pas partie de l'équipe " + equipes.nom);
        }
    }

    // Les méthodes suivantes servent à déplacer un joueur d'une équipe à une autre
    /**
     * Déplacer un attaquant d'une équipe1 à une équipe2
     * @param attaque L'attaquant à déplacer
     * @param equipe1 L'équipe de laquelle le joueur est retiré
     * @param equipe2 L'équipe à laquelle le joueur est ajouté
     * @see Attaque
     */
    public static void deplacerAttaque(Attaque attaque, Equipes equipe1, Equipes equipe2){
        equipe1.retirerAttaque(attaque, equipe1);
        equipe2.ajouterAttaque(attaque, equipe2);
    }

    /**
     * Déplacer un défenseur d'une équipe1 à une équipe2
     * @param defense Le défenseur à déplacer
     * @param equipe1 L'équipe de laquelle le joueur est retiré
     * @param equipe2 L'équipe à laquelle le joueur est ajouté
     * @see Defense
     */
    public static void deplacerDefense(Defense defense, Equipes equipe1, Equipes equipe2){
        equipe1.retirerDefense(defense, equipe1);
        equipe2.ajouterDefense(defense, equipe2);
    }

    /**
     * Déplacer un gardien d'une équipe1 à une équipe2
     * @param gardien Le gardien à déplacer
     * @param equipe1 L'équipe de laquelle le joueur est retiré
     * @param equipe2 L'équipe à laquelle le joueur est ajouté
     * @see Gardien
     */
    public static void deplacerGardien(Gardien gardien, Equipes equipe1, Equipes equipe2){
        equipe1.retirerGardien(gardien, equipe1);
        equipe2.ajouterGardien(gardien, equipe2);
    }

    /**
     * Déplacer un entraîneur d'une équipe1 à une équipe2
     * @param entraineur L'entraîneur à déplacer
     * @param equipe1 L'équipe de laquelle le joueur est retiré
     * @param equipe2 L'équipe à laquelle le joueur est ajouté
     * @see Entraineur
     */
    public static void deplacerEntraineur(Entraineur entraineur, Equipes equipe1, Equipes equipe2){
        equipe1.retirerEntraineur(entraineur, equipe1);
        equipe2.ajouterEntraineur(entraineur, equipe2);
    }

    /**
     * Méthode pour vérifier qu'une équipe est valide (pour participer à un tournoi)
     * @param equipes L'équipe dont on souhaite vérifier la validité
     * @return bool La validité de l'équipe
     */
    public static boolean validerEquipe(Equipes equipes){
        if ((equipes.attaque >= 5 && equipes.attaque <= 11) &&
                (equipes.defense >= 5 && equipes.defense <= 11) &&
                (equipes.gardiens >= 1 && equipes.gardiens <= 2) &&
                (equipes.entraineur == 1)){
            System.out.println("L'équipe " + equipes.nom + " est valide.");
            return true;
        } else {
            System.out.println("L'équipe " + equipes.nom + " n'est pas valide. Vérifiez que le nombre de ses joueurs est adéquat." +
                    "\n(Attaquants : min 5 max 11, Défenseurs : min 5 max 11, Gardiens : min 1 max 2, Entraîneur : 1)");
            return false;
        }
    }

    /**
     * Méthode pour effectuer le lancer d'un dé à 1000 faces
     * (pour les points aléatoires supplémentaires attribués à chaque équipe à chaque manche)
     * @return int Le résultat du lancer de dés
     */
    private int lancerDés (){
        Random r = new Random();
        int resultatDes = r.nextInt(1000)+1;
        return resultatDes;
    }

    /**
     * Méthode pour lancer un tournoi et afficher les résultats
     * @param equipe1 L'une des équipes participantes
     * @param equipe2 L'autre équipe participante
     */
    public String participerTournois(Equipes equipe1, Equipes equipe2)
    {
        String text = null;
        if (validerEquipe(equipe1) == true && validerEquipe(equipe2) == true){  // Si les deux équipes sont valides, le tournoi se déroule
            int victoiresEquipe1 = 0;
            int victoiresEquipe2 = 0;
            int match = 1;
            text = equipe1.nom + " VS " + equipe2.nom + "\n";
            while (match < 4)
            {
                int resultatEquipe1 = equipe1.nbPoints + equipe1.lancerDés();
                int resultatEquipe2 = equipe2.nbPoints + equipe2.lancerDés();
                text += "\n*** MATCH " + match + " ***";
                text += "\nL'équipe " + equipe1.nom + " a remporté " + resultatEquipe1 + " points." +
                        "\nL'équipe " + equipe2.nom + " a remporté " + resultatEquipe2 + " points.";
                if (resultatEquipe1 > resultatEquipe2){
                    text += "\nL'équipe " + equipe1.nom + " remporte cette manche.\n";
                    victoiresEquipe1++;
                } else {
                    text += "\nL'équipe " + equipe2.nom + " remporte cette manche.\n";
                    victoiresEquipe2++;
                }
                match++;
            }
            // Résultats finaux
            if (victoiresEquipe1 > victoiresEquipe2){
                text += "\nL'équipe " + equipe1.nom + " remporte ce tournoi!\nFélicitations " + equipe1.nom + "!";
            } else {
                text += "\nL'équipe " + equipe2.nom + " remporte ce tournoi!\nFélicitations " + equipe2.nom + "!";
            }
        } else {    // Affiche un message d'erreur
            text = "Revérifiez vos équipes avant de participer à un tournoi.";
        }
        return text;
    }
}
