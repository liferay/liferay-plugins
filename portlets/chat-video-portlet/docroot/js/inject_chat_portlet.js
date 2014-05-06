AUI().use(
	'anim-base',
	'anim-easing',
	'aui-base',
	'liferay-poller',
	function(A) {
		Liferay.namespace('Chat');

		Liferay.Chat.VideoManager = {
			appendNodeToOverlay: function(node) {
				var instance = this;

				node.appendTo(instance._chatVideoOverlayNode);
			},

			disableInRinging: function() {
				var instance = this;

				instance._tryDisableAudio(instance._inRingingEl);
			},

			disableOutRinging: function() {
				var instance = this;

				instance._tryDisableAudio(instance._outRingingEl);
			},

			enableInRinging: function() {
				var instance = this;

				if (instance._chatManager._playSound) {
					instance._inRingingEl.play();
				}
			},

			enableOutRinging: function() {
				var instance = this;

				if (instance._chatManager._playSound) {
					instance._outRingingEl.play();
				}
			},

			hideOverlay: function() {
				var instance = this;

				var overlayAnim = new A.Anim(
					{
						node: instance._chatVideoOverlayNode,
						to: {
							opacity: 0
						},
						duration: 0.25
					}
				);

				overlayAnim.on(
					'end',
					function() {
						instance._chatVideoOverlayNode.hide();
					}
				);

				overlayAnim.run();
			},

			init: function(chatManager) {
				var instance = this;

				instance._chatManager = chatManager;

				instance._buddies = {};
				instance._portletId = A.one('#chatVideoPortletId').val();
				instance._webRtcManager = Liferay.Chat.WebRtcManager;

				if (instance._webRtcManager.isSupported()) {
					var pollerNotificationsTimeout = A.Lang.toInt(A.one('#chatVideoPortletPollerNotificationsTimeout').val());
					var pollerRequestTimeout = A.Lang.toInt(A.one('#chatVideoPortletPollerRequestTimeout').val());

					instance._fastPollingRate = false;
					instance._fastPollingRateDelayMs = pollerNotificationsTimeout + pollerRequestTimeout + 100;
					instance._increasedPollingCountMs = 0;
					instance._increasedPollingMaxCountMs = 30000;

					Liferay.Poller.addListener(instance._portletId, instance._onPollerUpdate, instance);
					Liferay.bind('sessionExpired',
						function(event) {
							instance._stopFastPolling();
							Liferay.Poller.removeListener(instance._portletId);
						}
					);

					instance._webRtcManager.init(
						{
							cb: {
								disableInRinging: A.bind('disableInRinging', instance),

								disableOutRinging: A.bind('disableOutRinging', instance),

								enableInRinging: A.bind('enableInRinging', instance),

								enableOutRinging: A.bind('enableOutRinging', instance),

								ensurePanel: function(userId) {
									var buddy = instance._chatManager._buddies[userId];

									if (buddy) {
										var chat = instance._chatManager._chatSessions[userId];

										if (!chat) {
											chat = instance._chatManager._createChatSession(
												{
													fullName: buddy.fullName,
													portraitId: buddy.portraitId,
													userId: buddy.userId,
													statusMessage: buddy.statusMessage
												}
											);
										}
									}
								},

								isUserAvailable: function(userId) {
									return instance.isUserAvailable(userId);
								},

								onMediaDisabled: function() {
									instance.unmute();
								},

								onMediaEnabled: function() {
									instance.unmute();
								},

								send: function(payload) {
									instance._send(payload);
								}
							}
						}
					);

					instance._inRingingEl = A.one('#chatVideoInRingtone').getDOM();
					instance._outRingingEl = A.one('#chatVideoOutRingtone').getDOM();
					instance._chatVideoOverlayNode = A.one('#chatVideoOverlay');
					instance._overlayVideoCallTimeNode = instance._chatVideoOverlayNode.one('.call-time');

					/* Modify the "Play sound..." setting checkbox text to include sounds of
					 * video calls. This is hackish using plain DOM elements because we need
					 * to modify a text node without touching the checkbox node.
					 */
					var playSoundLabelEl = A.one('#playSound').ancestor().getDOM();
					var playSoundLabelTextEl = playSoundLabelEl.childNodes[1];
					playSoundLabelTextEl.nodeValue = ' Play a sound when I receive a new message in a hidden window and for video calls ringtones.';

					var showOnlineSettingNode = A.one('#onlineStatus').ancestor('li');
					var availableForChatVideoSettingHtml =
						'<li>' +
							'<label for="availableForChatVideo">' +
								'<input checked="checked" id="availableForChatVideo" type="checkbox">' +
								' Show me as available for video calls.' +
							'</label>' +
						'</li>';
					var availableForChatVideoSettingNode = A.Node.create(availableForChatVideoSettingHtml);
					showOnlineSettingNode.placeAfter(availableForChatVideoSettingNode);
					instance._availableForChatVideoSettingCheckboxNode = A.one('#availableForChatVideo');

					instance.hideOverlay();

					instance._onAfterUpdateSettings();
				}
			},

			isAvailable: function() {
				var instance = this;

				var available = false;

				if (!A.Lang.isUndefined(instance._availableForChatVideoSettingCheckboxNode)) {
					available = instance._availableForChatVideoSettingCheckboxNode.get('checked');
				}

				return available;
			},

			isUserAvailable: function(userId) {
				var instance = this;

				var available = false;

				if (!A.Lang.isUndefined(userId) && userId !== null) {
					available = !A.Lang.isUndefined(instance._buddies[userId]);
				}

				return available;
			},

			mute: function() {
				var instance = this;

				instance._webRtcManager.muteMike();
				instance._updateMikeButtons('muted');
			},

			onChatVideoConversationStateChange: function() {
				var instance = this;

				var globalState = instance._webRtcManager.getConversationsGlobalState();

				if (globalState.communicationRequired) {
					instance._startFastPolling();
				}
				else {
					instance._stopFastPolling();
				}
			},

			setOverlayVideoCallTime: function(timeStr) {
				var instance = this;

				instance._overlayVideoCallTimeNode.setContent(timeStr);
			},

			showOverlay: function() {
				var instance = this;

				instance._chatVideoOverlayNode.setStyle('opacity', '0');
				instance._chatVideoOverlayNode.show();

				var overlayAnim = new A.Anim(
					{
						node: instance._chatVideoOverlayNode,
						to: {
							opacity: 1
						},
						duration: 0.4
					}
				);

				overlayAnim.run();
			},

			unmute: function() {
				var instance = this;

				instance._webRtcManager.unmuteMike();
				instance._updateMikeButtons('unmuted');
			},

			_onAfterUpdateBuddies: function(buddies) {
				var instance = this;

				var listItems = instance._chatManager._onlineBuddies.all('li.user');

				listItems.each(
					function(liNode) {
						var uid = liNode.getAttribute('data-userId');
						var userImageNode = liNode.one('img');

						if (uid && instance.isUserAvailable(uid)) {
							var iconNode = A.Node.create('<div class="chat-video-available"></div>');
							userImageNode.placeAfter(iconNode);
						}
					}
				);
			},

			_onAfterUpdateSettings: function() {
				var instance = this;

				var globalState = instance._webRtcManager.getConversationsGlobalState();

				if (!instance._chatManager._playSound) {
					instance.disableOutRinging();
					instance.disableInRinging();
				}
				else {
					if (globalState.inRinging) {
						instance.enableInRinging();
					}

					if (globalState.outRinging) {
						instance.enableOutRinging();
					}
				}

				if (!A.Lang.isUndefined(instance._availableForChatVideoSettingCheckboxNode)) {
					var available = instance.isAvailable();
					instance._webRtcManager.sendSetAvailabilityMsg(available);

					if (!available) {
						for (var id in instance._chatManager._chatSessions) {
							var session = instance._chatManager._chatSessions[id];

							session.hideCtrlButtons();
						}
					}
				}
			},

			_onPanelClose: function(event) {
				var instance = this;

				var panel = event.target;
				var userId = panel._panelId;

				if (panel instanceof Liferay.Chat.Conversation) {
					instance._chatManager._chatSessions[userId].onDelete();
				}
			},

			_onPollerUpdate: function(response, chunk) {
				var instance = this;

				instance._buddies = {};

				for (var i in response.webRTCResponse.clients) {
					instance._buddies[response.webRTCResponse.clients[i]] = true;
				}

				for (var id in instance._chatManager._chatSessions) {
					var session = instance._chatManager._chatSessions[id];

					if (session._panelId in instance._buddies) {
						session.setAvailableForChatVideo(instance.isUserAvailable(session._panelId));
					}
					else {
						session.setAvailableForChatVideo(false);
					}
				}

				instance._webRtcManager.processWebRtcMails(response.webRTCResponse.mails);

				if (instance._fastPollingRate) {
					instance._increasedPollingCountMs += instance._fastPollingRateDelayMs;

					if (instance._increasedPollingCountMs >= instance._increasedPollingMaxCountMs) {
						instance._stopFastPolling();
					}
				}
			},

			_send: function(payload) {
				var instance = this;

				Liferay.Poller.submitRequest(instance._portletId, payload);
			},

			_setFastPollingRate: function(fastPollingRate) {
				var instance = this;

				instance._fastPollingRate = fastPollingRate;
			},

			_startFastPolling: function() {
				var instance = this;

				instance._increasedPollingCountMs = 0;
				instance._setFastPollingRate(true);
				Liferay.Poller.setCustomDelay(instance._fastPollingRateDelayMs);
			},

			_stopFastPolling: function() {
				var instance = this;

				instance._setFastPollingRate(false);
				Liferay.Poller.cancelCustomDelay();
			},

			_tryDisableAudio: function(el) {
				/* We do this here because when modifying the playback state
				 * of an HTMLMediaElement, it can throw some exceptions when
				 * not ready for some reason. Since it's not loaded anyway,
				 * we don't care about the raised exception since it's not
				 * playing and we want to disable it anyway.
				 */
				try {
					el.pause();
					el.currentTime = 0;
				}
				catch(e) {
					// Probably not ready yet: not playing anyway
				}
			},

			_updateMikeButtons: function(newClass) {
				var instance = this;

				for (id in instance._chatManager._chatSessions) {
					instance._chatManager._chatSessions[id].updateChatVideoMikeButton(newClass);
				}
			}
		};

		Liferay.Chat.VideoCallTimer =
			function(onTimeChange) {
				var instance = this;

				instance._onTimeChange = onTimeChange;
				instance._timer = null;
			}

		Liferay.Chat.VideoCallTimer.prototype = {
			reset: function() {
				var instance = this;

				if (instance._timer !== null) {
					clearInterval(instance._timer);
					instance._timer = null;
				}
			},

			start: function() {
				var instance = this;

				instance.reset();

				instance._beginMs = new Date().getTime();

				instance._timer = setInterval(
					function() {
						instance._onTick();
					}, 1000);
			},

			_onTick: function() {
				var instance = this;

				var curTimeMs = new Date().getTime();
				var elapsedMsTotal = curTimeMs - instance._beginMs;
				var elapsedSecondsTotal = parseInt(elapsedMsTotal / 1000);
				var elapsedMinutes = parseInt(elapsedSecondsTotal / 60);
				var elapsedSeconds = elapsedSecondsTotal - elapsedMinutes * 60;

				if (elapsedSeconds < 10) {
					elapsedSeconds = '0' + elapsedSeconds;
				}

				var elapsedTimeString = elapsedMinutes + ':' + elapsedSeconds;

				instance._onTimeChange(elapsedTimeString);
			}
		};

		Liferay.Chat.VideoConversationStatus =
			function(node) {
				var instance = this;

				instance._destroyed = false;
				instance._node = node;
				instance._msgNode = node.one('.msg');
				instance._workingNode = node.one('.working');
				instance._errorTimeout = null;
				instance._errorAnim = null;

				instance.hide();
			};

		Liferay.Chat.VideoConversationStatus.prototype = {
			destroy: function() {
				var instance = this;

				// Make sure to cancel future events
				instance._stopErrorMessage();

				instance._destroyed = true;
			},

			hide: function() {
				var instance = this;

				if (!instance._destroyed && instance._errorTimeout === null) {
					instance._node.hide();
				}
			},

			setErrorMessage: function(msg) {
				var instance = this;

				if (!instance._destroyed) {
					instance._stopErrorMessage();

					instance._node.removeClass('in-call');
					instance._node.addClass('error');

					instance._workingNode.hide();

					instance._msgNode.setHTML(msg);

					instance.show();

					instance._errorTimeout = setTimeout(
						function() {
							instance._errorTimeout = null;
							instance._errorAnim = new A.Anim(
								{
									node: instance._node,
									to: {
										opacity: 0
									},
									duration: 0.4
								}
							);
							instance._errorAnim.on(
								'end',
								function() {
									instance._errorAnim = null;
									instance.hide();
								}
							);
							instance._errorAnim.run();
						},
						3000
					);
				}
			},

			setInCallMessage: function(msg) {
				var instance = this;

				if (!instance._destroyed) {
					instance._stopErrorMessage();

					instance._node.removeClass('error');
					instance._node.addClass('in-call');

					instance._workingNode.hide();

					instance._msgNode.setHTML(msg);

					instance.show();
				}
			},

			setRegularMessage: function(msg, working) {
				var instance = this;

				if (!instance._destroyed) {
					if (A.Lang.isUndefined(working)) {
						working = false;
					}

					instance._stopErrorMessage();

					instance._node.removeClass('in-call');
					instance._node.removeClass('error');

					instance._msgNode.setHTML(msg);

					if (working) {
						instance._workingNode.show();
					}
					else {
						instance._workingNode.hide();
					}

					instance.show();
				}
			},

			show: function() {
				var instance = this;

				if (!instance._destroyed) {
					if (instance._errorAnim !== null) {
						instance._errorAnim.stop();
					}

					instance._node.setStyle('opacity', '1');

					instance._node.show();
				}
			},

			_stopErrorMessage: function() {
				var instance = this;

				if (instance._errorTimeout !== null) {
					clearTimeout(instance._errorTimeout);
					instance._errorTimeout = null;
					instance.hide();
				}

				if (instance._errorAnim !== null) {
					instance._errorAnim.stop();
					instance._errorAnim = null;
				}
			}
		};

		Liferay.on('chatPortletReady', function(event) {
			var instance = this;

			A.on(
				function(options) {
					var instance = this;

					Liferay.Chat.VideoManager.init(instance);
				},
				Liferay.Chat.Manager,
				'init'
			);

			A.on(
				function(event) {
					Liferay.Chat.VideoManager._onPanelClose(event);
				},
				Liferay.Chat.Manager,
				'_onPanelClose'
			);

			A.after(
				function(buddies) {
					Liferay.Chat.VideoManager._onAfterUpdateBuddies(buddies);
				},
				Liferay.Chat.Manager,
				'_updateBuddies'
			);

			A.after(
				function() {
					Liferay.Chat.VideoManager._onAfterUpdateSettings();
				},
				Liferay.Chat.Manager,
				'_updateSettings'
			);

			var Chat = Liferay.Chat;
			Chat.ConversationPanel = Chat.Conversation;

			Chat.Conversation = function() {
				var instance = this;

				Chat.Conversation.superclass.constructor.apply(instance, arguments);

				if (Liferay.Chat.WebRtcManager.isSupported()) {
					instance._webRtc = null;
					instance._availableForChatVideo = false;
					instance._videoCallTimer = null;

					instance._ctrlButtonsNode = instance._panel.one('.chat-video-ctrl-buttons');
					instance._localVideoNode = instance._panel.one('.panel-self-view video.local');
					instance._remoteVideoContainerNode = instance._panel.one('.remote-video-container');
					instance._remoteVideoOuterNode = instance._panel.one('.remote-video');
					instance._remoteVideoNode = instance._remoteVideoOuterNode.one('video.remote');
					instance._selfViewNode = instance._panel.one('.panel-self-view');
					instance._selfViewImgNode = instance._panel.one('.panel-self-view img');
					instance._ctrlButtonsContainerNode = instance._panel.one('.chat-video-ctrl');

					instance._chatVideoCtrlButtonsNodes = {
						'accept': instance._ctrlButtonsContainerNode.one('a.accept'),
						'call': instance._ctrlButtonsContainerNode.one('a.call'),
						'fullScreen': instance._ctrlButtonsContainerNode.one('a.fullscreen'),
						'hangUp': instance._ctrlButtonsContainerNode.one('a.hangup'),
						'mike': instance._ctrlButtonsContainerNode.one('a.mike')
					};

					var chatVideoMsgContainerNode = instance._panel.one('.chat-video-msg');
					instance._status = new Liferay.Chat.VideoConversationStatus(chatVideoMsgContainerNode);

					instance._videoCallTimer = new Liferay.Chat.VideoCallTimer(
						function(timeStr) {
							instance._onVideoCallTimeChange(timeStr);
						}
					);

					instance._webRtc = new Liferay.Chat.WebRtcConversation(
						{
							cb: {
								onError: function(error) {
									var Error = Liferay.Chat.WebRtcConversation.Error;

									var errorMessages = {};
									errorMessages[Error.CANNOTGETUSERMEDIA] = 'Cannot access your camera';
									errorMessages[Error.HANGUP] = 'Your friend hung up';
									errorMessages[Error.REMOTEPEERDENIEDCALL] = 'Your friend denied your call';
									errorMessages[Error.REMOTEPEERNOTAVAILABLE] = 'Your friend is not available';
									errorMessages[Error.REMOTEPEERRESET] = 'Your friend had an issue with the video call';

									if (error in errorMessages) {
										instance._status.setErrorMessage(errorMessages[error]);
									}
								},

								onStateChange: function(state) {
									var State = Liferay.Chat.WebRtcConversation.State;

									Liferay.Chat.VideoManager.onChatVideoConversationStateChange();

									// Accept control button
									switch (state) {
										case State.GOTCALL:
											instance._showCtrlButton('accept');
											break;

										default:
											instance._hideCtrlButton('accept');
											break;
									}

									// Hangup control button
									switch (state) {
										case State.STOPPED:
										case State.STOPPING:
										case State.DELETED:
										case State.DELETING:
											instance._hideCtrlButton('hangUp');
											break;

										default:
											instance._showCtrlButton('hangUp');
											break;
									}

									// Call control button
									switch (state) {
										case State.STOPPED:
											instance._showCtrlButton('call');
											break;

										default:
											instance._hideCtrlButton('call');
											break;
									}

									// Mute/unmute control button
									switch (state) {
										default:
											instance._hideCtrlButton('mike');
											break;
									}

									// Fullscreen control button
									switch (state) {
										default:
											instance._hideCtrlButton('fullScreen');
											break;
									}

									// Status message
									switch (state) {
										case State.DELETED:
										case State.STOPPED:
											instance._status.hide();
											break;

										case State.CALLINGWAITING:
										case State.GOTCALLWAITING:
											instance._status.setRegularMessage('Please share camera', true);
											break;

										case State.CALLING:
										case State.CALLED:
											instance._status.setRegularMessage('Calling friend...', true);
											break;

										case State.GOTCALL:
											instance._status.setRegularMessage('Incoming video call...', true);
											break;

										case State.GOTANSWER:
										case State.ANSWERED:
										case State.ACCEPTINGCALL:
											instance._status.setRegularMessage('Establishing connection...', true);
											break;

										case State.STOPPING:
										case State.DELETING:
											instance._status.setRegularMessage('Stopping video call...', true);
											break;

										case State.CONNECTED:
											instance._videoCallTimeStr = '0:00';
											instance._status.setInCallMessage(instance._videoCallTimeStr);
											break;
									}

									// Fullscreen hiding
									switch (state) {
										case State.DELETED:
										case State.DELETING:
										case State.STOPPED:
										case State.STOPPING:
											instance._disableVideoFullScreen();
											break;
									}

									// Remote video hiding
									switch (state) {
										case State.DELETED:
										case State.DELETING:
											instance._remoteVideoContainerNode.hide();
											break;

										case State.STOPPED:
										case State.STOPPING:
											instance._hideRemoteVideo();
											break;
									}

									// Local video
									switch (state) {
										case State.CALLING:
										case State.CALLED:
										case State.GOTCALL:
										case State.ANSWERED:
										case State.GOTANSWER:
										case State.ACCEPTINGCALL:
										case State.DENYINGCALL:
										case State.CONNECTED:
											instance._showLocalVideo();
											break;

										default:
											instance._hideLocalVideo();
											break;
									}

									// Video call timer
									switch (state) {
										case State.STOPPING:
										case State.DELETING:
										case State.STOPPED:
										case State.DELETED:
											instance._videoCallTimer.reset();
											break;
									}

									// Special handler for connected state
									if (state === State.CONNECTED) {
										instance._waitForRemoteStreamFlowing();
									}
								},

								onWebRtcEvent: function() {
									if (!instance.get('selected')) {
										instance.setWaiting(true);
									}
								}
							},

							iceServers: [],
							localVideoEl: instance._localVideoNode.getDOM(),
							remoteVideoEl: instance._remoteVideoNode.getDOM(),
							userId: instance._panelId
						}
					);

					instance._chatVideoCtrlButtonsNodes['accept'].on(
						'click',
						function() {
							instance._webRtc.onPressAccept();
						}
					);

					instance._chatVideoCtrlButtonsNodes['hangUp'].on(
						'click',
						function() {
							instance._disableVideoFullScreen();
							instance._webRtc.onPressHangUp();
						}
					);

					instance._chatVideoCtrlButtonsNodes['call'].on(
						'click',
						function() {
							if (Liferay.Chat.VideoManager.isUserAvailable(instance._panelId)) {
								instance._webRtc.onPressCall();
							}
						}
					);

					instance._chatVideoCtrlButtonsNodes['fullScreen'].on(
						'click',
						function() {
							instance._toggleFullScreen();
						}
					);

					instance._chatVideoCtrlButtonsNodes['mike'].on(
						'click',
						function() {
							var node = this;

							if (node.hasClass('muted')) {
								Liferay.Chat.VideoManager.unmute();
							}
							else {
								Liferay.Chat.VideoManager.mute();
							}
						}
					);

					instance._remoteVideoOuterNode.on(
						'click',
						function() {
							instance._toggleFullScreen();
						}
					);

					instance.setAvailableForChatVideo(Liferay.Chat.VideoManager.isUserAvailable(instance._panelId));
				}
			};

			A.extend(Chat.Conversation, Chat.ConversationPanel, {
				close: function() {
					var instance = this;

					Liferay.Chat.Panel.prototype.close.call(instance);
				},

				getWebRtc: function() {
					var instance = this;

					return instance._webRtc;
				},

				hideCtrlButtons: function() {
					var instance = this;

					instance._ctrlButtonsContainerNode.hide();
				},

				onDelete: function() {
					var instance = this;

					instance._videoCallTimer.reset();
					instance._status.destroy();

					if (instance.getWebRtc()) {
						instance.getWebRtc().onDelete();
					}
				},

				setAsRead: function() ­{
					var instance = this;

					Chat.Conversation.superclass.setAsRead.apply(instance, arguments);
				},

				setAsUnread: function() {
					var instance = this;

					Chat.Conversation.superclass.setAsUnread.apply(instance, arguments);
				},

				setAvailableForChatVideo: function(available) {
					var instance = this;

					instance._availableForChatVideo = available;

					if (instance._webRtc) {
						if (available) {
							instance.showCtrlButtons();
						}
						else {
							instance.hideCtrlButtons();
						}
					}
				},

				showCtrlButtons: function() {
					var instance = this;

					// Prevent showing control buttons if myself is not available for chat video
					if (Liferay.Chat.VideoManager.isAvailable()) {
						instance._ctrlButtonsContainerNode.show();
					}
				},

				_waitForRemoteStreamFlowing: function() {
					var instance = this;

					if (instance._webRtc.getState() === Liferay.Chat.WebRtcConversation.State.CONNECTED) {
						// Wait for the remote stream to "flow"
						if (!instance._webRtc.isRemoteStreamFlowing()) {
							setTimeout(
								function() {
									instance._waitForRemoteStreamFlowing();
								},
								250
							);
						}
						else {
							instance._onRemoteStreamFlowing();
						}
					}
				},

				_enableVideoFullScreen: function() {
					var instance = this;

					// Only allow this if we're connected
					if (instance._webRtc.getState() === Liferay.Chat.WebRtcConversation.State.CONNECTED) {
						Liferay.Chat.VideoManager.setOverlayVideoCallTime(instance._videoCallTimeStr);

						Liferay.Chat.VideoManager.appendNodeToOverlay(instance._remoteVideoOuterNode);
						instance._localVideoNode.appendTo(instance._remoteVideoOuterNode);
						Liferay.Chat.VideoManager.appendNodeToOverlay(instance._ctrlButtonsNode);
						instance._chatVideoCtrlButtonsNodes['fullScreen'].removeClass('off');
						instance._chatVideoCtrlButtonsNodes['fullScreen'].addClass('on');
						Liferay.Chat.VideoManager.showOverlay();
						instance._playVideos();
					}
				},

				_disableVideoFullScreen: function() {
					var instance = this;

					Liferay.Chat.VideoManager.hideOverlay();
					instance._remoteVideoOuterNode.appendTo(instance._remoteVideoContainerNode);
					instance._localVideoNode.appendTo(instance._selfViewNode);
					instance._ctrlButtonsNode.appendTo(instance._ctrlButtonsContainerNode);
					instance._chatVideoCtrlButtonsNodes['fullScreen'].removeClass('on');
					instance._chatVideoCtrlButtonsNodes['fullScreen'].addClass('off');
					instance._playVideos();
				},

				_flickerCtrlButtonsBackground: function() {
					var instance = this;

					/* Some weird rendering bug is happening in Chromium/Chrome. Even if the
					 * cache is completely disabled, the full screen and mute controls do not
					 * show when supposed to. Those tested actions can show them back:
					 *
					 *   * minimizing/maximizing the panel
					 *   * going to another tab and coming back
					 *   * hovering where they are supposed to be
					 *   * going full screen (clicking on the remote video element) and going
					 *     back to normal
					 *
					 * It seems like a rendering bug in the browser itself that's somehow
					 * related to the context.
					 *
					 * The (really ugly) solution is to modify the background color of the
					 * controls container and then, after a few ms, reset it to its original
					 * value. The user doesn't see this flicker; it's too fast. However, it
					 * seems like the browser, when having to render again the controls images
					 * over the new background color, will do properly this time.
					 *
					 * This bug does not exist on Firefox.
					 */
					var origBackgroundColor = instance._ctrlButtonsContainerNode.getStyle('backgroundColor');

					instance._ctrlButtonsContainerNode.setStyle('backgroundColor', '#20272c');

					setTimeout(
						function() {
							instance._ctrlButtonsContainerNode.setStyle('backgroundColor', origBackgroundColor);
						},
						2
					);
				},

				_hideCtrlButton: function(btnId) {
					var instance = this;

					instance._chatVideoCtrlButtonsNodes[btnId].hide();
					instance._flickerCtrlButtonsBackground();
				},

				_hideLocalVideo: function() {
					var instance = this;

					instance._localVideoNode.hide();
					instance._selfViewImgNode.show();
				},

				_hideRemoteVideo: function() {
					var instance = this;

					var remoteVideoAnim = new A.Anim(
						{
							node: instance._remoteVideoContainerNode,
							to: {
								height: 0
							},
							duration: 0.8,
							easing: A.Easing.easeOutStrong
						}
					);

					remoteVideoAnim.on(
						'end',
						function(event) {
							instance._remoteVideoContainerNode.hide();
						}
					);

					remoteVideoAnim.run();
				},

				_onRemoteStreamFlowing: function() {
					var instance = this;

					instance._hideCtrlButton('accept');
					instance._hideCtrlButton('call');
					instance._showCtrlButton('fullScreen');
					instance._showCtrlButton('hangUp');
					instance._showCtrlButton('mike');

					instance._disableVideoFullScreen();
					instance._showRemoteVideo();

					instance._videoCallTimer.start();
				},

				_onVideoCallTimeChange: function(timeStr) {
					var instance = this;

					instance._videoCallTimeStr = timeStr;

					instance._status.setInCallMessage(timeStr);
					Liferay.Chat.VideoManager.setOverlayVideoCallTime(timeStr);
				},

				_playVideos: function() {
					var instance = this;

					instance._remoteVideoNode.getDOM().play();
					instance._localVideoNode.getDOM().play();
				},

				_setPanelHTML: function() {
					var instance = this;

					var userImagePath = Liferay.Chat.Util.getUserImagePath(instance._panelIcon);

					// Custom HTML with integrated chat video elements
					var html =
						'<li class="user user_' + instance._panelId + '" panelId="' + instance._panelId + '">' +
							'<div class="panel-trigger">' +
								'<span class="trigger-name"></span>' +
								'<div class="typing-status"></div>' +
							'</div>' +
							'<div class="chat-panel">' +
								'<div class="remote-video-container hide">' +
									'<div class="remote-video">' +
										'<video autoplay="autoplay" class="remote"></video>' +
									'</div>' +
								'</div>' +
								'<div class="panel-window">' +
									'<div class="panel-button minimize"></div>' +
									'<div class="panel-button close"></div>' +
									'<div class="panel-self-view">' +
										'<img alt="" src="' + userImagePath + '" />' +
										'<video autoplay="autoplay" class="local hide" muted="muted"></video>' +
									'</div>' +
									'<div class="panel-title">' + Liferay.Util.escapeHTML(instance._panelTitle) + '</div>' +
									'<div class="panel-profile">...</div>' +
									'<div class="hide chat-video-ctrl">' +
										'<div class="hide chat-video-msg">' +
											'<div class="msg"></div>' +
											'<div class="working"></div>' +
										'</div>' +
										'<div class="chat-video-ctrl-buttons">' +
											'<a class="accept hide" href="javascript:void(0);" title="Accept video call"></a>' +
											'<a class="hangup hide" href="javascript:void(0);" title="Hang up video call"></a>' +
											'<a class="call hide" href="javascript:void(0);" title="Start video call"></a>' +
											'<a class="hide mike unmuted" href="javascript:void(0);" title="Mute/unmute microphone"></a>' +
											'<a class="fullscreen hide off" href="javascript:void(0);" title="Enable/disable fullscreen video call"></a>' +
											'<div style="clear: both;"></div>' +
										'</div>' +
									'</div>' +
									'<div class="panel-output"></div>' +
									'<div class="panel-input">' +
										'<textarea class="message-input"></textarea>' +
									'</div>' +
								'</div>' +
							'</div>' +
						'</li>';

					return html;
				},

				_showCtrlButton: function(btnId) {
					var instance = this;

					instance._chatVideoCtrlButtonsNodes[btnId].show();
					instance._flickerCtrlButtonsBackground();
				},

				_showLocalVideo: function() {
					var instance = this;

					instance._selfViewImgNode.hide();
					instance._localVideoNode.show();
					instance._localVideoNode.getDOM().play();
				},

				_showRemoteVideo: function() {
					var instance = this;

					instance._remoteVideoContainerNode.setStyle('height', '0');
					instance._remoteVideoContainerNode.show();

					var remoteVideoAnim = new A.Anim(
						{
							node: instance._remoteVideoContainerNode,
							to: {
								height: 165
							},
							duration: 0.8,
							easing: A.Easing.easeOutStrong
						}
					);

					remoteVideoAnim.run();
				},

				_toggleFullScreen: function() {
					var instance = this;

					if (instance._remoteVideoOuterNode.ancestor().attr('id') === 'chatVideoOverlay') {
						instance._disableVideoFullScreen();
					}
					else {
						instance._enableVideoFullScreen();
					}
				},

				updateChatVideoMikeButton: function(newClass) {
					var instance = this;
					var node = instance._chatVideoCtrlButtonsNodes['mike'];

					node.removeClass('muted');
					node.removeClass('unmuted');
					node.addClass(newClass);
				}
			});
		});
	}
);
