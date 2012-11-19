/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.hook.service.impl;

import com.liferay.compat.portlet.documentlibrary.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceWrapper;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatDLFileEntryLocalServiceImpl
	extends DLFileEntryLocalServiceWrapper {

	public CompatDLFileEntryLocalServiceImpl(
		DLFileEntryLocalService dlFileEntryLocalService) {

		super(dlFileEntryLocalService);
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, boolean majorVersion,
			String changeLog, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = DLFileEntryUtil.findByPrimaryKey(fileEntryId);

		ServiceContext threadLocalServiceContext =
			ServiceContextThreadLocal.getServiceContext();

		boolean webDAVCheckIn = false;

		if (threadLocalServiceContext != null) {
			webDAVCheckIn = GetterUtil.getBoolean(
				serviceContext.getAttribute(DLUtil.WEBDAV_CHECK_IN_MODE));
		}
		else {
			webDAVCheckIn = GetterUtil.getBoolean(
				serviceContext.getAttribute(DLUtil.WEBDAV_CHECK_IN_MODE));
		}

		ExpandoBridge expandoBridge = dlFileEntry.getExpandoBridge();

		boolean manualCheckInRequired = GetterUtil.getBoolean(
			expandoBridge.getAttribute(DLUtil.MANUAL_CHECK_IN_REQUIRED, false));

		if (!webDAVCheckIn && manualCheckInRequired) {
			expandoBridge.setAttribute(
				DLUtil.MANUAL_CHECK_IN_REQUIRED, false, false);
		}

		super.checkInFileEntry(
			userId, fileEntryId, majorVersion, changeLog, serviceContext);
	}

	@Override
	public DLFileEntry checkOutFileEntry(
			long userId, long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = DLFileEntryUtil.findByPrimaryKey(fileEntryId);

		boolean manualCheckinMode = GetterUtil.getBoolean(
			serviceContext.getAttribute(DLUtil.MANUAL_CHECK_IN_REQUIRED));

		if (manualCheckinMode) {
			ExpandoBridge expandoBridge = dlFileEntry.getExpandoBridge();

			expandoBridge.setAttribute(
				DLUtil.MANUAL_CHECK_IN_REQUIRED, true, false);
		}

		return super.checkOutFileEntry(
			userId, fileEntryId, owner, expirationTime, serviceContext);
	}

}