/**
 * UpdateContentTypeXmlDocumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateContentTypeXmlDocumentResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponseUpdateContentTypeXmlDocumentResult updateContentTypeXmlDocumentResult;

    public UpdateContentTypeXmlDocumentResponse() {
    }

    public UpdateContentTypeXmlDocumentResponse(
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponseUpdateContentTypeXmlDocumentResult updateContentTypeXmlDocumentResult) {
           this.updateContentTypeXmlDocumentResult = updateContentTypeXmlDocumentResult;
    }

    /**
     * Gets the updateContentTypeXmlDocumentResult value for this UpdateContentTypeXmlDocumentResponse.
     *
     * @return updateContentTypeXmlDocumentResult
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponseUpdateContentTypeXmlDocumentResult getUpdateContentTypeXmlDocumentResult() {
        return updateContentTypeXmlDocumentResult;
    }

    /**
     * Sets the updateContentTypeXmlDocumentResult value for this UpdateContentTypeXmlDocumentResponse.
     *
     * @param updateContentTypeXmlDocumentResult
     */
    public void setUpdateContentTypeXmlDocumentResult(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponseUpdateContentTypeXmlDocumentResult updateContentTypeXmlDocumentResult) {
        this.updateContentTypeXmlDocumentResult = updateContentTypeXmlDocumentResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateContentTypeXmlDocumentResponse)) return false;
        UpdateContentTypeXmlDocumentResponse other = (UpdateContentTypeXmlDocumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.updateContentTypeXmlDocumentResult==null && other.getUpdateContentTypeXmlDocumentResult()==null) ||
             (this.updateContentTypeXmlDocumentResult!=null &&
              this.updateContentTypeXmlDocumentResult.equals(other.getUpdateContentTypeXmlDocumentResult())));
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
        if (getUpdateContentTypeXmlDocumentResult() != null) {
            _hashCode += getUpdateContentTypeXmlDocumentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateContentTypeXmlDocumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateContentTypeXmlDocumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateContentTypeXmlDocumentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "UpdateContentTypeXmlDocumentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentTypeXmlDocumentResponse>UpdateContentTypeXmlDocumentResult"));
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