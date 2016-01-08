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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset id="controls" label="video-properties">
		<aui:input label="video-id" name="preferences--url--" value="<%= url %>" />

		<aui:select inlineField="<%= true %>" label="preset-frame-size" name="preferences--presetSize--" onChange='<%= renderResponse.getNamespace() + "updateFrameSize(this.value);" %>' value="<%= presetSize %>">
			<aui:option label="custom" value="custom" />
			<aui:option label="standard-360-4-3" value="480x360" />
			<aui:option label="standard-360-16-9" value="640x360" />
			<aui:option label="enhanced-480-4-3" value="640x480" />
			<aui:option label="enhanced-480-16-9" value="854x480" />
			<aui:option label="hd-720-4-3" value="960x720" />
			<aui:option label="hd-720-16-9" value="1280x720" />
			<aui:option label="full-hd-1080-4-3" value="1440x1080" />
			<aui:option label="full-hd-1080-16-9" value="1920x1080" />
		</aui:select>

		<aui:input disabled="<%= true %>" inlineField="<%= true %>" label="frame-width" name="preferences--width--" value="<%= width %>" />

		<aui:input disabled="<%= true %>" inlineField="<%= true %>" label="frame-height" name="preferences--height--" value="<%= height %>" />

		<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="advanced-options">
				<aui:input label="show-thumbnail" name="preferences--showThumbnail--" type="checkbox" value="<%= showThumbnail %>" />

				<aui:fieldset>
					<aui:input inlineField="<%= true %>" label="auto-play" name="preferences--autoplay--" type="checkbox" value="<%= autoplay %>" />

					<aui:input inlineField="<%= true %>" label="loop" name="preferences--loop--" type="checkbox" value="<%= loop %>" />
				</aui:fieldset>

				<aui:fieldset>
					<aui:input checked="<%= enableKeyboardControls %>" inlineField="<%= true %>" label="enable-keyboard-controls" name="preferences--enableKeyboardControls--" type="checkbox" value="<%= enableKeyboardControls %>" />
				</aui:fieldset>

				<aui:fieldset>
					<aui:input inlineField="<%= true %>" label="annotations" name="preferences--annotations--" type="checkbox" value="<%= annotations %>" />

					<aui:input inlineField="<%= true %>" label="closed-captioning" name="preferences--closedCaptioning--" type="checkbox" value="<%= closedCaptioning %>" />
				</aui:fieldset>

				<aui:input label="start-time" name="preferences--startTime--" value="<%= startTime %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />saveConfiguration() {
		var form = AUI.$(document.<portlet:namespace />fm);

		submitForm(form);
	}

	function <portlet:namespace />updateFrameSize(value) {
		var Util = Liferay.Util;

		var notCustom = value != 'custom';

		var heightNode = AUI.$('#<portlet:namespace />height');
		var widthNode = AUI.$('#<portlet:namespace />width');

		Util.toggleDisabled(heightNode, notCustom);
		Util.toggleDisabled(widthNode, notCustom);

		if (notCustom) {
			var dimensions = value.split('x');

			heightNode.val(dimensions[1]);
			widthNode.val(dimensions[0]);
		}
	}
</aui:script>