<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
if (!themeDisplay.isSignedIn()) {
%>

	Please sign in to run the test.

<%
	return;
}

Group group = null;

String[] importers = {"custom", "lar", "resource"};

for (String importer : importers) {
	if (group == null) {
		group = GroupLocalServiceUtil.fetchGroup(themeDisplay.getCompanyId(), "Test Resources Importer Portlet");
	}

	if (group != null) {
		if (importer.equals("lar")) {
			File privateLAR = _exportLayoutsAsFile(group, true);

			FileUtil.copyFile(privateLAR, new File(application.getRealPath("/WEB-INF/classes/test/lar/private.lar")));

			File publicLAR = _exportLayoutsAsFile(group, false);

			FileUtil.copyFile(publicLAR, new File(application.getRealPath("/WEB-INF/classes/test/lar/public.lar")));
		}

		GroupLocalServiceUtil.deleteGroup(group);
	}

	String resourcesPath = application.getRealPath("/WEB-INF/classes/resources-importer");

	FileUtil.deltree(resourcesPath);

	if (importer.equals("lar") || importer.equals("resource")) {
		FileUtil.mkdirs(resourcesPath);

		String importerPath = application.getRealPath("WEB-INF/classes/test/" + importer);

		FileUtil.copyDirectory(importerPath, resourcesPath);
	}

	Message message = new Message();

	message.put("command", "deploy");
	message.put("servletContextName", "test-resources-importer-portlet");

	message.setResponseDestinationName("liferay/resources_importer");

	long groupId = 0;

	try {
		Map<String, Object> responseMap = (Map<String, Object>)MessageBusUtil.sendSynchronousMessage(DestinationNames.HOT_DEPLOY, message);

		groupId = GetterUtil.getLong(responseMap.get("groupId"));
	}
	catch (Exception e) {
	}

	PluginPackage pluginPackage = DeployManagerUtil.getInstalledPluginPackage("test-resources-importer-portlet");

	String keySuffix = StringPool.DASH + pluginPackage.getVersion();
%>

	<h3>
		<c:choose>
			<c:when test='<%= importer.equals("custom") %>'>
				Custom Resource Directory
			</c:when>
			<c:when test='<%= importer.equals("lar") %>'>
				Default LAR File
			</c:when>
			<c:when test='<%= importer.equals("resource") %>'>
				Default Resource Directory
			</c:when>
		</c:choose>
	</h3>

	<p>

		<%
		group = GroupLocalServiceUtil.fetchGroup(groupId);
		%>

		GroupLocalServiceUtil#fetchGroup=<%= _assertTrue(group != null) %><br />

		<%
		if (groupId == 0) {
			continue;
		}
		%>

		<%
		Layout importedLayout = LayoutLocalServiceUtil.getLayout(groupId, false, 1);

		Map<Locale, String> nameMap = importedLayout.getNameMap();
		%>

		Layout#getNameMap=<%= _assertTrue(nameMap.containsValue("Bienvenue")) %><br />
		LayoutLocalServiceUtil#getLayoutsCount(group, false)=<%= _assertEquals(9, LayoutLocalServiceUtil.getLayoutsCount(group, false)) %><br />
		LayoutLocalServiceUtil#getLayoutsCount(group, true)=<%= _assertEquals(1, LayoutLocalServiceUtil.getLayoutsCount(group, true)) %><br />

		<%
		UnicodeProperties layoutTypeSettingsProperties = importedLayout.getTypeSettingsProperties();

		String nestedColumnIds = layoutTypeSettingsProperties.get(LayoutTypePortletConstants.NESTED_COLUMN_IDS);
		%>

		LayoutTypePortletConstants#NESTED_COLUMN_IDS=<%= _assertTrue((nestedColumnIds != null) && nestedColumnIds.contains("column-1") && nestedColumnIds.contains("column-2")) %>
	</p>

	<p>

		<%
		String[] assetTagNames = null;

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchFileEntry(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "company_logo");

		if (dlFileEntry != null) {
			assetTagNames = AssetTagLocalServiceUtil.getTagNames(DLFileEntry.class.getName(), dlFileEntry.getFileEntryId());
		}
		%>

		AssetTagLocalServiceUtil#getTagNames=<%= _assertTrue(ArrayUtil.contains(assetTagNames, "logo")) %>
	</p>

	<p>

		<%
		DLFolder dlFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Parent Folder");

		if (dlFolder != null) {
			dlFileEntry = DLFileEntryLocalServiceUtil.fetchFileEntry(groupId, dlFolder.getFolderId(), "child_document");
		}
		%>

		DLFolderLocalServiceUtil#fetchFolder=<%= _assertTrue(dlFolder != null) %><br />
		DLFileEntryLocalServiceUtil#fetchFileEntry=<%= _assertTrue(dlFileEntry != null) %><br />
		DLFileEntryLocalServiceUtil#getFileEntriesCount=<%= _assertEquals(1, DLFileEntryLocalServiceUtil.getFileEntriesCount(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) %>
	</p>

	<p>

		<%
			JournalArticle journalArticle = null;

			try {
				journalArticle = JournalArticleLocalServiceUtil.getArticle(groupId, "CHILD-WEB-CONTENT-1");
			}
			catch (NoSuchArticleException nsae) {
			}
		%>

		<c:choose>
			<c:when test="<%= journalArticle == null %>">
				JournalArticleLocalServiceUtil#getArticle=<%= _assertTrue(journalArticle != null) %><br />
			</c:when>
			<c:otherwise>
				JournalArticle#getDescription=<%= _assertTrue(Validator.isNotNull(journalArticle.getDescription())) %><br />
				JournalArticle#isSmallImage=<%= _assertTrue(journalArticle.isSmallImage()) %><br />
				JournalArticle#isTemplateDriven=<%= _assertTrue(journalArticle.isTemplateDriven()) %><br />
				JournalArticleLocalService#getArticlesCount=<%= _assertEquals(2, JournalArticleLocalServiceUtil.getArticlesCount(groupId)) %><br />
			</c:otherwise>
		</c:choose>
	</p>

	<p>

		<%
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPrototype.class);

		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("name")));

		Criterion layout1Criterion = RestrictionsFactoryUtil.like("name", "%Page 1%");
		Criterion layout2Criterion = RestrictionsFactoryUtil.like("name", "%Page 2%");

		dynamicQuery.add(RestrictionsFactoryUtil.or(layout1Criterion, layout2Criterion));

		List<Object> layoutPrototypes = LayoutPrototypeLocalServiceUtil.dynamicQuery(dynamicQuery);
		%>

		LayoutPrototype#getLayoutPrototypesCount=<%= _assertEquals(2, layoutPrototypes.size()) %>
	</p>

	<p>

		<%
		DDMStructure ddmStructure = null;

		String parentDDMStructureKey = StringPool.BLANK;

		try {
			ddmStructure = DDMStructureLocalServiceUtil.getStructure(groupId, PortalUtil.getClassNameId(JournalArticle.class), "CHILD-STRUCTURE-1" + keySuffix);

			DDMStructure parentDDMStructure = DDMStructureLocalServiceUtil.fetchStructure(ddmStructure.getParentStructureId());

			if (parentDDMStructure != null) {
				parentDDMStructureKey = parentDDMStructure.getStructureKey();
			}
		}
		catch (NoSuchStructureException nsse) {
		}
		%>

		<c:choose>
			<c:when test="<%= ddmStructure == null %>">
				DDMStructureLocalServiceUtil#getStructure=<%= _assertTrue(ddmStructure != null) %><br />
			</c:when>
			<c:otherwise>
				DDMStructure#getParentStructureId=<%= _assertEquals("PARENT-STRUCTURE" + keySuffix, parentDDMStructureKey) %><br />
				DDMStructureLocalServiceUtil#getStructuresCount(groupId, DDLRecordSet)=<%= _assertEquals(2, DDMStructureLocalServiceUtil.getStructuresCount(groupId, PortalUtil.getClassNameId(DDLRecordSet.class))) %><br />
				DDMStructureLocalServiceUtil#getStructuresCount=<%= _assertEquals(5, DDMStructureLocalServiceUtil.getStructuresCount(groupId)) %>
			</c:otherwise>
		</c:choose>
	</p>

	<p>

		<%
		DDMTemplate ddmTemplate1 = null;
		DDMTemplate ddmTemplate2 = null;

		String ddmStructureKey = StringPool.BLANK;

		try {
			ddmTemplate1 = DDMTemplateLocalServiceUtil.getTemplate(groupId, PortalUtil.getClassNameId(DDMStructure.class), "CHILD-TEMPLATE-1" + keySuffix);
			ddmTemplate2 = DDMTemplateLocalServiceUtil.getTemplate(groupId, PortalUtil.getClassNameId(AssetCategory.class), "ASSET-CATEGORY" + keySuffix);

			DDMStructure ddmTemplateStructure = null;

			if (ddmTemplate1 != null) {
				ddmTemplateStructure = DDMStructureLocalServiceUtil.fetchDDMStructure(ddmTemplate1.getClassPK());
			}

			if (ddmTemplateStructure != null) {
				ddmStructureKey = ddmTemplateStructure.getStructureKey();
			}
		}
		catch (NoSuchTemplateException nste) {
		}
		%>

		<c:choose>
			<c:when test="<%= ddmTemplate1 == null %>">
				DDMTemplateLocalServiceUtil#getTemplate=<%= _assertTrue(ddmStructure != null) %><br />
			</c:when>
			<c:otherwise>
				DDMTemplate#getLanguage=<%= _assertEquals("ftl", ddmTemplate2.getLanguage()) %><br />
				DDMTemplate#getStructureId=<%= _assertEquals("CHILD-STRUCTURE-1" + keySuffix, ddmStructureKey) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, AssetCategory)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(AssetCategory.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, AssetEntry)=<%= _assertEquals(2, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(AssetEntry.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, AssetTag)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(AssetTag.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, BlogsEntry)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(BlogsEntry.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, DDMStructure)=<%= _assertEquals(8, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(DDMStructure.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, FileEntry)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(FileEntry.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, LayoutSet)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(LayoutSet.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount(groupId, WikiPage)=<%= _assertEquals(1, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId, PortalUtil.getClassNameId(WikiPage.class))) %><br />
				DDMTemplateLocalServiceUtil#getTemplatesCount=<%= _assertEquals(16, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId)) %><br />
			</c:otherwise>
		</c:choose>
	</p>

	<p>

		<%
		SearchContext searchContext = SearchContextFactory.getInstance(request);

		Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);

		assetEntriesFacet.setStatic(true);

		searchContext.addFacet(assetEntriesFacet);

		Facet scopeFacet = new ScopeFacet(searchContext);

		scopeFacet.setStatic(true);

		searchContext.addFacet(scopeFacet);

		searchContext.setKeywords("Child");

		Indexer indexer = FacetedSearcher.getInstance();

		Hits hits = indexer.search(searchContext);
		%>

		Indexer#search=<%= _assertTrue(hits.getLength() == 2) %><br />
	</p>

<%
}
%>

<%!
private static String _assertEquals(Object expected, Object actual) {
	return _assertTrue(Validator.equals(expected, actual));
}

private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private static File _exportLayoutsAsFile(Group group, boolean privateLayout) throws PortalException, SystemException {
	Map<String, String[]> parameters = new HashMap<String, String[]>();

	parameters.put(PortletDataHandlerKeys.IGNORE_LAST_PUBLISH_DATE, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.LAYOUT_SET_SETTINGS, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.LOGO, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PERMISSIONS, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_CONFIGURATION, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_DATA, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_DATA_ALL, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_DATA + StringPool.UNDERLINE + AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_SETUP_ALL, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.THEME_REFERENCE, new String[] {Boolean.TRUE.toString()});
	parameters.put(PortletDataHandlerKeys.PORTLET_SETUP_ALL, new String[] {Boolean.TRUE.toString()});

	List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(group.getGroupId(), privateLayout);

	long[] layoutIds = new long[layouts.size()];

	for (int i = 0; i < layoutIds.length; i++) {
		Layout layout = layouts.get(i);

		layoutIds[i] = layout.getLayoutId();
	}

	return LayoutLocalServiceUtil.exportLayoutsAsFile(group.getGroupId(), privateLayout, layoutIds, parameters, null, null);
}
%>