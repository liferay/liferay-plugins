AUI.add(
	'liferay-autocomplete-input',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var AC_ATTRS_WHITELIST = [''];

		var REGEX_TRIGGER = /trigger/g;

		var STR_PHRASE_MATCH = 'phraseMatch';

		var STR_TRIGGER = 'trigger';

		var STR_VISIBLE = 'visible';

		var TRIGGER_CONFIG_DEFAULTS = {
			activateFirstItem: true,
			resultFilters: STR_PHRASE_MATCH,
			resultHighlighter: STR_PHRASE_MATCH
		};

		var AutoCompleteInputBase = function() {};

		AutoCompleteInputBase.ATTRS = {
			caretAtTerm: {
				validator: Lang.isBoolean,
				value: true
			},

			inputNode: {
				setter: A.one,
				writeOnce: true
			},

			offset: {
				validator: '_validateOffset',
				value: 10
			},

			regExp: {
				validator: Lang.isRegExp || Lang.isString,
				value: '(?:\\strigger|^trigger)([^\\s]+)'
			},

			source: {
			},

			tplReplace: {
				validator: Lang.isString
			},

			tplResults: {
				validator: Lang.isString
			},

			trigger: {
				setter: AArray,
				value: '@'
			}
		};

		AutoCompleteInputBase.prototype = {
			initializer: function() {
				var instance = this;

				instance.get('boundingBox').addClass('lfr-autocomplete-input-list');

				instance.set('resultFormatter', A.bind('_acResultFormatter', instance));

				instance._bindUIACIBase();

				var autocompleteAttrs = AArray.filter(
					A.Object.keys(A.AutoComplete.ATTRS),
					function(item) {
						return item !== 'value';
					}
				);

				instance._triggerConfigDefaults = A.merge(TRIGGER_CONFIG_DEFAULTS);

				A.mix(instance._triggerConfigDefaults, instance.getAttrs(), false, autocompleteAttrs);
			},

			destructor: function() {
				var instance = this;

				(new A.EventHandle(instance._eventHandles)).detach();
			},

			_acResultFormatter: function(query, results) {
				var instance = this;

				var tplResults = instance.get('tplResults');

				return AArray.map(
					results,
					function(result) {
						return Lang.sub(tplResults, result.raw);
					}
				);
			},

			_adjustACPosition: function() {
				var instance = this;

				var xy = instance._getACPositionBase();

				var caretXY = instance._getCaretOffset();

				var offset = instance.get('offset');

				var offsetX = 0;
				var offsetY = 0;

				if (Lang.isArray(offset)) {
					offsetX = offset[0];
					offsetY = offset[1];
				}
				else if (Lang.isNumber(offset)) {
					offsetY = offset;
				}

				var acOffset = instance._getACPositionOffset();

				xy[0] += caretXY.x + offsetX + acOffset[0];
				xy[1] += caretXY.y + offsetY + acOffset[1];

				instance.get('boundingBox').setXY(xy);
			},

			_afterACVisibleChange: function(event) {
				var instance = this;

				if (event.newVal) {
					instance._adjustACPosition();
				}

				instance._uiSetVisible(event.newVal);
			},

			_bindUIACIBase: function() {
				var instance = this;

				instance.on('query', instance._onACQuery, instance);

				instance.after('visibleChange', instance._afterACVisibleChange, instance);
			},

			_defSelectFn: function(event) {
				var instance = this;

				var text = event.result.text;

				var tplReplace = instance.get('tplReplace');

				if (tplReplace) {
					text = Lang.sub(tplReplace, event.result.raw);
				}

				instance._inputNode.focus();

				instance._updateValue(text);

				instance._ariaSay(
					'item_selected',
					{
						item: event.result.text
					}
				);

				instance.hide();
			},

			_getRegExp: function() {
				var instance = this;

				var regExp = instance.get('regExp');

				if (Lang.isString(regExp)) {
					var triggersExpr = '[' + instance._getTriggers().join('|') + ']';

					regExp = new RegExp(regExp.replace(REGEX_TRIGGER, triggersExpr));
				}

				return regExp;
			},

			_getTriggers: function() {
				var instance = this;

				if (!instance._triggers) {
					var triggers = [];

					AArray.each(
						instance.get(STR_TRIGGER),
						function(item, index, collection) {
							triggers.push(Lang.isString(item) ? item : item.term);
						}
					);

					instance._triggers = triggers;
				}

				return instance._triggers;
			},

			_keyDown: function() {
				var instance = this;

				if (instance.get(STR_VISIBLE)) {
					instance._activateNextItem();
				}
			},

			_onACQuery: function(event) {
				var instance = this;

				var input = instance._getQuery(event.query);

				if (input) {
					instance._setTriggerConfig(input[0]);

					event.query = input.substring(1);
				}
				else {
					event.preventDefault();

					if (instance.get(STR_VISIBLE)) {
						instance.hide();
					}
				}
			},

			_processKeyUp: function(query) {
				var instance = this;

				if (query) {
					instance._setTriggerConfig(query[0]);

					query = query.substring(1);

					instance.sendRequest(query);
				}
				else if (instance.get(STR_VISIBLE)) {
					instance.hide();
				}
			},

			_setTriggerConfig: function(trigger) {
				var instance = this;

				if (trigger !== instance._trigger) {
					var triggers = instance._getTriggers();

					var triggerConfig = instance.get(STR_TRIGGER)[AArray.indexOf(triggers, trigger)];

					instance.setAttrs(A.merge(instance._triggerConfigDefaults, triggerConfig));

					instance._trigger = trigger;
				}
			},

			_syncUIPosAlign: Lang.emptyFn,

			_validateOffset: function(value) {
				return (Lang.isArray(value) || Lang.isNumber(value));
			}
		};

		Liferay.AutoCompleteInputBase = AutoCompleteInputBase;
	},
	'',
	{
		requires: ['aui-base', 'autocomplete', 'autocomplete-filters', 'autocomplete-highlighters']
	}
);