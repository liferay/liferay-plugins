<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
			Map<String, String[]> parameters = new HashMap<String, String[]>();

			parameters.put(PortletDataHandlerKeys.PORTLET_DATA_ALL, new String[] {Boolean.TRUE.toString()});

			File file = LayoutLocalServiceUtil.exportLayoutsAsFile(group.getGroupId(), false, null, parameters, null, null);

			FileUtil.copyFile(file, new File(application.getRealPath("/WEB-INF/classes/test/lar/archive.lar")));
		}

		GroupLocalServiceUtil.deleteGroup(group);
	}

	String resourcesPath = application.getRealPath("/WEB-INF/classes/resources-importer");

	FileUtil.deltree(resourcesPath);
	FileUtil.mkdirs(resourcesPath);

	if (importer.equals("lar") || importer.equals("resource")) {
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

		LayoutLocalServiceUtil#getLayoutsCount=<%= _assertEquals(5, LayoutLocalServiceUtil.getLayoutsCount(group, false)) %><br />

		<%
		UnicodeProperties layoutTypeSettingsProperties = importedLayout.getTypeSettingsProperties();

		String nestedColumnIds = layoutTypeSettingsProperties.get(LayoutTypePortletConstants.NESTED_COLUMN_IDS);
		%>

		LayoutTypePortletConstants#NESTED_COLUMN_IDS=<%= _assertTrue((nestedColumnIds != null) && nestedColumnIds.contains("column-1") && nestedColumnIds.contains("column-2")) %><br />
	</p>

	<p>

		<%
		DLFileEntry dlFileEntry = null;

		String[] assetTagNames = null;

		try {
			dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "company_logo");

			assetTagNames = AssetTagLocalServiceUtil.getTagNames(DLFileEntry.class.getName(), dlFileEntry.getFileEntryId());
		}
		catch (Exception e) {
		}
		%>

		AssetTagLocalServiceUtil#getTagNames=<%= _assertTrue(ArrayUtil.contains(assetTagNames, "logo")) %>
	</p>

	<p>
		DLFileEntryLocalServiceUtil#getFileEntriesCount=<%= _assertEquals(1, DLFileEntryLocalServiceUtil.getFileEntriesCount(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) %><br />
	</p>

	<p>

		<%
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticle(groupId, "CHILD-WEB-CONTENT-1");
		%>

		JournalArticle#getDescription=<%= _assertTrue(Validator.isNotNull(journalArticle.getDescription())) %><br />

		JournalArticle#isSmallImage=<%= _assertTrue(journalArticle.isSmallImage()) %><br />

		JournalArticle#isTemplateDriven=<%= _assertTrue(journalArticle.isTemplateDriven()) %><br />

		JournalArticleLocalService#getArticlesCount=<%= _assertEquals(5, JournalArticleLocalServiceUtil.getArticlesCount(groupId)) %><br />

		<%
		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(groupId, PortalUtil.getClassNameId(JournalArticle.class), "CHILD-STRUCTURE-1");

		long parentStructureId = ddmStructure.getParentStructureId();

		String parentStructureKey = StringPool.BLANK;

		DDMStructure parentDDMStructure = DDMStructureLocalServiceUtil.fetchStructure(parentStructureId);

		if (parentDDMStructure != null) {
			parentStructureKey = parentDDMStructure.getStructureKey();
		}
		%>

		DDMStructure#getParentStructureId=<%= _assertEquals("PARENT-STRUCTURE", parentStructureKey) %><br />

		DDMStructureLocalServiceUtil#getStructuresCount=<%= _assertEquals(3, DDMStructureLocalServiceUtil.getStructuresCount(groupId)) %><br />

		<%
		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(groupId, PortalUtil.getClassNameId(DDMStructure.class), "CHILD-TEMPLATE-1");

		DDMStructure ddmTemplateStructure = DDMStructureLocalServiceUtil.fetchDDMStructure(ddmTemplate.getClassPK());

		String ddmStructureKey = StringPool.BLANK;

		if (ddmTemplateStructure != null) {
			ddmStructureKey = ddmTemplateStructure.getStructureKey();
		}
		%>

		DDMTemplate#getStructureId=<%= _assertEquals("CHILD-STRUCTURE-1", ddmStructureKey) %><br />

		DDMTemplateLocalServiceUtil#getTemplatesCount=<%= _assertEquals(2, DDMTemplateLocalServiceUtil.getTemplatesCount(groupId)) %><br />
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
%>