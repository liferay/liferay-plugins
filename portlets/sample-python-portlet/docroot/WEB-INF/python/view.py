import sys;

renderResponse.setContentType("text/html");
out = renderResponse.getPortletOutputStream();

custom = Custom(renderRequest, renderResponse);

out.write(custom.sayHello() + "\n");

out.write("""
	<br />

	<div>
		This is a sample portlet written in Python. It shows how to navigate pages
		and how to execute actions. The example action increments a number everytime
		it's called and saves it as a preference.
	</div>

	<br /> \n
""");

out.write(custom.showNumber() + "\n");

out.write(custom.navigation() + "\n");