Liferay.Chat = {
	init: function(params) {
		var instance = this;

		instance.prefs = {
			refresh: params.refresh || 1,
			refreshEase: 0,
			refreshMax: params.refreshMax || 7,
			running: false,
			user: params.user
		};

		instance.ajax = {
			cache: false,
			data: {
				fromUserId: params.user,
				createDate: '00000000000000'
			},
			type: params.type || 'get',
			url: params.url
		};

		instance.get();

		if (!instance.prefs.interval) {
			instance.prefs.interval = setInterval(
				function(){
					instance.get();
				},
				instance.prefs.refresh * 1000);
		}

		jQuery('li.item > a.item-link').click(
			function() {
				instance.chatWindowPopup(this);
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

		var chatAreaHeight = 193 - prepArea[0].scrollHeight;
		var textAreaHeight = prepArea[0].scrollHeight;

		if (jQuery.browser.msie) {
			chatAreaHeight -= 8;
			textAreaHeight -= 8;
		}

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

	get: function(d) {
		var instance = this;

		if (instance.prefs.running) {
			return;
		}

		jQuery.ajax(
			jQuery.extend(
				{},
				this.ajax,
				{
					beforeSend: function(connection) {
						Liferay.Chat.prefs.running = true;
						connection.setRequestHeader('Connection', 'Keep-Alive');
					},
					data: jQuery.extend(
						this.ajax.data,
						{
							timestamp: (new Date()).getTime()
						}
					),
					dataType: 'json',
					success: function(response) {
						Liferay.Chat.prefs.running = false;

						if (response.length) {
							if (Liferay.Chat.prefs.refreshEase > 0) {
								Liferay.Chat.prefs.refreshEase = 0;

								clearInterval(Liferay.Chat.prefs.interval);

								Liferay.Chat.prefs.interval = setInterval(
									function() {
										Liferay.Chat.get();
									},
									(Liferay.Chat.prefs.refresh) * 1000
								);
							}

							Liferay.Chat.ajax.data.createDate = response[response.length-1].createDate;

							for (i = 0; i < response.length; i++) {
								var u = (response[i].fromUserId != Liferay.Chat.prefs.user) ? response[i].fromUserId : response[i].toUserId;
								var uc = u.toString().replace(/ /g, '-').toLowerCase();

								if (jQuery('ul.chat-bar .' + uc).length == 0) {
									Liferay.Chat.newChat(u);
								}

								var o = jQuery('ul.chat-bar .' + uc);

								if (!o.is('.active')) {
									o.addClass('waiting');
								}
								else {
									o.removeClass('waiting');
								}

								var s = o.children('.popup').children('.chat').children('.show');

								s.append('<p class="' + ((response[i].fromUserId == Liferay.Chat.prefs.user) ? 'you' : 'not') + '"><b class="name">' + Liferay.Chat.users[response[i].fromUserId] + '</b><i class="date">' + response[i].createDate + '</i><span class="text">' + response[i].content.replace(/</g, '&lt;') + '</span></p>');
								s[0].scrollTop = s[0].scrollHeight;
							}
						}
						else if ((Liferay.Chat.prefs.refreshEase + Liferay.Chat.prefs.refresh) < Liferay.Chat.prefs.refreshMax) {
							Liferay.Chat.prefs.refreshEase++;

							clearInterval(Liferay.Chat.prefs.interval);

							Liferay.Chat.prefs.interval = setInterval('Liferay.Chat.get()', (Liferay.Chat.prefs.refresh + Liferay.Chat.prefs.refreshEase) * 1000);
						}
					}
				}
			)
		);
	},

	newChat: function(userId) {
		var instance = this;

		userId = jQuery.trim(userId.toString());

		var userIdClass = userId.replace(/ /, '-').toLowerCase();

		if (!jQuery('ul.chat-bar .item').is('.' + userIdClass)) {
			jQuery('ul.chat-bar').append('<li class="item ' + userIdClass + '"><a class="item-link" href="javascript:;">' + Liferay.Chat.users[userId] + '</a><div class="popup"><div class="chat"><div class="head"><a class="close" href="javascript:;" onClick="Liferay.Chat.chatClose(this);"><span>close</span></a><img class="image" src="' + Liferay.Chat.userIcons[userId] + '">' + Liferay.Chat.users[userId] + '</div><div class="info"></div><div class="show"></div><div class="type"><textarea class="prep"></textarea><textarea class="text" with="' + userId + '"></textarea></div></div></div></li>');
			jQuery('ul.chat-bar li:last a.item-link').click(
				function() {
					instance.chatWindowPopup(this);
				}
			);

			var ta = jQuery('ul.chat-bar li:last .text');

			var keyEvent = function(event){
				if (event.type == 'keyup') {
					if (event.keyCode == 13 && jQuery.trim(this.value) != '') {
						Liferay.Chat.send(this);
						this.value = '';
					}
				}

				Liferay.Chat.chatType(this);
			}

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
					url: themeDisplay.getLayoutURL() + '/-/chat/send',
					beforeSend: function(connection) {
						connection.setRequestHeader('Connection', 'Keep-Alive');
					},
					data: jQuery.extend({}, instance.ajax.data, {
						toUserId: jQuery(tag).attr('with'),
						content: jQuery.trim(jQuery(tag).val()).replace(/</g, '&lt;').replace(/>/g, '&gt;')
					}),
					success: function() {
						Liferay.Chat.get();
					}
				}
			)
		);
	}
};

jQuery().ready(
	function() {
		jQuery.ajax(
			{
				data: {
					timestamp: (new Date()).getTime()
				},
				dataType: 'json',
				success: function(buddies) {
					var chatPopup = jQuery('#buddy-list .chat-popup');

					jQuery('.chat-portlet li.item:first > a').html('Chat (' + buddies.length + ')');

					chatPopup.html('');

					Liferay.Chat.users = {};
					Liferay.Chat.userIcons = {};

					for (buddy in buddies) {
						Liferay.Chat.users[buddies[buddy].userId] = buddies[buddy].userName;
						Liferay.Chat.userIcons[buddies[buddy].userId] = themeDisplay.getPathImage() + '/user_portrait?img_id=' + buddies[buddy].userId;

						chatPopup.append('<li><a href="javascript: ;" onClick="Liferay.Chat.openChatWindow(\'' + buddies[buddy].userId + '\');">' + buddies[buddy].userName + '</a></li>');
					}

					Liferay.Chat.users[themeDisplay.getUserId()] = themeDisplay.getUserName();

					Liferay.Chat.init(
						{
							url: themeDisplay.getLayoutURL() + '/-/chat/entries',
							user: themeDisplay.getUserId()
						}
					);
				},
				type: 'get',
				url: themeDisplay.getLayoutURL() + '/-/chat/buddies'
			}
		);
	}
);