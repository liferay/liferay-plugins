renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

params = renderRequest.getParameterMap();

out << Custom.sayHello();

out << "<br />";

if (params['type'] && (params['type'][0] == 'user')) {
	out << LiferayPortlet.showUserDetails(userInfo);
}
else {
	out << LiferayPortlet.showPortletDetails(portletConfig, renderRequest);
}

out << Custom.navigation(renderResponse);