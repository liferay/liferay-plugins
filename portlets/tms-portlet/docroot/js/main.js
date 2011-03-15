AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	function(A) {
		Liferay.namespace('TMS');

		Liferay.TMS.Tasks = {
			init: function(param) {
				var instance = this;

				instance._setupFilter();
				instance._setupTagsPopup();
				instance._setupPopup();
				instance._setupProgressBar();

				instance._currentTab = param.currentTab;
				instance._taskListURL = param.taskListURL;
			},

			clearFilters: function() {
				var instance = this;

				A.all('.tms-portlet-tasks .asset-tag-filter .asset-tag.selected').toggle('selected');

				var groupFilter = A.one('.tms-portlet-tasks .group-filter select');

				if (groupFilter) {
					groupFilter.set('value', 0);
				}

				var showAll = A.one('.tms-portlet-tasks input[name="all-tasks"]').get('checked');

				instance.updateTaskList(null, showAll);
			},

			closePopup: function() {
				var instance = this;

				instance.getPopup().hide();
			},

			displayPopup: function(url, title) {
				var instance = this;

				var viewportRegion = A.getBody().get('viewportRegion');

				var popup = instance.getPopup();

				popup.show();

				popup.set('title', title);
				popup.set('xy', [viewportRegion.left + 20, viewportRegion.top + 20]);

				popup.io.set('uri', url);
				popup.io.start();
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							cssClass: 'tasks-dialog',
							resizable: false,
							width: 600
						}
					).plug(
						A.Plugin.IO,
						{autoLoad: false}
					).render();
				}

				instance._popup.io.set('form', null);
				instance._popup.io.set('uri', null);

				return instance._popup;
			},

			toggleCommentForm: function() {
				var comment = A.one('.tasks-dialog .add-comment');

				var control = comment.one('.control');
				var form = comment.one('.form');

				form.toggle();
				control.toggle();
			},

			toggleTasksFilter: function() {
				A.one('.tms-portlet-tasks .filter-wrapper').toggle();
			},

			updateTaskList: function(url, showAll) {
				var instance = this;

				instance._taskList = A.one('.tms-portlet-tasks .list-wrapper');

				if (!instance._taskList.io) {
					instance._taskList.plug(
						A.Plugin.IO,
						{autoLoad: false}
					);
				}

				if (!url) {
					url = instance._taskListURL;

					var data = {
						assetTagIds: instance._getAssetTagIds(),
						groupId: instance._getGroupId(),
						tabs1: instance._currentTab,
						tabs2: showAll ? 'all' : 'open'
					};

					instance._taskList.io.set('data', data);
				}

				instance._taskList.io.set('uri', url);

				instance._taskList.io.start();
			},

			_getAssetTagIds: function() {
				var assetTagIds = [];

				A.all('.tms-portlet-tasks .asset-tag-filter .asset-tag.selected').each(
					function(assetTag, index, collection) {
						assetTagIds.push(assetTag.attr('data-assetTagId'));
					}
				);

				return assetTagIds;
			},

			_getGroupId: function() {
				var groupSelect = A.one('.tms-portlet-tasks .group-filter select');

				if (!groupSelect) {
					return 0;
				}

				return groupSelect.get('value');
			},

			_setupFilter: function() {
				var instance = this;

				A.one('.tms-portlet-tasks .asset-tag-filter').delegate(
					'click',
					function(event) {
						var assetTag = event.currentTarget;

						assetTag.toggle('selected');

						var showAll = A.one('.tms-portlet-tasks input[name="all-tasks"]').get('checked');

						instance.updateTaskList(null, showAll);
					},
					'.asset-tag'
				);

				A.all('.tms-portlet-tasks .group-filter select').on(
					'change',
					function(event) {
						var showAll = A.one('.tms-portlet-tasks input[name="all-tasks"]').get('checked');

						instance.updateTaskList(null, showAll);
					}
				);
			},

			_setupTagsPopup: function() {
				var container = A.one('.tms-portlet-tasks');

				container.delegate(
					'mouseover',
					function(event) {
						event.currentTarget.one('.tags').show();
					},
					'.tags-wrapper'
				);

				container.delegate(
					'mouseout',
					function(event) {
						event.currentTarget.one('.tags').hide();
					},
					'.tags-wrapper'
				);
			},

			_setupPopup: function() {
				var instance = this;

				A.one('.tms-portlet-tasks').delegate(
					'click',
					function(event) {
						event.halt();

						var href = event.currentTarget.getAttribute('href');

						if (href) {
							instance.displayPopup(href, "Tasks");
						}
					},
					'a.tasks-title'
				);
			},

			_setupProgressBar: function() {
				var instance = this;

				var portlet = A.one('.tms-portlet-tasks');

				portlet.delegate(
					'mouseover',
					function(event) {
						event = event.currentTarget;

						event.one('.current').hide();
						event.one('.progress-picker').show();
					},
					'.progress-wrapper'
				);

				portlet.delegate(
					'mouseout',
					function(event) {
						event = event.currentTarget;

						event.one('.current').show();
						event.one('.progress-picker').hide();
					},
					'.progress-wrapper'
				);

				portlet.delegate(
					'mouseover',
					function(event) {
						event = event.currentTarget;

						var str = event.getAttribute('class');
						var pos = str.substring(str.indexOf('progress-') + 9);

						var container = event.ancestor('.progress-wrapper');

						container.one('.new-progress').setStyle('width', pos + '%');
						container.one('.progress-indicator').set('text', pos + '% Complete');
					},
					'.progress-selector a'
				);

				portlet.delegate(
					'click',
					function(event) {
						event.halt();

						var href = event.currentTarget.getAttribute('href');

						instance.updateTaskList(href);
					},
					'.progress-selector a'
				);
			}
		}
	}
);