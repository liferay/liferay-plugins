
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for handleEvents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="handleEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext"/>
 *         &lt;element name="runtimeContext" type="{urn:oasis:names:tc:wsrp:v2:types}RuntimeContext"/>
 *         &lt;element name="userContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *         &lt;element name="markupParams" type="{urn:oasis:names:tc:wsrp:v2:types}MarkupParams"/>
 *         &lt;element name="eventParams" type="{urn:oasis:names:tc:wsrp:v2:types}EventParams"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "handleEvents", propOrder = {
    "registrationContext",
    "portletContext",
    "runtimeContext",
    "userContext",
    "markupParams",
    "eventParams"
})
public class HandleEvents {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext registrationContext;
    @XmlElement(required = true)
    protected PortletContext portletContext;
    @XmlElement(required = true)
    protected RuntimeContext runtimeContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext userContext;
    @XmlElement(required = true)
    protected MarkupParams markupParams;
    @XmlElement(required = true)
    protected EventParams eventParams;

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
     * Gets the value of the markupParams property.
     * 
     * @return
     *     possible object is
     *     {@link MarkupParams }
     *     
     */
    public MarkupParams getMarkupParams() {
        return markupParams;
    }

    /**
     * Sets the value of the markupParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarkupParams }
     *     
     */
    public void setMarkupParams(MarkupParams value) {
        this.markupParams = value;
    }

    /**
     * Gets the value of the eventParams property.
     * 
     * @return
     *     possible object is
     *     {@link EventParams }
     *     
     */
    public EventParams getEventParams() {
        return eventParams;
    }

    /**
     * Sets the value of the eventParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventParams }
     *     
     */
    public void setEventParams(EventParams value) {
        this.eventParams = value;
    }

}
