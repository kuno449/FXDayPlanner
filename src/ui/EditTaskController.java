package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import io.SaveAndLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class EditTaskController  implements Initializable{
	
	@FXML
	private Label showType;
	@FXML
	private DatePicker date;
	@FXML
	public ComboBox<String> hour;
	@FXML
	public ComboBox<String> minute;
	
	private ObservableList<String> choiceHour = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
	private ObservableList<String> choiceMinute = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
		
	@FXML
	private TextField taskname;
	@FXML
	private TextField detail;
	
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
	public void setEditTask(ActionEvent c) {
		
		String type = null;
		type = showType.getText();
		
		String newDate = null;
		try {
			LocalDate localDate = date.getValue();
			newDate = localDate.toString();
		} catch (Exception e1) {
			alertSetDate();
		}
			
		String newHour = null;
		newHour = hour.getValue();
		
		String newMinute = null;
		newMinute = minute.getValue();	
		
		String newTaskname = "no input"; 
		if (!taskname.getText().equals("")){
		newTaskname = taskname.getText();
		}
		
		String newDetail = "no input"; 
		if (!detail.getText().equals("")){
		newDetail = detail.getText();
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
		
		try{
			SaveAndLoad.getTasks().remove(InterfaceController.chosenTask);
			SaveAndLoad.saveAllTasks(SaveAndLoad.getTasks());
			SaveAndLoad.saveNewTask(type, newTaskname, newDetail, newDate, newHour, newMinute, info1, info2, info3, info4);
		} catch (Exception e ){
			alertEditTask();
		}
		closeDialog(c);
	}

	public void alertSetDate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Please set the date and time.");
		alert.showAndWait();
	}
	
	public void alertEditTask() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Error : edit the task");
		alert.showAndWait();
	}

	public void closeDialog(ActionEvent c) {
		Button b = (Button) c.getSource();
		Stage editDialog = (Stage) b.getScene().getWindow();
		editDialog.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		hour.setItems(choiceHour);
		minute.setItems(choiceMinute);
		
		showType.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getType());
		date.setPromptText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getDate());
		hour.setPromptText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getHour());
		minute.setPromptText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getMinute());
		taskname.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getTaskname());
		detail.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getDetail());
				
		setTasktype();
	}

	public void setTasktype() {
		switch (SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getType()) {
		case "Appointment": memo1.setText("Place");	memo2.setText("Partner"); memo3.setText("Purpose");	memo4.setText("To bring"); 
			input1.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo1()); input2.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo2()); input3.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo3()); input4.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo4()); break;
		case "Shopping": memo1.setText("Shop"); memo2.setText("Item1"); memo3.setText("Item2"); memo4.setText("Item3"); 
			input1.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo1()); input2.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo2()); input3.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo3()); input4.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo4()); break;
		case "Leisure": memo1.setText("Accomodation"); memo2.setText("Transport"); memo3.setText("To bring1"); memo4.setText("To bring2");
			input1.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo1()); input2.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo2()); input3.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo3()); input4.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo4()); break;
		case "Job": memo1.setText("To Do"); memo2.setText("To prepare"); memo3.setText("Client"); memo4.setText("Deadline"); 
			input1.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo1()); input2.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo2()); input3.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo3()); input4.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo4()); break;
		case "Media": memo1.setText("Media"); memo2.setText("Channnel"); memo3.setText(""); memo4.setText(""); input3.setVisible(false); input4.setVisible(false); 
			input1.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo1()); input2.setText(SaveAndLoad.getTasks().get(InterfaceController.chosenTask).getInfo2()); break;
		default: break;
		}
	}
		
}