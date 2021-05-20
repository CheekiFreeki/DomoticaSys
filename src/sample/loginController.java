package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

        String hashedPassword = Account.hashPassword(enterPasswordField.getText());
        try {

            if (Database.checkPassword(usernameTextfield.getText(), hashedPassword)) {
                    //loginMsgLabel.setText("Gelukt");
                // open home pagina
                createAccountForm();

                Account account = new Account(usernameTextfield.getText());

                String rpi = Database.getRpiIp(account.getUsername()),
                        arduino = Database.getArduino(account.getUsername()),
                        maxTemp = Database.getTemp(account.getUsername()),
                        minLight = Database.getLight(account.getUsername());
                if(rpi!=null)
                {
                    account.setRpi(new RaspberryPi(rpi));
                    //gui.updateTemp();
                }
                if(arduino!=null)
                {
                    Thread.sleep(1000);
                    account.setMyArduino(new MyArduino());
                    MyArduino.setArduinoCon(arduino);
                    //gui.updateLight();
                }
                if(maxTemp!=null)
                {
                    Main.maxTemp=Integer.parseInt(maxTemp);
                }
                if(minLight!=null)
                {
                    Main.minLight = Integer.parseInt(minLight);
                }

            } else {
                    loginMsgLabel.setText("Helaas niet gelukt: verkeerde combinatie wachtwoord en gebruikersnaam!");
                }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            loginMsgLabel.setText("Helaas niet gelukt: geen verbinding met Database!");
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