package 나혼자실습;

import java.util.Date;

public class Drink {
	private int num;
	private String gender;
	private String detection;
	private String age;
	private String alcohol;
	private Date date;
	private String station;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDetection() {
		return detection;
	}
	public void setDetection(String detection) {
		this.detection = detection;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "Drink [num=" + num + ", gender=" + gender + ", detection=" + detection + ", age=" + age + ", alcohol="
				+ alcohol + ", date=" + date + ", station=" + station + "]";
	}
	
	
	
	
	
	
}
