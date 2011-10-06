/**
 * GetListContentTypesAndPropertiesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListContentTypesAndPropertiesResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponseGetListContentTypesAndPropertiesResult getListContentTypesAndPropertiesResult;

    public GetListContentTypesAndPropertiesResponse() {
    }

    public GetListContentTypesAndPropertiesResponse(
           com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponseGetListContentTypesAndPropertiesResult getListContentTypesAndPropertiesResult) {
           this.getListContentTypesAndPropertiesResult = getListContentTypesAndPropertiesResult;
    }

    /**
     * Gets the getListContentTypesAndPropertiesResult value for this GetListContentTypesAndPropertiesResponse.
     *
     * @return getListContentTypesAndPropertiesResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponseGetListContentTypesAndPropertiesResult getGetListContentTypesAndPropertiesResult() {
        return getListContentTypesAndPropertiesResult;
    }

    /**
     * Sets the getListContentTypesAndPropertiesResult value for this GetListContentTypesAndPropertiesResponse.
     *
     * @param getListContentTypesAndPropertiesResult
     */
    public void setGetListContentTypesAndPropertiesResult(com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponseGetListContentTypesAndPropertiesResult getListContentTypesAndPropertiesResult) {
        this.getListContentTypesAndPropertiesResult = getListContentTypesAndPropertiesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListContentTypesAndPropertiesResponse)) return false;
        GetListContentTypesAndPropertiesResponse other = (GetListContentTypesAndPropertiesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListContentTypesAndPropertiesResult==null && other.getGetListContentTypesAndPropertiesResult()==null) ||
             (this.getListContentTypesAndPropertiesResult!=null &&
              this.getListContentTypesAndPropertiesResult.equals(other.getGetListContentTypesAndPropertiesResult())));
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
        if (getGetListContentTypesAndPropertiesResult() != null) {
            _hashCode += getGetListContentTypesAndPropertiesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListContentTypesAndPropertiesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListContentTypesAndPropertiesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListContentTypesAndPropertiesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListContentTypesAndPropertiesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListContentTypesAndPropertiesResponse>GetListContentTypesAndPropertiesResult"));
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