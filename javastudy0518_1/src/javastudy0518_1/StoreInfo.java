package javastudy0518_1;

public class StoreInfo {
	private int num;
	private String name;
	private String bm;
	private String addr;
	private String storenum;
	private String intro;
	private int tablecount;
	private boolean parking;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getStorenum() {
		return storenum;
	}
	public void setStorenum(String storenum) {
		this.storenum = storenum;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getTablecount() {
		return tablecount;
	}
	public void setTablecount(int tablecount) {
		this.tablecount = tablecount;
	}
	public boolean isParking() {
		return parking;
	}
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	@Override
	public String toString() {
		return "StoreInfo [num=" + num + ", name=" + name + ", bm=" + bm + ", addr=" + addr + ", storenum=" + storenum
				+ ", intro=" + intro + ", tablecount=" + tablecount + ", parking=" + parking + "]";
	}

	
	
	
}
