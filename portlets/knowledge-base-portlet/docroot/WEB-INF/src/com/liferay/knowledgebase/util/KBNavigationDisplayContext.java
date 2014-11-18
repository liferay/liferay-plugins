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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Adolfo Pérez
 */
public class KBNavigationDisplayContext {

	public KBNavigationDisplayContext(
		PortletRequest portletRequest, PortletPreferences portletPreferences,
		KBArticle kbArticle) {

		_kbArticle = kbArticle;
		_portletPreferences = portletPreferences;
		_portletRequest = portletRequest;
	}

	public long getRootResourcePrimKey()
		throws PortalException, SystemException {

		long rootResourcePrimKey = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (_kbArticle != null) {
			rootResourcePrimKey = KnowledgeBaseUtil.getKBFolderId(
				_kbArticle.getParentResourceClassNameId(),
				_kbArticle.getParentResourcePrimKey());
		}

		if (rootResourcePrimKey == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			rootResourcePrimKey = KnowledgeBaseUtil.getRootResourcePrimKey(
				_portletRequest, PortalUtil.getScopeGroupId(_portletRequest),
				getResourceClassNameId(), getResourcePrimKey());
		}

		return rootResourcePrimKey;
	}

	protected long getResourceClassNameId() {
		return GetterUtil.getLong(
			_portletPreferences.getValue("resourceClassNameId", null),
			PortalUtil.getClassNameId(KBFolderConstants.getClassName()));
	}

	protected long getResourcePrimKey() {
		return GetterUtil.getLong(
			_portletPreferences.getValue("resourcePrimKey", null));
	}

	private final KBArticle _kbArticle;
	private final PortletPreferences _portletPreferences;
	private final PortletRequest _portletRequest;

}