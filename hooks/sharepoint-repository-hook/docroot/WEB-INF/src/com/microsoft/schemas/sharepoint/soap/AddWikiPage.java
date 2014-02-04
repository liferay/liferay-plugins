/**
 * AddWikiPage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class AddWikiPage  implements java.io.Serializable {
    private java.lang.String strListName;

    private java.lang.String listRelPageUrl;

    private java.lang.String wikiContent;

    public AddWikiPage() {
    }

    public AddWikiPage(
           java.lang.String strListName,
           java.lang.String listRelPageUrl,
           java.lang.String wikiContent) {
           this.strListName = strListName;
           this.listRelPageUrl = listRelPageUrl;
           this.wikiContent = wikiContent;
    }

    /**
     * Gets the strListName value for this AddWikiPage.
     *
     * @return strListName
     */
    public java.lang.String getStrListName() {
        return strListName;
    }

    /**
     * Sets the strListName value for this AddWikiPage.
     *
     * @param strListName
     */
    public void setStrListName(java.lang.String strListName) {
        this.strListName = strListName;
    }

    /**
     * Gets the listRelPageUrl value for this AddWikiPage.
     *
     * @return listRelPageUrl
     */
    public java.lang.String getListRelPageUrl() {
        return listRelPageUrl;
    }

    /**
     * Sets the listRelPageUrl value for this AddWikiPage.
     *
     * @param listRelPageUrl
     */
    public void setListRelPageUrl(java.lang.String listRelPageUrl) {
        this.listRelPageUrl = listRelPageUrl;
    }

    /**
     * Gets the wikiContent value for this AddWikiPage.
     *
     * @return wikiContent
     */
    public java.lang.String getWikiContent() {
        return wikiContent;
    }

    /**
     * Sets the wikiContent value for this AddWikiPage.
     *
     * @param wikiContent
     */
    public void setWikiContent(java.lang.String wikiContent) {
        this.wikiContent = wikiContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddWikiPage)) return false;
        AddWikiPage other = (AddWikiPage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.strListName==null && other.getStrListName()==null) ||
             (this.strListName!=null &&
              this.strListName.equals(other.getStrListName()))) &&
            ((this.listRelPageUrl==null && other.getListRelPageUrl()==null) ||
             (this.listRelPageUrl!=null &&
              this.listRelPageUrl.equals(other.getListRelPageUrl()))) &&
            ((this.wikiContent==null && other.getWikiContent()==null) ||
             (this.wikiContent!=null &&
              this.wikiContent.equals(other.getWikiContent())));
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
        if (getStrListName() != null) {
            _hashCode += getStrListName().hashCode();
        }
        if (getListRelPageUrl() != null) {
            _hashCode += getListRelPageUrl().hashCode();
        }
        if (getWikiContent() != null) {
            _hashCode += getWikiContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddWikiPage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">AddWikiPage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strListName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "strListName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listRelPageUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listRelPageUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wikiContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "wikiContent"));
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