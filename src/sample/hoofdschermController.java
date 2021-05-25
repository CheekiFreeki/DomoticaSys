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
    private Button playSongKnop;
    @FXML
    private ImageView playImageView;
    @FXML
    private Button pauseSongKnop;
    @FXML
    private ImageView pauseImageView;
    @FXML
    private Button nextSongKnop;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button prevSongKnop;
    @FXML
    private ImageView prevImageView;
    @FXML
    private Label tempLabel = new Label();
    @FXML
    private Label lightLabel = new Label();

    public static String temp = "Temperatuur wordt opgehaald...";
    public static String light = "Lichtsterkte wordt opgehaald...";

    private static Account account;

    ObservableList<String> list = FXCollections.observableArrayList("Hoofdmenu", "Accountinstellingen", "Uitloggen en sluiten");

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File playFile = new File("@../../Images/PlayWhite.png");
        Image playImage = new Image(playFile.toURI().toString());
        playImageView.setImage(playImage);

        File pauseFile = new File("@../../Images/PauseWhite.png");
        Image pauseImage = new Image(pauseFile.toURI().toString());
        pauseImageView.setImage(pauseImage);

        File nextFile = new File("@../../Images/NextIcon.png");
        Image nextImage = new Image(nextFile.toURI().toString());
        nextImageView.setImage(nextImage);

        File prevFile = new File("@../../Images/PrevIcon.png");
        Image prevImage = new Image(prevFile.toURI().toString());
        prevImageView.setImage(prevImage);

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
        MyArduino Speler = new MyArduino();
        Speler.play(Main.updater);
    }

    public void mainMenuPauseButton(){
        MyArduino Speler = new MyArduino();
        Speler.stop();
    }

    public void mainMenuNextButton(){
        MyArduino Speler = new MyArduino();
        Speler.resume();
    }

    public void mainMenuPrevButton(){
        MyArduino Speler = new MyArduino();
        Speler.pause();
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

    public static void setAccount(Account account) {
        hoofdschermController.account = account;
    }

    public static Account getAccount(){
        return account;
    }
}
