AUI.add(
	'opensocial-editor',
	function(A) {
		var Lang = A.Lang;

		var AArray = A.Array;

		var ACE_EDITOR = 'aceEditor';

		var BODY_CONTENT = 'bodyContent';

		var BOUNDING_BOX = 'boundingBox';

		var CONTENT_BOX = 'contentBox';

		var DISABLED = 'disabled';

		var EDITABLE = 'editable';

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

		var ID = 'id';

		var INCREASE = 'increase';

		var IS_DIRTY = 'isDirty';

		var IS_NEW = 'isNew';

		var IS_NEW_ENTRY = 'isNewEntry';

		var LABEL = 'label';

		var LAST_SELECTED = 'lastSelected';

		var LOADED_CHANGE = 'loadedChange';

		var NEW_FOLDER = 'New Folder';

		var PARENT_NODE = 'parentNode';

		var REPOSITORY_ID = 'repositoryId';

		var ROOT_FOLDER_ID = 'rootFolderId';

		var STR_EMPTY = '';

		var TPL_EDITOR = '<div id="editorPortlet">' +
			'<div id="gadgetEditorToolbar"></div>' +
			'<hr />' +
			'<div class="gadget-editor-content" id="gadgetEditorContent">' +
				'<div class="tree-view-editor-column" id="treeViewEditorColumn">' +
					'<div id="treeViewEditor"></div>' +
				'</div>' +
				'<div class="main-editor-column" id="mainEditorColumn">' +
					'<div id="tabViewEditor"></div>' +
				'</div>' +
			'</div>' +
		'</div>';

		var TPL_ERROR_MESSAGE = '{name}<br /><br />{message}';

		var TPL_URL_DISPLAY = '"{0}" URL:<br /><br /> {1}';

		var UNTITLED = 'Untitled';

		var XML = '.xml';

		var UNTITLED_XML = UNTITLED + XML;

		var VALUE = 'value';

		var Editor = A.Component.create(
			{
				ATTRS: {
					baseRenderURL: {
						validator: Lang.isString
					},

					editorGadgetURL: {},

					gadgetPortletId: {},

					gadgetServerBase: {},

					publishGadgetPermission: {},

					repositoryId: {},

					resourceURL: {},

					rootFolderId: {}
				},

				AUGMENTS: [Liferay.PortletBase],

				NAME: 'gadget-editor',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._io = {};

						instance._createEvents();
						instance._createIndexMap();
					},

					renderUI: function() {
						var instance = this;

						var container = A.Node.create(TPL_EDITOR);

						instance.get(CONTENT_BOX).appendChild(container);

						instance._container = container;

						instance._gadgetEditorHeight = container.one('#gadgetEditorContent').height();

						instance._renderLoadingMask();
						instance._renderToolbar();
						instance._renderTreeViewEditor();
						instance._renderTabViewEditor();
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

						instance.after('tab-view-editor:selectionChange', instance._afterSelectionChange);

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
					},

					_addEntryToMap: function(object) {
						var instance = this;

						var entryId = object.get(ENTRY_ID);

						if (entryId != STR_EMPTY) {
							var item = {};

							var containsKey = instance._indexMap.has(entryId);

							if (containsKey) {
								item = instance._indexMap.getValue(entryId);
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
								instance._indexMap.put(entryId, item);
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

									instance._treeViewEditor.addNewNodeToFolder(entryLabel, isLeaf, parentFolderId);
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

						instance._publishGadgetDialog.destroy();
					},

					_afterSelectionChange: function(event) {
						var instance = this;

						var activeTab = event.newVal;
						var previousTab = event.prevVal;

						var entryId = activeTab.get(ENTRY_ID);

						var node = instance._getNodeFromDataSet(entryId);

						if (activeTab.get(IS_NEW)) {
							var lastSelected = instance._treeViewEditor.get(LAST_SELECTED);

							if (lastSelected && lastSelected.isLeaf()) {
								lastSelected.unselect();

								var folderNode = lastSelected.get(PARENT_NODE);

								folderNode.select();
							}
						}
						else if (node && !node.isSelected()) {
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
					},

					_afterTabAdd: function(event) {
						var instance = this;

						instance._addEntryToMap(event.target);
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

						instance._addEntryToMap(event.target);
					},

					_afterTabIsDirtyChange: function(event) {
						var instance = this;

						instance._saveButton.set(DISABLED, !event.newVal);
					},

					_afterTabIsNewChange: function(event) {
						var instance = this;

						instance._deleteButton.set(DISABLED, event.newVal);
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

						instance._addEntryToMap(event.tree.node);
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

						instance._addEntryToMap(event.target);
					},

					_afterTreeNodePublish: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var tab = instance._getTabFromDataSet(entryId);

						if (tab && tab.get(IS_DIRTY)) {
							instance._showErrorDialog(Liferay.Language.get('save-the-gadget-before-publishing'));
						}
						else {
							var node = instance._getNodeFromDataSet(entryId);

							var uri = instance.get('editorGadgetURL').replace('editorGadgetURLPlaceholder', node.get(FILE_ENTRY_URL));

							var publishGadgetDialog = Liferay.Util.Window.getWindow(
								{
									cache: false,
									dialog: {
										centered: true,
										destroyOnHide: true,
										id: entryId,
										modal: true,
										width: 700
									},
									dialogIframe: {
										uri: uri
									},
									title: Liferay.Language.get('publish-gadget'),
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

						instance._createDialog('URL', bodyContent);
					},

					_afterTreeNodeUnpublish: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var node = instance._getNodeFromDataSet(entryId);

						var gadgetId = node.get(GADGET_ID);

						var message = Liferay.Language.get('are-you-sure-you-want-to-unpublish-the-gadget', node.get(LABEL));

						instance._showConfirmationDialog(message, instance._unpublishGadget, node, gadgetId);
					},

					_afterTreeViewAppend: function(event) {
						var instance = this;

						instance._addEntryToMap(event.tree.node);
					},

					_appendEditorChildren: function(data) {
						var instance = this;

						if (data.error) {
							instance._showErrorDialog(data.error);
						}
						else {
							var treeViewEditor = instance._treeViewEditor;

							AArray.each(
								data,
								function(item, index) {
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

						var aceEditor = instance._tabViewEditor.getSelectedTab().get(ACE_EDITOR).getEditor();

						var fontSize = aceEditor.getFontSize();

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

						aceEditor.setFontSize(fontSize);
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

					_closeSearchDialog: function() {
						var instance = this;

						instance._searchDialog.destroy();

						var tab = instance._tabViewEditor.getSelectedTab();

						instance._searchEditorButton.toggle();
					},

					_createDialog: function(title, bodyContent, options) {
						var instance = this;

						var dialog = {
							bodyContent: bodyContent,
							centered: true,
							height: 250,
							modal: true,
							width: 400
						};

						if (options) {
							if ('buttons' in options) {
								dialog['toolbars.footer'] = options['buttons'];
							}

							if ('centered' in options) {
								dialog.centered = options['centered'];
							}

							if ('height' in options) {
								dialog.height = options['height'];
							}

							if ('modal' in options) {
								dialog.modal = options['modal'];
							}

							if ('width' in options) {
								dialog.width = options['width'];
							}
						}

						return Liferay.Util.Window.getWindow(
							{
								dialog: dialog,
								title: title
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

					_createIndexMap: function() {
						var instance = this;

						instance._indexMap = new A.Map();
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
							var tabFileName =  tab.get('fileName');
							var message = Liferay.Language.get('has-not-been-saved-are-you-sure-you-want-to-close-the-tab', tabFileName);

							instance._showConfirmationDialog(message, instance._closeFileEntry, entryId);
						}
						else {
							instance._closeFileEntry(entryId);
						}
					},

					_defDeleteEntryFn: function(event) {
						var instance = this;

						var entryId = event.entryId;

						var unpublish = STR_EMPTY;

						var node = instance._getNodeFromDataSet(entryId);

						var message;

						if (node.get(GADGET_ID) > 0) {
							message = Liferay.Language.get('are-you-sure-you-want-to-unpublish-and-delete', node.get(LABEL));
						}
						else {
							message = Liferay.Language.get('are-you-sure-you-want-to-delete', node.get(LABEL));
						}

						instance._showConfirmationDialog(message, instance._deleteEntry, node, entryId);
					},

					_defLoadContentFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get(FILE_ENTRY_LOADED)) {
							if (event.entryId != instance._tabViewEditor.getSelectedTab().get(ENTRY_ID)) {
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

						var previewDialog = Liferay.Util.Window.getWindow(
							{
								dialog: {
									centered: true,
									destroyOnHide: true,
									height: 400,
									modal: true,
									width: 600
								},
								title: 'Preview'
							}
						);

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab.get(IS_DIRTY) || tab.get(IS_NEW)) {
							var message = Liferay.Language.get('save-the-gadget-before-previewing');

							previewDialog.set(BODY_CONTENT, message);

							return;
						}

						var node = instance._getNodeFromDataSet(event.entryId);

						var fileEntryURL = node.get(FILE_ENTRY_URL);

						var callback = function(data) {
							if (data.error) {
								var error;

								if (data.error.name == 'ProcessingException') {
									error = data.error.message;
								}
								else {
									error = data.error;
								}

								previewDialog.set(BODY_CONTENT, error);
							}
							else {
								new Liferay.OpenSocial.Gadget(
									{
										appId: fileEntryURL,
										baseRenderURL: instance.get('baseRenderURL'),
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
								).render(previewDialog.bodyNode);
							}
						};

						instance._requestGetRenderParameters(fileEntryURL, callback);
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

							instance._requestUpdateFileEntryContent(event.entryId, tab.get(ACE_EDITOR).getEditor().getValue(), callback);
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

									AArray.each(
										children,
										function(item, index) {
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

											var message = Liferay.Language.get('request-for-resource-id-failed', resourceId);

											instance._showErrorDialog(message);
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
							duplicateLabel = AArray.some(
								children,
								function(item, index) {
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

						if (entryId != STR_EMPTY) {
							var item = instance._indexMap.getValue(entryId);

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
							var item = instance._indexMap.getValue(entryId);

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

						instance._indexMap.remove(entryId);
					},

					_renderLoadingMask: function() {
						var instance = this;

						var loadingMask = A.one('#editorPortlet').plug(
						   A.LoadingMask,
						   {
							  background: 'none',
							  strings: {
								 loading: Liferay.Language.get('busy')
							  }
						   }
						).loadingmask;

						instance._loadingMask = loadingMask;
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

						var newFileEntryButton = new A.Button(
							{
								icon: 'icon-file',
								on: {
									click: function(event) {
										instance.fire(EVENT_ADD_FILE_ENTRY_NODE);
									}
								}
							}
						);

						var newFolderButton = new A.Button(
							{
								icon: 'icon-folder-close',
								on: {
									click: function(event) {
										var parentFolderId = instance._treeViewEditor.getSelectedFolderId();

										if (parentFolderId == 0) {
											parentFolderId = instance._treeViewEditor.getChildren(false)[0].get(ID);
										}

										instance.fire(
											EVENT_ADD_FOLDER_NODE,
											{
												parentFolderId: parentFolderId
											}
										);
									}
								}
							}
						);

						var saveButton = new A.Button(
							{
								disabled: true,
								icon: 'icon-save',
								on: {
									click: function(event) {
										var entryId = instance._tabViewEditor.getSelectedTab().get(ENTRY_ID);

										instance.fire(
											EVENT_SAVE_CONTENT,
											{
												entryId: entryId
											}
										);
									}
								}
							}
						);

						var deleteButton = new A.Button(
							{
								disabled: true,
								icon: 'icon-remove',
								on: {
									click: function(event) {
										var entryId = instance._tabViewEditor.getSelectedTab().get(ENTRY_ID);

										instance.fire(
											EVENT_DELETE_ENTRY,
											{
												entryId: entryId
											}
										);
									}
								}
							}
						);

						var previewButton = new A.Button(
							{
								icon: 'icon-eye-open',
								label: Liferay.Language.get('preview'),
								on: {
									click: function(event) {
										var entryId = instance._tabViewEditor.getSelectedTab().get(ENTRY_ID);

										instance.fire(
											EVENT_RENDER_GADGET,
											{
												entryId: entryId
											}
										);
									}
								}
							}
						);

						var increaseFontSizeButton = new A.Button(
							{
								icon: 'icon-zoom-in',
								on: {
									click: function(event) {
										instance._changeEditorFontSize(INCREASE);
									}
								}
							}
						);

						var decreaseFontSizeButton = new A.Button(
							{
								icon: 'icon-zoom-out',
								on: {
									click: function(event) {
										instance._changeEditorFontSize('decrease');
									}
								}
							}
						);

						var searchEditorButton = new A.ToggleButton(
							{
								activeState: true,
								icon: 'icon-search',
								label: Liferay.Language.get('search'),
								on: {
									click: function(event) {
										if (event.target.get('pressed')) {
											instance._showSearchDialog();
										}
										else {
											instance._closeSearchDialog();

											event.target.toggle();
										}
									}
								}
							}
						);

						var editorToolbar = new A.Toolbar(
							{
								boundingBox: '#gadgetEditorToolbar',
								children: [
									[
										newFileEntryButton,
										newFolderButton,
										saveButton,
										deleteButton
									],
									[
										previewButton
									],
									[
										increaseFontSizeButton,
										decreaseFontSizeButton
									],
									[
										searchEditorButton
									]
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
						instance._searchEditorButton = searchEditorButton;

						instance._editorToolbar = editorToolbar;
					},

					_renderTreeViewEditor: function() {
						var instance = this;

						var treeViewEditor = new A.TreeViewEditor(
							{
								boundingBox: '#treeViewEditor',
								io: {
									cfg: {
										data: function(node) {
											return instance.ns(
												{
													folderId: node.get(ENTRY_ID),
													getFileEntries: true,
													p_auth: Liferay.authToken,
													repositoryId: instance.get(REPOSITORY_ID)
												}
											);
										},
										on: {
											failure: function(event) {
												var message = Liferay.Language.get('unable-to-access-documents');

												instance._showErrorDialog(message);
											},
											success: function(event) {
												this.sortChildren();
											}
										}
									},
									url: instance._getResourceURL(GET_FOLDER_CHILDREN)
								},
								publishGadgetPermission: instance.get('publishGadgetPermission')
							}
						).render();

						var node = treeViewEditor.addRootNode('OpenSocial Gadgets', instance.get(ROOT_FOLDER_ID));

						node.expand();

						node.select();

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

										tab.updateEditorMode(label);
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

									var tab = tabViewEditor.getSelectedTab();

									tab.setAttrs(
										{
											entryId: fileEntryId,
											isDirty: false,
											isNew: false,
											label: label
										}
									);

									instance._addEntryToMap(tab);

									tab.updateEditorMode(label);
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
							instance._requestAddFileEntry(label, tabViewEditor.getSelectedTab().get(ACE_EDITOR).getEditor().getValue(), parentFolderEntryId, callback);
						}
						else {
							instance._requestAddFolder(label, parentFolderEntryId, callback);
						}
					},

					_sendIORequest: function(name, data, callback) {
						var instance = this;

						var io = instance._getIORequest(name, callback);

						data = A.merge(
							data,
							{
								p_auth: Liferay.authToken
							}
						);

						io.set('data', instance.ns(data));

						io.start();

						return io;
					},

					_showConfirmationDialog: function(message, callback) {
						var instance = this;

						var args = arguments;

						var buttons = [
							{
								icon: 'icon-ok',
								label: Liferay.Language.get('yes'),
								on: {
									click: function(event) {
										var node = args[2];

										if (A.instanceOf(node, A.TreeNodeEditor)) {
											var parentNode = node.get(PARENT_NODE);

											if (parentNode) {
												parentNode.select();
											}
										}

										if (callback) {
											callback.apply(instance, AArray(args, 2, true));
										}

										instance._confirmationDialog.destroy();
									}
								}
							},
							{
								icon: 'icon-remove',
								label: Liferay.Language.get('no'),
								on: {
									click: function(event) {
										instance._confirmationDialog.destroy();
									}
								}
							}
						];

						var options = {
							buttons: buttons
						};

						var confirmationDialog = instance._createDialog(Liferay.Language.get('confirm'), message, options);

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

						instance._createDialog(Liferay.Language.get('error'), bodyContent);
					},

					_showSearchDialog: function() {
						var instance = this;

						var form = new A.Form().render();

						var searchField = new A.Textfield(
							{
								labelText: Liferay.Language.get('search-for')
							}
						);

						var replaceField = new A.Textfield(
							{
								labelText: Liferay.Language.get('replace-with')
							}
						);

						form.add(searchField, true);
						form.add(replaceField, true);

						var buttons = [
							new A.Button(
								{
									icon: 'icon-search',
									label: Liferay.Language.get('find'),
									on: {
										click: function(event) {
											var tab = instance._tabViewEditor.getSelectedTab();

											var searchText = searchField.get(VALUE);

											tab.searchEditorText(searchText, false);
										}
									}
								}
							),
							new A.Button(
								{
									icon: 'icon-random',
									label: Liferay.Language.get('replace'),
									on: {
										click: function(event) {
											var tab = instance._tabViewEditor.getSelectedTab();

											var searchText = searchField.get(VALUE);

											var replaceText = replaceField.get(VALUE);

											tab.searchEditorText(searchText, false, replaceText, true);
										}
									}
								}
							),
							new A.Button(
								{
									icon: 'icon-remove',
									label: Liferay.Language.get('close'),
									on: {
										click: function(event) {
											instance._closeSearchDialog();
										}
									}
								}
							)
						];

						var options = {
							buttons: buttons,
							height: 300,
							modal: false,
							width: 400
						};

						instance._searchDialog = instance._createDialog('Search', form.get(BOUNDING_BOX), options);
					},

					_unpublishGadget: function(node, gadgetId) {
						var instance = this;

						instance._loadingMask.show();

						Liferay.Service(
							'/opensocial-portlet.gadget/delete-gadget',
							{
								gadgetId: gadgetId
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
					}
				}
			}
		);

		var OpenSocial = Liferay.namespace('OpenSocial');

		OpenSocial.Editor = Editor;
	},
	'',
	{
		requires: ['aui-button-core', 'aui-form-deprecated', 'aui-map', 'aui-panel-deprecated', 'aui-tabs-base', 'aui-toolbar', 'gadget-editor-tabs', 'gadget-editor-tree', 'liferay-open-social-gadget', 'liferay-portlet-base', 'liferay-util-window' ]
	}
);