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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
String googleAPIKey = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), "googleAPIKey");
String googleClientId = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), "googleClientId");
%>

<aui:script>
	Liferay.provide(
		window,
		'onGoogleAPILoad',
		function() {
			Liferay.fire('googleAPILoad');
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base">
	var clientId = '<%= googleClientId %>';

	var developerKey = '<%= googleAPIKey %>';

	var scope = [
		'https://www.googleapis.com/auth/drive.readonly',
		'https://www.googleapis.com/auth/photos.upload'
	];

	var authAPILoaded = false;
	var googleAPILoaded = false;
	var pickerAPILoaded = false;

	var oauthToken;
	var openPickerOnGAPILoaded = false;

	Liferay.on(
		'googleAPILoad',
		function() {
			googleAPILoaded = true;

			if (openPickerOnGAPILoaded) {
				openPicker();
			}
		}
	);

	A.one('#<portlet:namespace />fm').delegate(
		'click',
		function(event) {
			openPicker();
		},
		'.add-google-shortcut'
	);

	var openPicker = function() {
		if (googleAPILoaded) {
			gapi.load(
				'auth',
				{
					'callback': onAuthAPILoad
				}
			);
			gapi.load(
				'picker',
				{
					'callback': onPickerAPILoad
				}
			);
		}
		else {
			openPickerOnGAPILoaded = true;
		}
	};

	var onAuthAPILoad = function() {
		window.gapi.auth.authorize(
			{
				'client_id': clientId,
				'immediate': false,
				'scope': scope
			},
			function(authResult) {
				if (authResult && !authResult.error) {
					oauthToken = authResult.access_token;

					authAPILoaded = true;

					createPicker();
				}
			}
		);
	};

	var onPickerAPILoad = function() {
		pickerAPILoaded = true;

		createPicker();
	};

	var createPicker = function() {
		if (pickerAPILoaded && authAPILoaded) {
			var ViewId = google.picker.ViewId;

			var groupDocuments = new google.picker.ViewGroup(ViewId.DOCS);

			groupDocuments.addView(ViewId.DOCUMENTS);
			groupDocuments.addView(ViewId.SPREADSHEETS);
			groupDocuments.addView(ViewId.PRESENTATIONS);

			var groupPhotos = new google.picker.ViewGroup(ViewId.PHOTOS);

			groupPhotos.addView(ViewId.PHOTO_UPLOAD);
			groupPhotos.addView(ViewId.WEBCAM);

			var picker = new google.picker.PickerBuilder();

			picker.addViewGroup(groupDocuments);
			picker.addViewGroup(groupPhotos);

			picker.addView(ViewId.RECENTLY_PICKED);

			picker.setOAuthToken(oauthToken);
			picker.setDeveloperKey(developerKey);
			picker.setCallback(pickerCallback);

			picker.build().setVisible(true);
		}
	};

	var pickerCallback = function(data) {
		if (data[google.picker.Response.ACTION] === google.picker.Action.PICKED) {
			var doc = data[google.picker.Response.DOCUMENTS][0];

			var googlePickerDoc = google.picker.Document;

			var documentId = doc[googlePickerDoc.ID];
			var documentName = doc[googlePickerDoc.NAME];
			var documentDescription = doc[googlePickerDoc.DESCRIPTION] || '';
			var documentIconURL = doc[googlePickerDoc.ICON_URL] || '';
			var documentViewURL = doc[googlePickerDoc.EMBEDDABLE_URL] || '';
			var documentEditURL = doc[googlePickerDoc.URL] || '';

			A.one('#<portlet:namespace />pickButtonIcon').attr('src', documentIconURL);

			A.one('#<portlet:namespace />pickButtonName').html(documentName);

			A.one('#<portlet:namespace />title').val(documentName);
			A.one('#<portlet:namespace />description').val(documentDescription);

			var ddmInputs = A.all('#<portlet:namespace />fm .lfr-ddm-container input');

			A.Array.each(
				[documentId, documentName, documentIconURL, documentViewURL, documentEditURL],
				function(item, index) {
					ddmInputs.item(index).val(item);
				}
			);
		}
	};

	if (!window.gapi && !document.getElementById('googleAPILoader')) {
		var scriptNode = document.createElement('script');

		scriptNode.id = 'googleAPILoader';
		scriptNode.src = 'https://apis.google.com/js/api.js?onload=onGoogleAPILoad';

		document.body.appendChild(scriptNode);
	}
	else if (window.gapi) {
		Liferay.fire('googleAPILoad');
	}
</aui:script>