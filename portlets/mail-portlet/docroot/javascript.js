Liferay.Mail = {
	init: function(){
		var instance = this;

		instance._messagesPerPage = 10;

		// Commonly used jQuery Expressions

		instance.accountSelectionSelect = jQuery('#account-selection');

		instance.statusSpan = jQuery('#status');
		instance.debugSpan = jQuery('#debug');
		instance.composeMailLink = jQuery('#compose-mail');
		instance.searchButton = jQuery('#search-button');
		instance.searchTextInput = jQuery('#search-text');

		instance.readFromSpan = jQuery('#read-from');
		instance.readReplyToSpan = jQuery('#read-reply-to');
		instance.readToSpan = jQuery('#read-to');
		instance.readCcSpan = jQuery('#read-cc');
		instance.readDateSpan = jQuery('#read-date');
		instance.readSubjectSpan = jQuery('#read-subject');
		instance.readMailedBySpan = jQuery('#read-mailed-by');
		instance.readBodySpan = jQuery('#read-body');

		instance.sendFromSelect = jQuery('#send-from');
		instance.sendToInput = jQuery('#send-to');
		instance.sendCcInput = jQuery('#send-cc');
		instance.sendBccInput = jQuery('#send-bcc');
		instance.sendSubjectInput = jQuery('#send-subject');
		instance.sendBodyTextarea = jQuery('#send-body');

		instance.folderDiv = jQuery('#folder');
		instance.foldersDiv = jQuery('#folders');

		instance.folderControlsNewestLink = jQuery('.folder-controls .newest');
		instance.folderControlsNewerLink = jQuery('.folder-controls .newer');
		instance.folderControlsOlderLink = jQuery('.folder-controls .older');
		instance.folderControlsOldestLink = jQuery('.folder-controls .oldest');

		instance.folderControlsDeleteButton = jQuery('.folder-controls .delete');
		instance.folderControlsRefreshLink = jQuery('.folder-controls .refresh');

		instance.folderControlsSelectAction = jQuery('.folder-controls .select-actions');
		instance.folderControlsSelectAllLink = jQuery('.folder-controls .select-all');
		instance.folderControlsSelectNoneLink = jQuery('.folder-controls .select-none');
		instance.folderControlsSelectReadLink = jQuery('.folder-controls .select-read');
		instance.folderControlsSelectUnreadLink = jQuery('.folder-controls .select-unread');

		instance.messageDiv = jQuery('#message');

		instance.messageControlsDiv = jQuery('.message-controls');
		instance.messageControlsNewerLink = jQuery('.message-controls .newer');
		instance.messageControlsOlderLink = jQuery('.message-controls .older');
		instance.messageControlsStatus = jQuery('.message-controls .status');
		instance.messageControlsBackLink = jQuery('.message-controls .back');

		instance.messageOptionsDiv = jQuery('#message-options');
		instance.messageOptionsRespondDivs = jQuery('#reply,#reply-all,#forward');

		instance.messageReadDiv = jQuery('#message-read');
		instance.messageSendDiv = jQuery('#message-send');
		instance.messageSendSendButton = jQuery('#message-send .send');
		instance.messageSendSaveButton = jQuery('#message-send .save');
		instance.messageSendDiscardButton = jQuery('#message-send .discard');
		instance.messageListTable = jQuery('#message-list');

		// Init methods

		instance._assignEvents();
		instance.loadAccounts();

		// Disable features which have not yet been implemented

		instance.messageSendSaveButton.attr('disabled', 'disabled');
	},

	//
	// Getters and Setters
	//

	getCurrentAccountId: function() {
		var instance = this;

		return instance._currentAccountId;
	},

	setCurrentAccountId: function(accountId) {
		var instance = this;

		instance._currentAccountId = accountId;
	},

	getCurrentMessageUid: function() {
		var instance = this;

		return instance.getCurrentMessage().uid;
	},

	getCurrentMessage: function() {
		var instance = this;

		return instance._currentMessage;
	},

	setCurrentMessage: function(jsonMessage) {
		var instance = this;

		instance._currentMessage = jsonMessage;
	},

	getCurrentFolderName: function() {
		var instance = this;

		return instance.getCurrentFolder().name;
	},

	getCurrentFolder: function() {
		var instance = this;

		return instance._currentFolder;
	},

	setCurrentFolderByName: function(folderName) {
		var instance = this;

		instance._currentFolder = instance.getCachedJsonFolder(instance.getCurrentAccountId(), folderName);
	},

	getCurrentPageNum: function() {
		var instance = this;

		return instance._currentPageNum;
	},

	setCurrentPageNum: function(pageNum) {
		var instance = this;

		instance._currentPageNum = pageNum;
	},

	getMessagesPerPage: function() {
		var instance = this;

		return instance._messagesPerPage;
	},

	getMessageResponseType: function() {
		var instance = this;

		return instance._messageResponseType;
	},

	setMessageResponseType: function(responseType) {
		var instance = this;

		// Possible values include: 'reply', 'forward' and 'new'

		instance._messageResponseType = responseType;
	},

	getTotalMessages: function() {
		var instance = this;

		return instance._totalMessages;
	},

	setTotalMessages: function(totalMessages) {
		var instance = this;

		instance._totalMessages = totalMessages;
	},

	getTotalPages: function() {
		var instance = this;

		return instance._totalPages;
	},

	setTotalPages: function(totalPages) {
		var instance = this;

		instance._totalPages = totalPages;
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

	getSelectedMessageUids: function() {
		var instance = this;

		var checkedBoxes = jQuery('.message-checkbox:checked');
		var messageUids = '';

		jQuery.each(checkedBoxes, function() {
			messageUids += jQuery(this).parents('.message:first').attr('messageuid') + ',';
		});

		return messageUids;
	},

	isSearchMode: function() {
		return _isSearchMode;
	},

	setSearchMode: function(isSearchMode) {
		_isSearchMode = isSearchMode;
	},

	getNewerUid: function() {

	},

	getOlderUid: function() {

	},

	refreshMessages: function(resetCache) {
		var instance = this;

		if (resetCache) {
			instance.flushCachedJsonMessages();
		}

		instance.setStatus('Refreshing Folder.. ', '');

		instance.loadMessages(instance.getCurrentFolderName(), instance.getCurrentPageNum());
	},

	refreshFolders: function(resetCache) {
		var instance = this;

		if (resetCache) {
			instance.flushCachedJsonFolders();
		}

		instance.setStatus('Refreshing Account.. ', '');

		instance.loadFolders(instance.getCurrentAccountId());
	},

	//
	// UI Methods
	//

	clearStatus: function() {
		var instance = this;

		instance.setStatus('','');
		instance.statusSpan.css('display', 'none');
		instance.debugSpan.css('display', 'none');
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
		instance.sendBodyTextarea.val('');
	},

	setStatus: function(message, debugMessage) {
		var instance = this;

		instance.statusSpan.css('display', 'inline');
		instance.statusSpan.html(message);

		instance.debugSpan.html(debugMessage);
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
			}
		}
	},

	refreshMessageControlsNavigation: function() {
		var instance = this;

		instance.messageControlsNewerLink.css('display', 'inline');
		instance.messageControlsOlderLink.css('display', 'inline');

		// If on first message

		if (instance.getCurrentMessage().msgNum == instance.getTotalMessages()) {
			instance.messageControlsNewerLink.css('display', 'none');
			instance.messageControlsOlderLink.css('display', 'inline');
		}

		// If on last message

		if (instance.getCurrentMessage().msgNum == 1) {
			instance.messageControlsNewerLink.css('display', 'inline');
			instance.messageControlsOlderLink.css('display', 'none');
		}

		// Update page count status

		var msgNumberDisplayed = parseInt(instance.getTotalMessages()) - parseInt(instance.getCurrentMessage().msgNum) + 1;

		instance.messageControlsStatus.html('<span class="status-number">' + msgNumberDisplayed + '</span> of <span class="status-number">' + instance.getTotalMessages() + '</span>');
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

	//
	// Load methods
	//

	loadAccounts: function() {
		var instance = this;

		var jsonUrl = '/c/mail/get_accounts';

		instance.setStatus('Loading accounts.. ', jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: 'json',
			success: function(jsonAccounts){ instance.loadJsonAccounts(jsonAccounts); }
		});
	},

	loadFolders: function(accountId) {
 		var instance = this;

		// Set account id

		instance.setCurrentAccountId(accountId);

		if (instance.getCachedJsonFolders(accountId) != null) {

			// Messages found in cache

			var jsonFolders = instance.getCachedJsonFolders(accountId);

			instance.loadJsonFolders(jsonFolders);
		}
		else {

			// Get json

			var jsonUrl = '/c/mail/get_folders?accountId=' + accountId;

			instance.setStatus('Loading folders.. ', jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonFolders){
					instance.loadJsonFolders(jsonFolders);
					instance.accountSelectionSelect.val(accountId);
					instance.sendFromSelect.val(accountId);
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

		var jsonUrl = '/c/mail/get_messages_by_search?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage() + '&searchString=' + searchString;

		instance.setStatus('Loading messages.. ', jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: 'json',
			success: function(jsonMessages){ instance.loadJsonMessages(jsonMessages); }
		});
	},

	loadMessages: function(folderName, pageNum) {
		var instance = this;

		// Set folder and page num

		instance.setCurrentPageNum(pageNum);
		instance.setCurrentFolderByName(folderName);

		var accountId = instance.getCurrentAccountId();

		// Get json

		if (instance.getCachedJsonMessages(accountId, folderName, pageNum) != null) {

			// Messages found in cache

			var jsonMessages = instance.getCachedJsonMessages(accountId, folderName, pageNum);

			instance.loadJsonMessages(jsonMessages);
			instance.preloadMessages(folderName, pageNum + 1);
		}
		else {

			// Retrieve messages via ajax

			var jsonUrl = '/c/mail/get_messages?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage();

			instance.setStatus('Loading messages.. ', jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessages){ instance.loadJsonMessages(jsonMessages); }
			});
		}
	},

	loadMessageByMsgNum: function(messageNum) {
		var instance = this;

		var folderName = instance.getCurrentFolderName();
		var accountId = instance.getCurrentAccountId();

		// Get json

		if (instance.getCachedJsonMessageByNum(accountId, folderName, messageNum) != null) {

			// Message found in cache

			var jsonMessage = instance.getCachedJsonMessageByNum(accountId, folderName, messageNum);

			instance.loadJsonMessage(jsonMessage);

			// Preload Message (not implemented)

			//instance.preloadMessage(folderName, messageNum + 1);
		}
		else {
			var jsonUrl = '/c/mail/get_message_by_num?accountId=' + accountId + '&folderName=' + folderName + '&messageNum=' + messageNum;

			instance.setStatus('Loading message.. ', jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessage){ instance.loadJsonMessage(jsonMessage); }
			});
		}
	},

	loadMessageByUid: function(messageUid) {
		var instance = this;

		var folderName = instance.getCurrentFolderName();
		var accountId = instance.getCurrentAccountId();

		// Get json

		if (instance.getCachedJsonMessageByUid(accountId, folderName, messageUid) != null) {

			// Message found in cache

			var jsonMessage = instance.getCachedJsonMessageByUid(accountId, folderName, messageUid);

			instance.loadJsonMessage(jsonMessage);
		}
		else {
			var jsonUrl = '/c/mail/get_message_by_uid?accountId=' + accountId + '&folderName=' + folderName + '&messageUid=' + messageUid;

			instance.setStatus('Loading message.. ', jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessage){ instance.loadJsonMessage(jsonMessage); }
			});
		}
	},

	loadJsonAccounts: function(jsonAccounts) {
		var instance = this;

		instance.cacheJsonAccounts(jsonAccounts);
		instance.clearStatus();

		// Parse json

		var htmlAccountList = '';
		var firstAccountId = jsonAccounts.accounts[0].accountId;

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			var tempAccountName = account.emailAccount;
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

			htmlAccountList += tempAccountName;
			htmlAccountList += '</option>';
		}

		// Inject html

		instance.accountSelectionSelect.html(htmlAccountList);
		instance.sendFromSelect.html(htmlAccountList);

		// Refresh folder handlers

		instance.setCurrentAccountId(firstAccountId);
		instance.loadFolders(firstAccountId);
	},

	loadJsonFolders: function(jsonFolders) {
		var instance = this;

		instance.cacheJsonFolders(instance.getCurrentAccountId(), jsonFolders);
		instance.clearStatus();

		// Parse json

		var htmlFolderList = '';

		for (i = 0; i < jsonFolders.folders.length; i++) {

			var fldr = jsonFolders.folders[i];

			var folderName = fldr.id;
			var folderNewMessages = fldr.newMessages;

			htmlFolderList += '<div class="folder" folderName="' + fldr.name + '"><a href="#">' + fldr.name + '</a></div>';
		}

		// Inject html

		instance.foldersDiv.html(htmlFolderList);

		// Refresh folder handlers

		instance.refreshFolderHandler();
		jQuery('.folder:first').click();
	},

	loadJsonMessages: function(jsonMessages) {
		var instance = this;

		instance.setTotalPages(jsonMessages.totalPages);
		instance.setTotalMessages(jsonMessages.totalMessages);
		instance.cacheJsonMessages(instance.getCurrentAccountId(), instance.getCurrentFolderName(), instance.getCurrentPageNum(), jsonMessages);
		instance.clearStatus();

		// Parse json

		var htmlMessageList = '';

		if (jsonMessages.messages.length == 0) {
			htmlMessageList += '<tr><td class="alert">No Messages</td></tr>';
		}
		else {
			for (i = 0; i < jsonMessages.messages.length; i++) {

				var msg = jsonMessages.messages[i];

				htmlMessageList += '<tr class="message ' + msg.read + '" messageUid="' + msg.uid + '">';
				htmlMessageList += '	<td><div class="message-col-0"><input type="checkbox" class="message-checkbox" /></div></td>';
				htmlMessageList += '	<td><div class="message-col-1"><span class="message-from">' + msg.from + '</span></div></td>';
				htmlMessageList += '	<td><div class="message-col-2"><span class="message-subject">' + msg.subject + '</span> - <span class="message-body-preview">' + msg.bodyPreview + '</span></div></td>';
				htmlMessageList += '	<td><div class="message-col-3"><span class="message-date">' + msg.date + '</span></div></td>';
				htmlMessageList += '</tr>';
			}
		}

		// Inject html

		instance.messageListTable.html(htmlMessageList);

		// Refresh html and event handlers

		instance.refreshMessageHandler();
		instance.refreshFolderControls();
	},

	loadJsonMessage: function(jsonMessage) {
		var instance = this;

		instance.setCurrentMessage(jsonMessage);
		instance.cacheJsonMessage(instance.getCurrentAccountId(), instance.getCurrentFolderName(), jsonMessage);
		instance.clearStatus();

		// Parse json object

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

		if (msgBody.indexOf('<img') == -1) {
			msgBody = msgBody.replace(/\r\n/g,'<br />');
			msgBody = msgBody.replace(/\n\n/g,'<br />');
		}

		// Inject html

		instance.readFromSpan.html(msgFrom);
		instance.readReplyToSpan.html('-');
		instance.readToSpan.html(msgTo);
		instance.readCcSpan.html(msgCc);
		instance.readDateSpan.html(msgDate);
		instance.readSubjectSpan.html(msgSubject);
		instance.readMailedBySpan.html('-');
		instance.readBodySpan.html(msgBody);

		// Update other html

		jQuery('.message-controls .folder-name').html(instance.getCurrentFolderName());

		// Update message navigation

		instance.refreshMessageControlsNavigation();
	},

	//
	// Preload methods
	//

	preloadMessages: function(folderName, pageNum) {
		var instance = this;

		var accountId = instance.getCurrentAccountId();

		// Get json

		if (instance.getCachedJsonMessages(accountId, folderName, pageNum) == null) {

			// Retrieve messages via ajax

			var jsonUrl = '/c/mail/get_messages?accountId=' + accountId + '&folderName=' + folderName + '&pageNum=' + pageNum + '&messagesPerPage=' + instance.getMessagesPerPage();

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonMessages){ instance.cacheJsonMessages(accountId, folderName, pageNum, jsonMessages); }
			});
		}
	},

	//
	// jQuery triggers
	//

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

	_assignEvents: function() {
		var instance = this;

		instance.accountSelectionSelect.change(function() {
			var newAccountId = jQuery(this).val();

			instance.loadFolders(newAccountId);
		});

		instance.searchButton.click(function() {
			instance.setSearchMode(true);

			instance.loadSearchResults(instance.getCurrentFolderName(), 1, instance.searchTextInput.val());

			// Reset and set backgrounds

			jQuery('.folder').css('background-color', '#FFFFFF');
			instance.composeMailLink.css('background-color', '#FFFFFF');
		});

		// DELETE

		instance.folderControlsDeleteButton.click(function() {
			var messageUids = instance.getSelectedMessageUids();

			// Get json

			var jsonUrl = '/c/mail/messages_delete?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids;

			instance.setStatus('Deleting messages..', jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: 'json',
				success: function(jsonSuccess){
					instance.refreshMessages(true);
					instance.setStatus('Messages have been deleted', jsonUrl);
				}
			});
		});

		// READ OR UNREAD

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
				instance.setStatus('No messages selected..', jsonUrl);
				return false;
			}

			// Do selected folder action

			var jsonUrl;

			if (option == 'read') {
				var jsonUrl = '/c/mail/messages_mark_as_read?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids + '&isRead=true';

				instance.setStatus('Marking messages as read..', jsonUrl);
			}
			else if (option == 'unread') {
				var jsonUrl = '/c/mail/messages_mark_as_read?accountId=' + instance.getCurrentAccountId() + '&folderName=' + instance.getCurrentFolderName() + '&messageUids=' + messageUids + '&isRead=false';

				instance.setStatus('Marking messages as unread..', jsonUrl);
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

		// REPLY

		instance.messageSendDiscardButton.click(function() {
			instance.setView('viewMessage');

			instance.setStatus('Your message has been discarded..', '');
		});

		instance.folderControlsRefreshLink.click(function() {
			instance.refreshMessages(true);

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
					var fwdHeader = '\n\n---------- Forwarded message ----------\n';
					fwdHeader += 'From: ' + msgFrom + '\n';
					fwdHeader += 'Date: ' + msgDate + '\n';
					fwdHeader += 'Subject: ' + msgSubject + '\n';
					fwdHeader += 'To: ' + msgTo + '\n\n';

					instance.sendBodyTextarea.val(fwdHeader + msgBody);
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
					var replyHeader = '\n\nOn ' + msgDate + ', <' + msgFrom + '> wrote:\n\n';
					instance.sendBodyTextarea.val(replyHeader + msgBody);
				}
			}
		});

		// MESSAGE NAVIGATION

		instance.messageControlsOlderLink.click(function() {
			instance.setView('viewMessage');

			instance.loadMessageByMsgNum(parseInt(instance.getCurrentMessage().msgNum,10)  - 1);

			return false;
		});

		instance.messageControlsNewerLink.click(function() {
			instance.setView('viewMessage');

			instance.loadMessageByMsgNum(parseInt(instance.getCurrentMessage().msgNum,10) + 1);

			return false;
		});

		instance.messageControlsBackLink.click(function() {
			instance.setView('viewFolder');

			return false;
		});

		// FOLDER NAVIGATION

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

		// FOLDER CONTROLS

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

		// MESSAGE RESPONSE

		instance.composeMailLink.click(function() {

			instance.setView('composeMessage');
			instance.setMessageResponseType('new');

			// Reset and set backgrounds

			jQuery('.folder').css('background-color', '#FFFFFF');
			instance.composeMailLink.css('background-color', '#FFFFFF');

			instance.composeMailLink.css('background-color', '#C3D9FF');

			return false;
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

			instance.setStatus('Sending message..', ajaxUrl);

			jQuery.post(
				ajaxUrl,
				{
					accountId: fromAccountId,
					content: instance.sendBodyTextarea.val(),
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
					instance.setStatus('Your message has been sent', jsonResult);
				}
			);

		});
	},

	//
	// Private caching methods
	//

	getCachedJsonAccounts: function() {
		var instance = this;

		return instance._accounts;
	},

	getAccountAddress: function(accountId) {
		var instance = this;

		var jsonAccounts = instance.getCachedJsonAccounts();

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			if (accountId == account.accountId) {
				return account.emailAccount;
			}
		}
	},

	cacheJsonAccounts: function(accounts) {
		var instance = this;

		instance._accounts = accounts;
	},

	getCachedJsonFolder: function(accountId, folderName) {
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

	getCachedJsonFolders: function(accountId) {
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

	cacheJsonFolders: function(accountId, jsonFolders) {
		var instance = this;

		instance._jsonFolders[accountId] = jsonFolders;
	},

	flushCachedJsonFolders: function() {
		var instance = this;

		instance._jsonFolders = new Object();
	},

	getCachedJsonMessages: function(accountId, folderName, pageNum) {
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

	cacheJsonMessages: function(accountId, folderName, pageNum, jsonMessages) {
		var instance = this;

		instance._jsonMessages[accountId + '.' + folderName + '.' + pageNum] = jsonMessages;
	},

	flushCachedJsonMessages: function() {
		var instance = this;

		instance._jsonMessages = new Object();
	},

	cacheJsonMessage: function(accountId, folderName, jsonMessage) {
		var instance = this;

		instance._jsonMessage[accountId + '.' + folderName + '.num' + jsonMessage.msgNum] = jsonMessage;
		instance._jsonMessage[accountId + '.' + folderName + '.uid' + jsonMessage.uid] = jsonMessage;
	},

	getCachedJsonMessageByNum: function(accountId, folderName, msgNum) {
		var instance = this;

		try {
			if (instance._jsonMessage[accountId + '.' + folderName + '.num' + msgNum] == undefined) {
				return null;
			}
			else {
				return instance._jsonMessage[accountId + '.' + folderName + '.num' + msgNum];
			}
		}
		catch (ex) {
			return null;
		}
	},

	getCachedJsonMessageByUid: function(accountId, folderName, uid) {
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

	//
	// Private vars
	//

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

jQuery(
	function(){
		Liferay.Mail.init();
	}
);

/*
* http://dev.jquery.com/~john/plugins/ajaxqueue/
*
* Queued Ajax requests.
* A new Ajax request won't be started until the previous queued
* request has finished.
*/

jQuery.ajaxQueue = function(o){
	var _old = o.complete;
	o.complete = function(){
	if ( _old ) _old.apply( this, arguments );
	jQuery.dequeue( jQuery.ajaxQueue, 'ajax' );
};

jQuery([ jQuery.ajaxQueue ]).queue('ajax', function(){
	jQuery.ajax( o );
	});
};
