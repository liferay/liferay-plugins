
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HandleEventsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HandleEventsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateResponse" type="{urn:oasis:names:tc:wsrp:v2:types}UpdateResponse" minOccurs="0"/>
 *         &lt;element name="failedEvents" type="{urn:oasis:names:tc:wsrp:v2:types}HandleEventsFailed" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "HandleEventsResponse", propOrder = {
    "updateResponse",
    "failedEvents",
    "extensions"
})
public class HandleEventsResponse {

    protected UpdateResponse updateResponse;
    protected List<HandleEventsFailed> failedEvents;
    protected List<Extension> extensions;

    /**
     * Gets the value of the updateResponse property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateResponse }
     *     
     */
    public UpdateResponse getUpdateResponse() {
        return updateResponse;
    }

    /**
     * Sets the value of the updateResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateResponse }
     *     
     */
    public void setUpdateResponse(UpdateResponse value) {
        this.updateResponse = value;
    }

    /**
     * Gets the value of the failedEvents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failedEvents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailedEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HandleEventsFailed }
     * 
     * 
     */
    public List<HandleEventsFailed> getFailedEvents() {
        if (failedEvents == null) {
            failedEvents = new ArrayList<HandleEventsFailed>();
        }
        return this.failedEvents;
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
