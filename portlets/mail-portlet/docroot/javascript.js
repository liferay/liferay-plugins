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
	jQuery.dequeue( jQuery.ajaxQueue, "ajax" );
};

jQuery([ jQuery.ajaxQueue ]).queue("ajax", function(){
	jQuery.ajax( o );
	});
};

jQuery(document).ready(function() {

	//
	// Private vars
	//

	var _currentAccountId;
	var _currentFolderName;
	var _currentPageNum;
	var _currentMessage;

	var _messagesPerPage = 10;
	var _messageResponseType;
	var _totalPages;
	var _accounts;

	var __jsonFolders = new Object();
	var __jsonMessages = new Object();
	var __jsonMessage = new Object();

	//
	// Getters and Setters
	//

	var getCurrentAccountId = function() {
		return _currentAccountId;
	}

	var setCurrentAccountId = function(accountId) {
		_currentAccountId = accountId;
	}

	var getCurrentMessageUid = function() {
		return getCurrentMessage().uid;
	}

	var getCurrentMessage = function() {
		return _currentMessage;
	}

	var setCurrentMessage = function(jsonMessage) {
		_currentMessage = jsonMessage;
	}

	var getCurrentFolderName = function() {
		return getCurrentFolder().name;
	}

	var getCurrentFolder = function() {
		return _currentFolder;
	}

	var setCurrentFolderByName = function(folderName) {
		_currentFolder = getCachedJsonFolder(getCurrentAccountId(), folderName);
	}

	var getCurrentPageNum = function() {
		return _currentPageNum;
	}

	var setCurrentPageNum = function(pageNum) {
		_currentPageNum = pageNum;
	}

	var getMessagesPerPage = function() {
		return _messagesPerPage;
	}

	var getMessageResponseType = function() {
		return _messageResponseType;
	}

	var setMessageResponseType = function(responseType) {
		// Possible values include: "reply", "forward" and "new"

		_messageResponseType = responseType;
	}
	
	var getTotalPages = function() {
		return _totalPages;
	}

	var setTotalPages = function(totalPages) {
		_totalPages = totalPages;
	}
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Caching methods
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var getCachedJsonAccounts = function() {
		return _accounts;
	}

	var getAccountAddress = function(accountId) {
		var jsonAccounts = getCachedJsonAccounts();	

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			if (accountId == account.accountId) {
				return account.emailAccount;
			}
		}
	}

	var cacheJsonAccounts = function(accounts) {
		_accounts = accounts;
	}

	var getCachedJsonFolder = function(accountId, folderName) {
		try {
			for (i = 0; i < __jsonFolders[accountId].folders.length; i++) {

				var fldr = __jsonFolders[accountId].folders[i];

				var fldrName = fldr.name;

				if (fldrName == folderName) {
					return fldr;
				}
			}
		}
		catch (ex) {
			return null;
		}
	}

	var getCachedJsonFolders = function(accountId) {
		try {
			if (__jsonFolders[accountId] == undefined) {
				return null;
			}
			else {
				return __jsonFolders[accountId];
			}
		}
		catch (ex) {
			return null;
		}
	}

	var cacheJsonFolders = function(accountId, jsonFolders) {
		__jsonFolders[accountId] = jsonFolders;
	}

	var flushCachedJsonFolders = function() {
		__jsonFolders = new Object();
	}

	var getCachedJsonMessages = function(accountId, folderName, pageNum) {
		try {
			if (__jsonMessages[accountId + "." + folderName + "." + pageNum] == undefined) {
				return null;
			}
			else {
				return __jsonMessages[accountId + "." + folderName + "." + pageNum];
			}
		}
		catch (ex) {
			return null;
		}
	}

	var cacheJsonMessages = function(accountId, folderName, pageNum, jsonMessages) {
		__jsonMessages[accountId + "." + folderName + "." + pageNum] = jsonMessages;
	}

	var flushCachedJsonMessages = function() {
		__jsonMessages = new Object();
	}

	var cacheJsonMessage = function(accountId, folderName, msgNum, jsonMessage) {
		__jsonMessage[accountId + "." + folderName + "." + msgNum] = jsonMessage;
	}

	var getCachedJsonMessage = function(accountId, folderName, msgNum) {
		try {
			if (__jsonMessage[accountId + "." + folderName + "." + msgNum] == undefined) {
				return null;
			}
			else {
				return __jsonMessage[accountId + "." + folderName + "." + msgNum];
			}
		}
		catch (ex) {
			return null;
		}
	}
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var getFirstMessageNumOnPage = function() {
		return (getCurrentPageNum() - 1) * getMessagesPerPage() + 1;
	}

	var getLastMessageNumOnPage = function() {
		return (getCurrentPageNum()) * getMessagesPerPage();
	}

	var getSelectedMessageUids = function() {
		var checkedBoxes = jQuery(".message-checkbox:checked");
		var messageUids = "";

		jQuery.each(checkedBoxes, function() {
			messageUids += jQuery(this).parents(".message:first").attr("messageuid") + ",";
		});

		return messageUids;
	}

	var refreshMessages = function(resetCache) {
		if (resetCache) {
			flushCachedJsonMessages();
		}

		setStatus("Refreshing Folder.. ", "");

		loadMessages(getCurrentFolderName(), getCurrentPageNum());
	}

	var refreshFolders = function(resetCache) {
		if (resetCache) {
			flushCachedJsonFolders();
		}

		setStatus("Refreshing Account.. ", "");

		loadFolders(getCurrentAccountId());
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// UI Methods
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var clearStatus = function() {
		setStatus("","");

		jQuery("#status").css("display","none");
		jQuery("#debug").css("display","none");
	}

	var clearIncomingMessage = function() {
		jQuery("#read-from").html("");
		jQuery("#read-reply-to").html("");
		jQuery("#read-to").html("");
		jQuery("#read-date").html("");
		jQuery("#read-subject").html("");
		jQuery("#read-mailed-by").html("");
		jQuery("#read-body").html("");
	}

	var clearOutgoingMessage = function() { 
		jQuery("#send-from").val("");
		jQuery("#send-to").val("");
		jQuery("#send-cc").val("");
		jQuery("#send-bcc").val("");
		jQuery("#send-subject").val("");
		jQuery("#send-body").val("");
	}

	var setStatus = function(message, debugMessage) {
		jQuery("#status").css("display","inline");

		jQuery("#status").html(message);
		jQuery("#debug").html(debugMessage);
	}

	var setView = function(viewMode) {
		
		// Hide everything

		jQuery("#folder").css("display","none");
		jQuery("#message").css("display","none");

		jQuery(".message-controls").css("display","none");
		jQuery("#message-read").css("display","none");
		jQuery("#message-options").css("display","none");
		jQuery("#message-send").css("display","none");

		// Show desired windows

		if (viewMode == "viewFolder") {
			clearIncomingMessage();

			jQuery("#folder").css("display","block");
		}
		else {
			clearOutgoingMessage();

			jQuery("#message").css("display","block");
			jQuery("#message-options div").css("background-color", "#F7F7F7");

			if (viewMode == "viewMessage") {
				jQuery(".message-controls").css("display","block");
				jQuery("#message-read").css("display","block");
				jQuery("#message-options").css("display","block");
			}
			else if (viewMode == "replyMessage") {
				jQuery(".message-controls").css("display","block");
				jQuery("#message-read").css("display","block");
				jQuery("#message-options").css("display","block");
				jQuery("#message-send").css("display","block");
			}
			else if (viewMode == "composeMessage") {
				jQuery("#message-send").css("display","block");
			}
		}				
	}

	var refreshMessageControlsNavigation = function() {
		jQuery(".message-controls .newer").css("display","inline");
		jQuery(".message-controls .older").css("display","inline");

		// On first message
		if (getCurrentMessage().msgNum == getCurrentFolder().totalMessages) {
			jQuery(".message-controls .newer").css("display","none");
			jQuery(".message-controls .older").css("display","inline");
		}

		// On last message
		if (getCurrentMessage().msgNum == 1) {
			jQuery(".message-controls .newer").css("display","inline");
			jQuery(".message-controls .older").css("display","none");
		}

		// Update page count status

		var msgNumberDisplayed = parseInt(getCurrentFolder().totalMessages) - parseInt(getCurrentMessage().msgNum) + 1;

		jQuery(".message-controls .status").html("<span class='status-number'>" + msgNumberDisplayed + "</span> of <span class='status-number'>" + getCurrentFolder().totalMessages + "</span>");
	}

	var refreshFolderControls = function() {

		// Folder Navigation

		jQuery(".folder-controls .newest").css("display","inline");
		jQuery(".folder-controls .newer").css("display","inline");
		jQuery(".folder-controls .older").css("display","inline");
		jQuery(".folder-controls .oldest").css("display","inline");

		// On first page
		if (getCurrentPageNum() == 1) {
			jQuery(".folder-controls .newest").css("display","none");
			jQuery(".folder-controls .newer").css("display","none");
		}

		// On second page
		if (getCurrentPageNum() == 2) {
			jQuery(".folder-controls .newest").css("display","none");
		}

		// On second to last page
		if (getCurrentPageNum() == getTotalPages() - 1) {
			jQuery(".folder-controls .oldest").css("display","none");
		}

		// On last page
		if (getCurrentPageNum() == getTotalPages()) {
			jQuery(".folder-controls .older").css("display","none");
			jQuery(".folder-controls .oldest").css("display","none");
		}

		// Update page range count status	  (  x - x of xxxx )
		jQuery(".folder-controls .status").html("<span class='status-number'>" + getFirstMessageNumOnPage() + " - " + getLastMessageNumOnPage() + "</span> of <span class='status-number'>" + getCurrentFolder().totalMessages + "</span>");


		// Folder Options

		jQuery(".folder-controls .select-actions").val("none");
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Load methods
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var loadAccounts = function() {

		// Get json

		var jsonUrl = "/c/mail/get_accounts";

		setStatus("Loading accounts.. ", jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: "json",
			success: function(jsonAccounts){ loadJsonAccounts(jsonAccounts); }
		});
	}

	var loadFolders = function(accountId) {

		// Set account id

		setCurrentAccountId(accountId);

		if (getCachedJsonFolders(accountId) != null) {
			
			// Messages found in cache

			var jsonFolders = getCachedJsonFolders(accountId);

			loadJsonFolders(jsonFolders);
		}
		else {

			// Get json

			var jsonUrl = "/c/mail/get_folders?accountId=" + accountId;

			setStatus("Loading folders.. ", jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: "json",
				success: function(jsonFolders){ 
					loadJsonFolders(jsonFolders); 
					jQuery("#account-selection").val(accountId);
					jQuery("#send-from").val(accountId);
				}
			});
		}
	}

	var loadSearchResults = function(folderName, pageNum, searchString) {

		var accountId = getCurrentAccountId();

		var jsonUrl = "/c/mail/get_messages_by_search?accountId=" + accountId + "&folderName=" + folderName + "&pageNum=" + pageNum + "&messagesPerPage=" + getMessagesPerPage() + "&searchString=" + searchString; 
		
		setStatus("Loading messages.. ", jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: "json",
			success: function(jsonMessages){ loadJsonMessages(jsonMessages); }
		});
	}

	var loadMessages = function(folderName, pageNum) {

		// Set folder & page num

		setCurrentPageNum(pageNum);
		setCurrentFolderByName(folderName);

		var accountId = getCurrentAccountId();

		// Get json

		if (getCachedJsonMessages(accountId, folderName, pageNum) != null) {
			
			// Messages found in cache
			
			var jsonMessages = getCachedJsonMessages(accountId, folderName, pageNum);

			loadJsonMessages(jsonMessages);

			// Preload

			preloadMessages(folderName, pageNum + 1);
		}
		else {

			// Retrieve messages via ajax
			
			var jsonUrl = "/c/mail/get_messages?accountId=" + accountId + "&folderName=" + folderName + "&pageNum=" + pageNum + "&messagesPerPage=" + getMessagesPerPage(); 
	 		
			setStatus("Loading messages.. ", jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: "json",
				success: function(jsonMessages){ loadJsonMessages(jsonMessages); }
			});
		}
	}

	var loadMessageByMsgNum = function(messageNum) {
		// Get folder id 

		var folderName = getCurrentFolderName();

		// Get json

		if (getCachedJsonMessages(getCurrentAccountId(), folderName, messageNum) != null) {
			
			// Message found in cache
			
			var jsonMessage = getCachedJsonMessage(getCurrentAccountId(), folderName, messageNum);

			loadJsonMessage(jsonMessage);

			// Preload

			preloadMessage(folderName, messageNum + 1);
		}
		else {
			var jsonUrl = "/c/mail/get_message_by_num?accountId=" + getCurrentAccountId() + "&folderName=" + folderName + "&messageNum=" + messageNum; 

			setStatus("Loading message.. ", jsonUrl);

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: "json",
				success: function(jsonMessage){ loadJsonMessage(jsonMessage); }
			});
		}
	}

	var loadMessageByUid = function(messageUid) {

		// Get folder id 

		var folderName = getCurrentFolderName();
		var accountId = getCurrentAccountId();

		// Get json

		var jsonUrl = "/c/mail/get_message_by_uid?accountId=" + accountId + "&folderName=" + folderName + "&messageUid=" + messageUid; 

		setStatus("Loading message.. ", jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: "json",
			success: function(jsonMessage){ loadJsonMessage(jsonMessage); }
		});
	}

	var loadJsonAccounts = function(jsonAccounts) {
		
		// Caching

		cacheJsonAccounts(jsonAccounts);

		// Clear status

		clearStatus();

		// Parse json

		var htmlAccountList = "";
		var firstAccountId = jsonAccounts.accounts[0].accountId;

		for (i = 0; i < jsonAccounts.accounts.length; i++) {

			var account = jsonAccounts.accounts[i];

			var tempAccountName = account.emailAccount;
			var tempAccountId = account.accountId;

			htmlAccountList += "<option value='";
			htmlAccountList += tempAccountId;

			// Preselect first email account

			if (i == 0) {
				firstAccountId = tempAccountId;
				htmlAccountList += "' selected='selected'>";
			}
			else {
				htmlAccountList += "'>";
			}

			htmlAccountList += tempAccountName;
			htmlAccountList += "</option>";
		}

		// Inject html

		jQuery("#account-selection").html(htmlAccountList);
		jQuery("#send-from").html(htmlAccountList);

		// Refresh folder handlers

		setCurrentAccountId(firstAccountId);
		loadFolders(firstAccountId);
	}

	var loadJsonFolders = function(jsonFolders) {

		// Caching

		cacheJsonFolders(getCurrentAccountId(), jsonFolders);

		// Clear status

		clearStatus();

		// Parse json

		var htmlFolderList = "";

		for (i = 0; i < jsonFolders.folders.length; i++) {

			var fldr = jsonFolders.folders[i];

			var folderName = fldr.id;
			var folderNewMessages = fldr.newMessages;
			var folderTotalMessages = fldr.totalMessages;

			htmlFolderList += "<div class='folder' folderName='" + fldr.name + "'><a href='#'>" + fldr.name + "</a></div>";
		}

		// Inject html

		jQuery("#folders").html(htmlFolderList);

		// Refresh folder handlers

		refreshFolderHandler();
		jQuery(".folder:first").click();
	}

	var loadJsonMessages = function(jsonMessages) {

		// Caching

		setTotalPages(jsonMessages.totalPages);
		cacheJsonMessages(getCurrentAccountId(), getCurrentFolderName(), getCurrentPageNum(), jsonMessages);

		// Clear status

		clearStatus();

		// Parse json

		var htmlMessageList = "";

		for (i = 0; i < jsonMessages.messages.length; i++) {

			var msg = jsonMessages.messages[i];

			htmlMessageList += "<tr class='message " + msg.read + "' messageUid='" + msg.uid + "'>";
			htmlMessageList += "	<td><div class='message-col-0'><input type='checkbox' class='message-checkbox' /></div></td>";
			htmlMessageList += "	<td><div class='message-col-1'><span class='message-from'>" + msg.from + "</span></div></td>";
			htmlMessageList += "	<td><div class='message-col-2'><span class='message-subject'>" + msg.subject + "</span> - <span class='message-body-preview'>" + msg.bodyPreview + "</span></div></td>";
			htmlMessageList += "	<td><div class='message-col-3'><span class='message-date'>" + msg.date + "</span></div></td>";
			htmlMessageList += "</tr>";
		}

		// Inject html  

		jQuery("#message-list").html(htmlMessageList);	
		
		// Refresh event handlers

		refreshMessageHandler();

		// Rrefresh html

		refreshFolderControls();
	}

	var loadJsonMessage = function(jsonMessage) {

		// Caching

		setCurrentMessage(jsonMessage);
		cacheJsonMessage(getCurrentAccountId(), getCurrentFolderName(), getCurrentMessage().msgNum, jsonMessage)

		// Clear status

		clearStatus();

		// Set message id

		setCurrentMessage(jsonMessage);

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
		
		if (msgBody.indexOf("<img") == -1) {
			msgBody = msgBody.replace(/\r\n/g,"<br />");
			msgBody = msgBody.replace(/\n\n/g,"<br />");
		}

		// Inject html

		jQuery("#read-from").html(msgFrom);
		jQuery("#read-reply-to").html("-");
		jQuery("#read-to").html(msgTo);
		jQuery("#read-cc").html(msgCc);
		jQuery("#read-date").html(msgDate);
		jQuery("#read-subject").html(msgSubject);
		jQuery("#read-mailed-by").html("-");
		jQuery("#read-body").html(msgBody);

		// Update other html

		jQuery(".message-controls .folder-name").html(getCurrentFolderName());

		// Update message navigation

		refreshMessageControlsNavigation();
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Preload methods
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var preloadMessages = function(folderName, pageNum) {

		var accountId = getCurrentAccountId();

		// Get json

		if (getCachedJsonMessages(accountId, folderName, pageNum) == null) {

			// Retrieve messages via ajax
			
			var jsonUrl = "/c/mail/get_messages?accountId=" + accountId + "&folderName=" + folderName + "&pageNum=" + pageNum + "&messagesPerPage=" + getMessagesPerPage(); 

			jQuery.ajaxQueue({
				url: jsonUrl,
				dataType: "json",
				success: function(jsonMessages){ cacheJsonMessages(accountId, folderName, pageNum, jsonMessages); }
			});
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// jQuery triggers
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var refreshMessageHandler = function() {
		jQuery(".message td:not(:first-child)").click(function () {

			// Load message

			var messageUid = jQuery(this).parent(".message:first").attr("messageUid");

			loadMessageByUid(messageUid);

			// Show message

			setView("viewMessage");

			return false;
		});
	}

	var refreshFolderHandler = function() {
		jQuery(".folder").click(function () {

			var folderName = jQuery(this).attr("folderName");

			// Load message list

			loadMessages(folderName, 1);

			// Show message list

			setView("viewFolder");

			return false;
		});
	}

	jQuery("#account-selection").change(function() {
		var newAccountId = jQuery(this).val();

		loadFolders(newAccountId);
	});

	jQuery("#search-button").click(function() {
		var searchString = jQuery("#search-text").val();

		loadSearchResults(getCurrentFolderName(), 1, searchString);
	});

	// DELETE

	jQuery(".folder-controls .delete").click(function() {
		var messageUids = getSelectedMessageUids();

		// Get json

		var jsonUrl = "/c/mail/messages_delete?accountId=" + getCurrentAccountId() + "&folderName=" + getCurrentFolderName() + "&messageUids=" + messageUids;

		setStatus("Deleting messages..", jsonUrl);

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: "json",
			success: function(jsonSuccess){ 
				refreshMessages(true);
				setStatus("Messages have been deleted", jsonUrl);
			}
		});
	});

	// READ OR UNREAD

	jQuery(".folder-controls .select-actions").change(function() {
		
		// Do nothing if action not recognized

		var option = jQuery(this).val();

		if ((option != "read") && (option != "unread")) {
			refreshFolderControls();
			return false;
		}

		// Do nothing if no messages are selected

		var messageUids = getSelectedMessageUids();

		if (messageUids == "") {
			refreshFolderControls();
			setStatus("No messages selected..", jsonUrl);
			return false;
		}

		// Do action

		var jsonUrl;

		if (option == "read") {
			var jsonUrl = "/c/mail/messages_mark_as_read?accountId=" + getCurrentAccountId() + "&folderName=" + getCurrentFolderName() + "&messageUids=" + messageUids + "&isRead=true";

			setStatus("Marking messages as read..", jsonUrl);
		}
		else if (option == "unread") {
			var jsonUrl = "/c/mail/messages_mark_as_read?accountId=" + getCurrentAccountId() + "&folderName=" + getCurrentFolderName() + "&messageUids=" + messageUids + "&isRead=false";

			setStatus("Marking messages as unread..", jsonUrl);
		}

		jQuery.ajaxQueue({
			url: jsonUrl,
			dataType: "json",
			success: function(jsonSuccess){ 
				refreshMessages(true);
				refreshFolderControls();
			}
		});
	});

	// REPLY

	jQuery("#message-send .discard").click(function() {
		setView("viewMessage");

		setStatus("Your message has been discarded..", "");
	});
	
	jQuery(".folder-controls .refresh").click(function() { 
		refreshMessages(true);

		clearStatus();

		return false;
	});
	
	jQuery("#reply,#reply-all,#forward").click(function () {

		// Show message send

		setView("replyMessage");
		jQuery(this).css("background-color", "#C3D9FF");

		// Load default values for response message based on original message

		var msg = getCurrentMessage();

		var msgUid = msg.uid;
		var msgFrom = msg.from;
		var msgTo = msg.to;
		var msgCc = msg.cc;
		var msgSubject = msg.subject;
		var msgBody = msg.body;
		var msgDate = msg.date;

		// Has message been changed?
		var messageModified = false;

		if (jQuery(this).attr("id") == "forward") {
			
			// Forward
			
			setMessageResponseType("forward");
			jQuery("#send-subject").val("Fwd: " + msgSubject);

			// Load appropriate response message

			if (!messageModified) {
				var fwdHeader = "\n\n---------- Forwarded message ----------\n";
				fwdHeader += "From: " + msgFrom + "\n";
				fwdHeader += "Date: " + msgDate + "\n";
				fwdHeader += "Subject: " + msgSubject + "\n";
				fwdHeader += "To: " + msgTo + "\n\n";

				jQuery("#send-body").val(fwdHeader + msgBody);
			}
		}
		else {

			// Reply

			setMessageResponseType("reply");
			jQuery("#send-subject").attr("value", "Re: " + msgSubject);

			if (jQuery(this).attr("id") == "reply") {
				jQuery("#send-to").attr("value", msgFrom);
			}
			else if (jQuery(this).attr("id") == "reply-all") {
				jQuery("#send-to").attr("value", msgFrom);
				jQuery("#send-cc").attr("value", msgTo + "," + msgCc);
			}
			else {
				alert("Unknown Message Response Type.  Reply? Reply All? Forward?");
			}

			// Load appropriate response message

			if (!messageModified) {
				var replyHeader = "\n\nOn " + msgDate + ", <" + msgFrom + "> wrote:\n\n";
				jQuery("#send-body").val(replyHeader + msgBody);
			}
		}
	});

	// MESSAGE NAVIGATION

	jQuery(".message-controls .older").click(function() {
		loadMessageByMsgNum(parseInt(getCurrentMessage().msgNum,10)  - 1);

		return false;
	});

	jQuery(".message-controls .newer").click(function() {
		loadMessageByMsgNum(parseInt(getCurrentMessage().msgNum,10) + 1);

		return false;
	});

	jQuery(".message-controls .back").click(function() {
		setView("viewFolder");

		return false;
	});

	// FOLDER NAVIGATION

	jQuery(".folder-controls .newest").click(function() {
		loadMessages(getCurrentFolderName(), 1); 

		return false;
	});

	jQuery(".folder-controls .newer").click(function() {
		loadMessages(getCurrentFolderName(), parseInt(getCurrentPageNum()) - 1); 

		return false;
	});

	jQuery(".folder-controls .older").click(function() {
		loadMessages(getCurrentFolderName(), parseInt(getCurrentPageNum()) + 1); 

		return false;
	});

	jQuery(".folder-controls .oldest").click(function() {
		loadMessages(getCurrentFolderName(), parseInt(getTotalPages())); 

		return false;
	});

	// FOLDER CONTROLS

	jQuery(".folder-controls .select-all").click(function() {
		jQuery(".message-checkbox").attr("checked","true");

		return false;
	});

	jQuery(".folder-controls .select-none").click(function() {
		jQuery(".message-checkbox").attr("checked","");

		return false;
	});

	jQuery(".folder-controls .select-read").click(function() {
		jQuery(".read .message-checkbox").attr("checked","true");
		jQuery(".unread .message-checkbox").attr("checked","");

		return false;
	});

	jQuery(".folder-controls .select-unread").click(function() {
		jQuery(".read .message-checkbox").attr("checked","");
		jQuery(".unread .message-checkbox").attr("checked","true");

		return false;
	});

	// MESSAGE RESPONSE

	jQuery("#compose-mail").click(function() {

		setView("composeMessage");
		setMessageResponseType("new");

		return false;
	});

	jQuery("#message-send .send").click(function() {

		var ajaxUrl = "/c/mail/message_send"; 

		var folderNameVal = "";
		var messageUidVal = 0;


		if (getMessageResponseType() != "new") {
			folderNameVal = getCurrentFolderName();
			messageUidVal = getCurrentMessage().uid;		
		}

		var fromAccountId = jQuery("#send-from").val();

		setStatus("Sending message..", ajaxUrl);

		jQuery.post(
			ajaxUrl, 
			{
				accountId: fromAccountId, 
				content: jQuery("#send-body").val(), 
				folderName: folderNameVal, 
				messageUid: messageUidVal, 
				responseType: getMessageResponseType(),
				subject: jQuery("#send-subject").val(), 

				fromAccountId: fromAccountId, 
				recipientTo: jQuery("#send-to").val(),
				recipientCc: jQuery("#send-cc").val(), 
				recipientBcc: jQuery("#send-bcc").val() 
			}, 
			function(jsonResult) {
				setView("viewFolder");
				setStatus("Your message has been sent", jsonResult);
			}
		);

	});

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Initialization methods
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	loadAccounts();

	// Features which have not been implemented

	jQuery(".send-options .save").attr("disabled", "disabled"); 
});