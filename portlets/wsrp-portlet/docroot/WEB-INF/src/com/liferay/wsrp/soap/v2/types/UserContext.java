
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userContextKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userCategories" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="profile" type="{urn:oasis:names:tc:wsrp:v2:types}UserProfile" minOccurs="0"/>
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
@XmlType(name = "UserContext", propOrder = {
    "userContextKey",
    "userCategories",
    "profile",
    "extensions"
})
public class UserContext {

    @XmlElement(required = true)
    protected String userContextKey;
    protected List<String> userCategories;
    protected UserProfile profile;
    protected List<Extension> extensions;

    /**
     * Gets the value of the userContextKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserContextKey() {
        return userContextKey;
    }

    /**
     * Sets the value of the userContextKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserContextKey(String value) {
        this.userContextKey = value;
    }

    /**
     * Gets the value of the userCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserCategories() {
        if (userCategories == null) {
            userCategories = new ArrayList<String>();
        }
        return this.userCategories;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link UserProfile }
     *     
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserProfile }
     *     
     */
    public void setProfile(UserProfile value) {
        this.profile = value;
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
