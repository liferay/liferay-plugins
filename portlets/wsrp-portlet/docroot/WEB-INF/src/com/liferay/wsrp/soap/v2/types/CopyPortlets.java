
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for copyPortlets complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="copyPortlets">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="toRegistrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="toUserContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *         &lt;element name="fromRegistrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="fromUserContext" type="{urn:oasis:names:tc:wsrp:v2:types}UserContext"/>
 *         &lt;element name="fromPortletContexts" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext" maxOccurs="unbounded"/>
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
@XmlType(name = "copyPortlets", propOrder = {
    "toRegistrationContext",
    "toUserContext",
    "fromRegistrationContext",
    "fromUserContext",
    "fromPortletContexts",
    "lifetime"
})
public class CopyPortlets {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext toRegistrationContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext toUserContext;
    @XmlElement(required = true, nillable = true)
    protected RegistrationContext fromRegistrationContext;
    @XmlElement(required = true, nillable = true)
    protected UserContext fromUserContext;
    @XmlElement(required = true)
    protected List<PortletContext> fromPortletContexts;
    @XmlElement(required = true, nillable = true)
    protected Lifetime lifetime;

    /**
     * Gets the value of the toRegistrationContext property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationContext }
     *     
     */
    public RegistrationContext getToRegistrationContext() {
        return toRegistrationContext;
    }

    /**
     * Sets the value of the toRegistrationContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationContext }
     *     
     */
    public void setToRegistrationContext(RegistrationContext value) {
        this.toRegistrationContext = value;
    }

    /**
     * Gets the value of the toUserContext property.
     * 
     * @return
     *     possible object is
     *     {@link UserContext }
     *     
     */
    public UserContext getToUserContext() {
        return toUserContext;
    }

    /**
     * Sets the value of the toUserContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserContext }
     *     
     */
    public void setToUserContext(UserContext value) {
        this.toUserContext = value;
    }

    /**
     * Gets the value of the fromRegistrationContext property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationContext }
     *     
     */
    public RegistrationContext getFromRegistrationContext() {
        return fromRegistrationContext;
    }

    /**
     * Sets the value of the fromRegistrationContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationContext }
     *     
     */
    public void setFromRegistrationContext(RegistrationContext value) {
        this.fromRegistrationContext = value;
    }

    /**
     * Gets the value of the fromUserContext property.
     * 
     * @return
     *     possible object is
     *     {@link UserContext }
     *     
     */
    public UserContext getFromUserContext() {
        return fromUserContext;
    }

    /**
     * Sets the value of the fromUserContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserContext }
     *     
     */
    public void setFromUserContext(UserContext value) {
        this.fromUserContext = value;
    }

    /**
     * Gets the value of the fromPortletContexts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fromPortletContexts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFromPortletContexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortletContext }
     * 
     * 
     */
    public List<PortletContext> getFromPortletContexts() {
        if (fromPortletContexts == null) {
            fromPortletContexts = new ArrayList<PortletContext>();
        }
        return this.fromPortletContexts;
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
