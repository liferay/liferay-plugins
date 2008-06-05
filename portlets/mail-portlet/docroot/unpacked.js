Liferay.Mail = {
	init: function(params) {
		var instance = this;

		instance._messagesPerPage = 10;

		// Commonly used jQuery expressions

		instance.accountSelectionSelect = jQuery('#account-selection');
		instance.composeMailLink = jQuery('#compose-mail');
		instance.debugSpan = jQuery('#debug');
		instance.folderDiv = jQuery('#folder');
		instance.foldersDiv = jQuery('#folders');
		instance.folderControlsDeleteButton = jQuery('.folder-controls .delete');
		instance.folderControlsNewestLink = jQuery('.folder-controls .newest');
		instance.folderControlsNewerLink = jQuery('.folder-controls .newer');
		instance.folderControlsOlderLink = jQuery('.folder-controls .older');
		instance.folderControlsOldestLink = jQuery('.folder-controls .oldest');
		instance.folderControlsRefreshLink = jQuery('.folder-controls .refresh');
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
		instance.messageDiv = jQuery('#message');
		instance.messageListTable = jQuery('#message-list');
		instance.messageOptionsDiv = jQuery('#message-options');
		instance.messageOptionsRespondDivs = jQuery('#reply,#reply-all,#forward');
		instance.messageReadDiv = jQuery('#message-read');
		instance.messageSendDiv = jQuery('#message-send');
		instance.messageSendDiscardButton = jQuery('#message-send .discard');
		instance.messageSendSaveButton = jQuery('#message-send .save');
		instance.messageSendSendButton = jQuery('#message-send .send');
		instance.searchButton = jQuery('#search-button');
		instance.searchTextInput = jQuery('#search-text');
		instance.sendBccInput = jQuery('#send-bcc');
		instance.sendBodyEditor = params.sendBodyEditor;
		instance.sendBodySpan = jQuery('#send-body');
		instance.sendCcInput = jQuery('#send-cc');
		instance.sendFromSelect = jQuery('#send-from');
		instance.sendSubjectInput = jQuery('#send-subject');
		instance.sendToInput = jQuery('#send-to');
		instance.statusSpan = jQuery('#status');
		instance.readBodySpan = jQuery('#read-body');
		instance.readCcSpan = jQuery('#read-cc');
		instance.readDateSpan = jQuery('#read-date');
		instance.readFromSpan = jQuery('#read-from');
		instance.readMailedBySpan = jQuery('#read-mailed-by');
		instance.readReplyToSpan = jQuery('#read-reply-to');
		instance.readSubjectSpan = jQuery('#read-subject');
		instance.readToSpan = jQuery('#read-to');

		// Init methods

		instance._assignEvents();
		instance.setView('composeMessage');
		instance.setView('viewFolder');
		instance.loadAccounts();

		// Disable features which have not yet been implemented

		instance.messageSendSaveButton.attr('disabled', 'disabled');
	},

	clearIncomingMessage: function() {
		var instance = this;

		instance.readFromSpan.html('');
		instance.readReplyToSpan.html('');
		instance.readToSpan.html('');
		instance.readDateSpan.html('');
		instance.readSubjectSpan.html('');
		instance.readMailedBySpan.html('');
		instance.readBodySpan.html('');
	},

	clearOutgoingMessage: function() {
		var instance = this;

		instance.sendFromSelect.val('');
		instance.sendToInput.val('');
		instance.sendCcInput.val('');
		instance.sendBccInput.val('');
		instance.sendSubjectInput.val('');
		instance.sendBodySpan.css('visibility','hidden');
	},

	clearStatus: function() {
		var instance = this;

		instance.setStatus('', '');

		instance.statusSpan.css('display', 'none');
		instance.debugSpan.css('display', 'none');
	},

	getCurrentAccountId: function() {
		var instance = this;

		return instance._currentAccountId;
	},

	getCurrentFolder: function() {
		var instance = this;

		return instance._currentFolder;
	},

	getCurrentFolderName: function() {
		var instance = this;

		return instance.getCurrentFolder().name;
	},

	getCurrentMessage: function() {
		var instance = this;

		return instance._currentMessage;
	},

	getCurrentMessageUid: function() {
		var instance = this;

		return instance.getCurrentMessage().uid;
	},

	getCurrentPageNum: function() {
		var instance = this;

		return instance._currentPageNum;
	},

	getFirstMessageNumOnPage: function() {
		var instance = this;

		return (instance.getCurrentPageNum() - 1) * instance.getMessagesPerPage() + 1;
	},

	getLastMessageNumOnPage: function() {
		var instance = this;

		if (instance.getTotalPages() == instance.getCurrentPageNum()) {
			return instance.getTotalMessages();
		}
		else {
			return (instance.getCurrentPageNum()) * instance.getMessagesPerPage();
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

	getNewerUid: function() {

	},

	getOlderUid: function() {

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

	isSearchMode: function() {
		return _isSearchMode;
	},

	loadAccounts: function() {
		var instance = this;

		var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/accounts';

		instance.setStatus(Liferay.Language.get('loading-accounts'), jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: 'json',
			success: function(jsonAccounts) {
				instance.loadJsonAccounts(jsonAccounts);
			}
		});
	},

	loadFolders: function(accountId) {
 		var instance = this;

		// Set account id

		instance.setCurrentAccountId(accountId);

		if (instance._getCachedJsonFolders(accountId) != null) {

			// Messages found in cache

			var jsonFolders = instance._getCachedJsonFolders(accountId);

			instance.loadJsonFolders(jsonFolders);
		}
		else {

			// Get JSON

			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/folders?accountId=' + accountId;

			instance.setStatus(Liferay.Language.get('loading-folders'), jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonFolders) {
					instance.loadJsonFolders(jsonFolders);
					instance.accountSelectionSelect.val(accountId);
					instance.sendFromSelect.val(accountId);
				}
			});
		}
	},

	loadJsonAccounts: function(jsonAccounts) {
		var instance = this;

		instance.clearStatus();

		// Parse JSON

		var htmlAccountList = '';
		var firstAccountId = jsonAccounts.accounts[0].accountId;

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			var tempEmailAddress = account.emailAddress;
			var tempAccountId = account.accountId;

			htmlAccountList += '<option value="';
			htmlAccountList += tempAccountId;

			// Preselect first email account

			if (i == 0) {
				firstAccountId = tempAccountId;
				htmlAccountList += '" selected="selected">';
			}
			else {
				htmlAccountList += '">';
			}

			htmlAccountList += tempEmailAddress;
			htmlAccountList += '</option>';
		}

		// Inject HTML

		instance.accountSelectionSelect.html(htmlAccountList);
		instance.sendFromSelect.html(htmlAccountList);

		// Refresh folder handlers

		instance.setCurrentAccountId(firstAccountId);
		instance.loadFolders(firstAccountId);
	},

	loadJsonFolders: function(jsonFolders) {
		var instance = this;

		instance._cacheJsonFolders(instance.getCurrentAccountId(), jsonFolders);
		instance.clearStatus();

		// Parse JSON

		var htmlFolderList = '';

		for (i = 0; i < jsonFolders.folders.length; i++) {

			var fldr = jsonFolders.folders[i];

			var folderName = fldr.name;

			htmlFolderList += '<div class="folder" folderName="' + fldr.name + '"><a href="#">' + fldr.name + '</a></div>';
		}

		// Inject HTML

		instance.foldersDiv.html(htmlFolderList);

		// Refresh folder handlers

		instance.refreshFolderHandler();
		jQuery('.folder:first').click();
	},

	loadJsonMessage: function(jsonMessage) {
		var instance = this;

		instance.setCurrentMessage(jsonMessage);
		instance._cacheJsonMessage(instance.getCurrentAccountId(), instance.getCurrentFolderName(), jsonMessage);
		instance.clearStatus();

		// Parse JSON object

		var msg = jsonMessage;

		var msgUid = msg.uid;
		var msgFrom = msg.from;
		var msgTo = msg.to;
		var msgCc = msg.cc;
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
				msgBody += '<li><a href="' + themeDisplay.getLayoutURL() + '/-/mail/attachment?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUid=' + msgUid + '&fileName=' + msgAttachments[i][1] + '&contentPath=' + msgAttachments[i][0] + '" target="_blank">' + msgAttachments[i][1] + '</a>';
			}

			msgBody += '</ul>';
		}

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
	},

	loadJsonMessages: function(jsonMessages) {
		var instance = this;

		instance.setTotalPages(jsonMessages.pageCount);
		instance.setTotalMessages(jsonMessages.messageCount);
		instance._cacheJsonMessages(instance.getCurrentAccountId(), instance.getCurrentFolderName(), instance.getCurrentPageNum(), jsonMessages);
		instance.clearStatus();

		// Parse JSON

		var htmlMessageList = '';

		if (jsonMessages.messages.length == 0) {
			htmlMessageList += '<tr><td class="alert">' + Liferay.Language.get('no-messages-found') + '</td></tr>';
		}
		else {
			for (i = (jsonMessages.messages.length - 1); i >= 0; i--) {
				var msg = jsonMessages.messages[i];

				htmlMessageList += '<tr class="message ' + msg.read + '" messageUid="' + msg.uid + '">';
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
	},

	loadMessageByMsgNum: function(messageNum) {
		var instance = this;

		var folderName = instance.getCurrentFolderName();
		var accountId = instance.getCurrentAccountId();

		// Get JSON

		if (instance._getCachedJsonMessageByNum(accountId, folderName, messageNum) != null) {

			// Message found in cache

			var jsonMessage = instance._getCachedJsonMessageByNum(accountId, folderName, messageNum);

			instance.loadJsonMessage(jsonMessage);

			// Preload Message (not implemented)

			//instance.preloadMessage(folderName, messageNum + 1);
		}
		else {
			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/message_by_number?accountId=' + accountId + '&folderName=' + folderName + '&messageNum=' + messageNum;

			instance.setStatus(Liferay.Language.get('loading-message'), jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessage) {
					instance.loadJsonMessage(jsonMessage);
				}
			});
		}
	},

	loadMessageByUid: function(messageUid) {
		var instance = this;

		var folderName = instance.getCurrentFolderName();
		var accountId = instance.getCurrentAccountId();

		// Get JSON

		if (instance._getCachedJsonMessageByUid(accountId, folderName, messageUid) != null) {

			// Message found in cache

			var jsonMessage = instance._getCachedJsonMessageByUid(accountId, folderName, messageUid);

			instance.loadJsonMessage(jsonMessage);
		}
		else {
			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/message_by_uid?accountId=' + accountId + '&folderName=' + folderName + '&messageUid=' + messageUid;

			instance.setStatus(Liferay.Language.get('loading-message'), jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessage) {
					instance.loadJsonMessage(jsonMessage);
				}
			});
		}
	},

	loadMessages: function(folderName, pageNum) {
		var instance = this;

		// Set folder and page num

		instance.setCurrentPageNum(pageNum);
		instance.setCurrentFolderByName(folderName);

		var accountId = instance.getCurrentAccountId();

		// Get JSON

		if (instance._getCachedJsonMessages(accountId, folderName, pageNum) != null) {

			// Messages found in cache

			var jsonMessages = instance._getCachedJsonMessages(accountId, folderName, pageNum);

			instance.loadJsonMessages(jsonMessages);
			instance.preloadMessages(folderName, pageNum + 1);
		}
		else {

			// Retrieve messages via ajax

			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage();

			instance.setStatus(Liferay.Language.get('loading-messages'), jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessages) {
					instance.loadJsonMessages(jsonMessages);
				}
			});
		}
	},

	loadSearchResults: function(folderName, pageNum, searchString) {
		var instance = this;

		// Set folder and page num

		instance.setCurrentPageNum(pageNum);
		instance.setCurrentFolderByName(folderName);

		var accountId = instance.getCurrentAccountId();

		var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages_by_search?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage() + '&searchString=' + searchString;

		instance.setStatus(Liferay.Language.get('loading-messages'), jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: 'json',
			success: function(jsonMessages) {
				instance.loadJsonMessages(jsonMessages);
			}
		});
	},

	preloadMessages: function(folderName, pageNum) {
		var instance = this;

		var accountId = instance.getCurrentAccountId();

		// Get JSON

		if (instance._getCachedJsonMessages(accountId, folderName, pageNum) == null) {

			// Retrieve messages via ajax

			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage();

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessages) {
					instance._cacheJsonMessages(accountId, folderName, pageNum, jsonMessages);
				}
			});
		}
	},

	refreshFolderControls: function() {
 		var instance = this;

		// Folder Navigation

		instance.folderControlsNewestLink.css('display', 'inline');
		instance.folderControlsNewerLink.css('display', 'inline');
		instance.folderControlsOlderLink.css('display', 'inline');
		instance.folderControlsOldestLink.css('display', 'inline');

		// On first page

		if (instance.getCurrentPageNum() == 1) {
			instance.folderControlsNewestLink.css('display', 'none');
			instance.folderControlsNewerLink.css('display', 'none');
		}

		// On second page

		if (instance.getCurrentPageNum() == 2) {
			instance.folderControlsNewestLink.css('display', 'none');
		}

		// On second to last page

		if (instance.getCurrentPageNum() == instance.getTotalPages() - 1) {
			instance.folderControlsOldestLink.css('display', 'none');
		}

		// On last page

		if (instance.getCurrentPageNum() == instance.getTotalPages()) {
			instance.folderControlsOlderLink.css('display', 'none');
			instance.folderControlsOldestLink.css('display', 'none');
		}

		// Update page range count status	  (  x - x of xxxx )

		jQuery('.folder-controls .status').html('<span class="status-number">' + instance.getFirstMessageNumOnPage() + ' - ' + instance.getLastMessageNumOnPage() + '</span> of <span class="status-number">' + instance.getTotalMessages() + '</span>');

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
			instance.loadMessages(folderName, 1);

			// Show message list

			instance.setView('viewFolder');

			// Reset and set backgrounds

			jQuery('.folder').css('background-color', '#FFFFFF');
			instance.composeMailLink.css('background-color', '#FFFFFF');

			folderClicked.css('background-color', '#C3D9FF');

			return false;
		});
	},

	refreshFolders: function(resetCache) {
		var instance = this;

		if (resetCache) {
			instance._flushCachedJsonFolders();
		}

		instance.setStatus(Liferay.Language.get('refreshing-account'), '');

		instance.loadFolders(instance.getCurrentAccountId());
	},

	refreshMessageControlsNavigation: function() {
		var instance = this;

		instance.messageControlsNewerLink.css('display', 'inline');
		instance.messageControlsOlderLink.css('display', 'inline');

		// If on first message

		if (instance.getCurrentMessage().messageNumber == instance.getTotalMessages()) {
			instance.messageControlsNewerLink.css('display', 'none');
			instance.messageControlsOlderLink.css('display', 'inline');
		}

		// If on last message

		if (instance.getCurrentMessage().messageNumber == 1) {
			instance.messageControlsNewerLink.css('display', 'inline');
			instance.messageControlsOlderLink.css('display', 'none');
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

			instance.loadMessageByUid(messageUid);

			// Show message

			instance.setView('viewMessage');

			return false;
		});
	},

	refreshMessages: function(resetCache) {
		var instance = this;

		if (resetCache) {
			instance._flushCachedJsonMessages();
		}

		instance.setStatus(Liferay.Language.get('refreshing-folder'), '');

		instance.loadMessages(instance.getCurrentFolderName(), instance.getCurrentPageNum());
	},

	setCurrentAccountId: function(accountId) {
		var instance = this;

		instance._currentAccountId = accountId;
	},

	setCurrentFolderByName: function(folderName) {
		var instance = this;

		instance._currentFolder = instance._getCachedJsonFolder(instance.getCurrentAccountId(), folderName);
	},

	setCurrentMessage: function(jsonMessage) {
		var instance = this;

		instance._currentMessage = jsonMessage;
	},

	setCurrentPageNum: function(pageNum) {
		var instance = this;

		instance._currentPageNum = pageNum;
	},

	setMessageResponseType: function(responseType) {
		var instance = this;

		// Possible values include: 'reply', 'forward' and 'new'

		instance._messageResponseType = responseType;
	},

	setSearchMode: function(isSearchMode) {
		_isSearchMode = isSearchMode;
	},

	setStatus: function(message, debugMessage) {
		var instance = this;

		instance.statusSpan.css('display', 'inline');
		instance.statusSpan.html(message);

		instance.debugSpan.html(debugMessage);
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

		// Hide everything

		instance.folderDiv.css('display', 'none');
		instance.messageDiv.css('display', 'none');

		instance.messageControlsDiv.css('display', 'none');
		instance.messageReadDiv.css('display', 'none');
		instance.messageOptionsDiv.css('display', 'none');
		instance.messageSendDiv.css('display', 'none');

		// Show desired windows

		if (viewMode == 'viewFolder') {
			instance.clearIncomingMessage();

			instance.folderDiv.css('display', 'block');
		}
		else {
			instance.clearOutgoingMessage();

			instance.messageDiv.css('display', 'block');
			jQuery('#message-options td').css('background-color', '#F7F7F7');

			if (viewMode == 'viewMessage') {
				instance.messageControlsDiv.css('display', 'block');
				instance.messageReadDiv.css('display', 'block');
				instance.messageOptionsDiv.css('display', 'block');
			}
			else if (viewMode == 'replyMessage') {
				instance.messageControlsDiv.css('display', 'block');
				instance.messageReadDiv.css('display', 'block');
				instance.messageOptionsDiv.css('display', 'block');
				instance.messageSendDiv.css('display', 'block');
			}
			else if (viewMode == 'composeMessage') {
				instance.messageSendDiv.css('display', 'block');

				try {
					instance.sendBodySpan.css('visibility', 'visible');
					instance.sendBodyEditor.setHTML('');
				}
				catch (ex) {
				}
			}
		}
	},

	_assignEvents: function() {
		var instance = this;

		instance.accountSelectionSelect.change(function() {
			var newAccountId = jQuery(this).val();

			instance.loadFolders(newAccountId);
		});

		instance.composeMailLink.click(function() {

			instance.setView('composeMessage');
			instance.setMessageResponseType('new');

			// Reset and set backgrounds

			jQuery('.folder').css('background-color', '#FFFFFF');
			instance.composeMailLink.css('background-color', '#FFFFFF');

			instance.composeMailLink.css('background-color', '#C3D9FF');

			return false;
		});

		instance.folderControlsDeleteButton.click(function() {
			var messageUids = instance.getSelectedMessageUids();

			// Get JSON

			var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages_delete_by_uid?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids;

			instance.setStatus(Liferay.Language.get('deleting-messages'), jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonSuccess) {
					instance.refreshMessages(true);

					instance.setStatus(Liferay.Language.get('messages-have-been-deleted'), jsonUrl);
				}
			});
		});

		instance.folderControlsNewerLink.click(function() {
			var pageNum = parseInt(instance.getCurrentPageNum()) - 1;

			if (instance.isSearchMode())
			{
				instance.loadSearchResults(instance.getCurrentFolderName(), pageNum, instance.searchTextInput.val());
			}
			else {
				instance.loadMessages(instance.getCurrentFolderName(), pageNum);
			}

			return false;
		});

		instance.folderControlsNewestLink.click(function() {
			var pageNum = 1;

			if (instance.isSearchMode())
			{
				instance.loadSearchResults(instance.getCurrentFolderName(), pageNum, instance.searchTextInput.val());
			}
			else {
				instance.loadMessages(instance.getCurrentFolderName(), pageNum);
			}

			return false;
		});

		instance.folderControlsOlderLink.click(function() {
			var pageNum = parseInt(instance.getCurrentPageNum()) + 1;

			if (instance.isSearchMode())
			{
				instance.loadSearchResults(instance.getCurrentFolderName(), pageNum, instance.searchTextInput.val());
			}
			else {
				instance.loadMessages(instance.getCurrentFolderName(), pageNum);
			}

			return false;
		});

		instance.folderControlsOldestLink.click(function() {
			var pageNum = parseInt(instance.getTotalPages());

			if (instance.isSearchMode())
			{
				instance.loadSearchResults(instance.getCurrentFolderName(), pageNum, instance.searchTextInput.val());
			}
			else {
				instance.loadMessages(instance.getCurrentFolderName(), pageNum);
			}

			return false;
		});

		instance.folderControlsRefreshLink.click(function() {
			instance.refreshMessages(true);

			return false;
		});

		instance.folderControlsSelectAction.change(function() {

			// Do nothing if action not recognized

			var option = jQuery(this).val();

			if ((option != 'read') && (option != 'unread')) {
				instance.refreshFolderControls();

				return false;
			}

			// Do nothing if no messages are selected

			var messageUids = instance.getSelectedMessageUids();

			if (messageUids == '') {
				instance.refreshFolderControls();

				instance.setStatus(Liferay.Language.get('no-messages-selected'), '');

				return false;
			}

			// Do selected folder action

			var jsonUrl;

			if (option == 'read') {
				var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages_mark_as_read?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids + '&isRead=true';

				instance.setStatus(Liferay.Language.get('marking-messages-as-read'), jsonUrl);
			}
			else if (option == 'unread') {
				var jsonUrl = themeDisplay.getLayoutURL() + '/-/mail/messages_mark_as_read?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids + '&isRead=false';

				instance.setStatus(Liferay.Language.get('marking-messages-as-unread'), jsonUrl);
			}

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonSuccess) {
					instance.refreshMessages(true);
					instance.refreshFolderControls();
				}
			});
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

			instance.loadMessageByMsgNum(parseInt(instance.getCurrentMessage().messageNumber, 10) + 1);

			return false;
		});

		instance.messageControlsOlderLink.click(function() {
			instance.setView('viewMessage');

			instance.loadMessageByMsgNum(parseInt(instance.getCurrentMessage().messageNumber, 10)  - 1);

			return false;
		});

		instance.messageOptionsRespondDivs.click(function () {

			// Show message send

			instance.setView('replyMessage');
			jQuery(this).css('background-color', '#C3D9FF');

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

			if (jQuery(this).attr('id') == 'forward') {

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

					instance.sendBodySpan.css('visibility', 'visible');
					instance.sendBodyEditor.setHTML(fwdHeader + msgBody);
				}
			}
			else {

				// Reply

				instance.setMessageResponseType('reply');
				instance.sendSubjectInput.attr('value', 'Re: ' + msgSubject);

				if (jQuery(this).attr('id') == 'reply') {
					instance.sendToInput.attr('value', msgFrom);
				}
				else if (jQuery(this).attr('id') == 'reply-all') {
					instance.sendToInput.attr('value', msgFrom);
					instance.sendCcInput.attr('value', msgTo + ',' + msgCc);
				}
				else {
					alert('Unknown Message Response Type.  Reply? Reply All? Forward?');
				}

				// Load appropriate response message

				if (!messageModified) {
					var replyHeader = '<br /><br />On ' + msgDate + ', <' + msgFrom + '> wrote:<br /><br />';

					instance.sendBodySpan.css('visibility', 'visible');
					instance.sendBodyEditor.setHTML(replyHeader + msgBody);
				}
			}
		});

		instance.messageSendDiscardButton.click(function() {
			instance.setView('viewMessage');

			instance.setStatus(Liferay.Language.get('your-message-was-discarded'), '');
		});

		instance.messageSendSendButton.click(function() {

			var ajaxUrl = '/c/mail/message_send';

			var folderNameVal = '';
			var messageUidVal = 0;

			if (instance.getMessageResponseType() != 'new') {
				folderNameVal = instance.getCurrentFolderName();
				messageUidVal = instance.getCurrentMessage().uid;
			}

			var fromAccountId = instance.sendFromSelect.val();

			instance.setStatus(Liferay.Language.get('sending-message'), ajaxUrl);

			jQuery.post(
				ajaxUrl,
				{
					accountId: fromAccountId,
					content: instance.sendBodyEditor.getHTML(),
					folderName: folderNameVal,
					messageUid: messageUidVal,
					responseType: instance.getMessageResponseType(),
					subject: instance.sendSubjectInput.val(),

					fromAccountId: fromAccountId,
					recipientTo: instance.sendToInput.val(),
					recipientCc: instance.sendCcInput.val(),
					recipientBcc: instance.sendBccInput.val()
				},
				function(jsonResult) {
					instance.setView('viewFolder');

					instance.setStatus(Liferay.Language.get('your-message-was-sent'), jsonResult);
				}
			);

		});

		instance.searchButton.click(function() {
			instance.setSearchMode(true);

			instance.loadSearchResults(instance.getCurrentFolderName(), 1, instance.searchTextInput.val());

			// Reset and set backgrounds

			jQuery('.folder').css('background-color', '#FFFFFF');
			instance.composeMailLink.css('background-color', '#FFFFFF');
		});
	},

	_cacheJsonFolders: function(accountId, jsonFolders) {
		var instance = this;

		instance._jsonFolders[accountId] = jsonFolders;
	},

	_cacheJsonMessage: function(accountId, folderName, jsonMessage) {
		var instance = this;

		instance._jsonMessage[accountId + '.' + folderName + '.num' + jsonMessage.messageNumber] = jsonMessage;
		instance._jsonMessage[accountId + '.' + folderName + '.uid' + jsonMessage.uid] = jsonMessage;
	},

	_cacheJsonMessages: function(accountId, folderName, pageNum, jsonMessages) {
		var instance = this;

		instance._jsonMessages[accountId + '.' + folderName + '.' + pageNum] = jsonMessages;
	},

	_flushCachedJsonFolders: function() {
		var instance = this;

		instance._jsonFolders = new Object();
	},

	_flushCachedJsonMessages: function() {
		var instance = this;

		instance._jsonMessages = new Object();
	},

	_getCachedJsonFolder: function(accountId, folderName) {
		var instance = this;

		try {
			for (i = 0; i < instance._jsonFolders[accountId].folders.length; i++) {

				var fldr = instance._jsonFolders[accountId].folders[i];

				var fldrName = fldr.name;

				if (fldrName == folderName) {
					return fldr;
				}
			}
		}
		catch (ex) {
			return null;
		}
	},

	_getCachedJsonFolders: function(accountId) {
		var instance = this;

		try {
			if (instance._jsonFolders[accountId] == undefined) {
				return null;
			}
			else {
				return instance._jsonFolders[accountId];
			}
		}
		catch (ex) {
			return null;
		}
	},

	_getCachedJsonMessageByNum: function(accountId, folderName, messageNumber) {
		var instance = this;

		try {
			if (instance._jsonMessage[accountId + '.' + folderName + '.num' + messageNumber] == undefined) {
				return null;
			}
			else {
				return instance._jsonMessage[accountId + '.' + folderName + '.num' + messageNumber];
			}
		}
		catch (ex) {
			return null;
		}
	},

	_getCachedJsonMessageByUid: function(accountId, folderName, uid) {
		var instance = this;

		try {
			if (instance._jsonMessage[accountId + '.' + folderName + '.uid' + uid] == undefined) {
				return null;
			}
			else {
				return instance._jsonMessage[accountId + '.' + folderName + '.uid' + uid];
			}
		}
		catch (ex) {
			return null;
		}
	},

	_getCachedJsonMessages: function(accountId, folderName, pageNum) {
		var instance = this;

		try {
			if (instance._jsonMessages[accountId + '.' + folderName + '.' + pageNum] == undefined) {
				return null;
			}
			else {
				return instance._jsonMessages[accountId + '.' + folderName + '.' + pageNum];
			}
		}
		catch (ex) {
			return null;
		}
	},

	_currentAccountId: null,
	_currentFolderName: null,
	_currentPageNum: null,
	_currentMessage: null,
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

jQuery.ajaxQueue = function(o) {
	var old = o.complete;

	o.complete = function() {
		if (old) {
			old.apply(this, arguments);
		}

		jQuery.dequeue(jQuery.ajaxQueue, 'ajax');
	};

	jQuery([jQuery.ajaxQueue]).queue(
		'ajax',
		function() {
			jQuery.ajax(o);
		}
	);
};