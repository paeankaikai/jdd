package com.portal.bean;

import java.io.Serializable;

public class JddPresentTotalInfo implements Serializable { 
    private Integer friendTotal;
    
    private Integer inTotal;
    
    private Integer outToatal;

    private static final long serialVersionUID = 1L;

	public Integer getFriendTotal() {
		return friendTotal;
	}

	public void setFriendTotal(Integer friendTotal) {
		this.friendTotal = friendTotal;
	}

	public Integer getInTotal() {
		return inTotal;
	}

	public void setInTotal(Integer inTotal) {
		this.inTotal = inTotal;
	}

	public Integer getOutToatal() {
		return outToatal;
	}

	public void setOutToatal(Integer outToatal) {
		this.outToatal = outToatal;
	}


}