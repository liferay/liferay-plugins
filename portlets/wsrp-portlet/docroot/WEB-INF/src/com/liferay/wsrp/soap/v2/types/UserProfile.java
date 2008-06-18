
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for UserProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{urn:oasis:names:tc:wsrp:v2:types}PersonName" minOccurs="0"/>
 *         &lt;element name="bdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerInfo" type="{urn:oasis:names:tc:wsrp:v2:types}EmployerInfo" minOccurs="0"/>
 *         &lt;element name="homeInfo" type="{urn:oasis:names:tc:wsrp:v2:types}Contact" minOccurs="0"/>
 *         &lt;element name="businessInfo" type="{urn:oasis:names:tc:wsrp:v2:types}Contact" minOccurs="0"/>
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
@XmlType(name = "UserProfile", propOrder = {
    "name",
    "bdate",
    "gender",
    "employerInfo",
    "homeInfo",
    "businessInfo",
    "extensions"
})
public class UserProfile {

    protected PersonName name;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bdate;
    protected String gender;
    protected EmployerInfo employerInfo;
    protected Contact homeInfo;
    protected Contact businessInfo;
    protected List<Extension> extensions;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link PersonName }
     *     
     */
    public PersonName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonName }
     *     
     */
    public void setName(PersonName value) {
        this.name = value;
    }

    /**
     * Gets the value of the bdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBdate() {
        return bdate;
    }

    /**
     * Sets the value of the bdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBdate(XMLGregorianCalendar value) {
        this.bdate = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the employerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EmployerInfo }
     *     
     */
    public EmployerInfo getEmployerInfo() {
        return employerInfo;
    }

    /**
     * Sets the value of the employerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployerInfo }
     *     
     */
    public void setEmployerInfo(EmployerInfo value) {
        this.employerInfo = value;
    }

    /**
     * Gets the value of the homeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getHomeInfo() {
        return homeInfo;
    }

    /**
     * Sets the value of the homeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setHomeInfo(Contact value) {
        this.homeInfo = value;
    }

    /**
     * Gets the value of the businessInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getBusinessInfo() {
        return businessInfo;
    }

    /**
     * Sets the value of the businessInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setBusinessInfo(Contact value) {
        this.businessInfo = value;
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
