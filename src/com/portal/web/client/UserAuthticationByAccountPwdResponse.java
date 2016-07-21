
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
 *         &lt;element name="UserAuthticationByAccountPwdResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "userAuthticationByAccountPwdResult"
})
@XmlRootElement(name = "UserAuthticationByAccountPwdResponse")
public class UserAuthticationByAccountPwdResponse {

    @XmlElement(name = "UserAuthticationByAccountPwdResult")
    protected String userAuthticationByAccountPwdResult;

    /**
     * Gets the value of the userAuthticationByAccountPwdResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAuthticationByAccountPwdResult() {
        return userAuthticationByAccountPwdResult;
    }

    /**
     * Sets the value of the userAuthticationByAccountPwdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAuthticationByAccountPwdResult(String value) {
        this.userAuthticationByAccountPwdResult = value;
    }

}
