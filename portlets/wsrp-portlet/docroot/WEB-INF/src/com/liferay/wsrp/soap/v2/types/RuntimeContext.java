
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RuntimeContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RuntimeContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userAuthentication" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portletInstanceKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="namespacePrefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="templates" type="{urn:oasis:names:tc:wsrp:v2:types}Templates" minOccurs="0"/>
 *         &lt;element name="sessionParams" type="{urn:oasis:names:tc:wsrp:v2:types}SessionParams" minOccurs="0"/>
 *         &lt;element name="pageState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portletStates" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "RuntimeContext", propOrder = {
    "userAuthentication",
    "portletInstanceKey",
    "namespacePrefix",
    "templates",
    "sessionParams",
    "pageState",
    "portletStates",
    "extensions"
})
public class RuntimeContext {

    @XmlElement(required = true)
    protected String userAuthentication;
    @XmlElement(required = true)
    protected String portletInstanceKey;
    @XmlElement(required = true)
    protected String namespacePrefix;
    protected Templates templates;
    protected SessionParams sessionParams;
    protected String pageState;
    protected String portletStates;
    protected List<Extension> extensions;

    /**
     * Gets the value of the userAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAuthentication() {
        return userAuthentication;
    }

    /**
     * Sets the value of the userAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAuthentication(String value) {
        this.userAuthentication = value;
    }

    /**
     * Gets the value of the portletInstanceKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortletInstanceKey() {
        return portletInstanceKey;
    }

    /**
     * Sets the value of the portletInstanceKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortletInstanceKey(String value) {
        this.portletInstanceKey = value;
    }

    /**
     * Gets the value of the namespacePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamespacePrefix() {
        return namespacePrefix;
    }

    /**
     * Sets the value of the namespacePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamespacePrefix(String value) {
        this.namespacePrefix = value;
    }

    /**
     * Gets the value of the templates property.
     * 
     * @return
     *     possible object is
     *     {@link Templates }
     *     
     */
    public Templates getTemplates() {
        return templates;
    }

    /**
     * Sets the value of the templates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Templates }
     *     
     */
    public void setTemplates(Templates value) {
        this.templates = value;
    }

    /**
     * Gets the value of the sessionParams property.
     * 
     * @return
     *     possible object is
     *     {@link SessionParams }
     *     
     */
    public SessionParams getSessionParams() {
        return sessionParams;
    }

    /**
     * Sets the value of the sessionParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionParams }
     *     
     */
    public void setSessionParams(SessionParams value) {
        this.sessionParams = value;
    }

    /**
     * Gets the value of the pageState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageState() {
        return pageState;
    }

    /**
     * Sets the value of the pageState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageState(String value) {
        this.pageState = value;
    }

    /**
     * Gets the value of the portletStates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortletStates() {
        return portletStates;
    }

    /**
     * Sets the value of the portletStates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortletStates(String value) {
        this.portletStates = value;
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
