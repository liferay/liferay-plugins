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
String googleApiKey = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), "googleApiKey");
String googleClientId = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), "googleClientId");
%>

<script type="text/javascript">

	// The API developer key obtained from the Google Developers Console.
	var developerKey = '<%= googleApiKey %>';

	// The Client ID obtained from the Google Developers Console.
	var clientId = '<%= googleClientId %>';

	// Scope to use to access user's photos.
	var scope = [
		'https://www.googleapis.com/auth/drive.readonly',
		'https://www.googleapis.com/auth/photos.upload'
	];

	var googleApiLoaded = false;
	var authApiLoaded = false;
	var pickerApiLoaded = false;

	var oauthToken;
	var openPickerWhenGoogleApiLoaded = false;

	// Use the API Loader script to load google.picker and gapi.auth.
	function onGoogleApiLoad() {
		googleApiLoaded = true;

		if (openPickerWhenGoogleApiLoaded) {
			openPicker();
		}
	}

	function openPicker() {
		if (googleApiLoaded) {
			gapi.load('auth', {'callback': onAuthApiLoad});
			gapi.load('picker', {'callback': onPickerApiLoad});
		}
		else {
			openPickerWhenGoogleApiLoaded = true;
		}
	}

	function onAuthApiLoad() {
		window.gapi.auth.authorize(
			{
				'client_id': clientId,
				'scope': scope,
				'immediate': false
			},
			function(authResult) {
				if (authResult && !authResult.error) {
					oauthToken = authResult.access_token;

					authApiLoaded = true;

					createPicker();
				}
			}
		);
	}

	function onPickerApiLoad() {
		pickerApiLoaded = true;

		createPicker();
	}

	// Create and render a Picker object for picking user Photos.
	function createPicker() {
		if (pickerApiLoaded && authApiLoaded) {
			var groupDocuments = new google.picker.ViewGroup(google.picker.ViewId.DOCS);
			groupDocuments.addView(google.picker.ViewId.DOCUMENTS);
			groupDocuments.addView(google.picker.ViewId.SPREADSHEETS);
			groupDocuments.addView(google.picker.ViewId.PRESENTATIONS);

			var groupPhotos = new google.picker.ViewGroup(google.picker.ViewId.PHOTOS);
			groupPhotos.addView(google.picker.ViewId.PHOTO_UPLOAD);
			groupPhotos.addView(google.picker.ViewId.WEBCAM);

			var picker = new google.picker.PickerBuilder();
			picker.addViewGroup(groupDocuments);
			picker.addViewGroup(groupPhotos);
			picker.addView(google.picker.ViewId.RECENTLY_PICKED);

			picker.setOAuthToken(oauthToken);
			picker.setDeveloperKey(developerKey);
			picker.setCallback(<portlet:namespace />pickerCallback);

			picker.build().setVisible(true);
		}
	}
</script>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />fm').delegate(
		'click',
		function(event) {
			openPicker();
		},
		'.add-google-shortcut'
	);

	window['<portlet:namespace />pickerCallback'] = function(data) {
		if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
			var doc = data[google.picker.Response.DOCUMENTS][0];

			var googleDocumentId = doc[google.picker.Document.ID];
			var googleDocumentName = doc[google.picker.Document.NAME];
			var googleDocumentDescription = doc[google.picker.Document.DESCRIPTION];
			var googleDocumentIconURL = doc[google.picker.Document.ICON_URL];
			var googleDocumentViewURL = doc[google.picker.Document.EMBEDDABLE_URL];
			var googleDocumentEditURL = doc[google.picker.Document.URL];

			// Pick button's icon and text
			var pickButtonIcon = A.one('#pickButtonIcon').getDOM();

			if (googleDocumentIconURL) {
				pickButtonIcon.src = googleDocumentIconURL;
			}
			else {
				pickButtonIcon.src = "";
			}

			var pickButtonName = A.one('#pickButtonName').getDOM();

			pickButtonName.innerText = googleDocumentName;

			// Title field
			var titleInput = A.one('#<portlet:namespace />title').getDOM();

			titleInput.value = googleDocumentName;

			// Description field
			if (googleDocumentDescription) {
				var descriptionInput = A.one('#<portlet:namespace />description').getDOM();

				descriptionInput.value = googleDocumentDescription;
			}

			// DDM fields
			var ddmInputs = A.all('.lfr-ddm-container input').getDOMNodes();

			ddmInputs[0].value = googleDocumentId;
			ddmInputs[1].value = googleDocumentName;

			if (googleDocumentIconURL) {
				ddmInputs[2].value = googleDocumentIconURL;
			}
			else {
				ddmInputs[2].value = "";
			}

			if (googleDocumentViewURL) {
				ddmInputs[3].value = googleDocumentViewURL;
			}
			else {
				ddmInputs[3].value = "";
			}

			if (googleDocumentEditURL) {
				ddmInputs[4].value = googleDocumentEditURL;
			}
			else {
				ddmInputs[4].value = "";
			}
		}
	}
</aui:script>

<script src="https://apis.google.com/js/api.js?onload=onGoogleApiLoad" type="text/javascript"></script>