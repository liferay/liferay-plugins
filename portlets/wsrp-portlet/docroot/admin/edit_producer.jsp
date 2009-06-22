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

long wsrpProducerId = ParamUtil.getLong(request, "wsrpProducerId");

WSRPProducer wsrpProducer = null;

try {
	wsrpProducer = WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerId);
}
catch (NoSuchProducerException nsce) {
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveProducer() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateWSRPProducer"><portlet:param name="jspPage" value="/admin/edit_producer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveProducer(); return false;">
<input name="<portlet:namespace />wsrpProducerId" type="hidden" value="<%= wsrpProducerId %>" />

<liferay-ui:error exception="<%= WSRPProducerNameException.class %>" message="please-enter-a-valid-name" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="producers" /></a></span> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((wsrpProducer == null) ? Constants.ADD : Constants.UPDATE) + "-producer" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= WSRPProducer.class %>" bean="<%= wsrpProducer %>" field="name" />
	</td>
</tr>

<c:if test="<%= wsrpProducer != null %>">
	<tr>
		<td>
			<liferay-ui:message key="url" />
		</td>
		<td>
			<%= themeDisplay.getPortalURL() %>/wsrp-portlet/wsdl/<%= wsrpProducer.getWsrpProducerId() %>
		</td>
	</tr>
</c:if>

</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>

<script type="text/javascript">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</script>