AUI.add(
	'liferay-autocomplete-input-caretoffset-sel',
	function(A) {
		var Lang = A.Lang;

		var DOC = A.config.doc;

		var AutcompleteInputCaretOffset = function(){};

		AutcompleteInputCaretOffset.prototype = {
			_getCaretOffset: function(node) {
				var instance = this;

				node = node || instance.get('inputNode');

				node.focus();

				var range = DOC.selection.createRange();

				var xy = node.getXY();

				return {
					x: range.boundingLeft - xy[0],
					y: Lang.toInt(range.boundingTop) - xy[1] + node.get('scrollTop') + DOC.documentElement.scrollTop
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