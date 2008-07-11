Liferay.Mail = {
	init: function(params) {
		var instance = this;

		instance.namespace = params.namespace;

		// Commonly used jQuery expressions

		instance.accountContainerDiv = jQuery('#' + instance.namespace + 'account-container');
		instance.accountSelectionSelect = jQuery('#' + instance.namespace + 'account-selection');
		instance.composeMailLink = jQuery('#' + instance.namespace + 'compose-mail');
		instance.configurationPromptDiv = jQuery('#' + instance.namespace + 'configuration-prompt');
		instance.emailContainerDiv = jQuery('#' + instance.namespace + 'email-container');
		instance.folderDiv = jQuery('#' + instance.namespace + 'folder');
		instance.foldersDiv = jQuery('#' + instance.namespace + 'folders');
		instance.folderControlsDeleteButton = jQuery('.folder-controls .delete');
		instance.folderControlsNewestLink = jQuery('.folder-controls .newest');
		instance.folderControlsNewerLink = jQuery('.folder-controls .newer');
		instance.folderControlsOlderLink = jQuery('.folder-controls .older');
		instance.folderControlsOldestLink = jQuery('.folder-controls .oldest');
		instance.folderControlsSelectAction = jQuery('.folder-controls .select-actions');
		instance.folderControlsSelectAllLink = jQuery('.folder-controls .select-all');
		instance.folderControlsSelectNoneLink = jQuery('.folder-controls .select-none');
		instance.folderControlsSelectReadLink = jQuery('.folder-controls .select-read');
		instance.folderControlsSelectUnreadLink = jQuery('.folder-controls .select-unread');
		instance.messageControlsBackLink = jQuery('.message-controls .back');
		instance.messageControlsDiv = jQuery('.message-controls');
		instance.messageControlsNewerLink = jQuery('.message-controls .newer');
		instance.messageControlsOlderLink = jQuery('.message-controls .older');
		instance.messageControlsStatus = jQuery('.message-controls .status');
		instance.messageDiv = jQuery('#' + instance.namespace + 'message');
		instance.messageListTable = jQuery('#' + instance.namespace + 'message-list');
		instance.messageOptionsDiv = jQuery('#' + instance.namespace + 'messageOptions');
		instance.messageOptionsRespondDivs = jQuery('#' + instance.namespace + 'reply,#' + instance.namespace + 'reply-all,#' + instance.namespace + 'forward');
		instance.messageReadDiv = jQuery('#' + instance.namespace + 'messageRead');
		instance.messageSendDiv = jQuery('#' + instance.namespace + 'messageSend');
		instance.messageSendDiscardButton = jQuery('#' + instance.namespace + 'messageSend .discard');
		instance.messageSendSaveButton = jQuery('#' + instance.namespace + 'messageSend .save');
		instance.messageSendSendButton = jQuery('#' + instance.namespace + 'messageSend .send');
		instance.searchButton = jQuery('#' + instance.namespace + 'search-button');
		instance.searchTextInput = jQuery('#' + instance.namespace + 'search-text');
		instance.sendAttachmentsFile = jQuery('#' + instance.namespace + 'sendAttachments');
		instance.sendBccInput = jQuery('#' + instance.namespace + 'sendBcc');
		instance.sendBodyEditor = params.sendBodyEditor;
		instance.sendBodySpan = jQuery('.send-body-span');
		instance.sendBodyHidden = jQuery('#' + instance.namespace + 'sendBody');
		instance.sendCcInput = jQuery('#' + instance.namespace + 'sendCc');
		instance.sendDraftMessageUidHidden = jQuery('#' + instance.namespace + 'sendDraftMessageUid');
		instance.sendEmailAddressHidden = jQuery('#' + instance.namespace + 'sendEmailAddress');
		instance.sendFolderNameHidden = jQuery('#' + instance.namespace + 'sendFolderName');
		instance.sendForm = jQuery('#' + instance.namespace + 'fm');
		instance.sendFromSelect = jQuery('#' + instance.namespace + 'sendFrom');
		instance.sendMessageHidden = jQuery('#' + instance.namespace + 'sendMessage');
		instance.sendMessageTypeHidden = jQuery('#' + instance.namespace + 'sendMessageType');
		instance.sendMessageUidHidden = jQuery('#' + instance.namespace + 'sendMessageUid');
		instance.sendSubjectInput = jQuery('#' + instance.namespace + 'sendSubject');
		instance.sendToInput = jQuery('#' + instance.namespace + 'sendTo');
		instance.statusSpan = jQuery('#' + instance.namespace + 'status');
		instance.readBodySpan = jQuery('#' + instance.namespace + 'read-body');
		instance.readCcSpan = jQuery('#' + instance.namespace + 'read-cc');
		instance.readDateSpan = jQuery('#' + instance.namespace + 'read-date');
		instance.readFromSpan = jQuery('#' + instance.namespace + 'read-from');
		instance.readMailedBySpan = jQuery('#' + instance.namespace + 'read-mailed-by');
		instance.readReplyToSpan = jQuery('#' + instance.namespace + 'read-reply-to');
		instance.readSubjectSpan = jQuery('#' + instance.namespace + 'read-subject');
		instance.readToSpan = jQuery('#' + instance.namespace + 'read-to');

		// Init methods

		instance._messagesPerPage = params.messagesPerPage;
		instance._assignEvents();
		instance.loadAccounts();
		instance.initializeEditor();
	},

	clearIncomingMessage: function() {
		var instance = this;

		instance.setCurrentMessage('');

		instance.readFromSpan.html('');
		instance.readReplyToSpan.html('');
		instance.readToSpan.html('');
		instance.readDateSpan.html('');
		instance.readSubjectSpan.html('');
		instance.readMailedBySpan.html('');
		instance.readBodySpan.html('');
	},

	clearMessages: function() {
		var instance = this;

		instance.setTotalPages(0);
		instance.setTotalMessages(0);
		instance.clearStatus();
		instance.messageListTable.html('<tr><td class="alert">' + Liferay.Language.get('no-messages-found') + '</td></tr>');

		instance.refreshMessageHandler();
		instance.refreshFolderControls();
	},

	clearOutgoingMessage: function() {
		var instance = this;

		instance.sendBccInput.val('');
		instance.sendBodySpan.hide();
		instance.sendCcInput.val('');
		instance.sendDraftMessageUidHidden.val('');
		instance.sendEmailAddressHidden.val('');
		instance.sendFolderNameHidden.val('');
		instance.sendFromSelect.val('');
		instance.sendSubjectInput.val('');
		instance.sendToInput.val('');
		instance.sendMessageTypeHidden.val('');
		instance.sendMessageUidHidden.val('');
	},

	clearStatus: function() {
		var instance = this;

		instance.setStatus('');

		instance.statusSpan.hide();
	},

	deleteMessages: function(messageUids, preMessage, success) {
		var instance = this;

		// Get JSON

		instance.setStatus(preMessage);

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/messages_delete_by_uid',
				data: {
					emailAddress: instance.getCurrentEmailAddress(),
					folderName: instance.getCurrentFolderName(),
					messageUids: messageUids
				},
				dataType: 'json',
				success: success,
				type: 'POST'
			}
		);
	},

	getCurrentEmailAddress: function() {
		var instance = this;

		return instance._currentEmailAddress;
	},

	getCurrentFolderName: function() {
		var instance = this;

		return instance._currentFolderName;
	},

	getCurrentMessage: function() {
		var instance = this;

		return instance._currentMessage;
	},

	getCurrentMessageUid: function() {
		var instance = this;

		return instance.getCurrentMessage().uid;
	},

	getCurrentPageNumber: function() {
		var instance = this;

		return instance._currentPageNumber;
	},

	getFirstMessageNumOnPage: function() {
		var instance = this;

		return (instance.getCurrentPageNumber() - 1) * instance.getMessagesPerPage() + 1;
	},

	getLastMessageNumOnPage: function() {
		var instance = this;

		if (instance.getTotalPages() == instance.getCurrentPageNumber()) {
			return instance.getTotalMessages();
		}
		else {
			return (instance.getCurrentPageNumber()) * instance.getMessagesPerPage();
		}
	},

	getMessageResponseType: function() {
		var instance = this;

		return instance._messageResponseType;
	},

	getMessagesPerPage: function() {
		var instance = this;

		return instance._messagesPerPage;
	},

	getSelectedMessageUids: function() {
		var instance = this;

		var checkedBoxes = jQuery('.message-checkbox:checked');
		var messageUids = '';

		jQuery.each(checkedBoxes, function() {
			messageUids += jQuery(this).parents('.message:first').attr('messageuid') + ',';
		});

		return messageUids;
	},

	getTotalMessages: function() {
		var instance = this;

		return instance._totalMessages;
	},

	getTotalPages: function() {
		var instance = this;

		return instance._totalPages;
	},

	getView: function() {
		var instance = this;

		return instance._currentView;
	},

	initializeEditor: function() {
		setTimeout('Liferay.Mail.messageDiv.show()', 1000);
		setTimeout('Liferay.Mail.messageSendDiv.show()', 1000);
		setTimeout('Liferay.Mail.messageDiv.hide()', 3000);
	},

	isSearchMode: function() {
		return _isSearchMode;
	},

	loadAccounts: function() {
		var instance = this;

		instance.setStatus(Liferay.Language.get('loading-accounts'));

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/accounts',
				dataType: 'json',
				success: function(jsonAccounts) {
					instance.loadJsonAccounts(jsonAccounts);
				},
				type: 'POST'
			}
		);
	},

	loadFolders: function(emailAddress, silentUpdate) {
 		var instance = this;

		// Set account id

		instance.setCurrentEmailAddress(emailAddress);

		// Get JSON

		if (!silentUpdate) {
			instance.setStatus(Liferay.Language.get('loading-folders'));
		}

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/folders',
				data: {
					emailAddress: emailAddress
				},
				dataType: 'json',
				success: function(jsonFolders) {
					instance.loadJsonFolders(jsonFolders, silentUpdate);

					if (!silentUpdate) {
						instance.accountSelectionSelect.val(emailAddress);
						instance.sendFromSelect.val(emailAddress);
					}
				},
				type: 'POST'
			}
		);
	},

	loadJsonAccounts: function(jsonAccounts) {
		var instance = this;

		instance.clearStatus();

		// Prompt user to setup accounts if none are configured

		if (jsonAccounts.accounts.length == 0) {
			instance.setView('viewAccountConfiguration');

			return false;
		}

		// Parse JSON

		var htmlAccountList = '';
		var firstEmailAddress = jsonAccounts.accounts[0].emailAddress;

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			var tempEmailAddress = account.emailAddress;

			htmlAccountList += '<option value="';
			htmlAccountList += tempEmailAddress;

			// Preselect first email account

			if (i == 0) {
				firstEmailAddress = tempEmailAddress;
				htmlAccountList += '" selected="selected">';
			}
			else {
				htmlAccountList += '">';
			}

			htmlAccountList += tempEmailAddress;
			htmlAccountList += '</option>';

			// Auto update accounts

			instance.sendUpdateMessage(tempEmailAddress, false);
		}

		// Inject HTML

		instance.accountSelectionSelect.html(htmlAccountList);
		instance.sendFromSelect.html(htmlAccountList);

		// Refresh folder handlers

		instance.setCurrentEmailAddress(firstEmailAddress);
		instance.loadFolders(firstEmailAddress, false);
	},

	loadJsonFolders: function(jsonFolders, silentUpdate) {
		var instance = this;

		if (!silentUpdate) {
			instance.clearStatus();
		}

		// Parse JSON

		var htmlFolderList = '';

		for (i = 0; i < jsonFolders.folders.length; i++) {

			var folder = jsonFolders.folders[i];

			var fullName = folder.fullName;
			var name = folder.name;

			var htmlFolder = '<div class="folder" folderName="' + fullName + '"><a href="#">' + name + '</a></div>';

			if (fullName.toLowerCase() == "inbox") {
				htmlFolderList = htmlFolder + '<br />' + htmlFolderList;
			}
			else {
				htmlFolderList = htmlFolderList + htmlFolder;
			}
		}

		// Inject HTML

		instance.foldersDiv.html(htmlFolderList);

		if (instance.getView() == 'composeMessage') {
			jQuery('.folder').removeClass('folder-selected results-header');
			instance.composeMailLink.addClass('folder-selected results-header');
		}
		else {
			jQuery(".folder[folderName='" + instance.getCurrentFolderName() + "']").addClass('folder-selected results-header');
			instance.composeMailLink.removeClass('folder-selected results-header');
		}

		// Refresh folder handlers

		instance.refreshFolderHandler();

		if (!silentUpdate) {
			instance.clearMessages();
			instance.setView('viewFolder');

			jQuery('.folder:first').click();
		}
	},

	loadJsonMessage: function(jsonMessage) {
		var instance = this;

		instance.setCurrentMessage(jsonMessage);
		instance.clearStatus();

		// Parse JSON object

		var msg = jsonMessage;

		var msgUid = msg.uid;
		var msgFrom = msg.from;
		var msgTo = msg.to;
		var msgCc = msg.cc;
		var msgBcc = msg.bcc;
		var msgSubject = msg.subject;
		var msgBodyPreview = msg.bodyPreview;
		var msgBody = msg.body;
		var msgDate = msg.date;

		// Add line breaks if it is plain text

		if (msgBody.indexOf('<a') == -1) {
			msgBody = msgBody.replace(/\r\n/g,'<br />');
			msgBody = msgBody.replace(/\n\n/g,'<br />');
		}

		// Add links to attachments

		var msgAttachments = msg.attachments;

		if (msgAttachments != null) {
			if (msgAttachments.length > 0) {
				msgBody += '<br /><hr>' + msgAttachments.length + ' attachments<br /><ul>';
			}

			for (i = 0; i < msgAttachments.length; i++) {
				msgBody += '<li><a href="' + themeDisplay.getLayoutURL() + '/-/mail/attachment?emailAddress=' + instance.getCurrentEmailAddress() + '&folderName=' + instance.getCurrentFolderName() + '&messageUid=' + msgUid + '&fileName=' + msgAttachments[i][1] + '&contentPath=' + msgAttachments[i][0] + '" target="_blank">' + msgAttachments[i][1] + '</a>';
			}

			msgBody += '</ul>';
		}

		var draftsFolder = instance.getCurrentFolderName().toLowerCase().indexOf('drafts') != -1;

		if (true) {

			// Inject HTML

			instance.readFromSpan.html(msgFrom);
			instance.readReplyToSpan.html('-');
			instance.readToSpan.html(msgTo);
			instance.readCcSpan.html(msgCc);
			instance.readDateSpan.html(msgDate);
			instance.readSubjectSpan.html(msgSubject);
			instance.readMailedBySpan.html('-');
			instance.readBodySpan.html(msgBody);

			// Update other HTML

			jQuery('.message-controls .folder-name').html(instance.getCurrentFolderName());
			instance.setView('viewMessage');

			// Update message navigation

			instance.refreshMessageControlsNavigation();

			// Update read status

			instance.setMessageFlags(msgUid, 'seen', 'true', false);
		}
		else {

			// Load draft

			instance.setView('composeMessage');

			instance.sendBccInput.val(msgBcc);
			instance.sendBodyEditor.setHTML(msgBody);
			instance.sendCcInput.val(msgCc);
			instance.sendDraftMessageUidHidden.val(msgUid);
			instance.sendFromSelect.val(msgFrom);
			instance.sendSubjectInput.val(msgSubject);
			instance.sendToInput.val(msgTo);
		}
	},

	loadJsonMessages: function(jsonMessages) {
		var instance = this;

		instance.setTotalPages(jsonMessages.pageCount);
		instance.setTotalMessages(jsonMessages.messageCount);
		instance.clearStatus();

		// Parse JSON

		var htmlMessageList = '';

		if (jsonMessages.messages.length == 0) {
			htmlMessageList += '<tr><td class="alert">' + Liferay.Language.get('no-messages-found') + '</td></tr>';
		}
		else {
			for (i = (jsonMessages.messages.length - 1); i >= 0; i--) {
				var msg = jsonMessages.messages[i];

				if (msg.flags.seen) {
					htmlMessageList += '<tr class="message results-row alt read" messageUid="' + msg.uid + '">';
				}
				else {
					htmlMessageList += '<tr class="message results-row unread" messageUid="' + msg.uid + '">';
				}

				htmlMessageList += '	<td><div class="message-col-0"><input type="checkbox" class="message-checkbox" /></div></td>';
				htmlMessageList += '	<td><div class="message-col-1"><span class="message-from">' + msg.from + '</span></div></td>';
				htmlMessageList += '	<td><div class="message-col-2"><span class="message-subject">' + msg.subject + '</span> - <span class="message-body-preview">' + msg.bodyPreview + '</span></div></td>';
				htmlMessageList += '	<td><div class="message-col-3"><span class="message-date">' + msg.date + '</span></div></td>';
				htmlMessageList += '</tr>';
			}
		}

		// Inject HTML

		instance.messageListTable.html(htmlMessageList);

		// Refresh html and event handlers

		instance.refreshMessageHandler();
		instance.refreshFolderControls();
		instance.setView('viewFolder');
	},

	loadMessageRelativeToUid: function(messageUid, offset) {
		var instance = this;

		// Get JSON

		var searchStringVal = '';

		if (instance.isSearchMode()) {
			searchStringVal = instance.searchTextInput.val();
		}

		instance.setStatus(Liferay.Language.get('loading-message'));

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/message_relative_to_uid',
				data: {
					emailAddress: instance.getCurrentEmailAddress(),
					folderName: instance.getCurrentFolderName(),
					messageUid: messageUid,
					offset: offset,
					searchString: searchStringVal
				},
				dataType: 'json',
				success: function(jsonMessage) {
					if (jsonMessage != null) {
						instance.loadJsonMessage(jsonMessage);
					}
				},
				type: 'POST'
			}
		);
	},

	loadMessagesByPage: function(folderName, pageNumber) {
		var instance = this;

		// Set folder and page number

		instance.setCurrentPageNumber(pageNumber);
		instance.setCurrentFolderByName(folderName);

		var emailAddress = instance.getCurrentEmailAddress();

		// Get JSON

		instance.setStatus(Liferay.Language.get('loading-messages'));

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/messages_by_page',
				data: {
					emailAddress: instance.getCurrentEmailAddress(),
					folderName: folderName,
					pageNumber: pageNumber,
					messagesPerPage: instance.getMessagesPerPage()
				},
				dataType: 'json',
				success: function(jsonMessages) {
					instance.loadJsonMessages(jsonMessages);
				},
				type: 'POST'
			}
		);
	},

	loadMessagesBySearch: function(folderName, pageNumber, searchString) {
		var instance = this;

		// Set folder and page number

		instance.setCurrentPageNumber(pageNumber);
		instance.setCurrentFolderByName(folderName);

		// Get JSON

		instance.setStatus(Liferay.Language.get('loading-messages'));

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/messages_by_search',
				data: {
					emailAddress: instance.getCurrentEmailAddress(),
					folderName: folderName,
					pageNumber: pageNumber,
					messagesPerPage: instance.getMessagesPerPage(),
					searchString: searchString
				},
				dataType: 'json',
				success: function(jsonMessages) {
					instance.loadJsonMessages(jsonMessages);
				},
				type: 'POST'
			}
		);
	},

	refreshFolderControls: function() {
 		var instance = this;

		// Folder Navigation

		instance.folderControlsNewestLink.css('display', 'inline');
		instance.folderControlsNewerLink.css('display', 'inline');
		instance.folderControlsOlderLink.css('display', 'inline');
		instance.folderControlsOldestLink.css('display', 'inline');

		if (instance.getTotalPages() == 0) {

			// No pages

			instance.folderControlsNewestLink.hide();
			instance.folderControlsNewerLink.hide();
			instance.folderControlsOlderLink.hide();
			instance.folderControlsOldestLink.hide();

			// Update page range count status

			jQuery('.folder-controls .status').html('');
		}
		else {

			// On first page

			if (instance.getCurrentPageNumber() == 1) {
				instance.folderControlsNewestLink.hide();
				instance.folderControlsNewerLink.hide();
			}

			// On second page

			if (instance.getCurrentPageNumber() == 2) {
				instance.folderControlsNewestLink.hide();
			}

			// On second to last page

			if (instance.getCurrentPageNumber() == instance.getTotalPages() - 1) {
				instance.folderControlsOldestLink.hide();
			}

			// On last page

			if (instance.getCurrentPageNumber() == instance.getTotalPages()) {
				instance.folderControlsOlderLink.hide();
				instance.folderControlsOldestLink.hide();
			}

			// Update page range count status

			jQuery('.folder-controls .status').html('<span class="status-number">' + instance.getFirstMessageNumOnPage() + ' - ' + instance.getLastMessageNumOnPage() + '</span> of <span class="status-number">' + instance.getTotalMessages() + '</span>');
		}

		// Folder Options

		instance.folderControlsSelectAction.val('none');
	},

	refreshFolderHandler: function() {
		var instance = this;

		jQuery('.folder').click(function () {

			var folderClicked = jQuery(this);
			var folderName = folderClicked.attr('folderName');

			// Load message list

			instance.setSearchMode(false);
			instance.loadMessagesByPage(folderName, 1);

			// Show message list

			instance.setView('viewFolder');

			// Reset and set backgrounds

 			jQuery('.folder').removeClass('folder-selected results-header');
			instance.composeMailLink.removeClass('folder-selected results-header');

			folderClicked.addClass('folder-selected results-header');

			return false;
		});
	},

	refreshMessageControlsNavigation: function() {
		var instance = this;

		instance.messageControlsNewerLink.css('display', 'inline');
		instance.messageControlsOlderLink.css('display', 'inline');

		// If on first message

		if (instance.getCurrentMessage().messageNumber == instance.getTotalMessages()) {
			instance.messageControlsNewerLink.hide();
			instance.messageControlsOlderLink.css('display', 'inline');
		}

		// If on last message

		if (instance.getCurrentMessage().messageNumber == 1) {
			instance.messageControlsNewerLink.css('display', 'inline');
			instance.messageControlsOlderLink.hide();
		}

		// Update page count status

		var msgNumberDisplayed = parseInt(instance.getTotalMessages()) - parseInt(instance.getCurrentMessage().messageNumber) + 1;

		instance.messageControlsStatus.html('<span class="status-number">' + msgNumberDisplayed + '</span> of <span class="status-number">' + instance.getTotalMessages() + '</span>');
	},

	refreshMessageHandler: function() {
		var instance = this;

		jQuery('.message td:not(:first-child)').click(function () {

			// Load message

			var messageUid = jQuery(this).parent('.message:first').attr('messageUid');

			instance.loadMessageRelativeToUid(messageUid, 0);

			// Show message

			instance.setView('viewMessage');

			return false;
		});
	},

	saveOrSendMessage: function(send) {
		var instance = this;

		// Set flag to determine whether to save or send the message

		if (send) {
			instance.sendMessageHidden.val('true');
		}
		else {
			instance.sendMessageHidden.val('false');
		}

		var fromEmailAddress = instance.sendFromSelect.val();
		var folderNameVal = '';
		var messageUidVal = 0;

		if (instance.getMessageResponseType() != 'new') {
			folderNameVal = instance.getCurrentFolderName();
			messageUidVal = instance.getCurrentMessageUid();
		}

		instance.sendBodyHidden.val(instance.sendBodyEditor.getHTML());
		instance.sendEmailAddressHidden.val(fromEmailAddress);
		instance.sendFolderNameHidden.val(folderNameVal);
		instance.sendMessageTypeHidden.val(instance.getMessageResponseType());
		instance.sendMessageUidHidden.val(messageUidVal);

		instance.sendForm.submit();

		instance.clearStatus();
	},

	sendUpdateMessage: function(emailAccount, loadMessages) {
		var instance = this;

		if (loadMessages) {
			instance.setStatus(Liferay.Language.get('getting-new-mail'));
		}

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/synchronize_account',
				data: {
					emailAddress: emailAccount
				},
				dataType: 'json',
				success: function(jsonAccounts) {

					// If looking at a folder, auto update data

					if (instance.getView() == 'viewFolder') {

						// Automatically update folder listing

						instance.loadFolders(instance.getCurrentEmailAddress(), true);

						if (instance.getCurrentFolderName() == null) {
							jQuery('.folder:first').click();
						}
					}

					if (loadMessages) {
						instance.loadMessagesByPage(instance.getCurrentFolderName(), instance.getCurrentPageNumber());
						instance.clearStatus();
					}
					else {
						setTimeout('Liferay.Mail.sendUpdateMessage(\'' + emailAccount + '\', false)', 30000);
					}
				},
				type: 'POST'
			}
		);
	},

	setCurrentEmailAddress: function(emailAddress) {
		var instance = this;

		instance._currentEmailAddress = emailAddress;
	},

	setCurrentFolderByName: function(folderName) {
		var instance = this;

		instance._currentFolderName = folderName;
	},

	setCurrentMessage: function(jsonMessage) {
		var instance = this;

		instance._currentMessage = jsonMessage;
	},

	setCurrentPageNumber: function(pageNumber) {
		var instance = this;

		instance._currentPageNumber = pageNumber;
	},

	setMessageFlags: function(messageUids, flag, value, reloadMessages) {
		var instance = this;

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/flag_messages',
				data: {
					emailAddress: instance.getCurrentEmailAddress(),
					folderName: instance.getCurrentFolderName(),
					messageUids: messageUids,
					flag: flag,
					value: value
				},
				dataType: 'json',
				success: function(jsonSuccess) {
					if (reloadMessages) {
						instance.loadMessagesByPage(instance.getCurrentFolderName(), instance.getCurrentPageNumber());
					}

					instance.clearStatus();
				},
				type: 'POST'
			}
		);
	},

	setMessageResponseType: function(responseType) {
		var instance = this;

		// Possible values include: 'reply', 'forward' and 'new'

		instance._messageResponseType = responseType;
	},

	setSearchMode: function(isSearchMode) {
		_isSearchMode = isSearchMode;
	},

	setStatus: function(message) {
		var instance = this;

		instance.statusSpan.css('display', 'inline');
		instance.statusSpan.html(message);
	},

	setTotalMessages: function(totalMessages) {
		var instance = this;

		instance._totalMessages = totalMessages;
	},

	setTotalPages: function(totalPages) {
		var instance = this;

		instance._totalPages = totalPages;
	},

	setView: function(viewMode) {
		var instance = this;

		instance._currentView = viewMode;

		// Hide everything

		instance.configurationPromptDiv.hide();
		instance.folderDiv.hide();
		instance.messageDiv.hide();

		instance.messageControlsDiv.hide();
		instance.messageReadDiv.hide();
		instance.messageOptionsDiv.hide();
		instance.messageSendDiv.hide();

		// Show desired windows

		if (viewMode == 'viewAccountConfiguration') {
			instance.accountContainerDiv.hide();
			instance.configurationPromptDiv.show();
			instance.emailContainerDiv.hide();
		}
		else {
			instance.accountContainerDiv.show();
			instance.emailContainerDiv.show();

			if (viewMode == 'viewFolder') {
				instance.clearIncomingMessage();

				instance.folderDiv.show();
			}
			else {
				instance.clearOutgoingMessage();

				instance.messageDiv.show();
				jQuery('#' + instance.namespace + 'message-options td').css('background-color', '#F7F7F7');

				if (viewMode == 'viewMessage') {
					instance.messageControlsDiv.show();
					instance.messageReadDiv.show();
					instance.messageOptionsDiv.show();
				}
				else if (viewMode == 'replyOrForwardMessage') {
					instance.messageControlsDiv.show();
					instance.messageReadDiv.show();
					instance.messageOptionsDiv.show();
					instance.messageSendDiv.show();
				}
				else if (viewMode == 'composeMessage') {
					instance.clearOutgoingMessage();

					instance.messageSendDiv.show();

					try {
						instance.sendBodySpan.show();
						//instance.sendBodyEditor.setHTML('');
					}
					catch (ex) {
					}
				}
			}
		}
	},

	_assignEvents: function() {
		var instance = this;

		instance.accountSelectionSelect.change(function() {
			var newEmailAddress = jQuery(this).val();

			instance.loadFolders(newEmailAddress, false);
		});

		instance.composeMailLink.click(function() {
			instance.setView('composeMessage');
			instance.setMessageResponseType('new');
			instance.sendBodyEditor.setHTML('');
			instance.setCurrentMessage('');

			// Reset and set backgrounds

			jQuery('.folder').removeClass('folder-selected results-header');
			instance.composeMailLink.addClass('folder-selected results-header');

			return false;
		});

		instance.folderControlsDeleteButton.click(function() {
			var messageUids = instance.getSelectedMessageUids();
			var preMessage = Liferay.Language.get('deleting-messages');

			instance.deleteMessages(
				messageUids,
				preMessage,
				function(jsonResult) {
					instance.sendUpdateMessage(instance.getCurrentEmailAddress(), true);

					instance.setStatus(Liferay.Language.get('messages-have-been-deleted'));
				}
			);
		});

		instance.folderControlsNewerLink.click(function() {
			var pageNumber = parseInt(instance.getCurrentPageNumber()) - 1;

			if (instance.isSearchMode())
			{
				instance.loadMessagesBySearch(instance.getCurrentFolderName(), pageNumber, instance.searchTextInput.val());
			}
			else {
				instance.loadMessagesByPage(instance.getCurrentFolderName(), pageNumber);
			}

			return false;
		});

		instance.folderControlsNewestLink.click(function() {
			var pageNumber = 1;

			if (instance.isSearchMode())
			{
				instance.loadMessagesBySearch(instance.getCurrentFolderName(), pageNumber, instance.searchTextInput.val());
			}
			else {
				instance.loadMessagesByPage(instance.getCurrentFolderName(), pageNumber);
			}

			return false;
		});

		instance.folderControlsOlderLink.click(function() {
			var pageNumber = parseInt(instance.getCurrentPageNumber()) + 1;

			if (instance.isSearchMode())
			{
				instance.loadMessagesBySearch(instance.getCurrentFolderName(), pageNumber, instance.searchTextInput.val());
			}
			else {
				instance.loadMessagesByPage(instance.getCurrentFolderName(), pageNumber);
			}

			return false;
		});

		instance.folderControlsOldestLink.click(function() {
			var pageNumber = parseInt(instance.getTotalPages());

			if (instance.isSearchMode())
			{
				instance.loadMessagesBySearch(instance.getCurrentFolderName(), pageNumber, instance.searchTextInput.val());
			}
			else {
				instance.loadMessagesByPage(instance.getCurrentFolderName(), pageNumber);
			}

			return false;
		});

		instance.folderControlsSelectAction.change(function() {

			// Do nothing if action not recognized

			var option = (instance.folderControlsSelectAction.val()).split(",");

			var flag = option[0];
			var value = option[1];

			if (flag == '') {
				instance.refreshFolderControls();

				return false;
			}

			// Do nothing if no messages are selected

			var messageUids = instance.getSelectedMessageUids();

			if (messageUids == '') {
				instance.refreshFolderControls();

				instance.setStatus(Liferay.Language.get('no-messages-selected'));

				return false;
			}

			// Do selected folder action

			if (flag == 'seen') {
				if (value == 'true') {
					instance.setStatus(Liferay.Language.get('marking-messages-as-read'));
				}
				else {
					instance.setStatus(Liferay.Language.get('marking-messages-as-unread'));
				}

				instance.setMessageFlags(messageUids, flag, value, true);
			}
		});

		instance.folderControlsSelectAllLink.click(function() {
			jQuery('.message-checkbox').attr('checked', 'true');

			return false;
		});

		instance.folderControlsSelectNoneLink.click(function() {
			jQuery('.message-checkbox').attr('checked', '');

			return false;
		});

		instance.folderControlsSelectReadLink.click(function() {
			jQuery('.read .message-checkbox').attr('checked', 'true');
			jQuery('.unread .message-checkbox').attr('checked', '');

			return false;
		});

		instance.folderControlsSelectUnreadLink.click(function() {
			jQuery('.read .message-checkbox').attr('checked', '');
			jQuery('.unread .message-checkbox').attr('checked', 'true');

			return false;
		});

		instance.messageControlsBackLink.click(function() {
			instance.setView('viewFolder');

			return false;
		});

		instance.messageControlsNewerLink.click(function() {
			instance.setView('viewMessage');

			instance.loadMessageRelativeToUid(parseInt(instance.getCurrentMessageUid()), 1);

			return false;
		});

		instance.messageControlsOlderLink.click(function() {
			instance.setView('viewMessage');

			instance.loadMessageRelativeToUid(parseInt(instance.getCurrentMessageUid()), -1);

			return false;
		});

		instance.messageOptionsRespondDivs.click(function () {

			// Show message send

			instance.setView('replyOrForwardMessage');

			instance.messageOptionsRespondDivs.removeClass('selected-response-type results-header');
			jQuery(this).addClass('selected-response-type results-header');

			// Load default values for response message based on original message

			var msg = instance.getCurrentMessage();

			var msgUid = msg.uid;
			var msgFrom = msg.from;
			var msgTo = msg.to;
			var msgCc = msg.cc;
			var msgSubject = msg.subject;
			var msgBody = msg.body;
			var msgDate = msg.date;

			// Has message been changed?

			var messageModified = false;

			if (jQuery(this).attr('responseType') == 'forward') {

				// Forward

				instance.setMessageResponseType('forward');
				instance.sendSubjectInput.val('Fwd: ' + msgSubject);

				// Load appropriate response message

				if (!messageModified) {
					var fwdHeader = '<br /><br />---------- Forwarded message ----------<br />';
					fwdHeader += 'From: ' + msgFrom + '<br />';
					fwdHeader += 'Date: ' + msgDate + '<br />';
					fwdHeader += 'Subject: ' + msgSubject + '<br />';
					fwdHeader += 'To: ' + msgTo + '<br /><br />';

					instance.sendBodySpan.show();
					instance.sendBodyEditor.setHTML(fwdHeader + msgBody);
				}
			}
			else {

				// Reply

				instance.setMessageResponseType('reply');
				instance.sendSubjectInput.attr('value', 'Re: ' + msgSubject);

				if (jQuery(this).attr('responseType') == 'reply') {
					instance.sendToInput.attr('value', msgFrom);
				}
				else if (jQuery(this).attr('responseType') == 'reply-all') {
					instance.sendToInput.attr('value', msgFrom);
					instance.sendCcInput.attr('value', msgTo + ',' + msgCc);
				}
				else {
					alert('Unknown Message Response Type.  Reply? Reply All? Forward?');
				}

				// Load appropriate response message

				if (!messageModified) {
					var replyHeader = '<br /><br />On ' + msgDate + ', <' + msgFrom + '> wrote:<br /><br />';

					instance.sendBodySpan.show();
					instance.sendBodyEditor.setHTML(replyHeader + msgBody);
				}
			}
		});

		instance.messageSendDiscardButton.click(function() {
			if (instance.sendDraftMessageUidHidden.val() != '') {

				var messageUids = instance.sendDraftMessageUidHidden.val();
				var preMessage = Liferay.Language.get('discarding-draft');

				instance.deleteMessages(
					messageUids,
					preMessage,
					function(jsonResult) {
						instance.sendUpdateMessage(instance.getCurrentEmailAddress(), true);

						instance.setStatus(Liferay.Language.get('draft-has-been-discarded'));
					}
				);
			}

			if ((instance.getCurrentMessage() == null) || instance.getCurrentMessage() == '') {
				jQuery('.folder:first').click();
			}
			else {
				instance.setView('viewMessage');
			}

			instance.setStatus(Liferay.Language.get('your-message-was-discarded'));
		});

		instance.sendForm.submit(function() {
		    /*
		    var options = {
		        beforeSubmit: function(formData, jqForm, options) {
		        	// validate message and return false if not valid

					return true;
		        },
		        dataType: 'json',
		        iframe: true,
		        success: function(responseText, statusText) {
					instance.setView('viewFolder');

					instance.setStatus(Liferay.Language.get('the-email-was-sent-successfully'));
	        	}
		    };

	        instance.sendForm.ajaxSubmit(options);

	        return false;
	        */
		});

		instance.messageSendSaveButton.click(function() {
			instance.setStatus(Liferay.Language.get('saving-message'));

			instance.saveOrSendMessage(false);
		});

		instance.messageSendSendButton.click(function() {
			instance.setStatus(Liferay.Language.get('sending-message'));

			instance.saveOrSendMessage(true);
		});

		instance.searchButton.click(function() {
			instance.setSearchMode(true);

			instance.loadMessagesBySearch(instance.getCurrentFolderName(), 1, instance.searchTextInput.val());

			// Reset and set backgrounds

			jQuery('.folder').removeClass('folder-selected results-header');
			instance.composeMailLink.removeClass('folder-selected results-header');
		});
	},

	_currentEmailAddress: null,
	_currentFolderName: null,
	_currentMessage: null,
	_currentPageNumber: null,
	_currentView: 'viewFolder',
	_isSearchMode: false,

	_accounts: null,
	_messagesPerPage: 0,
	_messageResponseType: null,

	_totalMessages: null,
	_totalPages: null,
	_uidList: null,

	_jsonFolders: {},
	_jsonMessages: {},
	_jsonMessage: {}
}

Liferay.MailConfiguration = {
	init: function(params) {
		var instance = this;

		// Commonly used jQuery expressions

		instance.currentMailAccountsDiv = jQuery('.current-mail-accounts');
		instance.preconfiguredMailAccountsDiv = jQuery('.preconfigured-mail-accounts');
		instance.preconfiguredMailAccounts = params.preconfiguredMailAccounts;

		instance.loadJSONAccountsConfiguration();
	},

	appendJSONAccountConfigurationHTML: function(htmlElement, jsonAccount, newAccount) {
		var instance = this;

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/account_configuration',
				async: false,
				data: {
					emailAddress: jsonAccount.emailAddress,
					mailInHostName: jsonAccount.mailInHostName,
					mailInPort: jsonAccount.mailInPort,
					mailOutHostName: jsonAccount.mailOutHostName,
					mailOutPort: jsonAccount.mailOutPort,
					mailSecure: jsonAccount.mailSecure,
					newAccount: newAccount,
					password: jsonAccount.password,
					preconfigured: jsonAccount.preconfigured,
					title: jsonAccount.title,
					username: jsonAccount.username
				},
				dataType: 'html',
				success: function(accountConfigurationHTML) {
					htmlElement.append(accountConfigurationHTML);
				},
				type: 'POST'
			}
		);
	},

	saveAccountConfiguration: function (emailAddressValue, mailInHostNameValue, mailInPortValue, mailOutHostNameValue, mailOutPortValue, mailSecureValue, passwordValue, usernameValue) {
		var instance = this;

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/update_account',
				data: {
					emailAddress: emailAddressValue,
					mailInHostName: mailInHostNameValue,
					mailInPort: mailInPortValue,
					mailOutHostName: mailOutHostNameValue,
					mailOutPort: mailOutPortValue,
					mailSecure: mailSecureValue,
					password: passwordValue,
					username: usernameValue
				},
				dataType: 'json',
				success: function(success) {
					instance.loadJSONAccountsConfiguration();

					alert(Liferay.Language.get('you-have-successfully-added-a-new-email-account'));
				},
				type: 'POST'
			}
		);
	},

	loadJSONAccountsConfiguration: function () {
		var instance = this;

		instance.preconfiguredMailAccountsDiv.html("");
		instance.currentMailAccountsDiv.html("");

		var defaultJSONAccounts = instance.preconfiguredMailAccounts;

		for (i = 0; i < defaultJSONAccounts.accounts.length; i++) {
			var account = defaultJSONAccounts.accounts[i];

			instance.appendJSONAccountConfigurationHTML(instance.preconfiguredMailAccountsDiv, account, true);
		}

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/mail/accounts',
				dataType: 'json',
				success: function(jsonAccounts) {
					for (i = 0; i < jsonAccounts.accounts.length; i++) {
						var account = jsonAccounts.accounts[i];

						instance.appendJSONAccountConfigurationHTML(instance.currentMailAccountsDiv, account, false);
					}

					instance.refreshAccountEvents();
				},
				type: 'POST'
			}
		);
	},

	refreshAccountEvents: function () {
		var instance = this;

		jQuery('.cancel-account').click(function() {
			instance.loadJSONAccountsConfiguration();

			return false;
		});

		jQuery('.delete-account').click(function() {
			var accountTable = jQuery(this).parents('.account:first');

			// Get JSON

			jQuery.ajax(
				{
					url: themeDisplay.getLayoutURL() + '/-/mail/delete_account',
					data: {
						emailAddress: accountTable.find('.email-address:first').val()
					},
					dataType: 'json',
					success: function(success) {
						instance.loadJSONAccountsConfiguration();
					},
					type: 'POST'
				}
			);

			return false;
		});

		jQuery('.save-account').click(function() {
			var accountTable = jQuery(this).parents('.account:first');

			// Get JSON

			var emailAddressValue = accountTable.find('.email-address:first').val();
			var mailInHostNameValue = accountTable.find('.in-hostname:first').val();
			var mailInPortValue = accountTable.find('.in-port:first').val(); 
			var mailOutHostNameValue = accountTable.find('.out-hostname:first').val();
			var mailOutPortValue = accountTable.find('.out-port:first').val();
			var mailSecureValue = false;

			if (accountTable.find('.secure:first').attr('checked')) {
				mailSecureValue = true;
			}
			else if ((accountTable.find('.secure:first').attr('type') == 'hidden') && (accountTable.find('.secure:first').val())) {
				mailSecureValue = true;
			}

			var passwordValue = accountTable.find('.password:first').val();
			var usernameValue = accountTable.find('.username:first').val();

			// Test account configuration

			jQuery.ajax(
				{
					url: themeDisplay.getLayoutURL() + '/-/mail/test_account',
					data: {
						emailAddress: emailAddressValue,
						mailInHostName: mailInHostNameValue,
						mailInPort: mailInPortValue,
						mailOutHostName: mailOutHostNameValue,
						mailOutPort: mailOutPortValue,
						mailSecure: mailSecureValue,
						password: passwordValue,
						username: usernameValue
					},
					dataType: 'json',
					success: function(result) {
						if (result.incoming && result.outgoing) {
							instance.saveAccountConfiguration(emailAddressValue, mailInHostNameValue, mailInPortValue, mailOutHostNameValue, mailOutPortValue, mailSecureValue, passwordValue, usernameValue);
						}
						else if (!result.outgoing && !result.incoming) {
							alert(Liferay.Language.get('you-have-failed-to-connect-to-the-imap-and-smtp-server'));
						}
						else if (!result.outgoing) {
							alert(Liferay.Language.get('you-have-successfully-connected-to-the-imap-server-but-failed-to-connect-to-the-smtp-server'));
						}
						else if (!result.incoming) {
							alert(Liferay.Language.get('you-have-failed-to-connect-to-the-imap-server-but-successfully-connected-to-the-smtp-server'));
						}
					},
					type: 'POST'
				}
			);

			return false;
		});

		jQuery('.account').find('.title').click(function(){
			jQuery(this).parents('.account:first').find('.details').toggle();

			return false;
		});

		jQuery('.details').hide();
	}
}