/**
 * VersionsSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas.sharepoint.soap;

public class VersionsSoapStub extends org.apache.axis.client.Stub implements com.microsoft.schemas.sharepoint.soap.VersionsSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1() {
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetVersions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetVersionsResponse>GetVersionsResult"));
        oper.setReturnClass(com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetVersionsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RestoreVersion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileVersion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>RestoreVersionResponse>RestoreVersionResult"));
        oper.setReturnClass(com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "RestoreVersionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteVersion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileVersion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteVersionResponse>DeleteVersionResult"));
        oper.setReturnClass(com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteVersionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteAllVersions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "fileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteAllVersionsResponse>DeleteAllVersionsResult"));
        oper.setReturnClass(com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteAllVersionsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

    }

    public VersionsSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public VersionsSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public VersionsSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteAllVersionsResponse>DeleteAllVersionsResult");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>DeleteVersionResponse>DeleteVersionResult");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>GetVersionsResponse>GetVersionsResult");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">>RestoreVersionResponse>RestoreVersionResult");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteAllVersions");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteAllVersions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteAllVersionsResponse");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteVersion");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteVersion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">DeleteVersionResponse");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.DeleteVersionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">RestoreVersion");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.RestoreVersion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", ">RestoreVersionResponse");
            cachedSerQNames.add(qName);
            cls = com.microsoft.schemas.sharepoint.soap.RestoreVersionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult getVersions(java.lang.String fileName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.microsoft.com/sharepoint/soap/GetVersions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "GetVersions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult restoreVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.microsoft.com/sharepoint/soap/RestoreVersion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "RestoreVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileName, fileVersion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult deleteVersion(java.lang.String fileName, java.lang.String fileVersion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.microsoft.com/sharepoint/soap/DeleteVersion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileName, fileVersion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.microsoft.schemas.sharepoint.soap.DeleteVersionResponseDeleteVersionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult deleteAllVersions(java.lang.String fileName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.microsoft.com/sharepoint/soap/DeleteAllVersions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.microsoft.com/sharepoint/soap/", "DeleteAllVersions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.microsoft.schemas.sharepoint.soap.DeleteAllVersionsResponseDeleteAllVersionsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}