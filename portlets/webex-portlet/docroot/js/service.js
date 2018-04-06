Liferay.Service.register("Liferay.Service.WebEx", "com.liferay.meeting.webex.service", "webex-portlet");

Liferay.Service.registerClass(
	Liferay.Service.WebEx, "WebExAccount",
	{
		addWebExAccount: true,
		deleteWebExAccount: true,
		getWebExAccount: true,
		getWebExSiteWebExAccounts: true,
		getWebExSiteWebExAccountsCount: true,
		updateWebExAccount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.WebEx, "WebExSite",
	{
		addWebExSite: true,
		deleteWebExSite: true,
		fetchSiteKeyWebExSite: true,
		getWebExSite: true,
		getWebExSites: true,
		getWebExSitesCount: true,
		updateWebExSite: true
	}
);