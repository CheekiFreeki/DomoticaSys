package sample;

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

    ObservableList<String> list = FXCollections.observableArrayList("Hoofdmenu", "Accountinstellingen", "Uitloggen en sluiten");

    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File MainMenuImFile = new File("@../../Images/AvatarIcon.png");
//        Image AvatarIconImage = new Image(MainMenuImFile.toURI().toString());
//        avatarIconImageView.setImage(AvatarIconImage);
        accountOpties.setItems(list);
        accountOpties.setValue("Hoofdmenu");

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
        Speler.play();
    }

    public void mainMenuPauseButton(){
        MyArduino Speler = new MyArduino();
        Speler.stop();
    }

    public void mainMenuNextButton(){
        MyArduino Speler = new MyArduino();
        Speler.play();
    }

    public void mainMenuPrevButton(){
        MyArduino Speler = new MyArduino();
        Speler.play();
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


    private Account account;

    public void updateTemp()
    {
        try
        {
            if (account.getRpi().getLaatsteTemp()>0.0)
            {
                /*
                tempLabel.setText("actuele temperatuur: " + Double.toString(account.getRpi().getLaatsteTemp()));
                if (account.getRpi().getLaatsteTemp()>Main.maxTemp)
                {
                    this.verwarmingLabel.setText("verwarming staat uit.");
                    account.getMyArduino().verwarmingUit();
                }
                else
                {
                    this.verwarmingLabel.setText("verwarming staat aan.");
                    account.getMyArduino().verwarmingAan();
                }

                 */
            }
            else
            {
            /*
                this.tempLabel.setText("Geen verbinding met RPI.");
                this.verwarmingLabel.setText("verwarming staat uit.");
                account.getMyArduino().verwarmingUit();

                 */
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateLight()
    {
        try
        {
            String sLight = account.getMyArduino().getLight();
            if (sLight.equals("")) return;
            /*
            lightLabel.setText("actuele lichtsterkte: "+sLight);
            int iLight = Integer.parseInt(sLight.replaceAll("[^0-9]", ""));
            if(iLight<Main.minLight)
            {
                account.getMyArduino().lampAan();
                lampLabel.setText("Lamp is aan.");
            }
            else
            {
                lampLabel.setText("Lamp is uit.");
                account.getMyArduino().lampUit();
            }

             */
        }
        catch (Exception e)
        {
            /*
            lightLabel.setText("Arduino niet aangesloten.");
            lampLabel.setText("Lamp is uit.");
            account.getMyArduino().lampUit();

            e.printStackTrace();

             */
        }
    }
}
