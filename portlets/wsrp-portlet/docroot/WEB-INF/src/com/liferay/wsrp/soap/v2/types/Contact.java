
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Contact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Contact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="postal" type="{urn:oasis:names:tc:wsrp:v2:types}Postal" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:oasis:names:tc:wsrp:v2:types}Telecom" minOccurs="0"/>
 *         &lt;element name="online" type="{urn:oasis:names:tc:wsrp:v2:types}Online" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contact", propOrder = {
    "postal",
    "telecom",
    "online",
    "extensions"
})
public class Contact {

    protected Postal postal;
    protected Telecom telecom;
    protected Online online;
    protected List<Extension> extensions;

    /**
     * Gets the value of the postal property.
     * 
     * @return
     *     possible object is
     *     {@link Postal }
     *     
     */
    public Postal getPostal() {
        return postal;
    }

    /**
     * Sets the value of the postal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Postal }
     *     
     */
    public void setPostal(Postal value) {
        this.postal = value;
    }

    /**
     * Gets the value of the telecom property.
     * 
     * @return
     *     possible object is
     *     {@link Telecom }
     *     
     */
    public Telecom getTelecom() {
        return telecom;
    }

    /**
     * Sets the value of the telecom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Telecom }
     *     
     */
    public void setTelecom(Telecom value) {
        this.telecom = value;
    }

    /**
     * Gets the value of the online property.
     * 
     * @return
     *     possible object is
     *     {@link Online }
     *     
     */
    public Online getOnline() {
        return online;
    }

    /**
     * Sets the value of the online property.
     * 
     * @param value
     *     allowed object is
     *     {@link Online }
     *     
     */
    public void setOnline(Online value) {
        this.online = value;
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

}
