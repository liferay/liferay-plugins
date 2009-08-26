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
String redirect = ParamUtil.getString(request, "redirect");

long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

UnicodeProperties registrationProperties = wsrpConsumer.getRegistrationProperties();

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();

boolean supportsInbandRegistration = false;

if (serviceDescription.isRequiresRegistration() && (wsrpConsumerManager.getRegistrationService() != null)) {
	supportsInbandRegistration = true;
}

PropertyDescription[] propertyDescriptions = new PropertyDescription[0];

if (supportsInbandRegistration) {
	propertyDescriptions = wsrpConsumerManager.getPropertyDescriptions();
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveConsumerRegsitration() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateWSRPConsumerRegistration"><portlet:param name="jspPage" value="/admin/edit_consumer_registration.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumerRegistration(); return false;">
<input name="<portlet:namespace />wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="consumers" /></a></span> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((wsrpConsumer == null) ? Constants.ADD : Constants.UPDATE) + "-consumer-registration" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<%= wsrpConsumer.getName() %>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<a href="<%= wsrpConsumer.getUrl() %>" target="_blank"><%= wsrpConsumer.getUrl() %></a>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="registration-type" />
	</td>
	<td>
		<select id="<portlet:namespace />inbandRegistration" name="<portlet:namespace />inbandRegistration">
			<c:if test="<%= supportsInbandRegistration %>">
				<option value="true"><liferay-ui:message key="inband" /></option>
			</c:if>

			<option value="false"><liferay-ui:message key="outband" /></option>
		</select>
	</td>
</tr>
<tbody id="<portlet:namespace />registrationHandleSettings" <%= supportsInbandRegistration ? "style=\"display: none;\"" : "" %>>
	<tr>
		<td>
			<liferay-ui:message key="registration-handle" />
		</td>
		<td>
			<input class="lfr-input-text" name="<portlet:namespace />registrationHandle" type="text" />
		</td>
	</tr>
</tbody>
<tbody id="<portlet:namespace />registrationPropertiesSettings" <%= !supportsInbandRegistration ? "style=\"display: none;\"" : "" %>>
	<tr>
		<td>
			<liferay-ui:message key="registration-properties" />
		</td>
		<td>

			<%
			SearchContainer searchContainer = new SearchContainer();

			List<String> headerNames = new ArrayList<String>();

			headerNames.add("name");
			headerNames.add("value");
			headerNames.add("description");

			searchContainer.setHeaderNames(headerNames);
			searchContainer.setEmptyResultsMessage("there-are-no-registration-properties");

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < propertyDescriptions.length; i++) {
				PropertyDescription propertyDescription = propertyDescriptions[i];

				String name = propertyDescription.getName().getLocalPart();
				String fullyQualifiedName = propertyDescription.getName().toString();
				String description = propertyDescription.getDescription().getValue();

				ResultRow row = new ResultRow(name, name, i);

				// Name

				row.addText(name);

				// Value

				StringBuilder sb = new StringBuilder();

				sb.append("<input name=\"");
				sb.append(renderResponse.getNamespace());
				sb.append("registrationPropertyName");
				sb.append(i);
				sb.append("\" type=\"hidden\" value=\"");
				sb.append(fullyQualifiedName);
				sb.append("\" />");

				String registrationPropertyValue = GetterUtil.getString(registrationProperties.get(fullyQualifiedName));

				sb.append("<input name=\"");
				sb.append(renderResponse.getNamespace());
				sb.append("registrationPropertyValue");
				sb.append(i);
				sb.append("\" type=\"text\" value=\"");
				sb.append(registrationPropertyValue);
				sb.append("\" />");

				row.addText(sb.toString());

				// Description

				row.addText(description);

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
		</td>
	</tr>
</tbody>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>

<script type="text/javascript">
	AUI().ready(
		function() {
			jQuery('#<portlet:namespace />inbandRegistration').change(
				function() {
					jQuery('#<portlet:namespace />registrationHandleSettings').toggle();
					jQuery('#<portlet:namespace />registrationPropertiesSettings').toggle();
				}
			);
		}
	);
</script>