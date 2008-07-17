//importPackage(com.liferay.portal.service);

renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

out.println("\n\
	<b>Using the JDK</b>\n\n\
	<br /><br />\n\n\
	The following set has been generated with java.util.TreeSet.\n\n\
	<br /><br />\n");

set = new java.util.TreeSet();

set.add("foo");
set.add("Bar");
set.add("baz");

iter = set.iterator();

while (iter.hasNext()) {
	out.println(iter.next() + ", ");
};

out.println("\n\
	<br /><br />\n\n\
	<b>Invoking Liferay Services</b>\n\n\
	<br /><br />\n");

if (userInfo != null) {
	userId = userInfo.get('liferay.user.id');
	organizations = com.liferay.portal.service.OrganizationServiceUtil.getUserOrganizations(userId);
	out.println("\nYou belong to " + organizations.size() + " organizations.\n");
} else {
	out.println("\nYou do not belong to any organizations.\n");
}

out.println(Custom.navigation(renderResponse));