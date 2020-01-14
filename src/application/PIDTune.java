package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PIDTune {
	@FXML private TextField tf1;
	@FXML private TextField tf2;
	@FXML private TextField tf3;
	@FXML private TextField tf4;
	@FXML private Button Play;
	@FXML private Button Reset;
	@FXML LineChart<String, Number> lineChart3;
	
	
	
	public void Play(ActionEvent event) {

	double P = Double.parseDouble(tf1.getText());
	double I = Double.parseDouble(tf2.getText());
	double D = Double.parseDouble(tf3.getText()); 
	MiniPID miniPID1;
	miniPID1 = new MiniPID(P,I,D);
	//miniPID.setOutputLimits(10);
	//miniPID.setMaxIOutput(2);
	//miniPID.setOutputRampRate(3);
	//miniPID.setOutputFilter(.3);
	double target= Double.parseDouble(tf4.getText()); 
	double actual=0;
	double output=0;
	
	miniPID1.setSetpoint(0);
	miniPID1.setSetpoint(target);
	
	
	lineChart3.getData().clear();
	XYChart.Series<String, Number> series1 = new XYChart.Series<>();

	lineChart3.getData().addAll(series1);
	series1.setName("PID Step Input");

	//XYChart.Series<String, Number> series =new XYChart.Series<String, Number>();
 
	
	for (int i = 0 , j = 0 ; i < 200; i++ ,j++){
		int q;
	
		
			
			
	
		
			output = miniPID1.getOutput(actual, target);
			actual = actual + output;
			q= (int) actual;
			
			String strj = Integer.toString(j);//this is not used for the timebeing.
			//series.getData().add(new XYChart.Data<String, Number>(strj, actual));
			series1.getData().add(new XYChart.Data<>(strj, actual));

		
		
		   lineChart3.setCreateSymbols(false);
		   lineChart3.setAnimated(false);
				
			}	
	
	if (Reset(event)) {
		
		series1.getData().removeAll();
		
	}
	
		        }
			
	
	
	
	
	public boolean Reset(ActionEvent event) {

	double actual=0;
	double output=0;
	return true;

		        }
		}
	
	
	
	
	
	
