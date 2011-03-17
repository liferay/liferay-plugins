<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<aui:form name="fm">
	<div id="<portlet:namespace />error"></div>

	<aui:fieldset>
		<aui:input model="<%= Gadget.class %>" name="url" />

		<aui:button-row>
			<aui:button name="submit" value="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,aui-io,liferay-layout,liferay-portlet">
	A.one('#<portlet:namespace />submit').on(
		'click',
		function() {
			var Layout = Liferay.Layout;

			var placeHolder = A.Node.create('<div class="loading-animation" />');

			var layoutOptions = Layout.options;

			var firstColumn = A.one(layoutOptions.dropNodes);

			if (firstColumn) {
				var dropColumn = firstColumn.one(layoutOptions.dropContainer);

				var referencePortlet = Layout.findReferencePortlet(dropColumn);

				if (referencePortlet) {
					referencePortlet.placeBefore(placeHolder);
				}
				else {
					if (dropColumn) {
						dropColumn.append(placeHolder);
					}
				}
			}

			Liferay.Service.OpenSocial.Gadget.addGadget(
				{
					companyId: themeDisplay.getCompanyId(),
					url: A.one('#<portlet:namespace />url').get('value'),
					portletCategoryNames: 'category.gadgets',
					serviceContext: A.JSON.stringify({})
				},
				function(response) {
					if (response.exception) {
						var exception = response.exception;

						if (exception.indexOf('DuplicateGadgetURLException') > -1) {
							errorMessage = '<liferay-ui:message key="url-already-points-to-an-existing-gadget" />';
						}
						else {
							errorMessage = '<liferay-ui:message key="url-does-not-point-to-a-valid-gadget" />';
						}

						var errorNode = A.one('#<portlet:namespace />error');

						errorNode.addClass('portlet-msg-error');
						errorNode.set('innerHTML', errorMessage);

						dropColumn.remove(placeHolder);

						return;
					}

					var gadget = response;

					var portletId = 'OPENSOCIAL_' + gadget.uuid.replace(/-/g, '__');

					var portletOptions = {
						onComplete: function(portletBoundary) {
							Layout.syncDraggableClassUI();
							Layout.updatePortletDropZones(portletBoundary);
						},
						placeHolder: placeHolder,
						plid: themeDisplay.getPlid(),
						portletId: portletId
					};

					Liferay.Portlet.add(portletOptions);
				}
			);
		}
	);

	Liferay.Util.focusFormField('#<portlet:namespace />url');
</aui:script>