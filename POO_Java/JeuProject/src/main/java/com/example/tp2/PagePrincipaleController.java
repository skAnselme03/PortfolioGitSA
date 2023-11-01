package com.example.tp2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PagePrincipaleController {
    @FXML
    private Button btnEquipes;

    @FXML
    private Button btnJoueurs;

    @FXML
    private Button btnTournoi;

    @FXML
    public void goPageJoueurs() throws IOException {
        Stage stage = (Stage) btnJoueurs.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-joueurs.fxml"));
        stage.setTitle("Joueurs - créer et gérer les joueurs");
        stage.setScene(new Scene(fxmlloader, 410, 700));
    }

    @FXML
    public void goPageEquipes() throws IOException {
        Stage stage = (Stage) btnEquipes.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-equipes.fxml"));
        stage.setTitle("Équipes - créer et gérer les équipes");
        stage.setScene(new Scene(fxmlloader, 530, 730));
    }

    @FXML
    public void goPageTournoi() throws IOException {
        Stage stage = (Stage) btnTournoi.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-tournois.fxml"));
        stage.setTitle("Tournoi - organiser et lancer un tournoi");
        stage.setScene(new Scene(fxmlloader, 450, 800));
    }
}