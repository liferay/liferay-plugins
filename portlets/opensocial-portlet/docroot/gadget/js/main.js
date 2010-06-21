AUI().add(
	'liferay-open-social-gadget',
	function(A) {
		var Lang = A.Lang;

		var getClassName = A.ClassNameManager.getClassName;

		var CSS_CLASS_GADGET = getClassName('gadget');

		var GADGET_IFRAME_PREFIX = 'remote_iframe_';

		var TPL_IFRAME = '<iframe id="{iframeId}" name="{iframeId}" class="' + CSS_CLASS_GADGET + '" src="about:blank" frameborder="no" scrolling="no" {height} {width}></iframe>';

		var Gadget = function() {
			Gadget.superclass.constructor.apply(this, arguments);
		};

		Gadget._id = 0;

		Gadget.NAME = 'liferaygadget';

		Gadget.ATTRS = {
			additionalParams: {
				value: ''
			},
			appId: {},
			country: {
				value: 'ALL'
			},
			debug: {},
			height: {},
			iframeId: {
				getter: '_getIframeId'
			},
			iframeUrl: {
				getter: '_getIframeUrl'
			},
			language: {
				value: 'ALL'
			},
			moduleId: {},
			nocache: {
				value: 1
			},
			parentUrl: {
				value: 'http://' + document.location.host,
				setter: '_setParentUrl'
			},
			rpcRelay: {},
			rpcToken: {
				value: Math.round(0x7FFFFFFF * Math.random())
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
			userPrefsKey: {},
			view: {
				value: 'default'
			},
			viewParams: {},
			width: {}
		};

		A.extend(
			Gadget,
			A.Component,
			{
				_CONTAINER: 'default',

				initializer: function() {
					var instance = this;

					Gadget.register(instance);
				},

				renderUI: function() {
					var instance = this;

					var height = instance.get('height');
					var iframeId = instance.get('iframeId');
					var width = instance.get('width');

					var iframe = A.substitute(
						TPL_IFRAME,
						{
							height: (height ? 'height="' + height + '"' : ''),
							iframeId: iframeId,
							width: (width ? 'width="' + width + '"' : '')
						}
					);

					var iframeNode = A.Node.create(iframe);

					instance.get('contentBox').appendChild(iframeNode);

					instance._iframe = iframeNode;

					gadgets.rpc.setRelayUrl(iframeId, instance.get('serverBase') + instance.get('rpcRelay'));
					gadgets.rpc.setAuthToken(iframeId, instance.get('rpcToken'));
				},

				bindUI: function() {
					var instance = this;

					instance.after('iframeUrlChange', instance._afterIframeUrlChange);

					instance.after('heightChange', instance._afterIframeHeightChange);
					instance.after('widthChange', instance._afterIframeWidthChange);

					instance.after('userPrefsChange', instance._afterUserPrefsChange);

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

					return buffer.join('');
				},

				refresh: function() {
					var instance = this;

					instance._uiSetIframeUrl(instance.get('iframeUrl'));
				},

				_afterIframeHeightChange: function(event) {
					var instance = this;

					instance._uiSetIframeHeight(event.newVal);
				},

				_afterIframeUrlChange: function(event) {
					var instance = this;

					instance._uiSetIframeUrl(event.newVal);
				},

				_afterIframeWidthChange: function(event) {
					var instance = this;

					instance._uiSetIframeWidth(event.newVal);
				},

				_afterUserPrefsChange: function(event) {
					var instance = this;

					instance.get('store').savePrefs(instance);
				},

				_getIframeId: function(value) {
					var instance = this;

					return GADGET_IFRAME_PREFIX + instance.get('moduleId');
				},

				_getIframeUrl: function(value) {
					var instance = this;

					var url = '';

					var urlData = {
						appId: instance.get('appId'),
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

					instance.set('userPrefs', prefs);

					instance.refresh();
				},

				_uiSetIframeHeight: function(value) {
					var instance = this;

					instance._iframe.setAttribute('height', value);
				},

				_uiSetIframeUrl: function(value) {
					var instance = this;

					instance._iframe.set('src', value);
				},

				_uiSetIframeWidth: function(value) {
					var instance = this;

					instance._iframe.setAttribute('width', value);
				}
			}
		);

		// Data Store

		var DefaultStore = function() {
		};

		DefaultStore.prototype = {
			getPrefs: Lang.emptyFn,
			savePrefs: Lang.emptyFn
		};

		var CookieStore = function() {
			CookieStore.superclass.constructor.apply(this, arguments);
		};

		A.extend(
			CookieStore,
			DefaultStore,
			{
				getPrefs: function(gadget, callback) {
					var instance = this;

					if (Lang.isFunction(callback)) {
						callback(A.Cookie.getSubs(gadget.get('userPrefsKey')) || {});
					}
				},

				savePrefs: function(gadget) {
					var instance = this;

					return A.Cookie.setSubs(gadget.get('userPrefsKey'), gadget.get('userPrefs'));
				}
			}
		);

		var ExpandoStore = function() {
			ExpandoStore.superclass.constructor.apply(this, arguments);
		};
		
		A.extend(
			ExpandoStore,
			DefaultStore,
			{
				_CLASS_NAME: 'com.liferay.portal.model.User',
				_TABLE_NAME: 'OPEN_SOCIAL_DATA_',
				
				getPrefs: function(gadget, callback) {
					var instance = this;

					Liferay.Service.Expando.ExpandoValue.getJSONData(
						{
							companyId: themeDisplay.getCompanyId(),
							className: instance._CLASS_NAME,
							tableName: instance._TABLE_NAME,
							columnName: gadget.get('userPrefsKey'),
							classPK: themeDisplay.getUserId(),
						},  
						function(userPrefs) {
							if (Lang.isFunction(callback)) {
								callback(userPrefs || {});
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
							columnName: gadget.get('userPrefsKey'),
							classPK: themeDisplay.getUserId(),
							data: A.JSON.stringify(gadget.get('userPrefs')),
							serviceParameterTypes: A.JSON.stringify(serviceParameterTypes)
						}
					);
				}
			}
		);
		
		Gadget.Store = {
			Cookie: CookieStore,
			Default: DefaultStore,
			Expando: ExpandoStore
		};

		// Static Gadget Methods

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
				id = id.replace(GADGET_IFRAME_PREFIX, '');
			}

			return _instances[id];
		};

		// Gadget RPC Registration

		gadgets.rpc.register(
			'resize_iframe',
			function(height) {
				var gadget = Gadget.get(this.f);

				if (gadget) {
					gadget.set('height', height);
				}
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
				}

				gadget.set('userPrefs', userPrefs);
			}
		);
		gadgets.rpc.register('set_title', Lang.emptyFn);
		gadgets.rpc.register('requestNavigateTo', Lang.emptyFn);
		gadgets.rpc.register('requestSendMessage', Lang.emptyFn);

		Liferay.namespace('OpenSocial').Gadget = Gadget;
	},
	'',
	{
		requires: ['aui-base', 'cookie', 'json', 'liferay-service', 'substitute'],
		use: []
	}
);