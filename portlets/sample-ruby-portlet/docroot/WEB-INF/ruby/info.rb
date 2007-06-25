$renderResponse.setContentType "text/html"

out = $renderResponse.getPortletOutputStream

params = $renderRequest.getParameterMap

out.println Custom.sayHello

out.println "<br />"

if (params['type'] && params['type'][0] == 'user')
	out.println LiferayPortlet.showUserDetails($userInfo)
elsif
	out.println LiferayPortlet.showPortletDetails($renderRequest)
end

out.println Custom.navigation