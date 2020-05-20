package javastudy0518;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private Date date;
	private String task;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	@Override
	public String toString() {
		return "Log [date=" + date + ", task=" + task + "]";
	}
	
	
}
