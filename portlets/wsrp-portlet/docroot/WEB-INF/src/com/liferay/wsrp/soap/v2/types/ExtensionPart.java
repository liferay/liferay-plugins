
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for ExtensionPart complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensionPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extendedTypes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="schemaType" type="{urn:oasis:names:tc:wsrp:v2:types}ModelTypes" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v2:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="schemaLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionPart", propOrder = {
    "extendedTypes",
    "schemaType",
    "extensions"
})
public class ExtensionPart {

    protected List<String> extendedTypes;
    protected ModelTypes schemaType;
    protected List<Extension> extensions;
    @XmlAttribute(required = true)
    protected QName name;
    @XmlAttribute(required = true)
    protected QName type;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String schemaLocation;

    /**
     * Gets the value of the extendedTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendedTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtendedTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExtendedTypes() {
        if (extendedTypes == null) {
            extendedTypes = new ArrayList<String>();
        }
        return this.extendedTypes;
    }

    /**
     * Gets the value of the schemaType property.
     * 
     * @return
     *     possible object is
     *     {@link ModelTypes }
     *     
     */
    public ModelTypes getSchemaType() {
        return schemaType;
    }

    /**
     * Sets the value of the schemaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelTypes }
     *     
     */
    public void setSchemaType(ModelTypes value) {
        this.schemaType = value;
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

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setType(QName value) {
        this.type = value;
    }

    /**
     * Gets the value of the schemaLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemaLocation() {
        return schemaLocation;
    }

    /**
     * Sets the value of the schemaLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemaLocation(String value) {
        this.schemaLocation = value;
    }

}
