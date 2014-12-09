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

<aui:form name='fm'>
	<liferay-ui:tabs
		formName="fm"
		names="text,link,image,announcement"
		param="type"
		refresh="<%= false %>"
		tabsValues="text,link,image,announcement"
		type="pills"
	>
		<liferay-ui:section></liferay-ui:section>

		<liferay-ui:section>
			<%@ include file="link.jspf" %>
		</liferay-ui:section>

		<liferay-ui:section>
			<%@ include file="image.jspf" %>
		</liferay-ui:section>

		<liferay-ui:section>
			<%@ include file="announcement.jspf" %>
		</liferay-ui:section>

		<aui:input name="content" type="hidden" />
		<aui:input name="to" type="hidden" />
		<aui:input name="type" type="hidden" value="text" />

		<aui:field-wrapper label="content">
			<div class="content-editable-wrapper" id="<portlet:namespace />textAssetEdit">
				<div class="placeholder"><liferay-ui:message key="enter-your-message" /></div>

				<div class="content-editable content-editable-area"></div>
			</div>
		</aui:field-wrapper>

		<aui:field-wrapper label="to">
			<div class="content-editable-wrapper" id="<portlet:namespace />textAssetTo">
				<div class="placeholder"><liferay-ui:message key="enter-a-name" /></div>

				<div class="content-editable content-editable-inline"></div>
			</div>
		</aui:field-wrapper>

		<aui:button-row>
			<aui:button cssClass="pull-right" type="submit" value="Submit" />
		</aui:button-row>
	</liferay-ui:tabs>
</aui:form>

<aui:script use="aui-io,liferay-asset-feed-input,liferay-portlet-request">
	var contentInput = new Liferay.AssetFeedInput(
		{
			container: 'textAssetEdit',
			hiddenInput: 'content',
			namespace: '<portlet:namespace />'
		}
	).render();

	var toInput = new Liferay.AssetFeedInput(
		{
			container: 'textAssetTo',
			hiddenInput: 'to',
			inline: true,
			namespace: '<portlet:namespace />'
		}
	).render();

	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			event.halt();

			var actionURL = Liferay.PortletURL.createActionURL();

			A.io.request(
				actionURL.toString(),
				{
					dataType: 'JSON',
					form: {
						id: form
					},
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response.success) {
								Liferay.fire('assetFeedNewPost', response);

								contentInput.clearValue();

								toInput.clearValue();
							}
						}
					}
				}
			);
		}
	);
</aui:script>