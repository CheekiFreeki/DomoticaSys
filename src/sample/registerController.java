package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ResourceBundle;


public class registerController implements Initializable {

    @FXML
    private ImageView registerImageView;
    @FXML
    private Button registerTerugKnop;
    @FXML
    private Label registerMsgLabel;
    @FXML
    private PasswordField registerSetPassword;
    @FXML
    private PasswordField registerConfirmPassword;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField registerVoornaam;
    @FXML
    private TextField registerAchternaam;
    @FXML
    private TextField registerUsername;
    @FXML
    private Hyperlink registerLoginKnop;


    public void initialize(URL url, ResourceBundle resourceBundle){
        File registerImFile = new File("@../../Images/register.png");
        Image registerImage = new Image(registerImFile.toURI().toString());
        registerImageView.setImage(registerImage);

    }

    public void registerKnopOnAction(ActionEvent event) {

        if (!registerSetPassword.getText().equals("") && !registerConfirmPassword.getText().equals("") && !registerUsername.getText().equals("") && !registerAchternaam.getText().equals("") && !registerVoornaam.getText().equals("") && (registerSetPassword.getText().equals(registerConfirmPassword.getText()))) {
            registerUser();
            confirmPasswordLabel.setText("");
            registerMsgLabel.setText("Lijkt goed te gaan");
        }

        if (registerSetPassword.getText().equals("") || registerConfirmPassword.getText().equals("") || registerUsername.getText().equals("") || registerAchternaam.getText().equals("") || registerVoornaam.getText().equals("")) {
            registerMsgLabel.setText("Vul de verplichte velden in aub.");
        } if (!registerSetPassword.getText().equals(registerConfirmPassword.getText())) {
            confirmPasswordLabel.setText("Wachtwoorden komen niet overeen!");
        }
    }

    public void registerTerugKnopOnAction(ActionEvent event){
        Stage stage = (Stage) registerTerugKnop.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerLoginKnopOnAction(ActionEvent event){
        Stage stage = (Stage) registerLoginKnop.getScene().getWindow();
        stage.close();
        loginAccountForm();
    }

    public void registerUser(){
        String voornaam = registerVoornaam.getText();
        String achternaam = registerAchternaam.getText();
        String gebruikersnaam = registerUsername.getText();
        String password = registerSetPassword.getText();

        try{
            registerMsgLabel.setText("Succesvolle registratie");

            Account account = Account.signUp(gebruikersnaam, password, voornaam, achternaam);
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loginAccountForm(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage HoofdMenuStage = new Stage();
            HoofdMenuStage.initStyle(StageStyle.UNDECORATED);
            HoofdMenuStage.setScene(new Scene(root, 600, 400));
            HoofdMenuStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
