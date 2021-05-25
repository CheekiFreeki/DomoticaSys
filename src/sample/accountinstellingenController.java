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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

//import static sample.Database.changePass;
import static sample.Database.changeUsername;


public class accountinstellingenController implements Initializable {

    @FXML
    private Button accountinstellingenTerugKnop;
    @FXML
    private TextField changeUsernameField;
    @FXML
    private ImageView instellingenImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File instellingenFile = new File("@../../Images/SnailSol.png");
        Image instellingenImage = new Image(instellingenFile.toURI().toString());
        instellingenImageView.setImage(instellingenImage);

    }

    public void accountinstellingenTerugKnopOnAction (ActionEvent event){
        Stage stage = (Stage) accountinstellingenTerugKnop.getScene().getWindow();
        stage.close();
        mainMenuForm();
    }

    public void mainMenuForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("hoofdscherm.fxml"));
            Stage HoofdMenuStage = new Stage();
            HoofdMenuStage.initStyle(StageStyle.UNDECORATED);
            HoofdMenuStage.setScene(new Scene(root, 600, 400));
            HoofdMenuStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public static void setUsername(String oldUsername, String newUsername){
    }

    public void changeUsernameKnopOnAction(ActionEvent event){
        Account account = hoofdschermController.getAccount();
        try {
            changeUsername(account.getUsername(), changeUsernameField.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
