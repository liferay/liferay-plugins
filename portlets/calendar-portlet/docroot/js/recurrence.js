AUI.add(
	'liferay-calendar-recurrence-dialog',
	function(A) {
		var FREQUENCY_MONTHLY = 'MONTHLY';

		var FREQUENCY_WEEKLY = 'WEEKLY';

		var FREQUENCY_YEARLY = 'YEARLY';

		var LIMIT_COUNT = 'after';

		var LIMIT_DATE = 'on';

		var LIMIT_UNLIMITED = 'never';

		var DAYS_OF_WEEK = ['SU', 'MO', 'TU', 'WE', 'TH', 'FR', 'SA'];

		var RecurrenceDialogController = A.Component.create(
			{

				ATTRS: {
					container: {
						setter: A.one,
						value: null
					},

					dayOfWeekSelect: {
						setter: A.one,
						value: null
					},

					daysOfWeek: {
						getter: '_getDaysOfWeek'
					},

					daysOfWeekCheckboxes: {
						getter: '_getDaysOfWeekCheckboxes'
					},

					frequency: {
						getter: '_getFrequency'
					},

					frequencySelect: {
						setter: A.one,
						value: null
					},

					interval: {
						getter: '_getInterval'
					},

					intervalSelect: {
						setter: A.one,
						value: null
					},

					limitCount: {
						getter: '_getLimitCount'
					},

					limitCountInput: {
						setter: A.one,
						value: null
					},

					limitCountRadioButton: {
						setter: A.one,
						value: null
					},

					limitDate: {
						getter: '_getLimitDate'
					},

					limitDateDatePicker: {
						value: null
					},

					limitDateRadioButton: {
						setter: A.one,
						value: null
					},

					limitRadioButtons: {
						getter: '_getLimitRadioButtons'
					},

					limitType: {
						getter: '_getLimitType'
					},

					monthlyRecurrenceOptions: {
						setter: A.one,
						value: null
					},

					noLimitRadioButton: {
						setter: A.one,
						value: null
					},

					positionalDayOfWeek: {
						getter: '_getPositionalDayOfWeek'
					},

					positionalDayOfWeekOptions: {
						setter: A.one,
						value: null
					},

					positionSelect: {
						setter: A.one,
						value: null
					},

					recurrence: {
						getter: '_getRecurrence'
					},

					repeatCheckbox: {
						setter: A.one,
						value: null
					},

					repeatOnDayOfMonthRadioButton: {
						setter: A.one,
						value: null
					},

					repeatOnDayOfWeekRadioButton: {
						setter: A.one,
						value: null
					},

					startDateDatePicker: {
						value: null
					},

					summary: {
						getter: '_getSummary'
					},

					weeklyRecurrenceOptions: {
						setter: A.one,
						value: null
					}
				},

				NAME: 'recurrence-dialog',

				prototype: {
					initializer: function() {
						var instance = this;

						instance.bindUI();
					},

					bindUI: function() {
						var instance = this;

						var container = instance.get('container');

						var limitDateDatePicker = instance.get('limitDateDatePicker');

						var startDateDatePicker = instance.get('startDateDatePicker');

						container.delegate('change', A.bind(instance._onInputChange, instance), 'select,input');
						container.delegate('keypress', A.bind(instance._onInputChange, instance), 'select');

						limitDateDatePicker.after('selectionChange', A.bind(instance._onInputChange, instance));

						startDateDatePicker.after('selectionChange', A.bind(instance._onStartDateDatePickerChange, instance));
					},

					_getDaysOfWeek: function() {
						var instance = this;

						var dayOfWeekNodes = instance.get('daysOfWeekCheckboxes').filter(':checked');

						return dayOfWeekNodes.val();
					},

					_getDaysOfWeekCheckboxes: function() {
						var instance = this;

						var weeklyRecurrenceOptions = instance.get('weeklyRecurrenceOptions');

						return weeklyRecurrenceOptions.all(':checkbox');
					},

					_getFrequency: function() {
						var instance = this;

						var frequencySelect = instance.get('frequencySelect');

						return frequencySelect.val();
					},

					_getInterval: function() {
						var instance = this;

						var intervalSelect = instance.get('intervalSelect');

						return intervalSelect.val();
					},

					_getLimitCount: function() {
						var instance = this;

						var limitCountInput = instance.get('limitCountInput');

						return parseInt(limitCountInput.val(), 10);
					},

					_getLimitDate: function() {
						var instance = this;

						var limitDateDatePicker = instance.get('limitDateDatePicker');

						return limitDateDatePicker.getDate();
					},

					_getLimitRadioButtons: function() {
						var instance = this;

						return [instance.get('limitCountRadioButton'), instance.get('limitDateRadioButton'), instance.get('noLimitRadioButton')];
					},

					_getLimitType: function() {
						var instance = this;

						var checkedLimitRadioButton = A.Array.find(
							instance.get('limitRadioButtons'),
							function(item, index) {
								return item.get('checked');
							}
						);

						return checkedLimitRadioButton && checkedLimitRadioButton.val();
					},

					_getPositionalDayOfWeek: function() {
						var instance = this;

						var dayOfWeekSelect = instance.get('dayOfWeekSelect');
						var frequency = instance.get('frequency');
						var positionSelect = instance.get('positionSelect');

						var positionalDayOfWeek = null;

						var repeatOnDayOfWeek = instance.get('repeatOnDayOfWeekRadioButton').get('checked');

						var startDateDatePicker = instance.get('startDateDatePicker');

						if ((frequency === FREQUENCY_MONTHLY) || (frequency === FREQUENCY_YEARLY)) {
							if (repeatOnDayOfWeek) {
								positionalDayOfWeek = {
									month: startDateDatePicker.getDate().getMonth(),
									position: positionSelect.val(),
									weekday: dayOfWeekSelect.val()
								};
							}
						}

						return positionalDayOfWeek;
					},

					_getRecurrence: function() {
						var instance = this;

						return {
							count: instance.get('limitCount'),
							endValue: instance.get('limitType'),
							frequency: instance.get('frequency'),
							interval: instance.get('interval'),
							positionalWeekday: instance.get('positionalDayOfWeek'),
							untilDate: instance.get('limitDate'),
							weekdays: instance.get('daysOfWeek')
						};
					},

					_getSummary: function() {
						var instance = this;

						var recurrence = instance.get('recurrence');

						return Liferay.RecurrenceUtil.getSummary(recurrence);
					},

					_onInputChange: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var limitCountInput = instance.get('limitCountInput');
						var limitDateDatePicker = instance.get('limitDateDatePicker');
						var limitType = instance.get('limitType');

						if (currentTarget === instance.get('frequencySelect')) {
							instance._toggleView('weeklyRecurrenceOptions', currentTarget.val() === FREQUENCY_WEEKLY);
							instance._toggleView('monthlyRecurrenceOptions', (currentTarget.val() === FREQUENCY_MONTHLY) || (currentTarget.val() === FREQUENCY_YEARLY));
						}

						if (currentTarget === instance.get('repeatOnDayOfWeekRadioButton')) {
							instance._toggleView('positionalDayOfWeekOptions', currentTarget.val() === 'true');
						}

						var disableLimitcountInput = (limitType === LIMIT_UNLIMITED) || (limitType === LIMIT_DATE);

						Liferay.Util.toggleDisabled(limitCountInput, disableLimitcountInput);

						limitCountInput.selectText();

						var disableLimitDateDatePicker = (limitType === LIMIT_UNLIMITED) || (limitType === LIMIT_COUNT);

						limitDateDatePicker.set('disabled', disableLimitDateDatePicker);

						instance.fire('recurrenceChange');
					},

					_onStartDateDatePickerChange: function(event) {
						var instance = this;

						var date = event.newSelection[0];

						var dayOfWeek = DAYS_OF_WEEK[date.getDay()];

						var daysOfWeekCheckboxes = instance.get('daysOfWeekCheckboxes');

						daysOfWeekCheckboxes.each(
							function(item) {
								if (item.get('value') == dayOfWeek) {
									item.set('checked', true);
									item.set('disabled', true);
								}
								else if (item.get('disabled')) {
									item.set('disabled', false);
								}
							}
						);

						var repeatCheckbox = instance.get('repeatCheckbox');

						if (repeatCheckbox.get('checked')) {
							instance.fire('recurrenceChange');
						}
					},

					_toggleView: function(viewName, show) {
						var instance = this;

						var viewNode = instance.get(viewName);

						if (viewNode) {
							viewNode.toggle(show);
						}
					}
				}
			}
		);

		Liferay.RecurrenceDialogController = RecurrenceDialogController;
	},
	'',
	{
		requires: ['aui-base', 'liferay-calendar-recurrence-util']
	}
);