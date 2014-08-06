package zgl.test.nav;

public class POIInfo {
	String name;
	boolean isIndoor;
	String locator;
	String network;
	String floorId;
	
	public POIInfo(){
		this(null, false, null, null, null);
	}
	
	public POIInfo(String name, boolean isIndoor, String locator, String network, String floorId){
		this.name = name;
		this.isIndoor = isIndoor;
		this.locator = locator;
		this.network = network;
		this.floorId = floorId;
	}
	
	
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public boolean isIndoor() {
		return isIndoor;
	}
	public void setIndoor(boolean isIndoor) {
		this.isIndoor = isIndoor;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	
}
