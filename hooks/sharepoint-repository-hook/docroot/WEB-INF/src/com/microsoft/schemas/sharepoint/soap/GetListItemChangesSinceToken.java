/**
 * GetListItemChangesSinceToken.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemChangesSinceToken  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String viewName;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQuery query;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenViewFields viewFields;

    private java.lang.String rowLimit;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQueryOptions queryOptions;

    private java.lang.String changeToken;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenContains contains;

    public GetListItemChangesSinceToken() {
    }

    public GetListItemChangesSinceToken(
           java.lang.String listName,
           java.lang.String viewName,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQuery query,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenViewFields viewFields,
           java.lang.String rowLimit,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQueryOptions queryOptions,
           java.lang.String changeToken,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenContains contains) {
           this.listName = listName;
           this.viewName = viewName;
           this.query = query;
           this.viewFields = viewFields;
           this.rowLimit = rowLimit;
           this.queryOptions = queryOptions;
           this.changeToken = changeToken;
           this.contains = contains;
    }

    /**
     * Gets the listName value for this GetListItemChangesSinceToken.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this GetListItemChangesSinceToken.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the viewName value for this GetListItemChangesSinceToken.
     *
     * @return viewName
     */
    public java.lang.String getViewName() {
        return viewName;
    }

    /**
     * Sets the viewName value for this GetListItemChangesSinceToken.
     *
     * @param viewName
     */
    public void setViewName(java.lang.String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the query value for this GetListItemChangesSinceToken.
     *
     * @return query
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQuery getQuery() {
        return query;
    }

    /**
     * Sets the query value for this GetListItemChangesSinceToken.
     *
     * @param query
     */
    public void setQuery(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQuery query) {
        this.query = query;
    }

    /**
     * Gets the viewFields value for this GetListItemChangesSinceToken.
     *
     * @return viewFields
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenViewFields getViewFields() {
        return viewFields;
    }

    /**
     * Sets the viewFields value for this GetListItemChangesSinceToken.
     *
     * @param viewFields
     */
    public void setViewFields(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenViewFields viewFields) {
        this.viewFields = viewFields;
    }

    /**
     * Gets the rowLimit value for this GetListItemChangesSinceToken.
     *
     * @return rowLimit
     */
    public java.lang.String getRowLimit() {
        return rowLimit;
    }

    /**
     * Sets the rowLimit value for this GetListItemChangesSinceToken.
     *
     * @param rowLimit
     */
    public void setRowLimit(java.lang.String rowLimit) {
        this.rowLimit = rowLimit;
    }

    /**
     * Gets the queryOptions value for this GetListItemChangesSinceToken.
     *
     * @return queryOptions
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQueryOptions getQueryOptions() {
        return queryOptions;
    }

    /**
     * Sets the queryOptions value for this GetListItemChangesSinceToken.
     *
     * @param queryOptions
     */
    public void setQueryOptions(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQueryOptions queryOptions) {
        this.queryOptions = queryOptions;
    }

    /**
     * Gets the changeToken value for this GetListItemChangesSinceToken.
     *
     * @return changeToken
     */
    public java.lang.String getChangeToken() {
        return changeToken;
    }

    /**
     * Sets the changeToken value for this GetListItemChangesSinceToken.
     *
     * @param changeToken
     */
    public void setChangeToken(java.lang.String changeToken) {
        this.changeToken = changeToken;
    }

    /**
     * Gets the contains value for this GetListItemChangesSinceToken.
     *
     * @return contains
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenContains getContains() {
        return contains;
    }

    /**
     * Sets the contains value for this GetListItemChangesSinceToken.
     *
     * @param contains
     */
    public void setContains(com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenContains contains) {
        this.contains = contains;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemChangesSinceToken)) return false;
        GetListItemChangesSinceToken other = (GetListItemChangesSinceToken) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.listName==null && other.getListName()==null) ||
             (this.listName!=null &&
              this.listName.equals(other.getListName()))) &&
            ((this.viewName==null && other.getViewName()==null) ||
             (this.viewName!=null &&
              this.viewName.equals(other.getViewName()))) &&
            ((this.query==null && other.getQuery()==null) ||
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.viewFields==null && other.getViewFields()==null) ||
             (this.viewFields!=null &&
              this.viewFields.equals(other.getViewFields()))) &&
            ((this.rowLimit==null && other.getRowLimit()==null) ||
             (this.rowLimit!=null &&
              this.rowLimit.equals(other.getRowLimit()))) &&
            ((this.queryOptions==null && other.getQueryOptions()==null) ||
             (this.queryOptions!=null &&
              this.queryOptions.equals(other.getQueryOptions()))) &&
            ((this.changeToken==null && other.getChangeToken()==null) ||
             (this.changeToken!=null &&
              this.changeToken.equals(other.getChangeToken()))) &&
            ((this.contains==null && other.getContains()==null) ||
             (this.contains!=null &&
              this.contains.equals(other.getContains())));
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
        if (getListName() != null) {
            _hashCode += getListName().hashCode();
        }
        if (getViewName() != null) {
            _hashCode += getViewName().hashCode();
        }
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getViewFields() != null) {
            _hashCode += getViewFields().hashCode();
        }
        if (getRowLimit() != null) {
            _hashCode += getRowLimit().hashCode();
        }
        if (getQueryOptions() != null) {
            _hashCode += getQueryOptions().hashCode();
        }
        if (getChangeToken() != null) {
            _hashCode += getChangeToken().hashCode();
        }
        if (getContains() != null) {
            _hashCode += getContains().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemChangesSinceToken.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemChangesSinceToken"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "listName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "viewName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesSinceToken>query"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "viewFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesSinceToken>viewFields"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "rowLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "queryOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesSinceToken>queryOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "changeToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contains");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contains"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesSinceToken>contains"));
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