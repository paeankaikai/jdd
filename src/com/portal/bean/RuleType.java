package com.portal.bean;

import java.io.Serializable;

public class RuleType implements Serializable {
	
	private static final long serialVersionUID = 7494639214826166987L;

	private String typeName;
    
    private Integer typeCode;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
    
     

   
}