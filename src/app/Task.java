package app;

public class Task {
	
	private String type;
	private String taskname;
	private String detail;
	private String date;
	private String hour;
	private String minute;
	
	private String info1;
	private String info2;
	private String info3;
	private String info4;
	
	public Task(String type, String taskname, String detail, String date, String hour, String minute, String info1, String info2, String info3, String info4 ) {
		this.type = type;
		this.taskname = taskname;
		this.detail = detail;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.info1 = info1;
		this.info2 = info2;
		this.info3 = info3;
		this.info4 = info4;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}
	
}