AUI.add(
	'liferay-autocomplete-input-caretoffset',
	function(A) {
		var AArray = A.Array;
		var ANode = A.Node;

		var MIRROR_STYLES = [
			'boxSizing',
			'fontFamily',
			'fontSize',
			'fontStyle',
			'fontVariant',
			'fontWeight',
			'height',
			'letterSpacing',
			'lineHeight',
			'maxHeight',
			'minHeight',
			'padding',
			'textDecoration',
			'textIndent',
			'textTransform',
			'width',
			'wordSpacing'
		];

		var STR_INPUT_NODE = 'inputNode';

		var AutcompleteInputCaretOffset = function(){};

		AutcompleteInputCaretOffset.prototype = {
			TPL_CARET: '<span class="input-caret">&nbsp</span>',

			TPL_INPUT_MIRROR: '<div class="liferay-autocomplete-input-mirror"></div>',

			_applyMirrorContent: function() {
				var instance = this;

				var input = instance.get(STR_INPUT_NODE);

				var value = input.val();

				var caretIndex = instance._getCaretIndex().start;

				if (caretIndex === value.length) {
					value = value + instance.TPL_CARET;
				}
				else {
					if (instance.get('caretAtTerm')) {
						caretIndex = instance._getPrevTrigger(value, caretIndex).index + 1;
					}

					value = value.substring(0, caretIndex) + instance.TPL_CARET + value.substring(caretIndex + 1);
				}

				instance._inputMirror.html(value);

				return value;
			},

			_applyMirrorStyles: function() {
				var instance = this;

				var inputNode = instance.get(STR_INPUT_NODE);

				var inputMirror = instance._inputMirror;

				AArray.each(
					MIRROR_STYLES,
					function(item, index) {
						inputMirror.setStyle(item, inputNode.getStyle(item));
					}
				);
			},

			_createInputMirror: function() {
				var instance = this;

				if (!instance._inputMirror) {
					var inputMirror = ANode.create(instance.TPL_INPUT_MIRROR);

					A.getBody().append(inputMirror);

					instance._inputMirror = inputMirror;
				}
			},

			_getCaretOffset: function(node) {
				var instance = this;

				instance._createInputMirror();

				instance._applyMirrorStyles();
				instance._applyMirrorContent();

				node = node || instance.get(STR_INPUT_NODE);

				var inputEl = node.getDOM();

				var scrollLeft = inputEl.scrollLeft;
				var scrollTop = inputEl.scrollTop;

				var inputCaretEl = instance._inputMirror.one('.input-caret').getDOM();

				return {
					x: inputCaretEl.offsetLeft + scrollLeft,
					y: inputCaretEl.offsetTop - scrollTop
				};
			}
		};

		A.Base.mix(Liferay.AutoCompleteTextarea, [AutcompleteInputCaretOffset]);
	},
	'',
	{
		requires: ['liferay-autocomplete-textarea']
	}
);