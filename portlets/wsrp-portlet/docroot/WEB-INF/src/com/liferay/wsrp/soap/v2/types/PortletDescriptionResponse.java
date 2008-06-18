
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PortletDescriptionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortletDescriptionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portletDescription" type="{urn:oasis:names:tc:wsrp:v2:types}PortletDescription"/>
 *         &lt;element name="resourceList" type="{urn:oasis:names:tc:wsrp:v2:types}ResourceList" minOccurs="0"/>
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
@XmlType(name = "PortletDescriptionResponse", propOrder = {
    "portletDescription",
    "resourceList",
    "extensions"
})
public class PortletDescriptionResponse {

    @XmlElement(required = true)
    protected PortletDescription portletDescription;
    protected ResourceList resourceList;
    protected List<Extension> extensions;

    /**
     * Gets the value of the portletDescription property.
     * 
     * @return
     *     possible object is
     *     {@link PortletDescription }
     *     
     */
    public PortletDescription getPortletDescription() {
        return portletDescription;
    }

    /**
     * Sets the value of the portletDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortletDescription }
     *     
     */
    public void setPortletDescription(PortletDescription value) {
        this.portletDescription = value;
    }

    /**
     * Gets the value of the resourceList property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceList }
     *     
     */
    public ResourceList getResourceList() {
        return resourceList;
    }

    /**
     * Sets the value of the resourceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceList }
     *     
     */
    public void setResourceList(ResourceList value) {
        this.resourceList = value;
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
