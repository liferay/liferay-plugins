import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

class Custom {

	static String navigation(RenderResponse renderResponse) {
		"""
			<div class='separator'></div>

			<div>
				Navigation:

				${LiferayPortlet.renderLink(renderResponse, "Home", "/WEB-INF/groovy/view.groovy", [:])} -
				${LiferayPortlet.renderLink(renderResponse, "User Info", "/WEB-INF/groovy/info.groovy", ['type' : 'user'])} -
				${LiferayPortlet.renderLink(renderResponse, "Portlet Info", "/WEB-INF/groovy/info.groovy", ['type' : 'portlet'])} -
				${LiferayPortlet.renderLink(renderResponse, "Invoke Java", "/WEB-INF/groovy/java.groovy", [:])}
			</div>
		"""
	}

	static String sayHello() {
		return """
			<div>
				<b>Hello Groovy Portlet users!</b>
			</div>
		""";
	}

	static String showNumber(RenderRequest renderRequest, RenderResponse renderResponse) {
		PortletPreferences preferences = renderRequest.getPreferences();

		String num = preferences.getValue("number", "0");

		return """
			<div>
				Number: ${num} (${LiferayPortlet.actionLink(renderResponse, "Increment", [:])})
			</div>
		""";
	}

}