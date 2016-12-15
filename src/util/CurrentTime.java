package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
	
	  public static String returnDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentTime = new Date();
	    String today = formatter.format(currentTime);
	    return today;
	}
	
}