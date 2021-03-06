
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
 *         &lt;element name="MD5DecryptResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "md5DecryptResult"
})
@XmlRootElement(name = "MD5DecryptResponse")
public class MD5DecryptResponse {

    @XmlElement(name = "MD5DecryptResult")
    protected String md5DecryptResult;

    /**
     * Gets the value of the md5DecryptResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMD5DecryptResult() {
        return md5DecryptResult;
    }

    /**
     * Sets the value of the md5DecryptResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMD5DecryptResult(String value) {
        this.md5DecryptResult = value;
    }

}
