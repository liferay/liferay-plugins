AUI().add(
	'liferay-open-social-gadget',
	function(A) {
		var Lang = A.Lang;

		var containsString = Lang.String.contains;

		var getClassName = A.getClassName;

		var isArray = Lang.isArray;

		var isString = Lang.isString;

		var CSS_CLASS_GADGET = getClassName('gadget');

		var GADGET_IFRAME_PREFIX = 'remote_iframe_';

		var MAP = {};

		var PREFIX = 'gadget:';

		var STR_EMPTY = '';

		var TPL_IFRAME = '<iframe id="{iframeId}" name="{iframeId}" class="' + CSS_CLASS_GADGET + '" src="about:blank" frameborder="no" scrolling="{scrolling}" {height} {width}></iframe>';

		var Gadget = A.Component.create(
			{
				_id: 0,

				NAME: 'liferaygadget',

				ATTRS: {
					additionalParams: {
						value: STR_EMPTY
					},
					appId: {},
					content: {},
					country: {
						value: 'ALL'
					},
					debug: {},
					height: {
						setter: function(v) {
							if (v > 0) {
								return v;
							}
							else {
								return null;
							}
						}
					},
					iframeId: {
						getter: '_getIframeId'
					},
					iframeUrl: {
						getter: '_getIframeUrl'
					},
					language: {
						value: 'ALL'
					},
					moduleId: {
						valueFn: function() {
							return Gadget._id++;
						}
					},
					nocache: {
						value: 1
					},
					parentUrl: {
						value: document.location.protocol + '://' + document.location.host,
						setter: '_setParentUrl'
					},
					portletId:{},
					requiresPubsub:{},
					rpcRelay: {},
					rpcToken: {
						value: Math.round(0x7FFFFFFF * Math.random())
					},
					scrolling: {
						setter: function(v) {
							if (v) {
								return 'yes';
							}
							else {
								return 'no';
							}
						}
					},
					serverBase: {},
					secureToken: {
						value: 'john.doe:john.doe:appid:cont:url:0:default'
					},
					specUrl: {},
					store: {
						valueFn: function() {
							return new ExpandoStore();
						}
					},
					userPrefs: {
						value: {}
					},
					view: {
						value: 'default'
					},
					viewParams: {},
					width: {
						setter: function(v) {
							if (v > 0) {
								return v;
							}
							else {
								return null;
							}
						}
					}
				},

				UI_ATTRS: ['iframeUrl'],

				prototype: {
					initializer: function() {
						var instance = this;

						Gadget.register(instance);
					},

					renderUI: function() {
						var instance = this;

						var height = instance.get('height');
						var iframeId = instance.get('iframeId');
						var requiresPubsub = instance.get('requiresPubsub');
						var scrolling = instance.get('scrolling');
						var secureToken = instance.get('secureToken');
						var width = instance.get('width');

						if (requiresPubsub) {
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
										tunnelURI: shindig.uri(instance.get('serverBase') + instance.get('rpcRelay')).resolve(shindig.uri(window.location.href)),
										uri: instance.get('iframeUrl')
									}
								}
							);

							instance._iframe = container.getIframe();
						}
						else {
							var iframe = A.substitute(
								TPL_IFRAME,
								{
									height: (height ? 'height="' + height + '"' : STR_EMPTY),
									iframeId: iframeId,
									scrolling: scrolling,
									width: (width ? 'width="' + width + '"' : STR_EMPTY)
								}
							);

							var iframeNode = A.Node.create(iframe);

							instance.get('contentBox').appendChild(iframeNode);

							instance._iframe = iframeNode;

							gadgets.rpc.setRelayUrl(iframeId, instance.get('serverBase') + instance.get('rpcRelay'));
							gadgets.rpc.setAuthToken(iframeId, instance.get('rpcToken'));
					    }
					},

					bindUI: function() {
						var instance = this;

						instance.after('userPrefsChange', instance._afterUserPrefsChange);

						instance.after('heightChange', instance._afterIframeHeightChange);
						instance.after('widthChange', instance._afterIframeWidthChange);

						var refreshIframe = instance.refresh;

						instance.after(
							{
								nocacheChange: refreshIframe,
								countryChange: refreshIframe,
								languageChange: refreshIframe,
								secureTokenChange: refreshIframe,
								viewChange: refreshIframe,
								parentUrlChange: refreshIframe,
								debugChange: refreshIframe,
								additionalParamsChange: refreshIframe,
								viewParams: refreshIframe,
								specUrlChange: refreshIframe,
								serverBaseChange: refreshIframe
							}
						);
					},

					syncUI: function() {
						var instance = this;

						instance.get('store').getPrefs(instance, A.bind(instance._syncPrefs, instance));
					},

					getUserPrefParams: function() {
						var instance = this;

						var userPrefs = instance.get('userPrefs');
						var buffer = [];

						for (var i in userPrefs) {
							buffer.push('&up_' + encodeURIComponent(i) + '=' + encodeURIComponent(userPrefs[i]));
						}

						return buffer.join(STR_EMPTY);
					},

					refresh: function() {
						var instance = this;

						instance._uiSetIframeUrl(instance.get('iframeUrl'));
					},

					_afterIframeHeightChange: function(event) {
						var instance = this;

						instance._uiSetIframeHeight(event.newVal);
					},

					_afterIframeWidthChange: function(event) {
						var instance = this;

						instance._uiSetIframeWidth(event.newVal);
					},

					_afterUserPrefsChange: function(event) {
						var instance = this;

						if (!event.SYNC) {
							instance.get('store').savePrefs(instance);
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
							container: instance._CONTAINER,
							mid: instance.get('moduleId'),
							nocache: instance.get('nocache'),
							country: instance.get('country'),
							lang: instance.get('language'),
							view: instance.get('view'),
							url: instance.get('specUrl')
						};

						var parentUrl = instance.get('parentUrl');

						if (parentUrl) {
							urlData.parent = parentUrl;
						}

						if (instance.get('debug')) {
							urlData.debug = 1;
						}

						url = instance.get('serverBase') + 'ifr?' + A.QueryString.stringify(urlData);

						var content = instance.get('content');

						if (content) {
							url += '&rawxml=' + content;
						}

						var secureToken = instance.get('secureToken');

						url += instance.get('additionalParams');

						url += instance.getUserPrefParams();

						if (secureToken) {
							url += '&st=' + secureToken;
						}

						url += '#rpctoken=' + instance.get('rpcToken');

						var viewParams = instance.get('viewParams');

						if (viewParams) {
							url += '&view-params=' + encodeURIComponent(A.JSON.stringify(viewParams));
						}

						return url;
					},

					_setParentUrl: function(value) {
						var instance = this;

						if (!value.match(/^http[s]?:\/\//)) {
							value = document.location.href.match(/^[^?#]+\//)[0] + value;
						}

						return value;
					},

					_syncPrefs: function(prefs) {
						var instance = this;

						instance.set(
							'userPrefs',
							prefs,
							{
								SYNC: true
							}
						);

						instance.refresh();
					},

					_uiSetIframeHeight: function(value) {
						var instance = this;

						instance._iframe.setAttribute('height', value);
					},

					_uiSetIframeUrl: function(value) {
						var instance = this;

						if (instance._iframe && instance._iframe.set) {
							instance._iframe.set('src', value);
						}
					},

					_uiSetIframeWidth: function(value) {
						var instance = this;

						instance._iframe.setAttribute('width', value);
					},

					_CONTAINER: 'default'
				}
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
					getPrefs: Lang.emptyFn,
					savePrefs: Lang.emptyFn
				}
			}
		);

		var CookieStore = A.Component.create(
			{
				EXTENDS: DefaultStore,
				NAME: 'gadgetstorecookie',
				prototype: {
					getPrefs: function(gadget, callback) {
						var instance = this;

						if (Lang.isFunction(callback)) {
							callback(A.Cookie.getSubs(instance.get('userPrefsKey')) || {});
						}
					},

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
					getPrefs: function(gadget, callback) {
						var instance = this;

						Liferay.Service.Expando.ExpandoValue.getJSONData(
							{
								companyId: themeDisplay.getCompanyId(),
								className: instance._CLASS_NAME,
								tableName: instance._TABLE_NAME,
								columnName: instance.get('userPrefsKey'),
								classPK: themeDisplay.getUserId()
							},
							function(userPrefs) {
								if (Lang.isFunction(callback)) {
									if (!userPrefs.exception) {
										callback(userPrefs || {});
									}
								}
							}
						);
					},

					savePrefs: function(gadget) {
						var instance = this;

						var serviceParameterTypes = [
							'long',
							'java.lang.String',
							'java.lang.String',
							'java.lang.String',
							'long',
							'java.lang.String'
						];

						return Liferay.Service.Expando.ExpandoValue.addValue(
							{
								companyId: themeDisplay.getCompanyId(),
								className: instance._CLASS_NAME,
								tableName: instance._TABLE_NAME,
								columnName: instance.get('userPrefsKey'),
								classPK: themeDisplay.getUserId(),
								data: A.JSON.stringify(gadget.get('userPrefs')),
								serviceParameterTypes: A.JSON.stringify(serviceParameterTypes)
							}
						);
					},

					_CLASS_NAME: 'com.liferay.portal.model.User',
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
			var handle = topic;
			var gadgetTopic = topic;
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
			var handle;
			var gadgetTopic;
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

				var allIds = (!fn);

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
			if (isString(topic) && isString(subscriptionId)) {
				unsubscribeGadgetEvent(topic, subscriptionId, fn);
			}
			else if (isString(topic) && isArray(subscriptionId)) {
				for (var i = 0; i < subscriptionId.length; i++) {
					unsubscribeGadgetEvent(topic, subscriptionId[i], fn);
				}
			}
			else if (isArray(topic) && isArray(subscriptionId)) {
				for (var i = 0; i < subscriptionId.length; i++) {
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

		var inlineContainer = new OpenAjax.hub.InlineContainer(managedHub , "liferay",
			{
				Container: {
					onSecurityAlert: function(source, alertType) {},
					onConnect: function(container) {},
					onDisconnect: function(container) {}
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

		gadgets.pubsub2router.init(
			{
				hub: managedHub
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

				var portletURL = Liferay.PortletURL.createRenderURL();

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
						arg = arguments[i];

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
		requires: ['aui-base', 'aui-io', 'cookie', 'json', 'liferay-portlet-url', 'liferay-service', 'querystring', 'substitute']
	}
);