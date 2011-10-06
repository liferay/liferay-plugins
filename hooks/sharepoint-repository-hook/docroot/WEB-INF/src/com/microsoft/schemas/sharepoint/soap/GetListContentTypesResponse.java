/**
 * GetListContentTypesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListContentTypesResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponseGetListContentTypesResult getListContentTypesResult;

    public GetListContentTypesResponse() {
    }

    public GetListContentTypesResponse(
           com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponseGetListContentTypesResult getListContentTypesResult) {
           this.getListContentTypesResult = getListContentTypesResult;
    }

    /**
     * Gets the getListContentTypesResult value for this GetListContentTypesResponse.
     *
     * @return getListContentTypesResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponseGetListContentTypesResult getGetListContentTypesResult() {
        return getListContentTypesResult;
    }

    /**
     * Sets the getListContentTypesResult value for this GetListContentTypesResponse.
     *
     * @param getListContentTypesResult
     */
    public void setGetListContentTypesResult(com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponseGetListContentTypesResult getListContentTypesResult) {
        this.getListContentTypesResult = getListContentTypesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListContentTypesResponse)) return false;
        GetListContentTypesResponse other = (GetListContentTypesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListContentTypesResult==null && other.getGetListContentTypesResult()==null) ||
             (this.getListContentTypesResult!=null &&
              this.getListContentTypesResult.equals(other.getGetListContentTypesResult())));
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
        if (getGetListContentTypesResult() != null) {
            _hashCode += getGetListContentTypesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListContentTypesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListContentTypesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListContentTypesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListContentTypesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListContentTypesResponse>GetListContentTypesResult"));
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