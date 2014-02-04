/**
 * GetListContentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListContentType  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String contentTypeId;

    public GetListContentType() {
    }

    public GetListContentType(
           java.lang.String listName,
           java.lang.String contentTypeId) {
           this.listName = listName;
           this.contentTypeId = contentTypeId;
    }

    /**
     * Gets the listName value for this GetListContentType.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this GetListContentType.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the contentTypeId value for this GetListContentType.
     *
     * @return contentTypeId
     */
    public java.lang.String getContentTypeId() {
        return contentTypeId;
    }

    /**
     * Sets the contentTypeId value for this GetListContentType.
     *
     * @param contentTypeId
     */
    public void setContentTypeId(java.lang.String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListContentType)) return false;
        GetListContentType other = (GetListContentType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.listName==null && other.getListName()==null) ||
             (this.listName!=null &&
              this.listName.equals(other.getListName()))) &&
            ((this.contentTypeId==null && other.getContentTypeId()==null) ||
             (this.contentTypeId!=null &&
              this.contentTypeId.equals(other.getContentTypeId())));
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
        if (getListName() != null) {
            _hashCode += getListName().hashCode();
        }
        if (getContentTypeId() != null) {
            _hashCode += getContentTypeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListContentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListContentType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contentTypeId"));
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