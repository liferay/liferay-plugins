/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.display.search;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleSearch extends SearchContainer<KBArticle> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-articles-were-found";

	public KBArticleSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new DisplayTerms(portletRequest),
			new DisplayTerms(portletRequest), DEFAULT_CUR_PARAM, DEFAULT_DELTA,
			iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		try {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			PortletPreferences portletPreferences =
				PortletPreferencesFactoryUtil.getPortletSetup(portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				portalPreferences.setValue(
					PortletKeys.KNOWLEDGE_BASE_DISPLAY,
					"kb-articles-order-by-col", orderByCol);
				portalPreferences.setValue(
					PortletKeys.KNOWLEDGE_BASE_DISPLAY,
					"kb-articles-order-by-type", orderByType);
			}
			else {
				String portletOrderByCol = portletPreferences.getValue(
					"kbArticlesOrderByCol", StringPool.BLANK);
				String portletOrderByType = portletPreferences.getValue(
					"kbArticlesOrderByType", StringPool.BLANK);

				orderByCol = portalPreferences.getValue(
					PortletKeys.KNOWLEDGE_BASE_DISPLAY,
					"kb-articles-order-by-col", portletOrderByCol);
				orderByType = portalPreferences.getValue(
					PortletKeys.KNOWLEDGE_BASE_DISPLAY,
					"kb-articles-order-by-type", portletOrderByType);
			}

			OrderByComparator orderByComparator =
				KnowledgeBaseUtil.getKBArticleOrderByComparator(
					orderByCol, orderByType);

			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleSearch.class);

}