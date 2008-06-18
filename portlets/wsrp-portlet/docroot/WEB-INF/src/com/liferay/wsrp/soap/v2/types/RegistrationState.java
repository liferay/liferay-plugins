
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistrationState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistrationState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationState" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="scheduledDestruction" type="{urn:oasis:names:tc:wsrp:v2:types}Lifetime" minOccurs="0"/>
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
@XmlType(name = "RegistrationState", propOrder = {
    "registrationState",
    "scheduledDestruction",
    "extensions"
})
@XmlSeeAlso({
    RegistrationContext.class
})
public class RegistrationState {

    protected byte[] registrationState;
    protected Lifetime scheduledDestruction;
    protected List<Extension> extensions;

    /**
     * Gets the value of the registrationState property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getRegistrationState() {
        return registrationState;
    }

    /**
     * Sets the value of the registrationState property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setRegistrationState(byte[] value) {
        this.registrationState = ((byte[]) value);
    }

    /**
     * Gets the value of the scheduledDestruction property.
     * 
     * @return
     *     possible object is
     *     {@link Lifetime }
     *     
     */
    public Lifetime getScheduledDestruction() {
        return scheduledDestruction;
    }

    /**
     * Sets the value of the scheduledDestruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lifetime }
     *     
     */
    public void setScheduledDestruction(Lifetime value) {
        this.scheduledDestruction = value;
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
