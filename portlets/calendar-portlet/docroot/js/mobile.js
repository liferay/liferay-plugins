AUI.add(
	'scheduler-mobile',
	function(A) {
		var CSS_SCHEDULER_VIEW = A.getClassName('scheduler-base', 'view'),
			CSS_SCHEDULER_VIEW_ = A.getClassName('scheduler-base', 'view', ''),
			
			TPL_SCHEDULER_VIEW_BUTTON = '<button type="button" class="' + [CSS_SCHEDULER_VIEW, CSS_SCHEDULER_VIEW_].join(' ') +
				'{name}" data-view-name="{name}">{label}</button>',
			TPL_SCHEDULER_VIEW_LIST = '<option class="' + [CSS_SCHEDULER_VIEW, CSS_SCHEDULER_VIEW_].join(' ') +
				'{name}" data-view-name="{name}">{label}</option>',
			TPL_SCHEDULER_VIEWS_SELECT = '<select class="form-control visible-phone"></select>';
		
		var SchedulerMobile = A.Component.create(
			{
				ATTRS: {
					viewsSelectNode: {
						valueFn: function() {
							return A.Node.create(TPL_SCHEDULER_VIEWS_SELECT);
						}
					},
				},

				EXTENDS: A.Scheduler,
				
				NAME: 'scheduler-mobile',

				prototype: {
					initializer: function() {
						var instance = this;

						instance.viewsSelectNode = instance.get('viewsSelectNode');
					},

					getViewTriggerNode: function(view) {
						var instance = this,
							name = view.get('name'),
							viewportWidth = A.DOM.winWidth() + A.DOM.getScrollbarWidth();

						if (viewportWidth >= 768) {
							return instance.viewsNode.one('.' + CSS_SCHEDULER_VIEW_ + name);
						}

						return instance.viewsSelectNode.one('.' + CSS_SCHEDULER_VIEW_ + name);
					},

					renderDropdownList: function() {
						var instance = this;

						instance.viewsSelectNode.on('change', A.bind(instance._onSelectionChange, instance));
					},

					renderView: function(view) {
						var instance = this;

						SchedulerMobile.superclass.renderView.apply(this, arguments);

						if (view) {
							instance.bodyNode.prepend(instance.viewDateNode);
						}
					},

					syncStdContent: function() {
						var instance = this,
							views = instance.get('views');

						SchedulerMobile.superclass.syncStdContent.apply(this, arguments);

						instance.iconPrevNode.insert(instance.todayNode, 'after');

						A.Array.each(views, function(view) {
							instance.viewsSelectNode.append(instance._createViewTriggerNode(view, TPL_SCHEDULER_VIEW_LIST));
						});

						instance.viewsNode.all('button').addClass('hidden-phone');
						instance.viewsNode.append(instance.viewsSelectNode);
					},

					_afterRender: function(event) {
						var instance = this;

						SchedulerMobile.superclass._afterRender.apply(this, arguments);

						instance.renderDropdownList();
					},

					_createViewTriggerNode: function(view, tpl) {
						var instance = this,
							name = view.get('name'),
							tpl = tpl ? tpl : TPL_SCHEDULER_VIEW_BUTTON;

						return A.Node.create(
								A.Lang.sub(tpl, {
									name: name,
									label: (instance.getString(name) || name)
								})
								);
					},

					_onSelectionChange: function(event) {
						var instance = this,
							target = event.target,
							index = target.get('selectedIndex'),
							viewName = target.get('options').item(index).attr('data-view-name');

						instance.set('activeView', instance.getViewByName(viewName));
					},

					_uiSetActiveView: function(val) {
						var instance = this;

						SchedulerMobile.superclass._uiSetActiveView.apply(this, arguments);

						if (val) {
							var activeView = val.get('name'),
								activeNav = instance.viewsNode.one('.' + CSS_SCHEDULER_VIEW_ + activeView);

							if (activeNav) {
								instance.viewsSelectNode.one('[data-view-name=' + activeView + ']').set('selected', true);
							}
						}
					}
				}
			}
		);

		A.Scheduler = A.mix(SchedulerMobile, A.Scheduler);

		A.SchedulerView.ATTRS = A.mix({
			triggerNode: {
				getter: function() {
					return this.get('scheduler').getViewTriggerNode(this);
				},
				setter: A.one
			}
		}, A.SchedulerView.ATTRS);
	},
	'',
	{
		requires: []
	}
);