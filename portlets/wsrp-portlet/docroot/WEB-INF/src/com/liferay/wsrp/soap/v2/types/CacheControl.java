
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CacheControl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CacheControl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expires" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userScope" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validateTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CacheControl", propOrder = {
    "expires",
    "userScope",
    "validateTag",
    "extensions"
})
public class CacheControl {

    protected int expires;
    @XmlElement(required = true)
    protected String userScope;
    protected String validateTag;
    protected List<Extension> extensions;

    /**
     * Gets the value of the expires property.
     * 
     */
    public int getExpires() {
        return expires;
    }

    /**
     * Sets the value of the expires property.
     * 
     */
    public void setExpires(int value) {
        this.expires = value;
    }

    /**
     * Gets the value of the userScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserScope() {
        return userScope;
    }

    /**
     * Sets the value of the userScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserScope(String value) {
        this.userScope = value;
    }

    /**
     * Gets the value of the validateTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidateTag() {
        return validateTag;
    }

    /**
     * Sets the value of the validateTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidateTag(String value) {
        this.validateTag = value;
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
