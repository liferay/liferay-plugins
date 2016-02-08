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

<aui:form action="<%= configurationActionURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:row>
		<aui:col id="controls" width="<%= 50 %>">
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
					<div class="field-row">
						<aui:input cssClass="player-color" inlineField="true" label="player-color" name="preferences--playerColor--" value="<%= playerColor %>" />
					</div>

					<div class="field-row">
						<aui:input cssClass="autoplay" inlineField="true" label="auto-play" name="preferences--autoplay--" type="checkbox" value="<%= autoplay %>" />
					</div>

					<div class="field-row">
						<aui:input cssClass="enable-fullscreen" inlineField="true" label="enable-fullscreen-option" name="preferences--enableFullscreen--" type="checkbox" value="<%= enableFullscreen %>" />
					</div>

					<div class="field-row">
						<aui:input cssClass="show-byline" inlineField="true" label="show-byline" name="preferences--showByline--" type="checkbox" value="<%= showByline %>" />

						<aui:input cssClass="show-portrait" inlineField="true" label="show-portrait" name="preferences--showPortrait--" type="checkbox" value="<%= showPortrait %>" />

						<aui:input cssClass="show-title" inlineField="true" label="show-title" name="preferences--showTitle--" type="checkbox" value="<%= showTitle %>" />
					</div>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</aui:col>

		<aui:col width="<%= 50 %>">
			<div class="field-wrapper-content preview" id="<portlet:namespace />preview">
				<i class="icon-youtube-play preview-play"></i>
			</div>
		</aui:col>
	</aui:row>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-color-picker-popover,aui-datatype,aui-swf-deprecated">
	var swfURL = '<%= HttpUtil.getProtocol(request) + _SWF_URL %>';

	var allInputsNode = A.all('#<portlet:namespace />fm input');

	var formNode = A.one('#<portlet:namespace />fm');

	var controlsNode = A.one('#<portlet:namespace />controls');
	var previewNode = A.one('#<portlet:namespace />preview');

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

	var player;

	function presetChange(e) {
		if (this.val().indexOf('x') < 0) {
			A.one('#<portlet:namespace />height').ancestor('.control-group').removeClass('invisible');
			A.one('#<portlet:namespace />width').ancestor('.control-group').removeClass('invisible');

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
		var height = parseInt(heightNode.val(), 10) || 0;
		var id = urlToVideoId(urlNode.val());
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
			player = new A.SWF(
				{
					boundingBox: previewNode,
					height: height,
					url: playerOptionsCompiled.join('&'),
					version: 0,
					width: width
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
		'input.button-input-submit'
	);

	A.on(
		'windowresize',
		function(e) {
			createPlayer();
		}
	);

	new A.ColorPickerPopover(
		{
			on: {
				select: function(event) {
					playerColorNode.val(event.color);

					createPlayer();
				}
			},
			trigger: playerColorNode
		}
	).render();

	if (presetSizeNode.val() == 'custom') {
		A.one('#<portlet:namespace />height').ancestor('.control-group').removeClass('invisible');
		A.one('#<portlet:namespace />width').ancestor('.control-group').removeClass('invisible');
	}

	var dialog = Liferay.Util.getWindow();

	if (dialog !== A.config.win) {
		dialog.once(
			'visibleChange',
			function(event) {
				if (player && !event.newVal) {
					player.destroy();
				}
			}
		);
	}

	createPlayer();
</aui:script>