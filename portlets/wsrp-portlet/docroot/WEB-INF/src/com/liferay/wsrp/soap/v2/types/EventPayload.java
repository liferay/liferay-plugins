
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for EventPayload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventPayload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namedStringArray" type="{urn:oasis:names:tc:wsrp:v2:types}NamedStringArray" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventPayload", propOrder = {
    "namedStringArray",
    "any"
})
public class EventPayload {

    protected NamedStringArray namedStringArray;
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Gets the value of the namedStringArray property.
     * 
     * @return
     *     possible object is
     *     {@link NamedStringArray }
     *     
     */
    public NamedStringArray getNamedStringArray() {
        return namedStringArray;
    }

    /**
     * Sets the value of the namedStringArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedStringArray }
     *     
     */
    public void setNamedStringArray(NamedStringArray value) {
        this.namedStringArray = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
