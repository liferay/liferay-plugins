AUI.add(
	'liferay-calendar-recurrence-converter',
	function(A) {
		var Lang = A.Lang;

		var RRULE = 'RRULE';

		var STR_COLON = ':';

		var STR_COMMA = ',';

		var STR_EMPTY = '';

		var STR_EQUALS = '=';

		var STR_SEMICOLON = ';';

		var STR_ZERO = '0';

		var WEEKLY = 'WEEKLY';

		var RecurrenceConverter = function() {};

		A.mix(
			RecurrenceConverter.prototype,
			{
				encodeRecurrence: function(recurrence) {
					var instance = this;

					var string = null;

					if (recurrence) {
						var components = [];

						for (var key in recurrence) {
							var value = recurrence[key];

							components.push(key.toUpperCase() + STR_EQUALS + instance._encode(value));
						}

						var params = components.join(STR_SEMICOLON);

						if (components.length > 0) {
							string = RRULE + STR_COLON + params;
						}
					}

					return string;
				},

				parseRecurrence: function(string) {
					var instance = this;

					var recurrence = null;

					if (string.startsWith(RRULE + STR_COLON)) {
						string = string.slice(6);

						var params = string.split(STR_SEMICOLON);

						recurrence = {};

						for (var i in params) {
							var pair = params[i].split(STR_EQUALS);

							recurrence[pair[0].toLowerCase()] = pair[1];
						}

						if (recurrence.interval) {
							recurrence.interval = Lang.toInt(recurrence.interval);
						}

						if (recurrence.count) {
							recurrence.count = Lang.toInt(recurrence.count);
						}

						if (recurrence.until) {
							recurrence.until = instance._parseDate(recurrence.until);
						}

						if (recurrence.freq === WEEKLY) {
							recurrence.byday = recurrence.byday.split(STR_COMMA);
						}
						else if (recurrence.byday) {
							recurrence.byday = instance._parsePositionalByDay(recurrence.byday);

							if (recurrence.month) {
								recurrence.bymonth = Lang.toInt(recurrence.bymonth);
							}
						}
					}

					return recurrence;
				},

				_encode: function(value) {
					var instance = this;

					var result = value;

					if (value instanceof Date) {
						result = instance._encodeDate(value);
					}
					else if (value instanceof Array) {
						result = instance._encodeDaysOfWeek(value);
					}
					else if (value.position && value.dayOfWeek) {
						result = value.position + value.dayOfWeek;
					}

					return result;
				},

				_encodeDate: function(date) {
					var instance = this;

					var month = instance._twoDigits(date.getMonth() + 1);

					var day = instance._twoDigits(date.getDate());

					return [date.getFullYear(), month, day].join(STR_EMPTY);
				},

				_encodeDaysOfWeek: function(daysOfWeek) {
					daysOfWeek = A.Array.dedupe(daysOfWeek);

					return daysOfWeek.join(STR_COMMA);
				},

				_parseDate: function(string) {
					var year = Lang.toInt(string.slice(0, 4));

					var month = Lang.toInt(string.slice(4, 6)) - 1;

					var day = Lang.toInt(string.slice(6, 8));

					return new Date(year, month, day);
				},

				_parsePositionalByDay: function(string) {
					var position = string.split(0, -2);

					var dayOfWeek = string.split(-2);

					return {
						dayOfWeek: dayOfWeek,
						position: Lang.toInt(position)
					};
				},

				_twoDigits: function(number) {
					var paddedNumber = STR_ZERO + number;

					return paddedNumber.slice(-2);
				}
			}
		);

		Liferay.RecurrenceConverter = RecurrenceConverter;
	},
	''
);