package bear2web.model;

public class UserAccount {
	
	private int userAccountId;
	
	private String userAccount;
	
	private String userPasswd;
	
	private String userName;
	
	private int sectionId;
	
	private String sectionName;

	public int getUserAccountId() {
		return userAccountId;
	}
	
	public String toString() {
		return "userAccountId = " + userAccountId + 
		":userAccount = " + userAccount + 
		":userPasswd = " + userPasswd + 
		":userName = " + userName + 
		":sectionId = " + sectionId +
		":sectionName = " + sectionName;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	
	
	

}
