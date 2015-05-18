AUI.add(
	'gadget-editor-tree',
	function(A) {
		var Lang = A.Lang;
		var isString = Lang.isString;
		var isValue = Lang.isValue;

		var ACTIVE_EDITABLE = 'activeEditable';

		var AUTO = 'auto';

		var BOUNDING_BOX = 'boundingBox';

		var CLICK = 'click';

		var CONTENT_BOX = 'contentBox';

		var CSS_CONTEXT_MENU = 'gadget-editor-tree-node-contextmenuicon icon-sort-down icon-large';

		var CSS_CONTEXT_MENU_OPEN = 'gadget-editor-tree-node-contextmenuicon-open icon-sort-down icon-large';

		var DISABLED = 'disabled';

		var EDITABLE = 'editable';

		var ENTRY_ID = 'entryId';

		var GADGET_ID = 'gadgetId';

		var ID = 'id';

		var IS_ROOT_NODE = 'isRootNode';

		var LABEL = 'label';

		var NEW_NODE = 'newNode';

		var OVERFLOW = 'overflow';

		var OWNER_TREE = 'ownerTree';

		var PARENT_NODE = 'parentNode';

		var PERMISSIONS = 'permissions';

		var RENDERED = 'rendered';

		var STR_EMPTY = '';

		var TPL_ICON_CONTEXT_MENU = '<a href="javascript:;"></a>';

		var VISIBLE = 'visible';

		var TreeViewEditor = A.Component.create(
			{

				ATTRS: {
					activeEditable: {
						validator: Lang.isObject
					},

					publishGadgetPermission: {
						validator: Lang.isBoolean
					},

					treeActionOverlayManager: {
						validator: Lang.isObject,
						valueFn: function() {
							return new A.OverlayManager();
						}
					}
				},

				EXTENDS: A.TreeView,

				NAME: 'tree-view-editor',

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

						if (instance.get(ID) === parentId) {
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

						var fileEntryChildren = [];
						var folderChildren = [];

						children.forEach(
							function(item, index) {
								if (item.isLeaf()) {
									fileEntryChildren.push(item);
								}
								else {
									folderChildren.push(item);
								}
							}
						);

						fileEntryChildren.sort(arraySort);
						folderChildren.sort(arraySort);

						folderChildren.forEach(
							function(item, index, collection) {
								if (index != 0) {
									instance.insertAfter(item, collection[index - 1]);
								}
							}
						);

						fileEntryChildren.forEach(
							function(item, index, collection) {
								if (index === 0) {
									if (folderChildren.length > 0) {
										instance.insertAfter(item, folderChildren[folderChildren.length - 1]);
									}
								}
								else {
									instance.insertAfter(item, collection[index - 1]);
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
				ATTRS: {
					editable: {},

					entryId: {
						setter: function(value) {
							return String(value);
						},
						value: STR_EMPTY
					},

					fileEntryLoaded: {
						value: false
					},

					fileEntryURL: {
						value: STR_EMPTY
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

				EXTENDS: A.TreeNodeIO,

				NAME: 'tree-node-editor',

				prototype: {
					renderUI: function() {
						var instance = this;

						TreeNodeEditor.superclass.renderUI.apply(this, arguments);

						instance._renderContextMenu();
						instance._renderEditable();
						instance._renderFileEntryLoaded();
					},

					bindUI: function() {
						var instance = this;

						TreeNodeEditor.superclass.bindUI.apply(this, arguments);

						instance.after('fileEntryLoadedChange', instance._afterFileEntryLoadedChange);
						instance.after('gadgetIdChange', instance._afterGadgetIdChange);
						instance.after('labelChange', instance._afterLabelChange);
						instance.after('permissionsChange', instance._afterPermissionsChange);

						instance.on('entryIdChange', instance._onEntryIdChange);
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

							siblings.forEach(
								function(sibling) {
									if (sibling.isLeaf() === isLeaf) {
										filteredSiblings.push(sibling);
									}
								}
							);

							filteredSiblings.sort(arraySort);

							if (filteredSiblings.length === 1) {
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

						var fileEntryChildren = [];
						var folderChildren = [];

						var ownerTree = instance.get(OWNER_TREE);

						children.forEach(
							function(item, index) {
								if (item.isLeaf()) {
									fileEntryChildren.push(item);
								}
								else {
									folderChildren.push(item);
								}
							}
						);

						fileEntryChildren.sort(arraySort);
						folderChildren.sort(arraySort);

						folderChildren.forEach(
							function(item, index, collection) {
								if (index != 0) {
									ownerTree.insertAfter(item, collection[index - 1]);
								}
							}
						);

						fileEntryChildren.forEach(
							function(item, index, collection) {
								if (index === 0) {
									if (folderChildren.length > 0) {
										ownerTree.insertAfter(item, folderChildren[folderChildren.length - 1]);
									}
								}
								else {
									ownerTree.insertAfter(item, collection[index - 1]);
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

					_renderButtons: function(isLeaf) {
						var instance = this;

						if (isLeaf) {
							var closeContextMenuButton = new A.Button(
								{
									cssClass: 'close-file-entry',
									disabled: true,
									icon: 'icon-remove',
									label: Liferay.Language.get('close')
								}
							);

							var publishMenuButton = new A.Button(
								{
									cssClass: 'publish',
									disabled: !instance.get(OWNER_TREE).get('publishGadgetPermission'),
									icon: 'icon-bullhorn',
									label: Liferay.Language.get('publish')
								}
							);

							var showURLContextMenuButton = new A.Button(
								{
									cssClass: 'show-url',
									icon: 'icon-link',
									label: Liferay.Language.get('show-url')
								}
							);

							var unpublishMenuButton = new A.Button(
								{
									cssClass: 'unpublish',
									icon: 'icon-bullhorn',
									label: Liferay.Language.get('unpublish')
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
							var newFolderContextMenuButton = new A.Button(
								{
									cssClass: 'add-folder',
									icon: 'icon-folder-close',
									label: Liferay.Language.get('new-folder')
								}
							);

							newFolderContextMenuButton.addTarget(instance);

							instance._newFolderContextMenuButton = newFolderContextMenuButton;
						}

						var deleteContextMenuButton = new A.Button(
							{
								cssClass: 'delete-entry',
								disabled: instance.get(IS_ROOT_NODE),
								icon: 'icon-remove',
								label: Liferay.Language.get('delete')
							}
						);

						var renameContextMenuButton = new A.Button(
							{
								cssClass: 'rename-entry',
								disabled: instance.get(IS_ROOT_NODE),
								icon: 'icon-pencil',
								label: Liferay.Language.get('rename')
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

						instance._renderButtons(isLeaf);

						if (isLeaf) {
							instance._renderFileEntryContextMenu();
						}
						else {
							instance._renderFolderContextMenu();
						}

						var contextMenu = instance._contextMenu;

						contextMenu.get(CONTENT_BOX).addClass('gadget-editor-contextmenu-toolbar');

						var contextMenuIcon = A.Node.create(TPL_ICON_CONTEXT_MENU);

						contextMenuIcon.addClass(CSS_CONTEXT_MENU);

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

											contextMenu.get('children')[0].forEach(
												function(item, index) {
													if (A.instanceOf(item, A.Button)) {
														item.render();
													}
												}
											);

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

						instance._renderContextMenuDelegate(contextMenuOverlay);
					},

					_renderContextMenuDelegate: function(overlay) {
						var instance = this;

						var entry = {};

						overlay.get(BOUNDING_BOX).delegate(
							CLICK,
							function(event) {
								var buttonItem = event.currentTarget;

								entry.entryId = instance.get(ENTRY_ID);

								if (!buttonItem.get(DISABLED)) {
									if (buttonItem.hasClass('add-folder')) {
										instance.fire(
											'addFolderNode',
											{
												parentFolderId: instance.get(ID)
											}
										);
									}
									else if (buttonItem.hasClass('close-file-entry')) {
										instance.fire('closeFileEntry', entry);
									}
									else if (buttonItem.hasClass('delete-entry')) {
										instance.fire('deleteEntry', entry);
									}
									else if (buttonItem.hasClass('publish')) {
										instance.fire('publish', entry);
									}
									else if (buttonItem.hasClass('rename-entry')) {
										instance.fire('renameEntry', entry);
									}
									else if (buttonItem.hasClass('show-url')) {
										instance.fire('showURL', entry);
									}
									else if (buttonItem.hasClass('unpublish')) {
										instance.fire('unpublish', entry);
									}

									instance._contextMenuOverlay.hide();
								}
							},
							'button'
						);
					},

					_renderEditable: function() {
						var instance = this;

						var ownerTree = instance.get(OWNER_TREE);

						var ownerTreeBoundingBox = ownerTree.get(BOUNDING_BOX);

						var editable = new EditableEditor(
							{
								after: {
									startEditing: function(event) {
										ownerTree.set(ACTIVE_EDITABLE, event.target);
									},
									stopEditing: function(event) {
										ownerTree.set(ACTIVE_EDITABLE, null);
									}
								},
								entryId: instance.get(ENTRY_ID),
								eventType: STR_EMPTY,
								node: instance.get('labelEl'),
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
							[
								STR_EMPTY,
								'vertical',
								instance._closeContextMenuButton,
								instance._renameContextMenuButton,
								instance._deleteContextMenuButton,
								instance._publishMenuButton,
								instance._unpublishMenuButton,
								instance._showURLContextMenuButton
							]
						];

						var contextMenu = new A.Toolbar(
							{
								children: children
							}
						);

						instance._contextMenu = contextMenu;
					},

					_renderFileEntryLoaded: function() {
						var instance = this;

						if (instance.isLeaf()) {
							var fileEntryLoaded = instance.get('fileEntryLoaded');

							instance._closeContextMenuButton.set(DISABLED, !fileEntryLoaded);

							instance.get(CONTENT_BOX).toggleClass('tree-node-loaded', fileEntryLoaded);
						}
					},

					_renderFolderContextMenu: function() {
						var instance = this;

						var children = [
							[
								STR_EMPTY,
								'vertical',
								instance._newFolderContextMenuButton,
								instance._renameContextMenuButton,
								instance._deleteContextMenuButton
							]
						];

						var contextMenu = new A.Toolbar(
							{
								children: children
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

						if (extension === 'xml') {
							if (instance.get(GADGET_ID) > 0) {
								var unpublishPermission = instance.get(PERMISSIONS).unpublishPermission;

								unpublishMenuButton.set(DISABLED, !unpublishPermission);

								publishMenuButton.hide();
								unpublishMenuButton.show();
							}
							else {
								publishMenuButton.show();
								publishMenuButton.get('boundingBox').addClass('last', true);
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
				ATTRS: {
					entryId: {
						setter: function(value) {
							return String(value);
						},
						value: STR_EMPTY
					}
				},

				EXTENDS: A.Editable,

				NAME: 'editable-editor',

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
		requires: ['aui-editable-deprecated', 'aui-overlay-context-deprecated', 'aui-overlay-manager-deprecated', 'aui-toolbar', 'aui-tree-node', 'aui-tree-view']
	}
);