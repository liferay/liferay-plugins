<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:layout>
		<aui:column id="controls" columnWidth="50">
			<div class="aui-field-row">
				<aui:input cssClass="url" inlineField="true" label="url" name="preferences--url--" value="<%= url %>" />
			</div>

			<div class="aui-field-row">
				<aui:select label="preset-frame-size" name="preferences--presetSize--" inlineField="true" cssClass="preset-size">
					<aui:option label="Custom" value="custom" />
					<aui:option label="Standard 360 4:3" selected='<%= presetSize.equals("480x360") %>' value="480x360" />
					<aui:option label="Standard 360 16:9" selected='<%= presetSize.equals("640x360") %>' value="640x360" />
					<aui:option label="Enhanced 480 4:3" selected='<%= presetSize.equals("640x480") %>' value="640x480" />
					<aui:option label="Enhanced 480 16:9" selected='<%= presetSize.equals("854x480") %>' value="854x480" />
					<aui:option label="HD 720 4:3" selected='<%= presetSize.equals("960x720") %>' value="960x720" />
					<aui:option label="HD 720 16:9" selected='<%= presetSize.equals("1280x720") %>' value="1280x720" />
					<aui:option label="Full HD 1080 4:3" selected='<%= presetSize.equals("1440x1080") %>' value="1440x1080" />
					<aui:option label="Full HD 1080 16:9" selected='<%= presetSize.equals("1920x1080") %>' value="1920x1080" />
				</aui:select>

				<aui:input cssClass="width invisible" inlineField="true" label="frame-width" name="preferences--width--" value="<%= width %>" />

				<aui:input cssClass="height invisible" inlineField="true" label="frame-height" name="preferences--height--" value="<%= height %>" />
			</div>

			<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
				<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="advanced-options">
					<div class="aui-field-row">
						<aui:input cssClass="player-color" inlineField="true" label="player-color" name="preferences--playerColor--" value="<%= playerColor %>" />

						<aui:input cssClass="border-color" inlineField="true" label="border-color" name="preferences--borderColor--" value="<%= borderColor %>" />
					</div>

					<aui:input cssClass="show-thicker-border" inlineField="true" label="show-thicker-border" name="preferences--showThickerBorder--" value="<%= showThickerBorder %>" type="checkbox" />

					<aui:input cssClass="show-thumbnail" inlineField="true" label="show-thumbnail" name="preferences--showThumbnail--" value="<%= showThumbnail %>" type="checkbox" />

					<div class="aui-field-row">
						<aui:input cssClass="hd" inlineField="true" label="hd-video" name="preferences--hd--" value="<%= hd %>" type="checkbox" />

						<aui:input cssClass="autoplay" inlineField="true" label="auto-play" name="preferences--autoplay--" value="<%= autoplay %>" type="checkbox" />

						<aui:input cssClass="loop" inlineField="true" label="loop" name="preferences--loop--" value="<%= loop %>" type="checkbox" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="enable-fullscreen" inlineField="true" label="enable-fullscreen-option" name="preferences--enableFullscreen--" value="<%= enableFullscreen %>" type="checkbox" />

						<aui:input checked="<%= enableKeyboardControls %>" cssClass="enable-keyboard-controls" inlineField="true" label="enable-keyboard-controls" name="preferences--enableKeyboardControls--" value="<%= enableKeyboardControls %>" type="checkbox" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="show-info" inlineField="true" label="show-video-info" name="preferences--showInfo--" value="<%= showInfo %>" type="checkbox" />

						<aui:input cssClass="enable-enhanced-genie-menu" helpMessage="enhanced-genie-menu-help" inlineField="true" label="enable-enhanced-genie-menu" name="preferences--enableEnhancedGenieMenu--" value="<%= enableEnhancedGenieMenu %>" type="checkbox" />

						<aui:input cssClass="enable-search" inlineField="true" label="enable-search" name="preferences--enableSearch--" type="checkbox" value="<%= enableSearch %>" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="start-time" inlineField="true" label="start-time" name="preferences--startTime--" value="<%= startTime %>" />

						<aui:select label="annotations" name="annotations" value="<%= annotations %>" inlineField="true" cssClass="annotations">
							<aui:option label="show-by-default" selected="<%= annotations == 1 %>" value="1" />
							<aui:option label="do-not-show-by-default" selected="<%= annotations == 3 %>" value="3" />
						</aui:select>

						<aui:select label="closed-captioning" name="preferences--closedCaptioning--" inlineField="true" cssClass="closed-captioning">
							<aui:option label="show-by-default" selected="<%= closedCaptioning == 1 %>" value="1" />
							<aui:option label="do-not-show-by-default" selected="<%= closedCaptioning == 0 %>" value="0" />
						</aui:select>
					</div>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</aui:column>

		<aui:column columnWidth="50">
			<div class="aui-field-wrapper-content" id="preview"></div>
		</aui:column>
	</aui:layout>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-color-picker,aui-datatype,aui-swf">
	var createPlayer = function() {
		var id = urlToVideoId(urlNode.val());
		var height = parseInt(heightNode.val(), 10) || 0;
		var maxWidth = (formNode.get('clientWidth') || formNode.get('scrollWidth')) - (controlsNode.get('clientWidth') || controlsNode.get('scrollWidth'));
		var playerOptions = {
			border: showThickerBorderNode.val(),
			cc_load_policy: closedCaptioningNode.val(),
			color1: encodeHex(borderColorNode.val()),
			color2: encodeHex(playerColorNode.val()),
			disablekb: (!A.DataType.Boolean.parse(enableKeyboardControlsNode.val())).toString(),
			egm: enableEnhancedGenieMenuNode.val(),
			fs: enableFullscreenNode.val(),
			hd: hdNode.val(),
			iv_load_policy: annotationsNode.val(),
			rel: enableEnhancedGenieMenuNode.val(),
			showinfo: showInfoNode.val(),
			showsearch: enableSearchNode.val(),
			start: startTimeNode.val()
		};
		var width = parseInt(widthNode.val(), 10) || 0;

		var playerOptionsCompiled = [swfURL + id];
		var ratio = Math.min(maxWidth / width, 1);

		height = Math.floor(height * ratio);
		width = Math.floor(width * ratio);

		for (var i in playerOptions) {
			if (playerOptions[i]) {
				playerOptionsCompiled.push(i + '=' + playerOptions[i].replace(/^true$/, '1').replace(/^false$/, '0'));
			}
		}

		if (id) {
			previewNode.setContent(['<a href="', watchURL, id, '" rel="external" title="watch-this-video-at-youtube"><img src="', imageURL.replace('<%= id %>', id), '" alt="youtube-video" width="100%" height="100%" /></a>'].join(''));

			new A.SWF(
				{
					boundingBox: previewNode,
					height: height,
					url: playerOptionsCompiled.join('&'),
					width: width,
					version: 0
				}
			).render();
		}
		else {
			previewNode.setStyles(
				{
					height: height,
					width: width
				}
			);
		}
	};

	var encodeHex = function(hex) {
		return (hex) ? '0x' + hex.replace('#', '').replace(/^(.)(.)(.)$/, '$1$1$2$2$3$3').toLowerCase() : '';
	};

	var presetChange = function(e) {
		if (this.val().indexOf('x') < 0) {
			A.one('.aui-field.height').removeClass('invisible');
			A.one('.aui-field.width').removeClass('invisible');

			return;
		}

		var dimensions = this.val().split('x');

		heightNode.val(dimensions[1]);
		widthNode.val(dimensions[0]);

		createPlayer();
	};

	var urlToVideoId = function(url) {
		return url.replace(/^.*?v=([a-zA-Z0-9_\-]+).*$/, '$1');
	};

	var allInputsNode = A.all('#<portlet:namespace />fm input');

	var formNode = A.one('#<portlet:namespace />fm');

	var controlsNode = A.one('#controls');
	var previewNode = A.one('#preview');

	var annotationsNode = A.one('#<portlet:namespace />annotations');
	var borderColorNode = A.one('#<portlet:namespace />borderColor');
	var closedCaptioningNode = A.one('#<portlet:namespace />closedCaptioning');
	var enableEnhancedGenieMenuNode = A.one('#<portlet:namespace />enableEnhancedGenieMenu');
	var enableFullscreenNode = A.one('#<portlet:namespace />enableFullscreen');
	var enableKeyboardControlsNode = A.one('#<portlet:namespace />enableKeyboardControls');
	var enableSearchNode = A.one('#<portlet:namespace />enableSearch');
	var hdNode = A.one('#<portlet:namespace />hd');
	var heightNode = A.one('#<portlet:namespace />height');
	var playerColorNode = A.one('#<portlet:namespace />playerColor');
	var presetSizeNode = A.one('#<portlet:namespace />presetSize');
	var showInfoNode = A.one('#<portlet:namespace />showInfo');
	var showThickerBorderNode = A.one('#<portlet:namespace />showThickerBorder');
	var startTimeNode = A.one('#<portlet:namespace />startTime');
	var urlNode = A.one('#<portlet:namespace />url');
	var widthNode = A.one('#<portlet:namespace />width');

	var imageURL = '<%= imageURL %>';
	var swfURL = '<%= _SWF_URL %>';
	var watchURL = '<%= _WATCH_URL %>';

	A.on(
		'change',
		function(e) {
			createPlayer();
		},
		allInputsNode
	);

	presetSizeNode.on(
		{
			change: presetChange,
			keypress: presetChange
		}
	);

	A.on(
		'change',
		function(e) {
			presetSizeNode.val('');

			presetSizeNode.val(widthNode.val() + 'x' + heightNode.val());
		},
		heightNode
	);

	A.on(
		'change',
		function(e) {
			presetSizeNode.val('');

			presetSizeNode.val(widthNode.val() + 'x' + heightNode.val());
		},
		widthNode
	);

	A.on(
		'click',
		function(e) {
			e.preventDefault();

			submitForm(document['<portlet:namespace />fm']);
		},
		'input.aui-button-input-submit'
	);

	A.on(
		'windowresize',
		function(e) {
			createPlayer();
		}
	);

	new A.ColorPicker(
		{
			after: {
				colorChange: function(e) {
					playerColorNode.val('#' + this.get('hex'));

					createPlayer();
				}
			},
			constrain: true,
			preventOverlap: true,
			triggerParent: playerColorNode.get('parentNode')
		}
	).render();

	new A.ColorPicker(
		{
			after: {
				colorChange: function(e) {
					borderColorNode.val('#' + this.get('hex'));

					createPlayer();
				}
			},
			constrain: true,
			preventOverlap: true,
			triggerParent: borderColorNode.get('parentNode')
		}
	).render();

	if (presetSizeNode.val() == 'custom') {
		A.one('.aui-field.height').removeClass('invisible');
		A.one('.aui-field.width').removeClass('invisible');
	}

	createPlayer();
</aui:script>