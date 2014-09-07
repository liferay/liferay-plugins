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

package com.liferay.google.docs.hook.service.impl;

import com.liferay.google.docs.hook.util.GoogleDocsConstants;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iván Zaera
 */
public class GoogleDocsDLFileEntryTypeServiceImpl
	extends DLFileEntryTypeServiceWrapper {

	public GoogleDocsDLFileEntryTypeServiceImpl(
		DLFileEntryTypeService dlFileEntryTypeService) {

		super(dlFileEntryTypeService);
	}

	@Override
	public List<DLFileEntryType> search(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<DLFileEntryType> dlFileEntryTypes = super.search(
			companyId, groupIds, keywords, includeBasicFileEntryType, start,
			end, orderByComparator);

		if (includeBasicFileEntryType) {
			return dlFileEntryTypes;
		}

		return filterDLFileEntryTypes(dlFileEntryTypes);
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, String keywords,
			boolean includeBasicFileEntryType)
		throws SystemException {

		int searchCount = super.searchCount(
			companyId, groupIds, keywords, includeBasicFileEntryType);

		if (!includeBasicFileEntryType) {
			searchCount--;
		}

		return searchCount;
	}

	protected List<DLFileEntryType> filterDLFileEntryTypes(
		List<DLFileEntryType> dlFileEntryTypes) {

		List<DLFileEntryType> filteredDLFileEntryTypes =
			new ArrayList<DLFileEntryType>();

		for (DLFileEntryType dlFileEntryType : dlFileEntryTypes) {
			String fileEntryTypeKey = dlFileEntryType.getFileEntryTypeKey();

			if (fileEntryTypeKey.equals(
					GoogleDocsConstants.
						GOOGLE_DOCS_FILE_ENTRY_TYPE_KEY)) {

				continue;
			}

			filteredDLFileEntryTypes.add(dlFileEntryType);
		}

		return filteredDLFileEntryTypes;
	}

}