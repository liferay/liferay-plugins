AUI().add(
	'gadget-editor-tree',
	function(A) {
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
								entryId: 'newNode' + label,
								fileEntryLoaded: true,
								isNewEntry: true,
								label: label,
								leaf: isLeaf
							}
						);

						if (instance.get('id') == parentId) {
							instance.appendChild(node);
						}
						else {
							instance.getNodeById(parentId).appendChild(node);
						}

						node.sort();

						node.get('editable').fire('startEditing');
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
								folderId = lastSelected.get('parentNode').get('id');
							}
							else {
								folderId = lastSelected.get('id');
							}
						}
						else {
							folderId = instance.get('id');
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
							function(value) {
								if (value.isLeaf()) {
									fileEntryChildren.push(value);
								}
								else {
									folderChildren.push(value);
								}
							}
						);

						folderChildren.sort(instance._arraySort);

						fileEntryChildren.sort(instance._arraySort);

						A.Array.each(
							folderChildren,
							function(child, index, array) {
								if (index == 0) {
									return;
								}

								instance.insertAfter(child, array[index-1]);
							}
						);

						A.Array.each(
							fileEntryChildren,
							function(child, index, array) {
								if (index == 0) {
									instance.insertAfter(child, folderChildren[folderChildren.length - 1]);

									return;
								}

								instance.insertAfter(child, array[index-1]);
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

						node.addTarget(instance.get('ownerTree'));
					},

					sort: function() {
						var instance = this;

						var parentNode = instance.get('parentNode');

						if (!parentNode) {
							return;
						}

						var siblings = parentNode.getChildren();

						var filteredSiblings = [];

						var isLeaf = instance.isLeaf();

						var ownerTree = instance.get('ownerTree');

						A.Array.each(
							siblings,
							function(sibling) {
								if (sibling.isLeaf() == isLeaf) {
									filteredSiblings.push(sibling);
								}
							}
						);

						if (filteredSiblings.length == 1) {
							if (siblings.length == 1) {
								return;
							}

							if (isLeaf) {
								var lastNode = siblings[siblings.length - 1];

								ownerTree.insertAfter(instance, lastNode);
							}
							else {
								var firstNode = siblings[0];

								ownerTree.insertBefore(instance, firstNode);
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
					},

					sortChildren: function() {
						var instance = this;

						var children = instance.getChildren();

						var folderChildren = [];

						var fileEntryChildren = [];

						var ownerTree = instance.get('ownerTree');

						A.Array.each(
							children,
							function(value) {
								if (value.isLeaf()) {
									fileEntryChildren.push(value);
								}
								else {
									folderChildren.push(value);
								}
							}
						);

						folderChildren.sort(instance._arraySort);

						fileEntryChildren.sort(instance._arraySort);

						A.Array.each(
							folderChildren,
							function(child, index, array) {
								if (index == 0) {
									return;
								}

								ownerTree.insertAfter(child, array[index-1]);
							}
						);

						A.Array.each(
							fileEntryChildren,
							function(child, index, array) {
								if (index == 0) {
									ownerTree.insertAfter(child, folderChildren[folderChildren.length - 1]);

									return;
								}

								ownerTree.insertAfter(child, array[index-1]);
							}
						);
					},

					_afterFileEntryLoadedChange: function(event) {
						var instance = this;

						instance._renderFileEntryLoaded();
					},

					_arraySort: function(node1, node2) {
						label1 = node1.get('label');
						label2 = node2.get('label');

						if(!A.Lang.isValue(label1)) {
							if(!A.Lang.isValue(label2)) {
								return 0;
							}
							else {
								return 1;
							}
						}
						else if(!A.Lang.isValue(label2)) {
							return -1;
						}

						if(A.Lang.isString(label1)) {
							label1 = label1.toLowerCase();
						}
						if(A.Lang.isString(label2)) {
							label2 = label2.toLowerCase();
						}
						if(label1 < label2) {
							return -1;
						}
						else if (label1 > label2) {
							return 1;
						}
						else {
							return 0;
						}
					},

					_onEntryIdChange: function(event) {
						var instance = this;

						event.target.get('editable').set('entryId', event.newVal);
					},

					_renderContextMenu: function() {
						var instance = this;

						var ownerTree = instance.get('ownerTree');

						var newFolderContextMenuButton = new A.ButtonItem(
							{
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get('disabled')) {

										instance.fire('addFolderNode', { parentFolderId: instance.get('id') });

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-addfolder',
								label: 'New Folder'
							}
						);

						newFolderContextMenuButton.addTarget(instance);

						var closeContextMenuButton = new A.ButtonItem(
							{
								disabled: true,
								handler: function(event) {
									var buttonItem = event.target;

									if (!buttonItem.get('disabled')) {
										instance.fire('closeFileEntry', { entryId: instance.get('entryId') });

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

									if (!buttonItem.get('disabled')) {
										instance.fire('showURL', { entryId: instance.get('entryId') });

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

									if (!buttonItem.get('disabled')) {
										instance.fire('renameEntry', { entryId: instance.get('entryId') });

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

									if (!buttonItem.get('disabled')) {
										instance.fire('deleteEntry', { entryId: instance.get('entryId') });

										contextMenuOverlay.hide();
									}
								},
								icon: 'gadgeteditor-delete',
								label: 'Delete'
							}
						);

						deleteContextMenuButton.addTarget(instance);

						var children = [];

						children.push([ renameContextMenuButton, deleteContextMenuButton ]);

						if (instance.isLeaf()) {
							children.unshift(closeContextMenuButton);

							children.push( { type: 'ToolbarSpacer' });

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

						contextMenu.get('contentBox').addClass('gadget-editor-contextmenu-toolbar');

						var contextMenuIcon = A.Node.create('<a href="javascript:;"></a>');

						contextMenuIcon.addClass('gadget-editor-tree-node-contextmenuicon');

						instance.get('contentBox').append(contextMenuIcon);

						var treeActionOverlayManager = ownerTree.get('treeActionOverlayManager');

						var contextMenuOverlay = new A.OverlayContext(
							{
								boundingBox: contextMenu.get('boundingBox'),
								trigger: contextMenuIcon,
								hideOn: 'click',
								showOn: 'click',
								after: {
									visibleChange: function(event) {
										if (event.target.get('visible')) {
											contextMenuIcon.addClass('gadget-editor-tree-node-contextmenuicon-open');
										}
										else {
											contextMenuIcon.removeClass('gadget-editor-tree-node-contextmenuicon-open');
										}
									}
								},
								on: {
									show: function(event) {
										var overlayContext = event.target;

										if (!overlayContext.get('rendered')) {
											contextMenu.render();

											overlayContext.render();
										}

										treeActionOverlayManager.hideAll();

										var activeEditable = ownerTree.get('activeEditable');

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

						var ownerTree = instance.get('ownerTree');

						var editable = new EditableEditor(
							{
								eventType: '',
								entryId: instance.get('entryId'),
								node: instance.get('labelEl'),
								after: {
									startEditing: function(event) {
										ownerTree.set('activeEditable', event.target);
									},
									stopEditing: function(event) {
										ownerTree.set('activeEditable', null);
									}
								},
								on: {
									startEditing: function(event) {
										var editable = event.target;

										if (!editable.get('rendered')) {
											editable.render();
										}

										var ownerTreeBoundingBox = ownerTree.get('boundingBox');

										var region = ownerTreeBoundingBox.get('region');

										var editableY = editable.get('node').getXY()[1];

										if (editableY >= region.bottom) {
											ownerTreeBoundingBox.set('scrollTop', editableY);;
										}

										ownerTreeBoundingBox.setStyle('overflow', 'hidden');
									},
									stopEditing: function(event) {
										ownerTree.get('boundingBox').setStyle('overflow', 'auto');
									}
								}
							}
						);

						editable.addTarget(instance);

						instance.set('editable', editable);
					},

					_renderFileEntryLoaded: function() {
						var instance = this;

						if (!instance.isLeaf()) {
							return;
						}

						if (instance.get('fileEntryLoaded')) {
							instance.get('contentBox').addClass('aui-tree-node-loaded');

							instance._closeContextMenuButton.enable();
						}
						else {
							instance.get('contentBox').removeClass('aui-tree-node-loaded');

							instance._closeContextMenuButton.disable();
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

						if (instance.get('visible')) {
							instance.save();
						}
					},

					_defStartEditingFn: function(event) {
						var instance = this;

						EditableEditor.superclass._defStartEditingFn.apply(this, arguments);

						var inputField = instance._comboBox._field;

						inputField.set('width', 'auto');

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
		requires: ['aui-tree-view', 'aui-tree-node', 'aui-overlay-manager', 'aui-toolbar', 'aui-overlay-context'],
		use: []
	}
);