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

package com.liferay.contacts.service.impl;

import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.base.EntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryServiceImpl extends EntryServiceBaseImpl {

	public Entry addEntry(String fullName, String emailAddress, String comments)
		throws PortalException, SystemException {

		return entryLocalService.addEntry(
			getUserId(), fullName, emailAddress, comments);
	}

	public Entry deleteEntry(long entryId)
		throws PortalException, SystemException {

		return entryLocalService.deleteEntry(entryId);
	}

	public Entry updateEntry(
			long entryId, String fullName, String emailAddress, String comments)
		throws PortalException, SystemException {

		return entryLocalService.updateEntry(
			entryId, fullName, emailAddress, comments);
	}

}