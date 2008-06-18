
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for clonePortlet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="clonePortlet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext"/>
 *         &lt;element name="userContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *         &lt;element name="lifetime" type="{urn:oasis:names:tc:wsrp:v2:types}Lifetime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clonePortlet", propOrder = {
    "registrationContext",
    "portletContext",
    "userContext",
    "lifetime"
})
public class ClonePortlet {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext registrationContext;
    @XmlElement(required = true)
    protected PortletContext portletContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext userContext;
    @XmlElement(required = true, nillable = true)
    protected Lifetime lifetime;

    /**
     * Gets the value of the registrationContext property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationContext }
     *     
     */
    public RegistrationContext getRegistrationContext() {
        return registrationContext;
    }

    /**
     * Sets the value of the registrationContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationContext }
     *     
     */
    public void setRegistrationContext(RegistrationContext value) {
        this.registrationContext = value;
    }

    /**
     * Gets the value of the portletContext property.
     * 
     * @return
     *     possible object is
     *     {@link PortletContext }
     *     
     */
    public PortletContext getPortletContext() {
        return portletContext;
    }

    /**
     * Sets the value of the portletContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortletContext }
     *     
     */
    public void setPortletContext(PortletContext value) {
        this.portletContext = value;
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

}
