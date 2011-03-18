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

import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.CompanyConstants;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleImpl extends ArticleModelImpl implements Article {

	public ArticleImpl() {
	}

	public String getAttachmentsDirName() {
		return ArticleConstants.DIR_NAME_PREFIX + getClassPK();
	}

	public String[] getAttachmentsFileNames()
		throws PortalException, SystemException {

		try {
			return DLLocalServiceUtil.getFileNames(
				getCompanyId(), CompanyConstants.SYSTEM,
				getAttachmentsDirName());
		}
		catch (NoSuchDirectoryException nsde) {
			_log.error("No directory found for " + nsde.getMessage());
		}

		return new String[0];
	}

	public long getClassPK() {
		if (isFirstVersion() || isApproved()) {
			return getResourcePrimKey();
		}

		return getPrimaryKey();
	}

	public boolean isFirstVersion() {
		if (getVersion() == ArticleConstants.DEFAULT_VERSION) {
			return true;
		}

		return false;
	}

	public boolean isRoot() {
		if (getParentResourcePrimKey() ==
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(ArticleImpl.class);

}