
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ccppHeaders" type="{urn:oasis:names:tc:wsrp:v2:types}CCPPHeaders" minOccurs="0"/>
 *         &lt;element name="clientAttributes" type="{urn:oasis:names:tc:wsrp:v2:types}NamedString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="requestVerb" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientData", propOrder = {
    "userAgent",
    "ccppHeaders",
    "clientAttributes",
    "extensions"
})
public class ClientData {

    protected String userAgent;
    protected CCPPHeaders ccppHeaders;
    protected List<NamedString> clientAttributes;
    protected List<Extension> extensions;
    @XmlAttribute
    protected String requestVerb;

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the ccppHeaders property.
     * 
     * @return
     *     possible object is
     *     {@link CCPPHeaders }
     *     
     */
    public CCPPHeaders getCcppHeaders() {
        return ccppHeaders;
    }

    /**
     * Sets the value of the ccppHeaders property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCPPHeaders }
     *     
     */
    public void setCcppHeaders(CCPPHeaders value) {
        this.ccppHeaders = value;
    }

    /**
     * Gets the value of the clientAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedString }
     * 
     * 
     */
    public List<NamedString> getClientAttributes() {
        if (clientAttributes == null) {
            clientAttributes = new ArrayList<NamedString>();
        }
        return this.clientAttributes;
    }

    /**
     * Gets the value of the extensions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extension }
     * 
     * 
     */
    public List<Extension> getExtensions() {
        if (extensions == null) {
            extensions = new ArrayList<Extension>();
        }
        return this.extensions;
    }

    /**
     * Gets the value of the requestVerb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestVerb() {
        return requestVerb;
    }

    /**
     * Sets the value of the requestVerb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestVerb(String value) {
        this.requestVerb = value;
    }

}
