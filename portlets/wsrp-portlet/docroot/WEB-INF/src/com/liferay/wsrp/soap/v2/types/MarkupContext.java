
package com.liferay.wsrp.soap.v2.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarkupContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarkupContext">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:wsrp:v2:types}MimeResponse">
 *       &lt;sequence>
 *         &lt;element name="preferredTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validNewModes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarkupContext", propOrder = {
    "preferredTitle",
    "validNewModes"
})
public class MarkupContext
    extends MimeResponse
{

    protected String preferredTitle;
    protected List<String> validNewModes;

    /**
     * Gets the value of the preferredTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredTitle() {
        return preferredTitle;
    }

    /**
     * Sets the value of the preferredTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredTitle(String value) {
        this.preferredTitle = value;
    }

    /**
     * Gets the value of the validNewModes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validNewModes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidNewModes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidNewModes() {
        if (validNewModes == null) {
            validNewModes = new ArrayList<String>();
        }
        return this.validNewModes;
    }

}
