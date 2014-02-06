/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBArticleExportActionableDynamicQuery;
import com.liferay.knowledgebase.service.persistence.KBCommentExportActionableDynamicQuery;
import com.liferay.knowledgebase.service.persistence.KBTemplateExportActionableDynamicQuery;
import com.liferay.knowledgebase.util.comparator.KBArticleVersionComparator;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "knowledge_base";

	public AdminPortletDataHandler() {
		setDataLevel(DataLevel.SITE);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(KBArticle.class),
			new StagedModelType(KBComment.class),
			new StagedModelType(KBTemplate.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "articles", true, true, null,
				KBArticle.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "templates", true, true, null,
				KBTemplate.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "comments", true, true, null,
				KBComment.class.getName()));
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		KBArticleLocalServiceUtil.deleteGroupKBArticles(
			portletDataContext.getScopeGroupId());

		KBTemplateLocalServiceUtil.deleteGroupKBTemplates(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(RESOURCE_NAME);

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ActionableDynamicQuery KBArticleActionableDynamicQuery =
			getKBArticlesActionableDynamicQuery(portletDataContext);

		KBArticleActionableDynamicQuery.performActions();

		ActionableDynamicQuery KBTemplateActionableDynamicQuery =
			new KBTemplateExportActionableDynamicQuery(portletDataContext);

		KBTemplateActionableDynamicQuery.performActions();

		ActionableDynamicQuery KBCommentActionableDynamicQuery =
			getKBCommentsActionableDynamicQuery(portletDataContext);

		KBCommentActionableDynamicQuery.performActions();

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(RESOURCE_NAME);

		Element KBArticlesElement =
			portletDataContext.getImportDataGroupElement(KBArticle.class);

		List<Element> KBArticleElements = KBArticlesElement.elements();

		for (Element KBArticleElement : KBArticleElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, KBArticleElement);
		}

		Element KBTemplatesElement =
			portletDataContext.getImportDataGroupElement(KBTemplate.class);

		List<Element> KBTemplateElements = KBTemplatesElement.elements();

		for (Element KBTemplateElement : KBTemplateElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, KBTemplateElement);
		}

		Element KBCommentsElement =
			portletDataContext.getImportDataGroupElement(KBComment.class);

		List<Element> KBCommentElements = KBCommentsElement.elements();

		for (Element KBCommentElement : KBCommentElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, KBCommentElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery KBArticleActionableDynamicQuery =
			new KBArticleExportActionableDynamicQuery(portletDataContext);

		KBArticleActionableDynamicQuery.performCount();

		ActionableDynamicQuery KBTemplateActionableDynamicQuery =
			new KBTemplateExportActionableDynamicQuery(portletDataContext);

		KBTemplateActionableDynamicQuery.performCount();

		ActionableDynamicQuery KBCommentActionableDynamicQuery =
			getKBCommentsActionableDynamicQuery(portletDataContext);

		KBCommentActionableDynamicQuery.performCount();
	}

	protected ActionableDynamicQuery getKBArticlesActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		return new KBArticleExportActionableDynamicQuery(portletDataContext) {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				super.addCriteria(dynamicQuery);

				OrderFactoryUtil.addOrderByComparator(
					dynamicQuery, new KBArticleVersionComparator(true));
			}

		};
	}

	protected ActionableDynamicQuery getKBCommentsActionableDynamicQuery(
			final PortletDataContext portletDataContext)
		throws Exception {

		return new KBCommentExportActionableDynamicQuery(portletDataContext) {

			@Override
			protected StagedModelType getStagedModelType() {
				return new StagedModelType(
					PortalUtil.getClassNameId(KBComment.class.getName()),
					StagedModelType.REFERRER_CLASS_NAME_ID_ALL);
			}

		};
	}

	protected static final String RESOURCE_NAME =
		"com.liferay.knowledgebase.admin";

}