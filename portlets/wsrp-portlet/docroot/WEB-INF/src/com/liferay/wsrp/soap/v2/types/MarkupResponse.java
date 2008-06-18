
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarkupResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarkupResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="markupContext" type="{urn:oasis:names:tc:wsrp:v2:types}MarkupContext"/>
 *         &lt;element name="sessionContext" type="{urn:oasis:names:tc:wsrp:v2:types}SessionContext" minOccurs="0"/>
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
@XmlType(name = "MarkupResponse", propOrder = {
    "markupContext",
    "sessionContext",
    "extensions"
})
public class MarkupResponse {

    @XmlElement(required = true)
    protected MarkupContext markupContext;
    protected SessionContext sessionContext;
    protected List<Extension> extensions;

    /**
     * Gets the value of the markupContext property.
     * 
     * @return
     *     possible object is
     *     {@link MarkupContext }
     *     
     */
    public MarkupContext getMarkupContext() {
        return markupContext;
    }

    /**
     * Sets the value of the markupContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarkupContext }
     *     
     */
    public void setMarkupContext(MarkupContext value) {
        this.markupContext = value;
    }

    /**
     * Gets the value of the sessionContext property.
     * 
     * @return
     *     possible object is
     *     {@link SessionContext }
     *     
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * Sets the value of the sessionContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionContext }
     *     
     */
    public void setSessionContext(SessionContext value) {
        this.sessionContext = value;
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
