Liferay.Service.register("Liferay.Service.SSB", "com.liferay.sampleservicebuilder.service", "sample-service-builder-portlet");

Liferay.Service.registerClass(
	Liferay.Service.SSB, "Foo",
	{
		getUser: true,
		getUserSites: true
	}
);