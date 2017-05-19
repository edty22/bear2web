package bear2web.model;

public class Method {
	
	private int methodId;
	
	private String methodName;
	
	public String toString() {
		return "methodId=" + methodId + 
		":methodName=" + methodName ;
	}

	public int getMethodId() {
		return methodId;
	}

	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	

}
