/**
 * DeleteContentTypeXmlDocumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class DeleteContentTypeXmlDocumentResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponseDeleteContentTypeXmlDocumentResult deleteContentTypeXmlDocumentResult;

    public DeleteContentTypeXmlDocumentResponse() {
    }

    public DeleteContentTypeXmlDocumentResponse(
           com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponseDeleteContentTypeXmlDocumentResult deleteContentTypeXmlDocumentResult) {
           this.deleteContentTypeXmlDocumentResult = deleteContentTypeXmlDocumentResult;
    }

    /**
     * Gets the deleteContentTypeXmlDocumentResult value for this DeleteContentTypeXmlDocumentResponse.
     *
     * @return deleteContentTypeXmlDocumentResult
     */
    public com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponseDeleteContentTypeXmlDocumentResult getDeleteContentTypeXmlDocumentResult() {
        return deleteContentTypeXmlDocumentResult;
    }

    /**
     * Sets the deleteContentTypeXmlDocumentResult value for this DeleteContentTypeXmlDocumentResponse.
     *
     * @param deleteContentTypeXmlDocumentResult
     */
    public void setDeleteContentTypeXmlDocumentResult(com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponseDeleteContentTypeXmlDocumentResult deleteContentTypeXmlDocumentResult) {
        this.deleteContentTypeXmlDocumentResult = deleteContentTypeXmlDocumentResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteContentTypeXmlDocumentResponse)) return false;
        DeleteContentTypeXmlDocumentResponse other = (DeleteContentTypeXmlDocumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.deleteContentTypeXmlDocumentResult==null && other.getDeleteContentTypeXmlDocumentResult()==null) ||
             (this.deleteContentTypeXmlDocumentResult!=null &&
              this.deleteContentTypeXmlDocumentResult.equals(other.getDeleteContentTypeXmlDocumentResult())));
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
        if (getDeleteContentTypeXmlDocumentResult() != null) {
            _hashCode += getDeleteContentTypeXmlDocumentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteContentTypeXmlDocumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteContentTypeXmlDocumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteContentTypeXmlDocumentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteContentTypeXmlDocumentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteContentTypeXmlDocumentResponse>DeleteContentTypeXmlDocumentResult"));
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