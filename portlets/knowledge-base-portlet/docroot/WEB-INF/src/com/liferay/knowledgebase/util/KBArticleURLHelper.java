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
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Adolfo Pérez
 */
public class KBArticleURLHelper {

	public KBArticleURLHelper(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String templatePath) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_templatePath = templatePath;
	}

	public PortletURL createViewURL(KBArticle kbArticle)
		throws PortalException, SystemException {

		PortletURL portletURL = _renderResponse.createRenderURL();

		String portletId = PortalUtil.getPortletId(_renderRequest);

		if (portletId.startsWith(PortletKeys.KNOWLEDGE_BASE_ADMIN) ||
			portletId.startsWith(PortletKeys.KNOWLEDGE_BASE_SECTION)) {

			portletURL.setParameter(
				"mvcPath", _templatePath + "view_article.jsp");
		}

		if (portletId.startsWith(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
			portletURL.setParameter(
				"redirect", PortalUtil.getCurrentURL(_renderRequest));
		}

		if (Validator.isNull(kbArticle.getUrlTitle()) ||
			portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {

			portletURL.setParameter(
				"resourceClassNameId",
				String.valueOf(kbArticle.getClassNameId()));
			portletURL.setParameter(
				"resourcePrimKey",
				String.valueOf(kbArticle.getResourcePrimKey()));
		}
		else {
			portletURL.setParameter("urlTitle", kbArticle.getUrlTitle());

			if (kbArticle.getKbFolderId() !=
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(
					kbArticle.getKbFolderId());

				portletURL.setParameter(
					"kbFolderUrlTitle", kbFolder.getUrlTitle());
			}
		}

		return portletURL;
	}

	public PortletURL createViewWithCommentsURL(KBArticle kbArticle)
		throws PortalException, SystemException {

		PortletURL portletURL = createViewURL(kbArticle);

		portletURL.setParameter("expanded", Boolean.TRUE.toString());

		return portletURL;
	}

	public PortletURL createViewWithRedirectURL(
			KBArticle kbArticle, String redirect)
		throws PortalException, SystemException {

		PortletURL portletURL = createViewURL(kbArticle);

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		return portletURL;
	}

	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final String _templatePath;

}