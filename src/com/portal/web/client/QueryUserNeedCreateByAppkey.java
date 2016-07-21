
package com.portal.web.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="strAppKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strTimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "strAppKey",
    "strTimeStamp"
})
@XmlRootElement(name = "QueryUserNeedCreateByAppkey")
public class QueryUserNeedCreateByAppkey {

    protected String strAppKey;
    protected String strTimeStamp;

    /**
     * Gets the value of the strAppKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAppKey() {
        return strAppKey;
    }

    /**
     * Sets the value of the strAppKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAppKey(String value) {
        this.strAppKey = value;
    }

    /**
     * Gets the value of the strTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrTimeStamp() {
        return strTimeStamp;
    }

    /**
     * Sets the value of the strTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrTimeStamp(String value) {
        this.strTimeStamp = value;
    }

}
