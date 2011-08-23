AUI().add(
	'opensocial-editor',
	function (A) {
		var Lang = A.Lang;

		var ABSOLUTE = 'absolute';

		var ACTIVE = 'active';

		var ACTIVE_TAB = 'activeTab';

		var BODY_CONTENT = 'bodyContent';

		var BOUNDING_BOX = 'boundingBox';

		var CODE_MIRROR = 'codeMirror';

		var CONFIG_SPACER = {
			type: 'ToolbarSpacer'
		};

		var CONTENT_BOX = 'contentBox';

		var DISABLED = 'disabled';

		var EDITABLE = 'editable';

		var EDITOR = 'editor';

		var ENTRY_ID = 'entryId';

		var EVENT_ADD_FILE_ENTRY_NODE = 'addFileEntryNode';

		var EVENT_ADD_FOLDER_NODE = 'addFolderNode';

		var EVENT_CANCEL_RENAME_ENTRY = 'cancelRenameEntry';

		var EVENT_CLOSE_FILE_ENTRY = 'closeFileEntry';

		var EVENT_DELETE_ENTRY = 'deleteEntry';

		var EVENT_LOAD_CONTENT = 'loadContent';

		var EVENT_RENDER_GADGET = 'renderGadget';

		var EVENT_SAVE_CONTENT = 'saveContent';

		var EVENT_SAVE_RENAME_ENTRY = 'saveRenameEntry';

		var EVENT_START_RENAME_ENTRY = 'startRenameEntry';

		var FILE_ENTRY_LOADED = 'fileEntryLoaded';

		var FILE_ENTRY_URL = 'fileEntryURL';

		var GADGET_ID = 'gadgetId';

		var GET_FOLDER_CHILDREN = 'getFolderChildren';

		var HEIGHT = 'height';

		var ID = 'id';

		var INCREASE = 'increase';

		var IS_DIRTY = 'isDirty';

		var IS_NEW = 'isNew';

		var IS_NEW_ENTRY = 'isNewEntry';

		var IS_RENDERED = 'isRendered';

		var LABEL = 'label';

		var LAST_SELECTED = 'lastSelected';

		var LOADED_CHANGE = 'loadedChange';

		var NEW_FOLDER = 'New Folder';

		var NUM_PERCENTAGE_HUNDRED = '100%';

		var PARENT_NODE = 'parentNode';

		var PROXY_EL = 'proxyEl';

		var REPOSITORY_ID = 'repositoryId';

		var ROOT_FOLDER_ID = 'rootFolderId';

		var STR_T = 't';

		var TPL_EDITOR = '<div id="editorPortlet">' +
			'<div id="gadgetEditorToolbar"></div>' +
			'<hr />' +
			'<div class="gadget-editor-content" id="gadgetEditorContent">' +
				'<div class="tree-view-editor-column" id="treeViewEditorColumn">' +
					'<div id="treeViewEditor"></div>' +
				'</div>' +
				'<div class="main-editor-column" id="mainEditorColumn">' +
					'<div class="main-editor-column-resize" id="mainEditorColumnResize">' +
						'<div id="tabViewEditor"></div>' +
						'<div class="preview-panel" id="previewPanel"></div>' +
					'</div>' +
				'</div>' +
			'</div>' +
		'</div>';

		var TPL_ERROR_MESSAGE = '{name}<br /><br />{message}';

		var TPL_URL_DISPLAY = '"{0}" URL:<br /><br /> {1}';

		var UNTITLED = 'Untitled';

		var XML = '.xml';

		var UNTITLED_XML = UNTITLED + XML;

		var WIDTH = 'width';

		var WRAPPER = 'wrapper';

		var VALUE = 'value';

		var Editor = A.Component.create(
			{
				NAME: 'gadget-editor',

				ATTRS: {
					editorGadgetURL: {},
					gadgetPortletId: {},
					gadgetServerBase: {},
					publishGadgetPermission: {},
					repositoryId: {},
					resourceURL: {},
					rootFolderId: {}
				},

				prototype: {
					initializer: function() {
						var instance = this;

						A.StyleSheet.register(A.StyleSheet(), CODE_MIRROR);

						instance._io = {};

						instance._createEvents();
						instance._createIndexDataSet();
					},

					renderUI: function() {
						var instance = this;

						var container = A.Node.create(TPL_EDITOR);

						instance.get(CONTENT_BOX).appendChild(container);

						instance._container = container;

						instance._gadgetEditorHeight = container.one('#gadgetEditorContent').height();

						instance._renderLoadingMask();
						instance._renderToolbar();
						instance._renderEditorColumnResize();
						instance._renderPreviewPanelResize();
						instance._renderTreeViewEditor();
						instance._renderTabViewEditor();
						instance._renderPreviewPanel();
					},

					bindUI: function() {
						var instance = this;

						instance.after('tree-view-editor:append', instance._afterTreeViewAppend);

						instance.after('tree-node-editor:addFolderNode', instance._afterTreeNodeAddNewFolder);
						instance.after('tree-node-editor:append', instance._afterTreeNodeAppend);
						instance.after('tree-node-editor:closeFileEntry', instance._afterTreeNodeCloseFileEntry);
						instance.after('tree-node-editor:deleteEntry', instance._afterTreeNodeDeleteEntry);
						instance.after('tree-node-editor:entryIdChange', instance._afterTreeNodeEntryIdChange);
						instance.after('tree-node-editor:publish', instance._afterTreeNodePublish);
						instance.after('tree-node-editor:renameEntry', instance._afterTreeNodeRenameEntry);
						instance.after('tree-node-editor:select', instance._afterTreeNodeSelect);
						instance.after('tree-node-editor:showURL', instance._afterTreeNodeShowURL);
						instance.after('tree-node-editor:unpublish', instance._afterTreeNodeUnpublish);

						instance.after('editable-editor:cancel', instance._afterEditableCancel);

						instance.on('editable-editor:save', instance._onEditableSave);

						instance.after('tab-view-editor:activeTabChange', instance._afterTabViewActiveTabChange);

						instance.after('tab-editor:add', instance._afterTabAdd);
						instance.after('tab-editor:close', instance._afterTabClose);
						instance.after('tab-editor:entryIdChange', instance._afterTabEntryIdChange);
						instance.after('tab-editor:isDirtyChange', instance._afterTabIsDirtyChange);
						instance.after('tab-editor:isNewChange', instance._afterTabIsNewChange);

						instance.on('tab-editor:onEditorChange', instance._onTabEditorChange);
						instance.on('tab-editor:onEditorFocus', instance._onTabEditorFocus);

						Liferay.after('publishGadgetSuccess', instance._afterPublishGadgetSuccess, instance);
					},

					syncUI: function() {
						var instance = this;

						instance._tabViewEditor.addNewTab();

						var tabViewEditorBoundingBox = instance._tabViewEditor.get(BOUNDING_BOX);

						instance._tabViewEditor.setAttrs(
							{
								height: NUM_PERCENTAGE_HUNDRED,
								width: instance._getEditorColumnResizeWidth()
							}
						);

						tabViewEditorBoundingBox.setStyles(
							{
								position: ABSOLUTE,
								top: 0
							}
						);

						var previewResizeWrapper = instance._previewResize.get(WRAPPER);

						previewResizeWrapper.setStyles(
							{
								bottom: 0,
								height: 200,
								position: ABSOLUTE,
								width: instance._getEditorColumnResizeWidth()
							}
						);

						previewResizeWrapper.hide();

						instance._tabViewEditor.adjustEditorHeight();
					},

					_addEntryToDataSet: function(object) {
						var instance = this;

						var entryId = object.get(ENTRY_ID);

						if (entryId != '') {
							var item;

							var containsKey = instance._indexDataSet.containsKey(entryId);

							if (containsKey) {
								item = instance._indexDataSet.item(entryId);
							}
							else {
								item = {};
							}

							var addEntry = true;

							if (A.instanceOf(object, A.TreeNode)) {
								item.node = object;
							}
							else if (A.instanceOf(object, A.Tab)) {
								item.tab = object;
							}
							else {
								addEntry = false;
							}

							if (addEntry) {
								instance._indexDataSet.add(entryId, item);
							}
						}
					},

					_addNewEntryToTreeNode: function(isLeaf, parentFolderId) {
						var instance = this;

						var rootFolder = false;

						var parentFolderNode = instance._treeViewEditor.getNodeById(parentFolderId);

						if (!parentFolderNode) {
							parentFolderNode = instance._treeViewEditor;

							rootFolder = true;
						}

						if (rootFolder || parentFolderNode.get('loaded')) {
							if (!rootFolder) {
								parentFolderNode.expand();
							}

							A.setTimeout(
								function() {
									var entryLabel = instance._getNextNewEntryName(isLeaf, parentFolderNode);

									instance._treeViewEditor.addNodeToFolder(entryLabel, isLeaf, parentFolderId);
								},
								0
							);

							if (!rootFolder) {
								parentFolderNode.detach(LOADED_CHANGE);
							}
						}
						else {
							parentFolderNode.after(
								LOADED_CHANGE,
								function() {
									instance._addNewEntryToTreeNode(isLeaf, parentFolderId);
								}
							);

							parentFolderNode.expand();
						}
					},

					_adjustPreviewPanelContentHeight: function() {
						var instance = this;

						var previewResize = instance._previewResize;

						var previewResizeWrapper = previewResize.get(WRAPPER);
						var node = previewResize.get('node');

						var previewHeight = previewResizeWrapper.height() - node.getPadding(STR_T);

						var previewPanel = instance._previewPanel;

						var previewPanelHeaderHeight = previewPanel.headerNode.get('offsetHeight');

						var bodyNode = previewPanel.bodyNode;

						bodyNode.height(previewHeight - previewPanelHeaderHeight - bodyNode.getPadding('tb'));
					},

					_afterEditableCancel: function(event) {
						var instance = this;

						instance.fire(
							EVENT_CANCEL_RENAME_ENTRY,
							{
								entryId: event.target.get(ENTRY_ID)
							}
						);
					},

					_afterPublishGadgetSuccess: function(event) {
						var instance = this;

						var entryId = instance._publishGadgetDialog.get(ID);

						var node = instance._getNodeFromDataSet(entryId);

						var permissions = node.get('permissions');

						permissions.unpublishPermission = event.unpublishPermission;

						node.setAttrs(
							{
								gadgetId: event.gadgetId,
								permissions: permissions
							}
						);

						instance._publishGadgetDialog.close();
					},

					_afterTabAdd: function(event) {
						var instance = this;

						instance._addEntryToDataSet(event.target);
					},

					_afterTabClose: function(event) {
						var instance = this;

						instance.fire(
							EVENT_CLOSE_FILE_ENTRY,
							{
								entryId: event.entryId
							}
						);
					},

					_afterTabEntryIdChange: function(event) {
						var instance = this;

						instance._removeEntryFromDataSet(event.prevVal);

						instance._addEntryToDataSet(event.target);
					},

					_afterTabIsDirtyChange: function(event) {
						var instance = this;

						instance._saveButton.set(DISABLED, !event.newVal);
					},

					_afterTabIsNewChange: function(event) {
						var instance = this;

						instance._deleteButton.set(DISABLED, event.newVal);
					},

					_afterTabViewActiveTabChange: function(event) {
						var instance = this;

						var activeTab = event.newVal;
						var previousTab = event.prevVal;

						var entryId = activeTab.get(ENTRY_ID);

						var node = instance._getNodeFromDataSet(entryId);

						if (node && !node.isSelected()) {
							var lastSelected = instance._treeViewEditor.get(LAST_SELECTED);

							if (lastSelected) {
								lastSelected.unselect();
							}

							node.select();

							var parentNode = node.get(PARENT_NODE);

							while (parentNode && A.instanceOf(parentNode, A.TreeNode)) {
								parentNode.expand();

								parentNode = parentNode.get(PARENT_NODE);
							}
						}

						instance._saveButton.set(DISABLED, !activeTab.get(IS_DIRTY));

						instance._deleteButton.set(DISABLED, activeTab.get(IS_NEW));

						if (previousTab) {
							previousTab.unmarkSearch();
						}

						if (instance._previewButton.StateInteraction.get(ACTIVE)) {
							instance.fire(
								EVENT_RENDER_GADGET,
								{
									entryId: entryId,
									noCache: true
								}
							);
						}

						instance._updateUndoButtons();
					},

					_afterTreeNodeAddNewFolder: function(event) {
						var instance = this;

						instance.fire(
							EVENT_ADD_FOLDER_NODE,
							{
								parentFolderId: event.parentFolderId
							}
						);
					},

					_afterTreeNodeAppend: function(event) {
						var instance = this;

						instance._addEntryToDataSet(event.tree.node);
					},

					_afterTreeNodeCloseFileEntry: function(event) {
						var instance = this;

						instance.fire(
							EVENT_CLOSE_FILE_ENTRY,
							{
								entryId: event.entryId
							}
						);
					},

					_afterTreeNodeDeleteEntry: function(event) {
						var instance = this;

						instance.fire(
							EVENT_DELETE_ENTRY,
							{
								entryId: event.entryId
							}
						);
					},
					_afterTreeNodeEntryIdChange: function(event) {
						var instance = this;

						instance._removeEntryFromDataSet(event.prevVal);

						instance._addEntryToDataSet(event.target);
					},

					_afterTreeNodePublish: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var tab = instance._getTabFromDataSet(entryId);

						if (tab && tab.get(IS_DIRTY)) {
							var error = 'Save the gadget before publishing.';

							instance._showErrorDialog(error);
						}
						else {
							var node = instance._getNodeFromDataSet(entryId);

							var uri = instance.get('editorGadgetURL').replace('editorGadgetURLPlaceholder', node.get(FILE_ENTRY_URL));

							var publishGadgetDialog = Liferay.Util._openWindow(
								{
									cache: false,
									dialog: {
										centered: true,
										destroyOnClose: true,
										id: entryId,
										modal: true,
										width: 700
									},
									dialogIframe: {
										uri: uri
									},
									title: 'Publish Gadget',
									uri: uri
								}
							);

							instance._publishGadgetDialog = publishGadgetDialog;
						}
					},

					_afterTreeNodeRenameEntry: function(event) {
						var instance = this;

						instance.fire(
							EVENT_START_RENAME_ENTRY,
							{
								entryId: event.entryId
							}
						);
					},

					_afterTreeNodeSelect: function(event) {
						var instance = this;

						var target = event.target;

						if (target.isLeaf()) {
							instance.fire(
								EVENT_LOAD_CONTENT,
								{
									entryId: target.get(ENTRY_ID)
								}
							);
						}
					},

					_afterTreeNodeShowURL: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						var bodyContent = Lang.sub(TPL_URL_DISPLAY, [node.get(LABEL), node.get(FILE_ENTRY_URL)]);

						instance._createDialog('URL', bodyContent, false, true, null).render();
					},

					_afterTreeNodeUnpublish: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var node = instance._getNodeFromDataSet(entryId);

						var gadgetId = node.get(GADGET_ID);

						instance._showConfirmationDialog('Are you sure you want to unpublish the gadget "' + node.get(LABEL) + '"?', instance._unpublishGadget, node, gadgetId);
					},

					_afterTreeViewAppend: function(event) {
						var instance = this;

						instance._addEntryToDataSet(event.tree.node);
					},

					_appendEditorChildren: function(data) {
						var instance = this;

						if (data.error) {
							instance._showErrorDialog(data.error);
						}
						else {
							var treeViewEditor = instance._treeViewEditor;

							A.Array.each(
								data,
								function(item, index, collection) {
									var node = new A.TreeNodeEditor(
										{
											entryId: item.entryId,
											fileEntryURL: item.fileEntryURL,
											gadgetId: item.gadgetId,
											label: item.label,
											leaf: item.leaf,
											permissions: item.permissions,
											type: item.type
										}
									);

									treeViewEditor.appendChild(node);
								}
							);

							treeViewEditor.sortChildren();
						}
					},

					_changeEditorFontSize: function(action) {
						var instance = this;

						var fontSize = instance._fontSize;

						if (Lang.isUndefined(fontSize)) {
							fontSize = 12;
						}

						if (action == INCREASE) {
							fontSize += 2;

							if (fontSize >= 64) {
								instance._increaseEditorFontSizeButton.disable();
							}

							instance._decreaseEditorFontSizeButton.enable();
						}
						else {
							fontSize -= 2;

							if (fontSize <= 2) {
								instance._decreaseEditorFontSizeButton.disable();
							}

							instance._increaseEditorFontSizeButton.enable();
						}

						A.StyleSheet(CODE_MIRROR).set(
							'.CodeMirror',
							{
								fontSize: fontSize + 'px'
							}
						);

						instance._fontSize = fontSize;

						instance._tabViewEditor.get(ACTIVE_TAB).get(EDITOR).refresh();
					},

					_closeFileEntry: function(entryId) {
						var instance = this;

						var tab = instance._getTabFromDataSet(entryId);

						if (tab) {
							instance._tabViewEditor.closeTabById(tab.get(ID));
						}

						var node = instance._getNodeFromDataSet(entryId);

						if (node) {
							if (node.isSelected()) {
								node.unselect();
							}

							node.set(FILE_ENTRY_LOADED, false);
						}
					},

					_closePreviewPanel: function() {
						var instance = this;

						instance._previewResize.get(WRAPPER).hide();

						var tabViewEditor = instance._tabViewEditor;

						tabViewEditor.set(HEIGHT, instance._gadgetEditorHeight);

						tabViewEditor.adjustEditorHeight();

						var previewStateInteraction = instance._previewButton.StateInteraction;

						previewStateInteraction.set(ACTIVE, false);
					},

					_closeSearchDialog: function() {
						var instance = this;

						instance._searchDialog.close();

						var tab = instance._tabViewEditor.get(ACTIVE_TAB);

						tab.unmarkSearch();

						instance._searchEditorButton.StateInteraction.set(ACTIVE, false);
					},

					_createDialog: function(title, bodyContent, modal, close, buttons) {
						var instance = this;

						return new A.Dialog(
							{
								bodyContent: bodyContent,
								buttons: buttons,
								centered: true,
								close: close,
								destroyOnClose: true,
								draggable: true,
								height: 200,
								modal: modal,
								resizable: false,
								title: title,
								width: 500
							}
						);
					},

					_createEvents: function() {
						var instance = this;

						instance.publish(
							EVENT_ADD_FILE_ENTRY_NODE,
							{
								defaultFn: instance._defAddFileEntryNodeFn
							}
						);

						instance.publish(
							EVENT_ADD_FOLDER_NODE,
							{
								defaultFn: instance._defAddFolderNodeFn
							}
						);

						instance.publish(
							EVENT_CANCEL_RENAME_ENTRY,
							{
								defaultFn: instance._defCancelRenameEntryFn
							}
						);

						instance.publish(
							EVENT_CLOSE_FILE_ENTRY,
							{
								defaultFn: instance._defCloseFileEntryFn
							}
						);

						instance.publish(
							EVENT_DELETE_ENTRY,
							{
								defaultFn: instance._defDeleteEntryFn
							}
						);

						instance.publish(
							EVENT_LOAD_CONTENT,
							{
								defaultFn: instance._defLoadContentFn
							}
						);

						instance.publish(
							EVENT_RENDER_GADGET,
							{
								defaultFn: instance._defRenderGadgetFn
							}
						);

						instance.publish(
							EVENT_SAVE_CONTENT,
							{
								defaultFn: instance._defSaveContentFn
							}
						);

						instance.publish(
							EVENT_SAVE_RENAME_ENTRY,
							{
								defaultFn: instance._defSaveRenameEntryFn
							}
						);

						instance.publish(
							EVENT_START_RENAME_ENTRY,
							{
								defaultFn: instance._defStartRenameEntryFn
							}
						);
					},

					_createIndexDataSet: function() {
						var instance = this;

						instance._indexDataSet = new A.DataSet();
					},

					_defAddFileEntryNodeFn: function() {
						var instance = this;

						instance._tabViewEditor.addNewTab();
					},

					_defAddFolderNodeFn: function(event) {
						var instance = this;

						instance._addNewEntryToTreeNode(false, event.parentFolderId);
					},

					_defCancelRenameEntryFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get(IS_NEW_ENTRY)) {
							node.get(PARENT_NODE).removeChild(node);
						}
					},

					_defCloseFileEntryFn: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab && tab.get(IS_DIRTY) && !event.noConfirm) {
							instance._showConfirmationDialog('"' + tab.get(LABEL) + '" has not been saved. Are you sure you want to close the tab?', instance._closeFileEntry, entryId);
						}
						else {
							instance._closeFileEntry(entryId);
						}
					},

					_defDeleteEntryFn: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var unpublish = '';

						var node = instance._getNodeFromDataSet(entryId);

						if (node.get(GADGET_ID) > 0) {
							unpublish = 'unpublish and ';
						}

						instance._showConfirmationDialog('Are you sure you want to ' + unpublish + 'delete "' + node.get(LABEL) + '"?', instance._deleteEntry, node, entryId);
					},

					_defLoadContentFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get(FILE_ENTRY_LOADED)) {
							if (event.entryId != instance._tabViewEditor.get(ACTIVE_TAB).get(ENTRY_ID)) {
								var tabId = instance._getTabFromDataSet(event.entryId).get(ID);

								instance._tabViewEditor.selectTabById(tabId);
							}
						}
						else {
							var callback = function(data) {
								if (data.error) {
									node.unselect();

									instance._showErrorDialog(data.error);

									return;
								}

								var tab = instance._tabViewEditor.addExistingDocument(event.entryId, node.get(LABEL), data.content);

								node.set(FILE_ENTRY_LOADED, true);
							};

							instance._requestGetFileEntryContent(node.get(ENTRY_ID), callback);
						}
					},

					_defRenderGadgetFn: function(event) {
						var instance = this;

						var previewPanel = instance._previewPanel;
						var previewPanelBodyNode = previewPanel.bodyNode;

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab.get(IS_DIRTY) || tab.get(IS_NEW)) {
							previewPanelBodyNode.empty();

							var error = 'Save the gadget before previewing.';

							previewPanel.set(BODY_CONTENT, error);

							instance._showPreviewPanel();

							return;
						}

						var node = instance._getNodeFromDataSet(event.entryId);

						var fileEntryURL = node.get(FILE_ENTRY_URL);

						var callback = function(data) {
							if (data.error) {
								if (data.error.name == 'ProcessingException') {
									previewPanelBodyNode.empty();

									previewPanel.set(BODY_CONTENT, data.error.message);

									instance._showPreviewPanel();
								}
								else {
									instance._showErrorDialog(data.error);
								}
							}
							else {
								previewPanelBodyNode.empty();

								new Liferay.OpenSocial.Gadget(
									{
										appId: fileEntryURL,
										debug: 1,
										height: data.height,
										moduleId: data.moduleId,
										nocache: 1,
										portletId: instance.get('gadgetPortletId'),
										requiresPubsub: data.requiresPubsub,
										scrolling: data.scrolling,
										secureToken: data.secureToken,
										serverBase: instance.get('gadgetServerBase'),
										specUrl: fileEntryURL,
										view: 'default'
									}
								).render(previewPanelBodyNode);

								tab.set(IS_RENDERED, true);

								instance._showPreviewPanel();
							}
						};

						if (!tab.get(IS_RENDERED) || event.noCache) {
							instance._requestGetRenderParameters(fileEntryURL, callback);
						}
						else {
							instance._showPreviewPanel();
						}
					},

					_defSaveContentFn: function(event) {
						var instance = this;

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab.get(IS_NEW) && tab.get(IS_DIRTY)) {
							var parentFolderId = instance._treeViewEditor.getSelectedFolderId();

							if (parentFolderId == 0) {
								parentFolderId = instance._rootNode.get(ID);
							}

							instance._addNewEntryToTreeNode(true, parentFolderId);
						}
						else if (tab.get(IS_DIRTY)) {
							var callback = function(data) {
								if (data.error) {
									instance._showErrorDialog(data.error);
								}
								else {
									tab.set(IS_DIRTY, false);
								}
							};

							instance._requestUpdateFileEntryContent(event.entryId, tab.get(EDITOR).getValue(), callback);
						}
					},

					_defSaveRenameEntryFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get(IS_NEW_ENTRY)) {
							instance._saveRenameNewEntry(node);
						}
						else {
							instance._saveRenameExistingEntry(node);
						}
					},

					_defStartRenameEntryFn: function(event) {
						var instance = this;

						var editable = instance._getNodeFromDataSet(event.entryId).get(EDITABLE);

						instance._visibleEditable = editable;

						editable.fire('startEditing');
					},

					_deleteEntry: function(node, entryId) {
						var instance = this;

						var deleteEntryCallback = function(data) {
							if (data && data.error) {
								instance._showErrorDialog(data.error);
							}
							else {
								if (node.isLeaf()) {
									instance.fire(
										EVENT_CLOSE_FILE_ENTRY,
										{
											entryId: entryId,
											noConfirm: true
										}
									);
								}
								else {
									var children = node.getChildren(true);

									A.Array.each(
										children,
										function(item, index, collection) {
											if (item.isLeaf()) {
												instance.fire(
													EVENT_CLOSE_FILE_ENTRY,
													{
														entryId: item.get(ENTRY_ID),
														noConfirm: true
													}
												);
											}
										}
									);
								}

								var parentNode = node.get(PARENT_NODE);

								parentNode.removeChild(node);
							}
						};

						if (node.isLeaf()) {
							var gadgetId = node.get(GADGET_ID);

							if (gadgetId > 0) {
								instance._unpublishGadget(node, gadgetId);
							}

							instance._requestDeleteFileEntry(entryId, deleteEntryCallback);
						}
						else {
							instance._requestDeleteFolder(entryId, deleteEntryCallback);
						}
					},

					_getEditorColumnResizeWidth: function() {
						var instance = this;

						var mainEditorColumnResize = A.one('#mainEditorColumnResize');

						return mainEditorColumnResize.width();
					},

					_getIORequest: function(resourceId, callback) {
						var instance = this;

						var io = instance._io[resourceId];

						if (!io) {
							io = A.io.request(
								instance._getResourceURL(resourceId),
								{
									autoLoad: false,
									dataType: 'json',
									on: {
										failure: function(event) {
											instance._loadingMask.hide();

											instance._showErrorDialog('IO request "' + resourceId + '" failed');
										}
									}
								}
							);

							instance._io[resourceId] = io;
						}

						io.once(
							'success',
							function(event) {
								instance._loadingMask.hide();

								if (callback) {
									var message = this.get('responseData');

									callback.apply(instance, [message]);
								}
							}
						);

						return io;
					},

					_getNextNewEntryName: function(leafNode, parentNode) {
						var instance = this;

						var children = parentNode.getChildren();

						var label;

						if (leafNode) {
							label = UNTITLED_XML;
						}
						else {
							label = NEW_FOLDER;
						}

						var i = 0;

						var duplicateLabel = false;

						do {
							duplicateLabel = A.Array.some(
								children,
								function(item, index, collection) {
									if (item.isLeaf() == leafNode && item.get(LABEL).toLowerCase() == label.toLowerCase()) {
										i++;

										if (leafNode) {
											label = UNTITLED + i + XML;
										}
										else {
											label = NEW_FOLDER + i;
										}

										return true;
									}
								}
							);
						}
						while (duplicateLabel);

						return label;
					},

					_getNodeFromDataSet: function(entryId) {
						var instance = this;

						var node = null;

						if (entryId != '') {
							var item = instance._indexDataSet.item(entryId);

							node = item && item.node;
						}

						return node;
					},

					_getResourceURL: function(id) {
						var instance = this;

						var resourceURL = instance.get('resourceURL');

						resourceURL += '&p_p_resource_id=' + id;

						return resourceURL;
					},

					_getTabFromDataSet: function(entryId) {
						var instance = this;

						var tab = null;

						if (entryId != '') {
							var item = instance._indexDataSet.item(entryId);

							tab = item && item.tab;
						}

						return tab;
					},

					_onEditableSave: function(event) {
						var instance = this;

						event.preventDefault();

						instance.fire(
							EVENT_SAVE_RENAME_ENTRY,
							{
								entryId: event.target.get(ENTRY_ID)
							}
						);
					},

					_onTabEditorChange: function(event) {
						var instance = this;

						instance._updateUndoButtons();
					},

					_onTabEditorFocus: function(event) {
						var instance = this;

						instance._treeViewEditor.get('treeActionOverlayManager').hideAll();

						var activeEditable = instance._treeViewEditor.get('activeEditable');

						if (activeEditable) {
							activeEditable.save();
						}
					},

					_removeEntryFromDataSet: function(entryId) {
						var instance = this;

						instance._indexDataSet.removeKey(entryId);
					},

					_renderEditorColumnResize: function() {
						var instance = this;

						var gadgetEditorWidth = A.one('#gadgetEditorContent').width();

						var mainEditorColumnResize = new A.Resize(
							{
								after: {
									end: function(event) {
										var tabViewEditor = instance._tabViewEditor;

										var width = instance._getEditorColumnResizeWidth();

										tabViewEditor.set(WIDTH, width);

										instance._previewResize.get(WRAPPER).setStyle(WIDTH, width);

										instance._previewPanel.set(WIDTH, width);

										instance._tabViewEditor.adjustEditorHeight();
									}
								},
								handles: 'l',
								node: '#mainEditorColumnResize',
								on: {
									end: function(event) {
										var treeViewEditorColumn = A.one('#treeViewEditorColumn');

										var width = gadgetEditorWidth - event.target.info.offsetWidth;

										treeViewEditorColumn.setStyle(WIDTH, width);
									}
								},
								proxy: true,
								wrap: true
							}
						);

						mainEditorColumnResize.get(PROXY_EL).addClass('main-editor-column-resize-proxy');

						mainEditorColumnResize.get(WRAPPER).setStyle(HEIGHT, NUM_PERCENTAGE_HUNDRED);

						mainEditorColumnResize.plug(
							A.Plugin.ResizeConstrained,
							{
								maxWidth: gadgetEditorWidth - 50,
								minWidth: gadgetEditorWidth / 2
							}
						);
					},

					_renderLoadingMask: function() {
						var instance = this;

						var loadingMask = A.one('#editorPortlet').plug(
						   A.LoadingMask,
						   {
							  background: 'none',
							  strings: {
								 loading: 'Busy'
							  }
						   }
						).loadingmask;

						instance._loadingMask = loadingMask;
					},

					_renderPreviewPanel: function() {
						var instance = this;

						var previewPanelRefreshButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										var entryId = instance._tabViewEditor.get(ACTIVE_TAB).get(ENTRY_ID);

										instance.fire(
											EVENT_RENDER_GADGET,
											{
												entryId: entryId,
												noCache: true
											}
										);
									}
								},
								icon: 'refresh'
							}
						);

						var previewPanelCloseButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance._closePreviewPanel();
									}
								},
								icon: 'close'
							}
						);

						var previewPanel = new A.Panel(
							{
								headerContent: 'Preview',
								icons: [
									previewPanelRefreshButton,
									previewPanelCloseButton
								],
								boundingBox: '#previewPanel'
							}
						).render();

						instance._previewPanelRefreshButton = previewPanelRefreshButton;
						instance._previewPanelCloseButton = previewPanelCloseButton;
						instance._previewPanel = previewPanel;
					},

					_renderPreviewPanelResize: function() {
						var instance = this;

						var previewResize = new A.Resize(
							{
								handles: STR_T,
								node: '#previewPanel',
								after: {
									end: function(event) {
										var height = event.target.info.offsetHeight;

										var editorHeight = instance._gadgetEditorHeight - height;

										instance._tabViewEditor.set(HEIGHT,  editorHeight);

										instance._tabViewEditor.adjustEditorHeight();

										previewResize.get(WRAPPER).setStyle(HEIGHT, height);

										instance._adjustPreviewPanelContentHeight();
									}
								},
								proxy: true,
								wrap: true
							}
						);

						previewResize.get(PROXY_EL).addClass('preview-panel-resize-proxy');

						instance._previewResize = previewResize;

						previewResize.plug(A.Plugin.ResizeConstrained,
							{
								maxHeight: instance._gadgetEditorHeight - 50,
								minHeight: 50
							}
						);
					},

					_renderTabViewEditor: function() {
						var instance = this;

						var tabViewEditor = new A.TabViewEditor(
							{
								boundingBox: '#tabViewEditor'
							}
						).render();

						tabViewEditor.addTarget(instance);

						instance._tabViewEditor = tabViewEditor;
					},

					_renderToolbar: function() {
						var instance = this;

						var newFileEntryButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance.fire(EVENT_ADD_FILE_ENTRY_NODE);
									}
								},
								icon: 'gadgeteditor-addfile'
							}
						);

						var newFolderButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										var parentFolderId = instance._treeViewEditor.getSelectedFolderId();

										if (parentFolderId == 0) {
											parentFolderId = instance._rootNode.get(ID);
										}

										instance.fire(
											EVENT_ADD_FOLDER_NODE,
											{
												parentFolderId: parentFolderId
											}
										);
									}
								},
								icon: 'gadgeteditor-addfolder'
							}
						);

						var saveButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										var entryId = instance._tabViewEditor.get(ACTIVE_TAB).get(ENTRY_ID);

										instance.fire(
											EVENT_SAVE_CONTENT,
											{
												entryId: entryId
											}
										);
									}
								},
								icon: 'gadgeteditor-disk'
							}
						);

						var deleteButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										var entryId = instance._tabViewEditor.get(ACTIVE_TAB).get(ENTRY_ID);

										instance.fire(
											EVENT_DELETE_ENTRY,
											{
												entryId: entryId
											}
										);
									}
								},
								icon: 'gadgeteditor-delete'
							}
						);

						var previewButton = new A.ButtonItem(
							{
								activeState: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										if (event.target.StateInteraction.get(ACTIVE)) {
											var entryId = instance._tabViewEditor.get(ACTIVE_TAB).get(ENTRY_ID);

											instance.fire(
												EVENT_RENDER_GADGET,
												{
													entryId: entryId
												}
											);
										}
										else {
											instance._closePreviewPanel();
										}
									}
								},
								icon: 'image',
								label: 'Preview'
							}
						);

						var increaseFontSizeButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance._changeEditorFontSize(INCREASE);
									}
								},
								icon: 'zoomin'
							}
						);

						var decreaseFontSizeButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance._changeEditorFontSize('decrease');
									}
								},
								icon: 'zoomout'
							}
						);

						var undoEditorButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance._undoContent('undo');
									}
								},
								icon: 'arrowrefresh-1-l'
							}
						);

						var redoEditorButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										instance._undoContent('redo');
									}
								},
								icon: 'arrowrefresh-1-r'
							}
						);

						var formatEditorButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										var editor = instance._tabViewEditor.get(ACTIVE_TAB).get(EDITOR);

										var lineCount = editor.lineCount();

										for (var i = 1; i <= lineCount; i++) {
											editor.indentLine(i);
										}
									}
								},
								icon: 'arrowthickstop-1-r'
							}
						);

						var searchEditorButton = new A.ButtonItem(
							{
								activeState: true,
								handler: function(event) {
									if (!event.target.get(DISABLED)) {
										if (event.target.StateInteraction.get(ACTIVE)) {
											instance._showSearchDialog();
										}
										else {
											instance._closeSearchDialog();
										}
									}
								},
								icon: 'search'
							}
						);

						var editorToolbar = new A.Toolbar(
							{
								boundingBox: '#gadgetEditorToolbar',
								children: [
									newFileEntryButton,
									newFolderButton,
									saveButton,
									deleteButton,
									CONFIG_SPACER,
									previewButton,
									CONFIG_SPACER,
									increaseFontSizeButton,
									decreaseFontSizeButton,
									CONFIG_SPACER,
									undoEditorButton,
									redoEditorButton,
									CONFIG_SPACER,
									formatEditorButton,
									CONFIG_SPACER,
									searchEditorButton
								]
							}
						).render();

						instance._newFileEntryButton = newFileEntryButton;
						instance._newFolderButton = newFolderButton;
						instance._saveButton = saveButton;
						instance._deleteButton = deleteButton;
						instance._previewButton = previewButton;
						instance._increaseEditorFontSizeButton = increaseFontSizeButton;
						instance._decreaseEditorFontSizeButton = decreaseFontSizeButton;
						instance._undoContentButton = undoEditorButton;
						instance._redoEditorButton = redoEditorButton;
						instance._formatEditorButton = formatEditorButton;
						instance._searchEditorButton = searchEditorButton;

						instance._editorToolbar = editorToolbar;
					},

					_renderTreeViewEditor: function() {
						var instance = this;

						var callback = A.bind(instance._appendEditorChildren, instance);

						instance._requestGetFolderChildren(instance.get(ROOT_FOLDER_ID), instance.get(REPOSITORY_ID), true, callback);

						var treeViewEditor = new A.TreeViewEditor(
							{
								boundingBox: '#treeViewEditor',
								io: {
									cfg: {
										data: function(node) {
											return {
												folderId: node.get(ENTRY_ID),
												getFileEntries: true,
												repositoryId: instance.get(REPOSITORY_ID)
											};
										},
										on: {
											failure: function(event) {
												instance._showErrorDialog('Failed to read the Document Library');
											},
											success: function(event) {
												this.sortChildren();
											}
										}
									},
									url: instance._getResourceURL(GET_FOLDER_CHILDREN)
								},
								publishGadgetPermission: instance.get('publishGadgetPermission'),
								rootFolderId: instance.get(ROOT_FOLDER_ID)
							}
						).render();

						treeViewEditor.addTarget(instance);

						instance._treeViewEditor = treeViewEditor;
					},

					_requestAddFileEntry: function(name, content, folderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'addFileEntry',
							{
								content: content,
								fileEntryTitle: name,
								folderId: folderId
							},
							callback
						);
					},

					_requestAddFolder: function(name, parentFolderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'addFolder',
							{
								folderName: name,
								parentFolderId: parentFolderId
							},
							callback
						);
					},

					_requestDeleteFileEntry: function(fileEntryId, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'deleteFileEntry',
							{
								fileEntryId: fileEntryId
							},
							callback
						);
					},

					_requestDeleteFolder: function(folderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'deleteFolder',
							{
								folderId: folderId
							},
							callback
						);
					},

					_requestGetFileEntryContent: function(fileEntryId, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'getFileEntryContent',
							{
								fileEntryId: fileEntryId
							},
							callback
						);
					},

					_requestGetFolderChildren: function(folderId, repositoryId, getFileEntries, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							GET_FOLDER_CHILDREN,
							{
								folderId: folderId,
								getFileEntries: getFileEntries,
								repositoryId: repositoryId
							},
							callback
						);
					},

					_requestGetRenderParameters: function(fileEntryURL, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'getRenderParameters',
							{
								fileEntryURL: fileEntryURL
							},
							callback
						);
					},

					_requestUpdateFileEntryContent: function(fileEntryId, content, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'updateFileEntryContent',
							{
								content: content,
								fileEntryId: fileEntryId
							},
							callback
						);
					},

					_requestUpdateFileEntryTitle: function(fileEntryId, title, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'updateFileEntryTitle',
							{
								fileEntryId: fileEntryId,
								fileEntryTitle: title
							},
							callback
						);
					},

					_requestUpdateFolderName: function(folderId, name, callback) {
						var instance = this;

						instance._loadingMask.show();

						instance._sendIORequest(
							'updateFolderName',
							{
								folderId: folderId,
								folderName: name
							},
							callback
						);
					},

					_saveRenameExistingEntry: function(node) {
						var instance = this;

						var editable = node.get(EDITABLE);

						var label = editable.inputNode.get(VALUE);

						var entryId = node.get(ENTRY_ID);

						var callback = function(data) {
							var dataError = data.error;

							editable.fire('stopEditing', !dataError);

							if (dataError) {
								instance._showErrorDialog(dataError);
							}
							else {
								node.set(LABEL, label);

								node.sort();

								if (node.isLeaf()) {
									var tab = instance._getTabFromDataSet(entryId);

									if (tab) {
										tab.set(LABEL, label);

										tab.updateEditorMode();
									}
								}
							}
						};

						if (node.isLeaf()) {
							instance._requestUpdateFileEntryTitle(entryId, label, callback);
						}
						else {
							instance._requestUpdateFolderName(entryId, label, callback);
						}
					},

					_saveRenameNewEntry: function(node) {
						var instance = this;

						var editable = node.get(EDITABLE);

						var label = editable.inputNode.get(VALUE);

						var parentFolderNode = node.get(PARENT_NODE);

						var parentFolderEntryId;

						var tabViewEditor = instance._tabViewEditor;

						if (parentFolderNode.get(ID) == instance._treeViewEditor.get(ID)) {
							parentFolderEntryId = instance.get(ROOT_FOLDER_ID);
						}
						else {
							parentFolderEntryId = parentFolderNode.get(ENTRY_ID);
						}

						var callback = function(data) {
							var dataError = data.error;

							editable.fire('stopEditing', !dataError);

							if (dataError) {
								instance._showErrorDialog(dataError);

								parentFolderNode.removeChild(node);
							}
							else {
								if (node.isLeaf()) {
									var fileEntryId = data.fileEntryId;

									node.setAttrs(
										{
											entryId: fileEntryId,
											fileEntryLoaded: true,
											fileEntryURL: data.fileEntryURL,
											isNewEntry: false,
											label: label
										}
									);

									var tab = tabViewEditor.get(ACTIVE_TAB);

									tab.setAttrs(
										{
											entryId: fileEntryId,
											isDirty: false,
											isNew: false,
											label: label
										}
									);

									instance._addEntryToDataSet(tab);

									tab.updateEditorMode();
								}
								else {
									node.setAttrs(
										{
											entryId: data.folderId,
											isNewEntry: false,
											label: label
										}
									);
								}

								node.sort();

								var lastSelected = instance._treeViewEditor.get(LAST_SELECTED);

								if (lastSelected) {
									lastSelected.unselect();
								}

								node.select();
							}
						};

						if (node.isLeaf()) {
							instance._requestAddFileEntry(label, tabViewEditor.get(ACTIVE_TAB).get(EDITOR).getValue(), parentFolderEntryId, callback);
						}
						else {
							instance._requestAddFolder(label, parentFolderEntryId, callback);
						}
					},

					_sendIORequest: function(name, data, callback) {
						var instance = this;

						var io = instance._getIORequest(name, callback);

						io.set('data', data);

						io.start();

						return io;
					},

					_showConfirmationDialog: function(message, callback) {
						var instance = this;

						var args = arguments;

						var buttons = [
							{
								handler: function(event) {
									if (callback) {
										callback.apply(instance, A.Array(args, 2, true));
									}

									instance._confirmationDialog.close();
								},
								text: 'Yes'
							},
							{
								handler: function(event) {
									instance._confirmationDialog.close();
								},
								text: 'No'
							}
						];

						var confirmationDialog = instance._createDialog('Confirm', message, true, false, buttons).render();

						instance._confirmationDialog = confirmationDialog;
					},

					_showErrorDialog: function(error) {
						var instance = this;

						var bodyContent;

						if (Lang.isString(error)) {
							bodyContent = error;
						}
						else {
							bodyContent = Lang.sub(TPL_ERROR_MESSAGE, error);
						}

						instance._createDialog('Error', bodyContent, true, true, null).render();
					},

					_showPreviewPanel: function() {
						var instance = this;

						var previewResize = instance._previewResize;

						var previewResizeWrapper = previewResize.get(WRAPPER);

						var previewResizeWrapperHeight = previewResizeWrapper.height();

						instance._tabViewEditor.set(HEIGHT, instance._gadgetEditorHeight - previewResizeWrapperHeight);

						previewResizeWrapper.show();

						instance._tabViewEditor.adjustEditorHeight();

						instance._adjustPreviewPanelContentHeight();
					},

					_showSearchDialog: function() {
						var instance = this;

						var form = new A.Form().render();

						var searchField = new A.Textfield(
							{
								labelText: 'Search for:'
							}
						);

						var replaceField = new A.Textfield(
							{
								labelText: 'Replace with:'
							}
						);

						form.add(searchField, true);
						form.add(replaceField, true);

						var buttons = [
							{
								handler: function(event) {
									var tab = instance._tabViewEditor.get(ACTIVE_TAB);

									var searchText = searchField.get(VALUE);

									tab.searchEditorText(searchText, false);
								},
								text: 'Search'
							},
							{
								handler: function(event) {
									var tab = instance._tabViewEditor.get(ACTIVE_TAB);

									var searchText = searchField.get(VALUE);

									var replaceText = replaceField.get(VALUE);

									tab.searchEditorText(searchText, false, replaceText, true);
								},
								text: 'Replace'
							},
							{
								handler: function(event) {
									instance._closeSearchDialog();
								},
								text: 'Close'
							}
						];

						instance._searchDialog = instance._createDialog('Search', form.get(BOUNDING_BOX), false, false, buttons).render();
					},

					_undoContent: function(action) {
						var instance = this;

						var editor = instance._tabViewEditor.get(ACTIVE_TAB).get(EDITOR);

						if (action == 'undo') {
							editor.undo();
						}
						else {
							editor.redo();
						}
					},

					_unpublishGadget: function(node, gadgetId) {
						var instance = this;

						instance._loadingMask.show();

						Liferay.Service.OpenSocial.Gadget.deleteGadget(
							{
								gadgetId: gadgetId,
								serviceContext: '{}'
							},
							function(response) {
								instance._loadingMask.hide();

								if (response.exception) {
									instance._showErrorDialog(response.exception);
								}
								else {
									node.set(GADGET_ID, 0);
								}
							}
						);
					},

					_updateUndoButtons: function() {
						var instance = this;

						var tab = instance._tabViewEditor.get(ACTIVE_TAB);

						var editor = instance._tabViewEditor.get(ACTIVE_TAB).get(EDITOR);

						if (editor) {
							var historySize = editor.historySize();

							instance._redoEditorButton.set(DISABLED, historySize.redo == 0);
							instance._undoContentButton.set(DISABLED, historySize.undo == 0);
						}
					}
				}
			}
		);

		var OpenSocial = Liferay.namespace('OpenSocial');

		OpenSocial.Editor = Editor;
	},
	'',
	{
		requires: ['aui-dialog', 'aui-form', 'aui-loading-mask', 'aui-panel', 'aui-resize', 'aui-toolbar', 'gadget-editor-tabs', 'gadget-editor-tree', 'liferay-open-social-gadget', 'liferay-util-window', 'stylesheet']
	}
);