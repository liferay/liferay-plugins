import com.liferay.portal.kernel.service.OrganizationServiceUtil;

renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

out << """
	<b>Using the JDK</b>

	<br /><br />

	The following set has been generated with java.util.TreeSet.

	<br /><br />
""";

set = new TreeSet();

set.add("foo");
set.add("Bar");
set.add("baz");

set.each {
	out.println "${it}, "
};

out << """
	<br /><br />

	<b>Invoking Liferay Services</b>

	<br /><br />
""";

if (userInfo != null) {
	userId = userInfo['liferay.user.id'].toInteger();
	
	organizations = OrganizationServiceUtil.getUserOrganizations(userId);
	
	out << """
		You belong to ${organizations.size()} organizations.
	""";
} else {
	out << """
		You do not belong to any organizations.
	""";
}

out << Custom.navigation(renderResponse);