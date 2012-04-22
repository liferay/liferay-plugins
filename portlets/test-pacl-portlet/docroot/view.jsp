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
	title="Dynamic Query"
/>

<p>
	<h3>Chat Portlet</h3>
</p>

<p>
	com.liferay.chat.model.Entry=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader("1_WAR_chatportlet");

				DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Entry.class, classLoader);
			}

		};
		%>

	com.liferay.chat.model.Status=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader("1_WAR_chatportlet");

				DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Status.class, classLoader);
			}

		};
		%>

</p>

<p>
	<h3>Portal</h3>
</p>

<p>
	com.liferay.portal.model.Group=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Group.class);
			}

		};
		%>

	com.liferay.portal.model.Role=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Role.class);
			}

		};
		%>

</p>

<p>
	<h3>Test PACL Portlet</h3>
</p>

<p>
	com.liferay.testpacl.model.Foo=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Foo.class);
			}

		};
		%>

</p>

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
	title="Hook"
/>

<p>
	<h3>Indexers</h3>
</p>

<p>
	com.liferay.portal.model.Organization=<%= _assertFalse(OrganizationIndexerPostProcessor.isInstantiated()) %><br />
	com.liferay.portal.model.User=<%= _assertTrue(UserIndexerPostProcessor.isInstantiated()) %>
</p>

<p>
	<h3>Language.properties</h3>
</p>

<p>
	en_UK=<%= _assertEquals(LanguageUtil.get(Locale.UK, "stars"), "David Beckham") %><br />
	en_US=<%= _assertEquals(LanguageUtil.get(Locale.US, "stars"), "Stars") %><br />
	es_ES=<%= _assertEquals(LanguageUtil.get(new Locale("es"), "stars"), "Estrellas") %><br />
	it_IT=<%= _assertEquals(LanguageUtil.get(Locale.ITALY, "stars"), "Stelle") %><br />
	pt_BR=<%= _assertEquals(LanguageUtil.get(new Locale("pt", "BR"), "stars"), "Ricardo Kaka") %><br />
	pt_PT=<%= _assertEquals(LanguageUtil.get(new Locale("pt", "PT"), "stars"), "Cristiano Ronaldo") %>
</p>

<p>
	<h3>portal.properties</h3>
</p>

<p>
	locales.beta=<%= _assertFalse(LanguageUtil.isBetaLocale(Locale.US)) %><br />

	<%
	String phoneNumber = PhoneNumberFormatUtil.format("123");
	%>

	phone.number.format.impl=<%= _assertTrue(phoneNumber.startsWith("(TEST")) %>
</p>

<p>
	<h3>Services</h3>
</p>

<p>
	TestBlogsEntryLocalServiceImpl#getBlogsEntriesCount=<%= _assertTrue(BlogsEntryLocalServiceUtil.getBlogsEntriesCount() < 0) %><br />
	TestBlogsStatsUserLocalServiceImpl#getBlogsStatsUsersCount=<%= _assertTrue(BlogsStatsUserLocalServiceUtil.getBlogsStatsUsersCount() >= 0) %>
</p>

<p>
	<h3>Struts Actions</h3>
</p>

<p>
	/portal/test/pacl/failure=<%= _assertFalse(FailureStrutsAction.isInstantiated()) %><br />
	/portal/test/pacl/success=<%= _assertTrue(SuccessStrutsAction.isInstantiated()) %>
</p>

<liferay-ui:header
	title="Services: Chat Portlet"
/>

<p>
	<h3>com.liferay.chat.service.impl.EntryLocalServiceImpl</h3>
</p>

<p>
	EntryLocalServiceUtil#getEntry=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				try {
					EntryLocalServiceUtil.getEntry(0);

					throw new Exception("Failed to throw NoSuchEntryException");
				}
				catch (NoSuchEntryException nsee) {
				}
			}

		};
		%>

	EntryLocalServiceUtil#getEntries=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				EntryLocalServiceUtil.getEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>

	FooLocalServiceUtil#getEntryLocalServiceUtil_GetEntry=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				try {
					FooLocalServiceUtil.getEntryLocalServiceUtil_GetEntry(0);

					throw new Exception("Failed to throw NoSuchEntryException");
				}
				catch (NoSuchEntryException nsee) {
				}
			}

		};
		%>

	FooLocalServiceUtil#getEntryLocalServiceUtil_GetEntries=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getEntryLocalServiceUtil_GetEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>

</p>

<p>
	<h3>com.liferay.chat.service.impl.StatusLocalServiceImpl</h3>
</p>

<p>
	FooLocalServiceUtil#getStatusLocalServiceUtil_GetStatus=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getStatusLocalServiceUtil_GetStatus(0);
			}

		};
		%>

	FooLocalServiceUtil#getStatusLocalServiceUtil_GetStatuses=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getStatusLocalServiceUtil_GetStatuses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>

	StatusLocalServiceUtil#getStatus=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				StatusLocalServiceUtil.getStatus(0);
			}

		};
		%>

	StatusLocalServiceUtil#getStatuses=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				StatusLocalServiceUtil.getStatuses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			}

		};
		%>

</p>

<liferay-ui:header
	title="JNDI"
/>

<p>
	<h3>Bind</h3>
</p>

<p>

	<%
	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testBind("test-pacl:Matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:Mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:Luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl:john 3:16", "John");
		}

	};
	%>

</p>

<p>
	<h3>Lookup</h3>
</p>

	<%
	new JNDISecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testLookup("java_liferay:jdbc/LiferayPool", null);
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			try {
				testLookup("test-pacl:matthew", "Matthew 1:1");
			}
			catch (ExpectedTestException ete) {
			}
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:Mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:Luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl:john 3:16", "John");
		}

	};
	%>

<p>
	<h3>Unbind</h3>
</p>

<p>

	<%
	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testUnbind("test-pacl:Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl:john 3:16");
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

	FooLocalServiceUtil#getCompanyPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getCompanyPersistence_FindByPrimaryKey(themeDisplay.getCompanyId());
			}

		};
		%>

	FooLocalServiceUtil#getCompanyUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getCompanyUtil_FindByPrimaryKey(themeDisplay.getCompanyId());
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

	FooLocalServiceUtil#getGroupPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getGroupPersistence_FindByPrimaryKey(themeDisplay.getScopeGroupId());
			}

		};
		%>

	FooLocalServiceUtil#getGroupUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getGroupUtil_FindByPrimaryKey(themeDisplay.getScopeGroupId());
			}

		};
		%>

</p>

<p>
	<h3>com.liferay.portal.service.impl.PortalServiceImpl</h3>
</p>

<p>
	FooLocalServiceUtil#getPortalService_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalService_GetBuildNumber();
			}

		};
		%>

	FooLocalServiceUtil#getPortalService_TestGetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalService_TestGetBuildNumber();
			}

		};
		%>

	FooLocalServiceUtil#getPortalService_TestHasClassName=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalService_TestHasClassName();
			}

		};
		%>

	FooLocalServiceUtil#getPortalServiceUtil_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalServiceUtil_GetBuildNumber();
			}

		};
		%>

	FooLocalServiceUtil#getPortalServiceUtil_TestGetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalServiceUtil_TestGetBuildNumber();
			}

		};
		%>

	FooLocalServiceUtil#getPortalServiceUtil_TestHasClassName=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getPortalServiceUtil_TestHasClassName();
			}

		};
		%>

</p>

<p>
	<h3>com.liferay.portal.service.impl.UserLocalServiceImpl</h3>
</p>

<p>
	FooLocalServiceUtil#getUserPersistence_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getUserPersistence_FindByPrimaryKey(themeDisplay.getUserId());
			}

		};
		%>

	FooLocalServiceUtil#getUserUtil_FindByPrimaryKey=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getUserUtil_FindByPrimaryKey(themeDisplay.getUserId());
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
	<h3>com.liferay.testpacl.service.impl.FooLocalServiceImpl</h3>
</p>

<p>
	FooLocalServiceUtil#getFoosCount=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getFoosCount();
			}

		};
		%>

	FooLocalServiceUtil#getReleaseInfo_GetBuildNumber=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.getReleaseInfo_GetBuildNumber();
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
	title="SQL"
/>

<p>
	<h3>Create</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("create table TestPACL_CreateFailure (userId bigint)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("create table TestPACL_CreateFailure (userId bigint)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testPreparedStatement("create table TestPACL_CreateSuccess (userId bigint)");

			executePreparedStatement("drop table TestPACL_CreateSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testStatement("create table TestPACL_CreateSuccess (userId bigint)");

			executeStatement("drop table TestPACL_CreateSuccess");
		}

	};
	%>

</p>

<p>
	<h3>Drop</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("drop table TestPACL_DropFailure");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("drop table TestPACL_DropFailure");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_DropSuccess (userId bigint)");

			testPreparedStatement("drop table TestPACL_DropSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executeStatement("create table TestPACL_DropSuccess (userId bigint)");

			testStatement("drop table TestPACL_DropSuccess");
		}

	};
	%>

</p>

<p>
	<h3>Insert</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("insert into TestPACL_InsertFailure values (1)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("insert into TestPACL_InsertFailure values (1)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_InsertSuccess (userId bigint)");

			testPreparedStatement("insert into TestPACL_InsertSuccess values (1)");

			executePreparedStatement("drop table TestPACL_InsertSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executeStatement("create table TestPACL_InsertSuccess (userId bigint)");

			testStatement("insert into TestPACL_InsertSuccess values (1)");

			executeStatement("drop table TestPACL_InsertSuccess");
		}

	};
	%>

</p>

<p>
	<h3>Replace</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("replace TestPACL_ReplaceFailure (userId) values (1)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("replace TestPACL_ReplaceFailure (userId) values (1)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_ReplaceSuccess (userId bigint)");

			testPreparedStatement("replace TestPACL_ReplaceSuccess (userId) values (1)");

			executePreparedStatement("drop table TestPACL_ReplaceSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executeStatement("create table TestPACL_ReplaceSuccess (userId bigint)");

			testStatement("replace TestPACL_ReplaceSuccess (userId) values (1)");

			executeStatement("drop table TestPACL_ReplaceSuccess");
		}

	};
	%>

</p>

<p>
	<h3>Select</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testPreparedStatement("select * from Counter");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testStatement("select * from Counter");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("select * from Counter inner join User_ on User_.userId = Counter.currentId");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("select * from Counter inner join User_ on User_.userId = Counter.currentId");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			try {
				testPreparedStatement("select * from TestPACL_Bar");

				throw new Exception("Failed to throw SQLException");
			}
			catch (SQLException se) {
			}
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			try {
				testStatement("select * from TestPACL_Bar");

				throw new Exception("Failed to throw SQLException");
			}
			catch (SQLException se) {
			}
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("select * from User_");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("select * from User_");
		}

	};
	%>

</p>

<p>
	<h3>Truncate</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("truncate table TestPACL_TruncateFailure");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("truncate table TestPACL_TruncateFailure");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_TruncateSuccess (userId bigint)");

			testPreparedStatement("truncate table TestPACL_TruncateSuccess");

			executePreparedStatement("drop table TestPACL_TruncateSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executeStatement("create table TestPACL_TruncateSuccess (userId bigint)");

			testStatement("truncate table TestPACL_TruncateSuccess");

			executePreparedStatement("drop table TestPACL_TruncateSuccess");
		}

	};
	%>

</p>

<p>
	<h3>Update</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testDB("update ListType set name = 'Test PACL' where listTypeId = -123");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testPreparedStatement("update ListType set name = 'Test PACL' where listTypeId = -123");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testStatement("update ListType set name = 'Test PACL' where listTypeId = -123");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testDB("update ListType set name = 'Test PACL' where listTypeId = (select userId from User_)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("update ListType set name = 'Test PACL' where listTypeId = (select userId from User_)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("update ListType set name = 'Test PACL' where listTypeId = (select userId from User_)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testDB("update User_ set firstName = 'Test PACL' where userId = -123");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("update User_ set firstName = 'Test PACL' where userId = -123");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("update User_ set firstName = 'Test PACL' where userId = -123");
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
private static String _assertEquals(Object expected, Object actual) {
	return _assertTrue(Validator.equals(expected, actual));
}

private static String _assertFalse(boolean value) {
	return _assertTrue(!value);
}

private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private class ExpectedTestException extends Exception {
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

private class JNDISecurityExceptionTest extends SecurityExceptionTest {

	public JNDISecurityExceptionTest(Writer writer, ThemeDisplay themeDisplay, boolean expectSecurityException) throws IOException {
		super(writer, themeDisplay, expectSecurityException);
	}

	protected void bind(String name, Object object) throws Exception {
		Context context = new InitialContext();

		context.bind(name, object);
	}

	protected Object lookup(String name) throws Exception {
		Context context = new InitialContext();

		return context.lookup(name);
	}

	protected void unbind(String name) throws Exception {
		Context context = new InitialContext();

		context.unbind(name);
	}

	protected void testBind(String name, Object object) throws Exception {
		writer.write(name);
		writer.write("=");

		bind(name, object);
	}

	protected void testLookup(String name, String expectedObject) throws Exception {
		writer.write(name);
		writer.write("=");

		Object actualObject = lookup(name);

		if (!expectedObject.equals(actualObject)) {
			throw new ExpectedTestException();
		}
	}

	protected void testUnbind(String name) throws Exception {
		writer.write(name);
		writer.write("=");

		unbind(name);
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

private class SQLSecurityExceptionTest extends SecurityExceptionTest {

	public SQLSecurityExceptionTest(Writer writer, ThemeDisplay themeDisplay, boolean expectSecurityException) throws IOException {
		super(writer, themeDisplay, expectSecurityException);
	}

	protected void executeDB(String sql) throws Exception {
		DB db = DBFactoryUtil.getDB();

		db.runSQL(sql);
	}

	protected void executePreparedStatement(String sql) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DataAccess.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.execute();
		}
		finally {
			DataAccess.cleanUp(connection, preparedStatement);
		}
	}

	protected void executeStatement(String sql) throws Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DataAccess.getConnection();

			statement = connection.createStatement();

			statement.execute(sql);
		}
		finally {
			DataAccess.cleanUp(connection, statement);
		}

	}

	protected void testDB(String sql) throws Exception {
		writer.write(sql);
		writer.write("=");

		executeDB(sql);
	}

	protected void testPreparedStatement(String sql) throws Exception {
		writer.write(sql);
		writer.write("=");

		executePreparedStatement(sql);
	}

	protected void testStatement(String sql) throws Exception {
		writer.write(sql);
		writer.write("=");

		executeStatement(sql);
	}

}
%>