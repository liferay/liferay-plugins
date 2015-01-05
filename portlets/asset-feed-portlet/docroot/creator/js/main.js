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

AUI.add(
	'liferay-asset-feed-autocomplete',
	function(A) {
		var STR_PHRASE_MATCH = 'phraseMatch';

		var TPL_MENTION = '<span contenteditable="false"><a href="{url}">@{name}</a></span>';

		var TRIG_REG_EXP = /(?:\s[@]|^[@])([^\s]+)/;

		var AssetFeedAutoComplete = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				NAME: 'assetfeedautocomplete',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._inputNode = config.inputNode;

						instance._plugAC();
					},

					_createDataSource: function() {
						var instance = this;

						var actionURL = Liferay.PortletURL.createActionURL();

						var dataSource = new A.DataSource.IO(
							{
								on: {
									data: function(event) {
									},
									request: function(event) {
									}
								},
								source: actionURL.toString()
							}
						);

						return dataSource;
					},

					_handleACSelect: function(node) {
						var instance = this;

						node.ac.on(
							'select',
							function(response) {
								var data = response.result.raw;

								var queryData = instance._queryData;

								var mentionNode = A.Lang.sub(
									TPL_MENTION,
									{
										name: data,
										url: data.url
									}
								);

								var query = queryData.query.toString();

								var regExp = new RegExp('(\\s|^)' + query);

								var textContent = queryData.textContent.replace(regExp, '');

								queryData.textNode.set('textContent', textContent);

								queryData.selection.insertContent(mentionNode);

								node.ac.sendRequest(' ');
							}
						);
					},

					_plugAC: function() {
						var instance = this;

						A.Array.each(
							instance._inputNode,
							function(item) {
								item.plug(
									A.Plugin.AutoComplete,
									{
										minQueryLength: 1,
										resultFilters: STR_PHRASE_MATCH,
										source: instance._createDataSource
									}
								);

								instance._sendACRequest(item);
								instance._handleACSelect(item);
							}
						);
					},

					_sendACRequest: function(node) {
						var instance = this;

						node.on(
							'input',
							function(event) {
								var selection = new A.EditorSelection();

								var caretIndex = selection.anchorOffset;
								var textNode = selection.anchorTextNode;

								var textContent = textNode.get('textContent');

								var triggerIndex = textContent.lastIndexOf('@');

								var query = textContent.substring(triggerIndex, caretIndex);

								var triggerString = TRIG_REG_EXP.exec(query);

								var charBeforeTrigger = textContent.charAt(triggerIndex - 1);

								if (triggerString && (triggerString.input === query) && ((triggerIndex === 0) || (charBeforeTrigger === ' '))) {
									node.ac.sendRequest(query.substring(1));
								}

								instance._queryData = {
									query: query,
									selection: selection,
									textContent: textContent,
									textNode: textNode
								};
							}
						);
					}
				}
			}
		);

		Liferay.AssetFeedAutoComplete = AssetFeedAutoComplete;
	},
	'',
	{
		requires: ['autocomplete', 'autocomplete-filters', 'datasource-io', 'editor', 'liferay-portlet-base']
	}
);

AUI.add(
	'liferay-asset-feed-input-list',
	function(A) {
		var CSS_FEED_LIST_ITEM = 'asset-feed-list-item';

		var CSS_REMOVE = 'remove';

		var EVENT_AT_MENTION_REMOVE = 'assetFeedAtMentionRemove';

		var EVENT_AT_MENTION_SELECT = 'assetFeedAtMentionSelect';

		var EVENT_CLICK = 'click';

		var EVENT_SELECT = 'select';

		var STR_BLANK = '';

		var STR_CONTAINER = 'container';

		var STR_CONTENT_BOX = 'contentBox';

		var STR_INPUT_NODE = 'inputNode';

		var STR_ITEMS = 'items';

		var STR_KEY = 'key';

		var STR_NAME = 'name';

		var STR_PHRASE_MATCH = 'phraseMatch';

		var TPL_FEED_LIST_ITEM = '<span class="' + CSS_FEED_LIST_ITEM + '" data-key="{key}" data-name="{name}">{content}{action}</span>';

		var TPL_REMOVE = '<span class="' + CSS_REMOVE + ' icon-remove"></span>';

		var AssetFeedInputList = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: function(val) {
							var instance = this;

							return instance.byId(val);
						}
					},

					contentBox: {
						setter: function(val) {
							var instance = this;

							return instance.byId(val);
						}
					},

					inputNode: {
						setter: function(val) {
							var instance = this;

							return instance.byId(val);
						}
					},

					items: {
						value: []
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				NAME: 'assetfeedinputlist',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._createAutocomplete();
					},

					bindUI: function() {
						var instance = this;

						instance._handleFocus();
						instance._handleSelect();
						instance._handleRemove();
						instance._handleMention();
					},

					clearValue: function() {
						var instance = this;

						instance.get(STR_CONTAINER).html(STR_BLANK);

						instance.get(STR_INPUT_NODE).val(STR_BLANK);

						instance.set(
							STR_ITEMS,
							[]
						);
					},

					getValue: function() {
						var instance = this;

						return instance.get(STR_ITEMS);
					},

					_addItem: function(item, remove) {
						var instance = this;

						var inputNode = instance.get(STR_INPUT_NODE);
						var items = instance.get(STR_ITEMS);

						if (!instance._itemExists(item)) {
							var newItem = {
								className: item.className,
								classPK: item.classPK,
								name: item.name
							};

							items.push(newItem);

							instance.set(STR_ITEMS, items);

							instance._renderItem(newItem, remove);
						}
						else {
							instance._flashItem(item.classPK);
						}

						inputNode.val(STR_BLANK);
					},

					_createAutocomplete: function() {
						var instance = this;

						var inputNode = instance.get(STR_INPUT_NODE);

						inputNode.plug(
							A.Plugin.AutoComplete,
							{
								matchKey: STR_NAME,
								minQueryLength: 1,
								resultFilters: STR_PHRASE_MATCH,
								resultTextLocator: STR_NAME,
								source: [{name: 'Joe Blogs', classPK: 'joe.blogs@liferay.com', className: 'person'}, {name: 'Bruno Admin', classPK: 'bruno.admin@liferay.com', className: 'person'}]
							}
						);
					},

					_flashItem: function(key) {
						var instance = this;

						var container = instance.get(STR_CONTAINER);

						var existingNode = container.one('[data-key="' + key + '"');

						if (existingNode) {
							new A.Anim(
								{
									from: {
										backgroundColor: '#FFF'
									},
									node: existingNode,
									to: {
										backgroundColor: existingNode.getStyle('backgroundColor')
									}
								}
							).run();
						}
					},

					_handleFocus: function() {
						var instance = this;

						var inputNode = instance.get(STR_INPUT_NODE);
						var contentBox = instance.get(STR_CONTENT_BOX);

						contentBox.on(
							EVENT_CLICK,
							function(event) {
								inputNode.focus();
							}
						);
					},

					_handleMention: function() {
						var instance = this;

						Liferay.on(
							EVENT_AT_MENTION_SELECT,
							function(event) {
								instance._addItem(event.result.raw);
							}
						);

						Liferay.on(
							EVENT_AT_MENTION_REMOVE,
							function(event) {
								instance._removeItem(event.result.raw);
							}
						);
					},

					_handleRemove: function() {
						var instance = this;

						var container = instance.get(STR_CONTAINER);

						container.delegate(
							EVENT_CLICK,
							function(event) {
								var item = event.currentTarget.ancestor('.' + CSS_FEED_LIST_ITEM);

								if (item) {
									var key = item.getData(STR_KEY);

									instance._removeItem(key);

									item.remove();
								}
							},
							'.' + CSS_REMOVE
						);
					},

					_handleSelect: function() {
						var instance = this;

						var inputNode = instance.get(STR_INPUT_NODE);

						inputNode.ac.on(
							EVENT_SELECT,
							function(event) {
								event.preventDefault();

								instance._addItem(event.result.raw, true);

								inputNode.val(STR_BLANK);
							}
						);
					},

					_itemExists: function(newItem) {
						var instance = this;

						return A.Array.find(
							instance.get(STR_ITEMS),
							function(item) {
								return (newItem.classPK === item.classPK);
							}
						);
					},

					_removeItem: function(key) {
						var instance = this;

						var newItems = A.Array.reject(
							instance.get(STR_ITEMS),
							function(item) {
								return (item.classPK === key);
							}
						);

						instance.set(STR_ITEMS, newItems);
					},

					_renderItem: function(item, remove) {
						var instance = this;

						var container = instance.get(STR_CONTAINER);

						var action = STR_BLANK;

						if (remove) {
							action = TPL_REMOVE;
						}

						var newNode = A.Node.create(
							A.Lang.sub(
								TPL_FEED_LIST_ITEM,
								{
									action: action,
									content: item.name,
									key: item.classPK,
									name: item.className
								}
							)
						);

						instance.get(STR_CONTAINER).append(newNode);
					}
				}
			}
		);

		Liferay.AssetFeedInputList = AssetFeedInputList;
	},
	'',
	{
		requires: ['anim', 'aui-base', 'autocomplete', 'liferay-portlet-base']
	}
);