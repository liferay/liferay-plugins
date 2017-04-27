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
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
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
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Sorin Pop
 */
public class KBDisplayPortletDataHandler extends AdminPortletDataHandler {

	public KBDisplayPortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
		setDataPortletPreferences("resourceClassNameId", "resourcePrimKey");
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

		portletPreferences.setValue("resourceClassNameId", StringPool.BLANK);
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

		long resourceClassNameId = GetterUtil.getLong(
			portletPreferences.getValue(
				"resourceClassNameId", StringPool.BLANK));

		final String resourceClassName;

		if ((resourcePrimKey ==
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) &&
			(resourceClassNameId == 0)) {

			resourceClassName = KBFolderConstants.getClassName();
		}
		else {
			resourceClassName = PortalUtil.getClassName(resourceClassNameId);
		}

		try {
			portletPreferences.setValue(
				"resourceClassNameId", resourceClassName);
		}
		catch (ReadOnlyException roe) {
			StringBundler sb = new StringBundler(7);

			sb.append("Unable to save converted portlet preference ");
			sb.append("\"resourceClassNameId\" from ");
			sb.append(resourceClassNameId);
			sb.append(" to ");
			sb.append(resourceClassName);
			sb.append(" while exporting KB Display portlet ");
			sb.append(portletId);

			throw new PortletDataException(sb.toString(), roe);
		}

		if (resourceClassName.equals(KBArticleConstants.getClassName())) {
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
		}
		else if (resourceClassName.equals(KBFolderConstants.getClassName())) {
			if (resourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				KBFolder rootFolder = KBFolderLocalServiceUtil.fetchKBFolder(
					resourcePrimKey);

				if (rootFolder == null) {
					StringBundler sb = new StringBundler(4);

					sb.append("KB Display portlet with ID ");
					sb.append(portletId);
					sb.append(" refers to an inexistent root folder: ");
					sb.append(resourcePrimKey);

					throw new PortletDataException(sb.toString());
				}

				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, portletId, rootFolder);
			}
		}

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		String resourceClassName = GetterUtil.getString(
			portletPreferences.getValue(
				"resourceClassNameId", StringPool.BLANK));

		if (resourceClassName.equals(KBArticleConstants.getClassName())) {
			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, KBArticle.class);
		}
		else if (resourceClassName.equals(KBFolderConstants.getClassName())) {
			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, KBFolder.class);
		}

		try {
			portletPreferences.setValue(
				"resourceClassNameId",
				String.valueOf(PortalUtil.getClassNameId(resourceClassName)));
		}
		catch (ReadOnlyException roe) {
			StringBundler sb = new StringBundler(7);

			sb.append("Unable to save reconverted portlet preference ");
			sb.append("\"resourceClassNameId\" from ");
			sb.append(resourceClassName);
			sb.append(" to ");
			sb.append(PortalUtil.getClassNameId(resourceClassName));
			sb.append(" while importing KB Display portlet ");
			sb.append(portletId);

			throw new PortletDataException(sb.toString(), roe);
		}

		long resourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", StringPool.BLANK));

		Map<Long, Long> resourcePrimKeys = new HashMap<Long, Long>();

		if (resourceClassName.equals(KBArticleConstants.getClassName())) {
			resourcePrimKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					KBArticle.class);
		}
		else if (resourceClassName.equals(KBFolderConstants.getClassName())) {
			resourcePrimKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					KBFolder.class);
		}

		resourcePrimKey = MapUtil.getLong(
			resourcePrimKeys, resourcePrimKey, resourcePrimKey);

		try {
			portletPreferences.setValue(
				"resourcePrimKey", String.valueOf(resourcePrimKey));
		}
		catch (ReadOnlyException roe) {
			StringBundler sb = new StringBundler(5);

			sb.append("Unable to save converted portlet preference ");
			sb.append("\"resourcePrimKey\" ");
			sb.append(resourcePrimKey);
			sb.append(" while importing KB Display portlet ");
			sb.append(portletId);

			throw new PortletDataException(sb.toString(), roe);
		}

		return portletPreferences;
	}

}