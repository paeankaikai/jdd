package com.portal.common;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class InfoTool {

	public JSONObject getHtmlJsonByUrl(String urlTemp) {
		URL url = null;
		InputStreamReader input = null;
		HttpURLConnection conn;
		JSONObject jsonObj = null;
		try {
			url = new URL(urlTemp);
			conn = (HttpURLConnection) url.openConnection();
			input = new InputStreamReader(conn.getInputStream(), "utf-8");
			Scanner inputStream = new Scanner(input);
			StringBuffer sb = new StringBuffer();
			while (inputStream.hasNext()) {
				sb.append(inputStream.nextLine());
			}
			jsonObj = JSONObject.fromObject(sb.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return jsonObj;

	}

	public Object GerJasonInfo(String url) throws Exception {
		JSONObject jsonObject = getHtmlJsonByUrl(url);
		return jsonObject.get("result");
	}

	public Object GerJasonforPost(String url, String param) throws Exception {
		JSONObject jsonObject = sendPost(url, param);
		return jsonObject.get("reason");
	}

	/*
	 * 提交我的问题
	 */
	public JSONObject sendPost(String url, String param) {
		JSONObject jsonObj = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader( new
			// InputStreamReader(conn.getInputStream()));
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			jsonObj = JSONObject.fromObject(result.toString());
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return jsonObj;
	}

	public String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		String photo_file = path;
		System.out.println(photo_file);
		File f = new File(photo_file);
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			String imagecode = encoder.encodeBuffer(bytes).trim();
			return imagecode;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 转换两位小数
	public double getDouble(double f, int t) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(t, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// 判断是否为数字
	public boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成随机短信验证码 numberFlag 是否全数字 length 验证码长度
	 * */
	public String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		return retStr;
	}

	/**
	 * 得到当前日期
	 * **/
	public String getDates() {
		String temp_str = "";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		temp_str = sdf.format(dt);
		return temp_str;
	}

	/**
	 * 得到当前季度
	 * **/
	public int getQuarter() {
		int Quarter=1;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 1 || month == 2 || month == 3) {
			Quarter=1;
		} else if (month == 4 || month == 5 || month == 6) {
			Quarter=2;
		} else if (month == 7 || month == 8 || month == 9) {
			Quarter=3; 
		} else if (month == 10 || month == 11 || month == 12) {
			Quarter=4;
		}
		return Quarter;
	}

	/**
	 * 得到当前日期后三个月
	 * */
	public String getTYears() {
		String date;
		try {
			SimpleDateFormat matter = new SimpleDateFormat("yyyyMM");
			Calendar calendar = Calendar.getInstance();
			// 将calendar装换为Date类型
			calendar.add(Calendar.MONTH, -3);
			Date date02 = calendar.getTime();
			BigDecimal time02 = new BigDecimal(matter.format(date02));
			date = time02.toString();
			return date.substring(0, 4) + "-" + date.substring(4, 6) + "-01";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getDates();
		}

	}
	/**
	 * 转化组织绩效值
	 * A=6/6
	 * B=5/6,C=4/6,D=3/6,E=2/6,S=1/6
	 * */
	public double getPerformValue(String key){
		DecimalFormat df = new DecimalFormat("#.00");
		double date=100.00;
		double value=0.00;
		 
		if("A".equals(key)){
		 value=date;
		}else if("B".equals(key)){
		 value=83.00;
		}else if("C".equals(key)){
		 value=67.00;
		}else if("D".equals(key)){
		  value=50.00;	
		}else if("E".equals(key)){
		  value=33.00;	
		}else if("S".equals(key)){
		  value=12.00;
		}else{
			return value;
		}
		return Double.parseDouble(df.format(value));
	}
	
	/**
	 * 获取订单号
	 * */
	public String getOrderNumber(String userId){
		Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
    	String orderNumber = "A" + userId + formatter.format(date);
		return orderNumber;
	}
	
	/**
	 * 获取供应商订单号
	 * */
	public String getSupplierNumber(String userId){
		Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
    	String orderNumber = "G" + userId + formatter.format(date);
		return orderNumber;
	}

	/**
	 * 加密
	 * */
	public String md5AndBase64Encode(String object){
		String encodeStr = "error";
		byte[] tmpByte = null;
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			tmpByte = md5.digest(object.getBytes("UTF-8")); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return encodeStr;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return encodeStr;
		}
		
		BASE64Encoder base64en = new BASE64Encoder(); 
		encodeStr=base64en.encode(tmpByte);
		
		return encodeStr;
	}

}
