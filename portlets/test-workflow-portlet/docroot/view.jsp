<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.workflow.TaskInstanceManagerUtil" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil" %>

<portlet:defineObjects />

<h3>WorkflowDefinitionManager</h3>

<p>
	getWorkflowDefinitionCount()=<%= _assertEquals(0, WorkflowDefinitionManagerUtil.getWorkflowDefinitionCount()) %>
</p>

<p>
	isSupportsVersioning()=<%= _assertTrue(WorkflowDefinitionManagerUtil.isSupportsVersioning()) %>
</p>

<h3>WorkflowInstanceManagerUtil</h3>

<h3>TaskInstanceManagerUtil</h3>

<%!
private static String _assertEquals(Object expected, Object actual) {
	return _assertTrue(Validator.equals(expected, actual));
}

private static String _assertFalse(boolean value) {
	return _assertTrue(!value);
}

private static String _assertFalse(String value) {
	return _assertFalse(GetterUtil.getBoolean(value));
}

private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private static String _assertTrue(String value) {
	return _assertTrue(GetterUtil.getBoolean(value));
}
%>