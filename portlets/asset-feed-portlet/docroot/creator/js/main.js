AUI.add(
	'liferay-asset-feed-input',
	function(A) {
		var EVENT_INPUT = 'input';

		var EVENT_KEYPRESS = 'keypress';

		var REGEX_LINEBREAK = /(<br>)|(<div.*?>)|(<\/p>)/g;

		var REGEX_MISC_TAGS = /(<\/div>)|(<span.*?>)|(<\/span>)|(<\/br>)|(<br><\/div>)|(<p>)/g;

		var SELECTOR_CONTENT_EDITABLE = '.content-editable';

		var SELECTOR_PLACEHOLDER = '.placeholder';

		var STR_BLANK = '';

		var STR_CONTAINER = 'container';

		var STR_DATA_ID = 'data-id';

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

					form: {
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
					},

					clearValue: function() {
						var instance = this;

						instance.get(STR_INPUT_NODE).html(STR_BLANK);

						var placeholder = instance.get(STR_CONTAINER).one(SELECTOR_PLACEHOLDER);

						if (placeholder) {
							placeholder.show();
						}
					},

					handleFormSubmission: function() {
						var instance = this;

						var contentInput = instance.get(STR_CONTAINER);

						return {
							atMentions: instance._handleAtMentions(contentInput),
							hashtags: instance._handleHashtags(contentInput),
							linkData: instance.byId('linkData').val(),
							message: instance._handleTextContent(contentInput),
							type: instance.byId('type').val()
						};
					},

					_handleAtMentions: function(contentInput) {
						var atMentionNodes = contentInput.all('.at-mention');

						var atMentions = [];

						atMentionNodes.each(
							function(item, index) {
								atMentions.push(
									{
										className: 'userName',
										classPk: item.attr(STR_DATA_ID)
									}
								);
							}
						);

						return atMentions;
					},

					_handleHashtags: function(contentInput) {
						var tagNodes = contentInput.all('.hashtag');

						var tags = [];

						tagNodes.each(
							function(item, index) {
								var id = item.attr(STR_DATA_ID);

								tags.push(id);
							}
						);

						return tags;
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
					},

					_handleTextContent: function(contentInput) {
						var instance = this;

						var inputNodes = contentInput.all('input');

						var textContent = instance.get(STR_INPUT_NODE).html();

						inputNodes.each(
							function(item, index) {
								var text = item.val();

								var regExp = /<input.*?>/;

								textContent = textContent.replace(regExp, text);
							}
						);

						textContent = textContent.replace(REGEX_MISC_TAGS, STR_BLANK);
						textContent = textContent.replace(REGEX_LINEBREAK, '\n');

						instance.get(STR_INPUT_NODE).html(textContent);

						return textContent;
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
		var REGEX_TRIGGER = /(?:\s[@,#]|^[@,#])([^\s]+)/;

		var STR_AT = '@';

		var STR_BLANK = '';

		var STR_HASH = '#';

		var STR_PEOPLE = 'people';

		var STR_PHRASE_MATCH = 'phraseMatch';

		var STR_SOURCE = 'source';

		var STR_SPACE = ' ';

		var STR_TEXT_CONTENT = 'textContent';

		var STR_TOPICS = 'topics';

		var TPL_MENTION = '<input class="asset-feed-content-mention {className}" data-id="{id}" href="{url}" type="button" value="{trigger}{name}" />';

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

					_createACSelectNode: function(data) {
						var instance = this;

						var triggerName = 'at-mention';

						if (instance._trigger === STR_HASH) {
							triggerName = 'hashtag';

							data.id = data.name;
						}

						return A.Lang.sub(
							TPL_MENTION,
							{
								className: triggerName,
								id: data.id,
								name: data.name,
								trigger: instance._trigger,
								url: data.url
							}
						);
					},

					_createDataSource: function(type) {
						var instance = this;

						var resultFormatter;
						var servicePath;

						var requestParams = {
							end: 20,
							start: 0
						};

						if (type === STR_TOPICS) {
							requestParams.groupId = themeDisplay.getCompanyGroupId();
							requestParams.name = STR_BLANK;

							resultFormatter = function(result) {
								return result.tags;
							};

							servicePath = '/assettag/get-json-group-tags';
						}
						else if (type === STR_PEOPLE) {
							requestParams.companyId = themeDisplay.getCompanyId();

							resultFormatter = function(result) {
								return A.Array.map(
									result,
									function(item) {
										var name = item.firstName + STR_SPACE + item.lastName;

										return {
											className: item.className,
											classPK: item.userId.toString(),
											name: A.Lang.trim(name)
										};
									}
								);
							};

							servicePath = '/user/get-company-users';
						}

						return function(query, callback) {
							requestParams.name = query;

							Liferay.Service(
								servicePath,
								requestParams,
								function(result) {
									callback(resultFormatter(result));
								}
							);
						};
					},

					_handleACSelect: function() {
						var instance = this;

						var node = instance._inputNode;

						node.ac.on(
							'select',
							function(response) {
								var newValue = STR_BLANK;

								var data = response.result.raw;

								var queryData = instance._queryData;

								var query = queryData.query.toString();

								var queryLength = query.length;

								var selection = queryData.selection;

								if (queryData.charBeforeTrigger === STR_SPACE) {
									newValue = STR_SPACE;

									selection.anchorOffset = (selection.anchorOffset - queryLength);
									selection.focusOffset = (selection.focusOffset - queryLength);
								}

								var regExp = new RegExp('(\\s|^)' + query);

								var textContent = queryData.textContent.replace(regExp, newValue);

								queryData.textNode.set(STR_TEXT_CONTENT, textContent);

								var mentionNode = instance._createACSelectNode(data);

								selection.insertContent(mentionNode);

								node.ac.sendRequest(STR_SPACE);

								if (instance._trigger === STR_AT) {
									Liferay.fire('assetFeedAtMentionSelect', data);
								}
							}
						);
					},

					_handleACSource: function(caretIndex, textContent) {
						var instance = this;

						var node = instance._inputNode;

						var people = instance._createDataSource(STR_PEOPLE);
						var topics = instance._createDataSource(STR_TOPICS);

						var checkInput = textContent.charAt(caretIndex - 1);

						var source;

						if (checkInput === STR_AT) {
							instance._trigger = STR_AT;

							source = people;
						}
						else if (checkInput === STR_HASH) {
							instance._trigger = STR_HASH;

							source = topics;
						}

						if (checkInput === STR_AT || checkInput === STR_HASH) {
							node.ac.set(STR_SOURCE, source);
						}
					},

					_plugAC: function() {
						var instance = this;

						instance._inputNode.plug(
							A.Plugin.AutoComplete,
							{
								activateFirstItem: true,
								minQueryLength: 1,
								resultFilters: STR_PHRASE_MATCH,
								resultTextLocator: 'name',
								source: instance._createDataSource
							}
						);

						instance._sendACRequest();
						instance._handleACSelect();
					},

					_sendACRequest: function() {
						var instance = this;

						var node = instance._inputNode;

						node.on(
							'keyup',
							A.debounce(
								function(event) {
									var selection = new A.EditorSelection();

									var caretIndex = selection.anchorOffset;
									var textNode = selection.anchorTextNode;

									var textContent = textNode.get(STR_TEXT_CONTENT);

									var acList = instance.one('.yui3-aclist-list');

									var acListVisible = false;

									instance._handleACSource(caretIndex, textContent);

									if (instance._trigger) {
										var contentSubstring = textContent.substring(0, caretIndex);

										var triggerIndex = contentSubstring.lastIndexOf(instance._trigger);

										var query = textContent.substring(triggerIndex, caretIndex);

										var triggerString = REGEX_TRIGGER.exec(query);

										var charBeforeTrigger = textContent.charAt(triggerIndex - 1);

										if (triggerString && (triggerString.input === query) && ((triggerIndex === 0) || (charBeforeTrigger === STR_SPACE))) {
											acListVisible = true;

											if (!(event.isKey('up') || event.isKey('down'))) {
												node.ac.sendRequest(query.substring(1));
											}
										}
										else if (acList.hasChildNodes()) {
											acListVisible = false;
										}

										acList.toggle(acListVisible);

										instance._queryData = {
											charBeforeTrigger: charBeforeTrigger,
											query: query,
											selection: selection,
											textContent: textContent,
											textNode: textNode
										};
									}
								},
								100
							)
						);
					}
				}
			}
		);

		Liferay.AssetFeedAutoComplete = AssetFeedAutoComplete;
	},
	'',
	{
		requires: ['aui-debounce', 'autocomplete', 'autocomplete-filters', 'datasource-io', 'editor', 'liferay-portlet-base']
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

		var STR_DOT = '.';

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
								activateFirstItem: true,
								matchKey: STR_NAME,
								minQueryLength: 1,
								resultFilters: STR_PHRASE_MATCH,
								resultTextLocator: STR_NAME,
								source: instance._createDataSource()
							}
						);
					},

					_createDataSource: function() {
						return function(query, callback) {
							Liferay.Service(
								'/user/get-company-users',
								{
									companyId: themeDisplay.getCompanyId(),
									end: 20,
									name: query,
									start: 0
								},
								function(result) {
									callback(
										A.Array.map(
											result,
											function(item) {
												var name = item.firstName + ' ' + item.lastName;

												return {
													className: item.className,
													classPK: item.userId.toString(),
													name: A.Lang.trim(name)
												};
											}
										)
									);
								}
							);
						};
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
								instance._addItem(event.details[0]);
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
								var item = event.currentTarget.ancestor(STR_DOT + CSS_FEED_LIST_ITEM);

								if (item) {
									var key = item.getData(STR_KEY);

									instance._removeItem(key);

									item.remove();
								}
							},
							STR_DOT + CSS_REMOVE
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