/* global gadgets,OpenAjax,shindig */

AUI.add(
	'liferay-open-social-gadget',
	function(A) {
		var Lang = A.Lang;

		var containsString = Lang.String.contains;

		var getClassName = A.getClassName;

		var isArray = Array.isArray;

		var isString = Lang.isString;

		var CSS_CLASS_GADGET = getClassName('gadget');

		var GADGET_IFRAME_PREFIX = 'remote_iframe_';

		var MAP = {};

		var PREFIX = 'gadget:';

		var STR_EMPTY = '';

		var TPL_IFRAME = '<iframe class="' + CSS_CLASS_GADGET + '" frameborder="no" {height} id="{iframeId}" name="{iframeId}" scrolling="{scrolling}" src="{src}" {width}></iframe>';

		var Gadget = A.Component.create(
			{
				ATTRS: {
					additionalParams: {},
					appId: {},
					baseRenderURL: {
						validator: Lang.isString
					},
					checksum: {},
					content: {},
					country: {
						setter: function(v) {
							if (!v) {
								v = 'ALL';
							}

							return v;
						}
					},
					debug: {},
					height: {
						setter: function(v) {
							if (v <= 0) {
								v = null;
							}

							return v;
						}
					},
					iframeId: {
						getter: '_getIframeId'
					},
					iframeUrl: {
						getter: '_getIframeUrl'
					},
					language: {
						setter: function(v) {
							if (!v) {
								v = 'ALL';
							}

							return v;
						}
					},
					moduleId: {
						valueFn: function() {
							return Gadget._id++;
						}
					},
					nocache: {},
					parentUrl: {
						setter: '_setParentUrl',
						value: document.location.protocol + '://' + document.location.host
					},
					portletId: {},
					pubsubURILoadTimeout: {
						setter: function(v) {
							if (v <= 0) {
								v = null;
							}

							return v;
						}
					},
					requiresPubsub: {},
					rpcRelay: {},
					rpcToken: {
						value: Math.round(0x7FFFFFFF * Math.random())
					},
					scrolling: {
						setter: function(v) {
							return v ? 'yes' : 'no';
						}
					},
					secureToken: {
						value: 'john.doe:john.doe:appid:cont:url:0:default'
					},
					serverBase: {},
					specUrl: {},
					store: {
						valueFn: function() {
							return new ExpandoStore();
						}
					},
					userPrefs: {
						setter: function(v) {
							if (!v) {
								v = {};
							}

							return v;
						}
					},
					view: {
						value: 'default'
					},
					viewParams: {},
					width: {
						setter: function(v) {
							if (v <= 0) {
								v = null;
							}

							return v;
						}
					}
				},

				NAME: 'liferaygadget',

				prototype: {
					initializer: function() {
						var instance = this;

						Gadget.register(instance);

						gadgets.pubsub2router.init(
							{
								hub: managedHub
							}
						);
					},

					renderUI: function() {
						var instance = this;

						var height = instance.get('height');
						var iframeId = instance.get('iframeId');
						var scrolling = instance.get('scrolling');
						var secureToken = instance.get('secureToken');
						var width = instance.get('width');

						if (instance.get('requiresPubsub')) {
							var iframeAttrs = {
								className: CSS_CLASS_GADGET,
								frameborder: 'no',
								scrolling: scrolling
							};

							if (height) {
								iframeAttrs.height = height;
							}

							if (width) {
								iframeAttrs.width = width;
							}

							var container = gadgets.pubsub2router.hub.getContainer(iframeId);

							if (container) {
								delete gadgets.pubsub2router.hub._containers[iframeId];
							}

							container = new OpenAjax.hub.IframeContainer(
								gadgets.pubsub2router.hub,
								iframeId,
								{
									Container: {
										onSecurityAlert: function(source, alertType) {
											gadgets.error('Security error for container ' + source.getClientID() + ' : ' + alertType);
											source.getIframe().src = 'about:blank';
										}
									},
									IframeContainer: {
										iframeAttrs: iframeAttrs,
										parent: instance.get('contentBox'),
										timeout: instance.get('pubsubURILoadTimeout'),
										tunnelURI: shindig.uri(instance.get('serverBase') + instance.get('rpcRelay')).resolve(shindig.uri(window.location.href)),
										uri: instance.get('iframeUrl')
									}
								}
							);

							instance._iframe = container.getIframe();
						}
						else {
							var iframe = Lang.sub(
								TPL_IFRAME,
								{
									height: height ? 'height="' + height + '"' : STR_EMPTY,
									iframeId: iframeId,
									scrolling: scrolling,
									src: instance.get('iframeUrl'),
									width: width ? 'width="' + width + '"' : STR_EMPTY
								}
							);

							var iframeNode = A.Node.create(iframe);

							instance.get('contentBox').appendChild(iframeNode);

							instance._iframe = iframeNode.getDOM();

							gadgets.rpc.setRelayUrl(iframeId, instance.get('serverBase') + instance.get('rpcRelay'));
							gadgets.rpc.setAuthToken(iframeId, instance.get('rpcToken'));
						}
					},

					bindUI: function() {
						var instance = this;

						instance.after(
							{
								additionalParamsChange: instance._afterAdditionalParamsChange,
								countryChange: instance._afterCountryChange,
								debugChange: instance._afterDebugChange,
								heightChange: instance._afterIframeHeightChange,
								languageChange: instance._afterLanguageChange,
								nocacheChange: instance._afterNocacheChange,
								parentUrlChange: instance._afterParentUrlChange,
								scrollingChange: instance._afterIframeScrollingChange,
								secureTokenChange: instance._afterSecureTokenChange,
								specUrlChange: instance._afterSpecUrlChange,
								userPrefsChange: instance._afterUserPrefsChange,
								viewChange: instance._afterViewChange,
								viewParamsChange: instance._afterViewParamsChange,
								widthChange: instance._afterIframeWidthChange
							}
						);
					},

					_afterAdditionalParamsChange: function(event) {
						var instance = this;

						var src = instance._iframe.src;

						var prevAdditionalParams = event.prevVal;

						var i;

						for (i in prevAdditionalParams) {
							src = instance._setSrcParameter(encodeURIComponent(i), '', src);
						}

						var newAdditionalParams = event.newVal;

						for (i in newAdditionalParams) {
							src = instance._setSrcParameter(encodeURIComponent(i), encodeURIComponent(newAdditionalParams[i]), src);
						}

						instance._iframe.src = src;
					},

					_afterCountryChange: function(event) {
						var instance = this;

						instance._refreshSrcParameter('country', event.newVal);
					},

					_afterDebugChange: function(event) {
						var instance = this;

						var debug = 0;

						if (instance.get('debug')) {
							debug = 1;
						}

						instance._refreshSrcParameter('debug', debug);
					},

					_afterIframeHeightChange: function(event) {
						var instance = this;

						instance._uiSetIframeHeight(event.newVal);
					},

					_afterIframeScrollingChange: function(event) {
						var instance = this;

						instance._uiSetIframeScrolling(event.newVal);
					},

					_afterIframeWidthChange: function(event) {
						var instance = this;

						instance._uiSetIframeWidth(event.newVal);
					},

					_afterLanguageChange: function(event) {
						var instance = this;

						instance._refreshSrcParameter('lang', event.newVal);
					},

					_afterNocacheChange: function(event) {
						var instance = this;

						instance._refreshSrcParameter('nocache', event.newVal);
					},

					_afterParentUrlChange: function(event) {
						var instance = this;

						var parentUrl = event.newVal;

						if (parentUrl) {
							instance._refreshSrcParameter('parent', parentUrl);
						}
					},

					_afterSecureTokenChange: function(event) {
						var instance = this;

						var secureToken = event.newVal;

						if (secureToken) {
							instance._refreshSrcParameter('st', secureToken);
						}
					},

					_afterSpecUrlChange: function(event) {
						var instance = this;

						instance._refreshSrcParameter('url', event.newVal);
					},

					_afterUserPrefsChange: function(event) {
						var instance = this;

						if (!event.SYNC) {
							instance.get('store').savePrefs(instance);
						}

						instance._refreshUserPrefs();
					},

					_afterViewChange: function(event) {
						var instance = this;

						instance._refreshSrcParameter('view', event.newVal);
					},

					_afterViewParamsChange: function(event) {
						var instance = this;

						var viewParams = event.newVal;

						var parentUrl = instance.get('parentUrl');

						if (parentUrl) {
							instance._refreshSrcParameter('view-params', encodeURIComponent(JSON.stringify(viewParams)));
						}
					},

					_getIframeId: function(value) {
						var instance = this;

						return GADGET_IFRAME_PREFIX + instance.get('moduleId');
					},

					_getIframeUrl: function(value) {
						var instance = this;

						var url = STR_EMPTY;

						var urlData = {
							aid: instance.get('appId'),
							checksum: instance.get('checksum'),
							container: instance._CONTAINER,
							country: instance.get('country'),
							lang: instance.get('language'),
							mid: instance.get('moduleId'),
							url: instance.get('specUrl'),
							view: instance.get('view')
						};

						if (instance.get('debug')) {
							urlData.debug = 1;
						}

						if (instance.get('nocache')) {
							urlData.nocache = 1;
						}

						var parentUrl = instance.get('parentUrl');

						if (parentUrl) {
							urlData.parent = parentUrl;
						}

						url = instance.get('serverBase') + 'ifr?' + A.QueryString.stringify(urlData);

						var content = instance.get('content');

						if (content) {
							url += '&rawxml=' + content;
						}

						var secureToken = instance.get('secureToken');

						url += instance.get('additionalParams');

						url += instance._getUserPrefParams();

						if (secureToken) {
							url += '&st=' + secureToken;
						}

						if (!instance.get('requiresPubsub')) {
							url += '#rpctoken=' + instance.get('rpcToken');
						}

						var viewParams = instance.get('viewParams');

						if (viewParams) {
							url += '&view-params=' + encodeURIComponent(JSON.stringify(viewParams));
						}

						return url;
					},

					_getUserPrefParams: function() {
						var instance = this;

						var userPrefs = instance.get('userPrefs');

						var buffer = [];

						for (var i in userPrefs) {
							buffer.push('&up_' + encodeURIComponent(i) + '=' + encodeURIComponent(userPrefs[i]));
						}

						return buffer.join(STR_EMPTY);
					},

					_refreshSrcParameter: function(key, value) {
						var instance = this;

						var src = instance._iframe.src;

						src = instance._setSrcParameter(key, value, src);

						instance._iframe.src = src;
					},

					_refreshUserPrefs: function() {
						var instance = this;

						var src = instance._iframe.src;

						var userPrefs = instance.get('userPrefs');

						for (var i in userPrefs) {
							src = instance._setSrcParameter('&up_' + encodeURIComponent(i), encodeURIComponent(userPrefs[i]), src);
						}

						instance._iframe.src = src;
					},

					_setParentUrl: function(value) {
						var instance = this;

						if (!value.match(/^http[s]?:\/\//)) {
							value = document.location.href.match(/^[^?#]+\//)[0] + value;
						}

						return value;
					},

					_setSrcParameter: function(key, value, src) {
						var instance = this;

						var parameters = src.split('&');

						var parameterFound = false;

						for (var i = 0; i < parameters.length; i++) {
							var parameter = parameters[i].split('=');

							if (parameter[0] == key) {
								parameter[1] = value;

								parameters[i] = parameter.join('=');

								parameterFound = true;

								break;
							}
						}

						if (!parameterFound) {
							var newParameter = key + '=' + value;

							parameters[parameters.length] = newParameter;
						}

						return parameters.join('&');
					},

					_uiSetIframeHeight: function(value) {
						var instance = this;

						instance._iframe.setAttribute('height', value);
					},

					_uiSetIframeScrolling: function(value) {
						var instance = this;

						instance._iframe.setAttribute('scrolling', value);
					},

					_uiSetIframeWidth: function(value) {
						var instance = this;

						instance._iframe.setAttribute('width', value);
					},

					_CONTAINER: 'default'
				},

				_id: 0
			}
		);

		var DefaultStore = A.Component.create(
			{
				ATTRS: {
					userPrefsKey: {}
				},
				EXTENDS: A.Base,
				NAME: 'gadgetstoredefault',
				prototype: {
					savePrefs: Lang.emptyFn
				}
			}
		);

		var CookieStore = A.Component.create(
			{
				EXTENDS: DefaultStore,
				NAME: 'gadgetstorecookie',
				prototype: {
					savePrefs: function(gadget) {
						var instance = this;

						return A.Cookie.setSubs(instance.get('userPrefsKey'), gadget.get('userPrefs'));
					}
				}
			}
		);

		var ExpandoStore = A.Component.create(
			{
				EXTENDS: DefaultStore,
				NAME: 'gadgetstoreexpando',
				prototype: {
					savePrefs: function(gadget) {
						var instance = this;

						Liferay.Service(
							'/expandovalue/add-value',
							{
								className: instance._CLASS_NAME,
								classPK: themeDisplay.getPlid(),
								columnName: instance.get('userPrefsKey'),
								companyId: themeDisplay.getCompanyId(),
								data: JSON.stringify(gadget.get('userPrefs')),
								tableName: instance._TABLE_NAME
							}
						);
					},

					_CLASS_NAME: 'com.liferay.portal.model.Layout',
					_TABLE_NAME: 'OPEN_SOCIAL_DATA_'
				}
			}
		);

		var Store = {
			Cookie: CookieStore,
			Default: DefaultStore,
			Expando: ExpandoStore
		};

		var _instances = {};

		Gadget.register = function(gadget) {
			if (gadget) {
				var id = gadget.get('moduleId');

				_instances[id] = gadget;
			}

			return gadget;
		};

		Gadget.get = function(id) {
			id = String(id);

			if (id.indexOf(GADGET_IFRAME_PREFIX) === 0) {
				id = id.replace(GADGET_IFRAME_PREFIX, STR_EMPTY);
			}

			return _instances[id];
		};

		Liferay._detachInitialFn = Liferay.detach;
		Liferay._fireInitialFn = Liferay.fire;
		Liferay._onInitialFn = Liferay.on;

		Liferay.detach = function(topic, fn) {
			var gadgetTopic = topic;
			var handle = topic;

			var subscriptionId;

			if (handle && !handle.detach) {
				subscriptionId = getSubscriptionId(topic, fn);
			}

			if (subscriptionId) {
				unsubscribeTopic(gadgetTopic, subscriptionId);
			}

			return Liferay._detachInitialFn.apply(Liferay, arguments);
		};

		Liferay.fire = function(topic, data) {
			if (containsString(topic, PREFIX)) {
				var eventType = topic.replace(PREFIX, STR_EMPTY);

				gadgets.pubsub2router.hub.publish(eventType, data);
			}

			return Liferay._fireInitialFn.apply(Liferay, arguments);
		};

		Liferay.on = function(topic, fn) {
			var gadgetTopic;
			var handle;
			var subscriptionId;

			if (isArray(topic)) {
				gadgetTopic = [];
				subscriptionId = [];

				var eventName;

				for (var i = 0; i < topic.length; i++) {
					eventName = topic[i];

					if (containsString(eventName, PREFIX)) {
						gadgetTopic.push(eventName);
						subscriptionId.push(subscribeGadgetEvent(eventName, fn));
					}
				}
			}
			else if (containsString(topic, PREFIX)) {
				gadgetTopic = topic;
				subscriptionId = subscribeGadgetEvent(topic, fn);
			}

			if (subscriptionId && subscriptionId.length) {
				handle = Liferay._onInitialFn(topic, Lang.emptyFn);

				handle._LFR_HANDLE_DETACH = handle.detach;

				handle._LFR_GADGET_TOPIC = gadgetTopic;

				handle._LFR_SUB_ID = subscriptionId;

				handle.detach = function() {
					handle.detach = handle._LFR_HANDLE_DETACH;

					unsubscribeTopic(topic, subscriptionId);
				};
			}
			else {
				handle = Liferay._onInitialFn.apply(Liferay, arguments);
			}

			return handle;
		};

		var getSubscriptionId = function(topic, fn) {
			var subscriptionId = null;

			if (topic) {
				var eventType = topic.replace(PREFIX, STR_EMPTY);

				var eventMap = MAP[eventType];

				var allIds = !fn;

				if (allIds) {
					subscriptionId = [];
				}

				if (eventMap) {
					for (var i in eventMap) {
						if (allIds) {
							subscriptionId.push(i);
						}
						else if (eventMap[i] == fn) {
							subscriptionId = i;

							break;
						}
					}
				}
			}

			return subscriptionId;
		};

		var subscribeGadgetEvent = function(topic, fn) {
			var eventType = topic.replace(PREFIX, STR_EMPTY);

			var eventMap = MAP[eventType] || {};

			var subscriptionId = gadgets.pubsub2router.hub.subscribe(eventType, fn);

			eventMap[subscriptionId] = fn;

			MAP[eventType] = eventMap;

			return subscriptionId;
		};

		var unsubscribeGadgetEvent = function(topic, subscriptionId, fn) {
			var eventType = topic.replace(PREFIX, STR_EMPTY);

			var eventMap = MAP[eventType];

			if (!fn || eventMap[subscriptionId] == fn) {
				gadgets.pubsub2router.hub.unsubscribe(subscriptionId);

				delete eventMap[subscriptionId];
			}
		};

		var unsubscribeTopic = function(topic, subscriptionId, fn) {
			var i;

			var arraySubscriptionId = isArray(subscriptionId);
			var stringTopic = isString(topic);

			if (stringTopic && isString(subscriptionId)) {
				unsubscribeGadgetEvent(topic, subscriptionId, fn);
			}
			else if (stringTopic && arraySubscriptionId) {
				for (i = 0; i < subscriptionId.length; i++) {
					unsubscribeGadgetEvent(topic, subscriptionId[i], fn);
				}
			}
			else if (isArray(topic) && arraySubscriptionId) {
				for (i = 0; i < subscriptionId.length; i++) {
					unsubscribeGadgetEvent(topic[i], subscriptionId[i]);
				}
			}
		};

		var managedHub = new OpenAjax.hub.ManagedHub(
			{
				onPublish: function(topic, data, pcont, scont) {
					if (!containsString(topic, PREFIX)) {
						var eventType = PREFIX + topic;

						Liferay._fireInitialFn(eventType, data);
					}

					return true;
				},
				onSubscribe: Lang.emptyFnTrue,
				onUnsubscribe: Lang.emptyFn
			}
		);

		var inlineContainer = new OpenAjax.hub.InlineContainer(
			managedHub,
			'liferay',
			{
				Container: {
					onConnect: function(container) {},
					onDisconnect: function(container) {},
					onSecurityAlert: function(source, alertType) {}
				}
			}
		);

		var hubClient = new OpenAjax.hub.InlineHubClient(
			{
				HubClient: {
					onSecurityAlert: function(source, alertType) {}
				},
				InlineHubClient: {
					container: inlineContainer
				}
			}
		);

		gadgets.rpc.register(
			'resize_iframe',
			function(height) {
				var gadget = Gadget.get(this.f);

				if (gadget) {
					gadget.set('height', height);
				}
			}
		);

		var viewWindowStateMap = {
			canvas: 'maximized',
			'default': 'normal',
			home: 'normal',
			profile: 'normal'
		};

		gadgets.rpc.register(
			'requestNavigateTo',
			function(view, viewParams) {
				var gadget = Gadget.get(this.f);

				var portletURL = new Liferay.PortletURL.createURL(gadget.get('baseRenderURL'));

				portletURL.setPortletId(gadget.get('portletId'));
				portletURL.setParameter('returnToFullPageURL', document.location.href);
				portletURL.setParameter('view', view);

				var windowState = viewWindowStateMap[view];

				if (windowState) {
					portletURL.setWindowState(windowState);
				}

				if (Lang.isString(viewParams)) {
					portletURL.setParameter('viewParams', viewParams);
				}

				document.location.href = portletURL.toString();
			}
		);

		gadgets.rpc.register(
			'set_pref',
			function(editToken, name, value) {
				var gadget = Gadget.get(this.f);

				if (gadget) {
					var length = arguments.length;
					var userPrefs = gadget.get('userPrefs');

					for (var i = 1; i < length; i += 2) {
						var arg = arguments[i];

						userPrefs[arg] = arguments[i + 1];
					}

					gadget.set('userPrefs', userPrefs);
				}
			}
		);

		gadgets.rpc.register('set_title', Lang.emptyFn);
		gadgets.rpc.register('requestSendMessage', Lang.emptyFn);

		var OpenSocial = Liferay.namespace('OpenSocial');

		OpenSocial.Gadget = Gadget;
		OpenSocial.Store = Store;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-deprecated', 'cookie', 'json', 'liferay-portlet-url', 'querystring']
	}
);