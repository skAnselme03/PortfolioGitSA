package com.example.tp2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pkgJeu.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PageEquipesController {
    @FXML
    private Button btnConfirmerEquipe;

    @FXML
    private Button btnCreerEquipe;

    @FXML
    private Button btnPageJoueurs;

    @FXML
    private Button btnPageTournois;

    @FXML
    private Button btnPagePrincipale;

    @FXML
    private TextField strNomEquipe;

    @FXML
    private TextField intBudgetEquipe;

    @FXML
    private ListView<String> listEquipes;

    @FXML
    private ListView<String> listAttaque;

    @FXML
    private ListView<String> listDefense;

    @FXML
    private ListView<String> listEntraineur;

    @FXML
    private ListView<String> listGardien;

    /**
     * Fonction pour ajouter un joueur sélectionné dans une équipe sélectionnée
     * après avoir cliqué sur le bouton "Ajouter joueur dans équipe"
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    public void confirmerAjoutEquipes() throws IOException, SQLException {
        // Récupère les joueurs et l'équipe sélectionnés
        int idAttaqueSelection = (listAttaque.getSelectionModel().getSelectedIndex()) + 1;
        int idDefenseSelection = (listDefense.getSelectionModel().getSelectedIndex()) + 1;
        int idEntraineurSelection = (listEntraineur.getSelectionModel().getSelectedIndex()) + 1;
        int idGardienSelection = (listGardien.getSelectionModel().getSelectedIndex()) + 1;
        int idEquipeSelection = (listEquipes.getSelectionModel().getSelectedIndex()) + 1;

        // Assigne l'équipe aux joueurs
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                "root","admin1234");
        int x;
        Statement statement = connection.createStatement();
        // Attribue le nom de l'équipe aux attaquants
        String maRequete = "UPDATE attaque SET equipe = '" + idEquipeSelection + "' WHERE (idAttaque = '" + idAttaqueSelection + "')";
        x= statement.executeUpdate(maRequete);
        // Mets la table équipe à jour avec les informations des attaquants
        maRequete = "UPDATE equipes JOIN attaque ON equipes.idEquipes = attaque.equipe SET " +
                "equipes.nbAttaque = (SELECT COUNT(*) FROM attaque WHERE equipes.idEquipes = attaque.equipe GROUP BY equipe), " +
                "equipes.budget = equipes.budgetDepart - (SELECT SUM(salaire) FROM attaque WHERE equipes.idEquipes = attaque.equipe GROUP BY equipe);";
        x= statement.executeUpdate(maRequete);

        // Attribue le nom de l'équipe aux défenseurs
        maRequete = "UPDATE defense SET equipe = '" + idEquipeSelection + "' WHERE (idDefense = '" + idDefenseSelection + "')";
        x= statement.executeUpdate(maRequete);
        // Mets la table équipe à jour avec les informations des défenseurs
        maRequete = "UPDATE equipes JOIN defense ON equipes.idEquipes = defense.equipe SET " +
                "equipes.nbDefense = (SELECT COUNT(*) FROM defense WHERE equipes.idEquipes = defense.equipe GROUP BY equipe), " +
                "equipes.budget = equipes.budgetDepart - (SELECT SUM(salaire) FROM defense WHERE equipes.idEquipes = defense.equipe GROUP BY equipe);";
        x= statement.executeUpdate(maRequete);

        // Attribue le nom de l'équipe aux gardiens
        maRequete = "UPDATE gardien SET equipe = '" + idEquipeSelection + "' WHERE (idGardien = '" + idGardienSelection + "')";
        x= statement.executeUpdate(maRequete);
        // Mets la table équipe à jour avec les informations des gardiens
        maRequete = "UPDATE equipes JOIN gardien ON equipes.idEquipes = gardien.equipe SET " +
                "equipes.nbGardien = (SELECT COUNT(*) FROM gardien WHERE equipes.idEquipes = gardien.equipe GROUP BY equipe), " +
                "equipes.budget = equipes.budgetDepart - (SELECT SUM(salaire) FROM gardien WHERE equipes.idEquipes = gardien.equipe GROUP BY equipe);";
        x= statement.executeUpdate(maRequete);

        // Attribue le nom de l'équipe aux entraîneurs
        maRequete = "UPDATE entraineur SET equipe = '" + idEquipeSelection + "' WHERE (idEntraineur = '" + idEntraineurSelection + "')";
        x= statement.executeUpdate(maRequete);
        // Mets la table équipe à jour avec les informations des gardiens
        maRequete = "UPDATE equipes JOIN entraineur ON equipes.idEquipes = entraineur.equipe SET " +
                "equipes.nbEntraineur = (SELECT COUNT(*) FROM entraineur WHERE equipes.idEquipes = entraineur.equipe GROUP BY equipe), " +
                "equipes.budget = equipes.budgetDepart - (SELECT SUM(salaire) FROM entraineur WHERE equipes.idEquipes = entraineur.equipe GROUP BY equipe);";
        x= statement.executeUpdate(maRequete);

        // Mets la table équipe à jour avec les points de tous les joueurs
        maRequete = "UPDATE equipes JOIN attaque ON equipes.idEquipes = attaque.equipe JOIN defense ON equipes.idEquipes = defense.equipe " +
                "JOIN gardien ON equipes.idEquipes = gardien.equipe JOIN entraineur ON equipes.idEquipes = entraineur.equipe " +
                "SET equipes.nbPoints = " +
                "(SELECT SUM(nbPoints) FROM attaque WHERE equipes.idEquipes = attaque.equipe GROUP BY equipe) + " +
                "(SELECT SUM(nbPoints) FROM defense WHERE equipes.idEquipes = defense.equipe GROUP BY equipe) + " +
                "(SELECT SUM(nbPoints) FROM gardien WHERE equipes.idEquipes = gardien.equipe GROUP BY equipe) + " +
                "(SELECT SUM(nbPoints) FROM entraineur WHERE equipes.idEquipes = entraineur.equipe GROUP BY equipe);";
        x= statement.executeUpdate(maRequete);

        // On désélectionne les joueurs et équipes sélectionnées et on réinitialise la page
        listAttaque.getSelectionModel().clearSelection();
        listDefense.getSelectionModel().clearSelection();
        listGardien.getSelectionModel().clearSelection();
        listEntraineur.getSelectionModel().clearSelection();
        listEquipes.getSelectionModel().clearSelection();
        initialize();
    }

    /**
     * Fonction pour créer une équipe avec un nom et un budget saisi par l'utilisateur
     * après avoir cliqué sur le bouton "Créer une nouvelle équipe"
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void creerEquipe() throws IOException,SQLException {
        //Accès à la connection
        Connection connection = null;
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        ArrayList matrice = new ArrayList<ArrayList>();
        int index = 0;
        // Instancier une équipe
        Equipes newEquipe = new Equipes(strNomEquipe.getText(),
                Float.parseFloat(intBudgetEquipe.getText()));

        try{
            //faire la connection pour acceder à la BDD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                    "root","admin1234");
            //Ajouter le Statement
            Statement statement = connection.createStatement();

            String[] infosPartielNewEquipe = {newEquipe.getNom()};
            boolean equipeExiste = verifyEquipe(listNomDNEquipes(connection,statement),infosPartielNewEquipe);

            if(equipeExiste == true)
            {
                // Message si l'équipe existe
                messageAlert("L'équipe existe déjà");
            }else {
                //--- [*] Insérer un enregistrement avec paramètres ---//
                int x;
                // Utilisation de l'objet
                String maRequete = "INSERT INTO equipes (nom,budget,budgetDepart) VALUES ('"+ newEquipe.getNom() + "','"
                        + newEquipe.getBudget() + "','"
                        + newEquipe.getBudget() + "');";
                x= statement.executeUpdate(maRequete);
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }
        // recharger la scène pour faire apparaître la nouvelle équipe
        initialize();
    }

    @FXML
    public void goPageJoueurs() throws IOException {
        Stage stage = (Stage) btnPageJoueurs.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-joueurs.fxml"));
        stage.setTitle("Joueurs - créer et gérer les joueurs");
        stage.setScene(new Scene(fxmlloader, 410, 700));
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
     * Fonction qui retourne le nom d'une équipe
     * présent dans une base de données
     *
     * @param connection
     * @param statement
     * @return {ArrayList} la liste des équipes contenue dans la base de données
     * @throws SQLException
     */
    public ArrayList listNomDNEquipes(Connection connection, Statement statement)throws SQLException{
        int tailleTableLecture = 2;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        int index = 0;

        try{
            // Execution d'une requête pour aller chercher les infos dans la BDD
            ResultSet resultSetRqtLecture = statement.executeQuery("SELECT * FROM equipes");

            // Enregistrer la lecture dans une liste
            while (resultSetRqtLecture.next()){
                //Initialize each element of ArrayList with ArrayList<ArrayList<String>>
                // (Source:https://www.baeldung.com/java-multi-dimensional-arraylist)
                tableLectureData.add(new ArrayList());

                // Création d'une matrice pour stoquer les éléments nom,budget lu de la table Équipes
                tableLectureData.get(index).add(0,resultSetRqtLecture.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLecture.getString("budget"));
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
     *
     */
    public boolean verifyEquipe(ArrayList<ArrayList<String>> matrix, String[] data) {
        //boolean result = false;
        for (int i = 0; i < matrix.size(); i++) {

            /*if (matrix.get(i).get(0).toString().equals(data[0]) &
                    matrix.get(i).get(1).toString().equals(data[1]) &
                    matrix.get(i).get(2).toString().equals(data[2])) {
                return true;
            }*/

            if (matrix.get(i).get(0).toString().equals(data[0])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui ouvre une fenêtre pop-up selon le message passé en paramètres
     * @param  message {String} le message qui doit apparaître dans la fenêtre pop-up
     */
    public void messageAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Fonction pour générer toutes les listes de joueurs et d'équipe
     * dès le chargement de la scène
     */
    @FXML
    public void initialize() throws SQLException {
        // Efface toutes les données du tableau pour le réinitialiser (dans le cas où on ne change pas de scène)
        listAttaque.getItems().clear();
        listDefense.getItems().clear();
        listGardien.getItems().clear();
        listEntraineur.getItems().clear();
        listEquipes.getItems().clear();

        // Récupérer les joueurs et les équipes
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu", "root", "admin1234");
            Statement statement = connection.createStatement();
            // Afficher la liste des équipes
            ResultSet resultSet = statement.executeQuery("SELECT * FROM equipes");
            String strInfosEquipe;
            while (resultSet.next()){
                strInfosEquipe = resultSet.getString("idEquipes") + ". ÉQUIPE: " + resultSet.getString("nom")
                        + " | BUDGET: " + resultSet.getString("budget") + " | POINTS: "
                        + resultSet.getString("nbPoints");
                listEquipes.getItems().add(strInfosEquipe);
            }
            // Afficher les attaquants
            ResultSet resultSetA = statement.executeQuery("SELECT * FROM attaque");
            String strInfosAttaque;
            while (resultSetA.next()) {
                strInfosAttaque = "NOM: " + resultSetA.getString("nom") + " "
                        + resultSetA.getString("prenom") + " | SALAIRE: "
                        + resultSetA.getString("salaire") + " | POINTS: "
                        + resultSetA.getString("nbPoints") + " | ÉQUIPE: "
                        + resultSetA.getString("equipe");
                listAttaque.getItems().add(strInfosAttaque);
            }
            // Afficher les défenseurs
            ResultSet resultSetD = statement.executeQuery("SELECT * FROM defense");
            String strInfosDefense;
            while (resultSetD.next()) {
                strInfosDefense = "NOM: " + resultSetD.getString("nom") + " "
                        + resultSetD.getString("prenom") + " | SALAIRE: "
                        + resultSetD.getString("salaire") + " | POINTS: "
                        + resultSetD.getString("nbPoints") + " | ÉQUIPE: "
                        + resultSetD.getString("equipe");
                listDefense.getItems().add(strInfosDefense);
            }
            // Afficher les gardiens
            ResultSet resultSetG = statement.executeQuery("SELECT * FROM gardien");
            String strInfosGardien;
            while (resultSetG.next()) {
                strInfosGardien = "NOM: " + resultSetG.getString("nom") + " "
                        + resultSetG.getString("prenom") + " | SALAIRE: "
                        + resultSetG.getString("salaire") + " | POINTS: "
                        + resultSetG.getString("nbPoints") + " | ÉQUIPE: "
                        + resultSetG.getString("equipe");
                listGardien.getItems().add(strInfosGardien);
            }
            // Afficher les entraîneurs
            ResultSet resultSetE = statement.executeQuery("SELECT * FROM entraineur");
            String strInfosEntraineur;
            while (resultSetE.next()) {
                strInfosEntraineur = "NOM: " + resultSetE.getString("nom") + " "
                        + resultSetE.getString("prenom") + " | SALAIRE: "
                        + resultSetE.getString("salaire") + " | POINTS: "
                        + resultSetE.getString("nbPoints") + " | ÉQUIPE: "
                        + resultSetE.getString("equipe");
                listEntraineur.getItems().add(strInfosEntraineur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}