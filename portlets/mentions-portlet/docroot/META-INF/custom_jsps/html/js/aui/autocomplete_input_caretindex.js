AUI.add(
	'liferay-autocomplete-input-caretindex',
	function(A) {
		var STR_INPUT_NODE = 'inputNode';

		var AutcompleteInputCaretIndex = function(){};

		AutcompleteInputCaretIndex.prototype = {
			_getCaretIndex: function(node) {
				var instance = this;

				node = node || instance.get(STR_INPUT_NODE);

				var input = node.getDOM();

				return {
					end: input.selectionStart,
					start: input.selectionStart
				};
			},

			_setCaretIndex: function(node, caretIndex) {
				var instance = this;

				node = node || instance.get(STR_INPUT_NODE);

				var input = node.getDOM();

				Liferay.Util.focusFormField(input);

				input.setSelectionRange(caretIndex, caretIndex);
			}
		};

		A.Base.mix(Liferay.AutoCompleteTextarea, [AutcompleteInputCaretIndex]);
	},
	'',
	{
		requires: ['liferay-autocomplete-textarea']
	}
);