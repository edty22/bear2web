package bear2web.model;

public class Sector {
	
	private String sectorCode;
	
	private String sectorName;
	
	public String toString() {
		return "sectorCode=" + sectorCode +
		":sectorName=" + sectorName ;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	

}
