AUI.add(
	'liferay-scheduler',
	function(A) {
		var AArray = A.Array;
		var DateMath = A.DataType.DateMath;
		var Lang = A.Lang;

		var RecurrenceUtil = Liferay.RecurrenceUtil;
		var Workflow = Liferay.Workflow;

		var isArray = Lang.isArray;
		var isBoolean = Lang.isBoolean;
		var isDate = Lang.isDate;
		var isFunction = Lang.isFunction;
		var isObject = Lang.isObject;
		var isString = Lang.isString;

		var toInt = Lang.toInt;

		var STR_BLANK = '';

		var STR_COMMA = ',';

		var STR_COMMA_SPACE = ', ';

		var STR_DASH = '-';

		var STR_SPACE = ' ';

		var TPL_INVITEES_URL = '{inviteesURL}&{portletNamespace}parentCalendarBookingId={calendarBookingId}';

		var TPL_RENDERING_RULES_URL = '{renderingRulesURL}&{portletNamespace}calendarIds={calendarIds}&{portletNamespace}startDate={startDate}&{portletNamespace}endDate={endDate}&{portletNamespace}ruleName={ruleName}';

		var COMPANY_GROUP_ID = toInt(themeDisplay.getCompanyGroupId());

		var COMPANY_ID = toInt(themeDisplay.getCompanyId());

		var GROUP_ID = toInt(themeDisplay.getScopeGroupId());

		var USER_ID = toInt(themeDisplay.getUserId());

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
					AArray.some(
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
			INVITEES_URL: null,
			INVOKER_URL: '/api/jsonws/invoke',
			NOTIFICATION_DEFAULT_TYPE: 'email',
			PORTLET_NAMESPACE: STR_BLANK,
			RENDERING_RULES_URL: null,
			USER_TIMEZONE_OFFSET: 0,

			dataSource: null,
			availableCalendars: {},
			manageableCalendars: {},
			visibleCalendars: {},

			addEvent: function(schedulerEvent) {
				var instance = this;

				var scheduler = schedulerEvent.get('scheduler');

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/add-calendar-booking': {
							allDay: schedulerEvent.get('allDay'),
							calendarId: schedulerEvent.get('calendarId'),
							childCalendarIds: STR_BLANK,
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endDate: instance.toUTCTimeZone(schedulerEvent.get('endDate')).getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							parentCalendarBookingId: schedulerEvent.get('parentCalendarBookingId'),
							recurrence: schedulerEvent.get('recurrence'),
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

									if (scheduler) {
										scheduler.fire('eventsChangeBatch');
									}
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

				var scheduler = schedulerEvent.get('scheduler');

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/delete-calendar-booking': {
							calendarBookingId: schedulerEvent.get('calendarBookingId')
						}
					},
					{
						success: function() {
							scheduler.loadCalendarBookings();

							if (scheduler) {
								scheduler.fire('eventsChangeBatch');
							}
						}
					}
				);
			},

			deleteEventInstance: function(schedulerEvent, allFollowing) {
				var instance = this;

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/delete-calendar-booking-instance': {
							allFollowing: allFollowing,
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							startDate: CalendarUtil.toUTCTimeZone(schedulerEvent.get('startDate')).getTime()
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

			getCalendarRenderingRules: function(calendarIds, startDate, endDate, ruleName, callback) {
				var instance = this;

				var renderingRulesURL = Lang.sub(
					TPL_RENDERING_RULES_URL,
					{
						calendarIds: calendarIds.join(),
						endDate: endDate.getTime(),
						portletNamespace: instance.PORTLET_NAMESPACE,
						renderingRulesURL: instance.RENDERING_RULES_URL,
						ruleName: ruleName,
						startDate: startDate.getTime()
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

			getDataSource: function() {
				var instance = this;

				var dataSource = instance.dataSource;

				if (!dataSource) {
					dataSource = new A.DataSource.IO(
						{
							source: instance.INVOKER_URL,
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

			getEvent: function(calendarBookingId, success, failure) {
				var instance = this;

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/get-calendar-booking': {
							calendarBookingId: calendarBookingId
						}
					},
					{
						cache: false,
						failure: failure,
						success: success
					}
				);
			},

			getEvents: function(startDate, endDate, status, success, failure) {
				var instance = this;

				var calendarIds = A.Object.keys(instance.availableCalendars);

				instance.invoke(
					{
						'$booking = /calendar-portlet/calendarbooking/search': {
							calendarIds: calendarIds.join(','),
							calendarResourceIds: STR_BLANK,
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
				schedulerEvent.set('recurrence', data.recurrence);
				schedulerEvent.set('status', data.status);

				if (schedulerEvent.get('scheduler')) {
					var oldCalendar = instance.availableCalendars[oldCalendarId];

					if (oldCalendar) {
						oldCalendar.removeEvent(schedulerEvent);
					}

					var newCalendar = instance.availableCalendars[newCalendarId];

					if (newCalendar) {
						newCalendar.addEvent(schedulerEvent);
					}

					schedulerEvent.set('calendarId', newCalendarId);
				}
			},

			syncCalendarsMap: function() {
				var instance = this;

				var visibleCalendars = instance.visibleCalendars = {};
				var availableCalendars = instance.availableCalendars = {};

				AArray.each(
					arguments,
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
						recurrence: calendarBooking.recurrence,
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

			updateEvent: function(schedulerEvent, success) {
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
							recurrence: schedulerEvent.get('recurrence'),
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

							if (success) {
								success.call(this, data);
							}
						}
					}
				);
			},

			updateEventInstance: function(schedulerEvent, allFollowing, success) {
				var instance = this;

				instance.invoke(
					{
						'/calendar-portlet/calendarbooking/update-calendar-booking-instance': {
							allDay: schedulerEvent.get('allDay'),
							allFollowing: allFollowing,
							calendarBookingId: schedulerEvent.get('calendarBookingId'),
							calendarId: schedulerEvent.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(schedulerEvent.get('description')),
							endDate: instance.toUTCTimeZone(schedulerEvent.get('endDate')).getTime(),
							firstReminder: schedulerEvent.get('firstReminder'),
							firstReminderType: schedulerEvent.get('firstReminderType'),
							location: schedulerEvent.get('location'),
							recurrence: schedulerEvent.get('recurrence'),
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
			STATUS_MAYBE: 8
		};

		A.mix(CalendarWorkflow, Workflow);

		Liferay.CalendarWorkflow = CalendarWorkflow;

		var Scheduler = A.Component.create(
			{
				ATTRS: {
					currentMonth: {
						setter: toInt,
						valueFn: function(val) {
							var instance = this;

							return instance.get('date').getMonth();
						}
					},

					filterCalendarBookings: {
						validator: isFunction
					},

					portletNamespace: {
						value: STR_BLANK,
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
								'scheduler-base:dateChange': instance._afterDateChange,
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

						var date = instance.get('date');
						var firstDayOfWeek = instance.get('firstDayOfWeek');

						var startDate = DateMath.getFirstDayOfWeek(DateMath.findMonthStart(date), firstDayOfWeek);
						var endDate = DateMath.add(DateMath.getFirstDayOfWeek(DateMath.findMonthEnd(date), firstDayOfWeek), DateMath.DAY, 7);

						CalendarUtil.getEvents(
							startDate,
							endDate,
							[CalendarWorkflow.STATUS_APPROVED, CalendarWorkflow.STATUS_MAYBE, CalendarWorkflow.STATUS_PENDING],
							function(calendarBookings) {
								if (filterCalendarBookings) {
									calendarBookings = AArray.filter(calendarBookings, filterCalendarBookings);
								}

								instance.loadCalendarBookingsJSON(calendarBookings);
							}
						);
					},

					loadCalendarBookingsJSON: function(calendarBookings) {
						var instance = this;

						var events = A.Object.map(
							Liferay.CalendarUtil.availableCalendars,
							function(item, index, collection) {
								var events = CalendarUtil.filterJSONArray(calendarBookings, 'calendarId', toInt(index));

								item.set('events', events);

								return item;
							}
						);

						instance.set('events', events);

						if (instance.get('rendered')) {
							instance.syncEventsUI();
						}

						CalendarUtil.message(STR_BLANK);
					},

					_afterDateChange: function(event) {
						var instance = this;

						var currentMonth = event.newVal.getMonth();

						if (currentMonth !== instance.get('currentMonth')) {
							instance.set('currentMonth', currentMonth);
						}
					},

					_afterStartDateChange: function(event) {
						var instance = this;

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
											instance.loadCalendarBookings();
										}
									);

									this.close();
								},
								function() {
									CalendarUtil.getEvent(
										calendarBookingId,
										function(calendarBooking) {
											var newSchedulerEvent = CalendarUtil.toSchedulerEvent(calendarBooking);

											newSchedulerEvent.copyPropagateAttrValues(schedulerEvent);

											var offset = 0;

											var newVal = event.newVal;
											var prevVal = event.prevVal;

											if (isDate(newVal) && isDate(prevVal)) {
												offset = newVal.getTime() - prevVal.getTime();
											}

											var calendarStartDate = calendarBooking.startDate + offset;

											var startDate = CalendarUtil.toUserTimeZone(calendarStartDate);
											var endDate = CalendarUtil.toUserTimeZone(calendarStartDate + (schedulerEvent.getSecondsDuration() * 1000));

											newSchedulerEvent.set('startDate', startDate);
											newSchedulerEvent.set('endDate', endDate);

											CalendarUtil.updateEvent(
												newSchedulerEvent,
												function() {
													instance.loadCalendarBookings();
												}
											);
										}
									);

									this.close();
								},
								function() {
									instance.loadCalendarBookings();

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
									Liferay.Language.get('you-are-about-to-make-changes-that-will-only-be-reflected-on-calendar-x'),
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
									instance.loadCalendarBookings();

									this.close();
								}
							);
						}
					},

					_deleteEvent: function(schedulerEvent) {
						var instance = this;

						var eventRecorder = instance.get('eventRecorder');

						instance.removeEvent(schedulerEvent);

						eventRecorder.hideOverlay();

						instance.syncEventsUI();
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

									instance._deleteEvent(schedulerEvent);

									RecurrenceUtil.closeConfirmationPanel();
								},
								function() {
									CalendarUtil.deleteEventInstance(schedulerEvent, true);

									instance._deleteEvent(schedulerEvent);

									RecurrenceUtil.closeConfirmationPanel();
								},
								function() {
									CalendarUtil.deleteEvent(schedulerEvent);

									instance._deleteEvent(schedulerEvent);

									RecurrenceUtil.closeConfirmationPanel();
								}
							);

							event.preventDefault();
						}
						else if (schedulerEvent.isMasterBooking() && confirm(Liferay.Language.get('deleting-this-event-will-cancel-the-meeting-with-your-guests-would-you-like-to-delete'))) {
							CalendarUtil.deleteEvent(schedulerEvent);

							instance._deleteEvent(schedulerEvent);
						}
						else {
							event.preventDefault();
						}
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
						setter: toInt,
						value: 0
					},

					calendarId: {
						setter: toInt,
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
						setter: toInt,
						value: 0
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
						setter: toInt,
						value: 0
					},

					recurrence: {
						validator: isString,
						value: STR_BLANK
					},

					reminder: {
						getter: function() {
							var instance = this;

							return (instance.get('firstReminder') > 0) || (instance.get('secondReminder') > 0);
						}
					},

					repeated: {
						getter: function() {
							var instance = this;

							return instance.isRecurring();
						}
					},

					secondReminder: {
						setter: toInt,
						value: 0
					},

					secondReminderType: {
						validator: isString,
						value: CalendarUtil.NOTIFICATION_DEFAULT_TYPE
					},

					status: {
						setter: toInt,
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
										padding: '0 2px',
										border: 'none',
										color: '#111'
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
						setter: toInt,
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
						setter: toInt,
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

						var scheduler = instance.get('scheduler');
						var newSchedulerEvent = instance.get('event');

						if (!newSchedulerEvent) {
							newSchedulerEvent = new (instance.get('eventClass'))();
						}

						newSchedulerEvent.setAttrs(
							A.merge(
								instance.serializeForm(),
								{
									color: instance.get('color')
								}
							)
						);

						newSchedulerEvent.set('scheduler', scheduler);

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

						var calendar = CalendarUtil.availableCalendars[schedulerEvent.get('calendarId')];
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
						var date = scheduler.get('date');

						var schedulerEvent = instance.get('event');
						var editCalendarBookingURL = decodeURIComponent(instance.get('editCalendarBookingURL'));
						var data = instance.serializeForm();

						data.activeView = activeViewName;
						data.date = date.getTime();
						data.endDate = CalendarUtil.toUTCTimeZone(data.endDate).getTime();
						data.startDate = CalendarUtil.toUTCTimeZone(data.startDate).getTime();
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
											scheduler.loadCalendarBookings();
										}
									},
									destroyOnClose: true,
									modal: true
								},
								refreshWindow: window,
								title: Liferay.Language.get('edit-details'),
								uri: Lang.sub(editCalendarBookingURL, data)
							}
						);

						instance.hideOverlay();
					},

					_onOverlayVisibleChange: function(event) {
						var instance = this;

						var schedulerEvent = instance.get('event');
						var overlayBB = instance.overlay.get('boundingBox');

						overlayBB.toggleClass('calendar-portlet-event-recorder-editing', !!schedulerEvent);

						var calendarId = CalendarUtil.DEFAULT_CALENDAR.calendarId;
						var color = CalendarUtil.DEFAULT_CALENDAR.color;

						var eventInstance = instance;

						if (schedulerEvent) {
							calendarId = schedulerEvent.get('calendarId');

							color = CalendarUtil.manageableCalendars[calendarId].color;

							eventInstance = schedulerEvent;
						}

						eventInstance.set('color', color);

						SchedulerEventRecorder.superclass._onOverlayVisibleChange.apply(this, arguments);

						var portletNamespace = instance.get('portletNamespace');
						var eventRecorderCalendar = A.one('#' + portletNamespace + 'eventRecorderCalendar');

						if (eventRecorderCalendar) {
							eventRecorderCalendar.val(calendarId);
						}

						instance._syncToolbarButtons(event.newVal);

						if (event.newVal) {
							instance._syncInvitees();
						}
					},

					_renderOverlay: function() {
						var instance = this;

						var overlayBB = instance.overlay.get('boundingBox');

						SchedulerEventRecorder.superclass._renderOverlay.apply(this, arguments);

						overlayBB.delegate(
							['change', 'keypress'],
							function(event) {
								var schedulerEvent = instance.get('event') || instance;

								var calendarId = toInt(event.currentTarget.val());

								var selectedCalendar = CalendarUtil.manageableCalendars[calendarId];

								if (selectedCalendar) {
									schedulerEvent.set('color', selectedCalendar.color);
								}
							},
							'#' + instance.get('portletNamespace') + 'eventRecorderCalendar'
						);
					},

					_syncInvitees: function() {
						var instance = this;

						var schedulerEvent = instance.get('event');
						var portletNamespace = instance.get('portletNamespace');

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

						if (values.length > 0) {
							A.one(contentNode).show().one('.calendar-portlet-invitees').html(values.join(STR_COMMA_SPACE));
						}
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
						var calendar = CalendarUtil.availableCalendars[schedulerEvent.get('calendarId')];

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
						setter: toInt
					},

					calendarResourceId: {
						value: 0,
						setter: toInt
					},

					calendarResourceName: {
						value: STR_BLANK,
						validator: isString
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
		requires: ['aui-io', 'aui-scheduler', 'autocomplete', 'autocomplete-highlighters', 'datasource-cache', 'datasource-get', 'dd-plugin', 'liferay-calendar-message-util', 'liferay-calendar-recurrence-util', 'liferay-portlet-url', 'liferay-store', 'resize-plugin']
	}
);
