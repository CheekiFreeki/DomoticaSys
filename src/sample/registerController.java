package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
            //(registerSetPassword.getText().equals(registerConfirmPassword.getText()))
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
        databaseConnection connectNow = new databaseConnection();
        Connection connectDb = connectNow.getConnection();

        String voornaam = registerVoornaam.getText();
        String achternaam = registerAchternaam.getText();
        String gebruikersnaam = registerUsername.getText();
        String password = registerSetPassword.getText();

        String insertFields = "INSERT INTO account(firstname, surname, username, password) VALUES ('";
        String insertValues = voornaam + "','" + achternaam + "','" + gebruikersnaam + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);

            registerMsgLabel.setText("Succesvolle registratie");

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void loginAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage LoginStage = new Stage();
            LoginStage.initStyle(StageStyle.UNDECORATED);
            LoginStage.setScene(new Scene(root, 600, 420));
            LoginStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
