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

		var DISABLED = 'disabled';

		var EDITABLE = 'editable';

		var ENTRY_ID = 'entryId';

		var CSS_CONTEXT_MENU_OPEN = 'gadget-editor-tree-node-contextmenuicon-open';

		var ID = 'id';

		var LABEL = 'label';

		var NEW_NODE = 'newNode';

		var NEW_FOLDER = 'New Folder';

		var OWNER_TREE = 'ownerTree';

		var OVERFLOW = 'overflow';

		var PARENT_NODE = 'parentNode';

		var TPL_ICON_CONTEXT_MENU = '<a href="javascript:;"></a>';

		var VISIBLE = 'visible';

		var TreeViewEditor = A.Component.create(
			{
				EXTENDS: A.TreeView,

				NAME: 'tree-view-editor',

				ATTRS: {
					activeEditable: {},

					treeActionOverlayManager: {
						valueFn: function() {
							return new A.OverlayManager();
						}
					}
				},

				prototype: {
					addNodeToFolder: function(label, isLeaf, parentId) {
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

						if (instance.get() == parentId) {
							instance.appendChild(node);
						}
						else {
							instance.getNodeById(parentId).appendChild(node);
						}

						node.sort();

						node.get(EDITABLE).fire('startEditing');
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

						if (lastSelected) {
							if (lastSelected.isLeaf()) {
								folderId = lastSelected.get(PARENT_NODE).get(ID);
							}
							else {
								folderId = lastSelected.get(ID);
							}
						}
						else {
							folderId = instance.get(ID);
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

						var arraySort = instance._arraySort;

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
									instance.insertAfter(item, folderChildren[folderChildren.length - 1]);
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
						value: 0
					},

					fileEntryLoaded: {
						value: false
					},

					fileEntryURL: {
						value: ''
					},

					isNewEntry: {
						value: false
					}
				},

				prototype: {
					bindUI: function() {
						var instance = this;

						TreeNodeEditor.superclass.bindUI.apply(this, arguments);

						instance.after('fileEntryLoadedChange', instance._afterFileEntryLoadedChange, instance);

						instance.on('entryIdChange', instance._onEntryIdChange, instance);
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

						var arraySort = instance._arraySort;

						folderChildren.sort(arraySort);
						fileEntryChildren.sort(arraySort);

						A.Array.each(
							folderChildren,
							function(item, index, collection) {
								if (index != 0) {
									ownerTree.insertAfter(child, array[index-1]);
								}
							}
						);

						A.Array.each(
							fileEntryChildren,
							function(item, index, collection) {
								if (index == 0) {
									ownerTree.insertAfter(item, folderChildren[folderChildren.length - 1]);
								}
								else {
									ownerTree.insertAfter(item, array[index-1]);
								}
							}
						);
					},

					_afterFileEntryLoadedChange: function(event) {
						var instance = this;

						instance._renderFileEntryLoaded();
					},

					_arraySort: function(node1, node2) {
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
					},

					_onEntryIdChange: function(event) {
						var instance = this;

						event.target.get(EDITABLE).set(ENTRY_ID, event.newVal);
					},

					_renderContextMenu: function() {
						var instance = this;

						var ownerTree = instance.get(OWNER_TREE);

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

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-addfolder',
								label: NEW_FOLDER
							}
						);

						newFolderContextMenuButton.addTarget(instance);

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

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-close',
								label: 'Close'
							}
						);

						instance._closeContextMenuButton = closeContextMenuButton;

						closeContextMenuButton.addTarget(instance);

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

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-url',
								label: 'Show URL'
							}
						);

						showURLContextMenuButton.addTarget(instance);

						var renameContextMenuButton = new A.ButtonItem(
							{
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get(DISABLED)) {
										instance.fire(
											'renameEntry',
											{
												entryId: instance.get(ENTRY_ID)
											}
										);

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-rename',
								label: 'Rename'
							}
						);

						renameContextMenuButton.addTarget(instance);

						var deleteContextMenuButton = new A.ButtonItem(
							{
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get(DISABLED)) {
										instance.fire(
											'deleteEntry',
											{
												entryId: instance.get(ENTRY_ID)
											}
										);

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-delete',
								label: 'Delete'
							}
						);

						deleteContextMenuButton.addTarget(instance);

						var children = [];

						children.push(renameContextMenuButton, deleteContextMenuButton);

						if (instance.isLeaf()) {
							children.unshift(closeContextMenuButton);

							children.push(
								{
									type: 'ToolbarSpacer'
								}
							);

							children.push(showURLContextMenuButton);
						}
						else {
							children.unshift(newFolderContextMenuButton);
						}

						var contextMenu = new A.Toolbar(
							{
								children: children,
								orientation: 'vertical'
							}
						);

						contextMenu.get(CONTENT_BOX).addClass('gadget-editor-contextmenu-toolbar');

						var contextMenuIcon = A.Node.create(TPL_ICON_CONTEXT_MENU);

						contextMenuIcon.addClass('gadget-editor-tree-node-contextmenuicon');

						instance.get(CONTENT_BOX).append(contextMenuIcon);

						var treeActionOverlayManager = ownerTree.get('treeActionOverlayManager');

						var contextMenuOverlay = new A.OverlayContext(
							{
								boundingBox: contextMenu.get(BOUNDING_BOX),
								trigger: contextMenuIcon,
								hideOn: CLICK,
								showOn: CLICK,
								after: {
									render: function(event) {
										contextMenu.render();
										overlayContext.render();
									},
									visibleChange: function(event) {
										contextMenuIcon.toggleClass(CSS_CONTEXT_MENU_OPEN, event.target.get(VISIBLE));
									}
								},
								on: {
									show: function(event) {
										var overlayContext = event.target;

										treeActionOverlayManager.hideAll();

										var activeEditable = ownerTree.get(ACTIVE_EDITABLE);

										if (activeEditable) {
											activeEditable.fire('stopEditing', true);
										}
									}
								}
							}
						);

						treeActionOverlayManager.register(contextMenuOverlay);
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

										if (!editable.get('rendered')) {
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

					_renderFileEntryLoaded: function() {
						var instance = this;

						if (instance.isLeaf()) {
							var fileEntryLoaded = instance.get('fileEntryLoaded');

							instance._closeContextMenuButton.set(DISABLED, !fileEntryLoaded);

							instance.get(CONTENT_BOX).toggleClass('aui-tree-node-loaded', fileEntryLoaded);
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
						value: 0
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

		A.TreeNodeEditor = TreeNodeEditor;

		A.TreeNode.nodeTypes.editor = TreeNodeEditor;
	},
	'',
	{
		requires: ['aui-tree-view', 'aui-tree-node', 'aui-overlay-manager', 'aui-toolbar', 'aui-overlay-context']
	}
);