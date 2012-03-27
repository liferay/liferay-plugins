Liferay.Service.register("Liferay.Service.SO", "com.liferay.so.service", "so-portlet");

Liferay.Service.registerClass(
	Liferay.Service.SO, "SocialOffice",
	{
		isSocialOfficeSite: true
	}
);