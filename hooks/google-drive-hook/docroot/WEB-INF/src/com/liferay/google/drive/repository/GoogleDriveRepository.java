/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.google.drive.repository;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.google.drive.repository.model.GoogleDriveFileEntry;
import com.liferay.google.drive.repository.model.GoogleDriveFileVersion;
import com.liferay.google.drive.repository.model.GoogleDriveFolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.repository.external.CredentialsProvider;
import com.liferay.repository.external.ExtRepository;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryFileEntry;
import com.liferay.repository.external.ExtRepositoryFileVersion;
import com.liferay.repository.external.ExtRepositoryFileVersionDescriptor;
import com.liferay.repository.external.ExtRepositoryFolder;
import com.liferay.repository.external.ExtRepositoryObject;
import com.liferay.repository.external.ExtRepositoryObjectType;
import com.liferay.repository.external.ExtRepositorySearchResult;
import com.liferay.repository.external.search.ExtRepositoryQueryMapper;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 */
public class GoogleDriveRepository
	extends ExtRepositoryAdapter implements ExtRepository {

	public GoogleDriveRepository() {
		super(null);
	}

	@Override
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException {

		File file = addFile(
			extRepositoryParentFolderKey, mimeType, title, description,
			inputStream);

		return new GoogleDriveFileEntry(file);
	}

	@Override
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException {

		File file = addFile(
			extRepositoryParentFolderKey, _FOLDER_MIME_TYPE, name, description,
			null);

		return new GoogleDriveFolder(file, getRootFolderKey());
	}

	@Override
	public ExtRepositoryFileVersion cancelCheckOut(
		String extRepositoryFileEntryKey) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void checkInExtRepositoryFileEntry(
		String extRepositoryFileEntryKey, boolean createMajorVersion,
		String changeLog) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
		String extRepositoryFileEntryKey) {

		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			File newFile = new File();

			ParentReference parentReference = new ParentReference();

			parentReference.setId(newExtRepositoryFolderKey);

			newFile.setParents(Arrays.asList(parentReference));

			Drive.Files.Copy driveFilesCopy = driveFiles.copy(
				extRepositoryFileEntryKey, newFile);

			driveFilesCopy.execute();

			T extRepositoryObject = null;

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				extRepositoryObject = (T)new GoogleDriveFolder(
					newFile, getRootFolderKey());
			}
			else {
				extRepositoryObject = (T)new GoogleDriveFileEntry(newFile);
			}

			return extRepositoryObject;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Delete driveFilesDelete = driveFiles.delete(
				extRepositoryObjectKey);

			driveFilesDelete.execute();

			GoogleDriveCache googleDriveCache = GoogleDriveCache.getInstance();

			googleDriveCache.remove(extRepositoryObjectKey);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException {

		GoogleDriveFileEntry googleDriveFileEntry =
			(GoogleDriveFileEntry)extRepositoryFileEntry;

		return getContentStream(googleDriveFileEntry.getDownloadURL());
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException {

		GoogleDriveFileVersion googleDriveFileVersion =
			(GoogleDriveFileVersion)extRepositoryFileVersion;

		return getContentStream(googleDriveFileVersion.getDownloadURL());
	}

	public Drive getDrive() throws PortalException {
		GoogleDriveSession googleDriveSession = getGoogleDriveSession();

		return googleDriveSession.getDrive();
	}

	@Override
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Revisions driveRevisions = drive.revisions();

			Drive.Revisions.List driveRevisionsList = driveRevisions.list(
				extRepositoryFileEntry.getExtRepositoryModelKey());

			RevisionList revisionList = driveRevisionsList.execute();

			List<Revision> revisions = revisionList.getItems();

			int[] versionParts = StringUtil.split(
				version, StringPool.PERIOD, 0);

			Revision revision = revisions.get(versionParts[0]);

			return new GoogleDriveFileVersion(
				revision, extRepositoryFileEntry.getExtRepositoryModelKey(),
				versionParts[0]);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey) {

		String[] extRepositoryFileVersionKeyParts = StringUtil.split(
			extRepositoryFileVersionKey, StringPool.COLON);

		String extRepositoryFileEntryKey = extRepositoryFileVersionKeyParts[0];
		String version = extRepositoryFileVersionKeyParts[2];

		return new ExtRepositoryFileVersionDescriptor(
			extRepositoryFileEntryKey, version);
	}

	@Override
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Revisions driveRevisions = drive.revisions();

			Drive.Revisions.List driveRevisionsList = driveRevisions.list(
				extRepositoryFileEntry.getExtRepositoryModelKey());

			RevisionList revisionList = driveRevisionsList.execute();

			List<Revision> revisions = revisionList.getItems();

			List<ExtRepositoryFileVersion> extRepositoryFileVersions =
				new ArrayList<>(revisions.size());

			for (int i = 0; i < revisions.size(); i++) {
				Revision revision = revisions.get(i);

				extRepositoryFileVersions.add(
					new GoogleDriveFileVersion(
						revision,
						extRepositoryFileEntry.getExtRepositoryModelKey(),
						i + 1));
			}

			Collections.reverse(extRepositoryFileVersions);

			return extRepositoryFileVersions;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException {

		try {
			Drive drive = getDrive();

			File file = getFile(drive, extRepositoryObjectKey);

			T extRepositoryObject = null;

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				extRepositoryObject = (T)new GoogleDriveFolder(
					file, getRootFolderKey());
			}
			else {
				extRepositoryObject = (T)new GoogleDriveFileEntry(file);
			}

			return extRepositoryObject;
		}
		catch (IOException ioe) {
			if (extRepositoryObjectType == ExtRepositoryObjectType.FOLDER) {
				throw new NoSuchFolderException(ioe);
			}

			throw new NoSuchFileEntryException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException {

		try {
			StringBundler sb = new StringBundler();

			sb.append("'");
			sb.append(extRepositoryFolderKey);
			sb.append("' in parents and title contains '");
			sb.append(title);
			sb.append(" and mimeType ");

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				sb.append("= ");
			}
			else {
				sb.append("!= ");
			}

			sb.append(_FOLDER_MIME_TYPE);

			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.List driveFilesList = driveFiles.list();

			driveFilesList.setQ(sb.toString());

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			if (files.isEmpty()) {
				if (extRepositoryObjectType == ExtRepositoryObjectType.FOLDER) {
					throw new NoSuchFolderException(title);
				}

				throw new NoSuchFileEntryException(title);
			}

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				return (T)new GoogleDriveFolder(
					files.get(0), getRootFolderKey());
			}

			return (T)new GoogleDriveFileEntry(files.get(0));
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.List driveFilesList = driveFiles.list();

			StringBundler sb = new StringBundler();

			if (extRepositoryFolderKey != null) {
				sb.append("'");
				sb.append(extRepositoryFolderKey);
				sb.append("' in parents and ");
			}

			if (!extRepositoryObjectType.equals(
					ExtRepositoryObjectType.OBJECT)) {

				sb.append("mimeType");

				if (extRepositoryObjectType.equals(
						ExtRepositoryObjectType.FILE)) {

					sb.append(" != '");
				}
				else {
					sb.append(" = '");
				}

				sb.append(_FOLDER_MIME_TYPE);
				sb.append("' and ");
			}

			sb.append("trashed = false");

			driveFilesList.setQ(sb.toString());

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			List<T> extRepositoryObjects = new ArrayList<>();

			GoogleDriveCache googleDriveCache = GoogleDriveCache.getInstance();

			for (File file : files) {
				if (_FOLDER_MIME_TYPE.equals(file.getMimeType())) {
					extRepositoryObjects.add(
						(T)new GoogleDriveFolder(file, getRootFolderKey()));
				}
				else {
					extRepositoryObjects.add((T)new GoogleDriveFileEntry(file));
				}

				googleDriveCache.put(file);
			}

			return extRepositoryObjects;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException {

		List<? extends ExtRepositoryObject> extRepositoryObjects =
			getExtRepositoryObjects(
				extRepositoryObjectType, extRepositoryFolderKey);

		return extRepositoryObjects.size();
	}

	@Override
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException {

		try {
			Drive drive = getDrive();

			File file = getFile(
				drive, extRepositoryObject.getExtRepositoryModelKey());

			List<ParentReference> parentReferences = file.getParents();

			if (!parentReferences.isEmpty()) {
				ParentReference parentReference = parentReferences.get(0);

				File parentFile = getFile(drive, parentReference.getId());

				return new GoogleDriveFolder(parentFile, getRootFolderKey());
			}
		}
		catch (IOException ioe) {
			//_log.error(ioe, ioe);
		}

		return null;
	}

	@Override
	public String getLiferayLogin(String extRepositoryLogin) {
		return null;
	}

	@Override
	public String getRootFolderKey() {
		try {
			GoogleDriveSession googleDriveSession = getGoogleDriveSession();

			return googleDriveSession.getRootFolderKey();
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return StringPool.BLANK;
	}

	@Override
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException {

		List<String> subfolderKeys = new ArrayList<>();

		getSubfolderKeys(extRepositoryFolderKey, recurse, subfolderKeys);

		return subfolderKeys;
	}

	@Override
	public String[] getSupportedConfigurations() {
		return new String[0];
	}

	@Override
	public String[][] getSupportedParameters() {
		return new String[0][];
	}

	@Override
	public void initRepository(
			UnicodeProperties typeSettingsProperties,
			CredentialsProvider credentialsProvider)
		throws PortalException {

		getDrive();
	}

	@Override
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException {

		try {
			Drive drive = getDrive();

			File file = getFile(drive, extRepositoryObjectKey);

			Drive.Parents driveParents = drive.parents();

			List<ParentReference> parentReferences = file.getParents();

			for (ParentReference parentReference : parentReferences) {
				Drive.Parents.Delete driveParentsDelete = driveParents.delete(
					file.getId(), parentReference.getId());

				driveParentsDelete.execute();
			}

			ParentReference parentReference = new ParentReference();

			parentReference.setId(newExtRepositoryFolderKey);

			Drive.Parents.Insert driveParentsInsert = driveParents.insert(
				file.getId(), parentReference);

			driveParentsInsert.execute();

			if (extRepositoryObjectType.equals(ExtRepositoryObjectType.FILE)) {
				return (T)new GoogleDriveFileEntry(file);
			}

			return (T)new GoogleDriveFolder(file, getRootFolderKey());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.List driveFilesList = driveFiles.list();

			String searchQuery = getSearchQuery(
				searchContext.getKeywords(), searchContext.getFolderIds(),
				extRepositoryQueryMapper);

			driveFilesList.setQ(searchQuery);

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			List<ExtRepositorySearchResult<?>> extRepositorySearchResults =
				new ArrayList<>(files.size());

			for (File file : files) {
				if (_FOLDER_MIME_TYPE.equals(file.getMimeType())) {
					GoogleDriveFolder googleDriveFolder = new GoogleDriveFolder(
						file, getRootFolderKey());

					ExtRepositorySearchResult<GoogleDriveFolder>
						extRepositorySearchResult =
							new ExtRepositorySearchResult<>(
								googleDriveFolder, 1.0f, StringPool.BLANK);

					extRepositorySearchResults.add(extRepositorySearchResult);
				}
				else {
					GoogleDriveFileEntry googleDriveFileEntry =
						new GoogleDriveFileEntry(file);

					ExtRepositorySearchResult<GoogleDriveFileEntry>
						extRepositorySearchResult =
							new ExtRepositorySearchResult<>(
								googleDriveFileEntry, 1.0f, StringPool.BLANK);

					extRepositorySearchResults.add(extRepositorySearchResult);
				}
			}

			return extRepositorySearchResults;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			File file = getFile(drive, extRepositoryFileEntryKey);

			InputStreamContent inputStreamContent = new InputStreamContent(
				mimeType, inputStream);

			Drive.Files.Update driveFilesUpdate = driveFiles.update(
				extRepositoryFileEntryKey, file, inputStreamContent);

			file = driveFilesUpdate.execute();

			return new GoogleDriveFileEntry(file);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected File addFile(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, InputStream inputStream)
		throws PortalException {

		try {
			File file = new File();

			file.setDescription(description);
			file.setMimeType(mimeType);

			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			File extRepositoryParentFolderFile = getFile(
				drive, extRepositoryParentFolderKey);

			ParentReference parentReference = new ParentReference();

			parentReference.setId(extRepositoryParentFolderFile.getId());

			file.setParents(Arrays.asList(parentReference));

			file.setTitle(title);

			if (inputStream != null) {
				InputStreamContent inputStreamContent = new InputStreamContent(
					mimeType, inputStream);

				Drive.Files.Insert driveFilesInsert = driveFiles.insert(
					file, inputStreamContent);

				return driveFilesInsert.execute();
			}
			else {
				Drive.Files.Insert driveFilesInsert = driveFiles.insert(file);

				return driveFilesInsert.execute();
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected GoogleDriveSession buildGoogleDriveSession()
		throws IOException, PortalException {

		long userId = PrincipalThreadLocal.getUserId();

		User user = UserLocalServiceUtil.getUser(userId);

		if (user.isDefaultUser()) {
			throw new PrincipalException("User is not authenticated");
		}

		GoogleCredential.Builder builder = new GoogleCredential.Builder();

		String googleClientId = PrefsPropsUtil.getString(
			user.getCompanyId(), "google-client-id");
		String googleClientSecret = PrefsPropsUtil.getString(
			user.getCompanyId(), "google-client-secret");

		builder.setClientSecrets(googleClientId, googleClientSecret);

		JacksonFactory jsonFactory = new JacksonFactory();

		builder.setJsonFactory(jsonFactory);

		HttpTransport httpTransport = new NetHttpTransport();

		builder.setTransport(httpTransport);

		GoogleCredential googleCredential = builder.build();

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String googleAccessToken = GetterUtil.getString(
			expandoBridge.getAttribute("googleAccessToken", false));

		googleCredential.setAccessToken(googleAccessToken);

		String googleRefreshToken = GetterUtil.getString(
			expandoBridge.getAttribute("googleRefreshToken", false));

		googleCredential.setRefreshToken(googleRefreshToken);

		Drive.Builder driveBuilder = new Drive.Builder(
			httpTransport, jsonFactory, googleCredential);

		Drive drive = driveBuilder.build();

		Drive.About driveAbout = drive.about();

		Drive.About.Get driveAboutGet = driveAbout.get();

		About about = driveAboutGet.execute();

		return new GoogleDriveSession(drive, about.getRootFolderId());
	}

	protected InputStream getContentStream(String downloadURL)
		throws PortalException {

		if (Validator.isNull(downloadURL)) {
			return null;
		}

		Drive drive = getDrive();

		HttpRequestFactory httpRequestFactory = drive.getRequestFactory();

		GenericUrl genericUrl = new GenericUrl(downloadURL);

		try {
			HttpRequest httpRequest = httpRequestFactory.buildGetRequest(
				genericUrl);

			HttpResponse httpResponse = httpRequest.execute();

			return httpResponse.getContent();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected File getFile(Drive drive, String extRepositoryObjectKey)
		throws IOException {

		GoogleDriveCache googleDriveCache = GoogleDriveCache.getInstance();

		File file = googleDriveCache.get(extRepositoryObjectKey);

		if (file == null) {
			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryObjectKey);

			file = driveFilesGet.execute();

			googleDriveCache.put(file);
		}

		return file;
	}

	protected GoogleDriveSession getGoogleDriveSession()
		throws PortalException {

		GoogleDriveSession googleDriveSession = null;

		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		if (httpSession != null) {
			TransientValue<GoogleDriveSession> transientValue =
				(TransientValue<GoogleDriveSession>)httpSession.getAttribute(
					GoogleDriveSession.class.getName());

			if (transientValue != null) {
				googleDriveSession = transientValue.getValue();
			}
		}
		else {
			googleDriveSession = _googleDriveSessionThreadLocal.get();
		}

		if (googleDriveSession != null) {
			return googleDriveSession;
		}

		try {
			googleDriveSession = buildGoogleDriveSession();
		}
		catch (Exception e) {
			throw new PrincipalException(e);
		}

		if (httpSession != null) {
			httpSession.setAttribute(
				GoogleDriveSession.class.getName(),
				new TransientValue<GoogleDriveSession>(googleDriveSession));
		}
		else {
			_googleDriveSessionThreadLocal.set(googleDriveSession);
		}

		return googleDriveSession;
	}

	protected String getSearchQuery(
			String keywords, long[] folderIds,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws SearchException {

		StringBundler sb = new StringBundler();

		sb.append("fullText contains '");
		sb.append(keywords);
		sb.append("' and ");

		for (int i = 0; i < folderIds.length; i++) {
			if (i != 0) {
				sb.append(" and ");
			}

			long folderId = folderIds[i];

			String extRepositoryFolderKey =
				extRepositoryQueryMapper.formatParameterValue(
					"folderId", String.valueOf(folderId));

			sb.append(StringPool.APOSTROPHE);
			sb.append(extRepositoryFolderKey);
			sb.append(StringPool.APOSTROPHE);

			sb.append(" in parents");
		}

		return sb.toString();
	}

	protected List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse,
			List<String> subfolderKeys)
		throws PortalException {

		List<ExtRepositoryFolder> extRepositoryFolders =
			getExtRepositoryObjects(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		for (ExtRepositoryFolder extRepositoryFolder : extRepositoryFolders) {
			subfolderKeys.add(extRepositoryFolder.getExtRepositoryModelKey());

			if (recurse) {
				getSubfolderKeys(
					extRepositoryFolder.getExtRepositoryModelKey(), recurse,
					subfolderKeys);
			}
		}

		return subfolderKeys;
	}

	private static final String _FOLDER_MIME_TYPE =
		"application/vnd.google-apps.folder";

	private static Log _log = LogFactoryUtil.getLog(
		GoogleDriveRepository.class);

	private ThreadLocal<GoogleDriveSession> _googleDriveSessionThreadLocal =
		new AutoResetThreadLocal<>(Drive.class.getName());

}