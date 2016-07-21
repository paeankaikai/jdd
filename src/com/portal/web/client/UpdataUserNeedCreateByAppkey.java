
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
 *         &lt;element name="strUPN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "strUPN",
    "strAppKey",
    "strAppAccount",
    "strTimeStamp"
})
@XmlRootElement(name = "UpdataUserNeedCreateByAppkey")
public class UpdataUserNeedCreateByAppkey {

    protected String strUPN;
    protected String strAppKey;
    protected String strAppAccount;
    protected String strTimeStamp;

    /**
     * Gets the value of the strUPN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrUPN() {
        return strUPN;
    }

    /**
     * Sets the value of the strUPN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrUPN(String value) {
        this.strUPN = value;
    }

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
     * Gets the value of the strAppAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAppAccount() {
        return strAppAccount;
    }

    /**
     * Sets the value of the strAppAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAppAccount(String value) {
        this.strAppAccount = value;
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
