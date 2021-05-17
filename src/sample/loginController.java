package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

public class loginController implements Initializable {

    @FXML
    private Button loginTerugKnop;
    @FXML
    private Label loginMsgLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView loginImageView;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("@../../Images/SnailSol2.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File loginFile = new File("@../../Images/login.png");
        Image loginImage = new Image(loginFile.toURI().toString());
        loginImageView.setImage(loginImage);
    }

    public void loginKnopOnAction(ActionEvent event) {

        if (usernameTextfield.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMsgLabel.setText("Vul alstublieft uw gebruikersnaam en wachtwoord in.");
        }
    }

    public void loginTerugKnopOnAction(ActionEvent event) {
        Stage stage = (Stage) loginTerugKnop.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void validateLogin(){
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM account WHERE username = '" + usernameTextfield.getText() + "' AND password = '" + enterPasswordField.getText() + "'";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1) {
                    //loginMsgLabel.setText("Gelukt");
                    createAccountForm();
                } else {
                    loginMsgLabel.setText("Helaas niet gelukt");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("registreren.fxml"));
            Stage RegistreerStage = new Stage();
            RegistreerStage.initStyle(StageStyle.UNDECORATED);
            RegistreerStage.setScene(new Scene(root, 520, 570));
            RegistreerStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}