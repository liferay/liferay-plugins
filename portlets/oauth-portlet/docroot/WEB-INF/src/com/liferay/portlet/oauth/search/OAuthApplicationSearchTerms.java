package com.liferay.portlet.oauth.search;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

public class OAuthApplicationSearchTerms extends OAuthApplicationDisplayTerms {

	public OAuthApplicationSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
		
		name = DAOParamUtil.getLike(portletRequest, NAME);
	}

}
