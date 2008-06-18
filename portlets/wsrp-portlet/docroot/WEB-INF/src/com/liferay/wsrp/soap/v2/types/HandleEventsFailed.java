
package com.liferay.wsrp.soap.v2.types;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HandleEventsFailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HandleEventsFailed">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:wsrp:v2:types}BaseFailed">
 *       &lt;sequence>
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HandleEventsFailed", propOrder = {
    "index"
})
public class HandleEventsFailed
    extends BaseFailed
{

    @XmlElement(required = true)
    protected List<BigInteger> index;

    /**
     * Gets the value of the index property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the index property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getIndex() {
        if (index == null) {
            index = new ArrayList<BigInteger>();
        }
        return this.index;
    }

}
