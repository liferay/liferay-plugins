import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

class Custom {
	static String navigation(RenderResponse res) { 
		"""
			<div class='separator'></div>

			<div>
				Navigation:

				${LiferayPortlet.renderLink(res, "Home", "/WEB-INF/groovy/view.groovy", [:])} -
				${LiferayPortlet.renderLink(res, "User Info", "/WEB-INF/groovy/info.groovy", ['type' : 'user'])} -
				${LiferayPortlet.renderLink(res, "Portlet Info", "/WEB-INF/groovy/info.groovy", ['type' : 'portlet'])} -
				${LiferayPortlet.renderLink(res, "Invoke Java", "/WEB-INF/groovy/java.groovy", [:])}
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

	static String showNumber(RenderRequest req, RenderResponse res) {
		PortletPreferences prefs = req.getPreferences();
		String num = prefs.getValue("number", "0");

		return """
			<div>
				Number: ${num} (${LiferayPortlet.actionLink(res, "Increment", [:])})
			</div>
		""";
	}
}