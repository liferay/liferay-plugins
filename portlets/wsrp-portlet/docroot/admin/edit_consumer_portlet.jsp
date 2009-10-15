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

long wsrpConsumerPortletId = ParamUtil.getLong(request, "wsrpConsumerPortletId");

WSRPConsumerPortlet wsrpConsumerPortlet = null;

try {
	wsrpConsumerPortlet = WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(wsrpConsumerPortletId);
}
catch (NoSuchConsumerPortletException nscpe) {
}

long wsrpConsumerId = BeanParamUtil.getLong(wsrpConsumerPortlet, request, "wsrpConsumerId");
String portletHandle = BeanParamUtil.getString(wsrpConsumerPortlet, request, "portletHandle");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

ServiceDescription serviceDescription = wsrpConsumerManager.getServiceDescription();

PortletDescription[] portletDescriptions = serviceDescription.getOfferedPortlets();
%>

<script type="text/javascript">
	function <portlet:namespace />saveConsumerPortlet() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateWSRPConsumerPortlet"><portlet:param name="jspPage" value="/admin/edit_consumer_portlet.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumerPortlet(); return false;">
<input name="<portlet:namespace />wsrpConsumerPortletId" type="hidden" value="<%= wsrpConsumerPortletId %>" />
<input name="<portlet:namespace />wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

<liferay-ui:error exception="<%= WSRPConsumerPortletNameException.class %>" message="please-enter-a-valid-name" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="consumers" /></a></span> &raquo;

	<a href="<portlet:renderURL><portlet:param name="jspPage" value="/admin/edit_consumer.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" /></portlet:renderURL>"><%= wsrpConsumer.getName() %></a> &raquo;

	<a href="<portlet:renderURL><portlet:param name="jspPage" value="/admin/view_consumer_portlets.jsp" /><portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" /></portlet:renderURL>"><liferay-ui:message key="manage-portlets" /></a> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((wsrpConsumerPortlet == null) ? Constants.ADD : Constants.UPDATE) + "-portlet" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= WSRPConsumerPortlet.class %>" bean="<%= wsrpConsumerPortlet %>" field="name" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="remote-portlet" />
	</td>
	<td>
		<select name="<portlet:namespace />portletHandle">
			<option value=""></option>

			<%
			for (PortletDescription portletDescription : portletDescriptions) {
			%>

				<option <%= portletHandle.equals(portletDescription.getPortletHandle()) ? "selected" : "" %> value="<%= portletDescription.getPortletHandle() %>"><%= portletDescription.getDisplayName().getValue() %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<script type="text/javascript">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</script>