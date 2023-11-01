package com.example.tp2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnPagePrincipale;

    @FXML
    public void switchPagePrincipale() throws IOException {
        Stage stage = (Stage) btnPagePrincipale.getScene().getWindow();
        Parent fxmlloader = FXMLLoader.load(HelloApplication.class.getResource("page-principale.fxml"));
        stage.setTitle("Page principale");
        stage.setScene(new Scene(fxmlloader, 320, 240));
    }
}