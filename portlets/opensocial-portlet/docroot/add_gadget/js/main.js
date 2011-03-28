(function(A) {
	var Lang = A.Lang;

	var Dockbar;

	var OpenSocial = Liferay.namespace('OpenSocial');

	var TPL_LIST_ITEM_GADGET = '<li class="aui-menu-item"><a href="javascript:;"><span><img alt="{0}" class="icon" src="/opensocial-portlet/icon.png" title="{0}"></span> {0}</a></li>';

	function initDockbar() {
		if (!Dockbar) {
			Dockbar = Liferay.Dockbar;
		}
	}

	Liferay.provide(
		OpenSocial,
		'addMenuItem',
		function() {
			initDockbar();

			var moreApplicationsNode = A.one('li.add-application.last.more-applications');

			if (moreApplicationsNode) {
				var gadgetTPL = Lang.sub(TPL_LIST_ITEM_GADGET, [Liferay.Language.get('gadget')]);

				var addGadgetNode = A.Node.create(gadgetTPL);

				addGadgetNode.on('click', OpenSocial.addUnderlay, OpenSocial);

				moreApplicationsNode.placeBefore(addGadgetNode);

				Dockbar.addContent.get('contentBox').focusManager.refresh();
			}
		},
		['aui-base', 'liferay-language']
	);

	Liferay.provide(
		OpenSocial,
		'addUnderlay',
		function(event) {
			var portletURL = Liferay.PortletURL.createRenderURL();

			portletURL.setPortletId('3_WAR_opensocialportlet');
			portletURL.setWindowState('exclusive');

			portletURL.setParameter('jspPage', '/add_gadget/add_gadget.jsp');

			Dockbar.addUnderlay(
				{
					io: {
						uri: portletURL.toString()
					},
					name: 'addGadgetUnderlay',
					width: '375px'
				}
			);

			Dockbar.MenuManager.hideAll();
		},
		['liferay-portlet-url']
	);

	Liferay.after(
		'initDockbar',
		function(event) {
			OpenSocial.addMenuItem();
		}
	);
})(AUI());