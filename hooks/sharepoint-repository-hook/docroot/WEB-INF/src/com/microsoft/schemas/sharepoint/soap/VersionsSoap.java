/**
 * VersionsSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public interface VersionsSoap extends java.rmi.Remote {
    public com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult getVersions(java.lang.String fileName) throws java.rmi.RemoteException;
    public com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult restoreVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException;
    public com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult deleteVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException;
    public com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersions(java.lang.String fileName) throws java.rmi.RemoteException;
}