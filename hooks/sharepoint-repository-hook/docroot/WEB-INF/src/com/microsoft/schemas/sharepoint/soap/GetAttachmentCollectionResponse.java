/**
 * GetAttachmentCollectionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetAttachmentCollectionResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponseGetAttachmentCollectionResult getAttachmentCollectionResult;

    public GetAttachmentCollectionResponse() {
    }

    public GetAttachmentCollectionResponse(
           com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponseGetAttachmentCollectionResult getAttachmentCollectionResult) {
           this.getAttachmentCollectionResult = getAttachmentCollectionResult;
    }

    /**
     * Gets the getAttachmentCollectionResult value for this GetAttachmentCollectionResponse.
     *
     * @return getAttachmentCollectionResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponseGetAttachmentCollectionResult getGetAttachmentCollectionResult() {
        return getAttachmentCollectionResult;
    }

    /**
     * Sets the getAttachmentCollectionResult value for this GetAttachmentCollectionResponse.
     *
     * @param getAttachmentCollectionResult
     */
    public void setGetAttachmentCollectionResult(com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponseGetAttachmentCollectionResult getAttachmentCollectionResult) {
        this.getAttachmentCollectionResult = getAttachmentCollectionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAttachmentCollectionResponse)) return false;
        GetAttachmentCollectionResponse other = (GetAttachmentCollectionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getAttachmentCollectionResult==null && other.getGetAttachmentCollectionResult()==null) ||
             (this.getAttachmentCollectionResult!=null &&
              this.getAttachmentCollectionResult.equals(other.getGetAttachmentCollectionResult())));
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
        if (getGetAttachmentCollectionResult() != null) {
            _hashCode += getGetAttachmentCollectionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAttachmentCollectionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetAttachmentCollectionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getAttachmentCollectionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetAttachmentCollectionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetAttachmentCollectionResponse>GetAttachmentCollectionResult"));
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