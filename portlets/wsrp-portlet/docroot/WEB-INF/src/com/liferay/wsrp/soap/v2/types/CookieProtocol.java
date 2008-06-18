
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CookieProtocol.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CookieProtocol">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="perUser"/>
 *     &lt;enumeration value="perGroup"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CookieProtocol")
@XmlEnum
public enum CookieProtocol {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("perUser")
    PER_USER("perUser"),
    @XmlEnumValue("perGroup")
    PER_GROUP("perGroup");
    private final String value;

    CookieProtocol(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CookieProtocol fromValue(String v) {
        for (CookieProtocol c: CookieProtocol.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
