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

package com.liferay.knowledgebase.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.service.base.KBFolderServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KBFolderServiceImpl extends KBFolderServiceBaseImpl {

	@Override
	public List<KBFolder> getFolders(
			long groupId, long parentKBFolderId, int start, int end)
		throws PortalException {

		return kbFolderPersistence.filterFindByG_P(
			groupId, parentKBFolderId, start, end);
	}

	@Override
	public int getFoldersCount(long groupId, long parentKBFolderId)
		throws PortalException {

		return kbFolderPersistence.filterCountByG_P(groupId, parentKBFolderId);
	}

}