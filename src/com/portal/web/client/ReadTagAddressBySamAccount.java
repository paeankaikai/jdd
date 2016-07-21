
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
 *         &lt;element name="strSamAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "strSamAccount"
})
@XmlRootElement(name = "ReadTagAddressBySamAccount")
public class ReadTagAddressBySamAccount {

    protected String strSamAccount;

    /**
     * Gets the value of the strSamAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrSamAccount() {
        return strSamAccount;
    }

    /**
     * Sets the value of the strSamAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrSamAccount(String value) {
        this.strSamAccount = value;
    }

}
