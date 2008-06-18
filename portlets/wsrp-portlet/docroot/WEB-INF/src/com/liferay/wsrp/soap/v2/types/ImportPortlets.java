
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importPortlets complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="importPortlets">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v2:types}RegistrationContext"/>
 *         &lt;element name="importContext" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="importPortlet" type="{urn:oasis:names:tc:wsrp:v2:types}ImportPortlet" maxOccurs="unbounded"/>
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
@XmlType(name = "importPortlets", propOrder = {
    "registrationContext",
    "importContext",
    "importPortlet",
    "userContext",
    "lifetime"
})
public class ImportPortlets {

    @XmlElement(required = true, nillable = true)
    protected RegistrationContext registrationContext;
    @XmlElement(required = true, nillable = true)
    protected byte[] importContext;
    @XmlElement(required = true)
    protected List<ImportPortlet> importPortlet;
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
     * Gets the value of the importContext property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImportContext() {
        return importContext;
    }

    /**
     * Sets the value of the importContext property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImportContext(byte[] value) {
        this.importContext = ((byte[]) value);
    }

    /**
     * Gets the value of the importPortlet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the importPortlet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImportPortlet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImportPortlet }
     * 
     * 
     */
    public List<ImportPortlet> getImportPortlet() {
        if (importPortlet == null) {
            importPortlet = new ArrayList<ImportPortlet>();
        }
        return this.importPortlet;
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
