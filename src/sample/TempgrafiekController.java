package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class TempgrafiekController implements Initializable
{
    @FXML
    private LineChart<?, ?> graph;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
