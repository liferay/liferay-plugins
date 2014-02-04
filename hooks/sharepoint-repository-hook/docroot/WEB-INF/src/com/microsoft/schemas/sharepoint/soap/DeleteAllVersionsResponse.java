/**
 * DeleteAllVersionsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class DeleteAllVersionsResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersionsResult;

    public DeleteAllVersionsResponse() {
    }

    public DeleteAllVersionsResponse(
           com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersionsResult) {
           this.deleteAllVersionsResult = deleteAllVersionsResult;
    }

    /**
     * Gets the deleteAllVersionsResult value for this DeleteAllVersionsResponse.
     *
     * @return deleteAllVersionsResult
     */
    public com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult getDeleteAllVersionsResult() {
        return deleteAllVersionsResult;
    }

    /**
     * Sets the deleteAllVersionsResult value for this DeleteAllVersionsResponse.
     *
     * @param deleteAllVersionsResult
     */
    public void setDeleteAllVersionsResult(com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersionsResult) {
        this.deleteAllVersionsResult = deleteAllVersionsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteAllVersionsResponse)) return false;
        DeleteAllVersionsResponse other = (DeleteAllVersionsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.deleteAllVersionsResult==null && other.getDeleteAllVersionsResult()==null) ||
             (this.deleteAllVersionsResult!=null &&
              this.deleteAllVersionsResult.equals(other.getDeleteAllVersionsResult())));
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
        if (getDeleteAllVersionsResult() != null) {
            _hashCode += getDeleteAllVersionsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteAllVersionsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteAllVersionsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteAllVersionsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteAllVersionsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteAllVersionsResponse>DeleteAllVersionsResult"));
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