package com.example.tp2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import pkgJeu.Equipes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class PageTournoisController {
    @FXML
    private Button btnLancerTournoi;

    @FXML
    private Button btnPageEquipes;

    @FXML
    private Button btnPageJoueurs;

    @FXML
    private Button btnPagePrincipale;

    @FXML
    private ListView<String> listEquipe1;

    @FXML
    private ListView<String> listEquipe2;

    @FXML
    private TextFlow resultatTournoi;

    @FXML
    public void lancerTournoi() throws IOException, SQLException {
        resultatTournoi.getChildren().clear();

        int idEquipeSelection1 = (listEquipe1.getSelectionModel().getSelectedIndex()) + 1; // nous donne l'idEquipe de Equipe 1
        int idEquipeSelection2 = (listEquipe2.getSelectionModel().getSelectedIndex()) + 1; // nous donne l'idEquipe de Equipe 2

        // Equipes participerTournois(listEquipe1, listEquipe2);
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu",
                "root","admin1234");
        Equipes equipe1 = null;
        Equipes equipe2 = null;
       try {
            int x;
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            equipe1 = getEquipes(connection, statement1, listEquipe1, idEquipeSelection1);
            equipe2 = getEquipes(connection, statement2, listEquipe2, idEquipeSelection2);
       } catch (Exception e){e.printStackTrace();}
        finally {
            //Fermer la connection
            connection.close();
        }

        Text text1 = new Text(equipe1.participerTournois(equipe1, equipe2));
        resultatTournoi.getChildren().add(text1);
    }

    private Equipes getEquipes(Connection connection, Statement statement, ListView<String> listEquipe, int idEquipe) throws SQLException {
        Equipes equipe;
        ArrayList <ArrayList<String>> arrayEquipe = new ArrayList<>();
        arrayEquipe = listInfosEquipe(connection, statement, idEquipe);

        equipe = new Equipes(arrayEquipe.get(0).get(0),Float.parseFloat(arrayEquipe.get(0).get(1).toString()),
                Integer.parseInt(arrayEquipe.get(0).get(2).toString()), Integer.parseInt(arrayEquipe.get(0).get(3).toString()),
                Integer.parseInt(arrayEquipe.get(0).get(4).toString()), Integer.parseInt(arrayEquipe.get(0).get(5).toString()),
                Integer.parseInt(arrayEquipe.get(0).get(6).toString()));

        arrayEquipe = null;

        return equipe;
    }

    public ArrayList listInfosEquipe(Connection connection, Statement statement, int indexEquipe)throws SQLException{
        int tailleTableLecture = 7;
        ArrayList <ArrayList<String>> tableLectureData = new ArrayList(tailleTableLecture);
        int index = 0;
        try {
            ResultSet resultSetRqtLectureA = statement.executeQuery("SELECT * FROM equipes WHERE idEquipes = " + indexEquipe);
            while (resultSetRqtLectureA.next()){
                tableLectureData.add(new ArrayList());
                tableLectureData.get(index).add(0,resultSetRqtLectureA.getString("nom"));
                tableLectureData.get(index).add(1,resultSetRqtLectureA.getString("budget"));
                tableLectureData.get(index).add(2,resultSetRqtLectureA.getString("nbPoints"));
                tableLectureData.get(index).add(3,resultSetRqtLectureA.getString("nbAttaque"));
                tableLectureData.get(index).add(4,resultSetRqtLectureA.getString("nbDefense"));
                tableLectureData.get(index).add(5,resultSetRqtLectureA.getString("nbGardien"));
                tableLectureData.get(index).add(6,resultSetRqtLectureA.getString("nbEntraineur"));
                index++;
            }
        } catch (Exception e){e.printStackTrace();}
        return tableLectureData;
    }

    @FXML
    public void goPageEquipes() throws IOException {
        Stage stage = (Stage) btnPageEquipes.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-equipes.fxml"));
        stage.setTitle("Équipes - créer et gérer les équipes");
        stage.setScene(new Scene(fxmlloader, 530, 730));
    }

    @FXML
    public void goPageJoueurs() throws IOException {
        Stage stage = (Stage) btnPageJoueurs.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-joueurs.fxml"));
        stage.setTitle("Joueurs - créer et gérer les joueurs");
        stage.setScene(new Scene(fxmlloader, 410, 700));
    }

    @FXML
    public void switchPagePrincipale() throws IOException {
        Stage stage = (Stage) btnPagePrincipale.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-principale.fxml"));
        stage.setTitle("Page principale");
        stage.setScene(new Scene(fxmlloader, 300, 240));
    }

    @FXML
    public void initialize() throws SQLException {
        // Afficher les équipes dans les deux listViews
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jeu", "root", "admin1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM equipes");
            String strInfosEquipes;
            while (resultSet.next()) {
                strInfosEquipes = "Équipe: " + resultSet.getString("nom")
                        + " | Attaq.: " + resultSet.getString("nbAttaque") + " | Déf.: "
                        + resultSet.getString("nbDefense") + " | Gard.: "
                        + resultSet.getString("nbGardien") + " | Entr.: "
                        + resultSet.getString("nbEntraineur");
                listEquipe1.getItems().add(strInfosEquipes);
                listEquipe2.getItems().add(strInfosEquipes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}