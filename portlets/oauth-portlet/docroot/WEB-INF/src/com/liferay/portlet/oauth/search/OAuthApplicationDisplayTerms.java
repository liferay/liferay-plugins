package com.liferay.portlet.oauth.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;
public class OAuthApplicationDisplayTerms extends DisplayTerms {
	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public OAuthApplicationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		name = ParamUtil.getString(portletRequest, NAME);
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	protected String description;
	protected String name;
}