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

<%@ include file="/init.jsp" %>

<portlet:defineObjects />

This is the <b>Test Transaction</b> portlet. This was made to test transaction support in portlets.

<br /><br />

<b>Add Bar Portal Rollback : </b> <%=TestUtil.testAddBarPortalRollback() %>

<br /><br />

<b>Add Bar Portlet Rollback : </b> <%=TestUtil.testAddBarPortletRollback() %>

<br /><br />

<b>Add Bar Success : </b> <%=TestUtil.testAddBarSuccess() %>