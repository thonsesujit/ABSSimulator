package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class MainController extends VehicleSpeed{
	@FXML private RadioButton rb1;
	@FXML private RadioButton rb2;
	@FXML private Label lb1;
	@FXML LineChart<String, Number> lineChart;
	@FXML private Button set1;
	@FXML private Button gas;
	@FXML private Button brake;
    @FXML private CategoryAxis X;

    @FXML private NumberAxis Y;
	public void set1(ActionEvent event) throws Exception {
		String message1 ="Setting";
				lb1.setText(message1);
		Stage primaryStage = new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/application/setting.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		lineChart.setCreateSymbols(false);
		
		
		
	}
	public void gas(ActionEvent event) {
		XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
		for (int i = 0 , j = 0 ; i < 100; i++ ,j++){
			String strj = Integer.toString(j);
			series.getData().add(new XYChart.Data<String, Number>(strj, i));
			
		
		lineChart.getData().addAll(series);
			
		}
		
	}
	
	
	public void brake(ActionEvent event) {
		XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
		series.getData().add(new XYChart.Data<String, Number>("800", 200));
		series.getData().add(new XYChart.Data<String, Number>("900", 500));
		series.getData().add(new XYChart.Data<String, Number>("700", 600));
		series.getData().add(new XYChart.Data<String, Number>("400",300));
		series.getData().add(new XYChart.Data<String, Number>("600", 200));
		series.getData().add(new XYChart.Data<String, Number>("100", 200));
		
		
		lineChart.getData().addAll(series);
		
		
	}
	
	public void radioSelect(ActionEvent event) {
		String message = "";
		if (rb1.isSelected()) {
			message += rb1.getText() + "\n";
			
		}
		if (rb2.isSelected()) {
			message += rb2.getText() + "\n";
			
		}
		lb1.setText(message);
	}

}



