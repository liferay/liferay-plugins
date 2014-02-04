package com.microsoft.schemas.sharepoint.soap;

public class ListsSoapProxy implements com.microsoft.schemas.sharepoint.soap.ListsSoap {
  private String _endpoint = null;
  private com.microsoft.schemas.sharepoint.soap.ListsSoap listsSoap = null;

  public ListsSoapProxy() {
    _initListsSoapProxy();
  }

  public ListsSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initListsSoapProxy();
  }

  private void _initListsSoapProxy() {
    try {
      listsSoap = (new com.microsoft.schemas.sharepoint.soap.ListsLocator()).getListsSoap();
      if (listsSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)listsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)listsSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (listsSoap != null)
      ((javax.xml.rpc.Stub)listsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public com.microsoft.schemas.sharepoint.soap.ListsSoap getListsSoap() {
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap;
  }

  public com.microsoft.schemas.sharepoint.soap.GetListResponseGetListResult getList(java.lang.String listName) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getList(listName);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListAndViewResponseGetListAndViewResult getListAndView(java.lang.String listName, java.lang.String viewName) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListAndView(listName, viewName);
  }

  public void deleteList(java.lang.String listName) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    listsSoap.deleteList(listName);
  }

  public com.microsoft.schemas.sharepoint.soap.AddListResponseAddListResult addList(java.lang.String listName, java.lang.String description, int templateID) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.addList(listName, description, templateID);
  }

  public com.microsoft.schemas.sharepoint.soap.AddListFromFeatureResponseAddListFromFeatureResult addListFromFeature(java.lang.String listName, java.lang.String description, java.lang.String featureID, int templateID) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.addListFromFeature(listName, description, featureID, templateID);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateListResponseUpdateListResult updateList(java.lang.String listName, com.microsoft.schemas.sharepoint.soap.UpdateListListProperties listProperties, com.microsoft.schemas.sharepoint.soap.UpdateListNewFields newFields, com.microsoft.schemas.sharepoint.soap.UpdateListUpdateFields updateFields, com.microsoft.schemas.sharepoint.soap.UpdateListDeleteFields deleteFields, java.lang.String listVersion) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateList(listName, listProperties, newFields, updateFields, deleteFields, listVersion);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListCollectionResponseGetListCollectionResult getListCollection() throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListCollection();
  }

  public com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult getListItems(java.lang.String listName, java.lang.String viewName, com.microsoft.schemas.sharepoint.soap.GetListItemsQuery query, com.microsoft.schemas.sharepoint.soap.GetListItemsViewFields viewFields, java.lang.String rowLimit, com.microsoft.schemas.sharepoint.soap.GetListItemsQueryOptions queryOptions, java.lang.String webID) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListItems(listName, viewName, query, viewFields, rowLimit, queryOptions, webID);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListItemChangesResponseGetListItemChangesResult getListItemChanges(java.lang.String listName, com.microsoft.schemas.sharepoint.soap.GetListItemChangesViewFields viewFields, java.lang.String since, com.microsoft.schemas.sharepoint.soap.GetListItemChangesContains contains) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListItemChanges(listName, viewFields, since, contains);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeResponseGetListItemChangesWithKnowledgeResult getListItemChangesWithKnowledge(java.lang.String listName, java.lang.String viewName, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQuery query, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeViewFields viewFields, java.lang.String rowLimit, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeQueryOptions queryOptions, java.lang.String syncScope, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeKnowledge knowledge, com.microsoft.schemas.sharepoint.soap.GetListItemChangesWithKnowledgeContains contains) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListItemChangesWithKnowledge(listName, viewName, query, viewFields, rowLimit, queryOptions, syncScope, knowledge, contains);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenResponseGetListItemChangesSinceTokenResult getListItemChangesSinceToken(java.lang.String listName, java.lang.String viewName, com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQuery query, com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenViewFields viewFields, java.lang.String rowLimit, com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenQueryOptions queryOptions, java.lang.String changeToken, com.microsoft.schemas.sharepoint.soap.GetListItemChangesSinceTokenContains contains) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListItemChangesSinceToken(listName, viewName, query, viewFields, rowLimit, queryOptions, changeToken, contains);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponseUpdateListItemsResult updateListItems(java.lang.String listName, com.microsoft.schemas.sharepoint.soap.UpdateListItemsUpdates updates) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateListItems(listName, updates);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeResponseUpdateListItemsWithKnowledgeResult updateListItemsWithKnowledge(java.lang.String listName, com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeUpdates updates, java.lang.String syncScope, com.microsoft.schemas.sharepoint.soap.UpdateListItemsWithKnowledgeKnowledge knowledge) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateListItemsWithKnowledge(listName, updates, syncScope, knowledge);
  }

  public com.microsoft.schemas.sharepoint.soap.AddDiscussionBoardItemResponseAddDiscussionBoardItemResult addDiscussionBoardItem(java.lang.String listName, byte[] message) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.addDiscussionBoardItem(listName, message);
  }

  public com.microsoft.schemas.sharepoint.soap.AddWikiPageResponseAddWikiPageResult addWikiPage(java.lang.String strListName, java.lang.String listRelPageUrl, java.lang.String wikiContent) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.addWikiPage(strListName, listRelPageUrl, wikiContent);
  }

  public com.microsoft.schemas.sharepoint.soap.GetVersionCollectionResponseGetVersionCollectionResult getVersionCollection(java.lang.String strlistID, java.lang.String strlistItemID, java.lang.String strFieldName) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getVersionCollection(strlistID, strlistItemID, strFieldName);
  }

  public java.lang.String addAttachment(java.lang.String listName, java.lang.String listItemID, java.lang.String fileName, byte[] attachment) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.addAttachment(listName, listItemID, fileName, attachment);
  }

  public com.microsoft.schemas.sharepoint.soap.GetAttachmentCollectionResponseGetAttachmentCollectionResult getAttachmentCollection(java.lang.String listName, java.lang.String listItemID) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getAttachmentCollection(listName, listItemID);
  }

  public void deleteAttachment(java.lang.String listName, java.lang.String listItemID, java.lang.String url) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    listsSoap.deleteAttachment(listName, listItemID, url);
  }

  public boolean checkOutFile(java.lang.String pageUrl, java.lang.String checkoutToLocal, java.lang.String lastmodified) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.checkOutFile(pageUrl, checkoutToLocal, lastmodified);
  }

  public boolean undoCheckOut(java.lang.String pageUrl) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.undoCheckOut(pageUrl);
  }

  public boolean checkInFile(java.lang.String pageUrl, java.lang.String comment, java.lang.String checkinType) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.checkInFile(pageUrl, comment, checkinType);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListContentTypesResponseGetListContentTypesResult getListContentTypes(java.lang.String listName, java.lang.String contentTypeId) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListContentTypes(listName, contentTypeId);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListContentTypesAndPropertiesResponseGetListContentTypesAndPropertiesResult getListContentTypesAndProperties(java.lang.String listName, java.lang.String contentTypeId, java.lang.String propertyPrefix, java.lang.Boolean includeWebProperties) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListContentTypesAndProperties(listName, contentTypeId, propertyPrefix, includeWebProperties);
  }

  public com.microsoft.schemas.sharepoint.soap.GetListContentTypeResponseGetListContentTypeResult getListContentType(java.lang.String listName, java.lang.String contentTypeId) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.getListContentType(listName, contentTypeId);
  }

  public java.lang.String createContentType(java.lang.String listName, java.lang.String displayName, java.lang.String parentType, com.microsoft.schemas.sharepoint.soap.CreateContentTypeFields fields, com.microsoft.schemas.sharepoint.soap.CreateContentTypeContentTypeProperties contentTypeProperties, java.lang.String addToView) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.createContentType(listName, displayName, parentType, fields, contentTypeProperties, addToView);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeResponseUpdateContentTypeResult updateContentType(java.lang.String listName, java.lang.String contentTypeId, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeContentTypeProperties contentTypeProperties, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeNewFields newFields, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeUpdateFields updateFields, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeDeleteFields deleteFields, java.lang.String addToView) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateContentType(listName, contentTypeId, contentTypeProperties, newFields, updateFields, deleteFields, addToView);
  }

  public com.microsoft.schemas.sharepoint.soap.DeleteContentTypeResponseDeleteContentTypeResult deleteContentType(java.lang.String listName, java.lang.String contentTypeId) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.deleteContentType(listName, contentTypeId);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentResponseUpdateContentTypeXmlDocumentResult updateContentTypeXmlDocument(java.lang.String listName, java.lang.String contentTypeId, com.microsoft.schemas.sharepoint.soap.UpdateContentTypeXmlDocumentNewDocument newDocument) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateContentTypeXmlDocument(listName, contentTypeId, newDocument);
  }

  public com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentResponseUpdateContentTypesXmlDocumentResult updateContentTypesXmlDocument(java.lang.String listName, com.microsoft.schemas.sharepoint.soap.UpdateContentTypesXmlDocumentNewDocument newDocument) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.updateContentTypesXmlDocument(listName, newDocument);
  }

  public com.microsoft.schemas.sharepoint.soap.DeleteContentTypeXmlDocumentResponseDeleteContentTypeXmlDocumentResult deleteContentTypeXmlDocument(java.lang.String listName, java.lang.String contentTypeId, java.lang.String documentUri) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.deleteContentTypeXmlDocument(listName, contentTypeId, documentUri);
  }

  public com.microsoft.schemas.sharepoint.soap.ApplyContentTypeToListResponseApplyContentTypeToListResult applyContentTypeToList(java.lang.String webUrl, java.lang.String contentTypeId, java.lang.String listName) throws java.rmi.RemoteException{
    if (listsSoap == null)
      _initListsSoapProxy();
    return listsSoap.applyContentTypeToList(webUrl, contentTypeId, listName);
  }

}