/**
 * ApplyContentTypeToList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class ApplyContentTypeToList  implements java.io.Serializable {
    private java.lang.String webUrl;

    private java.lang.String contentTypeId;

    private java.lang.String listName;

    public ApplyContentTypeToList() {
    }

    public ApplyContentTypeToList(
           java.lang.String webUrl,
           java.lang.String contentTypeId,
           java.lang.String listName) {
           this.webUrl = webUrl;
           this.contentTypeId = contentTypeId;
           this.listName = listName;
    }

    /**
     * Gets the webUrl value for this ApplyContentTypeToList.
     *
     * @return webUrl
     */
    public java.lang.String getWebUrl() {
        return webUrl;
    }

    /**
     * Sets the webUrl value for this ApplyContentTypeToList.
     *
     * @param webUrl
     */
    public void setWebUrl(java.lang.String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * Gets the contentTypeId value for this ApplyContentTypeToList.
     *
     * @return contentTypeId
     */
    public java.lang.String getContentTypeId() {
        return contentTypeId;
    }

    /**
     * Sets the contentTypeId value for this ApplyContentTypeToList.
     *
     * @param contentTypeId
     */
    public void setContentTypeId(java.lang.String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    /**
     * Gets the listName value for this ApplyContentTypeToList.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this ApplyContentTypeToList.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplyContentTypeToList)) return false;
        ApplyContentTypeToList other = (ApplyContentTypeToList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.webUrl==null && other.getWebUrl()==null) ||
             (this.webUrl!=null &&
              this.webUrl.equals(other.getWebUrl()))) &&
            ((this.contentTypeId==null && other.getContentTypeId()==null) ||
             (this.contentTypeId!=null &&
              this.contentTypeId.equals(other.getContentTypeId()))) &&
            ((this.listName==null && other.getListName()==null) ||
             (this.listName!=null &&
              this.listName.equals(other.getListName())));
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
        if (getWebUrl() != null) {
            _hashCode += getWebUrl().hashCode();
        }
        if (getContentTypeId() != null) {
            _hashCode += getContentTypeId().hashCode();
        }
        if (getListName() != null) {
            _hashCode += getListName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplyContentTypeToList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">ApplyContentTypeToList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "webUrl"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
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