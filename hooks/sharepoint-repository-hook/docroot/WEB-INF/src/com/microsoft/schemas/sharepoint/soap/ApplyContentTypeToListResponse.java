/**
 * ApplyContentTypeToListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class ApplyContentTypeToListResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponseApplyContentTypeToListResult applyContentTypeToListResult;

    public ApplyContentTypeToListResponse() {
    }

    public ApplyContentTypeToListResponse(
           com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponseApplyContentTypeToListResult applyContentTypeToListResult) {
           this.applyContentTypeToListResult = applyContentTypeToListResult;
    }

    /**
     * Gets the applyContentTypeToListResult value for this ApplyContentTypeToListResponse.
     *
     * @return applyContentTypeToListResult
     */
    public com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponseApplyContentTypeToListResult getApplyContentTypeToListResult() {
        return applyContentTypeToListResult;
    }

    /**
     * Sets the applyContentTypeToListResult value for this ApplyContentTypeToListResponse.
     *
     * @param applyContentTypeToListResult
     */
    public void setApplyContentTypeToListResult(com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponseApplyContentTypeToListResult applyContentTypeToListResult) {
        this.applyContentTypeToListResult = applyContentTypeToListResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApplyContentTypeToListResponse)) return false;
        ApplyContentTypeToListResponse other = (ApplyContentTypeToListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.applyContentTypeToListResult==null && other.getApplyContentTypeToListResult()==null) ||
             (this.applyContentTypeToListResult!=null &&
              this.applyContentTypeToListResult.equals(other.getApplyContentTypeToListResult())));
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
        if (getApplyContentTypeToListResult() != null) {
            _hashCode += getApplyContentTypeToListResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApplyContentTypeToListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">ApplyContentTypeToListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applyContentTypeToListResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "ApplyContentTypeToListResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>ApplyContentTypeToListResponse>ApplyContentTypeToListResult"));
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