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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleImpl extends KBArticleBaseImpl {

	public KBArticleImpl() {
	}

	public String getAttachmentsDirName() {
		return KBArticleConstants.DIR_NAME_PREFIX + getClassPK();
	}

	public String[] getAttachmentsFileNames()
		throws PortalException, SystemException {

		try {
			return DLStoreUtil.getFileNames(
				getCompanyId(), CompanyConstants.SYSTEM,
				getAttachmentsDirName());
		}
		catch (NoSuchDirectoryException nsde) {
			_log.error("No directory found for " + nsde.getMessage());
		}

		return new String[0];
	}

	public long getClassPK() {
		if (isApproved()) {
			return getResourcePrimKey();
		}

		return getKbArticleId();
	}

	public boolean isFirstVersion() {
		if (getVersion() == KBArticleConstants.DEFAULT_VERSION) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isResourceMain() {
		return isMain();
	}

	public boolean isRoot() {
		if (getParentResourcePrimKey() ==
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleImpl.class);

}