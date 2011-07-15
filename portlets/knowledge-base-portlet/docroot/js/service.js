Liferay.Service.register("Liferay.Service.KB", "com.liferay.knowledgebase.service", "knowledge-base-portlet");

Liferay.Service.registerClass(
	Liferay.Service.KB, "KBArticle",
	{
		addKBArticle: true,
		deleteAttachment: true,
		deleteKBArticle: true,
		deleteKBArticles: true,
		getGroupKBArticles: true,
		getGroupKBArticlesCount: true,
		getKBArticle: true,
		getKBArticleAndAllDescendants: true,
		getKBArticleSearchDisplay: true,
		getKBArticleVersions: true,
		getKBArticleVersionsCount: true,
		getKBArticles: true,
		getLatestKBArticle: true,
		getSectionsKBArticles: true,
		getSectionsKBArticlesCount: true,
		getSiblingKBArticles: true,
		getSiblingKBArticlesCount: true,
		moveKBArticle: true,
		subscribeGroupKBArticles: true,
		subscribeKBArticle: true,
		unsubscribeGroupKBArticles: true,
		unsubscribeKBArticle: true,
		updateAttachments: true,
		updateKBArticle: true,
		updateKBArticlesPriorities: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.KB, "KBTemplate",
	{
		addKBTemplate: true,
		deleteKBTemplate: true,
		deleteKBTemplates: true,
		getGroupKBTemplates: true,
		getGroupKBTemplatesCount: true,
		getKBTemplate: true,
		getKBTemplateSearchDisplay: true,
		updateKBTemplate: true
	}
);