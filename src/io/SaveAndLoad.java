package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import app.Task;

public class SaveAndLoad {
	
	private static HashMap <Integer,Task> tasks = new HashMap<Integer,Task>();
	private static final String SAVEFILE = "SaveFile.txt";
	
	
	public static HashMap<Integer,Task> getTasks() {
		return tasks;
	}
	public static void setTasks(HashMap<Integer,Task> tasks) {
		SaveAndLoad.tasks = tasks;
	}
	

	public static void saveNewTask(String type, String taskname, String detail, String date, String hour, String minute, String info1, String info2, String info3, String info4){
		
		int i = 1;
		while(getTasks().containsKey(i)){
			i++;
		}
		getTasks().put( i , new Task(type, taskname, detail, date, hour, minute, info1, info2, info3, info4));
		try {
			PrintWriter print = new PrintWriter(new FileOutputStream(new File(SAVEFILE),true));
			print.println(i + "\t" + getTasks().get(i).getType() + "\t" + getTasks().get(i).getTaskname()+ "\t" + getTasks().get(i).getDetail()+ "\t" + getTasks().get(i).getDate()+ "\t" + getTasks().get(i).getHour()+ "\t" + getTasks().get(i).getMinute()+ "\t" + getTasks().get(i).getInfo1()+ "\t" + getTasks().get(i).getInfo2()+ "\t" + getTasks().get(i).getInfo3()+ "\t" + getTasks().get(i).getInfo4());
			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer, Task> loadTasks(String filename){		
		
		File file = new File(SAVEFILE);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setTasks(br); 
		return tasks;
	}
	
	private static void setTasks(BufferedReader br) {
		int t = 1;
		String str; 
		try {
			while((str = br.readLine())!= null){ 
				String[] rec = new String[12];
					for (int i = 0 ; i < rec.length ; i ++ ){
					rec = str.split("\t");
					}
				tasks.put(t++, new Task(rec[1], rec[2], rec[3], rec[4], rec[5], rec[6], rec[7], rec[8], rec[9], rec[10]));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void saveAllTasks(HashMap<Integer,Task> tasks) {

		try {
			BufferedWriter print = new BufferedWriter(new FileWriter(SAVEFILE));
			for (Integer tasknumber : tasks.keySet()) {
				print.write(tasknumber + "\t" + getTasks().get(tasknumber).getType() + "\t" + getTasks().get(tasknumber).getTaskname()+ "\t" + getTasks().get(tasknumber).getDetail()+ "\t" + getTasks().get(tasknumber).getDate()+ "\t" + getTasks().get(tasknumber).getHour()+ "\t" + getTasks().get(tasknumber).getMinute()+ "\t" + getTasks().get(tasknumber).getInfo1()+ "\t" + getTasks().get(tasknumber).getInfo2()+ "\t" + getTasks().get(tasknumber).getInfo3()+ "\t" + getTasks().get(tasknumber).getInfo4());
				print.newLine();
			} 
			print.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}