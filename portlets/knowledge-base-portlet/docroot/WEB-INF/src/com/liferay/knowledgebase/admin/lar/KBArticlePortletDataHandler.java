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

package com.liferay.knowledgebase.admin.lar;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Sorin Pop
 */
public class KBArticlePortletDataHandler extends AdminPortletDataHandler {

	public KBArticlePortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
		setDataPortletPreferences("resourcePrimKey");
		setExportControls(new PortletDataHandlerControl[0]);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletPreferences == null) {
			return portletPreferences;
		}

		portletPreferences.setValue("resourcePrimKey", StringPool.BLANK);

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long resourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", StringPool.BLANK));

		if (resourcePrimKey !=
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			List<KBArticle> kbArticles =
				KBArticleLocalServiceUtil.
					getKBArticleAndAllDescendantKBArticles(
						resourcePrimKey, WorkflowConstants.STATUS_APPROVED,
						null);

			for (KBArticle kbArticle : kbArticles) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, portletId, kbArticle);
			}
		}

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		long resourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", StringPool.BLANK));

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, KBArticle.class);

		Map<Long, Long> kbArticleResourcePrimKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				KBArticle.class);

		resourcePrimKey = MapUtil.getLong(
			kbArticleResourcePrimKeys, resourcePrimKey, resourcePrimKey);

		try {
			portletPreferences.setValue(
				"resourcePrimKey", String.valueOf(resourcePrimKey));
		}
		catch (ReadOnlyException roe) {
			StringBundler sb = new StringBundler(8);

			sb.append("Unable to save converted portlet preference ");
			sb.append("resourcePrimKey=");
			sb.append(resourcePrimKey);
			sb.append(" (the root article) ");
			sb.append("while importing KB Article portlet. ");
			sb.append("(portletId=");
			sb.append(portletId);
			sb.append(")");

			throw new PortletDataException(sb.toString(), roe);
		}

		return portletPreferences;
	}

}