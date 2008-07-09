from com.liferay.portal.service import OrganizationServiceUtil;

from java.util import TreeSet;

renderResponse.setContentType("text/html");

out = renderResponse.getPortletOutputStream();

custom = Custom(renderRequest, renderResponse);

out.write("""
	<b>Using the JDK</b>

	<br /><br />

	The following set has been generated with java.util.TreeSet.

	<br /><br /> \n
""");

set = TreeSet();

set.add("foo");
set.add("Bar");
set.add("baz");

for v in set:
	out.write(v + "\n");

out.write("""
	<br /><br />

	<b>Invoking Liferay Services</b>

	<br /><br /> \n
""");

if (userInfo != None):
	userId = int(userInfo['liferay.user.id']);
	
	organizations = OrganizationServiceUtil.getUserOrganizations(userId);
	
	out.write("""
		You belong to """ + str(organizations.size())+ """ organizations.\n
	""");
else:
	out.write("""
		You do not belong to any organizations.\n
	""");

out.write(custom.navigation() + "\n");