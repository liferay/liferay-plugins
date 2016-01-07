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

	<aui:layout>
		<aui:column columnWidth="50" id="controls">
			<div class="field-row">
				<aui:input cssClass="url" inlineField="true" label="url" name="preferences--url--" value="<%= url %>" />
			</div>

			<div class="field-row">
				<aui:select cssClass="preset-size" inlineField="true" label="preset-frame-size" name="preferences--presetSize--" value="<%= presetSize %>">
					<aui:option label="Custom" value="custom" />
					<aui:option label="Standard 360 4:3" value="480x360" />
					<aui:option label="Standard 360 16:9" value="640x360" />
					<aui:option label="Enhanced 480 4:3" value="640x480" />
					<aui:option label="Enhanced 480 16:9" value="854x480" />
					<aui:option label="HD 720 4:3" value="960x720" />
					<aui:option label="HD 720 16:9" value="1280x720" />
					<aui:option label="Full HD 1080 4:3" value="1440x1080" />
					<aui:option label="Full HD 1080 16:9" value="1920x1080" />
				</aui:select>

				<aui:input cssClass="width" inlineField="true" label="frame-width" name="preferences--width--" value="<%= width %>" wrapperCssClass="invisible" />

				<aui:input cssClass="height" inlineField="true" label="frame-height" name="preferences--height--" value="<%= height %>" wrapperCssClass="invisible" />
			</div>

			<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
				<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="advanced-options">
					<aui:input cssClass="show-thumbnail" inlineField="true" label="show-thumbnail" name="preferences--showThumbnail--" type="checkbox" value="<%= showThumbnail %>" />

					<div class="field-row">
						<aui:input cssClass="autoplay" inlineField="true" label="auto-play" name="preferences--autoplay--" type="checkbox" value="<%= autoplay %>" />

						<aui:input cssClass="loop" inlineField="true" label="loop" name="preferences--loop--" type="checkbox" value="<%= loop %>" />
					</div>

					<div class="field-row">
						<aui:input checked="<%= enableKeyboardControls %>" cssClass="enable-keyboard-controls" inlineField="true" label="enable-keyboard-controls" name="preferences--enableKeyboardControls--" type="checkbox" value="<%= enableKeyboardControls %>" />
					</div>

					<div class="field-row">
						<aui:input cssClass="annotations" inlineField="true" label="annotations" name="preferences--annotations--" type="checkbox" value="<%= annotations %>" />

						<aui:input cssClass="closedCaptioning" inlineField="true" label="closed-captioning" name="preferences--closedCaptioning--" type="checkbox" value="<%= closedCaptioning %>" />
					</div>

					<div class="field-row">
						<aui:input cssClass="start-time" inlineField="true" label="start-time" name="preferences--startTime--" value="<%= startTime %>" />
					</div>
				</liferay-ui:panel>
			</liferay-ui:panel-container>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:column>

		<aui:column columnWidth="50">
			<div class="field-wrapper-content preview" id="<portlet:namespace />preview">
				<i class="icon-youtube-play preview-play"></i>
			</div>
		</aui:column>
	</aui:layout>
</aui:form>

<aui:script use="aui-base,aui-datatype,aui-swf-deprecated">
	var Lang = A.Lang;

	var formNode = A.one('#<portlet:namespace />fm');

	var heightNode = formNode.one('#<portlet:namespace />height');
	var presetSizeNode = formNode.one('#<portlet:namespace />presetSize');
	var widthNode = formNode.one('#<portlet:namespace />width');

	var createPlayer = function() {
		var height = Lang.toInt(heightNode.val()) || 0;

		var annotationsNode = formNode.one('#<portlet:namespace />annotations');
		var closedCaptioningNode = formNode.one('#<portlet:namespace />closedCaptioning');
		var enableKeyboardControlsNode = formNode.one('#<portlet:namespace />enableKeyboardControls');
		var startTimeNode = formNode.one('#<portlet:namespace />startTime');

		if (annotationsNode && closedCaptioningNode && enableKeyboardControlsNode && startTimeNode) {
			var playerOptions = {
				cc_load_policy: closedCaptioningNode.val(),
				disablekb: (!A.DataType.Boolean.parse(enableKeyboardControlsNode.val())).toString(),
				iv_load_policy: annotationsNode.val(),
				start: startTimeNode.val()
			};

			var width = Lang.toInt(widthNode.val()) || 0;

			var playerOptionsCompiled = ['wmode=transparent'];

			var controlsNode = formNode.one('#<portlet:namespace />controls');

			if (controlsNode) {
				var controlsNodeWidth = controlsNode.get('clientWidth') || controlsNode.get('scrollWidth');
				var formNodeWidth = formNode.get('clientWidth') || formNode.get('scrollWidth');

				var maxWidth = formNodeWidth - controlsNodeWidth;

				var ratio = Math.min(maxWidth / width, 1);

				height = Math.floor(height * ratio);
				width = Math.floor(width * ratio);

				for (var i in playerOptions) {
					var currentOption = playerOptions[i];

					if (currentOption) {
						playerOptionsCompiled.push(i + '=' + currentOption.replace(/^true$/, '1').replace(/^false$/, '0'));
					}
				}

				var urlNode = formNode.one('#<portlet:namespace />url');

				if (urlNode) {
					var urlValue = urlNode.val();

					var id = urlValue.replace(/^.*?v=([a-zA-Z0-9_\-]+).*$/, '$1');

					var previewNode = formNode.one('#<portlet:namespace />preview');

					if (id) {
						previewNode.setContent(
							[
								'<iframe frameborder="0" height="', height, '" src="', '<%= embedURL %>', id, '?', playerOptionsCompiled.join('&'), '" width="', width, '" wmode="Opaque"></iframe>'
							].join('')
						);
					}
					else {
						previewNode.setStyles(
							{
								height: height,
								width: width
							}
						);
					}
				}
			}
		}
	};

	var presetChange = function(event) {
		var currentTarget = event.currentTarget;

		var presetValue = currentTarget.val();

		if (presetValue) {
			if (presetValue.indexOf('x') < 0) {
				var heightNodeControlGroup = heightNode.ancestor('.form-group');

				if (heightNodeControlGroup) {
					heightNodeControlGroup.removeClass('invisible');
				}

				var widthNodeControlGroup = widthNode.ancestor('.form-group');

				if (widthNodeControlGroup) {
					widthNodeControlGroup.removeClass('invisible');
				}
			}
			else {
				var dimensions = presetValue.split('x');

				heightNode.val(dimensions[1]);
				widthNode.val(dimensions[0]);

				createPlayer();
			}
		}
	};

	var updatePresetSize = function(event) {
		presetSizeNode.val('');

		presetSizeNode.val(widthNode.val() + 'x' + heightNode.val());
	};

	A.delegate(
		'change',
		function(event) {
			createPlayer();
		},
		formNode.all('input')
	);

	if (presetSizeNode) {
		presetSizeNode.on(
			{
				change: presetChange,
				keypress: presetChange
			}
		);
	}

	heightNode.on(
		'change',
		function(event) {
			updatePresetSize();
		}
	);

	widthNode.on(
		'change',
		function(event) {
			updatePresetSize();
		}
	);

	A.on(
		'windowresize',
		function(event) {
			createPlayer();
		}
	);

	createPlayer();
</aui:script>

<aui:script>
	function <portlet:namespace />saveConfiguration() {
		var form = AUI.$(document.<portlet:namespace />fm);

		submitForm(form);
	}
</aui:script>