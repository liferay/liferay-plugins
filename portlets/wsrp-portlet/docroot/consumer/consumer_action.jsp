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

<%
/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */
%>

<%@ include file="/consumer/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ConfiguredProducerElementBean configuredProducerBean = (ConfiguredProducerElementBean)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:actionURL var="editURL">
		<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.GET_DETAILS) %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="configuredProducerName" value="<%= configuredProducerBean.getName() %>" />
		<portlet:param name="configuredProducerId" value="<%= configuredProducerBean.getId() %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="edit" url="<%= editURL %>" />

	<portlet:actionURL var="installPortletURL">
		<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.GET_INFO_FOR_CHANNEL) %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="configuredProducerName" value="<%= configuredProducerBean.getName() %>" />
		<portlet:param name="configuredProducerId" value="<%= configuredProducerBean.getId() %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="add" message="install-portlet" url="<%= installPortletURL %>" />

	<portlet:actionURL var="updateServiceDescriptionURL">
		<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.UPDATE_SD) %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="configuredProducerName" value="<%= configuredProducerBean.getName() %>" />
		<portlet:param name="configuredProducerId" value="<%= configuredProducerBean.getId() %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="view" message="update-service-description" url="<%= updateServiceDescriptionURL %>" />

	<portlet:actionURL var="deleteURL">
		<portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.DELETE) %>" />
		<portlet:param name="selectedConfiguredProducers" value="<%= configuredProducerBean.getId() %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>