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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	@FXML private RadioButton abson;
	@FXML private RadioButton absoff;
	@FXML private Label lb1 , lb2 , lb3, lb4;
	@FXML LineChart<String, Number> lineChart;
	@FXML private Button set1;
	@FXML private Button gas;
	@FXML private Button brake;
	@FXML private TextField tf10;
	@FXML private TextField tf11;


	@FXML LineChart<String, Number> lineChart1;

	//User Variables*************************************************************
	double FL_BrakeP = 0.0;
	double FL_WheelVelo = 0.0;
	double vehicleVelocity =0.0;
	double FL_Decel = 0.0;
	double FL_Threshold = 0.0;
	boolean FL_pressureRelease = false;
	boolean carOn = false; 
	tf10 = new TextField();
	int xaxis = 0;
	
	//**************************************************************************
	
    
    
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
		String PR = "Brake Pressure Release";
		lb1.setText(message);
		
		//Initializing line chart
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		lineChart.getData().addAll(series1,series2);
		series1.setName("Vehicle Speed");
        series2.setName("Wheel Speed");
        lineChart.setCreateSymbols(false);
	
		
        boolean carOn = false; 

		
		//Initializing***********
		
		tf10.setText("200");
		vehicleVelocity = Double.parseDouble(tf10.getText().trim());
		//***********************************
		run();

	}
	
	public void calculate() {
			
			
		vehicleVelocity = Double.parseDouble(tf10.getText().trim());
		
		FL_WheelVelo = vehicleVelocity;
		FL_Threshold = 1.5;//Double.parseDouble(tf11.getText().trim());
		FL_BrakeP = 100/100;
		FL_Decel = 3*FL_BrakeP;
		if (FL_Decel > FL_Threshold)
		{
			// if wheels lock
			FL_pressureRelease = true;
			lb2.setText(PR);
			FL_BrakeP = FL_Threshold / 3.0;
			FL_Decel = FL_Threshold;
		}
		FL_WheelVelo -= (FL_Decel * .1 * 3.6);
		xaxis++;
		String strj = Integer.toString(xaxis);
		series1.getData().add(new XYChart.Data<>(strj, vehicleVelocity));
		series2.getData().add(new XYChart.Data<>(strj, FL_WheelVelo));
		
		// set new Vehicle Velocity after find out temp*************************************
		int temp = (int)(FL_WheelVelo);
		temp -= (FL_Decel * .1 * 3.6);
		tf10.setText(Integer.toString(temp));
		lb1.setText(message);
	}
		lineChart.getData().addAll(series1,series2);	
		
	}
	
	
	
	
	
	/*	double target=0;
		double actual = Double.parseDouble(tf10.getText()); //Vehicle speed
		double output=0;
		double j = actual;
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		lineChart.getData().addAll(series1,series2);
		series1.setName("Vehicle Speed");
        series2.setName("Wheel Speed");
        lineChart.setCreateSymbols(false);
	    
		//while (true) {
		for (int i = 0  ; i < 200; i++){
			double r = 0; 
			double s = Double.parseDouble(tf11.getText()); //Wheel Slip
			r= actual-(s*actual);
		
					
				if (j != 0) {
				j = j--;
				}
				double sd ;
				sd = (actual *actual)/(0.6*9.8*2) ;
						
				String strj = Double.toString(sd);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series1.getData().add(new XYChart.Data<>(strj, j));
				series2.getData().add(new XYChart.Data<>(strj, j));
		
		        
			//}
	
	}}*/	
	
	

	public void brake(ActionEvent event) {
		String message="Simulating ABSON";
		lb1.setText(message);
		MiniPID miniPID1;
		MiniPID miniPID2;
		miniPID1 = new MiniPID(0.05, 0.0, 0);
		miniPID2 = new MiniPID(0.01, 0.0, 0.0);
		//miniPID.setOutputLimits(10);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(3);
		//miniPID.setOutputFilter(.3);
		double target=0;
		double actual= Double.parseDouble(tf10.getText()); //Vehicle speed
		double output=0;
		double r = 0; 
		double s = Double.parseDouble(tf11.getText()); //Wheel Slip
		
		
		miniPID1.setSetpoint(0);
		miniPID1.setSetpoint(target);
		miniPID2.setSetpoint(0);
		miniPID2.setSetpoint(output);
		
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		lineChart1.getData().addAll(series1,series2);
		series1.setName("Vehicle Speed");
        series2.setName("Wheel Speed");
        lineChart1.setCreateSymbols(false);
		//XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
     
		
		for (int i = 0 , j = 0 ; i < 200; i++ ,j++){

			miniPID2.setSetpoint(output);
			double ctrlOP = r;
			ctrlOP = miniPID2.getOutput(s,0);
			r= actual-(ctrlOP*actual);
			
				output = miniPID1.getOutput(actual, target);
				actual = actual + output;
				
				
				String strj = Integer.toString(j);//this is not used for the timebeing.
				//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
				series2.getData().add(new XYChart.Data<>(strj, ctrlOP+r));
				series1.getData().add(new XYChart.Data<>(strj, actual));
			
			
			
			   
					
				
			
		        
			}
	}}
		
			
	
			
		
	
	



class Timer extends Thread 
{
	public void run()
	{
		double counter = 0;
		counter += .05;
		while(true)
		{
			
//			if (carOn == True)
//				ivjvehicleVelocityTxt.setEditable(false);
//			else
//				ivjvehicleVelocityTxt.setEditable(true);
			try {Thread.sleep(100);}catch(InterruptedException ie) {}
			if (counter >= .05)
			{
//				if (debug) ivjstatusWin.append("\nTime:" + counter);
//				if (carOn)
				{
					counter += .05;
					calculate();
//					displayGauges();
				}
				else
					counter = 0.0;
			}
			try {Thread.sleep(100);}catch(InterruptedException ie) {}
//			if ((counter >= .05) && (FL_pressureRelease == true))
//			{
//				ivjFL_pressureReleaseLbl.setBackground(java.awt.Color.darkGray);
//			}
//			if ((counter >= .05) && (FR_pressureRelease == true))
//			{
//				ivjFR_pressureReleaseLbl.setBackground(java.awt.Color.darkGray);
//			}
//			if ((counter >= .05) && (RL_pressureRelease == true))
//			{
//				ivjRL_pressureReleaseLbl.setBackground(java.awt.Color.darkGray);
//			}
//			if ((counter >= .05) && (RR_pressureRelease == true))
//			{
//				ivjRR_pressureReleaseLbl.setBackground(java.awt.Color.darkGray);
//			}
//			if ((carSpeed() < 1) || (carSpeed()> 180))
//			{
//				stopCar();
			}
			if (FL_WheelVelo == 0) break;
		}
		//stopCar();
	}
}



