AUI.add(
	'liferay-so-scroll',
	function(A) {
		var Lang = A.Lang,
			isNumber = Lang.isNumber,
			isString = Lang.isString,

			HOST = 'host',
			NAME = 'scroll',

			BOTTOM ='bottom',
			DELAY = 'delay',
			DOWN = 'down',
			EDGE_PROXIMITY = 'edgeProximity',
			LAST_STATE = 'lastState',
			MAX_COORDINATE = 'maxCoordinate',
			
			AVAILABLE = '-available',
			EDGE = '-edge',
			START = '-start',

			SCROLL = NAME;
		
		var Scroll = A.Component.create(
			{
				NAME: NAME,

				NS: NAME,

				ATTRS: {
					delay: {
						validator: isNumber,
						value: null
					},

					edgeProximity: {
						setter: function(val) {
							var instance = this;

							var value = 0;

							if (isNumber(val)) {
								value = val;
							}
							else if (isString(val)) {
								value = (Lang.toInt(val) / 100);
							}

							return value;
						},
						value: null
					},

					lastState: {
						value: {
							scrollTop: 0
						}
					},

					maxCoordinate: {
						value: null
					}
				},

				EXTENDS: A.Plugin.Base,

				prototype: {
					initializer: function(config) {
						var instance = this;

						var host = A.one(config.host);

						instance._host = host;

						instance._reset();

						host.on(SCROLL, A.bind(instance._onScroll, instance));
					},

					_onScroll: function(event) {
						var instance = this;

						window.e = event;

						var edgeProximity = instance.get(EDGE_PROXIMITY);
						var lastState = instance.get(LAST_STATE);
						var maxCoordinate = instance.get(MAX_COORDINATE);

						var node = instance._host._node;

						var scrollTop = node.scrollTop || node.scrollY;

						window.l = lastState;

						var maxCoordinateY = maxCoordinate.y;

						var isPercentage = (edgeProximity % 1) !== 0;

						var edgeProximityY = isPercentage ? (maxCoordinateY * edgeProximity) : edgeProximity;

						var scrolledDown = (scrollTop > lastState.scrollTop);
						
						var availableScrollY = (scrollTop - maxCoordinateY);

						var state = {
							availableScrollY: availableScrollY,
							scrollTop: scrollTop,
							scrolledDown: scrolledDown
						};

						if (scrolledDown) {
							instance.fire(DOWN, state);

							if ((availableScrollY + edgeProximityY) >= 0) {
								instance.fire(BOTTOM + EDGE, state);
							}

							if (availableScrollY > 0) {
								instance.fire(DOWN + AVAILABLE, state);

								if (lastState.availableScrollY < 1) {
									instance.fire(DOWN + AVAILABLE + START, state);
								}
							}

							if (!lastState.scrolledDown) {
								instance.fire(DOWN + START, state);
							}
						}

						if ((availableScrollY > 0) || (scrollTop < 0) ) {
							instance._reset();
						}

						instance.set(LAST_STATE, state);

						clearTimeout(instance._delay);

						instance._delay = setTimeout(instance._reset.bind(instance), instance.get(DELAY));
					},

					_reset: function() {
						var instance = this;

						var lastState = instance.get(LAST_STATE);

						lastState.availableScrollY = 0;

						instance.set(LAST_STATE, lastState);

						var host = instance._host;

						var node = host._node;

						var scrollY = node.scrollHeight || A.config.doc.documentElement.scrollHeight - A.config.doc.documentElement.clientHeight;

						instance.set(
							MAX_COORDINATE,
							{
								y: (scrollY)
							}
						);
					}
				}
			}
		);

		Liferay.namespace('SO');

		Liferay.SO.Scroll = Scroll;
	},
	'',
	{
		requires:['aui-base']
	}
);

AUI().use(
	'aui-base',
	'transition',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Activities = {
			addNewComment: function(commentsList, responseData) {
				var commentEntryTemplate =
					'<div class="comment-entry">' +
						'<div class="user-portrait">' +
							'<span class="avatar">' +
								'<a href={userDisplayURL}>' +
									'<img alt={userName} src={userPortraitURL} />' +
								'</a>' +
							'</span>' +
						'</div>' +
						'<div class="comment-body">' +
							'<span class="user-name"><a href={userDisplayURL}>{userName}</a></span>' +
							'<span class="message">{body}</span>' +
						'</div>' +
						'<div class="comment-info">' +
							'<span class="post-date">{modifiedDate} </span>' +
							'<span class="edit-comment">' +
								'<a data-mbMessageIdOrMicroblogsEntryId={mbMessageIdOrMicroblogsEntryId} href="javascript:;">' + Liferay.Language.get('edit') + '</a>' +
							'</span>' +
							'<span class="delete-comment">' +
								'<a data-mbMessageIdOrMicroblogsEntryId={mbMessageIdOrMicroblogsEntryId} href="javascript:;">' + Liferay.Language.get('delete') + '</a>' +
							'</span>' +
						'</div>' +
					'</div>';

				var commentEntryHtml = A.Lang.sub(
					commentEntryTemplate,
					{
						body: responseData.body,
						mbMessageIdOrMicroblogsEntryId: responseData.mbMessageIdOrMicroblogsEntryId,
						modifiedDate: responseData.modifiedDate,
						userDisplayURL: responseData.userDisplayURL ? responseData.userDisplayURL : '',
						userName: responseData.userName,
						userPortraitURL: responseData.userPortraitURL
					}
				);

				commentsList.append(commentEntryHtml);
			},

			toggleEntry: function(event, portletNamespace) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.one('#' + portletNamespace + entryId);

				var body = entry.one('.grouped-activity-body');
				var bodyContainer = entry.one('.grouped-activity-body-container');
				var control = entry.one('.toggle-entry');
				var subentryHeight = entry.one('.activity-subentry').outerHeight();

				var minHeight = (subentryHeight * 3) + 'px';

				var bodyHeight = minHeight;

				if (entry.hasClass('toggler-content-collapsed')) {
					entry.removeClass('toggler-content-collapsed');

					bodyContainer.setStyle('height', minHeight);
					bodyContainer.setStyle('maxHeight', 'none');

					bodyHeight = body.height() + 'px';

					if (control) {
						control.html(Liferay.Language.get('view-less'));
					}
				}
				else {
					entry.addClass('toggler-content-collapsed');

					if (control) {
						control.html(Liferay.Language.get('view-more'));
					}
				}

				bodyContainer.transition(
					{
						duration: 0.5,
						easing: 'ease-in-out',
						height: bodyHeight
					}
				);
			}
		};
	}
);