package application;

import javafx.event.ActionEvent;
import java.lang.*;

import VehicleSimulation.ABS;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	@FXML private RadioButton abson;
	@FXML private RadioButton absoff;
	@FXML private Label lb1;
	@FXML LineChart<String, Number> lineChart;
	@FXML private Button set1;
	@FXML private Button gas;
	@FXML private Button brake;
	@FXML private TextField tf10;
	@FXML private TextField tf11;


	@FXML LineChart<String, Number> lineChart1;


    
    
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
		String message="Simulating ABSOFF";
		lb1.setText(message);
		
		
		
		
		
		MiniPID miniPID;
		miniPID = new MiniPID(0.1, 0.001, 0);
		MiniPID miniPID2;
		miniPID2 = new MiniPID(0.03, 0, 0);
		miniPID.setOutputLimits(0);
		miniPID.setMaxIOutput(2);
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
        series1.setName("Vehicle Speed with gas");
        series2.setName("Wheel Speed with gas");

		lineChart.getData().addAll(series1,series2);
		   lineChart.setCreateSymbols(false);
        
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
			

					
			}
			if(i == 100 )
				target = 0;
		
			if ( i > 100 ) {
				
				
				output = miniPID.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, Math.abs(q - 2*q)));
			
		

			}
			
	
			
		}
		
	}
	
	

	public void brake(ActionEvent event) {
		String message="Simulating ABSON";
		lb1.setText(message);
/*		System.out.println(new ABS().calculate());
		ABS a = new ABS();
		Object aa = new ABS().calculate();*/
		MiniPID miniPID1;
		miniPID1 = new MiniPID(0.05, 0.0, 0);
		//miniPID.setOutputLimits(10);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(3);
		//miniPID.setOutputFilter(.3);
		double target=0;
		double actual=110;
		double output=0;
		
		miniPID1.setSetpoint(0);
		miniPID1.setSetpoint(target);
		
		
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		lineChart1.getData().addAll(series1,series2);
		series1.setName("Vehicle Speed");
        series2.setName("Wheel Speed");
		//XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
     
		
		for (int i = 0 , j = 0 ; i < 200; i++ ,j++){
			int q;
		
			
				
				
		
			
				output = miniPID1.getOutput(actual, target);
				actual = actual + output;
				q= (int) actual;
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, q-(0.2*q)));
			
			
			   lineChart1.setCreateSymbols(false);
					
				
			
		        
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
	
	



