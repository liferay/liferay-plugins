
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Templates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Templates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultTemplate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="blockingActionTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renderTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secureDefaultTemplate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="secureBlockingActionTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secureRenderTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secureResourceTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Templates", propOrder = {
    "defaultTemplate",
    "blockingActionTemplate",
    "renderTemplate",
    "resourceTemplate",
    "secureDefaultTemplate",
    "secureBlockingActionTemplate",
    "secureRenderTemplate",
    "secureResourceTemplate",
    "extensions"
})
public class Templates {

    @XmlElement(required = true, nillable = true)
    protected String defaultTemplate;
    protected String blockingActionTemplate;
    protected String renderTemplate;
    protected String resourceTemplate;
    @XmlElement(required = true, nillable = true)
    protected String secureDefaultTemplate;
    protected String secureBlockingActionTemplate;
    protected String secureRenderTemplate;
    protected String secureResourceTemplate;
    protected List<Extension> extensions;

    /**
     * Gets the value of the defaultTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    /**
     * Sets the value of the defaultTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultTemplate(String value) {
        this.defaultTemplate = value;
    }

    /**
     * Gets the value of the blockingActionTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockingActionTemplate() {
        return blockingActionTemplate;
    }

    /**
     * Sets the value of the blockingActionTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockingActionTemplate(String value) {
        this.blockingActionTemplate = value;
    }

    /**
     * Gets the value of the renderTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderTemplate() {
        return renderTemplate;
    }

    /**
     * Sets the value of the renderTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderTemplate(String value) {
        this.renderTemplate = value;
    }

    /**
     * Gets the value of the resourceTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceTemplate() {
        return resourceTemplate;
    }

    /**
     * Sets the value of the resourceTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceTemplate(String value) {
        this.resourceTemplate = value;
    }

    /**
     * Gets the value of the secureDefaultTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureDefaultTemplate() {
        return secureDefaultTemplate;
    }

    /**
     * Sets the value of the secureDefaultTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureDefaultTemplate(String value) {
        this.secureDefaultTemplate = value;
    }

    /**
     * Gets the value of the secureBlockingActionTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureBlockingActionTemplate() {
        return secureBlockingActionTemplate;
    }

    /**
     * Sets the value of the secureBlockingActionTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureBlockingActionTemplate(String value) {
        this.secureBlockingActionTemplate = value;
    }

    /**
     * Gets the value of the secureRenderTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureRenderTemplate() {
        return secureRenderTemplate;
    }

    /**
     * Sets the value of the secureRenderTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureRenderTemplate(String value) {
        this.secureRenderTemplate = value;
    }

    /**
     * Gets the value of the secureResourceTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureResourceTemplate() {
        return secureResourceTemplate;
    }

    /**
     * Sets the value of the secureResourceTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureResourceTemplate(String value) {
        this.secureResourceTemplate = value;
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
