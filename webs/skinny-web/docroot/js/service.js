Liferay.Service.register("Liferay.Service.Skinny", "com.liferay.skinny.service", "skinny-web");

Liferay.Service.registerClass(
	Liferay.Service.Skinny, "Skinny",
	{
		getSkinnyDDLRecords: true,
		getSkinnyJournalArticles: true
	}
);