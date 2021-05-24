package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TempgrafiekController implements Initializable
{
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?, ?> graph;
    @FXML
    private Button backButton;

    ArrayList<Double> logArray;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        XYChart.Series series = new XYChart.Series();
        try {
            logArray = Database.getTempLog(1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        for (int i = 0; i<10; i++)
        {
            System.out.println(i);
            series.getData().add(new XYChart.Data(String.valueOf(i), logArray.get(i)));
        }
        graph.getData().addAll(series);
    }

    public void goBack(ActionEvent event)
    {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
