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

package com.liferay.knowledgebase.hook.listeners;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.expando.model.ExpandoValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Peter Shin
 */
public class PortletPreferencesListener
	extends BaseModelListener<PortletPreferences> {

	public void onBeforeRemove(PortletPreferences preferences)
		throws ModelListenerException {

		try {
			doOnBeforeRemove(preferences);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	public void onBeforeUpdate(PortletPreferences preferences)
		throws ModelListenerException {

		try {
			doOnBeforeUpdate(preferences);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void doOnBeforeRemove(PortletPreferences preferences)
		throws Exception {

		String rootPortletId = PortletConstants.getRootPortletId(
			preferences.getPortletId());

		if (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_AGGREGATOR) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SEARCH)) {

			return;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(preferences.getPlid());

		List<ExpandoValue> expandoValues =
			ArticleLocalServiceUtil.getExpandoValues(
				layout.getCompanyId(), preferences.getPlid(),
				preferences.getPortletId());

		for (ExpandoValue expandoValue : expandoValues) {
			String[] portletPrimKeys = expandoValue.getStringArray();

			String portletPrimKey = ArticleConstants.getPortletPrimKey(
				preferences.getPlid(), preferences.getPortletId());

			if (!ArrayUtil.contains(portletPrimKeys, portletPrimKey)) {
				continue;
			}

			Subscription subscription =
				SubscriptionLocalServiceUtil.getSubscription(
					expandoValue.getClassPK());

			ArticleLocalServiceUtil.unsubscribe(
				subscription.getCompanyId(), subscription.getUserId(),
				preferences.getPlid(), preferences.getPortletId(),
				subscription.getClassPK());
		}
	}

	protected void doOnBeforeUpdate(PortletPreferences preferences)
		throws Exception {

		String rootPortletId = PortletConstants.getRootPortletId(
			preferences.getPortletId());

		if (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_AGGREGATOR) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST) &&
			!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SEARCH)) {

			return;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(preferences.getPlid());

		List<ExpandoValue> expandoValues =
			ArticleLocalServiceUtil.getExpandoValues(
				layout.getCompanyId(), preferences.getPlid(),
				preferences.getPortletId());

		List<Long> validResourcePrimKeys = new ArrayList<Long>();

		List<AssetEntry> assetEntries = KnowledgeBaseUtil.getAssetEntries(
			preferences.getPlid(), preferences.getPortletId(), 0, null);

		for (ExpandoValue expandoValue : expandoValues) {
			String[] portletPrimKeys = expandoValue.getStringArray();

			String portletPrimKey = ArticleConstants.getPortletPrimKey(
				preferences.getPlid(), preferences.getPortletId());

			if (!ArrayUtil.contains(portletPrimKeys, portletPrimKey)) {
				continue;
			}

			Subscription subscription =
				SubscriptionLocalServiceUtil.getSubscription(
					expandoValue.getClassPK());

			try {
				ArticleLocalServiceUtil.getLatestArticle(
					subscription.getClassPK(), WorkflowConstants.STATUS_ANY);
			}
			catch (NoSuchArticleException nsae) {
				continue;
			}

			if (validResourcePrimKeys.contains(subscription.getClassPK())) {
				continue;
			}

			if (hasArticle(
					subscription.getClassPK(), assetEntries, preferences)) {

				validResourcePrimKeys.add(subscription.getClassPK());

				continue;
			}

			ArticleLocalServiceUtil.unsubscribe(
				subscription.getCompanyId(), subscription.getUserId(),
				preferences.getPlid(), preferences.getPortletId(),
				subscription.getClassPK());
		}
	}

	protected boolean hasArticle(
			long resourcePrimKey, List<AssetEntry> assetEntries,
			PortletPreferences preferences)
		throws Exception {

		Map<String, Object> preferencesMap =
			KnowledgeBaseUtil.getPreferencesMap(preferences);

		String selectionMethod = (String)preferencesMap.get("selectionMethod");
		long[] resourcePrimKeys = ArrayUtil.toArray(
			(Long[])preferencesMap.get("resourcePrimKeys"));

		if (selectionMethod.equals("articles")) {
			if (assetEntries != null) {
				long[] classPKs = StringUtil.split(
					ListUtil.toString(assetEntries, "classPK"), 0L);

				Set<Long> classPKsSet = SetUtil.fromArray(classPKs);
				Set<Long> resourcePrimKeysSet = SetUtil.fromArray(
					resourcePrimKeys);

				resourcePrimKeysSet.retainAll(classPKsSet);

				resourcePrimKeys = StringUtil.split(
					StringUtil.merge(resourcePrimKeysSet), 0L);
			}

			while (resourcePrimKey !=
						ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

				if (ArrayUtil.contains(resourcePrimKeys, resourcePrimKey)) {
					return true;
				}

				Article article = ArticleLocalServiceUtil.getLatestArticle(
					resourcePrimKey, WorkflowConstants.STATUS_ANY);

				resourcePrimKey = article.getParentResourcePrimKey();
			}
		}
		else if (selectionMethod.equals("group")) {
			if (assetEntries == null) {
				return true;
			}

			long[] classPKs = StringUtil.split(
				ListUtil.toString(assetEntries, "classPK"), 0L);

			if (ArrayUtil.contains(classPKs, resourcePrimKey)) {
				return true;
			}
		}

		return false;
	}

}