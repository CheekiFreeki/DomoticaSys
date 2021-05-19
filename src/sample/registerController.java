package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
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


    public void initialize(URL url, ResourceBundle resourceBundle){
        File registerImFile = new File("@../../Images/register.png");
        Image registerImage = new Image(registerImFile.toURI().toString());
        registerImageView.setImage(registerImage);

    }

    public void registerKnopOnAction(ActionEvent event){

        if (registerSetPassword.getText().equals(registerConfirmPassword.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");

        } else {
            confirmPasswordLabel.setText("Wachtwoorden komen niet overeen!");
        }
    }

    public void registerTerugKnopOnAction(ActionEvent event){
        Stage stage = (Stage) registerTerugKnop.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){
        String voornaam = registerVoornaam.getText();
        String achternaam = registerAchternaam.getText();
        String gebruikersnaam = registerUsername.getText();
        String password = registerSetPassword.getText();

        try{
            registerMsgLabel.setText("Succesvolle registratie");

            Account account = Account.signUp(gebruikersnaam, Account.hashPassword(password), voornaam, achternaam);


            //gebruiker is ingelogd en moet nu naar hoofdscherm.

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
