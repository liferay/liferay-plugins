/**
 * Lists.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public interface Lists extends javax.xml.rpc.Service {
    public java.lang.String getListsSoapAddress();

    public com.microsoft.schemas.sharepoint.soap.ListsSoap getListsSoap() throws javax.xml.rpc.ServiceException;

    public com.microsoft.schemas.sharepoint.soap.ListsSoap getListsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}