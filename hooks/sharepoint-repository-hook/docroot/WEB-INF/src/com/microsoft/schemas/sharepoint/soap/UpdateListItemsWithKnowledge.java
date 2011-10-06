/**
 * UpdateListItemsWithKnowledge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateListItemsWithKnowledge  implements java.io.Serializable {
    private java.lang.String listName;

    private com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeUpdates updates;

    private java.lang.String syncScope;

    private com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeKnowledge knowledge;

    public UpdateListItemsWithKnowledge() {
    }

    public UpdateListItemsWithKnowledge(
           java.lang.String listName,
           com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeUpdates updates,
           java.lang.String syncScope,
           com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeKnowledge knowledge) {
           this.listName = listName;
           this.updates = updates;
           this.syncScope = syncScope;
           this.knowledge = knowledge;
    }

    /**
     * Gets the listName value for this UpdateListItemsWithKnowledge.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this UpdateListItemsWithKnowledge.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the updates value for this UpdateListItemsWithKnowledge.
     *
     * @return updates
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeUpdates getUpdates() {
        return updates;
    }

    /**
     * Sets the updates value for this UpdateListItemsWithKnowledge.
     *
     * @param updates
     */
    public void setUpdates(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeUpdates updates) {
        this.updates = updates;
    }

    /**
     * Gets the syncScope value for this UpdateListItemsWithKnowledge.
     *
     * @return syncScope
     */
    public java.lang.String getSyncScope() {
        return syncScope;
    }

    /**
     * Sets the syncScope value for this UpdateListItemsWithKnowledge.
     *
     * @param syncScope
     */
    public void setSyncScope(java.lang.String syncScope) {
        this.syncScope = syncScope;
    }

    /**
     * Gets the knowledge value for this UpdateListItemsWithKnowledge.
     *
     * @return knowledge
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeKnowledge getKnowledge() {
        return knowledge;
    }

    /**
     * Sets the knowledge value for this UpdateListItemsWithKnowledge.
     *
     * @param knowledge
     */
    public void setKnowledge(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeKnowledge knowledge) {
        this.knowledge = knowledge;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateListItemsWithKnowledge)) return false;
        UpdateListItemsWithKnowledge other = (UpdateListItemsWithKnowledge) obj;
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
            ((this.updates==null && other.getUpdates()==null) ||
             (this.updates!=null &&
              this.updates.equals(other.getUpdates()))) &&
            ((this.syncScope==null && other.getSyncScope()==null) ||
             (this.syncScope!=null &&
              this.syncScope.equals(other.getSyncScope()))) &&
            ((this.knowledge==null && other.getKnowledge()==null) ||
             (this.knowledge!=null &&
              this.knowledge.equals(other.getKnowledge())));
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
        if (getUpdates() != null) {
            _hashCode += getUpdates().hashCode();
        }
        if (getSyncScope() != null) {
            _hashCode += getSyncScope().hashCode();
        }
        if (getKnowledge() != null) {
            _hashCode += getKnowledge().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateListItemsWithKnowledge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateListItemsWithKnowledge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "updates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateListItemsWithKnowledge>updates"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("syncScope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "syncScope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "knowledge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateListItemsWithKnowledge>knowledge"));
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