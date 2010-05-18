<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="init.jsp" %>

<style type="text/css">
	#preview {
		-moz-border-radius: 4px;
		-webkit-border-radius: 4px;
		background: #4EBAFF url(/vimeo-portlet/images/preview_bg.png) no-repeat center center;
		border-radius: 4px;
		float: right;
		height: 30.5em;
	}
	#<portlet:namespace />fm select {
		padding: 4px;
	}

	#<portlet:namespace />fm .aui-field-inline {
		vertical-align: text-top;
	}

	#<portlet:namespace />fm .aui-field-label {
		vertical-align: bottom;
	}

	#<portlet:namespace />fm .aui-field-row .aui-field-content {
		margin-right: 1em;
	}

	#<portlet:namespace />fm .aui-field-input-choice {
		margin-right: 0.3em;
	}

	#<portlet:namespace />fm .url .aui-field-input {
		width: 30em;
	}

	#<portlet:namespace />fm .height .aui-field-input,
	#<portlet:namespace />fm .start-time .aui-field-input,
	#<portlet:namespace />fm .width .aui-field-input {
		width: 4.9em;
	}

	#<portlet:namespace />fm .aui-colorpicker-trigger {
		vertical-align: top;
	}

	#<portlet:namespace />fm .aui-field-text .aui-field-label,
	#<portlet:namespace />fm .aui-field-select .aui-field-label {
		display: block;
	}
	.lfr-panel-container {
		border: 0;
	}
	.annotations .aui-field-input,
	.closed-captioning .aui-field-input {
		width: 11em;
	}
	.invisible {
		visibility: hidden;
	}
</style>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:layout>
		<aui:column id="controls" columnWidth="50">
			<div class="aui-field-row">
				<aui:input cssClass="url" inlineField="true" label="url" name="url" value="<%= url %>" />
			</div>

			<div class="aui-field-row">
				<aui:select label="preset-frame-size" name="presetSize" inlineField="true" cssClass="preset-size">
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

				<aui:input cssClass="width invisible" inlineField="true" label="frame-width" name="width" value="<%= width %>" />

				<aui:input cssClass="height invisible" inlineField="true" label="frame-height" name="height" value="<%= height %>" />
			</div>

			<liferay-ui:panel-container extended="<%= false %>" persistState="<%= true %>">
				<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "advanced-options") %>'>
					<div class="aui-field-row">
						<aui:input cssClass="player-color" inlineField="true" label="player-color" name="playerColor" value="<%= playerColor %>" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="autoplay" inlineField="true" label="auto-play" name="autoplay" value="<%= autoplay %>" type="checkbox" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="enable-fullscreen" inlineField="true" label="enable-fullscreen-option" name="enableFullscreen" value="<%= enableFullscreen %>" type="checkbox" />
					</div>

					<div class="aui-field-row">
						<aui:input cssClass="show-byline" inlineField="true" label="show-byline" name="showByline" value="<%= showByline %>" type="checkbox" />
						<aui:input cssClass="show-portrait" inlineField="true" label="show-portrait" name="showPortrait" value="<%= showPortrait %>" type="checkbox" />
						<aui:input cssClass="show-title" inlineField="true" label="show-title" name="showTitle" value="<%= showTitle %>" type="checkbox" />
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

		<aui:button type="reset" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-color-picker,aui-datatype,aui-swf">
	var encodeHex = function (hex) {
			return (hex) ? hex.replace('#', '').replace(/^(.)(.)(.)$/, '$1$1$2$2$3$3').toLowerCase() : '';
		},
		urlToVideoId = function (url) {
			return url.replace(/.*vimeo.com\/([0-9]+).*/, '$1');
		},
		createPlayer = function () {
			console.log('createPlayer');
			var id = urlToVideoId(urlNode.val()),
				height = parseInt(heightNode.val(), 10) || 0,
				maxWidth = (formNode.get('clientWidth') || formNode.get('scrollWidth')) - (controlsNode.get('clientWidth') || controlsNode.get('scrollWidth')),
				playerOptions = {
					color: encodeHex(playerColorNode.val()),
					fullscreen: enableFullscreenNode.val(),
					server: 'vimeo.com',
					show_title: showTitleNode.val(),
					show_byline: showBylineNode.val(),
					show_portrait: showPortraitNode.val()
				},
				playerOptionsCompiled,
				ratio,
				value,
				width = parseInt(widthNode.val(), 10) || 0,
				x;

			ratio = Math.min(maxWidth / width, 1);
			height = Math.floor(height * ratio);
			width = Math.floor(width * ratio);

			playerOptionsCompiled = [swfURL + '?clip_id=' + id];

			for (x in playerOptions) {
				if (playerOptions[x] != "") {
					playerOptionsCompiled.push(x + '=' + playerOptions[x].replace(/^true$/, '1').replace(/^false$/, '0'));
				}
			}

			if (id) {
				console.log('swf', playerOptionsCompiled.join('&amp;'));

				new A.SWF(
					{
						boundingBox: previewNode,
						height: height,
						url: playerOptionsCompiled.join('&amp;'),
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
		},
		presetChange = function (e) {
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

	var allInputsNode = A.all('#<portlet:namespace />fm input');

	var formNode = A.one('#<portlet:namespace />fm');

	var controlsNode = A.one('#controls');
	var previewNode = A.one('#preview');

	var enableFullscreenNode = A.one('#<portlet:namespace />enableFullscreen');
	var heightNode = A.one('#<portlet:namespace />height');
	var playerColorNode = A.one('#<portlet:namespace />playerColor');
	var presetSizeNode = A.one('#<portlet:namespace />presetSize');
	var showBylineNode = A.one('#<portlet:namespace />showTitle');
	var showPortraitNode = A.one('#<portlet:namespace />showPortrait');
	var showTitleNode = A.one('#<portlet:namespace />showTitle');
	var urlNode = A.one('#<portlet:namespace />url');
	var widthNode = A.one('#<portlet:namespace />width');

	var swfURL = '<%= swfURL %>';
	var watchURL = '<%= watchURL %>';

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

			submitForm(document.<portlet:namespace />fm);
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

	if(presetSizeNode.val() == 'custom') {
		A.one('.aui-field.height').removeClass('invisible');
		A.one('.aui-field.width').removeClass('invisible');
	}

	createPlayer();
</aui:script>