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

<liferay-ui:header
	title="Files"
/>

<p>
	<h3>Delete</h3>
</p>

<p>

	<%
	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testDeleteWithFile("../webapps/chat-portlet/WEB-INF/liferay-releng.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testDeleteWithFileUtil("../webapps/chat-portlet/WEB-INF/liferay-releng.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testDeleteWithFile("../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testDeleteWithFileUtil("../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatUtil.java");
		}

	};
	%>

</p>

<p>
	<h3>Execute</h3>
</p>

<p>

	<%
	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testExecute("../webapps/chat-portlet/images/saved.png");
		}

	};

	if (OSDetector.isWindows()) {
		new FileSecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				testExecute("ping.exe");
			}

		};

		new FileSecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				testExecute("C:\\WINDOWS\\system32\\ping.exe");
			}

		};

		new FileSecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				testExecute("C:\\WINDOWS\\system32\\whoami.exe");
			}

		};
	}
	else {
		new FileSecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				testExecute("bash");
			}

		};

		new FileSecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				testExecute("/bin/bash");
			}

		};

		new FileSecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				testExecute("/bin/cat");
			}

		};
	}
	%>

</p>


<p>
	<h3>Read</h3>
</p>

<p>

	<%
	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testReadWithFile("../webapps/chat-portlet/css/main.css");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testReadWithFileUtil("../webapps/chat-portlet/css/main.css");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testReadWithFile("../webapps/chat-portlet/images/saved.png");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testReadWithFileUtil("../webapps/chat-portlet/images/saved.png");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testReadWithFile("../webapps/chat-portlet/WEB-INF/web.xml");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testReadWithFileUtil("../webapps/chat-portlet/WEB-INF/web.xml");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testReadWithFile("../webapps/chat-portlet/WEB-INF/src/content/Language.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testReadWithFileUtil("../webapps/chat-portlet/WEB-INF/src/content/Language.properties");
		}

	};
	%>

</p>

<p>
	<h3>Write</h3>
</p>

<p>

	<%
	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testWriteWithFile("../webapps/chat-portlet/css/main.css");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testWriteWithFileUtil("../webapps/chat-portlet/css/main.css");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testWriteWithFile("../webapps/chat-portlet/images/saved.png");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testWriteWithFileUtil("../webapps/chat-portlet/images/saved.png");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testWriteWithFile("../webapps/chat-portlet/WEB-INF/liferay-releng.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testWriteWithFileUtil("../webapps/chat-portlet/WEB-INF/liferay-releng.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testWriteWithFile("../webapps/chat-portlet/WEB-INF/src/content/Language.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testWriteWithFileUtil("../webapps/chat-portlet/WEB-INF/src/content/Language.properties");
		}

	};
	%>

</p>

<liferay-ui:header
	title="Services: Chat Portlet"
/>

<p>
	<h3>com.liferay.chat.service.impl.EntryLocalServiceImpl</h3>
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
	<h3>com.liferay.chat.service.impl.StatusLocalServiceImpl</h3>
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

<liferay-ui:header
	title="Services: Portal"
/>

<p>
	<h3>com.liferay.portal.service.impl.CompanyLocalServiceImpl</h3>
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
	<h3>com.liferay.portal.service.impl.GroupLocalServiceImpl</h3>
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
	<h3>com.liferay.portal.service.impl.PortalServiceImpl</h3>
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
	<h3>com.liferay.portal.service.impl.UserLocalServiceImpl</h3>
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

<liferay-ui:header
	title="Services: Test PACL Portlet"
/>

<p>
	<h3>com.liferay.testpacl.service.impl.TestPACLLocalServiceImpl</h3>
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

<liferay-ui:header
	title="Sockets"
/>

<p>
	<h3>Connect</h3>
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
	<h3>Listen</h3>
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

<liferay-ui:header
	title="Threads"
/>

<p>
	<h3>Current Thread</h3>
</p>

<p>

	<%
	Map<String, Boolean> results = TestPACLUtil.testCurrentThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(results.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(results.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h3>Message Bus Thread</h3>
</p>

<p>

	<%
	results = TestPACLUtil.testMessageBusThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(results.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(results.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h3>New Thread</h3>
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

private class FileSecurityExceptionTest extends SecurityExceptionTest {

	public FileSecurityExceptionTest(Writer writer, ThemeDisplay themeDisplay, boolean expectSecurityException) throws IOException {
		super(writer, themeDisplay, expectSecurityException);
	}

	protected void testDeleteWithFile(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		byte[] bytes = null;

		try {
			bytes = FileUtil.getBytes(file);
		}
		catch (SecurityException se) {
			throw new Exception(se.getMessage(), se);
		}

		if (file.delete()) {
			try {
				FileUtil.write(file, bytes);
			}
			catch (SecurityException se) {
				throw new Exception(se.getMessage(), se);
			}

			if (expectSecurityException) {
				throw new SecurityException();
			}
		}
		else {
			if (!expectSecurityException) {
				throw new SecurityException();
			}
		}
	}

	protected void testDeleteWithFileUtil(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		byte[] bytes = null;

		try {
			bytes = FileUtil.getBytes(file);
		}
		catch (SecurityException se) {
			throw new Exception(se.getMessage(), se);
		}

		if (FileUtil.delete(file)) {
			try {
				FileUtil.write(file, bytes);
			}
			catch (SecurityException se) {
				throw new Exception(se.getMessage(), se);
			}

			if (expectSecurityException) {
				throw new SecurityException();
			}
		}
		else {
			if (!expectSecurityException) {
				throw new SecurityException();
			}
		}
	}

	protected void testExecute(String cmd) throws Exception {
		writer.write(cmd);
		writer.write("=");

		Runtime runtime = Runtime.getRuntime();

		runtime.exec(cmd);
	}

	protected void testReadWithFile(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		if (expectSecurityException && file.canRead()) {
			throw new SecurityException();
		}
		else if (!expectSecurityException && !file.canRead()) {
			throw new SecurityException();
		}
	}

	protected void testReadWithFileUtil(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		FileUtil.read(fileName);
	}

	protected void testWriteWithFile(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		if (expectSecurityException && file.canWrite()) {
			throw new SecurityException();
		}
		else if (!expectSecurityException && !file.canWrite()) {
			throw new SecurityException();
		}
	}

	protected void testWriteWithFileUtil(String fileName) throws Exception {
		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		byte[] bytes = FileUtil.getBytes(file);

		FileUtil.write(file, bytes);
	}

}

private class SecurityExceptionTest {

	public SecurityExceptionTest(Writer writer, ThemeDisplay themeDisplay, boolean expectSecurityException) throws IOException {
		try {
			this.writer = writer;
			this.themeDisplay = themeDisplay;
			this.expectSecurityException = expectSecurityException;

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
			}
		}
		catch (Exception e) {
			writer.write("FAILED with " + e.getMessage());
		}

		writer.write("<br />");
	}

	protected void test() throws Exception {
	}

	protected boolean expectSecurityException;
	protected ThemeDisplay themeDisplay;
	protected Writer writer;

}
%>