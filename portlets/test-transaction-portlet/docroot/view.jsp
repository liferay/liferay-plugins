<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ page import="com.liferay.portal.service.PortalService" %>
<%@ page import="com.liferay.portal.service.PortalServiceUtil" %>
<%@ page import="com.liferay.testtransaction.model.Bar" %>
<%@ page import="com.liferay.testtransaction.service.BarLocalServiceUtil" %>

BarLocalServiceUtil.addBar=<%= _testAddBar() %><br />
BarLocalServiceUtil.addBarPortalRollback=<%= _testAddBarPortalRollback() %><br />
BarLocalServiceUtil.addBarPortletRollback=<%= _testAddBarPortletRollback() %><br />
<br />
PortalServiceUtil.testAddBar=<%= _testPortalAddBar() %><br />
PortalServiceUtil.testAddBarPortalRollback=<%= _testPortalAddBarPortalRollback() %><br />
PortalServiceUtil.testAddBarPortletRollback=<%= _testPortalAddBarPortletRollback() %>
<%!
private String _testAddBar() {
	String text = "Hello World";

	try {
		Bar bar = BarLocalServiceUtil.addBar(text);

		bar = BarLocalServiceUtil.getBar(bar.getBarId());

		BarLocalServiceUtil.cleanUp(bar);

		return "PASSED";
	}
	catch (Exception e) {
		e.printStackTrace();

		return "FAILED";
	}
}

private String _testAddBarPortalRollback() {
	String text = "Hello World";

	try {
		BarLocalServiceUtil.addBarPortalRollback(text);

		System.out.println("Portal failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portal failed to roll back Bar object");

				return "FAILED";
			}

			if (BarLocalServiceUtil.hasClassName()) {
				System.out.println("Portal failed to roll back ClassName object");

				return "FAILED";
			}
		}
		catch (SystemException se2) {
			se2.printStackTrace();

			return "FAILED";
		}

		return "PASSED";
	}
}

private String _testAddBarPortletRollback() {
	String text = "Hello World";

	try {
		BarLocalServiceUtil.addBarPortletRollback(text);

		System.out.println("Portlet failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portlet failed to roll back Bar object");

				return "FAILED";
			}

			if (BarLocalServiceUtil.hasClassName()) {
				System.out.println("Portlet failed to roll back ClassName object");

				return "FAILED";
			}
		}
		catch (SystemException se2) {
			se2.printStackTrace();

			return "FAILED";
		}

		return "PASSED";
	}
}

private String _testPortalAddBar() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddBar(text);

		if (!PortalServiceUtil.hasClassName()) {
			System.out.println("Class name " + PortalService.class.getName() + "is not committed.");

			return "FAILED";
		}

		PortalServiceUtil.cleanUpClassName();

		Bar bar = BarLocalServiceUtil.findBarByText(text);
		BarLocalServiceUtil.cleanUpWithoutClassName(bar);

		return "PASSED";
	}
	catch (Exception e) {
		e.printStackTrace();

		return "FAILED";
	}
}

private String _testPortalAddBarPortalRollback() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddBarPortalRollback(text);

		System.out.println("Portal failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portal failed to roll back Bar object");

				return "FAILED";
			}

			if (PortalServiceUtil.hasClassName()) {
				System.out.println("Portal failed to roll back ClassName object");

				return "FAILED";
			}
		}
		catch (SystemException se2) {
			se2.printStackTrace();

			return "FAILED";
		}

		return "PASSED";
	}
}

private String _testPortalAddBarPortletRollback() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddBarPortletRollback(text);

		System.out.println("Portlet failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portlet failed to roll back Bar object");

				return "FAILED";
			}

			if (PortalServiceUtil.hasClassName()) {
				System.out.println("Portlet failed to roll back ClassName object");

				return "FAILED";
			}
		}
		catch (SystemException se2) {
			se2.printStackTrace();

			return "FAILED";
		}

		return "PASSED";
	}
}
%>