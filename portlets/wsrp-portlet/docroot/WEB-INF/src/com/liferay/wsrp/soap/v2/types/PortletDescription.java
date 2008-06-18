
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for PortletDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortletDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portletHandle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="markupTypes" type="{urn:oasis:names:tc:wsrp:v2:types}MarkupType" maxOccurs="unbounded"/>
 *         &lt;element name="groupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="shortTitle" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="title" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="displayName" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="keywords" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="publishedEvents" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="handledEvents" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="navigationalPublicValueDescriptions" type="{urn:oasis:names:tc:wsrp:v2:types}ParameterDescription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userCategories" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userProfileItems" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="portletManagedModes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="usesMethodGet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="defaultMarkupSecure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="onlySecure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="userContextStoredInSession" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="templatesStoredInSession" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hasUserSpecificState" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="doesUrlTemplateProcessing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mayReturnPortletState" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="portletID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortletDescription", propOrder = {
    "portletHandle",
    "markupTypes",
    "groupID",
    "description",
    "shortTitle",
    "title",
    "displayName",
    "keywords",
    "publishedEvents",
    "handledEvents",
    "navigationalPublicValueDescriptions",
    "userCategories",
    "userProfileItems",
    "portletManagedModes",
    "usesMethodGet",
    "defaultMarkupSecure",
    "onlySecure",
    "userContextStoredInSession",
    "templatesStoredInSession",
    "hasUserSpecificState",
    "doesUrlTemplateProcessing",
    "mayReturnPortletState",
    "extensions"
})
public class PortletDescription {

    @XmlElement(required = true)
    protected String portletHandle;
    @XmlElement(required = true)
    protected List<MarkupType> markupTypes;
    protected String groupID;
    protected LocalizedString description;
    protected LocalizedString shortTitle;
    protected LocalizedString title;
    protected LocalizedString displayName;
    protected List<LocalizedString> keywords;
    protected List<QName> publishedEvents;
    protected List<QName> handledEvents;
    protected List<ParameterDescription> navigationalPublicValueDescriptions;
    protected List<String> userCategories;
    protected List<String> userProfileItems;
    protected List<String> portletManagedModes;
    @XmlElement(defaultValue = "false")
    protected Boolean usesMethodGet;
    @XmlElement(defaultValue = "false")
    protected Boolean defaultMarkupSecure;
    @XmlElement(defaultValue = "false")
    protected Boolean onlySecure;
    @XmlElement(defaultValue = "false")
    protected Boolean userContextStoredInSession;
    @XmlElement(defaultValue = "false")
    protected Boolean templatesStoredInSession;
    @XmlElement(defaultValue = "false")
    protected Boolean hasUserSpecificState;
    @XmlElement(defaultValue = "false")
    protected Boolean doesUrlTemplateProcessing;
    @XmlElement(defaultValue = "false")
    protected Boolean mayReturnPortletState;
    protected List<Extension> extensions;
    @XmlAttribute
    protected String portletID;

    /**
     * Gets the value of the portletHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortletHandle() {
        return portletHandle;
    }

    /**
     * Sets the value of the portletHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortletHandle(String value) {
        this.portletHandle = value;
    }

    /**
     * Gets the value of the markupTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the markupTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarkupTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarkupType }
     * 
     * 
     */
    public List<MarkupType> getMarkupTypes() {
        if (markupTypes == null) {
            markupTypes = new ArrayList<MarkupType>();
        }
        return this.markupTypes;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setDescription(LocalizedString value) {
        this.description = value;
    }

    /**
     * Gets the value of the shortTitle property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getShortTitle() {
        return shortTitle;
    }

    /**
     * Sets the value of the shortTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setShortTitle(LocalizedString value) {
        this.shortTitle = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setTitle(LocalizedString value) {
        this.title = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setDisplayName(LocalizedString value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedString }
     * 
     * 
     */
    public List<LocalizedString> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<LocalizedString>();
        }
        return this.keywords;
    }

    /**
     * Gets the value of the publishedEvents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publishedEvents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublishedEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getPublishedEvents() {
        if (publishedEvents == null) {
            publishedEvents = new ArrayList<QName>();
        }
        return this.publishedEvents;
    }

    /**
     * Gets the value of the handledEvents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the handledEvents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHandledEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getHandledEvents() {
        if (handledEvents == null) {
            handledEvents = new ArrayList<QName>();
        }
        return this.handledEvents;
    }

    /**
     * Gets the value of the navigationalPublicValueDescriptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the navigationalPublicValueDescriptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNavigationalPublicValueDescriptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterDescription }
     * 
     * 
     */
    public List<ParameterDescription> getNavigationalPublicValueDescriptions() {
        if (navigationalPublicValueDescriptions == null) {
            navigationalPublicValueDescriptions = new ArrayList<ParameterDescription>();
        }
        return this.navigationalPublicValueDescriptions;
    }

    /**
     * Gets the value of the userCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserCategories() {
        if (userCategories == null) {
            userCategories = new ArrayList<String>();
        }
        return this.userCategories;
    }

    /**
     * Gets the value of the userProfileItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userProfileItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserProfileItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserProfileItems() {
        if (userProfileItems == null) {
            userProfileItems = new ArrayList<String>();
        }
        return this.userProfileItems;
    }

    /**
     * Gets the value of the portletManagedModes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portletManagedModes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortletManagedModes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPortletManagedModes() {
        if (portletManagedModes == null) {
            portletManagedModes = new ArrayList<String>();
        }
        return this.portletManagedModes;
    }

    /**
     * Gets the value of the usesMethodGet property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUsesMethodGet() {
        return usesMethodGet;
    }

    /**
     * Sets the value of the usesMethodGet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUsesMethodGet(Boolean value) {
        this.usesMethodGet = value;
    }

    /**
     * Gets the value of the defaultMarkupSecure property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultMarkupSecure() {
        return defaultMarkupSecure;
    }

    /**
     * Sets the value of the defaultMarkupSecure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultMarkupSecure(Boolean value) {
        this.defaultMarkupSecure = value;
    }

    /**
     * Gets the value of the onlySecure property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlySecure() {
        return onlySecure;
    }

    /**
     * Sets the value of the onlySecure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlySecure(Boolean value) {
        this.onlySecure = value;
    }

    /**
     * Gets the value of the userContextStoredInSession property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUserContextStoredInSession() {
        return userContextStoredInSession;
    }

    /**
     * Sets the value of the userContextStoredInSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUserContextStoredInSession(Boolean value) {
        this.userContextStoredInSession = value;
    }

    /**
     * Gets the value of the templatesStoredInSession property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplatesStoredInSession() {
        return templatesStoredInSession;
    }

    /**
     * Sets the value of the templatesStoredInSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplatesStoredInSession(Boolean value) {
        this.templatesStoredInSession = value;
    }

    /**
     * Gets the value of the hasUserSpecificState property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasUserSpecificState() {
        return hasUserSpecificState;
    }

    /**
     * Sets the value of the hasUserSpecificState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasUserSpecificState(Boolean value) {
        this.hasUserSpecificState = value;
    }

    /**
     * Gets the value of the doesUrlTemplateProcessing property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDoesUrlTemplateProcessing() {
        return doesUrlTemplateProcessing;
    }

    /**
     * Sets the value of the doesUrlTemplateProcessing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoesUrlTemplateProcessing(Boolean value) {
        this.doesUrlTemplateProcessing = value;
    }

    /**
     * Gets the value of the mayReturnPortletState property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMayReturnPortletState() {
        return mayReturnPortletState;
    }

    /**
     * Sets the value of the mayReturnPortletState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMayReturnPortletState(Boolean value) {
        this.mayReturnPortletState = value;
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

    /**
     * Gets the value of the portletID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortletID() {
        return portletID;
    }

    /**
     * Sets the value of the portletID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortletID(String value) {
        this.portletID = value;
    }

}
