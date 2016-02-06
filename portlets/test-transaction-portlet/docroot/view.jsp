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

<%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ page import="com.liferay.portal.kernel.service.PortalService" %>
<%@ page import="com.liferay.portal.kernel.service.PortalServiceUtil" %>
<%@ page import="com.liferay.testtransaction.model.Bar" %>
<%@ page import="com.liferay.testtransaction.service.BarLocalServiceUtil" %>

<p>
	BarLocalServiceUtil#addBar_Success=<%= _testAddBar_Success() %><br />
	BarLocalServiceUtil#addBarAndClassName_PortalRollback=<%= _testAddBarAndClassName_PortalRollback() %><br />
	BarLocalServiceUtil#addBarAndClassName_PortletRollback=<%= _testAddBarAndClassName_PortletRollback() %>
</p>

<p>
	PortalServiceUtil#testAddClassNameAndTestTransactionPortletBar_Success=<%= _testAddClassNameAndTestTransactionPortletBar_Success() %><br />
	PortalServiceUtil#testAddClassNameAndTestTransactionPortletBar_PortalRollback=<%= _testAddClassNameAndTestTransactionPortletBar_PortalRollback() %><br />
	PortletServiceUtil#testAddClassNameAndTestTransactionPortletBar_PortletRollback=<%= _testAddClassNameAndTestTransactionPortletBar_PortletRollback() %>
</p>

<%!
private String _testAddBar_Success() {
	String text = "Hello World";

	try {
		Bar bar = BarLocalServiceUtil.addBar_Success(text);

		bar = BarLocalServiceUtil.getBar(bar.getBarId());

		BarLocalServiceUtil.deleteBar(bar);

		return "PASSED";
	}
	catch (Exception e) {
		e.printStackTrace();

		return "FAILED";
	}
}

private String _testAddBarAndClassName_PortalRollback() {
	String text = "Hello World";

	try {
		BarLocalServiceUtil.addBarAndClassName_PortalRollback(text);

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

private String _testAddBarAndClassName_PortletRollback() {
	String text = "Hello World";

	try {
		BarLocalServiceUtil.addBarAndClassName_PortletRollback(text);

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

private String _testAddClassNameAndTestTransactionPortletBar_Success() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddClassNameAndTestTransactionPortletBar_Success(text);

		if (!PortalServiceUtil.testHasClassName()) {
			System.out.println("Class name " + PortalService.class.getName() + "is not committed");

			return "FAILED";
		}

		PortalServiceUtil.testDeleteClassName();

		Bar bar = BarLocalServiceUtil.getBar(text);

		BarLocalServiceUtil.deleteBar(bar);

		return "PASSED";
	}
	catch (Exception e) {
		e.printStackTrace();

		return "FAILED";
	}
}

private String _testAddClassNameAndTestTransactionPortletBar_PortalRollback() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddClassNameAndTestTransactionPortletBar_PortalRollback(text);

		System.out.println("Portal failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portal failed to roll back Bar object");

				return "FAILED";
			}

			if (PortalServiceUtil.testHasClassName()) {
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

private String _testAddClassNameAndTestTransactionPortletBar_PortletRollback() {
	String text = "Hello World";

	try {
		PortalServiceUtil.testAddClassNameAndTestTransactionPortletBar_PortletRollback(text);

		System.out.println("Portlet failed to throw a SystemException");

		return "FAILED";
	}
	catch (SystemException se1) {
		try {
			if (BarLocalServiceUtil.hasBar(text)) {
				System.out.println("Portlet failed to roll back Bar object");

				return "FAILED";
			}

			if (PortalServiceUtil.testHasClassName()) {
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