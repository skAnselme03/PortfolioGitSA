/**
 *
 * @author Camille Andriamamonjy & Stephanie Katcha Anselme
 * @version 1.0
 */

package pkgJeu;

public class Entraineur extends Joueurs{
    String talent;      // débutant, intermédiaire, avancé
    int nbPoints;       // 50 pour débutants, 100 pour intermédiaire, 150 pour avancé
    String nomEquipe;

    public Entraineur(String nom, String prenom, String dateNais, float salaire, String talent, int nbPoints) {
        super(nom, prenom, dateNais, salaire);
        this.talent = talent;
        this.nbPoints = nbPoints;
        this.nomEquipe = "Sans équipe";
    }

    public Entraineur() {
    }

    /*Getters et Setters*/
    public String getTalent() { return talent; }

    public void setTalent(String talent) { this.talent = talent; }

    public int getNbPoints() { return nbPoints; }

    public void setNbPoints(int nbPoints) { this.nbPoints = nbPoints; }

    public String getNomEquipe() { return nomEquipe; }

    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }

    /**
     * Fonction permettant d'afficher les informations concernant un entraîneur
     */
    public String toString(){
        return (super.toString() + "\nTalent : " + talent + "\nNombre de points : " + nbPoints + "\nRôle : Entraîneur" + "\nÉquipe : " + nomEquipe);
    }
}
