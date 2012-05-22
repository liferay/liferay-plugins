(function() {
	var STR_BLANK = '';
	var STR_DASH = '-';
	var STR_DOT = '.';
	var STR_PLUS = '+';
	var STR_SPACE = ' ';

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

			var TPL_SIMPLE_MENU_ITEM = '<li class="{cssClass}" data-id="{id}">{caption}</li>';

			var getItemHandler = A.cached(
				function(id, items) {
					var found = null;

					AArray.some(
						items,
						function(item, index, collection) {
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

					AUGMENTS: [A.WidgetStdMod, A.WidgetPosition, A.WidgetStack, A.WidgetPositionAlign, A.WidgetPositionConstrain],

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

							var contentBox = instance.get('contentBox');

							contentBox.delegate('click', instance._onClickItems, STR_DOT + CSS_SIMPLE_MENU_ITEM, instance);

							A.getDoc().on('click', instance._onClickDocument, instance);
						},

						_onClickDocument: function() {
							var instance = this;

							instance.hide();
						},

						_onClickItems: function(event) {
							var instance = this;

							event.stopPropagation();

							var items = instance.get('items');

							var id = event.currentTarget.attr('data-id');

							var handler = getItemHandler(id, items);

							if (handler) {
								handler.apply(instance, arguments);
							}
						},

						_renderItems: function(items) {
							var instance = this;

							var contentBox = instance.get('contentBox');
							var hiddenItems = instance.get('hiddenItems');

							instance.items = A.NodeList.create();

							AArray.each(
								items,
								function(item, index, collection) {
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

									var li = A.Node.create(
										Lang.sub(
											TPL_SIMPLE_MENU_ITEM,
											{
												cssClass: cssClass,
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
									function(item, index, collection) {
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
		'' ,
		{
			requires: ['aui-base', 'aui-template', 'widget-position', 'widget-position-align', 'widget-position-constrain', 'widget-stack', 'widget-stdmod']
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

			var TPL_CALENDAR_LIST_EMPTY_MESSAGE = '<div class="' + CSS_CALENDAR_LIST_EMPTY_MESSAGE + '">{message}</div>';

			var TPL_CALENDAR_LIST_ITEM = new A.Template(
				'<tpl for="calendars">',
					'<div class="', CSS_CALENDAR_LIST_ITEM, '">',
						'<div class="', CSS_CALENDAR_LIST_ITEM_COLOR, '" {[ parent.calendars[$i].get("visible") ? ', '\'style="background-color:\'', STR_PLUS, 'parent.calendars[$i].get("color")', STR_PLUS, '";border-color:"', STR_PLUS, 'parent.calendars[$i].get("color")', STR_PLUS, '";\\""', ' : \'', STR_BLANK, '\' ]}></div>',
							'<span class="', CSS_CALENDAR_LIST_ITEM_LABEL, '">{[ parent.calendars[$i].getDisplayName() ]}</span>',
						'<div href="javascript:;" class="', CSS_CALENDAR_LIST_ITEM_ARROW, '"></div>',
					'</div>',
				'</tpl>'
			);

			var CalendarList = A.Component.create(
				{
					NAME: 'calendar-list',

					ATTRS: {
						calendars: {
							setter: '_setCalendars',
							validator: isArray,
							value: []
						},

						simpleMenu: {
							setter: '_setSimpleMenu',
							validator: isObject,
							value: null
						},

						strings: {
							value: {
								emptyMessage: Liferay.Language.get('no-calendars-selected')
							}
						}
					},

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
								A.bind(instance._onHoverOver, instance),
								A.bind(instance._onHoverOut, instance),
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
							var instance= this;

							instance.set('calendars', []);
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

							AArray.remove(calendars, AArray.indexOf(calendars, calendar));

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

							var target = event.target;

							if (target.hasClass(CSS_CALENDAR_LIST_ITEM_ARROW)) {
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
										'align.node': target,
										visible: ((simpleMenu.get('align.node') !== target) || !simpleMenu.get('visible'))
									}
								);
							}
							else {
								var calendar = instance.getCalendarByNode(event.currentTarget);

								calendar.set('visible', !calendar.get('visible'));
							}
						},

						_onHoverOver: function(event) {
							var instance = this;

							var currentTarget = event.currentTarget;

							var calendar = instance.getCalendarByNode(currentTarget);

							instance._setCalendarColor(calendar, calendar.get('color'));

							currentTarget.addClass(CSS_CALENDAR_LIST_ITEM_HOVER);
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

							AArray.each(
								val,
								function(item, index, collection) {
									if (!A.instanceOf(item, Liferay.SchedulerCalendar)) {
										val[index] = new Liferay.SchedulerCalendar(item);
									}

									val[index].addTarget(instance);
								}
							);

							return val;
						},

						_setSimpleMenu: function(val) {
							var instance = this;

							return A.merge(
								{
									align: {
										points: [ A.WidgetPositionAlign.TL, A.WidgetPositionAlign.BL ]
									},
									bubbleTargets: [ instance ],
									host: instance,
									items: [],
									plugins: [ A.Plugin.OverlayAutohide ],
									visible: false,
									width: 290,
									zIndex: 500
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
			requires: ['aui-template', 'liferay-scheduler']
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
					'<div class="', CSS_SIMPLE_COLOR_PICKER_ITEM, '" style="background-color: {.}', '; border-color:', '{.};','"></div>',
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

							var newNode = instance.items.item(pallete.indexOf(val));

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
			requires: ['aui-base', 'aui-template']
		}
	);
}());