
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
 *         &lt;element name="strTokenID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "strTokenID",
    "strTimeStamp",
    "strAppkey"
})
@XmlRootElement(name = "ReletToken")
public class ReletToken {

    protected String strTokenID;
    protected String strTimeStamp;
    protected String strAppkey;

    /**
     * Gets the value of the strTokenID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrTokenID() {
        return strTokenID;
    }

    /**
     * Sets the value of the strTokenID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrTokenID(String value) {
        this.strTokenID = value;
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
