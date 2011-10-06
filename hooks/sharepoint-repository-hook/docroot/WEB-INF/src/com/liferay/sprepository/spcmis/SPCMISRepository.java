/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sprepository.spcmis;

import com.liferay.portal.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryHandler;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryUtil;
import com.liferay.portal.kernel.repository.cmis.Session;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.ListsSoapProxy;
import com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponseUpdateListItemsResult;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsUpdates;
import com.microsoft.schemas.sharepoint.soap.VersionsSoap;
import com.microsoft.schemas.sharepoint.soap.VersionsSoapProxy;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.message.MessageElement;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;
public class SPCMISRepository extends CMISRepositoryHandler {

	public void cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		try {
			String objectId = toFileEntryId(fileEntryId);
			String objectFileRef = getFileRef(getLatestVersionId(objectId));

			ListsSoapProxy listsSoapProxy =
				new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			boolean response = listsSoapProxy.undoCheckOut(objectFileRef);

			if (response == false) {
				throw new RepositoryException(
					"Unable to cancel checkout file with fileEntryId = "
					+ fileEntryId);
			}
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public void checkInFileEntry(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		checkInFileEntry(
			fileEntryId, false, StringPool.BLANK, new ServiceContext());
	}

	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String objectId = toFileEntryId(fileEntryId);
			String objectFileRef = getFileRef(objectId);
			String checkinType = "0";

			if (major) {
				checkinType = "1";
			}

			ListsSoapProxy listsSoapProxy =
				new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(Call.PASSWORD_PROPERTY,
				PrincipalThreadLocal.getPassword());

			boolean response = listsSoapProxy.checkInFile(
				objectFileRef, changeLog, checkinType);

			if (response == false) {
				throw new RepositoryException(
					"Unable to checkin file with fileEntryId = "
					+ fileEntryId);
			}
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public FileEntry checkOutFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		String objectId = toFileEntryId(fileEntryId);

		try {
			String objectFileRef = getFileRef(objectId);

			ListsSoapProxy listsSoapProxy = new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			boolean response = listsSoapProxy.checkOutFile(
				objectFileRef, "false", null);

			if (response == false) {
				throw new RepositoryException(
					"Unable to checkout file with fileEntryId = "
					+ fileEntryId);
			}
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}

		return toFileEntry(objectId);
	}

	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			String objectId = toFileEntryId(fileEntryId);
			String sourceFileRef = getFileRef(objectId);

			objectId = StringUtil.extractFirst(objectId, "-");

			MessageElement batch = new MessageElement (
				new QName(StringPool.BLANK, "Batch"));

			MessageElement method = new MessageElement (
				new QName(StringPool.BLANK, "Method"));

			method.addAttribute(StringPool.BLANK, "ID", "1");
			method.addAttribute(StringPool.BLANK, "Cmd", "Delete");

			MessageElement title = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			title.addAttribute(StringPool.BLANK, "Name", "ID");
			title.addTextNode(objectId);

			MessageElement fileRef = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			fileRef.addAttribute(StringPool.BLANK, "Name", "FileRef");
			fileRef.addTextNode(sourceFileRef);

			method.addChild(title);
			method.addChild(fileRef);

			batch.addChild(method);

			MessageElement[] me = new MessageElement[1];

			me[0] = batch;

			UpdateListItemsUpdates updates = new UpdateListItemsUpdates(me);

			ListsSoapProxy listsSoapProxy =
				new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			UpdateListItemsResponseUpdateListItemsResult response =
				listsSoapProxy.updateListItems(
					getTypeSettingsValue(_REPOSITORY_ID), updates);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			String objectId = toFolderId(folderId);
			String objectFileRef = getFileRef(objectId);

			MessageElement batch = new MessageElement (
				new QName(StringPool.BLANK, "Batch"));

			MessageElement method = new MessageElement (
				new QName(StringPool.BLANK, "Method"));

			method.addAttribute(StringPool.BLANK, "ID", "1");
			method.addAttribute(StringPool.BLANK, "Cmd", "Delete");

			MessageElement title = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			title.addAttribute(StringPool.BLANK, "Name", "ID");
			title.addTextNode(objectId);

			MessageElement fileRef = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			fileRef.addAttribute(StringPool.BLANK, "Name", "FileRef");
			fileRef.addTextNode(objectFileRef);

			method.addChild(title);
			method.addChild(fileRef);

			batch.addChild(method);

			MessageElement[] me = new MessageElement[1];

			me[0] = batch;

			UpdateListItemsUpdates updates = new UpdateListItemsUpdates(me);

			ListsSoapProxy listsSoapProxy =
				new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			UpdateListItemsResponseUpdateListItemsResult response =
				listsSoapProxy.updateListItems(
					getTypeSettingsValue(_REPOSITORY_ID), updates);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public Session getSession() throws PortalException, RepositoryException {
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(
			SessionParameter.ATOMPUB_URL, getTypeSettingsValue(_ATOMPUB_URL));
		parameters.put(
			SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		Locale locale = LocaleUtil.getDefault();

		parameters.put(
			SessionParameter.LOCALE_ISO3166_COUNTRY,
			locale.getCountry());
		parameters.put(
			SessionParameter.LOCALE_ISO639_LANGUAGE, locale.getLanguage());

		parameters.put(SessionParameter.USER, getLogin());
		parameters.put(
			SessionParameter.PASSWORD, PrincipalThreadLocal.getPassword());

		CMISRepositoryUtil.checkRepository(
			getRepositoryId(), parameters, getTypeSettingsProperties(),
			_REPOSITORY_ID);

		Session session = CMISRepositoryUtil.createSession(parameters);

		session.setDefaultContext(
			null, true, true, false, IncludeRelationships.NONE.value(),
			null, false, "cmis:name ASC", true, 1000);

		return session;
	}

	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String sourceFileEntryId = toFileEntryId(fileEntryId);
			String targetFolderId = toFolderId(newFolderId);
			String sourceFileRef = getFileRef(sourceFileEntryId);
			String targetFileRef= getFileRef(targetFolderId);

			if (StringUtil.endsWith(targetFileRef, "/")) {
				targetFileRef += getObjectName(sourceFileEntryId);
			}
			else {
				targetFileRef += "/" + getObjectName(sourceFileEntryId);
			}

			MessageElement batch = new MessageElement (
				new QName(StringPool.BLANK, "Batch"));

			MessageElement method = new MessageElement (
				new QName(StringPool.BLANK, "Method"));

			method.addAttribute(StringPool.BLANK, "ID", "1");
			method.addAttribute(StringPool.BLANK, "Cmd", "Move");

			MessageElement title = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			title.addAttribute(StringPool.BLANK, "Name", "ID");
			title.addTextNode(sourceFileEntryId);

			MessageElement fileRef = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			fileRef.addAttribute(StringPool.BLANK, "Name", "FileRef");
			fileRef.addTextNode(sourceFileRef);

			MessageElement moveNewUrl = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			moveNewUrl.addAttribute(StringPool.BLANK, "Name", "MoveNewUrl");
			moveNewUrl.addTextNode(targetFileRef);

			method.addChild(title);
			method.addChild(fileRef);
			method.addChild(moveNewUrl);

			batch.addChild(method);

			MessageElement[] me = new MessageElement[1];

			me[0] = batch;

			UpdateListItemsUpdates updates = new UpdateListItemsUpdates(me);

			ListsSoapProxy listsSoapProxy =
				new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			UpdateListItemsResponseUpdateListItemsResult response =
				listsSoapProxy.updateListItems(
					getTypeSettingsValue(_REPOSITORY_ID), updates);

			return toFileEntry(sourceFileEntryId);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public Folder moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String sourceFolderId = toFileEntryId(folderId);
			String targetFolderId = toFolderId(parentFolderId);
			String sourceFolderRef = getFileRef(sourceFolderId);
			String targetFolderRef = getFileRef(targetFolderId);

			if (StringUtil.endsWith(targetFolderRef, "/")) {
				targetFolderRef += getObjectName(sourceFolderId);
			}
			else {
				targetFolderRef += "/" + getObjectName(sourceFolderId);
			}

			MessageElement batch = new MessageElement (
				new QName(StringPool.BLANK, "Batch"));

			MessageElement method = new MessageElement (
				new QName(StringPool.BLANK, "Method"));

			method.addAttribute(StringPool.BLANK, "ID", "1");
			method.addAttribute(StringPool.BLANK, "Cmd", "Move");

			MessageElement title = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			title.addAttribute(StringPool.BLANK, "Name", "ID");
			title.addTextNode(sourceFolderId);

			MessageElement fileRef = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			fileRef.addAttribute(StringPool.BLANK, "Name", "FileRef");
			fileRef.addTextNode(sourceFolderRef);

			MessageElement moveNewUrl = new MessageElement (
				new QName(StringPool.BLANK, "Field"));

			moveNewUrl.addAttribute(StringPool.BLANK, "Name", "MoveNewUrl");
			moveNewUrl.addTextNode(targetFolderRef);

			method.addChild(title);
			method.addChild(fileRef);
			method.addChild(moveNewUrl);

			batch.addChild(method);

			MessageElement[] me = new MessageElement[1];

			me[0] = batch;

			UpdateListItemsUpdates updates = new UpdateListItemsUpdates(me);

			ListsSoapProxy listsSoapProxy = new ListsSoapProxy(getListService());

			ListsSoap listsSoap = listsSoapProxy.getListsSoap();

			((Stub)listsSoap)._setProperty(Call.USERNAME_PROPERTY, getLogin());
			((Stub)listsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			listsSoapProxy.updateListItems(
				getTypeSettingsValue(_REPOSITORY_ID), updates);

			return toFolder(sourceFolderId);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String objectId = toFileEntryId(fileEntryId);
			String fileName = getFileRef(objectId);

			if (isCheckOutAllowable(objectId)) {
				checkOutFileEntry(fileEntryId);
			}

			VersionsSoapProxy versionsSoapProxy = new VersionsSoapProxy(
				getVersionService());

			VersionsSoap versionsSoap = versionsSoapProxy.getVersionsSoap();

			((Stub)versionsSoap)._setProperty(
				Call.USERNAME_PROPERTY, getLogin());
			((Stub)versionsSoap)._setProperty(
				Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());

			versionsSoapProxy.restoreVersion(fileName, version);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String objectId = toFileEntryId(fileEntryId);
			String currentTitle = getObjectName(objectId);

			if (isCheckOutAllowable(objectId)) {
				checkOutFileEntry(fileEntryId);
			}

			String checkOutDocumentObjectId = getLatestVersionId(objectId);

			Map<String, Object> properties = null;
			ContentStream contentStream = null;

			if (Validator.isNotNull(title) && !title.equals(currentTitle)) {
				properties = new HashMap<String, Object>();

				properties.put(PropertyIds.NAME, title);
			}

			updateFileEntry(
				checkOutDocumentObjectId, mimeType, properties, is,
				sourceFileName, size, serviceContext);

			return toFileEntry(checkOutDocumentObjectId);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}



	public String[] getSupportedConfigurations() {
		return _SUPPORTED_CONFIGURATIONS;
	}

	public String[][] getSupportedParameters() {
		return _SUPPORTED_PARAMETERS;
	}

	public boolean isDocumentRetrievableByVersionSeriesId() {
		return false;
	}

	public boolean isExtensionRequiredInTitle() {
		return true;
	}

	public boolean isRefreshBeforePermissionCheck() {
		return true;
	}

	protected String getFileRef(String objectId)
		throws PortalException, SystemException {

		String fileEntryPath = StringPool.BLANK;

		try {
			fileEntryPath = getRootFolder() + getObjectPaths(objectId).get(0);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}

		return fileEntryPath;
	}

	protected String getListService() throws InvalidRepositoryException{

		if (_listService != null) {
			return _listService;
		}

		String atomPubURL = getTypeSettingsValue(_ATOMPUB_URL);

		String services = StringUtil.extractFirst(atomPubURL, "/cmis");

		_listService = services + "/Lists.asmx";

		return _listService;
	}

	protected String getRootFolder() throws PortalException, SystemException {
		return getTypeSettingsValue(_SITE_PATH);
	}

	protected String getTypeSettingsValue(String typeSettingsKey)
		throws InvalidRepositoryException {

		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return CMISRepositoryUtil.getTypeSettingsValue(
			typeSettingsProperties, typeSettingsKey);
	}

	protected String getVersionService() throws InvalidRepositoryException{
		if (_versionService != null) {
			return _versionService;
		}

		String atomPubURL = getTypeSettingsValue(_ATOMPUB_URL);

		String services = StringUtil.extractFirst(atomPubURL, "/cmis");

		_versionService = services + "/Versions.asmx";

		return _versionService;
	}

	protected String toFileEntryId(long fileEntryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			fileEntryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {fileEntryId=" + fileEntryId + "}");
		}

		return repositoryEntry.getMappedId();
	}

	protected String toFolderId(long folderId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			folderId);

		if (repositoryEntry == null) {
			throw new NoSuchFolderException(
				"No CMIS folder with {folderId=" + folderId + "}");
		}

		return repositoryEntry.getMappedId();
	}



	private static String _listService = null;

	private static String _rootFolder = null;

	private static String _versionService = null;

	private static final String _ATOMPUB_URL = "ATOMPUB_URL";

	private static final String _CONFIGURATION_ATOMPUB = "SHAREPOINT_ATOMPUB";

	private static final String _REPOSITORY_ID = "REPOSITORY_ID";

	private static final String _SITE_PATH = "SITE_PATH";

	private static final String[] _SUPPORTED_CONFIGURATIONS = {
		_CONFIGURATION_ATOMPUB
	};

	private static final String[][] _SUPPORTED_PARAMETERS = {
		{_ATOMPUB_URL, _REPOSITORY_ID, _SITE_PATH}
	};

}