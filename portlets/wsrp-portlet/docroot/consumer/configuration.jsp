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
PortletPreferences prefs = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

List<Producer> producers = ProducerLocalServiceUtil.getProducers();

long producerId = PrefsParamUtil.getLong(prefs, request, "producerId");

String portletHandle = PrefsParamUtil.getString(prefs, request, "portletHandle");

List <PortletDescription> portletDescriptions = null;
Producer selectedProducer = null;

if (producerId > 0) {
	selectedProducer = ProducerLocalServiceUtil.getProducer(producerId);
	ServiceDescription sd = ProducerModelUtil.getServiceDescription(selectedProducer);
	portletDescriptions = sd.getOfferedPortlets();
}
else if (producers.size() > 0) {
	selectedProducer = producers.get(0);
	ServiceDescription sd = ProducerModelUtil.getServiceDescription(selectedProducer);
	portletDescriptions = sd.getOfferedPortlets();
}
%>

<script type="text/javascript">
function refresh() {
	document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
	submitForm(document.<portlet:namespace />fm);
}
</script>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="producer" />
	</td>
	<td>
			<select name="<portlet:namespace />producerId" onChange="refresh();">
			<%
			for (Producer producer : producers) {
			%>
				<option <%= producerId == producer.getProducerId() ? "selected" : "" %> value="<%= producer.getProducerId() %>"><%= producer.getName() %></option>
			<%
			}
			%>
		</select>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="portlet" />
	</td>
	<td>
		<select name="<portlet:namespace />portletHandle">
			<%
			if (portletDescriptions != null) {
				for (PortletDescription pd : portletDescriptions) {
			%>
					<option <%= portletHandle.equals(pd.getPortletHandle()) ? "selected" : "" %> value="<%= pd.getPortletHandle() %>"><%= pd.getTitle().getValue() %></option>
			<%
				}
			}
			%>
		</select>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

</form>