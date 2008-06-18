
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="propertyDescriptions" type="{urn:oasis:names:tc:wsrp:v2:types}PropertyDescription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="modelTypes" type="{urn:oasis:names:tc:wsrp:v2:types}ModelTypes" minOccurs="0"/>
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
@XmlType(name = "ModelDescription", propOrder = {
    "propertyDescriptions",
    "modelTypes",
    "extensions"
})
public class ModelDescription {

    protected List<PropertyDescription> propertyDescriptions;
    protected ModelTypes modelTypes;
    protected List<Extension> extensions;

    /**
     * Gets the value of the propertyDescriptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyDescriptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyDescriptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyDescription }
     * 
     * 
     */
    public List<PropertyDescription> getPropertyDescriptions() {
        if (propertyDescriptions == null) {
            propertyDescriptions = new ArrayList<PropertyDescription>();
        }
        return this.propertyDescriptions;
    }

    /**
     * Gets the value of the modelTypes property.
     * 
     * @return
     *     possible object is
     *     {@link ModelTypes }
     *     
     */
    public ModelTypes getModelTypes() {
        return modelTypes;
    }

    /**
     * Sets the value of the modelTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelTypes }
     *     
     */
    public void setModelTypes(ModelTypes value) {
        this.modelTypes = value;
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
