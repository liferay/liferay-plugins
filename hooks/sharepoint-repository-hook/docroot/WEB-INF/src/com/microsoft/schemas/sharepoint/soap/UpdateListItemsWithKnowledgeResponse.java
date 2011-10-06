/**
 * UpdateListItemsWithKnowledgeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateListItemsWithKnowledgeResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponseUpdateListItemsWithKnowledgeResult updateListItemsWithKnowledgeResult;

    public UpdateListItemsWithKnowledgeResponse() {
    }

    public UpdateListItemsWithKnowledgeResponse(
           com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponseUpdateListItemsWithKnowledgeResult updateListItemsWithKnowledgeResult) {
           this.updateListItemsWithKnowledgeResult = updateListItemsWithKnowledgeResult;
    }

    /**
     * Gets the updateListItemsWithKnowledgeResult value for this UpdateListItemsWithKnowledgeResponse.
     *
     * @return updateListItemsWithKnowledgeResult
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponseUpdateListItemsWithKnowledgeResult getUpdateListItemsWithKnowledgeResult() {
        return updateListItemsWithKnowledgeResult;
    }

    /**
     * Sets the updateListItemsWithKnowledgeResult value for this UpdateListItemsWithKnowledgeResponse.
     *
     * @param updateListItemsWithKnowledgeResult
     */
    public void setUpdateListItemsWithKnowledgeResult(com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponseUpdateListItemsWithKnowledgeResult updateListItemsWithKnowledgeResult) {
        this.updateListItemsWithKnowledgeResult = updateListItemsWithKnowledgeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateListItemsWithKnowledgeResponse)) return false;
        UpdateListItemsWithKnowledgeResponse other = (UpdateListItemsWithKnowledgeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.updateListItemsWithKnowledgeResult==null && other.getUpdateListItemsWithKnowledgeResult()==null) ||
             (this.updateListItemsWithKnowledgeResult!=null &&
              this.updateListItemsWithKnowledgeResult.equals(other.getUpdateListItemsWithKnowledgeResult())));
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
        if (getUpdateListItemsWithKnowledgeResult() != null) {
            _hashCode += getUpdateListItemsWithKnowledgeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateListItemsWithKnowledgeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateListItemsWithKnowledgeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateListItemsWithKnowledgeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "UpdateListItemsWithKnowledgeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateListItemsWithKnowledgeResponse>UpdateListItemsWithKnowledgeResult"));
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