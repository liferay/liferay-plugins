<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);

portletURL.setParameter("jspPage", "/sites/edit_community.jsp");
%>

<portlet:actionURL name="addCommunity" var="addCommunityURL" />

<aui:form action="<%= addCommunityURL %>" method="post" name="dialogFm">
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

	<liferay-ui:asset-tags-error />

	<aui:model-context model="<%= Group.class %>" />

	<aui:fieldset>
		<aui:column>
			<aui:input name="name" />

			<aui:input name="description" />

			<aui:select name="type">
				<aui:option label="open" value="<%= GroupConstants.TYPE_COMMUNITY_OPEN %>" />
				<aui:option label="restricted" value="<%= GroupConstants.TYPE_COMMUNITY_RESTRICTED %>" />
				<aui:option label="private" value="<%= GroupConstants.TYPE_COMMUNITY_PRIVATE %>" />
			</aui:select>
		</aui:column>
	</aui:fieldset>

	<%
	List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);
	%>

	<aui:fieldset>
		<aui:column>
			<aui:select label="public-pages" name="publicLayoutSetPrototypeId">
				<aui:option label="none" selected="<%= true %>" value="" />

				<%
				for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
					Boolean socialOfficeDefault = (Boolean)layoutSetPrototype.getExpandoBridge().getAttribute("socialOfficeDefault");
				%>

					<aui:option selected="<%= socialOfficeDefault %>" value="<%= layoutSetPrototype.getLayoutSetPrototypeId() %>"><%= layoutSetPrototype.getName(user.getLanguageId()) %></aui:option>

				<%
				}
				%>

			</aui:select>
		</aui:column>

		<aui:column>
			<aui:select label="private-pages" name="privateLayoutSetPrototypeId">
				<aui:option label="none" selected="<%= true %>" value="" />

				<%
				for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
				%>

					<aui:option value="<%= layoutSetPrototype.getLayoutSetPrototypeId() %>"><%= layoutSetPrototype.getName(user.getLanguageId()) %></aui:option>

				<%
				}
				%>

			</aui:select>
		</aui:column>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,aui-io">
	var form = A.one(document.<portlet:namespace />dialogFm);

	form.on(
		'submit',
		function(event) {
			event.halt();

			var popupNode = Liferay.SO.Sites.getPopup().bodyNode;

			if (!popupNode.io) {
				popupNode.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						on: {success: Liferay.SO.Sites.updateSites}
					}
				);
			}

			popupNode.io.set('form', {id: form.getDOM()});
			popupNode.io.set('uri', form.getAttribute('action'));

			popupNode.io.start();
		}
	);

	Liferay.Util.focusFormField(document.<portlet:namespace />dialogFm.<portlet:namespace />name);
</aui:script>