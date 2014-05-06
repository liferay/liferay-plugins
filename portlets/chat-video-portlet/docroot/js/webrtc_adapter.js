AUI().use(
	'aui-base',
	function(A) {
		Liferay.namespace('Chat');

		Liferay.Chat.WebRtcAdapter = {
			getWebRtcAdapter: function() {
				var rtc = null;

				if (navigator.mozGetUserMedia && mozRTCPeerConnection) {
					// Firefox
					var browserVersion = A.UA.firefox;

					if (browserVersion >= 22) {
						rtc = Liferay.Chat.WebRtcAdapter._getFirefoxWebRtcAdapter(browserVersion);
					}
				}
				else if (navigator.webkitGetUserMedia && webkitRTCPeerConnection) {
					// Chrome/Chromium
					rtc = Liferay.Chat.WebRtcAdapter._getChromeWebRtcAdapter(A.UA.chrome);
				}

				if (rtc !== null) {
					rtc.getUserMediaConstraints = {
						audio: true,
						video: {
							mandatory: {},
							optional: []
						}
					};

					if (A.UA.android) {
						rtc.getUserMediaConstraints.video.mandatory.minWidth = 320;
						rtc.getUserMediaConstraints.video.mandatory.minHeight = 240;
						rtc.getUserMediaConstraints.video.mandatory.maxFrameRate = 15;
					}
				}

				return rtc;
			},

			preferOpus: function(sdp) {
				var sdpLines = sdp.split('\r\n');

				// Search for m line
				var mLineIndex = null;
				for (var i = 0; i < sdpLines.length; ++i) {
					if (sdpLines[i].indexOf('m=audio') !== -1) {
						mLineIndex = i;
						break;
					}
				}

				if (mLineIndex === null) {
					return sdp;
				}

				// If Opus is available, set it as the default in m line
				for (var i = 0; i < sdpLines.length; ++i) {
					if (sdpLines[i].indexOf('opus/48000') !== -1) {
						var opusPayload = Liferay.Chat.WebRtcAdapter._extractSdp(sdpLines[i], /:(\d+) opus\/48000/i);

						if (opusPayload) {
							sdpLines[mLineIndex] = Liferay.Chat.WebRtcAdapter._setDefaultCodec(sdpLines[mLineIndex], opusPayload);
						}
						break;
					}
				}

				// Remove CN in m line and SDP
				sdpLines = Liferay.Chat.WebRtcAdapter._removeCn(sdpLines, mLineIndex);

				sdp = sdpLines.join('\r\n');

				return sdp;
			},

			_getChromeWebRtcAdapter: function(browserVersion) {
				var setStreamAudioTracksEnable =
					function(stream, en) {
						var audioTracks = null;

						if (!webkitMediaStream.prototype.getAudioTracks) {
							if (stream.audioTracks) {
								audioTracks = audioTracks;
							}
						} else {
							audioTracks = stream.getAudioTracks();
						}

						if (!audioTracks) {
							return;
						}

						for (var i = 0; i < audioTracks.length; ++i) {
							var audioTrack = audioTracks[i];
							audioTrack.enabled = en;
						}
					};

				rtc = {
					attachMediaStream: function(element, stream) {
						element.src = webkitURL.createObjectURL(stream);
					},

					createIceServer: function(iceCandidate) {
						var iceServer = null;
						var urlParts = iceCandidate.url.split(':');

						if (urlParts[0].indexOf('stun') === 0) {
							// Create ICE server with STUN URL
							iceServer = {
								url: iceCandidate.url
							};
						}
						else if (urlParts[0].indexOf('turn') === 0) {
							if (browserVersion < 28) {
								// Chrome < 28: use old TURN format
								var urlTurnParts = iceCandidate.url.split('turn:');
								iceServer = {
									url: 'turn:' + iceCandidate.username + '@' + urlTurnParts[1],
									credential: iceCandidate.password
								};
							}
							else {
								// Chrome >= 28: use new TURN format
								iceServer = {
									url: iceCandidate.url,
									credential: iceCandidate.password,
									username: iceCandidate.username
								};
							}
						}

						return iceServer;
					},

					getUserMedia: navigator.webkitGetUserMedia.bind(navigator),

					muteStreamAudio: function(stream) {
						setStreamAudioTracksEnable(stream, false);
					},

					peerConnectionConstraints: {
						optional: [
							{
								DtlsSrtpKeyAgreement: true
							}
						]
					},

					RTCIceCandidate: RTCIceCandidate,

					RTCPeerConnection: webkitRTCPeerConnection,

					RTCSessionDescription: RTCSessionDescription,

					unmuteStreamAudio: function(stream) {
						setStreamAudioTracksEnable(stream, true);
					}
				};

				// Disable DTLS on Android
				if (navigator.userAgent.indexOf('Android') !== -1) {
					rtc.peerConnectionConstraints = {};
				}

				// New syntax of getLocalStreams/getRemoteStreams methods in Chrome 26
				if (!webkitRTCPeerConnection.prototype.getLocalStreams) {
					webkitRTCPeerConnection.prototype.getLocalStreams = function() {
						return this.localStreams;
					};

					webkitRTCPeerConnection.prototype.getRemoteStreams = function() {
						return this.remoteStreams;
					};
				}

				return rtc;
			},

			_getFirefoxWebRtcAdapter: function(browserVersion) {
				var setStreamAudioTracksEnable =
					function(stream, en) {
						if (MediaStream.prototype.getAudioTracks) {
							var audioTracks = stream.getAudioTracks();
							for (var i = 0; i < audioTracks.length; ++i) {
								var audioTrack = audioTracks[i];
								audioTrack.enabled = en;
							}
						}
					};

				rtc = {
					attachMediaStream: function(element, stream) {
						element.mozSrcObject = stream;
						element.play();
					},

					createIceServer: function(iceCandidate) {
						var iceServer = null;
						var urlParts = iceCandidate.url.split(':');

						if (urlParts[0].indexOf('stun') === 0) {
							// Create ICE server with STUN URL
							iceServer = {
								url: iceCandidate.url
							};
						}
						else if (urlParts[0].indexOf('turn') === 0) {
							if (browserVersion < 27) {
								// Create ICE server with TURN URL
								// Ignore transport parameter from TURN URL for FF < 27
								var turnUrlParts = url.split('?');

								if (turnUrlParts.length === 1 ||
										turnUrlParts[1].indexOf('transport=udp') === 0) {
									iceServer = {
										url: turn_url_parts[0],
										credential: password,
										username: username
									};
								}
							}
							else {
								// FF >= 27 supports transport parameters in TURN URL
								iceServer = {
									url: url,
									credential: password,
									username: username
								};
							}
						}

						return iceServer;
					},

					getUserMedia: navigator.mozGetUserMedia.bind(navigator),

					muteStreamAudio: function(stream) {
						setStreamAudioTracksEnable(stream, false);
					},

					peerConnectionConstraints: {},

					RTCIceCandidate: mozRTCIceCandidate,

					RTCPeerConnection: mozRTCPeerConnection,

					RTCSessionDescription: mozRTCSessionDescription,

					unmuteStreamAudio: function(stream) {
						setStreamAudioTracksEnable(stream, true);
					}
				};

				return rtc;
			},

			_extractSdp: function(sdpLine, pattern) {
				var result = sdpLine.match(pattern);
				var sdp;

				if (result && result.length == 2) {
					sdp = result[1];
				} else {
					sdp = null;
				}

				return sdp;
			},

			_removeCn: function(sdpLines, mLineIndex) {
				var mLineElements = sdpLines[mLineIndex].split(' ');

				for (var i = sdpLines.length - 1; i >= 0; --i) {
					var payload = Liferay.Chat.WebRtcAdapter._extractSdp(sdpLines[i], /a=rtpmap:(\d+) CN\/\d+/i);

					if (payload) {
						var cnPos = mLineElements.indexOf(payload);

						if (cnPos !== -1) {
							// Remove CN payload from m line
							mLineElements.splice(cnPos, 1);
						}

						// Remove CN line in SDP
						sdpLines.splice(i, 1);
					}
				}

				sdpLines[mLineIndex] = mLineElements.join(' ');

				return sdpLines;
			},

			_setDefaultCodec: function(mLine, payload) {
				var elements = mLine.split(' ');
				var newLine = [];
				var index = 0;

				for (var i = 0; i < elements.length; ++i) {
					if (index === 3) {
						newLine[index++] = payload;
					}

					if (elements[i] !== payload) {
						newLine[index++] = elements[i];
					}
				}

				return newLine.join(' ');
			}
		};
	}
);
