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
		<aui:column columnWidth="50" id="controls">
			<div class="aui-field-row">
				<aui:input cssClass="url" inlineField="true" label="url" name="preferences--url--" value="<%= url %>" />
			</div>

			<div class="aui-field-row">
				<aui:select cssClass="preset-size" inlineField="true" label="preset-frame-size" name="preferences--presetSize--">
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
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="autoplay" inlineField="true" label="auto-play" name="preferences--autoplay--" type="checkbox" value="<%= autoplay %>" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="enable-fullscreen" inlineField="true" label="enable-fullscreen-option" name="preferences--enableFullscreen--" type="checkbox" value="<%= enableFullscreen %>" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="show-byline" inlineField="true" label="show-byline" name="preferences--showByline--" type="checkbox" value="<%= showByline %>" />

						<aui:input cssClass="show-portrait" inlineField="true" label="show-portrait" name="preferences--showPortrait--" type="checkbox" value="<%= showPortrait %>" />

						<aui:input cssClass="show-title" inlineField="true" label="show-title" name="preferences--showTitle--" type="checkbox" value="<%= showTitle %>" />
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
	var swfURL = '<%= _SWF_URL %>';
	var watchURL = '<%= _WATCH_URL %>';

	var allInputsNode = A.all('#<portlet:namespace />fm input');

	var formNode = A.one('#<portlet:namespace />fm');

	var controlsNode = A.one('#controls');
	var previewNode = A.one('#preview');

	var autoplayNode = A.one('#<portlet:namespace />autoplay');
	var enableFullscreenNode = A.one('#<portlet:namespace />enableFullscreen');
	var heightNode = A.one('#<portlet:namespace />height');
	var playerColorNode = A.one('#<portlet:namespace />playerColor');
	var presetSizeNode = A.one('#<portlet:namespace />presetSize');
	var showBylineNode = A.one('#<portlet:namespace />showByline');
	var showPortraitNode = A.one('#<portlet:namespace />showPortrait');
	var showTitleNode = A.one('#<portlet:namespace />showTitle');
	var urlNode = A.one('#<portlet:namespace />url');
	var widthNode = A.one('#<portlet:namespace />width');

	function presetChange(e) {
		if (this.val().indexOf('x') < 0) {
			A.one('.aui-field.height').removeClass('invisible');
			A.one('.aui-field.width').removeClass('invisible');

			return;
		}

		var dimensions = this.val().split('x');

		heightNode.val(dimensions[1]);
		widthNode.val(dimensions[0]);

		createPlayer();
	}

	function encodeBinary(value) {
		return (value) ? 1 : 0;
	}

	function encodeHex(hex) {
		return (hex) ? hex.replace('#', '').toLowerCase() : '';
	}

	function urlToVideoId(url) {
		return url.replace(/^.*vimeo\.com\/([0-9]+).*$/, '$1');
	}

	function createPlayer() {
		var id = urlToVideoId(urlNode.val());
		var height = parseInt(heightNode.val(), 10) || 0;
		var maxWidth = (formNode.get('clientWidth') || formNode.get('scrollWidth')) - (controlsNode.get('clientWidth') || controlsNode.get('scrollWidth'));
		var playerOptions = {
			autoplay: encodeBinary(autoplayNode.val()),
			clip_id: id,
			color: encodeHex(playerColorNode.val()),
			fullscreen: encodeBinary(enableFullscreenNode.val()),
			server: 'vimeo.com',
			show_byline: encodeBinary(showBylineNode.val()),
			show_portrait: encodeBinary(showPortraitNode.val()),
			show_title: encodeBinary(showTitleNode.val())
		};
		var width = parseInt(widthNode.val(), 10) || 0;

		var playerOptionsCompiled = [swfURL + '?'];
		var ratio = Math.min(maxWidth / width, 1);

		height = Math.floor(height * ratio);
		width = Math.floor(width * ratio);

		for (var i in playerOptions) {
			if (playerOptions[i]) {
				playerOptionsCompiled.push(i + '=' + playerOptions[i]);
			}
		}

		if (id) {
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
	}

	A.on(
		'change',
		function (e) {
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
		function (e) {
			presetSizeNode.val('');

			presetSizeNode.val(widthNode.val() + 'x' + heightNode.val());
		},
		heightNode
	);

	A.on(
		'change',
		function (e) {
			presetSizeNode.val('');

			presetSizeNode.val(widthNode.val() + 'x' + heightNode.val());
		},
		widthNode
	);

	A.on(
		'click',
		function (e) {
			e.preventDefault();

			submitForm(document['<portlet:namespace />fm']);
		},
		'input.aui-button-input-submit'
	);

	A.on(
		'windowresize',
		function (e) {
			createPlayer();
		}
	);

	new A.ColorPicker(
		{
			after: {
				colorChange: function (e) {
					playerColorNode.val('#' + this.get('hex'));

					createPlayer();
				}
			},
			constrain: true,
			preventOverlap: true,
			triggerParent: playerColorNode.get('parentNode')
		}
	).render();

	if (presetSizeNode.val() == 'custom') {
		A.one('.aui-field.height').removeClass('invisible');
		A.one('.aui-field.width').removeClass('invisible');
	}

	createPlayer();
</aui:script>