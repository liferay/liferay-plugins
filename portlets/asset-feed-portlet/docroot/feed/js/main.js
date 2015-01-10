AUI.add(
	'liferay-asset-feed',
	function(A) {
		var Lang = A.Lang;

		var moment = A.config.win.moment;

		var TPL_POST = '<div class="asset-card">' +
			'<div class="card-header">' +
				'<a href="{creatorURL}">' +
					'<img alt="{creatorName}" class="creator-thumbnail" src="{portraitURL}" />' +
				'</a>' +
				'<div class="header-text">' +
					'<div>' +
						'<a class="creator-name" href="{creatorURL}">' +
							'{creatorName}' +
						'</a>' +
					'</div>' +
					'<span class="creator-date">' +
						'{createTime}' +
					'</span>' +
				'</div>' +
			'</div>' +
			'<div class="card-content">' +
				'<div class="content-text">' +
					'{textContent}' +
				'</div>' +
			'</div>' +
			'{linkPreview}' +
		'</div>';

		var AssetFeed = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				NAME: 'assetfeed',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._locale = config.locale;

						instance._contentNode = instance.byId(config.contentNode);

						instance._timeZoneOffset = Lang.toInt(config.timeZoneOffset);

						instance._moment = moment;

						instance._moment.locale(instance._locale);

						instance._lastAccessTime = 0;
						instance._paginatorEnd = 20;
						instance._paginatorStart = 0;
					},

					renderUI: function() {
						var instance = this;

						instance.sendDataRequest();
					},

					renderPosts: function(source) {
						var instance = this;

						if (source) {
							var postHTML = A.Array.map(source, A.bind('renderTextPost', instance));

							instance._contentNode.prepend(postHTML.join(''));
						}
					},

					renderTextPost: function(entryData) {
						var instance = this;

						var modifiedTime = entryData.modifiedTime;

						var momentModifiedTime = instance._moment(modifiedTime);

						var createTime = momentModifiedTime.fromNow();

						var oneWeek = (instance._moment().diff(modifiedTime, 'days') < 7);

						if (!oneWeek) {
							createTime = momentModifiedTime.zone(instance._timeZoneOffset).format('llll');
						}

						var payload = A.JSON.parse(entryData.payload);

						var linkPreview = '';

						var linkData = payload.linkData;

						if (linkData) {
							linkData = A.JSON.parse(linkData);

							linkPreview = Liferay.AssetFeedShared.getLinkPreview(linkData);
						}

						return Lang.sub(
							TPL_POST,
							{
								createTime: createTime,
								creatorName: entryData.creator.creatorFullName,
								creatorURL: entryData.creator.creatorURL,
								linkPreview: linkPreview,
								portraitURL: entryData.creator.creatorPortraitURL,
								textContent: payload.message
							}
						);
					},

					sendDataRequest: function() {
						var instance = this;

						Liferay.Service(
							'/asset-entry-set-portlet.assetentryset/get-asset-entry-sets',
							{
								end: instance._paginatorEnd,
								lastAccessTime: instance._lastAccessTime,
								parentAssetEntrySetId: 0,
								start: instance._paginatorStart
							},
							function(result) {
								if (A.Object.hasKey(result[0], 'assetEntrySetId')) {
									instance._lastAccessTime = new Date().valueOf();

									instance.renderPosts(result);

									instance._paginatorEnd += 20;
									instance._paginatorStart += 20;
								}
							}
						);
					}
				}
			}
		);

		Liferay.AssetFeed = AssetFeed;
	},
	'',
	{
		requires: ['aui-io','liferay-portlet-base','liferay-portlet-url']
	}
);