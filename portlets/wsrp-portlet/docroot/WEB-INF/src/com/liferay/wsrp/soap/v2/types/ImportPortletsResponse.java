
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importPortletsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="importPortletsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importedPortlets" type="{urn:oasis:names:tc:wsrp:v2:types}ImportedPortlet" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="importFailed" type="{urn:oasis:names:tc:wsrp:v2:types}ImportPortletsFailed" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "importPortletsResponse", propOrder = {
    "importedPortlets",
    "importFailed",
    "resourceList",
    "extensions"
})
public class ImportPortletsResponse {

    protected List<ImportedPortlet> importedPortlets;
    protected List<ImportPortletsFailed> importFailed;
    protected ResourceList resourceList;
    protected List<Extension> extensions;

    /**
     * Gets the value of the importedPortlets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the importedPortlets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImportedPortlets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImportedPortlet }
     * 
     * 
     */
    public List<ImportedPortlet> getImportedPortlets() {
        if (importedPortlets == null) {
            importedPortlets = new ArrayList<ImportedPortlet>();
        }
        return this.importedPortlets;
    }

    /**
     * Gets the value of the importFailed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the importFailed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImportFailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImportPortletsFailed }
     * 
     * 
     */
    public List<ImportPortletsFailed> getImportFailed() {
        if (importFailed == null) {
            importFailed = new ArrayList<ImportPortletsFailed>();
        }
        return this.importFailed;
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
