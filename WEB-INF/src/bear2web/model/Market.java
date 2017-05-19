package bear2web.model;

public class Market {

	private String marketCd;

	private String marketName;

	private String type;

	private String market;

	private int unit;

	public String toString() {
		return "marketCd=" + marketCd +
		":marketName=" + marketName +
		":type=" + type +
		":market=" + market +
		":unit=" + unit +
		":";
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketCd() {
		return marketCd;
	}

	public void setMarketCd(String marketCd) {
		this.marketCd = marketCd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}




}
