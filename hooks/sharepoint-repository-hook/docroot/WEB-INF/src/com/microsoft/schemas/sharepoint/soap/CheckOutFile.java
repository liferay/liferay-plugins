/**
 * CheckOutFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class CheckOutFile  implements java.io.Serializable {
    private java.lang.String pageUrl;

    private java.lang.String checkoutToLocal;

    private java.lang.String lastmodified;

    public CheckOutFile() {
    }

    public CheckOutFile(
           java.lang.String pageUrl,
           java.lang.String checkoutToLocal,
           java.lang.String lastmodified) {
           this.pageUrl = pageUrl;
           this.checkoutToLocal = checkoutToLocal;
           this.lastmodified = lastmodified;
    }

    /**
     * Gets the pageUrl value for this CheckOutFile.
     *
     * @return pageUrl
     */
    public java.lang.String getPageUrl() {
        return pageUrl;
    }

    /**
     * Sets the pageUrl value for this CheckOutFile.
     *
     * @param pageUrl
     */
    public void setPageUrl(java.lang.String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * Gets the checkoutToLocal value for this CheckOutFile.
     *
     * @return checkoutToLocal
     */
    public java.lang.String getCheckoutToLocal() {
        return checkoutToLocal;
    }

    /**
     * Sets the checkoutToLocal value for this CheckOutFile.
     *
     * @param checkoutToLocal
     */
    public void setCheckoutToLocal(java.lang.String checkoutToLocal) {
        this.checkoutToLocal = checkoutToLocal;
    }

    /**
     * Gets the lastmodified value for this CheckOutFile.
     *
     * @return lastmodified
     */
    public java.lang.String getLastmodified() {
        return lastmodified;
    }

    /**
     * Sets the lastmodified value for this CheckOutFile.
     *
     * @param lastmodified
     */
    public void setLastmodified(java.lang.String lastmodified) {
        this.lastmodified = lastmodified;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckOutFile)) return false;
        CheckOutFile other = (CheckOutFile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.pageUrl==null && other.getPageUrl()==null) ||
             (this.pageUrl!=null &&
              this.pageUrl.equals(other.getPageUrl()))) &&
            ((this.checkoutToLocal==null && other.getCheckoutToLocal()==null) ||
             (this.checkoutToLocal!=null &&
              this.checkoutToLocal.equals(other.getCheckoutToLocal()))) &&
            ((this.lastmodified==null && other.getLastmodified()==null) ||
             (this.lastmodified!=null &&
              this.lastmodified.equals(other.getLastmodified())));
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
        if (getPageUrl() != null) {
            _hashCode += getPageUrl().hashCode();
        }
        if (getCheckoutToLocal() != null) {
            _hashCode += getCheckoutToLocal().hashCode();
        }
        if (getLastmodified() != null) {
            _hashCode += getLastmodified().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckOutFile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">CheckOutFile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "pageUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkoutToLocal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "checkoutToLocal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastmodified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "lastmodified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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