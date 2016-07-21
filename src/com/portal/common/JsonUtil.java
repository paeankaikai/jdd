package com.portal.common;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
		
	public static String ToStr(Object obj) throws JsonGenerationException, JsonMappingException, IOException {		
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, obj);
		return writer.toString();
	}
}
