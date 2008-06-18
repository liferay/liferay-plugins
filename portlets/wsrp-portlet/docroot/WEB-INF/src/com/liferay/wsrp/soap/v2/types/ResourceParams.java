
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResourceParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResourceParams">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:wsrp:v2:types}MimeRequest">
 *       &lt;sequence>
 *         &lt;element name="formParameters" type="{urn:oasis:names:tc:wsrp:v2:types}NamedString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="uploadContexts" type="{urn:oasis:names:tc:wsrp:v2:types}UploadContext" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resourceID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="portletStateChange" use="required" type="{urn:oasis:names:tc:wsrp:v2:types}StateChange" />
 *       &lt;attribute name="resourceState" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resourceCacheability" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceParams", propOrder = {
    "formParameters",
    "uploadContexts"
})
public class ResourceParams
    extends MimeRequest
{

    protected List<NamedString> formParameters;
    protected List<UploadContext> uploadContexts;
    @XmlAttribute(required = true)
    protected String resourceID;
    @XmlAttribute(required = true)
    protected StateChange portletStateChange;
    @XmlAttribute
    protected String resourceState;
    @XmlAttribute
    protected String resourceCacheability;

    /**
     * Gets the value of the formParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedString }
     * 
     * 
     */
    public List<NamedString> getFormParameters() {
        if (formParameters == null) {
            formParameters = new ArrayList<NamedString>();
        }
        return this.formParameters;
    }

    /**
     * Gets the value of the uploadContexts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uploadContexts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUploadContexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UploadContext }
     * 
     * 
     */
    public List<UploadContext> getUploadContexts() {
        if (uploadContexts == null) {
            uploadContexts = new ArrayList<UploadContext>();
        }
        return this.uploadContexts;
    }

    /**
     * Gets the value of the resourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * Sets the value of the resourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceID(String value) {
        this.resourceID = value;
    }

    /**
     * Gets the value of the portletStateChange property.
     * 
     * @return
     *     possible object is
     *     {@link StateChange }
     *     
     */
    public StateChange getPortletStateChange() {
        return portletStateChange;
    }

    /**
     * Sets the value of the portletStateChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateChange }
     *     
     */
    public void setPortletStateChange(StateChange value) {
        this.portletStateChange = value;
    }

    /**
     * Gets the value of the resourceState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceState() {
        return resourceState;
    }

    /**
     * Sets the value of the resourceState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceState(String value) {
        this.resourceState = value;
    }

    /**
     * Gets the value of the resourceCacheability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceCacheability() {
        return resourceCacheability;
    }

    /**
     * Sets the value of the resourceCacheability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceCacheability(String value) {
        this.resourceCacheability = value;
    }

}
