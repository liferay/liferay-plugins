package com.microsoft.schemas.sharepoint.soap;

public class VersionsSoapProxy implements com.microsoft.schemas.sharepoint.soap.VersionsSoap {
  private String _endpoint = null;
  private com.microsoft.schemas.sharepoint.soap.VersionsSoap versionsSoap = null;

  public VersionsSoapProxy() {
    _initVersionsSoapProxy();
  }

  public VersionsSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initVersionsSoapProxy();
  }

  private void _initVersionsSoapProxy() {
    try {
      versionsSoap = (new com.microsoft.schemas.sharepoint.soap.VersionsLocator()).getVersionsSoap();
      if (versionsSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)versionsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)versionsSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (versionsSoap != null)
      ((javax.xml.rpc.Stub)versionsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public com.microsoft.schemas.sharepoint.soap.VersionsSoap getVersionsSoap() {
    if (versionsSoap == null)
      _initVersionsSoapProxy();
    return versionsSoap;
  }

  public com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult getVersions(java.lang.String fileName) throws java.rmi.RemoteException{
    if (versionsSoap == null)
      _initVersionsSoapProxy();
    return versionsSoap.getVersions(fileName);
  }

  public com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult restoreVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException{
    if (versionsSoap == null)
      _initVersionsSoapProxy();
    return versionsSoap.restoreVersion(fileName, fileVersion);
  }

  public com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult deleteVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException{
    if (versionsSoap == null)
      _initVersionsSoapProxy();
    return versionsSoap.deleteVersion(fileName, fileVersion);
  }

  public com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersions(java.lang.String fileName) throws java.rmi.RemoteException{
    if (versionsSoap == null)
      _initVersionsSoapProxy();
    return versionsSoap.deleteAllVersions(fileName);
  }

}