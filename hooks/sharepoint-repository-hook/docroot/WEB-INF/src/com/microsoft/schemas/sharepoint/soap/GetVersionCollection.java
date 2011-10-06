/**
 * GetVersionCollection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetVersionCollection  implements java.io.Serializable {
    private java.lang.String strlistID;

    private java.lang.String strlistItemID;

    private java.lang.String strFieldName;

    public GetVersionCollection() {
    }

    public GetVersionCollection(
           java.lang.String strlistID,
           java.lang.String strlistItemID,
           java.lang.String strFieldName) {
           this.strlistID = strlistID;
           this.strlistItemID = strlistItemID;
           this.strFieldName = strFieldName;
    }

    /**
     * Gets the strlistID value for this GetVersionCollection.
     *
     * @return strlistID
     */
    public java.lang.String getStrlistID() {
        return strlistID;
    }

    /**
     * Sets the strlistID value for this GetVersionCollection.
     *
     * @param strlistID
     */
    public void setStrlistID(java.lang.String strlistID) {
        this.strlistID = strlistID;
    }

    /**
     * Gets the strlistItemID value for this GetVersionCollection.
     *
     * @return strlistItemID
     */
    public java.lang.String getStrlistItemID() {
        return strlistItemID;
    }

    /**
     * Sets the strlistItemID value for this GetVersionCollection.
     *
     * @param strlistItemID
     */
    public void setStrlistItemID(java.lang.String strlistItemID) {
        this.strlistItemID = strlistItemID;
    }

    /**
     * Gets the strFieldName value for this GetVersionCollection.
     *
     * @return strFieldName
     */
    public java.lang.String getStrFieldName() {
        return strFieldName;
    }

    /**
     * Sets the strFieldName value for this GetVersionCollection.
     *
     * @param strFieldName
     */
    public void setStrFieldName(java.lang.String strFieldName) {
        this.strFieldName = strFieldName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetVersionCollection)) return false;
        GetVersionCollection other = (GetVersionCollection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.strlistID==null && other.getStrlistID()==null) ||
             (this.strlistID!=null &&
              this.strlistID.equals(other.getStrlistID()))) &&
            ((this.strlistItemID==null && other.getStrlistItemID()==null) ||
             (this.strlistItemID!=null &&
              this.strlistItemID.equals(other.getStrlistItemID()))) &&
            ((this.strFieldName==null && other.getStrFieldName()==null) ||
             (this.strFieldName!=null &&
              this.strFieldName.equals(other.getStrFieldName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStrlistID() != null) {
            _hashCode += getStrlistID().hashCode();
        }
        if (getStrlistItemID() != null) {
            _hashCode += getStrlistItemID().hashCode();
        }
        if (getStrFieldName() != null) {
            _hashCode += getStrFieldName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetVersionCollection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetVersionCollection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strlistID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "strlistID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strlistItemID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "strlistItemID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "strFieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}