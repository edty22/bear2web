package bear2web.model;

/**
 * ���[�U�[�O���[�v�I�u�W�F�N�g
 * 2008/09/18
 * @author t.endo
 *
 */

public class UserGroup {
	
	private String groupId;
	
	private String groupName;
	
	public String toString() {
		return "groupId=" + groupId +
		":groupName=" + groupName ;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
	

}
