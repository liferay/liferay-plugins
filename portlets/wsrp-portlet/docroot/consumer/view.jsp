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

<c:choose>
	<c:when test="<%= permissionChecker.isOmniadmin() %>">

		<%
		int action = ParamUtil.getInteger(request, Constants.ACTION);

		String tabs1 = ParamUtil.getString(request, "tabs1", "producers");

		String redirect = ParamUtil.getString(request, "redirect");

		ConfiguredProducerElementBean[] configuredProducerBeans = (ConfiguredProducerElementBean[])renderRequest.getAttribute("listConfiguredProducerBeans");

		if (configuredProducerBeans == null) {
			configuredProducerBeans = new ConfiguredProducerElementBean[0];
		}
		%>

		<c:choose>
			<c:when test="<%= action == AdminPortletAction.GET_VERSION_INFO %>">

				<%
				String wsdl = ParamUtil.getString(request, "wsdl");
				%>

				<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
				<input name="<portlet:namespace /><%= Constants.ACTION %>" type="hidden" value="<%= action %>" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />

				<liferay-ui:tabs names="producer" />

				<c:if test='<%= request.getAttribute("CONSUMER_ADMIN_ERROR") != null %>'>
					<span class="portlet-msg-error">
						<liferay-ui:message key="please-enter-a-valid-url" />
					</span>
				</c:if>

				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="url" />
					</td>
					<td>
						<input class="lfr-input-text" name="<portlet:namespace />wsdl" type="text" value="<%= HtmlUtil.escape(wsdl) %>" />
					</td>
				</tr>
				</table>

				<br />

				<input type="submit" value="<liferay-ui:message key="next" />" />

				<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

				</form>
			</c:when>
			<c:when test="<%= action == AdminPortletAction.GET_PRODUCER_INFO %>">

				<%
				String wsdl = ParamUtil.getString(request, "wsdl");

				List<String> versions = (List<String>)portletSession.getAttribute("producerSupportedVersions");
				%>

				<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
				<input name="<portlet:namespace /><%= Constants.ACTION %>" type="hidden" value="<%= action %>" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
				<input name="<portlet:namespace />wsdl" type="hidden" value="<%= HtmlUtil.escape(wsdl) %>" />

				<liferay-ui:tabs names="producer" />

				<c:if test='<%= request.getAttribute("CONSUMER_ADMIN_ERROR") != null %>'>
					<span class="portlet-msg-error">
						<liferay-ui:message key="an-unexpected-error-occurred-while-connecting-to-the-producer" />
					</span>
				</c:if>

				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="url" />
					</td>
					<td>
						<a href="<%= HtmlUtil.escape(wsdl) %>" target="_blank"><%= HtmlUtil.escape(wsdl) %></a>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="version" />
					</td>
					<td>
						<select name="<portlet:namespace />version">

							<%
							for (String version : versions) {
							%>

								<option value="<%= version %>"><%= _formatVersion(version) %></option>

							<%
							}
							%>

						</select>
					</td>
				</tr>
				</table>

				<br />

				<input type="submit" value="<liferay-ui:message key="next" />" />

				<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

				</form>
			</c:when>
			<c:when test="<%= action == AdminPortletAction.GET_DEFAULT %>">

				<%
				String name = ParamUtil.getString(request, "configuredProducerName");
				String wsdl = ParamUtil.getString(request, "wsdl");
				String version = ParamUtil.getString(request, "version");
				%>

				<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
				<input name="<portlet:namespace /><%= Constants.ACTION %>" type="hidden" value="<%= AdminPortletAction.CREATE %>" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
				<input name="<portlet:namespace />wsdl" type="hidden" value="<%= HtmlUtil.escape(wsdl) %>" />
				<input name="<portlet:namespace />version" type="hidden" value="<%= HtmlUtil.escape(version) %>" />

				<liferay-ui:tabs names="producer" />

				<%
				String consumerAdminError = GetterUtil.getString((String)request.getAttribute("CONSUMER_ADMIN_ERROR"));
				%>

				<c:if test='<%= consumerAdminError.equals("DUPLICATE_CONSUMER_NAME") %>' >
					<span class="portlet-msg-error">
						<liferay-ui:message key="consumer-name-already-exists" />
					</span>
				</c:if>

				<c:if test='<%= consumerAdminError.equals("CONSUMER_CREATION_FAILED") %>' >
					<span class="portlet-msg-error">
						<liferay-ui:message key="failed-to-create-consumer" />
					</span>
				</c:if>

				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="name" />
					</td>
					<td>
						<input class="lfr-input-text" name="<portlet:namespace />configuredProducerName" type="text" value="<%= name %>" />
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="url" />
					</td>
					<td>
						<a href="<%= HtmlUtil.escape(wsdl) %>" target="_blank"><%= HtmlUtil.escape(wsdl) %></a>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="version" />
					</td>
					<td>
						<%= _formatVersion(version) %>
					</td>
				</tr>

				<%
				Map producerInfoMap = (Map)portletSession.getAttribute("producerInfoMap");

				boolean supportsInbandRegistration = false;

				if ((producerInfoMap != null) && !producerInfoMap.isEmpty()){
					boolean requiresRegistration = GetterUtil.getBoolean(producerInfoMap.get("RegistrationRequired").toString());

					if (requiresRegistration) {
						supportsInbandRegistration = GetterUtil.getBoolean(producerInfoMap.get("InbandSupported").toString());

						Map<String, String> registrationPropertyDescriptions = null;

						if (supportsInbandRegistration) {
							registrationPropertyDescriptions = (Map<String, String>)producerInfoMap.get("RegistrationPropertyDescription");
						}
				%>

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

									List<Map.Entry<String, String>> results = null;

									if (registrationPropertyDescriptions != null) {
										results = ListUtil.fromCollection(registrationPropertyDescriptions.entrySet());
									}
									else {
										results = Collections.EMPTY_LIST;
									}

									List resultRows = searchContainer.getResultRows();

									for (int i = 0; i < results.size(); i++) {
										Map.Entry<String, String> property = results.get(i);

										String propertyName = property.getKey();
										String propertyDescription = property.getValue();

										ResultRow row = new ResultRow(propertyName, propertyName, i);

										// Name

										row.addText(propertyName);

										// Value

										StringBuilder sb = new StringBuilder();

										sb.append("<input name=\"");
										sb.append(renderResponse.getNamespace());
										sb.append("regPropName");
										sb.append(i);
										sb.append("\" type=\"hidden\" value=\"");
										sb.append(propertyName);
										sb.append("\" />");

										sb.append("<input name=\"");
										sb.append(renderResponse.getNamespace());
										sb.append("regPropDescription");
										sb.append(i);
										sb.append("\" type=\"hidden\" value=\"");
										sb.append(propertyDescription);
										sb.append("\" />");

										sb.append("<input name=\"");
										sb.append(renderResponse.getNamespace());
										sb.append("regPropValue");
										sb.append(i);
										sb.append("\" type=\"text\" />");

										row.addText(sb.toString());

										// Description

										row.addText(propertyDescription);

										// Add result row

										resultRows.add(row);
									}
									%>

									<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
								</td>
							</tr>
						</tbody>
						<tr>
							<td colspan="2">
								<br />
							</td>
						</tr>

				<%
					}
				}
				%>

				<c:if test='<%= version.equals("V2") && supportsInbandRegistration %>'>
					<tr>
						<td>
							<liferay-ui:message key="lifetime" />
						</td>
						<td>
							<input id="<portlet:namespace />lifetimeSupplied" name="<portlet:namespace />lifetimeSupplied" type="checkbox" value="true" />
						</td>
					</tr>
					<tbody id="<portlet:namespace />lifetimeSettings">
						<tr>
							<td>
								<liferay-ui:message key="valid-days" />
							</td>
							<td>
								<input class="lfr-input-text" name="<portlet:namespace />lifetimeDays" size="5" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								<liferay-ui:message key="valid-hours" />
							</td>
							<td>
								<input class="lfr-input-text" name="<portlet:namespace />lifetimeHours" size="5" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								<liferay-ui:message key="valid-minutes" />
							</td>
							<td>
								<input class="lfr-input-text" name="<portlet:namespace />lifetimeMins" size="5" type="text" />
							</td>
						</tr>
					</tbody>
				</c:if>

				</table>

				<br />

				<input type="submit" value="<liferay-ui:message key="save" />" />

				<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

				</form>
			</c:when>
			<c:when test="<%= action == AdminPortletAction.GET_DETAILS %>">

				<%
				ConfiguredProducerBean configuredProducerBean = (ConfiguredProducerBean)portletSession.getAttribute("configuredProducerBean");
				%>

				<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
				<input name="<portlet:namespace /><%= Constants.ACTION %>" type="hidden" value="<%= AdminPortletAction.UPDATE %>" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
				<input name="<portlet:namespace />configuredProducerName" type="hidden" value="<%= HtmlUtil.escape(configuredProducerBean.getName()) %>" />
				<input name="<portlet:namespace />configuredProducerId" type="hidden" value="<%= HtmlUtil.escape(configuredProducerBean.getConfiguredProducerId()) %>" />

				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="producer-name" />
					</td>
					<td>
						<%= configuredProducerBean.getName() %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="url" />
					</td>
					<td>
						<a href="<%= configuredProducerBean.getWsdlURL() %>"><%= configuredProducerBean.getWsdlURL() %></a>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="version" />
					</td>
					<td>
						<%= _formatVersion(configuredProducerBean.getVersion()) %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="enabled" />
					</td>
					<td>
						<select name="<portlet:namespace />enabled">
							<option <%= configuredProducerBean.isStatus() ? "selected" : "" %> value="true"><liferay-ui:message key="yes" /></option>
							<option <%= !configuredProducerBean.isStatus() ? "selected" : "" %> value="false"><liferay-ui:message key="no" /></option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="identity" />
					</td>
					<td>
						<%= configuredProducerBean.getUserIdentityPropagation() %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="registration-handle" />
					</td>
					<td>
						<%= configuredProducerBean.getRegistrationHandle() %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="registration-properties" />
					</td>
					<td>

						<%
						Map registrationProperties = configuredProducerBean.getRegistrationProperties();
						Map registrationPropertyDescriptions = configuredProducerBean.getRegistrationPropertyDescriptions();

						SearchContainer searchContainer = new SearchContainer();

						List<String> headerNames = new ArrayList<String>();

						headerNames.add("name");
						headerNames.add("value");
						headerNames.add("description");

						searchContainer.setHeaderNames(headerNames);
						searchContainer.setEmptyResultsMessage("there-are-no-registration-properties");

						List<Map.Entry<String, String>> results = null;

						if (registrationPropertyDescriptions != null) {
							results = ListUtil.fromCollection(registrationPropertyDescriptions.entrySet());
						}
						else {
							results = Collections.EMPTY_LIST;
						}

						List resultRows = searchContainer.getResultRows();

						for (int i = 0; i < results.size(); i++) {
							Map.Entry<String, String> property = results.get(i);

							String propertyName = property.getKey();
							String propertyDescription = property.getValue();

							ResultRow row = new ResultRow(propertyName, propertyName, i);

							// Name

							row.addText(propertyName);

							// Value

							String propertyValue = StringPool.BLANK;

							if (registrationProperties != null){
								propertyValue = GetterUtil.getString((String)registrationProperties.get(propertyName));
							}

							StringBuilder sb = new StringBuilder();

							sb.append("<input name=\"");
							sb.append(renderResponse.getNamespace());
							sb.append("regPropName");
							sb.append(i);
							sb.append("\" type=\"hidden\" value=\"");
							sb.append(propertyName);
							sb.append("\" />");

							sb.append("<input name=\"");
							sb.append(renderResponse.getNamespace());
							sb.append("regPropDescription");
							sb.append(i);
							sb.append("\" type=\"hidden\" value=\"");
							sb.append(propertyDescription);
							sb.append("\" />");

							sb.append("<input name=\"");
							sb.append(renderResponse.getNamespace());
							sb.append("regPropValue");
							sb.append(i);
							sb.append("\" type=\"text\" value=\"");
							sb.append(propertyValue);
							sb.append("\" />");

							row.addText(sb.toString());

							// Description

							row.addText(propertyDescription);

							// Add result row

							resultRows.add(row);
						}
						%>

						<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
					</td>
				</tr>

				<c:if test="<%= configuredProducerBean.getTerminationTime() != null %>">
					<tr>
						<td>
							<liferay-ui:message key="termination-time" />
						</td>
						<td>
							<%= configuredProducerBean.getTerminationTime() %>
						</td>
					</tr>
				</c:if>

				</table>

				<br />

				<input type="submit" value="<liferay-ui:message key="save" />" />

				<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

				</form>
			</c:when>
			<c:when test="<%= action == AdminPortletAction.GET_INFO_FOR_CHANNEL %>">

				<%
				String name = ParamUtil.getString(request, "configuredProducerName");
				String id = ParamUtil.getString(request, "configuredProducerId");
				%>

				<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
				<input name="<portlet:namespace /><%= Constants.ACTION %>" type="hidden" value="<%= AdminPortletAction.CREATE_CHANNEL %>" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
				<input name="<portlet:namespace />configuredProducerName" type="hidden" value="<%= HtmlUtil.escape(name) %>" />
				<input name="<portlet:namespace />configuredProducerId" type="hidden" value="<%= HtmlUtil.escape(id) %>" />

				<liferay-ui:tabs names="portlet" />

				<%
				String consumerAdminError = GetterUtil.getString((String)request.getAttribute("CONSUMER_ADMIN_ERROR"));
				%>

				<c:if test='<%= consumerAdminError.equals("DUPLICATE_PORTLET_NAME") %>' >
					<span class="portlet-msg-error">
						<liferay-ui:message key="remote-portlet-name-already-exists" />
					</span>
				</c:if>

				<c:if test='<%= consumerAdminError.equals("CHANNEL_CREATION_FAILED") %>' >
					<span class="portlet-msg-error">
						<liferay-ui:message key="failed-to-install-remote-portlet" />
					</span>
				</c:if>

				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="producer-name" />
					</td>
					<td>
						<%= name %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="producer-id" />
					</td>
					<td>
						<%= id %>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="available-portlets" />
					</td>
					<td>
						<select name="<portlet:namespace />portletHandle">

							<%
							Map<String, String> channelInfo = (Map<String, String>)portletSession.getAttribute("infoForChannel");

							if ((channelInfo != null) && !channelInfo.isEmpty()) {
								Iterator itr = channelInfo.entrySet().iterator();

								while (itr.hasNext()) {
									Map.Entry<String, String> entry = (Map.Entry<String, String>)itr.next();

									String portletHandle = entry.getKey();
									String displayName = entry.getValue();
							%>

									<option value="<%= portletHandle %>"><%= displayName %></option>

							<%
								}
							}
							%>

						</select>
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="portlet-name" />
					</td>
					<td>
						<input class="lfr-input-text" name="<portlet:namespace />channelName" type="text" />
					</td>
				</tr>
				</table>

				<br />

				<input type="submit" value="<liferay-ui:message key="save" />" />

				<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

				</form>
			</c:when>
			<c:when test="<%= action == AdminPortletAction.LIST_CHANNELS %>">
				<liferay-util:include page="/consumer/tabs1.jsp">
					<liferay-util:param name="tabs1" value="portlets" />
				</liferay-util:include>

				<%
				SearchContainer searchContainer = new SearchContainer();

				List<String> headerNames = new ArrayList<String>();

				headerNames.add("portlet-name");
				headerNames.add(StringPool.BLANK);

				searchContainer.setHeaderNames(headerNames);
				searchContainer.setEmptyResultsMessage("there-are-no-installed-portlets");

				List<String> results = (List)portletSession.getAttribute("channelNames");

				if (results == null) {
					results = Collections.EMPTY_LIST;
				}

				List resultRows = searchContainer.getResultRows();

				for (int i = 0; i < results.size(); i++) {
					String channelName = results.get(i);

					ResultRow row = new ResultRow(channelName, channelName, i);

					// Name

					row.addText(channelName);

					// Action

					row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/consumer/channel_action.jsp", application, request, response);

					// Add result row

					resultRows.add(row);
				}
				%>

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("producers") %>'>
				<liferay-util:include page="/consumer/tabs1.jsp" />

				<%
				SearchContainer searchContainer = new SearchContainer();

				List<String> headerNames = new ArrayList<String>();

				headerNames.add("name");
				headerNames.add("id");
				headerNames.add("status");
				headerNames.add(StringPool.BLANK);

				searchContainer.setHeaderNames(headerNames);
				searchContainer.setEmptyResultsMessage("there-are-no-connected-producers");

				List resultRows = searchContainer.getResultRows();

				for (int i = 0; i < configuredProducerBeans.length; i++) {
					ConfiguredProducerElementBean configuredProducerBean = (ConfiguredProducerElementBean)configuredProducerBeans[i];

					ResultRow row = new ResultRow(configuredProducerBean, configuredProducerBean.getId(), i);

					// Name

					PortletURL portletURL = renderResponse.createActionURL();

					portletURL.setParameter(Constants.ACTION, String.valueOf(AdminPortletAction.GET_DETAILS));
					portletURL.setParameter("redirect", currentURL);
					portletURL.setParameter("configuredProducerId", configuredProducerBean.getId());

					row.addText(configuredProducerBean.getName(), portletURL);

					// Producer ID

					row.addText(configuredProducerBean.getId(), portletURL);

					// Status

					row.addText(LanguageUtil.get(pageContext, configuredProducerBean.getStatus() ? "enabled" : "disabled"), portletURL);

					// Action

					row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/consumer/consumer_action.jsp", application, request, response);

					// Add result row

					resultRows.add(row);
				}
				%>

				<div>
					<input type="button" value="<liferay-ui:message key="connect-to-producer" />" onClick="location.href = '<portlet:renderURL><portlet:param name="<%= Constants.ACTION %>" value="<%= String.valueOf(AdminPortletAction.GET_VERSION_INFO) %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';" />
				</div>

				<br />

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="<%= false %>" />
			</c:when>
		</c:choose>

		<script type="text/javascript">
			jQuery(
				function() {
					jQuery('#<portlet:namespace />inbandRegistration').change(
						function() {
							jQuery('#<portlet:namespace />registrationHandleSettings').toggle();
							jQuery('#<portlet:namespace />registrationPropertiesSettings').toggle();
						}
					);

					Liferay.Util.toggleBoxes('<portlet:namespace />lifetimeSupplied', '<portlet:namespace />lifetimeSettings');
				}
			);
		</script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_access_denied.jsp" />
	</c:otherwise>
</c:choose>

<%!
private String _formatVersion(String version) throws Exception {
	if (version.equals("V1")) {
		return "1.0";
	}
	else if (version.equals("V2")) {
		return "2.0";
	}
	else {
		return version;
	}
}
%>