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

package com.liferay.testopensocialoauthconsumer.messaging;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

/**
 * @author Ivica Cardic
 */
public class TestOpenSocialOAuthConsumerHotDeployMessageListener
	extends HotDeployMessageListener {

	public TestOpenSocialOAuthConsumerHotDeployMessageListener(
		String... servletContextNames) {

		super(servletContextNames);
	}

	protected static Folder getGadgetEditorRootFolder(
			long userId, long repositoryId)
		throws Exception {

		// See ShindigUtil.java in open-social-portlet

		Folder folder = null;

		try {
			folder = DLAppLocalServiceUtil.getFolder(
				repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				_GADGET_FOLDER_NAME);
		}
		catch (Exception e) {
		}

		if (folder != null) {
			return folder;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(repositoryId);

		return DLAppLocalServiceUtil.addFolder(
			userId, repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			_GADGET_FOLDER_NAME, StringPool.BLANK, serviceContext);
	}

	protected void deleteGadget() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		Company company = CompanyLocalServiceUtil.getCompany(companyId);
		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);
		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		Folder gadgetEditorRootFolder = getGadgetEditorRootFolder(
			user.getUserId(), group.getGroupId());

		FileEntry fileEntry = null;

		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(
				group.getGroupId(), gadgetEditorRootFolder.getFolderId(),
				_GADGET_FILE_NAME);
		}
		catch (NoSuchFileEntryException nsfee) {
			return;
		}

		Gadget gadget = GadgetLocalServiceUtil.fetchGadget(
			company.getCompanyId(), getFileEntryURL(fileEntry));

		if (gadget != null) {
			GadgetLocalServiceUtil.deleteGadget(gadget);
		}

		DLAppLocalServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
	}

	protected String getFileEntryURL(FileEntry fileEntry) {
		StringBundler sb = new StringBundler(10);

		sb.append("http://localhost:8080");
		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(fileEntry.getRepositoryId());
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getFolderId());
		sb.append(StringPool.SLASH);
		sb.append(HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())));
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getUuid());

		return sb.toString();
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		setupGadget();
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		deleteGadget();
	}

	protected void setupGadget() throws Exception {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		long companyId = PortalUtil.getDefaultCompanyId();

		User user = UserLocalServiceUtil.getDefaultUser(companyId);
		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		Folder gadgetEditorRootFolder = getGadgetEditorRootFolder(
			user.getUserId(), group.getGroupId());

		FileEntry fileEntry = null;

		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(
				group.getGroupId(), gadgetEditorRootFolder.getFolderId(),
				_GADGET_FILE_NAME);
		}
		catch (NoSuchFileEntryException nsfee) {
			InputStream inputStream = classLoader.getResourceAsStream(
				"/resources/gadgets/".concat(_GADGET_FILE_NAME));

			String xml = new String(FileUtil.getBytes(inputStream));

			xml = StringUtil.replace(
				xml,
				new String[] {
					"[$ACCESS_URL$]", "[$AUTHORIZATION_URL$]", "[$FOLDER_ID$]",
					"[$PORTAL_URL$]", "[$REPOSITORY_ID$]", "[$REQUEST_URL$]"
				},
				new String[] {
					"/c/portal/oauth/access_token", "/c/portal/oauth/authorize",
					String.valueOf(gadgetEditorRootFolder.getFolderId()),
					"http://localhost:8080", String.valueOf(group.getGroupId()),
					"/c/portal/oauth/request_token"
				});

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setScopeGroupId(group.getGroupId());

			serviceContext.setAttribute("sourceFileName", _GADGET_FILE_NAME);

			fileEntry = DLAppLocalServiceUtil.addFileEntry(
				user.getUserId(), gadgetEditorRootFolder.getRepositoryId(),
				gadgetEditorRootFolder.getFolderId(), _GADGET_FILE_NAME,
				ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED_UTF8,
				_GADGET_FILE_NAME, StringPool.BLANK, StringPool.BLANK,
				xml.getBytes(StringPool.UTF8), serviceContext);
		}

		Gadget gadget = GadgetLocalServiceUtil.fetchGadget(
			companyId, getFileEntryURL(fileEntry));

		if (gadget == null) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setScopeGroupId(group.getGroupId());

			GadgetLocalServiceUtil.addGadget(
				companyId, getFileEntryURL(fileEntry), _PORTLET_CATEGORY_NAMES,
				serviceContext);
		}
	}

	private static final String _GADGET_FILE_NAME = "test_oauth.xml";

	private static final String _GADGET_FOLDER_NAME = "OpenSocial Gadgets";

	private static final String _PORTLET_CATEGORY_NAMES =
		"root//category.gadgets";

}