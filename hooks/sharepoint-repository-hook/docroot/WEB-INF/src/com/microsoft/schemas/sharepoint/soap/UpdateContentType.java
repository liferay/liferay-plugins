/**
 * UpdateContentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateContentType  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String contentTypeId;

    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeContentTypeProperties contentTypeProperties;

    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeNewFields newFields;

    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeUpdateFields updateFields;

    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeDeleteFields deleteFields;

    private java.lang.String addToView;

    public UpdateContentType() {
    }

    public UpdateContentType(
           java.lang.String listName,
           java.lang.String contentTypeId,
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeContentTypeProperties contentTypeProperties,
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeNewFields newFields,
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeUpdateFields updateFields,
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeDeleteFields deleteFields,
           java.lang.String addToView) {
           this.listName = listName;
           this.contentTypeId = contentTypeId;
           this.contentTypeProperties = contentTypeProperties;
           this.newFields = newFields;
           this.updateFields = updateFields;
           this.deleteFields = deleteFields;
           this.addToView = addToView;
    }

    /**
     * Gets the listName value for this UpdateContentType.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this UpdateContentType.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the contentTypeId value for this UpdateContentType.
     *
     * @return contentTypeId
     */
    public java.lang.String getContentTypeId() {
        return contentTypeId;
    }

    /**
     * Sets the contentTypeId value for this UpdateContentType.
     *
     * @param contentTypeId
     */
    public void setContentTypeId(java.lang.String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    /**
     * Gets the contentTypeProperties value for this UpdateContentType.
     *
     * @return contentTypeProperties
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeContentTypeProperties getContentTypeProperties() {
        return contentTypeProperties;
    }

    /**
     * Sets the contentTypeProperties value for this UpdateContentType.
     *
     * @param contentTypeProperties
     */
    public void setContentTypeProperties(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeContentTypeProperties contentTypeProperties) {
        this.contentTypeProperties = contentTypeProperties;
    }

    /**
     * Gets the newFields value for this UpdateContentType.
     *
     * @return newFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeNewFields getNewFields() {
        return newFields;
    }

    /**
     * Sets the newFields value for this UpdateContentType.
     *
     * @param newFields
     */
    public void setNewFields(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeNewFields newFields) {
        this.newFields = newFields;
    }

    /**
     * Gets the updateFields value for this UpdateContentType.
     *
     * @return updateFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeUpdateFields getUpdateFields() {
        return updateFields;
    }

    /**
     * Sets the updateFields value for this UpdateContentType.
     *
     * @param updateFields
     */
    public void setUpdateFields(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeUpdateFields updateFields) {
        this.updateFields = updateFields;
    }

    /**
     * Gets the deleteFields value for this UpdateContentType.
     *
     * @return deleteFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeDeleteFields getDeleteFields() {
        return deleteFields;
    }

    /**
     * Sets the deleteFields value for this UpdateContentType.
     *
     * @param deleteFields
     */
    public void setDeleteFields(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeDeleteFields deleteFields) {
        this.deleteFields = deleteFields;
    }

    /**
     * Gets the addToView value for this UpdateContentType.
     *
     * @return addToView
     */
    public java.lang.String getAddToView() {
        return addToView;
    }

    /**
     * Sets the addToView value for this UpdateContentType.
     *
     * @param addToView
     */
    public void setAddToView(java.lang.String addToView) {
        this.addToView = addToView;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateContentType)) return false;
        UpdateContentType other = (UpdateContentType) obj;
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
            ((this.contentTypeProperties==null && other.getContentTypeProperties()==null) ||
             (this.contentTypeProperties!=null &&
              this.contentTypeProperties.equals(other.getContentTypeProperties()))) &&
            ((this.newFields==null && other.getNewFields()==null) ||
             (this.newFields!=null &&
              this.newFields.equals(other.getNewFields()))) &&
            ((this.updateFields==null && other.getUpdateFields()==null) ||
             (this.updateFields!=null &&
              this.updateFields.equals(other.getUpdateFields()))) &&
            ((this.deleteFields==null && other.getDeleteFields()==null) ||
             (this.deleteFields!=null &&
              this.deleteFields.equals(other.getDeleteFields()))) &&
            ((this.addToView==null && other.getAddToView()==null) ||
             (this.addToView!=null &&
              this.addToView.equals(other.getAddToView())));
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
        if (getContentTypeProperties() != null) {
            _hashCode += getContentTypeProperties().hashCode();
        }
        if (getNewFields() != null) {
            _hashCode += getNewFields().hashCode();
        }
        if (getUpdateFields() != null) {
            _hashCode += getUpdateFields().hashCode();
        }
        if (getDeleteFields() != null) {
            _hashCode += getDeleteFields().hashCode();
        }
        if (getAddToView() != null) {
            _hashCode += getAddToView().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateContentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateContentType"));
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
        elemField.setFieldName("contentTypeProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contentTypeProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentType>contentTypeProperties"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "newFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentType>newFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentType>updateFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentType>deleteFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addToView");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "addToView"));
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