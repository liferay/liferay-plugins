renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

out.println(Custom.sayHello());

out.println("\n \
	<br /> \n\n\
	<div>\n\
		This is a sample portlet written in Javascript. It shows how to navigate pages \
		and how to execute actions. The example action increments a number everytime \
		it's called and saves it as a preference.\n\
	</div>\n\n\
	<br />\n");

out.println(Custom.showNumber(renderRequest, renderResponse));

out.println(Custom.navigation(renderResponse));
