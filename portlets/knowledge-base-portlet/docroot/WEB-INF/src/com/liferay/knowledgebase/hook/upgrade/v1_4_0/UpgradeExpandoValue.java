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

package com.liferay.knowledgebase.hook.upgrade.v1_4_0;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

/**
 * @author Peter Shin
 */
public class UpgradeExpandoValue extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		ExpandoTable expandoTable = getExpandoTable("KB");

		if (expandoTable != null) {
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

	protected void upgradeExpandoValues(ExpandoTable expandoTable)
		throws Exception {

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				expandoTable.getCompanyId(), Subscription.class.getName(),
				"KB", "portletPrimKeys", QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			Subscription subscription = null;

			try {
				subscription = SubscriptionLocalServiceUtil.getSubscription(
					expandoValue.getClassPK());
			}
			catch (NoSuchSubscriptionException nsse) {
				continue;
			}

			try {
				ArticleLocalServiceUtil.getLatestArticle(
					subscription.getClassPK(), WorkflowConstants.STATUS_ANY);
			}
			catch (NoSuchArticleException nsae) {
				continue;
			}

			String[] portletPrimKeys = expandoValue.getStringArray();

			ExpandoValueLocalServiceUtil.deleteValue(
				subscription.getCompanyId(), Subscription.class.getName(), "KB",
				"portletPrimKeys", subscription.getSubscriptionId());

			ExpandoValueLocalServiceUtil.addValue(
				subscription.getCompanyId(), Subscription.class.getName(), "KB",
				"portletPrimKeys", subscription.getSubscriptionId(),
				portletPrimKeys[0]);
		}
	}

}