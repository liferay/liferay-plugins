renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

out << Custom.sayHello();

out << """
	<br />

	<div>
		This is a sample portlet written in Groovy. It shows how to navigate pages
		and how to execute actions. The example action increments a number everytime
		it's called and saves it as a preference.
	</div>

	<br />
""";

out << Custom.showNumber(renderRequest, renderResponse);

out << Custom.navigation(renderResponse);