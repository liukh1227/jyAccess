package com.jy.entity.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jy.entity.bo.ContractOrderBo;
import com.jy.entity.vo.ContractDeviceModelVo;

public class ContractOrder4PdfPojo extends ContractOrderBo {
	private static final long serialVersionUID = 1L;
	private String payMethodName;
	private String includeShippingFeeStr;
	private String includeInsuranceStr;
	private String includeInvoiceStr;
	private String includeJiShouStr;
	private String includeFueStr;
	private List<ContractDeviceModel4PdfPojo> contractDeviceModelPojos;

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public String getIncludeShippingFeeStr() {
		return includeShippingFeeStr;
	}

	public void setIncludeShippingFeeStr(String includeShippingFeeStr) {
		this.includeShippingFeeStr = includeShippingFeeStr;
	}

	public String getIncludeInsuranceStr() {
		return includeInsuranceStr;
	}

	public void setIncludeInsuranceStr(String includeInsuranceStr) {
		this.includeInsuranceStr = includeInsuranceStr;
	}

	public String getIncludeInvoiceStr() {
		return includeInvoiceStr;
	}

	public void setIncludeInvoiceStr(String includeInvoiceStr) {
		this.includeInvoiceStr = includeInvoiceStr;
	}

	public String getIncludeJiShouStr() {
		return includeJiShouStr;
	}

	public void setIncludeJiShouStr(String includeJiShouStr) {
		this.includeJiShouStr = includeJiShouStr;
	}

	public String getIncludeFueStr() {
		return includeFueStr;
	}

	public void setIncludeFueStr(String includeFueStr) {
		this.includeFueStr = includeFueStr;
	}

	public List<ContractDeviceModel4PdfPojo> getContractDeviceModelPojos() {
		return contractDeviceModelPojos;
	}

	public void setContractDeviceModelPojos(
			List<ContractDeviceModel4PdfPojo> contractDeviceModelPojos) {
		this.contractDeviceModelPojos = contractDeviceModelPojos;
	}

	public void copyFrom(ContractOrderBo other) {
		if (other.getContractOrder_Id() != null) {
			setContractOrder_Id(other.getContractOrder_Id());
		} else {
			setContractOrder_Id("");
		}
		if (other.getCompanyName() != null) {
			setCompanyName(other.getCompanyName());
		} else {
			setCompanyName("");
		}

		if (other.getComTelephone() != null) {
			setComTelephone(other.getComTelephone());
		} else {
			setComTelephone("");
		}

		if (other.getComAddress() != null) {
			setComAddress(other.getComAddress());
		} else {
			setComAddress("");
		}

		if (other.getComEmail() != null) {
			setComEmail(other.getComEmail());
		} else {
			setComEmail(" ");
		}

		if (other.getComBankName() != null) {
			setComBankName(other.getComBankName());
		} else {
			setComBankName("");
		}

		if (other.getComBankAccountNum() != null) {
			setComBankAccountNum(other.getComBankAccountNum());
		} else {
			setComBankAccountNum("");
		}

		if (other.getCustomerName() != null) {
			setCustomerName(other.getCustomerName());
		} else {
			setCustomerName("");
		}

		if (other.getCusTelephone() != null) {
			setCusTelephone(other.getCusTelephone());
		} else {
			setCusTelephone("");
		}

		if (other.getCusAddress() != null) {
			setCusAddress(other.getCusAddress());
		} else {
			setCusAddress("");
		}
		if(other.getCusEmail()!= null){
			setCusEmail(other.getCusEmail());
		}else{
			setCusEmail("");
		}

		if (other.getCusBankName() != null) {
			setCusBankName(other.getCusBankName());
		} else {
			setCusBankName("");
		}

		if (other.getCusBankAccountNum() != null) {
			setCusBankAccountNum(other.getCusBankAccountNum());
		} else {
			setCusBankAccountNum("");
		}

		if (other.getProjectName() != null) {
			setProjectName(other.getProjectName());
		} else {
			setProjectName("");
		}

		if (other.getJobNature() != null) {
			setJobNature(other.getJobNature());
		} else {
			setJobNature("");
		}

		if (other.getJobNature() != null) {
			setWorkSite(other.getJobNature());
		} else {
			setWorkSite("");
		}

		if (other.getPayMethod() != null) {
			if (other.isPayMethodYuFu()) {
				setPayMethodName("预付");
			}
			if (other.isPayMethodYueJie()) {
				setPayMethodName("月结");
			}
		} else {
			setPayMethodName("");
		}

		if (other.getIncludeShippingFee() != null) {
			if (other.getIncludeShippingFee()) {
				setIncludeShippingFeeStr("包含");
			} else {
				setIncludeShippingFeeStr("不包含");
			}
		} else {
			setIncludeShippingFeeStr("");
		}

		if (other.getIncludeInsurance() != null) {
			if (other.getIncludeInsurance()) {
				setIncludeInsuranceStr("包含");
			} else {
				setIncludeInsuranceStr("不包含");
			}
		} else {
			setIncludeInsuranceStr("");
		}

		if (other.getIncludeInvoice() != null) {
			if (other.getIncludeInvoice()) {
				setIncludeInvoiceStr("包含");
			} else {
				setIncludeInvoiceStr("不包含");
			}
		} else {
			setIncludeInvoiceStr("");
		}
		if (other.getIncludeJiShou() != null) {
			if (other.getIncludeJiShou()) {
				setIncludeJiShouStr("包含");
			} else {
				setIncludeJiShouStr("不包含");
			}
		} else {
			setIncludeJiShouStr("");
		}

		if (other.getIncludeFue() != null) {
			if (other.getIncludeFue()) {
				setIncludeFueStr("包含");
			} else {
				setIncludeFueStr("不包含");
			}
		} else {
			setIncludeFueStr("");
		}
		if(other.getPrepayFee()!= null){
			setPrepayFee(other.getPrepayFee());
		}else{
			setPrepayFee(BigDecimal.ZERO);
		}
		if(other.getShippingFee()!= null){
			setShippingFee(other.getShippingFee());
		}else{
			setShippingFee(BigDecimal.ZERO);
		}

		if (other.getContractDeviceModels() != null
				&& other.getContractDeviceModels().size() > 0) {
			List<ContractDeviceModel4PdfPojo> doneList = new ArrayList<ContractDeviceModel4PdfPojo>();
			for (ContractDeviceModelVo contractDeviceModelVo : other
					.getContractDeviceModels()) {
				ContractDeviceModel4PdfPojo contractDeviceModel4PdfPojo = new ContractDeviceModel4PdfPojo();
				contractDeviceModel4PdfPojo.copyFrom(contractDeviceModelVo);
				doneList.add(contractDeviceModel4PdfPojo);
			}
			setContractDeviceModelPojos(doneList);
		}

	}

}
