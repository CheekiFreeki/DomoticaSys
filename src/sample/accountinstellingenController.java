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
import static sample.Database.*;


public class accountinstellingenController implements Initializable {

    @FXML
    private Button accountinstellingenTerugKnop;
    @FXML
    private TextField changeUsernameField;
    @FXML
    private TextField changePassField;
    @FXML
    private TextField changeTempField;
    @FXML
    private TextField changeLightField;
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



    public void changeUsernameKnopOnAction(ActionEvent event){
        Account account = hoofdschermController.getAccount();
        account.setUsername(changeUsernameField.getText());
        try {
            changeUsername(account.getUsername(), changeUsernameField.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void changePassKnopOnAction(ActionEvent event)
    {
        String hashedPassword = Account.hashPassword(changePassField.getText());
        Account account = hoofdschermController.getAccount();
        try {
            changePass(account.getUsername(), hashedPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changeTempKnopOnAction(ActionEvent event)
    {
        Account account = hoofdschermController.getAccount();
        Main.maxTemp = Integer.parseInt(changeTempField.getText());
        try {
            insertTemp(Integer.parseInt(changeTempField.getText()), account.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void changeLightKnopOnAction(ActionEvent event)
    {
        Account account = hoofdschermController.getAccount();
        Main.minLight = Integer.parseInt(changeLightField.getText());
        try {
            insertLight(Integer.parseInt(changeLightField.getText()), account.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
