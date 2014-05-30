AUI.add(
	'gadget-editor-tabs',
	function(A) {
		var Lang = A.Lang;
		var AArray = A.Array;

		var ACE_EDITOR = 'aceEditor';

		var CONTENT_BOX = 'contentBox';

		var DIRTY_INDICATOR_NODE = 'dirtyIndicatorNode';

		var ENTRY_ID = 'entryId';

		var FILE_NAME = 'fileName';

		var ID = 'id';

		var IS_DIRTY = 'isDirty';

		var STR_EMPTY = '';

		var TPL_CLOSE_BUTTON = '<a class="gadget-editor-tab-close icon-remove" href="javascript:;"></a>';

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
						);

						instance._addTab(tab);

						instance.selectTabById(tab.get(ID));

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
								editorContent: STR_EMPTY,
								entryId: entryId,
								isDirty: false,
								isNew: true,
								label: 'new ' + newTabCounter
							}
						);

						instance._addTab(tab);

						instance._newTabCounter = newTabCounter;

						instance.selectTabById(tab.get(ID));

						return tab;
					},

					closeTabById: function(id) {
						var instance = this;

						var tab = instance.getTabById(id);

						if (tab) {
							if (instance.size() === 1) {
								instance.addNewTab();
							}

							instance.removeTabById(id);
						}
					},

					getSelectedTab: function() {
						var instance = this;

						return instance.get('selection');
					},

					getTabById: function(id) {
						var instance = this;

						var tab = null;

						instance.some(
							function(item, index) {
								if (item.get(ID) === id) {
									tab = item;

									return true;
								}
							},
							instance
						);

						return tab;
					},

					removeTabById: function(id) {
						var instance = this;

						var tab = instance.getTabById(id);

						var index = instance.indexOf(tab);

						instance.remove(index);

						var tabContentBox = tab.get(CONTENT_BOX);
						var tabViewContentBox = instance.get(CONTENT_BOX);

						if (tabViewContentBox.contains(tabContentBox)) {
							tabViewContentBox.removeChild(tabContentBox);
						}
					},

					selectTabById: function(id) {
						var instance = this;

						var index = instance.indexOf(instance.getTabById(id));

						instance.selectChild(index);
					},

					_addTab: function(tab) {
						var instance = this;

						tab.addTarget(instance);

						tab.fire('add');

						TabViewEditor.superclass.add.apply(instance, arguments);
					},

					_validateAceEditor: function(value) {
						return (value instanceof A.AceEditor);
					},

					_validateDirtyIndicatorNode: function(value) {
						return (value instanceof A.Node);
					}
				}
			}
		);

		A.TabViewEditor = TabViewEditor;

		var TabEditor = A.Component.create(
			{
				ATTRS: {
					aceEditor: {
						validator: '_validateAceEditor'
					},

					dirtyIndicatorNode: {
						validator: '_validateDirtyIndicatorNode'
					},

					entryId: {
						setter: function(value) {
							return String(value);
						},
						value: STR_EMPTY
					},

					fileName: {
						validator: Lang.isString
					},

					isDirty: {
						validator: Lang.isBoolean,
						value: false
					},

					isNew: {
						validator: Lang.isBoolean,
						value: true
					},

					isRendered: {
						validator: Lang.isBoolean,
						value: false
					},

					label: {
						validator: Lang.isString
					}
				},

				EXTENDS: A.Tab,

				NAME: 'tab-editor',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._editorModes = {
							css: {
								'css': 1
							},
							gadget: {
								'xml': 1
							},
							html: {
								'htm': 1,
								'html': 1,
								'shtm': 1,
								'shtml': 1,
								'xhtml': 1
							},
							javascript: {
								'js': 1
							},
							xml: {
								'kml': 1,
								'wsdl': 1,
								'xsd': 1,
								'xsl': 1,
								'xsml': 1
							}
						};

						instance._createEditor(instance.get('panelNode'), config.editorContent);

						instance.updateEditorMode(config.label);
					},

					renderUI: function() {
						var instance = this;

						TabEditor.superclass.renderUI.apply(this, arguments);
					},

					bindUI: function() {
						var instance = this;

						TabEditor.superclass.bindUI.apply(this, arguments);

						instance.on('isDirtyChange', instance._onIsDirtyChange);
						instance.on('labelChange', instance._onLabelChange);
					},

					searchEditorText: function(searchText, caseInsensitive, replaceText, doReplace) {
						var instance = this;

						if (searchText) {
							var editor = instance.get(ACE_EDITOR).getEditor();

							if (doReplace) {
								editor.replace(replaceText);
							}
							else {
								editor.find(searchText);
							}
						}
					},

					updateEditorMode: function(fileName) {
						var instance = this;

						var mode = 'ace/mode/text';

						var extension = fileName.substr(fileName.lastIndexOf('.') + 1);

						var editorModes = instance._editorModes;

						if (editorModes.css[extension]) {
							mode = 'ace/mode/css';
						}
						else if (editorModes.gadget[extension] || editorModes.html[extension]) {
							mode = 'ace/mode/html';
						}
						else if (editorModes.javascript[extension]) {
							mode = 'ace/mode/javascript';
						}
						else if (editorModes.xml[extension]) {
							mode = 'ace/mode/xml';
						}

						instance.get(ACE_EDITOR).getEditor().getSession().setMode(mode);
					},

					_createEditor: function(node, content) {
						var instance = this;

						var aceEditor = new A.AceEditor(
							{
								boundingBox: node,
								height: '600',
								value: content,
								width: '100%'
							}
						).render();

						var session = aceEditor.getSession();

						session.on(
							'change',
							function(event) {
								instance.set(IS_DIRTY, true);

								instance.fire('onEditorChange');
							}
						);

						instance.set(ACE_EDITOR, aceEditor);

						return aceEditor;
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

						instance.get(DIRTY_INDICATOR_NODE).toggleView(event.newVal);
					},

					_onLabelChange: function(event) {
						var instance = this;

						instance.get(CONTENT_BOX).setHTML(event.newVal);

						instance._renderDirtyIndicator();
						instance._renderCloseButton();

						instance.set(FILE_NAME, event.newVal);

						event.preventDefault();
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

						dirtyIndicatorNode.toggleView(instance.get(IS_DIRTY));

						instance.get(CONTENT_BOX).prepend(dirtyIndicatorNode);

						instance.set(DIRTY_INDICATOR_NODE, dirtyIndicatorNode);
					}
				}
			}
		);

		A.TabEditor = TabEditor;
	},
	'',
	{
		requires: ['aui-ace-editor', 'aui-tabs-base', 'aui-tabview']
	}
);