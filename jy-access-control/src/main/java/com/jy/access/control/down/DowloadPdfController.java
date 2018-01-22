package com.jy.access.control.down;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jy.EntityConstant;
import com.jy.access.control.base.CommonController;
import com.jy.access.service.ContractOrderService;
import com.jy.access.service.DeviceInMakesureOrderService;
import com.jy.access.service.DeviceOutMakesureOrderService;
import com.jy.access.unit.PdfUtil;
import com.jy.entity.pojo.ContractOrder4PdfPojo;
import com.jy.entity.pojo.DeviceInMakesureOrder4PdfPojo;
import com.jy.entity.pojo.DeviceOutMakesureOrder4PdfPojo;

@Controller
@Scope("prototype")
@RequestMapping(value = "/dowload/pdf")
public class DowloadPdfController extends CommonController {

	private static final Logger log = Logger.getLogger(DowloadPdfController.class);
	@Autowired
	private DeviceInMakesureOrderService deviceInMakesureOrderService;
	@Autowired
	private DeviceOutMakesureOrderService deviceOutMakesureOrderService;
	@Autowired
	private ContractOrderService contractOrderService;



	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deviceInMakesureOrder/{dInMakeSureId}", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> downDeviceInMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dInMakeSureId") String dInMakeSureId) {
		try{
			DeviceInMakesureOrder4PdfPojo dpojo = deviceInMakesureOrderService.getDInMakesureOrderDetailById(dInMakeSureId);
			if(dpojo!= null){
				if(dpojo.isDayBillingType()){
					dpojo.setDayLeaseTerm(dpojo.getLeaseTerm());
				}else if(dpojo.isWeekBillingType()){
					dpojo.setWeekLeaseTerm(dpojo.getLeaseTerm());
				}else if(dpojo.isMonthBillingType()){
					dpojo.setMonthLeaseTerm(dpojo.getLeaseTerm());
				}
				Map<String,String> paraMap = (Map<String, String>) PdfUtil.objectToMap(dpojo);
				if(paraMap != null){
					HttpHeaders headers = new HttpHeaders();      
			        String fileName = null; 
			        StringBuffer fileNameBf = new StringBuffer();
			        fileNameBf.append(EntityConstant.PDF_FILE_NAME_DEVICEINMAKESUREORDER_NAME);
			        fileNameBf.append("(");
			        fileNameBf.append(dInMakeSureId);
			        fileNameBf.append(")");
			        fileNameBf.append(".pdf");
			        
			        fileName = new String((fileNameBf.toString()).getBytes("UTF-8"),"iso-8859-1");//解决中文乱码  
			        headers.setContentDispositionFormData("attachment", fileName);     
			        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
			        String templeFileName =  EntityConstant.SITE_PDF_FILE_PACKAGE+File.separator+EntityConstant.PDF_FILE_NAME_DEVICEINMAKESUREORDER;
			        return new ResponseEntity<byte[]>(PdfUtil.fromPDFTempletToPdfWithValue(paraMap,templeFileName),headers, HttpStatus.OK);
				}
				  
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("The DeviceInMakesureOrder4PdfPojo is null!");
	return null;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deviceOutMakesureOrder/{dOutMakeSureId}", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> downDeviceOutMakesureOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "dOutMakeSureId") String dOutMakeSureId) {
		try{
			DeviceOutMakesureOrder4PdfPojo dpojo = deviceOutMakesureOrderService.getDOutMakesureOrderDetailById(dOutMakeSureId);
			if(dpojo!= null){
				if(dpojo.isDayBillingType()){
					dpojo.setDayLeaseTerm(dpojo.getLeaseTerm());
				}else if(dpojo.isWeekBillingType()){
					dpojo.setWeekLeaseTerm(dpojo.getLeaseTerm());
				}else if(dpojo.isMonthBillingType()){
					dpojo.setMonthLeaseTerm(dpojo.getLeaseTerm());
				}
				Map<String,String> paraMap = (Map<String, String>) PdfUtil.objectToMap(dpojo);
				if(paraMap != null){
					HttpHeaders headers = new HttpHeaders();      
					String fileName = null; 
					StringBuffer fileNameBf = new StringBuffer();
					fileNameBf.append(EntityConstant.PDF_FILE_NAME_DEVICEOUTMAKESUREORDER_NAME);
					fileNameBf.append("(");
					fileNameBf.append(dOutMakeSureId);
					fileNameBf.append(")");
					fileNameBf.append(".pdf");
					
					fileName = new String((fileNameBf.toString()).getBytes("UTF-8"),"iso-8859-1");//解决中文乱码  
					headers.setContentDispositionFormData("attachment", fileName);     
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
					String templeFileName =  EntityConstant.SITE_PDF_FILE_PACKAGE+File.separator+EntityConstant.PDF_FILE_NAME_DEVICEOUTMAKESUREORDER;
					return new ResponseEntity<byte[]>(PdfUtil.fromPDFTempletToPdfWithValue(paraMap,templeFileName),headers, HttpStatus.OK);
				}
				
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("The DeviceOutMakesureOrder4PdfPojo is null!");
		return null;
	}

	
	@RequestMapping(value = "/contractOrder/{contractOrderId}", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> downContractOrder(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "contractOrderId") String contractOrderId) {
		try{
			
			ContractOrder4PdfPojo contractOrder4PdfPojo = contractOrderService.getContractOrderDetailById(contractOrderId);
			if(contractOrder4PdfPojo != null){
				HttpHeaders headers = new HttpHeaders();      
				String fileName = null; 
				StringBuffer fileNameBf = new StringBuffer();
				fileNameBf.append(EntityConstant.PDF_FILE_NAME_CONTRACTORDER_NAME);
				fileNameBf.append("(");
				fileNameBf.append(contractOrderId);
				fileNameBf.append(")");
				fileNameBf.append(".pdf");
				
				fileName = new String((fileNameBf.toString()).getBytes("UTF-8"),"iso-8859-1");//解决中文乱码  
				headers.setContentDispositionFormData("attachment", fileName);     
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
				String path = EntityConstant.DOWN_FILE_CONTRACTORDER_PDF_PATH_TEST;
				
				return new ResponseEntity<byte[]>(PdfUtil.fromVelocityTempletToPdfWithValue(contractOrder4PdfPojo, path),headers, HttpStatus.OK);
			}
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("The downContractOrder is null!");
		return null;
	}

}
