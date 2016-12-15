package ui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import io.SaveAndLoad;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import util.CurrentTime;

public class InterfaceController implements Initializable{
	
	public static int chosenTask;
    
	@FXML
	private Label showDate;
	@FXML
	private Label displayTaskname;
	@FXML
	private Label displayTime;
	@FXML
	private Label displayDetail;
	
	@FXML private TilePane tile8;
	@FXML private TilePane tile9;
	@FXML private TilePane tile10;
	@FXML private TilePane tile11;
	@FXML private TilePane tile12;
	@FXML private TilePane tile13;
	@FXML private TilePane tile14;
	@FXML private TilePane tile15;
	@FXML private TilePane tile16;
	@FXML private TilePane tile17;
	@FXML private TilePane tile18;
	@FXML private TilePane tile19;
	@FXML private TilePane tile20;
	@FXML private TilePane tile21;
	@FXML private TilePane tile22;
		
	@FXML private DatePicker chooseDate; 
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat week = new SimpleDateFormat("EEE", Locale.ENGLISH); 
    Calendar c = Calendar.getInstance();
    
    public void refresh(){
    	String date = showDate.getText();
    	date = date.substring(0, 10);
    	makeTaskList(date); 
    }
        
	public void today(){
		
		String today = CurrentTime.returnDate();
		try {
			c.setTime(formatter.parse(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        today = formatter.format(c.getTime());
        String weekday = week.format(c.getTime());
		showDate.setText(today+ " " +weekday);
		makeTaskList(today);   
	}
		
    public void previousDay(){
    	
    	String date = showDate.getText();
    	try {
			c.setTime(formatter.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        c.add(Calendar.DATE, -1);  
        date = formatter.format(c.getTime());
        String weekday = week.format(c.getTime());
        showDate.setText(date + " " +weekday);
        makeTaskList(date);   
    }
    
    public void nextDay(){
    	
    	String date = showDate.getText();
        try {
			c.setTime(formatter.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        c.add(Calendar.DATE, 1);  
        date = formatter.format(c.getTime());
        String weekday = week.format(c.getTime());
        showDate.setText(date + " " +weekday);
        makeTaskList(date);   
    }
      
    public void chooseDate(){
		  
    	LocalDate localDate = chooseDate.getValue();
		String date = localDate.toString();
	    String weekday = week.format(c.getTime());
    	showDate.setText(date+ " " +weekday);
    	makeTaskList(date);
	}
    
	@FXML
	public void newTask(ActionEvent f) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("NewTask.fxml"));
			Stage dialog = new Stage();
			Scene newTask = new Scene(root);
			newTask.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialog.setScene(newTask);
			dialog.setTitle("Make New Task");
			dialog.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void editTask(ActionEvent e){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("EditTask.fxml"));
			Stage editDialog = new Stage();
			Scene editTask = new Scene(root);
			editTask.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			editDialog.setScene(editTask);
			editDialog.setTitle("Edit Task");
			editDialog.show();
		} catch(Exception e1) {
			alertChooseTask();
		}
	}

	public void alertChooseTask() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Please choose a task.");
		alert.showAndWait();
	}
	
	@FXML
	public void deleteTask(ActionEvent d){
		
		SaveAndLoad.getTasks().remove(chosenTask);
		try {
			SaveAndLoad.saveAllTasks(SaveAndLoad.getTasks());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    
	public void makeTaskList(String date){
		 
		refreshPane();
		
		for(Integer number: SaveAndLoad.getTasks().keySet()){
			
			if(date.equals(SaveAndLoad.getTasks().get(number).getDate())){
		    
				Hyperlink link = setLink(number);     		
   				showTasks(number, link);
	   	   	}
		}
    }
	
	public void refreshPane() {

		tile8.getChildren().clear();
		tile9.getChildren().clear();
		tile10.getChildren().clear();
		tile11.getChildren().clear();
		tile12.getChildren().clear();
		tile13.getChildren().clear();
		tile14.getChildren().clear();
		tile15.getChildren().clear();
		tile16.getChildren().clear();
		tile17.getChildren().clear();
		tile18.getChildren().clear();
		tile19.getChildren().clear();
		tile20.getChildren().clear();
		tile21.getChildren().clear();
		tile22.getChildren().clear();
	}

	public Hyperlink setLink(Integer number) {
		
		Hyperlink link = new Hyperlink(SaveAndLoad.getTasks().get(number).getHour() + ":" + SaveAndLoad.getTasks().get(number).getMinute() + " / " + SaveAndLoad.getTasks().get(number).getTaskname());  
		
		link.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	chosenTask = number;
		    	displayTaskname.setText(SaveAndLoad.getTasks().get(number).getTaskname());
		        displayTime.setText(SaveAndLoad.getTasks().get(number).getDate() + " "+ SaveAndLoad.getTasks().get(number).getHour() + ":" + SaveAndLoad.getTasks().get(number).getMinute());
		        switch (SaveAndLoad.getTasks().get(number).getType()) {
			        case "Appointment": displayDetail.setText("Place : " + SaveAndLoad.getTasks().get(number).getInfo1() + "\n" + "Partner : " + SaveAndLoad.getTasks().get(number).getInfo2() + "\n" + "Purpose : " + SaveAndLoad.getTasks().get(number).getInfo3() + "\n" + "To bring : " + SaveAndLoad.getTasks().get(number).getInfo4()  + "\n" + "Detail : " + SaveAndLoad.getTasks().get(number).getDetail()); break;
			        case "Shopping": displayDetail.setText("Shop : " + SaveAndLoad.getTasks().get(number).getInfo1() + "\n" + "Item1 : " + SaveAndLoad.getTasks().get(number).getInfo2() + "\n" + "Item2 : " + SaveAndLoad.getTasks().get(number).getInfo3() + "\n" + "Item3 : " + SaveAndLoad.getTasks().get(number).getInfo4()  + "\n" + "Detail : " + SaveAndLoad.getTasks().get(number).getDetail()); break;
			        case "Leisure": displayDetail.setText("Accomodation : " + SaveAndLoad.getTasks().get(number).getInfo1() + "\n" + "Transport : " + SaveAndLoad.getTasks().get(number).getInfo2() + "\n" + "To bring1 : " + SaveAndLoad.getTasks().get(number).getInfo3() + "\n" + "To bring2 : " + SaveAndLoad.getTasks().get(number).getInfo4()  + "\n" + "Detail : " + SaveAndLoad.getTasks().get(number).getDetail()); break;
			        case "Job": displayDetail.setText("To Do : " + SaveAndLoad.getTasks().get(number).getInfo1() + "\n" + "To prepare : " + SaveAndLoad.getTasks().get(number).getInfo2() + "\n" + "Client : " + SaveAndLoad.getTasks().get(number).getInfo3() + "\n" + "Deadline : " + SaveAndLoad.getTasks().get(number).getInfo4()  + "\n" + "Detail : " + SaveAndLoad.getTasks().get(number).getDetail()); break;
			        case "Media": displayDetail.setText("Media : " + SaveAndLoad.getTasks().get(number).getInfo1() + "\n" + "Channel : " + SaveAndLoad.getTasks().get(number).getInfo2() + "\n"  + SaveAndLoad.getTasks().get(number).getDetail()); break;
			     	default: break;
				}
		 }});
		return link;
	}
	
	public void showTasks(Integer number, Hyperlink link) {
		
		switch(SaveAndLoad.getTasks().get(number).getHour()){
		 	case "01": case "02": case "03": case "04": case "05": case "06": case "07": case "08":
				 tile8.getChildren().add(link); break;
			case "09": tile9.getChildren().add(link); break;
			case "10": tile10.getChildren().add(link); break;	
			case "11": tile11.getChildren().add(link); break;
			case "12": tile12.getChildren().add(link); break;
			case "13": tile13.getChildren().add(link); break;
			case "14": tile14.getChildren().add(link); break;
			case "15": tile15.getChildren().add(link); break;
			case "16": tile16.getChildren().add(link); break;
			case "17": tile17.getChildren().add(link); break;
			case "18": tile18.getChildren().add(link); break;
		 	case "19": tile19.getChildren().add(link); break;
			case "20": tile20.getChildren().add(link); break; 
			case "21": tile21.getChildren().add(link); break; 
			case "22": case "23": case "24": tile22.getChildren().add(link); break;
		 	default: tile22.getChildren().add(link); break;
		}
	}	
	
	public void exit(){
		Platform.exit();
	}
	
	public void about(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("about this programm");
		alert.setHeaderText(null);
		alert.setContentText("This programm was build by Yoshiki Kuno, a student of the Bildung Academy CIMDATA for the purpose of education. The copyright belongs to Yoshiki Kuno, it's forbitten to copy or publish without permission.");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SaveAndLoad.loadTasks("SaveFile.txt"); 
		today();
	}
}