renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

params = renderRequest.getParameterMap();

out << Custom.sayHello();

out << "<br />";

if (params['type'] && (params['type'][0] == 'user')) {
    if (userInfo != null) {
	   out << LiferayPortlet.showUserDetails(userInfo);
    } else {
       out << "User is not logged in<br/>";
    }
}
else {
	out << LiferayPortlet.showPortletDetails(portletConfig, renderRequest);
}

out << Custom.navigation(renderResponse);