
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionContext" type="{urn:oasis:names:tc:wsrp:v2:types}SessionContext" minOccurs="0"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext" minOccurs="0"/>
 *         &lt;element name="markupContext" type="{urn:oasis:names:tc:wsrp:v2:types}MarkupContext" minOccurs="0"/>
 *         &lt;element name="events" type="{urn:oasis:names:tc:wsrp:v2:types}Event" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="navigationalContext" type="{urn:oasis:names:tc:wsrp:v2:types}NavigationalContext" minOccurs="0"/>
 *         &lt;element name="newWindowState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "UpdateResponse", propOrder = {
    "sessionContext",
    "portletContext",
    "markupContext",
    "events",
    "navigationalContext",
    "newWindowState",
    "newMode",
    "extensions"
})
public class UpdateResponse {

    protected SessionContext sessionContext;
    protected PortletContext portletContext;
    protected MarkupContext markupContext;
    protected List<Event> events;
    protected NavigationalContext navigationalContext;
    protected String newWindowState;
    protected String newMode;
    protected List<Extension> extensions;

    /**
     * Gets the value of the sessionContext property.
     * 
     * @return
     *     possible object is
     *     {@link SessionContext }
     *     
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * Sets the value of the sessionContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionContext }
     *     
     */
    public void setSessionContext(SessionContext value) {
        this.sessionContext = value;
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
     * Gets the value of the markupContext property.
     * 
     * @return
     *     possible object is
     *     {@link MarkupContext }
     *     
     */
    public MarkupContext getMarkupContext() {
        return markupContext;
    }

    /**
     * Sets the value of the markupContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarkupContext }
     *     
     */
    public void setMarkupContext(MarkupContext value) {
        this.markupContext = value;
    }

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Event }
     * 
     * 
     */
    public List<Event> getEvents() {
        if (events == null) {
            events = new ArrayList<Event>();
        }
        return this.events;
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
     * Gets the value of the newWindowState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewWindowState() {
        return newWindowState;
    }

    /**
     * Sets the value of the newWindowState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewWindowState(String value) {
        this.newWindowState = value;
    }

    /**
     * Gets the value of the newMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewMode() {
        return newMode;
    }

    /**
     * Sets the value of the newMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewMode(String value) {
        this.newMode = value;
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
