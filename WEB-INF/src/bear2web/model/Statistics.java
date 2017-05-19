package bear2web.model;

public class Statistics {

	private String patternCd;

	private int targetCnt;

	private int totalCnt;

	private double targetRatio;

	private int targetValue;

	private String summaryValue;

	private String dispValue;

	//== 追加GETTER
	/**
	 * 対向の数量
	 */
	public int getPairCnt() {

		return totalCnt - targetCnt;

	}

	/**
	 * 対向の比率
	 * @return
	 */
	public double getPairRatio() {

		return (getPairCnt() / totalCnt ) * 100;

	}



	//== 追加GETTERここまで

	public String getPatternCd() {
		return patternCd;
	}

	public void setPatternCd(String patternCd) {
		this.patternCd = patternCd;
	}

	public int getTargetCnt() {
		return targetCnt;
	}

	public void setTargetCnt(int targetCnt) {
		this.targetCnt = targetCnt;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public double getTargetRatio() {

		return (targetCnt / totalCnt) * 100;
	}

	public void setTargetRatio(double targetRatio) {
		this.targetRatio = targetRatio;
	}

	public int getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}

	public String getSummaryValue() {
		return summaryValue;
	}

	public void setSummaryValue(String summaryValue) {
		this.summaryValue = summaryValue;
	}

	public String getDispValue() {
		return dispValue;
	}

	public void setDispValue(String dispValue) {
		this.dispValue = dispValue;
	}




}
