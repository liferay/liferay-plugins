
package com.liferay.wsrp.soap.v2.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateChange.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StateChange">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="readWrite"/>
 *     &lt;enumeration value="cloneBeforeWrite"/>
 *     &lt;enumeration value="readOnly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StateChange")
@XmlEnum
public enum StateChange {

    @XmlEnumValue("readWrite")
    READ_WRITE("readWrite"),
    @XmlEnumValue("cloneBeforeWrite")
    CLONE_BEFORE_WRITE("cloneBeforeWrite"),
    @XmlEnumValue("readOnly")
    READ_ONLY("readOnly");
    private final String value;

    StateChange(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateChange fromValue(String v) {
        for (StateChange c: StateChange.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
