/**
 * GetListItemChangesSinceTokenResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemChangesSinceTokenResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponseGetListItemChangesSinceTokenResult getListItemChangesSinceTokenResult;

    public GetListItemChangesSinceTokenResponse() {
    }

    public GetListItemChangesSinceTokenResponse(
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponseGetListItemChangesSinceTokenResult getListItemChangesSinceTokenResult) {
           this.getListItemChangesSinceTokenResult = getListItemChangesSinceTokenResult;
    }

    /**
     * Gets the getListItemChangesSinceTokenResult value for this GetListItemChangesSinceTokenResponse.
     *
     * @return getListItemChangesSinceTokenResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponseGetListItemChangesSinceTokenResult getGetListItemChangesSinceTokenResult() {
        return getListItemChangesSinceTokenResult;
    }

    /**
     * Sets the getListItemChangesSinceTokenResult value for this GetListItemChangesSinceTokenResponse.
     *
     * @param getListItemChangesSinceTokenResult
     */
    public void setGetListItemChangesSinceTokenResult(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponseGetListItemChangesSinceTokenResult getListItemChangesSinceTokenResult) {
        this.getListItemChangesSinceTokenResult = getListItemChangesSinceTokenResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemChangesSinceTokenResponse)) return false;
        GetListItemChangesSinceTokenResponse other = (GetListItemChangesSinceTokenResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListItemChangesSinceTokenResult==null && other.getGetListItemChangesSinceTokenResult()==null) ||
             (this.getListItemChangesSinceTokenResult!=null &&
              this.getListItemChangesSinceTokenResult.equals(other.getGetListItemChangesSinceTokenResult())));
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
        if (getGetListItemChangesSinceTokenResult() != null) {
            _hashCode += getGetListItemChangesSinceTokenResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemChangesSinceTokenResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemChangesSinceTokenResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListItemChangesSinceTokenResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListItemChangesSinceTokenResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesSinceTokenResponse>GetListItemChangesSinceTokenResult"));
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