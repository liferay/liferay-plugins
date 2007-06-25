$renderResponse.setContentType "text/html"

out = $renderResponse.getPortletOutputStream

out.println Custom.sayHello

out.println """
	<br />

	<div>
		This is a sample portlet written in Ruby. It shows how to navigate pages
		and how to execute actions. The example action increments a number everytime
		it's called and saves it as a preference.
	</div>

	<br />
"""

out.println Custom.showNumber($renderRequest)

out.println Custom.navigation