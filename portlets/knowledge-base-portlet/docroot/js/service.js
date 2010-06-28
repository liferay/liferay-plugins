Liferay.Service.register("Liferay.Service.KB", "com.liferay.knowledgebase.service");

Liferay.Service.registerClass(
	Liferay.Service.KB, "Article",
	{
		addArticle: true,
		deleteArticle: true,
		deleteAttachment: true,
		getArticle: true,
		getArticles: true,
		getArticlesCount: true,
		getCompanyArticles: true,
		getCompanyArticlesCount: true,
		getGroupArticles: true,
		getGroupArticlesCount: true,
		getLatestArticle: true,
		subscribe: true,
		subscribeArticle: true,
		unsubscribe: true,
		unsubscribeArticle: true,
		updateArticle: true,
		updateAttachments: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.KB, "Template",
	{
		addTemplate: true,
		deleteTemplate: true,
		getGroupTemplates: true,
		getGroupTemplatesCount: true,
		getTemplate: true,
		updateTemplate: true
	}
);