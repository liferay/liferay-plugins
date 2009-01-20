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

<%@ include file="/application_builder/init.jsp" %>


<p>
	<liferay-ui:message key="enter-code-that-renders-the-html-output"></liferay-ui:message>
</p>
<p>
	<liferay-ui:message key="the-following-implicit-variables-are-available"></liferay-ui:message>
</p>
<ul>
	<li>
		<b>portletConfig</b>: <liferay-ui:message key="the-current-portlet-configuration-object" />
	</li>
	<li>
		<b>portletContext</b>: <liferay-ui:message key="the-current-portlet-context-object" />
	</li>
	<li>
		<b>portletRequest</b>: <liferay-ui:message key="the-current-request-object" />
	</li>
	<li>
		<b>portletResponse</b>: <liferay-ui:message key="the-current-response-object" />
	</li>
	<li>
		<b>preferences</b>: <liferay-ui:message key="the-current-portlet-preferences-object" />
	</li>
	<li>
		<b>userInfo</b>: <liferay-ui:message key="the-current-user-info-object" />
	</li>
</ul>