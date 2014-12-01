var A = AUI();

var INPUT_EL = document.createElement('input');

var SUPPORTS_INPUT_SELECTION = ((typeof INPUT_EL.selectionStart === 'number') && (typeof INPUT_EL.selectionEnd === 'number'));

A.applyConfig(
	{
		modules: {
			'liferay-autocomplete-input': {
				path: 'autocomplete_input.js',
				requires: [
					'aui-base',
					'autocomplete',
					'autocomplete-filters',
					'autocomplete-highlighters'
				]
			},
			'liferay-autocomplete-input-caretindex': {
				condition: {
					name: 'liferay-autocomplete-input-caretindex',
					test: function() {
						return SUPPORTS_INPUT_SELECTION;
					},
					trigger: 'liferay-autocomplete-textarea'
				},
				path: 'autocomplete_input_caretindex.js',
				requires: [
					'liferay-autocomplete-textarea'
				]
			},
			'liferay-autocomplete-input-caretindex-sel': {
				condition: {
					name: 'liferay-autocomplete-input-caretindex-sel',
					test: function() {
						return !SUPPORTS_INPUT_SELECTION;
					},
					trigger: 'liferay-autocomplete-textarea'
				},
				path: 'autocomplete_input_caretindex_sel.js',
				requires: [
					'liferay-autocomplete-textarea'
				]
			},
			'liferay-autocomplete-input-caretoffset': {
				condition: {
					name: 'liferay-autocomplete-input-caretoffset',
					test: function(A) {
						return !(A.UA.ie && A.UA.ie < 9);
					},
					trigger: 'liferay-autocomplete-textarea'
				},
				path: 'autocomplete_input_caretoffset.js',
				requires: [
					'liferay-autocomplete-textarea'
				]
			},
			'liferay-autocomplete-input-caretoffset-sel': {
				condition: {
					name: 'liferay-autocomplete-input-caretoffset-sel',
					test: function(A) {
						return (A.UA.ie && A.UA.ie < 9);
					},
					trigger: 'liferay-autocomplete-textarea'
				},
				path: 'autocomplete_input_caretoffset_sel.js',
				requires: [
					'liferay-autocomplete-textarea'
				]
			},
			'liferay-autocomplete-textarea': {
				path: 'autocomplete_textarea.js',
				requires: [
					'liferay-autocomplete-input'
				]
			}
		}
	}
);