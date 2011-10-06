/**
 * UpdateContentTypeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class UpdateContentTypeResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponseUpdateContentTypeResult updateContentTypeResult;

    public UpdateContentTypeResponse() {
    }

    public UpdateContentTypeResponse(
           com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponseUpdateContentTypeResult updateContentTypeResult) {
           this.updateContentTypeResult = updateContentTypeResult;
    }

    /**
     * Gets the updateContentTypeResult value for this UpdateContentTypeResponse.
     *
     * @return updateContentTypeResult
     */
    public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponseUpdateContentTypeResult getUpdateContentTypeResult() {
        return updateContentTypeResult;
    }

    /**
     * Sets the updateContentTypeResult value for this UpdateContentTypeResponse.
     *
     * @param updateContentTypeResult
     */
    public void setUpdateContentTypeResult(com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponseUpdateContentTypeResult updateContentTypeResult) {
        this.updateContentTypeResult = updateContentTypeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateContentTypeResponse)) return false;
        UpdateContentTypeResponse other = (UpdateContentTypeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.updateContentTypeResult==null && other.getUpdateContentTypeResult()==null) ||
             (this.updateContentTypeResult!=null &&
              this.updateContentTypeResult.equals(other.getUpdateContentTypeResult())));
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
        if (getUpdateContentTypeResult() != null) {
            _hashCode += getUpdateContentTypeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateContentTypeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">UpdateContentTypeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateContentTypeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "UpdateContentTypeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>UpdateContentTypeResponse>UpdateContentTypeResult"));
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