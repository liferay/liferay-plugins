/**
 * GetListAndViewResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListAndViewResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListAndViewResponseGetListAndViewResult getListAndViewResult;

    public GetListAndViewResponse() {
    }

    public GetListAndViewResponse(
           com.microsoft.schemas.sharepoint.soap.GetListAndViewResponseGetListAndViewResult getListAndViewResult) {
           this.getListAndViewResult = getListAndViewResult;
    }

    /**
     * Gets the getListAndViewResult value for this GetListAndViewResponse.
     *
     * @return getListAndViewResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListAndViewResponseGetListAndViewResult getGetListAndViewResult() {
        return getListAndViewResult;
    }

    /**
     * Sets the getListAndViewResult value for this GetListAndViewResponse.
     *
     * @param getListAndViewResult
     */
    public void setGetListAndViewResult(com.microsoft.schemas.sharepoint.soap.GetListAndViewResponseGetListAndViewResult getListAndViewResult) {
        this.getListAndViewResult = getListAndViewResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListAndViewResponse)) return false;
        GetListAndViewResponse other = (GetListAndViewResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListAndViewResult==null && other.getGetListAndViewResult()==null) ||
             (this.getListAndViewResult!=null &&
              this.getListAndViewResult.equals(other.getGetListAndViewResult())));
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
        if (getGetListAndViewResult() != null) {
            _hashCode += getGetListAndViewResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListAndViewResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListAndViewResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListAndViewResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListAndViewResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListAndViewResponse>GetListAndViewResult"));
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