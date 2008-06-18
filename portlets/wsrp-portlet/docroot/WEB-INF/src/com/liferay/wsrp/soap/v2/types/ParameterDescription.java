
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for ParameterDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParameterDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="names" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="label" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="hint" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identifier" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParameterDescription", propOrder = {
    "names",
    "description",
    "label",
    "hint",
    "extensions"
})
public class ParameterDescription {

    protected List<QName> names;
    protected LocalizedString description;
    protected LocalizedString label;
    protected LocalizedString hint;
    protected List<Extension> extensions;
    @XmlAttribute(required = true)
    protected String identifier;

    /**
     * Gets the value of the names property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the names property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getNames() {
        if (names == null) {
            names = new ArrayList<QName>();
        }
        return this.names;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setDescription(LocalizedString value) {
        this.description = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setLabel(LocalizedString value) {
        this.label = value;
    }

    /**
     * Gets the value of the hint property.
     * 
     * @return
     *     possible object is
     *     {@link LocalizedString }
     *     
     */
    public LocalizedString getHint() {
        return hint;
    }

    /**
     * Sets the value of the hint property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalizedString }
     *     
     */
    public void setHint(LocalizedString value) {
        this.hint = value;
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
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

}
