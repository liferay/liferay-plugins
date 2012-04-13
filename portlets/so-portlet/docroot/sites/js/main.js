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

					this._bindUIACBase();
					this._syncUIACBase();
				}
			}
		);

		Liferay.namespace('SO');

		Liferay.SO.SiteList = SiteList;
	},
	'',
	{
		requires: ['aui-base', 'autocomplete-base']
	}
);

AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	'datasource-io',
	'json-parse',
	'liferay-so-site-list',
	function(A) {
		var STR_UNDEFINED = 'undefined';

		Liferay.namespace('SO');

		Liferay.SO.Sites = {
			init: function(config) {
				var instance = this;

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

			createDataSource: function(url) {
				return new A.DataSource.IO(
					{
						ioConfig: {
							method: "post"
						},
						on: {
							request: function(event) {
								var sitesTabsContainer = A.one('.so-portlet-sites .sites-tabs');

								var tabs1 = 'all-sites';

								if (sitesTabsContainer) {
									tabs1 = sitesTabsContainer.one('select').get('value');
								}

								var data = event.request;

								event.cfg.data = {
									directory: data.directory || false,
									end: data.end || 0,
									keywords: data.keywords || '',
									searchTab: data.searchTab || tabs1,
									start: data.start || 0
								}
							}
						},
						source: url
					}
				)
			},

			disableButton: function(button) {
				button = button.one('input') || button;

				button.set('disabled', true);
				button.ancestor('.aui-button').addClass('aui-button-disabled');
			},

			displayPopup: function(url, title, data) {
				var instance = this;

				var viewportRegion = A.getBody().get('viewportRegion');

				var popup = instance.getPopup();

				popup.show();

				popup.set('title', title);

				popup.io.set('uri', url);
				popup.io.set('data', data);

				popup.io.start();
			},

			enableButton: function(button) {
				button = button.one('input') || button;

				button.set('disabled', false);
				button.ancestor('.aui-button').removeClass('aui-button-disabled');
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
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
					).plug(
						A.Plugin.IO,
						{autoLoad: false}
					).render();
				}

				return instance._popup;
			},

			createDirectoryList: function(directoryList) {
				var instance = this;

				instance._directoryList = directoryList;
			},

			updateSites: function(showSuccessMessage) {
				var instance = this;

				if (instance._directoryList) {
					instance._directoryList.sendRequest();
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

				var siteList = new Liferay.SO.SiteList(
					{
						inputNode: siteSearchInput,
						listNode: siteList,
						minQueryLength: 0,
						requestTemplate: function(query) {
							return {
								keywords: query
							}
						},
						resultTextLocator: function(response) {
							var result = '';

							if (typeof response.toString != STR_UNDEFINED) {
								result = response.toString();
							}
							else if (typeof response.responseText != STR_UNDEFINED) {
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

				if (results.length == 0) {
					buffer.push(
						'<li class="empty">' + Liferay.Language.get('there-are-no-results') + '</li>'
					);
				}
				else {
					var siteTemplate =
						'<li class="{classNames}">' +
							'{favoriteHtml}' +
							'<span class="name">{siteName}</span>' +
						'</li>';

					buffer.push(
						A.Array.map(
							results,
							function(result) {
								var classNames = [];

								if (result.socialOfficeEnabled) {
									classNames.push('social-office-enabled');
								}

								if (!result.joinUrl) {
									classNames.push('member');
								}

								var name = result.name;

								if (result.url) {
									name = '<a href="' + result.url + '">' + name + '</a>';
								}

								return A.Lang.sub(
									siteTemplate,
									{
										classNames: classNames.join(' '),
										favoriteHtml: (result.favoriteURL ? '<span class="action favorite"><a href="' + result.favoriteURL + '">' + Liferay.Language.get('favorite') + '</a></span>' : '<span class="action unfavorite"><a href="' + result.unfavoriteURL + '">' + Liferay.Language.get('unfavorite') + '</a></span>'),
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

				instance._listNode.html(buffer.join(''));
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