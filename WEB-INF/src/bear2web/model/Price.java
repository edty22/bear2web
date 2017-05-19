package bear2web.model;

public class Price {

	/**
	 *
	 */
	private String registDate;

	/**
	 *
	 */
	private String marketCd;


	/**
	 *
	 */
	private double startPrice;

	/**
	 *
	 */
	private double highPrice;

	/**
	 *
	 */
	private double lowPrice;

	/**
	 *
	 */
	private double endPrice;

	/**
	 *
	 */
	private double volume;


	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public String getMarketCd() {
		return marketCd;
	}

	public void setMarketCd(String marketCd) {
		this.marketCd = marketCd;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 *
	 * @return
	 */
	public boolean isYousen() {
		return endPrice > startPrice;
	}

	/**
	 *
	 * @return
	 */
	public boolean isInnsen() {
		return endPrice < startPrice;
	}

	/**
	 *
	 * 0.5> 1
	 * 1.0> 2
	 *  3
	 * @return
	 */
	public double getYosenValue() {

		double result = 0;
		try {

			//
			double tmpl = (endPrice - startPrice) / endPrice * 100;
			//result = new BigDecimal(String.valueOf(tmpl)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			if (tmpl < 0.5) {
				result = 1;
			} else if (tmpl < 1) {
				result = 2;
			} else {
				result = 3;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 *
	 */
	public double getInnsenValue() {

		double result = 0;
		try {

			//
			double tmpl = (startPrice - endPrice) / endPrice * 100;
			//result = new BigDecimal(String.valueOf(tmpl)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			//System.out.println(result);

			if (tmpl < 0.5) {
				result = -1;
			} else if (tmpl < 1) {
				result = -2;
			} else {
				result = -3;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 *
	 * @return
	 */
	public double getSitahigePrice() {

		double result = 0.0;
		if (isYousen()) {
			result = startPrice - lowPrice;
		} else {
			result = endPrice - lowPrice;
		}
		return result;

	}

	/**
	 *
	 * @return
	 */
	public double getUehigePrice() {

		double result = 0.0;
		if (isYousen()) {
			result = highPrice - endPrice;
		} else {
			result = highPrice - startPrice;
		}
		return result;

	}

	/**
	 *
	 * @return
	 */
	public double getSitahigeParcent() {

		return (getSitahigePrice() / endPrice) * 100;

	}

	/**
	 *
	 * @return
	 */
	public double getUehigeParcent() {

		return ( getUehigePrice() / endPrice ) * 100;

	}

	public String toString() {

		return registDate + " : " + marketCd + " : " + startPrice + " : "
			+ highPrice + " : " + lowPrice + " : " + endPrice + " : " + volume
			+ " : ";

	}

}
