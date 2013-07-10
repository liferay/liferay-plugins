AUI.add(
	'liferay-scheduler',
	function(A) {
		var AArray = A.Array;
		var AObject = A.Object;
		var DateMath = A.DataType.DateMath;
		var Lang = A.Lang;

		var RecurrenceUtil = Liferay.RecurrenceUtil;
		var Workflow = Liferay.Workflow;

		var isBoolean = Lang.isBoolean;
		var isDate = Lang.isDate;
		var isFunction = Lang.isFunction;
		var isObject = Lang.isObject;
		var isValue = Lang.isValue;

		var toInitialCap = A.cached(
			function(str) {
				return str.substring(0, 1).toUpperCase() + str.substring(1);
			}
		);

		var toInt = function(value) {
			return Lang.toInt(value, 10, 0);
		};

		var STR_BLANK = '';

		var STR_COMMA_SPACE = ', ';

		var STR_DASH = '-';

		var STR_SPACE = ' ';

		var TPL_INVITEES_URL = '{inviteesURL}&{portletNamespace}parentCalendarBookingId={calendarBookingId}';

		var TPL_RENDERING_RULES_URL = '{renderingRulesURL}&{portletNamespace}calendarIds={calendarIds}&{portletNamespace}startTime={startTime}&{portletNamespace}endTime={endTime}&{portletNamespace}ruleName={ruleName}';

		var CONTROLS_NODE = 'controlsNode';

		var ICON_CREATE_EVENT_NODE = 'iconCreateEventNode';

		var TPL_ICON_CREATE_EVENT_NODE = '<button type="button" class="btn btn-primary calendar-create-event-btn">' +
											'<i class="icon-plus icon-white"></i> ' + Liferay.Language.get('new-event') +
										 '</button>';

		var COMPANY_GROUP_ID = toInt(themeDisplay.getCompanyGroupId());

		var COMPANY_ID = toInt(themeDisplay.getCompanyId());

		var GROUP_ID = toInt(themeDisplay.getScopeGroupId());

		var USER_ID = toInt(themeDisplay.getUserId());

		var Time = {
			DAY: 86400000,
			HOUR: 3600000,
			MINUTE: 60000,
			SECOND: 1000,
			WEEK: 604800000,

			TIME_DESC: ['weeks', 'days', 'hours', 'minutes'],

			getDescription: function(milliseconds) {
				var instance = this;

				var desc = 'minutes';
				var value = 0;

				if (milliseconds > 0) {
					AArray.some(
						[ Time.WEEK, Time.DAY, Time.HOUR, Time.MINUTE ],
						function(item, index, collection) {
							value = milliseconds / item;
							desc = Time.TIME_DESC[index];

							return (milliseconds % item === 0);
						}
					);
				}

				return {
					desc: desc,
					value: value
				};
			}
		};

		Liferay.Time = Time;

		var CalendarUtil = {
			INVITEES_URL: null,
			INVOKER_URL: '/api/jsonws/invoke',
			NOTIFICATION_DEFAULT_TYPE: 'email',
			PORTLET_NAMESPACE: STR_BLANK,
			RENDERING_RULES_URL: null,
			USER_TIMEZONE_OFFSET: 0,

			availableCalendars: {},
			visibleCalendars: {},

			addEvent: function(schedulerEvent) {
				var instance = this;

				var allDay = schedulerEvent.get('allDay');
				var startDate = schedulerEvent.get('startDate');
				var endDate = schedulerEvent.get('endDate');

				if (allDay) {
					startDate = instance.toUTCWithoutUserTimeZone(startDate);
					endDate = instance.toUTCWithoutUserTimeZone(endDate);
				}
				else {
					startDate = instance.toUTC(startDate);
					endDate = instance.toUTC(endDate);
				}

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/add-calendar-booking': {
							allDay: allDay,
							calendarId: schedulerEvent.get('calendarId'),
							childCalendarIds: STR_BLANK,
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endTime: endDate.getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							parentCalendarBookingId: schedulerEvent.get('parentCalendarBookingId'),
							recurrence: schedulerEvent.get('recurrence'),
							secondReminder: schedulerEvent.get('secondReminder'),
							secondReminderType: schedulerEvent.get('secondReminderType'),
							startTime: startDate.getTime(),
							titleMap: instance.getLocalizationMap(schedulerEvent.get('content'))
						}
					},
					{
						failure: function() {
							instance.destroyEvent(schedulerEvent);
						},

						start: function() {
							schedulerEvent.set(
								'loading',
								true,
								{
									silent: true
								}
							);
						},

						success: function(data) {
							schedulerEvent.set(
								'loading',
								false,
								{
									silent: true
								}
							);

							if (data) {
								if (data.exception) {
									instance.destroyEvent(schedulerEvent);
								}
								else {
									instance.setEventAttrs(schedulerEvent, data);
								}
							}
						}
					}
				);
			},

			createCalendarsAutoComplete: function(resourceURL, input, afterSelectFn) {
				var instance = this;

				input.plug(
					A.Plugin.AutoComplete,
					{
						activateFirstItem: true,
						after: {
							select: afterSelectFn
						},
						maxResults: 20,
						requestTemplate: '&' + instance.PORTLET_NAMESPACE + 'keywords={query}',
						resultFilters: function(query, results) {
							return AArray.filter(
								results,
								function(item, index, collection) {
									return !instance.availableCalendars[item.raw.calendarId];
								}
							);
						},
						resultFormatter: function(query, results) {
							return AArray.map(
								results,
								function(result) {
									var calendar = result.raw;
									var name = calendar.name;
									var calendarResourceName = calendar.calendarResourceName;

									if (name !== calendarResourceName) {
										name = [calendarResourceName, STR_DASH, name].join(STR_SPACE);
									}

									return A.Highlight.words(name, query);
								}
							);
						},
						resultHighlighter: 'wordMatch',
						resultTextLocator: 'calendarResourceName',
						source: resourceURL
					}
				);
			},

			deleteEvent: function(schedulerEvent) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				var eventRecorder = scheduler.get('eventRecorder');

				eventRecorder.hidePopover();

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/move-calendar-booking-to-trash': {
							calendarBookingId: schedulerEvent.get('calendarBookingId')
						}
					},
					{
						success: function() {
							scheduler.load();
						}
					}
				);
			},

			deleteEventInstance: function(schedulerEvent, allFollowing) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');
				var eventRecorder = scheduler.get('eventRecorder');

				eventRecorder.hidePopover();

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/delete-calendar-booking-instance': {
							allFollowing: allFollowing,
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							startTime: CalendarUtil.toUTC(schedulerEvent.get('startDate')).getTime()
						}
					},
					{
						success: function() {
							scheduler.load();
						}
					}
				);
			},

			destroyEvent: function(schedulerEvent) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				scheduler.removeEvents(schedulerEvent);

				scheduler.syncEventsUI();
			},

			filterJSONArray: function(jsonArray, property, value) {
				var instance = this;

				var events = [];

				AArray.each(
					jsonArray,
					function(item, index, collection) {
						if (value === item[property]) {
							events.push(instance.toSchedulerEvent(item));
						}
					}
				);

				return events;
			},

			getCalendarBookingInvitees: function(calendarBookingId, callback) {
				var instance = this;

				var inviteesURL = Lang.sub(
					TPL_INVITEES_URL,
					{
						calendarBookingId: calendarBookingId,
						inviteesURL: instance.INVITEES_URL,
						portletNamespace: instance.PORTLET_NAMESPACE
					}
				);

				A.io.request(
					inviteesURL,
					{
						dataType: 'json',
						on: {
							success: function() {
								callback(this.get('responseData'));
							}
						}
					}
				);
			},

			getCalendarName: function(name, calendarResourceName) {
				var instance = this;

				if (name !== calendarResourceName) {
					name = [calendarResourceName, STR_DASH, name].join(STR_SPACE);
				}

				return name;
			},

			getCalendarRenderingRules: function(calendarIds, startDate, endDate, ruleName, callback) {
				var instance = this;

				var renderingRulesURL = Lang.sub(
					TPL_RENDERING_RULES_URL,
					{
						calendarIds: calendarIds.join(),
						endTime: endDate.getTime(),
						portletNamespace: instance.PORTLET_NAMESPACE,
						renderingRulesURL: instance.RENDERING_RULES_URL,
						ruleName: ruleName,
						startTime: startDate.getTime()
					}
				);

				A.io.request(
					renderingRulesURL,
					{
						dataType: 'json',
						on: {
							success: function() {
								callback(this.get('responseData'));
							}
						}
					}
				);
			},

			getDatesList: function(startDate, total) {
				var instance = this;

				var ADate = A.Date;

				var output = [];

				if (ADate.isValidDate(startDate)) {
					for (var i = 0; i < total; i++) {
						output.push(ADate.addDays(startDate, i));
					}
				}

				return output;
			},

			getDefaultUserCalendar: function() {
				var instance = this;

				return instance.availableCalendars[CalendarUtil.DEFAULT_USER_CALENDAR_ID];
			},

			getEvent: function(calendarBookingId, success, failure) {
				var instance = this;

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/get-calendar-booking': {
							calendarBookingId: calendarBookingId
						}
					},
					{
						failure: failure,
						success: success
					}
				);
			},

			getEvents: function(startDate, endDate, status, success, failure) {
				var instance = this;

				var calendarIds = AObject.keys(instance.availableCalendars);

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/search': {
							calendarIds: calendarIds.join(','),
							calendarResourceIds: STR_BLANK,
							companyId: COMPANY_ID,
							end: -1,
							endTime: endDate.getTime(),
							groupIds: [0, COMPANY_GROUP_ID, GROUP_ID].join(','),
							keywords: null,
							orderByComparator: null,
							parentCalendarBookingId: -1,
							recurring: true,
							start: Workflow.STATUS_APPROVED,
							startTime: startDate.getTime(),
							statuses: status.join(',')
						}
					},
					{
						failure: failure,
						success: success
					}
				);
			},

			getLocalizationMap: function(value) {
				var instance = this;

				var map = {};
				map[themeDisplay.getLanguageId()] = value;

				return A.JSON.stringify(map);
			},

			getStatusLabel: function(statusId) {
				var status = String.valueOf(statusId);

				if (CalendarWorkflow.STATUS_APPROVED === statusId) {
					status = Liferay.Language.get('accepted');
				}
				else if (CalendarWorkflow.STATUS_DENIED === statusId) {
					status = Liferay.Language.get('declined');
				}
				else if (CalendarWorkflow.STATUS_DRAFT === statusId) {
					status = Liferay.Language.get('draft');
				}
				else if (CalendarWorkflow.STATUS_MAYBE === statusId) {
					status = Liferay.Language.get('maybe');
				}
				else if (CalendarWorkflow.STATUS_PENDING === statusId) {
					status = Liferay.Language.get('pending');
				}

				return status;
			},

			invokeService: function(payload, callback) {
				var instance = this;

				callback = callback || {};

				A.io.request(
					instance.INVOKER_URL,
					{
						cache: false,
						data: {
							cmd: A.JSON.stringify(payload),
							p_auth: Liferay.authToken
						},
						dataType: 'json',
						on: {
							failure: callback.failure,
							start: callback.start,
							success: function(event) {
								if (callback.success) {
									var data = this.get('responseData');

									callback.success.apply(this, [data, event]);
								}
							}
						}
					}
				);
			},

			invokeTransition: function(schedulerEvent, status) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/invoke-transition': {
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							transitionName: instance.getStatusLabel(status).toLowerCase(),
							userId: USER_ID
						}
					},
					{
						start: function() {
							schedulerEvent.set(
								'loading',
								true,
								{
									silent: true
								}
							);
						},

						success: function(data) {
							schedulerEvent.set(
								'loading',
								false,
								{
									silent: true
								}
							);

							if (data && !data.exception && scheduler) {
								var eventRecorder = scheduler.get('eventRecorder');

								eventRecorder.hidePopover();

								scheduler.load();
							}
						}
					}
				);
			},

			message: function(msg) {
				var instance = this;

				A.oneNS(instance.PORTLET_NAMESPACE, '#message').html(msg);
			},

			setEventAttrs: function(schedulerEvent, data) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				var newCalendarId = data.calendarId;

				var oldCalendarId = schedulerEvent.get('calendarId');

				if (scheduler) {
					var oldCalendar = instance.availableCalendars[oldCalendarId];
					var newCalendar = instance.availableCalendars[newCalendarId];

					if (oldCalendar !== newCalendar) {
						oldCalendar.remove(schedulerEvent);
					}

					if (newCalendar) {
						newCalendar.add(schedulerEvent);
					}

					schedulerEvent.setAttrs(
						{
							calendarBookingId: data.calendarBookingId,
							calendarId: newCalendarId,
							calendarResourceId: data.calendarResourceId,
							parentCalendarBookingId: data.parentCalendarBookingId,
							recurrence: data.recurrence,
							status: data.status
						},
						{
							silent: true
						}
					);

					scheduler.syncEventsUI();
				}
			},

			syncCalendarsMap: function(calendarLists) {
				var instance = this;

				var visibleCalendars = instance.visibleCalendars = {};
				var availableCalendars = instance.availableCalendars = {};

				AArray.each(
					calendarLists,
					function(calendarList) {
						var calendars = calendarList.get('calendars');

						A.each(
							calendars,
							function(item, index, collection) {
								var calendarId = item.get('calendarId');

								availableCalendars[calendarId] = item;

								if (item.get('visible')) {
									visibleCalendars[calendarId] = item;
								}
							}
						);
					}
				);

				return availableCalendars;
			},

			toSchedulerEvent: function(calendarBooking) {
				var instance = this;

				var allDay = calendarBooking.allDay;
				var startDate = calendarBooking.startTime;
				var endDate = calendarBooking.endTime;

				if (allDay) {
					startDate = instance.toLocalTimeWithoutUserTimeZone(startDate);
					endDate = instance.toLocalTimeWithoutUserTimeZone(endDate);
				}
				else {
					startDate = instance.toLocalTime(startDate);
					endDate = instance.toLocalTime(endDate);
				}

				return new Liferay.SchedulerEvent(
					{
						allDay: allDay,
						calendarBookingId: calendarBooking.calendarBookingId,
						calendarId: calendarBooking.calendarId,
						content: calendarBooking.titleCurrentValue,
						description: calendarBooking.descriptionCurrentValue,
						endDate: endDate,
						firstReminder: calendarBooking.firstReminder,
						firstReminderType: calendarBooking.firstReminderType,
						location: calendarBooking.location,
						parentCalendarBookingId: calendarBooking.parentCalendarBookingId,
						recurrence: calendarBooking.recurrence,
						secondReminder: calendarBooking.secondReminder,
						secondReminderType: calendarBooking.secondReminderType,
						startDate: startDate,
						status: calendarBooking.status
					}
				);
			},

			toLocalTime: function(utc) {
				var instance = this;

				var date = instance.toLocalTimeWithoutUserTimeZone(utc);

				return DateMath.add(date, DateMath.MINUTES, instance.USER_TIMEZONE_OFFSET / DateMath.ONE_MINUTE_MS);
			},

			toLocalTimeWithoutUserTimeZone: function(utc) {
				var instance = this;

				if (!isDate(utc)) {
					utc = new Date(utc);
				}

				return DateMath.add(utc, DateMath.MINUTES, utc.getTimezoneOffset());
			},

			toUTC: function(date) {
				var instance = this;

				var utc = instance.toUTCWithoutUserTimeZone(date);

				return DateMath.subtract(utc, DateMath.MINUTES, instance.USER_TIMEZONE_OFFSET / DateMath.ONE_MINUTE_MS);
			},

			toUTCWithoutUserTimeZone: function(date) {
				var instance = this;

				if (!isDate(date)) {
					date = new Date(date);
				}

				return DateMath.subtract(date, DateMath.MINUTES, date.getTimezoneOffset());
			},

			updateEvent: function(schedulerEvent, success) {
				var instance = this;

				var allDay = schedulerEvent.get('allDay');
				var startDate = schedulerEvent.get('startDate');
				var endDate = schedulerEvent.get('endDate');

				if (allDay) {
					startDate = instance.toUTCWithoutUserTimeZone(startDate);
					endDate = instance.toUTCWithoutUserTimeZone(endDate);
				}
				else {
					startDate = instance.toUTC(startDate);
					endDate = instance.toUTC(endDate);
				}

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/update-calendar-booking': {
							allDay: allDay,
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							calendarId: schedulerEvent.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endTime: endDate.getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							recurrence: schedulerEvent.get('recurrence'),
							secondReminder: schedulerEvent.get('secondReminder'),
							secondReminderType: schedulerEvent.get('secondReminderType'),
							startTime: startDate.getTime(),
							status: schedulerEvent.get('status'),
							titleMap: instance.getLocalizationMap(schedulerEvent.get('content')),
							userId: USER_ID
						}
					},
					{
						start: function() {
							schedulerEvent.set(
								'loading',
								true,
								{
									silent: true
								}
							);
						},

						success: function(data) {
							schedulerEvent.set(
								'loading',
								false,
								{
									silent: true
								}
							);

							if (data) {
								if (data.exception) {
									return;
								}
								else {
									instance.setEventAttrs(schedulerEvent, data);
								}
							}

							if (success) {
								success.call(this, data);
							}
						}
					}
				);
			},

			updateEventInstance: function(schedulerEvent, allFollowing, success) {
				var instance = this;

				instance.invokeService(
					{
						'/calendar-portlet/calendarbooking/update-calendar-booking-instance': {
							allDay: schedulerEvent.get('allDay'),
							allFollowing: allFollowing,
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							calendarId: schedulerEvent.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endTime: instance.toUTC(schedulerEvent.get('endDate')).getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							recurrence: schedulerEvent.get('recurrence'),
							secondReminder: schedulerEvent.get('secondReminder'),
							secondReminderType: schedulerEvent.get('secondReminderType'),
							startTime: instance.toUTC(schedulerEvent.get('startDate')).getTime(),
							status: schedulerEvent.get('status'),
							titleMap: instance.getLocalizationMap(schedulerEvent.get('content')),
							userId: USER_ID
						}
					},
					{
						start: function() {
							schedulerEvent.set(
								'loading',
								true,
								{
									silent: true
								}
							);
						},

						success: function(data) {
							schedulerEvent.set(
								'loading',
								false,
								{
									silent: true
								}
							);

							if (data) {
								if (data.exception) {
									return;
								}
								else {
									instance.setEventAttrs(schedulerEvent, data);
								}
							}

							if (success) {
								success.call(this, data);
							}
						}
					}
				);
			}
		};

		Liferay.CalendarUtil = CalendarUtil;

		var CalendarWorkflow = {
			STATUS_MAYBE: 9
		};

		A.mix(CalendarWorkflow, Workflow);

		Liferay.CalendarWorkflow = CalendarWorkflow;

		var SchedulerModelSync = function(config) {};

		SchedulerModelSync.prototype = {
			sync: function(action, options, callback) {
				var instance = this;

				var actionMethod = instance['_do' + toInitialCap(action)];

				if (isFunction(actionMethod)) {
					actionMethod.apply(instance, [options, callback]);
				}
			},

			_doRead: function() {
				var instance = this;

				var args = arguments;

				var callback = args[args.length - 1];

				if (isFunction(callback)) {
					callback();
				}
			}
		};

		Liferay.SchedulerModelSync = SchedulerModelSync;

		var SchedulerEvent = A.Component.create(
			{
				ATTRS: {
					calendarBookingId: {
						setter: toInt,
						value: 0
					},

					calendarId: {
						setter: toInt,
						value: 0
					},

					description: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					editingEvent: {
						validator: isBoolean,
						value: false
					},

					firstReminder: {
						setter: toInt,
						value: 0
					},

					firstReminderType: {
						setter: String,
						validator: isValue,
						value: CalendarUtil.NOTIFICATION_DEFAULT_TYPE
					},

					loading: {
						validator: isBoolean,
						value: false
					},

					location: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					parentCalendarBookingId: {
						setter: toInt,
						value: 0
					},

					recurrence: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					reminder: {
						getter: function() {
							var instance = this;

							return (instance.get('firstReminder') > 0) || (instance.get('secondReminder') > 0);
						}
					},

					repeated: {
						getter: 'isRecurring'
					},

					secondReminder: {
						setter: toInt,
						value: 0
					},

					secondReminderType: {
						setter: String,
						validator: isValue,
						value: CalendarUtil.NOTIFICATION_DEFAULT_TYPE
					},

					status: {
						setter: toInt,
						value: 0
					}
				},

				EXTENDS: A.SchedulerEvent,

				NAME: 'scheduler-event',

				PROPAGATE_ATTRS: A.SchedulerEvent.PROPAGATE_ATTRS.concat(['calendarBookingId', 'calendarId', 'calendarResourceId', 'parentCalendarBookingId', 'recurrence', 'status']),

				prototype: {
					eventModel: Liferay.SchedulerEvent,

					initializer: function() {
						var instance = this;

						instance._uiSetLoading(instance.get('loading'));
						instance._uiSetStartDate(instance.get('startDate'));
						instance._uiSetStatus(instance.get('status'));

						instance.on('loadingChange', instance._onLoadingChange);
						instance.on('startDateChange', instance._onStartDateChange);
						instance.on('statusChange', instance._onStatusChange);
					},

					isMasterBooking: function() {
						var instance = this;

						return (instance.get('parentCalendarBookingId') === instance.get('calendarBookingId'));
					},

					isRecurring: function() {
						var instance = this;

						return (instance.get('recurrence') !== STR_BLANK);
					},

					syncNodeColorUI: function() {
						var instance = this;

						Liferay.SchedulerEvent.superclass.syncNodeColorUI.apply(instance, arguments);

						var node = instance.get('node');
						var scheduler = instance.get('scheduler');

						if (scheduler && !instance.get('editingEvent')) {
							var activeViewName = scheduler.get('activeView').get('name');

							if ((activeViewName === 'month') && !instance.get('allDay')) {
								node.setStyles(
									{
										backgroundColor: instance.get('color'),
										border: 'none',
										color: '#111',
										padding: '0 2px'
									}
								);
							}
						}
					},

					_onLoadingChange: function(event) {
						var instance = this;

						instance._uiSetLoading(event.newVal);
					},

					_onStartDateChange: function(event) {
						var instance = this;

						instance._uiSetStartDate(event.newVal);
					},

					_onStatusChange: function(event) {
						var instance = this;

						instance._uiSetStatus(event.newVal);
					},

					_uiSetEndDate: function(val) {
						var instance = this;

						Liferay.SchedulerEvent.superclass._uiSetEndDate.apply(instance, arguments);

						var node = instance.get('node');

						node.attr('data-endDate', instance._formatDate(val, '%d-%m-%y %H:%M'));
					},

					_uiSetLoading: function(val) {
						var instance = this;

						instance.get('node').toggleClass('calendar-portlet-event-loading', val);
					},

					_uiSetStartDate: function(val) {
						var instance = this;

						var node = instance.get('node');

						node.attr('data-startDate', instance._formatDate(val, '%d-%m-%y %H:%M'));
					},

					_uiSetStatus: function(val) {
						var instance = this;

						var node = instance.get('node');

						node.toggleClass('calendar-portlet-event-approved', (val === CalendarWorkflow.STATUS_APPROVED));
						node.toggleClass('calendar-portlet-event-maybe', (val === CalendarWorkflow.STATUS_MAYBE));
						node.toggleClass('calendar-portlet-event-pending', (val === CalendarWorkflow.STATUS_PENDING));
					}
				}
			}
		);

		Liferay.SchedulerEvent = SchedulerEvent;

		var Calendar = A.Component.create(
			{
				ATTRS: {
					calendarId: {
						value: 0,
						setter: toInt
					},

					calendarResourceId: {
						value: 0,
						setter: toInt
					},

					calendarResourceName: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					classNameId: {
						value: 0,
						setter: toInt
					},

					classPK: {
						value: 0,
						setter: toInt
					},

					defaultCalendar: {
						setter: A.DataType.Boolean.parse,
						value: false
					},

					global: {
						setter: A.DataType.Boolean.parse,
						value: false
					},

					groupId: {
						value: 0,
						setter: toInt
					},

					permissions: {
						lazyAdd: false,
						setter: function(val) {
							var instance = this;

							instance.set('disabled', !val.MANAGE_BOOKINGS);

							return val;
						},
						value: {},
						validator: isObject
					},

					resourceGroupId: {
						setter: toInt,
						value: 0
					}
				},

				EXTENDS: A.SchedulerCalendar,

				NAME: 'scheduler-calendar',

				prototype: {
					getDisplayName: function() {
						var instance = this;

						var calendarResourceName = instance.get('calendarResourceName');
						var name = instance.get('name');

						return CalendarUtil.getCalendarName(name, calendarResourceName);
					},

					_afterColorChange: function(event) {
						var instance = this;

						Calendar.superclass._afterColorChange.apply(instance, arguments);

						var calendarId = instance.get('calendarId');

						var color = event.newVal;

						if (instance.get('permissions.UPDATE')) {
							CalendarUtil.invokeService(
								{
									'/calendar-portlet/calendar/update-color': {
										calendarId: calendarId,
										color: parseInt(color.substr(1), 16)
									}
								}
							);
						}
						else {
							Liferay.Store('calendar-portlet-calendar-' + calendarId + '-color', color);
						}
					},

					_afterVisibleChange: function(event) {
						var instance = this;

						Calendar.superclass._afterVisibleChange.apply(instance, arguments);

						var scheduler = instance.get('scheduler');

						scheduler.syncEventsUI();
					}
				}
			}
		);

		Liferay.SchedulerCalendar = Calendar;

		Liferay.SchedulerEvents = A.Base.create(
			'scheduler-events',
			A.SchedulerEvents,
			[Liferay.SchedulerModelSync],
			{
				getLoadEndDate: function(activeView) {
					var instance = this;

					var date = activeView.getNextDate();

					var viewName = activeView.get('name');

					if (viewName === 'agenda') {
						date = DateMath.add(date, DateMath.MONTH, 1);
					}
					else if (viewName === 'month') {
						date = DateMath.add(date, DateMath.WEEK, 1);
					}

					return CalendarUtil.toUTC(date);
				},

				getLoadStartDate: function(activeView) {
					var instance = this;

					var scheduler = activeView.get('scheduler');
					var viewName = activeView.get('name');

					var date = scheduler.get('viewDate');

					if (viewName === 'month') {
						date = DateMath.subtract(date, DateMath.WEEK, 1);
					}

					return CalendarUtil.toUTC(date);
				},

				_doRead: function(options, callback) {
					var instance = this;

					var scheduler = instance.get('scheduler');

					var activeView = scheduler.get('activeView');
					var filterCalendarBookings = scheduler.get('filterCalendarBookings');

					CalendarUtil.message(Liferay.Language.get('loading'));

					CalendarUtil.getEvents(
						instance.getLoadStartDate(activeView),
						instance.getLoadEndDate(activeView),
						[CalendarWorkflow.STATUS_APPROVED, CalendarWorkflow.STATUS_MAYBE, CalendarWorkflow.STATUS_PENDING],
						function(calendarBookings) {
							if (filterCalendarBookings) {
								calendarBookings = AArray.filter(calendarBookings, filterCalendarBookings);
							}

							callback(null, calendarBookings);
						}
					);
				}
			},
			{}
		);

		var Scheduler = A.Component.create(
			{
				ATTRS: {
					filterCalendarBookings: {
						validator: isFunction
					},

					iconCreateEventNode: {
						valueFn: function() {
							return A.Node.create(TPL_ICON_CREATE_EVENT_NODE);
						}
					},

					portletNamespace: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					preventPersistence: {
						validator: isBoolean,
						value: false
					},

					showNewEventBtn: {
						validator: isBoolean,
						value: true
					}
				},

				EXTENDS: A.Scheduler,

				NAME: 'scheduler-base',

				prototype: {
					calendarModel: Liferay.SchedulerCalendar,
					eventModel: Liferay.SchedulerEvent,
					eventsModel: Liferay.SchedulerEvents,

					renderUI: function() {
						var instance = this;

						Scheduler.superclass.renderUI.apply(this, arguments);

						var showNewEventBtn = instance.get('showNewEventBtn');

						if (showNewEventBtn) {
							instance[ICON_CREATE_EVENT_NODE] = instance.get(ICON_CREATE_EVENT_NODE);

							instance[CONTROLS_NODE].prepend(instance[ICON_CREATE_EVENT_NODE]);

							instance[ICON_CREATE_EVENT_NODE].on('click', instance._onClickCreateEvent, instance);
						}
					},

					bindUI: function() {
						var instance = this;

						instance.after(
							{
								'scheduler-base:dateChange': instance._afterDateChange,
								'scheduler-event:change': instance._afterSchedulerEventChange
							}
						);

						instance.on(
							{
								'*:load': instance._onLoadSchedulerEvents,
								'scheduler-event-recorder:delete': instance._onDeleteEvent,
								'scheduler-event-recorder:save': instance._onSaveEvent
							}
						);

						Scheduler.superclass.bindUI.apply(this, arguments);
					},

					load: function() {
						var instance = this;

						var events = instance._events;

						return events.load.apply(events, arguments);
					},

					plotCalendarBookings: function(calendarBookings) {
						var instance = this;

						var events = [];
						var calendarEvents = {};

						AArray.each(
							calendarBookings,
							function(item, index, collection) {
								var calendarId = item.calendarId;

								if (!calendarEvents[calendarId]) {
									calendarEvents[calendarId] = [];
								}

								var schedulerEvent = CalendarUtil.toSchedulerEvent(item);

								schedulerEvent.set(
									'scheduler',
									instance,
									{
										silent: true
									}
								);

								events.push(schedulerEvent);
								calendarEvents[calendarId].push(schedulerEvent);
							}
						);

						instance.resetEvents(events);

						A.each(
							Liferay.CalendarUtil.availableCalendars,
							function(item, index, collection) {
								item.reset(calendarEvents[index]);
							}
						);

						if (instance.get('rendered')) {
							instance.syncEventsUI();
						}

						CalendarUtil.message(STR_BLANK);
					},

					sync: function() {
						var instance = this;

						var events = instance._events;

						return events.sync.apply(events, arguments);
					},

					_afterActiveViewChange: function(event) {
						var instance = this;

						Scheduler.superclass._afterActiveViewChange.apply(this, arguments);

						instance.load();
					},

					_afterDateChange: function(event) {
						var instance = this;

						instance.load();
					},

					_afterSchedulerEventChange: function(event) {
						var instance = this;

						if (!instance.get('preventPersistence')) {
							var changed = event.changed;

							var persistentAttrMap = {
								calendarId: 1,
								color: 1,
								content: 1,
								endDate: 1,
								startDate: 1
							};

							var persist = true;

							A.each(
								changed,
								function(item, index, collection) {
									persist = AObject.owns(persistentAttrMap, index);
								}
							);

							if (persist) {
								var schedulerEvent = event.target;
								var calendarBookingId = schedulerEvent.get('calendarBookingId');

								if (schedulerEvent.isRecurring()) {
									Liferay.RecurrenceUtil.openConfirmationPanel(
										'update',
										schedulerEvent.isMasterBooking(),
										function() {
											CalendarUtil.updateEventInstance(schedulerEvent, false);

											this.close();
										},
										function() {
											CalendarUtil.updateEventInstance(
												schedulerEvent,
												true,
												function() {
													instance.load();
												}
											);

											this.close();
										},
										function() {
											CalendarUtil.getEvent(
												calendarBookingId,
												function(calendarBooking) {
													var newSchedulerEvent = CalendarUtil.toSchedulerEvent(calendarBooking);

													newSchedulerEvent.copyPropagateAttrValues(
														schedulerEvent,
														null,
														{
															silent: true
														}
													);

													var schedulerEventDuration = schedulerEvent.getSecondsDuration() * 1000;

													var calendarEndTime = calendarBooking.startTime + schedulerEventDuration;
													var calendarStartTime = calendarBooking.startTime;

													var changedStartDate = changed.startDate;

													if (changedStartDate) {
														var offset = 0;
														var newVal = changedStartDate.newVal;
														var prevVal = changedStartDate.prevVal;

														if (isDate(newVal) && isDate(prevVal)) {
															offset = newVal.getTime() - prevVal.getTime();
														}

														calendarStartTime = calendarStartTime + offset;
														calendarEndTime = calendarStartTime + schedulerEventDuration;
													}

													newSchedulerEvent.setAttrs(
														{
															endDate: CalendarUtil.toLocalTime(calendarEndTime),
															startDate: CalendarUtil.toLocalTime(calendarStartTime)
														}
													);

													CalendarUtil.updateEvent(
														newSchedulerEvent,
														function() {
															instance.load();
														}
													);
												}
											);

											this.close();
										},
										function() {
											instance.load();

											this.close();
										}
									);
								}
								else if (schedulerEvent.isMasterBooking()) {
									CalendarUtil.updateEvent(schedulerEvent);
								}
								else {
									var calendar = Liferay.CalendarUtil.availableCalendars[schedulerEvent.get('calendarId')];

									var content = [
										'<p class="calendar-portlet-confirmation-text">',
										Lang.sub(
											Liferay.Language.get('you-are-about-to-make-changes-that-will-only-effect-your-calendar-x'),
											[calendar.get('name')]
										),
										'</p>'
									].join(STR_BLANK);

									Liferay.CalendarMessageUtil.confirm(
										content,
										Liferay.Language.get('continue'),
										Liferay.Language.get('dont-change-the-event'),
										function() {
											CalendarUtil.updateEvent(schedulerEvent);

											this.close();
										},
										function() {
											instance.load();

											this.close();
										}
									);
								}
							}
						}
					},

					_onClickCreateEvent: function(event) {
						var instance = this;

						var recorder = instance.get('eventRecorder');

						var activeViewName = instance.get('activeView').get('name');

						var defaultUserCalendar = CalendarUtil.getDefaultUserCalendar();

						var calendarId = defaultUserCalendar.get('calendarId');

						var editCalendarBookingURL = decodeURIComponent(recorder.get('editCalendarBookingURL'));

						Liferay.Util.openWindow(
							{
								dialog: {
									after: {
										destroy: function(event) {
											instance.load();
										}
									},
									destroyOnHide: true,
									modal: true
								},
								title: Liferay.Language.get('add-event'),
								uri: Lang.sub(
									editCalendarBookingURL,
									{
										activeView: activeViewName,
										calendarId: calendarId
									}
								)
							}
						);
					},

					_onDeleteEvent: function(event) {
						var instance = this;

						var schedulerEvent = event.schedulerEvent;

						if (schedulerEvent.isRecurring()) {
							RecurrenceUtil.openConfirmationPanel(
								'delete',
								schedulerEvent.isMasterBooking(),
								function() {
									CalendarUtil.deleteEventInstance(schedulerEvent, false);

									RecurrenceUtil.closeConfirmationPanel();
								},
								function() {
									CalendarUtil.deleteEventInstance(schedulerEvent, true);

									RecurrenceUtil.closeConfirmationPanel();
								},
								function() {
									CalendarUtil.deleteEvent(schedulerEvent);

									RecurrenceUtil.closeConfirmationPanel();
								}
							);
						}
						else if (schedulerEvent.isMasterBooking() && confirm(Liferay.Language.get('deleting-this-event-will-cancel-the-meeting-with-your-guests-would-you-like-to-delete'))) {
							CalendarUtil.deleteEvent(schedulerEvent);
						}

						event.preventDefault();
					},

					_onLoadSchedulerEvents: function(event) {
						var instance = this;

						instance.plotCalendarBookings(event.parsed);
					},

					_onSaveEvent: function(event) {
						var instance = this;

						CalendarUtil.addEvent(event.newSchedulerEvent);
					}
				}
			}
		);

		Liferay.Scheduler = Scheduler;

		var SchedulerEventRecorder = A.Component.create(
			{
				ATTRS: {
					calendarId: {
						setter: toInt,
						value: 0
					},

					editCalendarBookingURL: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					permissionsCalendarBookingURL: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					portletNamespace: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					},

					status: {
						setter: toInt,
						value: CalendarWorkflow.STATUS_DRAFT
					},

					toolbar: {
						value: {
							children: []
						}
					},

					viewCalendarBookingURL: {
						setter: String,
						validator: isValue,
						value: STR_BLANK
					}
				},

				EXTENDS: A.SchedulerEventRecorder,

				NAME: 'scheduler-event-recorder',

				prototype: {
					getTemplateData: function() {
						var instance = this;

						var editing = true;

						var schedulerEvent = instance.get('event');

						if (!schedulerEvent) {
							editing = false;

							schedulerEvent = instance;
						}

						var availableCalendars = CalendarUtil.availableCalendars;

						var calendar = availableCalendars[schedulerEvent.get('calendarId')];

						var permissions = calendar.get('permissions');

						var templateData = SchedulerEventRecorder.superclass.getTemplateData.apply(this, arguments);

						return A.merge(
							templateData,
							{
								allDay: schedulerEvent.get('allDay'),
								availableCalendars: availableCalendars,
								calendar: calendar,
								calendarIds: AObject.keys(availableCalendars),
								editing: editing,
								endTime: templateData.endDate,
								permissions: permissions,
								startTime: templateData.startDate,
								status: CalendarUtil.getStatusLabel(schedulerEvent.get('status'))
							}
						);
					},

					getUpdatedSchedulerEvent: function(optAttrMap) {
						var instance = this;

						var	attrMap = {
							color: instance.get('color')
						};

						return SchedulerEventRecorder.superclass.getUpdatedSchedulerEvent.call(instance, A.merge(attrMap, optAttrMap));
					},

					isMasterBooking: Lang.emptyFnFalse,

					populateForm: function() {
						var instance = this;

						var bodyTemplate = instance.get('bodyTemplate');

						var headerTemplate = instance.get('headerTemplate');

						var templateData = instance.getTemplateData();

						if (A.instanceOf(bodyTemplate, A.Template) && A.instanceOf(headerTemplate, A.Template)) {
							instance.popover.setStdModContent('body', bodyTemplate.parse(templateData));
							instance.popover.setStdModContent('header', headerTemplate.parse(templateData));

							instance.popover.addToolbar(instance._getFooterToolbar(), 'footer');
						}
						else {
							SchedulerEventRecorder.superclass.populateForm.apply(instance, arguments);
						}

						instance.popover.addToolbar(
							[
								{
									cssClass: 'close',
									label: "\u00D7",
									on: {
										click: A.bind(instance._handleCancelEvent, instance)
									},
									render: true
								}
							],
							'header'
						);
					},

					_handleEventAcceptResponse: function(event) {
						var instance = this;

						var schedulerEvent = instance.get('event');

						if (schedulerEvent) {
							CalendarUtil.invokeTransition(schedulerEvent, CalendarWorkflow.STATUS_APPROVED);
						}
					},

					_handleEventDeclineResponse: function(event) {
						var instance = this;

						var schedulerEvent = instance.get('event');

						if (schedulerEvent) {
							CalendarUtil.invokeTransition(schedulerEvent, CalendarWorkflow.STATUS_DENIED);
						}
					},

					_handleEventMaybeResponse: function(event) {
						var instance = this;

						var schedulerEvent = instance.get('event');

						if (schedulerEvent) {
							CalendarUtil.invokeTransition(schedulerEvent, CalendarWorkflow.STATUS_MAYBE);
						}
					},

					_handleEditEvent: function(event) {
						var instance = this;

						var scheduler = instance.get('scheduler');

						var activeViewName = scheduler.get('activeView').get('name');

						var date = scheduler.get('date');

						var schedulerEvent = instance.get('event');

						var editCalendarBookingURL = decodeURIComponent(instance.get('editCalendarBookingURL'));

						var data = instance.serializeForm();

						data.activeView = activeViewName;

						data.date = date.getTime();

						data.endTime = CalendarUtil.toUTC(data.endTime).getTime();
						data.startTime = CalendarUtil.toUTC(data.startTime).getTime();

						data.titleCurrentValue = encodeURIComponent(data.content);

						if (schedulerEvent) {
							data.allDay = schedulerEvent.get('allDay');
							data.calendarBookingId = schedulerEvent.get('calendarBookingId');
						}

						Liferay.Util.openWindow(
							{
								dialog: {
									after: {
										destroy: function(event) {
											scheduler.load();
										}
									},
									destroyOnHide: true,
									modal: true
								},
								refreshWindow: window,
								title: Liferay.Language.get('edit'),
								uri: Lang.sub(editCalendarBookingURL, data)
							}
						);

						instance.hidePopover();
					},

					_handleViewEvent: function(event) {
						var instance = this;

						var scheduler = instance.get('scheduler');

						var viewCalendarBookingURL = decodeURIComponent(instance.get('viewCalendarBookingURL'));

						var data = instance.serializeForm();

						var schedulerEvent = instance.get('event');

						data.calendarBookingId = schedulerEvent.get('calendarBookingId');

						Liferay.Util.openWindow(
							{
								dialog: {
									after: {
										destroy: function(event) {
											scheduler.load();
										}
									},
									destroyOnHide: true,
									modal: true
								},
								refreshWindow: window,
								title: Liferay.Language.get('view'),
								uri: Lang.sub(viewCalendarBookingURL, data)
							}
						);

						instance.hidePopover();
					},

					_hasAcceptButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS && (status !== CalendarWorkflow.STATUS_APPROVED) && (status !== CalendarWorkflow.STATUS_DRAFT);
					},

					_hasDeclineButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS && (status !== CalendarWorkflow.STATUS_DRAFT);
					},

					_hasDeleteButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS && calendar;
					},

					_hasEditButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS;
					},

					_hasMaybeButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS && (status !== CalendarWorkflow.STATUS_DRAFT) && (status !== CalendarWorkflow.STATUS_MAYBE);
					},

					_hasSaveButton: function(permissions, calendar, status) {
						return permissions.MANAGE_BOOKINGS;
					},

					_afterPopoverVisibleChange: function(event) {
						var instance = this;

						var schedulerEvent = instance.get('event');

						var popoverBB = instance.popover.get('boundingBox');

						popoverBB.toggleClass('calendar-portlet-event-recorder-editing', !!schedulerEvent);

						var defaultUserCalendar = CalendarUtil.getDefaultUserCalendar();

						var calendarId = defaultUserCalendar.get('calendarId');
						var color = defaultUserCalendar.get('color');

						var eventInstance = instance;

						if (schedulerEvent) {
							calendarId = schedulerEvent.get('calendarId');

							var calendar = CalendarUtil.availableCalendars[calendarId];

							if (calendar) {
								color = calendar.get('color');

								eventInstance = schedulerEvent;
							}
						}

						eventInstance.set(
							'color',
							color,
							{
								silent: true
							}
						);

						SchedulerEventRecorder.superclass._afterPopoverVisibleChange.apply(this, arguments);

						var portletNamespace = instance.get('portletNamespace');

						var eventRecorderCalendar = A.one('#' + portletNamespace + 'eventRecorderCalendar');

						if (eventRecorderCalendar) {
							eventRecorderCalendar.val(calendarId.toString());
						}

						if (event.newVal) {
							instance._syncInvitees();
						}
					},

					_renderPopOver: function() {
						var instance = this;

						var popoverBB = instance.popover.get('boundingBox');

						SchedulerEventRecorder.superclass._renderPopOver.apply(this, arguments);

						popoverBB.delegate(
							['change', 'keypress'],
							function(event) {
								var schedulerEvent = instance.get('event') || instance;

								var calendarId = toInt(event.currentTarget.val());

								var selectedCalendar = CalendarUtil.availableCalendars[calendarId];

								if (selectedCalendar) {
									schedulerEvent.set(
										'color',
										selectedCalendar.get('color'),
										{
											silent: true
										}
									);
								}
							},
							'#' + instance.get('portletNamespace') + 'eventRecorderCalendar'
						);
					},

					_syncInvitees: function() {
						var instance = this;

						var portletNamespace = instance.get('portletNamespace');
						var schedulerEvent = instance.get('event');

						if (schedulerEvent) {
							var parentCalendarBookingId = schedulerEvent.get('parentCalendarBookingId');

							CalendarUtil.getCalendarBookingInvitees(
								parentCalendarBookingId,
								function(data) {
									var results = AArray.partition(
										data,
										function(item) {
											return item.classNameId === CalendarUtil.USER_CLASS_NAME_ID;
										}
									);

									instance._syncInviteesContent('#' + portletNamespace + 'eventRecorderUsers', results.matches);
									instance._syncInviteesContent('#' + portletNamespace + 'eventRecorderResources', results.rejects);
								}
							);
						}
					},

					_syncInviteesContent: function(contentNode, calendarResources) {
						var instance = this;

						var values = AArray.map(
							calendarResources,
							function(item) {
								return item.name;
							}
						);

						contentNode = A.one(contentNode);

						var messageNode = contentNode.one('.calendar-portlet-invitees');

						var messageHTML = '&mdash;';

						if (values.length > 0) {
							contentNode.show();

							messageHTML = values.join(STR_COMMA_SPACE);
						}

						messageNode.html(messageHTML);
					},

					_getFooterToolbar: function() {
						var instance = this;

						var schedulerEvent = instance.get('event');

						var schedulerEventCreated = false;

						if (schedulerEvent) {
							schedulerEventCreated = true;
						}
						else {
							schedulerEvent = instance;
						}

						var children = [];
						var editGroup = [];
						var respondGroup = [];

						var status = schedulerEvent.get('status');
						var calendar = CalendarUtil.availableCalendars[schedulerEvent.get('calendarId')];

						if (calendar) {
							var permissions = calendar.get('permissions');

							if (instance._hasSaveButton(permissions, calendar, status)) {
								editGroup.push(
									{
										on: {
											click: A.bind(instance._handleSaveEvent, instance)
										},
										icon: 'icon-hdd',
										id: 'saveBtn',
										label: Liferay.Language.get('save'),
										primary: true
									}
								);
							}

							if (instance._hasEditButton(permissions, calendar, status)) {
								editGroup.push(
									{
										on: {
											click: A.bind(instance._handleEditEvent, instance)
										},
										icon: 'icon-edit',
										id: 'editBtn',
										label: Liferay.Language.get('edit')
									}
								);
							}

							if ((schedulerEventCreated === true) && permissions.VIEW_BOOKING_DETAILS) {
								editGroup.push(
									{
										on: {
											click: A.bind(instance._handleViewEvent, instance)
										},
										icon: 'icon-eye-open',
										id: 'viewBtn',
										label: Liferay.Language.get('view')
									}
								);
							}

							if (schedulerEvent.isMasterBooking() && instance._hasDeleteButton(permissions, calendar, status)) {
								editGroup.push(
									{
										on: {
											click: A.bind(instance._handleDeleteEvent, instance)
										},
										icon: 'icon-trash',
										id: 'deleteBtn',
										label: Liferay.Language.get('delete')
									}
								);
							}

							if (instance._hasAcceptButton(permissions, calendar, status)) {
								respondGroup.push(
									{
										on: {
											click: A.bind(instance._handleEventAcceptResponse, instance)
										},
										icon: 'icon-ok-sign',
										id: 'acceptBtn',
										label: Liferay.Language.get('accept')
									}
								);
							}

							if (instance._hasMaybeButton(permissions, calendar, status)) {
								respondGroup.push(
									{
										on: {
											click: A.bind(instance._handleEventMaybeResponse, instance)
										},
										icon: 'icon-question-sign',
										id: 'maybeBtn',
										label: Liferay.Language.get('maybe')
									}
								);
							}

							if (instance._hasDeclineButton(permissions, calendar, status)) {
								respondGroup.push(
									{
										on: {
											click: A.bind(instance._handleEventDeclineResponse, instance)
										},
										icon: ' icon-remove-sign',
										id: 'declineBtn',
										label: Liferay.Language.get('decline')
									}
								);
							}

							if (editGroup.length) {
								children.push(editGroup);
							}

							if (respondGroup.length) {
								children.push(respondGroup);
							}
						}

						return children;
					}
				}
			}
		);

		Liferay.SchedulerEventRecorder = SchedulerEventRecorder;
	},
	'',
	{
		requires: ['aui-io', 'aui-scheduler', 'aui-toolbar', 'autocomplete', 'autocomplete-highlighters', 'dd-plugin', 'liferay-calendar-message-util', 'liferay-calendar-recurrence-util', 'liferay-node', 'liferay-portlet-url', 'liferay-store', 'resize-plugin']
	}
);