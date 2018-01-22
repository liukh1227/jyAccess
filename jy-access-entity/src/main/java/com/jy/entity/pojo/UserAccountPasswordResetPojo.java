package com.jy.entity.pojo;


public class UserAccountPasswordResetPojo  extends UserAccountLoginPojo{

	private static final long serialVersionUID = 1L;

	
	    
	    private String againPassword;
	    
	    private String cellPhone;
	    
	    private String validCode;

		public String getAgainPassword() {
			return againPassword;
		}

		public void setAgainPassword(String againPassword) {
			this.againPassword = againPassword;
		}

		public String getCellPhone() {
			return cellPhone;
		}

		public void setCellPhone(String cellPhone) {
			this.cellPhone = cellPhone;
		}

		public String getValidCode() {
			return validCode;
		}

		public void setValidCode(String validCode) {
			this.validCode = validCode;
		}
		
		
	    
	    
}
