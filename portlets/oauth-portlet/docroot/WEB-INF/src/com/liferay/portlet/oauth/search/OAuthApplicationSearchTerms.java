package com.liferay.portlet.oauth.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

import javax.portlet.PortletRequest;
public class OAuthApplicationSearchTerms extends OAuthApplicationDisplayTerms {

	public OAuthApplicationSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = DAOParamUtil.getLike(portletRequest, NAME);
	}

}