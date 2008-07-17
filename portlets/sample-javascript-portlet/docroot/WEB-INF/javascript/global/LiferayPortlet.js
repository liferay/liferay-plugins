function LiferayPortlet() {}

LiferayPortlet.url = function(portletUrl, params) {
	for (var item in params) {
		portletUrl.setParameter(item, params[item]);
	}

	return portletUrl.toString();
}

LiferayPortlet.actionUrl = function(res, params) {
		var portletUrl = res.createActionURL();

		return LiferayPortlet.url(portletUrl, params);
	}

LiferayPortlet.link = function(url, text) {
		return "<a href='" + url + "'>" + text + "</a>";
	}

LiferayPortlet.actionLink = function(res, text, params) {
		return LiferayPortlet.link(LiferayPortlet.actionUrl(res, params), text);
	}


LiferayPortlet.renderLink = function(res, text, target, params) {
		return LiferayPortlet.link(LiferayPortlet.renderUrl(res, target, params), text);
	}

LiferayPortlet.renderUrl = function(res, target, params) {
		portletUrl = res.createRenderURL();

		if (com.liferay.portal.kernel.util.Validator.isNotNull(target)) {
			portletUrl.setParameter("javascriptFile", target);
		}
		return LiferayPortlet.url(portletUrl, params);
}

LiferayPortlet.printMapArray = function(map) {
	var result = "";
	
	iter = map.keySet().iterator();
	while (iter.hasNext()) {
		key = iter.next();
		result += key + ": [";
		for (var item in map.get(key)) {
			result += map.get(key)[0] + ",";
		}
		result += "], "
	}
	
	return result;
}

LiferayPortlet.printMap = function(map) {
	var result = "";
	
	iter = map.keySet().iterator();
	while (iter.hasNext()) {
		key = iter.next();
		result += key + ": " + map.get(key) + "<br />";
	}
	
	return result;
}

LiferayPortlet.showPortletDetails = function(portletConfig, req) {
	return "\n\
			<table class='liferay-table'>\n\
	   		<tr>\n\
				<td>Portlet Name:</td>\n\
				<td>" + portletConfig.getPortletName() + "</td>\n\
			</tr>\n\
			<tr>\n\
				<td>Preferences:</td>\n\
				<td>" + LiferayPortlet.printMapArray(req.getPreferences().getMap()) + "</td>\n\
			</tr>\n\
			<tr>\n\
				<td>Parameters:</td>\n\
				<td>" + LiferayPortlet.printMapArray(req.getParameterMap()) + "</td>\n\
			</tr>\n\
			</table>\n";
}

LiferayPortlet.showUserDetails = function(userInfo) {
	return "\n\
			<table class='liferay-table'>\n\
			<tr>\n\
				<td>User ID:</td>\n\
				<td>" + userInfo.get('liferay.user.id') + "</td>\n\
			</tr>\n\
			<tr>\n\
				<td>First Name:</td>\n\
				<td>" + userInfo.get('user.name.given') + "</td>\n\
			</tr>\n\
			<tr>\n\
				<td>Last Name:</td>\n\
				<td>" + userInfo.get('user.name.family') + "</td>\n\
			</tr>\n\
			<tr>\n\
				<td>Email Address:</td>\n\
				<td>" + userInfo.get('user.home-info.online.email') + "</td>\n\
			</tr>\n\
			</table>\n";
}

