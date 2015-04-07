AUI.add(
	'liferay-so-scroll',
	function(A) {
		var Lang = A.Lang;

		var	isNumber = Lang.isNumber;
		var	isString = Lang.isString;

		var AVAILABLE = '-available';

		var BOTTOM = 'bottom';

		var DELAY = 'delay';

		var DOC_EL = A.config.doc.documentElement;

		var DOWN = 'down';

		var EDGE = '-edge';

		var EDGE_PROXIMITY = 'edgeProximity';

		var LAST_STATE = 'lastState';

		var MAX_COORDINATE = 'maxCoordinate';

		var SCROLL = 'scroll';

		var START = '-start';

		var Scroll = A.Component.create(
			{
				ATTRS: {
					delay: {
						validator: isNumber,
						value: null
					},

					edgeProximity: {
						setter: function(val) {
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

				NAME: SCROLL,

				NS: SCROLL,

				prototype: {
					initializer: function(config) {
						var instance = this;

						var host = A.one(config.host);

						instance._host = host;

						instance._resetFn();

						host.on(SCROLL, A.bind('_onScroll', instance));

						instance._createResetTask();

						instance.after('delayChange', instance._createResetTask);
					},

					_createResetTask: function() {
						var instance = this;

						instance._resetTask = A.debounce('_resetFn', instance.get(DELAY), instance);
					},

					_onScroll: function(event) {
						var instance = this;

						var edgeProximity = instance.get(EDGE_PROXIMITY);
						var lastState = instance.get(LAST_STATE);
						var maxCoordinate = instance.get(MAX_COORDINATE);

						var edgeProximityY = edgeProximity;

						var maxCoordinateY = maxCoordinate.y;

						var host = instance._host;

						var scrollTop = host.get('scrollTop') || host.get('scrollY') || 0;

						if (edgeProximity % 1) {
							edgeProximityY *= maxCoordinateY;
						}

						var scrolledDown = (scrollTop > lastState.scrollTop);

						var availableScrollY = (scrollTop - maxCoordinateY);

						var state = {
							availableScrollY: availableScrollY,
							scrolledDown: scrolledDown,
							scrollTop: scrollTop
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

						if ((availableScrollY > 0) || (scrollTop < 0)) {
							instance._resetFn();
						}

						instance.set(LAST_STATE, state);

						instance._resetTask();
					},

					_resetFn: function() {
						var instance = this;

						var lastState = instance.get(LAST_STATE);

						lastState.availableScrollY = 0;

						instance.set(LAST_STATE, lastState);

						var scrollY = instance._host._node.scrollHeight || DOC_EL.scrollHeight - DOC_EL.clientHeight;

						instance.set(
							MAX_COORDINATE,
							{
								y: scrollY
							}
						);
					}
				}
			}
		);

		Liferay.namespace('SO').Scroll = Scroll;
	},
	'',
	{
		requires: ['aui-base']
	}
);

AUI().use(
	'aui-base',
	'liferay-node',
	'transition',
	function(A) {
		var TPL_COMMENT_ENTRY = '<div class="comment-entry">' +
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
					'<span class="edit-comment {commentControlsClass}">' +
						'<a data-mbMessageIdOrMicroblogsEntryId={mbMessageIdOrMicroblogsEntryId} href="javascript:;">' + Liferay.Language.get('edit') + '</a>' +
					'</span>' +
					'<span class="delete-comment {commentControlsClass}">' +
						'<a data-mbMessageIdOrMicroblogsEntryId={mbMessageIdOrMicroblogsEntryId} href="javascript:;">' + Liferay.Language.get('delete') + '</a>' +
					'</span>' +
				'</div>' +
			'</div>';

		var Activities = {
			addNewComment: function(commentsList, responseData) {
				responseData.userDisplayURL = responseData.userDisplayURL || '';

				commentsList.append(A.Lang.sub(TPL_COMMENT_ENTRY, responseData));
			},

			toggleEntry: function(event, portletNamespace) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.byIdNS(portletNamespace, entryId);

				var body = entry.one('.grouped-activity-body');
				var bodyContainer = entry.one('.grouped-activity-body-container');
				var control = entry.one('.toggle-entry');
				var subentryHeight = entry.one('.activity-subentry').outerHeight();

				var minHeight = subentryHeight * 3;

				var bodyHeight = minHeight;

				var collapsed = entry.hasClass('toggler-content-collapsed');

				entry.toggleClass('toggler-content-collapsed', !collapsed);

				var viewText = '<i class="icon-expand-alt"></i><span> ' + Liferay.Language.get('view-more') + '</span>';

				if (collapsed) {
					bodyContainer.setStyles(
						{
							height: minHeight,
							maxHeight: 'none'
						}
					);

					bodyHeight = body.height();

					viewText = '<i class="icon-collapse-alt"></i><span> ' + Liferay.Language.get('view-less') + '</span>';
				}

				if (control) {
					control.html(viewText);
				}

				bodyContainer.transition(
					{
						duration: 0.5,
						easing: 'ease-in-out',
						height: bodyHeight + 'px'
					}
				);
			}
		};

		Liferay.namespace('SO').Activities = Activities;
	}
);