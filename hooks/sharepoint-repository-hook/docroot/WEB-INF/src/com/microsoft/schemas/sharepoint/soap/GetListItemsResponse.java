/**
 * GetListItemsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemsResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult getListItemsResult;

    public GetListItemsResponse() {
    }

    public GetListItemsResponse(
           com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult getListItemsResult) {
           this.getListItemsResult = getListItemsResult;
    }

    /**
     * Gets the getListItemsResult value for this GetListItemsResponse.
     *
     * @return getListItemsResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult getGetListItemsResult() {
        return getListItemsResult;
    }

    /**
     * Sets the getListItemsResult value for this GetListItemsResponse.
     *
     * @param getListItemsResult
     */
    public void setGetListItemsResult(com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult getListItemsResult) {
        this.getListItemsResult = getListItemsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemsResponse)) return false;
        GetListItemsResponse other = (GetListItemsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListItemsResult==null && other.getGetListItemsResult()==null) ||
             (this.getListItemsResult!=null &&
              this.getListItemsResult.equals(other.getGetListItemsResult())));
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
        if (getGetListItemsResult() != null) {
            _hashCode += getGetListItemsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListItemsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListItemsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemsResponse>GetListItemsResult"));
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