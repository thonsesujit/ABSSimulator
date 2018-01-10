package application;

import javafx.event.ActionEvent;
import java.lang.*;
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

public class MainController {
	@FXML private RadioButton abson;
	@FXML private RadioButton absoff;
	@FXML private Label lb1;
	@FXML LineChart<String, Number> lineChart;
	@FXML private Button set1;
	@FXML private Button gas;
	@FXML private Button brake;
	@FXML LineChart<String, Number> lineChart1;
//    @FXML private CategoryAxis X;
//
//    @FXML private NumberAxis Y;
//    
    
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
		miniPID = new MiniPID(0.05, 0.001, 0);
		//miniPID.setOutputLimits(10);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(10);
		//miniPID.setOutputFilter(.3);
		double target=100;
		double actual=0;
		double output=0;
		
		miniPID.setSetpoint(0);
		miniPID.setSetpoint(target);
		
		
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		//XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
     
		
		for (int i = 0 , j = 0 ; i < 200; i++ ,j++){
			int q;
			
			if ( i < 100 ) {
				output = miniPID.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, q));
			
			lineChart.getData().addAll(series1,series2);
			   lineChart.setCreateSymbols(false);
					
			}
			
			if(i == 100 )
				target = 20;
			
			if ( i > 100 ) {
				
				if(i == 100 )
					target = 20;
				
				output = miniPID.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, Math.cos(q) ));
			
			lineChart.getData().addAll(series1,series2);
			   lineChart.setCreateSymbols(false);
		        series1.setName("Vehicle Speed");
		        series2.setName("Wheel Speed");
			}
			
	
			
		}
		
	}
	
	

	public void brake(ActionEvent event) {
		
		MiniPID miniPID1;
		miniPID1 = new MiniPID(0.05, 0.001, 0);
		//miniPID.setOutputLimits(10);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(3);
		//miniPID.setOutputFilter(.3);
		double target=100;
		double actual=0;
		double output=0;
		
		miniPID1.setSetpoint(0);
		miniPID1.setSetpoint(target);
		
		
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		//XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
     
		
		for (int i = 0 , j = 0 ; i < 200; i++ ,j++){
			int q;
			
			if ( i < 100 ) {
				output = miniPID1.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, q));
			
			lineChart.getData().addAll(series1,series2);
			   lineChart.setCreateSymbols(false);
					
			}
			
			if(i == 100 )
				target = 20;
			
			if ( i > 100 ) {
				
				if(i == 100 )
					target = 20;
				
				output = miniPID1.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, q^2 ));
			
			lineChart1.getData().addAll(series1,series2);
			   lineChart1.setCreateSymbols(false);
		        series1.setName("Vehicle Speed");
		        series2.setName("Wheel Speed");
			}
			
	
			
		}
		
	}
		
	

	
	// code to ABS on and ABS off selection. This has to be linked to ABS on or ABS off interface
	public void radioSelect(ActionEvent event) {
		String message = "";
		if (abson.isSelected()) {
			message += abson.getText() + "\n";
			
		}
		if (absoff.isSelected()) {
			message += absoff.getText() + "\n";
			
		}
		lb1.setText(message);
	}

}



