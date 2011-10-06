/**
 * UpdateContentTypeXmlDocument.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateContentTypeXmlDocument  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String contentTypeId;

    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentNewDocument newDocument;

    public UpdateContentTypeXmlDocument() {
    }

    public UpdateContentTypeXmlDocument(
           java.lang.String listName,
           java.lang.String contentTypeId,
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentNewDocument newDocument) {
           this.listName = listName;
           this.contentTypeId = contentTypeId;
           this.newDocument = newDocument;
    }

    /**
     * Gets the listName value for this UpdateContentTypeXmlDocument.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this UpdateContentTypeXmlDocument.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the contentTypeId value for this UpdateContentTypeXmlDocument.
     *
     * @return contentTypeId
     */
    public java.lang.String getContentTypeId() {
        return contentTypeId;
    }

    /**
     * Sets the contentTypeId value for this UpdateContentTypeXmlDocument.
     *
     * @param contentTypeId
     */
    public void setContentTypeId(java.lang.String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    /**
     * Gets the newDocument value for this UpdateContentTypeXmlDocument.
     *
     * @return newDocument
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentNewDocument getNewDocument() {
        return newDocument;
    }

    /**
     * Sets the newDocument value for this UpdateContentTypeXmlDocument.
     *
     * @param newDocument
     */
    public void setNewDocument(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentNewDocument newDocument) {
        this.newDocument = newDocument;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateContentTypeXmlDocument)) return false;
        UpdateContentTypeXmlDocument other = (UpdateContentTypeXmlDocument) obj;
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
              this.contentTypeId.equals(other.getContentTypeId()))) &&
            ((this.newDocument==null && other.getNewDocument()==null) ||
             (this.newDocument!=null &&
              this.newDocument.equals(other.getNewDocument())));
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
        if (getNewDocument() != null) {
            _hashCode += getNewDocument().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateContentTypeXmlDocument.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateContentTypeXmlDocument"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newDocument");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "newDocument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentTypeXmlDocument>newDocument"));
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