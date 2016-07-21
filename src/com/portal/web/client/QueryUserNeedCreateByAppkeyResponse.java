
package com.portal.web.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryUserNeedCreateByAppkeyResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryUserNeedCreateByAppkeyResult"
})
@XmlRootElement(name = "QueryUserNeedCreateByAppkeyResponse")
public class QueryUserNeedCreateByAppkeyResponse {

    @XmlElement(name = "QueryUserNeedCreateByAppkeyResult")
    protected String queryUserNeedCreateByAppkeyResult;

    /**
     * Gets the value of the queryUserNeedCreateByAppkeyResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryUserNeedCreateByAppkeyResult() {
        return queryUserNeedCreateByAppkeyResult;
    }

    /**
     * Sets the value of the queryUserNeedCreateByAppkeyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryUserNeedCreateByAppkeyResult(String value) {
        this.queryUserNeedCreateByAppkeyResult = value;
    }

}
