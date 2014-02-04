/**
 * AddDiscussionBoardItemResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class AddDiscussionBoardItemResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponseAddDiscussionBoardItemResult addDiscussionBoardItemResult;

    public AddDiscussionBoardItemResponse() {
    }

    public AddDiscussionBoardItemResponse(
           com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponseAddDiscussionBoardItemResult addDiscussionBoardItemResult) {
           this.addDiscussionBoardItemResult = addDiscussionBoardItemResult;
    }

    /**
     * Gets the addDiscussionBoardItemResult value for this AddDiscussionBoardItemResponse.
     *
     * @return addDiscussionBoardItemResult
     */
    public com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponseAddDiscussionBoardItemResult getAddDiscussionBoardItemResult() {
        return addDiscussionBoardItemResult;
    }

    /**
     * Sets the addDiscussionBoardItemResult value for this AddDiscussionBoardItemResponse.
     *
     * @param addDiscussionBoardItemResult
     */
    public void setAddDiscussionBoardItemResult(com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponseAddDiscussionBoardItemResult addDiscussionBoardItemResult) {
        this.addDiscussionBoardItemResult = addDiscussionBoardItemResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddDiscussionBoardItemResponse)) return false;
        AddDiscussionBoardItemResponse other = (AddDiscussionBoardItemResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.addDiscussionBoardItemResult==null && other.getAddDiscussionBoardItemResult()==null) ||
             (this.addDiscussionBoardItemResult!=null &&
              this.addDiscussionBoardItemResult.equals(other.getAddDiscussionBoardItemResult())));
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
        if (getAddDiscussionBoardItemResult() != null) {
            _hashCode += getAddDiscussionBoardItemResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddDiscussionBoardItemResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">AddDiscussionBoardItemResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addDiscussionBoardItemResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "AddDiscussionBoardItemResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>AddDiscussionBoardItemResponse>AddDiscussionBoardItemResult"));
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