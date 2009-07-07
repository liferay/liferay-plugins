module LiferayPortlet
	def LiferayPortlet.actionLink(text, params=nil)
		link actionUrl(params), text
	end

	def LiferayPortlet.actionUrl(params=nil)
		portletUrl = $renderResponse.createActionURL
		url portletUrl, params
	end

	def LiferayPortlet.link(url, text)
		"<a href='" + url + "'>" + text + "</a>"
	end

	def LiferayPortlet.renderLink(text, target=nil, params=nil)
		link renderUrl(target, params), text
	end

	def LiferayPortlet.renderUrl(target=nil, params=nil)
		portletUrl = $renderResponse.createRenderURL

		if target
			portletUrl.setParameter "rubyFile", target
		end

		url portletUrl, params
	end

	def LiferayPortlet.showPortletDetails(renderRequest)
		"""
			<table class='liferay-table'>
			<tr>
				<td>Portlet Name:</td>
				<td>#{$portletConfig.portletName}</td>
			</tr>
			<tr>
				<td>Preferences:</td>
				<td>#{renderRequest.getPreferences.getMap}</td>
			</tr>
			<tr>
				<td>Parameters:</td>
				<td>#{renderRequest.getParameterMap}</td>
			</tr>
			</table>
		"""
	end

	def LiferayPortlet.showUserDetails(userInfo)
		"""
			<table class='liferay-table'>
			<tr>
				<td>User ID:</td>
				<td>#{userInfo['liferay.user.id']}</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td>#{userInfo['user.name.given']}</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>#{userInfo['user.name.family']}</td>
			</tr>
			<tr>
				<td>Email Address:</td>
				<td>#{userInfo['user.home-info.online.email']}</td>
			</tr>
			</table>
		"""
	end

	def LiferayPortlet.url(portletUrl, params=nil)
		if params
			params.each { |key, value|
				portletUrl.setParameter(key, value)
			}
		end

		portletUrl.toString
	end
end