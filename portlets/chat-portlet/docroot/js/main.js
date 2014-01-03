AUI().use(
	'anim-color',
	'anim-easing',
	'aui-base',
	'aui-live-search-deprecated',
	'liferay-poller',
	'stylesheet',
	'swfobject',
	function(A) {
		var Lang = A.Lang;
		var Notification = A.config.win.Notification;

		var now = Lang.now;

		var DOC = A.config.doc;

		var NOTIFICATIONS_PERMISSION_DEFAULT = 'default';

		var NOTIFICATIONS_PERMISSION_GRANTED = 'granted';

		var NOTIFICATIONS_LIST = [];

		var STR_NEW_MESSAGE = Liferay.Language.get('new-message-from-x');

		Liferay.namespace('Chat');

		A.one(DOC.documentElement).toggleClass('desktop-notifications', !!Notification);

		Liferay.Chat.Util = {
			getDefaultColor: function() {
				var instance = this;

				var defaultColor = instance._defaultColor;

				if (!defaultColor) {
					var bgColorNode = A.one('#chatBar .panel-trigger');

					if (bgColorNode) {
						defaultColor = bgColorNode.getStyle('backgroundColor');

						while (defaultColor.toLowerCase() == 'transparent') {
							defaultColor = bgColorNode.getStyle('backgroundColor');

							bgColorNode = bgColorNode.ancestor();
						}
					}

					instance._defaultColor = defaultColor;
				}

				return defaultColor;
			},

			getWaitingColor: function() {
				var instance = this;

				var waitingColor = instance._waitingColor;

				if (!waitingColor) {
					var waitingColorNode = A.Node.create('<span class="hide message-waiting" />').appendTo(DOC.body);

					waitingColor = waitingColorNode.getStyle('backgroundColor');

					waitingColorNode.remove();

					instance._waitingColor = waitingColor;
				}

				return waitingColor;
			},

			formatTime: function(time) {
				var instance = this;

				time = instance._convertToClientTimestamp(time);

				time = new Date(time);

				var meridian = 'am';
				var hour = time.getHours();
				var minute = time.getMinutes();

				if (hour >= 12) {
					meridian = 'pm';
				}

				if (hour > 12) {
					hour -= 12;
				}

				if (hour === 0) {
					hour += 12;
				}

				if (minute < 10) {
					minute = '0' + minute;
				}

				return hour + ':' + minute + ' ' + meridian;
			},

			getCurrentTimestamp: function() {
				var instance = this;

				return now() - instance._getOffset();
			},

			getUserImagePath: function(userId) {
				var instance = this;

				return themeDisplay.getPathImage() + '/user_portrait?img_id=' + userId;
			},

			_convertToClientTimestamp: function(time) {
				var instance = this;

				time = Number(time);

				time += instance._getOffset();

				var currentSystemTime = now();

				if (time > currentSystemTime) {
					time = currentSystemTime;
				}

				return time;
			},

			_getOffset: function() {
				var instance = this;

				var offset = instance._offset;

				if (Lang.isUndefined(offset)) {
					var currentChatServerTime = A.one('#currentChatServerTime').val() || now();

					offset = now() - currentChatServerTime;

					instance._offset = offset;
				}

				return offset;
			},

			TIMESTAMP_24: (24 * 60 * 60 * 1000)
		};

		var Panel = function(options) {
			var instance = this;

			if (!options.container) {
				instance._tabsContainer = Liferay.Chat.Manager.getContainer();
			}
			else {
				instance._tabsContainer = A.one(options.container);
			}

			instance._chatProperties = {};
			instance._eventsSuspended = false;

			instance._panelId = options.panelId;
			instance._panelTitle = options.panelTitle;
			instance._panelIcon = options.panelIcon;

			var panelHTML = instance._setPanelHTML(options.panelHTML);

			instance.set('panelHTML', panelHTML);

			instance._createPanel(options.fromMarkup);

			if (options.panelTitle) {
				instance.setTitle(options.panelTitle);
			}

			instance._popupTrigger.unselectable();
		};

		Panel.prototype = {
			close: function() {
				var instance = this;

				instance._panel.remove();

				instance.fire('close');
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

				instance.fire('hide');
			},

			resumeEvents: function() {
				var instance = this;

				instance._eventsSuspended = false;
			},

			setTitle: function(value) {
				var instance = this;

				instance._popupTrigger.one('.trigger-name').text(value);
				instance._popupTitle.text(value);
			},

			show: function() {
				var instance = this;

				instance.set('selected', true);
				instance._panel.addClass('selected');

				instance.fire('show');
			},

			suspendEvents: function() {
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

			_createPanel: function(fromMarkup) {
				var instance = this;

				var panel;

				if (fromMarkup) {
					panel = A.one(fromMarkup);
				}
				else {
					panel = A.Node.create(instance.get('panelHTML'));
				}

				instance._popup = panel.one('.chat-panel');
				instance._popupTitle = panel.one('.panel-title');
				instance._textBox = panel.one('textarea');
				instance._popupTrigger = panel.one('.panel-trigger');

				instance._popupTrigger.on('click', instance.toggle, instance);

				panel.all('.panel-button').on(
					'click',
					function(event) {
						var target = event.currentTarget;

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
								'<div class="search-buddies"><input class="search-buddies" type="text" /></div>' +
								'<div class="panel-content"></div>' +
							'</div>' +
						'</div>' +
					'</li>';
				}

				return html;
			}
		};

		A.augment(Panel, A.Attribute);

		var Conversation = function(options) {
			var instance = this;

			Conversation.superclass.constructor.call(instance, options);

			instance._chatInput = instance._panel.one('.panel-input textarea');
			instance._chatOutput = instance._panel.one('.panel-output');
			instance._statusMessage = instance._panel.one('.panel-profile');

			instance._lastMessageTime = 0;
			instance._lastTypedTime = 0;
			instance._typingDelay = 5000;
			instance._unreadMessages = 0;
			instance._originalPageTitle = DOC.title;

			instance._stopTypingTask = A.debounce(instance.setTyping, instance._typingDelay, instance, false);

			instance._heightMonitor = A.Node.create('<pre class="chat-height-monitor" />');
			instance._heightMonitor.appendTo(DOC.body);

			instance._unreadMessagesContainer = instance._panel.one('.unread');

			if (!instance._unreadMessagesContainer) {
				instance._unreadMessagesContainer = A.Node.create('<div class="hide unread" />');
				instance._popupTrigger.append(instance._unreadMessagesContainer);
			}

			if (options.statusMessage) {
				instance._statusMessage.text(options.statusMessage);
			}

			instance._chatInput.on('keyup', instance._keystroke, instance);
			instance._chatInput.on('focus', instance._keystroke, instance);
		};

		A.extend(
			Conversation,
			Panel,
			{
				send: function(options) {
					var instance = this;

					Liferay.Chat.Manager.send(options);
				},

				setAsRead: function() {
					var instance = this;

					instance.setWaiting(false);

					instance._unreadMessagesContainer.hide();

					instance._unreadMessages = 0;

					instance.set('lastReadTime', Liferay.Chat.Util.getCurrentTimestamp());
					DOC.title = instance._originalPageTitle;
				},

				setAsUnread: function() {
					var instance = this;

					if (!instance.get('selected')) {
						if (instance._unreadMessages > 1) {
							instance._unreadMessagesContainer.text(instance._unreadMessages);
							instance._unreadMessagesContainer.show();
						}
						else {
							Liferay.Chat.Manager.triggerSound();
							instance.setWaiting(true);
							instance._unreadMessagesContainer.hide();
						}

						DOC.title = instance._originalPageTitle + ' - Unread messages (' + instance._unreadMessages + ')';
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

				setWaiting: function(isWaiting) {
					var instance = this;

					var panel = instance._panel;
					var waitingAnim = instance._waitingAnim;

					if (isWaiting) {
						if (!waitingAnim) {
							var ChatUtil = Liferay.Chat.Util;

							waitingAnim = new A.Anim(
								{
									direction: 'alternate',
									duration: 0.65,
									easing: 'easeBoth',
									from: {
										backgroundColor: ChatUtil.getDefaultColor()
									},
									iterations: 'infinite',
									node: panel,
									to: {
										backgroundColor: ChatUtil.getWaitingColor()
									}
								}
							);

							instance._waitingAnim = waitingAnim;
						}

						waitingAnim.run();
					}
					else {
						if (waitingAnim) {
							waitingAnim.stop();
						}

						panel.setStyle('backgroundColor', '');
					}
				},

				show: function() {
					var instance = this;

					Liferay.Chat.Panel.prototype.show.call(instance);
					instance.setAsRead();

					var outputEl = instance._chatOutput.getDOM();

					outputEl.scrollTop = outputEl.scrollHeight;
				},

				update: function(entry) {
					var instance = this;

					if (entry.incoming && !entry.cache) {
						if (entry.content.length) {
							if (!instance.get('selected')) {
								var lastRead = instance.get('lastReadTime') || 0;

								if (lastRead < entry.createDate) {
									instance._unreadMessages++;
								}

								Liferay.Chat.Manager.notify(
									Liferay.Chat.Util.getUserImagePath(instance._panelIcon),
									Lang.sub(STR_NEW_MESSAGE, [instance._panelTitle]),
									entry.content.replace(/\n/g, ' ')
								);
							}

							instance.setAsUnread();
						}
						else {
							instance.setTyping(true);

							instance._stopTypingTask();
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

					var heightMonitorEl = instance._heightMonitor.getDOM();

					if (!instance._chatInputWidth) {
						instance._chatInputWidth = instance._chatInput.get('offsetWidth');

						instance._heightMonitor.setStyle('width', instance._chatInputWidth);
					}

					var chatInputEl = instance._chatInput.getDOM();

					var content = Liferay.Util.escapeHTML(chatInputEl.value);
					var textNode = DOC.createTextNode(content);

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

					var chatInput = instance._chatInput;
					var userId = instance._panelId;

					var chatInputEl = chatInput.getDOM();
					var content = chatInputEl.value.replace(/\n|\r/gim, '');

					if (event.type == 'keyup') {
						if (instance.get('typedTo') == userId) {
							var currentTime = Liferay.Chat.Util.getCurrentTimestamp();

							if (currentTime - instance._lastTypedTime > instance._typingDelay) {
								instance.send(
									{
										toUserId: userId,
										content: ''
									}
								);

								instance._lastTypedTime = currentTime;
							}
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
											'<img alt="" class="panel-icon" src="' + userImagePath + '" />' +
											'<div class="panel-title">' + Liferay.Util.escapeHTML(instance._panelTitle) + '</div>' +
											'<div class="panel-profile">...</div>' +
											'<div class="panel-output"></div>' +
											'<div class="panel-input">' +
												'<textarea class="message-input"></textarea>' +
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
									'<b class="name">' + Liferay.Util.escapeHTML(userName) + '</b>' +
									'<i class="date">' + Liferay.Chat.Util.formatTime(entry.createDate) + '</i>' +
									'<span class="text">' + content + '</span>' +
								'</p>';

					var outputEl = output.getDOM();

					outputEl.innerHTML += message;

					instance._lastMessageTime = entry.createDate;

					setTimeout(
						function() {
							outputEl.scrollTop = outputEl.scrollHeight - outputEl.clientHeight;
						},
						1
					);
				}
			}
		);

		Liferay.Chat.Manager = {
			init: function() {
				var instance = this;

				instance._initialRequest = true;
				instance._notificationTimeout = 8000;

				instance._chatContainer = A.one('#chatBar');
				instance._portletId = A.one('#chatPortletId').val();

				instance._myStatus = instance._chatContainer.one('.status-message');
				instance._soundContainer = instance._chatContainer.one('.chat-sound');
				instance._tabsContainer = instance._chatContainer.one('.chat-tabs');

				instance._sendTask = A.debounce(instance.send, 100, instance);

				instance._sound = new SWFObject('/chat-portlet/alert.swf', 'alertsound', '0', '0', '8');

				instance._updatePresenceTask = A.debounce(instance._updatePresence, 30000, instance);

				instance._updatePresenceTask.delay(0);

				instance._notifyPermission = instance._getNotifyPermission();

				Liferay.Poller.addListener(instance._portletId, instance._onPollerUpdate, instance);

				Liferay.bind(
					'sessionExpired',
					function(event) {
						Liferay.Poller.removeListener(instance._portletId);

						instance._chatContainer.hide();
					}
				);

				instance._initializeActivePanels();

				instance._createBuddyListPanel();
				instance._createSettingsPanel();
			},

			getContainer: function() {
				var instance = this;

				return instance._tabsContainer;
			},

			notify: function(iconUrl, title, body) {
				var instance = this;

				if (instance._notifyPermission === NOTIFICATIONS_PERMISSION_GRANTED) {
					var notification = new Notification(
						title,
						{
							icon: iconUrl,
							body: body
						}
					);

					if (!NOTIFICATIONS_LIST.length) {
						instance._notificationHandle = A.getWin().on(
							'beforeunload',
							function(event) {
								A.Array.invoke(NOTIFICATIONS_LIST, 'close');

								NOTIFICATIONS_LIST.length = 0;

								instance._notificationHandle.detach();

								instance._notificationHandle = null;
							}
						);
					}

					NOTIFICATIONS_LIST.push(notification);

					setTimeout(
						function() {
							notification.close();

							NOTIFICATIONS_LIST.shift();

							if (!NOTIFICATIONS_LIST.length && instance._notificationHandle) {
								instance._notificationHandle.detach();

								instance._notificationHandle = null;
							}
						},
						instance._notificationTimeout
					);
				}
			},

			registerBuddyService: function(options) {
				var instance = this;

				var name = options.name;
				var fn = options.fn;
				var icon = options.icon;

				instance._buddyServices[name] = fn;

				var styleSheet = instance._styleSheet;

				if (!styleSheet) {
					styleSheet = new A.StyleSheet();

					instance._styleSheet = styleSheet;
				}

				styleSheet.set(
					'.chat-bar .buddy-services .' + name,
					{
						backgroundImage: 'url("' + icon + '")'
					}
				);
			},

			send: function(options, id) {
				var instance = this;

				if (!options.updatePresence) {
					instance._updatePresenceTask.cancel();
				}

				Liferay.Poller.submitRequest(instance._portletId, options, id);

				instance._updatePresenceTask();
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
					instance._sound.write(instance._soundContainer.getDOM());
				}
			},

			_addChat: function(chatName, chat) {
				var instance = this;

				instance._chatSessions[chatName] = chat;
			},

			_addPanel: function(panelName, panel) {
				var instance = this;

				instance._panels[panelName] = panel;

				panel.on('close', instance._onPanelClose, instance);
				panel.on('hide', instance._onPanelHide, instance);
				panel.on('show', instance._onPanelShow, instance);
			},

			_createBuddyListPanel: function() {
				var instance = this;

				var buddyListPanel = new Liferay.Chat.Panel(
					{
						fromMarkup: '.chat-tabs > .buddy-list',
						panelId: 'buddylist'
					}
				);

				instance._addPanel('buddylist', buddyListPanel);

				var buddyListNode = buddyListPanel.getPanel();

				var buddyList = buddyListNode.one('.online-users');

				var searchBuddiesField = buddyListNode.one('.search-buddies');

				var liveSearch = new A.LiveSearch(
					{
						input: searchBuddiesField,
						nodes: '#chatBar .buddy-list .online-users li',
						data: function(node) {
							return node.one('.name').text();
						}
					}
				);

				searchBuddiesField.on('focus', liveSearch.refreshIndex, liveSearch);

				buddyListPanel.on(
					'show',
					function(event) {
						if (searchBuddiesField.val()) {
							searchBuddiesField.selectText();
						}
					}
				);

				if (buddyList) {
					buddyList.delegate(
						'click',
						function(event) {
							var target = event.currentTarget;

							if (target.ancestor('.buddy-services')) {
								event.stopPropagation();

								var serviceName = target.getAttribute('class');

								instance._buddyServices[serviceName](target.ancestor('li.user'));
							}
							else {
								instance._createChatFromUser(target);
							}
						},
						'li, .buddy-services div'
					);
				}

				instance._searchBuddiesField = searchBuddiesField;
				instance._liveSearch = liveSearch;

				instance._onlineBuddies = buddyList;
			},

			_createChatFromUser: function(user) {
				var instance = this;

				var buddy;
				var buddies = instance._buddies;
				var userId = user;

				user = A.one(user);

				if (user) {
					userId = user.getAttribute('userId');
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

					var entries = entryCache.entries;

					for (var i in entries) {
						var entry = entries[i];

						var incomingEntry = (entry.fromUserId == userId);

						chat.update(
							{
								cache: entry.flag,
								content: entry.content,
								createDate: entry.createDate,
								incoming: incomingEntry
							}
						);

						entry.flag = 1;
					}
				}

				if (options.open) {
					chat.show();
				}

				return chat;
			},

			_createPanelsForNewMessages: function() {
				var instance = this;

				var entryCache = instance._entryCache;

				for (var userId in entryCache) {
					var chat = instance._chatSessions[userId];

					var userEntryCache = entryCache[userId];

					if (!chat && userEntryCache.newMessages) {
						var buddy = instance._buddies[userId];

						if (buddy) {
							instance._createChatSession(
								{
									fullName: buddy.fullName,
									portraitId: buddy.portraitId,
									statusMessage: buddy.statusMessage,
									userId: userId
								}
							);
						}
					}
				}
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
				var saveSettings = settingsPanel.one('#saveSettings');

				instance._statusMessageObj = settingsPanel.one('#statusMessage');
				instance._onlineObj = settingsPanel.one('#onlineStatus');
				instance._playSoundObj = settingsPanel.one('#playSound');
				instance._showNotificationsObj = settingsPanel.one('#showNotifications');

				instance._statusMessage = instance._statusMessageObj.val() || '';
				instance._online = instance._onlineObj.get('checked') ? 1 : 0;
				instance._playSound = instance._playSoundObj.get('checked') ? 1 : 0;

				if (Notification) {
					var showNotificationsObj = instance._showNotificationsObj;

					var notifyPermission = instance._notifyPermission;

					var attrs = {
						checked: (notifyPermission === NOTIFICATIONS_PERMISSION_GRANTED)
					};

					if (notifyPermission === NOTIFICATIONS_PERMISSION_DEFAULT) {
						attrs.disabled = false;
					}

					showNotificationsObj.attr(attrs);
				}

				saveSettings.on('click', instance._updateSettings, instance);
			},

			_getNotifyPermission: function() {
				var notifyPermission;

				if (Notification) {
					if (Notification.permissionLevel) {
						notifyPermission = Notification.permissionLevel();
					}
					else if (Notification.permission) {
						notifyPermission = Notification.permission;
					}
					else if (A.config.win.webkitNotifications) {
						var webkitNotifyPermission = A.config.win.webkitNotifications.checkPermission();

						if (webkitNotifyPermission === 0) {
							notifyPermission = NOTIFICATIONS_PERMISSION_GRANTED;
						}
						else if (webkitNotifyPermission === 1) {
							notifyPermission = NOTIFICATIONS_PERMISSION_DEFAULT;
						}
					}
				}

				return notifyPermission;
			},

			_getSettings: function() {
				var instance = this;

				var activePanelIds = {
					open: instance._openPanelId,
					minimized: A.Object.keys(instance._minimizedPanelIds)
				};

				return {
					activePanelIds: A.JSON.stringify(activePanelIds),
					online: instance._online,
					statusMessage: instance._statusMessage,
					playSound: instance._playSound
				};
			},

			_initializeActivePanels: function() {
				var instance = this;

				var activePanelIds = A.one('#activePanelIds').val() || '';

				try {
					activePanelIds = A.JSON.parse(activePanelIds);
				}
				catch (e) {
					activePanelIds = {
						open: null,
						minimized: []
					};
				}

				instance._activePanelIds = activePanelIds;

				instance._openPanelId = activePanelIds.open || '';
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
				var entryIds = instance._entryIds.join('|');

				var currentUserId = themeDisplay.getUserId();

				var entriesLength = entries.length;

				for (var i = 0; i < entriesLength; i++) {
					var entry = entries[i];

					var incoming = false;
					var userId = entry.toUserId;

					if (userId == currentUserId) {
						incoming = true;
						userId = entry.fromUserId;
					}

					if (!entryCache[userId]) {
						entryCache[userId] = {
							entries: {},
							newMessages: false
						};
					}

					var userEntryCache = entryCache[userId];

					var entryId = entry.entryId;

					var entryProcessed = (entryIds.indexOf('|' + entryId) > -1);

					if (!entryProcessed) {
						userEntryCache.entries[entryId] = entry;

						instance._entryIds.push(entryId);

						if (!userEntryCache.newMessages && !entry.flag && incoming) {
							userEntryCache.newMessages = true;
						}
					}
				}
			},

			_onPanelClose: function(event) {
				var instance = this;

				var panel = event.target;

				var userId = panel._panelId;

				delete instance._panels[userId];

				if (panel instanceof Liferay.Chat.Conversation) {
					delete instance._chatSessions[userId];
				}

				instance._openPanelId = '';
				instance._saveSettings();
			},

			_onPanelHide: function(event) {
				var instance = this;

				var userId = event.target._panelId;

				if (Lang.toInt(userId)) {
					Liferay.Chat.Manager._minimizedPanelIds[userId] = true;
				}

				instance._openPanelId = '';
				instance._saveSettings();
			},

			_onPanelShow: function(event) {
				var instance = this;

				var panel = event.target;

				for (var i in instance._panels) {
					if (instance._panels[i] != panel) {
						instance._panels[i].hide();
					}
				}

				if (panel instanceof Liferay.Chat.Conversation) {
					panel._chatInput.focus();
				}

				var userId = panel._panelId;

				delete Liferay.Chat.Manager._minimizedPanelIds[userId];

				instance._openPanelId = panel._panelId;
				instance._saveSettings();
			},

			_onPollerUpdate: function(response, chunkId) {
				var instance = this;

				instance._updateBuddies(response.buddies);

				var entries = response.entries;

				if (instance._initialRequest) {
					instance._loadCache(entries);

					if (instance._openPanelId.length) {
						var openPanelId = parseInt(instance._openPanelId, 10);

						if (!isNaN(openPanelId)) {
							instance._createChatFromUser(instance._openPanelId);
						}
					}

					instance._restoreMinimizedPanels();

					instance._createPanelsForNewMessages();

					instance._chatContainer.one('.chat-tabs > .buddy-list').removeClass('loading');

					instance._initialRequest = false;
				}
				else {
					instance._updateConversations(entries);
				}
			},

			_restoreMinimizedPanels: function() {
				var instance = this;

				var buddies = instance._buddies;

				var minimized = instance._activePanelIds.minimized;

				var minimizedPanelIds = instance._minimizedPanelIds;

				A.Array.each(
					minimized,
					function(item, index, collection) {
						minimizedPanelIds[item] = true;

						var buddy = buddies[item];

						if (buddy) {
							instance._createChatSession(
								{
									fullName: buddy.fullName,
									open: false,
									portraitId: buddy.portraitId,
									statusMessage: buddy.statusMessage,
									userId: item
								}
							);
						}
					}
				);
			},

			_saveSettings: function() {
				var instance = this;

				instance._sendTask(instance._getSettings());
			},

			_updateBuddies: function(buddies) {
				var instance = this;

				var searchBuddiesField = instance._searchBuddiesField;
				var search = searchBuddiesField.val().toLowerCase();

				var buddyList = buddies || [];
				var numBuddies = buddyList.length;

				var currentBuddies = instance._buddies;
				var currentChats = instance._chatSessions;

				instance._onlineBuddiesCount = numBuddies;

				var buffer = [''];

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
						'<li class="user active" userId="' + buddy.userId + '">' +
							'<img alt="" src="' + userImagePath + '" />' +
							'<div class="name">' + Liferay.Util.escapeHTML(buddy.fullName) + '</div>' +
							'<div class="buddy-services">');

					var serviceNames = instance._buddyServices;

					for (var serviceName in serviceNames) {
						buffer.push('<div class="' + serviceName + '"></div>');
					}

					buffer.push(
							'</div>' +
						'</li>');
				}

				instance._onlineBuddies.html(buffer.join(''));

				if (
					searchBuddiesField.test(':visible') &&
					(search.length > 2 || searchBuddiesField.compareTo(DOC.activeElement))) {

					instance._liveSearch.refreshIndex();
					instance._liveSearch.fire('search', {liveSearch: {}});
				}

				instance._updateBuddyList();

				instance.fire('updateBuddies', buddies);
			},

			_updateBuddyList: function(buddy) {
				var instance = this;

				var buddyList = instance._panels.buddylist;

				var title = buddyList.getTitle();

				title = title.replace(/\(\d+\)/, '(' + instance._onlineBuddiesCount + ')');

				buddyList.setTitle(title);
			},

			_updateConversations: function(entries) {
				var instance = this;

				var entriesLength = entries.length;

				var currentUserId = themeDisplay.getUserId();

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

							if (!chat && entry.content) {
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

							entry.flag = 1;
						}
					}
				}

				instance._loadCache(entries);
			},

			_updatePresence: function() {
				var instance = this;

				instance.send(
					{
						updatePresence: true
					}
				);
			},

			_updateSettings: function() {
				var instance = this;

				var settings = instance._panels.settings;
				var settingsPanel = settings.getPanel();

				instance._statusMessage = instance._statusMessageObj.val();
				instance._online = instance._onlineObj.get('checked') ? 1 : 0;
				instance._playSound = instance._playSoundObj.get('checked') ? 1 : 0;

				var showNotificationsObj = instance._showNotificationsObj;

				if (showNotificationsObj.attr('checked') && (instance._notifyPermission === NOTIFICATIONS_PERMISSION_DEFAULT)) {
					var notification = A.config.win.webkitNotifications || Notification;

					notification.requestPermission(
						function(notifyPermission) {
							var allowed = (notifyPermission == NOTIFICATIONS_PERMISSION_GRANTED);

							showNotificationsObj.attr(
								{
									checked: allowed,
									disabled: allowed
								}
							);

							instance._notifyPermission = notifyPermission;
						}
					);
				}

				instance._openPanelId = '';

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
			_buddyServices: {},
			_chatSessions: {},
			_entries: [],
			_panels: {},
			_minimizedPanelIds: {},
			_settings: {},
			_styleSheet: null
		};

		A.augment(Liferay.Chat.Manager, A.Attribute, true);

		Liferay.Chat.Panel = Panel;
		Liferay.Chat.Conversation = Conversation;

		Liferay.publish(
			'chatPortletReady',
			{
				defaultFn: A.bind('init', Liferay.Chat.Manager),
				fireOnce: true
			}
		);

		A.on(
			'domready',
			function() {
				Liferay.fire('chatPortletReady');
			}
		);
	}
);