
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPortletsLifetime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPortletsLifetime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext" maxOccurs="unbounded"/>
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
@XmlType(name = "getPortletsLifetime", propOrder = {
    "registrationContext",
    "portletContext",
    "userContext"
})
public class GetPortletsLifetime {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext registrationContext;
    @XmlElement(required = true)
    protected List<PortletContext> portletContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext userContext;

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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portletContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortletContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortletContext }
     * 
     * 
     */
    public List<PortletContext> getPortletContext() {
        if (portletContext == null) {
            portletContext = new ArrayList<PortletContext>();
        }
        return this.portletContext;
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
