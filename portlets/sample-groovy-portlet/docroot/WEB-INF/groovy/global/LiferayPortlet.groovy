import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletConfig;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

class LiferayPortlet {

	static String actionLink(RenderResponse renderResponse, String text, Map params) {
		return link(actionUrl(renderResponse, params), text);
	}

	static String actionUrl(RenderResponse renderResponse, Map params) {
		PortletURL portletUrl = renderResponse.createActionURL();

		return url(portletUrl, params);
	}

	static String link(String url, String text) {
		return "<a href='" + url + "'>" + text + "</a>";
	}

	static String renderLink(RenderResponse renderResponse, String text, String target, Map params) {
		return link(renderUrl(renderResponse, target, params), text);
	}

	static renderUrl(RenderResponse renderResponse, String target, Map params) {
		PortletURL portletUrl = renderResponse.createRenderURL();

		if (Validator.isNotNull(target)) {
			portletUrl.setParameter("groovyFile", target);
		}

		return url(portletUrl, params);
	}

	static String showPortletDetails(PortletConfig portletConfig, RenderRequest renderRequest) {
		"""
			<table class='liferay-table'>
	   		<tr>
				<td>Portlet Name:</td>
				<td>${portletConfig.getPortletName()}</td>
			</tr>
			<tr>
				<td>Preferences:</td>
				<td>${renderRequest.getPreferences().getMap()}</td>
			</tr>
			<tr>
				<td>Parameters:</td>
				<td>${renderRequest.getParameterMap()}</td>
			</tr>
			</table>
		"""
	}

	static String showUserDetails(Map userInfo) {
		"""
			<table class='liferay-table'>
			<tr>
				<td>User ID:</td>
				<td>${userInfo['liferay.user.id']}</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td>${userInfo['user.name.given']}</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>${userInfo['user.name.family']}</td>
			</tr>
			<tr>
				<td>Email Address:</td>
				<td>${userInfo['user.home-info.online.email']}</td>
			</tr>
			</table>
		"""
	}

	static String url(PortletURL portletUrl, Map params) {
		params.keySet().each({portletUrl.setParameter(it, params[it])});

		return portletUrl.toString();
	}

}