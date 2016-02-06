require 'java'

include_class 'com.liferay.portal.kernel.service.OrganizationServiceUtil'
include_class 'java.util.TreeSet'

$renderResponse.setContentType "text/html"

out = $renderResponse.getPortletOutputStream

out.println """
	<b>Using the JDK</b>

	<br /><br />

	The following set has been generated with java.util.TreeSet.

	<br /><br />
"""

set = TreeSet.new

set.add "foo"
set.add "Bar"
set.add "baz"

set.each do |v|
	out.println "#{v}, "
end

out.println """
	<br /><br />

	<b>Invoking Liferay Services</b>

	<br /><br />
"""
if ($userInfo != nil)
	userId = $userInfo['liferay.user.id'].to_i
	
	organizations = OrganizationServiceUtil.getUserOrganizations(userId)
	
	out.println """
		You belong to #{organizations.size} organizations.
	"""
elsif
	out.println """
		You do not belong to any organizations.
	"""
end
out.println Custom.navigation