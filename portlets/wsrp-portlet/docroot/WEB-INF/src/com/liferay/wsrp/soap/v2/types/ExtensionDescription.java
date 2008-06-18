
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for ExtensionDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensionDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parts" type="{urn:oasis:names:tc:wsrp:v2:types}ExtensionPart" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="aliases" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="label" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="hint" type="{urn:oasis:names:tc:wsrp:v2:types}LocalizedString" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionDescription", propOrder = {
    "parts",
    "aliases",
    "description",
    "label",
    "hint",
    "extensions"
})
public class ExtensionDescription {

    protected List<ExtensionPart> parts;
    protected List<QName> aliases;
    protected LocalizedString description;
    protected LocalizedString label;
    protected LocalizedString hint;
    protected List<Extension> extensions;
    @XmlAttribute(required = true)
    protected QName name;

    /**
     * Gets the value of the parts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionPart }
     * 
     * 
     */
    public List<ExtensionPart> getParts() {
        if (parts == null) {
            parts = new ArrayList<ExtensionPart>();
        }
        return this.parts;
    }

    /**
     * Gets the value of the aliases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aliases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAliases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getAliases() {
        if (aliases == null) {
            aliases = new ArrayList<QName>();
        }
        return this.aliases;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setName(QName value) {
        this.name = value;
    }

}
