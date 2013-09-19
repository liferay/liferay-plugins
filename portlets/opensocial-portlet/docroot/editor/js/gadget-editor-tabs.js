AUI.add(
	'gadget-editor-tabs',
	function(A) {
		var Lang = A.Lang;

		var ACE_EDITOR = 'aceEditor';

		var CONTENT_BOX = 'contentBox';

		var DIRTY_INDICATOR_NODE = 'dirtyIndicatorNode';

		var ENTRY_ID = 'entryId';

		var FILE_NAME = 'fileName';

		var ID = 'id';

		var IS_DIRTY = 'isDirty';

		var LABEL = 'label';

		var TPL_CLOSE_BUTTON = '<a class="gadget-editor-tab-close icon-remove" href="javascript:;"></a>';

		var TPL_DIV = '<div></div>';

		var TPL_DIRTY_INDICATOR = '<span>*</span>';

		var TabViewEditor = A.Component.create(
			{
				EXTENDS: A.TabView,

				NAME: 'tab-view-editor',

				prototype: {
					_addTab: function(tab) {
						var instance = this;

						tab.addTarget(instance);

						tab.fire('add');

						TabViewEditor.superclass.add.apply(instance, arguments);
					},

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
								editorContent: '',
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
							if (instance.size() == 1) {
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
							function(item, index, collection) {
								if (item.get(ID) == id) {
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
				}
			}
		);

		A.TabViewEditor = TabViewEditor;

		var TabEditor = A.Component.create(
			{
				ATTRS: {
					aceEditor: {},
					dirtyIndicatorNode: {},
					fileName: '',
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
					},
					label: ''
				},

				EXTENDS: A.Tab,

				NAME: 'tab-editor',

				prototype: {
					bindUI: function() {
						var instance = this;

						TabEditor.superclass.bindUI.apply(this, arguments);

						instance.on('isDirtyChange', instance._onIsDirtyChange);
						instance.on('labelChange', instance._onLabelChange);
					},

					initializer: function(config) {
						var instance = this;

						instance._editorModes = {
							css: ['css'],
							gadget: ['xml'],
							html: ['html', 'htm', 'shtml', 'shtm', 'xhtml'],
							javascript: ['js'],
							xml: ['xsml', 'xsl', 'xsd', 'kml', 'wsdl']
						};

						instance._createEditor(instance.get('panelNode'), config.editorContent);

						instance.updateEditorMode(config.label);
					},

					renderUI: function() {
						var instance = this;

						TabEditor.superclass.renderUI.apply(this, arguments);
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

						var mode;

						var extension = fileName.substr(fileName.lastIndexOf('.') + 1);

						if (A.Array.indexOf(instance._editorModes.css, extension) != -1) {
							mode = 'ace/mode/css';
						}
						else if (A.Array.indexOf(instance._editorModes.gadget, extension) != -1) {
							mode = 'ace/mode/html';
						}
						else if (A.Array.indexOf(instance._editorModes.html, extension) != -1) {
							mode = 'ace/mode/html';
						}
						else if (A.Array.indexOf(instance._editorModes.javascript, extension) != -1) {
							mode = 'ace/mode/javascript';
						}
						else if (A.Array.indexOf(instance._editorModes.xml, extension) != -1) {
							mode = 'ace/mode/xml';
						}
						else {
							mode = 'ace/mode/text';
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

						if (instance.get(IS_DIRTY)) {
							dirtyIndicatorNode.show();
						}
						else {
							dirtyIndicatorNode.hide();
						}

						instance.get(CONTENT_BOX).prepend(dirtyIndicatorNode);

						instance.set(DIRTY_INDICATOR_NODE, dirtyIndicatorNode);
					},

					_showIsDirtyIndicator: function() {
						var instance = this;

						instance.get(DIRTY_INDICATOR_NODE).show();
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