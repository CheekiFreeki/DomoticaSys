package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;



public class accountinstellingenController implements Initializable {

    @FXML
    private Button accountinstellingenTerugKnop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void accountinstellingenTerugKnopOnAction (ActionEvent event){
        Stage stage = (Stage) accountinstellingenTerugKnop.getScene().getWindow();
        stage.close();
    }
}
