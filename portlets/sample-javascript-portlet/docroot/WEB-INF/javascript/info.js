renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

out.println(Custom.sayHello());

out.println("<br />");

params = renderRequest.getParameterMap();

if (params.get('type') && (params.get('type')[0] == 'user')) {
	if (userInfo != null) {
		out.println(LiferayPortlet.showUserDetails(userInfo));
	} else {
		out.println("You are not signed in<br />");
	}
}
else {
	out.println(LiferayPortlet.showPortletDetails(portletConfig, renderRequest));
}

out.println(Custom.navigation(renderResponse));