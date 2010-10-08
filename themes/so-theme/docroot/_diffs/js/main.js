AUI().use(
	'aui-base', 'aui-dialog', 'aui-io-plugin',
	function(A) {
		LiferayInc = {
			init: function() {
				var instance = this;

				instance.handleAddContent();
				instance.handleMySitesDropDown();
			},

			handleAddContent: function() {
				var instance = this;

				var addContent = A.one('#soAddContent');

				if (!addContent) {
					return;
				}

				addContent.on(
					'click',
					function() {
						if (!instance._addContentDialog) {
							var viewportRegion = A.getBody().get('viewportRegion');

							instance._addContentDialog = new A.Dialog(
								{
									title: Liferay.Language.get('add-content'),
									width: 250,
									xy: [viewportRegion.left + 20, viewportRegion.top + 20]
								}
							).plug(
								A.Plugin.IO,
								{
									data: {
										doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
										p_l_id: themeDisplay.getPlid(),
										p_p_id: 87,
										p_p_state: 'exclusive'
									},
									on: {
										success: function() {
											AUI().use(
												'liferay-layout-configuration',
												function(A) {
													Liferay.LayoutConfiguration._dialogBody = instance._addContentDialog.bodyNode;

													Liferay.LayoutConfiguration._loadContent();
												}
											);
										}
									},
									uri: themeDisplay.getPathMain() + '/portal/render_portlet'
								}
							).render();
						}

						instance._addContentDialog.show();
					}
				);
			},

			handleMySitesDropDown: function() {
				var menu = A.one('#navigation-top .my-sites');

				if (!menu) {
					return;
				}

				menu.on(
					'mouseenter',
					function(event) {
						menu.addClass('open');
						menu.one('.child-menu').show();
					}
				);

				menu.on(
					'mouseleave',
					function(event) {
						menu.removeClass('open');
						menu.one('.child-menu').hide();
					}
				);
			}
		};
	}
);

AUI().ready(
	function(A) {
		LiferayInc.init();
	}
);
