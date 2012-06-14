package com.liferay.portlet.oauth.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.oauth.OAuthConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
public class OAuthApplicationSearch extends SearchContainer<OAuthApplication> {
	public static final String ORDER_BY_ASC = "name ASC";

	public static final String ORDER_BY_DESC = "name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add(OAuthConstants.WEB_APP_NAME);
		headerNames.add(OAuthConstants.WEB_APP_WEBSITE);
		headerNames.add(OAuthConstants.WEB_APP_CALLBACKURL);

		orderableHeaders.put(OAuthConstants.WEB_APP_NAME, OAuthConstants.WEB_APP_NAME);
	}

	public OAuthApplicationSearch(PortletRequest portletRequest,
			PortletURL iteratorURL) {
		super(portletRequest,
				new OAuthApplicationDisplayTerms(portletRequest),
				new OAuthApplicationSearchTerms(portletRequest),
				DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
				OAuthConstants.EMPTY_RESULTS_MESSAGE);

		OAuthApplicationDisplayTerms displayTerms =
				(OAuthApplicationDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(OAuthApplicationDisplayTerms.NAME,
				displayTerms.getName());

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
						OAuthConstants.PORTLET_KEY_OAUTH_ADMIN,
							"apps-order-by-col", orderByCol);
					preferences.setValue(
							OAuthConstants.PORTLET_KEY_OAUTH_ADMIN,
								"apps-order-by-type", orderByType);
				}
				else {
					orderByCol = preferences.getValue(
							OAuthConstants.PORTLET_KEY_OAUTH_ADMIN,
								"apps-order-by-col", "name");
					orderByType = preferences.getValue(
							OAuthConstants.PORTLET_KEY_OAUTH_ADMIN,
								"apps-order-by-type", "asc");

					setOrderableHeaders(orderableHeaders);
					setOrderByCol(orderByCol);
					setOrderByType(orderByType);
					setOrderByComparator(
							getOAuthApplicationOrderByComparator(
									orderByCol, orderByType));
				}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	protected OrderByComparator getOAuthApplicationOrderByComparator(
			final String orderByColumn, final String orderByType) {
		return getOAuthApplicationOrderByComparator("asc".equals(orderByType),
				orderByColumn);
	}

	protected OrderByComparator getOAuthApplicationOrderByComparator(
			final boolean ascending, final String orderByColumn) {
		return new OrderByComparator() {

			@Override
			public int compare(Object obj1, Object obj2) {
				// TODO implement reflections (try to find get method for column - default is name
				OAuthApplication app1 = (OAuthApplication)obj1;
				OAuthApplication app2 = (OAuthApplication)obj2;

				int value = app1.getName().compareTo(app2.getName());

				if (_ascending) {
					return value;
				}
				else {
					return -value;
				}
			}

			@Override
			public String getOrderBy() {
				if (_ascending) {
					return ORDER_BY_ASC;
				}
				else {
					return ORDER_BY_DESC;
				}
			}

			@Override
			public String[] getOrderByFields() {
				return ORDER_BY_FIELDS;
			}

			@Override
			public boolean isAscending() {
				return _ascending;
			}

			boolean _ascending = ascending;
			String _orderByColumn = orderByColumn;
		};
	}

	private static Log _log = LogFactoryUtil.getLog(OAuthApplicationSearch.class);

}