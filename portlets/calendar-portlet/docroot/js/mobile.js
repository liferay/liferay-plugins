AUI.add(
	'scheduler-mobile',
	function(A) {
		var SchedulerMobile = A.Component.create(
			{
				ATTRS: {},

				EXTENDS: A.Scheduler,
				
				NAME: 'scheduler-mobile',

				prototype: {
					syncStdContent: function() {
						var instance = this;

						SchedulerMobile.superclass.syncStdContent.apply(this, arguments);

						instance.iconPrevNode.insert(instance.todayNode, 'after');
					}
				}
			}
		);

		A.Scheduler = A.mix(SchedulerMobile, A.Scheduler);
	},
	'',
	{
		requires: []
	}
);