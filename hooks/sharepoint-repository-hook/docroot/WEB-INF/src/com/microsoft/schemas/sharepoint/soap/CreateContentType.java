/**
 * CreateContentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class CreateContentType  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String displayName;

    private java.lang.String parentType;

    private com.microsoft.schemas.sharepoint.soap.CreateContentTypeFields fields;

    private com.microsoft.schemas.sharepoint.soap.CreateContentTypeContentTypeProperties contentTypeProperties;

    private java.lang.String addToView;

    public CreateContentType() {
    }

    public CreateContentType(
           java.lang.String listName,
           java.lang.String displayName,
           java.lang.String parentType,
           com.microsoft.schemas.sharepoint.soap.CreateContentTypeFields fields,
           com.microsoft.schemas.sharepoint.soap.CreateContentTypeContentTypeProperties contentTypeProperties,
           java.lang.String addToView) {
           this.listName = listName;
           this.displayName = displayName;
           this.parentType = parentType;
           this.fields = fields;
           this.contentTypeProperties = contentTypeProperties;
           this.addToView = addToView;
    }

    /**
     * Gets the listName value for this CreateContentType.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this CreateContentType.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the displayName value for this CreateContentType.
     *
     * @return displayName
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the displayName value for this CreateContentType.
     *
     * @param displayName
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the parentType value for this CreateContentType.
     *
     * @return parentType
     */
    public java.lang.String getParentType() {
        return parentType;
    }

    /**
     * Sets the parentType value for this CreateContentType.
     *
     * @param parentType
     */
    public void setParentType(java.lang.String parentType) {
        this.parentType = parentType;
    }

    /**
     * Gets the fields value for this CreateContentType.
     *
     * @return fields
     */
    public com.microsoft.schemas.sharepoint.soap.CreateContentTypeFields getFields() {
        return fields;
    }

    /**
     * Sets the fields value for this CreateContentType.
     *
     * @param fields
     */
    public void setFields(com.microsoft.schemas.sharepoint.soap.CreateContentTypeFields fields) {
        this.fields = fields;
    }

    /**
     * Gets the contentTypeProperties value for this CreateContentType.
     *
     * @return contentTypeProperties
     */
    public com.microsoft.schemas.sharepoint.soap.CreateContentTypeContentTypeProperties getContentTypeProperties() {
        return contentTypeProperties;
    }

    /**
     * Sets the contentTypeProperties value for this CreateContentType.
     *
     * @param contentTypeProperties
     */
    public void setContentTypeProperties(com.microsoft.schemas.sharepoint.soap.CreateContentTypeContentTypeProperties contentTypeProperties) {
        this.contentTypeProperties = contentTypeProperties;
    }

    /**
     * Gets the addToView value for this CreateContentType.
     *
     * @return addToView
     */
    public java.lang.String getAddToView() {
        return addToView;
    }

    /**
     * Sets the addToView value for this CreateContentType.
     *
     * @param addToView
     */
    public void setAddToView(java.lang.String addToView) {
        this.addToView = addToView;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateContentType)) return false;
        CreateContentType other = (CreateContentType) obj;
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
            ((this.displayName==null && other.getDisplayName()==null) ||
             (this.displayName!=null &&
              this.displayName.equals(other.getDisplayName()))) &&
            ((this.parentType==null && other.getParentType()==null) ||
             (this.parentType!=null &&
              this.parentType.equals(other.getParentType()))) &&
            ((this.fields==null && other.getFields()==null) ||
             (this.fields!=null &&
              this.fields.equals(other.getFields()))) &&
            ((this.contentTypeProperties==null && other.getContentTypeProperties()==null) ||
             (this.contentTypeProperties!=null &&
              this.contentTypeProperties.equals(other.getContentTypeProperties()))) &&
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
        if (getDisplayName() != null) {
            _hashCode += getDisplayName().hashCode();
        }
        if (getParentType() != null) {
            _hashCode += getParentType().hashCode();
        }
        if (getFields() != null) {
            _hashCode += getFields().hashCode();
        }
        if (getContentTypeProperties() != null) {
            _hashCode += getContentTypeProperties().hashCode();
        }
        if (getAddToView() != null) {
            _hashCode += getAddToView().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateContentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">CreateContentType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "displayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "parentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>CreateContentType>fields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentTypeProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contentTypeProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>CreateContentType>contentTypeProperties"));
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