
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
 *         &lt;element name="strAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strTimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "strAccount",
    "strPassword",
    "strTimeStamp",
    "strAppkey"
})
@XmlRootElement(name = "UserAuthticationByAccountPwd")
public class UserAuthticationByAccountPwd {

    protected String strAccount;
    protected String strPassword;
    protected String strTimeStamp;
    protected String strAppkey;

    /**
     * Gets the value of the strAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAccount() {
        return strAccount;
    }

    /**
     * Sets the value of the strAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAccount(String value) {
        this.strAccount = value;
    }

    /**
     * Gets the value of the strPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * Sets the value of the strPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrPassword(String value) {
        this.strPassword = value;
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

    /**
     * Gets the value of the strAppkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAppkey() {
        return strAppkey;
    }

    /**
     * Sets the value of the strAppkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAppkey(String value) {
        this.strAppkey = value;
    }

}
