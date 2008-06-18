
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImportedPortlet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImportedPortlet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newPortletContext" type="{urn:oasis:names:tc:wsrp:v2:types}PortletContext"/>
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
@XmlType(name = "ImportedPortlet", propOrder = {
    "importID",
    "newPortletContext",
    "extensions"
})
public class ImportedPortlet {

    @XmlElement(required = true)
    protected String importID;
    @XmlElement(required = true)
    protected PortletContext newPortletContext;
    protected List<Extension> extensions;

    /**
     * Gets the value of the importID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportID() {
        return importID;
    }

    /**
     * Sets the value of the importID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportID(String value) {
        this.importID = value;
    }

    /**
     * Gets the value of the newPortletContext property.
     * 
     * @return
     *     possible object is
     *     {@link PortletContext }
     *     
     */
    public PortletContext getNewPortletContext() {
        return newPortletContext;
    }

    /**
     * Sets the value of the newPortletContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortletContext }
     *     
     */
    public void setNewPortletContext(PortletContext value) {
        this.newPortletContext = value;
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
