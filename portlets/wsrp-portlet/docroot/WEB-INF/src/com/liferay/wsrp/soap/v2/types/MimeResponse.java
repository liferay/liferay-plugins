
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MimeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MimeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="useCachedItem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemBinary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="locale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requiresRewriting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cacheControl" type="{urn:oasis:names:tc:wsrp:v2:types}CacheControl" minOccurs="0"/>
 *         &lt;element name="clientAttributes" type="{urn:oasis:names:tc:wsrp:v2:types}NamedString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ccppProfileWarning" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MimeResponse", propOrder = {
    "useCachedItem",
    "mimeType",
    "itemString",
    "itemBinary",
    "locale",
    "requiresRewriting",
    "cacheControl",
    "clientAttributes",
    "extensions"
})
@XmlSeeAlso({
    MarkupContext.class,
    ResourceContext.class
})
public class MimeResponse {

    @XmlElement(defaultValue = "false")
    protected Boolean useCachedItem;
    protected String mimeType;
    protected String itemString;
    protected byte[] itemBinary;
    protected String locale;
    @XmlElement(defaultValue = "false")
    protected Boolean requiresRewriting;
    protected CacheControl cacheControl;
    protected List<NamedString> clientAttributes;
    protected List<Extension> extensions;
    @XmlAttribute
    protected String ccppProfileWarning;

    /**
     * Gets the value of the useCachedItem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseCachedItem() {
        return useCachedItem;
    }

    /**
     * Sets the value of the useCachedItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseCachedItem(Boolean value) {
        this.useCachedItem = value;
    }

    /**
     * Gets the value of the mimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the itemString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemString() {
        return itemString;
    }

    /**
     * Sets the value of the itemString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemString(String value) {
        this.itemString = value;
    }

    /**
     * Gets the value of the itemBinary property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getItemBinary() {
        return itemBinary;
    }

    /**
     * Sets the value of the itemBinary property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setItemBinary(byte[] value) {
        this.itemBinary = ((byte[]) value);
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the requiresRewriting property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiresRewriting() {
        return requiresRewriting;
    }

    /**
     * Sets the value of the requiresRewriting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiresRewriting(Boolean value) {
        this.requiresRewriting = value;
    }

    /**
     * Gets the value of the cacheControl property.
     * 
     * @return
     *     possible object is
     *     {@link CacheControl }
     *     
     */
    public CacheControl getCacheControl() {
        return cacheControl;
    }

    /**
     * Sets the value of the cacheControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link CacheControl }
     *     
     */
    public void setCacheControl(CacheControl value) {
        this.cacheControl = value;
    }

    /**
     * Gets the value of the clientAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedString }
     * 
     * 
     */
    public List<NamedString> getClientAttributes() {
        if (clientAttributes == null) {
            clientAttributes = new ArrayList<NamedString>();
        }
        return this.clientAttributes;
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
     * Gets the value of the ccppProfileWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcppProfileWarning() {
        return ccppProfileWarning;
    }

    /**
     * Sets the value of the ccppProfileWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcppProfileWarning(String value) {
        this.ccppProfileWarning = value;
    }

}
