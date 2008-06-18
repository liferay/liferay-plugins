
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for BaseFailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseFailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reason" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="resourceList" type="{urn:oasis:names:tc:wsrp:v2:types}ResourceList" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="errorCode" use="required" type="{urn:oasis:names:tc:wsrp:v2:types}ErrorCodes" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseFailed", propOrder = {
    "reason",
    "resourceList",
    "extensions"
})
@XmlSeeAlso({
    HandleEventsFailed.class,
    ImportPortletsFailed.class,
    FailedPortlets.class
})
public class BaseFailed {

    protected LocalizedString reason;
    protected ResourceList resourceList;
    protected List<Extension> extensions;
    @XmlAttribute(required = true)
    protected QName errorCode;

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setReason(LocalizedString value) {
        this.reason = value;
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

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setErrorCode(QName value) {
        this.errorCode = value;
    }

}
