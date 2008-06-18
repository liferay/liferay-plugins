
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext"/>
 *         &lt;element name="runtimeContext" type="{urn:oasis:names:tc:wsrp:v2:types}RuntimeContext"/>
 *         &lt;element name="userContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *         &lt;element name="resourceParams" type="{urn:oasis:names:tc:wsrp:v2:types}ResourceParams"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResource", propOrder = {
    "registrationContext",
    "portletContext",
    "runtimeContext",
    "userContext",
    "resourceParams"
})
public class GetResource {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext registrationContext;
    @XmlElement(required = true)
    protected PortletContext portletContext;
    @XmlElement(required = true)
    protected RuntimeContext runtimeContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext userContext;
    @XmlElement(required = true)
    protected ResourceParams resourceParams;

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
     * Gets the value of the runtimeContext property.
     * 
     * @return
     *     possible object is
     *     {@link RuntimeContext }
     *     
     */
    public RuntimeContext getRuntimeContext() {
        return runtimeContext;
    }

    /**
     * Sets the value of the runtimeContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuntimeContext }
     *     
     */
    public void setRuntimeContext(RuntimeContext value) {
        this.runtimeContext = value;
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
     * Gets the value of the resourceParams property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceParams }
     *     
     */
    public ResourceParams getResourceParams() {
        return resourceParams;
    }

    /**
     * Sets the value of the resourceParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceParams }
     *     
     */
    public void setResourceParams(ResourceParams value) {
        this.resourceParams = value;
    }

}
