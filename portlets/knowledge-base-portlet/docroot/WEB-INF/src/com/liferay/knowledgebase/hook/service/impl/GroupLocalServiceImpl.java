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

package com.liferay.knowledgebase.hook.service.impl;

import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceWrapper;

/**
 * @author Peter Shin
 */
public class GroupLocalServiceImpl extends GroupLocalServiceWrapper {

	public GroupLocalServiceImpl(GroupLocalService groupLocalService) {
		super(groupLocalService);
	}

	public void deleteGroup(long groupId)
		throws PortalException, SystemException {

		ArticleLocalServiceUtil.deleteGroupArticles(groupId);

		TemplateLocalServiceUtil.deleteGroupTemplates(groupId);

		super.deleteGroup(groupId);
	}

}