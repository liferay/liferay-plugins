Liferay.Chat = {
	init: function(params) {
		var instance = this;

		instance.prefs = {
			refresh: params.refresh || 1,
			refreshThrottle: 0,
			refreshMax: params.refreshMax || 8,
			running: false,
			user: params.user,
			activeBrowser: true
		};

		instance.ajax = {
			cache: false,
			data: {
				saveLastPath: 0,
				fromUserId: params.user,
				activeBrowserKey: Math.floor(Math.random() * 1000000)
			},
			type: 'get',
			url: themeDisplay.getLayoutURL() + '/-/chat/latest'
		};

		instance.get(true);

		if (!instance.prefs.interval) {
			instance.prefs.interval = setInterval(
				function() {
					instance.get(false);
				},
				instance.prefs.refresh * 1000);
		}

		jQuery('li.item > a.item-link').click(
			function() {
				instance.prefs.refreshThrottle = 0;
				instance.prefs.activeBrowser = true;

				if (!jQuery(this).is('.null')) {
					instance.chatWindowPopup(this);
				}

				clearInterval(instance.prefs.interval);

				instance.prefs.interval = setInterval(
					function() {
						Liferay.Chat.get(true);
					},
					(Liferay.Chat.prefs.refresh + Liferay.Chat.prefs.refreshThrottle) * 1000
				);
			}
		);
	},

	chatClose: function(element) {
		jQuery(element).parents('.item').remove();
	},

	chatType: function(element) {
		element = jQuery(element);

		var prepArea = element.siblings('.prep');
		var showArea = element.parents('.chat').children('.show');

		if (element.val().indexOf('\n') > -1) {
			element.val(element.val().replace(/\n|\r/g, ''));
		}

		prepArea.val(element.val());

		var chatAreaHeight = 219 - prepArea[0].scrollHeight;
		var textAreaHeight = prepArea[0].scrollHeight;

		if (textAreaHeight != element.height()) {
			if (textAreaHeight > 78) {
				showArea.css(
					{
						height: 141
					}
				);

				element.css(
					{
						height: 78,
						overflowY: 'scroll'
					}
				);
			}
			else {
				showArea.css(
					{
						height: chatAreaHeight
					}
				);

				element.css(
					{
						height: textAreaHeight,
						overflowY: 'hidden'
					}
				);
			}

			showArea[0].scrollTop = showArea[0].scrollHeight;
			element[0].scrollTop = prepArea[0].scrollHeight;
		}
	},

	chatWindowPopup: function(obj) {
		var element = jQuery(obj || this);
		var parent = element.parent();

		if (!parent.is('.active')) {
			parent.removeClass('waiting');
			parent.siblings('.item').removeClass('active');
			parent.addClass('active');

			var popup = element.siblings('.popup');

			if (popup.children().is('.chat')) {
				var cc = popup.find('.show')[0];

				cc.scrollTop = cc.scrollHeight;

				popup.find('.text').trigger('focus');
			}
		}
		else {
			parent.removeClass('active');
		}
	},

	get: function() {
		var instance = this;

		var AJAXdata = jQuery.extend(
			{
				activeBrowser: false
			},
			this.ajax.data,
			{
				t: (new Date()).getTime()
			}
		);

		if (instance.prefs.activeBrowser === true) {
			AJAXdata.activeBrowser = true;

			instance.prefs.activeBrowser = null;
		}

		if (instance.prefs.running || (instance.prefs.activeBrowser === false)) {
			return;
		}

		var ajaxRequest = jQuery.extend(
			{},
			this.ajax,
			{
				beforeSend: function(connection) {
					Liferay.Chat.prefs.running = true;

					connection.setRequestHeader('Connection', 'Keep-Alive');
				},
				data: AJAXdata,
				dataType: 'json',
				success: function(response) {
					Liferay.Chat.prefs.running = false;

					if (response.activeBrowser === false) {
						jQuery('ul.chat-bar .item').removeClass('active');

						Liferay.Chat.prefs.activeBrowser = false;
					}
					else {
						var buddyListItem = jQuery('.chat-portlet li.item:first');

						if (response.buddies.length > 0) {
							var chatPopup = jQuery('#buddy-list .chat-popup');

							buddyListItem.children('a').removeClass('null');

							buddyListItem.children('a').html('Chat (' + response.buddies.length + ')');

							chatPopup.html('');

							Liferay.Chat.users = {};
							Liferay.Chat.userIcons = {};

							for (i = 0; i < response.buddies.length; i++) {
								Liferay.Chat.users[response.buddies[i].userId] = response.buddies[i].fullName;
								Liferay.Chat.userIcons[response.buddies[i].userId] = themeDisplay.getPathImage() + '/user_portrait?img_id=' + response.buddies[i].portraitId;

								chatPopup.append('<li><a href="javascript: ;" onClick="Liferay.Chat.openChatWindow(\'' + response.buddies[i].userId + '\');">' + response.buddies[i].fullName + '</a></li>');
							}

							Liferay.Chat.users[themeDisplay.getUserId()] = themeDisplay.getUserName();
						}
						else {							
							buddyListItem.children('a').html('Chat (0)');
							buddyListItem.children('a').addClass('null');
						}

						if (response.entries.length) {
							if (Liferay.Chat.prefs.refreshThrottle > 0) {
								Liferay.Chat.prefs.refreshThrottle = 0;

								clearInterval(Liferay.Chat.prefs.interval);

								Liferay.Chat.prefs.interval = setInterval(
									function() {
										Liferay.Chat.get(false);
									},
									(Liferay.Chat.prefs.refresh) * 1000
								);
							}

							Liferay.Chat.ajax.data.createDate = response.entries[response.entries.length-1].createDate;

							for (i = 0; i < response.entries.length; i++) {
								var chatBuddy = (response.entries[i].fromUserId != Liferay.Chat.prefs.user) ? response.entries[i].fromUserId : response.entries[i].toUserId;
								var chatBuddyCSSname = chatBuddy.toString().replace(/ /g, '-').toLowerCase();

								Liferay.Chat.users[response.entries[i].fromUserId] = response.entries[i].fromFullName || Liferay.Chat.users[response.entries[i].fromUserId];

								var time = new Date(response.entries[i].createDate);

								var timeAMPM = (time.getHours() < 12) ? 'am' : 'pm';
								var timeHour = (time.getHours() > 12) ? time.getHours() - 12 : time.getHours();
								var timeMinute = (time.getMinutes() < 10) ? '0' + time.getMinutes() : time.getMinutes();

								var timeBind = timeHour + ':' + timeMinute + ' ' + timeAMPM;

								if (jQuery('ul.chat-bar .' + chatBuddyCSSname).length == 0) {
									Liferay.Chat.newChat(chatBuddy);
								}

								var o = jQuery('ul.chat-bar .' + chatBuddyCSSname);

								if (!o.is('.active')) {
									o.addClass('waiting');
								}
								else {
									o.removeClass('waiting');
								}

								var s = o.children('.popup').children('.chat').children('.show');

								s.append('<p class="' + ((response.entries[i].fromUserId == Liferay.Chat.prefs.user) ? 'you' : 'not') + '"><b class="name">' + Liferay.Chat.users[response.entries[i].fromUserId] + '</b><i class="date">' + timeBind + '</i><span class="text">' + response.entries[i].content.replace(/</g, '&lt;').replace(/>/g, '&gt;') + '</span></p>');

								s[0].scrollTop = s[0].scrollHeight;
							}
						}
						else if ((Liferay.Chat.prefs.refreshThrottle + Liferay.Chat.prefs.refresh) < Liferay.Chat.prefs.refreshMax) {
							Liferay.Chat.prefs.refreshThrottle += 0.5;

							clearInterval(Liferay.Chat.prefs.interval);

							Liferay.Chat.prefs.interval = setInterval(
								function() {
									Liferay.Chat.get(false);
								},
								(Liferay.Chat.prefs.refresh + Liferay.Chat.prefs.refreshThrottle) * 1000
							);
						}
					}
				}
			}
		);

		jQuery.ajax(ajaxRequest);
	},

	newChat: function(userId) {
		var instance = this;

		userId = jQuery.trim(userId.toString());

		var userIdClass = userId.replace(/ /, '-').toLowerCase();

		if (!jQuery('ul.chat-bar .item').is('.' + userIdClass)) {
			jQuery('ul.chat-bar').append('<li class="item ' + userIdClass + '"><a class="item-link" href="javascript:;">' + Liferay.Chat.users[userId] + '</a><div class="popup"><div class="chat"><div class="head"><a class="close" href="javascript:;" onClick="Liferay.Chat.chatClose(this);"><span>close</span></a><img class="image" src="' + Liferay.Chat.userIcons[userId] + '">' + Liferay.Chat.users[userId] + '</div><div class="info"></div><div class="show"></div><div class="type"><textarea class="prep"></textarea><textarea class="text" with="' + userId + '"></textarea></div></div></div></li>');
			jQuery('ul.chat-bar li:last a.item-link').click(
				function() {
					instance.prefs.refreshThrottle = 0;
					instance.prefs.activeBrowser = true;

					instance.chatWindowPopup(this);

					clearInterval(instance.prefs.interval);

					instance.prefs.interval = setInterval(
						function() {
							Liferay.Chat.get(true);
						},
						(Liferay.Chat.prefs.refresh + Liferay.Chat.prefs.refreshThrottle) * 1000
					);
				}
			);

			var ta = jQuery('ul.chat-bar li:last .text');

			var keyEvent = function(event) {
				if (event.type == 'keyup') {
					if (event.keyCode == 13 && jQuery.trim(this.value) != '') {
						Liferay.Chat.send(this);
						this.value = '';
					}
				}

				Liferay.Chat.chatType(this);
			};

			ta.keydown(keyEvent);

			ta.keypress(keyEvent);

			ta.keyup(keyEvent);

			jQuery('ul.chat-bar li:last').addClass('waiting');

			instance.chatWindowPopup(jQuery('ul.chat-bar li:last a.item-link')[0]);
		}
	},

	openChatWindow: function(userId) {
		var instance = this;

		var userIdClass = userId.replace(/ /, '-').toLowerCase();

		var newWindow = jQuery('ul.chat-bar li.' + userIdClass + ' a.item-link');
		var newWindowExists = (newWindow.length > 0);

		instance.newChat(userId);

		if (newWindowExists) {
			instance.chatWindowPopup(jQuery('ul.chat-bar li.' + userIdClass + ' a.item-link')[0]);
		}
	},

	send: function(tag) {
		var instance = this;

		jQuery.ajax(
			jQuery.extend(
				{},
				instance.ajax,
				{
					cache: false,
					beforeSend: function(connection) {
						connection.setRequestHeader('Connection', 'Keep-Alive');
					},
					data: jQuery.extend({}, instance.ajax.data, {
						toUserId: jQuery(tag).attr('with'),
						content: jQuery.trim(jQuery(tag).val()).replace(/</g, '&lt;').replace(/>/g, '&gt;')
					}),
					success: function() {
						Liferay.Chat.get(false);
					},
					url: themeDisplay.getLayoutURL() + '/-/chat/send'
				}
			)
		);
	}
};

jQuery().ready(
	function() {
		Liferay.Chat.init(
			{
				user: themeDisplay.getUserId()
			}
		);
	}
);