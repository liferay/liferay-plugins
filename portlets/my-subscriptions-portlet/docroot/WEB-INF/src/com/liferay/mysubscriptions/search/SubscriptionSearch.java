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

package com.liferay.mysubscriptions.search;

import com.liferay.mysubscriptions.util.comparator.SubscriptionClassNameIdComparator;
import com.liferay.mysubscriptions.util.comparator.SubscriptionClassPKComparator;
import com.liferay.mysubscriptions.util.comparator.SubscriptionCreateDateComparator;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Subscription;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 */
public class SubscriptionSearch extends SearchContainer<Subscription> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("class-name-id");
		headerNames.add("class-primary-key");
		headerNames.add("create-date");

		orderableHeaders.put("class-name-id", "class-name-id");
		orderableHeaders.put("class-primary-key", "class-primary-key");
		orderableHeaders.put("create-date", "create-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-subscriptions-were-found";

	public SubscriptionSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new DisplayTerms(portletRequest),
			new DisplayTerms(portletRequest), DEFAULT_CUR_PARAM, DEFAULT_DELTA,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					"1_WAR_mysubscriptionsportlet",
					"subscriptions-order-by-col", orderByCol);
				preferences.setValue(
					"1_WAR_mysubscriptionsportlet",
					"subscriptions-order-by-type", orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					"1_WAR_mysubscriptionsportlet",
					"subscriptions-order-by-col", "create-date");
				orderByType = preferences.getValue(
					"1_WAR_mysubscriptionsportlet",
					"subscriptions-order-by-type", "desc");
			}

			boolean orderByAsc = false;

			if (orderByType.equals("asc")) {
				orderByAsc = true;
			}

			OrderByComparator orderByComparator = null;

			if (orderByCol.equals("class-name-id")) {
				orderByComparator = new SubscriptionClassNameIdComparator(
					orderByAsc);
			}
			else if (orderByCol.equals("class-primary-key")) {
				orderByComparator = new SubscriptionClassPKComparator(
					orderByAsc);
			}
			else if (orderByCol.equals("create-date")) {
				orderByComparator = new SubscriptionCreateDateComparator(
					orderByAsc);
			}

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SubscriptionSearch.class);

}