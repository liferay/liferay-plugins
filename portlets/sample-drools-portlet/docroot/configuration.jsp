<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String domainNameValue = ParamUtil.getString(request, "domainName", domainName);
String rulesValue = ParamUtil.getString(request, "rules", rules);
String userCustomAttributeNamesValue = ParamUtil.getString(request, "userCustomAttributeNamesValue", userCustomAttributeNames);
long[] classNameIdValues = StringUtil.split(ParamUtil.getString(request, "classNameIds", StringUtil.merge(classNameIds)), 0L);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="classNameIds" type="hidden" />

	<liferay-ui:error key="classNameIds" message="please-select-at-least-one-asset" />
	<liferay-ui:error key="domainName" message="please-enter-a-valid-domain-name" />
	<liferay-ui:error key="rules" message="please-enter-valid-rules" />
	<liferay-ui:error key="rulesEngineException" message="please-check-the-syntax-of-your-rules" />

	<aui:fieldset>
		<aui:input name="domainName" value="<%= domainNameValue %>" wrapperCssClass="lfr-input-text-container"  />

		<aui:input name="rules" style="height: 250px; width: 100%;" type="textarea" value="<%= rulesValue %>" wrap="off" wrapperCssClass="lfr-textarea-container" />

		<%

		// Left list

		MethodKey methodKey = new MethodKey(ClassResolverUtil.resolveByPortalClassLoader("com.liferay.portal.security.permission.ResourceActionsUtil"), "getModelResource", HttpServletRequest.class, String.class);

		List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

		for (long classNameId : classNameIdValues) {
			String value = (String)PortalClassInvoker.invoke(false, methodKey, pageContext, PortalUtil.getClassName(classNameId));

			leftList.add(new KeyValuePair(String.valueOf(classNameId), value));
		}

		// Right list

		List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

		for (long classNameId : AssetRendererFactoryRegistryUtil.getClassNameIds()) {
			if (!ArrayUtil.contains(classNameIdValues, classNameId)) {
				String value = (String)PortalClassInvoker.invoke(false, methodKey, pageContext, PortalUtil.getClassName(classNameId));

				rightList.add(new KeyValuePair(String.valueOf(classNameId), value));
			}
		}
		%>

		<aui:input name="userCustomAttributeNames" value="<%= userCustomAttributeNamesValue %>" wrapperCssClass="lfr-input-text-container" />

		<liferay-ui:input-move-boxes
			leftBoxName="currentClassNameIds"
			leftList="<%= ListUtil.sort(leftList, new KeyValuePairComparator(false, true)) %>"
			leftTitle="current"
			rightBoxName="availableClassNameIds"
			rightList="<%= ListUtil.sort(rightList, new KeyValuePairComparator(false, true)) %>"
			rightTitle="available"
		/>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveConfiguration',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />classNameIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentClassNameIds);

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>