<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<h3>Services: Chat Portlet</h3>

<p>
	<h4>com.liferay.chat.service.impl.EntryLocalServiceImpl</h4>
</p>

<p>
	TestPACLLocalServiceUtil#getEntryLocalServiceUtil_GetEntry=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				try {
					TestPACLLocalServiceUtil.getEntryLocalServiceUtil_GetEntry(0);
				}
				catch (NoSuchEntryException nsee) {
				}
			}

		};
		%>

	TestPACLLocalServiceUtil#getEntryLocalServiceUtil_GetEntries=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getEntryLocalServiceUtil_GetEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>
</p>

<p>
	<h4>com.liferay.chat.service.impl.StatusLocalServiceImpl</h4>
</p>

<p>
	TestPACLLocalServiceUtil#getStatusLocalServiceUtil_GetStatus=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getStatusLocalServiceUtil_GetStatus(0);
			}

		};
		%>

	TestPACLLocalServiceUtil#getStatusLocalServiceUtil_GetStatuses=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getStatusLocalServiceUtil_GetStatuses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>
</p>

<h3>Services: Portal</h3>

<p>
	<h4>com.liferay.portal.service.impl.CompanyLocalServiceImpl</h4>
</p>

<p>
	CompanyLocalServiceUtil#getCompany=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				CompanyLocalServiceUtil.getCompany(themeDisplay.getCompanyId());
			}

		};
		%>

	TestPACLLocalServiceUtil#getCompanyPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getCompanyPersistence_FindByPrimaryKey(themeDisplay.getCompanyId());
			}

		};
		%>

	TestPACLLocalServiceUtil#getCompanyUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getCompanyUtil_FindByPrimaryKey(themeDisplay.getCompanyId());
			}

		};
		%>

</p>

<p>
	<h4>com.liferay.portal.service.impl.GroupLocalServiceImpl</h4>
</p>

<p>
	GroupLocalServiceUtil#getGroup=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
			}

		};
		%>

	TestPACLLocalServiceUtil#getGroupPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getGroupPersistence_FindByPrimaryKey(themeDisplay.getScopeGroupId());
			}

		};
		%>

	TestPACLLocalServiceUtil#getGroupUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getGroupUtil_FindByPrimaryKey(themeDisplay.getScopeGroupId());
			}

		};
		%>

</p>

<p>
	<h4>com.liferay.portal.service.impl.PortalServiceImpl</h4>
</p>

<p>
	TestPACLLocalServiceUtil#getPortalService_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalService_GetBuildNumber();
			}

		};
		%>

	TestPACLLocalServiceUtil#getPortalService_TestGetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalService_TestGetBuildNumber();
			}

		};
		%>

	TestPACLLocalServiceUtil#getPortalService_TestHasClassName=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalService_TestHasClassName();
			}

		};
		%>

	TestPACLLocalServiceUtil#getPortalServiceUtil_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalServiceUtil_GetBuildNumber();
			}

		};
		%>

	TestPACLLocalServiceUtil#getPortalServiceUtil_TestGetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalServiceUtil_TestGetBuildNumber();
			}

		};
		%>

	TestPACLLocalServiceUtil#getPortalServiceUtil_TestHasClassName=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getPortalServiceUtil_TestHasClassName();
			}

		};
		%>

</p>

<p>
	<h4>com.liferay.portal.service.impl.UserLocalServiceImpl</h4>
</p>

<p>
	TestPACLLocalServiceUtil#getUserPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getUserPersistence_FindByPrimaryKey(themeDisplay.getUserId());
			}

		};
		%>

	TestPACLLocalServiceUtil#getUserUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getUserUtil_FindByPrimaryKey(themeDisplay.getUserId());
			}

		};
		%>

	UserLocalServiceUtil#getUser=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			}

		};
		%>

</p>

<h3>Services: Test PACL Portlet</h3>

<p>
	<h4>com.liferay.testpacl.service.impl.TestPACLLocalServiceImpl</h4>
</p>

<p>
	TestPACLLocalServiceUtil#getReleaseInfo_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				TestPACLLocalServiceUtil.getReleaseInfo_GetBuildNumber();
			}

		};
		%>

</p>

<h3>Sockets</h3>

<p>
	<h4>Connect</h4>
</p>

<p>
	www.abc.com:80=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				HttpUtil.URLtoString("http://www.abc.com");
			}

		};
		%>

	www.cbs.com:80=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				HttpUtil.URLtoString("http://www.cbs.com");
			}

		};
		%>

	www.msn.com:80=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				new Socket("www.msn.com", 80);
			}

		};
		%>

	www.google.com:80=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Socket socket = new Socket("www.google.com", 80);

				socket.close();
			}

		};
		%>

	www.google.com:443=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Socket socket = new Socket("www.google.com", 443);

				socket.close();
			}

		};
		%>

	www.yahoo.com:80=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				new Socket("www.yahoo.com", 80);
			}

		};
		%>

	www.yahoo.com:443=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Socket socket = new Socket("www.yahoo.com", 443);

				socket.close();
			}

		};
		%>
</p>

<p>
	<h4>Listen</h4>
</p>

<p>
	4315=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				new ServerSocket(4315);
			}

		};
		%>

	4316=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4316);

				serverSocket.close();
			}

		};
		%>

	4317=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4317);

				serverSocket.close();
			}

		};
		%>

	4318=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4318);

				serverSocket.close();
			}

		};
		%>


	4319=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				new ServerSocket(4319);
			}

		};
		%>


	4320=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4320);

				serverSocket.close();
			}

		};
		%>


	4321=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4321);

				serverSocket.close();
			}

		};
		%>

</p>

<h3>Threads</h3>

<p>
	<h4>Current Thread</h4>
</p>

<p>

	<%
	Map<String, Boolean> results = TestPACLUtil.testCurrentThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(results.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(results.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h4>Message Bus Thread</h4>
</p>

<p>

	<%
	results = TestPACLUtil.testMessageBusThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(results.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(results.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h4>New Thread</h4>
</p>

<p>

	<%
	results = TestPACLUtil.testNewThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(results.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(results.get("UserLocalServiceUtil#getUser")) %>
</p>

<%!
private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private class SecurityExceptionTest {

	public SecurityExceptionTest(Writer writer, ThemeDisplay themeDisplay, boolean expectSecurityException) throws IOException {
		try {
			this.themeDisplay = themeDisplay;

			test();

			if (expectSecurityException) {
				writer.write("FAILED");
			}
			else {
				writer.write("PASSED");
			}
		}
		catch (SecurityException se) {
			if (expectSecurityException) {
				writer.write("PASSED");
			}
			else {
				writer.write("FAILED");
			} }
		catch (Exception e) {
			writer.write("FAILED with " + e.getMessage());
		}

		writer.write("<br />");
	}

	protected void test() throws Exception {
	}

	protected ThemeDisplay themeDisplay;

}
%>