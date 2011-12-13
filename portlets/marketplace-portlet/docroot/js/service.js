Liferay.Service.register("Liferay.Service.Marketplace", "com.liferay.marketplace.service", "marketplace-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Marketplace, "App",
	{
		deleteApp: true,
		installApp: true,
		uninstallApp: true
	}
);