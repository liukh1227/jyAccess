package com.jy.access.common;

import org.apache.commons.net.util.Base64;
import com.alibaba.fastjson.JSON;
import com.jy.base.common.Constant;
import com.jy.base.common.utils.Xxtea;

/**
 * 
 * @author liukh
 * @date 2017-7-3 下午4:37:27
 */
public class EncryptUtils {

	public static String decrypt(String data) {
		String jsonStr = "";
		try {
			byte[] dataBytes = data.getBytes("UTF-8");
			byte[] base64Byte = Base64.decodeBase64(dataBytes);
			byte[] key = Constant.KEY.getBytes();
			byte[] deByte = Xxtea.decrypt(base64Byte, key);
			data = new String(new String(deByte).getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			JSON.parseObject(data);
		} catch (Exception e1) {
			e1.printStackTrace();
			return jsonStr;
		}
		return data;
	}

	public static String encrypt(String data) {
		try {
			byte[] dataBytes = data.getBytes("UTF-8");
			byte[] key = Constant.KEY.getBytes();
			byte[] deByte = Xxtea.encrypt(dataBytes, key);
			byte[] base64Byte = Base64.encodeBase64(deByte);
			data = new String(new String(base64Byte).getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
