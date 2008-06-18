
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for copyPortletsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="copyPortletsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="copiedPortlets" type="{urn:oasis:names:tc:wsrp:v2:types}CopiedPortlet" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="failedPortlets" type="{urn:oasis:names:tc:wsrp:v2:types}FailedPortlets" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "copyPortletsResponse", propOrder = {
    "copiedPortlets",
    "failedPortlets",
    "resourceList",
    "extensions"
})
public class CopyPortletsResponse {

    protected List<CopiedPortlet> copiedPortlets;
    protected List<FailedPortlets> failedPortlets;
    protected ResourceList resourceList;
    protected List<Extension> extensions;

    /**
     * Gets the value of the copiedPortlets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copiedPortlets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopiedPortlets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CopiedPortlet }
     * 
     * 
     */
    public List<CopiedPortlet> getCopiedPortlets() {
        if (copiedPortlets == null) {
            copiedPortlets = new ArrayList<CopiedPortlet>();
        }
        return this.copiedPortlets;
    }

    /**
     * Gets the value of the failedPortlets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failedPortlets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailedPortlets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FailedPortlets }
     * 
     * 
     */
    public List<FailedPortlets> getFailedPortlets() {
        if (failedPortlets == null) {
            failedPortlets = new ArrayList<FailedPortlets>();
        }
        return this.failedPortlets;
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
