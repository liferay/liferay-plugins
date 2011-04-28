AUI().add(
	'gadget-editor-tabs',
	function(A) {
		var TabViewEditor = A.Component.create(
			{
				EXTENDS: A.TabView,

				NAME: 'tab-view-editor',

				prototype: {
					addExistingDocument: function(entryId, label, editorContent) {
						var instance = this;

						var contentNode = A.Node.create('<div></div>');

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

						if (instance._newTabCounter === undefined) {
							instance._newTabCounter = 0;
						}

						var newTabCounter = instance._newTabCounter;

						newTabCounter++;

						var entryId = 'newTab' + newTabCounter;

						var contentNode = A.Node.create('<div></div>');

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

						var tab = instance.get('activeTab');

						var codeMirrorElement = tab.get('contentNode').one('.CodeMirror');

						if (!codeMirrorElement) {
							return;
						}

						var contentTabsHeight = instance.get('boundingBox').get('clientHeight');

						var listNodeHeight = instance.get('listNode').get('offsetHeight');

						var height =  contentTabsHeight - listNodeHeight;

						codeMirrorElement.setStyle('height', height + 'px');

						tab.get('editor').refresh();
					},

					closeTabById: function(id) {
						var instance = this;

						var tab = instance.getTabById(id);

						if (!tab) {
							return;
						}

						var items = instance.get('items');

						if (instance.get('items').length == 1) {
							instance.addNewTab();
						}

						instance.removeTabById(id);

						instance.adjustEditorHeight();
					},

					getTabById: function(id) {
						var instance = this;

						var items = instance.get('items');

						var tab = null;

						A.Array.some(
							items,
							function(value) {
								if (value.get('id') == id) {
									tab = value;

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

						var tabContentNode = tab.get('contentNode');

						var tabViewContentNode = instance.get('contentNode');

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

						if (!event.newVal) {
							return;
						}

						instance.adjustEditorHeight();
					}
				}
			}
		);

		A.TabViewEditor = TabViewEditor;

		var TabEditor = A.Component.create(
			{
				ATTRS: {
					contentValid: {},
					dirtyIndicatorNode: {},
					editor: {},
					entryId: {
						value: 0
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

						instance._searched = false;
						instance._searchLastPosition = null;
						instance._searchLastQuery = null;
						instance._searchMarked = [];

						instance._editorModes = {
							css: [ 'css' ],
							gadget: [ 'xml' ],
							javascript: [ 'js' ],
							xml: [ 'html', 'htm', 'shtml', 'shtm', 'xhtml', 'xsml', 'xsl', 'xsd', 'kml', 'wsdl' ]
						}

						instance._createEditor(instance.get('contentNode'), config.editorContent);
					},

					renderUI: function() {
						var instance = this;

						TabEditor.superclass.renderUI.apply(this, arguments);

						instance._renderCloseButton();

						instance._renderDirtyIndicator();
					},

					getEditorModeFromLabel: function() {
						var instance = this;

						var label = instance.get('label');

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

						if (!searchText) {
							return;
						}

						var editor = instance.get('editor');

						if (instance._searchLastQuery != searchText) {
							instance._searchLastPosition = null;

							instance._searched = false;
						}

						if (!instance._searched) {
							instance.unmarkSearch();

							for (var cursor = editor.getSearchCursor(searchText, null, caseInsensitive); cursor.findNext();) {
								instance._searchMarked.push(editor.markText(cursor.from(), cursor.to(), "codemirror-searched"));
							}

							instance._searched = true;
						}

						var cursor = null;

						if (instance._searchLastQuery != searchText) {
							instance._searchLastPosition = null;

							instance._searched = false;

							cursor = editor.getSearchCursor(searchText, editor.getCursor(), caseInsensitive);
						}
						else {
							cursor = editor.getSearchCursor(searchText, instance._searchLastPosition, caseInsensitive);

							if (doReplace && cursor.findPrevious()) {
								editor.replaceRange(replaceText, cursor.from(), cursor.to());
							}
						}

						if (!cursor.findNext()) {
							cursor = editor.getSearchCursor(searchText, null, caseInsensitive);

							if (!cursor.findNext()) {
								return;
							}
						}

						editor.setSelection(cursor.from(), cursor.to());

						instance._searchLastQuery = searchText;

						instance._searchLastPosition = cursor.to();
					},

					updateEditorMode: function() {
						var instance = this;

						var mode = instance.getEditorModeFromLabel();

						var editor = instance.get('editor');

						editor.setOption('mode', mode);

						editor.setValue(editor.getValue());
					},

					unmarkSearch: function() {
						var instance = this;

						for (var i = 0; i < instance._searchMarked.length; ++i) {
							instance._searchMarked[i]();
						}

						instance._searchMarked.length = 0;

						instance._searched = false;
					},

					_afterIdChange: function(event) {
						var instance = this;

						var id = event.newVal;

						instance.get('boundingBox').attr('id', id);
					},

					_createEditor: function(node , content) {
						var instance = this;

						var mode = instance.getEditorModeFromLabel();

						var editor = new CodeMirror(node.getDOM(), {
							lineNumbers: true,
							matchBrackets: true,
							mode: mode,
							onChange: function(editor) {
								instance.set('isDirty', true);
								instance.set('isRendered', false);

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
						});

						var currentLine = editor.setLineClass(0, 'codemirror-activeline');

						instance.set('editor', editor);

						return editor;
					},

					_hideIsDirtyIndicator: function() {
						var instance = this;

						instance.get('dirtyIndicatorNode').hide();
					},

					_onIsDirtyChange: function(event) {
						var instance = this;

						var isDirty = event.newVal;

						if (isDirty) {
							instance._showIsDirtyIndicator();
						}
						else {
							instance._hideIsDirtyIndicator();
						}
					},

					_renderCloseButton: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');

						var closeButton = A.Node.create('<a href="javascript:;"></a>');

						closeButton.addClass('gadget-editor-tab-close');

						var onClickCloseButton = function(event) {
							event.preventDefault();

							instance.fire('close', { entryId: instance.get('entryId') });
						};

						closeButton.on('click', onClickCloseButton);

						contentBox.append(closeButton);
					},

					_renderDirtyIndicator: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');

						var dirtyIndicatorNode = A.Node.create('<span>*</span>');

						dirtyIndicatorNode.hide();

						contentBox.prepend(dirtyIndicatorNode);

						instance.set('dirtyIndicatorNode', dirtyIndicatorNode);
					},

					_showIsDirtyIndicator: function() {
						var instance = this;

						instance.get('dirtyIndicatorNode').show();
					}
				}
			}
		);

		A.TabEditor = TabEditor;
	},
	'',
	{
		requires: ['aui-tabs-base'],
		use: []
	}
);