Liferay.Service.register("Liferay.Service.KB", "com.liferay.knowledgebase.service", "knowledge-base-portlet");

Liferay.Service.registerClass(
	Liferay.Service.KB, "Article",
	{
		addArticle: true,
		deleteArticle: true,
		deleteArticles: true,
		deleteAttachment: true,
		getArticle: true,
		getArticleAndAllDescendants: true,
		getArticleVersions: true,
		getArticleVersionsCount: true,
		getArticles: true,
		getArticleSearchDisplay: true,
		getGroupArticles: true,
		getGroupArticlesCount: true,
		getLatestArticle: true,
		getSiblingArticles: true,
		getSiblingArticlesCount: true,
		moveArticle: true,
		subscribeArticle: true,
		subscribeGroupArticles: true,
		unsubscribeArticle: true,
		unsubscribeGroupArticles: true,
		updateArticle: true,
		updateAttachments: true,
		updatePriorities: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.KB, "Template",
	{
		addTemplate: true,
		deleteTemplate: true,
		deleteTemplates: true,
		getGroupTemplates: true,
		getGroupTemplatesCount: true,
		getTemplate: true,
		getTemplateSearchDisplay: true,
		updateTemplate: true
	}
);