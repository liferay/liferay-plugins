
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResourceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResourceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceContext" type="{urn:oasis:names:tc:wsrp:v2:types}ResourceContext"/>
 *         &lt;element name="sessionContext" type="{urn:oasis:names:tc:wsrp:v2:types}SessionContext" minOccurs="0"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext" minOccurs="0"/>
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
@XmlType(name = "ResourceResponse", propOrder = {
    "resourceContext",
    "sessionContext",
    "portletContext",
    "extensions"
})
public class ResourceResponse {

    @XmlElement(required = true)
    protected ResourceContext resourceContext;
    protected SessionContext sessionContext;
    protected PortletContext portletContext;
    protected List<Extension> extensions;

    /**
     * Gets the value of the resourceContext property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceContext }
     *     
     */
    public ResourceContext getResourceContext() {
        return resourceContext;
    }

    /**
     * Sets the value of the resourceContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceContext }
     *     
     */
    public void setResourceContext(ResourceContext value) {
        this.resourceContext = value;
    }

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
