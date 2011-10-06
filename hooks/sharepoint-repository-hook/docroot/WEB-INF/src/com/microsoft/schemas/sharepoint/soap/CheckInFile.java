/**
 * CheckInFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class CheckInFile  implements java.io.Serializable {
    private java.lang.String pageUrl;

    private java.lang.String comment;

    private java.lang.String checkinType;

    public CheckInFile() {
    }

    public CheckInFile(
           java.lang.String pageUrl,
           java.lang.String comment,
           java.lang.String checkinType) {
           this.pageUrl = pageUrl;
           this.comment = comment;
           this.checkinType = checkinType;
    }

    /**
     * Gets the pageUrl value for this CheckInFile.
     *
     * @return pageUrl
     */
    public java.lang.String getPageUrl() {
        return pageUrl;
    }

    /**
     * Sets the pageUrl value for this CheckInFile.
     *
     * @param pageUrl
     */
    public void setPageUrl(java.lang.String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * Gets the comment value for this CheckInFile.
     *
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }

    /**
     * Sets the comment value for this CheckInFile.
     *
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    /**
     * Gets the checkinType value for this CheckInFile.
     *
     * @return checkinType
     */
    public java.lang.String getCheckinType() {
        return checkinType;
    }

    /**
     * Sets the checkinType value for this CheckInFile.
     *
     * @param checkinType
     */
    public void setCheckinType(java.lang.String checkinType) {
        this.checkinType = checkinType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckInFile)) return false;
        CheckInFile other = (CheckInFile) obj;
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
            ((this.comment==null && other.getComment()==null) ||
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.checkinType==null && other.getCheckinType()==null) ||
             (this.checkinType!=null &&
              this.checkinType.equals(other.getCheckinType())));
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
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getCheckinType() != null) {
            _hashCode += getCheckinType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckInFile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">CheckInFile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "pageUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkinType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "CheckinType"));
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