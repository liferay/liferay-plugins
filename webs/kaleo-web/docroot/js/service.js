Liferay.Service.register("Liferay.Service.Kaleo", "com.liferay.portal.workflow.kaleo.service", "kaleo-web");

Liferay.Service.registerClass(
	Liferay.Service.Kaleo, "KaleoDefinition",
	{
		getKaleoDefinitions: true
	}
);