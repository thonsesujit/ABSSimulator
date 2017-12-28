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
	//MiniPID class is used to simulate acceleration temperoraly. This will be replaced with model when the 'model class' is programed by Panda.
	public void gas(ActionEvent event) {
		MiniPID miniPID;
		miniPID = new MiniPID(0.25, 0.01, 0.4);
		miniPID.setOutputLimits(10);
		miniPID.setMaxIOutput(2);
		miniPID.setOutputRampRate(3);
		miniPID.setOutputFilter(.3);
		double target=100;
		double actual=0;
		double output=0;
		
		miniPID.setSetpoint(0);
		miniPID.setSetpoint(target);
		
		
		XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
		for (int i = 0 , j = 0 ; i < 100; i++ ,j++){
			
			output = miniPID.getOutput(actual, target);
			actual = actual + output;
			
			String strj = Integer.toString(j);//this is not used for the timebeing.
			series.getData().add(new XYChart.Data<String, Number>(strj, actual));
			
		
		lineChart.getData().addAll(series);
			
		}
		
	}
	
	
	public void brake(ActionEvent event) {
		MiniPID miniPID;
		miniPID = new MiniPID(0.25, 0.01, 0.4);
		miniPID.setOutputLimits(10);
		miniPID.setMaxIOutput(2);
		miniPID.setOutputRampRate(3);
		miniPID.setOutputFilter(.3);
		double target=0;
		double actual=100;
		double output=0;
		
		miniPID.setSetpoint(0);
		miniPID.setSetpoint(target);
		
		
		XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
		for (int i = 0 , j = 100 ; i < 100; i++ ,j++){
			
			output = miniPID.getOutput(actual, target);
			actual = actual + output;
			
			String strj = Integer.toString(j);//this is not used for the timebeing.
			series.getData().add(new XYChart.Data<String, Number>(strj, actual));
			
		
		lineChart.getData().addAll(series);
			
		}
		
		
		
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



