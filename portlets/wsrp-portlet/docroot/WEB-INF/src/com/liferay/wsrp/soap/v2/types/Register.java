
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for register complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="register">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationData" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationData"/>
 *         &lt;element name="lifetime" type="{urn:oasis:names:tc:wsrp:v2:types}Lifetime"/>
 *         &lt;element name="userContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "register", propOrder = {
    "registrationData",
    "lifetime",
    "userContext"
})
public class Register {

    @XmlElement(required = true)
    protected RegistrationData registrationData;
    @XmlElement(required = true, nillable = true)
    protected Lifetime lifetime;
    @XmlElement(required = true, nillable = true)
    protected UserContext userContext;

    /**
     * Gets the value of the registrationData property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationData }
     *     
     */
    public RegistrationData getRegistrationData() {
        return registrationData;
    }

    /**
     * Sets the value of the registrationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationData }
     *     
     */
    public void setRegistrationData(RegistrationData value) {
        this.registrationData = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link Lifetime }
     *     
     */
    public Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lifetime }
     *     
     */
    public void setLifetime(Lifetime value) {
        this.lifetime = value;
    }

    /**
     * Gets the value of the userContext property.
     * 
     * @return
     *     possible object is
     *     {@link UserContext }
     *     
     */
    public UserContext getUserContext() {
        return userContext;
    }

    /**
     * Sets the value of the userContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserContext }
     *     
     */
    public void setUserContext(UserContext value) {
        this.userContext = value;
    }

}
