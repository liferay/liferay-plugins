renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

params = renderRequest.getParameterMap();

custom = Custom(renderRequest, renderResponse);
lp = LiferayPortlet(renderRequest, renderResponse);

out.write(custom.sayHello() + "\n");

out.write("<br />");

if (params['type'] and params['type'][0] == 'user'):
	out.write(lp.showUserDetails(userInfo) + "\n");
else:
	out.write(lp.showPortletDetails(portletConfig) + "\n");

out.write(custom.navigation() + "\n");