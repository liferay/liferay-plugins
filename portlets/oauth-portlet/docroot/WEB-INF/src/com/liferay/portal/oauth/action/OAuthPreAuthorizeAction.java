package com.liferay.portal.oauth.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.StringBundler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class OAuthPreAuthorizeAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String uri = request.getRequestURI();
		String queryString = request.getQueryString();

		StringBundler sb =new StringBundler();
		sb.append(uri);
		sb.append("/private?");
		sb.append(queryString);
		sb.append("&redirect=");
		sb.append(uri);
		sb.append("?");
		sb.append(queryString);

		response.sendRedirect(sb.toString());

		return null;
	}

}