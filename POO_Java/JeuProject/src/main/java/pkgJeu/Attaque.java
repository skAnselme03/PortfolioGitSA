/**
 *
 * @author Camille Andriamamonjy & Stephanie Katcha Anselme
 * @version 1.0
 */

package pkgJeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Attaque extends Joueurs{
    String talent;      // débutant, intermédiaire, avancé
    int nbPoints;       // 50 pour débutants, 100 pour intermédiaire, 150 pour avancé
    String position;    // avant, arrière
    String nomEquipe;

    public Attaque(String nom, String prenom, String dateNais, float salaire, String talent, int nbPoints, String position) {
        super(nom, prenom, dateNais, salaire);
        this.talent = talent;
        this.nbPoints = nbPoints;
        this.position = position;
        this.nomEquipe = "Sans équipe";
    }

    public Attaque() {
    }

    /*Getters et Setters*/
    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNomEquipe() { return nomEquipe; }

    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }

    /**
     * Fonction permettant d'afficher les informations concernant un attaquant
     */
    public String toString(){
        return (super.toString() + "\nTalent : " + talent + "\nNombre de points : " + nbPoints
                + "\nPosition sur le terrain : " + position + "\nRôle : Attaque" + "\nÉquipe : " + nomEquipe);
    }
}
