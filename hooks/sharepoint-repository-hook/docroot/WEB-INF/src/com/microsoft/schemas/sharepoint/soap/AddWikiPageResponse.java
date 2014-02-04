/**
 * AddWikiPageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class AddWikiPageResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.AddWikiPageResponseAddWikiPageResult addWikiPageResult;

    public AddWikiPageResponse() {
    }

    public AddWikiPageResponse(
           com.microsoft.schemas.sharepoint.soap.AddWikiPageResponseAddWikiPageResult addWikiPageResult) {
           this.addWikiPageResult = addWikiPageResult;
    }

    /**
     * Gets the addWikiPageResult value for this AddWikiPageResponse.
     *
     * @return addWikiPageResult
     */
    public com.microsoft.schemas.sharepoint.soap.AddWikiPageResponseAddWikiPageResult getAddWikiPageResult() {
        return addWikiPageResult;
    }

    /**
     * Sets the addWikiPageResult value for this AddWikiPageResponse.
     *
     * @param addWikiPageResult
     */
    public void setAddWikiPageResult(com.microsoft.schemas.sharepoint.soap.AddWikiPageResponseAddWikiPageResult addWikiPageResult) {
        this.addWikiPageResult = addWikiPageResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddWikiPageResponse)) return false;
        AddWikiPageResponse other = (AddWikiPageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.addWikiPageResult==null && other.getAddWikiPageResult()==null) ||
             (this.addWikiPageResult!=null &&
              this.addWikiPageResult.equals(other.getAddWikiPageResult())));
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
        if (getAddWikiPageResult() != null) {
            _hashCode += getAddWikiPageResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddWikiPageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">AddWikiPageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addWikiPageResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "AddWikiPageResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>AddWikiPageResponse>AddWikiPageResult"));
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