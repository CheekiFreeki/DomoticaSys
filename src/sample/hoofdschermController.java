package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.net.URL;

public class hoofdschermController implements Initializable {
    @FXML
    private ComboBox<String> accountOpties;
    @FXML
    private ImageView avatarIconImageView;
    @FXML
    private Hyperlink mainLoggingKnop;
    @FXML
    private Label tempLabel = new Label();
    @FXML
    private Label lightLabel = new Label();
    @FXML
    private CheckBox lied1;
    @FXML
    private CheckBox lied2;
    @FXML
    private CheckBox lied3;
    @FXML
    private CheckBox lied4;
    @FXML
    private CheckBox lied5;
    @FXML
    private Button opslaan;

    MyArduino Speler = new MyArduino();

    public static String temp = "Temperatuur wordt opgehaald...";
    public static String light = "Lichtsterkte wordt opgehaald...";

    private static Account account;

    ObservableList<String> list = FXCollections.observableArrayList("Hoofdmenu", "Accountinstellingen", "Uitloggen en sluiten");

    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File MainMenuImFile = new File("@../../Images/AvatarIcon.png");
//        Image AvatarIconImage = new Image(MainMenuImFile.toURI().toString());
//        avatarIconImageView.setImage(AvatarIconImage);
        accountOpties.setItems(list);
        accountOpties.setValue("Hoofdmenu");

        tempLabel.setText(temp);
        lightLabel.setText(light);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e ->{
            tempLabel.setText(temp);
            lightLabel.setText(light);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void comboChanged(ActionEvent event) {
        switch (accountOpties.getValue()){
            case "Uitloggen en sluiten":
                mainMenuCloseKnopOnAction();
                break;
            case "Hoofdmenu":
                //mainMenuForm();
                break;
            case "Accountinstellingen":
                Stage stage = (Stage) accountOpties.getScene().getWindow();
                stage.close();
                mainMenuAccountinstellingenForm();
                break;
        }

    }

    public void mainMenuCloseKnopOnAction() {
        Stage stage = (Stage) accountOpties.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void mainMenuPlayButton(){
        Speler.play(Main.updater);
    }

    public void mainMenuPauseButton(){
        Speler.stop();
    }

    public void mainMenuNextButton(){
        Speler.next();
    }

    public void mainMenuPrevButton(){
        Speler.previous();
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

    public void mainMenuAccountinstellingenForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("accountinstellingen.fxml"));
            Stage HoofdMenuStage = new Stage();
            HoofdMenuStage.initStyle(StageStyle.UNDECORATED);
            HoofdMenuStage.setScene(new Scene(root, 600, 400));
            HoofdMenuStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void mainMenuLoggingForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("tempgrafiek.fxml"));
            Stage HoofdMenuStage = new Stage();
            HoofdMenuStage.initStyle(StageStyle.UNDECORATED);
            HoofdMenuStage.setScene(new Scene(root, 600, 400));
            HoofdMenuStage.show();

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void opslaan()
    {
        ArrayList<String> nummers = new ArrayList<String>();
        if(lied1.isSelected())
        {
            nummers.add("1");
        }
        if(lied2.isSelected())
        {
            nummers.add("2");
        }
        if(lied3.isSelected())
        {
            nummers.add("3");
        }
        if(lied4.isSelected())
        {
            nummers.add("4");
        }
        if(lied5.isSelected())
        {
            nummers.add("5");
        }
        Speler.setNummers(nummers);
    }

    public static void setAccount(Account account) {
        hoofdschermController.account = account;
    }
}
