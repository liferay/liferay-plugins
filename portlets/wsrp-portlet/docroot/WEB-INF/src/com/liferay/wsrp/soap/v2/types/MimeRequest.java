
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MimeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MimeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="secureClientCommunication" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="locales" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="mimeTypes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="windowState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientData" type="{urn:oasis:names:tc:wsrp:v2:types}ClientData" minOccurs="0"/>
 *         &lt;element name="navigationalContext" type="{urn:oasis:names:tc:wsrp:v2:types}NavigationalContext" minOccurs="0"/>
 *         &lt;element name="markupCharacterSets" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validateTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validNewModes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validNewWindowStates" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "MimeRequest", propOrder = {
    "secureClientCommunication",
    "locales",
    "mimeTypes",
    "mode",
    "windowState",
    "clientData",
    "navigationalContext",
    "markupCharacterSets",
    "validateTag",
    "validNewModes",
    "validNewWindowStates",
    "extensions"
})
@XmlSeeAlso({
    ResourceParams.class,
    MarkupParams.class
})
public class MimeRequest {

    protected boolean secureClientCommunication;
    @XmlElement(required = true)
    protected List<String> locales;
    @XmlElement(required = true)
    protected List<String> mimeTypes;
    @XmlElement(required = true)
    protected String mode;
    @XmlElement(required = true)
    protected String windowState;
    protected ClientData clientData;
    protected NavigationalContext navigationalContext;
    protected List<String> markupCharacterSets;
    protected String validateTag;
    protected List<String> validNewModes;
    protected List<String> validNewWindowStates;
    protected List<Extension> extensions;

    /**
     * Gets the value of the secureClientCommunication property.
     * 
     */
    public boolean isSecureClientCommunication() {
        return secureClientCommunication;
    }

    /**
     * Sets the value of the secureClientCommunication property.
     * 
     */
    public void setSecureClientCommunication(boolean value) {
        this.secureClientCommunication = value;
    }

    /**
     * Gets the value of the locales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLocales() {
        if (locales == null) {
            locales = new ArrayList<String>();
        }
        return this.locales;
    }

    /**
     * Gets the value of the mimeTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mimeTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMimeTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMimeTypes() {
        if (mimeTypes == null) {
            mimeTypes = new ArrayList<String>();
        }
        return this.mimeTypes;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the windowState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWindowState() {
        return windowState;
    }

    /**
     * Sets the value of the windowState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWindowState(String value) {
        this.windowState = value;
    }

    /**
     * Gets the value of the clientData property.
     * 
     * @return
     *     possible object is
     *     {@link ClientData }
     *     
     */
    public ClientData getClientData() {
        return clientData;
    }

    /**
     * Sets the value of the clientData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientData }
     *     
     */
    public void setClientData(ClientData value) {
        this.clientData = value;
    }

    /**
     * Gets the value of the navigationalContext property.
     * 
     * @return
     *     possible object is
     *     {@link NavigationalContext }
     *     
     */
    public NavigationalContext getNavigationalContext() {
        return navigationalContext;
    }

    /**
     * Sets the value of the navigationalContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link NavigationalContext }
     *     
     */
    public void setNavigationalContext(NavigationalContext value) {
        this.navigationalContext = value;
    }

    /**
     * Gets the value of the markupCharacterSets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the markupCharacterSets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarkupCharacterSets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMarkupCharacterSets() {
        if (markupCharacterSets == null) {
            markupCharacterSets = new ArrayList<String>();
        }
        return this.markupCharacterSets;
    }

    /**
     * Gets the value of the validateTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidateTag() {
        return validateTag;
    }

    /**
     * Sets the value of the validateTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidateTag(String value) {
        this.validateTag = value;
    }

    /**
     * Gets the value of the validNewModes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validNewModes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidNewModes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidNewModes() {
        if (validNewModes == null) {
            validNewModes = new ArrayList<String>();
        }
        return this.validNewModes;
    }

    /**
     * Gets the value of the validNewWindowStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validNewWindowStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidNewWindowStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidNewWindowStates() {
        if (validNewWindowStates == null) {
            validNewWindowStates = new ArrayList<String>();
        }
        return this.validNewWindowStates;
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
