AUI().add(
	'gadget-editor-tree',
	function(A) {
		var Lang = A.Lang;
		var isString = Lang.isString;
		var isValue = Lang.isValue;

		var AUTO = 'auto';

		var ACTIVE_EDITABLE = 'activeEditable';

		var BOUNDING_BOX = 'boundingBox';

		var CLICK = 'click';

		var CONTENT_BOX = 'contentBox';

		var CSS_CONTEXT_MENU_OPEN = 'gadget-editor-tree-node-contextmenuicon-open';

		var DISABLED = 'disabled';

		var EDITABLE = 'editable';

		var ENTRY_ID = 'entryId';

		var GADGET_ID = 'gadgetId';

		var ID = 'id';

		var IS_ROOT_NODE = 'isRootNode';

		var LABEL = 'label';

		var NEW_NODE = 'newNode';

		var NEW_FOLDER = 'New Folder';

		var OWNER_TREE = 'ownerTree';

		var OVERFLOW = 'overflow';

		var PARENT_NODE = 'parentNode';

		var PERMISSIONS = 'permissions';

		var RENDERED = 'rendered';

		var TPL_ICON_CONTEXT_MENU = '<a href="javascript:;"></a>';

		var VISIBLE = 'visible';

		var TreeViewEditor = A.Component.create(
			{
				EXTENDS: A.TreeView,

				NAME: 'tree-view-editor',

				ATTRS: {
					activeEditable: {},

					publishGadgetPermission: {},

					treeActionOverlayManager: {
						valueFn: function() {
							return new A.OverlayManager();
						}
					}
				},

				prototype: {
					addNewNodeToFolder: function(label, isLeaf, parentId) {
						var instance = this;

						var node = new TreeNodeEditor(
							{
								entryId: NEW_NODE + label,
								fileEntryLoaded: true,
								isNewEntry: true,
								label: label,
								leaf: isLeaf
							}
						);

						if (instance.get(ID) == parentId) {
							instance.appendChild(node);
						}
						else {
							instance.getNodeById(parentId).appendChild(node);
						}

						node.sort();

						node.get(EDITABLE).fire('startEditing');

						return node;
					},

					addRootNode: function(label, folderId) {
						var instance = this;

						var node = new TreeNodeEditor(
							{
								entryId: folderId,
								isNewEntry: false,
								isRootNode: true,
								label: label,
								leaf: false
							}
						);

						instance.appendChild(node);

						node.sort();

						return node;
					},

					appendChild: function(node) {
						var instance = this;

						TreeViewEditor.superclass.appendChild.apply(instance, arguments);

						node.addTarget(instance);
					},

					getSelectedFolderId: function() {
						var instance = this;

						var folderId = 0;

						var lastSelected = instance.get('lastSelected');

						if (lastSelected && !lastSelected.isLeaf() && lastSelected.isSelected()) {
							folderId = lastSelected.get(ID);
						}

						return folderId;
					},

					sortChildren: function() {
						var instance = this;

						var children = instance.getChildren();

						var folderChildren = [];
						var fileEntryChildren = [];

						A.Array.each(
							children,
							function(item, index, collection) {
								if (item.isLeaf()) {
									fileEntryChildren.push(item);
								}
								else {
									folderChildren.push(item);
								}
							}
						);

						folderChildren.sort(arraySort);
						fileEntryChildren.sort(arraySort);

						A.Array.each(
							folderChildren,
							function(item, index, collection) {
								if (index != 0) {
									instance.insertAfter(item, collection[index-1]);
								}
							}
						);

						A.Array.each(
							fileEntryChildren,
							function(item, index, collection) {
								if (index == 0) {
									if (folderChildren.length > 0) {
										instance.insertAfter(item, folderChildren[folderChildren.length - 1]);
									}
								}
								else {
									instance.insertAfter(item, collection[index-1]);
								}
							}
						);
					}
				}
			}
		);

		A.TreeViewEditor = TreeViewEditor;

		var TreeNodeEditor = A.Component.create(
			{
				EXTENDS: A.TreeNodeIO,

				NAME: 'tree-node-editor',

				ATTRS: {
					editable: {},

					entryId: {
						value: '',
						setter: function(v) {
							return String(v);
						}
					},

					fileEntryLoaded: {
						value: false
					},

					fileEntryURL: {
						value: ''
					},

					gadgetId: {
						value: 0
					},

					isNewEntry: {
						value: false
					},

					isRootNode: {
						value: false
					},

					permissions: {
						value: {}
					}
				},

				prototype: {
					bindUI: function() {
						var instance = this;

						TreeNodeEditor.superclass.bindUI.apply(this, arguments);

						instance.after('fileEntryLoadedChange', instance._afterFileEntryLoadedChange);
						instance.after('gadgetIdChange', instance._afterGadgetIdChange);
						instance.after('labelChange', instance._afterLabelChange);
						instance.after('permissionsChange', instance._afterPermissionsChange);

						instance.on('entryIdChange', instance._onEntryIdChange);
					},

					renderUI: function() {
						var instance = this;

						TreeNodeEditor.superclass.renderUI.apply(this, arguments);

						instance._renderContextMenu();
						instance._renderEditable();
						instance._renderFileEntryLoaded();
					},

					appendChild: function(node) {
						var instance = this;

						TreeNodeEditor.superclass.appendChild.apply(instance, arguments);

						node.addTarget(instance.get(OWNER_TREE));
					},

					sort: function() {
						var instance = this;

						var parentNode = instance.get(PARENT_NODE);

						if (parentNode) {
							var siblings = parentNode.getChildren();

							var filteredSiblings = [];

							var isLeaf = instance.isLeaf();

							var ownerTree = instance.get(OWNER_TREE);

							A.Array.each(
								siblings,
								function(sibling) {
									if (sibling.isLeaf() == isLeaf) {
										filteredSiblings.push(sibling);
									}
								}
							);

							filteredSiblings.sort(arraySort);

							if (filteredSiblings.length == 1) {
								if (siblings.length > 1) {
									if (isLeaf) {
										var lastNode = siblings[siblings.length - 1];

										ownerTree.insertAfter(instance, lastNode);
									}
									else {
										var firstNode = siblings[0];

										ownerTree.insertBefore(instance, firstNode);
									}
								}
							}
							else {
								var nodeIndex = filteredSiblings.indexOf(instance);

								if (nodeIndex > 0) {
									var nodeBefore = filteredSiblings[nodeIndex - 1];

									ownerTree.insertAfter(instance, nodeBefore);
								}
								else {
									var nodeAfter = filteredSiblings[nodeIndex + 1];

									ownerTree.insertBefore(instance, nodeAfter);
								}
							}
						}
					},

					sortChildren: function() {
						var instance = this;

						var children = instance.getChildren();

						var folderChildren = [];
						var fileEntryChildren = [];

						var ownerTree = instance.get(OWNER_TREE);

						A.Array.each(
							children,
							function(item, index, collection) {
								if (item.isLeaf()) {
									fileEntryChildren.push(item);
								}
								else {
									folderChildren.push(item);
								}
							}
						);

						folderChildren.sort(arraySort);
						fileEntryChildren.sort(arraySort);

						A.Array.each(
							folderChildren,
							function(item, index, collection) {
								if (index != 0) {
									ownerTree.insertAfter(item, collection[index-1]);
								}
							}
						);

						A.Array.each(
							fileEntryChildren,
							function(item, index, collection) {
								if (index == 0) {
									if (folderChildren.length > 0) {
										ownerTree.insertAfter(item, folderChildren[folderChildren.length - 1]);
									}
								}
								else {
									ownerTree.insertAfter(item, collection[index-1]);
								}
							}
						);
					},

					_afterFileEntryLoadedChange: function(event) {
						var instance = this;

						instance._renderFileEntryLoaded();
					},

					_afterGadgetIdChange: function(event) {
						var instance = this;

						instance._updatePublishButtons();
					},

					_afterLabelChange: function(event) {
						var instance = this;

						if (instance.isLeaf()) {
							instance._updatePublishButtons();
						}
					},

					_afterPermissionsChange: function(event) {
						var instance = this;

						instance._updatePublishButtons();
					},

					_onEntryIdChange: function(event) {
						var instance = this;

						event.target.get(EDITABLE).set(ENTRY_ID, event.newVal);
					},

					_renderButtonItems: function(isLeaf) {
						var instance = this;

						if (isLeaf) {
							var closeContextMenuButton = new A.ButtonItem(
								{
									disabled: true,
									handler: function(event) {
										var buttonItem = event.target;

										if (!buttonItem.get(DISABLED)) {
											instance.fire(
												'closeFileEntry',
												{
													entryId: instance.get(ENTRY_ID)
												}
											);

											instance._contextMenuOverlay.hide();
										}
									},
									icon: 'gadgeteditor-close',
									label: 'Close'
								}
							);

							var publishMenuButton = new A.ButtonItem(
								{
									disabled: !instance.get(OWNER_TREE).get('publishGadgetPermission'),
									handler: function(event) {
										var buttonItem = event.target;

										if (!buttonItem.get(DISABLED)) {
											instance.fire(
												'publish',
												{
													entryId: instance.get(ENTRY_ID)
												}
											);

											instance._contextMenuOverlay.hide();
										}
									},
									icon: 'gadgeteditor-publish',
									label: 'Publish'
								}
							);

							var showURLContextMenuButton= new A.ButtonItem(
								{
									handler: function(event) {
										var buttonItem = event.target;

										if (!buttonItem.get(DISABLED)) {
											instance.fire(
												'showURL',
												{
													entryId: instance.get(ENTRY_ID)
												}
											);

											instance._contextMenuOverlay.hide();
										}
									},
									icon: 'gadgeteditor-url',
									label: 'Show URL'
								}
							);

							var unpublishMenuButton= new A.ButtonItem(
								{
									handler: function(event) {
										var buttonItem = event.target;

										if (!buttonItem.get(DISABLED)) {
											instance.fire(
												'unpublish',
												{
													entryId: instance.get(ENTRY_ID)
												}
											);

											instance._contextMenuOverlay.hide();
										}
									},
									icon: 'gadgeteditor-publish',
									label: 'Unpublish'
								}
							);

							closeContextMenuButton.addTarget(instance);
							publishMenuButton.addTarget(instance);
							showURLContextMenuButton.addTarget(instance);
							unpublishMenuButton.addTarget(instance);

							instance._closeContextMenuButton = closeContextMenuButton;
							instance._publishMenuButton = publishMenuButton;
							instance._showURLContextMenuButton = showURLContextMenuButton;
							instance._unpublishMenuButton = unpublishMenuButton;
						}
						else {
							var newFolderContextMenuButton = new A.ButtonItem(
								{
									handler: function(event) {
										var buttonItem = event.target;

										if (!buttonItem.get(DISABLED)) {

											instance.fire(
												'addFolderNode',
												{
													parentFolderId: instance.get(ID)
												}
											);

											instance._contextMenuOverlay.hide();
										}
									},
									icon: 'gadgeteditor-addfolder',
									label: NEW_FOLDER
								}
							);

							newFolderContextMenuButton.addTarget(instance);

							instance._newFolderContextMenuButton = newFolderContextMenuButton;
						}

						var deleteContextMenuButton = new A.ButtonItem(
							{
								disabled: instance.get(IS_ROOT_NODE),
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get(DISABLED)) {
										instance.fire(
											'deleteEntry',
											{
												entryId: instance.get(ENTRY_ID)
											}
										);

										instance._contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-delete',
								label: 'Delete'
							}
						);

						var renameContextMenuButton = new A.ButtonItem(
							{
								disabled: instance.get(IS_ROOT_NODE),
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get(DISABLED)) {
										instance.fire(
											'renameEntry',
											{
												entryId: instance.get(ENTRY_ID)
											}
										);

										instance._contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-rename',
								label: 'Rename'
							}
						);

						deleteContextMenuButton.addTarget(instance);
						renameContextMenuButton.addTarget(instance);

						instance._deleteContextMenuButton = deleteContextMenuButton;
						instance._renameContextMenuButton = renameContextMenuButton;
					},

					_renderContextMenu: function() {
						var instance = this;

						var isLeaf = instance.isLeaf();

						instance._renderButtonItems(isLeaf);

						if (isLeaf) {
							instance._renderFileEntryContextMenu();
						}
						else {
							instance._renderFolderContextMenu();
						}

						var contextMenu = instance._contextMenu;

						contextMenu.get(CONTENT_BOX).addClass('gadget-editor-contextmenu-toolbar');

						var contextMenuIcon = A.Node.create(TPL_ICON_CONTEXT_MENU);

						contextMenuIcon.addClass('gadget-editor-tree-node-contextmenuicon');

						instance.get(CONTENT_BOX).append(contextMenuIcon);

						var ownerTree = instance.get(OWNER_TREE);

						var treeActionOverlayManager = ownerTree.get('treeActionOverlayManager');

						var contextMenuOverlay = new A.OverlayContext(
							{
								after: {
									visibleChange: function(event) {
										contextMenuIcon.toggleClass(CSS_CONTEXT_MENU_OPEN, event.target.get(VISIBLE));
									}
								},
								boundingBox: contextMenu.get(BOUNDING_BOX),
								hideOn: CLICK,
								on: {
									show: function(event) {
										var overlayContext = event.target;

										if (!overlayContext.get(RENDERED)) {
											contextMenu.render();

											overlayContext.render();
										}

										treeActionOverlayManager.hideAll();

										var activeEditable = ownerTree.get(ACTIVE_EDITABLE);

										if (activeEditable) {
											activeEditable.fire('stopEditing', true);
										}
									}
								},
								showOn: CLICK,
								trigger: contextMenuIcon
							}
						);

						treeActionOverlayManager.register(contextMenuOverlay);

						instance._contextMenuOverlay = contextMenuOverlay;
					},

					_renderEditable: function() {
						var instance = this;

						var ownerTree = instance.get(OWNER_TREE);

						var ownerTreeBoundingBox = ownerTree.get(BOUNDING_BOX);

						var editable = new EditableEditor(
							{
								eventType: '',
								entryId: instance.get(ENTRY_ID),
								node: instance.get('labelEl'),
								after: {
									startEditing: function(event) {
										ownerTree.set(ACTIVE_EDITABLE, event.target);
									},
									stopEditing: function(event) {
										ownerTree.set(ACTIVE_EDITABLE, null);
									}
								},
								on: {
									startEditing: function(event) {
										var editable = event.target;

										if (!editable.get(RENDERED)) {
											editable.render();
										}

										var region = ownerTreeBoundingBox.get('region');

										var editableY = editable.get('node').getXY()[1];

										if (editableY >= region.bottom) {
											ownerTreeBoundingBox.set('scrollTop', editableY);
										}

										ownerTreeBoundingBox.setStyle(OVERFLOW, 'hidden');
									},
									stopEditing: function(event) {
										ownerTreeBoundingBox.setStyle(OVERFLOW, AUTO);
									}
								}
							}
						);

						editable.addTarget(instance);

						instance.set(EDITABLE, editable);
					},

					_renderFileEntryContextMenu: function() {
						var instance = this;

						instance._updatePublishButtons();

						var children = [
							instance._closeContextMenuButton,
							instance._renameContextMenuButton,
							instance._deleteContextMenuButton,
							instance._publishMenuButton,
							instance._unpublishMenuButton,
							instance._showURLContextMenuButton
						];

						var contextMenu = new A.Toolbar(
							{
								children: children,
								orientation: 'vertical'
							}
						);

						instance._contextMenu = contextMenu;
					},

					_renderFileEntryLoaded: function() {
						var instance = this;

						if (instance.isLeaf()) {
							var fileEntryLoaded = instance.get('fileEntryLoaded');

							instance._closeContextMenuButton.set(DISABLED, !fileEntryLoaded);

							instance.get(CONTENT_BOX).toggleClass('aui-tree-node-loaded', fileEntryLoaded);
						}
					},

					_renderFolderContextMenu: function() {
						var instance = this;

						var children = [
							instance._newFolderContextMenuButton,
							instance._renameContextMenuButton,
							instance._deleteContextMenuButton
						];

						var contextMenu = new A.Toolbar(
							{
								children: children,
								orientation: 'vertical'
							}
						);

						instance._contextMenu = contextMenu;
					},

					_updatePublishButtons: function() {
						var instance = this;

						var publishMenuButton = instance._publishMenuButton;
						var showURLContextMenuButton = instance._showURLContextMenuButton;
						var unpublishMenuButton = instance._unpublishMenuButton;

						var label = instance.get(LABEL);

						var extension = label.substr(label.lastIndexOf('.') + 1);

						if (extension == 'xml') {
							if (instance.get(GADGET_ID) > 0) {
								var unpublishPermission = instance.get(PERMISSIONS).unpublishPermission;

								unpublishMenuButton.set(DISABLED, !unpublishPermission);

								publishMenuButton.hide();
								unpublishMenuButton.show();
							}
							else {
								publishMenuButton.show();
								publishMenuButton.get('boundingBox').addClass('last', true)
								unpublishMenuButton.hide();
							}
						}
						else {
							publishMenuButton.hide();
							unpublishMenuButton.hide();
						}
					}
				}
			}
		);

		var EditableEditor = A.Component.create(
			{
				EXTENDS: A.Editable,

				NAME: 'editable-editor',

				ATTRS: {
					entryId: {
						setter: function(v) {
							return String(v);
						},
						value: ''
					}
				},

				prototype: {
					_afterFocusedChangeEditable: function(event) {
						var instance = this;

						if (instance.get(VISIBLE)) {
							instance.save();
						}
					},

					_defStartEditingFn: function(event) {
						var instance = this;

						EditableEditor.superclass._defStartEditingFn.apply(this, arguments);

						var inputField = instance._comboBox._field;

						inputField.set('width', AUTO);

						inputField.fire('adjustSize');
					}
				}
			}
		);

		var arraySort = function(node1, node2) {
			var instance = this;

			var label1 = node1.get(LABEL);
			var label2 = node2.get(LABEL);

			var returnValue = 0;

			if (!isValue(label1)) {
				if (isValue(label2)) {
					returnValue = 1;
				}
			}
			else if (!isValue(label2)) {
				returnValue = -1;
			}

			if (isString(label1)) {
				label1 = label1.toLowerCase();
			}

			if (isString(label2)) {
				label2 = label2.toLowerCase();
			}

			if (label1 < label2) {
				returnValue = -1;
			}
			else if (label1 > label2) {
				returnValue = 1;
			}

			return returnValue;
		};

		A.TreeNodeEditor = TreeNodeEditor;

		A.TreeNode.nodeTypes.editor = TreeNodeEditor;
	},
	'',
	{
		requires: ['aui-tree-view', 'aui-tree-node', 'aui-overlay-manager', 'aui-toolbar', 'aui-overlay-context']
	}
);