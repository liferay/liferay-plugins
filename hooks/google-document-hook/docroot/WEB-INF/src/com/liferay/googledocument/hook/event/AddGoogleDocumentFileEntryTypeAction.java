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

package com.liferay.googledocument.hook.event;

import com.liferay.googledocument.hook.util.GoogleDocumentConstants;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeServiceUtil;
import com.liferay.util.ContentUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class AddGoogleDocumentFileEntryTypeAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId)
		throws PortalException, SystemException {

		Company company = CompanyLocalServiceUtil.getCompanyById(companyId);

		if (!_hasGoogleDriveDocumentFileEntryType(company.getGroupId())) {
			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			_createGoogleDriveDocumentFileEntryType(
				defaultUserId, company.getGroupId());
		}
	}

	private void _createGoogleDriveDocumentFileEntryType(
			long userId, long groupId)
		throws PortalException, SystemException {

		Locale defaultLocale = LocaleUtil.getDefault();

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(
			defaultLocale,
			GoogleDocumentConstants.GOOGLE_DOCUMENT_FILE_ENTRY_TYPE_NAME);

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		descriptionMap.put(
			defaultLocale,
			GoogleDocumentConstants.GOOGLE_DOCUMENT_FILE_ENTRY_TYPE_NAME);

		long[] ddmStructureIds = new long[] {};

		ServiceContext serviceContext = new ServiceContext();

		String xsd = ContentUtil.get(
			getClass().getClassLoader(),
			"com/liferay/googledocument/hook/event/" +
				"google-document-file-entry-type-xsd.xml");

		serviceContext.setAttribute("xsd", xsd);

		DLFileEntryTypeLocalServiceUtil.addFileEntryType(
			userId, groupId,
			GoogleDocumentConstants.GOOGLE_DOCUMENT_FILE_ENTRY_TYPE_KEY,
			nameMap, descriptionMap, ddmStructureIds, serviceContext);
	}

	private boolean _hasGoogleDriveDocumentFileEntryType(long groupId)
		throws SystemException {

		long[] groupIds = new long[] {groupId};

		List<DLFileEntryType> fileEntryTypes =
			DLFileEntryTypeServiceUtil.getFileEntryTypes(groupIds);

		for (DLFileEntryType fileEntryType : fileEntryTypes) {
			String fileEntryTypeKey = fileEntryType.getFileEntryTypeKey();

			if (GoogleDocumentConstants.GOOGLE_DOCUMENT_FILE_ENTRY_TYPE_KEY.
					equals(fileEntryTypeKey)) {

				return true;
			}
		}

		return false;
	}

}