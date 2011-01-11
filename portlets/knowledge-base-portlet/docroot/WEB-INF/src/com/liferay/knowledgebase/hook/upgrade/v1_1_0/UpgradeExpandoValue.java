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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

/**
 * @author Peter Shin
 */
public class UpgradeExpandoValue extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		ExpandoTable expandoTable = getExpandoTable("KB");

		if (expandoTable == null) {
			return;
		}

		if (hasExpandoColumn(expandoTable, "portletIds")) {
			upgradeExpandoValues(expandoTable);
		}
	}

	protected ExpandoTable getExpandoTable(String name) throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		try {
			return ExpandoTableLocalServiceUtil.getTable(
				companyId, Subscription.class.getName(), name);
		}
		catch (NoSuchTableException nste) {
			return null;
		}
	}

	protected boolean hasExpandoColumn(ExpandoTable expandoTable, String name)
		throws Exception {

		try {
			ExpandoColumnLocalServiceUtil.getColumn(
				expandoTable.getTableId(), name);
		}
		catch (NoSuchColumnException nsce) {
			return false;
		}

		return true;
	}

	protected void upgradeExpandoValues(ExpandoTable expandoTable)
		throws Exception {

		Group controlPanelGroup = GroupLocalServiceUtil.getGroup(
			expandoTable.getCompanyId(), GroupConstants.CONTROL_PANEL);

		long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(
			controlPanelGroup.getGroupId(), true);

		if (!hasExpandoColumn(expandoTable, "portletPrimKeys")) {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), "portletPrimKeys",
				ExpandoColumnConstants.STRING_ARRAY);
		}

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				expandoTable.getCompanyId(), Subscription.class.getName(),
				"KB", "portletIds", QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			Subscription subscription = null;

			try {
				subscription = SubscriptionLocalServiceUtil.getSubscription(
					expandoValue.getClassPK());
			}
			catch (NoSuchSubscriptionException nsse) {
				continue;
			}

			Article article = null;

			try {
				article = ArticleLocalServiceUtil.getLatestArticle(
					subscription.getClassPK(), WorkflowConstants.STATUS_ANY);
			}
			catch (NoSuchArticleException nsae) {
			}

			long groupId = subscription.getClassPK();

			if (article != null) {
				groupId = article.getGroupId();
			}

			String[] portletPrimKeys = new String[0];

			String[] portletIds = expandoValue.getStringArray();

			for (int i = 0; i < portletIds.length; i++) {
				String portletId = portletIds[i];

				String portletPrimKey = null;

				if (portletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) {
					portletPrimKey = ArticleConstants.getPortletPrimKey(
						controlPanelPlid, PortletKeys.KNOWLEDGE_BASE_ADMIN);
				}

				long plid = LayoutConstants.DEFAULT_PLID;

				if (portletPrimKey == null) {
					plid = PortalUtil.getPlidFromPortletId(groupId, portletId);
				}

				if (plid != LayoutConstants.DEFAULT_PLID) {
					portletPrimKey = ArticleConstants.getPortletPrimKey(
						plid, portletId);
				}

				if (portletPrimKey == null) {
					continue;
				}

				portletPrimKeys = ArrayUtil.append(
					portletPrimKeys, portletPrimKey);

				if ((portletPrimKeys.length > 0) && (article != null)) {
					break;
				}
			}

			if (portletPrimKeys.length <= 0) {
				SubscriptionLocalServiceUtil.deleteSubscription(
					subscription.getSubscriptionId());
			}
			else {
				ExpandoValueLocalServiceUtil.addValue(
					subscription.getCompanyId(), Subscription.class.getName(),
					"KB", "portletPrimKeys", subscription.getSubscriptionId(),
					portletPrimKeys);
			}
		}

		ExpandoColumnLocalServiceUtil.deleteColumn(
			expandoTable.getTableId(), "portletIds");
	}

}