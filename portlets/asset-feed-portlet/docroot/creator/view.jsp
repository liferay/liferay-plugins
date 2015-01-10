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

<aui:form name="fm">
	<aui:input name="content" type="hidden" />
	<aui:input name="type" type="hidden" value="text" />

	<aui:field-wrapper label="content">
		<div class="content-editable-wrapper" id="<portlet:namespace />textAssetEdit">
			<div class="placeholder"><liferay-ui:message key="enter-your-message" /></div>

			<div class="content-editable content-editable-area"></div>
		</div>
	</aui:field-wrapper>

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
			<%@ include file="/creator/link.jspf" %>
		</liferay-ui:section>

		<liferay-ui:section>
			<%@ include file="/creator/image.jspf" %>
		</liferay-ui:section>

		<liferay-ui:section>
			<%@ include file="/creator/announcement.jspf" %>
		</liferay-ui:section>
	</liferay-ui:tabs>

	<aui:field-wrapper label="to">
		<div class="asset-feed-list-content" id="<portlet:namespace />textAssetTo">
			<div class="asset-feed-list-container" id="<portlet:namespace />textAssetToContainer"></div>

			<aui:input cssClass="asset-feed-list-input" label="" name="textAssetToInput" type="text" />
		</div>
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button cssClass="pull-right" type="submit" value="Submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-form-validator,aui-io,liferay-asset-feed-autocomplete,liferay-asset-feed-input-list,liferay-asset-feed-input,liferay-portlet-request">
	var contentInputComponent = new Liferay.AssetFeedInput(
		{
			container: 'textAssetEdit',
			form: 'fm',
			namespace: '<portlet:namespace />'
		}
	).render();

	var toInputComponent = new Liferay.AssetFeedInputList(
		{
			container: 'textAssetToContainer',
			contentBox: 'textAssetTo',
			inputNode: 'textAssetToInput',
			namespace: '<portlet:namespace />'
		}
	).render();

	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			event.halt();

			var data = contentInputComponent.handleFormSubmission();

			if (data) {
				Liferay.Service(
					'/asset-entry-set-portlet.assetentryset/add-asset-entry-set',
					{
						file: '',
						payload: A.JSON.stringify(
							{
								linkData: data.linkData,
								message: data.message,
								type: data.type
							}
						),
						privateAssetEntrySet: false
					},
					function(result) {
						if (A.Object.hasKey(result, 'assetEntrySetId')) {
							Liferay.fire('assetFeedNewPost', result);

							contentInputComponent.clearValue();
							toInputComponent.clearValue();

							var linkPreviewContainer = A.one('#<portlet:namespace />linkPreviewContainer');

							if (linkPreviewContainer) {
								linkPreviewContainer.html('');
							}

							var linkURLInput = A.one('#<portlet:namespace />linkURL');

							if (linkURLInput) {
								linkURLInput.val('');
							}
						}
					}
				);
			}
		}
	);

	var contentInputNode = A.one('#<portlet:namespace />textAssetEdit .content-editable');

	new Liferay.AssetFeedAutoComplete(
		{
			inputNode: contentInputNode,
			namespace: '<portlet:namespace />'
		}
	);

	new A.FormValidator(
		{
			boundingBox: '#<portlet:namespace />fm',
			rules: {
				<portlet:namespace />linkURL: {
					required: false,
					url: true
				}
			}
		}
	);
</aui:script>