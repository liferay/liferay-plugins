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

Liferay.Chat.Panel = new Expanse.Class(
	{
		initialize: function(options) {
			var instance = this;

			if (!options.container) {
				instance._tabsContainer = Liferay.Chat.Manager.getContainer()
			}
			else {
				instance._tabsContainer = jQuery(options.container);
			}

			instance._chatProperties = {};
			instance._eventsSuspended = false;

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

		resumeEvents: function(){
			var instance = this;

			instance._eventsSuspended = false;
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

		suspendEvents: function(){
			var instance = this;

			instance._eventsSuspended = true;
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

			instance._popup = panel.find('.chat-panel');
			instance._popupTitle = panel.find('.panel-title');
			instance._textBox = panel.find('textarea');
			instance._popupTrigger = panel.find('.panel-trigger');

			instance._popupTrigger.click(
				function(event) {
					instance.toggle();
				}
			);

			panel.find('.panel-button').bind(
				'click',
				function(event) {
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

			instance._tabsContainer.append(panel);
		},

		_setPanelHTML: function(html) {
			var instance = this;

			if (!html) {
				html = '<li class="panel">' +
					'<div class="panel-trigger"><span class="trigger-name"></span></div>' +
					'<div class="chat-panel">' +
						'<div class="panel-window">' +
							'<div class="panel-button minimize"></div>' +
							'<div class="panel-title"></div>' +
							'<div class="panel-content"></div>' +
						'</div>' +
					'</div>' +
				'</li>';
			}

			return html;
		}
	}
);

Liferay.Chat.Conversation = Liferay.Chat.Panel.extend(
	{
		initialize: function(options) {
			var instance = this;

			Liferay.Chat.Panel.prototype.initialize.call(instance, options);

			instance._chatInput = instance._panel.find('.panel-input textarea');
			instance._chatOutput = instance._panel.find('.panel-output');
			instance._statusMessage = instance._panel.find('.panel-profile');

			instance._created = Liferay.Chat.Util.getCurrentTimestamp();
			instance._lastMessageTime = 0;
			instance._unreadMessages = 0;
			instance._originalPageTitle = document.title;

			instance._heightMonitor = jQuery('<pre class="chat-height-monitor" />');
			instance._heightMonitor.appendTo(document.body);

			instance._unreadMessagesContainer = instance._panel.find('.unread');

			if (!instance._unreadMessagesContainer.length) {
				instance._unreadMessagesContainer = jQuery('<div class="unread" />');
				instance._popupTrigger.append(instance._unreadMessagesContainer);
			}

			if (options.statusMessage) {
				instance._statusMessage.text(options.statusMessage);
			}

			instance._chatInput.bind('keyup focus',
				function(event) {
					instance._keystroke(event, this);
				}
			);
		},

		send: function(options) {
			var instance = this;

			Liferay.Chat.Manager.send(options);
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

		setTyping: function(isTyping) {
			var instance = this;

			if (isTyping) {
				instance._panel.addClass('typing');
			}
			else {
				instance._panel.removeClass('typing');
			}
		},

		show: function() {
			var instance = this;

			Liferay.Chat.Panel.prototype.show.call(instance);
			instance.setAsRead();

			var outputEl = instance._chatOutput[0];

			outputEl.scrollTop = outputEl.scrollHeight;
		},

		update: function(entry) {
			var instance = this;

			instance.setTyping(false);

			if (entry.incoming && !entry.cache) {
				if (entry.content.length) {
					if (!instance.get('selected')) {
						var lastRead = instance.get('lastReadTime') || 0;

						if (lastRead < entry.createDate) {
							instance._unreadMessages++;
						}
					}

					instance.setAsUnread();
				}
				else {
					instance.setTyping(true);
				}
			}

			if (entry.content.length) {
				instance._updateMessageWindow(entry);
				instance.setTyping(false);
			}

			if (entry.statusMessage) {
				instance._statusMessage.text(entry.statusMessage);
			}
		},

		updateStatus: function(status) {
			var instance = this;

			instance._statusMessage.text(status);
		},

		_autoSize: function() {
			var instance = this;

			var heightMonitorEl = instance._heightMonitor[0];

			if (!instance._chatInputWidth) {
				instance._chatInputWidth = instance._chatInput.outerWidth();

				instance._heightMonitor.width(instance._chatInputWidth);
			}

			var chatInputEl = instance._chatInput[0];

			var content = Liferay.Util.escapeHTML(chatInputEl.value);
			var textNode = document.createTextNode(content);

			heightMonitorEl.innerHTML = '';

			heightMonitorEl.appendChild(textNode);

			content = heightMonitorEl.innerHTML;

			if (!content.length) {
				content = '&nbsp;&nbsp;';
			}

			if (Liferay.Browser.isIe()) {
				content = content.replace(/\n/g, '<br />');
			}

			heightMonitorEl.innerHTML = content;

			var height = Math.max(heightMonitorEl.offsetHeight, 14);

			height = Math.min(height, 64);
			chatInputEl.style.overflowY = 'auto';
			if (height != instance._lastHeight) {
				instance._lastHeight = height;

				chatInputEl.style.height = height + 'px';
				chatInputEl.style.overflowY = (height == 64) ? 'scroll' : 'hidden';

				chatInputEl.parentNode.style.height = (height + 5) + 'px';
			}
		},

		_keystroke: function(event) {
			var instance = this;

			var chatInputEl = instance._chatInput[0];
			var outputEl = instance._chatOutput[0];
			var userId = instance._panelId;

			var chatInputEl = instance._chatInput[0];
			var content = chatInputEl.value.replace(/\n|\r/gim, '');

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

			if (event.keyCode == 13 && !event.shiftKey && content.length) {
				instance._sendChat(chatInputEl.value);

				chatInputEl.value = '';
			}

			instance._autoSize();
		},

		_sendChat: function(content) {
			var instance = this;

			var createDate = Liferay.Chat.Util.getCurrentTimestamp();
			var userId = instance._panelId;

			var escapedHTML = Liferay.Util.escapeHTML(content);

			instance.send(
				{
					content: content,
					toUserId: userId
				}
			);

			instance._updateMessageWindow(
				{
					content: escapedHTML,
					createDate: createDate
				}
			);
		},

		_setPanelHTML: function() {
			var instance = this;

			var userImagePath = Liferay.Chat.Util.getUserImagePath(instance._panelIcon);

			var html = '<li class="user user_' + instance._panelId + '" panelId="' + instance._panelId + '">' +
							'<div class="panel-trigger">' +
								'<span class="trigger-name"></span>' +
								'<div class="typing-status"></div>' +
							'</div>' +
							'<div class="chat-panel">' +
								'<div class="panel-window">' +
									'<div class="panel-button minimize"></div>' +
									'<div class="panel-button close"></div>' +
									'<img class="panel-icon" src="' + userImagePath + '" />' +
									'<div class="panel-title">' + instance._panelTitle + '</div>' +
									'<div class="panel-profile">...</div>' +
									'<div class="panel-output"></div>' +
									'<div class="panel-input">' +
										'<textarea></textarea>' +
									'</div>' +
								'</div>' +
							'</div>' +
						'</li>';

			return html;
		},

		_updateMessageWindow: function(entry) {
			var instance = this;

			var output = instance._chatOutput;
			var cssClass = 'outgoing';

			var content = entry.content;
			var incoming = entry.incoming;
			var userName = themeDisplay.getUserName();

			if (incoming) {
				cssClass = 'incoming';
				userName = instance._panelTitle;
			}

			content = content.replace(/\n/g, '<br />');

			var message = '<p class="blurb ' + cssClass + '">' +
							'<b class="name">' + userName + '</b>' +
							'<i class="date">' + Liferay.Chat.Util.formatTime(entry.createDate) + '</i>' +
							'<span class="text">' + content + '</span>'
						'</p>';

			output[0].innerHTML += message;

			instance._lastMessageTime = entry.createDate;

			setTimeout(
				function() {
					output[0].scrollTop = output[0].scrollHeight - output[0].clientHeight;
			}, 1);
		}
	}
);

Liferay.Chat.Manager = {
	init: function() {
		var instance = this;

		instance._chatContainer = jQuery('.portlet-chat');
		instance._tabsContainer = instance._chatContainer.find('.chat-tabs');

		instance._activePanelId = jQuery('#activePanelId').val() || '';
		instance._portletId = jQuery('#chatPortletId').val();

		instance._closedChats = {};

		instance._created = Liferay.Chat.Util.getCurrentTimestamp();

		instance._myStatus = instance._chatContainer.find('.status-message');

		instance._sound = new SWFObject('/chat-portlet/alert.swf', 'alertsound', '0', '0', '8');
		instance._soundContainer = instance._chatContainer.find('.chat-sound');

		Liferay.Poller.addListener(instance._portletId, instance._onPollerUpdate, instance);

		instance._createBuddyListPanel();
		instance._createSettingsPanel();
	},

	close: function(panelName) {
		var instance = this;

		if ((panelName != 'buddylist') && (panelName != 'settings')) {
			var panel = instance._panels[panelName];

			if (panel) {
				panel.close();
			}
		}
	},

	getContainer: function() {
		var instance = this;

		return instance._tabsContainer;
	},

	send: function(options, id) {
		var instance = this;

		Liferay.Poller.submitRequest(instance._portletId, options, id);
	},

	show: function(panelName) {
		var instance = this;

		var panel = instance._panels[panelName];

		if (panel) {
			panel.show();
		}
	},

	toggle: function(panelName) {
		var instance = this;

		var panel = instance._panels[panelName];

		if (panel) {
			panel.toggle();
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

		panel.bind('close', instance._onPanelClose, instance);
		panel.bind('hide', instance._onPanelHide, instance);
		panel.bind('show', instance._onPanelShow, instance);
	},

	_createBuddyListPanel: function() {
		var instance = this;

		var buddylist = new Liferay.Chat.Panel(
			{
				fromMarkup: '.chat-tabs > .buddy-list',
				panelId: 'buddylist'
			}
		);

		instance._addPanel('buddylist', buddylist);

		instance._onlineBuddies = buddylist.getPanel().find('.online-users');

		if (instance._onlineBuddies.length) {
			var buddies = jQuery('li', instance._onlineBuddies[0]);

			buddies.livequery(
				'click',
				function(event) {
					instance._createChatFromUser(this);
				}
			);
		}
	},

	_createChatFromUser: function(user) {
		var instance = this;

		var buddy;
		var buddies = instance._buddies;
		var userId = user;

		if (user.tagName || user.jquery) {
			userId = jQuery(user).attr('userId');
		}

		if (!isNaN(Number(userId))) {
			buddy = buddies[userId];

			if (buddy) {
				var chat = instance._chatSessions[userId];

				if (!chat) {
					chat = instance._createChatSession(buddy);
				}

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
				panelTitle: options.fullName,
				panelIcon: options.portraitId,
				statusMessage: options.statusMessage
			}
		);

		instance._addChat(userId, chat);
		instance._addPanel(userId, chat);

		if (instance._entryCache && instance._entryCache[userId]) {
			var entryCache = instance._entryCache[userId];

			for (var i  in entryCache) {
				var entry = entryCache[i];

				chat.update(
					{
						cache: true,
						content: entry.content,
						createDate: entry.createDate,
						incoming: (entry.fromUserId == userId)
					}
				);
			}
		}

		if (options.open) {
			chat.show();
		}

		return chat;
	},

	_createSettingsPanel: function() {
		var instance = this;

		var settings = new Liferay.Chat.Panel(
			{
				fromMarkup: '.chat-tabs > .chat-settings',
				panelId: 'settings'
			}
		);

		instance._addPanel('settings', settings);

		var settingsPanel = settings.getPanel();
		var saveSettings = settingsPanel.find('#saveSettings');

		instance._statusMessageObj = settingsPanel.find('#statusMessage');
		instance._onlineObj = settingsPanel.find('#onlineStatus');
		instance._playSoundObj = settingsPanel.find('#playSound');

		instance._statusMessage = instance._statusMessageObj.val() || '';
		instance._online = instance._onlineObj.is(':checked') ? 1 : 0;
		instance._playSound = instance._playSoundObj.is(':checked') ? 1 : 0;

		saveSettings.click(
			function(event) {
				instance._updateSettings();
			}
		);
	},

	_getSettings: function() {
		var instance = this;

		return {
			activePanelId: instance._activePanelId,
			online: instance._online,
			statusMessage: instance._statusMessage,
			playSound: instance._playSound
		};
	},

	_loadCache: function(entries) {
		var instance = this;

		if (!instance._entryCache) {
			instance._entryCache = {};
		}

		if (!instance._entryIds) {
			instance._entryIds = [0];
		}

		var entryCache = instance._entryCache;
		var entryIds = instance._entryIds;

		var currentUserId = themeDisplay.getUserId();

		var entriesLength = entries.length;

		for (var i = 0; i < entriesLength; i++) {
			var entry = entries[i];

			var userId = entry.toUserId;

			if (userId == currentUserId) {
				userId = entry.fromUserId;
			}

			if (!entryCache[userId]) {
				entryCache[userId] = {};
			}

			var userEntryCache = entryCache[userId];

			if (entry.content != '') {
				userEntryCache[entry.entryId] = entry;
				entryIds.push(entry.entryId);
			}
		}
	},

	_isMessageNew: function(entry, userId) {
		var instance = this;

		var createDate = entry.createDate;
		var initDate = instance._created;
		var closedDate = instance._closedChats[userId] || 0;

		return (createDate > initDate && createDate > closedDate);
	},

	_onPanelClose: function(event, panel) {
		var instance = this;

		var userId = panel._panelId;

		delete instance._panels[userId];

		if (panel instanceof Liferay.Chat.Conversation) {
			delete instance._chatSessions[userId];
		}

		instance._closedChats[userId] = Liferay.Chat.Util.getCurrentTimestamp();

		instance._activePanelId = '';
		instance._saveSettings();
	},

	_onPanelHide: function(event, panel) {
		var instance = this;

		instance._activePanelId = '';
		instance._saveSettings();
	},

	_onPanelShow: function(event, panel) {
		var instance = this;

		for (var i in instance._panels) {
			if (instance._panels[i] != panel) {
				instance._panels[i].hide();
			}
		}

		if (panel instanceof Liferay.Chat.Conversation) {
			panel._chatInput.trigger('focus');
		}

		instance._activePanelId = panel._panelId;
		instance._saveSettings();
	},

	_onPollerUpdate: function(response, chunkId) {
		var instance = this;

		instance._updateBuddies(response.buddies);

		if (instance._cacheLoaded) {
			instance._updateConversations(response.entries);
		}

		if (response.initialRequest) {
			instance._loadCache(response.entries);

			if (instance._activePanelId.length) {
				var activePanelId = parseInt(instance._activePanelId, 10);

				if (!isNaN(activePanelId)) {
					instance._createChatFromUser(instance._activePanelId);
				}
			}

			instance._cacheLoaded = true;
		}
	},

	_saveSettings: function() {
		var instance = this;

		Liferay.Poller.submitRequest(instance._portletId, instance._getSettings());
	},

	_updateBuddies: function(buddies) {
		var instance = this;

		var buddyList = buddies || [];
		var numBuddies = buddyList.length;

		var currentBuddies = instance._buddies;
		var currentChats = instance._chatSessions;

		instance._onlineBuddiesCount = numBuddies;

		var buffer = [];

		for (var i = 0; i < numBuddies; i++) {
			var buddy = buddyList[i];
			var currentBuddy = currentBuddies[buddy.userId];
			var currentChat = currentChats[buddy.userId];

			buddy.isTyping = false;

			if (currentChat && (!currentBuddy || currentBuddy.statusMessage != buddy.statusMessage)) {
				currentChat.updateStatus(buddy.statusMessage);
			}

			currentBuddies[buddy.userId] = buddy;

			var userImagePath = Liferay.Chat.Util.getUserImagePath(buddy.portraitId);

			buffer.push(
				'<li class="active" userId="' + buddy.userId + '">' +
					'<img src="' + userImagePath + '" />' +
					'<div class="name">' + buddy.fullName + '</div>' +
				'</li>');
		}

		instance._onlineBuddies.html(buffer.join(''));

		instance._updateBuddyList();
	},

	_updateBuddyList: function(buddy) {
		var instance = this;

		var buddyList = instance._panels['buddylist'];

		var title = buddyList.getTitle();

		title = title.replace(/\(\d+\)/, '(' + instance._onlineBuddiesCount + ')');

		buddyList.setTitle(title);
	},

	_updateConversations: function(entries) {
		var instance = this;

		var entriesLength = entries.length;

		var currentUserId = themeDisplay.getUserId();

		if (!entriesLength) {
			var chatSessions = instance._chatSessions;

			for(var i in chatSessions) {
				chatSessions[i].setTyping(false);
			}
		}

		var entryCache = instance._entryCache;
		var entryIds = instance._entryIds.join('|');

		for (var i = 0; i < entriesLength; i++) {
			var entry = entries[i];

			var entryProcessed = (entryIds.indexOf('|' + entry.entryId) > -1);

			if (!entryProcessed) {
				var userId = entry.toUserId;
				var incoming = false;

				if (entry.fromUserId != currentUserId) {
					userId = entry.fromUserId;
					incoming = true;
				}

				var buddy = instance._buddies[userId];

				if (buddy && incoming) {
					var chat = instance._chatSessions[userId];

					if (!chat && entry.content && instance._isMessageNew(entry, userId)) {
						chat = instance._createChatSession(
							{
								portraitId: buddy.portraitId,
								userId: buddy.userId,
								fullName: buddy.fullName,
								statusMessage: buddy.statusMessage
							}
						);
					}

					if (chat) {
						chat.update(
							{
								incoming: incoming,
								content: entry.content,
								createDate: entry.createDate,
								entryId: entry.entryId,
								statusMessage: buddy.statusMessage
							}
						);
					}
				}
			}
		}

		instance._loadCache(entries);
	},

	_updateSettings: function() {
		var instance = this;

		var settings = instance._panels.settings;
		var settingsPanel = settings.getPanel();

		instance._statusMessage = instance._statusMessageObj.val();
		instance._online = instance._onlineObj.is(':checked') ? 1 : 0;
		instance._playSound = instance._playSoundObj.is(':checked') ? 1 : 0;

		instance._activePanelId = '';

		settings.hide();
		instance._saveSettings();

		settingsPanel.addClass('saved');

		if (instance._statusMessage) {
			instance._myStatus.html('You are <strong>' + Liferay.Util.escapeHTML(instance._statusMessage) + '</strong>');
		}

		setTimeout(
			function() {
				settingsPanel.removeClass('saved');
			}, 1500);
	},

	_buddies: {},
	_chatSessions: {},
	_entries: [],
	_panels: {},
	_settings: {}
};

Liferay.Chat.FixBadBrowsers = function() {
	var chatBar = jQuery('.chat-bar');
	var position = chatBar.css('position');

	if (position == 'absolute') {
		var win = jQuery(window);
		var body = jQuery(document.body);

		var chatBarHeight = chatBar.outerHeight();
		var chatBarWidth = chatBar.outerWidth();
		var frame = Liferay.Util.viewport.frame();
		var maxPageScroll = (Liferay.Util.viewport.page().y - frame.y) + chatBarHeight;

		var zIndex = chatBar.css('zIndex');

		var iframe = jQuery('<iframe class="lfr-shim" frameborder="0" src="javascript: ;" />');

		iframe.width(chatBarWidth);
		iframe.height(chatBarHeight);
		iframe.css('zIndex', zIndex);

		chatBar.css('zIndex', zIndex + 100);

		win.bind(
			'scroll resize',
			function(event) {
				if (event.type == 'resize') {
					frame = Liferay.Util.viewport.frame();
				}

				var scrollTop = win.scrollTop();

				if ((event.type == 'resize') ||
					((event.type == 'scroll') && (scrollTop < maxPageScroll))) {

					chatBar.css('top', (scrollTop + frame.y) - chatBarHeight);
					iframe.css('top', ((scrollTop + frame.y) - chatBarHeight));
				}
			}
		);

		body.append(iframe);
	}
};

jQuery.fn.extend(
	{
		disableSelection: function() {
			return this.attr('unselectable', 'on').css('MozUserSelect', 'none').bind('selectstart.ui', function() {
					return false;
				}
			);
		},

		enableSelection: function() {
			return this.attr('unselectable', 'off').css('MozUserSelect', '').unbind('selectstart.ui');
		}
	}
);

jQuery(
	function() {
		Liferay.Chat.Manager.init();
		Liferay.Chat.FixBadBrowsers();
	}
);