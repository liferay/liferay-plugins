/**
 * GetListItemChanges.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemChanges  implements java.io.Serializable {
    private java.lang.String listName;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesViewFields viewFields;

    private java.lang.String since;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesContains contains;

    public GetListItemChanges() {
    }

    public GetListItemChanges(
           java.lang.String listName,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesViewFields viewFields,
           java.lang.String since,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesContains contains) {
           this.listName = listName;
           this.viewFields = viewFields;
           this.since = since;
           this.contains = contains;
    }

    /**
     * Gets the listName value for this GetListItemChanges.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this GetListItemChanges.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the viewFields value for this GetListItemChanges.
     *
     * @return viewFields
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesViewFields getViewFields() {
        return viewFields;
    }

    /**
     * Sets the viewFields value for this GetListItemChanges.
     *
     * @param viewFields
     */
    public void setViewFields(com.microsoft.schemas.sharepoint.soap.GetListItemChangesViewFields viewFields) {
        this.viewFields = viewFields;
    }

    /**
     * Gets the since value for this GetListItemChanges.
     *
     * @return since
     */
    public java.lang.String getSince() {
        return since;
    }

    /**
     * Sets the since value for this GetListItemChanges.
     *
     * @param since
     */
    public void setSince(java.lang.String since) {
        this.since = since;
    }

    /**
     * Gets the contains value for this GetListItemChanges.
     *
     * @return contains
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesContains getContains() {
        return contains;
    }

    /**
     * Sets the contains value for this GetListItemChanges.
     *
     * @param contains
     */
    public void setContains(com.microsoft.schemas.sharepoint.soap.GetListItemChangesContains contains) {
        this.contains = contains;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemChanges)) return false;
        GetListItemChanges other = (GetListItemChanges) obj;
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
            ((this.viewFields==null && other.getViewFields()==null) ||
             (this.viewFields!=null &&
              this.viewFields.equals(other.getViewFields()))) &&
            ((this.since==null && other.getSince()==null) ||
             (this.since!=null &&
              this.since.equals(other.getSince()))) &&
            ((this.contains==null && other.getContains()==null) ||
             (this.contains!=null &&
              this.contains.equals(other.getContains())));
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
        if (getViewFields() != null) {
            _hashCode += getViewFields().hashCode();
        }
        if (getSince() != null) {
            _hashCode += getSince().hashCode();
        }
        if (getContains() != null) {
            _hashCode += getContains().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemChanges.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemChanges"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "viewFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChanges>viewFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("since");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "since"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contains");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contains"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChanges>contains"));
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