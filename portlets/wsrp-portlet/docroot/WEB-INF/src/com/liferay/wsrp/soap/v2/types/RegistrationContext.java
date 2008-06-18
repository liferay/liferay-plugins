
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistrationContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistrationContext">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:wsrp:v2:types}RegistrationState">
 *       &lt;sequence>
 *         &lt;element name="registrationHandle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrationContext", propOrder = {
    "registrationHandle"
})
public class RegistrationContext
    extends RegistrationState
{

    @XmlElement(required = true)
    protected String registrationHandle;

    /**
     * Gets the value of the registrationHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationHandle() {
        return registrationHandle;
    }

    /**
     * Sets the value of the registrationHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationHandle(String value) {
        this.registrationHandle = value;
    }

}
