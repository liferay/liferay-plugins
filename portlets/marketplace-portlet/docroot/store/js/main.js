AUI.add(
	'liferay-marketplace-messenger',
	function(A) {
		var NATIVE_MSG = !!(window.postMessage);

		var MarketplaceMessenger = {
			init: function(options, initMessage) {
				var instance = this;

				if (A.Lang.isString(options)) {
					instance._targetURI = options;
				}
				else if (A.Lang.isObject(options)) {
					instance._targetFrame = options.targetFrame;
					instance._targetURI = options.targetURI;
				}

				if (initMessage) {
					instance.postMessage(initMessage);
				}
			},

			postMessage: function(message) {
				var instance = this;

				if (NATIVE_MSG) {
					A.postMessage(message, instance._targetURI, instance._targetFrame);
				}
				else {
					instance._messages.push(message);

					if (instance._messages.length == 1) {
						A.postMessage(message, instance._targetURI, instance._targetFrame);
					}
				}
			},

			receiveMessage: function(callback, validator) {
				var instance = this;

				validator = validator || instance._targetURI;

				if (NATIVE_MSG) {
					A.receiveMessage(callback, validator);
				}
				else {
					var wrappedCallback = function(event) {
						var response = event.responseData;

						callback(event);

						instance._messages.shift();

						var message = null;

						if (instance._messages.length > 0) {
							message = instance._messages[0];
						}
						else if (!response.empty) {
							message = {
								empty: true
							};
						}

						if (message) {
							A.postMessage(message, instance._targetURI, instance._targetFrame);
						}
					};

					A.receiveMessage(wrappedCallback, validator);
				}
			},

			setTargetFrame: function(targetFrame) {
				this._targetFrame = targetFrame;
			},

			setTargetURI: function(targetURI) {
				this._targetURI = targetURI;
			},

			_messages: [],
			_targetFrame: null,
			_targetURI: null
		};

		Liferay.MarketplaceMessenger = MarketplaceMessenger;
	},
	'',
	{
		requires: ['aui-messaging']
	}
);

AUI.add(
	'liferay-marketplace-util',
	function(A) {
		var MarketplaceUtil = {
			namespaceObject: function(namespace, object) {
				var returnObject = {};

				var keys = A.Object.keys(object);

				A.Array.each(
					keys,
					function(key) {
						returnObject[namespace + key] = object[key];
					}
				);

				return returnObject;
			}
		};

		Liferay.MarketplaceUtil = MarketplaceUtil;
	},
	'',
	{
		requires: ['aui-base']
	}
);