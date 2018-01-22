package com.jy.access.unit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jy.EntityConstant;
import com.jy.entity.bo.ContractOrderBo;

public class PdfUtil {

	public static byte[] fromPDFTempletToPdfWithValue(Map<String, String> map,
			String fileName) {
		ByteArrayOutputStream bos = null;
		PdfStamper ps = null;
		Document document = null;
		byte[] result = null;
		InputStream in = null;
		try {
			in = PdfUtil.class.getClassLoader().getResourceAsStream(fileName);
			PdfReader reader = new PdfReader(in);
			bos = new ByteArrayOutputStream();
			ps = new PdfStamper(reader, bos);
			AcroFields s = ps.getAcroFields(); // 读取文本域
			BaseFont baseFont = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

			Set<String> keys = map.keySet();// map里保存要填入的数据，key和文本域的name一样
			for (String key : keys) {
				if (map.get(key) != null) {
					// 1.模板文件的大小不变；2.字体格式满足中文要求
					s.setFieldProperty(key, "textfont", baseFont, null);
					s.setFieldProperty(key, "textsize", 10, null);
					s.setField(key, map.get(key));
				}
			}
			document = new Document();
			document.open();
			ps.setFormFlattening(true);
			in.close();
			ps.close();
			result = bos.toByteArray();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			try {
				if (in != null) {
					in.close();
				}

				ps.close();
				bos.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public static byte[] fromVelocityTempletToPdfWithValue(ContractOrderBo contractOrderBo,String path) {
		ByteArrayOutputStream bos = null;
		Document document = null;
		byte[] result = null;
		
		PdfWriter pdfWriter = null;
		Reader reader = null;
		StringWriter writer = null;
		FileInputStream returnIn = null;
		try {

			VelocityEngine ve = new VelocityEngine();
			Properties properties = new Properties();
			properties.setProperty("resource.loader", "class");
			properties
					.setProperty("class.resource.loader.class",
							"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			// 初始花velocity 让设置的路径生效
			ve.init(properties);
			String velocityTemplateName = EntityConstant.DOWN_FILE_CONTRACTORDER_DIRECTORY +File.separator+EntityConstant.DOWN_FILE_CONTRACTORDER_VELOCITY_NAME;
			Template t = ve.getTemplate(velocityTemplateName);
			// 取得velocity的上下文context
			VelocityContext context = new VelocityContext();
			// 往vm中写入信息
			//context.put("name", "中国");
			//context.put("date", (new Date()).toString());
			context.put("contractOrderBo", contractOrderBo);
			writer = new StringWriter();

			// 把数据填入上下文
			t.merge(context, writer);
			String out = writer.toString();
			
			
			document = new Document(PageSize.A4, 1, 1, 1, 1);
			String pdfTemplateName = EntityConstant.DOWN_FILE_CONTRACTORDER_DIRECTORY +File.separator+EntityConstant.DOWN_FILE_CONTRACTORDER_PDF_NAME;
			//URL url = PdfUtil.class.getClassLoader().getResource(pdfTemplateName);
			//if(url != null){
		     File file = new File(path);
		     System.out.println(file);
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

			// step 3

			document.open();

			reader = new StringReader(out);

			// step 4

			XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document,
					reader);

			// step 5

			document.close();
			//File returnFile = new File(path);
			 System.out.println(file);
			 returnIn = new FileInputStream(file);
			System.out.println(returnIn.available());
			
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = returnIn.read(b)) != -1) {

				bos.write(b, 0, b.length);
			}

			result = bos.toByteArray();
			
			//}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(document != null){
					document.close();
				}
				if(bos != null){
					bos.close();	
				}
				if(returnIn != null){
					returnIn.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		return result;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return BeanUtils.describe(obj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// obj
		return null;
	}
}
