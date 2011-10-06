/**
 * GetListItemChangesWithKnowledgeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemChangesWithKnowledgeResponse  implements java.io.Serializable {
    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponseGetListItemChangesWithKnowledgeResult getListItemChangesWithKnowledgeResult;

    public GetListItemChangesWithKnowledgeResponse() {
    }

    public GetListItemChangesWithKnowledgeResponse(
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponseGetListItemChangesWithKnowledgeResult getListItemChangesWithKnowledgeResult) {
           this.getListItemChangesWithKnowledgeResult = getListItemChangesWithKnowledgeResult;
    }

    /**
     * Gets the getListItemChangesWithKnowledgeResult value for this GetListItemChangesWithKnowledgeResponse.
     *
     * @return getListItemChangesWithKnowledgeResult
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponseGetListItemChangesWithKnowledgeResult getGetListItemChangesWithKnowledgeResult() {
        return getListItemChangesWithKnowledgeResult;
    }

    /**
     * Sets the getListItemChangesWithKnowledgeResult value for this GetListItemChangesWithKnowledgeResponse.
     *
     * @param getListItemChangesWithKnowledgeResult
     */
    public void setGetListItemChangesWithKnowledgeResult(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponseGetListItemChangesWithKnowledgeResult getListItemChangesWithKnowledgeResult) {
        this.getListItemChangesWithKnowledgeResult = getListItemChangesWithKnowledgeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemChangesWithKnowledgeResponse)) return false;
        GetListItemChangesWithKnowledgeResponse other = (GetListItemChangesWithKnowledgeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getListItemChangesWithKnowledgeResult==null && other.getGetListItemChangesWithKnowledgeResult()==null) ||
             (this.getListItemChangesWithKnowledgeResult!=null &&
              this.getListItemChangesWithKnowledgeResult.equals(other.getGetListItemChangesWithKnowledgeResult())));
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
        if (getGetListItemChangesWithKnowledgeResult() != null) {
            _hashCode += getGetListItemChangesWithKnowledgeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemChangesWithKnowledgeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemChangesWithKnowledgeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getListItemChangesWithKnowledgeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetListItemChangesWithKnowledgeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledgeResponse>GetListItemChangesWithKnowledgeResult"));
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