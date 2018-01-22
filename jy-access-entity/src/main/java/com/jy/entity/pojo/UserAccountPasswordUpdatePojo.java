package com.jy.entity.pojo;

import java.io.Serializable;


public class UserAccountPasswordUpdatePojo  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	   private String userAccountId;
	   
	    private String oldPassword;
	    
	    private String newPassword;

		public String getUserAccountId() {
			return userAccountId;
		}

		public void setUserAccountId(String userAccountId) {
			this.userAccountId = userAccountId;
		}

		public String getOldPassword() {
			return oldPassword;
		}

		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	    
	    
}
