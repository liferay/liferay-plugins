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

<%@ include file="/init.jsp" %>

<%
String dlDisplayPath = PrefsParamUtil.getString(preferences, request, "dlDisplayPath");
String widgetUrlPrefix = PortalUtil.getPortalURL(request) + PortalUtil.getPathContext() + "/widget/";
%>


<form action="<liferay-portlet:actionURL portletConfiguration="true" />" class="uni-form" id="<portlet:namespace />fm" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">
	<div class="portlet-msg-info">
		<liferay-ui:message key="get-widget-document-library-display-url-from-sharing-to-any-website-tab" />
	</div>

	<liferay-ui:message key="document-library-display-widget-url" />: <%= widgetUrlPrefix %> <input class="lfr-input-text" name="<portlet:namespace />dlDisplayPath" value="<%= dlDisplayPath %>" />

	<div class="button-holder">
		<input type="submit" value="<liferay-ui:message key="save" />" />
	</div>
</form>