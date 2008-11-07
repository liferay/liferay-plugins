(function ($) {
$.fn.disableSelection = function() {
	return this.attr('unselectable', 'on').css('MozUserSelect', 'none').bind('selectstart.ui', function() {
		return false;
	});
};

$.fn.enableSelection = function() {
	return this.attr('unselectable', 'off').css('MozUserSelect', '').unbind('selectstart.ui');
};

Liferay.Chat = {};

Liferay.Chat.Util = {
	formatTime: function(time) {
		var instance = this;

		time = Number(time);
		time = new Date(time);

		var meridian = 'am';
		var hour = time.getHours();
		var minute = time.getMinutes();

		if (hour >= 11) {
			meridian = 'pm';
		}

		if (hour > 12) {
			hour -= 12;
		}

		if (hour == 0) {
			hour += 12;
		}

		if (minute < 10) {
			minute = '0' + minute;
		}

		return hour + ':' + minute + ' ' + meridian;
	},

	getCurrentTimestamp: function() {
		var instance = this;

		return (new Date()).getTime();
	},

	getUserImagePath: function(userId) {
		var instance = this;

		return themeDisplay.getPathImage() + '/user_portrait?img_id=' + userId;
	},

	TIMESTAMP_24: (24 * 60 * 60 * 1000)
};

Liferay.Chat.Panel = new Class({
	initialize: function(options) {
		var instance = this;

		if (!options.container) {
			instance._container = Liferay.Chat.Manager.getContainer()
		}
		else {
			instance._container = jQuery(options.container);
		}

		instance._chatProperties = {};

		instance._eventObj = jQuery(instance);

		instance._panelId = options.panelId;
		instance._panelTitle = options.panelTitle;
		instance._panelIcon = options.panelIcon;

		instance._created = Liferay.Chat.Util.getCurrentTimestamp();

		var panelHTML = instance._setPanelHTML(options.panelHTML);

		instance.set('panelHTML', panelHTML);

		instance._createPanel(options.fromMarkup);

		if (options.panelTitle) {
			instance.setTitle(options.panelTitle);
		}

		instance._popupTrigger.disableSelection();
	},

	bind: function(event, fn, scope) {
		var instance = this;

		if (event && fn) {
			var method = function(event) {
				fn.apply(scope || instance, arguments);
			};

			instance._eventObj.bind(event, method);
		}
	},

	close: function() {
		var instance = this;

		instance._panel.remove();

		instance.trigger('close', instance);
	},

	get: function(key) {
		var instance = this;

		return instance._chatProperties[key];
	},

	getPanel: function() {
		var instance = this;

		return instance._panel;
	},

	getTitle: function() {
		var instance = this;

		return instance._popupTitle.text();
	},

	hide: function() {
		var instance = this;

		instance.set('selected', false);
		instance._panel.removeClass('selected');

		instance.trigger('hide', instance);
	},

	set: function(key, value) {
		var instance = this;

		instance._chatProperties[key] = value;

		return value;
	},

	setTitle: function(value) {
		var instance = this;

		instance._popupTrigger.find('.trigger-name').text(value);
		instance._popupTitle.text(value);
	},

	show: function() {
		var instance = this;

		instance.set('selected', true);
		instance._panel.addClass('selected');

		instance.trigger('show', instance);

	},

	toggle: function() {
		var instance = this;
		if (instance.get('selected')) {
			instance.hide();
		}
		else {
			instance.show();
		}
	},

	resumeEvents: function(){
		var instance = this;

		instance._eventsSuspended = false;
	},

	suspendEvents: function(){
		var instance = this;

		instance._eventsSuspended = true;
	},

	trigger: function(event, data){
		var instance = this;

		if (instance._eventsSuspended == false) {
			instance._eventObj.triggerHandler(event, data);
		}
	},

	_createPanel: function(fromMarkup) {
		var instance = this;

		var panel;
		var panelHTML = '';

		var markup = instance.get('panelHTML');

		if (markup) {
			panelHTML = markup;
		}

		if (fromMarkup) {
			panelHTML = fromMarkup;
		}

		panel = jQuery(panelHTML);

		instance._popup = panel.find('.popup');
		instance._popupTitle = panel.find('.popup-title');
		instance._textBox = panel.find('textarea');
		instance._popupTrigger = panel.find('.link');

		instance._popupTrigger.click(
			function(event) {
				instance.toggle();
			}
		);

		panel.find('.popup-button').bind(
			'click',
			function (event) {
				var target = jQuery(event.target);

				if (target.hasClass('minimize')) {
					instance.hide();
				}
				else if (target.hasClass('close')) {
					instance.close();
				}
			}
		);

		instance._panel = panel;

		instance._container.append(panel);
	},

	_setPanelHTML: function(html) {
		var instance = this;

		if (!html) {
			html = '<li class="panel">' +
				'<div class="link"><span class="trigger-name"></span></div>' +
				'<div class="popup">' +
					'<div class="popup-window">' +
						'<div class="popup-button minimize"></div>' +
						'<div class="popup-title"></div>' +
						'<div class="popup-content"></div>' +
					'</div>' +
				'</div>' +
			'</li>';
		}

		return html;
	},

	_eventsSuspended: false
});

Liferay.Chat.Conversation = Liferay.Chat.Panel.extend({
	initialize: function(options) {
		var instance = this;

		instance.parent(options);

		instance._chatInput = instance._panel.find('.popup-input textarea');
		instance._chatOutput = instance._panel.find('.popup-output');
		instance._statusMessage = instance._panel.find('.popup-profile');

		instance._created = Liferay.Chat.Util.getCurrentTimestamp();
		instance._lastMessageTime = 0;
		instance._unreadMessages = 0;
		instance._originalPageTitle = document.title;

		instance._unreadMessagesContainer = instance._panel.find('.unread');

		if (!instance._unreadMessagesContainer.length) {
			instance._unreadMessagesContainer = jQuery('<div class="unread" />');
			instance._popupTrigger.append(instance._unreadMessagesContainer);
		}

		if (options.statusMessage) {
			instance._statusMessage.text(options.statusMessage);
		}

		instance._chatInput.bind('keyup focus',
			function (event) {
				instance._keystroke(event, this);
			}
		);
	},

	getLastMessageTime: function() {
		var instance = this;

		return instance._lastMessageTime;
	},

	setTyping: function(isTyping) {
		var instance = this;

		if (isTyping) {
			instance._panel.addClass('typing');
		}
		else {
			instance._panel.removeClass('typing');
		}
	},

	setAsRead: function() {
		var instance = this;

		instance._panel.removeClass('waiting');
		instance._unreadMessagesContainer.hide();

		instance._unreadMessages = 0;

		instance.set('lastReadTime', Liferay.Chat.Util.getCurrentTimestamp());
		document.title = instance._originalPageTitle;
	},

	setAsUnread: function() {
		var instance = this;

		if (!instance.get('selected')) {
			var panel = instance._panel;

			panel.addClass('waiting');

			if (instance._unreadMessages > 1) {
				instance._unreadMessagesContainer.text(instance._unreadMessages);
				instance._unreadMessagesContainer.show();
			}
			else {
				Liferay.Chat.Manager.triggerSound();
				instance._unreadMessagesContainer.hide();
			}

			document.title = instance._originalPageTitle + ' - Unread messages (' + instance._unreadMessages + ')';
		}
	},

	send: function(options) {
		var instance = this;

		Liferay.Chat.Manager.send(options);
	},

	show: function() {
		var instance = this;

		instance.parent();
		instance.setAsRead();
	},

	update: function(options) {
		var instance = this;

		instance.setTyping(false);

		if (options.time > instance._lastMessageTime) {
			if (options.isIncoming) {

				if (options.text.length) {
					if (!instance.get('selected')) {
						var lastRead = instance.get('lastReadTime') || 0;

						if (lastRead < options.time) {
							instance._unreadMessages++;
						}
					}

					instance.setAsUnread();
				}
				else {
					instance.setTyping(true);
				}
			}
			if (options.text.length) {
				instance._updateMessageWindow(options);
			}
		}

		if (options.statusMessage) {
			instance._statusMessage.text(options.statusMessage);
		}
	},

	_keystroke: function(event, el) {
		var instance = this;

		var current;
		var output = instance._chatOutput[0];
		var reScroll;
		var userId = instance._panelId;

		if (event.type == 'keyup') {

			if (instance.get('typedTo') == userId) {
				instance.send(
					{
						toUserId: userId,
						content: ''
					}
				);
			}

			instance.set('typedTo', userId);
		}

		if (event.keyCode == 13) {
			var now = Liferay.Chat.Util.getCurrentTimestamp();

			instance.send(
				{
					toUserId: userId,
					content: el.value,
					createDate: now
				}
			);

			instance._updateMessageWindow(
				{
					text: el.value,
					time: now
				}
			);

			instance._lastMessageTime = now;

			el.value = '';
		}

		// this.style.height = '0px';

		current = {
			clientHeight: el.clientHeight,
			scrollHeight: el.scrollHeight,
			scrollTop: el.scrollTop
		};

		current.scrollHeight = (current.scrollHeight > 64) ? 64 : current.scrollHeight;
		el.style.overflowY = (current.scrollHeight == 64) ? 'scroll' : 'hidden';

		reScroll = (output.scrollTop == output.scrollHeight - output.clientHeight);

		el.style.height = current.scrollHeight + 'px';

		el.parentNode.style.height = (current.scrollHeight + 5) + 'px';
		output.style.height = (206 - current.scrollHeight) + 'px';
		output.scrollTop = (reScroll) ? output.scrollHeight - output.clientHeight : output.scrollTop;
	},

	_setPanelHTML: function() {
		var instance = this;

		var userImagePath = Liferay.Chat.Util.getUserImagePath(instance._panelIcon);

		var html = '<li class="user user_' + instance._panelId + '" userId="' + instance._panelId + '">' +
						'<div class="link" userId="' + instance._panelId + '">' +
							'<span class="trigger-name"></span>' +
							'<div class="typing-status"></div>' +
						'</div>' +
						'<div class="popup">' +
							'<div class="popup-window">' +
								'<div class="popup-button minimize"></div>' +
								'<div class="popup-button close"></div>' +
								'<img class="popup-icon" src="' + userImagePath + '" />' +
								'<div class="popup-title">' + instance._panelTitle + '</div>' +
								'<div class="popup-profile">...</div>' +
								'<div class="popup-output"></div>' +
								'<div class="popup-input">' +
									'<textarea></textarea>' +
								'</div>' +
							'</div>' +
						'</div>' +
					'</li>';

			return html;
	},

	_updateMessageWindow: function(options) {
		var instance = this;

		var output = instance._chatOutput;
		var isIncoming = options.isIncoming;
		var userName = Liferay.Chat.Manager.getCurrentUser().userName;
		var cssClass = 'outgoing';

		if (isIncoming) {
			cssClass = 'incoming';
			userName = instance._panelTitle;
		}

		var message = '<p class="blurb ' + cssClass + '">' +
						'<b class="name">' + userName + '</b>' +
						'<i class="date">' + Liferay.Chat.Util.formatTime(options.time) + '</i>' +
						'<span class="text">' + options.text + '</span>'
					'</p>';

		output[0].innerHTML += message;

		instance._lastMessageTime = options.time;

		setTimeout(
			function () {
				output[0].scrollTop = output[0].scrollHeight - output[0].clientHeight;
		}, 1);
	}
});

Liferay.Chat.Manager = {
	init: function() {
		var instance = this;

		instance._activeBrowser = true;
		instance._activeBrowserKey = Liferay.Util.randomInt();
		instance._activePanelId = jQuery('#activePanelId').val() || '';

		instance._baseUrl = themeDisplay.getLayoutURL() + '/-/chat/';
		instance._baseImagePath = themeDisplay.getPathImage() + '/user_portrait';
		instance._created = Liferay.Chat.Util.getCurrentTimestamp();
		instance._entriesFrom = instance._created - Liferay.Chat.Util.TIMESTAMP_24;

		instance._myStatus = jQuery('#chatMyStatus');

		instance._latestUrl = instance._baseUrl + 'latest';
		instance._sendUrl = instance._baseUrl + 'send';

		instance._container = jQuery('#chat-tabs');

		instance._sound = new SWFObject("/chat-portlet/alert.swf", "alertsound", "0", "0", "8");
		instance._soundContainer = jQuery('#btm-dock-sound');

		var buddyList = new Liferay.Chat.Panel(
			{
				fromMarkup: '#chat-tabs > .buddylist',
				panelId: 'buddylist'
			}
		);

		var settings = new Liferay.Chat.Panel(
			{
				fromMarkup: '#chat-tabs > .settings',
				panelId: 'settings'
			}
		);

		instance._addPanel('buddylist', buddyList);
		instance._addPanel('settings', settings);

		instance._onlineBuddies = buddyList.getPanel().find('.online-users');

		if (instance._onlineBuddies.length) {
			var buddies = jQuery('li', instance._onlineBuddies[0]);
			buddies.livequery(
				'click',
				function(event) {
					instance._createChatFromUser(this);
				}
			);
		}

		var settingsPanel = settings.getPanel();

		instance._statusMessage = settingsPanel.find('#statusMessage').val() || '';
		instance._online = settingsPanel.find('#onlineStatus').is(':checked') || true;
		instance._playSound = settingsPanel.find('#playSound').is(':checked') || true;

		if (instance._activePanelId.length &&
			!/buddylist|settings/.test(instance._activePanelId) &&
			!isNaN(parseInt(instance._activePanelId))) {

			instance._createChatFromUser(instance._activePanelId);
		}

		instance._currentUser = {
			userId: themeDisplay.getUserId(),
			userName: themeDisplay.getUserName()
		};

		instance._requestInterval = 5000;

		instance._createRequestTimer();

		var saveSettings = settingsPanel.find('#saveSettings');

		saveSettings.click(
			function(event) {
				instance._statusMessage = settingsPanel.find('#statusMessage').val();
				instance._online = settingsPanel.find('#onlineStatus').is(':checked');
				instance._playSound = settingsPanel.find('#playSound').is(':checked');

				instance._getRequest();
				settings.hide();
				settingsPanel.addClass('saved');
				instance._activePanelId = '';

				if (instance._statusMessage) {
					instance._myStatus.html('You are <strong>' + instance._statusMessage + '</strong>');
				}

				setTimeout(
					function() {
						settingsPanel.removeClass('saved');
					}, 1500);
			}
		);
	},

	getContainer: function() {
		var instance = this;

		return instance._container;
	},

	getCurrentUser: function() {
		var instance = this;

		return instance._currentUser;
	},

	send: function(options) {
		var instance = this;

		instance._cancelRequestTimer();

		instance._sendRequest(options,
			function(message) {
				instance._createRequestTimer();
			}
		);
	},

	show: function(panelName) {
		var instance = this;

		var panel = instance._panels[panelName];

		if (panel) {
			panel.addClass('selected');
		}
	},

	triggerSound: function() {
		var instance = this;

	 	if (instance._playSound) {
	 		instance._sound.write(instance._soundContainer[0]);
	 	}
	},

	_addChat: function(chatName, chat) {
		var instance = this;

		instance._chatSessions[chatName] = chat;
	},

	_addPanel: function(panelName, panel) {
		var instance = this;

		instance._panels[panelName] = panel;

		panel.bind('show', instance._onPanelShow, instance);
		panel.bind('close', instance._onPanelClose, instance);
	},

	_cancelRequestTimer: function() {
		var instance = this;

		clearInterval(instance._requestTimer);
	},

	_createRequestTimer: function() {
		var instance = this;

		instance._requestTimer = setInterval(
			function() {
				instance._getRequest();
			},
			instance._requestInterval
		);
	},

	_createChatFromUser: function(user) {
		var instance = this;

		var buddy;
		var userId = '';

		if (typeof user == 'string' || typeof user == 'number') {
			buddy = jQuery('li[userId=' + user + ']', instance._onlineBuddies[0]);
			userId = user;
		}
		else if(user.tagName || user.jquery) {
			buddy = jQuery(user);
			userId = buddy.attr('userId');
		}

		if (buddy.length) {
			var userIcon = buddy.find('img').attr('src');
			var userId = buddy.attr('userId');
			var userName = buddy.find('.name').text();

			var chat = instance._chatSessions[userId];

			if (!chat) {
				instance._createChatSession(
					{
						userIcon: userIcon,
						userId: userId,
						userName: userName,
						open: true
					}
				);
			} else {
				chat.show();
			}
		}
	},

	_createChatSession: function(options) {
		var instance = this;

		var userId = options.userId;

		var chat = new Liferay.Chat.Conversation(
			{
				panelId: options.userId,
				panelTitle: options.userName,
				panelIcon: options.userIcon,
				statusMessage: options.statusMessage
			}
		);

		instance._addChat(userId, chat);
		instance._addPanel(userId, chat);

		if (options.open) {
			chat.show();
		}

		return chat;
	},

	_getChat: function(chatName) {
		var instance = this;

		return instance._chatSessions[chatName];
	},

	_getPanel: function(panelName) {
		var instance = this;

		return instance._panels[panelName];
	},

	_hideChats: function() {
		var instance = this;

		for (var i in instance._chatSessions) {
			instance._chatSessions[i].hide();
		}
	},

	_onPanelShow: function(event, panel) {
		var instance = this;

		for (var i in instance._panels) {
			if (instance._panels[i] != panel) {
				instance._panels[i].hide();
			}
		}

		instance._activePanelId = panel._panelId;
		instance._getRequest();
	},

	_onPanelClose: function(event, panel) {
		var instance = this;

		delete instance._panels[panel._panelId];

		if (panel instanceof Liferay.Chat.Conversation) {
			delete instance._chatSessions[panel._panelId];
		}

		instance._activePanelId = '';
		instance._getRequest();
	},

	_getRequest: function() {
		var instance = this;

		if (!instance._requestFiring) {
			var params = instance._getSettings();

			jQuery.ajax(
				{
					beforeSend: function (connection) {
						instance._requestFiring = true;

						connection.setRequestHeader('Connection', 'Keep-Alive');
					},

					cache: false,
					type: 'POST',
					url: instance._latestUrl,
					data: params,
					dataType: 'json',

					complete: function(xHR) {
						instance._requestFiring = false;
					},

					success: function (response) {
						instance._activeBrowser = response.activeBrowser;

						instance._updateBuddies(response.buddies);
						instance._updateConversations(response.entries);
					}
				}
			);
		}
	},

	_getSettings: function() {
		var instance = this;

		return {
			activeBrowser: instance._activeBrowser,
			activeBrowserKey: instance._activeBrowserKey,
			activePanelId: instance._activePanelId,
			online: instance._online,
			statusMessage: instance._statusMessage,
			playSound: instance._playSound,
			createDate: instance._entriesFrom
		};
	},

	_sendRequest: function(options, callback) {
		var instance = this;

		jQuery.ajax(
			{
				cache: false,
				type: 'POST',
				url: instance._sendUrl,
				data: options,
				success: function () {
					if (callback) {
						callback.apply(this, arguments);
					}
					else {
						instance._getRequest();
					}
				}
			}
		);
	},

	_updateBuddies: function(buddies) {
		var instance = this;

		var buddyList = buddies || [];
		var numBuddies = buddyList.length;

		var currentUser = instance._currentUser;
		var currentBuddies = instance._buddies;

		instance._onlineBuddiesCount = numBuddies;
		var buffer = [];

		for (var i=0; i < numBuddies; i++) {
			var buddy = buddyList[i];

				currentBuddies[buddy.userId] = {
					userId: buddy.userId,
					userName: buddy.fullName,
					userIcon: buddy.portraitId,
					isActive: buddy.awake,
					lastMessageTime: buddy.lastMessageTime,
					statusMessage: buddy.statusMessage,
					unreadCount: (buddy.unreadCount || 0),
					isTyping: false
				};

				var cssClass = 'active';

				if (!buddy.awake) {
					cssClass = 'idle';
				}

				var userImagePath = Liferay.Chat.Util.getUserImagePath(buddy.userIcon);

				buffer.push('<li class="' + cssClass + '" userId="' + buddy.userId + '">' +
								'<img src="' + userImagePath + '" />' +
								'<div class="name">' + buddy.fullName + '</div>' +
							'</li>');
		}

		instance._onlineBuddies.html(buffer.join(''));

		instance._updateBuddyList();
	},

	_updateBuddyList: function(buddy) {
		var instance = this;

		var buddyList = instance._getPanel('buddylist');

		var title = buddyList.getTitle();
		title = title.replace(/\(\d+\)/, '(' + instance._onlineBuddiesCount + ')');

		buddyList.setTitle(title);
	},

	_updateConversations: function(entries) {
		var instance = this;

		var numEntries = entries.length || 0;

		var currentUser = instance._currentUser;
		var currentUserId = currentUser.userId;

		if (!numEntries) {
			for(var i in instance._chatSessions) {
				instance._chatSessions[i].setTyping(false);
			}
		}

		for (var i = 0; i < numEntries; i++) {
			var entry = entries[i];

			var userId = entry.toUserId;
			var isIncoming = false;

			if (entry.fromUserId != currentUserId) {
				userId = entry.fromUserId;
				isIncoming = true;
			}

			var buddy = instance._buddies[userId];

			if (buddy) {
				var chat = instance._chatSessions[userId];

				if (!chat && entry.content != '' && entry.createDate > instance._created) {
					chat = instance._createChatSession(
						{
							userIcon: buddy.userIcon,
							userId: buddy.userId,
							userName: buddy.userName,
							statusMessage: buddy.statusMessage
						}
					);
				}

				if (chat) {
					chat.update(
						{
							isIncoming: isIncoming,
							time: entry.createDate,
							text: entry.content,
							statusMessage: buddy.statusMessage
						}
					);
				}
			}
		}
	},

	_buddies: {},
	_baseUrl: '',
	_chatSessions: {},
	_entries: [],
	_panels: {},
	_requestFiring: false,
	_settings: {}
};

$(function () {
	Liferay.Chat.Manager.init();
});
}(jQuery));