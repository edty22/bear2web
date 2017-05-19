package bear2web.model;

import java.util.List;

public class Pattern {

	private String patternCd;

	private String patternName;

	private String targetMarketCd;

	private String targetAtype;

	private String memo;

	private List<PatternDetail> patternDetails;

	public String getPatternCd() {
		return patternCd;
	}

	public void setPatternCd(String patternCd) {
		this.patternCd = patternCd;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public String getTargetMarketCd() {
		return targetMarketCd;
	}

	public void setTargetMarketCd(String targetMarketCd) {
		this.targetMarketCd = targetMarketCd;
	}

	public String getTargetAtype() {
		return targetAtype;
	}

	public void setTargetAtype(String targetAtype) {
		this.targetAtype = targetAtype;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<PatternDetail> getPatternDetails() {
		return patternDetails;
	}

	public void setPatternDetails(List<PatternDetail> patternDetails) {
		this.patternDetails = patternDetails;
	}



}
