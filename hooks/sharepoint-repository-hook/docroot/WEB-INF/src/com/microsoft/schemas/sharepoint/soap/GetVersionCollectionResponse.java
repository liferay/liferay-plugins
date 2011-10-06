/**
 * GetVersionCollectionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetVersionCollectionResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponseGetVersionCollectionResult getVersionCollectionResult;

    public GetVersionCollectionResponse() {
    }

    public GetVersionCollectionResponse(
           com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponseGetVersionCollectionResult getVersionCollectionResult) {
           this.getVersionCollectionResult = getVersionCollectionResult;
    }

    /**
     * Gets the getVersionCollectionResult value for this GetVersionCollectionResponse.
     *
     * @return getVersionCollectionResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponseGetVersionCollectionResult getGetVersionCollectionResult() {
        return getVersionCollectionResult;
    }

    /**
     * Sets the getVersionCollectionResult value for this GetVersionCollectionResponse.
     *
     * @param getVersionCollectionResult
     */
    public void setGetVersionCollectionResult(com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponseGetVersionCollectionResult getVersionCollectionResult) {
        this.getVersionCollectionResult = getVersionCollectionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetVersionCollectionResponse)) return false;
        GetVersionCollectionResponse other = (GetVersionCollectionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getVersionCollectionResult==null && other.getGetVersionCollectionResult()==null) ||
             (this.getVersionCollectionResult!=null &&
              this.getVersionCollectionResult.equals(other.getGetVersionCollectionResult())));
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
        if (getGetVersionCollectionResult() != null) {
            _hashCode += getGetVersionCollectionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetVersionCollectionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetVersionCollectionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getVersionCollectionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetVersionCollectionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetVersionCollectionResponse>GetVersionCollectionResult"));
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