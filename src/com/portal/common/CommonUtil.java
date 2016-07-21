package com.portal.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sun.misc.BASE64Decoder;

import com.portal.bean.webservice.IntfConfDto;
import com.portal.bean.webservice.IntfNameSpace;

public class CommonUtil {
	
	public static String createGUID() {
		return UUID.randomUUID().toString();
	}

	public static String[] createGUID(int count) {
		String[] rtn = new String[count];
		for (int i = 0; i<count; count++) {
			rtn[i] = createGUID();
		}
		return rtn;
	}
	
	/**
	 * 判断列表数据为空
	 * @param lst
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List lst) {
		return lst == null || lst.size() == 0;
	}
	
	/**
	 * 判断列表数据不为空
	 * @param lst
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List lst) {
		
        return !isEmpty(lst);
    }
	
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
	/**
	 * 判断列表数据为空
	 * @param col
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection col) {
		return col == null || col.size() == 0;
	}
	
	/**
	 * 判断列表数据不为空
	 * @param col
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection col) {
        return !isEmpty(col);
    }

	/**
	 * 对日期进行指定的格式化
	 * @param date 需要处理的日期
	 * @param formatStr 格式化方式yyyyMMddHHmmss
	 * @return 
	 */
	public static String dateFormat(Date date, String formatStr) {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}
	
	/**
	 * 对日期进行指定的格式化
	 * @param date 需要处理的日期
	 * @param formatStr 格式化方式yyyyMMddHHmmss
	 * @return 
	 * @throws ParseException 
	 */
	public static Date dateFormat(String dateStr, String formatStr) throws ParseException {
		Date date = new Date() ;
	    SimpleDateFormat strToDate = new SimpleDateFormat (formatStr);
	    // parse format String to date
	    date = strToDate.parse(dateStr);
	    return date;
	}
	
	public static boolean isDate(String date)
    {
		try {
			dateFormat(date, "yyyy-MM-dd hh:mm:ss");
		} catch (Exception e) {
			return false;
		}
      
		return true;
    }
	
	/**
	 * base64字符串转化成图片  
	 * @param imgStr Base64字符串
	 * @param imgName 图片名称
	 * @param filePath 图片保存路径
	 * @return
	 */
	public static boolean GenerateImage(String imgStr, String imgName, String filePath) {
		
		OutputStream out = null;
		
		// 对字节数组字符串进行Base64解码并生成图片
		if (isEmpty(imgStr)) // 图像数据为空
			return false;
		
		imgStr = imgStr.split(",")[1];
		
		File rootFile = new File(filePath);
		
		if (!rootFile.exists() && !rootFile.isDirectory()) {
			rootFile.mkdirs();
		}
		
		// 生成jpeg图片
		String imgFilePath = filePath + imgName + ".jpg";// 新生成的图片
		
		// 判断文件是否存在
		File file = new File(imgFilePath);
		if (!file.exists()) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imgStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				
				out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * HttpURLConnection 方式调用webservice
	 * @param urlStr webService地址
	 * @param SOAPAction soap方法
	 * @param request 传递结构体
	 * @return
	 */
	public static String doRequest(String urlStr, String SOAPAction ,String request){
        HttpURLConnection connection = null;
        String rspMsg = "";
        
        try {
			URL url = new  URL(urlStr);
           
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

		    connection.setRequestProperty("SOAPAction", SOAPAction); 
			connection.setRequestProperty("Content-Length", String.valueOf(request.getBytes().length)); 
//			connection.setRequestProperty("Host", "10.86.88.137:8000"); 
			connection.setRequestProperty("Accept-Encoding", "gzip,deflate"); 
			connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8"); 
			connection.setRequestProperty("Connection", "Keep-Alive"); 
			connection.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)"); 

            connection.connect();
			
			OutputStream reqStream = connection.getOutputStream();
			reqStream.write(request.getBytes());
           
            reqStream.flush();
            reqStream.close();
 		
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
            byte[] readBuf = new byte[100];
            while (true) {
                int ret = in.read(readBuf);
                if (ret < 0)
                    break;
                bufOut.write(readBuf, 0, ret);
            }
			byte[] rspBuf = bufOut.toByteArray();
             
            rspMsg = new String(rspBuf,"utf-8");

		} catch (Exception e) {
         	rspMsg=e.toString();
        }
 
		return rspMsg;
    }
	
	/**
	 * 生成XML文件
	 * @param dataMap
	 * @param nameSpaceMap
	 * @return
	 */
	public static String createSoapXml(TreeMap<String, Object> dataMap, TreeMap<String, Object> nameSpaceMap) {
        Document document = DocumentHelper.createDocument();
        // 设置根节点
        Element root = document.addElement("soapenv:Envelope");
        
        // 设定NAMESPACE
        for (Map.Entry<String, Object> entry : nameSpaceMap.entrySet()) {
        	root.addNamespace(entry.getKey(), entry.getValue().toString());
        }
        
        // 设置DATA主区块
        Element body = root.addElement("soapenv:Body");
        
        Element sunElement = null;
        
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
        	Element element = null;
        	if ("".equals(entry.getValue())) {
        		if (sunElement != null) {
        			element = sunElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
        		} else {
        			element = body.addElement(entry.getKey().substring(3, entry.getKey().length()));
        			sunElement = element;
        		}
        		
        	} else {
        		element = sunElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
        	}
        	
        	if (entry.getValue() != null) {
                element.setText(entry.getValue().toString());
            }
        }

        return document.asXML();
    }
	

	
	/**
	 * 生成XML文件
	 * @param dataMapLst
	 * @param nameSpaceMap
	 * @return
	 */
	public static String createSoapXmlWithLst(TreeMap<String, Object> rootMap, List<TreeMap<String, Object>> dataMapLst, TreeMap<String, Object> nameSpaceMap) {
        Document document = DocumentHelper.createDocument();
        // 设置根节点
        Element root = document.addElement("soapenv:Envelope");
        
        // 设定NAMESPACE
        for (Map.Entry<String, Object> entry : nameSpaceMap.entrySet()) {
        	root.addNamespace(entry.getKey(), entry.getValue().toString());
        }
        
        // 设置DATA主区块
        Element body = root.addElement("soapenv:Body");
        
        Element baseElement = null;
        
        if (rootMap != null) {
        	
        	Element subElement = null;
        	
        	for (Map.Entry<String, Object> entry : rootMap.entrySet()) {
        		
        		Element element = null;
        		
        		if (subElement != null) {
        			element = subElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
        		} else {
        			element = body.addElement(entry.getKey().substring(3, entry.getKey().length()));
        		}
        		
        		subElement = element;
        	}
        	
        	baseElement = subElement;
        }
        
        for (TreeMap<String, Object> dataMap : dataMapLst) {
            
            Element subElement = null;
            
        	for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            	Element element = null;
            	if ("".equals(entry.getValue())) {
            		if (subElement != null) {
            			element = subElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
            		} else {
            			element = baseElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
            		}
            		subElement = element;
            	} else {
            		element = subElement.addElement(entry.getKey().substring(3, entry.getKey().length()));
            	}
            	
            	if (entry.getValue() != null) {
                    element.setText(entry.getValue().toString());
                }
            }
        }
        
        return document.asXML();
    }
	
	/**
	 * 解析接口返回XML结构数据
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> readIntfXml(String xmlContent) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();
    				
    				List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String,Object>>();
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			
            			Element subElem = (Element) subIter.next();
            			
            			if (isEmpty(subElem.getData().toString())) {
            				
            				List<Element> elementButtomLst = subElem.elements();
            				
        					HashMap<String, Object> map = new HashMap<String, Object>();
            				
            				for (Iterator buttomIter = elementButtomLst.iterator(); buttomIter.hasNext(); ) {
            					
            					Element buttomElem = (Element) buttomIter.next();
            					
            					map.put(buttomElem.getName(), buttomElem.getData());
            					
            				}
            				
        					mapLst.add(map);
            				
            			} else {
            				resultMap.put(subElem.getName(), subElem.getData());
            			}
            		}
            		
            		resultMap.put("mapLst", mapLst);
            	}
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * 解析接口返回XML返回用户权限
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> readIntfXmlRights(String xmlContent) {
		
		List<String> resultLst = new ArrayList<>();
		
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			
            			Element subElem = (Element) subIter.next();
            			
            			resultLst.add(subElem.getData().toString());
            		}
            	}
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultLst;
	}
	
	
	/**
	 * 解析接口返回XML结构数据Calendar
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> readIntfXmlCalendar(String xmlContent) {
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();
    				
//    				List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String,Object>>();
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			//return
            			Element subElem = (Element) subIter.next();
            			if (isEmpty(subElem.getData().toString())) {
            				
            				List<Element> elementButtomLst = subElem.elements();
            				List<HashMap<String, Object>> mapLstBusFinal = new ArrayList<HashMap<String,Object>>();
            				List<HashMap<String, Object>> mapLstLeaFinal = new ArrayList<HashMap<String,Object>>();
            				List<HashMap<String, Object>> mapLstResFinal = new ArrayList<HashMap<String,Object>>();
            				
        					//return 下面的各级
            				for (Iterator buttomIter = elementButtomLst.iterator(); buttomIter.hasNext(); ) {
            					
            					Element buttomElem = (Element) buttomIter.next();
            					
            					if("listBusinessInfo".equals(buttomElem.getName().toString())){
            						
            						HashMap<String,Object>tempMap=new HashMap<String,Object>();
            						List<Element> elementButtomLstLast = buttomElem.elements();
            						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
                    					Element lastElem = (Element) last.next();
                    					tempMap.put(lastElem.getName(), lastElem.getData());
                    				}
            						mapLstBusFinal.add(tempMap);
            						
            					}else if("listLeaveInfo".equals(buttomElem.getName().toString())){
            						
            						HashMap<String,Object>tempMap=new HashMap<String,Object>();
            						List<Element> elementButtomLstLast = buttomElem.elements();
            						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
                    					Element lastElem = (Element) last.next();
                    					tempMap.put(lastElem.getName(), lastElem.getData());
                    				}
            						mapLstLeaFinal.add(tempMap);
            						
            						
            					}else if("listResultInfo".equals(buttomElem.getName().toString())){
            						
            						HashMap<String,Object>tempMap=new HashMap<String,Object>();
            						List<Element> elementButtomLstLast = buttomElem.elements();
            						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
                    					Element lastElem = (Element) last.next();
                    					tempMap.put(lastElem.getName(), lastElem.getData());
                    				}
            						mapLstResFinal.add(tempMap);
            						
            					}
 
            				}
            				returnMap.put("business",mapLstBusFinal);
            				returnMap.put("leave",mapLstLeaFinal);
            				returnMap.put("result",mapLstResFinal);
            					
            			}
            			
            		}
            		
            	}
            }
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.clear();
		}
		
		return returnMap;
	}
	
	
	/**
	 * 解析接口返回XML结构数据绩效待办 任务 信息
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> readIntfXmlGLwebInfo(String xmlContent) {
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();
    				
//    				List<HashMap<String, Object>> mapLst = new ArrayList<HashMap<String,Object>>();
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			//return
            			Element subElem = (Element) subIter.next();
            			if (isEmpty(subElem.getData().toString())) {
            				
            				List<Element> elementButtomLst = subElem.elements();
            				List<HashMap<String, Object>> mapLstBackLogFinal = new ArrayList<HashMap<String,Object>>();
            				List<HashMap<String, Object>> mapLstTaskInfoFinal = new ArrayList<HashMap<String,Object>>();
            				String backLogCount="";
        					//return 下面的各级
            				for (Iterator buttomIter = elementButtomLst.iterator(); buttomIter.hasNext(); ) {
            					
            					Element buttomElem = (Element) buttomIter.next();
            					
            					if("backLogCount".equals(buttomElem.getName().toString())){
            						backLogCount=buttomElem.getData().toString();
            						
            					}else if("backLogInfos".equals(buttomElem.getName().toString())){
            						//backLogInfos
            						List<Element> backLogListLst = buttomElem.elements();
            						for (Iterator backLogListIter = backLogListLst.iterator(); backLogListIter.hasNext(); ) {
            							//backLogInfo
            							Element backLogListElem = (Element) backLogListIter.next();
            							HashMap<String,Object>tempMap=new HashMap<String,Object>();
                						List<Element> elementButtomLstLast = backLogListElem.elements();
                						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
                        					Element lastElem = (Element) last.next();
                        					tempMap.put(lastElem.getName(), lastElem.getData());
                        				}
                						mapLstBackLogFinal.add(tempMap);
            						}

            					}else if("taskInfos".equals(buttomElem.getName().toString())){
            						//taskInfos
            						List<Element> taskInfoListLst = buttomElem.elements();
            						for (Iterator taskInfoListIter = taskInfoListLst.iterator(); taskInfoListIter.hasNext(); ) {
            							//taskInfo
            							Element taskInfoListElem = (Element) taskInfoListIter.next();
	            						HashMap<String,Object>tempMap=new HashMap<String,Object>();
	            						List<Element> elementButtomLstLast = taskInfoListElem.elements();
	            						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
	                    					Element lastElem = (Element) last.next();
	                    					tempMap.put(lastElem.getName(), lastElem.getData());
	                    				}
	            						mapLstTaskInfoFinal.add(tempMap);
            						}
            						
            					}
 
            				}
            				
            				returnMap.put("backLog",mapLstBackLogFinal);
            				returnMap.put("taskInfo",mapLstTaskInfoFinal);
            				returnMap.put("backLogCount",backLogCount);
            					
            			}
            			
            		}
            		
            	}
            }

		} catch (Exception e) {
			e.printStackTrace();
			returnMap.clear();
		}
		
		return returnMap;
	}
	
	
	
	/**
	 * 解析接口返回XML结构数据绩效待办 任务 信息
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> readIntfXmlGLplanInfo(String xmlContent) {
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();				
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			//return
            			Element subElem = (Element) subIter.next();
            			if (isEmpty(subElem.getData().toString())) {
            				
            				List<Element> elementButtomLst = subElem.elements();
            				List<HashMap<String, Object>> mapLstPlanInfoFinal = new ArrayList<HashMap<String,Object>>();
            				String planCount="";
        					//return 下面的各级
            				for (Iterator buttomIter = elementButtomLst.iterator(); buttomIter.hasNext(); ) {
            					
            					Element buttomElem = (Element) buttomIter.next();
            					
            					if("planCount".equals(buttomElem.getName().toString())){
            						planCount=buttomElem.getData().toString();
            						
            					}else if("planInfos".equals(buttomElem.getName().toString())){
            						//planInfos
            						List<Element> planInfoListLst = buttomElem.elements();
            						for (Iterator planInfoListIter = planInfoListLst.iterator(); planInfoListIter.hasNext(); ) {
            							//backLogInfo
            							Element planInfoListElem = (Element) planInfoListIter.next();
            							HashMap<String,Object>tempMap=new HashMap<String,Object>();
                						List<Element> elementButtomLstLast = planInfoListElem.elements();
                						for (Iterator last = elementButtomLstLast.iterator(); last.hasNext(); ) {
                        					Element lastElem = (Element) last.next();
                        					tempMap.put(lastElem.getName(), lastElem.getData());
                        				}
                						mapLstPlanInfoFinal.add(tempMap);
            						}

            					}
 
            				}
            				
            				returnMap.put("planInfoList",mapLstPlanInfoFinal);
            				returnMap.put("planInfoCount",planCount);
            					
            			}
            			
            		}
            		
            	}
            }

		} catch (Exception e) {
			e.printStackTrace();
			returnMap.clear();
		}
		
		return returnMap;
	}
	
	
	/**
	 * 解析接口返回XML结构数据绩效  所有下属季绩效list 
	 * @param xmlContent
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, Object> readIntfXmlGLUnder(String xmlContent) {
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			Document document = DocumentHelper.parseText(xmlContent);

            Element root = document.getRootElement();
            Element baseElement = root.element("Body");
            
            if (baseElement.element("SOAP-ENV:Body") != null && baseElement.element("SOAP-ENV:Body").element("SOAP-ENV:Fault") != null) {
            	return null;
            } else {

            	List<Element> elementLst = baseElement.elements();
            	
            	for (Iterator iter = elementLst.iterator(); iter.hasNext(); ) {
            		Element elem = (Element) iter.next();
            		
            		List<Element> elementSubLst = elem.elements();				
            		
            		for (Iterator subIter = elementSubLst.iterator(); subIter.hasNext(); ) {
            			//return
            			Element subElem = (Element) subIter.next();
            			if (isEmpty(subElem.getData().toString())) {
            				
            				List<Element> elementButtomLst = subElem.elements();
            				List<HashMap<String, Object>> mapLstPlanInfoNowFinal = new ArrayList<HashMap<String,Object>>();
            				List<HashMap<String, Object>> mapLstPlanInfoPreFinal = new ArrayList<HashMap<String,Object>>();
//            				String planCount="";
        					//return 下面的各级
            				for (Iterator buttomIter = elementButtomLst.iterator(); buttomIter.hasNext(); ) {
            					
            				   Element buttomElem = (Element) buttomIter.next();
            					
            				   if("planInfoNow".equals(buttomElem.getName().toString())){
            						//planInfos
            						List<Element> planInfoListLst = buttomElem.elements();
            						HashMap<String,Object>tempMap=new HashMap<String,Object>();
                						for (Iterator last = planInfoListLst.iterator(); last.hasNext(); ) {
                        					Element lastElem = (Element) last.next();
                        					tempMap.put(lastElem.getName(), lastElem.getData());
                        				}
                						mapLstPlanInfoNowFinal.add(tempMap);
            						
            					}else if("planInfoPre".equals(buttomElem.getName().toString())){
            						//planInfos
            						List<Element> planInfoListLst = buttomElem.elements();
        							HashMap<String,Object>tempMap=new HashMap<String,Object>();
	            						for (Iterator last = planInfoListLst.iterator(); last.hasNext(); ) {
	                    					Element lastElem = (Element) last.next();
	                    					tempMap.put(lastElem.getName(), lastElem.getData());
	                    				}
	            						mapLstPlanInfoPreFinal.add(tempMap);

            					}
 
            				}
            				
            				returnMap.put("planInfoNow",mapLstPlanInfoNowFinal);
            				returnMap.put("planInfoPre",mapLstPlanInfoPreFinal);
            					
            			}
            			
            		}
            		
            	}
            }

		} catch (Exception e) {
			e.printStackTrace();
			returnMap.clear();
		}
		
		return returnMap;
	}
	/**
	 * 得到服务器时间
	 * 
	 * */
	public static String getDate(){
		Date date = new Date ();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date).toString();
	}
	
	public static String getDateT(){
		Date date = new Date ();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date).toString();
	}
   /*
    * 获得ip地址
    * */
	public static String getIPAddress(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取PS接口配置信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String, Object> getIntfConfXml(String confPath) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			SAXReader reader = new SAXReader();
			
			Document document = reader.read(new File(confPath));
			
			Element root = document.getRootElement();
			
			List<Element> list = root.elements();
			
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				
				Element elem = (Element) iter.next();
				
				IntfConfDto intfConfDto = new IntfConfDto();
				
				intfConfDto.setSoapAction(elem.element("SOAPACTION").getData().toString());
				intfConfDto.setWsdlUrl(elem.element("WSDL").getData().toString());
				intfConfDto.setRootNode(elem.element("ROOTNODE").getData().toString());
				
				if (elem.element("SUBNODES") != null) {
					List<Element> subNodeList = elem.element("SUBNODES").elements();
					
					List<String> nodeLst = new ArrayList<String>();
					for (Element subElem : subNodeList) {
						
						nodeLst.add(subElem.getText());
					}
					
					intfConfDto.setSubNodeLst(nodeLst);
				}
				
				if (elem.element("NAMESPACE") != null) {
					List<Element> nameSpaceList = elem.element("NAMESPACE").elements();
					
					List<IntfNameSpace> nameSpaceLst = new ArrayList<IntfNameSpace>();
					for (Element nameSpaceElem : nameSpaceList) {
						
						IntfNameSpace intfNameSpace = new IntfNameSpace();
						intfNameSpace.setKey(nameSpaceElem.getName());
						intfNameSpace.setValue(nameSpaceElem.getData().toString());
						
						nameSpaceLst.add(intfNameSpace);
					}
					
					intfConfDto.setNameSpaceLst(nameSpaceLst);
				}
				
				resultMap.put(elem.getName(), intfConfDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return resultMap;
	}
	
}