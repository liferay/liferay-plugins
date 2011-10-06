/**
 * DeleteContentTypeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class DeleteContentTypeResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponseDeleteContentTypeResult deleteContentTypeResult;

    public DeleteContentTypeResponse() {
    }

    public DeleteContentTypeResponse(
           com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponseDeleteContentTypeResult deleteContentTypeResult) {
           this.deleteContentTypeResult = deleteContentTypeResult;
    }

    /**
     * Gets the deleteContentTypeResult value for this DeleteContentTypeResponse.
     *
     * @return deleteContentTypeResult
     */
    public com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponseDeleteContentTypeResult getDeleteContentTypeResult() {
        return deleteContentTypeResult;
    }

    /**
     * Sets the deleteContentTypeResult value for this DeleteContentTypeResponse.
     *
     * @param deleteContentTypeResult
     */
    public void setDeleteContentTypeResult(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponseDeleteContentTypeResult deleteContentTypeResult) {
        this.deleteContentTypeResult = deleteContentTypeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteContentTypeResponse)) return false;
        DeleteContentTypeResponse other = (DeleteContentTypeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.deleteContentTypeResult==null && other.getDeleteContentTypeResult()==null) ||
             (this.deleteContentTypeResult!=null &&
              this.deleteContentTypeResult.equals(other.getDeleteContentTypeResult())));
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
        if (getDeleteContentTypeResult() != null) {
            _hashCode += getDeleteContentTypeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteContentTypeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteContentTypeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteContentTypeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteContentTypeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteContentTypeResponse>DeleteContentTypeResult"));
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