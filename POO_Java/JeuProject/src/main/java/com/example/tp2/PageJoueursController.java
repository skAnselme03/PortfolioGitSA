package com.example.tp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pkgJeu.Attaque;
import pkgJeu.Defense;
import pkgJeu.Entraineur;
import pkgJeu.Gardien;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PageJoueursController{
    @FXML
    private Button btnCreerAttaque;

    @FXML
    private Button btnCreerDefense;

    @FXML
    private Button btnCreerEntraineur;

    @FXML
    private Button btnCreerGardien;

    @FXML
    private Button btnPageEquipes;

    @FXML
    private Button btnPageTournois;

    @FXML
    private Button btnPagePrincipale;

    @FXML
    private TextField intSalaireJoueur;

    @FXML
    private ToggleGroup radNiveau;

    @FXML
    private RadioButton radioAvance;

    @FXML
    private RadioButton radioDebutant;

    @FXML
    private RadioButton radioIntermediaire;

    @FXML
    private DatePicker strDateNaissanceJoueur;

    @FXML
    private TextField strNomJoueur;

    @FXML
    private TextField strPrenomJoueur;
    @FXML
    private Spinner<Integer> intPointsJoueurs=new Spinner<Integer>();

    //intSalaireJoueur.setValueFactory(valueFactory1);

    //salaire = String.valueOf(intSalaireJoueur.getValue());

    String talent;
    String point;
    //String salaire = intSalaireJoueur.getText();

    @FXML
    void getTalent(ActionEvent event) throws IOException{
        int valeurInitiale;
        try {
            if (radioDebutant.isSelected()) {
                talent = radioDebutant.getText();
                // 50 pour débutant, 100 pour intermédiaire, 150 pour avancé
                valeurInitiale = 50;
                intPointsJoueurs.setDisable(false);
                // Value Factory:
                SpinnerValueFactory<Integer> valueFactory = //
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(valeurInitiale, 99,
                                valeurInitiale);
                intPointsJoueurs.setValueFactory(valueFactory);
            } else if (radioIntermediaire.isSelected()) {
                talent = radioIntermediaire.getText();
                valeurInitiale = 100;
                intPointsJoueurs.setDisable(false);
                // Value Factory:
                SpinnerValueFactory<Integer> valueFactory = //
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(valeurInitiale, 149,
                                valeurInitiale);
                intPointsJoueurs.setValueFactory(valueFactory);
            } else if (radioAvance.isSelected()) {
                talent = radioAvance.getText();
                valeurInitiale = 150;
                intPointsJoueurs.setDisable(false);
                // Value Factory:
                SpinnerValueFactory<Integer> valueFactory = //
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(valeurInitiale, 10000,
                                valeurInitiale);
                intPointsJoueurs.setValueFactory(valueFactory);
            }
            point = String.valueOf(intPointsJoueurs.getValue());
        }catch (Exception e){e.printStackTrace();}
    }


    @FXML
    public void creerAttaque() throws IOException,SQLException {
        //Accès à la connection
        Connection connection = null;
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        ArrayList matrice = new ArrayList<ArrayList>();
        int index = 0;
        String point = String.valueOf(intPointsJoueurs.getValue());
        // Instancier un Attaquant
        Attaque attaque = new Attaque(strNomJoueur.getText(),
                strPrenomJoueur.getText(),
                strDateNaissanceJoueur.getValue().toString(),
                Float.parseFloat(intSalaireJoueur.getText()),
                talent,
                Integer.parseInt(point),
                "Avant");

        try{
            //faire la connection pour acceder à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                    "root","admin1234");
            //Ajouter le Statement
            Statement statement = connection.createStatement();

            String[] infosPartielNewJoueur = {attaque.getNom(), attaque.getPrenom(), attaque.getDateNais()};
            boolean joueurExiste = verifyList(listNomPrenomDNJoeurs(connection,statement),infosPartielNewJoueur);

            if(joueurExiste == true)
            {
                // Message si le joueur existe
                messageAlert("Ce joueur existe déjà!");
            }else {
                //--- [*] Insérer un enregistrement avec paramètres ---//
                int x;
                // Utilisation de l'objet
                String maRequete = "INSERT INTO attaque (nom,prenom," +
                        "dateNais,salaire,talent,nbPoints)" +
                        " VALUES ('"+ attaque.getNom() + "','"
                        + attaque.getPrenom() + "','"
                        + attaque.getDateNais() + "',"
                        + (double)attaque.getSalaire() + ",'"
                        + attaque.getTalent() + "',"
                        + attaque.getNbPoints() +")";
                x= statement.executeUpdate(maRequete);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }
    }

    @FXML
    public void creerDefense() throws IOException, SQLException {
        //Accès à la connection
        Connection connection = null;
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        ArrayList matrice = new ArrayList<ArrayList>();
        int index = 0;
        String point = String.valueOf(intPointsJoueurs.getValue());
        // Instancier un Défenseur
        Defense defense = new Defense(strNomJoueur.getText(),
                strPrenomJoueur.getText(),
                strDateNaissanceJoueur.getValue().toString(),
                Float.parseFloat(intSalaireJoueur.getText()),
                talent,
                Integer.parseInt(point),
                "Avant");

        try{
            //faire la connection pour acceder à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                    "root","admin1234");
            //Ajouter le Statement
            Statement statement = connection.createStatement();

            String[] infosPartielNewJoueur = {defense.getNom(), defense.getPrenom(), defense.getDateNais()};
            boolean joueurExiste = verifyList(listNomPrenomDNJoeurs(connection,statement),infosPartielNewJoueur);

            if(joueurExiste == true)
            {
                // Message si le joueur existe
                messageAlert("Ce joueur existe déjà!");
            }else {
                //--- [*] Insérer un enregistrement avec paramètres ---//
                int x;
                // Utilisation de l'objet
                String maRequete = "INSERT INTO defense (nom,prenom," +
                        "dateNais,salaire,talent,nbPoints)" +
                        " VALUES ('"+ defense.getNom() + "','"
                        + defense.getPrenom() + "','"
                        + defense.getDateNais() + "',"
                        + (double)defense.getSalaire() + ",'"
                        + defense.getTalent() + "',"
                        + defense.getNbPoints() +")";
                x= statement.executeUpdate(maRequete);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }
    }

    @FXML
    public void creerEntraineur() throws IOException, SQLException {
        //Accès à la connection
        Connection connection = null;
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        ArrayList matrice = new ArrayList<ArrayList>();
        int index = 0;
        String point = String.valueOf(intPointsJoueurs.getValue());
        // Instancier un Entraineur
        Entraineur entraineur = new Entraineur(strNomJoueur.getText(),
                strPrenomJoueur.getText(),
                strDateNaissanceJoueur.getValue().toString(),
                Float.parseFloat(intSalaireJoueur.getText()),
                talent,
                Integer.parseInt(point));

        try{
            //faire la connection pour acceder à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                    "root","admin1234");
            //Ajouter le Statement
            Statement statement = connection.createStatement();

            String[] infosPartielNewJoueur = {entraineur.getNom(), entraineur.getPrenom(), entraineur.getDateNais()};
            boolean joueurExiste = verifyList(listNomPrenomDNJoeurs(connection,statement),infosPartielNewJoueur);

            if(joueurExiste == true)
            {
                // Message si le joueur existe
                messageAlert("Ce joueur existe déjà!");
            }else {
                //--- [*] Insérer un enregistrement avec paramètres ---//
                int x;
                // Utilisation de l'objet
                String maRequete = "INSERT INTO entraineur (nom,prenom," +
                        "dateNais,salaire,talent,nbPoints)" +
                        " VALUES ('"+ entraineur.getNom() + "','"
                        + entraineur.getPrenom() + "','"
                        + entraineur.getDateNais() + "',"
                        + (double)entraineur.getSalaire() + ",'"
                        + entraineur.getTalent() + "',"
                        + entraineur.getNbPoints() +")";
                x= statement.executeUpdate(maRequete);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }
    }

    @FXML
    public void creerGardien() throws IOException, SQLException {
        //Accès à la connection
        Connection connection = null;
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        ArrayList matrice = new ArrayList<ArrayList>();
        int index = 0;
        // Instancier un Gardien
        Gardien gardien = new Gardien(strNomJoueur.getText(),
                strPrenomJoueur.getText(),
                strDateNaissanceJoueur.getValue().toString(),
                Float.parseFloat(intSalaireJoueur.getText()),
                talent,
                Integer.parseInt(point),
                "Arrière");

        try{
            //faire la connection pour acceder à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                    "root","admin1234");
            //Ajouter le Statement
            Statement statement = connection.createStatement();

            String[] infosPartielNewJoueur = {gardien.getNom(), gardien.getPrenom(),
                    gardien.getDateNais()};
            boolean joueurExiste = verifyList(listNomPrenomDNJoeurs(connection,statement),
                    infosPartielNewJoueur);

            if(joueurExiste == true)
            {
                // Message si le joueur existe
                messageAlert("Ce joueur existe déjà!");
            }else {
                //--- [*] Insérer un enregistrement avec paramètres ---//
                int x;
                // Utilisation de l'objet
                String maRequete = "INSERT INTO gardien (nom,prenom," +
                        "dateNais,salaire,talent,nbPoints)" +
                        " VALUES ('"+ gardien.getNom() + "','"
                        + gardien.getPrenom() + "','"
                        + gardien.getDateNais() + "',"
                        + (double)gardien.getSalaire() + ",'"
                        + gardien.getTalent() + "',"
                        + gardien.getNbPoints() +")";
                x= statement.executeUpdate(maRequete);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }
    }

    @FXML
    public void goPageEquipes() throws IOException {
        Stage stage = (Stage) btnPageEquipes.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-equipes.fxml"));
        stage.setTitle("Équipes - créer et gérer les équipes");
        stage.setScene(new Scene(fxmlloader, 530, 730));
    }

    @FXML
    public void goPageTournois() throws IOException {
        Stage stage = (Stage) btnPageTournois.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-tournois.fxml"));
        stage.setTitle("Tournoi - organiser et lancer un tournoi");
        stage.setScene(new Scene(fxmlloader, 450, 800));
    }

    @FXML
    public void switchPagePrincipale() throws IOException {
        Stage stage = (Stage) btnPagePrincipale.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-principale.fxml"));
        stage.setTitle("Page principale");
        stage.setScene(new Scene(fxmlloader, 300, 240));
    }



    /**
     * Fonction qui retourne le nom, prenom et la date de naissance d'un joueur
     * présents dans une base de données
     *
     * @param connection
     * @param statement
     * @return {ArrayList} la liste des joueurs contenue dans la base de données
     * @throws SQLException
     */
    public ArrayList listNomPrenomDNJoeurs(Connection connection, Statement statement)throws SQLException{
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        int index = 0;

        try{
            // Execution d'une requête pour aller chercher les infos d'un attaquant dans la BDD
            ResultSet resultSetRqtLectureA = statement.executeQuery("SELECT * FROM attaque");

            // Enregistrer la lecture dans une liste
            while (resultSetRqtLectureA.next()){
                //Initialize each element of ArrayList with ArrayList<ArrayList<String>>
                // (Source:https://www.baeldung.com/java-multi-dimensional-arraylist)
                tableLectureData.add(new ArrayList());

                // Création d'une matrice pour stocker les éléments nom, prénom, date de naissance lu de la table attaque
                tableLectureData.get(index).add(0,resultSetRqtLectureA.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLectureA.getString("prenom"));
                tableLectureData.get(index).add(2,resultSetRqtLectureA.getString("dateNais"));
                index++;
            }

            // Execution d'une requête pour aller chercher les infos d'un défenseur dans la BDD
            ResultSet resultSetRqtLectureD = statement.executeQuery("SELECT * FROM defense");

            // Enregistrer la lecture dans une liste
            while (resultSetRqtLectureD.next()){
                //Initialize each element of ArrayList with ArrayList<ArrayList<String>>
                // (Source:https://www.baeldung.com/java-multi-dimensional-arraylist)
                tableLectureData.add(new ArrayList());

                // Création d'une matrice pour stocker les éléments nom, prénom, date de naissance lu de la table defense
                tableLectureData.get(index).add(0,resultSetRqtLectureD.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLectureD.getString("prenom"));
                tableLectureData.get(index).add(2,resultSetRqtLectureD.getString("dateNais"));
                index++;
            }

            // Execution d'une requête pour aller chercher les infos d'un gardien dans la BDD
            ResultSet resultSetRqtLectureG = statement.executeQuery("SELECT * FROM gardien");

            // Enregistrer la lecture dans une liste
            while (resultSetRqtLectureG.next()){
                //Initialize each element of ArrayList with ArrayList<ArrayList<String>>
                // (Source:https://www.baeldung.com/java-multi-dimensional-arraylist)
                tableLectureData.add(new ArrayList());

                // Création d'une matrice pour stocker les éléments nom, prénom, date de naissance lu de la table gardien
                tableLectureData.get(index).add(0,resultSetRqtLectureG.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLectureG.getString("prenom"));
                tableLectureData.get(index).add(2,resultSetRqtLectureG.getString("dateNais"));
                index++;
            }

            // Execution d'une requête pour aller chercher les infos d'un gardien dans la BDD
            ResultSet resultSetRqtLectureE = statement.executeQuery("SELECT * FROM entraineur");

            // Enregistrer la lecture dans une liste
            while (resultSetRqtLectureE.next()){
                //Initialize each element of ArrayList with ArrayList<ArrayList<String>>
                // (Source:https://www.baeldung.com/java-multi-dimensional-arraylist)
                tableLectureData.add(new ArrayList());

                // Création d'une matrice pour stocker les éléments nom, prénom, date de naissance lu de la table entraineur
                tableLectureData.get(index).add(0,resultSetRqtLectureE.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLectureE.getString("prenom"));
                tableLectureData.get(index).add(2,resultSetRqtLectureE.getString("dateNais"));
                index++;
            }
        }catch (Exception e){e.printStackTrace();}
        return tableLectureData;
    }

    /**
     * Fonction pour vérifier si les elements d'un tableau se retrouve dans une liste
     * @param matrix
     * @param data
     * @return true si les éléments sont présents, sinon false
     */
    public boolean verifyList(ArrayList<ArrayList<String>> matrix, String[] data) {
        //boolean result = false;
        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(i).get(0).toString().equals(data[0]) &
                    matrix.get(i).get(1).toString().equals(data[1]) &
                    matrix.get(i).get(2).toString().equals(data[2])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ouvrire une fenêtre popup selon le message passé enparamètre
     * Sources:https://o7planning.org/11529/javafx-alert-dialog
     * @param  message {String}
     */
    public void messageAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}