AUI().add(
	'gadget-editor-tabs',
	function(A) {
		var Lang = A.Lang;

		var ACTIVE_TAB = 'activeTab';

		var AUI_TAB_LABEL = 'aui-tab-label';

		var BOUNDING_BOX = 'boundingBox';

		var CONTENT_BOX = 'contentBox';

		var CONTENT_NODE = 'contentNode';

		var DIRTY_INDICATOR_NODE = 'dirtyIndicatorNode';

		var EDITOR = 'editor';

		var ENTRY_ID = 'entryId';

		var HEIGHT = 'height';

		var ID = 'id';

		var IS_DIRTY = 'isDirty';

		var IS_RENDERED = 'isRendered';

		var ITEMS = 'items';

		var LABEL = 'label';

		var TPL_CLOSE_BUTTON = '<a class="gadget-editor-tab-close" href="javascript:;"></a>';

		var TPL_DIV = '<div></div>';

		var TPL_DIRTY_INDICATOR = '<span>*</span>';

		var TabViewEditor = A.Component.create(
			{
				EXTENDS: A.TabView,

				NAME: 'tab-view-editor',

				prototype: {
					addExistingDocument: function(entryId, label, editorContent) {
						var instance = this;

						var contentNode = A.Node.create(TPL_DIV);

						var tab = new TabEditor(
							{
								active: true,
								contentNode: contentNode,
								editorContent: editorContent,
								entryId: entryId,
								isDirty: false,
								isNew: false,
								label: label
							}
						).render();

						instance.addTab(tab);

						instance.adjustEditorHeight();

						return tab;
					},

					addNewTab: function() {
						var instance = this;

						var newTabCounter = instance._newTabCounter || 0;

						newTabCounter++;

						var entryId = 'newTab' + newTabCounter;

						var contentNode = A.Node.create(TPL_DIV);

						var tab = new TabEditor(
							{
								active: true,
								contentNode: contentNode,
								editorContent: '',
								entryId: entryId,
								isDirty: false,
								isNew: true,
								label: 'new ' + newTabCounter
							}
						).render();

						instance.addTab(tab);

						instance._newTabCounter = newTabCounter;

						instance.adjustEditorHeight();

						return tab;
					},

					addTab: function(tab) {
						var instance = this;

						tab.addTarget(instance);

						tab.fire('add');

						TabViewEditor.superclass.addTab.apply(instance, arguments);
					},

					adjustEditorHeight: function() {
						var instance = this;

						var tab = instance.get(ACTIVE_TAB);

						var codeMirrorElement = tab.get(CONTENT_NODE).one('.CodeMirror');

						if (codeMirrorElement) {
							var contentTabsHeight = instance.get(BOUNDING_BOX).get('clientHeight');

							var listNodeHeight = instance.get('listNode').get('offsetHeight');

							var height =  contentTabsHeight - listNodeHeight;

							codeMirrorElement.setStyle(HEIGHT, height);

							tab.get(EDITOR).refresh();
						}
					},

					closeTabById: function(id) {
						var instance = this;

						var tab = instance.getTabById(id);

						if (tab) {
							var items = instance.get(ITEMS);

							if (items.length == 1) {
								instance.addNewTab();
							}

							instance.removeTabById(id);

							instance.adjustEditorHeight();
						}
					},

					getTabById: function(id) {
						var instance = this;

						var tab = null;

						A.Array.some(
							instance.get(ITEMS),
							function(item, index, collection) {
								if (item.get(ID) == id) {
									tab = item;

									return true;
								}
							}
						);

						return tab;
					},

					removeTabById: function(id) {
						var instance = this;

						var tab = instance.getTabById(id);

						instance.removeTab(tab);

						var tabContentNode = tab.get(CONTENT_NODE);
						var tabViewContentNode = instance.get(CONTENT_NODE);

						if (tabViewContentNode.contains(tabContentNode)) {
							tabViewContentNode.removeChild(tabContentNode);
						}
					},

					selectTabById: function(id) {
						var instance = this;

						var index = instance.getTabIndex(instance.getTabById(id));

						instance.selectTab(index);
					},

					_onActiveTabChange: function(event) {
						var instance = this;

						TabViewEditor.superclass._onActiveTabChange.apply(this, arguments);

						if (event.newVal) {
							instance.adjustEditorHeight();
						}
					}
				}
			}
		);

		A.TabViewEditor = TabViewEditor;

		var TabEditor = A.Component.create(
			{
				ATTRS: {
					dirtyIndicatorNode: {},
					editor: {},
					entryId: {
						setter: function(v) {
							return String(v);
						},
						value: ''
					},
					isDirty: {
						value: false
					},
					isNew: {
						value: true
					},
					isRendered: {
						value: false
					}
				},

				EXTENDS: A.Tab,

				NAME: 'tab-editor',

				prototype: {
					bindUI: function() {
						var instance = this;

						TabEditor.superclass.bindUI.apply(this, arguments);

						instance.after('idChange', instance._afterIdChange);

						instance.on('isDirtyChange', instance._onIsDirtyChange);
					},

					initializer: function(config) {
						var instance = this;

						instance._searchMarked = [];

						instance._editorModes = {
							css: ['css'],
							gadget: ['xml'],
							javascript: ['js'],
							xml: ['html', 'htm', 'shtml', 'shtm', 'xhtml', 'xsml', 'xsl', 'xsd', 'kml', 'wsdl']
						};

						instance._createEditor(instance.get(CONTENT_NODE), config.editorContent);
					},

					renderUI: function() {
						var instance = this;

						TabEditor.superclass.renderUI.apply(this, arguments);

						instance.get('labelNode').removeClass(AUI_TAB_LABEL);
						instance.get(CONTENT_BOX).addClass(AUI_TAB_LABEL);

						instance._renderCloseButton();
						instance._renderDirtyIndicator();
					},

					getEditorModeFromLabel: function() {
						var instance = this;

						var label = instance.get(LABEL);

						var mode;

						var extension = label.substr(label.lastIndexOf('.') + 1);

						if (A.Array.indexOf(instance._editorModes.gadget, extension) != -1) {
							mode = 'gadget';
						}
						else if (A.Array.indexOf(instance._editorModes.javascript, extension) != -1) {
							mode = 'javascript';
						}
						else if (A.Array.indexOf(instance._editorModes.css, extension) != -1) {
							mode = 'css';
						}
						else if (A.Array.indexOf(instance._editorModes.xml, extension) != -1) {
							mode = 'xml';
						}
						else {
							mode = 'text/plain';
						}

						return mode;
					},

					searchEditorText: function(searchText, caseInsensitive, replaceText, doReplace) {
						var instance = this;

						var searched = instance._searched;
						var searchLastPosition = instance._searchLastPosition;
						var searchLastQuery = instance._searchLastQuery;

						if (searchText) {
							var editor = instance.get(EDITOR);

							if (searchLastQuery != searchText) {
								searchLastPosition = null;

								searched = false;
							}

							if (!searched) {
								instance.unmarkSearch();

								var searchMarked = instance._searchMarked;

								var cursor = editor.getSearchCursor(searchText, null, caseInsensitive)

								while (cursor.findNext()) {
									searchMarked.push(editor.markText(cursor.from(), cursor.to(), 'codemirror-searched'));
								}

								searched = true;
							}

							var cursor;

							if (searchLastQuery != searchText) {
								searchLastPosition = null;

								searched = false;

								cursor = editor.getSearchCursor(searchText, editor.getCursor(), caseInsensitive);
							}
							else {
								cursor = editor.getSearchCursor(searchText, searchLastPosition, caseInsensitive);

								if (doReplace && cursor.findPrevious()) {
									editor.replaceRange(replaceText, cursor.from(), cursor.to());
								}
							}

							if (!cursor.findNext()) {
								cursor = editor.getSearchCursor(searchText, null, caseInsensitive);

								if (!cursor.findNext()) {
									cursor = null;
								}
							}

							if (cursor) {
								editor.setSelection(cursor.from(), cursor.to());

								instance._searchLastQuery = searchText;

								searchLastPosition = cursor.to();
							}

							instance._searched = searched;
							instance._searchLastPosition = searchLastPosition;
						}
					},

					updateEditorMode: function() {
						var instance = this;

						var mode = instance.getEditorModeFromLabel();

						var editor = instance.get(EDITOR);

						editor.setOption('mode', mode);
					},

					unmarkSearch: function() {
						var instance = this;

						var searchMarked = instance._searchMarked;
						var length = searchMarked.length;

						for (var i = 0; i < length; ++i) {
							searchMarked[i]();
						}

						instance._searchMarked.length = 0;
						instance._searched = false;
					},

					_afterIdChange: function(event) {
						var instance = this;

						instance.get(BOUNDING_BOX).attr(ID, event.newVal);
					},

					_createEditor: function(node , content) {
						var instance = this;

						var mode = instance.getEditorModeFromLabel();

						var editor = new CodeMirror(
							node.getDOM(),
							{
								lineNumbers: true,
								matchBrackets: true,
								mode: mode,
								onChange: function(editor) {
									instance.set(IS_DIRTY, true);
									instance.set(IS_RENDERED, false);

									instance.fire('onEditorChange');
								},
								onCursorActivity: function(editor) {
									editor.setLineClass(currentLine, null);

									currentLine = editor.setLineClass(editor.getCursor().line, 'codemirror-activeline');
								},
								onFocus: function(editor) {
									instance.fire('onEditorFocus');
								},
								value: content
							}
						);

						var currentLine = editor.setLineClass(0, 'codemirror-activeline');

						instance.set(EDITOR, editor);

						return editor;
					},

					_hideIsDirtyIndicator: function() {
						var instance = this;

						instance.get(DIRTY_INDICATOR_NODE).hide();
					},

					_onCloseButtonClick: function(event) {
						var instance = this;

						event.preventDefault();

						instance.fire(
							'close',
							{
								entryId: instance.get(ENTRY_ID)
							}
						);
					},

					_onIsDirtyChange: function(event) {
						var instance = this;

						if (event.newVal) {
							instance._showIsDirtyIndicator();
						}
						else {
							instance._hideIsDirtyIndicator();
						}
					},

					_renderCloseButton: function() {
						var instance = this;

						var closeButton = A.Node.create(TPL_CLOSE_BUTTON);

						closeButton.on('click', instance._onCloseButtonClick, instance);

						instance.get(CONTENT_BOX).append(closeButton);
					},

					_renderDirtyIndicator: function() {
						var instance = this;

						var dirtyIndicatorNode = A.Node.create(TPL_DIRTY_INDICATOR);

						dirtyIndicatorNode.hide();

						instance.get(CONTENT_BOX).prepend(dirtyIndicatorNode);

						instance.set(DIRTY_INDICATOR_NODE, dirtyIndicatorNode);
					},

					_showIsDirtyIndicator: function() {
						var instance = this;

						instance.get(DIRTY_INDICATOR_NODE).show();
					},

					_searched: false,
					_searchLastPosition: null,
					_searchLastQuery: null
				}
			}
		);

		A.TabEditor = TabEditor;
	},
	'',
	{
		requires: ['aui-tabs-base']
	}
);