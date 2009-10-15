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

String[] portletIds = StringUtil.split(BeanParamUtil.getString(wsrpProducer, request, "portletIds"));
%>

<script type="text/javascript">
	function <portlet:namespace />saveProducer() {
		document.<portlet:namespace />fm.<portlet:namespace />portletIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentPortletIds);
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateWSRPProducer"><portlet:param name="jspPage" value="/admin/edit_producer.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveProducer(); return false;">
<input name="<portlet:namespace />wsrpProducerId" type="hidden" value="<%= wsrpProducerId %>" />
<input name="<portlet:namespace />portletIds" type="hidden" value="" />

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
			<a href="<%= themeDisplay.getPortalURL() %>/wsrp-portlet/wsdl/<%= wsrpProducer.getWsrpProducerId() %>" target="_blank"><%= themeDisplay.getPortalURL() %>/wsrp-portlet/wsdl/<%= wsrpProducer.getWsrpProducerId() %></a>
		</td>
	</tr>
</c:if>

<tr>
	<td colspan="3">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="portlets" />
	</td>
	<td>

		<%

		// Left list

		List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

		for (String portletId : portletIds) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletId);

			if ((portlet == null) || portlet.isUndeployedPortlet()) {
				continue;
			}

			leftList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, application, locale)));
		}

		leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

		// Right list

		List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

		Arrays.sort(portletIds);

		Iterator<Portlet> itr = PortletLocalServiceUtil.getPortlets(company.getCompanyId(), false, false).iterator();

		while (itr.hasNext()) {
			Portlet portlet = (Portlet)itr.next();

			if (portlet.isUndeployedPortlet()) {
				continue;
			}

			String portletId = portlet.getPortletId();

			if (Arrays.binarySearch(portletIds, portletId) < 0) {
				rightList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, application, locale)));
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<liferay-ui:input-move-boxes
			formName="fm"
			leftTitle="current"
			rightTitle="available"
			leftBoxName="currentPortletIds"
			rightBoxName="availablePortletIds"
			leftList="<%= leftList %>"
			rightList="<%= rightList %>"
		/>
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