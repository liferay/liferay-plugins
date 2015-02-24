AUI.add(
	'scheduler-mobile',
	function(A) {
		var Lang = A.Lang;

		var CSS_SCHEDULER_VIEW = A.getClassName('scheduler-base', 'view');

		var CSS_SCHEDULER_VIEW_ = A.getClassName('scheduler-base', 'view', '');

		var STR_LOCALE = 'locale';

		var STR_NAME = 'name';

		var STR_SCHEDULER = 'scheduler';

		var STR_SPACE = ' ';

		var TPL_SCHEDULER_VIEWS_SELECT = '<select class="form-control visible-phone"></select>';

		var TPL_SCHEDULER_VIEW_BUTTON = '<button type="button" class="' + [CSS_SCHEDULER_VIEW, CSS_SCHEDULER_VIEW_].join(STR_SPACE) +
				'{name}" data-view-name="{name}">{label}</button>';

		var TPL_SCHEDULER_VIEW_LIST = '<option class="' + [CSS_SCHEDULER_VIEW, CSS_SCHEDULER_VIEW_].join(STR_SPACE) +
				'{name}" data-view-name="{name}">{label}</option>';

		var SchedulerMobile = A.Component.create(
			{
				ATTRS: {
					viewsSelectNode: {
						valueFn: function() {
							return A.Node.create(TPL_SCHEDULER_VIEWS_SELECT);
						}
					}
				},

				EXTENDS: A.Scheduler,

				NAME: 'scheduler-mobile',

				prototype: {
					initializer: function() {
						var instance = this;

						instance.viewsSelectNode = instance.get('viewsSelectNode');
					},

					getViewTriggerNode: function(view) {
						var instance = this;

						var name = view.get(STR_NAME);

						var viewportWidth = A.DOM.winWidth() + A.DOM.getScrollbarWidth();

						var nodeSelector = '.' + CSS_SCHEDULER_VIEW_ + name;

						var viewNode = instance.viewsSelectNode.one(nodeSelector);

						if (viewportWidth >= 768) {
							viewNode = instance.viewsNode.one(nodeSelector);
						}

						return viewNode;
					},

					renderDropdownList: function() {
						var instance = this;

						instance.viewsSelectNode.on(
							'change',
							A.bind(instance._onSelectionChange, instance)
						);
					},

					renderView: function(view) {
						var instance = this;

						SchedulerMobile.superclass.renderView.apply(this, arguments);

						if (view) {
							instance.bodyNode.prepend(instance.viewDateNode);
						}
					},

					syncStdContent: function() {
						var instance = this;

						var views = instance.get('views');

						SchedulerMobile.superclass.syncStdContent.apply(this, arguments);

						instance.iconPrevNode.insert(instance.todayNode, 'after');

						A.Array.each(
							views,
							function(view) {
								instance.viewsSelectNode.append(instance._createViewTriggerNode(view, TPL_SCHEDULER_VIEW_LIST));
							}
						);

						instance.viewsNode.all('button').addClass('hidden-phone');

						instance.viewsNode.append(instance.viewsSelectNode);
					},

					_afterRender: function(event) {
						var instance = this;

						SchedulerMobile.superclass._afterRender.apply(this, arguments);

						instance.renderDropdownList();
					},

					_createViewTriggerNode: function(view, tpl) {
						var instance = this;

						var name = view.get(STR_NAME);

						var template = tpl || TPL_SCHEDULER_VIEW_BUTTON;

						return A.Node.create(
							Lang.sub(
								template,
								{
									label: (instance.getString(name) || name),
									name: name
								}
							)
						);
					},

					_onSelectionChange: function(event) {
						var instance = this;

						var target = event.target;

						var index = target.get('selectedIndex');
						var options = target.get('options');

						var viewName = options.item(index).attr('data-view-name');

						instance.set(
							'activeView',
							instance.getViewByName(viewName)
						);
					},

					_uiSetActiveView: function(val) {
						var instance = this;

						SchedulerMobile.superclass._uiSetActiveView.apply(this, arguments);

						if (val) {
							var activeView = val.get(STR_NAME);

							var activeNav = instance.viewsNode.one('.' + CSS_SCHEDULER_VIEW_ + activeView);

							if (activeNav) {
								var viewSelectNode = instance.viewsSelectNode.one('[data-view-name=' + activeView + ']');

								viewSelectNode.set('selected', true);
							}
						}
					}
				}
			}
		);

		A.Scheduler = A.mix(SchedulerMobile, A.Scheduler);

		A.SchedulerView.ATTRS = A.mix(
			{
				triggerNode: {
					getter: function() {
						return this.get(STR_SCHEDULER).getViewTriggerNode(this);
					},
					setter: A.one
				}
			},
			A.SchedulerView.ATTRS
		);

		A.SchedulerAgendaView.ATTRS = A.mix(
			{
				navigationDateFormatter: {
					validator: Lang.isFunction,
					value: function() {
						return '';
					}
				}
			},
			A.SchedulerAgendaView.ATTRS
		);

		A.SchedulerDayView.ATTRS = A.mix(
			{
				headerDateFormatter: {
					validator: Lang.isString,
					value: function(date) {
						var instance = this;

						var scheduler = instance.get(STR_SCHEDULER);

						return A.DataType.Date.format(
							date,
							{
								format: '<span>%a</span> <span>%d</span>',
								locale: scheduler.get(STR_LOCALE)
							}
						);
					}
				}
			},
			A.SchedulerDayView.ATTRS
		);

		A.SchedulerTableView.ATTRS = A.mix(
			{
				headerDateFormatter: {
					value: function(date) {
						var instance = this;

						var scheduler = instance.get(STR_SCHEDULER);

						return A.DataType.Date.format(
							date,
							{
								format: '%a',
								locale: scheduler.get(STR_LOCALE)
							}
						);
					},
					validator: A.Lang.isString
				}
			},
			A.SchedulerTableView.ATTRS
		);
	},
	'',
	{
		requires: ['aui-scheduler']
	}
);