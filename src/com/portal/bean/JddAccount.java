package com.portal.bean;

import java.io.Serializable;


public class JddAccount implements Serializable {
    private String guid;

    private String yearNo;

    private String userId;

    private String userName;
    
    private String userCom;
    
    private String userPosition;

    private Integer lyNum;

    private Integer lyCostNum;

    private Integer cyNum;

    private Integer cyCostNum;
    
    private Integer pageNum;
    
    
    private static final long serialVersionUID = 1L;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getYearNo() {
        return yearNo;
    }

    public void setYearNo(String yearNo) {
        this.yearNo = yearNo == null ? null : yearNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getLyNum() {
        return lyNum;
    }

    public void setLyNum(Integer lyNum) {
        this.lyNum = lyNum;
    }

    public Integer getLyCostNum() {
        return lyCostNum;
    }

    public void setLyCostNum(Integer lyCostNum) {
        this.lyCostNum = lyCostNum;
    }

    public Integer getCyNum() {
        return cyNum;
    }

    public void setCyNum(Integer cyNum) {
        this.cyNum = cyNum;
    }

    public Integer getCyCostNum() {
        return cyCostNum;
    }

    public void setCyCostNum(Integer cyCostNum) {
        this.cyCostNum = cyCostNum;
    }
    
    public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        JddAccount other = (JddAccount) that;
        return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
            && (this.getYearNo() == null ? other.getYearNo() == null : this.getYearNo().equals(other.getYearNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getLyNum() == null ? other.getLyNum() == null : this.getLyNum().equals(other.getLyNum()))
            && (this.getLyCostNum() == null ? other.getLyCostNum() == null : this.getLyCostNum().equals(other.getLyCostNum()))
            && (this.getCyNum() == null ? other.getCyNum() == null : this.getCyNum().equals(other.getCyNum()))
            && (this.getCyCostNum() == null ? other.getCyCostNum() == null : this.getCyCostNum().equals(other.getCyCostNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((getYearNo() == null) ? 0 : getYearNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getLyNum() == null) ? 0 : getLyNum().hashCode());
        result = prime * result + ((getLyCostNum() == null) ? 0 : getLyCostNum().hashCode());
        result = prime * result + ((getCyNum() == null) ? 0 : getCyNum().hashCode());
        result = prime * result + ((getCyCostNum() == null) ? 0 : getCyCostNum().hashCode());
        return result;
    }
}