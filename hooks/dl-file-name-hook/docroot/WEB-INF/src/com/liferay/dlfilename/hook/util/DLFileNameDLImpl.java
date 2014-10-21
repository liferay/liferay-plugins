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

package com.liferay.dlfilename.hook.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.util.DL;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Preston Crary
 */
public class DLFileNameDLImpl implements DL {

	public DLFileNameDLImpl(DL dl) {
		_dl = dl;
	}

	@Override
	public void addPortletBreadcrumbEntries(
			DLFileShortcut dlFileShortcut, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(
			dlFileShortcut, request, renderResponse);
	}

	@Override
	public void addPortletBreadcrumbEntries(
			FileEntry fileEntry, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(fileEntry, request, renderResponse);
	}

	@Override
	public void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(
			folder, request, liferayPortletResponse);
	}

	@Override
	public void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(folder, request, portletURL);
	}

	@Override
	public void addPortletBreadcrumbEntries(
			Folder folder, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(folder, request, renderResponse);
	}

	@Override
	public void addPortletBreadcrumbEntries(
			long folderId, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		_dl.addPortletBreadcrumbEntries(folderId, request, renderResponse);
	}

	@Override
	public int compareVersions(String version1, String version2) {
		return _dl.compareVersions(version1, version2);
	}

	@Override
	public String getAbsolutePath(PortletRequest portletRequest, long folderId)
		throws PortalException, SystemException {

		return _dl.getAbsolutePath(portletRequest, folderId);
	}

	@Override
	public Set<String> getAllMediaGalleryMimeTypes() {
		return _dl.getAllMediaGalleryMimeTypes();
	}

	@Override
	public String getDDMStructureKey(DLFileEntryType dlFileEntryType) {
		return _dl.getDDMStructureKey(dlFileEntryType);
	}

	@Override
	public String getDDMStructureKey(String fileEntryTypeUuid) {
		return _dl.getDDMStructureKey(fileEntryTypeUuid);
	}

	@Override
	public String getDeprecatedDDMStructureKey(
		DLFileEntryType dlFileEntryType) {

		return _dl.getDeprecatedDDMStructureKey(dlFileEntryType);
	}

	@Override
	public String getDeprecatedDDMStructureKey(long fileEntryTypeId) {
		return _dl.getDeprecatedDDMStructureKey(fileEntryTypeId);
	}

	@Override
	public String getDividedPath(long id) {
		return _dl.getDividedPath(id);
	}

	public DL getDL() {
		return _dl;
	}

	@Override
	public String getDLFileEntryControlPanelLink(
			PortletRequest portletRequest, long fileEntryId)
		throws PortalException, SystemException {

		return _dl.getDLFileEntryControlPanelLink(portletRequest, fileEntryId);
	}

	@Override
	public String getDLFolderControlPanelLink(
			PortletRequest portletRequest, long folderId)
		throws PortalException, SystemException {

		return _dl.getDLFolderControlPanelLink(portletRequest, folderId);
	}

	@Override
	public Map<Locale, String> getEmailFileEntryAddedBodyMap(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryAddedBodyMap(preferences);
	}

	@Override
	public boolean getEmailFileEntryAddedEnabled(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryAddedEnabled(preferences);
	}

	@Override
	public Map<Locale, String> getEmailFileEntryAddedSubjectMap(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryAddedSubjectMap(preferences);
	}

	@Override
	public Map<Locale, String> getEmailFileEntryUpdatedBodyMap(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryUpdatedBodyMap(preferences);
	}

	@Override
	public boolean getEmailFileEntryUpdatedEnabled(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryUpdatedEnabled(preferences);
	}

	@Override
	public Map<Locale, String> getEmailFileEntryUpdatedSubjectMap(
		PortletPreferences preferences) {

		return _dl.getEmailFileEntryUpdatedSubjectMap(preferences);
	}

	@Override
	public String getEmailFromAddress(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return _dl.getEmailFromAddress(preferences, companyId);
	}

	@Override
	public String getEmailFromName(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return _dl.getEmailFromName(preferences, companyId);
	}

	@Override
	public List<Object> getEntries(Hits hits) {
		return _dl.getEntries(hits);
	}

	@Override
	public String getFileEntryImage(
		FileEntry fileEntry, ThemeDisplay themeDisplay) {

		return _dl.getFileEntryImage(fileEntry, themeDisplay);
	}

	@Override
	public Set<Long> getFileEntryTypeSubscriptionClassPKs(long userId)
		throws SystemException {

		return _dl.getFileEntryTypeSubscriptionClassPKs(userId);
	}

	@Override
	public String getFileIcon(String extension) {
		return _dl.getFileIcon(extension);
	}

	@Override
	public String getGenericName(String extension) {
		return _dl.getGenericName(extension);
	}

	@Override
	public String getImagePreviewURL(
			FileEntry fileEntry, FileVersion fileVersion,
			ThemeDisplay themeDisplay)
		throws Exception {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getImagePreviewURL(fileEntry, fileVersion, themeDisplay);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String getImagePreviewURL(
			FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getImagePreviewURL(fileEntry, themeDisplay);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String[] getMediaGalleryMimeTypes(
		PortletPreferences portletPreferences, PortletRequest portletRequest) {

		return _dl.getMediaGalleryMimeTypes(portletPreferences, portletRequest);
	}

	@Override
	public String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString) {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getPreviewURL(
				fileEntry, fileVersion, themeDisplay, queryString);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendToken) {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getPreviewURL(
				fileEntry, fileVersion, themeDisplay, queryString, appendToken);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String getPreviewURL(
		FileEntry fileEntry, FileVersion fileVersion, ThemeDisplay themeDisplay,
		String queryString, boolean appendVersion, boolean absoluteURL) {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getPreviewURL(
				fileEntry, fileVersion, themeDisplay, queryString,
				appendVersion, absoluteURL);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public OrderByComparator getRepositoryModelOrderByComparator(
		String orderByCol, String orderByType) {

		return _dl.getRepositoryModelOrderByComparator(orderByCol, orderByType);
	}

	@Override
	public String getTempFileId(long id, String version) {
		return _dl.getTempFileId(id, version);
	}

	@Override
	public String getTempFileId(long id, String version, String languageId) {
		return _dl.getTempFileId(id, version, languageId);
	}

	@Override
	public String getThumbnailSrc(
			FileEntry fileEntry, DLFileShortcut dlFileShortcut,
			ThemeDisplay themeDisplay)
		throws Exception {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getThumbnailSrc(fileEntry, dlFileShortcut, themeDisplay);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String getThumbnailSrc(
			FileEntry fileEntry, FileVersion fileVersion,
			DLFileShortcut dlFileShortcut, ThemeDisplay themeDisplay)
		throws Exception {

		boolean dlFileNameEnabled = DLFileNameThreadLocal.isEnabled();

		DLFileNameThreadLocal.setEnabled(true);

		try {
			return _dl.getThumbnailSrc(
				fileEntry, fileVersion, dlFileShortcut, themeDisplay);
		}
		finally {
			DLFileNameThreadLocal.setEnabled(dlFileNameEnabled);
		}
	}

	@Override
	public String getThumbnailStyle() throws Exception {
		return _dl.getThumbnailStyle();
	}

	@Override
	public String getThumbnailStyle(boolean max, int margin) throws Exception {
		return _dl.getThumbnailStyle(max, margin);
	}

	@Override
	public String getTitleWithExtension(FileEntry fileEntry) {
		return _dl.getTitleWithExtension(fileEntry);
	}

	@Override
	public String getTitleWithExtension(String title, String extension) {
		return _dl.getTitleWithExtension(title, extension);
	}

	@Override
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry)
		throws PortalException, SystemException {

		return _dl.getWebDavURL(themeDisplay, folder, fileEntry);
	}

	@Override
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired)
		throws PortalException, SystemException {

		return _dl.getWebDavURL(
			themeDisplay, folder, fileEntry, manualCheckInRequired);
	}

	@Override
	public String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired, boolean officeExtensionRequired)
		throws PortalException, SystemException {

		return _dl.getWebDavURL(
			themeDisplay, folder, fileEntry, manualCheckInRequired,
			officeExtensionRequired);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, long folderId, long fileEntryTypeId)
		throws Exception {

		return _dl.hasWorkflowDefinitionLink(
			companyId, groupId, folderId, fileEntryTypeId);
	}

	@Override
	public boolean isAutoGeneratedDLFileEntryTypeDDMStructureKey(
		String ddmStructureKey) {

		return _dl.isAutoGeneratedDLFileEntryTypeDDMStructureKey(
			ddmStructureKey);
	}

	@Override
	public boolean isOfficeExtension(String extension) {
		return _dl.isOfficeExtension(extension);
	}

	@Override
	public boolean isSubscribedToFileEntryType(
			long companyId, long groupId, long userId, long fileEntryTypeId)
		throws SystemException {

		return _dl.isSubscribedToFileEntryType(
			companyId, groupId, userId, fileEntryTypeId);
	}

	@Override
	public boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId)
		throws PortalException, SystemException {

		return _dl.isSubscribedToFolder(companyId, groupId, userId, folderId);
	}

	@Override
	public boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId,
			boolean recursive)
		throws PortalException, SystemException {

		return _dl.isSubscribedToFolder(
			companyId, groupId, userId, folderId, recursive);
	}

	@Override
	public boolean isValidVersion(String version) {
		return _dl.isValidVersion(version);
	}

	private DL _dl;

}