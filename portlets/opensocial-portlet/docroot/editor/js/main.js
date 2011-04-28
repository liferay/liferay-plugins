AUI().add(
	'opensocial-editor',
	function (A) {
		var Lang = A.Lang;

		var TPL_EDITOR =
			'<div id="editorPortlet">' +
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

		var Editor = A.Component.create(
			{
				NAME: 'gadget-editor',

				ATTRS: {
					gadgetDebug: {},
					gadgetNocache: {
						value: 1
					},
					gadgetPortletId:{},
					gadgetServerBase: {},
					gadgetStore: {
						valueFn: function() {
							return new OpenSocial.Store.Expando();
						}
					},
					gadgetUserPrefs: {
						value: {}
					},
					gadgetView: {
						value: 'default'
					},
					gadgetViewParams: {},
					portalURL: {},
					repositoryId: {},
					resourceURL: {},
					rootFolderId: {}
				},

				prototype: {
					initializer: function() {
						var instance = this;

						A.StyleSheet.register(A.StyleSheet(), 'codeMirror');

						instance._io = {};

						instance._createEvents();

						instance._createIndexDataSet();
					},

					renderUI: function() {
						var instance = this;

						var container = A.Node.create(TPL_EDITOR);

						instance.get('contentBox').appendChild(container);

						instance._container = container;

						instance._gadgetEditorHeight = parseInt(container.one('#gadgetEditorContent').getStyle('height'));

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
						instance.after('tree-node-editor:renameEntry', instance._afterTreeNodeRenameEntry);
						instance.after('tree-node-editor:select', instance._afterTreeNodeSelect);
						instance.after('tree-node-editor:showURL', instance._afterTreeNodeShowURL);

						instance.after('editable-editor:cancel', instance._afterEditableCancel);

						instance.on('editable-editor:save', instance._onEditableSave);

						instance.after('tab-view-editor:activeTabChange', instance._afterTabViewActiveTabChange);

						instance.after('tab-editor:add', instance._afterTabAdd);
						instance.after('tab-editor:close', instance._afterTabClose);
						instance.after('tab-editor:entryIdChange', instance._afterTabEntryIdChange);
						instance.after('tab-editor:isDirtyChange', instance._afterTabIsDirtyChange);
						instance.after('tab-editor:isNewChange', instance._afterTabIsNewChange);

						instance.on('tab-editor:onEditorFocus', instance._onTabViewEditorFocus);
						instance.on('tab-editor:onEditorChange', instance._onTabViewEditorChange);
					},

					syncUI: function() {
						var instance = this;

						instance._tabViewEditor.addNewTab();

						var tabViewEditorBoundingBox = instance._tabViewEditor.get('boundingBox');

						tabViewEditorBoundingBox.setStyles(
							{
								height: '100%',
								position: 'absolute',
								top: 0,
								width: instance._getEditorColumnResizeWidth()
							}
						);

						var previewResizeWrapper = instance._previewResize.get('wrapper');

						previewResizeWrapper.setStyles(
							{
								bottom: 0,
								height: 200,
								position: 'absolute',
								width: instance._getEditorColumnResizeWidth()
							}
						);

						previewResizeWrapper.hide();

						instance._tabViewEditor.adjustEditorHeight();
					},

					_addEntryToDataSet: function(object) {
						var instance = this;

						var entryId = object.get('entryId');

						if (entryId == 0) {
							return;
						}

						var item = null;

						var containsKey = instance._indexDataSet.containsKey(entryId);

						if (containsKey) {
							item = instance._indexDataSet.item(entryId);
						}
						else {
							item = {};
						}

						if (object instanceof A.TreeNode) {
							item.node = object;
						}
						else if (object instanceof A.Tab) {
							item.tab = object;
						}
						else {
							return;
						}

						instance._indexDataSet.add(entryId, item);
					},

					_addNewEntryToTreeNode: function(isLeaf, parentFolderId) {
						var instance = this;

						var isRoot = false;

						var parentFolderNode = instance._treeViewEditor.getNodeById(parentFolderId);

						if (!parentFolderNode) {
							parentFolderNode = instance._treeViewEditor;

							isRoot = true;
						}

						if (isRoot || parentFolderNode.get('loaded')) {
							if (!isRoot) {
								parentFolderNode.expand();
							}

							setTimeout(
								function() {
									var entryLabel = instance._getNextNewEntryName(isLeaf, parentFolderNode);

									instance._treeViewEditor.addNodeToFolder(entryLabel, isLeaf, parentFolderId);
								},
								0
							);

							if (!isRoot) {
								parentFolderNode.detach('loadedChange');
							}
						}
						else {
							parentFolderNode.after(
								'loadedChange',
								function() {
									instance._addNewEntryToTreeNode(isLeaf, parentFolderId);
								}
							);

							parentFolderNode.expand();
						}
					},

					_adjustPreviewPanelContentHeight: function() {
						var instance = this;

						var wrapper = instance._previewResize.get('wrapper');

						var node = instance._previewResize.get('node');

						var previewHeight = parseInt(wrapper.getStyle('height')) - node.getPadding('t');

						var previewPanelHeaderHeight = parseInt(instance._previewPanel.headerNode.get('offsetHeight'));

						var bodyNode = instance._previewPanel.bodyNode;

						bodyNode.setStyle('height', previewHeight - previewPanelHeaderHeight - bodyNode.getPadding('tb'));
					},

					_afterEditableCancel: function(event) {
						var instance = this;

						instance.fire(
							'cancelRenameEntry',
							{
								entryId: event.target.get('entryId')
							}
						);
					},

					_afterTabAdd: function(event) {
						var instance = this;

						instance._addEntryToDataSet(event.target);
					},

					_afterTabClose: function(event) {
						var instance = this;

						instance.fire(
							'closeFileEntry',
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

						instance._saveButton.set('disabled', !event.newVal);
					},

					_afterTabIsNewChange: function(event) {
						var instance = this;

						instance._deleteButton.set('disabled', event.newVal);
					},

					_afterTabViewActiveTabChange: function(event) {
						var instance = this;

						var entryId = event.newVal.get('entryId');

						var node = instance._getNodeFromDataSet(entryId);

						if (node && !node.isSelected()) {
							var lastSelected = instance._treeViewEditor.get('lastSelected');

							if (lastSelected) {
								lastSelected.unselect();
							}

							node.select();

							var parentNode = node.get('parentNode');

							while (parentNode && parentNode instanceof A.TreeNode) {
								parentNode.expand();

								parentNode = parentNode.get('parentNode');
							}
						}

						instance._saveButton.set('disabled', !event.newVal.get('isDirty'));

						instance._deleteButton.set('disabled', event.newVal.get('isNew'));

						if (event.prevVal) {
							event.prevVal.unmarkSearch();
						}

						if (instance._previewButton.StateInteraction.get('active')) {
							instance.fire(
								'renderGadget',
								{
									entryId: entryId,
									noCache: true
								}
							)
						}

						instance._updateUndoButtons();
					},

					_afterTreeNodeAddNewFolder: function(event) {
						var instance = this;

						instance.fire(
							'addFolderNode',
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
							'closeFileEntry',
							{
								entryId: event.entryId
							}
						);
					},

					_afterTreeNodeDeleteEntry: function(event) {
						var instance = this;

						instance.fire(
							'deleteEntry',
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

					_afterTreeNodeRenameEntry: function(event) {
						var instance = this;

						instance.fire(
							'startRenameEntry',
							{
								entryId: event.entryId
							}
						);
					},

					_afterTreeNodeSelect: function(event) {
						var instance = this;

						if (event.target.isLeaf()) {
							instance.fire(
								'loadContent',
								{
									entryId: event.target.get('entryId')
								}
							);
						}
					},

					_afterTreeNodeShowURL: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						var url = instance.get('portalURL') + node.get('fileEntryURL');

						var bodyContent = '"' + node.get('label') + '" URL:<br /><br />' + url;

						instance._createDialog('URL', bodyContent, false, true, null).render();
					},

					_afterTreeViewAppend: function(event) {
						var instance = this;

						instance._addEntryToDataSet(event.tree.node);
					},

					_changeEditorFontSize: function(action) {
						var instance = this;

						if (instance._fontSize === undefined) {
							instance._fontSize = 12;
						}

						if (action == 'increase') {
							instance._fontSize += 2;

							if (instance._fontSize >= 64) {
								instance._increaseEditorFontSizeButton.disable();
							}

							instance._decreaseEditorFontSizeButton.enable();
						}
						else {
							instance._fontSize -= 2;

							if (instance._fontSize <= 2) {
								instance._decreaseEditorFontSizeButton.disable();
							}

							instance._increaseEditorFontSizeButton.enable();
						}

						A.StyleSheet('codeMirror').set(
							'.CodeMirror',
							{
								fontSize: instance._fontSize + 'px'
							}
						);

						instance._tabViewEditor.get('activeTab').get('editor').refresh();
					},

					_closePreviewPanel: function() {
						var instance = this;

						instance._previewResize.get('wrapper').hide();

						instance._tabViewEditor.get('boundingBox').setStyle('height', instance._gadgetEditorHeight + 'px');

						instance._tabViewEditor.adjustEditorHeight();

						if (instance._previewButton.StateInteraction.get('active')) {
							instance._previewButton.StateInteraction.set('active', false);
						}
					},

					_closeSearchDialog: function() {
						var instance = this;

						instance._searchDialog.close();

						var tab = instance._tabViewEditor.get('activeTab');

						tab.unmarkSearch();

						instance._searchEditorButton.StateInteraction.set('active', false);
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
							'addFileEntryNode',
							{
								defaultFn: instance._defAddFileEntryNodeFn
							}
						);

						instance.publish(
							'addFolderNode',
							{
								defaultFn: instance._defAddFolderNodeFn
							}
						);

						instance.publish(
							'cancelRenameEntry',
							{
								defaultFn: instance._defCancelRenameEntryFn
							}
						);

						instance.publish(
							'closeFileEntry',
							{
								defaultFn: instance._defCloseFileEntryFn
							}
						);

						instance.publish(
							'deleteEntry',
							{
								defaultFn: instance._defDeleteEntryFn
							}
						);

						instance.publish(
							'loadContent',
							{
								defaultFn: instance._defLoadContentFn
							}
						);

						instance.publish(
							'renderGadget',
							{
								defaultFn: instance._defRenderGadgetFn
							}
						);

						instance.publish(
							'saveContent',
							{
								defaultFn: instance._defSaveContentFn
							}
						);

						instance.publish(
							'saveRenameEntry',
							{
								defaultFn: instance._defSaveRenameEntryFn
							}
						);

						instance.publish(
							'startRenameEntry',
							{
								defaultFn: instance._defStartRenameEntryFn
							}
						);
					},

					_createIndexDataSet: function() {
						var instance = this;

						instance._indexDataSet = new A.DataSet;
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

						if (node.get('isNewEntry')) {
							node.get('parentNode').removeChild(node);
						}
					},

					_defCloseFileEntryFn: function(event) {
						var instance = this;

						var closeFileEntryFn = function() {
							var tab = instance._getTabFromDataSet(event.entryId);

							if (tab) {
								instance._tabViewEditor.closeTabById(tab.get('id'));
							}

							var node = instance._getNodeFromDataSet(event.entryId);

							if (node) {
								if (node.isSelected()) {
									node.unselect();
								}

								node.set('fileEntryLoaded', false);
							}
						};

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab && tab.get('isDirty') && !event.noConfirm) {
							instance._showConfirmationDialog('"' + tab.get('label') + '" has not been saved. Are you sure you want to close the tab?', closeFileEntryFn);
						}
						else {
							closeFileEntryFn();
						}
					},

					_defDeleteEntryFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						var callback = function() {
							var deleteEntryCallback = function(data) {
								if (data && data.error) {
									instance._showErrorDialog(data.error);

									return;
								}

								if (node.isLeaf()) {
									instance.fire(
										'closeFileEntry',
										{
											entryId: event.entryId,
											noConfirm: true
										}
									);
								}
								else {
									var children = node.getChildren(true);

									A.Array.each(
										children,
										function(value) {
											if (value.isLeaf()) {
												instance.fire(
													'closeFileEntry',
													{
														entryId: value.get('entryId'),
														noConfirm: true
													}
												);
											}
										}
									);
								}

								var parentNode = node.get('parentNode');

								parentNode.removeChild(node);
							};

							if (node.isLeaf()) {
								instance._requestDeleteFileEntry(event.entryId, deleteEntryCallback);
							}
							else {
								instance._requestDeleteFolder(event.entryId, deleteEntryCallback);
							}
						};

						instance._showConfirmationDialog('Are you sure you want to delete "' + node.get('label') + '"?', callback);
					},

					_defLoadContentFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get('fileEntryLoaded')) {
							var tabId = instance._getTabFromDataSet(event.entryId).get('id');

							instance._tabViewEditor.selectTabById(tabId);
						}
						else {
							var callback = function(data) {
								if (data.error) {
									node.unselect();

									instance._showErrorDialog(data.error);

									return;
								}

								var tab = instance._tabViewEditor.addExistingDocument(event.entryId, node.get('label'), data.content);

								node.set('fileEntryLoaded', true);
							};

							instance._requestGetFileEntryContent(node.get('entryId'), callback);
						}
					},

					_defRenderGadgetFn: function(event) {
						var instance = this;

						var tab = instance._getTabFromDataSet(event.entryId);

						var content = tab.get('editor').getValue();

						var callback = function(data) {
							if (data.error) {
								instance._showErrorDialog(data.error);

								return;
							}

							var contentValid = data.contentValid;

							tab.set('contentValid', contentValid);

							if (!contentValid) {
								instance._previewPanel.bodyNode.empty();

								var error = 'The gadget content is not valid.';

								instance._previewPanel.set('bodyContent', error);
							}
							else if (!tab.get('isRendered') || event.noCache) {
								instance._previewPanel.bodyNode.empty();

								new Liferay.OpenSocial.Gadget(
									{
										appId: data.appId,
										content: escape(content),
										debug: instance.get('gadgetDebug'),
										moduleId: data.moduleId,
										nocache: instance.get('gadgetNocache'),
										portletId: instance.get('gadgetPortletId'),
										requiresPubsub: data.requiresPubsub,
										secureToken: data.secureToken,
										serverBase: instance.get('gadgetServerBase'),
										specUrl: data.specUrl,
										store: instance.get('gadgetStore'),
										view: instance.get('gadgetView'),
										viewParams: instance.get('gadgetViewParams')
									}
								).render(instance._previewPanel.bodyNode);

								tab.set('isRendered', true);
							}

							var previewResizeWrapperHeight = parseInt(instance._previewResize.get('wrapper').getStyle('height'));

							instance._tabViewEditor.get('boundingBox').setStyle('height', instance._gadgetEditorHeight - previewResizeWrapperHeight + 'px');

							instance._previewResize.get('wrapper').show();

							instance._tabViewEditor.adjustEditorHeight();

							instance._adjustPreviewPanelContentHeight();
						};

						instance._requestGetRenderParameters(content, callback);
					},

					_defSaveContentFn: function(event) {
						var instance = this;

						var tab = instance._getTabFromDataSet(event.entryId);

						if (tab.get('isNew') && tab.get('isDirty')) {
							var parentFolderId = instance._treeViewEditor.getSelectedFolderId();

							if (parentFolderId == 0) {
								parentFolderId = instance._rootNode.get('id');
							}

							instance._addNewEntryToTreeNode(true, parentFolderId);
						}
						else if (tab.get('isDirty')) {
							var callback = function(data) {
								if (data.error) {
									instance._showErrorDialog(data.error);

									return;
								}

								tab.set('isDirty', false);
							};

							instance._requestUpdateFileEntryContent(event.entryId, tab.get('editor').getValue(), callback);
						}
					},

					_defSaveRenameEntryFn: function(event) {
						var instance = this;

						var node = instance._getNodeFromDataSet(event.entryId);

						if (node.get('isNewEntry')) {
							instance._saveRenameNewEntry(node);
						}
						else {
							instance._saveRenameExistingEntry(node);
						}
					},

					_defStartRenameEntryFn: function(event) {
						var instance = this;

						var editable = instance._getNodeFromDataSet(event.entryId).get('editable');

						instance._visibleEditable = editable;

						editable.fire('startEditing');
					},

					_getEditorColumnResizeWidth: function() {
						var instance = this;

						var mainEditorColumnResize = A.one('#mainEditorColumnResize');

						return mainEditorColumnResize.get('offsetWidth') - mainEditorColumnResize.getPadding('l');
					},

					_getIORequest: function(resourceId, callback) {
						instance = this;

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

						var onSuccess = io.on(
							'success',
							function(event) {
								onSuccess.detach();

								instance._loadingMask.hide();

								if (callback) {
									var message = this.get('responseData');

									callback(message);
								}
							}
						);

						return io;
					},

					_getNextNewEntryName: function(isLeaf, parentNode) {
						var children = parentNode.getChildren();

						var label;

						if (isLeaf) {
							label = 'Untitled.xml';
						}
						else {
							label = 'New Folder';
						}

						var newUntitledCounter = 0;

						var isDuplicateLabel = false;

						do {
							isDuplicateLabel = A.Array.some(
								children,
								function(value) {
									if (value.isLeaf() == isLeaf && value.get('label').toLowerCase() == label.toLowerCase()) {
										newUntitledCounter++;

										if (isLeaf) {
											label = 'Untitled' + newUntitledCounter + '.xml';
										}
										else {
											label = 'New Folder' + newUntitledCounter;
										}

										return true;
									}
								}
							);
						}
						while (isDuplicateLabel)

						return label;
					},

					_getNodeFromDataSet: function(entryId) {
						var instance = this;

						if (entryId == 0) {
							return null;
						}

						var item = instance._indexDataSet.item(entryId);

						return item && item.node;
					},

					_getResourceURL: function(id) {
						var instance = this;

						var resourceURL = instance.get('resourceURL');

						resourceURL += ('&p_p_resource_id=' + id);

						return resourceURL;
					},

					_getTabFromDataSet: function(entryId) {
						var instance = this;

						if (entryId == 0) {
							return null;
						}

						var item = instance._indexDataSet.item(entryId);

						return item && item.tab;
					},

					_onEditableSave: function(event) {
						var instance = this;

						event.preventDefault();

						instance.fire(
							'saveRenameEntry',
							{
								entryId: event.target.get('entryId')
							}
						);
					},

					_onTabViewEditorChange: function(event) {
						var instance = this;

						instance._updateUndoButtons();
					},

					_onTabViewEditorFocus: function(event) {
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

						var gadgetEditorWidth = parseInt(A.one('#gadgetEditorContent').getStyle('width'));

						var mainEditorColumnResize = new A.Resize(
							{
								after: {
									end: function(event) {
										instance._tabViewEditor.get('boundingBox').setStyle('width', instance._getEditorColumnResizeWidth());

										instance._previewResize.get('wrapper').setStyle('width', instance._getEditorColumnResizeWidth());

										instance._previewPanel.get('boundingBox').setStyle('width', instance._getEditorColumnResizeWidth());

										instance._tabViewEditor.adjustEditorHeight();
									}
								},
								handles: 'l',
								node: A.one('#mainEditorColumnResize'),
								on: {
									end: function(event) {
										var treeViewEditorColumn = A.one('#treeViewEditorColumn');

										treeViewEditorColumn.setStyle('width', (gadgetEditorWidth - event.target.info.offsetWidth) + 'px');
									}
								},
								proxy: true,
								wrap: true
							}
						);

						mainEditorColumnResize.get('proxyEl').addClass('main-editor-column-resize-proxy');

						mainEditorColumnResize.get('wrapper').setStyle('height', '100%');

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
									if (!event.target.get('disabled')) {
										var entryId = instance._tabViewEditor.get('activeTab').get('entryId');

										instance.fire(
											'renderGadget',
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

						instance._previewPanelRefreshButton = previewPanelRefreshButton;

						var previewPanelCloseButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get('disabled')) {
										instance._closePreviewPanel();
									}
								},
								icon: 'close'
							}
						);

						instance._previewPanelCloseButton = previewPanelCloseButton;

						var previewPanel = new A.Panel(
							{
								headerContent: 'Preview',
								icons: [
									previewPanelRefreshButton,
									previewPanelCloseButton
								],
								boundingBox: A.one('#previewPanel'),
							}
						).render();

						instance._previewPanel = previewPanel;
					},

					_renderPreviewPanelResize: function() {
						var instance = this;

						var previewResize = new A.Resize(
							{
								handles: 't',
								node: A.one('#previewPanel'),
								after: {
									end: function(event) {
										var height = parseInt(event.target.info.offsetHeight);

										instance._tabViewEditor.get('boundingBox').setStyle('height', instance._gadgetEditorHeight - height + 'px');

										instance._tabViewEditor.adjustEditorHeight();

										previewResize.get('wrapper').setStyle('height', height + 'px');

										instance._adjustPreviewPanelContentHeight();
									}
								},
								proxy: true,
								wrap: true
							}
						);

						previewResize.get('proxyEl').addClass('preview-panel-resize-proxy');

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
								boundingBox: A.one('#tabViewEditor')
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
									if (!event.target.get('disabled')) {
										instance.fire('addFileEntryNode');
									}
								},
								icon: 'gadgeteditor-addfile'
							}
						);

						instance._newFileEntryButton = newFileEntryButton;

						var newFolderButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get('disabled')) {
										var parentFolderId = instance._treeViewEditor.getSelectedFolderId();

										if (parentFolderId == 0) {
											parentFolderId = instance._rootNode.get('id');
										}

										instance.fire(
											'addFolderNode',
											{
												parentFolderId: parentFolderId
											}
										);
									}
								},
								icon: 'gadgeteditor-addfolder'
							}
						);

						instance._newFolderButton = newFolderButton;

						var saveButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										var entryId = instance._tabViewEditor.get('activeTab').get('entryId');

										instance.fire(
											'saveContent',
											{
												entryId: entryId
											}
										);
									}
								},
								icon: 'gadgeteditor-disk'
							}
						);

						instance._saveButton = saveButton;

						var deleteButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										var entryId = instance._tabViewEditor.get('activeTab').get('entryId');

										instance.fire(
											'deleteEntry',
											{
												entryId: entryId
											}
										);
									}
								},
								icon: 'gadgeteditor-delete'
							}
						);

						instance._deleteButton = deleteButton;

						var previewButton = new A.ButtonItem(
							{
								activeState: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										if (event.target.StateInteraction.get('active')) {
											var entryId = instance._tabViewEditor.get('activeTab').get('entryId');

											instance.fire(
												'renderGadget',
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

						instance._previewButton = previewButton;

						var increaseFontSizeButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get('disabled')) {
										instance._changeEditorFontSize('increase');
									}
								},
								icon: 'zoomin',
							}
						);

						instance._increaseEditorFontSizeButton = increaseFontSizeButton;

						var decreaseFontSizeButton = new A.ButtonItem(
							{
								handler: function(event) {
									if (!event.target.get('disabled')) {
										instance._changeEditorFontSize('decrease');
									}
								},
								icon: 'zoomout',
							}
						);

						instance._decreaseEditorFontSizeButton = decreaseFontSizeButton;

						var undoEditorButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										instance._undoContent('undo');
									}
								},
								icon: 'arrowrefresh-1-l'
							}
						);

						instance._undoContentButton = undoEditorButton;

						var redoEditorButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										instance._undoContent('redo');
									}
								},
								icon: 'arrowrefresh-1-r'
							}
						);

						instance._redoEditorButton = redoEditorButton;

						var searchEditorButton = new A.ButtonItem(
							{
								activeState: true,
								handler: function(event) {
									if (!event.target.get('disabled')) {
										if (event.target.StateInteraction.get('active')) {
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

						instance._searchEditorButton = searchEditorButton;

						new A.Toolbar(
							{
								boundingBox: A.one('#gadgetEditorToolbar'),
								children: [
									newFileEntryButton,
									newFolderButton,
									saveButton,
									deleteButton,
									{
										type: 'ToolbarSpacer'
									},
									previewButton,
									{
										type: 'ToolbarSpacer'
									},
									increaseFontSizeButton,
									decreaseFontSizeButton,
									{
										type: 'ToolbarSpacer'
									},
									undoEditorButton,
									redoEditorButton,
									{
										type: 'ToolbarSpacer'
									},
									searchEditorButton
								],
							}
						).render();
					},

					_renderTreeViewEditor: function() {
						var instance = this;

						var callback = function(data) {
							A.Array.each(
								data,
								function(value) {
									var node = new A.TreeNodeEditor(
										{
											entryId: value.entryId,
											label: value.label,
											leaf: value.leaf,
											type: value.type
										}
									);

									instance._treeViewEditor.appendChild(node);
								}
							);

							instance._treeViewEditor.sortChildren();
						};

						var children = instance._requestGetFolderChildren(instance.get('rootFolderId'), instance.get('repositoryId'), true, callback)

						var treeViewEditor = new A.TreeViewEditor(
							{
								boundingBox: A.one('#treeViewEditor'),
								io: {
									cfg: {
										data: function(node) {
											return {
												folderId: node.get('entryId'),
												getFileEntries: true,
												repositoryId: instance.get('repositoryId')
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
									url: instance._getResourceURL('getFolderChildren')
								},
								rootFolderId: instance.get('rootFolderId')
							}
						).render();

						treeViewEditor.addTarget(instance);

						instance._treeViewEditor = treeViewEditor;
					},

					_requestAddFileEntry: function(name, content, folderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('addFileEntry', callback);

						io.setAttrs(
							{
								data: {
									content: content,
									fileEntryTitle: name,
									folderId: folderId
								}
							}
						);

						io.start();
					},

					_requestAddFolder: function(name, parentFolderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('addFolder', callback);

						io.setAttrs(
							{
								data: {
									folderName: name,
									parentFolderId: parentFolderId
								}
							}
						);

						io.start();
					},

					_requestDeleteFileEntry: function(fileEntryId, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('deleteFileEntry', callback);

						io.setAttrs(
							{
								data: {
									fileEntryId: fileEntryId
								}
							}
						);

						io.start();
					},

					_requestDeleteFolder: function(folderId, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('deleteFolder', callback);

						io.setAttrs(
							{
								data: {
									folderId: folderId
								}
							}
						);

						io.start();
					},

					_requestGetFileEntryContent: function(fileEntryId, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('getFileEntryContent', callback);

						io.setAttrs(
							{
								data: {
									fileEntryId: fileEntryId
								}
							}
						);

						io.start();
					},

					_requestGetFolderChildren: function(folderId, repositoryId, getFileEntries, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('getFolderChildren', callback);

						io.setAttrs(
							{
								data: {
									folderId: folderId,
									getFileEntries: getFileEntries,
									repositoryId: repositoryId
								}
							}
						);

						io.start();
					},

					_requestGetRenderParameters: function(content, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('getRenderParameters', callback);

						io.setAttrs(
							{
								data: {
									content: content
								}
							}
						);

						io.start();
					},

					_requestUpdateFileEntryContent: function(fileEntryId, content, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('updateFileEntryContent', callback);

						io.setAttrs(
							{
								data: {
									content: content,
									fileEntryId: fileEntryId
								}
							}
						);

						io.start();
					},

					_requestUpdateFileEntryTitle: function(fileEntryId, title, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('updateFileEntryTitle', callback);

						io.setAttrs(
							{
								data: {
									fileEntryId: fileEntryId,
									fileEntryTitle: title
								}
							}
						);

						io.start();
					},

					_requestUpdateFolderName: function(folderId, name, callback) {
						var instance = this;

						instance._loadingMask.show();

						var io = instance._getIORequest('updateFolderName', callback);

						io.setAttrs(
							{
								data: {
									folderId: folderId,
									folderName: name
								}
							}
						);

						io.start();
					},

					_showConfirmationDialog: function(message, callback) {
						var instance = this;

						var buttons = [
							{
								handler: function(event) {
									if (callback) {
										callback();
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

						if (A.Lang.isString(error)) {
							bodyContent = error;
						}
						else {
							bodyContent = error.name + '<br /><br />' + error.message;
						}

						instance._createDialog('Error', bodyContent, true, true, null).render();
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
									var tab = instance._tabViewEditor.get('activeTab');

									var searchText = searchField.get('value');

									tab.searchEditorText(searchText, false);
								},
								text: 'Search'
							},
							{
								handler: function(event) {
									var tab = instance._tabViewEditor.get('activeTab');

									var searchText = searchField.get('value');

									var replaceText = replaceField.get('value');

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

						var _searchDialog = instance._createDialog('Search', form.get('boundingBox'), false, false, buttons).render();

						instance._searchDialog = _searchDialog;
					},

					_saveRenameExistingEntry: function(node) {
						var instance = this;

						var editable = node.get('editable');

						var label = editable.inputNode.get('value');

						var entryId = node.get('entryId');

						var callback = function(data) {
							editable.fire('stopEditing', !data.error);

							if (data.error) {
								instance._showErrorDialog(data.error);

								return;
							}

							node.set('label', label);

							node.sort();

							if (node.isLeaf()) {
								var tab = instance._getTabFromDataSet(entryId);

								if (tab) {
									tab.set('label', label);
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

						var editable = node.get('editable');

						var label = editable.inputNode.get('value');

						var parentFolderNode = node.get('parentNode');

						var parentFolderEntryId;

						if (parentFolderNode.get('id') == instance._treeViewEditor.get('id')) {
							parentFolderEntryId = instance.get('rootFolderId');
						}
						else {
							parentFolderEntryId = parentFolderNode.get('entryId');
						}

						var callback = function(data) {
							editable.fire('stopEditing', !data.error);

							if (data.error) {
								instance._showErrorDialog(data.error);

								parentFolderNode.removeChild(node);

								return;
							}

							if (node.isLeaf()) {
								node.setAttrs(
									{
										entryId: data.fileEntryId,
										fileEntryLoaded: true,
										fileEntryURL: data.fileEntryURL,
										isNewEntry: false,
										label: label
									}
								);

								var tab = instance._tabViewEditor.get('activeTab');
								tab.setAttrs(
									{
										entryId: data.fileEntryId,
										isDirty: false,
										isNew: false,
										label: label
									}
								);

								instance._addEntryToDataSet(tab);
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
						};

						if (node.isLeaf()) {
							instance._requestAddFileEntry(label, instance._tabViewEditor.get('activeTab').get('editor').getValue(), parentFolderEntryId, callback);
						}
						else {
							instance._requestAddFolder(label, parentFolderEntryId, callback);
						}
					},

					_undoContent: function(action) {
						var instance = this;

						var editor = instance._tabViewEditor.get('activeTab').get('editor');

						if (action == 'undo') {
							editor.undo();
						}
						else {
							editor.redo();
						}
					},

					_updateUndoButtons: function() {
						var instance = this;

						var tab = instance._tabViewEditor.get('activeTab');

						var editor = instance._tabViewEditor.get('activeTab').get('editor');

						if (!editor) {
							return;
						}

						var historySize = editor.historySize();

						instance._redoEditorButton.set('disabled', historySize.redo == 0);

						instance._undoContentButton.set('disabled', historySize.undo == 0);
					}
				}
			}
		);

		var OpenSocial = Liferay.namespace('OpenSocial');

		OpenSocial.Editor = Editor;
	},
	'',
	{
		requires: [ 'gadget-editor-tree','gadget-editor-tabs','aui-toolbar','aui-loading-mask','aui-dialog','aui-resize','aui-panel','stylesheet','liferay-open-social-gadget' ]
	}
);