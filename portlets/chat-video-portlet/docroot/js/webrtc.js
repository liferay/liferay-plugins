AUI().use(
	'aui-base',
	'json',
	function(A) {
		Liferay.namespace('Chat');

		var UPDATE_PRESENCE_PERIOD_MS = 10000;

		Liferay.Chat.WebRtcManager = {
			State: {
				ACQUIRED: 'acquired',
				ASKED: 'asked',
				INIT: 'init'
			},

			init: function(config) {
				var instance = this;

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

			debugIO: function(msgType, details, dir) {
				var msg = null;

				if (dir === 'i') {
					msg = '[S -> C]';
				}
				else if (dir === 'o') {
					msg = '[C -> S]';
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
				// console.debug('WebRTC: ' + msg);
			},

			debugObj: function(obj) {
				// console.debug(obj);
			},

			errorMsg: function(msg) {
				// console.error('WebRTC: ' + msg);
			},

			getConversationsGlobalState: function() {
				var instance = this;

				var ret = {
					active: false,
					communicationRequired: false,
					inRinging: false,
					outRinging: false,
					waiting: false
				};

				var state = Liferay.Chat.WebRtcConversation.State;

				var conversations = instance._conversations;

				var deleting = state.DELETING;
				var gotCallWaiting = state.GOTCALLWAITING;
				var stopping = state.STOPPING;

				for (var userId in conversations) {
					var conversation = conversations[userId];

					var conversationState = conversation.getState();

					if (conversationState !== state.STOPPED && conversationState !== state.DELETED && conversationState !== stopping && conversationState !== deleting) {
						ret.active = true;

						if (conversationState !== state.CONNECTED) {
							ret.communicationRequired = true;
						}
					}

					if (conversationState === stopping || conversationState === deleting) {
						ret.communicationRequired = true;
					}

					if (conversationState === state.CALLING || conversationState === state.CALLED) {

						ret.outRinging = true;
					}

					if (conversationState === state.GOTCALL || conversationState === gotCallWaiting) {
						ret.inRinging = true;
					}

					if (conversationState === state.CALLINGWAITING || conversationState === gotCallWaiting) {
						ret.waiting = true;
					}
				}

				return ret;
			},

			getLocalStream: function() {
				var instance = this;

				return instance._localStream;
			},

			getState: function() {
				var instance = this;

				return instance._currentState;
			},

			getWebRtcAdapter: function() {
				return Liferay.Chat.WebRtcManager._webRtcAdapter;
			},

			isSupported: function() {
				var instance = this;

				if (instance._webRtcAdapter === null) {
					instance._webRtcAdapter = Liferay.Chat.WebRtcAdapter.getWebRtcAdapter();
				}

				return instance.getWebRtcAdapter() !== null;
			},

			isUserAvailable: function(userId) {
				var instance = this;

				return instance._cb.isUserAvailable(userId);
			},

			muteMike: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('muting microphone');

				var localStream = instance.getLocalStream();

				if (localStream) {
					instance.getWebRtcAdapter().muteStreamAudio(localStream);
				}
			},

			onConversationStateChange: function() {
				var instance = this;

				var globalState = instance.getConversationsGlobalState();

				if (!globalState.active) {
					instance._disableMedia(true);
				}

				if (globalState.waiting) {
					instance._enableMedia();
				}

				var cb = instance._cb;

				if (globalState.inRinging) {
					cb.enableInRinging();
				}
				else {
					cb.disableInRinging();
				}

				if (globalState.outRinging) {
					cb.enableOutRinging();
				}
				else {
					cb.disableOutRinging();
				}
			},

			processWebRtcMails: function(mails) {
				var instance = this;

				var mailsLength = mails.length;

				if (mailsLength > 0) {
					instance.debugMsg('received ' + mailsLength + ' mail' + (mailsLength > 1 ? 's' : '') + ':');

					instance.debugObj(mails);
				}

				for (var i = 0; i < mailsLength; ++i) {
					var mail = mails[i];

					var mailType = mail.type;
					var mailUserId = mail.sourceUserId;
					var msg = mail.message;

					var msgType = msg.type;

					var ensurePanel = !(mailType === 'conn' && msgType === 'status');

					if (ensurePanel) {
						instance.debugMsg('asking host to ensure panel for user ID ' + mailUserId);

						instance._cb.ensurePanel(mailUserId);
					}

					var webRtcConversation = instance._conversations[mailUserId];

					if (!webRtcConversation) {
						instance.errorMsg('got message for user ' + mailUserId + ', but conversation not registered');

						continue;
					}

					Liferay.Chat.WebRtcManager.debugJsonIO(mailType, msg, 'i');

					if (mailType === 'err') {
						webRtcConversation.onMsgError(msg);
					}
					else if (mailType === 'conn') {
						webRtcConversation._cb.onWebRtcEvent();

						if (msgType === 'call') {
							webRtcConversation.onMsgGotCall();
						}
						else if (msgType === 'answer') {
							webRtcConversation.onMsgGotAnswer(msg);
						}
						else if (msgType === 'status') {
							webRtcConversation.onMsgGotStatus(msg);
						}
						else {
							instance.errorMsg('got "conn" message, but unknown connection message type "' + msgType + '"');
						}
					}
					else if (mailType === 'ice') {
						webRtcConversation.onMsgNewIceCandidate(msg);
					}
					else if (mailType === 'sdp') {
						webRtcConversation.onMsgNewSdp(msg);
					}
					else {
						instance.errorMsg('got message, but unknown message type "' + mailType + '"');
					}
				}
			},

			registerConversation: function(conversation) {
				var instance = this;

				var conversationUserId = conversation.getToUserId();

				instance._conversations[conversationUserId] = conversation;

				instance.debugMsg('registering conversation ID ' + conversationUserId);
			},

			sendMsg: function(msgType, payload) {
				var instance = this;

				payload.type = msgType;

				instance._cb.send(payload);

				Liferay.Chat.WebRtcManager.debugJsonIO(msgType, payload, 'o');
			},

			sendResetMsg: function() {
				var instance = this;

				instance.sendMsg('reset', {});
			},

			sendSetAvailabilityMsg: function(available) {
				var instance = this;

				instance.sendMsg(
					'setAvailability',
					{
						available: available
					}
				);
			},

			sendUpdatePresenceMsg: function() {
				var instance = this;

				instance.sendMsg('updatePresence', {});
			},

			setState: function(state) {
				var instance = this;

				instance._currentState = state;
			},

			unmuteMike: function() {
				var instance = this;

				instance.debugMsg('unmuting microphone');

				var localStream = instance.getLocalStream();

				if (localStream) {
					instance.getWebRtcAdapter().unmuteStreamAudio(localStream);
				}
			},

			_disableMedia: function(notify) {
				var instance = this;

				var state = instance.State;

				if (instance.getState() === state.ACQUIRED) {
					instance.setState(state.INIT);

					instance._stopLocalStream();

					if (notify) {
						instance._cb.onMediaDisabled();
					}
				}
			},

			_enableMedia: function() {
				var instance = this;

				var state = instance.State;

				var currentState = instance.getState();

				if (currentState === state.INIT) {
					instance.setState(state.ASKED);

					instance.getWebRtcAdapter().getUserMedia(
						instance.getWebRtcAdapter().getUserMediaConstraints,
						function(stream) {
							instance.debugMsg('user media acquired');

							instance._setLocalStream(stream);
							instance.setState(state.ACQUIRED);

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

							instance.setState(state.INIT);

							instance._updateWaitingConversationsCancel();
						}
					);
				}
				else if (currentState === state.ACQUIRED) {
					instance._updateWaitingConversationsNextState();
				}
				else {
					instance.debugMsg('local stream in progress of being acquired');
				}
			},

			_setAudioTracksEnabled: function(en) {
				var instance = this;

				var localStream = instance.getLocalStream();

				if (instance.getState() === instance.State.ACQUIRED && localStream) {
					var audioTracks = localStream.getAudioTracks();

					for (var i in audioTracks) {
						var track = audioTracks[i];

						track.enabled = en;
					}
				}
			},

			_setLocalStream: function(stream) {
				var instance = this;

				instance._localStream = stream;
			},

			_stopLocalStream: function() {
				var instance = this;

				var localStream = instance._localStream;

				if (localStream) {
					/* This is how we dispose of a local stream: we stop
					 * it and then set it to null so that there's no more
					 * reference to it.
					 */

					instance.debugMsg('stopping local stream');

					localStream.stop();

					instance._localStream = null;
				}
			},

			_updatePresence: function() {
				var instance = this;

				instance.sendUpdatePresenceMsg();
			},

			_updateWaitingConversationsCancel: function() {
				var instance = this;

				var conversations = instance._conversations;

				var webRtcConversation = Liferay.Chat.WebRtcConversation;

				var state = webRtcConversation.State;

				for (var i in conversations) {
					var conversation = conversations[i];

					var conversationState = conversation.getState();

					conversation.onError(webRtcConversation.Error.CANNOTGETUSERMEDIA);

					if (conversationState === state.GOTCALLWAITING) {
						conversation.setState(state.DENYINGCALL);
					}
					else if (conversationState === state.CALLINGWAITING) {
						conversation.setState(state.STOPPED);
					}
				}
			},

			_updateWaitingConversationsNextState: function() {
				var instance = this;

				var conversations = instance._conversations;

				var state = Liferay.Chat.WebRtcConversation.State;

				for (var i in conversations) {
					var conversation = conversations[i];

					var conversationState = conversation.getState();

					if (conversationState === state.GOTCALLWAITING) {
						conversation.setState(state.GOTCALL);
					}

					if (conversationState === state.CALLINGWAITING) {
						conversation.setState(state.CALLING);
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

			instance._iceServers = [];

			var iceServers = config.iceServers;

			for (var i in iceServers) {
				var ice = iceServers[i];

				var compatIce = Liferay.Chat.WebRtcManager.getWebRtcAdapter().createIceServer(ice);

				if (compatIce !== null) {
					instance._iceServers.push(compatIce);
				}
			}

			var state = Liferay.Chat.WebRtcConversation.State;

			var stateHandlers = {};

			stateHandlers[state.STOPPED] = A.bind('_onStateStopped', instance);
			stateHandlers[state.DELETED] = A.bind('_onStateDeleted', instance);
			stateHandlers[state.CALLING] = A.bind('_onStateCalling', instance);
			stateHandlers[state.CALLED] = A.bind('_onStateCalled', instance);
			stateHandlers[state.GOTCALL] = A.bind('_onStateGotCall', instance);
			stateHandlers[state.ACCEPTINGCALL] = A.bind('_onStateAcceptingCall', instance);
			stateHandlers[state.DENYINGCALL] = A.bind('_onStateDenyingCall', instance);
			stateHandlers[state.GOTANSWER] = A.bind('_onStateGotAnswer', instance);
			stateHandlers[state.STOPPING] = A.bind('_onStateStopping', instance);
			stateHandlers[state.DELETING] = A.bind('_onStateDeleting', instance);

			instance._stateHandlers = stateHandlers;

			Liferay.Chat.WebRtcManager.registerConversation(instance);

			instance.setState(state.STOPPED);
		};

		Liferay.Chat.WebRtcConversation.State = {
			ACCEPTINGCALL: 'acceptingCall',
			ANSWERED: 'answered',
			CALLED: 'called',
			CALLING: 'calling',
			CALLINGWAITING: 'callingWaiting',
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

		var CONVERSATION_ERROR = Liferay.Chat.WebRtcConversation.Error;
		var STATE = Liferay.Chat.WebRtcConversation.State;

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

				var flowing = !(remoteVideo.readyState <= HTMLMediaElement.HAVE_CURRENT_DATA || remoteVideo.paused || remoteVideo.currentTime <= 0);

				return flowing;
			},

			onDelete: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: conversation deleted');

				var state = instance.getState();

				var deleted = STATE.DELETED;
				var deleting = STATE.DELETING;

				if (state === STATE.CALLED || state === STATE.ACCEPTINGCALL || state === STATE.ANSWERED || state === STATE.GOTANSWER || state === STATE.CONNECTED) {
					instance.setState(deleting);

					instance._sendHangUpMsg();
				}
				else if (state === STATE.STOPPING) {
					instance.setState(deleting);
				}
				else if (state === STATE.GOTCALL || state === STATE.GOTCALLWAITING || state === STATE.DENYINGCALL) {
					instance.setState(deleted);

					instance._sendAnswerMsg(false);
				}
				else {
					instance.setState(deleted);
				}
			},

			onError: function(error) {
				var instance = this;

				instance._cb.onError(error);
			},

			onMsgError: function(msg) {
				var instance = this;

				instance._cb.onWebRtcEvent();

				var msgId = msg.id;

				if (msgId === 'unavailableUser' || msgId === 'invalidState' || msgId === 'cannotAnswer') {
					Liferay.Chat.WebRtcManager.errorMsg('error from server: "' + msgId + '"');

					instance.setState(STATE.STOPPED);
				}
			},

			onMsgGotAnswer: function(msg) {
				var instance = this;

				if (instance.getState() === STATE.CALLED) {
					if (msg.answer) {
						instance.setState(STATE.GOTANSWER);
					}
					else {
						instance.onError(CONVERSATION_ERROR.REMOTEPEERDENIEDCALL);

						instance.setState(STATE.STOPPED);
					}
				}
			},

			onMsgGotCall: function() {
				var instance = this;

				var state = instance.getState();

				if (state === STATE.STOPPED || state === STATE.STOPPING || state === STATE.DELETED || state === STATE.DELETING || state === STATE.CALLINGWAITING) {
					instance.setState(STATE.GOTCALLWAITING);
				}
				else if (state === STATE.CALLED || state === STATE.CALLING) {
					instance.setState(STATE.GOTCALL);
				}
				else {
					// Error: wrong state for an incoming call, so deny it

					instance.setState(STATE.DENYINGCALL);
				}
			},

			onMsgGotStatus: function(msg) {
				var instance = this;

				if (msg.status === 'lost') {
					instance._onLostConnection(msg.reason);
				}
			},

			onMsgNewIceCandidate: function(msg) {
				var instance = this;

				if (instance._isWebRtcStarted()) {
					var iceCandidate = JSON.parse(msg.candidate);

					var RTCIceCandidate = Liferay.Chat.WebRtcManager.getWebRtcAdapter().RTCIceCandidate;

					var rtcIce = new RTCIceCandidate(
						{
							candidate: iceCandidate.candidate,
							sdpMLineIndex: iceCandidate.sdpMLineIndex
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

				var state = instance.getState();

				if (state === STATE.GOTCALL || state === STATE.GOTCALLWAITING) {
					if (!instance._isUserAvailable()) {
						instance.onError(CONVERSATION_ERROR.REMOTEPEERNOTAVAILABLE);

						instance.setState(STATE.DENYINGCALL);

						Liferay.Chat.WebRtcManager.errorMsg('remote peer not available for WebRTC to "accept"');
					}
					else {
						instance.setState(STATE.ACCEPTINGCALL);
					}
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('wrong state "' + state + ' to "accept"');
				}
			},

			onPressCall: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: user pressed "call"');

				var state = instance.getState();

				if (state === STATE.STOPPED) {
					if (!instance._isUserAvailable()) {
						Liferay.Chat.WebRtcManager.errorMsg('remote peer not available for WebRTC to "call"');
					}

					instance.setState(STATE.CALLINGWAITING);
				}
				else {
					Liferay.Chat.WebRtcManager.errorMsg('wrong state "' + state + ' to "call"');
				}
			},

			onPressHangUp: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.debugMsg('event: user pressed "hang up"');

				var state = instance.getState();

				var denyingCall = STATE.DENYINGCALL;

				if (state === STATE.GOTCALL || state === STATE.GOTCALLWAITING) {
					instance.setState(denyingCall);
				}
				else if (state === STATE.CALLED || state === STATE.CALLING || state === STATE.ANSWERED || state === STATE.ACCEPTINGCALL || state === denyingCall || state === STATE.GOTANSWER || state === STATE.CONNECTED) {
					instance._sendHangUpMsg();

					instance.setState(STATE.STOPPING);
				}
				else if (state === STATE.CALLINGWAITING) {
					instance.setState(STATE.STOPPED);
				}
			},

			setState: function(state) {
				var instance = this;

				var webRtcManager = Liferay.Chat.WebRtcManager;

				if (A.Lang.isUndefined(instance.getState())) {
					webRtcManager.debugMsg('{' + state + '}');
				}
				else {
					webRtcManager.debugMsg('{' + instance.getState() + ' -> ' + state + '}');
				}

				instance._currentState = state;

				instance._cb.onStateChange(state);

				Liferay.Chat.WebRtcManager.onConversationStateChange();

				instance._onStateChange();
			},

			_addIceCandidate: function(ice) {
				var instance = this;

				var webRtcManager = Liferay.Chat.WebRtcManager;

				if (instance._acceptIceCandidates) {
					webRtcManager.debugMsg('adding remote ICE candidate to peer connection');

					instance._pc.addIceCandidate(ice);
				}
				else {
					webRtcManager.errorMsg('cannot add following ICE candidate to peer connection:');

					webRtcManager.debugObj(ice);
				}
			},

			_configurePC: function() {
				var instance = this;

				var webRtcManager = Liferay.Chat.WebRtcManager;

				var RTCPeerConnection = webRtcManager.getWebRtcAdapter().RTCPeerConnection;

				instance._pc = new RTCPeerConnection(
					{
						iceServers: instance._iceServers
					},
					webRtcManager.getWebRtcAdapter().peerConnectionConstraints
				);

				instance._pc.addStream(webRtcManager.getLocalStream());

				instance._pc.onicecandidate = function(event) {
					instance._onIceCandidate(event);
				};

				instance._pc.onaddstream = function(event) {
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
					var iceCandidatesBuffer = instance._iceCandidatesBuffer;

					Liferay.Chat.WebRtcManager.debugMsg('flushing ICE candidates buffer (length=' + iceCandidatesBuffer.length + ')');

					iceCandidatesBuffer.forEach(instance._addIceCandidate, instance);

					iceCandidatesBuffer.length = 0;
				}
			},

			_isUserAvailable: function() {
				var instance = this;

				var destUserId = instance.getToUserId();

				return Liferay.Chat.WebRtcManager.isUserAvailable(destUserId);
			},

			_isWebRtcStarted: function() {
				var instance = this;

				var state = instance.getState();

				return state === STATE.ANSWERED || state === STATE.GOTANSWER || state === STATE.CONNECTED;
			},

			_onAddStream: function(event) {
				var instance = this;

				if (instance._isWebRtcStarted() && event) {
					Liferay.Chat.WebRtcManager.debugMsg('added remote stream');

					instance._setRemoteVideoStream(event.stream);

					instance.setState(STATE.CONNECTED);
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

				var pc = instance._pc;

				if (pc) {
					pc.close();

					pc = null;
				}

				if (reason === 'hangUp') {
					instance.onError(CONVERSATION_ERROR.HANGUP);
				}
				else if (reason === 'reset') {
					instance.onError(CONVERSATION_ERROR.REMOTEPEERRESET);
				}

				var state = instance.getState();

				if (state === STATE.DELETING) {
					instance.setState(STATE.DELETED);
				}
				else {
					instance.setState(STATE.STOPPED);
				}
			},

			_onStateAcceptingCall: function() {
				var instance = this;

				instance._sendAnswerMsg(true);

				instance.setState(STATE.ANSWERED);
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

				instance.setState(STATE.CALLED);
			},

			_onStateChange: function() {
				var instance = this;

				var state = instance.getState();

				var stateHandlers = instance._stateHandlers;

				if (state in stateHandlers) {
					stateHandlers[state]();
				}
			},

			_onStateDeleted: function() {
			},

			_onStateDeleting: function() {
			},

			_onStateDenyingCall: function() {
				var instance = this;

				instance._sendAnswerMsg(false);

				instance.setState(STATE.STOPPED);
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
			},

			_resetWebRtcState: function() {
				var instance = this;

				instance._acceptIceCandidates = false;
				instance._caller = false;
				instance._iceCandidatesBuffer = [];
				instance._pc = null;
			},

			_sendAnswerMsg: function(accept) {
				var instance = this;

				Liferay.Chat.WebRtcManager.sendMsg(
					'answer',
					{
						answer: accept,
						destinationUserId: instance.getToUserId()
					}
				);
			},

			_sendCallMsg: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.sendMsg(
					'call',
					{
						destinationUserId: instance.getToUserId()
					}
				);
			},

			_sendHangUpMsg: function() {
				var instance = this;

				Liferay.Chat.WebRtcManager.sendMsg(
					'hangUp',
					{
						destinationUserId: instance.getToUserId()
					}
				);
			},

			_sendIceMsg: function(ice) {
				var instance = this;

				Liferay.Chat.WebRtcManager.sendMsg(
					'ice',
					{
						candidate: JSON.stringify(ice),
						destinationUserId: instance.getToUserId()
					}
				);
			},

			_sendSdpMsg: function(desc) {
				var instance = this;

				Liferay.Chat.WebRtcManager.sendMsg(
					'sdp',
					{
						description: JSON.stringify(desc),
						destinationUserId: instance.getToUserId()
					}
				);
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

				var webRtcManager = Liferay.Chat.WebRtcManager;

				if (instance._pc) {
					var RTCSessionDescription = webRtcManager.getWebRtcAdapter().RTCSessionDescription;

					var remoteDesc = new RTCSessionDescription(desc);

					instance._pc.setRemoteDescription(remoteDesc);

					instance._pc.createAnswer(
						function(desc) {
							desc.sdp = Liferay.Chat.WebRtcAdapter.preferOpus(desc.sdp);

							webRtcManager.debugMsg('generated SDP answer');

							instance._pc.setLocalDescription(desc);

							instance._sendSdpMsg(desc);

							instance._acceptIceCandidates = true;

							instance._flushIceCandidatesBuffer();
						},
						function(error) {
							webRtcManager.errorMsg(error.message);
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
					webRtcManager.errorMsg('when trying to create WebRTC answer: no peer connection available');
				}
			},

			_webRtcCompleteOffer: function(desc) {
				var instance = this;

				var webRtcManager = Liferay.Chat.WebRtcManager;

				if (instance._pc) {
					instance._flushIceCandidatesBuffer();

					var RTCSessionDescription = webRtcManager.getWebRtcAdapter().RTCSessionDescription;

					instance._pc.setRemoteDescription(new RTCSessionDescription(desc));
				}
				else {
					webRtcManager.errorMsg('when trying to complete WebRTC offer: no peer connection available');
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