AUI().use(
	'aui-base',
	function(A) {
		Liferay.namespace('Chat');

		var UPDATE_PRESENCE_PERIOD_MS = 10000;

		Liferay.Chat.WebRtcManager = {
			State: {
				ACQUIRED: 'acquired',
				ASKED: 'asked',
				INIT: 'init'
			},

			debugIO: function(msgType, details, dir) {
				var msg = null;

				if (dir === 'i') {
					msg += '[S -> C]';
				}
				else if (dir === 'o') {
					msg += '[C -> S]';
				}

				if (msg) {
					msg += ' (' + msgType + ') ' + details;
					Liferay.Chat.WebRtcManager.debugMsg(msg);
				}
			},

			debugJsonIO: function(msgType, msgObj, dir) {
				Liferay.Chat.WebRtcManager.debugIO(msgType, JSON.stringify(msgObj), dir);
			},

			debugMsg: function(msg) {
				console.debug('WebRTC: ' + msg);
			},

			debugObj: function(obj) {
				console.debug(obj);
			},

			errorMsg: function(msg) {
				console.error('WebRTC: ' + msg);
			},

			getConversationsGlobalState: function() {
				var instance = this;

				var State = Liferay.Chat.WebRtcConversation.State;
				var ret = {
					active: false,
					communicationRequired: false,
					inRinging: false,
					outRinging: false,
					waiting: false
				};

				for (userId in instance._conversations) {
					var conversation = instance._conversations[userId];
					var conversationState = conversation.getState();

					if (conversationState !== State.STOPPED &&
						   conversationState !== State.DELETED &&
						   conversationState !== State.STOPPING &&
						   conversationState !== State.DELETING) {
						ret.active = true;

						if (conversationState !== State.CONNECTED) {
							ret.communicationRequired = true;
						}
					}

					if (conversationState === State.STOPPING ||
						   conversationState === State.DELETING) {
						ret.communicationRequired = true;
					}

					if (conversationState === State.CALLING ||
						   conversationState === State.CALLED) {
						ret.outRinging = true;
					}

					if (conversationState === State.GOTCALL ||
						   conversationState === State.GOTCALLWAITING) {
						ret.inRinging = true;
					}

					if (conversationState === State.CALLINGWAITING ||
						   conversationState === State.GOTCALLWAITING) {
						ret.waiting = true;
					}
				}

				return ret;
			},

			getLocalStream: function() {
				var instance = Liferay.Chat.WebRtcManager;

				return instance._localStream;
			},

			getWebRtcAdapter: function() {
				return Liferay.Chat.WebRtcManager._webRtcAdapter;
			},

			getState: function() {
				var instance = Liferay.Chat.WebRtcManager;

				return instance._currentState;
			},

			init: function(config) {
				var instance = Liferay.Chat.WebRtcManager;

				instance.debugMsg('initializing the WebRTC manager');

				instance._webRtcAdapter = Liferay.Chat.WebRtcAdapter.getWebRtcAdapter();
				if (instance._webRtcAdapter) {
					instance.debugMsg('WebRTC seems supported!');

					A.mix(instance._cb, config.cb, true);

					instance._currentState = Liferay.Chat.WebRtcManager.State.INIT;

					instance.sendResetMsg();

					instance._updatePresenceTimerId = setInterval(
						function() {
							instance._updatePresence();
						},
						UPDATE_PRESENCE_PERIOD_MS
					);
				}
				else {
					instance.debugMsg('WebRTC is not supported');
				}
			},

			isSupported: function() {
				var instance = Liferay.Chat.WebRtcManager;

				if (instance._webRtcAdapter === null) {
					instance._webRtcAdapter = Liferay.Chat.WebRtcAdapter.getWebRtcAdapter();
				}

				return instance.getWebRtcAdapter() !== null;
			},

			isUserAvailable: function(userId) {
				var instance = Liferay.Chat.WebRtcManager;

				return instance._cb.isUserAvailable(userId);
			},

			muteMike: function() {
				var instance = Liferay.Chat.WebRtcManager;

				Liferay.Chat.WebRtcManager.debugMsg('muting microphone');

				var localStream = instance.getLocalStream();
				if (localStream) {
					instance.getWebRtcAdapter().muteStreamAudio(localStream);
				}
			},

			onConversationStateChange: function() {
				var instance = Liferay.Chat.WebRtcManager;

				var globalState = instance.getConversationsGlobalState();

				if (!globalState.active) {
					instance._disableMedia(true);
				}

				if (globalState.waiting) {
					instance._enableMedia();
				}

				if (globalState.inRinging) {
					instance._cb.enableInRinging();
				}
				else {
					instance._cb.disableInRinging();
				}

				if (globalState.outRinging) {
					instance._cb.enableOutRinging();
				}
				else {
					instance._cb.disableOutRinging();
				}
			},

			processWebRtcMails: function(mails) {
				var instance = Liferay.Chat.WebRtcManager;

				if (mails.length > 0) {
					instance.debugMsg('received ' + mails.length + ' mail' + (mails.length > 1 ? 's' : '') + ':');
					instance.debugObj(mails);
				}

				for (var i = 0; i < mails.length; ++i) {
					var mail = mails[i];
					var msg = mail.message;

					var ensurePanel = !(mail.type === 'conn' && msg.type === 'status');
					if (ensurePanel) {
						instance.debugMsg('asking host to ensure panel for user ID ' + mail.sourceUserId);
						var webRtcConversation = instance._cb.ensurePanel(mail.sourceUserId);
					}

					var webRtcConversation = instance._conversations[mail.sourceUserId];
					if (!webRtcConversation) {
						instance.errorMsg('got message for user ' + mail.sourceUserId + ', but conversation not registered');
						continue;
					}

					Liferay.Chat.WebRtcManager.debugJsonIO(mail.type, msg, 'i');

					switch (mail.type) {
						case 'err':
							webRtcConversation.onMsgError(msg);
							break;

						case 'conn':
							webRtcConversation._cb.onWebRtcEvent();
							switch (msg.type) {
								case 'call':
									webRtcConversation.onMsgGotCall();
									break;

								case 'answer':
									webRtcConversation.onMsgGotAnswer(msg);
									break;

								case 'status':
									webRtcConversation.onMsgGotStatus(msg);
									break;

								default:
									instance.errorMsg('got "conn" message, but unknown connection message type "' + msg.type + '"');
							}
							break;

						case 'ice':
							webRtcConversation.onMsgNewIceCandidate(msg);
							break;

						case 'sdp':
							webRtcConversation.onMsgNewSdp(msg);
							break;

						default:
							instance.errorMsg('got message, but unknown message type "' + mail.type + '"');
					}
				}
			},

			registerConversation: function(conversation) {
				var instance = Liferay.Chat.WebRtcManager;

				instance._conversations[conversation.getToUserId()] = conversation;
				instance.debugMsg('registering conversation ID ' + conversation.getToUserId());
			},

			sendMsg: function(msgType, payload) {
				var instance = Liferay.Chat.WebRtcManager;

				payload.type = msgType;
				instance._cb.send(payload);
				Liferay.Chat.WebRtcManager.debugJsonIO(msgType, payload, 'o');
			},

			sendResetMsg: function() {
				var instance = Liferay.Chat.WebRtcManager;

				instance.sendMsg('reset', {});
			},

			sendSetAvailabilityMsg: function(available) {
				var instance = Liferay.Chat.WebRtcManager;

				var msgType = 'setAvailability';
				var msg = {
					available: available
				};

				instance.sendMsg(msgType, msg);
			},

			sendUpdatePresenceMsg: function() {
				var instance = Liferay.Chat.WebRtcManager;

				var msgType = 'updatePresence';

				instance.sendMsg(msgType, {});
			},

			setState: function(state) {
				var instance = Liferay.Chat.WebRtcManager;

				instance._currentState = state;
			},

			unmuteMike: function() {
				var instance = Liferay.Chat.WebRtcManager;

				instance.debugMsg('unmuting microphone');

				var localStream = instance.getLocalStream();
				if (localStream) {
					instance.getWebRtcAdapter().unmuteStreamAudio(localStream);
				}
			},

			_disableMedia: function(notify) {
				var instance = Liferay.Chat.WebRtcManager;

				if (instance.getState() === instance.State.ACQUIRED) {
					instance.setState(instance.State.INIT);
					instance._stopLocalStream();
					if (notify) {
						instance._cb.onMediaDisabled();
					}
				}
			},

			_enableMedia: function() {
				var instance = Liferay.Chat.WebRtcManager;

				if (instance.getState() === instance.State.INIT) {
					instance.setState(instance.State.ASKED);
					instance.getWebRtcAdapter().getUserMedia(instance.getWebRtcAdapter().getUserMediaConstraints,
						function(stream) {
							instance.debugMsg('user media acquired');

							instance._setLocalStream(stream);
							instance.setState(instance.State.ACQUIRED);

							var globalState = instance.getConversationsGlobalState();
							if (!globalState.active) {
								instance._disableMedia(false);
							}
							else {
								instance._cb.onMediaEnabled();
								instance._updateWaitingConversationsNextState();
							}
						},
						function(event) {
							instance.errorMsg('error while trying to acquired user media');

							instance.setState(instance.State.INIT);
							instance._updateWaitingConversationsCancel();
						}
					);
				}
				else if (instance.getState() === instance.State.ACQUIRED) {
					instance._updateWaitingConversationsNextState();
				}
				else {
					instance.debugMsg('local stream in progress of being acquired');
				}
			},

			_setAudioTracksEnabled: function(en) {
				var instance = this;

				if (instance.getState() === instance.State.ACQUIRED && instance.getLocalStream()) {
					for (i in instance.getLocalStream().getAudioTracks()) {
						var track = instance.getLocalStream().getAudioTracks()[i];
						track.enabled = en;
					}
				}
			},

			_setLocalStream: function(stream) {
				var instance = Liferay.Chat.WebRtcManager;

				instance._localStream = stream;
			},

			_stopLocalStream: function() {
				var instance = this;

				if (instance._localStream) {
					/* This is how we dispose of a local stream: we stop
					 * it and then set it to null so that there's no more
					 * reference to it.
					 */
					instance.debugMsg('stopping local stream');
					instance._localStream.stop();
					instance._localStream = null;
				}
			},

			_updatePresence: function() {
				var instance = Liferay.Chat.WebRtcManager;

				instance.sendUpdatePresenceMsg();
			},

			_updateWaitingConversationsCancel: function() {
				var instance = Liferay.Chat.WebRtcManager;

				for (var i in instance._conversations) {
					var conversation = instance._conversations[i];
					var conversationState = conversation.getState();

					conversation.onError(Liferay.Chat.WebRtcConversation.Error.CANNOTGETUSERMEDIA);

					if (conversationState === Liferay.Chat.WebRtcConversation.State.GOTCALLWAITING) {
						conversation.setState(Liferay.Chat.WebRtcConversation.State.DENYINGCALL);
					}
					else if (conversationState === Liferay.Chat.WebRtcConversation.State.CALLINGWAITING) {
						conversation.setState(Liferay.Chat.WebRtcConversation.State.STOPPED);
					}
				}
			},

			_updateWaitingConversationsNextState: function() {
				var instance = Liferay.Chat.WebRtcManager;

				for (var i in instance._conversations) {
					var conversation = instance._conversations[i];
					var conversationState = conversation.getState();

					if (conversationState === Liferay.Chat.WebRtcConversation.State.GOTCALLWAITING) {
						conversation.setState(Liferay.Chat.WebRtcConversation.State.GOTCALL);
					}

					if (conversationState === Liferay.Chat.WebRtcConversation.State.CALLINGWAITING) {
						conversation.setState(Liferay.Chat.WebRtcConversation.State.CALLING);
					}
				}
			},

			_cb: {},
			_conversations: [],
			_currentState: null,
			_localStream: null,
			_updatePresenceTimerId: null,
			_webRtcAdapter: null
		};

		Liferay.Chat.WebRtcConversation = function(config) {
			var instance = this;

			instance._userId = config.userId;
			Liferay.Chat.WebRtcManager.debugMsg('creating new WebRTC conversation (ID ' + instance._userId + ')');

			instance._cb = {};
			A.mix(instance._cb, config.cb, true);

			instance._remoteVideoEl = config.remoteVideoEl;
			instance._localVideoEl = config.localVideoEl;

			instance._lastError = Liferay.Chat.WebRtcConversation.Error.NOERROR;

			instance._iceServers = [];
			for (var i in config.iceServers) {
				var ice = config.iceServers[i];
				var compatIce = Liferay.Chat.WebRtcManager.getWebRtcAdapter().createIceServer(ice);

				if (compatIce !== null) {
					instance._iceServers.push(compatIce);
				}
			}

			Liferay.Chat.WebRtcManager.registerConversation(instance);
			instance.setState(Liferay.Chat.WebRtcConversation.State.STOPPED);
		};

		Liferay.Chat.WebRtcConversation.State = {
			ACCEPTINGCALL: 'acceptingCall',
			ANSWERED: 'answered',
			CALLED: 'called',
			CALLINGWAITING: 'callingWaiting',
			CALLING: 'calling',
			CONNECTED: 'connected',
			DELETED: 'deleted',
			DELETING: 'deleting',
			DENYINGCALL: 'denyingCall',
			GOTANSWER: 'gotAnswer',
			GOTCALL: 'gotCall',
			GOTCALLWAITING: 'gotCallWaiting',
			STOPPED: 'stopped',
			STOPPING: 'stopping'
		};

		Liferay.Chat.WebRtcConversation.Error = {
			CANNOTGETUSERMEDIA: 'cannotGetUserMedia',
			HANGUP: 'hangUp',
			REMOTEPEERDENIEDCALL: 'remotePeerDeniedCall',
			REMOTEPEERNOTAVAILABLE: 'remotePeerNotAvailable',
			REMOTEPEERRESET: 'remotePeerReset'
		};

		var State = Liferay.Chat.WebRtcConversation.State;
		var Error = Liferay.Chat.WebRtcConversation.Error;

		Liferay.Chat.WebRtcConversation.prototype = {
			getState: function() {
				var instance = this;

				return instance._currentState;
			},

			getToUserId: function() {
				var instance = this;

				return instance._userId;
			},

			isRemoteStreamFlowing: function() {
				var instance = this;
				var remoteVideo = instance._remoteVideoEl;
				var flowing = !(remoteVideo.readyState <= HTMLMediaElement.HAVE_CURRENT_DATA ||
						remoteVideo.paused || remoteVideo.currentTime <= 0);

				return flowing;
			},

			onDelete: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: conversation deleted');

				switch (instance.getState()) {
					case State.CALLED:
					case State.ACCEPTINGCALL:
					case State.ANSWERED:
					case State.GOTANSWER:
					case State.CONNECTED:
						instance.setState(Liferay.Chat.WebRtcConversation.State.DELETING);
						instance._sendHangUpMsg();
						break;

					case State.STOPPING:
						instance.setState(Liferay.Chat.WebRtcConversation.State.DELETING);
						break;

					case State.GOTCALL:
					case State.GOTCALLWAITING:
					case State.DENYINGCALL:
						instance.setState(Liferay.Chat.WebRtcConversation.State.DELETED);
						instance._sendAnswerMsg(false);
						break;

					default:
						instance.setState(Liferay.Chat.WebRtcConversation.State.DELETED);
						break;
				}
			},

			onError: function(error) {
				var instance = this;

				instance._cb.onError(error);
			},

			onMsgError: function(msg) {
				var instance = this;

				instance._cb.onWebRtcEvent();
				switch (msg.id) {
					case 'existingConnection':
						break;

					case 'unavailableUser':
					case 'invalidState':
					case 'cannotAnswer':
						Liferay.Chat.WebRtcManager.errorMsg('error from server: "' + msg.id + '"');
						instance.setState(State.STOPPED);
						break;
				}
			},

			onMsgGotAnswer: function(msg) {
				var instance = this;

				if (instance.getState() === State.CALLED) {
					if (msg.answer) {
						instance.setState(State.GOTANSWER);
					}
					else {
						instance.onError(Error.REMOTEPEERDENIEDCALL);
						instance.setState(State.STOPPED);
					}
				}
			},

			onMsgGotCall: function() {
				var instance = this;

				var wrongState = false;

				switch (instance.getState()) {
					case State.STOPPED:
					case State.STOPPING:
					case State.DELETED:
					case State.DELETING:
						break;

					case State.CALLED:
					case State.CALLING:
						instance.setState(State.GOTCALL);
						break;

					case State.CALLINGWAITING:
						instance.setState(State.GOTCALLWAITING);
						break;

					default:
						// Error: wrong state for an incoming call, so deny it
						instance.setState(State.DENYINGCALL);
						wrongState = true;
				}

				if (!wrongState) {
					instance.setState(State.GOTCALLWAITING);
				}
			},

			onMsgGotStatus: function(msg) {
				var instance = this;

				switch (msg.status) {
					case 'lost':
						instance._onLostConnection(msg.reason);
						break;

					default:
						// Unknown connection status
				}
			},

			onMsgNewIceCandidate: function(msg) {
				var instance = this;

				if (instance._isWebRtcStarted()) {
					var iceCandidate = JSON.parse(msg.candidate);

					RTCIceCandidate = Liferay.Chat.WebRtcManager.getWebRtcAdapter().RTCIceCandidate;
					var rtcIce = new RTCIceCandidate(
						{
							sdpMLineIndex: iceCandidate.sdpMLineIndex,
							candidate: iceCandidate.candidate
						}
					);

					if (!instance._pc || !instance._acceptIceCandidates) {
						instance._iceCandidatesBuffer.push(rtcIce);
					}
					else {
						instance._addIceCandidate(rtcIce);
					}
				}
			},

			onMsgNewSdp: function(msg) {
				var instance = this;

				var description = JSON.parse(msg.description);

				if (instance._isWebRtcStarted()) {
					if (instance._caller) {
						instance._webRtcCompleteOffer(description);
					}
					else {
						instance._webRtcAnswer(description);
					}
				}
			},

			onPressAccept: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: user pressed "accept"');

				if (instance.getState() === State.GOTCALL || instance.getState() === State.GOTCALLWAITING) {
					if (!instance._isUserAvailable()) {
						instance.onError(Error.REMOTEPEERNOTAVAILABLE);
						instance.setState(State.DENYINGCALL);
						Liferay.Chat.WebRtcManager.errorMsg('remote peer not available for WebRTC to "accept"');
					}
					else {
						instance.setState(State.ACCEPTINGCALL);
					}
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('wrong state "' + instance.getState() + ' to "accept"');
				}
			},

			onPressCall: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: user pressed "call"');

				if (instance.getState() === State.STOPPED) {
					if (!instance._isUserAvailable()) {
						Liferay.Chat.WebRtcManager.errorMsg('remote peer not available for WebRTC to "call"');
					}

					instance.setState(State.CALLINGWAITING);
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('wrong state "' + instance.getState() + ' to "call"');
				}
			},

			onPressHangUp: function() {
				var instance = this;

				var State = Liferay.Chat.WebRtcConversation.State;

				Liferay.Chat.WebRtcManager.debugMsg('event: user pressed "hang up"');

				switch (instance.getState()) {
					case State.GOTCALL:
					case State.GOTCALLWAITING:
						instance.setState(State.DENYINGCALL);
						break;

					case State.CALLED:
					case State.CALLING:
					case State.ANSWERED:
					case State.ACCEPTINGCALL:
					case State.DENYINGCALL:
					case State.GOTANSWER:
					case State.CONNECTED:
						instance._sendHangUpMsg();
						instance.setState(State.STOPPING);
						break;

					case State.CALLINGWAITING:
						instance.setState(State.STOPPED);
						break;

					case State.STOPPING:
					case State.STOPPED:
					case State.DELETING:
					case State.DELETED:
						break;
				}
			},

			setState: function(state) {
				var instance = this;

				if (A.Lang.isUndefined(instance.getState())) {
					Liferay.Chat.WebRtcManager.debugMsg('{' + state + '}');
				}
				else {
					Liferay.Chat.WebRtcManager.debugMsg('{' + instance.getState() + ' -> ' + state + '}');
				}

				instance._currentState = state;

				instance._cb.onStateChange(state);
				Liferay.Chat.WebRtcManager.onConversationStateChange();
				instance._onStateChange();
			},

			_addIceCandidate: function(ice) {
				var instance = this;

				if (instance._acceptIceCandidates) {
					Liferay.Chat.WebRtcManager.debugMsg('adding remote ICE candidate to peer connection');
					instance._pc.addIceCandidate(ice);
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('cannot add following ICE candidate to peer connection:');
					Liferay.Chat.WebRtcManager.debugObj(ice);
				}
			},

			_configurePC: function() {
				var instance = this;

				var RTCPeerConnection = Liferay.Chat.WebRtcManager.getWebRtcAdapter().RTCPeerConnection;
				instance._pc = new RTCPeerConnection({
					iceServers: instance._iceServers
				}, Liferay.Chat.WebRtcManager.getWebRtcAdapter().peerConnectionConstraints);

				instance._pc.addStream(Liferay.Chat.WebRtcManager.getLocalStream());

				instance._pc.onicecandidate =
					function(event) {
						instance._onIceCandidate(event);
					};

				instance._pc.onaddstream =
					function(event) {
						instance._onAddStream(event);
					};
			},

			_doWebRtcCall: function() {
				var instance = this;

				if (instance._isWebRtcStarted()) {
					if (instance._caller) {
						instance._webRtcOffer();
					}
					else {
						Liferay.Chat.WebRtcManager.errorMsg('cannot call with WebRTC because we\'re not the original caller');
					}
				}
			},

			_flushIceCandidatesBuffer: function() {
				var instance = this;

				if (instance._isWebRtcStarted()) {
					Liferay.Chat.WebRtcManager.debugMsg('flushing ICE candidates buffer (length=' + instance._iceCandidatesBuffer.length + ')');

					A.Array.each(instance._iceCandidatesBuffer, instance._addIceCandidate, instance);

					instance._iceCandidatesBuffer.length = 0;
				}
			},

			_isUserAvailable: function() {
				var instance = this;

				var destUserId = instance.getToUserId();

				return Liferay.Chat.WebRtcManager.isUserAvailable(destUserId);
			},

			_isWebRtcStarted: function() {
				var instance = this;

				return instance.getState() === State.ANSWERED ||
					instance.getState() === State.GOTANSWER ||
					instance.getState() === State.CONNECTED;
			},

			_onAddStream: function(event) {
				var instance = this;

				if (instance._isWebRtcStarted() && event) {
					Liferay.Chat.WebRtcManager.debugMsg('added remote stream');

					instance._setRemoteVideoStream(event.stream);
					instance.setState(State.CONNECTED);
				}
			},

			_onIceCandidate: function(event) {
				var instance = this;

				if (instance._pc && event && event.candidate) {
					Liferay.Chat.WebRtcManager.debugMsg('new local ICE candidate ready');
					instance._sendIceMsg(event.candidate);
				}
			},

			_onLostConnection: function(reason) {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: lost logical connection with remote peer');
				
				if (instance._pc) {
					instance._pc.close();
					instance._pc = null;
				}

				if (reason === 'hangUp') {
					instance.onError(Error.HANGUP);
				}
				else if (reason === 'reset') {
					instance.onError(Error.REMOTEPEERRESET);   
				}

				switch (instance.getState()) {
					case Liferay.Chat.WebRtcConversation.State.DELETING:
						instance.setState(State.DELETED);
						break;

					default:
						instance.setState(State.STOPPED);
						break;
				}
			},

			_onStateAcceptingCall: function() {
				var instance = this;

				instance._sendAnswerMsg(true);
				instance.setState(State.ANSWERED);
			},

			_onStateCalled: function() {
				var instance = this;

				instance._caller = true;
				instance._setLocalVideoStream();
			},

			_onStateCalling: function() {
				var instance = this;

				instance._sendCallMsg();
				instance._caller = true;
				instance.setState(State.CALLED);
			},

			_onStateChange: function() {
				var instance = this;

				switch (instance.getState()) {
					case State.STOPPED:
						instance._onStateStopped();
						break;

					case State.DELETED:
						instance._onStateDeleted();
						break;

					case State.CALLING:
						instance._onStateCalling();
						break;

					case State.CALLED:
						instance._onStateCalled();
						break;

					case State.GOTCALL:
						instance._onStateGotCall();
						break;

					case State.ACCEPTINGCALL:
						instance._onStateAcceptingCall();
						break;

					case State.DENYINGCALL:
						instance._onStateDenyingCall();
						break;

					case State.GOTANSWER:
						instance._onStateGotAnswer();
						break;

					case State.STOPPING:
						instance._onStateStopping();
						break;

					case State.DELETING:
						instance._onStateDeleting();
						break;
				}
			},

			_onStateDeleted: function() {
				var instance = this;
			},

			_onStateDeleting: function() {
				var instance = this;
			},

			_onStateDenyingCall: function() {
				var instance = this;

				instance._sendAnswerMsg(false);
				instance.setState(State.STOPPED);
			},

			_onStateGotAnswer: function() {
				var instance = this;

				instance._doWebRtcCall();
			},

			_onStateGotCall: function() {
				var instance = this;

				instance._caller = false;

				instance._setLocalVideoStream();
			},

			_onStateStopped: function() {
				var instance = this;

				instance._resetWebRtcState();
			},

			_onStateStopping: function() {
				var instance = this;
			},

			_resetWebRtcState: function() {
				var instance = this;

				instance._pc = null;
				instance._caller = false;
				instance._iceCandidatesBuffer = [];
				instance._acceptIceCandidates = false;
			},

			_sendAnswerMsg: function(accept) {
				var instance = this;

				var userId = instance.getToUserId();
				var msg = {
					destinationUserId: userId,
					answer: accept
				};

				Liferay.Chat.WebRtcManager.sendMsg('answer', msg);
			},

			_sendCallMsg: function() {
				var instance = this;

				var userId = instance.getToUserId();
				var msg = {
					destinationUserId: userId
				};

				Liferay.Chat.WebRtcManager.sendMsg('call', msg);
			},

			_sendHangUpMsg: function() {
				var instance = this;

				var userId = instance.getToUserId();
				var msg = {
					destinationUserId: userId
				};

				Liferay.Chat.WebRtcManager.sendMsg('hangUp', msg);
			},

			_sendIceMsg: function(ice) {
				var instance = this;

				var jsonIce = JSON.stringify(ice);
				var msg = {
					destinationUserId: instance.getToUserId(),
					candidate: jsonIce
				};

				Liferay.Chat.WebRtcManager.sendMsg('ice', msg);
			},

			_sendSdpMsg: function(desc) {
				var instance = this;

				var jsonDesc = JSON.stringify(desc);
				var msg = {
					destinationUserId: instance.getToUserId(),
					description: jsonDesc
				};

				Liferay.Chat.WebRtcManager.sendMsg('sdp', msg);
			},

			_setLocalVideoStream: function() {
				var instance = this;
				var localStream = Liferay.Chat.WebRtcManager.getLocalStream();

				if (localStream) {
					instance._setVideoStream(instance._localVideoEl, localStream);
				}
			},

			_setRemoteVideoStream: function(stream) {
				var instance = this;

				instance._setVideoStream(instance._remoteVideoEl, stream);
			},

			_setVideoStream: function(el, stream) {
				var instance = this;

				Liferay.Chat.WebRtcManager.getWebRtcAdapter().attachMediaStream(el, stream);
			},

			_webRtcAnswer: function(desc) {
				var instance = this;

				instance._configurePC();

				if (instance._pc) {
					var RTCSessionDescription = Liferay.Chat.WebRtcManager.getWebRtcAdapter().RTCSessionDescription;
					var remoteDesc = new RTCSessionDescription(desc);
					instance._pc.setRemoteDescription(remoteDesc);
					instance._pc.createAnswer(
						function(desc) {
							desc.sdp = Liferay.Chat.WebRtcAdapter.preferOpus(desc.sdp);
							Liferay.Chat.WebRtcManager.debugMsg('generated SDP answer');
							instance._pc.setLocalDescription(desc);
							instance._sendSdpMsg(desc);
							instance._acceptIceCandidates = true;
							instance._flushIceCandidatesBuffer();
						},
						function(error) {
							Liferay.Chat.WebRtcManager.errorMsg(error.message);
						},
						{
							mandatory: {
								OfferToReceiveAudio: true,
								OfferToReceiveVideo: true
							}
						}
					);
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('when trying to create WebRTC answer: no peer connection available');
				}
			},

			_webRtcCompleteOffer: function(desc) {
				var instance = this;

				if (instance._pc) {
					instance._flushIceCandidatesBuffer();

					var RTCSessionDescription = Liferay.Chat.WebRtcManager.getWebRtcAdapter().RTCSessionDescription;
					instance._pc.setRemoteDescription(new RTCSessionDescription(desc));
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('when trying to complete WebRTC offer: no peer connection available');
				}
			},

			_webRtcOffer: function() {
				var instance = this;

				instance._configurePC();

				if (instance._pc) {
					instance._pc.createOffer(
						function(desc) {
							desc.sdp = Liferay.Chat.WebRtcAdapter.preferOpus(desc.sdp);
							Liferay.Chat.WebRtcManager.debugMsg('generated SDP offer');
							instance._pc.setLocalDescription(desc);
							instance._sendSdpMsg(desc);
							instance._acceptIceCandidates = true;
						},
						function(error) {
							Liferay.Chat.WebRtcManager.errorMsg(error.message);
						},
						{
							mandatory: {
								OfferToReceiveAudio: true,
								OfferToReceiveVideo: true
							},
							optional: []
						}
					);
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('when trying to create WebRTC offer: no peer connection available');
				}
			}
		};
	}
);
