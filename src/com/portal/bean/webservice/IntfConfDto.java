package com.portal.bean.webservice;

import java.util.List;

public class IntfConfDto {

	public String wsdlUrl;
	public String soapAction;
	public String rootNode;
	public List<String> subNodeLst;
	public List<IntfNameSpace> nameSpaceLst;

	/**
	 * @return the wsdlUrl
	 */
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	/**
	 * @param wsdlUrl the wsdlUrl to set
	 */
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
	/**
	 * @return the soapAction
	 */
	public String getSoapAction() {
		return soapAction;
	}
	/**
	 * @param soapAction the soapAction to set
	 */
	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}
	/**
	 * @return the rootNode
	 */
	public String getRootNode() {
		return rootNode;
	}
	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(String rootNode) {
		this.rootNode = rootNode;
	}
	/**
	 * @return the subNodeLst
	 */
	public List<String> getSubNodeLst() {
		return subNodeLst;
	}
	/**
	 * @param subNodeLst the subNodeLst to set
	 */
	public void setSubNodeLst(List<String> subNodeLst) {
		this.subNodeLst = subNodeLst;
	}
	/**
	 * @return the nameSpaceLst
	 */
	public List<IntfNameSpace> getNameSpaceLst() {
		return nameSpaceLst;
	}
	/**
	 * @param nameSpaceLst the nameSpaceLst to set
	 */
	public void setNameSpaceLst(List<IntfNameSpace> nameSpaceLst) {
		this.nameSpaceLst = nameSpaceLst;
	}
}