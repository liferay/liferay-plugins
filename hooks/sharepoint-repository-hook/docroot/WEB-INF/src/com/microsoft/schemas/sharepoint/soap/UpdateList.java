/**
 * UpdateList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateList  implements java.io.Serializable {
    private java.lang.String listName;

    private com.microsoft.schemas.sharepoint.soap.UpdateListListProperties listProperties;

    private com.microsoft.schemas.sharepoint.soap.UpdateListNewFields newFields;

    private com.microsoft.schemas.sharepoint.soap.UpdateListUpdateFields updateFields;

    private com.microsoft.schemas.sharepoint.soap.UpdateListDeleteFields deleteFields;

    private java.lang.String listVersion;

    public UpdateList() {
    }

    public UpdateList(
           java.lang.String listName,
           com.microsoft.schemas.sharepoint.soap.UpdateListListProperties listProperties,
           com.microsoft.schemas.sharepoint.soap.UpdateListNewFields newFields,
           com.microsoft.schemas.sharepoint.soap.UpdateListUpdateFields updateFields,
           com.microsoft.schemas.sharepoint.soap.UpdateListDeleteFields deleteFields,
           java.lang.String listVersion) {
           this.listName = listName;
           this.listProperties = listProperties;
           this.newFields = newFields;
           this.updateFields = updateFields;
           this.deleteFields = deleteFields;
           this.listVersion = listVersion;
    }

    /**
     * Gets the listName value for this UpdateList.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this UpdateList.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the listProperties value for this UpdateList.
     *
     * @return listProperties
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListListProperties getListProperties() {
        return listProperties;
    }

    /**
     * Sets the listProperties value for this UpdateList.
     *
     * @param listProperties
     */
    public void setListProperties(com.microsoft.schemas.sharepoint.soap.UpdateListListProperties listProperties) {
        this.listProperties = listProperties;
    }

    /**
     * Gets the newFields value for this UpdateList.
     *
     * @return newFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListNewFields getNewFields() {
        return newFields;
    }

    /**
     * Sets the newFields value for this UpdateList.
     *
     * @param newFields
     */
    public void setNewFields(com.microsoft.schemas.sharepoint.soap.UpdateListNewFields newFields) {
        this.newFields = newFields;
    }

    /**
     * Gets the updateFields value for this UpdateList.
     *
     * @return updateFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListUpdateFields getUpdateFields() {
        return updateFields;
    }

    /**
     * Sets the updateFields value for this UpdateList.
     *
     * @param updateFields
     */
    public void setUpdateFields(com.microsoft.schemas.sharepoint.soap.UpdateListUpdateFields updateFields) {
        this.updateFields = updateFields;
    }

    /**
     * Gets the deleteFields value for this UpdateList.
     *
     * @return deleteFields
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListDeleteFields getDeleteFields() {
        return deleteFields;
    }

    /**
     * Sets the deleteFields value for this UpdateList.
     *
     * @param deleteFields
     */
    public void setDeleteFields(com.microsoft.schemas.sharepoint.soap.UpdateListDeleteFields deleteFields) {
        this.deleteFields = deleteFields;
    }

    /**
     * Gets the listVersion value for this UpdateList.
     *
     * @return listVersion
     */
    public java.lang.String getListVersion() {
        return listVersion;
    }

    /**
     * Sets the listVersion value for this UpdateList.
     *
     * @param listVersion
     */
    public void setListVersion(java.lang.String listVersion) {
        this.listVersion = listVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateList)) return false;
        UpdateList other = (UpdateList) obj;
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
            ((this.listProperties==null && other.getListProperties()==null) ||
             (this.listProperties!=null &&
              this.listProperties.equals(other.getListProperties()))) &&
            ((this.newFields==null && other.getNewFields()==null) ||
             (this.newFields!=null &&
              this.newFields.equals(other.getNewFields()))) &&
            ((this.updateFields==null && other.getUpdateFields()==null) ||
             (this.updateFields!=null &&
              this.updateFields.equals(other.getUpdateFields()))) &&
            ((this.deleteFields==null && other.getDeleteFields()==null) ||
             (this.deleteFields!=null &&
              this.deleteFields.equals(other.getDeleteFields()))) &&
            ((this.listVersion==null && other.getListVersion()==null) ||
             (this.listVersion!=null &&
              this.listVersion.equals(other.getListVersion())));
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
        if (getListProperties() != null) {
            _hashCode += getListProperties().hashCode();
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
        if (getListVersion() != null) {
            _hashCode += getListVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateList>listProperties"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "newFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateList>newFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updateFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateList>updateFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "deleteFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateList>deleteFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listVersion"));
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