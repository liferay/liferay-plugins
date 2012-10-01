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

package com.liferay.portlet.oauth.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.oauth.util.comparator.OAuthApplicationOrderByComparator;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 *
 * @author Igor Beslic
 * @author Raymond Augé
 *
 */
public class OAuthApplicationUserSearch
	extends SearchContainer<ApplicationUser> implements OAuthConstants {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add(NAME);
		headerNames.add(WEBSITE);
		headerNames.add(CALLBACK_URL);

		orderableHeaders.put(NAME, NAME);
	}

	public OAuthApplicationUserSearch(
			PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new OAuthApplicationDisplayTerms(portletRequest),
			new OAuthApplicationSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, NO_APPLICATION_USERS);

		OAuthApplicationDisplayTerms displayTerms =
				(OAuthApplicationDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(NAME, displayTerms.getName());

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
						OAUTH_USERS, "apps-order-by-col", orderByCol);
					preferences.setValue(
						OAUTH_USERS, "apps-order-by-type", orderByType);
				}
				else {
					orderByCol = preferences.getValue(
						OAUTH_USERS, "apps-order-by-col", NAME);
					orderByType = preferences.getValue(
						OAUTH_USERS, "apps-order-by-col", ASC);;

					setOrderableHeaders(orderableHeaders);
					setOrderByCol(orderByCol);
					setOrderByType(orderByType);

					OrderByComparator orderByComparator =
									getOAuthApplicationOrderByComparator(
										orderByCol, orderByType);

					setOrderByComparator(orderByComparator);
				}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	protected OrderByComparator getOAuthApplicationOrderByComparator(
			final String orderByColumn, final String orderByType) {
		return getOAuthApplicationOrderByComparator(
				ASC.equals(orderByType), orderByColumn);
	}

	protected OrderByComparator getOAuthApplicationOrderByComparator(
			final boolean ascending, final String orderByColumn) {
		return new OAuthApplicationOrderByComparator(ascending, orderByColumn);
	}

	private static Log _log = LogFactoryUtil.getLog(
			OAuthApplicationUserSearch.class);

}