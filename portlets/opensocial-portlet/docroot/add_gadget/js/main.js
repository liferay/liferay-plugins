(function(A) {
	Liferay.namespace('OpenSocial');

	Liferay.provide(
		Liferay.OpenSocial,
		'addMenuItem',
		function(event) {
			var moreApplicationsNode = A.one('li.add-application.last.more-applications');

			if (!moreApplicationsNode) {
				return;
			}

			var gadget = Liferay.Language.get('gadget');

			var addGadgetNode = A.Node.create('<li class="aui-menu-item"><a href="javascript:;"><span><img alt="' + gadget + '" class="icon" src="/opensocial-portlet/icon.png" title="' + gadget + '"></span> ' + gadget + '</a></li>');

			addGadgetNode.on(
				'click',
				function() {
					Liferay.OpenSocial.addUnderlay();
				}
			);

			moreApplicationsNode.placeBefore(addGadgetNode);

			Liferay.Dockbar.addContent.get('contentBox').focusManager.refresh();
		},
		['aui-base', 'liferay-language']
	);

	Liferay.provide(
		Liferay.OpenSocial,
		'addUnderlay',
		function(event) {
			var portletURL = Liferay.PortletURL.createRenderURL();

			portletURL.setPortletId('3_WAR_opensocialportlet');
			portletURL.setWindowState('exclusive');

			portletURL.setParameter('jspPage', '/add_gadget/add_gadget.jsp');

			Liferay.Dockbar.addUnderlay(
				{
					io: {
						uri: portletURL.toString()
					},
					name: 'addGadgetUnderlay',
					width: '375px'
				}
			);

			Liferay.Dockbar.MenuManager.hideAll();
		},
		['liferay-portlet-url']
	);

	Liferay.after(
	'initDockbar',
	function(event) {
		Liferay.OpenSocial.addMenuItem();
	}
);
})(AUI());