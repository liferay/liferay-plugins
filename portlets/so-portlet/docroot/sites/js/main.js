AUI.add(
	'liferay-so-site-list',
	function(A) {
		var SiteList = A.Base.create(
			'sitelist',
			A.Base,
			[A.AutoCompleteBase],
			{
				initializer: function(config) {
					this._listNode = A.one(config.listNode);

					if (this._listNode) {
						this._bindUIACBase();
						this._syncUIACBase();
					}
				}
			}
		);

		Liferay.namespace('SO');

		Liferay.SO.SiteList = SiteList;
	},
	'',
	{
		requires: ['aui-base', 'autocomplete-base', 'node-core']
	}
);

AUI.add(
	'liferay-so-user-menu',
	function(A) {
		var UserMenu = function(config) {
			var hideClass = config.hideClass;
			var hideOn = config.hideOn || 'close-menus';
			var showClass = config.showClass;
			var showOn = config.showOn || 'click';

			var node = A.one(config.node);

			var target = A.one(config.target) || node;

			target.on(
				'clickoutside',
				function(event) {
					if (hideClass && !target.hasClass(hideClass)) {
						target.addClass(hideClass);
					}

					if (showClass && target.hasClass(showClass)) {
						target.removeClass(showClass);
					}
				}
			);

			var trigger = A.one(config.trigger) || node;

			trigger.on(
				showOn,
				function(event) {
					if (hideClass && target.hasClass(hideClass)) {
						setTimeout(
							function() {
								target.removeClass(hideClass);
							},
							10
						);
					}

					if (showClass && !target.hasClass(showClass)) {
						target.addClass(showClass);
					}
				}
			);
		};

		Liferay.namespace('SO');

		Liferay.SO.UserMenu = UserMenu;
	},
	'',
	{
		requires: ['aui-base', 'node-core']
	}
);

AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	'datasource-io',
	'json-parse',
	'liferay-so-site-list',
	'liferay-util-window',
	function(A) {
		var Lang = A.Lang;

		Liferay.namespace('SO');

		Liferay.SO.Sites = {
			init: function(config) {
				var instance = this;

				instance._namespace = config.namespace;

				instance._createSiteList(config);
				instance._assignEvents();
			},

			closePopup: function() {
				var instance = this;

				var popup = instance.getPopup();

				if (popup) {
					popup.hide();
				}
			},

			createDataSource: function(url, namespace) {
				var instance = this;

				if (namespace) {
					instance._namespace = namespace;
				}

				return new A.DataSource.IO(
					{
						ioConfig: {
							method: 'POST'
						},
						on: {
							request: function(event) {
								var sitesTabsContainer = A.one('.so-portlet-sites .sites-tabs');

								var tabs1 = 'all-sites';

								if (sitesTabsContainer) {
									tabs1 = sitesTabsContainer.one('select').get('value');
								}

								var eventData = {};

								var data = event.request;

								eventData[instance._namespace + 'directory'] = data[instance._namespace + 'directory'] || false;
								eventData[instance._namespace + 'end'] = data[instance._namespace + 'end'] || 10;
								eventData[instance._namespace + 'keywords'] = data[instance._namespace + 'keywords'] || '';
								eventData[instance._namespace + 'searchTab'] = data[instance._namespace + 'searchTab'] || tabs1;
								eventData[instance._namespace + 'start'] = data[instance._namespace + 'start'] || 0;

								event.cfg.data = eventData;
							}
						},
						source: url
					}
				);
			},

			createDirectoryList: function(directoryList) {
				var instance = this;

				instance._directoryList = directoryList;
			},

			disableButton: function(button) {
				button.set('disabled', true);

				button.addClass('disabled');
			},

			displayPopup: function(url, title, data) {
				var instance = this;

				var popup = instance.getPopup();

				popup.titleNode.html(title);

				popup.show();

				popup.io.set('uri', url);
				popup.io.set('data', data);

				popup.io.start();
			},

			enableButton: function(button) {
				button.set('disabled', false);

				button.removeClass('disabled');
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = Liferay.Util.Window.getWindow(
						{
							dialog: {
								align: {
									node: null,
									points: ['tc', 'tc']
								},
								constrain2view: true,
								cssClass: 'so-portlet-sites-dialog',
								modal: true,
								resizable: true,
								width: 650
							}
						}
					).plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					).render();
				}

				return instance._popup;
			},

			setTitle: function(title) {
				var instance = this;

				var popup = instance.getPopup();

				popup.titleNode.html(title);
			},

			updateSites: function(showSuccessMessage, keywordsInput, requestTemplate) {
				var instance = this;

				if (instance._directoryList) {
					instance._directoryList.sendRequest(keywordsInput, requestTemplate);
				}

				if (instance._siteList) {
					instance._siteList.sendRequest();
				}

				if (showSuccessMessage && instance._messages) {
					instance._messages.html('<span class="portlet-msg-success">' + Liferay.Language.get('your-request-completed-successfully') + '</span>');
				}
			},

			_assignEvents: function() {
				A.one('.so-portlet-sites').delegate(
					'click',
					function(event) {
						var downImage = 'arrow_down.png';
						var rightImage = 'arrow_right.png';

						var image = event.currentTarget;

						var description = image.get('parentNode').one('.description');
						var src = image.get('src');

						if (src.indexOf('down.png') > -1) {
							description.hide();
							src = src.replace(downImage, rightImage);
						}
						else {
							description.show();
							src = src.replace(rightImage, downImage);
						}

						image.set('src', src);
					},
					'.description-toggle'
				);
			},

			_createSiteList: function(config) {
				var instance = this;

				var siteListContainer = config.siteListContainer;

				var siteList = config.siteList;
				var siteListURL = config.siteListURL;
				var siteSearchInput = config.siteSearchInput;

				siteList = new Liferay.SO.SiteList(
					{
						inputNode: siteSearchInput,
						listNode: siteList,
						minQueryLength: 0,
						requestTemplate: function(query) {
							var data = {};

							data[instance._namespace + 'keywords'] = query;

							return data;
						},
						resultTextLocator: function(response) {
							var result = '';

							if (!Lang.isUndefined(response.toString)) {
								result = response.toString();
							}
							else if (!Lang.isUndefined(response.responseText)) {
								result = response.responseText;
							}

							return result;
						},
						source: instance.createDataSource(siteListURL)
					}
				);

				siteList.on('results', instance._updateSiteList);

				instance._siteList = siteList;

				instance._messages = A.one(config.messages);
			},

			_updateSiteList: function(event) {
				var instance = this;

				var data = A.JSON.parse(event.data.responseText);

				var results = data.sites;
				var count = data.count;

				var buffer = [];

				var getSiteActionHtml = function(actionClassNames, actionLinkClassName, actionTitle, actionURL) {
					var siteActionTemplate = '<span class="{actionClassNames}" title="{actionTitle}">' +
							'<a class="{actionLinkClassName}" href="{actionURL}">' +
							'</a>' +
						'</span>';

					return	A.Lang.sub(
						siteActionTemplate,
						{
							actionClassNames: actionClassNames,
							actionLinkClassName: actionLinkClassName,
							actionTitle: actionTitle,
							actionURL: actionURL
						}
					);
				};

				if (results.length == 0) {
					buffer.push(
						'<li class="empty">' + Liferay.Language.get('there-are-no-results') + '</li>'
					);
				}
				else {
					var siteTemplate = '<li class="{classNames}">' +
							'{favoriteHTML}' +
							'<span class="name">{siteName}</span>' +
						'</li>';

					buffer.push(
						A.Array.map(
							results,
							function(result) {
								var classNames = [];

								if (result.socialOfficeGroup) {
									classNames.push('social-office-enabled');
								}

								if (!result.joinURL) {
									classNames.push('member');
								}

								var favoriteHTML;

								if (result.favoriteURL == '') {
									favoriteHTML = getSiteActionHtml('favorite', 'disabled', Liferay.Language.get('you-must-be-a-member-of-the-site-to-add-to-favorites'), '#');
								}
								else if (result.favoriteURL) {
									favoriteHTML = getSiteActionHtml('action favorite', '', Liferay.Language.get('add-to-favorites'), result.favoriteURL);
								}
								else {
									favoriteHTML = getSiteActionHtml('action unfavorite', '', Liferay.Language.get('remove-from-favorites'), result.unfavoriteURL);
								}

								var name = result.name;

								if (result.publicLayoutsURL) {
									name = '<a href="' + result.publicLayoutsURL + '">' + name + '</a>';

									if (result.privateLayoutsURL) {
										name += '<a class="private-pages" href="' + result.privateLayoutsURL + '"> (' + Liferay.Language.get('private-pages') + ')</a>';
									}
								}
								else if (!result.publicLayoutsURL && result.privateLayoutsURL) {
									name = '<a href="' + result.privateLayoutsURL + '">' + name + '</a>';
								}

								return A.Lang.sub(
									siteTemplate,
									{
										classNames: classNames.join(' '),
										favoriteHTML: favoriteHTML,
										siteName: name
									}
								);
							}
						).join('')
					);

					if (count > results.length) {
						buffer.push(
							'<li class="more">' +
								'<a href="javascript:;">' + Liferay.Language.get('view-all') + ' (' + count + ')' + '</a>' +
							'</li>'
						);
					}
				}

				if (instance._listNode) {
					instance._listNode.html(buffer.join(''));
				}
			}
		};

		Liferay.on(
			'sessionExpired',
			function(event) {
				var reload = function() {
					window.location.reload();
				};

				Liferay.SO.Sites.displayPopup = reload;
				Liferay.SO.Sites.updateSites = reload;
			}
		);
	}
);