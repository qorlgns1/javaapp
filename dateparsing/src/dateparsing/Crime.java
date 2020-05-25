package dateparsing;

public class Crime {
	//구분,살인,강도,강간·추행,절도,폭력
	
	private String classification;
	private int salin;
	private int gangdo;
	private int ganggan;
	private int juldo;
	private int pokhang;
	
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getSalin() {
		return salin;
	}
	public void setSalin(int salin) {
		this.salin = salin;
	}
	public int getGangdo() {
		return gangdo;
	}
	public void setGangdo(int gangdo) {
		this.gangdo = gangdo;
	}
	public int getGanggan() {
		return ganggan;
	}
	public void setGanggan(int ganggan) {
		this.ganggan = ganggan;
	}
	public int getJuldo() {
		return juldo;
	}
	public void setJuldo(int juldo) {
		this.juldo = juldo;
	}
	public int getPokhang() {
		return pokhang;
	}
	public void setPokhang(int pokhang) {
		this.pokhang = pokhang;
	}
	
	@Override
	public String toString() {
		return "Crime [classification=" + classification + ", salin=" + salin + ", gangdo=" + gangdo + ", ganggan="
				+ ganggan + ", juldo=" + juldo + ", pokhang=" + pokhang + "]";
	}
	
	
	
}
