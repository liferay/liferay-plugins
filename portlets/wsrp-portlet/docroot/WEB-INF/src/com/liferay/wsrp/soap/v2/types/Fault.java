
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Fault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fault")
@XmlSeeAlso({
    InconsistentParametersFault.class,
    MissingParametersFault.class,
    OperationNotSupportedFault.class,
    InvalidRegistrationFault.class,
    AccessDeniedFault.class,
    PortletStateChangeRequiredFault.class,
    InvalidUserCategoryFault.class,
    InvalidHandleFault.class,
    UnsupportedWindowStateFault.class,
    UnsupportedLocaleFault.class,
    UnsupportedModeFault.class,
    ExportNoLongerValidFault.class,
    ResourceSuspendedFault.class,
    ModifyRegistrationRequiredFault.class,
    InvalidSessionFault.class,
    ExportByValueNotSupportedFault.class,
    InvalidCookieFault.class,
    OperationFailedFault.class,
    UnsupportedMimeTypeFault.class
})
public class Fault {


}
