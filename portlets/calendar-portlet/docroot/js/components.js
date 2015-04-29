(function() {
	var STR_BLANK = '';

	var STR_COMMA = ',';

	var STR_DASH = '-';

	var STR_DOT = '.';

	var STR_PLUS = '+';

	var STR_SPACE = ' ';

	var TPL_SPAN = '<span>';

	var TPL_SPAN_CLOSE = '</span>';

	AUI.add(
		'liferay-calendar-simple-menu',
		function(A) {
			var AArray = A.Array;
			var Lang = A.Lang;

			var getClassName = A.getClassName;

			var isArray = Lang.isArray;

			var owns = A.Object.owns;

			var CSS_SIMPLE_MENU_ITEM = getClassName('simple-menu', 'item');

			var CSS_SIMPLE_MENU_ITEM_HIDDEN = getClassName('simple-menu', 'item', 'hidden');

			var CSS_SIMPLE_MENU_SEPARATOR = getClassName('simple-menu', 'separator');

			var DEFAULT_ALIGN_POINTS = [A.WidgetPositionAlign.TL, A.WidgetPositionAlign.BL];

			var TPL_ICON = '<i class="{iconClass}"></i>';

			var TPL_SIMPLE_MENU_ITEM = '<li class="{cssClass}" data-id="{id}">{icon} {caption}</li>';

			var getItemHandler = A.cached(
				function(id, items) {
					var found = null;

					AArray.some(
						items,
						function(item, index) {
							if (item.id === id) {
								found = item;

								return true;
							}
						}
					);

					return (found && found.fn);
				}
			);

			var SimpleMenu = A.Component.create(
				{

					ATTRS: {
						alignNode: {
							value: null
						},

						hiddenItems: {
							validator: isArray,
							value: []
						},

						host: {
							value: null
						},

						items: {
							validator: isArray,
							value: []
						}
					},

					AUGMENTS: [A.WidgetModality, A.WidgetPosition, A.WidgetPositionAlign, A.WidgetPositionConstrain, A.WidgetStack, A.WidgetStdMod],

					NAME: 'simple-menu',

					UI_ATTRS: ['hiddenItems', 'items'],

					prototype: {
						CONTENT_TEMPLATE: '<ul></ul>',

						renderUI: function() {
							var instance = this;

							instance.get('boundingBox').unselectable();

							instance._renderItems(instance.get('items'));
						},

						bindUI: function() {
							var instance = this;

							A.Event.defineOutside('touchend');

							var contentBox = instance.get('contentBox');

							contentBox.delegate('click', instance._onClickItems, STR_DOT + CSS_SIMPLE_MENU_ITEM, instance);

							contentBox.on('touchendoutside', instance._closeMenu, instance);

							A.getDoc().on('click', instance._closeMenu, instance);

							A.getWin().on(
								'resize',
								A.debounce(instance._positionMenu, 200, instance)
							);

							instance.after('visibleChange', instance._positionMenu, instance);
						},

						_closeMenu: function() {
							var instance = this;

							instance.hide();
						},

						_onClickItems: function(event) {
							var instance = this;

							var items = instance.get('items');

							var id = event.currentTarget.attr('data-id');

							var handler = getItemHandler(id, items);

							if (handler) {
								handler.apply(instance, arguments);
							}
						},

						_positionMenu: function() {
							var instance = this;

							var Util = Liferay.Util;

							var align = {
								node: instance.get('alignNode'),
								points: DEFAULT_ALIGN_POINTS
							};

							var centered = false;
							var modal = false;
							var width = 222;

							if (Util.isPhone() || Util.isTablet()) {
								align = null;
								centered = true;
								modal = true;
								width = '90%';
							}

							instance.setAttrs(
								{
									align: align,
									centered: centered,
									modal: modal,
									width: width
								}
							);
						},

						_renderItems: function(items) {
							var instance = this;

							var contentBox = instance.get('contentBox');
							var hiddenItems = instance.get('hiddenItems');

							instance.items = A.NodeList.create();

							AArray.each(
								items,
								function(item, index) {
									var caption = item.caption;

									if (!owns(item, 'id')) {
										item.id = A.guid();
									}

									var id = item.id;

									var cssClass = CSS_SIMPLE_MENU_ITEM;

									if (caption == STR_DASH) {
										cssClass = CSS_SIMPLE_MENU_SEPARATOR;
									}

									if (AArray.indexOf(hiddenItems, id) > -1) {
										cssClass += STR_SPACE + CSS_SIMPLE_MENU_ITEM_HIDDEN;
									}

									if (item.cssClass) {
										cssClass += STR_SPACE + item.cssClass;
									}

									var icon = STR_BLANK;

									if (item.icon) {
										icon = Lang.sub(
											TPL_ICON,
											{
												iconClass: item.icon
											}
										);

										caption = [icon, caption].join(STR_SPACE);
									}

									var li = A.Node.create(
										Lang.sub(
											TPL_SIMPLE_MENU_ITEM,
											{
												cssClass: cssClass,
												icon: icon,
												id: id
											}
										)
									);

									li.setContent(caption);

									instance.items.push(li);
								}
							);

							contentBox.setContent(instance.items);
						},

						_uiSetHiddenItems: function(val) {
							var instance = this;

							if (instance.get('rendered')) {
								instance.items.each(
									function(item, index) {
										var id = item.attr('data-id');

										item.toggleClass(CSS_SIMPLE_MENU_ITEM_HIDDEN, (AArray.indexOf(val, id) > -1));
									}
								);
							}
						},

						_uiSetItems: function(val) {
							var instance = this;

							if (instance.get('rendered')) {
								instance._renderItems(val);
							}
						}
					}
				}
			);

			Liferay.SimpleMenu = SimpleMenu;
		},
		'',
		{
			requires: ['aui-base', 'aui-template-deprecated', 'event-outside', 'event-touch', 'widget-modality', 'widget-position', 'widget-position-align', 'widget-position-constrain', 'widget-stack', 'widget-stdmod']
		}
	);

	AUI.add(
		'liferay-calendar-list',
		function(A) {
			var AArray = A.Array;
			var Lang = A.Lang;

			var isArray = Lang.isArray;
			var isObject = Lang.isObject;

			var	getClassName = A.getClassName;

			var STR_CALENDAR_LIST = 'calendar-list';

			var STR_ITEM = 'item';

			var CSS_CALENDAR_LIST_EMPTY_MESSAGE = getClassName(STR_CALENDAR_LIST, 'empty', 'message');

			var CSS_CALENDAR_LIST_ITEM = getClassName(STR_CALENDAR_LIST, STR_ITEM);

			var CSS_CALENDAR_LIST_ITEM_ACTIVE = getClassName(STR_CALENDAR_LIST, STR_ITEM, 'active');

			var CSS_CALENDAR_LIST_ITEM_ARROW = getClassName(STR_CALENDAR_LIST, STR_ITEM, 'arrow');

			var CSS_CALENDAR_LIST_ITEM_COLOR = getClassName(STR_CALENDAR_LIST, STR_ITEM, 'color');

			var CSS_CALENDAR_LIST_ITEM_HOVER = getClassName(STR_CALENDAR_LIST, STR_ITEM, 'hover');

			var CSS_CALENDAR_LIST_ITEM_LABEL = getClassName(STR_CALENDAR_LIST, STR_ITEM, 'label');

			var CSS_ICON_CARET_DOWN = 'icon-caret-down';

			var TPL_CALENDAR_LIST_EMPTY_MESSAGE = '<div class="' + CSS_CALENDAR_LIST_EMPTY_MESSAGE + '">{message}</div>';

			var TPL_CALENDAR_LIST_ITEM = new A.Template(
				'<tpl for="calendars">',
					'<div class="', CSS_CALENDAR_LIST_ITEM, '">',
						'<div class="', CSS_CALENDAR_LIST_ITEM_COLOR, '" {[ parent.calendars[$i].get("visible") ? ', '\'style="background-color:\'', STR_PLUS, 'parent.calendars[$i].get("color")', STR_PLUS, '";border-color:"', STR_PLUS, 'parent.calendars[$i].get("color")', STR_PLUS, '";\\""', ' : \'', STR_BLANK, '\' ]}></div>',
						'<span class="', CSS_CALENDAR_LIST_ITEM_LABEL, '">{[LString.escapeHTML(parent.calendars[$i].getDisplayName())]}</span>',
						'<div class="', CSS_CALENDAR_LIST_ITEM_ARROW, '">',
							'<i class="', CSS_ICON_CARET_DOWN, '"></i>',
						'</div>',
					'</div>',
				'</tpl>'
			);

			var CalendarList = A.Component.create(
				{
					ATTRS: {
						calendars: {
							setter: '_setCalendars',
							validator: isArray,
							value: []
						},

						scheduler: {
						},

						simpleMenu: {
							setter: '_setSimpleMenu',
							validator: isObject,
							value: null,
							zIndex: Liferay.zIndex.MENU
						},

						strings: {
							value: {
								emptyMessage: Liferay.Language.get('no-calendars-selected')
							}
						}
					},

					NAME: 'calendar-list',

					UI_ATTRS: ['calendars'],

					prototype: {
						initializer: function() {
							var instance = this;

							var emptyMessage = instance.get('strings.emptyMessage');

							instance.emptyMessageNode = A.Node.create(
								Lang.sub(
									TPL_CALENDAR_LIST_EMPTY_MESSAGE,
									{
										message: emptyMessage
									}
								)
							);

							instance.simpleMenu = new Liferay.SimpleMenu(instance.get('simpleMenu'));
						},

						renderUI: function() {
							var instance = this;

							instance._renderCalendars();

							instance.simpleMenu.render();
						},

						bindUI: function() {
							var instance = this;

							var contentBox = instance.get('contentBox');

							instance.on('scheduler-calendar:colorChange', instance._onCalendarColorChange, instance);
							instance.on('scheduler-calendar:visibleChange', instance._onCalendarVisibleChange, instance);
							instance.on('simple-menu:visibleChange', instance._onSimpleMenuVisibleChange, instance);

							contentBox.delegate('click', instance._onClick, STR_DOT + CSS_CALENDAR_LIST_ITEM, instance);

							contentBox.delegate(
								'hover',
								A.bind('_onHoverOver', instance),
								A.bind('_onHoverOut', instance),
								STR_DOT + CSS_CALENDAR_LIST_ITEM
							);
						},

						add: function(calendar) {
							var instance = this;

							var calendars = instance.get('calendars');

							calendars.push(calendar);

							instance.set('calendars', calendars);
						},

						clear: function() {
							var instance = this;

							instance.set('calendars', []);
						},

						getCalendar: function(calendarId) {
							var instance = this;

							var calendars = instance.get('calendars');

							var calendar = null;

							for (var i = 0; i < calendars.length; i++) {
								var cal = calendars[i];

								if (cal.get('calendarId') === calendarId) {
									calendar = cal;

									break;
								}
							}

							return calendar;
						},

						getCalendarByNode: function(node) {
							var instance = this;

							var calendars = instance.get('calendars');

							return calendars[instance.items.indexOf(node)];
						},

						getCalendarNode: function(calendar) {
							var instance = this;

							var calendars = instance.get('calendars');

							return instance.items.item(AArray.indexOf(calendars, calendar));
						},

						remove: function(calendar) {
							var instance = this;

							var calendars = instance.get('calendars');

							if (calendars.length > 0) {
								var index = AArray.indexOf(calendars, calendar);

								if (index > -1) {
									AArray.remove(calendars, index);
								}
							}

							instance.set('calendars', calendars);
						},

						_clearCalendarColor: function(calendar) {
							var instance = this;

							var node = instance.getCalendarNode(calendar);

							var colorNode = node.one(STR_DOT + CSS_CALENDAR_LIST_ITEM_COLOR);

							colorNode.setAttribute('style', STR_BLANK);
						},

						_onCalendarColorChange: function(event) {
							var instance = this;

							var target = event.target;

							if (target.get('visible')) {
								instance._setCalendarColor(target, event.newVal);
							}
						},

						_onCalendarVisibleChange: function(event) {
							var instance = this;

							var target = event.target;

							if (event.newVal) {
								instance._setCalendarColor(target, target.get('color'));
							}
							else {
								instance._clearCalendarColor(target);
							}
						},

						_onClick: function(event) {
							var instance = this;

							var target = event.target.ancestor(STR_DOT + CSS_CALENDAR_LIST_ITEM_ARROW, true, STR_DOT + CSS_CALENDAR_LIST_ITEM);

							if (target) {
								event.stopPropagation();

								var activeNode = instance.activeNode;

								if (activeNode) {
									activeNode.removeClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);
								}

								activeNode = event.currentTarget;

								instance.activeItem = instance.getCalendarByNode(activeNode);

								activeNode.addClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);

								instance.activeNode = activeNode;

								var simpleMenu = instance.simpleMenu;

								simpleMenu.setAttrs(
									{
										alignNode: target,
										visible: ((simpleMenu.get('align.node') !== target) || !simpleMenu.get('visible'))
									}
								);
							}
							else {
								var calendar = instance.getCalendarByNode(event.currentTarget);

								calendar.set('visible', !calendar.get('visible'));
							}
						},

						_onHoverOut: function(event) {
							var instance = this;

							var currentTarget = event.currentTarget;

							var calendar = instance.getCalendarByNode(currentTarget);

							if (!calendar.get('visible')) {
								instance._clearCalendarColor(calendar);
							}

							currentTarget.removeClass(CSS_CALENDAR_LIST_ITEM_HOVER);
						},

						_onHoverOver: function(event) {
							var instance = this;

							var currentTarget = event.currentTarget;

							var calendar = instance.getCalendarByNode(currentTarget);

							currentTarget.addClass(CSS_CALENDAR_LIST_ITEM_HOVER);

							if (!calendar.get('visible')) {
								instance._setCalendarColor(calendar, calendar.get('color'));
							}
						},

						_onSimpleMenuVisibleChange: function(event) {
							var instance = this;

							if (instance.activeNode && !event.newVal) {
								instance.activeNode.removeClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);
							}
						},

						_renderCalendars: function() {
							var instance = this;

							var calendars = instance.get('calendars');
							var contentBox = instance.get('contentBox');

							if (calendars.length === 0) {
								contentBox.setContent(instance.emptyMessageNode);
							}
							else {
								instance.items = A.NodeList.create(
									TPL_CALENDAR_LIST_ITEM.parse(
										{
											calendars: calendars
										}
									)
								);

								contentBox.setContent(instance.items);
							}
						},

						_setCalendarColor: function(calendar, val) {
							var instance = this;

							var node = instance.getCalendarNode(calendar);

							var colorNode = node.one(STR_DOT + CSS_CALENDAR_LIST_ITEM_COLOR);

							colorNode.setStyles(
								{
									backgroundColor: val,
									borderColor: val
								}
							);
						},

						_setCalendars: function(val) {
							var instance = this;

							var scheduler = instance.get('scheduler');

							AArray.each(
								val,
								function(item, index) {
									var calendar = item;

									if (!A.instanceOf(item, Liferay.SchedulerCalendar)) {
										calendar = new Liferay.SchedulerCalendar(item);

										val[index] = calendar;
									}

									calendar.addTarget(instance);

									calendar.set('scheduler', scheduler);
								}
							);

							return val;
						},

						_setSimpleMenu: function(val) {
							var instance = this;

							return A.merge(
								{
									align: {
										points: [A.WidgetPositionAlign.TL, A.WidgetPositionAlign.BL]
									},
									bubbleTargets: [instance],
									constrain: true,
									host: instance,
									items: [],
									plugins: [A.Plugin.OverlayAutohide],
									visible: false,
									width: 290,
									zIndex: Liferay.zIndex.MENU
								},
								val || {}
							);
						},

						_uiSetCalendars: function(val) {
							var instance = this;

							if (instance.get('rendered')) {
								instance._renderCalendars();
							}
						}
					}
				}
			);

			Liferay.CalendarList = CalendarList;
		},
		'',
		{
			requires: ['aui-template-deprecated', 'liferay-scheduler']
		}
	);

	AUI.add(
		'liferay-calendar-simple-color-picker',
		function(A) {
			var	AArray = A.Array;
			var Lang = A.Lang;

			var	getClassName = A.getClassName;

			var	CSS_SIMPLE_COLOR_PICKER_ITEM = getClassName('simple-color-picker', 'item');

			var	CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED = getClassName('simple-color-picker', 'item', 'selected');

			var TPL_SIMPLE_COLOR_PICKER_ITEM = new A.Template(
				'<tpl for="pallete">',
					'<div class="', CSS_SIMPLE_COLOR_PICKER_ITEM, '" style="background-color: {.}', '; border-color:', '{.};', '"></div>',
				'</tpl>'
			);

			var SimpleColorPicker = A.Component.create(
				{
					ATTRS: {
						color: {
							setter: function(val) {
								return val.toUpperCase();
							},
							validator: Lang.isString,
							value: STR_BLANK
						},

						host: {
							value: null
						},

						pallete: {
							setter: function(val) {
								return AArray.invoke(val, 'toUpperCase');
							},
							validator: Lang.isArray,
							value: ['#d96666', '#e67399', '#b373b3', '#8c66d9', '#668cb3', '#668cd9', '#59bfb3', '#65ad89', '#4cb052', '#8cbf40', '#bfbf4d', '#e0c240', '#f2a640', '#e6804d', '#be9494', '#a992a9', '#8997a5', '#94a2be', '#85aaa5', '#a7a77d', '#c4a883', '#c7561e', '#b5515d', '#c244ab']
						}
					},

					NAME: 'simple-color-picker',

					UI_ATTRS: ['color', 'pallete'],

					prototype: {
						renderUI: function() {
							var instance = this;

							instance._renderPallete();
						},

						bindUI: function() {
							var instance = this;

							var contentBox = instance.get('contentBox');

							contentBox.delegate('click', instance._onClickColor, STR_DOT + CSS_SIMPLE_COLOR_PICKER_ITEM, instance);
						},

						_onClickColor: function(event) {
							var instance = this;

							var pallete = instance.get('pallete');

							instance.set('color', pallete[instance.items.indexOf(event.currentTarget)]);
						},

						_renderPallete: function() {
							var instance = this;

							instance.items = A.NodeList.create(
								TPL_SIMPLE_COLOR_PICKER_ITEM.parse(
									{
										pallete: instance.get('pallete')
									}
								)
							);

							instance.get('contentBox').setContent(instance.items);
						},

						_uiSetColor: function(val) {
							var instance = this;

							var pallete = instance.get('pallete');

							instance.items.removeClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);

							var newNode = instance.items.item(AArray.indexOf(pallete, val));

							if (newNode) {
								newNode.addClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);
							}
						},

						_uiSetPallete: function(val) {
							var instance = this;

							if (instance.get('rendered')) {
								instance._renderPallete();
							}
						}
					}
				}
			);

			Liferay.SimpleColorPicker = SimpleColorPicker;
		},
		'',
		{
			requires: ['aui-base', 'aui-template-deprecated']
		}
	);

	AUI.add(
		'liferay-calendar-reminders',
		function(A) {
			var Lang = A.Lang;

			var TPL_REMINDER_SECTION = '<div class="calendar-portlet-reminder-section form-inline">' +
					'<label class="checkbox">' +
						'<input class="calendar-portlet-reminder-check" id="{portletNamespace}reminder{i}" name="{portletNamespace}reminder{i}" type="checkbox" <tpl if="!disabled">checked="checked"</tpl> />' +
					'</label>' +
					'<label class="reminder-type" for="{portletNamespace}reminder{i}">' +
						'<input id="{portletNamespace}reminderType{i}" name="{portletNamespace}reminderType{i}" type="hidden" value="email" />' +
						'{email}' +
					'</label>' +
					'<input class="input-mini reminder-value" name="{portletNamespace}reminderValue{i}" type="text" size="5" value="{time.value}" <tpl if="disabled">disabled="disabled"</tpl> /> ' +
					'<select class="reminder-duration span2" name="{portletNamespace}reminderDuration{i}" <tpl if="disabled">disabled="disabled"</tpl>>' +
						'<option value="60" <tpl if="time.desc == \'minutes\'">selected="selected"</tpl>>{minutes}</option>' +
						'<option value="3600" <tpl if="time.desc == \'hours\'">selected="selected"</tpl>>{hours}</option>' +
						'<option value="86400" <tpl if="time.desc == \'days\'">selected="selected"</tpl>>{days}</option>' +
						'<option value="604800" <tpl if="time.desc == \'weeks\'">selected="selected"</tpl>>{weeks}</option>' +
					'</select>' +
				'</div>';

			var Reminders = A.Component.create(
				{
					ATTRS: {
						portletNamespace: {
							value: ''
						},

						strings: {
							value: {
								days: Liferay.Language.get('days'),
								email: Liferay.Language.get('email'),
								hours: Liferay.Language.get('hours'),
								minutes: Liferay.Language.get('minutes'),
								weeks: Liferay.Language.get('weeks')
							}
						},

						values: {
							validator: Lang.isArray,
							value: [
								{
									interval: 10,
									type: Liferay.CalendarUtil.NOTIFICATION_DEFAULT_TYPE
								},
								{
									interval: 60,
									type: Liferay.CalendarUtil.NOTIFICATION_DEFAULT_TYPE
								}
							]
						}
					},

					NAME: 'reminders',

					UI_ATTRS: ['values'],

					prototype: {
						initializer: function() {
							var instance = this;

							instance.tplReminder = new A.Template(TPL_REMINDER_SECTION);
						},

						bindUI: function() {
							var instance = this;

							var boundingBox = instance.get('boundingBox');

							boundingBox.delegate('change', instance._onChangeCheckbox, '.calendar-portlet-reminder-check', instance);
						},

						_onChangeCheckbox: function(event) {
							var instance = this;

							var target = event.target;
							var checked = target.get('checked');
							var elements = target.ancestor().siblings('input[type=text],select');

							elements.set('disabled', !checked);

							if (checked) {
								elements.first().select();
							}
						},

						_uiSetValues: function(val) {
							var instance = this;

							var boundingBox = instance.get('boundingBox');
							var portletNamespace = instance.get('portletNamespace');
							var strings = instance.get('strings');

							var buffer = [];

							var tplReminder = instance.tplReminder;

							for (var i = 0; i < val.length; i++) {
								var value = val[i];

								buffer.push(
									tplReminder.parse(
										A.merge(
											strings,
											{
												disabled: !value.interval,
												i: i,
												portletNamespace: portletNamespace,
												time: Liferay.Time.getDescription(value.interval)
											}
										)
									)
								);
							}

							boundingBox.setContent(buffer.join(STR_BLANK));
						}
					}
				}
			);

			Liferay.Reminders = Reminders;
		},
		'',
		{
			requires: ['aui-base']
		}
	);

	AUI.add(
		'liferay-calendar-date-picker-util',
		function(A) {
			var Lang = A.Lang;

			var toInt = Lang.toInt;

			Liferay.DatePickerUtil = {
				syncUI: function(form, fieldName, date) {
					var instance = this;

					var amPmNode = form.one('select[name$=' + fieldName + 'AmPm]');
					var hourNode = form.one('select[name$=' + fieldName + 'Hour]');
					var minuteNode = form.one('select[name$=' + fieldName + 'Minute]');

					var datePicker = Liferay.component(Liferay.CalendarUtil.PORTLET_NAMESPACE + fieldName + 'datePicker');

					if (datePicker) {
						datePicker.calendar.deselectDates();
						datePicker.calendar.selectDates(date);

						datePicker.syncUI();
					}

					var hours = date.getHours();
					var minutes = date.getMinutes();

					var amPm = (hours < 12) ? 0 : 1;

					if (amPm === 1) {
						hours -= 12;

						if (hours === 12) {
							hours = 0;
						}
					}

					amPmNode.val(amPm);
					hourNode.val(hours);
					minuteNode.val(minutes);
				},

				linkToSchedulerEvent: function(datePickerContainer, schedulerEvent, dateAttr) {
					var instance = this;

					var selects = A.one(datePickerContainer).all('select');

					selects.on(
						'change',
						function(event) {
							var currentTarget = event.currentTarget;

							var date = schedulerEvent.get(dateAttr);

							var selectedSetter = selects.indexOf(currentTarget);

							var setters = [date.setMonth, date.setDate, date.setFullYear, date.setHours, date.setMinutes, date.setHours];

							var value = toInt(currentTarget.val());

							if ((selectedSetter === 3) && (date.getHours() > 12)) {
								value += 12;
							}

							if (selectedSetter === 5) {
								value = date.getHours() + ((value === 1) ? 12 : -12);
							}

							setters[selectedSetter].call(date, value);

							schedulerEvent.get('scheduler').syncEventsUI();
						}
					);
				}
			};
		},
		'',
		{
			requires: ['aui-base']
		}
	);

	AUI.add(
		'liferay-calendar-recurrence-util',
		function(A) {
			Liferay.RecurrenceUtil = {
				FREQUENCY: {
					DAILY: 'DAILY',
					MONTHLY: 'MONTHLY',
					WEEKLY: 'WEEKLY',
					YEARLY: 'YEARLY'
				},

				INTERVAL_LABELS: {
					DAILY: Liferay.Language.get('days'),
					MONTHLY: Liferay.Language.get('months'),
					WEEKLY: Liferay.Language.get('weeks'),
					YEARLY: Liferay.Language.get('years')
				},

				MONTH_LABELS: [
					Liferay.Language.get('january'),
					Liferay.Language.get('february'),
					Liferay.Language.get('march'),
					Liferay.Language.get('april'),
					Liferay.Language.get('may'),
					Liferay.Language.get('june'),
					Liferay.Language.get('july'),
					Liferay.Language.get('august'),
					Liferay.Language.get('september'),
					Liferay.Language.get('october'),
					Liferay.Language.get('november'),
					Liferay.Language.get('december')
				],

				POSITION_LABELS: {
					'-1': Liferay.Language.get('last'),
					'1': Liferay.Language.get('first'),
					'2': Liferay.Language.get('second'),
					'3': Liferay.Language.get('third'),
					'4': Liferay.Language.get('fourth')
				},

				WEEKDAY_LABELS: {
					FR: Liferay.Language.get('weekday.FR'),
					MO: Liferay.Language.get('weekday.MO'),
					SA: Liferay.Language.get('weekday.SA'),
					SU: Liferay.Language.get('weekday.SU'),
					TH: Liferay.Language.get('weekday.TH'),
					TU: Liferay.Language.get('weekday.TU'),
					WE: Liferay.Language.get('weekday.WE')
				},

				getSummary: function(recurrence) {
					var instance = this;

					var month = null;
					var position = null;
					var template = [];
					var weekDay = null;

					if (recurrence.interval == 1) {
						template.push(recurrence.frequency);
					}
					else {
						template.push(Liferay.Language.get('every'), ' {interval} {intervalLabel}');
					}

					if (recurrence.positionalWeekday) {
						if (recurrence.frequency == instance.FREQUENCY.MONTHLY) {
							template.push(STR_SPACE, Liferay.Language.get('on'), ' {position} {weekDay}');
						}
						else {
							template.push(STR_SPACE, Liferay.Language.get('on-the'), ' {position} {weekDay} ', Liferay.Language.get('of'), ' {month}');
						}

						month = instance.MONTH_LABELS[recurrence.positionalWeekday.month];
						position = instance.POSITION_LABELS[recurrence.positionalWeekday.position];
						weekDay = instance.WEEKDAY_LABELS[recurrence.positionalWeekday.weekday];
					}
					else if ((recurrence.frequency == instance.FREQUENCY.WEEKLY) && (recurrence.weekdays.length > 0)) {
						template.push(STR_SPACE, TPL_SPAN, Liferay.Language.get('on'), TPL_SPAN_CLOSE, ' {weekDays}');
					}

					if (recurrence.count && (recurrence.endValue === 'after')) {
						template.push(', {count} ', Liferay.Language.get('times'));
					}
					else if (recurrence.untilDate && (recurrence.endValue === 'on')) {
						var untilDate = recurrence.untilDate;

						template.push(
							STR_COMMA,
							STR_SPACE,
							TPL_SPAN,
							Liferay.Language.get('until'),
							TPL_SPAN_CLOSE,
							A.Lang.sub(
								' {month} {date}, {year}',
								{
									date: untilDate.getDate(),
									month: instance.MONTH_LABELS[untilDate.getMonth()],
									year: untilDate.getFullYear()
								}
							)
						);
					}

					var summary = A.Lang.sub(
						template.join(STR_BLANK),
						{
							count: recurrence.count,
							interval: recurrence.interval,
							intervalLabel: instance.INTERVAL_LABELS[recurrence.frequency],
							month: month,
							position: position,
							weekDay: weekDay,
							weekDays: recurrence.weekdays.join(', ')
						}
					);

					return A.Lang.String.capitalize(summary);
				},

				openConfirmationPanel: function(actionName, onlyThisInstanceFn, allFollowingFn, allEventsInFn, cancelFn) {
					var instance = this;

					var changeDeleteText;
					var confirmationPanel;
					var titleText;

					if (actionName === 'delete') {
						titleText = Liferay.Language.get('delete-recurring-event');
						changeDeleteText = Liferay.Language.get('would-you-like-to-delete-only-this-event-all-events-in-the-series-or-this-and-all-future-events-in-the-series');
					}
					else {
						titleText = Liferay.Language.get('change-recurring-event');
						changeDeleteText = Liferay.Language.get('would-you-like-to-change-only-this-event-all-events-in-the-series-or-this-and-all-future-events-in-the-series');
					}

					var getButtonConfig = function(label, callback) {
						return {
							label: label,
							on: {
								click: function() {
									if (callback) {
										callback.apply(this, arguments);
									}

									confirmationPanel.hide();
								}
							}
						};
					};

					confirmationPanel = Liferay.Util.Window.getWindow(
						{
							dialog:	{
								bodyContent: changeDeleteText,
								destroyOnHide: true,
								height: 250,
								hideOn: [],
								resizable: false,
								toolbars: {
									footer: [
										getButtonConfig(Liferay.Language.get('only-this-instance'), onlyThisInstanceFn),
										getButtonConfig(Liferay.Language.get('all-following'), allFollowingFn),
										getButtonConfig(Liferay.Language.get('all-events-in-the-series'), allEventsInFn),
										getButtonConfig(Liferay.Language.get('cancel-this-change'), cancelFn)
									]
								},
								width: 700
							},
							title: titleText
						}
					);

					return confirmationPanel.render().show();
				}
			};
		},
		'',
		{
			requires: ['aui-base', 'liferay-util-window']
		}
	);

	AUI.add(
		'liferay-calendar-message-util',
		function(A) {
			var Lang = A.Lang;
			var LString = Lang.String;

			var TPL_MESSAGE_UPDATE_ALL_INVITED = '<p class="calendar-portlet-confirmation-text">' +
					Liferay.Language.get('invited-users-will-be-notified') +
				'</p>';

			Liferay.CalendarMessageUtil = {

				confirm: function(message, yesButtonLabel, noButtonLabel, yesFn, noFn) {
					var instance = this;

					var confirmationPanel;

					var getButtonConfig = function(label, callback) {
						return {
							label: label,
							on: {
								click: function() {
									if (callback) {
										callback.apply(this, arguments);
									}

									confirmationPanel.hide();
								}
							}
						};
					};

					confirmationPanel = Liferay.Util.Window.getWindow(
						{
							dialog: {
								bodyContent: message,
								height: 250,
								hideOn: [],
								resizable: false,
								toolbars: {
									footer: [
										getButtonConfig(yesButtonLabel, yesFn),
										getButtonConfig(noButtonLabel, noFn)
									]
								},
								width: 700
							},
							title: Liferay.Language.get('are-you-sure')
						}
					);

					return confirmationPanel.render().show();
				},

				promptSchedulerEventUpdate: function(data) {
					var instance = this;

					data.answers = {};

					var queue = new A.AsyncQueue();

					if (data.recurring) {
						queue.add(
							{
								args: [data],
								autoContinue: false,
								context: instance,
								fn: instance._queueableQuestionUpdateRecurring,
								timeout: 0
							}
						);
					}

					if (data.masterBooking) {
						if (data.hasChild) {
							queue.add(
								{
									args: [data],
									autoContinue: false,
									context: instance,
									fn: instance._queueableQuestionUpdateAllInvited,
									timeout: 0
								}
							);
						}
					}
					else {
						queue.add(
							{
								args: [data],
								autoContinue: false,
								context: instance,
								fn: instance._queueableQuestionUserCalendarOnly,
								timeout: 0
							}
						);
					}

					queue.add(
						{
							args: [data],
							autoContinue: false,
							context: instance,
							fn: data.resolver,
							timeout: 0
						}
					);

					instance.queue = queue;

					queue.run();
				},

				_queueableQuestionUpdateAllInvited: function(data) {
					var instance = this;

					var answers = data.answers;

					var showNextQuestion = A.bind('run', instance.queue);

					if (answers.cancel) {
						A.soon(showNextQuestion);
					}
					else {
						Liferay.CalendarMessageUtil.confirm(
							TPL_MESSAGE_UPDATE_ALL_INVITED,
							Liferay.Language.get('save-changes'),
							Liferay.Language.get('do-not-change-the-event'),
							showNextQuestion,
							function() {
								answers.cancel = true;

								showNextQuestion();
							}
						);
					}
				},

				_queueableQuestionUpdateRecurring: function(data) {
					var instance = this;

					var answers = data.answers;

					var showNextQuestion = A.bind('run', instance.queue);

					if (answers.cancel) {
						A.soon(showNextQuestion);
					}
					else {
						Liferay.RecurrenceUtil.openConfirmationPanel(
							'update',
							function() {
								answers.updateInstance = true;

								showNextQuestion();
							},
							function() {
								answers.allFollowing = true;
								answers.updateInstance = true;

								showNextQuestion();
							},
							showNextQuestion,
							function() {
								answers.cancel = true;

								showNextQuestion();
							}
						);
					}
				},

				_queueableQuestionUserCalendarOnly: function(data) {
					var instance = this;

					var answers = data.answers;

					var showNextQuestion = A.bind('run', instance.queue);

					if (answers.cancel) {
						A.soon(showNextQuestion);
					}
					else {
						var content = [
							'<p class="calendar-portlet-confirmation-text">',
							Lang.sub(
								Liferay.Language.get('you-are-about-to-make-changes-that-will-only-affect-your-calendar-x'),
								[LString.escapeHTML(data.calendarName)]
							),
							'</p>'
						].join(STR_BLANK);

						Liferay.CalendarMessageUtil.confirm(
							content,
							Liferay.Language.get('save-changes'),
							Liferay.Language.get('do-not-change-the-event'),
							showNextQuestion,
							function() {
								answers.cancel = true;

								showNextQuestion();
							}
						);
					}
				},

				showAlert: function(container, message) {
					new A.Alert(
						{
							animated: true,
							bodyContent: message,
							closeable: true,
							cssClass: 'alert-success',
							destroyOnHide: true,
							duration: 1
						}
					).render(container);
				}
			};
		},
		'',
		{
			requires: ['aui-alert', 'liferay-util-window']
		}
	);
}());