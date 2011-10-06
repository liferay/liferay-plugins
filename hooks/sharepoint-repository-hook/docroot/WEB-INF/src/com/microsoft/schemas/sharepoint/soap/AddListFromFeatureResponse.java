/**
 * AddListFromFeatureResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class AddListFromFeatureResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponseAddListFromFeatureResult addListFromFeatureResult;

    public AddListFromFeatureResponse() {
    }

    public AddListFromFeatureResponse(
           com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponseAddListFromFeatureResult addListFromFeatureResult) {
           this.addListFromFeatureResult = addListFromFeatureResult;
    }

    /**
     * Gets the addListFromFeatureResult value for this AddListFromFeatureResponse.
     *
     * @return addListFromFeatureResult
     */
    public com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponseAddListFromFeatureResult getAddListFromFeatureResult() {
        return addListFromFeatureResult;
    }

    /**
     * Sets the addListFromFeatureResult value for this AddListFromFeatureResponse.
     *
     * @param addListFromFeatureResult
     */
    public void setAddListFromFeatureResult(com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponseAddListFromFeatureResult addListFromFeatureResult) {
        this.addListFromFeatureResult = addListFromFeatureResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddListFromFeatureResponse)) return false;
        AddListFromFeatureResponse other = (AddListFromFeatureResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.addListFromFeatureResult==null && other.getAddListFromFeatureResult()==null) ||
             (this.addListFromFeatureResult!=null &&
              this.addListFromFeatureResult.equals(other.getAddListFromFeatureResult())));
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
        if (getAddListFromFeatureResult() != null) {
            _hashCode += getAddListFromFeatureResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddListFromFeatureResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">AddListFromFeatureResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addListFromFeatureResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "AddListFromFeatureResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>AddListFromFeatureResponse>AddListFromFeatureResult"));
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