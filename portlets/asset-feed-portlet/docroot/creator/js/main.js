AUI.add(
	'liferay-asset-feed-input',
	function(A) {
		var EVENT_INPUT = 'input';

		var EVENT_KEYPRESS = 'keypress';

		var SELECTOR_CONTENT_EDITABLE = '.content-editable';

		var SELECTOR_PLACEHOLDER = '.placeholder';

		var STR_BLANK = '';

		var STR_CONTAINER = 'container';

		var STR_HIDDEN_INPUT = 'hiddenInput';

		var STR_INLINE = 'inline';

		var STR_INPUT_NODE = 'inputNode';

		var AssetFeedInput = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: function(val) {
							var instance = this;

							return instance.byId(val);
						}
					},

					hiddenInput: {
						setter: function(val) {
							var instance = this;

							return instance.byId(val);
						}
					},

					inline: {
						value: false
					},

					inputNode: {
						valueFn: function() {
							var instance = this;

							return instance.get(STR_CONTAINER).one(SELECTOR_CONTENT_EDITABLE);
						}
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				NAME: 'assetfeedinput',

				prototype: {
					renderUI: function() {
						var instance = this;

						instance.get(STR_INPUT_NODE).attr('contentEditable', true);
					},

					bindUI: function() {
						var instance = this;

						instance._handleInlineInput();
						instance._handlePlaceHolder();
						instance._handleHiddenInput();
					},

					clearValue: function() {
						var instance = this;

						instance.get(STR_INPUT_NODE).html(STR_BLANK);
						instance.get(STR_HIDDEN_INPUT).val(STR_BLANK);

						var placeholder = instance.get(STR_CONTAINER).one(SELECTOR_PLACEHOLDER);

						if (placeholder) {
							placeholder.show();
						}
					},

					getValue: function() {
						var instance = this;

						var miscTags = /(<\/div>)|(<br>)/g;
						var newLines = /<div.*?>/g;

						var value = instance.get(STR_INPUT_NODE).html().replace(newLines, '\n');

						value = value.replace(miscTags, STR_BLANK);

						return value;
					},

					_handleHiddenInput: function() {
						var instance = this;

						var hiddenInput = instance.get(STR_HIDDEN_INPUT);
						var inputNode = instance.get(STR_INPUT_NODE);

						if (hiddenInput) {
							inputNode.on(
								EVENT_INPUT,
								function(event) {
									var value = instance.getValue();

									hiddenInput.val(value);
								}
							);
						}
					},

					_handleInlineInput: function() {
						var instance = this;

						if (instance.get(STR_INLINE)) {
							instance.get(STR_INPUT_NODE).on(
								EVENT_KEYPRESS,
								function(event) {
									if (event.isKey('ENTER')) {
										event.preventDefault();
									}
								}
							);
						}
					},

					_handlePlaceHolder: function() {
						var instance = this;

						var inputNode = instance.get(STR_INPUT_NODE);

						var placeholder = instance.get(STR_CONTAINER).one(SELECTOR_PLACEHOLDER);

						if (placeholder) {
							inputNode.on(
								EVENT_INPUT,
								function(event) {
									placeholder.toggle(inputNode.text() === STR_BLANK);
								}
							);
						}
					}
				}
			}
		);

		Liferay.AssetFeedInput = AssetFeedInput;
	},
	'',
	{
		requires: ['liferay-portlet-base']
	}
);