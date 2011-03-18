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

package com.liferay.knowledgebase.admin.search;

import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class TemplateSearch extends SearchContainer<Template> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-templates-were-found";

	public TemplateSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TemplateDisplayTerms(portletRequest),
			new TemplateSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		TemplateDisplayTerms displayTerms =
			(TemplateDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TemplateDisplayTerms.ANYTIME,
			String.valueOf(displayTerms.isAnytime()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.CONTENT, displayTerms.getContent());
		iteratorURL.setParameter(
			TemplateDisplayTerms.END_DATE_DAY,
			String.valueOf(displayTerms.getEndDateDay()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.END_DATE_MONTH,
			String.valueOf(displayTerms.getEndDateMonth()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.END_DATE_YEAR,
			String.valueOf(displayTerms.getEndDateYear()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.START_DATE_DAY,
			String.valueOf(displayTerms.getStartDateDay()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.START_DATE_MONTH,
			String.valueOf(displayTerms.getStartDateMonth()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.START_DATE_YEAR,
			String.valueOf(displayTerms.getStartDateYear()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.TITLE, displayTerms.getTitle());

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String oldOrderByCol = preferences.getValue(
				PortletKeys.KNOWLEDGE_BASE_ADMIN, "templates-order-by-col",
				"modified-date");
			String oldOrderByType = preferences.getValue(
				PortletKeys.KNOWLEDGE_BASE_ADMIN, "templates-order-by-type",
				"desc");

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol", oldOrderByCol);
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType", oldOrderByType);

			if (!Validator.equals(orderByCol, oldOrderByCol) ||
				!Validator.equals(orderByType, oldOrderByType)) {

				preferences.setValue(
					PortletKeys.KNOWLEDGE_BASE_ADMIN, "templates-order-by-col",
					orderByCol);
				preferences.setValue(
					PortletKeys.KNOWLEDGE_BASE_ADMIN, "templates-order-by-type",
					orderByType);

				TemplateSearchTerms searchTerms =
					(TemplateSearchTerms)getSearchTerms();

				searchTerms.setCurStartValues(new int[0]);
			}

			OrderByComparator orderByComparator =
				KnowledgeBaseUtil.getTemplateOrderByComparator(
					orderByCol, orderByType);

			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TemplateSearch.class);

}