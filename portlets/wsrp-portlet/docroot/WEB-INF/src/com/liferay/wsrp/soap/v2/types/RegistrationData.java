
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistrationData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistrationData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consumerAgent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="methodGetSupported" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consumerModes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="consumerWindowStates" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="consumerUserScopes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="extensionDescriptions" type="{urn:oasis:names:tc:wsrp:v2:types}ExtensionDescription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="registrationProperties" type="{urn:oasis:names:tc:wsrp:v2:types}Property" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceList" type="{urn:oasis:names:tc:wsrp:v2:types}ResourceList" minOccurs="0"/>
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
@XmlType(name = "RegistrationData", propOrder = {
    "consumerName",
    "consumerAgent",
    "methodGetSupported",
    "consumerModes",
    "consumerWindowStates",
    "consumerUserScopes",
    "extensionDescriptions",
    "registrationProperties",
    "resourceList",
    "extensions"
})
public class RegistrationData {

    @XmlElement(required = true)
    protected String consumerName;
    @XmlElement(required = true)
    protected String consumerAgent;
    protected boolean methodGetSupported;
    protected List<String> consumerModes;
    protected List<String> consumerWindowStates;
    protected List<String> consumerUserScopes;
    protected List<ExtensionDescription> extensionDescriptions;
    protected List<Property> registrationProperties;
    protected ResourceList resourceList;
    protected List<Extension> extensions;

    /**
     * Gets the value of the consumerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * Sets the value of the consumerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerName(String value) {
        this.consumerName = value;
    }

    /**
     * Gets the value of the consumerAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerAgent() {
        return consumerAgent;
    }

    /**
     * Sets the value of the consumerAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerAgent(String value) {
        this.consumerAgent = value;
    }

    /**
     * Gets the value of the methodGetSupported property.
     * 
     */
    public boolean isMethodGetSupported() {
        return methodGetSupported;
    }

    /**
     * Sets the value of the methodGetSupported property.
     * 
     */
    public void setMethodGetSupported(boolean value) {
        this.methodGetSupported = value;
    }

    /**
     * Gets the value of the consumerModes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consumerModes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsumerModes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConsumerModes() {
        if (consumerModes == null) {
            consumerModes = new ArrayList<String>();
        }
        return this.consumerModes;
    }

    /**
     * Gets the value of the consumerWindowStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consumerWindowStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsumerWindowStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConsumerWindowStates() {
        if (consumerWindowStates == null) {
            consumerWindowStates = new ArrayList<String>();
        }
        return this.consumerWindowStates;
    }

    /**
     * Gets the value of the consumerUserScopes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consumerUserScopes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsumerUserScopes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConsumerUserScopes() {
        if (consumerUserScopes == null) {
            consumerUserScopes = new ArrayList<String>();
        }
        return this.consumerUserScopes;
    }

    /**
     * Gets the value of the extensionDescriptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensionDescriptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensionDescriptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionDescription }
     * 
     * 
     */
    public List<ExtensionDescription> getExtensionDescriptions() {
        if (extensionDescriptions == null) {
            extensionDescriptions = new ArrayList<ExtensionDescription>();
        }
        return this.extensionDescriptions;
    }

    /**
     * Gets the value of the registrationProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registrationProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistrationProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getRegistrationProperties() {
        if (registrationProperties == null) {
            registrationProperties = new ArrayList<Property>();
        }
        return this.registrationProperties;
    }

    /**
     * Gets the value of the resourceList property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceList }
     *     
     */
    public ResourceList getResourceList() {
        return resourceList;
    }

    /**
     * Sets the value of the resourceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceList }
     *     
     */
    public void setResourceList(ResourceList value) {
        this.resourceList = value;
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
