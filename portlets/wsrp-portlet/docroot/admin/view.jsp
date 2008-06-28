<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

List headerNames = new ArrayList();

headerNames.add("name");
headerNames.add("wsdl-url");
headerNames.add("registration");
headerNames.add(StringPool.BLANK);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

int total = ProducerLocalServiceUtil.getProducerCount();

searchContainer.setTotal(total);

List<Producer> results = ProducerLocalServiceUtil.getProducers(searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);

List<ResultRow> resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	Producer producerOrig = (Producer)results.get(i);

	Producer producer = producerOrig.toEscapedModel();

	ResultRow row = new ResultRow(producer, producer.getProducerId(), i);

	// Name

	row.addText(producer.getName());

	// WSDL Location

	row.addText(producer.getWsdlURL());

	// Registration

	ServiceDescription sd = ProducerModelUtil.getServiceDescription(producerOrig);

	if (sd.isRequiresRegistration()) {
		if (Validator.isNull(producer.getRegistrationContext())) {
			row.addText(LanguageUtil.get(pageContext, "required"));
		}
		else {
			row.addText(LanguageUtil.get(pageContext, "registered"));
		}
	}
	else {
		row.addText(LanguageUtil.get(pageContext, "not-required"));
	}

	// Action

	row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/admin/producer_action.jsp", application, request, response);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:tabs
	names="producers"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>
<portlet:renderURL var="redirectURL" />

<c:if test='<%= PortletPermissionUtil.contains(permissionChecker, plid.longValue(), portletDisplay.getRootPortletId(), "ADD_PRODUCER") %>'>
	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addProducerURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
		<portlet:param name="redirect" value="<%= HtmlUtil.escape(redirectURL) %>" />
	</portlet:renderURL>

	<input type="button" value="<liferay-ui:message key='add-producer' />" onClick="location.href = '<%= addProducerURL %>';" />
</c:if>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
