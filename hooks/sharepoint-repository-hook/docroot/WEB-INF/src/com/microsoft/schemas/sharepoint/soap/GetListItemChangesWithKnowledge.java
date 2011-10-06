/**
 * GetListItemChangesWithKnowledge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class GetListItemChangesWithKnowledge  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.String viewName;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQuery query;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeViewFields viewFields;

    private java.lang.String rowLimit;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQueryOptions queryOptions;

    private java.lang.String syncScope;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeKnowledge knowledge;

    private com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeContains contains;

    public GetListItemChangesWithKnowledge() {
    }

    public GetListItemChangesWithKnowledge(
           java.lang.String listName,
           java.lang.String viewName,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQuery query,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeViewFields viewFields,
           java.lang.String rowLimit,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQueryOptions queryOptions,
           java.lang.String syncScope,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeKnowledge knowledge,
           com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeContains contains) {
           this.listName = listName;
           this.viewName = viewName;
           this.query = query;
           this.viewFields = viewFields;
           this.rowLimit = rowLimit;
           this.queryOptions = queryOptions;
           this.syncScope = syncScope;
           this.knowledge = knowledge;
           this.contains = contains;
    }

    /**
     * Gets the listName value for this GetListItemChangesWithKnowledge.
     *
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }

    /**
     * Sets the listName value for this GetListItemChangesWithKnowledge.
     *
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }

    /**
     * Gets the viewName value for this GetListItemChangesWithKnowledge.
     *
     * @return viewName
     */
    public java.lang.String getViewName() {
        return viewName;
    }

    /**
     * Sets the viewName value for this GetListItemChangesWithKnowledge.
     *
     * @param viewName
     */
    public void setViewName(java.lang.String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the query value for this GetListItemChangesWithKnowledge.
     *
     * @return query
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQuery getQuery() {
        return query;
    }

    /**
     * Sets the query value for this GetListItemChangesWithKnowledge.
     *
     * @param query
     */
    public void setQuery(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQuery query) {
        this.query = query;
    }

    /**
     * Gets the viewFields value for this GetListItemChangesWithKnowledge.
     *
     * @return viewFields
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeViewFields getViewFields() {
        return viewFields;
    }

    /**
     * Sets the viewFields value for this GetListItemChangesWithKnowledge.
     *
     * @param viewFields
     */
    public void setViewFields(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeViewFields viewFields) {
        this.viewFields = viewFields;
    }

    /**
     * Gets the rowLimit value for this GetListItemChangesWithKnowledge.
     *
     * @return rowLimit
     */
    public java.lang.String getRowLimit() {
        return rowLimit;
    }

    /**
     * Sets the rowLimit value for this GetListItemChangesWithKnowledge.
     *
     * @param rowLimit
     */
    public void setRowLimit(java.lang.String rowLimit) {
        this.rowLimit = rowLimit;
    }

    /**
     * Gets the queryOptions value for this GetListItemChangesWithKnowledge.
     *
     * @return queryOptions
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQueryOptions getQueryOptions() {
        return queryOptions;
    }

    /**
     * Sets the queryOptions value for this GetListItemChangesWithKnowledge.
     *
     * @param queryOptions
     */
    public void setQueryOptions(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQueryOptions queryOptions) {
        this.queryOptions = queryOptions;
    }

    /**
     * Gets the syncScope value for this GetListItemChangesWithKnowledge.
     *
     * @return syncScope
     */
    public java.lang.String getSyncScope() {
        return syncScope;
    }

    /**
     * Sets the syncScope value for this GetListItemChangesWithKnowledge.
     *
     * @param syncScope
     */
    public void setSyncScope(java.lang.String syncScope) {
        this.syncScope = syncScope;
    }

    /**
     * Gets the knowledge value for this GetListItemChangesWithKnowledge.
     *
     * @return knowledge
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeKnowledge getKnowledge() {
        return knowledge;
    }

    /**
     * Sets the knowledge value for this GetListItemChangesWithKnowledge.
     *
     * @param knowledge
     */
    public void setKnowledge(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeKnowledge knowledge) {
        this.knowledge = knowledge;
    }

    /**
     * Gets the contains value for this GetListItemChangesWithKnowledge.
     *
     * @return contains
     */
    public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeContains getContains() {
        return contains;
    }

    /**
     * Sets the contains value for this GetListItemChangesWithKnowledge.
     *
     * @param contains
     */
    public void setContains(com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeContains contains) {
        this.contains = contains;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListItemChangesWithKnowledge)) return false;
        GetListItemChangesWithKnowledge other = (GetListItemChangesWithKnowledge) obj;
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
            ((this.syncScope==null && other.getSyncScope()==null) ||
             (this.syncScope!=null &&
              this.syncScope.equals(other.getSyncScope()))) &&
            ((this.knowledge==null && other.getKnowledge()==null) ||
             (this.knowledge!=null &&
              this.knowledge.equals(other.getKnowledge()))) &&
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
        if (getSyncScope() != null) {
            _hashCode += getSyncScope().hashCode();
        }
        if (getKnowledge() != null) {
            _hashCode += getKnowledge().hashCode();
        }
        if (getContains() != null) {
            _hashCode += getContains().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListItemChangesWithKnowledge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">GetListItemChangesWithKnowledge"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledge>query"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "viewFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledge>viewFields"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledge>queryOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("syncScope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "syncScope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "knowledge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledge>knowledge"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contains");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "contains"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetListItemChangesWithKnowledge>contains"));
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