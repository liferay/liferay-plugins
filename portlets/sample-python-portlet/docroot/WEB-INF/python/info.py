renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

params = renderRequest.getParameterMap();

custom = Custom(renderRequest, renderResponse);
lp = LiferayPortlet(renderRequest, renderResponse);

out.write(custom.sayHello() + "\n");

out.write("<br />");

if (params['type'] and params['type'][0] == 'user'):
	if (userInfo != None):
	   out.write(lp.showUserDetails(userInfo) + "\n");
	else:
	   out.write("You are not signed in.<br />");
else:
	out.write(lp.showPortletDetails(portletConfig) + "\n");

out.write(custom.navigation() + "\n");