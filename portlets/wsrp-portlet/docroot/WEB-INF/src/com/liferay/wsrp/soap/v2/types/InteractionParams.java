
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InteractionParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InteractionParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portletStateChange" type="{urn:oasis:names:tc:wsrp:v2:types}StateChange"/>
 *         &lt;element name="interactionState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formParameters" type="{urn:oasis:names:tc:wsrp:v2:types}NamedString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="uploadContexts" type="{urn:oasis:names:tc:wsrp:v2:types}UploadContext" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "InteractionParams", propOrder = {
    "portletStateChange",
    "interactionState",
    "formParameters",
    "uploadContexts",
    "extensions"
})
public class InteractionParams {

    @XmlElement(required = true)
    protected StateChange portletStateChange;
    protected String interactionState;
    protected List<NamedString> formParameters;
    protected List<UploadContext> uploadContexts;
    protected List<Extension> extensions;

    /**
     * Gets the value of the portletStateChange property.
     * 
     * @return
     *     possible object is
     *     {@link StateChange }
     *     
     */
    public StateChange getPortletStateChange() {
        return portletStateChange;
    }

    /**
     * Sets the value of the portletStateChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateChange }
     *     
     */
    public void setPortletStateChange(StateChange value) {
        this.portletStateChange = value;
    }

    /**
     * Gets the value of the interactionState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInteractionState() {
        return interactionState;
    }

    /**
     * Sets the value of the interactionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInteractionState(String value) {
        this.interactionState = value;
    }

    /**
     * Gets the value of the formParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedString }
     * 
     * 
     */
    public List<NamedString> getFormParameters() {
        if (formParameters == null) {
            formParameters = new ArrayList<NamedString>();
        }
        return this.formParameters;
    }

    /**
     * Gets the value of the uploadContexts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uploadContexts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUploadContexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UploadContext }
     * 
     * 
     */
    public List<UploadContext> getUploadContexts() {
        if (uploadContexts == null) {
            uploadContexts = new ArrayList<UploadContext>();
        }
        return this.uploadContexts;
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
