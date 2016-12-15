package ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import io.SaveAndLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTaskController extends InterfaceController implements Initializable{
	
	@FXML
	public ComboBox<String> inputType;
	public ObservableList<String> choiceType = FXCollections.observableArrayList("Appointment", "Job", "Leisure", "Shopping", "Media");
	
	@FXML 
	public DatePicker inputDate;
	
	@FXML
	public ComboBox<String> inputHour;
	@FXML
	public ComboBox<String> inputMinute;
	
	private ObservableList<String> choiceHour = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
	private ObservableList<String> choiceMinute = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
	
	@FXML
	public TextField inputTaskname;
	@FXML
	public TextArea inputDetail;
	
	@FXML 
	public Label memo1;
	@FXML 
	public Label memo2;
	@FXML 
	public Label memo3;
	@FXML 
	public Label memo4;
	
	@FXML 
	public TextField input1;
	@FXML 
	public TextField input2;
	@FXML 
	public TextField input3;
	@FXML 
	public TextField input4;
	
	@FXML 
	public DatePicker datePicker1;
	
	@FXML 
	public  Button save;
	
	
	@FXML
	public void setNewTask(ActionEvent g){

		try{				
	    	String type = null;
    		type = inputType.getValue();
	    				
			LocalDate localDate = inputDate.getValue();
			String date = localDate.toString();
				
			String hour = null;
			hour = inputHour.getValue();
			
			String minute = null;
			minute = inputMinute.getValue();	
		
			String taskname = "no input"; 
				if (!inputTaskname.getText().equals("")){
					taskname = inputTaskname.getText();
				}
			
			String taskdetail = "no input"; 
				if (!inputDetail.getText().equals("")){
					taskdetail = inputDetail.getText();
				}
			
			String info1 = "no input";
				if (!input1.getText().equals("")){
				info1 = input1.getText();
				}
			
			String info2 = "no input";
				if (!input2.getText().equals("")){
				info2 = input2.getText();
				}
			
			String info3 = "no input";
				if (!input3.getText().equals("")){
					info3 = input3.getText();
				}
			
			String info4 = "no input";
				if (!input4.getText().equals("")){
					info4 = input4.getText();
				}
			
			SaveAndLoad.saveNewTask(type, taskname, taskdetail, date, hour, minute, info1, info2, info3, info4);
	
		} catch (Exception e ){
			alertNewTask();
		}
		closeDialog(g);
	}

	public void alertNewTask() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Input Error");
		alert.setHeaderText(null);
		alert.setContentText("Error : make new task");
		alert.showAndWait();
	}

	public void closeDialog(ActionEvent g) {
		Button b = (Button) g.getSource();
		Stage dialog = (Stage) b.getScene().getWindow();
		dialog.close();
	}
	
	@FXML
	public void typeChosen(){
		
		input1.setVisible(true);
		input2.setVisible(true);
		input3.setVisible(true);
		input4.setVisible(true);
		datePicker1.setVisible(false);
		
		switch (inputType.getValue()) {
		case "Appointment": typeAppointment("a") ; break;
		case "Shopping": typeShopping("s") ; break;
		case "Leisure": typeLeisure("l") ; break;
		case "Job": typeJob("j") ; break;
		case "Media": typeMedia("m") ; break;
	
		default:	System.out.println("Unknown");
			break;
		}
	}

	public void typeAppointment(String a){
		memo1.setText("Place");
		memo2.setText("Partner");
		memo3.setText("Purpose");
		memo4.setText("To bring");
	}
	
	public void typeLeisure(String l){
		memo1.setText("Accomodation");
		memo2.setText("Transport");
		memo3.setText("To bring1");
		memo4.setText("To bring2");
	}
	
	public void typeJob(String j){
		memo1.setText("To Do");
		memo2.setText("To prepare");
		memo3.setText("Client");
		memo4.setText("Deadline");
		input4.setVisible(false);
		datePicker1.setVisible(true);
	}
	
	public void typeMedia(String m){
		memo1.setText("Media");
		memo2.setText("Channnel");
		memo3.setText("");
		memo4.setText("");
		input3.setVisible(false);
		input4.setVisible(false);
	}
	
	public void typeShopping(String s){
		memo1.setText("Shop");
		memo2.setText("Item1");
		memo3.setText("Item2");
		memo4.setText("Item3");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			inputType.setItems(choiceType);
			inputHour.setItems(choiceHour);
			inputMinute.setItems(choiceMinute);
			inputType.setPromptText("none");
			
			memo1.setText("");
			memo2.setText("");
			memo3.setText("");
			memo4.setText("");
			
			input1.setVisible(false);
			input2.setVisible(false);
			input3.setVisible(false);
			input4.setVisible(false);
			datePicker1.setVisible(false);
	}
}
