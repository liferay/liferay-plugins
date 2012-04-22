AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	function(A) {
		Liferay.namespace('Tasks');

		Liferay.Tasks = {
			init: function(param) {
				var instance = this;

				instance._setupFilter();
				instance._setupTagsPopup();
				instance._setupProgressBar();

				instance._currentTab = param.currentTab;
				instance._taskListURL = param.taskListURL;
			},

			clearFilters: function() {
				var instance = this;

				A.all('.tasks-portlet .asset-tag-filter .asset-tag.selected').toggle('selected');

				var groupFilter = A.one('.tasks-portlet .group-filter select');

				if (groupFilter) {
					groupFilter.set('value', 0);
				}

				var showAll = A.one('.tasks-portlet input[name="all-tasks"]').get('checked');

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

				popup.io.set('uri', url);
				popup.io.start();
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							centered: true,
							constrain2view: true,
							cssClass: 'tasks-dialog',
							modal: true,
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

			openTask: function(href) {
				this.displayPopup(href, "Tasks");
			},

			toggleCommentForm: function() {
				var comment = A.one('.tasks-dialog .add-comment');

				var control = comment.one('.control');
				var form = comment.one('.form');

				form.toggle();
				control.toggle();
			},

			toggleTasksFilter: function() {
				A.one('.tasks-portlet .filter-wrapper').toggle();
			},

			updateTaskList: function(url, showAll) {
				var instance = this;

				instance._taskList = A.one('.tasks-portlet .list-wrapper');

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

				A.all('.tasks-portlet .asset-tag-filter .asset-tag.selected').each(
					function(assetTag, index, collection) {
						assetTagIds.push(assetTag.attr('data-assetTagId'));
					}
				);

				return assetTagIds.join(',');
			},

			_getGroupId: function() {
				var groupSelect = A.one('.tasks-portlet .group-filter select');

				if (!groupSelect) {
					return 0;
				}

				return groupSelect.get('value');
			},

			_setupFilter: function() {
				var instance = this;

				A.one('.tasks-portlet .asset-tag-filter').delegate(
					'click',
					function(event) {
						var assetTag = event.currentTarget;

						assetTag.toggleClass('selected');

						var showAll = A.one('.tasks-portlet input[name="all-tasks"]').get('checked');

						instance.updateTaskList(null, showAll);
					},
					'.asset-tag'
				);

				A.all('.tasks-portlet .group-filter select').on(
					'change',
					function(event) {
						var showAll = A.one('.tasks-portlet input[name="all-tasks"]').get('checked');

						instance.updateTaskList(null, showAll);
					}
				);
			},

			_setupTagsPopup: function() {
				var container = A.one('.tasks-portlet');

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

			_setupProgressBar: function() {
				var instance = this;

				var portlet = A.one('.tasks-portlet .list-wrapper');

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