(function() {
	var Workflow = Liferay.Workflow;

	var toNumber = function(val) {
		return parseInt(val, 10) || 0;
	};

	var STR_BLANK = '';

	var STR_COMMA = ',';

	var STR_COMMA_SPACE = ', ';

	var STR_DASH = '-';

	var STR_SPACE = ' ';

	var COMPANY_GROUP_ID = toNumber(themeDisplay.getCompanyGroupId());

	var COMPANY_ID = toNumber(themeDisplay.getCompanyId());

	var GROUP_ID = toNumber(themeDisplay.getScopeGroupId());

	var USER_ID = toNumber(themeDisplay.getUserId());

	AUI.add(
		'liferay-scheduler',
		function(A) {
		var DateMath = A.DataType.DateMath;
		var Lang = A.Lang;

		var isArray = Lang.isArray;
		var isBoolean = Lang.isBoolean;
		var isDate = Lang.isDate;
		var isFunction = Lang.isFunction;
		var isObject = Lang.isObject;
		var isString = Lang.isString;

		var jsonParse = function(val) {
			var jsonObj = null;

			try {
				jsonObj = A.JSON.parse(val);
			}
			catch(e) {
			}

			return jsonObj;
		};

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
					A.Array.some(
						[ Time.WEEK, Time.DAY, Time.HOUR, Time.MINUTE ],
						function(item, index, collection) {
							value = milliseconds/item;
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
			NOTIFICATION_DEFAULT_TYPE: 'email',
			PORTLET_NAMESPACE: STR_BLANK,
			USER_TIMEZONE_OFFSET: 0,

			dataSource: null,
			invokerURL: '/api/secure/jsonws/invoke',
			visibleCalendars: {},

			addEvent: function(schedulerEvent) {
				var instance = this;

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/add-calendar-booking': {
							allDay: schedulerEvent.get('allDay'),
							calendarId: schedulerEvent.get('calendarId'),
							childCalendarIds: '',
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endDate: instance.toUTCTimeZone(schedulerEvent.get('endDate')).getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							parentCalendarBookingId: schedulerEvent.get('parentCalendarBookingId'),
							recurrence: schedulerEvent.get('repeat'),
							secondReminder: schedulerEvent.get('secondReminder'),
							secondReminderType: schedulerEvent.get('secondReminderType'),
							startDate: instance.toUTCTimeZone(schedulerEvent.get('startDate')).getTime(),
							titleMap: instance.getLocalizationMap(schedulerEvent.get('content'))
						}
					},
					{
						failure: function() {
							instance.destroyEvent(schedulerEvent);
						},

						start: function() {
							schedulerEvent.set('loading', true);
						},

						success: function(data) {
							schedulerEvent.set('loading', false);

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
							return A.Array.filter(
								results,
								function(item, index, collection) {
									return !instance.visibleCalendars[item.raw.calendarId];
								}
							);
						},
						resultFormatter: function(query, results) {
							return A.Array.map(
								results,
								function (result) {
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

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/delete-calendar-booking': {
							calendarBookingId: schedulerEvent.get('calendarBookingId')
						}
					},
					{
						success: function() {
							schedulerEvent.get('scheduler').loadCalendarBookings();
						}
					}
				);
			},

			destroyEvent: function(schedulerEvent) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				scheduler.removeEvent(schedulerEvent);
				scheduler.syncEventsUI();
			},

			filterJSONArray: function(jsonArray, property, value) {
				var instance = this;

				var events = [];

				A.Array.each(
					jsonArray,
					function(item, index, collection) {
						if (value === item[property]) {
							events.push(instance.toSchedulerEvent(item));
						}
					}
				);

				return events;
			},

			getCalendarJSONById: function(calendarJSONArray, calendarId) {
				var instance = this;

				return A.Array.find(
					calendarJSONArray,
					function(item, index, collection) {
						return item.calendarId === calendarId;
					}
				);
			},

			getDataSource: function() {
				var instance = this;

				var dataSource = instance.dataSource;

				if (!dataSource) {
					dataSource = new A.DataSource.IO(
						{
							source: instance.invokerURL,
							on: {
								request: function(e) {
									var callback = e.callback && e.callback.start;

									if (callback) {
										var payload = e.details[0];

										callback.apply(this, [payload, e]);
									}
								}
							}
						}
					);

					dataSource.plug(
						A.Plugin.DataSourceCache,
						{
							max: 100
						}
					);

					instance.dataSource = dataSource;
				}

				return instance.dataSource;
			},

			getEvents: function(startDate, endDate, status, success, failure) {
				var instance = this;

				var calendarIds = A.Object.keys(instance.visibleCalendars);

				instance.invoke(
					{
						'$booking = /calendar-portlet/calendarbooking/search': {
							calendarIds: calendarIds.join(','),
							calendarResourceIds: '',
							companyId: COMPANY_ID,
							end: -1,
							endDate: endDate.getTime(),
							groupIds: [0, COMPANY_GROUP_ID, GROUP_ID].join(','),
							keywords: null,
							orderByComparator: null,
							parentCalendarBookingId: -1,
							recurring: true,
							start: Workflow.STATUS_APPROVED,
							startDate: startDate.getTime(),
							statuses: status.join(',')
						}
					},
					{
						cache: true,
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

			invoke: function(service, callback) {
				var instance = this;

				var dataSource = instance.getDataSource();

				callback = A.merge(
					{
						cache: false
					},
					callback
				);

				if (callback.cache === false) {
					dataSource.cache.flush();
				}

				dataSource.sendRequest(
					{
						callback: {
							failure: callback.failure,
							start: callback.start,
							success: function(e) {
								var xhr = e && e.response.results && e.response.results[0];

								if (xhr && callback.success) {
									var data = jsonParse(xhr.responseText);

									callback.success.apply(this, [data, e]);
								}
							}
						},
						request: '?p_auth=' + Liferay.authToken + '&cmd=' + A.JSON.stringify(service)
					}
				);
			},

			invokeTransition: function(schedulerEvent, status) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/invoke-transition': {
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							transitionName: instance.getStatusLabel(status).toLowerCase(),
							userId: USER_ID
						}
					},
					{
						start: function() {
							schedulerEvent.set('loading', true);
						},

						success: function(data) {
							schedulerEvent.set('loading', false);

							if (data && !data.exception && scheduler) {
								var eventRecorder = scheduler.get('eventRecorder');

								eventRecorder.hideOverlay();

								scheduler.loadCalendarBookings();
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

				var newCalendarId = data.calendarId;

				var oldCalendarId = schedulerEvent.get('calendarId');

				schedulerEvent.set('calendarBookingId', data.calendarBookingId);
				schedulerEvent.set('calendarResourceId', data.calendarResourceId);
				schedulerEvent.set('parentCalendarBookingId', data.parentCalendarBookingId);

				schedulerEvent.set('status', data.status);

				var oldCalendar = instance.visibleCalendars[oldCalendarId];

				if (oldCalendar) {
					oldCalendar.removeEvent(schedulerEvent);
				}

				var newCalendar = instance.visibleCalendars[newCalendarId];

				if (newCalendar) {
					newCalendar.addEvent(schedulerEvent);
				}

				schedulerEvent.set('calendarId', newCalendarId);
			},

			syncVisibleCalendarsMap: function() {
				var instance = this;

				var visibleCalendars = instance.visibleCalendars = {};

				A.Array.each(
					arguments,
					function(calendarList) {
						var calendars = calendarList.get('calendars');

						A.each(
							calendars,
							function(item, index, collection) {
								visibleCalendars[item.get('calendarId')] = item;
							}
						);
					}
				);

				return visibleCalendars;
			},

			toSchedulerEvent: function(calendarBooking) {
				var instance = this;

				return new Liferay.SchedulerEvent(
					{
						allDay: calendarBooking.allDay,
						calendarBookingId: calendarBooking.calendarBookingId,
						calendarId: calendarBooking.calendarId,
						content: calendarBooking.titleCurrentValue,
						description: calendarBooking.descriptionCurrentValue,
						endDate: instance.toUserTimeZone(calendarBooking.endDate),
						location: calendarBooking.location,
						parentCalendarBookingId: calendarBooking.parentCalendarBookingId,
						startDate: instance.toUserTimeZone(calendarBooking.startDate),
						status: calendarBooking.status
					}
				);
			},

			toUserTimeZone: function(utc) {
				var instance = this;

				if (!isDate(utc)) {
					utc = new Date(utc);
				}

				return DateMath.add(utc, DateMath.MINUTES, utc.getTimezoneOffset() + instance.USER_TIMEZONE_OFFSET / DateMath.ONE_MINUTE_MS);
			},

			toUTCTimeZone: function(date) {
				var instance = this;

				if (!isDate(date)) {
					date = new Date(date);
				}

				return DateMath.subtract(date, DateMath.MINUTES, date.getTimezoneOffset() + instance.USER_TIMEZONE_OFFSET / DateMath.ONE_MINUTE_MS);
			},

			updateEvent: function(schedulerEvent) {
				var instance = this;

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/update-calendar-booking': {
							allDay: schedulerEvent.get('allDay'),
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							calendarId: schedulerEvent.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endDate: instance.toUTCTimeZone(schedulerEvent.get('endDate')).getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							recurrence: schedulerEvent.get('repeat'),
							secondReminder: schedulerEvent.get('secondReminder'),
							secondReminderType: schedulerEvent.get('secondReminderType'),
							startDate: instance.toUTCTimeZone(schedulerEvent.get('startDate')).getTime(),
							status: schedulerEvent.get('status'),
							titleMap: instance.getLocalizationMap(schedulerEvent.get('content')),
							userId: USER_ID
						}
					},
					{
						start: function() {
							schedulerEvent.set('loading', true);
						},

						success: function(data) {
							schedulerEvent.set('loading', false);

							if (data) {
								if (data.exception) {
									return;
								}
								else {
									instance.setEventAttrs(schedulerEvent, data);
								}
							}
						}
					}
				);
			}
		};

		Liferay.CalendarUtil = CalendarUtil;

		var CalendarWorkflow = {
			STATUS_MAYBE: 8
		};

		A.mix(CalendarWorkflow, Workflow);

		Liferay.CalendarWorkflow = CalendarWorkflow;

		var Scheduler = A.Component.create(
			{
				ATTRS: {
					currentMonth: {
						setter: toNumber,
						valueFn: function(val) {
							var instance = this;

							return instance.get('currentDate').getMonth();
						}
					},

					filterCalendarBookings: {
						validator: isFunction
					},

					portletNamespace: {
						value: '',
						validator: isString
					}
				},

				EXTENDS: A.Scheduler,

				NAME: 'scheduler-base',

				UI_ATTRS: ['currentMonth'],

				prototype: {
					bindUI: function() {
						var instance = this;

						instance.after(
							{
								'scheduler-base:currentDateChange': instance._afterCurrentDateChange,
								'scheduler-event:startDateChange': instance._afterStartDateChange
							}
						);

						instance.on(
							{
								'scheduler-event-recorder:delete': instance._onDeleteEvent,
								'scheduler-event-recorder:save': instance._onSaveEvent
							}
						);

						Scheduler.superclass.bindUI.apply(this, arguments);
					},

					loadCalendarBookings: function() {
						var instance = this;

						var filterCalendarBookings = instance.get('filterCalendarBookings');

						CalendarUtil.message(Liferay.Language.get('loading') + '...');

						var currentDate = instance.get('currentDate');
						var firstDayOfWeek = instance.get('firstDayOfWeek');

						var startDate = DateMath.getFirstDayOfWeek(DateMath.findMonthStart(currentDate), firstDayOfWeek);
						var endDate = DateMath.add(DateMath.getFirstDayOfWeek(DateMath.findMonthEnd(currentDate), firstDayOfWeek), DateMath.DAY, 7);

						CalendarUtil.getEvents(
							startDate,
							endDate,
							[CalendarWorkflow.STATUS_APPROVED, CalendarWorkflow.STATUS_MAYBE, CalendarWorkflow.STATUS_PENDING],
							function(calendarBookings) {
								if (filterCalendarBookings) {
									calendarBookings = A.Array.filter(calendarBookings, filterCalendarBookings);
								}

								instance.loadCalendarBookingsJSON(calendarBookings);
							}
						);
					},

					loadCalendarBookingsJSON: function(calendarBookings) {
						var instance = this;

						var visibleCalendarsMap = Liferay.CalendarUtil.visibleCalendars;

						A.each(
							visibleCalendarsMap,
							function(item, index, collection) {
								var events = CalendarUtil.filterJSONArray(calendarBookings, 'calendarId', toNumber(index));

								item.set('events', events);
							}
						);

						instance.set('events', A.Object.values(visibleCalendarsMap));

						if (instance.get('rendered')) {
							instance.syncEventsUI();
						}

						CalendarUtil.message('');
					},

					_afterCurrentDateChange: function(event) {
						var instance = this;

						var currentMonth = event.newVal.getMonth();

						if (currentMonth !== instance.get('currentMonth')) {
							instance.set('currentMonth', currentMonth);
						}
					},

					_afterStartDateChange: function(event) {
						var instance = this;

						setTimeout(function() {
							CalendarUtil.updateEvent(event.target);
						}, 0);
					},

					_onDeleteEvent: function(event) {
						var instance = this;

						var schedulerEvent = event.schedulerEvent;

						if (schedulerEvent.isMasterBooking() && !confirm(Liferay.Language.get('deleting-this-event-will-cancel-the-meeting-with-your-guests-would-you-like-to-delete'))) {
							event.preventDefault();
							return;
						}

						CalendarUtil.deleteEvent(schedulerEvent);
					},

					_onSaveEvent: function(event) {
						var instance = this;

						CalendarUtil.addEvent(event.newSchedulerEvent);
					},

					_uiSetCurrentMonth: function(val) {
						var instance = this;

						instance.loadCalendarBookings();
					}
				}
			}
		);

		Liferay.Scheduler = Scheduler;

		var SchedulerEvent = A.Component.create(
			{
				ATTRS: {
					calendarBookingId: {
						setter: toNumber,
						value: 0
					},

					calendarId: {
						setter: toNumber,
						value: 0
					},

					description: {
						validator: isString,
						value: STR_BLANK
					},

					editingEvent: {
						validator: isBoolean,
						value: false
					},

					firstReminder: {
						setter: toNumber,
						value: 3600000
					},

					firstReminderType: {
						validator: isString,
						value: CalendarUtil.NOTIFICATION_DEFAULT_TYPE
					},

					loading: {
						validator: isBoolean,
						value: false
					},

					location: {
						validator: isString,
						value: STR_BLANK
					},

					parentCalendarBookingId: {
						setter: toNumber,
						value: 0
					},

					secondReminder: {
						setter: toNumber,
						value: 0
					},

					secondReminderType: {
						validator: isString,
						value: CalendarUtil.NOTIFICATION_DEFAULT_TYPE
					},

					status: {
						setter: toNumber,
						value: 0
					}
				},

				EXTENDS: A.SchedulerEvent,

				NAME: 'scheduler-event',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._uiSetLoading(instance.get('loading'));
						instance._uiSetStatus(instance.get('status'));

						instance.on('loadingChange', instance._onLoadingChange);
						instance.on('statusChange', instance._onStatusChange);
					},

					isMasterBooking: function() {
						var instance = this;

						return (instance.get('parentCalendarBookingId') === instance.get('calendarBookingId'));
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
										backgroundColor: '#f8f8f8',
										border: 'none',
										color: instance.get('color')
									}
								);
							}
						}
					},

					_onLoadingChange: function(event) {
						var instance = this;

						instance._uiSetLoading(event.newVal);
					},

					_onStatusChange: function(event) {
						var instance = this;

						instance._uiSetStatus(event.newVal);
					},

					_uiSetLoading: function(val) {
						var instance = this;

						instance.get('node').toggleClass('calendar-portlet-event-loading', val);
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

		var SchedulerEventRecorder = A.Component.create(
			{
				ATTRS: {
					calendarId: {
						setter: toNumber,
						value: 0
					},

					editCalendarBookingURL: {
						validator: isString,
						value: STR_BLANK
					},

					portletNamespace: {
						validator: isString,
						value: STR_BLANK
					},

					status: {
						setter: toNumber,
						value: CalendarWorkflow.STATUS_DRAFT
					},

					toolbar: {
						value: {
							children: []
						}
					}
				},

				EXTENDS: A.SchedulerEventRecorder,

				NAME: 'scheduler-event-recorder',

				prototype: {
					getEventCopy: function() {
						var instance = this;
						var newSchedulerEvent = instance.get('event');

						if (!newSchedulerEvent) {
							newSchedulerEvent = new (instance.get('eventClass'))();
						}

						newSchedulerEvent.setAttrs(instance.serializeForm());

						return newSchedulerEvent;
					},

					getTemplateData: function() {
						var instance = this;

						var editing = false;
						var schedulerEvent = instance.get('event');

						if (!schedulerEvent) {
							schedulerEvent = instance;
							editing = true;
						}

						var calendar = CalendarUtil.visibleCalendars[schedulerEvent.get('calendarId')];
						var permissions = calendar.get('permissions');

						return A.merge(
							SchedulerEventRecorder.superclass.getTemplateData.apply(this, arguments),
							{
								allDay: schedulerEvent.get('allDay'),
								calendar: calendar,
								editing: editing,
								permissions: permissions,
								status: CalendarUtil.getStatusLabel(schedulerEvent.get('status'))
							}
						);
					},

					isMasterBooking: function() {
						var instance = this;

						return false;
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

					_handleEditDetailsEvent: function(event) {
						var instance = this;

						var scheduler = instance.get('scheduler');
						var activeViewName = scheduler.get('activeView').get('name');
						var currentDate = scheduler.get('currentDate');

						var schedulerEvent = instance.get('event');
						var editCalendarBookingURL = decodeURIComponent(instance.get('editCalendarBookingURL'));
						var data = instance.serializeForm();

						data.activeView = activeViewName;
						data.currentDate = currentDate.getTime();
						data.endDate = CalendarUtil.toUTCTimeZone(data.endDate).getTime();
						data.startDate = CalendarUtil.toUTCTimeZone(data.startDate).getTime();
						data.titleCurrentValue = encodeURIComponent(data.content);

						if (schedulerEvent) {
							data.allDay = schedulerEvent.get('allDay');
							data.calendarBookingId = schedulerEvent.get('calendarBookingId');
						}

						A.config.win.location.href = A.Lang.sub(editCalendarBookingURL, data);
					},

					_onOverlayVisibleChange: function(event) {
						var instance = this;

						SchedulerEventRecorder.superclass._onOverlayVisibleChange.apply(this, arguments);

						var schedulerEvent = instance.get('event');
						var overlayBB = instance.overlay.get('boundingBox');
						var portletNamespace = instance.get('portletNamespace');

						overlayBB.toggleClass('calendar-portlet-event-recorder-editing', !!schedulerEvent);

						var eventRecorderCalendar = A.one('#' + portletNamespace + 'eventRecorderCalendar');

						if (eventRecorderCalendar) {
							var calendarId = CalendarUtil.DEFAULT_CALENDAR.calendarId;

							if (schedulerEvent) {
								calendarId = schedulerEvent.get('calendarId');
							}

							eventRecorderCalendar.val(calendarId);
						}

						instance._syncToolbarButtons(event.newVal);
					},

					_syncToolbarButtons: function(overlayVisible) {
						var instance = this;

						var overlay = instance.overlay;
						var toolbar = instance.toolbar;

						if (!overlayVisible) {
							toolbar.removeAll();

							return;
						}

						var schedulerEvent = instance.get('event') || instance;
						var status = schedulerEvent.get('status');
						var calendar = CalendarUtil.visibleCalendars[schedulerEvent.get('calendarId')];

						var permissions = calendar.get('permissions');

						toolbar.add(
							{
								handler: A.bind(instance._handleCancelEvent, instance),
								id: 'cancelBtn',
								label: Liferay.Language.get('close')
							}
						);

						toolbar.add(
							{
								id: 'toolbarSpacer1',
								type: 'ToolbarSpacer'
							}
						);

						toolbar.add(
							{
								handler: A.bind(instance._handleSaveEvent, instance),
								id: 'saveBtn',
								label: Liferay.Language.get('save')
							}
						);

						toolbar.add(
							{
								handler: A.bind(instance._handleEditDetailsEvent, instance),
								id: 'editDetailsBtn',
								label: Liferay.Language.get('edit-details')
							}
						);

						if (schedulerEvent.isMasterBooking()) {
							toolbar.add(
								{
									handler: A.bind(instance._handleDeleteEvent, instance),
									id: 'deleteBtn',
									label: Liferay.Language.get('delete')
								}
							);
						}

						toolbar.add(
							{
								id: 'toolbarSpacer2',
								type: 'ToolbarSpacer'
							}
						);

						toolbar.add(
							{
								handler: A.bind(instance._handleEventAcceptResponse, instance),
								icon: 'circle-check',
								id: 'acceptBtn',
								label: Liferay.Language.get('accept')
							}
						);

						toolbar.add(
							{
								handler: A.bind(instance._handleEventMaybeResponse, instance),
								icon: 'help',
								id: 'maybeBtn',
								label: Liferay.Language.get('maybe')
							}
						);

						toolbar.add(
							{
								handler: A.bind(instance._handleEventDeclineResponse, instance),
								icon: 'circle-close',
								id: 'declineBtn',
								label: Liferay.Language.get('decline')
							}
						);

						if (!permissions.MANAGE_BOOKINGS) {
							toolbar.remove('acceptBtn');
							toolbar.remove('declineBtn');
							toolbar.remove('deleteBtn');
							toolbar.remove('editDetailsBtn');
							toolbar.remove('maybeBtn');
							toolbar.remove('saveBtn');
						}

						if (!calendar) {
							toolbar.remove('deleteBtn');
						}

						if (status === CalendarWorkflow.STATUS_DRAFT) {
							toolbar.remove('declineBtn');
							toolbar.remove('maybeBtn');
						}

						if (status === CalendarWorkflow.STATUS_MAYBE) {
							toolbar.remove('maybeBtn');
						}

						if (status === CalendarWorkflow.STATUS_APPROVED ||
							status === CalendarWorkflow.STATUS_DRAFT) {

							toolbar.remove('acceptBtn');
						}

						var estimatedOverlayWidth = 50 + toolbar.get('boundingBox').get('offsetWidth');

						overlay.set('width', Math.max(300, estimatedOverlayWidth));
					}
				}
			}
		);

		Liferay.SchedulerEventRecorder = SchedulerEventRecorder;

		var Calendar = A.Component.create(
			{
				ATTRS: {
					calendarId: {
						value: 0,
						setter: toNumber
					},

					calendarResourceId: {
						value: 0,
						setter: toNumber
					},

					calendarResourceName: {
						value: '',
						validator: isString
					},

					classNameId: {
						value: 0,
						setter: toNumber
					},

					classPK: {
						value: 0,
						setter: toNumber
					},

					defaultCalendar: {
						setter: A.DataType.Boolean.parse,
						value: false
					},

					global: {
						setter: A.DataType.Boolean.parse,
						value: false
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
					}
				},

				EXTENDS: A.SchedulerCalendar,

				NAME: 'scheduler-calendar',

				prototype: {
					getDisplayName: function() {
						var instance = this;

						var displayName = instance.get('name');
						var calendarResourceName = instance.get('calendarResourceName');

						if (displayName !== calendarResourceName) {
							displayName = [calendarResourceName, STR_DASH, displayName].join(STR_SPACE);
						}

						return displayName;
					},

					_afterColorChange: function(event) {
						var instance = this;

						Calendar.superclass._afterColorChange.apply(instance, arguments);

						var calendarId = instance.get('calendarId');

						var color = event.newVal;

						if (instance.get('permissions.UPDATE')) {
							CalendarUtil.invoke(
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
					}
				}
			}
		);

		Liferay.SchedulerCalendar = Calendar;
	},
	'' ,
	{
		requires: ['aui-io', 'aui-scheduler', 'autocomplete', 'autocomplete-highlighters', 'datasource-cache', 'datasource-get', 'liferay-portlet-url', 'liferay-store']
	}
);
}());