/**
 *
 * @author Camille Andriamamonjy & Stephanie Katcha Anselme
 * @version 1.0
 */

package pkgJeu;

public class Gardien extends Joueurs{

    String talent; // débutant, intermédiare, avancé
    int nbPoints; // 50 pour débutant, 100 pour intermédiaire, 150 pour avancé
    String position; // avant, arrière
    String nomEquipe;

    public Gardien(String nom, String prenom, String dateNais,
                   float salaire, String talent, int nbPoints,
                   String position) {
        super(nom, prenom, dateNais, salaire);
        this.talent = talent;
        this.nbPoints = nbPoints;
        this.position = position;
        this.nomEquipe = "Sans équipe";
    }

    public Gardien() {
    }

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

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    /**
     * Fonction permettant d'afficher les informations concernant un gardien
     */
    public String toString(){
        return (super.toString() + "\nTalent : " + talent + "\nNombre de points : " + nbPoints +
                "\nPosition : " + position + "\nRôle : Gardien" + "\nÉquipe : "+ nomEquipe);
    }
}
