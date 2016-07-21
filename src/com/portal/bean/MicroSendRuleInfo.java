package com.portal.bean;

import java.io.Serializable;

public class MicroSendRuleInfo implements Serializable {

    private String microSendNum;

    private String microSendCount;

    private Integer microSendNumEnable; //0不启用 1启用
    
    private Integer microSendCountEnable; //0不启用 1启用

    private static final long serialVersionUID = 1L;

	public String getMicroSendNum() {
		return microSendNum;
	}

	public void setMicroSendNum(String microSendNum) {
		this.microSendNum = microSendNum;
	}

	public String getMicroSendCount() {
		return microSendCount;
	}

	public void setMicroSendCount(String microSendCount) {
		this.microSendCount = microSendCount;
	}

	public Integer getMicroSendNumEnable() {
		return microSendNumEnable;
	}

	public void setMicroSendNumEnable(Integer microSendNumEnable) {
		this.microSendNumEnable = microSendNumEnable;
	}

	public Integer getMicroSendCountEnable() {
		return microSendCountEnable;
	}

	public void setMicroSendCountEnable(Integer microSendCountEnable) {
		this.microSendCountEnable = microSendCountEnable;
	}


}