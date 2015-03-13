<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
	title="Bean Property"
/>

<p>
	<h3>Get</h3>
</p>

<p>
	JournalContentUtil#getJournalContent=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				JournalContentUtil.getJournalContent();
			}

		};
		%>

	LanguageUtil#getLanguage=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				LanguageUtil.getLanguage();
			}

		};
		%>

</p>

<p>
	<h3>Set</h3>
</p>

<p>
	EntityCacheUtil#setEntityCache=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				EntityCacheUtil entityCacheUtil = new EntityCacheUtil();

				EntityCache entityCache = EntityCacheUtil.getEntityCache();

				entityCacheUtil.setEntityCache(entityCache);
			}

		};
		%>

	FinderCacheUtil#setFinderCache=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FinderCacheUtil finderCacheUtil = new FinderCacheUtil();

				FinderCache finderCache = FinderCacheUtil.getFinderCache();

				finderCacheUtil.setFinderCache(finderCache);
			}

		};
		%>

	PortalCustomSQLUtil#setPortalCustomSQL=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				PortalCustomSQLUtil portalCustomSQLUtil = new PortalCustomSQLUtil();

				PortalCustomSQL portalCustomSQL = PortalCustomSQLUtil.getPortalCustomSQL();

				portalCustomSQLUtil.setPortalCustomSQL(portalCustomSQL);
			}

		};
		%>

</p>

<liferay-ui:header
	title="Class Loader"
/>

<p>
	<h3>Get</h3>
</p>

<p>
	com.liferay.chat.model.EntryClp#toEscapedModel=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				long entryId = System.currentTimeMillis();

				Entry newEntry = EntryLocalServiceUtil.createEntry(entryId);

				newEntry.toEscapedModel();
			}

		};
		%>

	com.liferay.chat.model.EntryClp.class#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				EntryClp.class.getClassLoader();
			}

		};
		%>

	com.liferay.chat.service.EntryLocalService#getClass#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				EntryLocalService entryLocalService = EntryLocalServiceUtil.getService();

				Class<?> clazz = entryLocalService.getClass();

				clazz.getClassLoader();
			}

		};
		%>

	com.liferay.portal.kernel.portlet.PortletClassLoaderUtil#getClassLoader("1_WAR_chatportlet")=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				PortletClassLoaderUtil.getClassLoader("1_WAR_chatportlet");
			}

		};
		%>

	com.liferay.portal.kernel.portlet.PortletClassLoaderUtil#getClassLoader("1_WAR_flashportlet")=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				PortletClassLoaderUtil.getClassLoader("1_WAR_flashportlet");
			}

		};
		%>

	com.liferay.portal.kernel.portlet.PortletClassLoaderUtil#getClassLoader("chat-portlet")=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				PortletClassLoaderUtil.getClassLoader("chat-portlet");
			}

		};
		%>

	com.liferay.portal.kernel.portlet.PortletClassLoaderUtil#getClassLoader("flash-portlet")=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				PortletClassLoaderUtil.getClassLoader("flash-portlet");
			}

		};
		%>

	com.liferay.portal.kernel.util.PortalClassLoaderUtil#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				PortalClassLoaderUtil.getClassLoader();
			}

		};
		%>

	com.liferay.portal.util.Portal#getClass#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Portal portal = PortalUtil.getPortal();

				Class<?> clazz = portal.getClass();

				clazz.getClassLoader();
			}

		};
		%>

	com.liferay.portlet.blogs.service.BlogsEntryLocalService#getClass#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				BlogsEntryLocalService blogsEntryLocalService = BlogsEntryLocalServiceUtil.getService();

				Class<?> clazz = blogsEntryLocalService.getClass();

				clazz.getClassLoader();
			}

		};
		%>

	com.liferay.testpacl.service.FooLocalService#getClass#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				FooLocalService fooLocalService = FooLocalServiceUtil.getService();

				Class<?> clazz = fooLocalService.getClass();

				clazz.getClassLoader();
			}

		};
		%>

	com.liferay.testpacl.util.TestPACLUtil.class#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				TestPACLUtil.class.getClassLoader();
			}

		};
		%>

	java.lang.ClassLoader#getSystemClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				ClassLoader.getSystemClassLoader();
			}

		};
		%>

	java.lang.Class#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Class<?> clazz = getClass();

				clazz.getClassLoader();
			}

		};
		%>

	java.lang.Object.class#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Object.class.getClassLoader();
			}

		};
		%>

	java.lang.Object#getClass#getClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Object object = new Object();

				Class<?> clazz = object.getClass();

				clazz.getClassLoader();
			}

		};
		%>

	java.lang.Thread#getContextClassLoader=

		<%
		if (ServerDetector.isWebLogic()) {

			// In WebLogic, the context classloader is always the JSP class
			// loader. Therefore, there is no security check.

			out.print("PASSED");
		}
		else {
			new SecurityExceptionTest(out, themeDisplay, true) {

				protected void test() throws Exception {
					Thread thread = Thread.currentThread();

					thread.getContextClassLoader();
				}

			};
		}
		%>

</p>

<p>
	<h3>Set</h3>
</p>

<p>
	java.net.URLClassLoader=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				new URLClassLoader(new URL[0], ClassLoader.getSystemClassLoader());
			}

		};
		%>

</p>

<liferay-ui:header
	title="Dynamic Query"
/>

<p>
	<h3>Chat Portlet</h3>
</p>

<p>
	EntryLocalServiceUtil#dynamicQuery=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				EntryLocalServiceUtil.dynamicQuery();
			}

		};
		%>

	StatusLocalServiceUtil#dynamicQuery=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				StatusLocalServiceUtil.dynamicQuery();
			}

		};
		%>

</p>

<p>
	<h3>Portal</h3>
</p>

<p>
	DynamicQueryFactoryUtil#forClass(Group.class)=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				DynamicQueryFactoryUtil.forClass(Group.class);
			}

		};
		%>

	DynamicQueryFactoryUtil#forClass(Role.class)=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				DynamicQueryFactoryUtil.forClass(Role.class);
			}

		};
		%>

	GroupLocalServiceUtil#dynamicQuery=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				GroupLocalServiceUtil.dynamicQuery();
			}

		};
		%>

</p>

<p>
	<h3>Test PACL Portlet</h3>
</p>

<p>
	DynamicQueryFactoryUtil#forClass(Foo.class)=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				DynamicQueryFactoryUtil.forClass(Foo.class);
			}

		};
		%>

	FooLocalServiceUtil#dynamicQuery=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				FooLocalServiceUtil.dynamicQuery();
			}

		};
		%>

</p>

<liferay-ui:header
	title="Environment Variables"
/>

<p>
	JAVA_HOME= (<%= System.getenv("JAVA_HOME") %>)

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				System.getenv("JAVA_HOME");
			}

		};
		%>

	PATH=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				System.getenv("PATH");
			}

		};
		%>

</p>

<liferay-ui:header
	title="Expando Bridge"
/>

<p>
	Group#getExpandoBridge=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Group group = themeDisplay.getScopeGroup();

				group.getExpandoBridge();
			}

		};
		%>

	Group#setExpandoBridgeAttributes=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Group group = themeDisplay.getScopeGroup();

				ServiceContext serviceContext = new ServiceContext();

				group.setExpandoBridgeAttributes(serviceContext);
			}

		};
		%>

	GroupWrapper#getExpandoBridge=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Group group = themeDisplay.getScopeGroup();

				group = new GroupWrapper(group);

				group.getExpandoBridge();
			}

		};
		%>

	GroupWrapper#setExpandoBridgeAttributes=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Group group = themeDisplay.getScopeGroup();

				group = new GroupWrapper(group);

				ServiceContext serviceContext = new ServiceContext();

				group.setExpandoBridgeAttributes(serviceContext);
			}

		};
		%>

	User#getExpandoBridge=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				User user = themeDisplay.getUser();

				user.getExpandoBridge();
			}

		};
		%>

	User#setExpandoBridgeAttributes=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				User user = themeDisplay.getUser();

				ServiceContext serviceContext = new ServiceContext();

				user.setExpandoBridgeAttributes(serviceContext);
			}

		};
		%>

	UserWrapper#getExpandoBridge=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				User user = themeDisplay.getUser();

				user = new UserWrapper(user);

				user.getExpandoBridge();
			}

		};
		%>

	UserWrapper#setExpandoBridgeAttributes=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				User user = themeDisplay.getUser();

				user = new UserWrapper(user);

				ServiceContext serviceContext = new ServiceContext();

				user.setExpandoBridgeAttributes(serviceContext);
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
			testDelete("../webapps/chat-portlet/WEB-INF/liferay-releng.properties");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testDelete("../webapps/chat-portlet/WEB-INF/src/com/liferay/chat/util/ChatConstants.java");
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

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testReadWithFile(System.getenv("JAVA_HOME"));
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testReadWithFile(System.getenv("JAVA_HOME") + "/bin");
		}

	};

	new FileSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			String javaCommand = "java";

			if (OSDetector.isWindows()) {
				javaCommand = "java.exe";
			}

			testReadWithFile(System.getenv("JAVA_HOME") + "/bin/" + javaCommand);
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
	Organization=<%= _assertFalse(OrganizationIndexerPostProcessor.isInstantiated()) %><br />
	User=<%= _assertTrue(UserIndexerPostProcessor.isInstantiated()) %>
</p>

<p>
	<h3>Language.properties</h3>
</p>

<p>
	en_UK=<%= _assertEquals(LanguageUtil.get(LocaleUtil.UK, "stars"), "David Beckham") %><br />
	en_US=<%= _assertEquals(LanguageUtil.get(LocaleUtil.US, "stars"), "Stars") %><br />
	es_ES=<%= _assertEquals(LanguageUtil.get(LocaleUtil.SPAIN, "stars"), "Estrellas") %><br />
	it_IT=<%= _assertEquals(LanguageUtil.get(LocaleUtil.ITALY, "stars"), "Stelle") %><br />
	pt_BR=<%= _assertEquals(LanguageUtil.get(LocaleUtil.BRAZIL, "stars"), "Ricardo Kaka") %><br />
	pt_PT=<%= _assertEquals(LanguageUtil.get(LocaleUtil.PORTUGAL, "stars"), "Cristiano Ronaldo") %>
</p>

<p>
	<h3>portal.properties</h3>
</p>

<p>
	locales.beta=<%= _assertFalse(LanguageUtil.isBetaLocale(LocaleUtil.US)) %><br />

	<%
	String phoneNumber = PhoneNumberFormatUtil.format("123");
	%>

	phone.number.format.impl=<%= _assertTrue(phoneNumber.startsWith("(TEST")) %>
</p>

<p>
	<h3>Services</h3>
</p>

<p>
	BlogsEntryLocalService#getBlogsEntriesCount=<%= _assertTrue(BlogsEntryLocalServiceUtil.getBlogsEntriesCount() < 0) %><br />
	BlogsStatsUserLocalService#getBlogsStatsUsersCount=<%= _assertTrue(BlogsStatsUserLocalServiceUtil.getBlogsStatsUsersCount() >= 0) %>
</p>

<p>
	<h3>Struts Actions</h3>
</p>

<p>
	/portal/test/pacl/failure=<%= _assertFalse(FailureStrutsAction.isInstantiated()) %><br />
	/portal/test/pacl/success=<%= _assertTrue(SuccessStrutsAction.isInstantiated()) %>
</p>

<liferay-ui:header
	title="Java Security"
/>

<p>
	<h3>Crypto</h3>
</p>

<p>
	AES Encrypt=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

				keyGenerator.init(128);

				SecretKey secretKey = keyGenerator.generateKey();

				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, secretKey);

				String text = "Hello World";

				cipher.doFinal(text.getBytes());
			}
		};
		%>

	HmacMD5=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Mac mac = Mac.getInstance("HmacMD5");

				String key = "123456789";

				SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacMD5");

				mac.init(secretKeySpec);

				String text = "Hello World";

				mac.doFinal(text.getBytes());
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
			testBind("test-pacl-matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testBind("test-pacl-Matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl-mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl-Mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl-luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl-Luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testBind("test-pacl-john 3:16", "John");
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
			testLookup("test-pacl-matthew", "Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			try {
				testLookup("test-pacl-matthew", "Matthew 1:1");
			}
			catch (ExpectedTestException ete) {
			}
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl-mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl-Mark", "Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl-luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl-Luke", "Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testLookup("test-pacl-john 3:16", "John");
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
			testUnbind("test-pacl-matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testUnbind("test-pacl-Matthew");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl-mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl-Mark");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl-luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl-Luke");
		}

	};

	new JNDISecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			testUnbind("test-pacl-john 3:16");
		}

	};
	%>

</p>

<liferay-ui:header
	title="Message Bus"
/>

<p>
	<h3>Listen</h3>
</p>

<p>
	liferay/test_pacl_listen_failure=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Object value = MessageBusUtil.sendSynchronousMessage("liferay/test_pacl_listen_failure", "Listen Failure");

				if (value != null) {
					throw new Exception("Message bus destination is not protected");
				}
			}

		};
		%>

	liferay/test_pacl_listen_success=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Object value = MessageBusUtil.sendSynchronousMessage("liferay/test_pacl_listen_success", "Listen Success");

				if ((value == null) || !value.equals("Listen Success")) {
					throw new Exception("Message bus destination is not registered");
				}
			}

		};
		%>

</p>

<p>
	<h3>Send</h3>
</p>

<p>
	liferay/test_pacl_send_failure=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				MessageBusUtil.sendMessage("liferay/test_pacl_send_failure", "Send Failure");
			}

		};
		%>

	liferay/test_pacl_send_success=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				MessageBusUtil.sendMessage("liferay/test_pacl_send_success", "Send Success");
			}

		};
		%>

</p>

<liferay-ui:header
	title="Reflection"
/>

<p>
	<h3>Portal</h3>
</p>

<p>

	PropsKeys.class#ADMIN_DEFAULT_GROUP_NAMES=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Class<?> clazz = PropsKeys.class;

				clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");
			}

		};
		%>

	PropsKeys.class#ADMIN_DEFAULT_GROUP_NAMES#setAccessible(false)=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Class<?> clazz = PropsKeys.class;

				Field field = clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");

				field.setAccessible(false);
			}

		};
		%>

	UserLocalServiceUtil.class#_service=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Class<?> clazz = UserLocalServiceUtil.class;

				clazz.getDeclaredField("_service");
			}

		};
		%>

</p>

<p>
	<h3>Test PACL Portlet</h3>
</p>

<p>
	ObjectMapper=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				new ObjectMapper();
			}

		};
		%>

	TestPACLUtil.class#TEST_FIELD=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Class<?> clazz = TestPACLUtil.class;

				clazz.getField("TEST_FIELD");
			}

		};
		%>

	TestPACLUtil.class#TEST_FIELD#setAccessible(false)=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				Class<?> clazz = TestPACLUtil.class;

				Field field = clazz.getField("TEST_FIELD");

				field.setAccessible(false);
			}

		};
		%>

	TestPACLUtil.class#_log=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				Class<?> clazz = TestPACLUtil.class;

				clazz.getDeclaredField("_log");
			}

		};
		%>

</p>

<liferay-ui:header
	title="Search Container"
/>

<p>

	<%
	List<Foo> foos = new ArrayList<Foo>();

	foos.add(new FooImpl(1, "Class Loader"));
	foos.add(new FooImpl(2, "Reflection"));
	%>

	<liferay-util:buffer var="searchContainerHTML">
		<liferay-ui:search-container
			headerNames="Check,Result"
			total="<%= 1 %>"
		>
			<liferay-ui:search-container-results
				results="<%= foos %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.testpacl.model.Foo"
				keyProperty="fooId"
				modelVar="foo"
			>

				<liferay-ui:search-container-column-text
					name="Check"
					value="<%= foo.getField1() %>"
				/>

				<liferay-ui:search-container-column-text
					name="Result"
					value="PASSED"
				/>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-util:buffer>

	<%
	if (searchContainerHTML.replaceAll("\\s*", "").isEmpty()) {
		out.write("FAILED");
	}
	else {
		out.write(searchContainerHTML);
	}
	%>

</p>

<liferay-ui:header
	title="Search Engine"
/>

<p>
	GENERIC_ENGINE=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				SearchEngineUtil.getSearchEngine("GENERIC_ENGINE");
			}

		};
		%>

	SYSTEM_ENGINE=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				SearchEngineUtil.getSearchEngine("SYSTEM_ENGINE");
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

	EntryLocalServiceUtil#updateEntry=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				long entryId = System.currentTimeMillis();

				Entry newEntry = EntryLocalServiceUtil.createEntry(entryId);

				String content = PwdGenerator.getPassword();

				newEntry.setContent(content);

				EntryLocalServiceUtil.updateEntry(newEntry);

				Entry existingEntry = EntryLocalServiceUtil.getEntry(entryId);

				if (!Validator.equals(entryId, existingEntry.getEntryId()) ||
					!Validator.equals(content, existingEntry.getContent())) {

					throw new Exception("Expected identical entry");
				}
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
	title="Services: Portal"
/>

<p>
	<h3>com.liferay.portal.service.impl.CompanyLocalServiceImpl</h3>
</p>

<p>
	CompanyLocalServiceUtil#getCompanyByWebId=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
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
	title="Services: Sample Service Builder Portlet"
/>

<p>
	<h3>com.liferay.sampleservicebuilder.service.impl.FooLocalServiceImpl</h3>
</p>

<p>
	com.liferay.sampleservicebuilder.service.FooLocalServiceUtil.FooLocalServiceUtil#getFoosCount=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				com.liferay.sampleservicebuilder.service.FooLocalServiceUtil.getFoosCount();
			}

		};
		%>

	com.liferay.sampleservicebuilder.service.FooLocalServiceUtil.FooLocalServiceUtil#getLocalObject=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				com.liferay.sampleservicebuilder.service.FooLocalServiceUtil.getLocalObject();
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
	<h3>Accept</h3>
</p>

<p>
	localhost:4320=

		<%
		new SecurityExceptionTest(out, themeDisplay, false) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4316);

				serverSocket.setSoTimeout(1000);

				try {
					Runnable runnable = new Runnable() {

						public void run() {
							Socket socket = new Socket();

							try {
								socket.setSoLinger(true, 0);

								socket.bind(new InetSocketAddress("localhost", 4320));

								socket.connect(new InetSocketAddress("localhost", 4316), 500);
							}
							catch (Exception e) {
								throw new RuntimeException(e);
							}
							finally {
								try {
									socket.close();
								}
								catch (Exception e) {
									throw new RuntimeException(e);
								}
							}
						}

					};

					Thread thread = new Thread(runnable);

					thread.start();

					Socket socket = serverSocket.accept();

					socket.close();
				}
				finally {
					serverSocket.close();
				}
			}

		};
		%>

	localhost:4321=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				ServerSocket serverSocket = new ServerSocket(4316);

				serverSocket.setSoTimeout(1000);

				try {
					Runnable runnable = new Runnable() {

						public void run() {
							Socket socket = new Socket();

							try {
								socket.setSoLinger(true, 0);

								socket.bind(new InetSocketAddress("localhost", 4321));

								socket.connect(new InetSocketAddress("localhost", 4316), 500);
							}
							catch (Exception e) {
								throw new RuntimeException(e);
							}
							finally {
								try {
									socket.close();
								}
								catch (Exception e) {
									throw new RuntimeException(e);
								}
							}
						}

					};

					Thread thread = new Thread(runnable);

					thread.start();

					Socket socket = serverSocket.accept();

					socket.close();
				}
				finally {
					serverSocket.close();
				}
			}

		};
		%>

</p>

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
	<h3>Index</h3>
</p>

<p>

	<%
	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testPreparedStatement("create index index1 ON TestPACL_CreateFailure (userId)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, true) {

		protected void test() throws Exception {
			testStatement("create index index1 ON TestPACL_CreateFailure (userId)");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_CreateSuccess (userId bigint)");

			testPreparedStatement("create index index1 ON TestPACL_CreateSuccess (userId)");

			executePreparedStatement("drop table TestPACL_CreateSuccess");
		}

	};

	new SQLSecurityExceptionTest(out, themeDisplay, false) {

		protected void test() throws Exception {
			executePreparedStatement("create table TestPACL_CreateSuccess (userId bigint)");

			testStatement("create index index1 ON TestPACL_CreateSuccess (userId)");

			executePreparedStatement("drop table TestPACL_CreateSuccess");
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

<%
DB db = DBFactoryUtil.getDB();

String dbType = db.getType();
%>

<c:if test="<%= dbType.equals(DB.TYPE_MYSQL) %>">
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
</c:if>

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

<c:if test="<%= dbType.equals(DB.TYPE_MYSQL) %>">
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
</c:if>

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
	Map<String, Boolean> testPACLUtilResults = TestPACLUtil.testCurrentThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(testPACLUtilResults.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(testPACLUtilResults.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h3>Message Bus Thread</h3>
</p>

<p>

	<%
	testPACLUtilResults = TestPACLUtil.testMessageBusThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(testPACLUtilResults.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(testPACLUtilResults.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h3>New Thread</h3>
</p>

<p>

	<%
	testPACLUtilResults = TestPACLUtil.testNewThread(themeDisplay.getUserId());
	%>

	PortalServiceUtil#getBuildNumber=<%= _assertTrue(testPACLUtilResults.get("PortalServiceUtil#getBuildNumber")) %><br />
	UserLocalServiceUtil#getUser=<%= _assertTrue(testPACLUtilResults.get("UserLocalServiceUtil#getUser")) %>
</p>

<p>
	<h3>Portal Executor Manager</h3>
</p>

<p>
	PortalExecutorManagerUtil.shutdown("liferay/hot_deploy")=

		<%
		new SecurityExceptionTest(out, themeDisplay, true) {

			protected void test() throws Exception {
				ThreadPoolExecutor threadPoolExecutor = PortalExecutorManagerUtil.getPortalExecutor("liferay/hot_deploy");

				threadPoolExecutor.shutdown();
			}

		};
		%>

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

	protected void testDelete(String fileName) throws Exception {
		fileName = TestPACLUtil.translateFileName(fileName);

		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		PortalFilePermission.checkDelete(file.getPath());
	}

	protected void testExecute(String cmd) throws Exception {
		writer.write(cmd);
		writer.write("=");

		Runtime runtime = Runtime.getRuntime();

		runtime.exec(cmd);
	}

	protected void testReadWithFile(String fileName) throws Exception {
		fileName = TestPACLUtil.translateFileName(fileName);

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
		fileName = TestPACLUtil.translateFileName(fileName);

		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		PortalFilePermission.checkRead(file.getPath());
	}

	protected void testWriteWithFile(String fileName) throws Exception {
		fileName = TestPACLUtil.translateFileName(fileName);

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
		fileName = TestPACLUtil.translateFileName(fileName);

		writer.write(fileName);
		writer.write("=");

		File file = new File(fileName);

		PortalFilePermission.checkWrite(file.getPath());
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
			e.printStackTrace();

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