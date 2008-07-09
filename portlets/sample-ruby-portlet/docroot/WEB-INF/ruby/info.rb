$renderResponse.setContentType "text/html"

out = $renderResponse.getPortletOutputStream

params = $renderRequest.getParameterMap

out.println Custom.sayHello

out.println "<br />"

if (params['type'] && (params['type'][0] == 'user'))
	if ($userInfo != nil)
	   out.println LiferayPortlet.showUserDetails($userInfo)
	elsif
	   out.println "You are not signed in.<br />"
	end
elsif
	out.println LiferayPortlet.showPortletDetails($renderRequest)
end

out.println Custom.navigation